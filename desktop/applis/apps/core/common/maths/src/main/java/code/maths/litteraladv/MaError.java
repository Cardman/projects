package code.maths.litteraladv;

import code.util.core.NumberUtil;

public final class MaError {
    private int offset = -1;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int _index) {
        offset = NumberUtil.max(0,_index);
    }

    public String display() {
        return "#"+ offset;
    }
}
