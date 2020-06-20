package code.expressionlanguage.files;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.util.Ints;

public final class InputTypeCreation {

    private int nextIndex;

    private FileBlock file;
    private Ints badIndexes = new Ints();

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int _nextIndex) {
        nextIndex = _nextIndex;
    }
    public FileBlock getFile() {
        return file;
    }
    public void setFile(FileBlock _file) {
        file = _file;
    }

    public Ints getBadIndexes() {
        return badIndexes;
    }
}
