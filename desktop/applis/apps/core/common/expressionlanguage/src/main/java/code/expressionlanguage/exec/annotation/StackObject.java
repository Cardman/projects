package code.expressionlanguage.exec.annotation;

import code.expressionlanguage.structs.Struct;

abstract class StackObject {

    private Struct value;

    Struct getValue() {
        return value;
    }

    void setValue(Struct _value) {
        value = _value;
    }

    abstract String getPrefix();
}
