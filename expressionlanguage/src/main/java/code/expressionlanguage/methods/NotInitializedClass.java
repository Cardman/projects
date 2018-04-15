package code.expressionlanguage.methods;


public final class NotInitializedClass {

    private final String className;

    public NotInitializedClass(String _className) {
        className = _className;
    }

    public String getClassName() {
        return className;
    }
}
