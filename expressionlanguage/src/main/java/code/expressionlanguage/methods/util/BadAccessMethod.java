package code.expressionlanguage.methods.util;

import code.expressionlanguage.opers.util.MethodId;
import code.util.StringList;

public class BadAccessMethod extends FoundErrorInterpret {

    private static final String CLASS_NAME = "bad access method";

    private MethodId id;

    @Override
    public String display() {
        return StringList.concat(super.display(),CLASS_NAME,SEP_KEY_VAL,id.getSignature(),SEP_INFO);
    }

    public MethodId getId() {
        return id;
    }

    public void setId(MethodId _id) {
        id = _id;
    }
}
