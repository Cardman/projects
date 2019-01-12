package code.expressionlanguage.errors.custom;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.ConstructorId;
import code.util.StringList;

public final class DuplicateConstructor extends FoundErrorInterpret {

    private static final String CLASS_NAME = "duplicate method";

    private ConstructorId id;

    @Override
    public String display(Classes _classes) {
        return StringList.concat(super.display(_classes),CLASS_NAME,SEP_KEY_VAL,id.getSignature(),SEP_INFO);
    }

    public void setId(ConstructorId _id) {
        id = _id;
    }

}
