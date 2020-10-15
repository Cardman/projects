package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.stds.StandardMethod;

public final class ImportedMethod {
    private String returnType;
    private String fileName="";
    private ClassMethodId id;
    private int rootNumber;
    private int memberNumber;
    private StandardMethod standardMethod;
    private NamedFunctionBlock custMethod;

    public ImportedMethod(String _returnType, ClassMethodId _id) {
        this.returnType = _returnType;
        this.id = _id;
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

    public void setFileName(String _fileName) {
        this.fileName = _fileName;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int _memberNumber) {
        this.memberNumber = _memberNumber;
    }

    public int getRootNumber() {
        return rootNumber;
    }

    public void setRootNumber(int _rootNumber) {
        this.rootNumber = _rootNumber;
    }

    public StandardMethod getStandardMethod() {
        return standardMethod;
    }

    public void setStandardMethod(StandardMethod _standardMethod) {
        this.standardMethod = _standardMethod;
    }

    public NamedFunctionBlock getCustMethod() {
        return custMethod;
    }

    public void setCustMethod(NamedFunctionBlock _custMethod) {
        this.custMethod = _custMethod;
    }
}
