package code.expressionlanguage.fwd.opers;

public final class AnaArrayInstancingContent {
    private final String methodName;

    private String className;
    public AnaArrayInstancingContent(String _methodName) {
        this.methodName = _methodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        this.className = _className;
    }

    public String getMethodName() {
        return methodName;
    }
}
