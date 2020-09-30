package code.expressionlanguage.stds;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.structs.Struct;

public final class DefaultExecConstantsCalculator implements AbstractExecConstantsCalculator {
    private final AliasNumber refNb;

    public DefaultExecConstantsCalculator(AliasNumber refNb) {
        this.refNb = refNb;
    }

    @Override
    public Struct getInnerSimpleResult(ClassField _classField) {
        return ValidatorStandard.getSimpleResultBase(_classField,getRefNb());
    }

    public AliasNumber getRefNb() {
        return refNb;
    }
}
