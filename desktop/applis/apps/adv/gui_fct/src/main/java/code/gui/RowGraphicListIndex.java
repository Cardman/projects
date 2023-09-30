package code.gui;

public final class RowGraphicListIndex<T> {
    private final RowGraphicList<T> row;
    private final int index;

    public RowGraphicListIndex(RowGraphicList<T> _r, int _i) {
        this.row = _r;
        this.index = _i;
    }

    public RowGraphicList<T> getRow() {
        return row;
    }

    public int getIndex() {
        return index;
    }
}
