package code.expressionlanguage.fwd.opers;

public final class ExecStaticFctCommonContent {

    private final String methodName;

    private final String lastType;

    private final int naturalVararg;

    public ExecStaticFctCommonContent(AnaCallFctContent _a) {
        methodName = _a.getMethodName();
        lastType = _a.getLastType();
        naturalVararg = _a.getNaturalVararg();
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public String getLastType() {
        return lastType;
    }

    public String getMethodName() {
        return methodName;
    }
}
