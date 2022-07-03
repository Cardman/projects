package code.gui;

import code.util.CustList;

public interface AbsGraphicListCommon extends AbsGraphicListDefBase,WithListListener {
    AbsPanel getPanel();
    AbsGraphicListPainter getGraphicListPainter();
    CustList<IndexableListener> getIndexableKey();
    CustList<IndexableListener> getIndexableMouse();
    int getSelectedValuesLsLen();
    void selectEvent(int _firstIndex, int _lastIndex, boolean _methodAction);

    void addRange();
    void clearRange();
    AbsCustCellRender setted();

    void setFirstIndex(int _index);

    void setLastIndex(int _index);

    int size();
    void multSelect(int _index, AbsPreparedLabel _c);
    void singleSelect(int _index, AbsPreparedLabel _c);
    boolean isSimple();
    void rebuildAct();
    int getMaxWidth();

    AbsCustCellRender getSimpleRender();
    void multSelSet(int _index, AbsPreparedLabel _lab);
    void singleSelSet(int _index, AbsPreparedLabel _lab);
    void addSingleSel(int _index, AbsPreparedLabel _lab);
    void addMultSel(int _index, AbsPreparedLabel _lab);
}
