package code.bean.nat.exec.variables;

import code.expressionlanguage.structs.Struct;

public final class VariableWrapperNat {

    private Struct element;

    public VariableWrapperNat(Struct _local) {
        element = _local;
    }
    public void setValue(Struct _right) {
        element= _right;
    }

    public Struct getValue() {
        return element;
    }

}
