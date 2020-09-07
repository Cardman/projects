package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.opers.NamedArgumentOperation;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.util.CustList;
import code.util.IdMap;

public final class ExecNamedArgumentOperation extends ExecAbstractUnaryOperation {

    private int offset;
    private int index;
    public ExecNamedArgumentOperation(NamedArgumentOperation _f) {
        super(_f);
        offset = _f.getOffset();
        index = _f.getIndex();
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument argres_ = getArgument(arguments_, _conf);
        setSimpleArgument(argres_, _conf, _nodes);
    }

    Argument getArgument(CustList<Argument> _arguments, ContextEl _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        return _arguments.first();
    }

    public int getIndex() {
        return index;
    }
}
