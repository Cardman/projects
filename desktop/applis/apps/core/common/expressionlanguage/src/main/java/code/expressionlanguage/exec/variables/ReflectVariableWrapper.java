package code.expressionlanguage.exec.variables;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.exec.StackCall;

public final class ReflectVariableWrapper extends AbstractVariableWrapper {

    public ReflectVariableWrapper(LocalVariable _local) {
        super(_local);
    }

    @Override
    public String getClassName(StackCall _stack, ContextEl _conf) {
        return getLocal().getStruct().getClassName(_conf);
    }
}
