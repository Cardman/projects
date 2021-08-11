package code.bean.nat.analyze.opers;
import code.expressionlanguage.functionid.*;
import code.expressionlanguage.stds.StandardMethod;
import code.util.StringList;

public final class NatMethodInfo {

    private MethodId constraints;
    private MethodId formatted;

    private String className = "";

    private String returnType = "";
    private String originalReturnType = "";

    private int ancestor;

    private StandardMethod standardMethod;

    public MethodId getConstraints() {
        return constraints;
    }

    public void classMethodId(String _className, MethodId _id) {
        className = _className;
        constraints = _id;
    }

    public void types(String _originalReturnType) {
        originalReturnType = _originalReturnType;
        returnType = _originalReturnType;
    }
    public String getReturnType() {
        return returnType;
    }

    public String getOriginalReturnType() {
        return originalReturnType;
    }

    public String getClassName() {
        return className;
    }

    public int getAncestor() {
        return ancestor;
    }

    public void setAncestor(int _ancestor) {
        ancestor = _ancestor;
    }

    public void format(boolean _keepParams) {
        StringList params_ = new StringList();
        int nbParams_ = constraints.getParametersTypesLength();
        for (int i = 0; i < nbParams_; i++) {
            params_.add(constraints.getParametersType(i));
        }
        formatted = buildFormatted(MethodId.getKind(_keepParams), params_, constraints);
    }

    private static MethodId buildFormatted(MethodAccessKind _keepParams, StringList _params, MethodId _id) {
        return MethodId.to(_keepParams, _params, _id);
    }

    public MethodId getFormatted() {
        return formatted;
    }

    public StandardMethod getStandardMethod() {
        return standardMethod;
    }

    public void setStandardMethod(StandardMethod _standardMethod) {
        this.standardMethod = _standardMethod;
    }

}
