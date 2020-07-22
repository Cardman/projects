package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.inherits.AnaTemplates;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;

public final class FieldInfo {
    private final String declaringClass;
    private final String type;
    private final String realType;
    private final boolean staticField;
    private final boolean finalField;
    private final ClassField classField;
    private final Accessed accessed;
    private final int valOffset;
    private String fileName;
    private int rootNumber;
    private int memberNumber;

    private FieldInfo(String _name, String _declaringClass, String _type, String _realType,
                      boolean _staticField, boolean _finalField, Accessed _accessed, int _valOffset) {
        declaringClass = _declaringClass;
        String declaringBaseClass_ = StringExpUtil.getIdFromAllTypes(_declaringClass);
        classField = new ClassField(declaringBaseClass_, _name);
        type = _type;
        realType = _realType;
        staticField = _staticField;
        finalField = _finalField;
        accessed = _accessed;
        valOffset = _valOffset;
    }
    public static FieldInfo newFieldInfo(String _name, String _declaringClass, String _type,
                                         boolean _staticField, boolean _finalField, ContextEl _cont, boolean _aff, Accessed _accessed, int _valOffset) {
        String formattedType_ = _type;
        if (_staticField) {
            return new FieldInfo(_name, _declaringClass, formattedType_, _type, true, _finalField, _accessed, _valOffset);
        }
        if (_aff) {
            formattedType_ = AnaTemplates.wildCardFormatParam(_declaringClass, formattedType_, _cont);
        } else {
            formattedType_ = AnaTemplates.wildCardFormatReturn(_declaringClass, formattedType_, _cont);
        }
        if (formattedType_.isEmpty()) {
            return null;
        }
        return new FieldInfo(_name, _declaringClass, formattedType_, _type, false, _finalField, _accessed, _valOffset);
    }
    public static FieldInfo newFieldMetaInfo(String _name, String _declaringClass, String _type,
                                             boolean _staticField, boolean _finalField, Accessed _accessed, int _valOffset) {
        return new FieldInfo(_name, _declaringClass, _type, _type, _staticField, _finalField, _accessed, _valOffset);
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

    public int getValOffset() {
        return valOffset;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public void setRootNumber(int rootNumber) {
        this.rootNumber = rootNumber;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }
}
