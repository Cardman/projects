package code.expressionlanguage.functionid;


import code.expressionlanguage.stds.StandardMethod;

public final class ClassMethodIdReturn {

    private final boolean foundMethod;
    private ClassMethodId id;
    private MethodId realId;
    private String realClass;

    private String returnType;
    private String originalReturnType = "";
    private boolean staticMethod;

    private boolean abstractMethod;

    private boolean varArgToCall;
    private int ancestor;
    private String fileName;
    private int rootNumber = -1;
    private int memberNumber = -1;
    private StandardMethod standardMethod;

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

    public void setOriginalReturnType(String originalReturnType) {
        this.originalReturnType = originalReturnType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isStaticMethod() {
        return staticMethod;
    }

    public void setStaticMethod(boolean _staticMethod) {
        staticMethod = _staticMethod;
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

    public StandardMethod getStandardMethod() {
        return standardMethod;
    }

    public void setStandardMethod(StandardMethod standardMethod) {
        this.standardMethod = standardMethod;
    }

}
