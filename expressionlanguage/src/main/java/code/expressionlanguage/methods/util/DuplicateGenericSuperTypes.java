package code.expressionlanguage.methods.util;

import code.util.StringList;

public class DuplicateGenericSuperTypes extends FoundErrorInterpret {

    private static final String CLASS_NAME = "generic super types";

    private StringList genericSuperTypes;

    @Override
    public String toString() {
        return super.toString()+CLASS_NAME+SEP_KEY_VAL+genericSuperTypes+SEP_INFO;
    }

    public StringList getGenericSuperTypes() {
        return genericSuperTypes;
    }

    public void setGenericSuperTypes(StringList _genericSuperTypes) {
        genericSuperTypes = _genericSuperTypes;
    }
}
