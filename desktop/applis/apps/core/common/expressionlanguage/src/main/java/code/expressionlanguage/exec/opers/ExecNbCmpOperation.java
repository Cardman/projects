package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.util.IdMap;

public final class ExecNbCmpOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    private final ExecOperatorContent operatorContent;
    public ExecNbCmpOperation(ExecOperationContent _opCont, ExecOperatorContent _operatorContent) {
        super(_opCont);
        operatorContent = _operatorContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        setRelOffsetPossibleLastPage(operatorContent.getOpOffset(), _stack);
        Argument first_ = getFirstArgument(_nodes,this);
        Argument second_ = getLastArgument(_nodes,this);
        Argument arg_ = new Argument(NumParsers.compareNb(operatorContent.getOper(), first_.getStruct(), second_.getStruct()));
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }

}
