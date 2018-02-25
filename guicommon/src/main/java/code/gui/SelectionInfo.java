package code.gui;

public final class SelectionInfo {

    private final int firstIndex;
    private final int lastIndex;
    private final Object source;
    private final boolean methodAction;
    public SelectionInfo(int _firstIndex, int _lastIndex, Object _source,
            boolean _methodAction) {
        firstIndex = _firstIndex;
        lastIndex = _lastIndex;
        source = _source;
        methodAction = _methodAction;
    }
    public int getFirstIndex() {
        return firstIndex;
    }
    public int getLastIndex() {
        return lastIndex;
    }
    public Object getSource() {
        return source;
    }
    public boolean isMethodAction() {
        return methodAction;
    }

}
