package code.expressionlanguage.fwd.opers;


import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.stds.StandardConstructor;

public final class ExecInstancingDirContent extends ExecInstancingCommonContent {
    private final ConstructorId constId;
    private final StandardConstructor constructor;

    public ExecInstancingDirContent(AnaInstancingCommonContent _cont) {
        super(_cont);
        constId = _cont.getConstId();
        constructor = _cont.getConstructor();
    }

    public ConstructorId getConstId() {
        return constId;
    }

    public StandardConstructor getConstructor() {
        return constructor;
    }
}
