package code.expressionlanguage;

import code.sml.RowCol;
import code.util.StringList;
import code.util.ints.Displayable;
import code.util.ints.Equallable;

public final class FileRowCol implements Equallable<FileRowCol>, Displayable {

    private static final String SEP = ":";

    private final String fileName;
    private final RowCol rowCol;
    private String typeName;

    public FileRowCol(String _fileName, String _typeName, RowCol _rowCol) {
        fileName = _fileName;
        typeName = _typeName;
        rowCol = _rowCol;
    }

    public FileRowCol(String _fileName, RowCol _rowCol) {
        fileName = _fileName;
        rowCol = _rowCol;
    }

    public String getFileName() {
        return fileName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String _typeName) {
        typeName = _typeName;
    }

    public RowCol getRowCol() {
        return rowCol;
    }

    @Override
    public boolean eq(FileRowCol _g) {
        if (!StringList.quickEq(fileName, _g.fileName)) {
            return false;
        }
        if (!rowCol.eq(_g.rowCol)) {
            return false;
        }
        return true;
    }

    @Override
    public String display() {
        return StringList.concat(fileName,SEP,rowCol.display());
    }

}
