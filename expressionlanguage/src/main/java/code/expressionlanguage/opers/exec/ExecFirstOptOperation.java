package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.FirstOptOperable;
import code.expressionlanguage.opers.FirstOptOperation;
import code.util.CustList;
import code.util.IdMap;

public final class ExecFirstOptOperation extends ExecAbstractUnaryOperation implements FirstOptOperable {

    private int offset;
    public ExecFirstOptOperation(FirstOptOperation _f) {
        super(_f);
        offset = _f.getOffset();
    }

    @Override
    public void quickCalculate(ContextEl _conf) {
        FirstOptOperation.setArg(_conf, this);
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
}
