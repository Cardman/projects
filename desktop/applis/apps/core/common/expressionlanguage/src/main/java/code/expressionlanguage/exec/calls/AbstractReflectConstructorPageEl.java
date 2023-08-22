package code.expressionlanguage.exec.calls;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.exec.MetaInfoUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.CustomFoundExc;
import code.expressionlanguage.exec.inherits.ExecInheritsAdv;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.structs.ConstructorMetaInfo;
import code.expressionlanguage.structs.ErrorStruct;
import code.util.CustList;

public abstract class AbstractReflectConstructorPageEl extends AbstractReflectPageEl {
    private boolean initClass;
    private boolean calledMethod;
    private int checkedParams;
    private boolean checkingEntryExit;

    protected AbstractReflectConstructorPageEl(boolean _lambda) {
        super(_lambda);
    }

    @Override
    public void receive(AbstractWrapper _wrap, Argument _argument, ContextEl _context, StackCall _stack) {
        setWrapper(_wrap);
        setReturnedArgument(_argument);
    }
    protected boolean keep(GeneType _gene,ContextEl _context, StackCall _stackCall) {
        LgNames stds_ = _context.getStandards();
        GeneType type_ = getFormatted().getRootBlock();
        if (!initClass) {
            initClass = true;
            if (!MetaInfoUtil.isAbstractType(_gene) && _context.getExiting().hasToExit(_stackCall, type_)) {
                setWrapException(true);
                return false;
            }
        }
        String className_ = getFormatted().getFormatted();
        if (MetaInfoUtil.isAbstractType(_gene)) {
            String null_ = stds_.getContent().getCoreNames().getAliasAbstractTypeErr();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_context, className_, null_, _stackCall)));
            setWrapException(false);
            return false;
        }
        String res_ = ExecInheritsAdv.correctClassPartsDynamicWildCard(className_,_context);
        if (res_.isEmpty()) {
            String null_;
            null_ = stds_.getContent().getCoreNames().getAliasIllegalType();
            _stackCall.setCallingState(new CustomFoundExc(new ErrorStruct(_context, className_, null_, _stackCall)));
            setWrapException(false);
            return false;
        }
        return true;
    }
    protected boolean checkParams(ContextEl _context, StackCall _stack, ConstructorMetaInfo _meta, ArgumentListCall _args) {
        if (getCheckedParams() == 0) {
            setCheckedParams(1);
            if (_meta.getStandardType() != null) {
                CustList<Argument> args_ = _args.getArguments();
                ConstructorId mid_ = _meta.getRealId();
                return checkParamsBase(_context,_stack,mid_,args_,"",null);
            }
        }
        return false;
    }

    protected boolean end(ContextEl _context, StackCall _stack, ConstructorMetaInfo _meta, Argument _arg) {
        if (_context.callsOrException(_stack)) {
            setWrapException(_stack.calls());
            return false;
        }
        setReturnedArgument(_arg);
        return postArg(_meta, _stack);
    }

    protected boolean postArg(ConstructorMetaInfo _meta, StackCall _stack) {
        if (getCheckedParams() == 1) {
            setCheckedParams(2);
            if (_meta.getStandardType() != null) {
                checkingEntryExit = true;
                return !_stack.getStopper().isStopAtExcMethod();
            }
        }
        checkingEntryExit = false;
        return true;
    }

    public boolean isCalledMethod() {
        return calledMethod;
    }

    public void setCalledMethod(boolean _c) {
        this.calledMethod = _c;
    }

    public int getCheckedParams() {
        return checkedParams;
    }

    public void setCheckedParams(int _c) {
        this.checkedParams = _c;
    }

    public boolean isCheckingEntryExit() {
        return checkingEntryExit;
    }

    public void setCheckingEntryExit(boolean _c) {
        this.checkingEntryExit = _c;
    }

    protected abstract ExecFormattedRootBlock getFormatted();
    public abstract int getRef();
}
