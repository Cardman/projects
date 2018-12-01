package code.expressionlanguage.errors.custom;

import code.expressionlanguage.opers.util.ConstructorId;
import code.util.StringList;

public class BadAccessConstructor extends FoundErrorInterpret {

    private static final String CLASS_NAME = "bad access constructor";

    private ConstructorId id;

    @Override
    public String display() {
        return StringList.concat(super.display(),CLASS_NAME,SEP_KEY_VAL,id.getSignature(),SEP_INFO);
    }

    public ConstructorId getId() {
        return id;
    }

    public void setId(ConstructorId _id) {
        id = _id;
    }
}
