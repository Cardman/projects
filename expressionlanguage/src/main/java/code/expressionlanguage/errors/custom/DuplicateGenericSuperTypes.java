package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.util.StringList;

public class DuplicateGenericSuperTypes extends FoundErrorInterpret {

    private static final String CLASS_NAME = "generic super types";

    private StringList genericSuperTypes;

    @Override
    public String display(Classes _classes) {
        StringBuilder str_ = new StringBuilder(super.display(_classes));
        str_.append(CLASS_NAME);
        str_.append(SEP_KEY_VAL);
        str_.append(genericSuperTypes.display());
        str_.append(SEP_INFO);
        return str_.toString();
    }

    public StringList getGenericSuperTypes() {
        return genericSuperTypes;
    }

    public void setGenericSuperTypes(StringList _genericSuperTypes) {
        genericSuperTypes = _genericSuperTypes;
    }
}
