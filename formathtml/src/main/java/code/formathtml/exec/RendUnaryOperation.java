package code.formathtml.exec;
import code.expressionlanguage.Argument;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.UnaryOperation;
import code.expressionlanguage.opers.exec.ExecUnaryOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.formathtml.Configuration;
import code.util.CustList;
import code.util.IdMap;

public final class RendUnaryOperation extends RendAbstractUnaryOperation {
    private String oper;

    public RendUnaryOperation(UnaryOperation _u) {
        super(_u);
        oper = _u.getOper();
    }

    @Override
    public void calculate(IdMap<RendDynOperationNode, ArgumentsPair> _nodes, Configuration _conf) {
        CustList<RendDynOperationNode> chidren_ = getChildrenNodes();
        Argument arg_ = getArgument(_nodes,chidren_.first());
        Argument a_ = getArgument(_conf, arg_);
        setSimpleArgument(a_, _conf,_nodes);
    }

    Argument getArgument(Configuration _conf,
                         Argument _in) {
        setRelativeOffsetPossibleLastPage(getIndexInEl(), _conf);
        ClassArgumentMatching to_ = getResultClass();
        return ExecUnaryOperation.getArgument(_conf.getContext(),_in,to_,oper);
    }
}
