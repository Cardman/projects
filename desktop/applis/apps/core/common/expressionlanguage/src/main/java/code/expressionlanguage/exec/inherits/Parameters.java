package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.exec.util.Cache;
import code.expressionlanguage.exec.variables.LocalVariable;
import code.expressionlanguage.structs.Struct;
import code.util.StringMap;

public final class Parameters {
    private Struct error;
    private final StringMap<LocalVariable> parameters = new StringMap<LocalVariable>();
    private Cache cache;
    private Argument right;

    public Struct getError() {
        return error;
    }

    public void setError(Struct _error) {
        this.error = _error;
    }

    public StringMap<LocalVariable> getParameters() {
        return parameters;
    }

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache _cache) {
        this.cache = _cache;
    }

    public Argument getRight() {
        return right;
    }

    public void setRight(Argument _right) {
        this.right = _right;
    }
}
