package code.gui;

import code.util.CustList;
import code.util.Ints;

public interface AbsBasicGraphicList {
    boolean selectOneAmongIntervalPaint(boolean _sel, int _index);
    Interval selectIntervalKeyPaint(boolean _sel, int _index);
    Interval selectIntervalPaint(boolean _sel, int _index);
    void clear();
    void updateGraphics();
    void clearRevalidate();
    void remove(int _index);
    int getVisibleRowCount();
    void setVisibleRowCount(int _visibleRowCount);
    int getFirstIndex();
    void setFirstIndex(int _firstIndex);

    void setSelectedIndice(int _min);
    void clearSelection();
    Ints getSelectedIndexes();
    CustList<PreparedLabel> getListComponents();
    void setSelectedIndexes(Ints _values);
    ListSelection getListener();

    void setListener(ListSelection _listener);


    int getSelectedIndex();

    boolean isEmpty();

    boolean isSelectionEmpty();

    int size();

    boolean isEnabled();
    void setEnabled(boolean _enabled);
}
