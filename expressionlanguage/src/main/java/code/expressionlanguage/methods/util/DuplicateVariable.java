package code.expressionlanguage.methods.util;

import code.util.StringList;


public final class DuplicateVariable extends FoundErrorInterpret {

    private static final String CLASS_NAME = "duplicate variable";

    private String id;

    @Override
    public String display() {
        return StringList.concat(super.display(),CLASS_NAME,SEP_KEY_VAL,id,SEP_INFO);
    }

    public String getId() {
        return id;
    }

    public void setId(String _id) {
        id = _id;
    }

}
