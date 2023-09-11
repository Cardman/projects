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
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.StringMap;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class DbgStackStopper extends AbsStackStopperImpl {
    public DbgStackStopper(AbsLogDbg _log) {
        super(_log);
    }
    @Override
    public int checkNext(ContextEl _context, StackCall _stackCall) {
        if (!_stackCall.getBreakPointInfo().getBreakPointOutputInfo().isStoppedBreakPoint()) {
            return 0;
        }
        if (_stackCall.getInitializingTypeInfos().getInitEnums() != InitPhase.NOTHING || stopAtWp(getLogger(),_context, _stackCall)) {
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
        if (_context.pausedLoop().getAndSet(false)) {
            return StopDbgEnum.PAUSE;
        }
        return stopAtCheckedBp(getLogger(),_context, _stackCall);
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
            return new StdMethodCheckedExecOperationNodeInfos(_context.getStandards().getCoreNames().getAliasObject(),(StdParamsOperable)_o,cl((StdParamsOperable) _o,_context), _el.getArguments(), ((StdParamsOperable)_o).instance(_el.getArguments(), _last), _ex);
        }
        if (_o instanceof ExecAnnotationMethodOperation) {
            Struct instance_ = instance(0,_el, _o, _last);
            return new CheckedExecOperationNodeInfos((ExecAnnotationMethodOperation)_o, _context,WatchPoint.BPC_READ, formatted(_context, instance_), instance_, null);
        }
        if (_o instanceof ExecArrayFieldOperation) {
            Struct instance_ = instance(0,_el, _o, _last);
            return new ArrCheckedExecOperationNodeInfos(_context,instance_);
        }
        return core(_context,_el,_last,_ex, _o);
    }

    private static CoreCheckedExecOperationNodeInfos affectationOrCompound(ExpressionLanguage _el, ExecAbstractAffectOperation _o, ContextEl _context, AbstractPageEl _last) {
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
        return settable(_el, _o, _context, false,WatchPoint.BPC_WRITE, new Struct[]{right_, NullStruct.NULL_VALUE}, _last);
    }

    private static CoreCheckedExecOperationNodeInfos field(ExpressionLanguage _el, ExecSettableFieldOperation _o, ContextEl _context, AbstractPageEl _last) {
        if (!_o.resultCanBeSet()) {
            int anc_ = _o.getSettableFieldContent().getAnc();
            Struct instance_ = instance(anc_,_el, _o, _last);
            if (sub(_o)) {
                return new CheckedExecOperationNodeInfos(_o, WatchPoint.BPC_COMPOUND_READ, formatted(_context, _o, instance_), instance_, null);
            }
            return new CheckedExecOperationNodeInfos(_o, WatchPoint.BPC_READ, formatted(_context, _o, instance_), instance_, null);
        }
        return null;
    }

    private static CoreCheckedExecOperationNodeInfos variable(ExecStdRefVariableOperation _o, ContextEl _context, AbstractPageEl _last) {
        if (!_o.resultCanBeSet()) {
            if (sub(_o)) {
                return prVar(_o, _context, WatchPoint.BPC_COMPOUND_READ, _last);
            }
            return prVar(_o, _context, WatchPoint.BPC_READ, _last);
        }
        return null;
    }

    private static CoreCheckedExecOperationNodeInfos prVar(ExecStdRefVariableOperation _o, ContextEl _context, int _mode, AbstractPageEl _last) {
        String v_ = _o.getVariableContent().getVariableName();
        AbstractWrapper w_ = ExecVariableTemplates.getWrapper(v_, _o.getVariableContent().getDeep(), _last.getCache(), _last.getRefParams());
        if (w_ instanceof FieldWrapper) {
            Struct instance_ = value(w_);
            return new CheckedExecOperationNodeInfos((FieldWrapper) w_, _mode, formatted(_context, (FieldWrapper) w_), instance_, null);
        }
        if (w_ instanceof ArrayCustWrapper) {
            return checkLda(new CallCheckedExecOperationNodeInfos(_context,false,(ArrayCustWrapper) w_));
        }
        return null;
    }
    private static CoreCheckedExecOperationNodeInfos compound(ExpressionLanguage _el, ExecCompoundAffectationOperation _o, ContextEl _context, AbstractPageEl _last) {
        ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_el.getArguments(), _o.getSettableAnc());
        if (argumentPair_.isArgumentTest()) {
            if (!ExecCompoundAffectationOperation.sh(_o.getOperatorContent())) {
                return settable(_el, _o, _context, false,WatchPoint.BPC_COMPOUND_WRITE,new Struct[]{ArgumentListCall.toStr(argumentPair_.getArgument()),NullStruct.NULL_VALUE}, _last);
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
            return settable(_el, _o, _context, false, WatchPoint.BPC_COMPOUND_WRITE, new Struct[]{((ExecCompoundAffectationStringOperation)_o).calculated(_el.getArguments(), _context, _last),((ExecCompoundAffectationStringOperation)_o).leftArg(_el.getArguments())}, _last);
        }
        return null;
    }

    private static CoreCheckedExecOperationNodeInfos settable(ExpressionLanguage _el, ExecAbstractAffectOperation _o, ContextEl _ctx, boolean _exit,int _mode, Struct[] _right, AbstractPageEl _last) {
        ExecOperationNode set_ = _o.getSettable();
        if (isField(set_)) {
            int anc_ = ((ExecSettableFieldOperation) set_).getSettableFieldContent().getAnc();
            Struct instance_ = instanceSet(_el, anc_, set_,  ((ExecSettableFieldOperation) set_).resultCanBeSet(), _last);
            return new CheckedExecOperationNodeInfos((ExecSettableFieldOperation) set_, altMode(_mode,_right[0]), formatted(_ctx, (ExecSettableFieldOperation) set_, instance_), instance_, altRight(_mode,_right[0],_right[1]));
        }
        if (set_ instanceof ExecStdRefVariableOperation || set_ instanceof ExecSettableCallFctOperation) {
            ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_el.getArguments(), set_);
            AbstractWrapper w_ = argumentPair_.getWrapper();
            if (w_ instanceof FieldWrapper) {
                Struct instance_ = value(w_);
                return new CheckedExecOperationNodeInfos((FieldWrapper) w_, altMode(_mode,_right[0]), formatted(_ctx, (FieldWrapper) w_), instance_, altRight(_mode,_right[0],_right[1]));
            }
            if (w_ instanceof ArrayCustWrapper) {
                return checkLda(new CallCheckedExecOperationNodeInfos(_ctx,_exit,(ArrayCustWrapper) w_, _right[0]));
            }
        }
        if (set_ instanceof ExecCustArrOperation) {
            return checkLda(new CallCheckedExecOperationNodeInfos(_ctx, _el.getArguments(), _exit, (ExecCustArrOperation) set_, _right[0]));
        }
        if (set_ instanceof ExecCustArrWriteOperation) {
            return checkLda(new CallCheckedExecOperationNodeInfos(_ctx, _el.getArguments(), _last, _exit, (ExecCustArrWriteOperation) set_, _right[0]));
        }
        return null;
    }

    private static int altMode(int _mode, Struct _right) {
        if (_mode == WatchPoint.BPC_COMPOUND_WRITE && _right == null) {
            return WatchPoint.BPC_COMPOUND_WRITE_ERR;
        }
        return _mode;
    }

    private static Struct altRight(int _mode, Struct _right, Struct _other) {
        if (_mode == WatchPoint.BPC_COMPOUND_WRITE && _right == null) {
            return _other;
        }
        return _right;
    }

    private static boolean isField(ExecOperationNode _o) {
        return (_o instanceof ExecSettableFieldOperation) && !((ExecSettableFieldOperation) _o).isDeclare();
    }

    private static Struct instance(int _anc, ExpressionLanguage _el, ExecOperationNode _o, AbstractPageEl _last) {
        return ExecOperationNode.instance(_o,_anc, _el.getArguments(), _last);
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
    private static CoreCheckedExecOperationNodeInfos refMethod(ContextEl _context, DirectAnnotationRefectMethodPageEl _p) {
        if (_p.isCheckingEntryExit()) {
            Struct instance_ = ArgumentListCall.toStr(_p.getInstance());
            return new CheckedExecOperationNodeInfos(_p.getMetaInfo().getRealId().getName(), _context,WatchPoint.BPC_READ, formatted(_context, instance_), instance_, null);
        }
        return null;
    }
    private static CoreCheckedExecOperationNodeInfos refMethod(ContextEl _context, LambdaAnnotationRefectMethodPageEl _p) {
        if (_p.isCheckingEntryExit()) {
            Struct instance_ = ArgumentListCall.toStr(_p.getInstance());
            return new CheckedExecOperationNodeInfos(_p.getMetaInfo().getRealId().getName(), _context,WatchPoint.BPC_READ, formatted(_context, instance_), instance_, null);
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

    private static CoreCheckedExecOperationNodeInfos lambdaGet(ContextEl _context, LambdaVariableGetValuePageEl _p) {
        if (!_p.isCheckField()) {
            return null;
        }
        ArgumentWrapper firstArgumentWrapper_ = ExecHelper.getFirstArgumentWrapper(_p.getArr().getArgumentWrappers());
        AbstractWrapper w_ = firstArgumentWrapper_.getWrapper();
        if (!_p.isExiting() && w_ instanceof FieldWrapper) {
            Struct instance_ = value(w_);
            return new CheckedExecOperationNodeInfos((FieldWrapper) w_, WatchPoint.BPC_READ, formatted(_context, (FieldWrapper) w_),instance_,null);
        }
        if (w_ instanceof ArrayCustWrapper) {
            return checkLda(new CallCheckedExecOperationNodeInfos(_context,_p.isExiting(),(ArrayCustWrapper) w_));
        }
        return null;
    }

    private static CoreCheckedExecOperationNodeInfos lambdaSet(ContextEl _context, LambdaVariableSetValuePageEl _p) {
        if (!_p.isCheckField()) {
            return null;
        }
        CustList<ArgumentWrapper> argumentWrappers_ = _p.getArr().getArgumentWrappers();
        ArgumentWrapper firstArgumentWrapper_ = ExecHelper.getFirstArgumentWrapper(argumentWrappers_);
        AbstractWrapper w_ = firstArgumentWrapper_.getWrapper();
        if (!_p.isExiting() && w_ instanceof FieldWrapper) {
            Struct right_ = ArgumentListCall.toStr(ArgumentWrapper.helpArg(ExecHelper.getLastArgumentWrapper(argumentWrappers_)));
            Struct instance_ = value(w_);
            return new CheckedExecOperationNodeInfos((FieldWrapper) w_, WatchPoint.BPC_WRITE, formatted(_context, (FieldWrapper) w_),instance_,right_);
        }
        if (w_ instanceof ArrayCustWrapper) {
            Struct right_ = ArgumentListCall.toStr(ArgumentWrapper.helpArg(ExecHelper.getLastArgumentWrapper(argumentWrappers_)));
            return checkLda(new CallCheckedExecOperationNodeInfos(_context,_p.isExiting(),(ArrayCustWrapper) w_, right_));
        }
        return null;
    }

    private static CoreCheckedExecOperationNodeInfos reflectGet(ContextEl _context, ReflectGetFieldPageEl _p) {
        if (!_p.isCheckField()) {
            return null;
        }
        if (_p.isExiting()) {
            return null;
        }
        Struct instance_ = ArgumentListCall.toStr(_p.getArgument());
        return new CheckedExecOperationNodeInfos(_p.getMetaInfo(), WatchPoint.BPC_READ, formatted(_context, _p.getMetaInfo().getFormatted().getRootBlock(), instance_), instance_, null);
    }

    private static CoreCheckedExecOperationNodeInfos reflectSet(ContextEl _context, ReflectSetFieldPageEl _p) {
        if (!_p.isCheckField()) {
            return null;
        }
        if (_p.isExiting()) {
            return null;
        }
        Struct instance_ = ArgumentListCall.toStr(_p.getFirst());
        Struct right_ = ArgumentListCall.toStr(_p.getLast());
        return new CheckedExecOperationNodeInfos(_p.getMetaInfo(), WatchPoint.BPC_WRITE, formatted(_context, _p.getMetaInfo().getFormatted().getRootBlock(), instance_), instance_, right_);
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
                return settable(_el,(ExecCompoundAffectationOperation) ex_,_context, true,WatchPoint.BPC_COMPOUND_WRITE,new Struct[]{ArgumentListCall.toStr(_last.getReturnedArgument()),NullStruct.NULL_VALUE}, _last);
            }
        }
        if (ex_ instanceof ExecSettableCallFctOperation) {
            if (ex_ instanceof StdParamsOperable) {
                return new StdMethodCheckedExecOperationNodeInfos(_context.getStandards().getCoreNames().getAliasObject(),(StdParamsOperable)ex_,cl((StdParamsOperable) ex_,_context), _el.getArguments(), ((StdParamsOperable)ex_).instance(_el.getArguments(), _last), true);
            }
            CoreCheckedExecOperationNodeInfos c_ = core(_context, _el, _last, true, ex_);
            if (c_ != null) {
                return c_;
            }
            if (sub(ex_)) {
                AbstractWrapper w_ = _last.getWrapper();
                return wrapp(w_, _context, WatchPoint.BPC_COMPOUND_READ);
            } else if (!((ExecSettableCallFctOperation) ex_).resultCanBeSet()) {
                AbstractWrapper w_ = _last.getWrapper();
                return wrapp(w_, _context, WatchPoint.BPC_READ);
            }
        }
        return null;
    }
    private static CoreCheckedExecOperationNodeInfos core(ContextEl _context, ExpressionLanguage _el, AbstractPageEl _last, boolean _exit, ExecOperationNode _ex) {
        if (_ex instanceof ExecFctOperation) {
            return checkLda(new CallCheckedExecOperationNodeInfos(_context, _el.getArguments(), _last, _exit, (ExecFctOperation) _ex));
        }
        if (_ex instanceof ExecCustArrOperation) {
            return checkLda(new CallCheckedExecOperationNodeInfos(_context, _el.getArguments(), _last, _exit, (ExecCustArrOperation) _ex));
        }
        if (_ex instanceof ExecCustArrReadOperation) {
            return checkLda(new CallCheckedExecOperationNodeInfos(_context, _el.getArguments(), _last, _exit, (ExecCustArrReadOperation) _ex));
        }
        return null;
    }

    private static CallCheckedExecOperationNodeInfos checkLda(CallCheckedExecOperationNodeInfos _c) {
        if (_c.lda() != null) {
            return _c;
        }
        return null;
    }

    private static String cl(StdParamsOperable _o, ContextEl _c) {
        if (_o instanceof ExecCallDynMethodOperation) {
            return _c.getStandards().getReflect().getAliasFct();
        }
        if (_o instanceof ExecStdFctOperation) {
            return ((ExecStdFctOperation)_o).cl();
        }
        return "";
    }

    private static CoreCheckedExecOperationNodeInfos wrapp(AbstractWrapper _w, ContextEl _context, int _m) {
        if (_w instanceof FieldWrapper) {
            Struct instance_ = value(_w);
            String formatted_ = ExecInherits.getQuickFullTypeByBases(instance_.getClassName(_context), ((FieldWrapper) _w).getId().getClassName(), _context);
            return new CheckedExecOperationNodeInfos((FieldWrapper) _w, _m,ExecFormattedRootBlock.build(formatted_, _context.getClasses()),instance_,null);
        }
        if (_w instanceof ArrayCustWrapper) {
            return checkLda(new CallCheckedExecOperationNodeInfos(_context,true,(ArrayCustWrapper) _w));
        }
        return null;
    }

    private static boolean stopAtWp(AbsLogDbg _l,ContextEl _context,StackCall _stackCall) {
        if (_stackCall.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint() == StopDbgEnum.STEP_RETURN_METHOD) {
            CoreCheckedExecOperationNodeInfos infos_ = infos(_context, _stackCall);
            AbstractPageEl p_ = _stackCall.getLastPage();
            Struct str_ = stopExcValuRetThrowCatch(_context, _stackCall, p_);
            StopDbgEnum s_ = logs(_l,current(_context, _stackCall, infos_, str_),_context,_stackCall);
            if (s_ != StopDbgEnum.NONE) {
                _stackCall.getBreakPointInfo().getBreakPointOutputInfo().setStoppedBreakPoint(s_);
                return true;
            }
        }
        if (_stackCall.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint() == StopDbgEnum.METHOD_STD_ENTRY || _stackCall.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint() == StopDbgEnum.METHOD_ENTRY) {
            CoreCheckedExecOperationNodeInfos infos_ = infos(_context, _stackCall);
            AbstractPageEl p_ = _stackCall.getLastPage();
            StopDbgEnum s_ = logs(_l,otherStopAndBp(_context, _stackCall, infos_, p_),_context,_stackCall);
            if (s_ != StopDbgEnum.NONE) {
                _stackCall.getBreakPointInfo().getBreakPointOutputInfo().setStoppedBreakPoint(s_);
                return true;
            }
        }
        return false;
    }

    private static StopDbgEnum stopAtCheckedBp(AbsLogDbg _l,ContextEl _context, StackCall _stackCall) {
        AbstractPageEl p_ = _stackCall.getLastPage();
        CoreCheckedExecOperationNodeInfos infos_ = infos(_context, _stackCall);
        if (!checkBreakPoint(_context,_stackCall,p_,infos_)) {
            return StopDbgEnum.NONE;
        }
        Struct exc_ = stopExcValuRetThrowCatch(_context, _stackCall, p_);
        StopDbgEnum step_ = stopStep(_context, _stackCall, p_, exc_);
        if (step_ != StopDbgEnum.NONE) {
            return step_;
        }
        return logs(_l,current(_context, _stackCall, infos_, exc_),_context,_stackCall);
    }
    private static StopDbgEnum logs(AbsLogDbg _l, StopDbgEnum _s, ContextEl _context, StackCall _stackCall) {
        if (_s == StopDbgEnum.NONE) {
            return _s;
        }
        AbstractPageEl p_ = _stackCall.getLastPage();
        BreakPointOutputInfo out_ = _stackCall.getBreakPointInfo().getBreakPointOutputInfo();
        BreakPointCondition bp_ = out_.getBpc();
        if (bp_.getStackLog().get()) {
            for (String s: ResultContextLambda.trace(_stackCall,_context)) {
                _l.log(s);
            }
        }
        CoreCheckedExecOperationNodeInfos o_ = out_.getOperElt();
        ResultContextLambda logs_ = bp_.getLogs();
        if (logs_ != null) {
            for (String s: logs_.evalLog(_context, o_, p_)) {
                _l.log(s);
            }
        }
        if (bp_.getSuspend().get()) {
            return _s;
        }
        out_.setBpc(null);
        out_.setOperElt(null);
        return StopDbgEnum.NONE;
    }

    private static StopDbgEnum current(ContextEl _context, StackCall _stackCall, CoreCheckedExecOperationNodeInfos _infos, Struct _exc) {
        if (_stackCall.getBreakPointInfo().getBreakPointInputInfo().isMute()) {
            return StopDbgEnum.NONE;
        }
        AbstractPageEl p_ = _stackCall.getLastPage();
        if (_stackCall.normalCallNoExit(_context)) {
            return enterCase(_context, _stackCall, p_);
        }
        if (exitMethod(_context, _stackCall, p_)) {
            return StopDbgEnum.METHOD_EXIT;
        }
        if (_exc != null) {
            if (stopExc(_context, _stackCall, p_, _exc)) {
                return StopDbgEnum.EXCEPTION;
            }
            return defBp(_context, _stackCall);
        }
        if (_infos instanceof StdMethodCheckedExecOperationNodeInfos && !((StdMethodCheckedExecOperationNodeInfos)_infos).isExiting()) {
            return enterCase(_context,(StdMethodCheckedExecOperationNodeInfos)_infos,_stackCall,p_);
        }
        if (_infos instanceof CheckedExecOperationNodeInfos) {
            return checkedWp(_context, _stackCall, p_, (CheckedExecOperationNodeInfos) _infos);
        }
        if (_infos instanceof CallCheckedExecOperationNodeInfos && !((CallCheckedExecOperationNodeInfos) _infos).isExit()) {
            return enterCase(_context, _stackCall, p_, (CallCheckedExecOperationNodeInfos) _infos);
        }
        return otherStopAndBp(_context, _stackCall, _infos, p_);
    }

    private static StopDbgEnum otherStopAndBp(ContextEl _context, StackCall _stackCall, CoreCheckedExecOperationNodeInfos _infos, AbstractPageEl _p) {
        CoreCheckedExecOperationNodeInfos arr_ = arr(_context, _stackCall, _infos);
        if (arr_ != null) {
            return arr(_context, _stackCall, _p, arr_);
        }
        return afterArr(_context, _stackCall, _infos, _p);
    }

    private static StopDbgEnum afterArr(ContextEl _context, StackCall _stackCall, CoreCheckedExecOperationNodeInfos _infos, AbstractPageEl _p) {
        if (_infos instanceof StdMethodCheckedExecOperationNodeInfos && ((StdMethodCheckedExecOperationNodeInfos) _infos).isExiting()) {
            return enterCase(_context, (StdMethodCheckedExecOperationNodeInfos) _infos, _stackCall, _p);
        }
        if (_infos instanceof CallCheckedExecOperationNodeInfos && ((CallCheckedExecOperationNodeInfos) _infos).isExit() && exitMethod(_context, _stackCall, _p, (CallCheckedExecOperationNodeInfos) _infos)) {
            return StopDbgEnum.METHOD_EXIT;
        }
        return defBp(_context, _stackCall);
    }

    private static StopDbgEnum arr(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, CoreCheckedExecOperationNodeInfos _arr) {
        if (stopArr(_context, _stackCall, _p, _arr)){
            return StopDbgEnum.ARRAY;
        }
        return StopDbgEnum.NONE;
    }

    private static CoreCheckedExecOperationNodeInfos arr(ContextEl _context, StackCall _stackCall, CoreCheckedExecOperationNodeInfos _infos) {
        if (_infos instanceof ArrCheckedExecOperationNodeInfos) {
            return _infos;
        }
        if (_infos instanceof StdMethodCheckedExecOperationNodeInfos && ((StdMethodCheckedExecOperationNodeInfos) _infos).isExiting()) {
            return null;
        }
        if (_infos instanceof CallCheckedExecOperationNodeInfos && ((CallCheckedExecOperationNodeInfos) _infos).isExit()) {
            return null;
        }
        if (_infos instanceof CallCheckedExecOperationNodeInfos) {
            return callDyn(_context, _stackCall, ((CallCheckedExecOperationNodeInfos)_infos).getArgs(), ((CallCheckedExecOperationNodeInfos)_infos).lda());
        }
        if (_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() != null) {
            return null;
        }
        AbstractPageEl p_ = _stackCall.getLastPage();
        if (p_.isEmptyEl()) {
            return null;
        }
        ExpressionLanguage el_ = p_.getLastEl();
        ExecOperationNode o_ = el_.getCurrentOper();
        if (!(o_ instanceof ExecCallDynMethodOperation)) {
            return null;
        }
        if (!((ExecCallDynMethodOperation) o_).args(el_.getArguments(), _context.getStandards().getCoreNames().getAliasObject()).getArguments().getArgumentWrappers().isEmpty()) {
            return callDyn(_context, _stackCall, ((ExecCallDynMethodOperation) o_).list(el_.getArguments()).getArguments(), ((ExecCallDynMethodOperation) o_).instance(el_.getArguments(), p_));
        }
        return null;
    }

    private static ArrCheckedExecOperationNodeInfos callDyn(ContextEl _context, StackCall _stackCall, ArgumentListCall _ls, Struct _ins) {
        CustList<ArgumentWrapper> ret_ = _ls.getArgumentWrappers();
        CustomFoundExc fe_ = ExecInvokingOperation.foundExc(ArgumentListCall.toStr(_ins), ret_, _context, _stackCall);
        if (fe_ == null && _ins instanceof LambdaMethodStruct && !((LambdaMethodStruct) _ins).isSafeInstance()) {
            String meth_ = ((LambdaMethodStruct) _ins).getMethodName();
            if (vars(meth_)) {
                return null;
            }
            CustList<ArgumentWrapper> formal_ = AbstractParamChecker.formal(ret_, ((LambdaMethodStruct) _ins).getMethodName());
            ArgumentListCall call_ = AbstractParamChecker.adjusted(_stackCall, (LambdaMethodStruct) _ins, formal_, ret_);
            if (call_ != null) {
                Struct metaInfo_ = ((LambdaMethodStruct) _ins).getMetaInfo();
                if (!(metaInfo_ instanceof MethodMetaInfo) && call_.getArgumentWrappers().isEmpty()) {
                    Struct ins_ = AbstractParamChecker.adjustedInstance(_stackCall, (LambdaMethodStruct) _ins, ret_);
                    return new ArrCheckedExecOperationNodeInfos(_context, ins_);
                }
            }
        }
        return null;
    }

    private static boolean vars(String _meth) {
        return StringUtil.quickEq(_meth, ":") || StringUtil.quickEq(_meth, "=");
    }

    private static StopDbgEnum defBp(ContextEl _context, StackCall _stackCall) {
        AbstractPageEl p_ = _stackCall.getLastPage();
        if (_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() != null || _stackCall.trueException() != null || getCurrentOper(p_) != null || _stackCall.getReadWrite() != ReadWrite.ENTRY) {
            return StopDbgEnum.NONE;
        }
        for (int i : list(p_)) {
            BreakPoint bp_ = _context.getNotNull(p_.getFile(), i);
            if (stopCurrentBp(_context, _stackCall,p_,bp_)) {
                return StopDbgEnum.INSTRUCTION;
            }
        }
        return StopDbgEnum.NONE;
    }

    private static StopDbgEnum checkedWp(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, CheckedExecOperationNodeInfos _infos) {
        if (!okField(_context,_stackCall, _infos)) {
            return StopDbgEnum.NONE;
        }
        ClassField clField_ = _infos.getIdClass();
        WatchPoint bp_ = _context.getNotNullWatch(_infos.isTrueField(), _infos.getNbType(),clField_.getFieldName());
        BreakPointCondition condition_ = stopCurrentWpCondition(bp_, _infos);
        if (stopCurrent(_context, _stackCall, _p, condition_, _infos)) {
            return StopDbgEnum.FIELD;
        }
        return StopDbgEnum.NONE;
    }

    private static StopDbgEnum enterCase(ContextEl _context, StdMethodCheckedExecOperationNodeInfos _ex, StackCall _stackCall, AbstractPageEl _p) {
        if (!okMethod(_context,_stackCall,_ex)) {
            return StopDbgEnum.NONE;
        }
        StandardNamedFunction call_ = _ex.getFct();
        ExecFormattedRootBlock glClass_ = _ex.getDeclaring();
        Struct instance_ = _ex.getInstance();
        ArgumentListCall original_ = _ex.getArgs();
        StdMethodPointBlockPair pairs_ = _context.getPair(call_);
        if (pairs_ != null) {
            Parameters params_ = build(_context, pairs_, original_);
            MethodPoint mp_ = pairs_.getValue();
            if (stopCurrentMp(_context, _stackCall, _p, mp_, _ex.isExiting(),new CheckedMethodInfos(glClass_, instance_, params_))) {
                if (_ex.isExiting()) {
                    return StopDbgEnum.METHOD_STD_EXIT;
                }
                return StopDbgEnum.METHOD_STD_ENTRY;
            }
        }
        CoreCheckedExecOperationNodeInfos arr_ = arr(_context, _stackCall, _ex);
        if (arr_ != null) {
            return arr(_context, _stackCall, _p, arr_);
        }
        return StopDbgEnum.NONE;
    }

    private static StopDbgEnum enterCase(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
        ExecBlock call_ = call(_stackCall.getCallingState());
        ExecFormattedRootBlock glClass_ = globalClass(_stackCall.getCallingState());
        Struct instance_ = instance(_stackCall.getCallingState());
        Parameters original_ = params(_stackCall.getCallingState());
        CustList<MethodPointBlockPairRootBlock> pairs_ = _context.getPairs(call_, glClass_, _context, instance_,false);
        for (MethodPointBlockPairRootBlock m: pairs_) {
            Parameters params_ = build(original_.getRefParameters(), original_.getCache(), _context, m.getId());
            MethodPoint mp_ = m.getId().getValue();
            if (stopCurrentMp(_context, _stackCall, _p, mp_, false,new CheckedMethodInfos(m.getValue(), instance_, params_))) {
                return StopDbgEnum.METHOD_ENTRY;
            }
        }
        return StopDbgEnum.NONE;
    }

    private static StopDbgEnum enterCase(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, CallCheckedExecOperationNodeInfos _c) {
        ExecNamedFunctionBlock call_ = _c.getPoly().getPair().getFct();
        ExecFormattedRootBlock glClass_ = _c.getFormattedRootBlock();
        Struct instance_ = _c.getInstance();
        Parameters original_ = ExecTemplates.quickArgsSet(call_,glClass_,_c.getCache(),_c.getArgs(),_context,_stackCall);
        CustList<MethodPointBlockPairRootBlock> pairs_ = _context.getPairs(call_, glClass_, _context, instance_,false);
        for (MethodPointBlockPairRootBlock m: pairs_) {
            Parameters params_ = build(original_.getRefParameters(), original_.getCache(), _context, m.getId());
            MethodPoint mp_ = m.getId().getValue();
            if (stopCurrentMp(_context, _stackCall, _p, mp_, false,new CheckedMethodInfos(m.getValue(), instance_, params_))) {
                return StopDbgEnum.METHOD_ENTRY;
            }
        }
        LambdaStruct lda_ = _c.lda();
        ArrCheckedExecOperationNodeInfos arr_ = callDyn(_context, _stackCall, _c.getArgs(), lda_);
        if (arr_ != null) {
            return arr(_context, _stackCall, _p, arr_);
        }
        return StopDbgEnum.NONE;
    }

    private static boolean exitMethod(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
        if (exiting(_stackCall)) {
            CustList<MethodPointBlockPairRootBlock> pairs_ = _context.getPairs(_p.getBlockRoot(), _p.getGlobalClass(),_context, _p.getGlobalStruct(), true);
            for (MethodPointBlockPairRootBlock m: pairs_) {
                Parameters params_ = build(_p.getRefParams(), _p.getCache(), _context, m.getId());
                MethodPoint mp_ = m.getId().getValue();
                if (stopCurrentMp(_context, _stackCall, _p, mp_, true, new CheckedMethodInfos(m.getValue(),_p.getGlobalStruct(), params_))) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean exitMethod(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, CallCheckedExecOperationNodeInfos _c) {
        ExecNamedFunctionBlock call_ = _c.getPoly().getPair().getFct();
        ExecFormattedRootBlock glClass_ = _c.getFormattedRootBlock();
        Struct instance_ = _c.getInstance();
        Parameters original_ = ExecTemplates.quickArgsSet(call_,glClass_, _c.getCache(), _c.getArgs(), _context, _stackCall);
        CustList<MethodPointBlockPairRootBlock> pairs_ = _context.getPairs(call_, glClass_, _context, instance_, true);
        for (MethodPointBlockPairRootBlock m: pairs_) {
            Parameters params_ = build(original_.getRefParameters(), original_.getCache(), _context, m.getId());
            MethodPoint mp_ = m.getId().getValue();
            if (stopCurrentMp(_context, _stackCall, _p, mp_, true, new CheckedMethodInfos(m.getValue(), instance_, params_))) {
                return true;
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
        CustList<String> ls_ = _id.getSm().names();
        StringMap<AbstractWrapper> pars_ = new StringMap<AbstractWrapper>();
        for (ArgumentWrapper a: _params.getArgumentWrappers()) {
            pars_.addEntry("",new VariableWrapper(LocalVariable.newLocalVariable(ArgumentListCall.toStr(a.getValue()),_conf)));
        }
        return build(pars_, null, ls_);
    }

    private static Parameters build(StringMap<AbstractWrapper> _params, Cache _cache, ContextEl _conf, MethodPointBlockPair _id) {
        CustList<String> ls_ = _id.getMp().names(_conf);
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

    private static StopDbgEnum stopStep(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, Struct _str) {
        if (_str != null || _stackCall.normalCallNoExit(_context)) {
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
                if (_context.isTmp(_p.getFile(), i)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean stopArr(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, CoreCheckedExecOperationNodeInfos _arr) {
        String clName_ = _arr.getInstance().getClassName(_context);
        if (checkArr(_context, _stackCall, _p, _context.getNotNullArr(clName_,true),_arr)) {
            return true;
        }
        if (!clName_.isEmpty() && checkArr(_context, _stackCall, _p, _context.getNotNullArr(StringExpUtil.getIdFromAllTypes(clName_), false),_arr)) {
            return true;
        }
        return checkArr(_context, _stackCall, _p, _context.getNotNullArr("",false),_arr);
    }
    private static boolean checkArr(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, ArrPoint _bp, CoreCheckedExecOperationNodeInfos _arr) {
        BreakPointCondition bpc_ = stopArrValue(_bp);
        return stopCurrent(_context, _stackCall, _p, bpc_, _arr);
    }

    private static boolean stopExc(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, Struct _str) {
        String clName_ = _str.getClassName(_context);
        if (checkExc(_context, _stackCall, _p, _str, _context.getNotNullExc(clName_,true))) {
            return true;
        }
        if (!clName_.isEmpty() && checkExc(_context, _stackCall, _p, _str, _context.getNotNullExc(StringExpUtil.getIdFromAllTypes(clName_), false))) {
            return true;
        }
        return checkExc(_context, _stackCall, _p, _str, _context.getNotNullExc("",false));
    }

    private static boolean checkExc(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, Struct _str, ExcPoint _bp) {
        BreakPointCondition bpc_ = stopExcValue(_p, _bp, _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting());
        String clName_ = _str.getClassName(_context);
        return stopCurrent(_context, _stackCall, _p, bpc_, new CoreCheckedExecOperationNodeInfos(ExecFormattedRootBlock.build(clName_, _context.getClasses()), _str,null));
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
        if (!_ex.isEnabled()) {
            return null;
        }
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

    private static BreakPointCondition stopArrValue(ArrPoint _ex) {
        if (!_ex.isEnabled()) {
            return null;
        }
        if (_ex.isLength()) {
            return _ex.getResultLength();
        }
        return null;
    }
    private static boolean stopCurrentBp(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, BreakPoint _bp) {
        BreakPointCondition condition_ = stopCurrentBpCondition(_p,_bp);
        return stopCurrent(_context, _stackCall, _p, condition_,null);
    }

    private static boolean stopCurrentMp(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, MethodPoint _bp, boolean _exit, CoreCheckedExecOperationNodeInfos _info) {
        BreakPointCondition condition_ = stopCurrentMpCondition(_bp,_exit);
        return stopCurrent(_context, _stackCall, _p, condition_, _info);
    }
    private static boolean stopCurrent(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, BreakPointCondition _condition, CoreCheckedExecOperationNodeInfos _info) {
        if (!okStack(_context, _stackCall, _condition)) {
            return false;
        }
        ResultContextLambda resLda_ = _condition.getResult();
        if (resLda_ != null && !condition(_context, _stackCall, _p, resLda_, _info, _condition.getStackErrLog().get(), _condition.getStackResErrLog().get())) {
            return false;
        }
        return postCondition(_stackCall, _condition, _info);
    }
    private static boolean postCondition(StackCall _stackCall, BreakPointCondition _condition, CoreCheckedExecOperationNodeInfos _info) {
        if (countMatch(_condition)) {
            if (!_condition.getEnabled().get()) {
                return false;
            }
            for (BreakPointCondition o: _condition.getOthers().elts()) {
                if (_condition.getDisableAgain().get()) {
                    if (!o.getHit().getAndSet(false)) {
                        return false;
                    }
                } else if (!o.getHit().get()) {
                    return false;
                }
            }
            _condition.getHit().set(true);
            if (_condition.getDisableWhenHit().get()) {
                _condition.getEnabled().set(false);
            }
            BreakPointOutputInfo out_ = _stackCall.getBreakPointInfo().getBreakPointOutputInfo();
            out_.setOperElt(_info);
            out_.setBpc(_condition);
            return true;
        }
        return false;
    }


    private static BreakPointCondition stopCurrentWpCondition(WatchPoint _bp, CheckedExecOperationNodeInfos _check) {
        if (!_bp.isEnabled()) {
            return null;
        }
        if (!(_bp.isRead() && _check.getModeField() == WatchPoint.BPC_READ || _bp.isWrite() && _check.getModeField() == WatchPoint.BPC_WRITE || _bp.isCompoundRead() && _check.getModeField() == WatchPoint.BPC_COMPOUND_READ || _bp.isCompoundWrite() && _check.getModeField() == WatchPoint.BPC_COMPOUND_WRITE || _bp.isCompoundWriteErr() && _check.getModeField() == WatchPoint.BPC_COMPOUND_WRITE_ERR)) {
            return null;
        }
        if (_check.getModeField() == WatchPoint.BPC_READ) {
            return _bp.getResultRead();
        }
        if (_check.getModeField() == WatchPoint.BPC_WRITE) {
            return _bp.getResultWrite();
        }
        if (_check.getModeField() == WatchPoint.BPC_COMPOUND_READ) {
            return _bp.getResultCompoundRead();
        }
        if (_check.getModeField() == WatchPoint.BPC_COMPOUND_WRITE) {
            return _bp.getResultCompoundWrite();
        }
        return _bp.getResultCompoundWriteErr();
    }
    private static boolean countMatch(BreakPointCondition _cond) {
        int c_ = _cond.getCountModulo().get();
        if (c_ <= 0) {
            return true;
        }
        return NumberUtil.mod(_cond.incr(),c_) == 0;
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

    private static boolean condition(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, ResultContextLambda _result, CoreCheckedExecOperationNodeInfos _info, boolean _errStack, boolean _errLogs) {
        StackCallReturnValue result_ = _result.eval(_context, _info, _p);
        if (result_.getStack().getCallingState() != null) {
            if (_errStack) {
                for (String s: ResultContextLambda.trace(_stackCall,_context)) {
                    _stackCall.getStopper().getLogger().log(s);
                }
            }
            if (_errLogs) {
                for (String l: ResultContextLambda.traceView(result_.getStack(),_context)) {
                    _stackCall.getStopper().getLogger().log(l);
                }
            }
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
        if (_stackCall.trueException() != null || (_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() != null && _stackCall.getBreakPointInfo().getBreakPointInputInfo().getStep() == StepDbgActionEnum.RETURN_METHOD) || _infos != null) {
            return true;
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

    private static boolean okMethod(ContextEl _context, StackCall _stackCall, StdMethodCheckedExecOperationNodeInfos _check) {
        return ExecTemplates.checkParams(_context, _check.getOwn(),_check.getFct().id(), ArgumentListCall.toStr(_check.getInstance()), _check.getArgs().getArguments(), _stackCall) == null;
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
