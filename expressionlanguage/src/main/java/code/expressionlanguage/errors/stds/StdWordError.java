package code.expressionlanguage.errors.stds;

import code.util.StringList;


public class StdWordError {

    private ErrorCat errCat;

    private String message;

    public String display() {
        return StringList.concat(errCat.name(),"-",message);
    }

    public ErrorCat getErrCat() {
        return errCat;
    }

    public void setErrCat(ErrorCat _errCat) {
        errCat = _errCat;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String _message) {
        message = _message;
    }

}
