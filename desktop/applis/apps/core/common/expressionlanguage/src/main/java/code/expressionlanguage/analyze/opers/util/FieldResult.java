package code.expressionlanguage.analyze.opers.util;


import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.common.ClassField;

public final class FieldResult {

    private SearchingMemberStatus status;
    private String declaringClass;
    private String realType;
    private String type;
    private boolean staticField;
    private boolean finalField;
    private RootBlock fieldType;
    private ClassField classField;
    private int valOffset;
    private int anc;
    private String fileName;
    private MemberId memberId = new MemberId();

    public int getValOffset() {
        return valOffset;
    }

    public void setValOffset(int _valOffset) {
        valOffset = _valOffset;
    }

    public ClassField getClassField() {
        return classField;
    }

    public void setClassField(ClassField _classField) {
        this.classField = _classField;
    }

    public String getDeclaringClass() {
        return declaringClass;
    }

    public void setDeclaringClass(String _declaringClass) {
        this.declaringClass = _declaringClass;
    }

    public boolean isStaticField() {
        return staticField;
    }

    public void setStaticField(boolean _staticField) {
        this.staticField = _staticField;
    }

    public boolean isFinalField() {
        return finalField;
    }

    public void setFinalField(boolean _finalField) {
        this.finalField = _finalField;
    }

    public String getRealType() {
        return realType;
    }

    public void setRealType(String _realType) {
        this.realType = _realType;
    }

    public RootBlock getFieldType() {
        return fieldType;
    }

    public void setFieldType(RootBlock _fieldType) {
        fieldType = _fieldType;
    }

    public String getType() {
        return type;
    }

    public void setType(String _type) {
        type = _type;
    }

    public SearchingMemberStatus getStatus() {
        return status;
    }

    public void setStatus(SearchingMemberStatus _status) {
        status = _status;
    }

    public int getAnc() {
        return anc;
    }

    public void setAnc(int _anc) {
        anc = _anc;
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
        this.memberId = _memberId;
    }

}
