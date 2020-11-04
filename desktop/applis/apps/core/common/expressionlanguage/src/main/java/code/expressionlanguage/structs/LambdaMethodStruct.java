package code.expressionlanguage.structs;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.functionid.MethodAccessKind;

public final class LambdaMethodStruct extends WithoutParentIdStruct implements LambdaStruct {

    private Argument instanceCall = Argument.createVoid();

    private final String className;
    private final String formClassName;

    private final boolean polymorph;

    private final boolean shiftInstance;

    private final int ancestor;

    private final boolean abstractMethod;

    private boolean directCast;
    private boolean safeInstance;
    private boolean expCast;
    private MethodAccessKind kind;
    private String methodName = "";
    private Struct metaInfo = NullStruct.NULL_VALUE;
    public LambdaMethodStruct(String _className, String _formClassName,
                              boolean _polymorph, boolean _shiftInstance, int _ancestor, boolean _abstractMethod) {
        className = _className;
        formClassName = _formClassName;
        polymorph = _polymorph;
        shiftInstance = _shiftInstance;
        ancestor = _ancestor;
        abstractMethod = _abstractMethod;
    }

    public Argument getInstanceCall() {
        return instanceCall;
    }

    public void setInstanceCall(Argument _instanceCall) {
        instanceCall = _instanceCall;
    }

    public Struct getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(Struct _metaInfo) {
        this.metaInfo = _metaInfo;
    }

    public String getFormClassName() {
        return formClassName;
    }

    public MethodAccessKind getKind() {
        return kind;
    }

    public void setKind(MethodAccessKind _staticMethod) {
        this.kind = _staticMethod;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String _methodName) {
        methodName = _methodName;
    }

    public boolean isPolymorph() {
        return polymorph;
    }

    public boolean isShiftInstance() {
        return shiftInstance;
    }

    public boolean isSafeInstance() {
        return safeInstance;
    }

    public void setSafeInstance(boolean _safeInstance) {
        safeInstance = _safeInstance;
    }

    public int getAncestor() {
        return ancestor;
    }
    public boolean isAbstractMethod() {
        return abstractMethod;
    }

    public boolean isStaticCall() {
        return kind == MethodAccessKind.STATIC_CALL || directCast || expCast;
    }

    public void setDirectCast(boolean _directCast) {
        directCast = _directCast;
    }

    public void setExpCast(boolean _expCast) {
        expCast = _expCast;
    }

    @Override
    public String getClassName(ContextEl _contextEl) {
        return className;
    }

}
