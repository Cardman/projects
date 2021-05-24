package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.ExecHelper;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.exec.RendStackCall;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public final class RendDefaultOperation extends RendAbstractUnaryOperation {

    private final int offset;

    public RendDefaultOperation(ExecOperationContent _content, int _offset) {
        super(_content);
        offset = _offset;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, BeanLgNames _advStandards, ContextEl _context, RendStackCall _rendStack) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        setRelativeOffsetPossibleLastPage(getIndexInEl() + offset, _rendStack);
        Argument argres_ = new Argument(ExecClassArgumentMatching.convertWide(ExecHelper.getFirstArgument(arguments_).getStruct(), _context, getResultClass().getNames()));
        setSimpleArgument(argres_, _nodes, _context, _rendStack);
    }

}
