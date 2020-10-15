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

    public void setSub(RootBlock _sub) {
        this.sub = _sub;
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

    public void setSup(RootBlock _sup) {
        this.sup = _sup;
    }

    public GeneStringOverridable getSupMethod() {
        return supMethod;
    }

    public void setSupMethod(GeneStringOverridable _supMethod) {
        supMethod = _supMethod;
    }

}
