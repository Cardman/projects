package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.util.CustList;
import code.util.IdMap;

public final class ExecNbCmpOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    private ExecOperatorContent operatorContent;
    public ExecNbCmpOperation(ExecOperationContent _opCont, ExecOperatorContent _operatorContent) {
        super(_opCont);
        operatorContent = _operatorContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        CustList<ExecOperationNode> chidren_ = getChildrenNodes();
        ExecOperationNode opOne_ = chidren_.first();
        ExecOperationNode opTwo_ = chidren_.last();
        setRelativeOffsetPossibleLastPage(getIndexInEl()+ operatorContent.getOpOffset(), _conf);
        Argument first_ = getArgument(_nodes,opOne_);
        Argument second_ = getArgument(_nodes,opTwo_);
        Argument arg_ = new Argument(NumParsers.compareNb(operatorContent.getOper(), first_.getStruct(), second_.getStruct()));
        setSimpleArgument(arg_, _conf, _nodes);
    }

}
