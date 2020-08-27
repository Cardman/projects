package code.expressionlanguage.exec.calls.util;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.blocks.ExecRootBlock;

public final class NotInitializedFields implements CallingState {

    private final String className;
    private final ExecRootBlock rootBlock;

    private final Argument currentObject;

    public NotInitializedFields(String _className,
                                ExecRootBlock _rootBlock,
            Argument _currentObject) {
        className = _className;
        rootBlock = _rootBlock;
        currentObject = _currentObject;
    }

    public String getClassName() {
        return className;
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public Argument getCurrentObject() {
        return currentObject;
    }
}
