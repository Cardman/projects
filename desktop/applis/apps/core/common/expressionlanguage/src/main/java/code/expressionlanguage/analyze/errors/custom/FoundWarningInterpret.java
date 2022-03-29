package code.expressionlanguage.analyze.errors.custom;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.FileBlock;
import code.util.core.StringUtil;

public final class FoundWarningInterpret {

    private static final String SEP_INFO = "\n";

    private static final String SEP_KEY_VAL = ":";

    private static final String FILE_INTRO = "file";

    private static final String LINE_COL = "line col";

    private FileBlock file;

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
        str_.append(FILE_INTRO).append(SEP_KEY_VAL).append(file.getFileName()).append(SEP_INFO);
        str_.append(LINE_COL).append(SEP_KEY_VAL).append(_locationFile).append(SEP_INFO);
        fullLocationFile = str_.toString();
    }

    public String getBuiltWarning() {
        return builtWarning;
    }

    public FileBlock getFile() {
        return file;
    }

    public void setFile(FileBlock _file) {
        this.file = _file;
    }

    public int getIndexFile() {
        return indexFile;
    }

    public void setIndexFile(int _indexFile) {
        indexFile = _indexFile;
    }

    public void setIndexFile(AnalyzedPageEl _page) {
        indexFile = _page.getTraceIndex();
    }
}
