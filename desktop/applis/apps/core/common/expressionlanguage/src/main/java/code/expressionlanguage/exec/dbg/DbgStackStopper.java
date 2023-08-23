package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.*;
import code.expressionlanguage.exec.calls.AbstractCallingInstancingPageEl;
import code.expressionlanguage.exec.calls.*;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.calls.StaticInitPageEl;
import code.expressionlanguage.exec.calls.util.*;
import code.expressionlanguage.exec.inherits.*;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.exec.stacks.*;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.exec.variables.*;
import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.FieldableStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.NumberUtil;

public final class DbgStackStopper implements AbsStackStopper {
    public static final int READ = 0;
    public static final int WRITE = 1;
    public static final int COMPOUND_READ = 2;
    public static final int COMPOUND_WRITE = 3;
    public static final int COMPOUND_WRITE_ERR = 4;
    @Override
    public int checkNext(ContextEl _context, StackCall _stackCall) {
        if (!_stackCall.getBreakPointInfo().getBreakPointOutputInfo().isStoppedBreakPoint()) {
            return 0;
        }
        if (stopAtWp(_context, _stackCall)) {
            return 1;
        }
        return 2;
    }

    @Override
    public StepDbgActionEnum firstStep() {
        return StepDbgActionEnum.DEBUG;
    }

    @Override
    public boolean firstEnter(AbstractPageEl _page) {
        return _page.sizeEl() == 0;
    }
    @Override
    public boolean stopAt(StackCall _stack) {
        return _stack.getBreakPointInfo().getStackState().isCheckingBp();
    }

    @Override
    public boolean isStopAt(ExpressionLanguage _el, ExecOperationNode _o, ContextEl _context, StackCall _stackCall) {
        if (_stackCall.getBreakPointInfo().getStackState().visitedExp()) {
            return false;
        }
        _stackCall.getBreakPointInfo().getStackState().resetVisitAndCheckBp();
        _stackCall.getBreakPointInfo().getStackState().visitExp();
        _el.currentOper(_o);
        return true;
    }

    @Override
    public boolean isStopAtRef(ContextEl _context, StackCall _stackCall) {
        if (_stackCall.getBreakPointInfo().getStackState().visitedExp()) {
            return false;
        }
        _stackCall.getBreakPointInfo().getStackState().resetVisitAndCheckBp();
        _stackCall.getBreakPointInfo().getStackState().visitExp();
        return true;
    }

    @Override
    public StopDbgEnum stopBreakPoint(ContextEl _context, StackCall _stackCall) {
        if (_context.getClasses().getDebugMapping().getBreakPointsBlock().getPausedLoop().getAndSet(false)) {
            return StopDbgEnum.PAUSE;
        }
        return stopAtCheckedBp(_context, _stackCall);
    }

    @Override
    public boolean callsOrException(ContextEl _owner, StackCall _stackCall) {
        return _stackCall.callsOrException() || _stackCall.getReadWrite() != ReadWrite.ENTRY;
    }

    @Override
    public boolean hasValueStd(StackCall _stack) {
        return !_stack.callsOrException();
    }

    @Override
    public boolean isStopAtExcMethod() {
        return true;
    }

    @Override
    public int checkBpWithoutClearCount(StackCall _stack, AbstractPageEl _ip, int _old) {
        if (_old < _ip.sizeEl()) {
            _stack.getBreakPointInfo().getStackState().resetVisitAndCheckBp();
            return 1;
        }
        return 0;
    }

    private static CoreCheckedExecOperationNodeInfos expOper(ExpressionLanguage _el, ExecOperationNode _o, ContextEl _context, AbstractPageEl _last, boolean _ex) {
        if (_o instanceof ExecAbstractAffectOperation) {
            return affectationOrCompound(_el, (ExecAbstractAffectOperation)_o, _context, _last);
        }
        if (isField(_o)) {
            return field(_el, (ExecSettableFieldOperation) _o,  _context, _last);
        }
        if (_o instanceof ExecStdRefVariableOperation) {
            return variable((ExecStdRefVariableOperation)_o,  _context, _last);
        }
        if (_o instanceof StdParamsOperable) {
            return new StdMethodCheckedExecOperationNodeInfos(_context.getStandards().getCoreNames().getAliasObject(),(StdParamsOperable)_o, _el.getArguments(), ((StdParamsOperable)_o).instance(_el.getArguments(), _last), _ex);
        }
        if (_o instanceof ExecAnnotationMethodOperation) {
            Struct instance_ = instance(0,_el, _o, _last);
            return new CheckedExecOperationNodeInfos((ExecAnnotationMethodOperation)_o, _context,READ, formatted(_context, instance_), instance_, null);
        }
        return null;
    }

    private static CheckedExecOperationNodeInfos affectationOrCompound(ExpressionLanguage _el, ExecAbstractAffectOperation _o, ContextEl _context, AbstractPageEl _last) {
        _last.setOffset(_o.getIndexInEl()+ _o.getOperatorContent().getOpOffset());
        if (_o.getSettableParent() instanceof ExecSafeDotOperation && Argument.getNullableValue(ExecHelper.getArgumentPair(_el.getArguments(), _o.getSettableParent().getFirstChild()).getArgument()).isNull()) {
            return null;
        }
        if (_o instanceof ExecCompoundAffectationOperation) {
            return compound(_el, (ExecCompoundAffectationOperation)_o, _context, _last);
        }
        if (_o.getSettable() instanceof ExecStdRefVariableOperation && ((ExecStdRefVariableOperation) _o.getSettable()).isDeclare()) {
            return null;
        }
        CustList<ExecOperationNode> childrenNodes_ = _o.getChildrenNodes();
        Struct right_ = ArgumentListCall.toStr(ExecHelper.getArgumentPair(_el.getArguments(), ExecHelper.getNode(childrenNodes_, childrenNodes_.size() - 1)).getArgument());
        return settable(_el, _o, _context, WRITE, right_, NullStruct.NULL_VALUE, _last);
    }

    private static CheckedExecOperationNodeInfos field(ExpressionLanguage _el, ExecSettableFieldOperation _o, ContextEl _context, AbstractPageEl _last) {
        if (!_o.resultCanBeSet()) {
            int anc_ = _o.getSettableFieldContent().getAnc();
            Struct instance_ = instance(anc_,_el, _o, _last);
            if (sub(_o)) {
                return new CheckedExecOperationNodeInfos(_o, COMPOUND_READ, formatted(_context, _o, instance_), instance_, null);
            }
            return new CheckedExecOperationNodeInfos(_o, READ, formatted(_context, _o, instance_), instance_, null);
        }
        return null;
    }

    private static CheckedExecOperationNodeInfos variable(ExecStdRefVariableOperation _o, ContextEl _context, AbstractPageEl _last) {
        if (!_o.resultCanBeSet()) {
            if (sub(_o)) {
                return prVar(_o, _context, COMPOUND_READ, _last);
            }
            return prVar(_o, _context, READ, _last);
        }
        return null;
    }

    private static CheckedExecOperationNodeInfos prVar(ExecStdRefVariableOperation _o, ContextEl _context, int _mode, AbstractPageEl _last) {
        String v_ = _o.getVariableContent().getVariableName();
        AbstractWrapper w_ = ExecVariableTemplates.getWrapper(v_, _o.getVariableContent().getDeep(), _last.getCache(), _last.getRefParams());
        if (w_ instanceof FieldWrapper) {
            Struct instance_ = value(w_);
            return new CheckedExecOperationNodeInfos((FieldWrapper) w_, _mode, formatted(_context, (FieldWrapper) w_), instance_, null);
        }
        return null;
    }
    private static CheckedExecOperationNodeInfos compound(ExpressionLanguage _el, ExecCompoundAffectationOperation _o, ContextEl _context, AbstractPageEl _last) {
        ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_el.getArguments(), _o.getSettableAnc());
        if (argumentPair_.isArgumentTest()) {
            if (!ExecCompoundAffectationOperation.sh(_o.getOperatorContent())) {
                return settable(_el, _o, _context, COMPOUND_WRITE,ArgumentListCall.toStr(argumentPair_.getArgument()),NullStruct.NULL_VALUE, _last);
            }
            return null;
        }
        if (_o instanceof ExecCompoundAffectationStringOperation) {
            ImplicitMethods implicits_ = _o.getConverter();
            ArgumentsPair pairBefore_ = ExecHelper.getArgumentPair(_el.getArguments(), _o);
            int indexImplicit_ = pairBefore_.getIndexImplicitConv();
            if (ImplicitMethods.isValidIndex(implicits_,indexImplicit_)) {
                return null;
            }
            return settable(_el, _o, _context, COMPOUND_WRITE, ((ExecCompoundAffectationStringOperation)_o).calculated(_el.getArguments(), _context, _last),((ExecCompoundAffectationStringOperation)_o).leftArg(_el.getArguments()), _last);
        }
        return null;
    }

    private static CheckedExecOperationNodeInfos settable(ExpressionLanguage _el, ExecAbstractAffectOperation _o, ContextEl _ctx, int _mode, Struct _right, Struct _other, AbstractPageEl _last) {
        ExecOperationNode set_ = _o.getSettable();
        if (isField(set_)) {
            int anc_ = ((ExecSettableFieldOperation) set_).getSettableFieldContent().getAnc();
            Struct instance_ = instanceSet(_el, anc_, set_,  ((ExecSettableFieldOperation) set_).resultCanBeSet(), _last);
            return new CheckedExecOperationNodeInfos((ExecSettableFieldOperation) set_, altMode(_mode,_right), formatted(_ctx, (ExecSettableFieldOperation) set_, instance_), instance_, altRight(_mode,_right,_other));
        }
        if (set_ instanceof ExecStdRefVariableOperation || set_ instanceof ExecSettableCallFctOperation) {
            ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_el.getArguments(), set_);
            AbstractWrapper w_ = argumentPair_.getWrapper();
            if (w_ instanceof FieldWrapper) {
                Struct instance_ = value(w_);
                return new CheckedExecOperationNodeInfos((FieldWrapper) w_, altMode(_mode,_right), formatted(_ctx, (FieldWrapper) w_), instance_, altRight(_mode,_right,_other));
            }
        }
        return null;
    }

    private static int altMode(int _mode, Struct _right) {
        if (_mode == COMPOUND_WRITE && _right == null) {
            return COMPOUND_WRITE_ERR;
        }
        return _mode;
    }

    private static Struct altRight(int _mode, Struct _right, Struct _other) {
        if (_mode == COMPOUND_WRITE && _right == null) {
            return _other;
        }
        return _right;
    }

    private static boolean isField(ExecOperationNode _o) {
        return (_o instanceof ExecSettableFieldOperation) && !((ExecSettableFieldOperation) _o).isDeclare();
    }

    private static Struct instance(int _anc, ExpressionLanguage _el, ExecOperationNode _o, AbstractPageEl _last) {
        Struct prev_ = ArgumentListCall.toStr(Argument.getNullableValue(ExecHelper.getArgumentPair(_el.getArguments(), _o).getPreviousArgument()));
        Struct instance_;
        if (prev_ == NullStruct.NULL_VALUE) {
            instance_ = _last.getGlobalStruct();
        } else {
            instance_ = prev_;
        }
        return ExecFieldTemplates.getParent(_anc,instance_);
    }

    private static Struct instanceSet(ExpressionLanguage _el, int _anc, ExecOperationNode _o, boolean _writeOnly, AbstractPageEl _last) {
        if (_writeOnly) {
            return instance(_anc,_el,_o, _last);
        }
        return ArgumentListCall.toStr(Argument.getNullableValue(ExecHelper.getArgumentPair(_el.getArguments(), _o).getArgumentParent()));
    }
    private static boolean sub(ExecOperationNode _o) {
        ExecOperationNode curr_ = _o;
        while (curr_ != null) {
            if (curr_ instanceof ExecCompoundAffectationOperation && ((ExecCompoundAffectationOperation)curr_).getSettable() == _o) {
                return true;
            }
            curr_ = curr_.getParent();
        }
        return false;
    }

    private static CoreCheckedExecOperationNodeInfos infos(ContextEl _context, StackCall _stackCall) {
        AbstractPageEl p_ = _stackCall.getLastPage();
        CoreCheckedExecOperationNodeInfos infos_;
        if (_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() != null) {
            infos_ = endCase(_context, _stackCall);
        } else if (p_ instanceof LambdaVariableGetValuePageEl) {
            infos_ = lambdaGet(_context, (LambdaVariableGetValuePageEl) p_);
        } else if (p_ instanceof LambdaVariableSetValuePageEl) {
            infos_ = lambdaSet(_context, (LambdaVariableSetValuePageEl) p_);
        } else if (p_ instanceof ReflectGetFieldPageEl) {
            infos_ = reflectGet(_context, (ReflectGetFieldPageEl) p_);
        } else if (p_ instanceof ReflectSetFieldPageEl) {
            infos_ = reflectSet(_context, (ReflectSetFieldPageEl) p_);
        } else if (p_ instanceof DirectStdRefectMethodPageEl) {
            infos_ = refMethod((DirectStdRefectMethodPageEl) p_);
        } else if (p_ instanceof LambdaDirectStdRefectMethodPageEl) {
            infos_ = refMethod((LambdaDirectStdRefectMethodPageEl) p_);
        } else if (p_ instanceof ReflectConstructorPageEl) {
            infos_ = refMethod((ReflectConstructorPageEl) p_);
        } else if (p_ instanceof ReflectLambdaConstructorPageEl) {
            infos_ = refMethod((ReflectLambdaConstructorPageEl) p_);
        } else if (p_ instanceof DirectAnnotationRefectMethodPageEl) {
            infos_ = refMethod(_context, (DirectAnnotationRefectMethodPageEl) p_);
        } else if (p_ instanceof LambdaAnnotationRefectMethodPageEl) {
            infos_ = refMethod(_context, (LambdaAnnotationRefectMethodPageEl) p_);
        } else if (!p_.isEmptyEl()){
            ExpressionLanguage el_ = p_.getLastEl();
            infos_ = expOper(el_, el_.getCurrentOper(), _context, p_,_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getCalculated() != null);
        } else {
            infos_ = null;
        }
        return infos_;
    }

    private static CoreCheckedExecOperationNodeInfos endCase(ContextEl _context, StackCall _stackCall) {
        AbstractPageEl p_ = _stackCall.getLastPage();
        CoreCheckedExecOperationNodeInfos infos_;
        if (!p_.isEmptyEl()) {
            ExpressionLanguage el_ = p_.getLastEl();
            infos_ = end(_context, el_, _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting());
        } else {
            infos_ = null;
        }
        return infos_;
    }

    private static StdMethodCheckedExecOperationNodeInfos refMethod(DirectStdRefectMethodPageEl _p) {
        if (_p.isCheckingEntryExit()) {
            return new StdMethodCheckedExecOperationNodeInfos(_p);
        }
        return null;
    }
    private static StdMethodCheckedExecOperationNodeInfos refMethod(LambdaDirectStdRefectMethodPageEl _p) {
        if (_p.isCheckingEntryExit()) {
            return new StdMethodCheckedExecOperationNodeInfos(_p);
        }
        return null;
    }
    private static CheckedExecOperationNodeInfos refMethod(ContextEl _context, DirectAnnotationRefectMethodPageEl _p) {
        if (_p.isCheckingEntryExit()) {
            Struct instance_ = ArgumentListCall.toStr(_p.getInstance());
            return new CheckedExecOperationNodeInfos(_p.getMetaInfo().getRealId().getName(), _context,READ, formatted(_context, instance_), instance_, null);
        }
        return null;
    }
    private static CheckedExecOperationNodeInfos refMethod(ContextEl _context, LambdaAnnotationRefectMethodPageEl _p) {
        if (_p.isCheckingEntryExit()) {
            Struct instance_ = ArgumentListCall.toStr(_p.getInstance());
            return new CheckedExecOperationNodeInfos(_p.getMetaInfo().getRealId().getName(), _context,READ, formatted(_context, instance_), instance_, null);
        }
        return null;
    }
    private static StdMethodCheckedExecOperationNodeInfos refMethod(ReflectConstructorPageEl _p) {
        if (_p.isCheckingEntryExit()) {
            return new StdMethodCheckedExecOperationNodeInfos(_p);
        }
        return null;
    }
    private static StdMethodCheckedExecOperationNodeInfos refMethod(ReflectLambdaConstructorPageEl _p) {
        if (_p.isCheckingEntryExit()) {
            return new StdMethodCheckedExecOperationNodeInfos(_p);
        }
        return null;
    }

    private static CheckedExecOperationNodeInfos lambdaGet(ContextEl _context, LambdaVariableGetValuePageEl _p) {
        ArgumentWrapper firstArgumentWrapper_ = ExecHelper.getFirstArgumentWrapper(_p.getArr().getArgumentWrappers());
        AbstractWrapper w_ = firstArgumentWrapper_.getWrapper();
        if (w_ instanceof FieldWrapper) {
            Struct instance_ = value(w_);
            return new CheckedExecOperationNodeInfos((FieldWrapper) w_, DbgStackStopper.READ, formatted(_context, (FieldWrapper) w_),instance_,null);
        }
        return null;
    }

    private static CheckedExecOperationNodeInfos lambdaSet(ContextEl _context, LambdaVariableSetValuePageEl _p) {
        CustList<ArgumentWrapper> argumentWrappers_ = _p.getArr().getArgumentWrappers();
        ArgumentWrapper firstArgumentWrapper_ = ExecHelper.getFirstArgumentWrapper(argumentWrappers_);
        AbstractWrapper w_ = firstArgumentWrapper_.getWrapper();
        if (w_ instanceof FieldWrapper) {
            Struct right_ = ArgumentListCall.toStr(ArgumentWrapper.helpArg(ExecHelper.getLastArgumentWrapper(argumentWrappers_)));
            Struct instance_ = value(w_);
            return new CheckedExecOperationNodeInfos((FieldWrapper) w_, DbgStackStopper.WRITE, formatted(_context, (FieldWrapper) w_),instance_,right_);
        }
        return null;
    }

    private static CheckedExecOperationNodeInfos reflectGet(ContextEl _context, ReflectGetFieldPageEl _p) {
        Struct instance_ = ArgumentListCall.toStr(_p.getArgument());
        return new CheckedExecOperationNodeInfos(_p.getMetaInfo(), DbgStackStopper.READ, formatted(_context, _p.getMetaInfo().getFormatted().getRootBlock(), instance_), instance_, null);
    }

    private static CheckedExecOperationNodeInfos reflectSet(ContextEl _context, ReflectSetFieldPageEl _p) {
        Struct instance_ = ArgumentListCall.toStr(_p.getFirst());
        Struct right_ = ArgumentListCall.toStr(_p.getLast());
        return new CheckedExecOperationNodeInfos(_p.getMetaInfo(), DbgStackStopper.WRITE, formatted(_context, _p.getMetaInfo().getFormatted().getRootBlock(), instance_), instance_, right_);
    }

    private static ExecFormattedRootBlock formatted(ContextEl _ctx, FieldWrapper _w) {
        Struct instance_ = value(_w);
        return formatted(_ctx, _w.owner(), instance_);
    }
    private static ExecFormattedRootBlock formatted(ContextEl _ctx, ExecSettableFieldOperation _id, Struct _inst) {
        return formatted(_ctx, _id.owner(),_inst);
    }
    private static ExecFormattedRootBlock formatted(ContextEl _ctx, Struct _inst) {
        String cl_ = _inst.getClassName(_ctx);
        ExecRootBlock root_ = _ctx.getClasses().getClassBody(cl_);
        return formatted(_ctx, root_,_inst);
    }

    private static ExecFormattedRootBlock formatted(ContextEl _ctx, ExecRootBlock _id, Struct _inst) {
        if (_id == null) {
            return null;
        }
        if (_inst == NullStruct.NULL_VALUE) {
            return new ExecFormattedRootBlock(_id);
        }
        String formatted_ = ExecInherits.getQuickFullTypeByBases(_inst.getClassName(_ctx), _id.getFullName(), _ctx);
        return new ExecFormattedRootBlock(_id, formatted_);
    }

    private static Struct value(AbstractWrapper _f) {
        if (_f instanceof InstanceFieldWrapper) {
            return ((InstanceFieldWrapper)_f).getParent();
        }
        return NullStruct.NULL_VALUE;
    }

    private static ExecOperationNode getCurrentOper(AbstractPageEl _p) {
        ExecOperationNode ex_;
        if (!_p.isEmptyEl()){
            ExpressionLanguage el_ = _p.getLastEl();
            ex_ = el_.getCurrentOper();
        } else {
            ex_ = null;
        }
        return ex_;
    }

    private static CoreCheckedExecOperationNodeInfos end(ContextEl _context, ExpressionLanguage _el, AbstractPageEl _last) {
        ExecOperationNode ex_ = _el.getCurrentOper();
        if (ex_ instanceof ExecCompoundAffectationOperation) {
            ImplicitMethods implicits_ = ((ExecCompoundAffectationOperation) ex_).getConverter();
            ArgumentsPair pairBefore_ = ExecHelper.getArgumentPair(_el.getArguments(),ex_);
            int indexImplicit_ = pairBefore_.getIndexImplicitConv();
            if (!ImplicitMethods.isValidIndex(implicits_,indexImplicit_)&&!pairBefore_.isEndCalculate()) {
                return settable(_el,(ExecCompoundAffectationOperation) ex_,_context, COMPOUND_WRITE,ArgumentListCall.toStr(_last.getReturnedArgument()),NullStruct.NULL_VALUE, _last);
            }
        }
        if (ex_ instanceof ExecSettableCallFctOperation) {
            if (ex_ instanceof StdParamsOperable) {
                return new StdMethodCheckedExecOperationNodeInfos(_context.getStandards().getCoreNames().getAliasObject(),(StdParamsOperable)ex_, _el.getArguments(), ((StdParamsOperable)ex_).instance(_el.getArguments(), _last), true);
            }
            if (sub(ex_)) {
                AbstractWrapper w_ = _last.getWrapper();
                return wrapp(w_, _context, COMPOUND_READ);
            } else if (!((ExecSettableCallFctOperation) ex_).resultCanBeSet()) {
                AbstractWrapper w_ = _last.getWrapper();
                return wrapp(w_, _context, READ);
            }
        }
        return null;
    }

    private static CheckedExecOperationNodeInfos wrapp(AbstractWrapper _w, ContextEl _context, int _m) {
        if (_w instanceof FieldWrapper) {
            Struct instance_ = value(_w);
            String formatted_ = ExecInherits.getQuickFullTypeByBases(instance_.getClassName(_context), ((FieldWrapper) _w).getId().getClassName(), _context);
            ExecRootBlock ex_ = _context.getClasses().getClassBody(((FieldWrapper) _w).getId().getClassName());
            return new CheckedExecOperationNodeInfos((FieldWrapper) _w, _m,new ExecFormattedRootBlock(ex_,formatted_),instance_,null);
        }
        return null;
    }

    private static boolean stopAtWp(ContextEl _context,StackCall _stackCall) {
        if (_stackCall.getBreakPointInfo().getBreakPointInputInfo().isMute()) {
            return false;
        }
        if (_stackCall.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint() == StopDbgEnum.STEP_RETURN_METHOD) {
            CoreCheckedExecOperationNodeInfos infos_ = infos(_context, _stackCall);
            StopDbgEnum s_ = current(_context, _stackCall, infos_);
            if (s_ != StopDbgEnum.NONE) {
                _stackCall.getBreakPointInfo().getBreakPointOutputInfo().setStoppedBreakPoint(s_);
                return true;
            }
        }
        return false;
    }

    private static StopDbgEnum stopAtCheckedBp(ContextEl _context, StackCall _stackCall) {
        AbstractPageEl p_ = _stackCall.getLastPage();
        CoreCheckedExecOperationNodeInfos infos_ = infos(_context, _stackCall);
        if (!checkBreakPoint(_context,_stackCall,p_,infos_)) {
            return StopDbgEnum.NONE;
        }
        StopDbgEnum step_ = stopStep(_context, _stackCall, p_);
        if (step_ != StopDbgEnum.NONE) {
            return step_;
        }
        if (_stackCall.getBreakPointInfo().getBreakPointInputInfo().isMute()) {
            return StopDbgEnum.NONE;
        }
        return current(_context, _stackCall, infos_);
    }

    private static StopDbgEnum current(ContextEl _context, StackCall _stackCall, CoreCheckedExecOperationNodeInfos _infos) {
        AbstractPageEl p_ = _stackCall.getLastPage();
        if (_stackCall.normalCallNoExit(_context)) {
            return enterCase(_context, _stackCall, p_);
        }
        if (_stackCall.trueException() == null && _infos instanceof StdMethodCheckedExecOperationNodeInfos) {
            return enterCase(_context,(StdMethodCheckedExecOperationNodeInfos)_infos,_stackCall,p_);
        }
        if (exitMethod(_context, _stackCall, p_)) {
            return StopDbgEnum.METHOD_EXIT;
        }
        if (stopExc(_context, _stackCall, p_)) {
            return StopDbgEnum.EXCEPTION;
        }
        StopDbgEnum s_ = checkedWp(_context, _stackCall, p_, _infos);
        if (s_ != null) {
            return s_;
        }
        if (_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() != null || _stackCall.trueException() != null || getCurrentOper(p_) != null || _stackCall.getReadWrite() != ReadWrite.ENTRY) {
            return StopDbgEnum.NONE;
        }
        for (int i : list(p_)) {
            BreakPoint bp_ = _context.getClasses().getDebugMapping().getBreakPointsBlock().getNotNull(p_.getFile(), i);
            if (stopCurrentBp(_context, _stackCall,p_,bp_)) {
                return StopDbgEnum.INSTRUCTION;
            }
        }
        return StopDbgEnum.NONE;
    }

    private static StopDbgEnum checkedWp(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, CoreCheckedExecOperationNodeInfos _infos) {
        if (stopExcValuRetThrowCatch(_context,_stackCall, _p) == null && _infos instanceof CheckedExecOperationNodeInfos) {
            BreakPointOutputInfo i_ = new BreakPointOutputInfo();
            i_.setOperElt(_infos);
            BreakPointOutputInfo info_ = new BreakPointOutputInfo();
            info_.setOperElt(_infos);
            ClassField clField_ = ((CheckedExecOperationNodeInfos) _infos).getIdClass();
            WatchPoint bp_ = _context.getClasses().getDebugMapping().getBreakPointsBlock().getNotNullWatch(((CheckedExecOperationNodeInfos) _infos).isTrueField(),((CheckedExecOperationNodeInfos) _infos).getNbType(),clField_.getFieldName());
            BreakPointCondition condition_ = stopCurrentWpCondition(bp_, (CheckedExecOperationNodeInfos)_infos);
            if (stopCurrent(_context, _stackCall, _p, condition_, i_)) {
                return StopDbgEnum.FIELD;
            }
            return StopDbgEnum.NONE;
        }
        return null;
    }

    private static StopDbgEnum enterCase(ContextEl _context, StdMethodCheckedExecOperationNodeInfos _ex, StackCall _stackCall, AbstractPageEl _p) {
        StandardNamedFunction call_ = _ex.getFct();
        ExecFormattedRootBlock glClass_ = _ex.getDeclaring();
        Struct instance_ = _ex.getInstance();
        ArgumentListCall original_ = _ex.getArgs();
        CustList<StdMethodPointBlockPair> pairs_ = _context.getClasses().getDebugMapping().getBreakPointsBlock().getStdPairs(call_);
        for (StdMethodPointBlockPair m: pairs_) {
            Parameters params_ = build(_context, m, original_);
            BreakPointOutputInfo info_ = new BreakPointOutputInfo();
            info_.setCheckedMethodInfos(new CheckedMethodInfos(glClass_, instance_, params_));
            MethodPoint mp_ = m.getValue();
            if (stopCurrentMp(_context, _stackCall, _p, mp_, _ex.isExiting(),info_)) {
                if (_ex.isExiting()) {
                    return StopDbgEnum.METHOD_EXIT;
                }
                return StopDbgEnum.METHOD_ENTRY;
            }
        }
        return StopDbgEnum.NONE;
    }

    private static StopDbgEnum enterCase(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
        ExecBlock call_ = call(_stackCall.getCallingState());
        ExecFormattedRootBlock glClass_ = globalClass(_stackCall.getCallingState());
        Struct instance_ = instance(_stackCall.getCallingState());
        Parameters original_ = params(_stackCall.getCallingState());
        CustList<MethodPointBlockPairRootBlock> pairs_ = _context.getClasses().getDebugMapping().getBreakPointsBlock().getPairs(call_, glClass_, _context, instance_);
        for (MethodPointBlockPairRootBlock m: pairs_) {
            Parameters params_ = build(original_.getRefParameters(), original_.getCache(), _context, m.getId());
            BreakPointOutputInfo info_ = new BreakPointOutputInfo();
            info_.setCheckedMethodInfos(new CheckedMethodInfos(m.getValue(), instance_, params_));
            MethodPoint mp_ = m.getId().getValue();
            if (stopCurrentMp(_context, _stackCall, _p, mp_, false,info_)) {
                return StopDbgEnum.METHOD_ENTRY;
            }
        }
        return StopDbgEnum.NONE;
    }

    private static boolean exitMethod(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
        if (exiting(_stackCall)) {
            CustList<MethodPointBlockPairRootBlock> pairs_ = _context.getClasses().getDebugMapping().getBreakPointsBlock().getPairs(_p.getBlockRoot(), _p.getGlobalClass(),_context, _p.getGlobalStruct());
            for (MethodPointBlockPairRootBlock m: pairs_) {
                Parameters params_ = build(_p.getRefParams(), _p.getCache(), _context, m.getId());
                BreakPointOutputInfo info_ = new BreakPointOutputInfo();
                info_.setCheckedMethodInfos(new CheckedMethodInfos(m.getValue(),_p.getGlobalStruct(), params_));
                MethodPoint mp_ = m.getId().getValue();
                if (stopCurrentMp(_context, _stackCall, _p, mp_, true, info_)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static ExecBlock call(CallingState _c) {
        if (_c instanceof CustomFoundBlock) {
            return ((CustomFoundBlock)_c).getBlock();
        }
        if (_c instanceof CustomFoundConstructor) {
            return ((CustomFoundConstructor)_c).getPair().getFct();
        }
        if (_c instanceof CustomFoundMethod) {
            return ((CustomFoundMethod)_c).getPair().getFct();
        }
        if (_c instanceof CustomFoundSwitch) {
            return ((CustomFoundSwitch)_c).getSwitchMethod();
        }
        return null;
    }

    private static Struct instance(CallingState _c) {
        if (_c instanceof CustomFoundBlock) {
            return ArgumentListCall.toStr(((CustomFoundBlock)_c).getCurrentObject());
        }
        if (_c instanceof CustomFoundConstructor) {
            return ArgumentListCall.toStr(((CustomFoundConstructor)_c).getCurrentObject());
        }
        if (_c instanceof CustomFoundMethod) {
            return ArgumentListCall.toStr(((CustomFoundMethod)_c).getGl());
        }
        if (_c instanceof CustomFoundSwitch) {
            return ArgumentListCall.toStr(((CustomFoundSwitch)_c).getGl());
        }
        return NullStruct.NULL_VALUE;
    }

    private static ExecFormattedRootBlock globalClass(CallingState _c) {
        if (_c instanceof GlobalClassCallingState) {
            return ((GlobalClassCallingState)_c).getClassName();
        }
        return ExecFormattedRootBlock.defValue();
    }

    private static Parameters params(CallingState _c) {
        if (_c instanceof CustomFoundConstructor) {
            return ((CustomFoundConstructor)_c).getArguments();
        }
        if (_c instanceof CustomFoundMethod) {
            return ((CustomFoundMethod)_c).getArguments();
        }
        if (_c instanceof CustomFoundSwitch) {
            Parameters params_ = new Parameters();
            params_.setCache(((CustomFoundSwitch)_c).getCache());
            return params_;
        }
        return new Parameters();
    }

    private static Parameters build(ContextEl _conf, StdMethodPointBlockPair _id, ArgumentListCall _params) {
        CustList<String> ls_ = _id.names();
        StringMap<AbstractWrapper> pars_ = new StringMap<AbstractWrapper>();
        for (ArgumentWrapper a: _params.getArgumentWrappers()) {
            pars_.addEntry("",new VariableWrapper(LocalVariable.newLocalVariable(ArgumentListCall.toStr(a.getValue()),_conf)));
        }
        return build(pars_, null, ls_);
    }

    private static Parameters build(StringMap<AbstractWrapper> _params, Cache _cache, ContextEl _conf, MethodPointBlockPair _id) {
        CustList<String> ls_ = _id.names(_conf);
        return build(_params, _cache, ls_);
    }

    private static Parameters build(StringMap<AbstractWrapper> _params, Cache _cache, CustList<String> _ls) {
        int s_ = NumberUtil.min(_params.size(), _ls.size());
        Parameters params_ = new Parameters();
        for (int i = 0; i < s_; i++) {
            params_.getRefParameters().addEntry(_ls.get(i), _params.getValue(i));
        }
        params_.setCache(_cache);
        return params_;
    }

    private static StopDbgEnum stopStep(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
        if (stopExcValuRetThrowCatch(_context,_stackCall, _p) != null || _stackCall.normalCallNoExit(_context)) {
            return StopDbgEnum.NONE;
        }
        if (_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() == null && _stackCall.getBreakPointInfo().getBreakPointInputInfo().getStep() == StepDbgActionEnum.RETURN_METHOD && _stackCall.getReadWrite() != ReadWrite.ENTRY && _stackCall.nbPages() == 1) {
            return StopDbgEnum.STEP_RETURN_METHOD;
        }
        if (exiting(_stackCall)) {
            return StopDbgEnum.NONE;
        }
        if (_stackCall.getBreakPointInfo().getBreakPointInputInfo().getStep() == StepDbgActionEnum.RETURN_METHOD && _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getPreviousNbPages() > _stackCall.nbPages()) {
            return StopDbgEnum.STEP_RETURN_METHOD;
        }
        if (skipStepNotReturn(_stackCall, _p)) {
            return StopDbgEnum.NONE;
        }
        if (_stackCall.getBreakPointInfo().getBreakPointInputInfo().getStep() == StepDbgActionEnum.NEXT_BLOCK && (_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getPreviousNbPages() == _stackCall.nbPages() && _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getPreviousNbBlocks() > _p.nbBlock() || _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getPreviousNbPages() > _stackCall.nbPages())) {
            return StopDbgEnum.STEP_NEXT_BLOCK;
        }
        if (_stackCall.getBreakPointInfo().getBreakPointInputInfo().getStep() == StepDbgActionEnum.NEXT_IN_METHOD && _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getPreviousNbPages() >= _stackCall.nbPages()) {
            return StopDbgEnum.STEP_NEXT_IN_METHOD;
        }
        if (_stackCall.getBreakPointInfo().getBreakPointInputInfo().getStep() == StepDbgActionEnum.NEXT_INSTRUCTION) {
            return StopDbgEnum.STEP_NEXT_INSTRUCTION;
        }
        if (stopTmp(_context, _stackCall, _p)) {
            return StopDbgEnum.STEP_CURSOR;
        }
        return StopDbgEnum.NONE;
    }

    private static boolean exiting(StackCall _stackCall) {
        return _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() == null && _stackCall.getReadWrite() != ReadWrite.ENTRY;
    }

    private static boolean skipStepNotReturn(StackCall _stackCall, AbstractPageEl _p) {
        return _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() != null || getCurrentOper(_p) != null;
    }

    private static boolean stopTmp(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
        if (_stackCall.getBreakPointInfo().getBreakPointInputInfo().getStep() == StepDbgActionEnum.CURSOR) {
            for (int i : list(_p)) {
                if (_context.getClasses().getDebugMapping().getBreakPointsBlock().isTmp(_p.getFile(), i)) {
                    return true;
                }
            }
        }
        return false;
    }
    private static boolean stopExc(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
        Struct str_ = stopExcValuRetThrowCatch(_context,_stackCall, _p);
        if (str_ == null) {
            return false;
        }
        String clName_ = str_.getClassName(_context);
        if (checkExc(_context, _stackCall, _p, str_, _context.getClasses().getDebugMapping().getBreakPointsBlock().getNotNullExc(clName_,true))) {
            return true;
        }
        if (!clName_.isEmpty() && checkExc(_context, _stackCall, _p, str_, _context.getClasses().getDebugMapping().getBreakPointsBlock().getNotNullExc(StringExpUtil.getIdFromAllTypes(clName_), false))) {
            return true;
        }
        return checkExc(_context, _stackCall, _p, str_, _context.getClasses().getDebugMapping().getBreakPointsBlock().getNotNullExc("",false));
    }

    private static boolean checkExc(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, Struct _str, ExcPoint _bp) {
        BreakPointCondition bpc_ = stopExcValue(_p, _bp, _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting());
        String clName_ = _str.getClassName(_context);
        BreakPointOutputInfo info_ = new BreakPointOutputInfo();
        info_.setOperElt(new CoreCheckedExecOperationNodeInfos(new ExecFormattedRootBlock(_context.getClasses().getClassBody(StringExpUtil.getIdFromAllTypes(clName_)), clName_), _str,null));
        return stopCurrent(_context, _stackCall, _p, bpc_, info_);
    }

    private static Struct stopExcValuRetThrowCatch(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
        if (_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() == null) {
            AbstractStask stLast_ = _p.tryGetLastStack();
            ExecBlock bl_ = _p.getBlock();
            if (stLast_ instanceof TryBlockStack && bl_ instanceof ExecAbstractCatchEval) {
                Struct str_ = ((TryBlockStack) stLast_).getException();
                if (ExecHelperBlocks.firstMatch(_context, _stackCall, ((ExecAbstractCatchEval) bl_).getContent(), str_, ((ExecAbstractCatchEval) bl_).isCatchAll()) && _p.sizeEl() < 2) {
                    return str_;
                }
                return null;
            }
        }
        CustomFoundExc exc_ = _stackCall.trueException();
        if (exc_ != null) {
            return exc_.getStruct();
        }
        return null;
    }
    private static BreakPointCondition stopExcValue(AbstractPageEl _p, ExcPoint _ex, AbstractPageEl _exiting) {
        if (_exiting == null) {
            AbstractStask stLast_ = _p.tryGetLastStack();
            ExecBlock bl_ = _p.getBlock();
            if (stLast_ instanceof TryBlockStack && bl_ instanceof ExecAbstractCatchEval) {
                if (_ex.isCaught()) {
                    return _ex.getResultCaught();
                }
                return null;
            }
            if (_ex.isThrown()) {
                return _ex.getResultThrown();
            }
            return null;
        }
        if (_ex.isPropagated()) {
            return _ex.getResultPropagated();
        }
        return null;
    }
    private static boolean stopCurrentBp(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, BreakPoint _bp) {
        BreakPointCondition condition_ = stopCurrentBpCondition(_p,_bp);
        return stopCurrent(_context, _stackCall, _p, condition_,new BreakPointOutputInfo());
    }

    private static boolean stopCurrentMp(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, MethodPoint _bp, boolean _exit, BreakPointOutputInfo _info) {
        BreakPointCondition condition_ = stopCurrentMpCondition(_bp,_exit);
        return stopCurrent(_context, _stackCall, _p, condition_, _info);
    }
    private static boolean stopCurrent(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, BreakPointCondition _condition, BreakPointOutputInfo _info) {
        if (!okStack(_context, _stackCall, _condition)) {
            return false;
        }
        ResultContextLambda resLda_ = _condition.getResult();
        if (resLda_ != null && !condition(_context, _stackCall, _p, resLda_, _info)) {
            return false;
        }
        return postCondition(_stackCall, _condition, _info);
    }
    private static boolean postCondition(StackCall _stackCall, BreakPointCondition _condition, BreakPointOutputInfo _info) {
        if (!_condition.getEnabled().get()) {
            return false;
        }
        if (countMatch(_condition)) {
            if (_condition.getDisableWhenHit().get()) {
                _condition.getEnabled().set(false);
            }
            _stackCall.getBreakPointInfo().getBreakPointOutputInfo().aff(_info);
            return true;
        }
        return false;
    }


    private static BreakPointCondition stopCurrentWpCondition(WatchPoint _bp, CheckedExecOperationNodeInfos _check) {
        if (!_bp.isEnabled()) {
            return null;
        }
        if (!(_bp.isRead() && _check.getModeField() == READ || _bp.isWrite() && _check.getModeField() == WRITE || _bp.isCompoundRead() && _check.getModeField() == COMPOUND_READ || _bp.isCompoundWrite() && _check.getModeField() == COMPOUND_WRITE || _bp.isCompoundWriteErr() && _check.getModeField() == COMPOUND_WRITE_ERR)) {
            return null;
        }
        if (_check.getModeField() == READ) {
            return _bp.getResultRead();
        }
        if (_check.getModeField() == WRITE) {
            return _bp.getResultWrite();
        }
        if (_check.getModeField() == COMPOUND_READ) {
            return _bp.getResultCompoundRead();
        }
        if (_check.getModeField() == COMPOUND_WRITE) {
            return _bp.getResultCompoundWrite();
        }
        return _bp.getResultCompoundWriteErr();
    }
    private static boolean countMatch(BreakPointCondition _cond) {
        int c_ = _cond.getCountModulo();
        if (c_ <= 0) {
            return true;
        }
        int p_ = _cond.getCount();
        _cond.setCount(p_ + 1);
        return NumberUtil.mod(_cond.getCount(),c_) == 0;
    }
    private static boolean okStack(ContextEl _context, StackCall _stackCall, BreakPointCondition _bp) {
        if (_bp == null) {
            return false;
        }
        for (AbsCallContraints e: _bp.getExclude().elts()) {
            if (!excOk(_context,_stackCall,e)) {
                return false;
            }
        }
        for (AbsCallContraints e: _bp.getInclude().elts()) {
            if (!incOk(_context,_stackCall,e)) {
                return false;
            }
        }
        return true;
    }

    private static boolean excOk(ContextEl _context, StackCall _stackCall, AbsCallContraints _elt) {
        int nb_ = _stackCall.nbPages();
        for (int i = 0; i < nb_; i++) {
            AbstractPageEl e_ = _stackCall.getCall(i);
            if (_elt.match(_context,e_)){
                return false;
            }
        }
        return true;
    }
    private static boolean incOk(ContextEl _context, StackCall _stackCall, AbsCallContraints _elt) {
        int nb_ = _stackCall.nbPages();
        for (int i = 0; i < nb_; i++) {
            AbstractPageEl e_ = _stackCall.getCall(i);
            if (_elt.match(_context,e_)){
                return true;
            }
        }
        return false;
    }

    private static boolean condition(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, ResultContextLambda _result, BreakPointOutputInfo _info) {
        StackCallReturnValue result_ = _result.eval(_context, _info, _p);
        if (result_.getStack().getCallingState() != null) {
            _stackCall.getBreakPointInfo().getBreakPointOutputInfo().setCallingStateSub(result_.getStack().getCallingState());
            return true;
        }
        return BooleanStruct.isTrue(ArgumentListCall.toStr(result_.getStack().aw().getValue()));
    }

    private static BreakPointCondition stopCurrentBpCondition(AbstractPageEl _p, BreakPoint _bp) {
        if (!(_bp.isEnabled() && (!_bp.isEnabledChgtType() || _bp.isInstanceType() && _p instanceof AbstractCallingInstancingPageEl || _bp.isStaticType() && _p instanceof StaticInitPageEl))) {
            return null;
        }
        if (!_bp.isEnabledChgtType()) {
            return _bp.getResultStd();
        }
        if (_bp.isInstanceType()) {
            return _bp.getResultInstance();
        }
        return _bp.getResultStatic();
    }

    private static BreakPointCondition stopCurrentMpCondition(MethodPoint _bp, boolean _exit) {
        if (!_bp.isEnabled()) {
            return null;
        }
        if (!(_bp.isEntry() && !_exit || _bp.isExit() && _exit)) {
            return null;
        }
        if (_exit) {
            return _bp.getResultExit();
        }
        return _bp.getResultEntry();
    }
    private static int[] list(AbstractPageEl _p) {
        ExecBlock bl_ = _p.getBlock();
        AbstractStask st_ = _p.tryGetLastStack();
        if (st_ instanceof LoopBlockStack && bl_ instanceof ExecAbstractForEachLoop && !(bl_ instanceof ExecForEachIterable) && ((ExecAbstractForEachLoop) bl_).getVariable().getOffset() == _p.getGlobalOffset()) {
            if (!((LoopBlockStack) st_).getContent().hasNext()) {
                return NumberUtil.wrapIntArray(((ExecAbstractForEachLoop) bl_).getSeparator());
            }
            return NumberUtil.wrapIntArray(_p.getGlobalOffset(), ((ExecAbstractForEachLoop) bl_).getSeparator());
        }
        if (_p.getLastLoopIfPossible(bl_) == null){
            if (bl_ instanceof ExecForEachIterable && _p.sizeEl() == 2) {
                return NumberUtil.wrapIntArray(((ExecForEachIterable) bl_).getIteratorOffset());
            }
            if (bl_ instanceof ExecForEachTable && _p.sizeEl() == 2) {
                return NumberUtil.wrapIntArray(((ExecForEachTable) bl_).getOffsets().getIteratorOffset());
            }
        }
        return NumberUtil.wrapIntArray(_p.getGlobalOffset());
    }

    private static boolean checkBreakPoint(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, CoreCheckedExecOperationNodeInfos _infos) {
        if (visitedOrNotVisitableLambda(_stackCall,_p)) {
            return false;
        }
        if (_stackCall.trueException() != null || (_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() != null && _stackCall.getBreakPointInfo().getBreakPointInputInfo().getStep() == StepDbgActionEnum.RETURN_METHOD) || _infos != null) {
            return possibleDeclared(_context, _stackCall, _infos);
        }
        if (_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() != null) {
            return false;
        }
        return checkBreakPointCurrent(_context, _stackCall, _p);
    }

    private static boolean checkBreakPointCurrent(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
        if (enterExit(_context, _stackCall)) {
            return true;
        }
        ExecBlock bl_ = _p.getBlock();
        if (bl_ instanceof ExecDeclareVariable || _p.isEmptyEl()) {
            return false;
        }
        if (bl_ instanceof ExecLine || bl_ instanceof ExecAbstractReturnMethod || bl_ instanceof MethodCallingFinally || bl_ instanceof ExecThrowing || bl_ instanceof ExecDoWhileCondition) {
            return true;
        }
        AbstractStask st_ = _p.tryGetLastStack();
        if (st_ instanceof TryBlockStack && bl_ instanceof ExecAbstractCatchEval) {
            return true;
        }
        if (st_ instanceof EnteredStack) {
            return !((EnteredStack) st_).isEntered();
        }
        if (st_ instanceof LoopBlockStack) {
            return !((LoopBlockStack) st_).getContent().isFinished();
        }
        if (st_ instanceof SwitchBlockStack) {
            return !((SwitchBlockStack) st_).isEntered();
        }
        return true;
    }

    private static boolean visitedOrNotVisitableLambda(StackCall _stackCall, AbstractPageEl _p) {
        return _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() == null && _p instanceof AbstractLambdaVariable && (!((AbstractLambdaVariable)_p).isVisitablePage() || ((AbstractLambdaVariable)_p).isVisitedPage());
    }

    private static boolean possibleDeclared(ContextEl _context, StackCall _stackCall, CoreCheckedExecOperationNodeInfos _infos) {
        return !(_infos instanceof CheckedExecOperationNodeInfos) || okField(_context,_stackCall, (CheckedExecOperationNodeInfos) _infos);
    }

    private static boolean okField(ContextEl _context, StackCall _stackCall, CheckedExecOperationNodeInfos _check) {
        if (_check.getDeclaring() == null) {
            return false;
        }
        Struct v_ = strValue(_context, _check);
        if (v_ == null) {
            return false;
        }
        Struct right_ = _check.getRight();
        if (right_ == null) {
            return true;
        }
        String ret_;
        if (!_check.isStaticField()) {
            String arg_ = _check.getInstance().getClassName(_context);
            ret_ = ExecFieldTemplates.formatType(_context, _check.getFieldType().getRootBlock(), _check.getFieldType().getReturnType(), arg_);
        } else {
            if (_check.isCheckFinalField()) {
                return false;
            }
            ret_ = _check.getFieldType().getReturnType();
        }
        return ExecInheritsAdv.checkObjectEx(ret_, right_.getClassName(_context), _context, _stackCall) == null;
    }

    private static Struct strValue(ContextEl _context, CheckedExecOperationNodeInfos _check) {
        if (_check.isStaticField()) {
            return NumParsers.getStaticField(_check.getIdClass(), _context.getClasses().getStaticFields());
        }
        Struct ins_ = _check.getInstance();
        if (!(ins_ instanceof FieldableStruct)) {
            return null;
        }
        ClassFieldStruct found_ = ((FieldableStruct) ins_).getEntryStruct(_check.getIdClass());
        if (found_ == null) {
            return null;
        }
        return found_.getStruct();
    }
    private static boolean enterExit(ContextEl _context, StackCall _stackCall) {
        return _stackCall.normalCallNoExit(_context) || _stackCall.getReadWrite() != ReadWrite.ENTRY;
    }
}
