package code.expressionlanguage.analyze.opers.util;


public class FieldResult {

    private FieldInfo id;

    private SearchingMemberStatus status;

    private int anc;
    private String fileName;
    private int rootNumber = -1;
    private int memberNumber = -1;
    public FieldInfo getId() {
        return id;
    }

    public void setId(FieldInfo _id) {
        id = _id;
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
