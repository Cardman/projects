package code.gui;


public final class SelectionEvent<T> implements Runnable {
    private final int firstIndex;
    private final int lastIndex;
    private final GraphicList<T> grList;

    public SelectionEvent(int _firstIndex, int _lastIndex,
                          GraphicList<T> _grList) {
        firstIndex = _firstIndex;
        lastIndex = _lastIndex;
        grList = _grList;
    }

    @Override
    public void run() {
        grList.selectEvent(firstIndex, lastIndex, true);
    }
}
