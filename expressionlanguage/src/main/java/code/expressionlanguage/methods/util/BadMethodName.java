package code.expressionlanguage.methods.util;

public final class BadMethodName extends FoundErrorInterpret {

    private static final String CLASS_NAME = "bad method name";

    private String name;

    @Override
    public String toString() {
        return super.toString()+CLASS_NAME+SEP_KEY_VAL+name+SEP_INFO;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

}
