package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public final class Parameters {
    private Struct error;
    private final StringMap<LocalVariable> parameters = new StringMap<LocalVariable>();
    private Argument right;

    public Struct getError() {
        return error;
    }

    public void setError(Struct error) {
        this.error = error;
    }

    public StringMap<LocalVariable> getParameters() {
        return parameters;
    }

    public Argument getRight() {
        return right;
    }

    public void setRight(Argument right) {
        this.right = right;
    }
}
