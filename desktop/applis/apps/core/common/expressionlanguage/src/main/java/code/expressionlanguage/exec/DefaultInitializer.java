package code.expressionlanguage.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecAnnotationMethodBlock;
import code.expressionlanguage.exec.blocks.ExecFieldBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.ForwardPageEl;
import code.expressionlanguage.exec.calls.StaticInitPageEl;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.calls.util.ReadWrite;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.stds.AbstractInterceptorStdCaller;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.core.IndexConstants;

public class DefaultInitializer implements Initializer {

    @Override
    public final Struct processInit(ContextEl _context, Struct _parent,
                                    ExecFormattedRootBlock _className, String _fieldName, int _ordinal) {
        CustList<ClassFieldStruct> fields_ = feedFields(_context, _className);
        return init(_context, _parent, _className, _fieldName, _ordinal, fields_);
    }

    public final CustList<ClassFieldStruct> feedFields(ContextEl _context, ExecFormattedRootBlock _className) {
        ExecFormattedRootBlock base_ = ExecFormattedRootBlock.gene(_className);
        CustList<ExecFormattedRootBlock> allClasses_ = new CustList<ExecFormattedRootBlock>(base_);
        allClasses_.addAllElts(base_.getRootBlock().getAllGenericSuperTypes());
        CustList<ClassFieldStruct> fields_ = new CustList<ClassFieldStruct>();
        for (ExecFormattedRootBlock c: allClasses_) {
            String preFormatted_ = c.getFormatted();
            String id_ = StringExpUtil.getIdFromAllTypes(preFormatted_);
            ExecFormattedRootBlock formatted_ = ExecFormattedRootBlock.quickFormat(_className, c);
            ExecRootBlock rootBlock_ = c.getRootBlock();
            for (ExecFieldBlock b: rootBlock_.getInstanceFields()) {
                String fieldDeclClass_ = ExecInherits.quickFormat(formatted_, b.getImportedClassName());
                for (String f: b.getFieldName()) {
                    ClassField key_ = new ClassField(id_, f);
                    fields_.add(new ClassFieldStruct(key_, ExecClassArgumentMatching.defaultValue(fieldDeclClass_, _context)));
                }
            }
        }
        return fields_;
    }

    @Override
    public final Struct processInitAnnot(ContextEl _context,
                                         ExecFormattedRootBlock _className,ExecRootBlock _rootBlock) {
        String baseClass_ = StringExpUtil.getIdFromAllTypes(_className.getFormatted());
        CustList<ClassFieldStruct> fields_ = new CustList<ClassFieldStruct>();
        for (ExecAnnotationMethodBlock b: _rootBlock.getAnnotationsFields()) {
            Struct str_ = b.getDefaultArgument();
            String fieldName_ = b.getName();
            String fieldDeclClass_ = b.getImportedReturnType();
            ClassField key_ = new ClassField(baseClass_, fieldName_);
            if (str_ != null) {
                fields_.add(new ClassFieldStruct(key_, str_));
            } else {
                fields_.add(new ClassFieldStruct(key_, ExecClassArgumentMatching.defaultValue(fieldDeclClass_, _context)));
            }
        }
        return new AnnotationStruct(_className.getFormatted(), fields_);
    }
    @Override
    public final void loopCalling(ContextEl _owner, StackCall _stackCall) {
        AbstractPageEl first_ = _stackCall.getCall(0);
        AbstractInterceptorStdCaller caller_ = _owner.getCaller();
        if (caller_.stopNormal(this,_owner, _stackCall)) {
            endLoop(_stackCall, first_);
            return;
        }
        _stackCall.getBreakPointInfo().getBreakPointOutputInfo().setStoppedBreakPoint(StopDbgEnum.NONE);
        _stackCall.getBreakPointInfo().getBreakPointOutputInfo().setCallingStateSub(null);
        while (true) {
            if (caller_.stop(this,_owner, _stackCall)) {
                endLoop(_stackCall, first_);
                return;
            }
        }
    }

    private void endLoop(StackCall _stackCall, AbstractPageEl _first) {
        notVisit(_stackCall);
        _stackCall.setReturnedArgument(ArgumentListCall.toStr(_first.getReturnedArgument()));
        _stackCall.setWrapper(_first.getWrapper());
    }

    private void notVisit(StackCall _stackCall) {
        if (!_stackCall.hasPages()) {
            _stackCall.getBreakPointInfo().getBreakPointOutputInfo().setStoppedBreakPoint(StopDbgEnum.NONE);
        }
    }

    public boolean stop(ContextEl _owner, StackCall _stackCall) {
        StopDbgEnum status_ = _stackCall.getStopper().stopBreakPoint(_owner, _stackCall);
        if (status_ != StopDbgEnum.NONE) {
            _stackCall.getBreakPointInfo().getBreakPointOutputInfo().setStoppedBreakPoint(status_);
            _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().setPreviousNbPages(_stackCall.nbPages());
            _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().setPreviousNbBlocks(_stackCall.getLastPage().nbBlock());
            _owner.tmpList().clear();
            return true;
        }
        return stopNormal(_owner, _stackCall);
    }

    public boolean stopNormal(ContextEl _owner, StackCall _stackCall) {
        int code_ = stopIntern(_owner, _stackCall);
        if (code_ == 1) {
            return true;
        }
        if (code_ == 0) {
            return exitAfterCallInt(_owner, _stackCall);
        }
        return false;
    }

    public int stopIntern(ContextEl _owner, StackCall _stackCall) {
        if (_stackCall.getStopper().stopAt(_stackCall)) {
            _stackCall.getBreakPointInfo().getStackState().setCheckingBp(false);
            if (_stackCall.normalCallNoExit(_owner)) {
                return 0;
            }
            afterCheckExit(_owner, _stackCall);
            afterCheckCalculate(_owner, _stackCall);
        }
        AbstractPageEl p_ = _stackCall.getLastPage();
        ReadWrite rw_ = _stackCall.getReadWrite();
        if (rw_ == ReadWrite.EXIT) {
            success(_owner, p_);
            _stackCall.removeLastPage();
            if (!_stackCall.hasPages()) {
                return 1;
            }
            if (_stackCall.getStopper().isStopAtExcMethod()) {
                _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().setExiting(p_);
                _stackCall.getBreakPointInfo().getStackState().resetVisitAndCheckBp();
                return -1;
            }
            AbstractPageEl b_ = _stackCall.getLastPage();
            tryForward(_owner, p_, b_, _stackCall);
            _stackCall.entryReadWrite();
        } else if (rw_ == ReadWrite.EXIT_FAIL) {
            Struct custCause_ = _owner.getLocks().processErrorClass(_owner, p_.getThrown().getStruct(), p_, _stackCall);
            _stackCall.removeLastPage();
            if (!_stackCall.hasPages()) {
                _stackCall.setCallingState(new CustomFoundExc(custCause_,_stackCall));
                return 1;
            }
            p_.setThrown(new CustomFoundExc(custCause_,_stackCall));
            if (_stackCall.getStopper().isStopAtExcMethod()) {
                _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().setExiting(p_);
                _stackCall.setCallingState(p_.getThrown());
                _stackCall.getBreakPointInfo().getStackState().resetVisitAndCheckBp();
                return -1;
            }
            _stackCall.setCallingState(p_.getThrown());
            _stackCall.entryReadWrite();
        }
        iterate(_owner, _stackCall);
        return 0;
    }
    private void success(ContextEl _owner, AbstractPageEl _p) {
        if (_p instanceof StaticInitPageEl) {
            ((StaticInitPageEl) _p).sucessClass(_owner);
        }
    }

    private void afterCheckExit(ContextEl _owner, StackCall _stackCall) {
        AbstractPageEl ex_ = _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting();
        if (ex_ != null) {
            if (_stackCall.getReadWrite() == ReadWrite.EXIT) {
                tryForward(_owner, ex_, _stackCall.getLastPage(), _stackCall);
            } else {
                _stackCall.setCallingState(ex_.getThrown());
            }
            _stackCall.getBreakPointInfo().getStackState().visitedNone();
            _stackCall.entryReadWrite();
        }
        _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().setExiting(null);
    }

    private void afterCheckCalculate(ContextEl _owner, StackCall _stackCall) {
        ArgumentWrapper ex_ = _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getCalculated();
        if (ex_ != null) {
            AbstractPageEl p_ = _stackCall.getLastPage();
            ExpressionLanguage el_ = p_.getLastEl();
            el_.setArgument(ex_.getWrapper(),ex_.getValue(),_owner,_stackCall);
            _stackCall.getBreakPointInfo().getStackState().visitedNone();
            _stackCall.entryReadWrite();
        }
        _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().setCalculated(null);
    }

    private void iterate(ContextEl _owner, StackCall _stackCall) {
        if (_stackCall.normalCallNoExit(_owner)) {
            checkCall(_owner, _stackCall);
            return;
        }
        if (!_owner.callsOrException(_stackCall)) {
            _stackCall.getLastPage().processTagsBase(_owner, _stackCall);
            checkStack(_owner, _stackCall);
            checkCall(_owner, _stackCall);
        }
        if (!_stackCall.getStopper().stopAt(_stackCall)) {
            visitException(_stackCall);
        }
    }

    private void checkCall(ContextEl _owner, StackCall _stackCall) {
        if (_stackCall.getStopper().callsOrException(_owner, _stackCall)) {
            _stackCall.getBreakPointInfo().getStackState().resetVisitAndCheckBp();
        }
        if (_stackCall.trueException() != null) {
            _stackCall.stackView(_owner);
        }
    }

    private void visitException(StackCall _stackCall) {
        ExecutingUtil.processException(_stackCall);
        if (_stackCall.getLastPage().getThrown() != null && _stackCall.getStopper().isStopAtExcMethod()) {
            _stackCall.getBreakPointInfo().getStackState().resetVisitAndCheckBp();
        }
    }

    public static void checkStack(ContextEl _owner, StackCall _stackCall) {
        if (_stackCall.calls() && _stackCall.getCallingState() != null && _owner.getStackOverFlow() >= IndexConstants.FIRST_INDEX && _owner.getStackOverFlow() <= _stackCall.nbPages()) {
            LgNames stds_ = _owner.getStandards();
            String sof_ = stds_.getContent().getCoreNames().getAliasSof();
            CustomFoundExc exec_ = new CustomFoundExc(new ErrorStruct(_owner, sof_, _stackCall));
            _stackCall.setCallingState(exec_);
        }
    }

    private static void tryForward(ContextEl _owner, AbstractPageEl _p, AbstractPageEl _b, StackCall _stackCall) {
        if (_p instanceof ForwardPageEl) {
            _p.forwardTo(_b, _owner, _stackCall);
        } else if (_p instanceof StaticInitPageEl) {
            StaticInitPageEl s_ = (StaticInitPageEl) _p;
            Argument fwd_ = s_.getFwd();
            if (fwd_ != null) {
                _b.receive(null, fwd_, _owner, _stackCall);
            }
        }
    }

    public boolean exitAfterCallInt(ContextEl _owner, StackCall _stack) {
        return _owner.stopped() || exitAfterCall(_owner, _stack);
    }
    protected boolean exitAfterCall(ContextEl _owner, StackCall _stack) {
        if (_stack.getStopper().stopAt(_stack)) {
            return false;
        }
        AbstractPageEl abs_ = ExecutingUtil.processAfterOperation(_owner, _stack);
        if (abs_ != null) {
            ExecutingUtil.addPage(abs_, _stack);
        }
        return _stack.isFailInit();
    }

    protected Struct init(ContextEl _context, Struct _parent,
                          ExecFormattedRootBlock _className, String _fieldName, int _ordinal, CustList<ClassFieldStruct> _fields) {
        return new InnerCustStruct(_className.getFormatted(), _fields, _parent, _parent.getClassName(_context), _ordinal, _fieldName);
    }
}
