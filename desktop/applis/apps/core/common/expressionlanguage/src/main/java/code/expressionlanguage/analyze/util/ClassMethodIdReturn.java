package code.expressionlanguage.analyze.util;


import code.expressionlanguage.analyze.opers.util.AnaTypeFct;
import code.expressionlanguage.analyze.opers.util.MemberId;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.stds.StandardMethod;

public final class ClassMethodIdReturn {

    private final boolean foundMethod;
    private ClassMethodId id;
    private MethodId realId;
    private String realClass;

    private String returnType;
    private String originalReturnType = "";

    private boolean abstractMethod;

    private boolean varArgToCall;
    private int ancestor;
    private String fileName;
    private MemberId memberId = new MemberId();
    private StandardMethod standardMethod;
    private AnaTypeFct pair;

    public ClassMethodIdReturn(boolean _foundMethod) {
        foundMethod = _foundMethod;
    }

    public boolean isFoundMethod() {
        return foundMethod;
    }

    public ClassMethodId getId() {
        return id;
    }

    public void setId(ClassMethodId _id) {
        id = _id;
    }

    public MethodId getRealId() {
        return realId;
    }

    public void setRealId(MethodId _realId) {
        realId = _realId;
    }

    public String getRealClass() {
        return realClass;
    }

    public void setRealClass(String _realClass) {
        realClass = _realClass;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String _returnType) {
        returnType = _returnType;
    }

    public String getOriginalReturnType() {
        return originalReturnType;
    }

    public void setOriginalReturnType(String _originalReturnType) {
        this.originalReturnType = _originalReturnType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        this.fileName = _fileName;
    }

    public boolean isAbstractMethod() {
        return abstractMethod;
    }

    public void setAbstractMethod(boolean _abstractMethod) {
        abstractMethod = _abstractMethod;
    }

    public boolean isVarArgToCall() {
        return varArgToCall;
    }

    public void setVarArgToCall(boolean _varArgToCall) {
        varArgToCall = _varArgToCall;
    }

    public int getAncestor() {
        return ancestor;
    }

    public void setAncestor(int _ancestor) {
        ancestor = _ancestor;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public void setMemberId(MemberId _memberId) {
        this.memberId = _memberId;
    }

    public StandardMethod getStandardMethod() {
        return standardMethod;
    }

    public void setStandardMethod(StandardMethod _standardMethod) {
        this.standardMethod = _standardMethod;
    }

    public AnaTypeFct getPair() {
        return pair;
    }

    public void setPair(AnaTypeFct _pair) {
        this.pair = _pair;
    }

}
