package code.expressionlanguage.adv;

import code.expressionlanguage.stds.StandardType;
import code.gui.MutableTreeNodeNav;

public final class MetaStdType extends MutableTreeNodeNav {
    private final StandardType standardType;

    public MetaStdType(StandardType _s) {
        this.standardType = _s;
    }

    public StandardType getStandardType() {
        return standardType;
    }
}
