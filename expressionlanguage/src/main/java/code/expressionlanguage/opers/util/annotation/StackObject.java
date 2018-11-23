package code.expressionlanguage.opers.util.annotation;

import code.expressionlanguage.structs.Struct;

abstract class StackObject {

    private Struct value;

    public Struct getValue() {
        return value;
    }

    public void setValue(Struct _value) {
        value = _value;
    }

    abstract String getPrefix();
}
