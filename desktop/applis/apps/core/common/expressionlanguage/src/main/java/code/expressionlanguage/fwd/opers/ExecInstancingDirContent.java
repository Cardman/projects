package code.expressionlanguage.fwd.opers;


import code.expressionlanguage.functionid.ConstructorId;

public final class ExecInstancingDirContent extends ExecInstancingCommonContent {
    private final ConstructorId constId;

    public ExecInstancingDirContent(AnaInstancingCommonContent _cont) {
        super(_cont);
        constId = _cont.getConstId();
    }

    public ConstructorId getConstId() {
        return constId;
    }
}
