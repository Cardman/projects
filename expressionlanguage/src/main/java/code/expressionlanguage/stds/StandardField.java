package code.expressionlanguage.stds;

import code.expressionlanguage.common.GeneField;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.methods.AccessEnum;
import code.util.StringList;

public final class StandardField implements GeneField {

    private final String fieldName;

    private final String className;

    private final boolean staticField;

    private final boolean finalField;

    private final StandardType owner;

    public StandardField(String _fieldName, String _className,
            boolean _staticField, boolean _finalField, StandardType _owner) {
        fieldName = _fieldName;
        className = _className;
        staticField = _staticField;
        finalField = _finalField;
        owner = _owner;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public boolean isStaticField() {
        return staticField;
    }

    @Override
    public boolean isFinalField() {
        return finalField;
    }

    @Override
    public AccessEnum getAccess() {
        return AccessEnum.PUBLIC;
    }

    public String getPrettyString(String _className) {
        return StringList.concat(_className," ",className," ",fieldName);
    }

    @Override
    public GeneType belong() {
        return owner;
    }
}
