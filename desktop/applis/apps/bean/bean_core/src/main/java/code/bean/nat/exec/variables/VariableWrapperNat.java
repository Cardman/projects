package code.bean.nat.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.structs.Struct;

public final class VariableWrapperNat {
    private final LocalVariable local;
    public VariableWrapperNat(LocalVariable _local) {
        local = _local;
    }
    public void setValue(Argument _right) {
        local.setStruct(_right.getStruct());
    }

    public Struct getValue() {
        return local.getStruct();
    }

}
