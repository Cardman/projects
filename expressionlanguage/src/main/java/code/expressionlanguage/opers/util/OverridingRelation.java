package code.expressionlanguage.opers.util;

public final class OverridingRelation {

    private ClassMethodId subMethod;

    private ClassMethodId supMethod;

    private boolean base;

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

    public boolean isBase() {
        return base;
    }

    public void setBase(boolean _base) {
        base = _base;
    }
}
