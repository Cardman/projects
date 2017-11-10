package code.expressionlanguage.opers.util;

public final class ConstrustorIdVarArg {

    private ConstructorId constId;

    private boolean varArgToCall;

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
}
