package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.exec.opers.ExecUnaryOperation;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendUnaryOperation extends RendMethodOperation implements RendCalculableOperation {
    private final String oper;

    public RendUnaryOperation(ExecOperationContent _content, String _oper) {
        super(_content);
        oper = _oper;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Argument arg_ = getArgument(_nodes,getFirstNode(this));
        ExecClassArgumentMatching to_ = getResultClass();
        Argument a_ = ExecUnaryOperation.getArgument(arg_, to_, oper);
        setSimpleArgument(a_, _nodes, _context, _rendStack);
    }

}
