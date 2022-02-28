package code.bean.nat.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.bean.nat.fwd.opers.NatExecVariableContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.opers.RendCalculableOperation;
import code.formathtml.exec.opers.RendDynOperationNode;
import code.formathtml.exec.opers.RendLeafOperation;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class NatFinalVariableOperation extends RendLeafOperation implements RendCalculableOperation {

    private final NatExecVariableContent variableContent;

    public NatFinalVariableOperation(ExecOperationContent _content, NatExecVariableContent _variableContent) {
        super(_content);
        variableContent = _variableContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        setRelOffsetPossibleLastPage(variableContent.getOff(), _rendStack);
        Argument arg_ = NatStdRefVariableOperation.getIndexLoop(variableContent, _rendStack.getPageEl().getVars());
        calcArg(_nodes, arg_);
    }

}
