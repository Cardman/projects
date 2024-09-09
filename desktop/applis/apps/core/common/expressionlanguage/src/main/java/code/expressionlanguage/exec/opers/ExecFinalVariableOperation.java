package code.expressionlanguage.exec.opers;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecVariableTemplates;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.expressionlanguage.fwd.opers.ExecVariableContent;
import code.expressionlanguage.structs.Struct;
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
        Struct arg_ = ExecVariableTemplates.getIndexLoop(_conf, variableContent, _stack);
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }

}
