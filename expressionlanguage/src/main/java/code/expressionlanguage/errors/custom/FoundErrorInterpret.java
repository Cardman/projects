package code.expressionlanguage.errors.custom;
import code.sml.RowCol;

public abstract class FoundErrorInterpret {

    protected static final String SEP_INFO = "\n";

    protected static final String SEP_KEY_VAL = ":";

    private static final String FILE = "file";

    private static final String LINE_COL = "line col";

    private RowCol rc;

    private String fileName;

    public String display() {
        StringBuilder str_ = new StringBuilder(SEP_INFO);
        str_.append(FILE).append(SEP_KEY_VAL).append(fileName).append(SEP_INFO);
        str_.append(LINE_COL).append(SEP_KEY_VAL).append(rc.display()).append(SEP_INFO);
        return str_.toString();
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
