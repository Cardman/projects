package code.expressionlanguage.exec.variables;

public abstract class AbstractVariableWrapper implements AbstractWrapper {
    private final LocalVariable local;

    protected AbstractVariableWrapper(LocalVariable _local) {
        local = _local;
    }

    protected LocalVariable getLocal() {
        return local;
    }
}
