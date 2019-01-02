package code.expressionlanguage.files;

import code.expressionlanguage.methods.FileBlock;
import code.util.Numbers;

public final class InputTypeCreation {

    private FileBlock fileBlock;

    private int nextIndex;

    private EnablingSpaces enabledSpaces;
    private Numbers<Integer> badIndexes = new Numbers<Integer>();

    public FileBlock getFileBlock() {
        return fileBlock;
    }

    public void setFileBlock(FileBlock _fileBlock) {
        fileBlock = _fileBlock;
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
    public Numbers<Integer> getBadIndexes() {
        return badIndexes;
    }
}
