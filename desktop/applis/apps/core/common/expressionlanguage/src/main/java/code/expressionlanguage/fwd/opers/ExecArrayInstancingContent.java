package code.expressionlanguage.fwd.opers;

public final class ExecArrayInstancingContent {
    private final String methodName;

    private final String className;

    public ExecArrayInstancingContent(AnaArrayInstancingContent _cont) {
        this.methodName = _cont.getMethodName();
        this.className = _cont.getClassName();
    }

    public String getMethodName() {
        return methodName;
    }

    public String getClassName() {
        return className;
    }
}
