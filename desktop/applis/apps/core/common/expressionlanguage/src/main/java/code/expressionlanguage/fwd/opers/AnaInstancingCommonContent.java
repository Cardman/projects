package code.expressionlanguage.fwd.opers;


import code.expressionlanguage.functionid.ConstructorId;

public final class AnaInstancingCommonContent {
    private final String methodName;

    private String className;

    private int naturalVararg = -1;

    private String lastType = "";

    private ConstructorId constId;

    public AnaInstancingCommonContent(String _methodName) {
        methodName = _methodName;
    }
    public String getMethodName() {
        return methodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public void setNaturalVararg(int naturalVararg) {
        this.naturalVararg = naturalVararg;
    }

    public String getLastType() {
        return lastType;
    }

    public void setLastType(String lastType) {
        this.lastType = lastType;
    }

    public ConstructorId getConstId() {
        return constId;
    }

    public void setConstId(ConstructorId constId) {
        this.constId = constId;
    }
}
