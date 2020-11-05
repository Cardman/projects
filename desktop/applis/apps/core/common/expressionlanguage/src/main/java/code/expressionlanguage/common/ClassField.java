package code.expressionlanguage.common;
import code.util.core.StringUtil;

public final class ClassField {

    private final String className;

    private final String fieldName;

    public ClassField(String _className, String _fieldName) {
        className = StringUtil.nullToEmpty(_className);
        fieldName = StringUtil.nullToEmpty(_fieldName);
    }

    public boolean eq(ClassField _obj) {
        if (!StringUtil.quickEq(className, _obj.className)) {
            return false;
        }
        return StringUtil.quickEq(fieldName, _obj.fieldName);
    }

    public String getClassName() {
        return className;
    }

    public String getFieldName() {
        return fieldName;
    }
}
