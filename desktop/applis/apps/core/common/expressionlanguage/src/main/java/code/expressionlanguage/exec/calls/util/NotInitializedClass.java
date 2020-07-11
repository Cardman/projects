package code.expressionlanguage.exec.calls.util;


import code.expressionlanguage.Argument;

public final class NotInitializedClass implements CallingState {

    private final String className;
    private final Argument argument;

    public NotInitializedClass(String _className, Argument _argument) {
        className = _className;
        argument = _argument;
    }

    public String getClassName() {
        return className;
    }

    public Argument getArgument() {
        return argument;
    }
}
