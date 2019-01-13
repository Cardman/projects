package code.expressionlanguage.files;

import code.expressionlanguage.methods.FileBlock;

public final class EnablingSpaces {

    private boolean enabledTab;
    private boolean enabledSpace;
    private boolean ok = true;
    private int begin;
    private int end;
    private FileBlock file;
    private boolean onlySpacesLine = true;
    private boolean checkTabs = true;

    public boolean isEnabledTab() {
        return enabledTab;
    }
    public void setEnabledTab(boolean _enabledTab) {
        enabledTab = _enabledTab;
    }
    public boolean isEnabledSpace() {
        return enabledSpace;
    }
    public void setEnabledSpace(boolean _enabledSpace) {
        enabledSpace = _enabledSpace;
    }
    public boolean isOk() {
        return ok;
    }
    public void setOk(boolean _ok) {
        ok = _ok;
    }
    public int getBegin() {
        return begin;
    }
    public void setBegin(int _begin) {
        begin = _begin;
    }
    public int getEnd() {
        return end;
    }
    public void setEnd(int _end) {
        end = _end;
    }
    public FileBlock getFile() {
        return file;
    }
    public void setFile(FileBlock _file) {
        file = _file;
    }
    public boolean isOnlySpacesLine() {
        return onlySpacesLine;
    }
    public void setOnlySpacesLine(boolean _onlySpacesLine) {
        onlySpacesLine = _onlySpacesLine;
    }
    public int getTabWidth() {
        return file.getTabWidth();
    }
    public boolean isCheckTabs() {
        return checkTabs;
    }
    public void setCheckTabs(boolean _checkTabs) {
        checkTabs = _checkTabs;
    }
}
