package code.expressionlanguage.stds;

import code.expressionlanguage.structs.Struct;

public final class ResultErrorStd {

    private Struct result;

    private String error;

    private String errorMessage;

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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String _errorMessage) {
        errorMessage = _errorMessage;
    }

}
