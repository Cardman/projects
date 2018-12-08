package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.util.StringList;

public final class StaticAccessError extends FoundErrorInterpret {

    private static final String CLASS_NAME = "no class context";

    @Override
    public String display(Classes _classes) {
        return StringList.concat(super.display(_classes),SEP_INFO,CLASS_NAME);
    }
}
