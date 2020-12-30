package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.util.ArgumentListCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.blocks.ExecTypeFunction;
import code.expressionlanguage.fwd.opers.ExecInvokingConstructorContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;

public final class ExecInterfaceInvokingConstructor extends ExecAbstractInvokingConstructor {

    public ExecInterfaceInvokingConstructor(ExecOperationContent _opCont, boolean _intermediateDottedOperation, ExecInvokingConstructorContent _invokingConstructorContent, ExecTypeFunction _pair) {
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

        String superClass_ = _stackCall.formatVarType(getClassFromName());
        checkParametersCtors(_conf, superClass_, getPair(),  getArgs(_nodes, superClass_), InstancingStep.USING_SUPER, _stackCall);
        return Argument.createVoid();
    }

    private ArgumentListCall getArgs(IdMap<ExecOperationNode, ArgumentsPair> _nodes, String _superClass) {
        String lastType_ = ExecTemplates.quickFormat(getPair().getType(),_superClass, getLastType());
        return fectchArgs(_nodes,lastType_,getNaturalVararg());
    }

}
