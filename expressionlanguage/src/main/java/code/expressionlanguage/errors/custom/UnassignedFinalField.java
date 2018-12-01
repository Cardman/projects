package code.expressionlanguage.errors.custom;

import code.expressionlanguage.opers.util.ClassField;
import code.util.StringList;

public final class UnassignedFinalField extends FoundErrorInterpret {

    private final ClassField finalField;

    public UnassignedFinalField(ClassField _finalField) {
        finalField = _finalField;
    }

    @Override
    public String display() {
        return StringList.concat(super.display(),SEP_INFO,finalField.getClassName(),SEP_INFO,finalField.getFieldName());
    }

}
