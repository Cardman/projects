package code.expressionlanguage.stds;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.structs.Struct;

public interface AbstractExecConstantsCalculator {
    Struct getInnerSimpleResult(ClassField _classField);
}
