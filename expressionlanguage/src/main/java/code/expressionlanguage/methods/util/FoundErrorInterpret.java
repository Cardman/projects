package code.expressionlanguage.methods.util;
import code.sml.RowCol;
import code.util.ints.Displayable;

public abstract class FoundErrorInterpret implements Displayable {

    protected static final String SEP_INFO = "\n";

    protected static final String SEP_KEY_VAL = ":";

    private static final String FILE = "file";

    private static final String LINE_COL = "line col";

    private RowCol rc;

    private String fileName;

    @Override
    public String display() {
        return SEP_INFO+FILE+SEP_KEY_VAL+fileName+SEP_INFO+LINE_COL+SEP_KEY_VAL+rc+SEP_INFO;
    }
    @Override
    public String toString() {
        return SEP_INFO+FILE+SEP_KEY_VAL+fileName+SEP_INFO+LINE_COL+SEP_KEY_VAL+rc+SEP_INFO;
    }

    public RowCol getRc() {
        return rc;
    }

    public void setRc(RowCol _rc) {
        rc = _rc;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        fileName = _fileName;
    }

}
