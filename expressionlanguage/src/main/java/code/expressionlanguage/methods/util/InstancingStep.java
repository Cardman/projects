package code.expressionlanguage.methods.util;

public enum InstancingStep {
    NOTHING(CallingClassConstructor.NOTHING),
    NEWING(CallingClassConstructor.NOTHING,true),
    USING_THIS(CallingClassConstructor.THIS_CLASS,true, true),
    USING_SUPER(CallingClassConstructor.SUPER_CLASS,true, true);
    private final CallingClassConstructor call;
    private final boolean instancing;
    private final boolean calling;
    InstancingStep(CallingClassConstructor _call) {
        this(_call, false);
    }
    InstancingStep(CallingClassConstructor _call,boolean _instancing) {
        this(_call, _instancing, false);
    }
    InstancingStep(CallingClassConstructor _call,boolean _instancing, boolean _calling) {
        call = _call;
        instancing = _instancing;
        calling = _calling;
    }
    public CallingClassConstructor getCall() {
        return call;
    }
    public boolean isInstancing() {
        return instancing;
    }
    public boolean isCalling() {
        return calling;
    }
}
