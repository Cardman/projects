package code.bean.nat.analyze.opers;

import code.bean.nat.SpecNatMethod;

public final class NatClassMethodIdReturn {

    private String realClass;

    private String returnType;

    private SpecNatMethod standardMethod;

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

    public SpecNatMethod getStandardMethod() {
        return standardMethod;
    }

    public void setStandardMethod(SpecNatMethod _standardMethod) {
        this.standardMethod = _standardMethod;
    }
}
