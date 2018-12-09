package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.util.StringList;

public final class UnexpectedOperationAffect extends FoundErrorInterpret {

    private static final String MESSAGE = "unexpected operation affect";

    @Override
    public String display(Classes _classes) {
        return StringList.concat(super.display(_classes),SEP_INFO,MESSAGE);
    }
}
