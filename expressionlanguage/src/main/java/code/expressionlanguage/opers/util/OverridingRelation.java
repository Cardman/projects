package code.expressionlanguage.opers.util;

public final class OverridingRelation {

    private MethodId realId;

    private ClassMethodId subMethod;

    private ClassMethodId supMethod;

    public MethodId getRealId() {
        return realId;
    }

    public void setRealId(MethodId _realId) {
        realId = _realId;
    }

    public ClassMethodId getSubMethod() {
        return subMethod;
    }

    public void setSubMethod(ClassMethodId _subMethod) {
        subMethod = _subMethod;
    }

    public ClassMethodId getSupMethod() {
        return supMethod;
    }

    public void setSupMethod(ClassMethodId _supMethod) {
        supMethod = _supMethod;
    }
}
