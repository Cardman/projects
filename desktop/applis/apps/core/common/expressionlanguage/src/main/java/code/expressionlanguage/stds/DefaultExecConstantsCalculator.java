package code.expressionlanguage.stds;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.options.ValidatorStandard;
import code.expressionlanguage.structs.Struct;

public final class DefaultExecConstantsCalculator implements AbstractExecConstantsCalculator {
    private final AliasNumberType refNb;

    public DefaultExecConstantsCalculator(AliasNumberType _refNb) {
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
