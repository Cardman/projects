package code.gui;

public final class SelectionInfo {

    private final int firstIndex;
    private final int lastIndex;
    private final boolean methodAction;
    public SelectionInfo(int _firstIndex, int _lastIndex,
                         boolean _methodAction) {
        firstIndex = _firstIndex;
        lastIndex = _lastIndex;
        methodAction = _methodAction;
    }
    public int getFirstIndex() {
        return firstIndex;
    }
    public int getLastIndex() {
        return lastIndex;
    }

    public boolean isMethodAction() {
        return methodAction;
    }

}
