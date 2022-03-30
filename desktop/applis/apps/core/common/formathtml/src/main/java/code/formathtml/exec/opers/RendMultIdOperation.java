package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendMultIdOperation extends RendMethodOperation implements RendCalculableOperation {

    public RendMultIdOperation(ExecOperationContent _content) {
        super(_content);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        RendDynOperationNode o_ = getFirstNode(this);
        Argument a_ = getArgument(_nodes,o_);
        setSimpleArgument(a_, _nodes, _context, _rendStack);
    }
}
