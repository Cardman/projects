package code.expressionlanguage.opers.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.methods.util.ArgumentsPair;
import code.expressionlanguage.opers.ForwardOperation;
import code.util.IdMap;

public final class ExecForwardOperation extends ExecVariableLeafOperation implements ExecPossibleIntermediateDotted {
    private boolean intermediate;

    ExecForwardOperation(ForwardOperation _v) {
        super(_v);
        intermediate = _v.isIntermediate();
        setPreviousArgument(null);
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

    @Override
    public void setPreviousArgument(Argument _argument) {
    }
}
