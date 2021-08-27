package code.gui;


public final class SelectionEvent implements Runnable {
    private final int firstIndex;
    private final int lastIndex;
    private final AbsGraphicListCommon grList;

    public SelectionEvent(int _firstIndex, int _lastIndex,
                          AbsGraphicListCommon _grList) {
        firstIndex = _firstIndex;
        lastIndex = _lastIndex;
        grList = _grList;
    }

    @Override
    public void run() {
        grList.selectEvent(firstIndex, lastIndex, true);
    }
}
