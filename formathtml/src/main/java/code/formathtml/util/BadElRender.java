package code.formathtml.util;

import code.expressionlanguage.errors.custom.ErrorList;
import code.expressionlanguage.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.methods.Classes;
import code.util.StringList;

public final class BadElRender extends FoundErrorInterpret {

    private ErrorList errors;

    @Override
    public String display(Classes _classes) {
        return StringList.concat(super.display(_classes),SEP_INFO,errors.display(_classes));
    }

    public ErrorList getErrors() {
        return errors;
    }

    public void setErrors(ErrorList _errors) {
        errors = _errors;
    }

}
