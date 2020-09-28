package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendDefaultOperation extends RendAbstractUnaryOperation {

    private int offset;

    public RendDefaultOperation(ExecOperationContent _content, int _offset) {
        super(_content);
        offset = _offset;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument argres_ = getArgument(arguments_, _conf);
        setSimpleArgument(argres_, _conf, _nodes);
    }

    Argument getArgument(CustList<Argument> _arguments, Configuration _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl() + offset, _conf);
        return new Argument(ExecClassArgumentMatching.convertWide(_conf.getPageEl(), _arguments.first().getStruct(), _conf.getContext(), getResultClass().getNames()));
    }
}
