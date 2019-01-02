package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.FirstOptOperation;
import code.util.CustList;
import code.util.IdMap;

public final class ExecFirstOptOperation extends ExecAbstractUnaryOperation {

    private int offset;
    public ExecFirstOptOperation(FirstOptOperation _f) {
        super(_f);
        offset = _f.getOffset();
    }

    @Override
    public void quickCalculate(Analyzable _conf) {
        if (!_conf.isGearConst()) {
            return;
        }
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (ExecOperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        setSimpleArgumentAna(arguments_.first(), _conf);
    }

    @Override
    public Argument calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        CustList<Argument> arguments_ = getArguments(_nodes, this);
        Argument argres_ = getArgument(arguments_, _conf);
        setSimpleArgument(argres_, _conf, _nodes);
        return argres_;
    }

    Argument getArgument(CustList<Argument> _arguments, ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        return _arguments.first();
    }
}
