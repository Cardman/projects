package code.expressionlanguage.fwd.opers;

public final class ExecCallFctAnnotContent {

    private final String methodName;

    private final String classMethodId;

    public ExecCallFctAnnotContent(AnaCallFctContent _cont) {
        methodName = _cont.getMethodName();
        classMethodId = _cont.getClassMethodId().getConstraints().getName();
    }

    public String getMethodName() {
        return methodName;
    }

    public String getClassMethodId() {
        return classMethodId;
    }
}
