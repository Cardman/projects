package code.expressionlanguage.opers.util;

import code.expressionlanguage.Templates;

public final class FieldInfo {
    private final String name;
    private final String declaringClass;
    private final String declaringBaseClass;
    private final String type;
    private final String realType;
    private final boolean staticField;
    private final boolean finalField;
    private final boolean enumField;
    public FieldInfo(String _name,String _declaringClass, String _type, String _realType,
            boolean _staticField, boolean _finalField, boolean _enumField) {
        name = _name;
        declaringClass = _declaringClass;
        declaringBaseClass = Templates.getIdFromAllTypes(_declaringClass);
        type = _type;
        realType = _realType;
        staticField = _staticField;
        finalField = _finalField;
        enumField = _enumField;
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
    public boolean isEnumField() {
        return enumField;
    }
}
