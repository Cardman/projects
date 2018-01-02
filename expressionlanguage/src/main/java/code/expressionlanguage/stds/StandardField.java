package code.expressionlanguage.stds;

import code.expressionlanguage.methods.AccessEnum;
import code.util.StringList;

public final class StandardField {

    private final String fieldName;

    private final String className;

    private final boolean staticField;

    private final boolean finalField;

    private final AccessEnum access = AccessEnum.PUBLIC;

    public StandardField(String _fieldName, String _className,
            boolean _staticField, boolean _finalField) {
        fieldName = _fieldName;
        className = _className;
        staticField = _staticField;
        finalField = _finalField;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getClassName() {
        return className;
    }

    public boolean isStaticField() {
        return staticField;
    }

    public boolean isFinalField() {
        return finalField;
    }

    public AccessEnum getAccess() {
        return access;
    }

    public String getPrettyString(String _className) {
        return StringList.concat(_className," ",className," ",fieldName);
    }
}
