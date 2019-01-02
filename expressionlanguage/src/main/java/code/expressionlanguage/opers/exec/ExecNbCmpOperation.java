package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.CmpOperation;
import code.util.CustList;
import code.util.IdMap;

public final class ExecNbCmpOperation extends ExecReflectableOpering {

    private String oper;
    public ExecNbCmpOperation(CmpOperation _r) {
        super(_r);
        oper = _r.getOp();
    }
    @Override
    public final void quickCalculate(Analyzable _conf) {
        CmpOperation.tryGetResult(_conf, oper, null, false, this);
    }
    @Override
    public Argument calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
            ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode opOne_ = chidren_.first();
        ExecOperationNode opTwo_ = chidren_.last();
        Argument first_ = getArgument(_nodes,opOne_);
        Argument second_ = getArgument(_nodes,opTwo_);
        Argument arg_ = CmpOperation.calculateCommonNb(first_, second_, oper);
        setSimpleArgument(arg_, _conf, _nodes);
        return arg_;
    }

}
