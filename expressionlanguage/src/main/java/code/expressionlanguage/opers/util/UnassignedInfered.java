package code.expressionlanguage.opers.util;

import code.expressionlanguage.methods.util.FoundErrorInterpret;
import code.util.StringList;

public final class UnassignedInfered extends FoundErrorInterpret {

    private final String finalField;

    public UnassignedInfered(String _finalField) {
        finalField = _finalField;
    }

    @Override
    public String display() {
        return StringList.concat(super.display(),SEP_INFO,finalField);
    }

}
