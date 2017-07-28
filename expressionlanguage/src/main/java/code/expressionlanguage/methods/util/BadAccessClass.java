package code.expressionlanguage.methods.util;


public class BadAccessClass extends FoundErrorInterpret {

    private static final String CLASS_NAME = "bad access class";

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
