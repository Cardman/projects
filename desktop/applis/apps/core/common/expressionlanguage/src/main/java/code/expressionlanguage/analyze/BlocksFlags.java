package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.AbsBk;
import code.util.CustList;
import code.util.IdList;

public final class BlocksFlags {
    private final IdList<AbsBk> blocks = new IdList<AbsBk>();
    private final CustList<Boolean> flags = new CustList<Boolean>();

    public boolean getVal(AbsBk _reach) {
        int i_ = index(_reach);
        if (!flags.isValidIndex(i_)) {
            return false;
        }
        return flags.get(i_);
    }

    public void put(AbsBk _reach, boolean _b) {
        int i_ = index(_reach);
        if (i_ < 0) {
            blocks.add(_reach);
            flags.add(_b);
        } else {
            flags.set(i_,_b);
        }
    }
    private int index(AbsBk _bl) {
        return blocks.indexOfObj(_bl);
    }

}
