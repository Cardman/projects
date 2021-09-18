package code.expressionlanguage.fwd.opers;


import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardType;

public final class ExecInstancingDirContent extends ExecInstancingCommonContent {
    private final ConstructorId constId;
    private final StandardConstructor constructor;
    private final StandardType standardType;

    public ExecInstancingDirContent(AnaInstancingCommonContent _cont) {
        super(_cont);
        constId = _cont.getConstId();
        constructor = _cont.getConstructor();
        standardType = _cont.getStandardType();
    }

    public ConstructorId getConstId() {
        return constId;
    }

    public StandardConstructor getConstructor() {
        return constructor;
    }

    public StandardType getStandardType() {
        return standardType;
    }
}
