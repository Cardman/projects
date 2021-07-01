package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public final class RendFirstOptOperation extends RendMethodOperation implements RendCalculableOperation {

    private final int offset;
    public RendFirstOptOperation(ExecOperationContent _content, int _offset) {
        super(_content);
        offset = _offset;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        setRelOffsetPossibleLastPage(offset, _rendStack);
        Argument argres_ = ExecHelper.getFirstArgument(arguments_);
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

}
