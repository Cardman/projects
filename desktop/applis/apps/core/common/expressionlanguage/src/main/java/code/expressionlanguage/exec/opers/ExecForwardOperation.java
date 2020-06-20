package code.expressionlanguage.exec.opers;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.variables.ArgumentsPair;
import code.expressionlanguage.analyze.opers.ForwardOperation;
import code.util.IdMap;

public final class ExecForwardOperation extends ExecLeafOperation implements AtomicExecCalculableOperation,ExecPossibleIntermediateDotted {
    private boolean intermediate;

    ExecForwardOperation(ForwardOperation _v) {
        super(_v);
        intermediate = _v.isIntermediate();
    }

    @Override
    public void calculate(IdMap<ExecOperationNode, ArgumentsPair> _nodes, ContextEl _conf) {
        if (isIntermediateDottedOperation()) {
            Argument arg_ = getPreviousArg(this,_nodes,_conf);
            setSimpleArgument(arg_, _conf, _nodes);
        } else {
            Argument arg_ = _conf.getLastPage().getGlobalArgument();
            setSimpleArgument(arg_, _conf, _nodes);
        }
    }

    @Override
    public Argument getPreviousArgument() {
        return null;
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }

}
