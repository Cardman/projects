package code.formathtml.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecNamedContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendNamedArgumentOperation extends RendAbstractUnaryOperation {

    private ExecNamedContent namedContent;
    public RendNamedArgumentOperation(ExecOperationContent _content, ExecNamedContent _namedContent) {
        super(_content);
        namedContent = _namedContent;
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes,this);
        Argument argres_ = getArgument(arguments_, _conf);
        setSimpleArgument(argres_, _conf,_nodes);
    }

    Argument getArgument(CustList<Argument> _arguments, Configuration _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ namedContent.getOffset(), _conf);
        return _arguments.first();
    }

    int getIndex() {
        return namedContent.getIndex();
    }
}
