package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.common.symbol.CommonOperSymbol;
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
import code.expressionlanguage.exec.symbols.ExecOperSymbol;
import code.expressionlanguage.exec.util.*;
import code.expressionlanguage.exec.variables.*;
import code.expressionlanguage.fcts.*;
import code.expressionlanguage.options.ResultContextLambda;
import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.stds.StdCaller;
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

    private static CoreCheckedExecOperationNodeInfos expOper(ExpressionLanguage _el, ExecOperationNode _o, ContextEl _context, AbstractPageEl _last) {
        if (_o instanceof ExecAbstractAffectOperation) {
            return affectationOrCompound(_el, (ExecAbstractAffectOperation)_o, _context, _last);
        }
        if (isField(_o)) {
            return field(_el, (ExecSettableFieldOperation) _o,  _context, _last);
        }
        if (_o instanceof ExecStdRefVariableOperation) {
            return variable((ExecStdRefVariableOperation)_o,  _context, _last);
        }
        if (_o instanceof ExecAnnotationMethodOperation) {
            Struct instance_ = instance(0,_el, _o, _last);
            return new CheckedExecOperationNodeInfos((ExecAnnotationMethodOperation)_o, _context,WatchPoint.BPC_READ, formatted(_context, instance_), instance_, null);
        }
        if (_o instanceof ExecArrayFieldOperation) {
            Struct instance_ = instance(0,_el, _o, _last);
            return new ArrCheckedExecOperationNodeInfos(_context,instance_);
        }
        if (_o instanceof ExecArrOperation) {
            return arr(_el,(ExecArrOperation) _o,_context, _last);
        }
        if (_o instanceof ExecAbstractArrayInstancingOperation && ((ExecAbstractArrayInstancingOperation)_o).getCountArrayDims() >= 0) {
            CustList<Argument> args_ = ((ExecAbstractArrayInstancingOperation)_o).getArguments(_el.getArguments());
            int indexes_ = args_.size();
            ArrayStruct arr_ = new ArrayStruct(indexes_,StringExpUtil.getPrettyArrayType(_context.getStandards().getPrimTypes().getAliasPrimInteger()));
            for (int i = 0; i <indexes_; i++) {
                arr_.set(i,ArgumentListCall.toStr(args_.get(i)));
            }
            String c_ = StringExpUtil.getPrettyArrayType(_last.formatVarType(((ExecAbstractArrayInstancingOperation)_o).getClassName()), indexes_ + ((ExecAbstractArrayInstancingOperation)_o).getCountArrayDims());
            return new ArrCheckedExecOperationNodeInfos(_context, c_, arr_);
        }
        return null;
    }

    private static OperNatCheckedExecOperationNodeInfos expOperNat(ExpressionLanguage _el, ExecOperationNode _o, ContextEl _context, AbstractPageEl _last) {
        if (_o instanceof ExecAbstractAffectOperation) {
            return affectationOrCompoundNat(_el, (ExecAbstractAffectOperation)_o, _context, _last);
        }
        if (_o instanceof ExecQuickOperation) {
            _last.setOffset(_o.getIndexInEl()+ ((ExecQuickOperation) _o).getOperatorContent().getOpOffset());
            Argument arg_ = ExecHelper.getArgumentPair(_el.getArguments(), _o).getArgument();
            if (arg_ != null) {
                return null;
            }
            ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_el.getArguments(), _o.getFirstChild());
            if (argumentPair_.isArgumentTest()) {
                return null;
            }
            CustList<ExecOperationNode> ls_ = ((ExecQuickOperation) _o).getChildrenNodes();
            return operNat(_el, _context, ls_, ((ExecQuickOperation) _o).getOperSymbol(), OperNatPoint.BPC_SIMPLE);
        }
        return null;
    }

    private static OperNatCheckedExecOperationNodeInfos operNat(ExpressionLanguage _el, ContextEl _context, CustList<ExecOperationNode> _ls, ExecOperSymbol _symbol, int _mode) {
        Struct left_ = ArgumentListCall.toStr(Argument.getNullableValue(ExecHelper.getArgumentPair(_el.getArguments(), ExecHelper.getNode(_ls, 0)).getArgument()));
        Struct right_;
        if (_ls.size() > 1) {
            right_ = ArgumentListCall.toStr(Argument.getNullableValue(ExecHelper.getArgumentPair(_el.getArguments(), ExecHelper.getNode(_ls, _ls.size() - 1)).getArgument()));
        } else {
            right_ = null;
        }
        return new OperNatCheckedExecOperationNodeInfos(_context, _symbol.getSgn(), _mode, left_, right_);
    }

    private static CoreCheckedExecOperationNodeInfos expOperPar(ExpressionLanguage _el, ExecOperationNode _o, ContextEl _context, AbstractPageEl _last) {
        if (_o instanceof ExecThisOperation) {
            int anc_ = ((ExecThisOperation) _o).getThisContent().getNbAncestors();
            Struct instance_ = _last.getGlobalStruct();
            return new ParCheckedExecOperationNodeInfos(_context,instance_,anc_);
        }
        if (_o instanceof ExecParentInstanceOperation) {
            Struct instance_ = instance(_el, _o, _last);
            return new ParCheckedExecOperationNodeInfos(_context,instance_,1);
        }
        if (_o instanceof ExecAffectationOperation) {
            return affectationOrCompoundPar(_el,(ExecAffectationOperation) _o,_context,_last);
        }
        if (_o instanceof ExecCustArrOperation) {
            int anc_ = ((ExecCustArrOperation) _o).getReadWrite().getInstRead().getInst().getAnc();
            Struct instance_ = instance(_el, _o, _last);
            return new ParCheckedExecOperationNodeInfos(_context,instance_,anc_);
        }
        if (_o instanceof ExecCustArrReadOperation) {
            int anc_ = ((ExecCustArrReadOperation) _o).getInstRead().getInst().getAnc();
            Struct instance_ = instance(_el, _o, _last);
            return new ParCheckedExecOperationNodeInfos(_context,instance_,anc_);
        }
        if (_o instanceof ExecFctOperation) {
            int anc_ = ((ExecFctOperation) _o).getInst().getInst().getAnc();
            Struct instance_ = instance(_el, _o, _last);
            return new ParCheckedExecOperationNodeInfos(_context,instance_,anc_);
        }
        if (_o instanceof ExecSettableFieldInstOperation&&isField(_o)&&!((ExecSettableFieldInstOperation)_o).resultCanBeSet()) {
            int anc_ = ((ExecSettableFieldInstOperation) _o).getSettableFieldContent().getAnc();
            Struct instance_ = instance(_el, _o, _last);
            return new ParCheckedExecOperationNodeInfos(_context,instance_,anc_);
        }
        return null;
    }

    private static CallCheckedExecOperationNodeInfos expOperCall(ExpressionLanguage _el, ExecOperationNode _o, ContextEl _context, AbstractPageEl _last, boolean _ex) {
        if (_o instanceof ExecAbstractAffectOperation) {
            return affectationOrCompoundCall(_el, (ExecAbstractAffectOperation)_o, _context, _last);
        }
        if (_o instanceof ExecStdRefVariableOperation) {
            return variableCall((ExecStdRefVariableOperation)_o,  _context, _last);
        }
        return core(_context,_el,_last,_ex, _o);
    }

    private static StdMethodCheckedExecOperationNodeInfos expOperStd(ExpressionLanguage _el, ExecOperationNode _o, ContextEl _context, AbstractPageEl _last, boolean _ex) {
        if (_o instanceof StdParamsOperable) {
            return new StdMethodCheckedExecOperationNodeInfos(_context.getStandards().getCoreNames().getAliasObject(),(StdParamsOperable)_o,cl((StdParamsOperable) _o,_context), _el.getArguments(), ((StdParamsOperable)_o).instance(_el.getArguments(), _last), _ex);
        }
        return null;
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
        return settable(_el, _o, _context, WatchPoint.BPC_WRITE, new Struct[]{right_, NullStruct.NULL_VALUE}, _last);
    }
    private static OperNatCheckedExecOperationNodeInfos affectationOrCompoundNat(ExpressionLanguage _el, ExecAbstractAffectOperation _o, ContextEl _context, AbstractPageEl _last) {
        _last.setOffset(_o.getIndexInEl()+ _o.getOperatorContent().getOpOffset());
        if (_o.getSettableParent() instanceof ExecSafeDotOperation && Argument.getNullableValue(ExecHelper.getArgumentPair(_el.getArguments(), _o.getSettableParent().getFirstChild()).getArgument()).isNull()) {
            return null;
        }
        if (_o instanceof ExecCompoundAffectationStringOperation) {
            ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_el.getArguments(), _o.getSettableAnc());
            if (argumentPair_.isArgumentTest()) {
                return null;
            }
            return operNat(_el, _context, _o.getChildrenNodes(), ((ExecCompoundAffectationStringOperation) _o).getSymbol(), OperNatPoint.BPC_COMPOUND);
        }
        return null;
    }

    private static CoreCheckedExecOperationNodeInfos affectationOrCompoundPar(ExpressionLanguage _el, ExecAffectationOperation _o, ContextEl _context, AbstractPageEl _last) {
        _last.setOffset(_o.getIndexInEl()+ _o.getOperatorContent().getOpOffset());
        return settablePar(_el, _o, _context, _last);
    }
    private static CallCheckedExecOperationNodeInfos affectationOrCompoundCall(ExpressionLanguage _el, ExecAbstractAffectOperation _o, ContextEl _context, AbstractPageEl _last) {
        _last.setOffset(_o.getIndexInEl()+ _o.getOperatorContent().getOpOffset());
        if (_o.getSettableParent() instanceof ExecSafeDotOperation && Argument.getNullableValue(ExecHelper.getArgumentPair(_el.getArguments(), _o.getSettableParent().getFirstChild()).getArgument()).isNull()) {
            return null;
        }
        if (_o instanceof ExecCompoundAffectationOperation) {
            return compoundCall(_el, (ExecCompoundAffectationOperation)_o, _context, _last);
        }
        if (_o.getSettable() instanceof ExecStdRefVariableOperation && ((ExecStdRefVariableOperation) _o.getSettable()).isDeclare()) {
            return null;
        }
        CustList<ExecOperationNode> childrenNodes_ = _o.getChildrenNodes();
        Struct right_ = ArgumentListCall.toStr(ExecHelper.getArgumentPair(_el.getArguments(), ExecHelper.getNode(childrenNodes_, childrenNodes_.size() - 1)).getArgument());
        return settableCall(_el, _o, _context, false, new Struct[]{right_, NullStruct.NULL_VALUE}, _last);
    }
    private static CoreCheckedExecOperationNodeInfos field(ExpressionLanguage _el, ExecSettableFieldOperation _o, ContextEl _context, AbstractPageEl _last) {
        if (!_o.resultCanBeSet()) {
            int anc_ = _o.getSettableFieldContent().getAnc();
            Struct instance_ = instance(anc_,_el, _o, _last);
            if (sub(_o)) {
                return new CheckedExecOperationNodeInfos(_context,_o, WatchPoint.BPC_COMPOUND_READ, formatted(_context, _o, instance_), instance_, null);
            }
            return new CheckedExecOperationNodeInfos(_context,_o, WatchPoint.BPC_READ, formatted(_context, _o, instance_), instance_, null);
        }
        return null;
    }

    private static CoreCheckedExecOperationNodeInfos arr(ExpressionLanguage _el, ExecArrOperation _o, ContextEl _context, AbstractPageEl _last) {
        if (!_o.resultCanBeSet()) {
            Struct instance_ = instance(0,_el, _o, _last);
            Struct index_ = ArgumentListCall.toStr(ExecHelper.getArgumentPair(_el.getArguments(), _o.getFirstChild()).getArgument());
            if (sub(_o)) {
                if (index_ instanceof NumberStruct) {
                    ArrayStruct arr_ = new ArrayStruct(1,StringExpUtil.getPrettyArrayType(_context.getStandards().getPrimTypes().getAliasPrimInteger()));
                    arr_.set(0, index_);
                    return new ArrCheckedExecOperationNodeInfos(_context,instance_, WatchPoint.BPC_COMPOUND_READ+1, arr_, null);
                }
                return new ArrCheckedExecOperationNodeInfos(_context,instance_, WatchPoint.BPC_COMPOUND_READ+6, index_, null);
            }
            if (index_ instanceof NumberStruct) {
                ArrayStruct arr_ = new ArrayStruct(1,StringExpUtil.getPrettyArrayType(_context.getStandards().getPrimTypes().getAliasPrimInteger()));
                arr_.set(0, index_);
                return new ArrCheckedExecOperationNodeInfos(_context,instance_, WatchPoint.BPC_READ+1, arr_, null);
            }
            return new ArrCheckedExecOperationNodeInfos(_context,instance_, WatchPoint.BPC_READ+6, index_, null);
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

    private static CallCheckedExecOperationNodeInfos variableCall(ExecStdRefVariableOperation _o, ContextEl _context, AbstractPageEl _last) {
        if (!_o.resultCanBeSet()) {
            if (sub(_o)) {
                return prVarCall(_o, _context, _last);
            }
            return prVarCall(_o, _context, _last);
        }
        return null;
    }

    private static CoreCheckedExecOperationNodeInfos prVar(ExecStdRefVariableOperation _o, ContextEl _context, int _mode, AbstractPageEl _last) {
        String v_ = _o.getVariableContent().getVariableName();
        AbstractWrapper w_ = ExecVariableTemplates.getWrapper(v_, _o.getVariableContent().getDeep(), _last.getCache(), _last.getRefParams());
        return wrapperElt(_context,w_,_mode,null);
    }

    private static CallCheckedExecOperationNodeInfos prVarCall(ExecStdRefVariableOperation _o, ContextEl _context, AbstractPageEl _last) {
        String v_ = _o.getVariableContent().getVariableName();
        AbstractWrapper w_ = ExecVariableTemplates.getWrapper(v_, _o.getVariableContent().getDeep(), _last.getCache(), _last.getRefParams());
        if (w_ instanceof ArrayCustWrapper) {
            return checkLda(new CallCheckedExecOperationNodeInfos(_context,false,(ArrayCustWrapper) w_));
        }
        return null;
    }
    private static CoreCheckedExecOperationNodeInfos compound(ExpressionLanguage _el, ExecCompoundAffectationOperation _o, ContextEl _context, AbstractPageEl _last) {
        ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_el.getArguments(), _o.getSettableAnc());
        if (argumentPair_.isArgumentTest()) {
            if (!ExecCompoundAffectationOperation.sh(_o.getOperatorContent())) {
                return settable(_el, _o, _context, WatchPoint.BPC_COMPOUND_WRITE,new Struct[]{ArgumentListCall.toStr(argumentPair_.getArgument()),NullStruct.NULL_VALUE}, _last);
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
            return settable(_el, _o, _context, WatchPoint.BPC_COMPOUND_WRITE, new Struct[]{((ExecCompoundAffectationStringOperation)_o).calculated(_el.getArguments(), _context, _last),((ExecCompoundAffectationStringOperation)_o).leftArg(_el.getArguments())}, _last);
        }
        return null;
    }

    private static CallCheckedExecOperationNodeInfos compoundCall(ExpressionLanguage _el, ExecCompoundAffectationOperation _o, ContextEl _context, AbstractPageEl _last) {
        ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_el.getArguments(), _o.getSettableAnc());
        if (argumentPair_.isArgumentTest()) {
            if (!ExecCompoundAffectationOperation.sh(_o.getOperatorContent())) {
                return settableCall(_el, _o, _context, false, new Struct[]{ArgumentListCall.toStr(argumentPair_.getArgument()),NullStruct.NULL_VALUE}, _last);
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
            return settableCall(_el, _o, _context, false, new Struct[]{((ExecCompoundAffectationStringOperation)_o).calculated(_el.getArguments(), _context, _last),((ExecCompoundAffectationStringOperation)_o).leftArg(_el.getArguments())}, _last);
        }
        return null;
    }
    private static CoreCheckedExecOperationNodeInfos settable(ExpressionLanguage _el, ExecAbstractAffectOperation _o, ContextEl _ctx, int _mode, Struct[] _right, AbstractPageEl _last) {
        ExecOperationNode set_ = _o.getSettable();
        if (isField(set_)) {
            int anc_ = ((ExecSettableFieldOperation) set_).getSettableFieldContent().getAnc();
            Struct instance_ = instanceSet(_el, anc_, set_,  ((ExecSettableFieldOperation) set_).resultCanBeSet(), _last);
            return new CheckedExecOperationNodeInfos(_ctx,(ExecSettableFieldOperation) set_, altMode(_mode,_right[0]), formatted(_ctx, (ExecSettableFieldOperation) set_, instance_), instance_, altRight(_mode,_right[0],_right[1]));
        }
        if (set_ instanceof ExecStdRefVariableOperation || set_ instanceof ExecSettableCallFctOperation) {
            ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_el.getArguments(), set_);
            AbstractWrapper w_ = argumentPair_.getWrapper();
            return wrapperElt(_ctx,w_,altMode(_mode,_right[0]),altRight(_mode,_right[0],_right[1]));
        }
        if (set_ instanceof ExecArrOperation) {
            Struct instance_ = instance(0,_el, set_, _last);
            Struct index_ = ArgumentListCall.toStr(ExecHelper.getArgumentPair(_el.getArguments(), set_.getFirstChild()).getArgument());
            if (index_ instanceof NumberStruct) {
                ArrayStruct arr_ = new ArrayStruct(1,StringExpUtil.getPrettyArrayType(_ctx.getStandards().getPrimTypes().getAliasPrimInteger()));
                arr_.set(0, index_);
                return new ArrCheckedExecOperationNodeInfos(_ctx,instance_,altMode(_mode,_right[0])+1,arr_, altRight(_mode,_right[0],_right[1]));
            }
            return new ArrCheckedExecOperationNodeInfos(_ctx,instance_,altMode(_mode,_right[0])+6,index_, altRight(_mode,_right[0],_right[1]));
        }
        return null;
    }
    private static CoreCheckedExecOperationNodeInfos settablePar(ExpressionLanguage _el, ExecAffectationOperation _o, ContextEl _ctx, AbstractPageEl _last) {
        ExecOperationNode set_ = _o.getSettable();
        if (set_ instanceof ExecSettableFieldInstOperation&&isField(set_)) {
            int anc_ = ((ExecSettableFieldOperation) set_).getSettableFieldContent().getAnc();
            Struct instance_ = instance(_el, set_, _last);
            return new ParCheckedExecOperationNodeInfos(_ctx,instance_,anc_);
        }
        if (set_ instanceof ExecCustArrWriteOperation) {
            int anc_ = ((ExecCustArrWriteOperation) set_).getInstWrite().getInst().getAnc();
            Struct instance_ = instance(_el, set_, _last);
            return new ParCheckedExecOperationNodeInfos(_ctx,instance_,anc_);
        }
        return null;
    }
    private static CallCheckedExecOperationNodeInfos settableCall(ExpressionLanguage _el, ExecAbstractAffectOperation _o, ContextEl _ctx, boolean _exit, Struct[] _right, AbstractPageEl _last) {
        ExecOperationNode set_ = _o.getSettable();
        if (set_ instanceof ExecStdRefVariableOperation || set_ instanceof ExecSettableCallFctOperation) {
            ArgumentsPair argumentPair_ = ExecHelper.getArgumentPair(_el.getArguments(), set_);
            AbstractWrapper w_ = argumentPair_.getWrapper();
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


    private static Struct instance(ExpressionLanguage _el, ExecOperationNode _o, AbstractPageEl _last) {
        return ExecOperationNode.instance(_o, _el.getArguments(), _last);
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

    private static CallCheckedExecOperationNodeInfos infosCall(ContextEl _context, StackCall _stackCall) {
        AbstractPageEl p_ = _stackCall.getLastPage();
        CallCheckedExecOperationNodeInfos infos_;
        if (_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() != null) {
            infos_ = endCaseCall(_context, _stackCall);
        } else if (p_ instanceof LambdaVariableGetValuePageEl) {
            infos_ = lambdaGetCall(_context, (LambdaVariableGetValuePageEl) p_);
        } else if (p_ instanceof LambdaVariableSetValuePageEl) {
            infos_ = lambdaSetCall(_context, (LambdaVariableSetValuePageEl) p_);
        } else if (p_ instanceof AbstractRefectMethodPageEl) {
            infos_ = refMethod(_context,(AbstractRefectMethodPageEl) p_);
        } else if (p_ instanceof AbstractRefectLambdaMethodPageEl) {
            infos_ = refMethod(_context,(AbstractRefectLambdaMethodPageEl) p_);
        } else if (!p_.isEmptyEl()){
            ExpressionLanguage el_ = p_.getLastEl();
            infos_ = expOperCall(el_, el_.getCurrentOper(), _context, p_,_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getCalculated() != null);
        } else {
            infos_ = null;
        }
        return infos_;
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
        } else if (p_ instanceof LambdaMethodWithoutInfo) {
            infos_ = reflectLambdaMethodWithoutInfo(_context, (LambdaMethodWithoutInfo) p_);
        } else if (p_ instanceof LambdaConstructorWithoutInfo) {
            infos_ = reflectLambdaMethodWithoutInfo(_context, (LambdaConstructorWithoutInfo) p_);
        } else if (p_ instanceof ReflectGetFieldPageEl) {
            infos_ = reflectGet(_context, (ReflectGetFieldPageEl) p_);
        } else if (p_ instanceof ReflectSetFieldPageEl) {
            infos_ = reflectSet(_context, (ReflectSetFieldPageEl) p_);
        } else if (p_ instanceof DirectAnnotationRefectMethodPageEl) {
            infos_ = refMethod(_context, (DirectAnnotationRefectMethodPageEl) p_);
        } else if (p_ instanceof LambdaAnnotationRefectMethodPageEl) {
            infos_ = refMethod(_context, (LambdaAnnotationRefectMethodPageEl) p_);
        } else if (!p_.isEmptyEl()){
            ExpressionLanguage el_ = p_.getLastEl();
            infos_ = expOper(el_, el_.getCurrentOper(), _context, p_);
        } else {
            infos_ = null;
        }
        return infos_;
    }

    private static OperNatCheckedExecOperationNodeInfos infosOperNat(ContextEl _context, StackCall _stackCall) {
        AbstractPageEl p_ = _stackCall.getLastPage();
        OperNatCheckedExecOperationNodeInfos infos_;
        if (_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() != null) {
            infos_ = null;
        } else if (p_ instanceof LambdaMethodWithoutInfo) {
            infos_ = reflectLambdaMethodWithoutInfoNat(_context, (LambdaMethodWithoutInfo) p_);
        } else if (!p_.isEmptyEl()){
            ExpressionLanguage el_ = p_.getLastEl();
            infos_ = expOperNat(el_, el_.getCurrentOper(), _context, p_);
        } else {
            infos_ = null;
        }
        return infos_;
    }

    private static CoreCheckedExecOperationNodeInfos infosPar(ContextEl _context, StackCall _stackCall) {
        if (_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() != null) {
            return null;
        }
        AbstractPageEl p_ = _stackCall.getLastPage();
        CoreCheckedExecOperationNodeInfos infos_;
        if (p_ instanceof AbstractRefectLambdaMethodPageEl) {
            infos_ = refMethodPar(_context,(AbstractRefectLambdaMethodPageEl) p_);
        } else if (p_ instanceof LambdaMethodWithoutInfo) {
            infos_ = reflectLambdaMethodWithoutInfoPar(_context, (LambdaMethodWithoutInfo) p_);
        } else if (p_ instanceof LambdaFieldWithoutInfo) {
            infos_ = reflectLambdaFieldWithoutInfoPar(_context, (LambdaFieldWithoutInfo) p_);
        } else if (p_ instanceof ReflectGetFieldPageEl) {
            infos_ = reflectGetPar(_context, (ReflectGetFieldPageEl) p_);
        } else if (p_ instanceof ReflectSetFieldPageEl) {
            infos_ = reflectSetPar(_context, (ReflectSetFieldPageEl) p_);
        } else if (!p_.isEmptyEl()){
            ExpressionLanguage el_ = p_.getLastEl();
            infos_ = expOperPar(el_, el_.getCurrentOper(), _context, p_);
        } else {
            infos_ = null;
        }
        return infos_;
    }
    private static StdMethodCheckedExecOperationNodeInfos infosStd(ContextEl _context, StackCall _stackCall) {
        AbstractPageEl p_ = _stackCall.getLastPage();
        StdMethodCheckedExecOperationNodeInfos infos_;
        if (_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() != null) {
            infos_ = endCaseStd(_context, _stackCall);
        } else if (p_ instanceof DirectStdRefectMethodPageEl) {
            infos_ = refMethod((DirectStdRefectMethodPageEl) p_);
        } else if (p_ instanceof LambdaDirectStdRefectMethodPageEl) {
            infos_ = refMethod((LambdaDirectStdRefectMethodPageEl) p_);
        } else if (p_ instanceof ReflectConstructorPageEl) {
            infos_ = refMethod((ReflectConstructorPageEl) p_);
        } else if (p_ instanceof ReflectLambdaConstructorPageEl) {
            infos_ = refMethod((ReflectLambdaConstructorPageEl) p_);
        } else if (!p_.isEmptyEl()){
            ExpressionLanguage el_ = p_.getLastEl();
            infos_ = expOperStd(el_, el_.getCurrentOper(), _context, p_,_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getCalculated() != null);
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

    private static StdMethodCheckedExecOperationNodeInfos endCaseStd(ContextEl _context, StackCall _stackCall) {
        AbstractPageEl p_ = _stackCall.getLastPage();
        StdMethodCheckedExecOperationNodeInfos infos_;
        if (!p_.isEmptyEl()) {
            ExpressionLanguage el_ = p_.getLastEl();
            infos_ = endStd(_context, el_, _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting());
        } else {
            infos_ = null;
        }
        return infos_;
    }

    private static CallCheckedExecOperationNodeInfos endCaseCall(ContextEl _context, StackCall _stackCall) {
        AbstractPageEl p_ = _stackCall.getLastPage();
        CallCheckedExecOperationNodeInfos infos_;
        if (!p_.isEmptyEl()) {
            ExpressionLanguage el_ = p_.getLastEl();
            infos_ = endCall(_context, el_, _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting());
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

    private static CallCheckedExecOperationNodeInfos refMethod(ContextEl _context, AbstractRefectMethodPageEl _p) {
        if (_p.isCheckingEntryExit()) {
            return checkLda(new CallCheckedExecOperationNodeInfos(_context,_p));
        }
        return null;
    }

    private static CallCheckedExecOperationNodeInfos refMethod(ContextEl _context, AbstractRefectLambdaMethodPageEl _p) {
        if (_p.isCheckingEntryExit()) {
            return checkLda(new CallCheckedExecOperationNodeInfos(_context,_p));
        }
        return null;
    }

    private static ParCheckedExecOperationNodeInfos refMethodPar(ContextEl _context, AbstractRefectLambdaMethodPageEl _p) {
        if (_p.isEntered()&&_p.isCheckedParent()) {
            return new ParCheckedExecOperationNodeInfos(_context,_p.getOriginalInstance(),_p.getAncestor());
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
            Struct instance_ = _p.getParent();
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
        if (!_p.isExiting()) {
            return wrapperElt(_context,w_,WatchPoint.BPC_READ,null);
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
        if (!_p.isExiting()) {
            Struct right_ = ArgumentListCall.toStr(ArgumentWrapper.helpArg(ExecHelper.getLastArgumentWrapper(argumentWrappers_)));
            return wrapperElt(_context,w_,WatchPoint.BPC_WRITE,right_);
        }
        return null;
    }
    private static CoreCheckedExecOperationNodeInfos wrapperElt(ContextEl _context, AbstractWrapper _w, int _mode, Struct _right) {
        if (_w instanceof FieldWrapper) {
            Struct instance_ = value(_w);
            return new CheckedExecOperationNodeInfos(_context,(FieldWrapper) _w, _mode, formatted(_context, (FieldWrapper) _w),instance_,_right);
        }
        if (_w instanceof ArrayWrapper) {
            Struct instance_ = ((ArrayWrapper)_w).getContainer();
            ArrayStruct arr_ = new ArrayStruct(1,StringExpUtil.getPrettyArrayType(_context.getStandards().getPrimTypes().getAliasPrimInteger()));
            arr_.set(0,((ArrayWrapper)_w).getIndex());
            return new ArrCheckedExecOperationNodeInfos(_context,instance_,_mode+1, arr_,_right);
        }
        if (_w instanceof ArrPartWrapper) {
            Struct instance_ = ((ArrPartWrapper)_w).getArray();
            return new ArrCheckedExecOperationNodeInfos(_context,instance_,_mode+6, ((ArrPartWrapper)_w).getRange(),_right);
        }
        return null;
    }

    private static CallCheckedExecOperationNodeInfos lambdaGetCall(ContextEl _context, LambdaVariableGetValuePageEl _p) {
        if (!_p.isCheckField()) {
            return null;
        }
        ArgumentWrapper firstArgumentWrapper_ = ExecHelper.getFirstArgumentWrapper(_p.getArr().getArgumentWrappers());
        AbstractWrapper w_ = firstArgumentWrapper_.getWrapper();
        if (w_ instanceof ArrayCustWrapper) {
            return checkLda(new CallCheckedExecOperationNodeInfos(_context,_p.isExiting(),(ArrayCustWrapper) w_));
        }
        return null;
    }

    private static CallCheckedExecOperationNodeInfos lambdaSetCall(ContextEl _context, LambdaVariableSetValuePageEl _p) {
        if (!_p.isCheckField()) {
            return null;
        }
        CustList<ArgumentWrapper> argumentWrappers_ = _p.getArr().getArgumentWrappers();
        ArgumentWrapper firstArgumentWrapper_ = ExecHelper.getFirstArgumentWrapper(argumentWrappers_);
        AbstractWrapper w_ = firstArgumentWrapper_.getWrapper();
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
        return new CheckedExecOperationNodeInfos(_context, _p.getMetaInfo(), WatchPoint.BPC_READ, formatted(_context, _p.getMetaInfo().getFormatted().getRootBlock(), instance_), instance_, null);
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
        return new CheckedExecOperationNodeInfos(_context, _p.getMetaInfo(), WatchPoint.BPC_WRITE, formatted(_context, _p.getMetaInfo().getFormatted().getRootBlock(), instance_), instance_, right_);
    }

    private static CoreCheckedExecOperationNodeInfos reflectGetPar(ContextEl _context, ReflectGetFieldPageEl _p) {
        if (!_p.getMetaInfo().isStaticField()&&_p.isCheckingParent()) {
            Struct instance_ = _p.getOriginalInstance();
            return new ParCheckedExecOperationNodeInfos(_context, instance_, _p.getAncestor());
        }
        return null;
    }

    private static CoreCheckedExecOperationNodeInfos reflectSetPar(ContextEl _context, ReflectSetFieldPageEl _p) {
        if (!_p.getMetaInfo().isStaticField()&&_p.isCheckingParent()) {
            Struct instance_ = _p.getOriginalInstance();
            return new ParCheckedExecOperationNodeInfos(_context, instance_, _p.getAncestor());
        }
        return null;
    }

    private static CoreCheckedExecOperationNodeInfos reflectLambdaMethodWithoutInfo(ContextEl _context, LambdaMethodWithoutInfo _p) {
        if (!_p.isCheckElement()) {
            return null;
        }
        return callDynArr(_context,_p.getName(), _p.getInstance(), _p.getArguments());
    }

    private static OperNatCheckedExecOperationNodeInfos reflectLambdaMethodWithoutInfoNat(ContextEl _context, LambdaMethodWithoutInfo _p) {
        if (!_p.isCheckElement()) {
            return null;
        }
        CommonOperSymbol c_ = _p.current();
        if (c_ != null) {
            if (_p.getArguments().getArgumentWrappers().size() < 2) {
                return new OperNatCheckedExecOperationNodeInfos(_context,c_.getSgn(),OperNatPoint.BPC_SIMPLE,ArgumentWrapper.helpArg(ExecHelper.getFirstArgumentWrapper(_p.getArguments().getArgumentWrappers())).getStruct(),null);
            }
            return new OperNatCheckedExecOperationNodeInfos(_context,c_.getSgn(),OperNatPoint.BPC_SIMPLE,ArgumentWrapper.helpArg(ExecHelper.getFirstArgumentWrapper(_p.getArguments().getArgumentWrappers())).getStruct(),ArgumentWrapper.helpArg(ExecHelper.getLastArgumentWrapper(_p.getArguments().getArgumentWrappers())).getStruct());
        }
        return null;
    }

    private static CoreCheckedExecOperationNodeInfos reflectLambdaMethodWithoutInfoPar(ContextEl _context, LambdaMethodWithoutInfo _p) {
        if (_p.isEntered()&&_p.isCheckedParent()) {
            return new ParCheckedExecOperationNodeInfos(_context, _p.getOriginalInstance(),_p.getAncestor());
        }
        return null;
    }

    private static CoreCheckedExecOperationNodeInfos reflectLambdaFieldWithoutInfoPar(ContextEl _context, LambdaFieldWithoutInfo _p) {
        if (_p.isEntered()&&!_p.isExited()) {
            return new ParCheckedExecOperationNodeInfos(_context, _p.getInstance(),_p.getAncestor());
        }
        return null;
    }

    private static CoreCheckedExecOperationNodeInfos reflectLambdaMethodWithoutInfo(ContextEl _context, LambdaConstructorWithoutInfo _p) {
        if (!_p.isCheckElement()) {
            return null;
        }
        return callDynInit(_context, _p.getArguments(), _p.getLambdaConstructorStruct());
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
                return settable(_el,(ExecCompoundAffectationOperation) ex_,_context, WatchPoint.BPC_COMPOUND_WRITE,new Struct[]{ArgumentListCall.toStr(_last.getReturnedArgument()),NullStruct.NULL_VALUE}, _last);
            }
        }
        return wrapp(_context, _last, ex_);
    }

    private static StdMethodCheckedExecOperationNodeInfos endStd(ContextEl _context, ExpressionLanguage _el, AbstractPageEl _last) {
        ExecOperationNode ex_ = _el.getCurrentOper();
        if (ex_ instanceof StdParamsOperable) {
            return new StdMethodCheckedExecOperationNodeInfos(_context.getStandards().getCoreNames().getAliasObject(),(StdParamsOperable)ex_,cl((StdParamsOperable) ex_,_context), _el.getArguments(), ((StdParamsOperable)ex_).instance(_el.getArguments(), _last), true);
        }
        return null;
    }
    private static CallCheckedExecOperationNodeInfos endCall(ContextEl _context, ExpressionLanguage _el, AbstractPageEl _last) {
        ExecOperationNode ex_ = _el.getCurrentOper();
        if (ex_ instanceof ExecCompoundAffectationOperation) {
            ImplicitMethods implicits_ = ((ExecCompoundAffectationOperation) ex_).getConverter();
            ArgumentsPair pairBefore_ = ExecHelper.getArgumentPair(_el.getArguments(),ex_);
            int indexImplicit_ = pairBefore_.getIndexImplicitConv();
            if (!ImplicitMethods.isValidIndex(implicits_,indexImplicit_)&&!pairBefore_.isEndCalculate()) {
                return settableCall(_el,(ExecCompoundAffectationOperation) ex_,_context, true, new Struct[]{ArgumentListCall.toStr(_last.getReturnedArgument()),NullStruct.NULL_VALUE}, _last);
            }
        }
        return core(_context, _el, _last, true, ex_);
    }

    private static CoreCheckedExecOperationNodeInfos wrapp(ContextEl _context, AbstractPageEl _last, ExecOperationNode _ex) {
        if (!(_ex instanceof ExecSettableCallFctOperation)) {
            return null;
        }
        if (sub(_ex)) {
            AbstractWrapper w_ = _last.getWrapper();
            return wrapp(w_, _context, WatchPoint.BPC_COMPOUND_READ);
        }
        if (!((ExecSettableCallFctOperation)_ex).resultCanBeSet()) {
            AbstractWrapper w_ = _last.getWrapper();
            return wrapp(w_, _context, WatchPoint.BPC_READ);
        }
        return null;
    }

    private static CallCheckedExecOperationNodeInfos core(ContextEl _context, ExpressionLanguage _el, AbstractPageEl _last, boolean _exit, ExecOperationNode _ex) {
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
        return wrapperElt(_context,_w,_m,null);
    }

    private static boolean stopAtWp(AbsLogDbg _l,ContextEl _context,StackCall _stackCall) {
        StopDbgEnum current_ = _stackCall.getBreakPointInfo().getBreakPointOutputInfo().getStoppedBreakPoint();
        while (true) {
            StopDbgEnum first_ = calculateInterm(_context, _stackCall, current_);
            if (first_ == StopDbgEnum.NONE) {
                return false;
            }
            StopDbgEnum second_ = logs(_l,first_,_context,_stackCall);
            if (second_ != StopDbgEnum.NONE) {
                _stackCall.getBreakPointInfo().getBreakPointOutputInfo().setStoppedBreakPoint(second_);
                return true;
            }
            current_ = first_;
        }
    }
    private static StopDbgEnum calculateInterm(ContextEl _context, StackCall _stackCall, StopDbgEnum _state) {
        CoreCheckedExecOperationNodeInfos infos_ = infos(_context, _stackCall);
        StdMethodCheckedExecOperationNodeInfos infosStd_ = infosStd(_context, _stackCall);
        CallCheckedExecOperationNodeInfos infosCall_ = infosCall(_context, _stackCall);
        if (_state == StopDbgEnum.STEP_RETURN_METHOD) {
            AbstractPageEl p_ = _stackCall.getLastPage();
            Struct str_ = stopExcValuRetThrowCatch(_context, _stackCall, p_);
            return current(_context, _stackCall, infos_, infosCall_, infosStd_, str_);
        }
        if (infosStd_ != null && (_state == StopDbgEnum.METHOD_STD_ENTRY || _state == StopDbgEnum.METHOD_STD_EXIT)) {
            AbstractPageEl p_ = _stackCall.getLastPage();
            return nextEntry(_context, infosStd_, _stackCall, p_);
        }
        if (_state == StopDbgEnum.METHOD_ABS_EXIT) {
            AbstractPageEl p_ = _stackCall.getLastPage();
            return checkedMicro(_context,_stackCall,p_,infos_);
        }
        if (_state == StopDbgEnum.PARENT) {
            AbstractPageEl p_ = _stackCall.getLastPage();
            return checkedNatAw(_context,_stackCall,p_,infos_);
        }
        if (_state == StopDbgEnum.OPER_NAT) {
            AbstractPageEl p_ = _stackCall.getLastPage();
            return checkedAw(_context,_stackCall,p_,infos_);
        }
        return StopDbgEnum.NONE;
    }

    private static StopDbgEnum stopAtCheckedBp(AbsLogDbg _l,ContextEl _context, StackCall _stackCall) {
        AbstractPageEl p_ = _stackCall.getLastPage();
        CoreCheckedExecOperationNodeInfos infos_ = infos(_context, _stackCall);
        StdMethodCheckedExecOperationNodeInfos infosStd_ = infosStd(_context, _stackCall);
        CallCheckedExecOperationNodeInfos infosCall_ = infosCall(_context, _stackCall);
        if (!checkBreakPoint(_context,_stackCall, infos_, infosCall_, infosStd_)) {
            return StopDbgEnum.NONE;
        }
        Struct exc_ = stopExcValuRetThrowCatch(_context, _stackCall, p_);
        StopDbgEnum step_ = stopStep(_context, _stackCall, p_, exc_);
        if (step_ != StopDbgEnum.NONE) {
            return step_;
        }
        return logs(_l,current(_context, _stackCall, infos_, infosCall_, infosStd_, exc_),_context,_stackCall);
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

    private static StopDbgEnum current(ContextEl _context, StackCall _stackCall, CoreCheckedExecOperationNodeInfos _infos, CallCheckedExecOperationNodeInfos _call, StdMethodCheckedExecOperationNodeInfos _std, Struct _exc) {
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
        if (_std != null && !_std.isExiting()) {
            return enterCase(_context,_std, _stackCall,p_);
        }
        if (_infos instanceof CheckedExecOperationNodeInfos || _infos instanceof ArrCheckedExecOperationNodeInfos) {
            return checkedMicro(_context, _stackCall, p_, _infos);
        }
        if (_call != null && !_call.isExit()) {
            return enterCase(_context, _stackCall, p_, _call, StopDbgEnum.METHOD_ABS_ENTRY);
        }
        return otherStopAndBp(_context, _stackCall, _infos, _std, _call, p_);
    }

    private static StopDbgEnum otherStopAndBp(ContextEl _context, StackCall _stackCall, CoreCheckedExecOperationNodeInfos _infos, StdMethodCheckedExecOperationNodeInfos _std, CallCheckedExecOperationNodeInfos _call, AbstractPageEl _p) {
        if (_std != null) {
            return enterCase(_context, _std, _stackCall, _p);
        }
        if (_call != null && exitMethod(_context, _stackCall, _p, _call)) {
            return StopDbgEnum.METHOD_ABS_EXIT;
        }
        return checkedMicro(_context, _stackCall, _p, _infos);
    }

    private static StopDbgEnum arr(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, ArrCheckedExecOperationNodeInfos _arr) {
        if (stopArr(_context, _stackCall, _p, _arr)){
            return StopDbgEnum.ARRAY;
        }
        return StopDbgEnum.NONE;
    }

    private static StopDbgEnum par(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, CoreCheckedExecOperationNodeInfos _arr) {
        if (stopPar(_context, _stackCall, _p, _arr.getInstance(), _arr)){
            return StopDbgEnum.PARENT;
        }
        return StopDbgEnum.NONE;
    }

    private static StopDbgEnum operNat(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
        OperNatCheckedExecOperationNodeInfos opNat_ = infosOperNat(_context, _stackCall);
        if (opNat_ == null) {
            return StopDbgEnum.NONE;
        }
        String call_ = opNat_.getKey();
        OperNatPointBlockPair pairs_ = _context.getPairOperNat(call_);
        OperNatPoint mp_ = pairs_.getValue();
        if (checkOperNat(_context, _stackCall, _p, mp_, opNat_)) {
            return StopDbgEnum.OPER_NAT;
        }
        return StopDbgEnum.NONE;
    }

    private static CallCheckedExecOperationNodeInfos checkRefl(ContextEl _context, StdMethodCheckedExecOperationNodeInfos _infos) {
        StdCaller c_ = _infos.getFct().getCaller();
        if (c_ instanceof FctMethodGetDeclaredAnonymousLambdaLocalVars1
                ||c_ instanceof FctMethodGetDeclaredAnonymousLambdaLocalVars2
                ||c_ instanceof FctMethodGetDeclaredAnonymousLambdaLocalVars3
                ||c_ instanceof FctMethodGetDeclaredAnonymousLambdaLocalVars4) {
            MethodMetaInfo m_ = NumParsers.getMethod(_infos.getInstance());
            Cache cache_ = m_.getCache();
            if (cache_ != null) {
                ArgumentListCall args_ = _infos.getArgs();
                AbstractWrapper lw_ = cache_.getLocalWrapper(NumParsers.getString(str(c_,args_, 0)).getInstance(), NumParsers.convertToNumber(str(c_,args_, 1)).longStruct());
                if (lw_ instanceof ArrayCustWrapper) {
                    return checkCallCheckedExecOperationNodeInfos(_context,_infos,c_,args_, (ArrayCustWrapper) lw_);
                }
            }
        }
        return null;
    }

    private static CoreCheckedExecOperationNodeInfos checkReflAw(ContextEl _context, StdMethodCheckedExecOperationNodeInfos _infos) {
        StdCaller c_ = _infos.getFct().getCaller();
        if (c_ instanceof FctMethodGetDeclaredAnonymousLambdaLocalVars1
                ||c_ instanceof FctMethodGetDeclaredAnonymousLambdaLocalVars2
                ||c_ instanceof FctMethodGetDeclaredAnonymousLambdaLocalVars3
                ||c_ instanceof FctMethodGetDeclaredAnonymousLambdaLocalVars4) {
            MethodMetaInfo m_ = NumParsers.getMethod(_infos.getInstance());
            Cache cache_ = m_.getCache();
            if (cache_ != null) {
                ArgumentListCall args_ = _infos.getArgs();
                AbstractWrapper lw_ = cache_.getLocalWrapper(NumParsers.getString(str(c_,args_, 0)).getInstance(), NumParsers.convertToNumber(str(c_,args_, 1)).longStruct());
                Struct r_ = right(c_, args_);
                return wrapperElt(_context,lw_,mode(c_),r_);
            }
        }
        return null;
    }
    private static CallCheckedExecOperationNodeInfos checkCallCheckedExecOperationNodeInfos(ContextEl _context, StdMethodCheckedExecOperationNodeInfos _infos, StdCaller _c, ArgumentListCall _args, ArrayCustWrapper _lw) {
        Struct r_ = right(_c, _args);
        if (r_ != null) {
            return checkLda(new CallCheckedExecOperationNodeInfos(_context, _infos.isExiting(), _lw, r_));
        }
        return checkLda(new CallCheckedExecOperationNodeInfos(_context, _infos.isExiting(), _lw));
    }
    private static int mode(StdCaller _c) {
        if (_c instanceof FctMethodGetDeclaredAnonymousLambdaLocalVars3 || _c instanceof FctMethodGetDeclaredAnonymousLambdaLocalVars4) {
            return WatchPoint.BPC_WRITE;
        }
        return WatchPoint.BPC_READ;
    }
    private static Struct right(StdCaller _c,ArgumentListCall _a) {
        if (_c instanceof FctMethodGetDeclaredAnonymousLambdaLocalVars3){
            return str(_a,1);
        }
        if (_c instanceof FctMethodGetDeclaredAnonymousLambdaLocalVars4){
            return str(_a,2);
        }
        return null;
    }
    private static Struct str(StdCaller _c,ArgumentListCall _a, int _i) {
        if (_c instanceof FctMethodGetDeclaredAnonymousLambdaLocalVars3 && _i == 1) {
            return new IntStruct(0);
        }
        return str(_a,_i);
    }
    private static Struct str(ArgumentListCall _a, int _i) {
        CustList<ArgumentWrapper> ls_ = _a.getArgumentWrappers();
        if (ls_.isValidIndex(_i)) {
            return ls_.get(_i).getValue().getStruct();
        }
        return new IntStruct(0);
    }

    private static ArrCheckedExecOperationNodeInfos callDynInit(ContextEl _context, ArgumentListCall _ls, LambdaConstructorStruct _lam) {
        CustList<ArgumentWrapper> args_ = _ls.getArgumentWrappers();
        int indexes_ = args_.size();
        ArrayStruct arr_ = new ArrayStruct(indexes_,StringExpUtil.getPrettyArrayType(_context.getStandards().getPrimTypes().getAliasPrimInteger()));
        for (int i = 0; i <indexes_; i++) {
            arr_.set(i,ArgumentListCall.toStr(args_.get(i).getValue()));
        }
        String c_ = StringExpUtil.getPrettyArrayType(_lam.getFormClassName(), indexes_);
        return new ArrCheckedExecOperationNodeInfos(_context, c_, arr_);
    }
    private static ArrCheckedExecOperationNodeInfos callDynArr(ContextEl _context, String _name,Struct _instance, ArgumentListCall _ls) {
        CustList<ArgumentWrapper> args_ = _ls.getArgumentWrappers();
        if (args_.isEmpty()) {
            return new ArrCheckedExecOperationNodeInfos(_context, _instance);
        }
        if (StringUtil.quickEq(_name,"[:]")) {
            Struct r_ = rangeInts(_context, _ls.getArguments(), _instance);
            return new ArrCheckedExecOperationNodeInfos(_context,_instance,ArrPoint.BPC_RANGE_GET,r_,null);
        }
        if (StringUtil.quickEq(_name,"[:]=")) {
            Struct r_ = rangeInts(_context, _ls.getArguments(), _instance);
            return new ArrCheckedExecOperationNodeInfos(_context,_instance,ArrPoint.BPC_RANGE_SET,r_,ArgumentListCall.toStr(_ls.getRight()));
        }
        Struct last_ = ArgumentListCall.toStr(args_.last().getValue());
        if (last_ instanceof RangeStruct) {
            Argument right_ = _ls.getRight();
            if (right_ != null) {
                return new ArrCheckedExecOperationNodeInfos(_context,_instance,ArrPoint.BPC_RANGE_SET,last_,ArgumentListCall.toStr(_ls.getRight()));
            }
            return new ArrCheckedExecOperationNodeInfos(_context,_instance,ArrPoint.BPC_RANGE_GET,last_,null);
        }
        int indexes_ = args_.size();
        ArrayStruct arr_ = new ArrayStruct(indexes_,StringExpUtil.getPrettyArrayType(_context.getStandards().getPrimTypes().getAliasPrimInteger()));
        for (int i = 0; i <indexes_; i++) {
            arr_.set(i,ArgumentListCall.toStr(args_.get(i).getValue()));
        }
        if (StringUtil.quickEq(_name,"[]=")) {
            return new ArrCheckedExecOperationNodeInfos(_context,_instance,ArrPoint.BPC_INT_GET_SET,arr_,ArgumentListCall.toStr(_ls.getRight()));
        }
        return new ArrCheckedExecOperationNodeInfos(_context,_instance,ArrPoint.BPC_INT_GET,arr_,null);
    }

    private static Struct rangeInts(ContextEl _conf, CustList<Argument> _arguments, Struct _arr) {
        if (_arguments.size() == 2) {
            Struct lower_ = _arguments.get(0).getStruct();
            Struct step_ = _arguments.get(1).getStruct();
            return Argument.getNull(FctRangeUnlimitedStep.rangeUnlimitStep(lower_, step_));
        }
        Struct lower_ = _arguments.last().getStruct();
        Struct upper_ = new IntStruct(ExecArrayFieldOperation.getLength(_arr, _conf));
        return Argument.getNull(FctRangeUnlimitedStep.range(lower_, upper_));
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

    private static StopDbgEnum checkedMicro(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, CoreCheckedExecOperationNodeInfos _infos) {
        StopDbgEnum res_ = checkedParent(_context, _stackCall, _p);
        if (res_ != StopDbgEnum.NONE) {
            return res_;
        }
        return checkedNatAw(_context, _stackCall, _p, _infos);
    }

    private static StopDbgEnum checkedNatAw(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, CoreCheckedExecOperationNodeInfos _infos) {
        StopDbgEnum nat_ = operNat(_context, _stackCall, _p);
        if (nat_ != StopDbgEnum.NONE) {
            return nat_;
        }
        return checkedAw(_context, _stackCall, _p, _infos);
    }
    private static StopDbgEnum checkedParent(ContextEl _context, StackCall _stackCall, AbstractPageEl _p) {
        CoreCheckedExecOperationNodeInfos par_ = infosPar(_context, _stackCall);
        if (par_ != null) {
            return par(_context,_stackCall,_p,par_);
        }
        return StopDbgEnum.NONE;
    }
    private static StopDbgEnum checkedAw(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, CoreCheckedExecOperationNodeInfos _infos) {
        if (_infos instanceof ArrCheckedExecOperationNodeInfos) {
            return arr(_context, _stackCall, _p, (ArrCheckedExecOperationNodeInfos) _infos);
        }
        if (_infos instanceof CheckedExecOperationNodeInfos) {
            CheckedExecOperationNodeInfos infWatch_ = (CheckedExecOperationNodeInfos) _infos;
            if (!okField(_context,_stackCall, infWatch_)) {
                return StopDbgEnum.NONE;
            }
            ClassField clField_ = infWatch_.getIdClass();
            WatchPoint bp_ = _context.getNotNullWatch(infWatch_.isTrueField(), infWatch_.getNbType(),clField_.getFieldName());
            BreakPointCondition condition_ = stopCurrentWpCondition(bp_, infWatch_);
            if (stopCurrent(_context, _stackCall, _p, condition_, infWatch_)) {
                return StopDbgEnum.FIELD;
            }
            return StopDbgEnum.NONE;
        }
        return defBp(_context, _stackCall);
    }

    private static StopDbgEnum enterCase(ContextEl _context, StdMethodCheckedExecOperationNodeInfos _ex, StackCall _stackCall, AbstractPageEl _p) {
        if (!okMethod(_context,_stackCall,_ex)) {
            return StopDbgEnum.NONE;
        }
        return entryCaseTrue(_context, _ex, _stackCall, _p);
    }

    private static StopDbgEnum entryCaseTrue(ContextEl _context, StdMethodCheckedExecOperationNodeInfos _ex, StackCall _stackCall, AbstractPageEl _p) {
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
        return nextEntry(_context, _ex, _stackCall, _p);
    }

    private static StopDbgEnum nextEntry(ContextEl _context, StdMethodCheckedExecOperationNodeInfos _ex, StackCall _stackCall, AbstractPageEl _p) {
        CallCheckedExecOperationNodeInfos ref_ = checkRefl(_context, _ex);
        if (!_ex.isExiting()) {
            if (ref_ != null) {
                return enterCase(_context, _stackCall, _p, ref_, StopDbgEnum.METHOD_ABS_REF_ENTRY);
            }
            return stdCallee(_context, _ex, _stackCall, _p);
        }
        if (ref_ != null &&exitMethod(_context, _stackCall, _p, ref_)) {
            return StopDbgEnum.METHOD_ABS_REF_EXIT;
        }
        return StopDbgEnum.NONE;
    }

    private static StopDbgEnum stdCallee(ContextEl _context, StdMethodCheckedExecOperationNodeInfos _ex, StackCall _stackCall, AbstractPageEl _p) {
        CoreCheckedExecOperationNodeInfos c_ = checkReflAw(_context, _ex);
        if (c_ != null) {
            return checkedMicro(_context, _stackCall, _p, c_);
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

    private static StopDbgEnum enterCase(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, CallCheckedExecOperationNodeInfos _c, StopDbgEnum _flag) {
        ExecNamedFunctionBlock call_ = _c.getPoly().getPair().getFct();
        ExecFormattedRootBlock glClass_ = _c.getFormattedRootBlock();
        Struct instance_ = _c.getInstance();
        Parameters original_ = ExecTemplates.quickArgsSet(call_,glClass_, _c.getCache(), _c.getArgs(),_context,_stackCall);
        CustList<MethodPointBlockPairRootBlock> pairs_ = _context.getPairs(call_, glClass_, _context, instance_,false, original_);
        for (MethodPointBlockPairRootBlock m: pairs_) {
            Parameters params_ = build(original_.getRefParameters(), original_.getCache(), _context, m.getId());
            MethodPoint mp_ = m.getId().getValue();
            if (stopCurrentMp(_context, _stackCall, _p, mp_, false,new CheckedMethodInfos(m.getValue(), instance_, params_))) {
                return _flag;
            }
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
        CustList<MethodPointBlockPairRootBlock> pairs_ = _context.getPairs(call_, glClass_, _context, instance_, true, original_);
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

    private static boolean stopArr(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, ArrCheckedExecOperationNodeInfos _arr) {
        String clName_ = _arr.getDeclaring().getFormatted();
        if (checkArr(_context, _stackCall, _p, _context.getNotNullArr(clName_,true),_arr)) {
            return true;
        }
        if (!clName_.isEmpty() && checkArr(_context, _stackCall, _p, _context.getNotNullArr(StringExpUtil.getIdFromAllTypes(clName_), false),_arr)) {
            return true;
        }
        return checkArr(_context, _stackCall, _p, _context.getNotNullArr("",false),_arr);
    }
    private static boolean checkArr(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, ArrPoint _bp, ArrCheckedExecOperationNodeInfos _arr) {
        BreakPointCondition bpc_ = stopArrValue(_bp, _arr);
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

    private static boolean stopPar(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, Struct _str, CoreCheckedExecOperationNodeInfos _arr) {
        String clName_ = _str.getClassName(_context);
        if (checkPar(_context, _stackCall, _p, _context.getNotNullPar(clName_,true),_arr)) {
            return true;
        }
        if (!clName_.isEmpty() && checkPar(_context, _stackCall, _p, _context.getNotNullPar(StringExpUtil.getIdFromAllTypes(clName_), false),_arr)) {
            return true;
        }
        return checkPar(_context, _stackCall, _p, _context.getNotNullPar("",false),_arr);
    }

    private static boolean checkExc(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, Struct _str, ExcPoint _bp) {
        BreakPointCondition bpc_ = stopExcValue(_p, _bp, _stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting());
        String clName_ = _str.getClassName(_context);
        return stopCurrent(_context, _stackCall, _p, bpc_, new CoreCheckedExecOperationNodeInfos(ExecFormattedRootBlock.build(clName_, _context.getClasses()), _str));
    }

    private static boolean checkPar(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, ParPoint _bp, CoreCheckedExecOperationNodeInfos _arr) {
        BreakPointCondition bpc_ = stopParValue(_bp);
        return stopCurrent(_context, _stackCall, _p, bpc_, _arr);
    }

    private static boolean checkOperNat(ContextEl _context, StackCall _stackCall, AbstractPageEl _p, OperNatPoint _bp, OperNatCheckedExecOperationNodeInfos _arr) {
        BreakPointCondition bpc_ = stopOperNatValue(_bp, _arr);
        return stopCurrent(_context, _stackCall, _p, bpc_, _arr);
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
                return stopBpc(_ex.isCaught(), _ex.getResultCaught());
            }
            return stopBpc(_ex.isThrown(), _ex.getResultThrown());
        }
        return stopBpc(_ex.isPropagated(), _ex.getResultPropagated());
    }

    private static BreakPointCondition stopArrValue(ArrPoint _ex, ArrCheckedExecOperationNodeInfos _a) {
        if (!_ex.isEnabled()) {
            return null;
        }
        if (_a.getModeField() == ArrPoint.BPC_LENGTH) {
            return stopBpc(_ex.isLength(), _ex.getResultLength());
        }
        if (_a.getModeField() == ArrPoint.BPC_INT_GET) {
            return stopBpc(_ex.isIntGet(), _ex.getResultIntGet());
        }
        if (_a.getModeField() == ArrPoint.BPC_INT_SET) {
            return stopBpc(_ex.isIntSet(), _ex.getResultIntSet());
        }
        if (_a.getModeField() == ArrPoint.BPC_INT_COMPOUND_GET) {
            return stopBpc(_ex.isIntCompoundGet(), _ex.getResultIntCompoundGet());
        }
        if (_a.getModeField() == ArrPoint.BPC_INT_COMPOUND_SET) {
            return stopBpc(_ex.isIntCompoundSet(), _ex.getResultIntCompoundSet());
        }
        if (_a.getModeField() == ArrPoint.BPC_RANGE_GET) {
            return stopBpc(_ex.isRangeGet(), _ex.getResultRangeGet());
        }
        if (_a.getModeField() == ArrPoint.BPC_RANGE_SET) {
            return stopBpc(_ex.isRangeSet(), _ex.getResultRangeSet());
        }
        if (_a.getModeField() == ArrPoint.BPC_RANGE_COMPOUND_GET) {
            return stopBpc(_ex.isRangeCompoundGet(), _ex.getResultRangeCompoundGet());
        }
        if (_a.getModeField() == ArrPoint.BPC_RANGE_COMPOUND_SET) {
            return stopBpc(_ex.isRangeCompoundSet(), _ex.getResultRangeCompoundSet());
        }
        if (_a.getModeField() == ArrPoint.BPC_INT_GET_SET) {
            return stopBpc(_ex.isIntGetSet(), _ex.getResultIntGetSet());
        }
        if (_a.getModeField() == ArrPoint.BPC_INIT) {
            return stopBpc(_ex.isInitArray(), _ex.getResultInitArray());
        }
        return stopBpc(_ex.isIntCompoundSetErr(), _ex.getResultIntCompoundSetErr());
    }

    private static BreakPointCondition stopParValue(ParPoint _ex) {
        if (!_ex.isEnabled()) {
            return null;
        }
        return stopBpc(_ex.isGet(), _ex.getResultGet());
    }

    private static BreakPointCondition stopOperNatValue(OperNatPoint _ex, OperNatCheckedExecOperationNodeInfos _a) {
        if (!_ex.isEnabled()) {
            return null;
        }
        if (_a.getModeField() == OperNatPoint.BPC_SIMPLE) {
            return stopBpc(_ex.isSimple(), _ex.getResultSimple());
        }
        return stopBpc(_ex.isCompound(), _ex.getResultCompound());
    }

    private static BreakPointCondition stopBpc(boolean _en, BreakPointCondition _res) {
        if (_en) {
            return _res;
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
        if (_check.getModeField() == WatchPoint.BPC_READ) {
            return stopBpc(_bp.isRead(), _bp.getResultRead());
        }
        if (_check.getModeField() == WatchPoint.BPC_WRITE) {
            return stopBpc(_bp.isWrite(), _bp.getResultWrite());
        }
        if (_check.getModeField() == WatchPoint.BPC_COMPOUND_READ) {
            return stopBpc(_bp.isCompoundRead(), _bp.getResultCompoundRead());
        }
        if (_check.getModeField() == WatchPoint.BPC_COMPOUND_WRITE) {
            return stopBpc(_bp.isCompoundWrite(), _bp.getResultCompoundWrite());
        }
        return stopBpc(_bp.isCompoundWriteErr(), _bp.getResultCompoundWriteErr());
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

    private static boolean checkBreakPoint(ContextEl _context, StackCall _stackCall, CoreCheckedExecOperationNodeInfos _infos, CallCheckedExecOperationNodeInfos _call, StdMethodCheckedExecOperationNodeInfos _std) {
        AbstractPageEl p_ = _stackCall.getLastPage();
        CoreCheckedExecOperationNodeInfos par_ = infosPar(_context, _stackCall);
        CoreCheckedExecOperationNodeInfos opNat_ = infosOperNat(_context, _stackCall);
        if (_stackCall.trueException() != null || (_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() != null && _stackCall.getBreakPointInfo().getBreakPointInputInfo().getStep() == StepDbgActionEnum.RETURN_METHOD) || _infos != null || _call != null || _std != null || par_ != null || opNat_ != null) {
            return true;
        }
        if (_stackCall.getBreakPointInfo().getBreakPointMiddleInfo().getExiting() != null) {
            return false;
        }
        return checkBreakPointCurrent(_context, _stackCall, p_);
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
        AbstractWrapper w_ = ExecVariableTemplates.getWrapper(_context.getClasses().getKeyWordValue(), 0, _check.getCache(), new StringMap<AbstractWrapper>());
        if (w_ == null) {
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
        return ExecInheritsAdv.checkObjectEx(ret_, w_.getValue().getClassName(_context), _context, _stackCall) == null;
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
