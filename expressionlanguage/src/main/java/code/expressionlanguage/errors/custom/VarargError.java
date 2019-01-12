package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.util.StringList;

public final class VarargError extends FoundErrorInterpret {

    private static final String BAD_VAR_ARG_SYNTAX = "bad var arg syntax";

    private String methodName;

    @Override
    public String display(Classes _classes) {
        return StringList.concat(super.display(_classes),SEP_INFO,BAD_VAR_ARG_SYNTAX,methodName);
    }

    public void setMethodName(String _methodName) {
        methodName = _methodName;
    }
}
