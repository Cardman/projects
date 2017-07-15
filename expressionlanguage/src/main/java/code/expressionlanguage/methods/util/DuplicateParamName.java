package code.expressionlanguage.methods.util;

public final class DuplicateParamName extends FoundErrorInterpret {

    private static final String CLASS_NAME = "duplicate param name";

    private String paramName;

    @Override
    public String toString() {
        return super.toString()+CLASS_NAME+SEP_KEY_VAL+paramName+SEP_INFO;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String _className) {
        paramName = _className;
    }

}
