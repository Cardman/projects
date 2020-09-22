package code.expressionlanguage.analyze.files;

import code.expressionlanguage.analyze.blocks.Block;

public final class ResultCreation {

    private int nextIndex;

    private Block block;

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block _block) {
        block = _block;
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int _nextIndex) {
        nextIndex = _nextIndex;
    }

}
