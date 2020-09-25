package code.expressionlanguage.fwd.opers;


import code.expressionlanguage.functionid.ConstructorId;

public final class ExecInstancingCommonContent {
    private final String methodName;

    private final String className;

    private final int naturalVararg;

    private final String lastType;

    private final ConstructorId constId;

    public ExecInstancingCommonContent(AnaInstancingCommonContent _cont) {
        methodName = _cont.getMethodName();
        className = _cont.getClassName();
        naturalVararg = _cont.getNaturalVararg();
        lastType = _cont.getLastType();
        constId = _cont.getConstId();
    }
    public String getMethodName() {
        return methodName;
    }

    public String getClassName() {
        return className;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public String getLastType() {
        return lastType;
    }

    public ConstructorId getConstId() {
        return constId;
    }
}
