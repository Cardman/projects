package code.expressionlanguage.guicompos;

import code.gui.GraphicList;
import code.gui.ListSelection;

public final class SelectionEventLater implements Runnable {
    private final int firstIndex;
    private final int lastIndex;
    private final GraphicListStruct grList;

    public SelectionEventLater(int _firstIndex, int _lastIndex,
                          GraphicListStruct _grList) {
        firstIndex = _firstIndex;
        lastIndex = _lastIndex;
        grList = _grList;
    }
    @Override
    public void run() {
        GraphicList.selectEvent(firstIndex,lastIndex, true, (ListSelection) grList.getListener());
    }
}
