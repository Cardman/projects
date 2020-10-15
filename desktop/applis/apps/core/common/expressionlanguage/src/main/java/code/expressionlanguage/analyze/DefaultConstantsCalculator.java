package code.expressionlanguage.analyze;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.stds.AliasNumber;
import code.expressionlanguage.structs.Struct;

public final class DefaultConstantsCalculator implements AbstractConstantsCalculator {
    private final AliasNumber refNb;

    public DefaultConstantsCalculator(AliasNumber _refNb) {
        this.refNb = _refNb;
    }

    @Override
    public Struct getInnerSimpleResult(ClassField _classField) {
        return ValidatorStandard.getSimpleResultBase(_classField,getRefNb());
    }

    public AliasNumber getRefNb() {
        return refNb;
    }
}
