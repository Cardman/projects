package code.expressionlanguage.analyze;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.stds.AliasNumberType;
import code.expressionlanguage.structs.Struct;

public final class DefaultConstantsCalculator implements AbstractConstantsCalculator {
    private final AliasNumberType refNb;

    public DefaultConstantsCalculator(AliasNumberType _refNb) {
        this.refNb = _refNb;
    }

    @Override
    public Struct getInnerSimpleResult(ClassField _classField) {
        return ValidatorStandard.getSimpleResultBase(_classField,getRefNb());
    }

    public AliasNumberType getRefNb() {
        return refNb;
    }
}
