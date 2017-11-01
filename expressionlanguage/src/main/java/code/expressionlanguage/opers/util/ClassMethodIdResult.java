package code.expressionlanguage.opers.util;

import java.lang.reflect.Method;

import code.util.CustList;

public class ClassMethodIdResult {

    private ClassMethodId id;
    private MethodId realId;
    private String realClass;

    private Method method;

    private SearchingMemberStatus status;

    private CustList<MethodInfo> methods;

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
