package code.expressionlanguage.analyze.types;


import code.expressionlanguage.analyze.blocks.RootBlock;

public final class OverridingRelation {

    private RootBlock sub;
    private GeneStringOverridable subMethod;

    private RootBlock sup;
    private GeneStringOverridable supMethod;

    public RootBlock getSub() {
        return sub;
    }

    public void setSub(RootBlock sub) {
        this.sub = sub;
    }

    public GeneStringOverridable getSubMethod() {
        return subMethod;
    }

    public void setSubMethod(GeneStringOverridable _subMethod) {
        subMethod = _subMethod;
    }

    public RootBlock getSup() {
        return sup;
    }

    public void setSup(RootBlock sup) {
        this.sup = sup;
    }

    public GeneStringOverridable getSupMethod() {
        return supMethod;
    }

    public void setSupMethod(GeneStringOverridable _supMethod) {
        supMethod = _supMethod;
    }

}
