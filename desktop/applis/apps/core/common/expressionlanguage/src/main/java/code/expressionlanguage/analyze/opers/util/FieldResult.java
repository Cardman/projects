package code.expressionlanguage.analyze.opers.util;


import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.fwd.opers.AnaSettableOperationContent;

public final class FieldResult {

    private SearchingMemberStatus status;
    private final AnaSettableOperationContent content = new AnaSettableOperationContent();
    private String type = "";
    private AnaFormattedRootBlock formattedType = AnaFormattedRootBlock.defValue();
    private String fileName = "";
    private MemberId memberId = new MemberId();
    private CstFieldInfo cstFieldInfo;

    public AnaSettableOperationContent getContent() {
        return content;
    }

    public AnaFormattedRootBlock getFormattedType() {
        return formattedType;
    }

    public void setFormattedType(AnaFormattedRootBlock _formattedType) {
        this.formattedType = _formattedType;
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

    public CstFieldInfo getCstFieldInfo() {
        return cstFieldInfo;
    }

    public void setCstFieldInfo(CstFieldInfo _c) {
        this.cstFieldInfo = _c;
    }
}
