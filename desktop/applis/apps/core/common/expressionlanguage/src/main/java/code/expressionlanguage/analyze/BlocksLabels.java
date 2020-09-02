package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.Block;
import code.util.IdList;
import code.util.StringList;

public final class BlocksLabels {
    private IdList<Block> blocks = new IdList<Block>();
    private StringList flags = new StringList();

    public String getVal(Block _reach) {
        int i_ = index(_reach);
        if (!flags.isValidIndex(i_)) {
            return "";
        }
        return flags.get(i_);
    }

    public void put(Block _reach, String _b) {
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
