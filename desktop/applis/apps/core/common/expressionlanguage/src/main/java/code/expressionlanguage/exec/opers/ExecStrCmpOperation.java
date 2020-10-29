package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecOperatorContent;
import code.util.IdMap;

public final class ExecStrCmpOperation extends ExecMethodOperation implements AtomicExecCalculableOperation {

    private ExecOperatorContent operatorContent;
    public ExecStrCmpOperation(ExecOperationContent _opCont, ExecOperatorContent _operatorContent) {
        super(_opCont);
        operatorContent = _operatorContent;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf) {
        setRelOffsetPossibleLastPage(operatorContent.getOpOffset(), _conf);
        Argument first_ = getFirstArgument(_nodes,this);
        Argument second_ = getLastArgument(_nodes,this);
        Argument arg_ = new Argument(NumParsers.compareStr(operatorContent.getOper(), first_.getStruct(), second_.getStruct()));
        setSimpleArgument(arg_, _conf, _nodes);
    }

}
