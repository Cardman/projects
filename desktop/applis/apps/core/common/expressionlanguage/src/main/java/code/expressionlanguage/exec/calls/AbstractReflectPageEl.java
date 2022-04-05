package code.expressionlanguage.exec.calls;

import code.expressionlanguage.exec.util.ExecFormattedRootBlock;

public abstract class AbstractReflectPageEl extends AbstractPageEl implements ForwardPageEl {

    private boolean wrapException;

    private final boolean lambda;

    protected AbstractReflectPageEl(boolean _lambda) {
        super(ExecFormattedRootBlock.defValue());
        lambda = _lambda;
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

}
