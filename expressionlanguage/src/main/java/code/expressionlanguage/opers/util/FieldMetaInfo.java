package code.expressionlanguage.opers.util;


public final class FieldMetaInfo {
    private final ClassName declaringClass;
    private final String name;

    private final ClassName type;

    private final boolean staticField;

    private final boolean finalField;

    private final boolean enumElement;

    public FieldMetaInfo(ClassName _declaringClass,
            String _name,
            ClassName _returnType, boolean _static,
            boolean _finalField, boolean _enumElement) {
        declaringClass = _declaringClass;
        name = _name;
        type = _returnType;
        staticField = _static;
        finalField = _finalField;
        enumElement = _enumElement;
    }

    public ClassName getDeclaringClass() {
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
    public ClassName getType() {
        return type;
    }
    public boolean isEnumElement() {
        return enumElement;
    }

}
