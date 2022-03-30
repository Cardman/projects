package code.bean.nat.exec.opers;

import code.bean.nat.fwd.opers.NatExecVariableContent;
import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
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
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, RendStackCall _rendStack) {
        setRelOffsetPossibleLastPage(variableContent.getOff(), _rendStack);
        Argument arg_ = NatStdRefVariableOperation.getIndexLoop(variableContent, _rendStack.getPageEl().getVars());
        calcArg(_nodes, arg_);
    }

}
