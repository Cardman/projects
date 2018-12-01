package code.expressionlanguage.errors.custom;

import code.util.StringList;

public final class BadFieldName extends FoundErrorInterpret {

    private static final String CLASS_NAME = "bad field name";

    private String name;

    @Override
    public String display() {
        return StringList.concat(super.display(),CLASS_NAME,SEP_KEY_VAL,name,SEP_INFO);
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

}
