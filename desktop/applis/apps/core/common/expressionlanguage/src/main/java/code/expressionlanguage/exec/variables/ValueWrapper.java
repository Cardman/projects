package code.expressionlanguage.exec.variables;

import code.expressionlanguage.structs.Struct;

public abstract class ValueWrapper implements AbstractWrapper {
    private Struct value;
    protected ValueWrapper(Struct _v) {
        value = _v;
    }

    public Struct getValue() {
        return value;
    }

    public void setValue(Struct _v) {
        this.value = _v;
    }
}
