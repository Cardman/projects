package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.formathtml.util.BeanLgNames;
import code.util.CustList;
import code.util.IdMap;

public final class RendDefaultOperation extends RendAbstractUnaryOperation {

    private int offset;

    public RendDefaultOperation(ExecOperationContent _content, int _offset) {
        super(_content);
        offset = _offset;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf, BeanLgNames _advStandards, ContextEl _context) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument argres_ = getArgument(arguments_, _conf, _context);
        setSimpleArgument(argres_, _conf, _nodes, _context);
    }

    Argument getArgument(CustList<Argument> _arguments, Configuration _conf, ContextEl _context) {
        setRelativeOffsetPossibleLastPage(getIndexInEl() + offset, _conf);
        return new Argument(ExecClassArgumentMatching.convertWide(ExecTemplates.getFirstArgument(_arguments).getStruct(), _context, getResultClass().getNames()));
    }
}
