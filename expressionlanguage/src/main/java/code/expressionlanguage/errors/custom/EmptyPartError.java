package code.expressionlanguage.errors.custom;

import code.util.StringList;

public final class EmptyPartError extends FoundErrorInterpret {

    private static final String MESSAGE = "found empty part";

    @Override
    public String display() {
        return StringList.concat(super.display(),SEP_INFO,MESSAGE);
    }
}
