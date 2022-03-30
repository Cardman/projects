package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.BooleanStruct;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;
import code.util.core.StringUtil;

public final class RendEqOperation extends RendMethodOperation implements RendCalculableOperation {

    private final String oper;
    public RendEqOperation(ExecOperationContent _content, String _oper) {
        super(_content);
        oper = _oper;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Argument first_ = getArgument(_nodes,getFirstNode(this));
        Argument second_ = getArgument(_nodes,getLastNode(this));
        String op_ = oper.trim();
        boolean b_ = first_.getStruct().sameReference(second_.getStruct());
        if (StringUtil.quickEq(op_, DIFF)) {
            b_ = !b_;
        }
        Argument arg_ = new Argument(BooleanStruct.of(b_));
        setSimpleArgument(arg_, _nodes, _context, _rendStack);
    }
}
