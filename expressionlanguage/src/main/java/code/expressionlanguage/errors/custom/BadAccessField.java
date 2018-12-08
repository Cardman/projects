package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.ClassField;
import code.util.StringList;

public class BadAccessField extends FoundErrorInterpret {

    private static final String CLASS_NAME = "bad access field";

    private ClassField id;

    @Override
    public String display(Classes _classes) {
        return StringList.concat(super.display(_classes),CLASS_NAME,SEP_KEY_VAL,id.getClassName(),".",id.getFieldName(),SEP_INFO);
    }

    public ClassField getId() {
        return id;
    }

    public void setId(ClassField _id) {
        id = _id;
    }
}
