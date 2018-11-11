package code.expressionlanguage;

import code.expressionlanguage.methods.FileBlock;
import code.sml.RowCol;

public final class InputTypeCreation {

    private FileBlock fileBlock;

    private RowCol nextRowCol;

    private int nextIndex;

    private EnablingSpaces enabledSpaces;

    public FileBlock getFileBlock() {
        return fileBlock;
    }

    public void setFileBlock(FileBlock _fileBlock) {
        fileBlock = _fileBlock;
    }

    public RowCol getNextRowCol() {
        return nextRowCol;
    }

    public void setNextRowCol(RowCol _nextRowCol) {
        nextRowCol = _nextRowCol;
    }

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int _nextIndex) {
        nextIndex = _nextIndex;
    }

    public EnablingSpaces getEnabledSpaces() {
        return enabledSpaces;
    }

    public void setEnabledSpaces(EnablingSpaces _enabledSpaces) {
        enabledSpaces = _enabledSpaces;
    }
}
