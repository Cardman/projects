package code.bean.nat;

import code.util.core.StringUtil;

public final class NaStSt implements NatDisplayableStruct {
    private final String inst;

    public NaStSt(String _v) {
        this.inst = StringUtil.nullToEmpty(_v);
    }

    @Override
    public boolean sameReference(NaSt _other) {
        if (!(_other instanceof NaStSt)) {
            return false;
        }
        return StringUtil.quickEq(getInstance(),((NaStSt)_other).getInstance());
    }

    @Override
    public NaStSt getDisplayedString() {
        return this;
    }

    public String getInstance() {
        return inst;
    }
}
