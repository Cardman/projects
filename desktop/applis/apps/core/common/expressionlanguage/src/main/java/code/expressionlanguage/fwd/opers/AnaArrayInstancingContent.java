package code.expressionlanguage.fwd.opers;

public final class AnaArrayInstancingContent {
    private final String methodName;

    private String className;
    public AnaArrayInstancingContent(String methodName) {
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }
}
