package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.opers.NamedArgumentOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendNamedArgumentOperation extends RendAbstractUnaryOperation {

    private int offset;
    private int index;
    public RendNamedArgumentOperation(NamedArgumentOperation _f) {
        super(_f);
        offset = _f.getOffset();
        index = _f.getIndex();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        Argument argres_ = getArgument(arguments_, _conf);
        setSimpleArgument(argres_, _conf,_nodes);
    }

    Argument getArgument(CustList<Argument> _arguments, Configuration _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        return _arguments.first();
    }

    int getIndex() {
        return index;
    }
}
