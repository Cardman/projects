package code.bean.nat.exec.opers;

import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.fwd.opers.NatExecVariableContent;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.opers.RendLeafOperation;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class NatFinalVariableOperation extends RendLeafOperation implements NatRendCalculableOperation {

    private final NatExecVariableContent variableContent;

    public NatFinalVariableOperation(ExecOperationContent _content, NatExecVariableContent _variableContent) {
        super(_content);
        variableContent = _variableContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, NatRendStackCall _rendStack) {
        Argument arg_ = NatStdRefVariableOperation.getIndexLoop(variableContent, _rendStack.getLastPage().getVars());
        calcArg(_nodes, arg_);
    }

}
