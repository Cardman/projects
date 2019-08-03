package code.gui;


public final class SelectionComboEvent implements Runnable {
    private int firstIndex;
    private int lastIndex;
    private GraphicCombo grList;

    public SelectionComboEvent(int _firstIndex, int _lastIndex,
            GraphicCombo _grList) {
        firstIndex = _firstIndex;
        lastIndex = _lastIndex;
        grList = _grList;
    }

    @Override
    public void run() {
        SelectionUtil.selectEvent(firstIndex, lastIndex, grList,grList.getListener(), true);
    }
}
