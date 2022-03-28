package code.expressionlanguage.exec.variables;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.exec.inherits.ExecVariableTemplates;
import code.expressionlanguage.structs.Struct;

public abstract class AbstractVariableWrapper implements AbstractWrapper {
    private final LocalVariable local;

    protected AbstractVariableWrapper(LocalVariable _local) {
        local = _local;
    }
    public void setValue(StackCall _stack, ContextEl _conf, Argument _right) {
        ExecVariableTemplates.checkSet(_conf,local,_right, _stack);
    }

    public Struct getValue(StackCall _stack, ContextEl _conf) {
        return local.getStruct();
    }

    protected LocalVariable getLocal() {
        return local;
    }
}
