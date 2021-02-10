package code.expressionlanguage.analyze.errors.custom;

import code.util.core.StringUtil;

public final class FoundWarningInterpret {

    private static final String SEP_INFO = "\n";

    private static final String SEP_KEY_VAL = ":";

    private static final String FILE = "file";

    private static final String LINE_COL = "line col";

    private String fileName;

    private int indexFile;

    private String fullLocationFile = "";

    private String builtWarning = "";

    public void buildWarning(String _message, String... _args) {
        builtWarning = buildARWarning(_message,_args);
    }

    public static String buildARWarning(String _message, String... _args) {
        return StringUtil.simpleStringsFormat(_message,_args);
    }

    public String display() {
        StringBuilder str_ = new StringBuilder(fullLocationFile);
        str_.append(builtWarning);
        return str_.toString();
    }

    public void setLocationFile(String _locationFile) {
        StringBuilder str_ = new StringBuilder(SEP_INFO);
        str_.append(FILE).append(SEP_KEY_VAL).append(fileName).append(SEP_INFO);
        str_.append(LINE_COL).append(SEP_KEY_VAL).append(_locationFile).append(SEP_INFO);
        fullLocationFile = str_.toString();
    }

    public String getBuiltWarning() {
        return builtWarning;
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
