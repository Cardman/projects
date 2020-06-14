package code.expressionlanguage.exec.calls.util;


public final class NotInitializedClass implements CallingState {

    private final String className;

    public NotInitializedClass(String _className) {
        className = _className;
    }

    public String getClassName() {
        return className;
    }
}
