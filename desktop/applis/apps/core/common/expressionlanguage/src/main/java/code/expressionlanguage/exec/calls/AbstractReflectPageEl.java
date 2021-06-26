package code.expressionlanguage.exec.calls;

import code.expressionlanguage.exec.util.ExecFormattedRootBlock;

public abstract class AbstractReflectPageEl extends AbstractPageEl implements ForwardPageEl {

    private boolean wrapException;

    private boolean lambda;

    protected AbstractReflectPageEl() {
        super(ExecFormattedRootBlock.defValue());
    }

    public boolean isWrapException() {
        return wrapException;
    }

    void setWrapException(boolean _wrapException) {
        if (_wrapException && lambda) {
            wrapException = false;
            return;
        }
        wrapException = _wrapException;
    }

    public void setLambda(boolean _lambda) {
        lambda = _lambda;
    }

}
