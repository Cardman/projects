package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.MethodId;
import code.util.StringList;

public class BadAccessMethod extends FoundErrorInterpret {

    private static final String CLASS_NAME = "bad access method";

    private String id;

    @Override
    public String display(Classes _classes) {
        return StringList.concat(super.display(_classes),CLASS_NAME,SEP_KEY_VAL,id,SEP_INFO);
    }

    public void setId(String _id) {
        id = _id;
    }
}
