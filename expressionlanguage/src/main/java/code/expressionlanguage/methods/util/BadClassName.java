package code.expressionlanguage.methods.util;

public final class BadClassName extends FoundErrorInterpret {

    private static final String CLASS_NAME = "bad name";

    private String className;

    @Override
    public String toString() {
        return super.toString()+CLASS_NAME+SEP_KEY_VAL+className+SEP_INFO;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String _className) {
        className = _className;
    }

}
