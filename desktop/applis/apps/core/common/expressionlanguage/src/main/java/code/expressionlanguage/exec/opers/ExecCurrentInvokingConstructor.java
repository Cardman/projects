package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecInvokingConstructorContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;

public final class ExecCurrentInvokingConstructor extends ExecAbstractInvokingConstructor {

    public ExecCurrentInvokingConstructor(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInvokingConstructorContent _invokingConstructorContent, ExecTypeFunction _pair) {
        super(_opCont, _intermediateDottedOperation, _invokingConstructorContent, _pair);
    }


    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        Argument res_ = getArgument(_nodes, _conf, _stack);
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

    Argument getArgument(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stackCall) {
        int off_ = getOffsetOper();
        setRelOffsetPossibleLastPage(off_, _stackCall);

        checkParametersCtors(_conf, _stackCall.getLastPage().getGlobalClass(), getPair(), getArgs(_nodes, _stackCall), InstancingStep.USING_THIS, _stackCall);
        return Argument.createVoid();
    }

    private ArgumentListCall getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, StackCall _stackCall) {
        String lastType_ = _stackCall.formatVarType(getLastType());
        return fectchArgs(_nodes,lastType_,getNaturalVararg());
    }

}
