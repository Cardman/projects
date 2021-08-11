package code.bean.nat.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.variables.AbstractWrapper;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.structs.Struct;

public abstract class NatAbstractVariableWrapper implements AbstractWrapper {
    private final LocalVariable local;

    protected NatAbstractVariableWrapper(LocalVariable _local) {
        local = _local;
    }
    public void setValue(StackCall _stack, ContextEl _conf, Argument _right) {
        local.setStruct(_right.getStruct());
    }

    public Struct getValue(StackCall _stack, ContextEl _conf) {
        return local.getStruct();
    }

}
