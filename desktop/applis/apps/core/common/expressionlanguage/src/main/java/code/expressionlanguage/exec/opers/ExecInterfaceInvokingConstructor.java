package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.util.InstancingStep;
import code.expressionlanguage.exec.inherits.ExecInherits;
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
        int off_ = getOffsetOper();
        setRelOffsetPossibleLastPage(off_, _stack);

        String superClass_ = _stack.formatVarType(getClassFromName());
        String lastType_ = ExecInherits.quickFormat(getPair().getType(), superClass_, getLastType());
        checkParametersCtors(_conf, superClass_, getPair(), fectchArgs(_nodes, lastType_, getNaturalVararg()), InstancingStep.USING_SUPER, _stack);
        Argument res_ = Argument.createVoid();
        setSimpleArgument(res_, _conf, _nodes, _stack);
    }

}
