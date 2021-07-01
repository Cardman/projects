package code.expressionlanguage.fwd.opers;


public abstract class ExecInstancingCommonContent {
    private final String methodName;

    private final int naturalVararg;

    private final String lastType;

    protected ExecInstancingCommonContent(AnaInstancingCommonContent _cont) {
        methodName = _cont.getMethodName();
        naturalVararg = _cont.getNaturalVararg();
        lastType = _cont.getLastType();
    }
    public String getMethodName() {
        return methodName;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public String getLastType() {
        return lastType;
    }

}
