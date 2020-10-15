package code.expressionlanguage.guicompos;

import code.expressionlanguage.analyze.AbstractConstantsCalculator;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.structs.Struct;

public final class AdvancedConstantsCalculator implements AbstractConstantsCalculator {
    private final LgNamesGui stds;

    public AdvancedConstantsCalculator(LgNamesGui _stds) {
        this.stds = _stds;
    }

    @Override
    public Struct getInnerSimpleResult(ClassField _classField) {
        return stds.getInnerSimpleResult(_classField);
    }
}
