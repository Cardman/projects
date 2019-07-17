package code.formathtml.exec;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.ForwardOperation;

public final class RendForwardOperation extends RendLeafOperation implements RendCalculableOperation,RendPossibleIntermediateDotted {
    private boolean intermediate;
    private Argument previousArgument;

    RendForwardOperation(ForwardOperation _v) {
        super(_v);
        intermediate = _v.isIntermediate();
    }

    @Override
    public void setPreviousArgument(Argument _argument) {
        previousArgument = _argument;
    }

    @Override
    public void calculate(ExecutableCode _conf) {
        Argument previous_ = getPreviousArg(this,_conf);
        setSimpleArgument(previous_, _conf);
    }

    @Override
    public Argument getPreviousArgument() {
        return previousArgument;
    }

    @Override
    public boolean isIntermediateDottedOperation() {
        return intermediate;
    }
}
