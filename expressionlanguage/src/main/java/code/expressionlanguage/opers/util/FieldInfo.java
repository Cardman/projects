package code.expressionlanguage.opers.util;

import code.util.StringList;

public final class FieldInfo {
    private final String name;
    private final String declaringClass;
    private final String declaringBaseClass;
    private final String type;
    private final String realType;
    private final boolean staticField;
    private final boolean finalField;
    public FieldInfo(String _name,String _declaringClass, String _type, String _realType,
            boolean _staticField, boolean _finalField) {
        name = _name;
        declaringClass = _declaringClass;
        declaringBaseClass = StringList.getAllTypes(_declaringClass).first();
        type = _type;
        realType = _realType;
        staticField = _staticField;
        finalField = _finalField;
    }
    public String getName() {
        return name;
    }
    public String getDeclaringBaseClass() {
        return declaringBaseClass;
    }
    public String getDeclaringClass() {
        return declaringClass;
    }
    public String getType() {
        return type;
    }
    public String getRealType() {
        return realType;
    }
    public boolean isStaticField() {
        return staticField;
    }
    public boolean isFinalField() {
        return finalField;
    }
}
