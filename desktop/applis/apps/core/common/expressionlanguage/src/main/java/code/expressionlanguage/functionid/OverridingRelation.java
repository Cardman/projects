package code.expressionlanguage.functionid;


import code.expressionlanguage.exec.blocks.ExecRootBlock;

public final class OverridingRelation {

    private ExecRootBlock sub;
    private ClassMethodId subMethod;

    private ExecRootBlock sup;
    private ClassMethodId supMethod;

    public ExecRootBlock getSub() {
        return sub;
    }

    public void setSub(ExecRootBlock sub) {
        this.sub = sub;
    }

    public ClassMethodId getSubMethod() {
        return subMethod;
    }

    public void setSubMethod(ClassMethodId _subMethod) {
        subMethod = _subMethod;
    }

    public ExecRootBlock getSup() {
        return sup;
    }

    public void setSup(ExecRootBlock sup) {
        this.sup = sup;
    }

    public ClassMethodId getSupMethod() {
        return supMethod;
    }

    public void setSupMethod(ClassMethodId _supMethod) {
        supMethod = _supMethod;
    }

}
