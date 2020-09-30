package code.expressionlanguage.analyze;

import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.structs.Struct;

public interface AbstractConstantsCalculator {
    Struct getInnerSimpleResult(ClassField _classField);
}
