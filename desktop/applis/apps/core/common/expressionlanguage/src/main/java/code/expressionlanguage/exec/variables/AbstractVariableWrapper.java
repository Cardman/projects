package code.expressionlanguage.exec.variables;

public abstract class AbstractVariableWrapper extends ValueWrapper {
    private final LocalVariable local;

    protected AbstractVariableWrapper(LocalVariable _local) {
        super(_local.getStruct());
        local = _local;
    }

    protected LocalVariable getLocal() {
        return local;
    }
}
