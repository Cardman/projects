package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.util.StringList;

public final class IllegalCallCtorByType extends FoundErrorInterpret {

    private static final String BAD_CALL_CTOR_TYPE = "bad constructor type call";
    private String type;

    @Override
    public String display(Classes _classes) {
        return StringList.concat(super.display(_classes),SEP_INFO,BAD_CALL_CTOR_TYPE,SEP_KEY_VAL,type);
    }

    public void setType(String _type) {
        type = _type;
    }
}
