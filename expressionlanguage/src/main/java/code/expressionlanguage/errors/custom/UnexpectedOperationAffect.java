package code.expressionlanguage.errors.custom;

import code.util.StringList;

public final class UnexpectedOperationAffect extends FoundErrorInterpret {

    private static final String MESSAGE = "unexpected operation affect";

    @Override
    public String display() {
        return StringList.concat(super.display(),SEP_INFO,MESSAGE);
    }
}
