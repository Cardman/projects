package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendForwardOperation extends RendLeafOperation implements RendCalculableOperation,RendPossibleIntermediateDotted {
    private final boolean intermediate;

    public RendForwardOperation(ExecOperationContent _content, boolean _intermediate) {
        super(_content);
        intermediate = _intermediate;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        Argument previous_ = getPreviousArg(this,_nodes, _rendStack);
        setSimpleArgument(previous_, _nodes, _context, _rendStack);
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }
}
