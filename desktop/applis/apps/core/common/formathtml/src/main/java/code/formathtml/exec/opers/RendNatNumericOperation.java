package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.symbols.ExecOperSymbol;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.structs.Struct;
import code.formathtml.exec.RendStackCall;
import code.util.IdMap;

public final class RendNatNumericOperation extends RendNumericOperation {

    private final ExecOperSymbol pair;
    public RendNatNumericOperation(ExecOperSymbol _pair, ExecOperationContent _content, int _opOffset) {
        super(_content, _opOffset);
        pair = _pair;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        Argument a_ = getArgument(_nodes,getFirstNode(this));
        Argument c_ = getArgument(_nodes,getLastNode(this));
        setRelOffsetPossibleLastPage(getOpOffset(), _rendStack);
        Struct r_ = pair.calculateOperator(a_.getStruct(), c_.getStruct(), getResultClass().getUnwrapObjectNb(), _context, _rendStack.getStackCall());
        setSimpleArgument(new Argument(r_), _nodes, _context, _rendStack);
    }
}
