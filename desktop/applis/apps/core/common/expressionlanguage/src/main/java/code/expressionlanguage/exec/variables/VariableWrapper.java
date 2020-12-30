package code.expressionlanguage.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecTemplates;
import code.expressionlanguage.structs.Struct;

public final class VariableWrapper implements AbstractWrapper {
    private final LocalVariable local;

    public VariableWrapper(LocalVariable _local) {
        local = _local;
    }
    public void setValue(StackCall _stack, ContextEl _conf, Argument _right) {
        ExecTemplates.checkSet(_conf,local,_right, _stack);
    }

    public Struct getValue(StackCall _stack, ContextEl _conf) {
        return local.getStruct();
    }

}
