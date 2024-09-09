package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.CallPrepareState;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.DefaultParamChecker;
import code.expressionlanguage.exec.inherits.ExecInherits;
import code.expressionlanguage.exec.inherits.ExecInheritsAdv;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.exec.util.ExecOperationInfo;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.functionid.MethodAccessKind;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecArrContent;
import code.expressionlanguage.fwd.opers.ExecInvokingConstructorContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.IdMap;

public final class ExecAbstractInvokingConstructor extends ExecSettableCallFctOperation {

    private final ExecFormattedRootBlock formattedType;
    private final ExecInvokingConstructorContent invokingConstructorContent;

    private final ExecTypeFunction pair;
    private final String className;

    public ExecAbstractInvokingConstructor(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInvokingConstructorContent _invokingConstructorContent, String _className, ExecTypeFunction _pair) {
        super(_opCont, _intermediateDottedOperation,new ExecArrContent(false));
        invokingConstructorContent = _invokingConstructorContent;
        className = _className;
        pair = _pair;
        formattedType = _invokingConstructorContent.getFormattedType();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        int off_ = getInvokingConstructorContent().getOffsetOper();
        setRelOffsetPossibleLastPage(off_, _stack);
        ExecOperationNode main_ = ExecHelper.getMainNode(this);
        ArgumentsPair pair_ = ExecHelper.getArgumentPair(_nodes, main_);
        Struct mainArgument_ = ArgumentListCall.getNull(pair_.getArgument());
        if (getIndexChild() == 1) {
            //init and test
            Struct lda_ = ExecInheritsAdv.checkObject(_conf.getStandards().getContent().getReflect().getAliasFct(), mainArgument_, _conf, _stack);
            if (_conf.callsOrException(_stack)) {
                setResult(NullStruct.NULL_VALUE, _conf, _nodes, _stack);
                return;
            }
            String form_ = _stack.formatVarType(className);
            Struct struct_ = ExecCastOperation.wrapFct(form_, true, _conf, lda_);
            Struct ref_ = ExecInheritsAdv.checkObject(form_, struct_, _conf, _stack);
            if (_conf.callsOrException(_stack)) {
                setResult(NullStruct.NULL_VALUE, _conf, _nodes, _stack);
                return;
            }
            pair_.setArgument(ref_);
            ExecFormattedRootBlock superClass_ = StackCall.formatVarType(_stack,getFormattedType());
            prep(_conf, _stack, ref_, superClass_, buildInfos(_nodes), getInvokingConstructorContent(), getPair());
            setResult(NullStruct.NULL_VALUE, _conf, _nodes, _stack);
            return;
        }
        Struct arg_;
        if (getParent() == null) {
            arg_ = _stack.getLastPage().getGlobalStruct();
        } else {
            arg_ = mainArgument_;
        }
        ExecFormattedRootBlock superClass_ = StackCall.formatVarType(_stack,getFormattedType());
        prep(_conf, _stack, arg_, superClass_, buildInfos(_nodes), getInvokingConstructorContent(), getPair());
        setResult(NullStruct.NULL_VALUE, _conf, _nodes, _stack);
    }
    public static void prep(ContextEl _conf, StackCall _stack, Struct _arg, ExecFormattedRootBlock _superClass, CustList<ExecOperationInfo> _infos, ExecInvokingConstructorContent _invokingConstructorContent, ExecTypeFunction _pair) {
        String lastType_ = ExecInherits.quickFormat(_superClass, _invokingConstructorContent.getLastType());
        new DefaultParamChecker(_pair, fectchArgs(lastType_, _invokingConstructorContent.getNaturalVararg(), _conf, _stack, _infos), MethodAccessKind.INSTANCE, CallPrepareState.CTOR).checkParams(_superClass, _arg, null, _conf, _stack);
    }

    public ExecInvokingConstructorContent getInvokingConstructorContent() {
        return invokingConstructorContent;
    }

    public ExecFormattedRootBlock getFormattedType() {
        return formattedType;
    }

    public ExecTypeFunction getPair() {
        return pair;
    }

}
