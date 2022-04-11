package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.stds.StandardMethod;

public final class ImportedMethod {
    private final String returnType;
    private String fileName="";
    private final ClassMethodId id;
    private final MemberId memberId = new MemberId();
    private StandardMethod standardMethod;
    private RootBlock type;
    private AnaGeneType owner;
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

    public MemberId getMemberId() {
        return memberId;
    }
    public void memberId(int _rootNumber,int _memberNumber) {
        memberId.setRootNumber(_rootNumber);
        memberId.setMemberNumber(_memberNumber);
    }

    public StandardMethod getStandardMethod() {
        return standardMethod;
    }

    public void setStandardMethod(StandardMethod _standardMethod) {
        this.standardMethod = _standardMethod;
    }

    public RootBlock getType() {
        return type;
    }

    public void setType(RootBlock _type) {
        type = _type;
    }

    public NamedFunctionBlock getCustMethod() {
        return custMethod;
    }

    public void setCustMethod(NamedFunctionBlock _custMethod) {
        this.custMethod = _custMethod;
    }

    public AnaGeneType getOwner() {
        return owner;
    }

    public void setOwner(AnaGeneType _own) {
        this.owner = _own;
    }
}
