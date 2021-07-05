package code.expressionlanguage.analyze.opers.util;


import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.fwd.opers.AnaSettableOperationContent;

public final class FieldResult {

    private SearchingMemberStatus status;
    private String declaringClass;
    private final AnaSettableOperationContent content = new AnaSettableOperationContent();
    private String type;
    private AnaFormattedRootBlock formattedType;
    private RootBlock fieldType;
    private int valOffset;
    private String fileName;
    private MemberId memberId = new MemberId();

    public int getValOffset() {
        return valOffset;
    }

    public void setValOffset(int _valOffset) {
        valOffset = _valOffset;
    }

    public AnaSettableOperationContent getContent() {
        return content;
    }

    public String getDeclaringClass() {
        return declaringClass;
    }

    public void setDeclaringClass(String _declaringClass) {
        this.declaringClass = _declaringClass;
    }

    public AnaFormattedRootBlock getFormattedType() {
        return formattedType;
    }

    public void setFormattedType(AnaFormattedRootBlock _formattedType) {
        this.formattedType = _formattedType;
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
