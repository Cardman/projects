package code.expressionlanguage.methods.util;

import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.util.StringList;

public final class UnexpectedTypeError extends FoundErrorInterpret {

    private static final String TYPE = "found type ";

    private String type;

    @Override
    public String display() {
        return StringList.concat(super.display(),TYPE,SEP_KEY_VAL,type,SEP_INFO);
    }

    public String getType() {
        return type;
    }

    public void setType(String _type) {
        type = _type;
    }

    public void setType(ClassArgumentMatching _type) {
        type = _type.getName();
    }

}
