package code.expressionlanguage.fwd.opers;


import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.functionid.ConstructorId;

public final class AnaInstancingCommonContent {
    private final String methodName;

    private String className = "";
    private AnaFormattedRootBlock formattedType;

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

    public void setClassName(String _className) {
        this.className = _className;
    }

    public AnaFormattedRootBlock getFormattedType() {
        return formattedType;
    }

    public void setFormattedType(AnaFormattedRootBlock _formattedType) {
        this.formattedType = _formattedType;
    }

    public int getNaturalVararg() {
        return naturalVararg;
    }

    public void setNaturalVararg(int _naturalVararg) {
        this.naturalVararg = _naturalVararg;
    }

    public String getLastType() {
        return lastType;
    }

    public void setLastType(String _lastType) {
        this.lastType = _lastType;
    }

    public ConstructorId getConstId() {
        return constId;
    }

    public void setConstId(ConstructorId _constId) {
        this.constId = _constId;
    }
}
