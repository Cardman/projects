package code.expressionlanguage.analyze.files;

import code.expressionlanguage.analyze.blocks.FileBlock;
import code.util.Ints;

public final class InputTypeCreation {

    private int nextIndex;
    private int nextIndexBef;

    private OuterBlockEnum type;
    private FileBlock file;
    private Ints badIndexes = new Ints();

    public int getNextIndex() {
        return nextIndex;
    }

    public void setNextIndex(int _nextIndex) {
        nextIndex = _nextIndex;
    }

    public int getNextIndexBef() {
        return nextIndexBef;
    }

    public void setNextIndexBef(int nextIndexBef) {
        this.nextIndexBef = nextIndexBef;
    }

    public OuterBlockEnum getType() {
        return type;
    }

    public void setType(OuterBlockEnum type) {
        this.type = type;
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
