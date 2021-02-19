package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecVariableContent;
import code.util.IdMap;

public final class ExecFinalVariableOperation extends ExecLeafOperation implements
        AtomicExecCalculableOperation {

    private final ExecVariableContent variableContent;

    public ExecFinalVariableOperation(ExecOperationContent _opCont, ExecVariableContent _variableContent) {
        super(_opCont);
        variableContent = _variableContent;
    }
    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        setRelOffsetPossibleLastPage(variableContent.getOff(), _stack);
        AbstractPageEl ip_ = _stack.getLastPage();
        Argument arg_ = ExecTemplates.getIndexLoop(_conf, variableContent.getVariableName(), variableContent.getDeep(), ip_.getCache(), ip_.getVars(), _stack);
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }

}
