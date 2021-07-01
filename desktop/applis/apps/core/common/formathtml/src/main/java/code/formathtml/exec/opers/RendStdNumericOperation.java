package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public abstract class RendStdNumericOperation extends RendNumericOperation {
    private final String op;

    protected RendStdNumericOperation(ExecOperationContent _content, int _opOffset, String _op) {
        super(_content, _opOffset);
        op = _op;

    }

    abstract Argument calculateOper(Argument _a, String _op, Argument _b, ContextEl _cont, RendStackCall _stack);

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        Argument a_ = getArgument(_nodes,getFirstNode(this));
        Argument c_ = getArgument(_nodes,getLastNode(this));
        setRelOffsetPossibleLastPage(getOpOffset(), _rendStack);
        Argument r_ = calculateOper(a_, op, c_, _context, _rendStack);
        setSimpleArgument(r_, _nodes, _context, _rendStack);
    }
}
