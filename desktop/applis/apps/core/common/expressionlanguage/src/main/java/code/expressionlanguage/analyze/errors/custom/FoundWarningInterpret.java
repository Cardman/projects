package code.expressionlanguage.analyze.errors.custom;

public abstract class FoundWarningInterpret {

    protected static final String SEP_INFO = "\n";

    protected static final String SEP_KEY_VAL = ":";

    private static final String FILE = "file";

    private static final String LINE_COL = "line col";

    private String fileName;

    private int indexFile;
    private String locationFile;

    public String display() {
        StringBuilder str_ = new StringBuilder(SEP_INFO);
        str_.append(FILE).append(SEP_KEY_VAL).append(fileName).append(SEP_INFO);
        str_.append(LINE_COL).append(SEP_KEY_VAL).append(locationFile).append(SEP_INFO);
        return str_.toString();
    }

    public void setLocationFile(String _locationFile) {
        this.locationFile = _locationFile;
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
