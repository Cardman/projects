package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.opers.DefaultOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.CustList;
import code.util.IdMap;

public final class ExecDefaultOperation extends ExecAbstractUnaryOperation {

    private int offset;
    public ExecDefaultOperation(DefaultOperation _f) {
        super(_f);
        offset = _f.getOffset();
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
        return new Argument(ClassArgumentMatching.convertWide(_conf.getLastPage(),getResultClass(), _arguments.first().getStruct(), _conf));
    }
}
