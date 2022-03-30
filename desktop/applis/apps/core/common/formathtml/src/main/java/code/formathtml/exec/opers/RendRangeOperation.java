package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.opers.ExecRangeOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.util.CustList;
import code.util.IdMap;

public final class RendRangeOperation extends RendMethodOperation implements RendCalculableOperation {
    private final int opOffset;
    private final boolean implicitMiddle;
    public RendRangeOperation(ExecOperationContent _content, int _opOffset, boolean _implicitMiddle) {
        super(_content);
        opOffset = _opOffset;
        implicitMiddle = _implicitMiddle;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, ContextEl _context, RendStackCall _rendStack) {
        setRelOffsetPossibleLastPage(opOffset, _rendStack);
        CustList<Argument> args_ = getArguments(_nodes, this);
        Argument r_ = ExecRangeOperation.range(_context,_rendStack.getStackCall(),args_,implicitMiddle);
        setSimpleArgument(r_, _nodes, _context, _rendStack);
    }
}
