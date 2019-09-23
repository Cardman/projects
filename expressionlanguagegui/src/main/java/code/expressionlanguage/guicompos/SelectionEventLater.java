package code.expressionlanguage.guicompos;

public final class SelectionEventLater implements Runnable {
    private int firstIndex;
    private int lastIndex;
    private GraphicListStruct grList;

    public SelectionEventLater(int _firstIndex, int _lastIndex,
                          GraphicListStruct _grList) {
        firstIndex = _firstIndex;
        lastIndex = _lastIndex;
        grList = _grList;
    }
    @Override
    public void run() {
        SelectionStructUtil.selectEvent(firstIndex,lastIndex,grList,true);
    }
}
