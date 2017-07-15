package code.expressionlanguage.methods.util;
import code.expressionlanguage.opers.util.MethodId;
import code.util.StringList;

public final class MissingReturnMethod extends FoundErrorInterpret {

    private static final String CLASS_NAME = "missing returning {0} or throwing for method";

    private MethodId id;

    private String returning;

    @Override
    public String toString() {
        String message_ = StringList.simpleFormat(CLASS_NAME, returning);
        return super.toString()+message_+SEP_KEY_VAL+id.getSignature()+SEP_INFO;
    }

    public MethodId getId() {
        return id;
    }

    public void setId(MethodId _id) {
        id = _id;
    }

    public String getReturning() {
        return returning;
    }

    public void setReturning(String _returning) {
        returning = _returning;
    }

}
