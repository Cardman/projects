package code.expressionlanguage.analyze.opers.util;

import code.expressionlanguage.analyze.blocks.NamedFunctionBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;

public final class AnaTypeFct {
    private RootBlock type;
    private NamedFunctionBlock function;

    public NamedFunctionBlock getFunction() {
        return function;
    }

    public void setFunction(NamedFunctionBlock _function) {
        this.function = _function;
    }

    public RootBlock getType() {
        return type;
    }

    public void setType(RootBlock _type) {
        this.type = _type;
    }
}
