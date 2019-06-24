package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.CmpOperation;
import code.util.CustList;
import code.util.IdMap;

public final class ExecStrCmpOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    private String oper;
    private int opOffset;
    public ExecStrCmpOperation(CmpOperation _r) {
        super(_r);
        oper = _r.getOp();
        opOffset = _r.getOpOffset();
    }
    @Override
    public void quickCalculate(Analyzable _conf) {
        CmpOperation.tryGetResult(_conf, oper, null, true, this);
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode opOne_ = chidren_.first();
        ExecOperationNode opTwo_ = chidren_.last();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+opOffset, _conf);
        Argument first_ = getArgument(_nodes,opOne_);
        Argument second_ = getArgument(_nodes,opTwo_);
        Argument arg_ = CmpOperation.calculateCommonStr(first_, second_, oper);
        setSimpleArgument(arg_, _conf, _nodes);
    }

}
