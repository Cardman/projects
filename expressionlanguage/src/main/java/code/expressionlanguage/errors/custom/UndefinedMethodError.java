package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.MethodId;
import code.util.StringList;

public final class UndefinedMethodError extends FoundErrorInterpret {

    private static final String CLASS_NAME = "undefined method";

    private StringList className;

    private String id;

    @Override
    public String display(Classes _classes) {
        return StringList.concat(super.display(_classes),SEP_INFO,CLASS_NAME,SEP_KEY_VAL,className.display(),".",id,SEP_INFO);
    }

    public void setClassName(StringList _className) {
        className = _className;
    }

    public void setId(String _id) {
        id = _id;
    }
}
