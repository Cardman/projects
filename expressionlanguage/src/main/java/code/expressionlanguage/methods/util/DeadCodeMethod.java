package code.expressionlanguage.methods.util;

public final class DeadCodeMethod extends FoundErrorInterpret {

    private static final String CLASS_NAME = "dead code for method";

    private String id;

    @Override
    public String display() {
        return super.display()+CLASS_NAME+SEP_KEY_VAL+id+SEP_INFO;
    }

    public String getId() {
        return id;
    }

    public void setId(String _id) {
        id = _id;
    }
}
