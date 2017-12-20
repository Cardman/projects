package code.expressionlanguage.stds;

import code.expressionlanguage.opers.util.Struct;

public final class ResultErrorStd {

    private Struct result;

    private String error;

    public Struct getResult() {
        return result;
    }

    public void setResult(Struct _result) {
        result = _result;
    }

    public String getError() {
        return error;
    }

    public void setError(String _error) {
        error = _error;
    }
}
