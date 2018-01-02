package code.expressionlanguage.methods.util;
import code.util.StringList;

public final class MissingReturnMethod extends FoundErrorInterpret {

    private static final String CLASS_NAME = "missing returning {0} or throwing for method";

    private String id;

    private String returning;

    @Override
    public String display() {
        String message_ = StringList.simpleStringsFormat(CLASS_NAME, returning);
        return StringList.concat(super.display(),message_,SEP_KEY_VAL,id,SEP_INFO);
    }

    public String getId() {
        return id;
    }

    public void setId(String _id) {
        id = _id;
    }

    public String getReturning() {
        return returning;
    }

    public void setReturning(String _returning) {
        returning = _returning;
    }

}
