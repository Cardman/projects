package code.bean.nat.analyze.opers;
import code.expressionlanguage.stds.StandardMethod;

public final class NatMethodInfo {

    private String className = "";

    private String returnType = "";

    private StandardMethod standardMethod;

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

    public StandardMethod getStandardMethod() {
        return standardMethod;
    }

    public void setStandardMethod(StandardMethod _standardMethod) {
        this.standardMethod = _standardMethod;
    }

}
