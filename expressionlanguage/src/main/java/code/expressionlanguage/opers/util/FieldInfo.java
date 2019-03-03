package code.expressionlanguage.opers.util;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.inherits.Templates;

public final class FieldInfo {
    private final String declaringClass;
    private final String type;
    private final String realType;
    private final boolean staticField;
    private final boolean finalField;
    private final ClassField classField;
    private FieldInfo(String _name,String _declaringClass, String _type, String _realType,
            boolean _staticField, boolean _finalField) {
        declaringClass = _declaringClass;
        String declaringBaseClass_ = Templates.getIdFromAllTypes(_declaringClass);
        classField = new ClassField(declaringBaseClass_, _name);
        type = _type;
        realType = _realType;
        staticField = _staticField;
        finalField = _finalField;
    }
    public static FieldInfo newFieldInfo(String _name, String _declaringClass, String _type,
                                         boolean _staticField, boolean _finalField, Analyzable _cont, boolean _aff) {
        String formattedType_ = _type;
        if (_aff) {
            formattedType_ = Templates.wildCardFormatParam(_staticField,_declaringClass, formattedType_, _cont);
        } else {
            formattedType_ = Templates.wildCardFormatReturn(_staticField,_declaringClass, formattedType_, _cont);
        }
        if (formattedType_ == null) {
            return null;
        }
        return new FieldInfo(_name, _declaringClass, formattedType_, _type, _staticField, _finalField);
    }
    public static FieldInfo newFieldMetaInfo(String _name, String _declaringClass, String _type,
                                             boolean _staticField, boolean _finalField) {
        return new FieldInfo(_name, _declaringClass, _type, _type, _staticField, _finalField);
    }
    public ClassField getClassField() {
        return classField;
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
