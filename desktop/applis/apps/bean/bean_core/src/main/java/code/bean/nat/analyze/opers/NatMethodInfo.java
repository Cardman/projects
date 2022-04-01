package code.bean.nat.analyze.opers;
import code.bean.nat.SpecNatMethod;

public final class NatMethodInfo {

    private String returnType = "";

    private SpecNatMethod standardMethod;

    public void types(String _originalReturnType) {
        returnType = _originalReturnType;
    }
    public String getReturnType() {
        return returnType;
    }

    public SpecNatMethod getStandardMethod() {
        return standardMethod;
    }

    public void setStandardMethod(SpecNatMethod _standardMethod) {
        this.standardMethod = _standardMethod;
    }

}
