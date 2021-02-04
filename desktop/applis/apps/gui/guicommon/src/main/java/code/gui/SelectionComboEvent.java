package code.gui;


public final class SelectionComboEvent implements Runnable {
    private final int firstIndex;
    private final int lastIndex;
    private final GraphicComboGrInt grList;

    public SelectionComboEvent(int _firstIndex, int _lastIndex,
                               GraphicComboGrInt _grList) {
        firstIndex = _firstIndex;
        lastIndex = _lastIndex;
        grList = _grList;
    }

    @Override
    public void run() {
        SelectionUtil.selectEvent(firstIndex, lastIndex, grList,grList.getListener(), true);
    }
}
