package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.util.StringList;

public final class BadVariableName extends FoundErrorInterpret {

    private static final String CLASS_NAME = "bad variable name";

    private String varName;

    @Override
    public String display(Classes _classes) {
        return StringList.concat(super.display(_classes),CLASS_NAME,SEP_KEY_VAL,varName,SEP_INFO);
    }

    public void setVarName(String _className) {
        varName = _className;
    }

}
