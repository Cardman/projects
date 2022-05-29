package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.Identifiable;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardType;

public final class ConstrustorIdVarArg extends AbsPossibleVarArg {

    private ConstructorId realId;

    private ConstructorId constId;

    private StandardType standardType;
    private StandardConstructor constructor;

    @Override
    public Identifiable ident() {
        return getRealId();
    }

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

    public StandardType getStandardType() {
        return standardType;
    }

    public void setStandardType(StandardType _standardType) {
        standardType = _standardType;
    }

    public StandardConstructor getConstructor() {
        return constructor;
    }

    public void setConstructor(StandardConstructor _constructor) {
        this.constructor = _constructor;
    }

}
