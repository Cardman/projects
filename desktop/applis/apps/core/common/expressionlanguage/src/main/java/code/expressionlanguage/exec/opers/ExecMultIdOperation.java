package code.expressionlanguage.exec.opers;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.opers.IdOperation;
import code.util.CustList;
import code.util.IdMap;

public final class ExecMultIdOperation extends ExecAbstractUnaryOperation {

    public ExecMultIdOperation(IdOperation _i) {
        super(_i);
    }

    @Override
    public void calculate(IdMap<ExecOperationNode,ArgumentsPair> _nodes, ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode o_ = chidren_.first();
        Argument a_ = getArgument(_nodes,o_);
        setSimpleArgument(a_, _conf, _nodes);
    }
}
