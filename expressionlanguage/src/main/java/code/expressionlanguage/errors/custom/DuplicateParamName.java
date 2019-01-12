package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.util.StringList;

public final class DuplicateParamName extends FoundErrorInterpret {

    private static final String CLASS_NAME = "duplicate param name";

    private String paramName;

    @Override
    public String display(Classes _classes) {
        return StringList.concat(super.display(_classes),CLASS_NAME,SEP_KEY_VAL,paramName,SEP_INFO);
    }

    public void setParamName(String _className) {
        paramName = _className;
    }

}
