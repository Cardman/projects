package code.expressionlanguage.errors.custom;
import code.expressionlanguage.Analyzable;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.FileBlock;

public abstract class FoundWarningInterpret {

    protected static final String SEP_INFO = "\n";

    protected static final String SEP_KEY_VAL = ":";

    private static final String FILE = "file";

    private static final String LINE_COL = "line col";

    private String fileName;

    private int indexFile;

    private Analyzable analyzable;

    public void setAnalyzable(Analyzable analyzable) {
        this.analyzable = analyzable;
    }

    public String display(Classes _classes) {
        StringBuilder str_ = new StringBuilder(SEP_INFO);
        str_.append(FILE).append(SEP_KEY_VAL).append(fileName).append(SEP_INFO);
        int r_ = analyzable.getRowFile(fileName, indexFile);
        int c_ = analyzable.getColFile(fileName, indexFile, r_);
        str_.append(LINE_COL).append(SEP_KEY_VAL).append(Integer.toString(r_));
        str_.append(SEP_KEY_VAL).append(Integer.toString(c_)).append(SEP_INFO);
        str_.append(LINE_COL).append(SEP_KEY_VAL).append(Integer.toString(indexFile)).append(SEP_INFO);
        return str_.toString();
    }

    public void setFileName(String _fileName) {
        fileName = _fileName;
    }

    public void setIndexFile(int _indexFile) {
        indexFile = _indexFile;
    }
}
