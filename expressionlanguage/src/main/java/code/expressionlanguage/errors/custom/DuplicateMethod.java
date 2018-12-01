package code.expressionlanguage.errors.custom;
import code.expressionlanguage.opers.util.MethodId;
import code.util.StringList;

public final class DuplicateMethod extends FoundErrorInterpret {

    private static final String CLASS_NAME = "duplicate method";

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
