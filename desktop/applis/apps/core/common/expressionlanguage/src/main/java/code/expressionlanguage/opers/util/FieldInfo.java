package code.expressionlanguage.opers.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.GeneField;
import code.expressionlanguage.inherits.Templates;

public final class FieldInfo {
    private final String declaringClass;
    private final String type;
    private final String realType;
    private final boolean staticField;
    private final boolean finalField;
    private final ClassField classField;
    private final GeneField geneField;
    private FieldInfo(String _name, String _declaringClass, String _type, String _realType,
                      boolean _staticField, boolean _finalField, GeneField _geneField) {
        declaringClass = _declaringClass;
        String declaringBaseClass_ = Templates.getIdFromAllTypes(_declaringClass);
        classField = new ClassField(declaringBaseClass_, _name);
        type = _type;
        realType = _realType;
        staticField = _staticField;
        finalField = _finalField;
        geneField = _geneField;
    }
    public static FieldInfo newFieldInfo(String _name, String _declaringClass, String _type,
                                         boolean _staticField, boolean _finalField, ContextEl _cont, boolean _aff, GeneField _geneField) {
        String formattedType_ = _type;
        if (_aff) {
            formattedType_ = Templates.wildCardFormatParam(_declaringClass, formattedType_, _cont);
        } else {
            formattedType_ = Templates.wildCardFormatReturn(_declaringClass, formattedType_, _cont);
        }
        if (formattedType_.isEmpty()) {
            return null;
        }
        return new FieldInfo(_name, _declaringClass, formattedType_, _type, _staticField, _finalField, _geneField);
    }
    public static FieldInfo newFieldMetaInfo(String _name, String _declaringClass, String _type,
                                             boolean _staticField, boolean _finalField, GeneField _geneField) {
        return new FieldInfo(_name, _declaringClass, _type, _type, _staticField, _finalField, _geneField);
    }

    public GeneField getGeneField() {
        return geneField;
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
