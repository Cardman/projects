package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.AbsBk;
import code.util.CustList;
import code.util.IdList;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;

public final class BlocksFlags {
    private final IdList<AbsBk> blocks = new IdList<AbsBk>();
    private final CustList<BoolVal> flags = new CustList<BoolVal>();

    public boolean getVal(AbsBk _reach) {
        int i_ = index(_reach);
        if (!flags.isValidIndex(i_)) {
            return false;
        }
        return flags.get(i_) == BoolVal.TRUE;
    }

    public void put(AbsBk _reach, boolean _b) {
        int i_ = index(_reach);
        if (i_ < 0) {
            blocks.add(_reach);
            flags.add(ComparatorBoolean.of(_b));
        } else {
            flags.set(i_,ComparatorBoolean.of(_b));
        }
    }
    private int index(AbsBk _bl) {
        return blocks.indexOfObj(_bl);
    }

}
