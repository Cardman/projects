package code.gui;


public final class SelectionComboEvent implements Runnable {
    private final int firstIndex;
    private final int lastIndex;
    private final GraphicComboGrInt grList;
    private final ListSelection listener;
    private final int old;

    public SelectionComboEvent(int _firstIndex, int _lastIndex,
                               GraphicComboGrInt _grList, ListSelection _listener, int _old) {
        firstIndex = _firstIndex;
        lastIndex = _lastIndex;
        grList = _grList;
        listener = _listener;
        old = _old;
    }

    @Override
    public void run() {
        SelectionUtil.selectEvent(firstIndex, lastIndex, grList, listener, true, old);
    }
}
