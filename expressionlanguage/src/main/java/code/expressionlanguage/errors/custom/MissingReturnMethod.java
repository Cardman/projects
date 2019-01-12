package code.expressionlanguage.errors.custom;
import code.expressionlanguage.methods.Classes;
import code.util.StringList;

public final class MissingReturnMethod extends FoundErrorInterpret {

    private static final String CLASS_NAME = "missing returning {0} or throwing for method";

    private String id;

    private String returning;

    @Override
    public String display(Classes _classes) {
        String message_ = StringList.simpleStringsFormat(CLASS_NAME, returning);
        return StringList.concat(super.display(_classes),message_,SEP_KEY_VAL,id,SEP_INFO);
    }

    public void setId(String _id) {
        id = _id;
    }

    public void setReturning(String _returning) {
        returning = _returning;
    }

}
