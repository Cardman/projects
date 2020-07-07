package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.util.Ints;

public final class FieldInfo {
    private final String declaringClass;
    private final String type;
    private final String realType;
    private final boolean staticField;
    private final boolean finalField;
    private final ClassField classField;
    private final Accessed accessed;
    private final int valOffset;
    private final Ints valueOffset;
    private FieldInfo(String _name, String _declaringClass, String _type, String _realType,
                      boolean _staticField, boolean _finalField, Accessed _accessed, int _valOffset,Ints _valueOffset) {
        declaringClass = _declaringClass;
        String declaringBaseClass_ = StringExpUtil.getIdFromAllTypes(_declaringClass);
        classField = new ClassField(declaringBaseClass_, _name);
        type = _type;
        realType = _realType;
        staticField = _staticField;
        finalField = _finalField;
        accessed = _accessed;
        valOffset = _valOffset;
        valueOffset = _valueOffset;
    }
    public static FieldInfo newFieldInfo(String _name, String _declaringClass, String _type,
                                         boolean _staticField, boolean _finalField, ContextEl _cont, boolean _aff, Accessed _accessed, int _valOffset,Ints _valueOffset) {
        String formattedType_ = _type;
        if (_staticField) {
            return new FieldInfo(_name, _declaringClass, formattedType_, _type, true, _finalField, _accessed, _valOffset,_valueOffset);
        }
        if (_aff) {
            formattedType_ = AnaTemplates.wildCardFormatParam(_declaringClass, formattedType_, _cont);
        } else {
            formattedType_ = AnaTemplates.wildCardFormatReturn(_declaringClass, formattedType_, _cont);
        }
        if (formattedType_.isEmpty()) {
            return null;
        }
        return new FieldInfo(_name, _declaringClass, formattedType_, _type, false, _finalField, _accessed, _valOffset,_valueOffset);
    }
    public static FieldInfo newFieldMetaInfo(String _name, String _declaringClass, String _type,
                                             boolean _staticField, boolean _finalField, Accessed _accessed, int _valOffset,Ints _valueOffset) {
        return new FieldInfo(_name, _declaringClass, _type, _type, _staticField, _finalField, _accessed, _valOffset,_valueOffset);
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

    public Accessed getAccessed() {
        return accessed;
    }

    public Ints getValueOffset() {
        return valueOffset;
    }

    public int getValOffset() {
        return valOffset;
    }
}
