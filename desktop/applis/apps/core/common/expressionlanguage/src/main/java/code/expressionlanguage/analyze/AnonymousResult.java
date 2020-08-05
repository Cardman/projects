package code.expressionlanguage.analyze;

import code.expressionlanguage.analyze.blocks.Block;

public final class AnonymousResult {
    private int index;
    private int until;
    private int length;
    private Block type;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getUntil() {
        return until;
    }

    public void setUntil(int _until) {
        until = _until;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Block getType() {
        return type;
    }

    public void setType(Block type) {
        this.type = type;
    }
}
