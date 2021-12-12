package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.calls.AbstractPageEl;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.util.IdMap;

public final class ExecInternVariableOperation extends ExecLeafOperation implements AtomicExecCalculableOperation {

    private final String variableName;

    public ExecInternVariableOperation(int _indexChild, ExecClassArgumentMatching _res, int _order, String _varName) {
        super(_indexChild,_res,_order);
        variableName = _varName;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes,
                          ContextEl _conf, StackCall _stack) {
        AbstractPageEl ip_ = _stack.getLastPage();
        Argument a_ = ExecTemplates.getValueVar(variableName,ip_.getInternVars(),_conf, _stack);
        setSimpleArgument(a_, _conf, _nodes, _stack);
    }

}
