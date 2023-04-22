package code.expressionlanguage.fwd.opers;


import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardType;

public final class AnaInstancingCommonContent {
    private final String methodName;

    private AnaFormattedRootBlock formattedType;

    private int naturalVararg = -1;

    private String lastType = "";

    private ConstructorId constId;
    private StandardType std;
    private StandardConstructor constructor;

    public AnaInstancingCommonContent(String _methodName) {
        methodName = _methodName;
    }
    public String getMethodName() {
        return methodName;
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

    public StandardType getStd() {
        return std;
    }

    public void setStd(StandardType _s) {
        this.std = _s;
    }

    public StandardConstructor getConstructor() {
        return constructor;
    }

    public void setConstructor(StandardConstructor _constructor) {
        this.constructor = _constructor;
    }

}
