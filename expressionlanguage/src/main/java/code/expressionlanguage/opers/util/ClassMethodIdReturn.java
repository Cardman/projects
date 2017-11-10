package code.expressionlanguage.opers.util;

import code.expressionlanguage.methods.MethodBlock;

public final class ClassMethodIdReturn {

    private final boolean foundMethod;
    private ClassMethodId id;
    private MethodBlock method;
    private MethodId realId;
    private String realClass;

    private String returnType;

    private boolean staticMethod;

    private boolean abstractMethod;

    private boolean varArgToCall;

    public ClassMethodIdReturn(boolean _foundMethod) {
        foundMethod = _foundMethod;
    }

    public boolean isFoundMethod() {
        return foundMethod;
    }

    public ClassMethodId getId() {
        return id;
    }

    public void setId(ClassMethodId _id) {
        id = _id;
    }

    public MethodBlock getMethod() {
        return method;
    }

    public void setMethod(MethodBlock _method) {
        method = _method;
    }

    public MethodId getRealId() {
        return realId;
    }

    public void setRealId(MethodId _realId) {
        realId = _realId;
    }

    public String getRealClass() {
        return realClass;
    }

    public void setRealClass(String _realClass) {
        realClass = _realClass;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String _returnType) {
        returnType = _returnType;
    }

    public boolean isStaticMethod() {
        return staticMethod;
    }

    public void setStaticMethod(boolean _staticMethod) {
        staticMethod = _staticMethod;
    }

    public boolean isAbstractMethod() {
        return abstractMethod;
    }

    public void setAbstractMethod(boolean _abstractMethod) {
        abstractMethod = _abstractMethod;
    }

    public boolean isVarArgToCall() {
        return varArgToCall;
    }

    public void setVarArgToCall(boolean _varArgToCall) {
        varArgToCall = _varArgToCall;
    }

}
