package code.expressionlanguage.files;

import code.util.Ints;

public final class InputTypeCreation {

    private int nextIndex;

    private EnablingSpaces enabledSpaces;
    private Ints badIndexes = new Ints();

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
    public Ints getBadIndexes() {
        return badIndexes;
    }
}
