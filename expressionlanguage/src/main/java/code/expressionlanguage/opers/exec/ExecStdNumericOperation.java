package code.expressionlanguage.opers.exec;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.NumericOperation;
import code.util.CustList;
import code.util.IdMap;

public abstract class ExecStdNumericOperation extends ExecNumericOperation {

    private String oper;

    public ExecStdNumericOperation(NumericOperation _n) {
        super(_n);
        oper = _n.getOp();
    }
    @Override
    public final void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                                ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode o_ = chidren_.first();
        Argument a_ = getArgument(_nodes,o_);
        o_ = chidren_.last();
        Argument c_ = getArgument(_nodes,o_);
        setRelativeOffsetPossibleLastPage(getIndexInEl()+getOpOffset(), _conf);
        Argument r_;
        r_ = calculateOper(a_, oper, c_, _conf);
        a_ = r_;
        setSimpleArgument(a_, _conf, _nodes);
    }
    abstract Argument calculateOper(Argument _a, String _op, Argument _b, ContextEl _cont);
    abstract Argument calculateOperAna(Argument _a, String _op, Argument _b, ContextEl _an);

    
    @Override
    public void quickCalculate(ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = chidren_.first().getArgument();
        Argument c_ = chidren_.last().getArgument();
        Argument r_;
        r_ = calculateOperAna(a_, oper, c_, _conf);
        if (r_.isNull()) {
            return;
        }
        a_ = r_;
        setSimpleArgumentAna(a_, _conf);
    }


}
