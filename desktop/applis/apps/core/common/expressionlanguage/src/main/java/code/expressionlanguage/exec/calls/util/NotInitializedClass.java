package code.expressionlanguage.exec.calls.util;


import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.blocks.ExecRootBlock;

public final class NotInitializedClass implements CallingState {

    private final String className;
    private final ExecRootBlock rootBlock;
    private final Argument argument;

    public NotInitializedClass(String _className, ExecRootBlock _rootBlock, Argument _argument) {
        className = _className;
        rootBlock = _rootBlock;
        argument = _argument;
    }

    public String getClassName() {
        return className;
    }

    public ExecRootBlock getRootBlock() {
        return rootBlock;
    }

    public Argument getArgument() {
        return argument;
    }
}
