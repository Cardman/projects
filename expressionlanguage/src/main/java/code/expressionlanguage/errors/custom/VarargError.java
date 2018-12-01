package code.expressionlanguage.errors.custom;

import code.util.StringList;

public final class VarargError extends FoundErrorInterpret {

    private static final String BAD_VAR_ARG_SYNTAX = "bad var arg syntax";

    private String methodName;

    @Override
    public String display() {
        return StringList.concat(super.display(),SEP_INFO,BAD_VAR_ARG_SYNTAX,methodName);
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String _methodName) {
        methodName = _methodName;
    }
}
