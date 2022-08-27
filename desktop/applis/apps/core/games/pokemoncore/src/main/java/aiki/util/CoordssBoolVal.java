package aiki.util;

import code.util.CollCapacity;
import code.util.core.BoolVal;

public final class CoordssBoolVal extends Coordss<BoolVal> {
    public CoordssBoolVal() {
    }
    public CoordssBoolVal(CollCapacity _cap) {
        super(_cap);
    }

    @Override
    protected BoolVal def() {
        return BoolVal.FALSE;
    }
}
