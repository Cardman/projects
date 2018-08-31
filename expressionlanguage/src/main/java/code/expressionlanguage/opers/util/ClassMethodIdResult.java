package code.expressionlanguage.opers.util;

import code.util.CustList;

public class ClassMethodIdResult {

    private ClassMethodId id;
    private MethodId realId;
    private String realClass;

    private SearchingMemberStatus status;

    private CustList<MethodInfo> methods;

    private boolean varArgToCall;

    private boolean correctTemplated;

    private String returnType;
    private int ancestor;
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

    public SearchingMemberStatus getStatus() {
        return status;
    }

    public void setStatus(SearchingMemberStatus _status) {
        status = _status;
    }

    public CustList<MethodInfo> getMethods() {
        return methods;
    }

    public void setMethods(CustList<MethodInfo> _methods) {
        methods = _methods;
    }

    public boolean isVarArgToCall() {
        return varArgToCall;
    }

    public void setVarArgToCall(boolean _varArgToCall) {
        varArgToCall = _varArgToCall;
    }

    public boolean isCorrectTemplated() {
        return correctTemplated;
    }

    public void setCorrectTemplated(boolean _correctTemplated) {
        correctTemplated = _correctTemplated;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String _returnType) {
        returnType = _returnType;
    }

    public int getAncestor() {
        return ancestor;
    }

    public void setAncestor(int _ancestor) {
        ancestor = _ancestor;
    }

}
