package code.bean.nat.exec.variables;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.LocalVariable;

public final class VariableWrapperNat extends NatAbstractVariableWrapper {

    public VariableWrapperNat(LocalVariable _local) {
        super(_local);
    }

    @Override
    public String getClassName(StackCall _stackCall, ContextEl _contextEl) {
        return "";
    }
}
