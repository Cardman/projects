package code.expressionlanguage.guicompos;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.stds.AbstractExecConstantsCalculator;
import code.expressionlanguage.structs.Struct;

public final class AdvancedExecConstantsCalculator implements AbstractExecConstantsCalculator {
    private final LgNamesGui stds;

    public AdvancedExecConstantsCalculator(LgNamesGui stds) {
        this.stds = stds;
    }

    @Override
    public Struct getInnerSimpleResult(ClassField _classField) {
        return stds.getInnerSimpleResult(_classField);
    }
}
