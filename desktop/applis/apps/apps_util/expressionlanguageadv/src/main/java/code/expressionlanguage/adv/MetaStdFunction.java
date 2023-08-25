package code.expressionlanguage.adv;

import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.stds.StandardType;
import code.gui.MutableTreeNodeNav;

public final class MetaStdFunction extends MutableTreeNodeNav {
    private final StandardType par;
    private final StandardNamedFunction function;

    public MetaStdFunction(StandardType _p, StandardNamedFunction _f) {
        this.par = _p;
        this.function = _f;
    }

    public StandardType getPar() {
        return par;
    }

    public StandardNamedFunction getFunction() {
        return function;
    }
}
