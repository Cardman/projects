package code.expressionlanguage.opers.util;

import code.util.CustList;

public class ClassMethodIdResult {

    private ClassMethodId id;

    private SearchingMemberStatus status;

    private CustList<MethodInfo> methods;

    private boolean interf;

    public ClassMethodId getId() {
        return id;
    }

    public void setId(ClassMethodId _id) {
        id = _id;
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

    public boolean isInterf() {
        return interf;
    }

    public void setInterf(boolean _interf) {
        interf = _interf;
    }
}
