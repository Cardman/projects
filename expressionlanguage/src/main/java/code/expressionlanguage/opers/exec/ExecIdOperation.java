package code.expressionlanguage.opers.exec;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.IdOperation;
import code.util.CustList;
import code.util.IdMap;

public final class ExecIdOperation extends ExecAbstractUnaryOperation {

    public ExecIdOperation(IdOperation _i) {
        super(_i);
    }

    @Override
    public Argument calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode o_ = chidren_.first();
        Argument a_ = getArgument(_nodes,o_);
        setSimpleArgument(a_, _conf, _nodes);
        return a_;
    }
    @Override
    public void quickCalculate(Analyzable _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        setSimpleArgumentAna(chidren_.first().getArgument(), _conf);
    }
}
