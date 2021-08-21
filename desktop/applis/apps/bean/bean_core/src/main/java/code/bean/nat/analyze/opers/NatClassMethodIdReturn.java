package code.bean.nat.analyze.opers;

import code.expressionlanguage.stds.StandardMethod;

public final class NatClassMethodIdReturn {

    private String realClass;

    private String returnType;

    private StandardMethod standardMethod;

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

    public StandardMethod getStandardMethod() {
        return standardMethod;
    }

    public void setStandardMethod(StandardMethod _standardMethod) {
        this.standardMethod = _standardMethod;
    }
}
