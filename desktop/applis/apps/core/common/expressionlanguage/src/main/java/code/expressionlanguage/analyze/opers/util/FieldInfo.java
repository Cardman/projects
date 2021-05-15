package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.accessing.Accessed;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.inherits.AnaInherits;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.common.ClassField;

public final class FieldInfo {
    private final String type;
    private final boolean staticField;
    private final boolean finalField;
    private final ClassField classField;
    private final Accessed accessed;
    private final int valOffset;
    private final String fileName;
    private final RootBlock fieldType;
    private final MemberId memberId = new MemberId();

    private FieldInfo(ClassField _id, String _type,
                      boolean _staticField, boolean _finalField, Accessed _accessed, int _valOffset, String _fileName) {
        classField = _id;
        type = _type;
        staticField = _staticField;
        finalField = _finalField;
        accessed = _accessed;
        valOffset = _valOffset;
        fieldType = _accessed.getType();
        fileName = _fileName;
    }
    public static String newFieldInfo(String _declaringClass, String _type,
                                      boolean _staticField, boolean _aff, AnalyzedPageEl _page) {
        String formattedType_ = _type;
        if (_staticField) {
            return formattedType_;
        }
        if (_aff) {
            formattedType_ = AnaInherits.wildCardFormatParam(_declaringClass, formattedType_, _page);
        } else {
            formattedType_ = AnaInherits.wildCardFormatReturn(_declaringClass, formattedType_, _page);
        }
        return formattedType_;
    }

    public static FieldInfo newFieldMetaInfo(ClassField _id, String _type,
                                             boolean _staticField, boolean _finalField, Accessed _accessed, int _valOffset, String _fileName) {
        return new FieldInfo(_id, _type, _staticField, _finalField, _accessed, _valOffset,_fileName);
    }

    public AnaFormattedRootBlock buildFormatted(String _formatted) {
        return new AnaFormattedRootBlock(fieldType,_formatted);
    }
    public ClassField getClassField() {
        return classField;
    }

    public String getType() {
        return type;
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

    public Accessed getAccessed() {
        return accessed;
    }

    public int getValOffset() {
        return valOffset;
    }

    public String getFileName() {
        return fileName;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public void memberId(int _rootNumber, int _memberNumber) {
        memberId.setRootNumber(_rootNumber);
        memberId.setMemberNumber(_memberNumber);
    }

}
