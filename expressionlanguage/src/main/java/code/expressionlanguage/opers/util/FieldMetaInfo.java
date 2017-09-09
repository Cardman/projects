package code.expressionlanguage.opers.util;


public final class FieldMetaInfo {
    private final String declaringClass;
    private final String name;

    private final String type;

    private final boolean staticField;

    private final boolean finalField;

    private final boolean enumElement;

    public FieldMetaInfo(String _declaringClass,
            String _name,
            String _returnType, boolean _static,
            boolean _finalField, boolean _enumElement) {
        declaringClass = _declaringClass;
        name = _name;
        type = _returnType;
        staticField = _static;
        finalField = _finalField;
        enumElement = _enumElement;
    }

    public String getDeclaringClass() {
        return declaringClass;
    }
    public String getName() {
        return name;
    }
    public boolean isStaticField() {
        return staticField;
    }
    public boolean isFinalField() {
        return finalField;
    }
    public String getType() {
        return type;
    }
    public boolean isEnumElement() {
        return enumElement;
    }

}
