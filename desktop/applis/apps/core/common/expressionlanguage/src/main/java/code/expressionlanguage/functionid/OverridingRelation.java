package code.expressionlanguage.functionid;


import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;

public final class OverridingRelation {

    private RootBlock sub;
    private ClassMethodId subMethod;

    private RootBlock sup;
    private ClassMethodId supMethod;

    public RootBlock getSub() {
        return sub;
    }

    public void setSub(RootBlock sub) {
        this.sub = sub;
    }

    public ClassMethodId getSubMethod() {
        return subMethod;
    }

    public void setSubMethod(ClassMethodId _subMethod) {
        subMethod = _subMethod;
    }

    public RootBlock getSup() {
        return sup;
    }

    public void setSup(RootBlock sup) {
        this.sup = sup;
    }

    public ClassMethodId getSupMethod() {
        return supMethod;
    }

    public void setSupMethod(ClassMethodId _supMethod) {
        supMethod = _supMethod;
    }

}
