package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecNamedContent;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.CustList;
import code.util.IdMap;

public final class ExecNamedArgumentOperation extends ExecAbstractUnaryOperation {

    private ExecNamedContent namedContent;
    public ExecNamedArgumentOperation(ExecOperationContent _opCont, ExecNamedContent _namedContent) {
        super(_opCont);
        namedContent = _namedContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument argres_ = getArgument(arguments_, _conf);
        setSimpleArgument(argres_, _conf, _nodes);
    }

    Argument getArgument(CustList<Argument> _arguments, ContextEl _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ namedContent.getOffset(), _conf);
        return _arguments.first();
    }

    public int getIndex() {
        return namedContent.getIndex();
    }
}
