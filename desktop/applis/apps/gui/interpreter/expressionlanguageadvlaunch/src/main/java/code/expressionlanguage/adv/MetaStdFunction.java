package code.expressionlanguage.adv;

import code.expressionlanguage.stds.StandardNamedFunction;
import code.expressionlanguage.stds.StandardType;

public final class MetaStdFunction extends AbsMetaStdType {
    private final StandardNamedFunction function;

    public MetaStdFunction(StandardType _p, StandardNamedFunction _f) {
        super(_p);
        this.function = _f;
    }

    public StandardNamedFunction getFunction() {
        return function;
    }
}
