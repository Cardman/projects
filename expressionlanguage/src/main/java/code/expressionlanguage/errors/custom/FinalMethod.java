package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.util.StringList;

public final class FinalMethod extends FoundErrorInterpret {

    private static final String SEP_CLASS_METHOD = ".";

    private static final String CLASS_NAME = "final method";

    private String className;

    private String id;

    @Override
    public String display(Classes _classes) {
        return StringList.concat(super.display(_classes),CLASS_NAME,SEP_KEY_VAL,className,SEP_CLASS_METHOD,id,SEP_INFO);
    }

    public void setClassName(String _className) {
        className = _className;
    }

    public void setId(String _id) {
        id = _id;
    }
}
