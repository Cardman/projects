package code.bean.nat.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.structs.Struct;

public final class VariableWrapperNat {

    private Struct element;

    public VariableWrapperNat(Struct _local) {
        element = _local;
    }
    public void setValue(Argument _right) {
        element= _right.getStruct();
    }

    public Struct getValue() {
        return element;
    }

}
