package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.NumericOperation;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public abstract class RendStdNumericOperation extends RendNumericOperation {
    private String op;

    public RendStdNumericOperation(NumericOperation _n) {
        super(_n);
        op = _n.getOp();

    }

    abstract Argument calculateOper(Argument _a, String _op, Argument _b, ExecutableCode _cont);

    @Override
    public void calculate(ExecutableCode _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = chidren_.first().getArgument();
        Argument c_ = chidren_.last().getArgument();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+getOpOffset(), _conf);
        Argument r_;
        r_ = calculateOper(a_, op, c_, _conf);
        a_ = r_;
        setSimpleArgument(a_, _conf);
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        Argument a_ = getArgument(_nodes,chidren_.first());
        Argument c_ = getArgument(_nodes,chidren_.last());
        setRelativeOffsetPossibleLastPage(getIndexInEl()+getOpOffset(), _conf);
        Argument r_;
        r_ = calculateOper(a_, op, c_, _conf);
        a_ = r_;
        setSimpleArgument(a_, _conf,_nodes);
    }
}
