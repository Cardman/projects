package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.AbsBk;
import code.util.IdList;
import code.util.StringList;

public final class BlocksLabels {
    private final IdList<AbsBk> blocks = new IdList<AbsBk>();
    private final StringList flags = new StringList();

    public String getVal(AbsBk _reach) {
        int i_ = index(_reach);
        if (!flags.isValidIndex(i_)) {
            return "";
        }
        return flags.get(i_);
    }

    public void put(AbsBk _reach, String _b) {
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
