package code.expressionlanguage.opers.util;

import java.lang.reflect.Method;

import code.util.CustList;

public class ClassMethodIdResult {

    private ClassMethodId id;

    private Method method;

    private SearchingMemberStatus status;

    private CustList<MethodInfo> methods;

    public ClassMethodId getId() {
        return id;
    }

    public void setId(ClassMethodId _id) {
        id = _id;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method _method) {
        method = _method;
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
}
