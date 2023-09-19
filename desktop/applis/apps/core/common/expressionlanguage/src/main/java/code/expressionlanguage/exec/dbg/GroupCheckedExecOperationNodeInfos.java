package code.expressionlanguage.exec.dbg;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.common.symbol.CommonOperSymbol;
import code.expressionlanguage.exec.*;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.calls.*;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.inherits.ExecVariableTemplates;
import code.expressionlanguage.exec.opers.*;
import code.expressionlanguage.exec.symbols.ExecOperSymbol;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ImplicitMethods;
import code.expressionlanguage.exec.variables.*;
import code.expressionlanguage.fcts.*;
import code.expressionlanguage.stds.StdCaller;
import code.expressionlanguage.structs.*;
import code.util.CustList;
import code.util.core.StringUtil;

public final class GroupCheckedExecOperationNodeInfos {
    private final CoreCheckedExecOperationNodeInfos infos;
    private final StdMethodCheckedExecOperationNodeInfos infosStd;
    private final CallCheckedExecOperationNodeInfos infosCall;
    private final CoreCheckedExecOperationNodeInfos par;
    private final OperNatCheckedExecOperationNodeInfos opNat;
    private final CallCheckedExecOperationNodeInfos checkRefl;
    private final CoreCheckedExecOperationNodeInfos checkReflAw;
    public GroupCheckedExecOperationNodeInfos(ContextEl _context, StackCall _stackCall) {
        infos = infos(_context, _stackCall);
        infosStd = infosStd(_context, _stackCall);
        infosCall = infosCall(_context, _stackCall);
        par = infosPar(_context, _stackCall);
        opNat = infosOperNat(_context, _stackCall);
        if (infosStd != null) {
            checkRefl = checkRefl(_context,infosStd);
            checkReflAw = checkReflAw(_context,infosStd);
        } else {
            checkRefl = null;
            checkReflAw = null;
        }
    }
    public boolean idDefined() {
        return infos != null || infosStd != null || infosCall != null || par != null || opNat != null;
    }

    public CoreCheckedExecOperationNodeInfos getInfos() {
        return infos;
    }

    public StdMethodCheckedExecOperationNodeInfos getInfosStd() {
        return infosStd;
    }

    public CoreCheckedExecOperationNodeInfos getPar() {
        return par;
    }

    public OperNatCheckedExecOperationNodeInfos getOpNat() {
        return opNat;
    }

    public CallCheckedExecOperationNodeInfos getInfosCall() {
        return infosCall;
    }

    public CallCheckedExecOperationNodeInfos getCheckRefl() {
        return checkRefl;
    }

    public CoreCheckedExecOperationNodeInfos getCheckReflAw() {
        return checkReflAw;
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
            return new FieldCheckedExecOperationNodeInfos((ExecAnnotationMethodOperation)_o, _context,WatchPoint.BPC_READ, formatted(_context, instance_), instance_, null);
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
            ArrayStruct arr_ = new ArrayStruct(indexes_, StringExpUtil.getPrettyArrayType(_context.getStandards().getPrimTypes().getAliasPrimInteger()));
            for (int i = 0; i <indexes_; i++) {
                arr_.set(i, ArgumentListCall.toStr(args_.get(i)));
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
                return new FieldCheckedExecOperationNodeInfos(_context,_o, WatchPoint.BPC_COMPOUND_READ, formatted(_context, _o, instance_), instance_, null);
            }
            return new FieldCheckedExecOperationNodeInfos(_context,_o, WatchPoint.BPC_READ, formatted(_context, _o, instance_), instance_, null);
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
            return new FieldCheckedExecOperationNodeInfos(_ctx,(ExecSettableFieldOperation) set_, altMode(_mode,_right[0]), formatted(_ctx, (ExecSettableFieldOperation) set_, instance_), instance_, altRight(_mode,_right[0],_right[1]));
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
            return new FieldCheckedExecOperationNodeInfos(_p.getMetaInfo().getRealId().getName(), _context,WatchPoint.BPC_READ, formatted(_context, instance_), instance_, null);
        }
        return null;
    }
    private static CoreCheckedExecOperationNodeInfos refMethod(ContextEl _context, LambdaAnnotationRefectMethodPageEl _p) {
        if (_p.isCheckingEntryExit()) {
            Struct instance_ = _p.getParent();
            return new FieldCheckedExecOperationNodeInfos(_p.getMetaInfo().getRealId().getName(), _context,WatchPoint.BPC_READ, formatted(_context, instance_), instance_, null);
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
            return new FieldCheckedExecOperationNodeInfos(_context,(FieldWrapper) _w, _mode, formatted(_context, (FieldWrapper) _w),instance_,_right);
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
        return new FieldCheckedExecOperationNodeInfos(_context, _p.getMetaInfo(), WatchPoint.BPC_READ, formatted(_context, _p.getMetaInfo().getFormatted().getRootBlock(), instance_), instance_, null);
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
        return new FieldCheckedExecOperationNodeInfos(_context, _p.getMetaInfo(), WatchPoint.BPC_WRITE, formatted(_context, _p.getMetaInfo().getFormatted().getRootBlock(), instance_), instance_, right_);
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
}
