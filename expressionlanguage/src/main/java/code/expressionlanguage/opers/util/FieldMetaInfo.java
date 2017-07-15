package code.expressionlanguage.opers.util;


public final class FieldMetaInfo {
    
    private final ClassName declaringClass;
    
    private final String name;

    private final ClassName type;

    private final boolean staticField;

    private final boolean enumElement;

    public FieldMetaInfo(ClassName _declaringClass,
            String _name,
            ClassName _returnType, boolean _static, boolean _enumElement) {
        declaringClass = _declaringClass;
        name = _name;
        type = _returnType;
        staticField = _static;
        enumElement = _enumElement;
    }
//    public FieldMetaInfo(Field _method) {
//        type = new ClassName(_method.getType().getName(), false);
//        staticField = Modifier.isStatic(_method.getModifiers());
//    }
    public ClassName getDeclaringClass() {
        return declaringClass;
    }
    public String getName() {
        return name;
    }
    public boolean isStaticField() {
        return staticField;
    }
    public ClassName getType() {
        return type;
    }
    public boolean isEnumElement() {
        return enumElement;
    }

}
