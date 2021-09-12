package code.bean.nat.analyze.opers;
import code.bean.nat.SpecNatMethod;

public final class NatMethodInfo {

    private String className = "";

    private String returnType = "";

    private SpecNatMethod standardMethod;

    public void classMethodId(String _className) {
        className = _className;
    }

    public void types(String _originalReturnType) {
        returnType = _originalReturnType;
    }
    public String getReturnType() {
        return returnType;
    }

    public String getClassName() {
        return className;
    }

    public SpecNatMethod getStandardMethod() {
        return standardMethod;
    }

    public void setStandardMethod(SpecNatMethod _standardMethod) {
        this.standardMethod = _standardMethod;
    }

}
