package code.expressionlanguage.files;

import code.expressionlanguage.methods.FileBlock;

public final class EnablingSpaces {

    private int begin;
    private int end;
    private FileBlock file;
    private boolean onlySpacesLine = true;

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
}
