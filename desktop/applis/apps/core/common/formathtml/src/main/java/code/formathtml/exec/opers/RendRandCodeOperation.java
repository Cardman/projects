package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.IdMap;

public final class RendRandCodeOperation extends RendMethodOperation implements RendCalculableOperation {
    private final int opOffset;
    public RendRandCodeOperation(ExecOperationContent _m, int _opOffset) {
        super(_m);
        opOffset = _opOffset;
    }
    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        RendDynOperationNode opOne_ = getFirstNode(this);
        Argument a_ = getArgument(_nodes,opOne_);
        setRelOffsetPossibleLastPage(opOffset, _rendStack);
        setSimpleArgument(processRandCode(a_,_context,_rendStack), _nodes, _context, _rendStack);
    }
}