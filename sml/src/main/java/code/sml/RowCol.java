package code.sml;
import code.util.Numbers;
import code.util.ints.Cmp;
import code.util.ints.Displayable;

public final class RowCol implements Cmp<RowCol>, Displayable {

    private static final String SEP = ",";

    private int row;

    private int col;

    @Override
    public String display() {
        StringBuilder str_ = new StringBuilder();
        str_.append(row);
        str_.append(SEP);
        str_.append(col);
        return str_.toString();
    }

    @Override
    public boolean eq(RowCol _obj) {
        if (row != _obj.row) {
            return false;
        }
        if (col != _obj.col) {
            return false;
        }
        return true;
    }

    @Override
    public int cmp(RowCol _other) {
        if (row != _other.row) {
            return Numbers.compare(row, _other.row);
        }
        return Numbers.compare(col, _other.col);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int _row) {
        row = _row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int _col) {
        col = _col;
    }
}
