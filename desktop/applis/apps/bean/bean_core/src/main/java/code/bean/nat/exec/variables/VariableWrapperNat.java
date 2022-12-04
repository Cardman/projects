package code.bean.nat.exec.variables;

import code.bean.nat.*;

public final class VariableWrapperNat {

    private NaSt element;

    public VariableWrapperNat(NaSt _local) {
        element = _local;
    }
    public void setValue(NaSt _right) {
        element= _right;
    }

    public NaSt getValue() {
        return element;
    }

}
