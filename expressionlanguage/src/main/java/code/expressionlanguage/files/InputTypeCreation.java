package code.expressionlanguage.files;

import code.util.Numbers;

public final class InputTypeCreation {

    private int nextIndex;

    private EnablingSpaces enabledSpaces;
    private Numbers<Integer> badIndexes = new Numbers<Integer>();

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
