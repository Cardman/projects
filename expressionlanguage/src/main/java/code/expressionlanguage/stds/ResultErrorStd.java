package code.expressionlanguage.stds;

import code.expressionlanguage.opers.util.Struct;

public final class ResultErrorStd {

    private Struct result;

    private Object error;

    public Struct getResult() {
        return result;
    }

    public void setResult(Struct _result) {
        result = _result;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object _error) {
        error = _error;
    }
}
