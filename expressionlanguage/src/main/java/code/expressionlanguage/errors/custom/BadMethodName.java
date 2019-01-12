package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.util.StringList;

public final class BadMethodName extends FoundErrorInterpret {

    private static final String CLASS_NAME = "bad method name";

    private String name;

    @Override
    public String display(Classes _classes) {
        return StringList.concat(super.display(_classes),CLASS_NAME,SEP_KEY_VAL,name,SEP_INFO);
    }

    public void setName(String _name) {
        name = _name;
    }

}
