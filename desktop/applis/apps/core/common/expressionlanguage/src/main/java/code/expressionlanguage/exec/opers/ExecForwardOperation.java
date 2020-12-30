package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;

public final class ExecForwardOperation extends ExecLeafOperation implements AtomicExecCalculableOperation,ExecPossibleIntermediateDotted {
    private final boolean intermediate;

    public ExecForwardOperation(ExecOperationContent _opCont, boolean _intermediate) {
        super(_opCont);
        intermediate = _intermediate;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf, StackCall _stack) {
        Argument arg_ = getPreviousArg(this,_nodes, _stack);
        setSimpleArgument(arg_, _conf, _nodes, _stack);
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }

}
