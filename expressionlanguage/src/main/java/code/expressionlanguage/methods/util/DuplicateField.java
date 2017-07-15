package code.expressionlanguage.methods.util;


public final class DuplicateField extends FoundErrorInterpret {

    private static final String CLASS_NAME = "duplicate field";

    private String id;

    @Override
    public String toString() {
        return super.toString()+CLASS_NAME+SEP_KEY_VAL+id+SEP_INFO;
    }

    public String getId() {
        return id;
    }

    public void setId(String _id) {
        id = _id;
    }

}
