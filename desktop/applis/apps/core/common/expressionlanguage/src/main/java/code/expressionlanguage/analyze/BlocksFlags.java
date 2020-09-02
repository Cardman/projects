package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.Block;
import code.util.BooleanList;
import code.util.IdList;

public final class BlocksFlags {
    private IdList<Block> blocks = new IdList<Block>();
    private BooleanList flags = new BooleanList();

    public boolean getVal(Block _reach) {
        int i_ = index(_reach);
        if (!flags.isValidIndex(i_)) {
            return false;
        }
        return flags.get(i_);
    }

    public void put(Block _reach, boolean _b) {
        int i_ = index(_reach);
        if (i_ < 0) {
            blocks.add(_reach);
            flags.add(_b);
        } else {
            flags.set(i_,_b);
        }
    }
    private int index(Block _bl) {
        return blocks.indexOfObj(_bl);
    }

}
