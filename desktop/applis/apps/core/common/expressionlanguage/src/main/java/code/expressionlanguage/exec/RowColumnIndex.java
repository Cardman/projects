package code.expressionlanguage.exec;

import code.expressionlanguage.common.FileMetrics;
import code.expressionlanguage.exec.blocks.ExecFileBlockAbs;

public class RowColumnIndex {
    private final int row;
    private final int column;
    private final int index;

    private RowColumnIndex(int _row, int _column, int _index) {
        this.row = _row;
        this.column = _column;
        this.index = _index;
    }
    public static RowColumnIndex def() {
        return new RowColumnIndex(0,0,0);
    }
    public static RowColumnIndex calculate(ExecFileBlockAbs _info, int _trace, int _tab) {
        int indexFileType_ = _info.getFileEscapedCalc().realIndex(_trace);
        FileMetrics metrics_ = new FileMetrics(_info.getMetricsCore(),_tab);
        int row_ = metrics_.getRowFile(indexFileType_);
        int col_ = metrics_.getColFile(indexFileType_,row_);
        return new RowColumnIndex(row_,col_,indexFileType_);
    }

    public int getIndex() {
        return index;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
