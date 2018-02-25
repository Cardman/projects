package code.gui;


public final class SelectionEvent extends Thread {
    private int firstIndex;
    private int lastIndex;
    private GraphicListable grList;

    public SelectionEvent(int _firstIndex, int _lastIndex,
            GraphicListable _grList) {
        firstIndex = _firstIndex;
        lastIndex = _lastIndex;
        grList = _grList;
    }

    @Override
    public void run() {
        SelectionUtil.selectEvent(firstIndex, lastIndex, grList, true);
    }
}
