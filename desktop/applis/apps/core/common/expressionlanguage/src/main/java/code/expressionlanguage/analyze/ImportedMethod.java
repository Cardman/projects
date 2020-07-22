package code.expressionlanguage.analyze;

import code.expressionlanguage.functionid.ClassMethodId;

public final class ImportedMethod {
    private String returnType;
    private String fileName="";
    private ClassMethodId id;
    private int rootNumber;
    private int memberNumber;

    public ImportedMethod(String returnType, ClassMethodId id) {
        this.returnType = returnType;
        this.id = id;
    }

    public String getReturnType() {
        return returnType;
    }

    public ClassMethodId getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public void setRootNumber(int rootNumber) {
        this.rootNumber = rootNumber;
    }
}
