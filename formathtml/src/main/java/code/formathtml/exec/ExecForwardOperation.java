package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.ForwardOperation;

public final class ExecForwardOperation extends ExecVariableLeafOperation implements ExecPossibleIntermediateDotted {
    private boolean intermediate;
    private int off;
    private Argument previousArgument;

    ExecForwardOperation(ForwardOperation _v) {
        super(_v);
        intermediate = _v.isIntermediate();
        off = _v.getOff();
    }

    @Override
    public void setPreviousArgument(Argument _argument) {
        previousArgument = _argument;
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        Argument previous_;
        if (isIntermediateDottedOperation()) {
            previous_ = getPreviousArgument();
        } else {
            previous_ = _conf.getOperationPageEl().getGlobalArgument();
        }
        setSimpleArgument(previous_, _conf);
    }

    public Argument getPreviousArgument() {
        return previousArgument;
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }
}
