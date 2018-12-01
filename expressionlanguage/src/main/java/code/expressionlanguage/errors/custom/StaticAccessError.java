package code.expressionlanguage.errors.custom;

import code.util.StringList;

public final class StaticAccessError extends FoundErrorInterpret {

    private static final String CLASS_NAME = "no class context";

    @Override
    public String display() {
        return StringList.concat(super.display(),SEP_INFO,CLASS_NAME);
    }
}
