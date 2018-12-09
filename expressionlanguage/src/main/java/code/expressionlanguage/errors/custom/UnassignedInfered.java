package code.expressionlanguage.errors.custom;

import code.expressionlanguage.methods.Classes;
import code.util.StringList;

public final class UnassignedInfered extends FoundErrorInterpret {

    private final String finalField;

    public UnassignedInfered(String _finalField) {
        finalField = _finalField;
    }

    @Override
    public String display(Classes _classes) {
        return StringList.concat(super.display(_classes),SEP_INFO,finalField);
    }

}
