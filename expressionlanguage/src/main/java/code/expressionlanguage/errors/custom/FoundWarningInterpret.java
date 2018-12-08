package code.expressionlanguage.errors.custom;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.FileBlock;

public abstract class FoundWarningInterpret {

    protected static final String SEP_INFO = "\n";

    protected static final String SEP_KEY_VAL = ":";

    private static final String FILE = "file";

    private static final String LINE_COL = "line col";

    private String fileName;

    private int indexFile;

    public String display(Classes _classes) {
        StringBuilder str_ = new StringBuilder(SEP_INFO);
        str_.append(FILE).append(SEP_KEY_VAL).append(fileName).append(SEP_INFO);
        FileBlock f_ = _classes.getFileBody(fileName);
        if (f_ == null) {
            str_.append(LINE_COL).append(SEP_KEY_VAL).append(SEP_INFO);
        } else {
            str_.append(LINE_COL).append(SEP_KEY_VAL).append(Integer.toString(f_.getRowFile(indexFile)));
            str_.append(SEP_KEY_VAL).append(Integer.toString(f_.getColFile(indexFile))).append(SEP_INFO);
        }
        str_.append(LINE_COL).append(SEP_KEY_VAL).append(Integer.toString(indexFile)).append(SEP_INFO);
        return str_.toString();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String _fileName) {
        fileName = _fileName;
    }

    public int getIndexFile() {
        return indexFile;
    }

    public void setIndexFile(int _indexFile) {
        indexFile = _indexFile;
    }
}
