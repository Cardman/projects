package code.expressionlanguage;


public class SelectionComboEventStruct implements Runnable {
    private int firstIndex;
    private int lastIndex;
    private GraphicComboStruct grList;

    public SelectionComboEventStruct(int _firstIndex, int _lastIndex,
                                     GraphicComboStruct _grList) {
        firstIndex = _firstIndex;
        lastIndex = _lastIndex;
        grList = _grList;
    }

    @Override
    public void run() {
        SelectionStructUtil.selectEvent(firstIndex, lastIndex, grList,grList.getSelection(), true);
    }
}
