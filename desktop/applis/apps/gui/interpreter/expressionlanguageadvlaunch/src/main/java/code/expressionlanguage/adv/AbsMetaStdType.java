package code.expressionlanguage.adv;

import code.expressionlanguage.stds.StandardType;
import code.gui.MutableTreeNodeNav;

public abstract class AbsMetaStdType {
    private final MutableTreeNodeNav<AbsMetaStdType> elt = new MutableTreeNodeNav<AbsMetaStdType>();
    private final StandardType standardType;
    protected AbsMetaStdType(StandardType _s) {
        elt.info(this);
        standardType = _s;
    }

    public StandardType getStandardType() {
        return standardType;
    }

    public MutableTreeNodeNav<AbsMetaStdType> getElt() {
        return elt;
    }
}
