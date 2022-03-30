package code.formathtml.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendUnaryBinOperation extends RendMethodOperation implements RendCalculableOperation {

    public RendUnaryBinOperation(ExecOperationContent _content) {
        super(_content);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Argument arg_ = getArgument(_nodes,getFirstNode(this));
        Argument a_ = new Argument(NumParsers.negBinNumber(NumParsers.convertToNumber(arg_.getStruct()), getResultClass().getUnwrapObjectNb()));
        setSimpleArgument(a_, _nodes, _context, _rendStack);
    }

}
