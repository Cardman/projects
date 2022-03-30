package code.formathtml.exec.opers;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendDotOperation extends RendAbstractDotOperation {

    public RendDotOperation(ExecOperationContent _content) {
        super(_content);
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        calculateDot(_nodes, _context, _rendStack);
    }
}
