package code.formathtml.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.FirstOptOperation;
import code.util.CustList;

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
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (ExecDynOperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        setSimpleArgumentAna(arguments_.first(), _conf);
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<ExecDynOperationNode> chidren_ = getChildrenNodes();
        CustList<Argument> arguments_ = new CustList<Argument>();
        for (ExecDynOperationNode o: chidren_) {
            arguments_.add(o.getArgument());
        }
        Argument argres_ = getArgument(arguments_, _conf);
        setSimpleArgument(argres_, _conf);
    }

    Argument getArgument(CustList<Argument> _arguments, ExecutableCode _conf) {
        setRelativeOffsetPossibleLastPage(getIndexInEl()+offset, _conf);
        return _arguments.first();
    }
}
