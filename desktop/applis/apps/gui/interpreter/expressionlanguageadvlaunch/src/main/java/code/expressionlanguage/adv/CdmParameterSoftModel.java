package code.expressionlanguage.adv;

import code.util.StringList;

public final class CdmParameterSoftModel {
    private boolean directMatchKeyValue;
    private String execConf = "";
    private String folderExpression = "";
    private StringList lines = new StringList();
    private final StringList openedFiles = new StringList();
    private final StringList openedFilesToInit = new StringList();

    public boolean isDirectMatchKeyValue() {
        return directMatchKeyValue;
    }

    public void setDirectMatchKeyValue(boolean _d) {
        this.directMatchKeyValue = _d;
    }

    public String getExecConf() {
        return execConf;
    }

    public void setExecConf(String _e) {
        this.execConf = _e;
    }

    public String getFolderExpression() {
        return folderExpression;
    }

    public void setFolderExpression(String _f) {
        this.folderExpression = _f;
    }

    public StringList getOpenedFiles() {
        return openedFiles;
    }

    public StringList getOpenedFilesToInit() {
        return openedFilesToInit;
    }

    public StringList getLines() {
        return lines;
    }

    public void setLines(StringList _lines) {
        this.lines = _lines;
    }

}
