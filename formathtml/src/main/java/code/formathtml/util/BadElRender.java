package code.formathtml.util;

import code.expressionlanguage.errors.custom.ErrorList;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.util.StringList;

public final class BadElRender extends FoundErrorInterpret {

    private ErrorList errors;

    @Override
    public String display() {
        return StringList.concat(super.display(),SEP_INFO,errors.display());
    }

    public ErrorList getErrors() {
        return errors;
    }

    public void setErrors(ErrorList _errors) {
        errors = _errors;
    }

}
