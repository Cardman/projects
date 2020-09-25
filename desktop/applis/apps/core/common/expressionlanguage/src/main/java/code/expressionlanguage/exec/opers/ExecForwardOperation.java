package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.fwd.opers.ExecOperationContent;
import code.util.IdMap;

public final class ExecForwardOperation extends ExecLeafOperation implements AtomicExecCalculableOperation,ExecPossibleIntermediateDotted {
    private boolean intermediate;

    public ExecForwardOperation(ExecOperationContent _opCont, boolean _intermediate) {
        super(_opCont);
        intermediate = _intermediate;
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        Argument arg_ = getPreviousArg(this,_nodes,_conf);
        setSimpleArgument(arg_, _conf, _nodes);
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }

}
