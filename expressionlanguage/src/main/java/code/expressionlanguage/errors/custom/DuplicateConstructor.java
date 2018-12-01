package code.expressionlanguage.errors.custom;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.StringList;

public final class DuplicateConstructor extends FoundErrorInterpret {

    private static final String CLASS_NAME = "duplicate method";

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
