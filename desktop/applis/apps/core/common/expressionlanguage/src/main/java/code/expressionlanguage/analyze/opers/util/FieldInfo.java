package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.blocks.RootBlock;
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
    private RootBlock fieldType;
    private MemberId memberId = new MemberId();

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
                                         boolean _staticField, boolean _finalField, boolean _aff, Accessed _accessed, int _valOffset, AnalyzedPageEl _page) {
        String formattedType_ = _type;
        if (_staticField) {
            return new FieldInfo(_name, _declaringClass, formattedType_, _type, true, _finalField, _accessed, _valOffset);
        }
        if (_aff) {
            formattedType_ = AnaTemplates.wildCardFormatParam(_declaringClass, formattedType_, _page);
        } else {
            formattedType_ = AnaTemplates.wildCardFormatReturn(_declaringClass, formattedType_, _page);
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

    public RootBlock getFieldType() {
        return fieldType;
    }

    public void setFieldType(RootBlock _fieldType) {
        fieldType = _fieldType;
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

    public void setFileName(String _fileName) {
        this.fileName = _fileName;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public void setMemberId(MemberId _memberId) {
        memberId = _memberId;
    }

    public void memberId(int _rootNumber, int _memberNumber) {
        memberId.setRootNumber(_rootNumber);
        memberId.setMemberNumber(_memberNumber);
    }

}
