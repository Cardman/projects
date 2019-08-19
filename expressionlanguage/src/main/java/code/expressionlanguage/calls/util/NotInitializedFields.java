package code.expressionlanguage.calls.util;

import code.expressionlanguage.Argument;

public final class NotInitializedFields implements CallingState {

    private final String className;

    private final Argument currentObject;

    public NotInitializedFields(String _className,
            Argument _currentObject) {
        className = _className;
        currentObject = _currentObject;
    }

    public String getClassName() {
        return className;
    }

    public Argument getCurrentObject() {
        return currentObject;
    }
}
