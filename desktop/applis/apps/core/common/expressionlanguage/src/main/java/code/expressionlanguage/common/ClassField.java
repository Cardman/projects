package code.expressionlanguage.common;
import code.util.StringList;
import code.util.ints.Equallable;

public final class ClassField implements Equallable<ClassField> {

    private final String className;

    private final String fieldName;

    public ClassField(String _className, String _fieldName) {
        className = _className;
        fieldName = _fieldName;
    }

    @Override
    public boolean eq(ClassField _obj) {
        if (!StringList.quickEq(className, _obj.className)) {
            return false;
        }
        return StringList.quickEq(fieldName, _obj.fieldName);
    }

    public String getClassName() {
        return className;
    }

    public String getFieldName() {
        return fieldName;
    }
}
