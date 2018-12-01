package code.expressionlanguage.errors.custom;

import code.util.StringList;

public final class IllegalCallCtorByType extends FoundErrorInterpret {

    private static final String BAD_CALL_CTOR_TYPE = "bad constructor type call";
    private String type;

    @Override
    public String display() {
        return StringList.concat(super.display(),SEP_INFO,BAD_CALL_CTOR_TYPE,SEP_KEY_VAL,type);
    }

    public String getType() {
        return type;
    }

    public void setType(String _type) {
        type = _type;
    }
}
