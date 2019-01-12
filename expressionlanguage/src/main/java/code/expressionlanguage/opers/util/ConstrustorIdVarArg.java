package code.expressionlanguage.opers.util;

import code.expressionlanguage.common.GeneConstructor;

public final class ConstrustorIdVarArg {

    private ConstructorId realId;

    private ConstructorId constId;

    private boolean varArgToCall;

    private GeneConstructor ctor;

    public ConstructorId getRealId() {
        return realId;
    }

    public void setRealId(ConstructorId _realId) {
        realId = _realId;
    }

    public ConstructorId getConstId() {
        return constId;
    }

    public void setConstId(ConstructorId _constId) {
        constId = _constId;
    }

    public boolean isVarArgToCall() {
        return varArgToCall;
    }

    public void setVarArgToCall(boolean _varArgToCall) {
        varArgToCall = _varArgToCall;
    }

    public void setCtor(GeneConstructor _ctor) {
        ctor = _ctor;
    }
}
