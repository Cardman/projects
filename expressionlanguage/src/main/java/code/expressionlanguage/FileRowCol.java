package code.expressionlanguage;

import code.sml.RowCol;

public final class FileRowCol {

    private final String fileName;
    private final String typeName;
    private final RowCol rowCol;

    public FileRowCol(String _fileName, String _typeName, RowCol _rowCol) {
        fileName = _fileName;
        typeName = _typeName;
        rowCol = _rowCol;
    }

    public String getFileName() {
        return fileName;
    }

    public String getTypeName() {
        return typeName;
    }

    public RowCol getRowCol() {
        return rowCol;
    }

}
