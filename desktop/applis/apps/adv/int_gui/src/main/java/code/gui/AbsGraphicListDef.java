package code.gui;

import code.util.CustList;
import code.util.Ints;

public interface AbsGraphicListDef {
    void clear();
    void clearRevalidate();
    void remove(int _index);
    int getVisibleRowCount();
    void setVisibleRowCount(int _visibleRowCount);

    void setSelectedIndice(int _min);
    void clearSelection();
    Ints getSelectedIndexes();
    void setSelectedIndexes(Ints _values);
    CustList<ListSelection> getListeners();

    void addListener(ListSelection _listener);

    void removeListener(ListSelection _listener);


    int getSelectedIndex();

    boolean isEmpty();

    boolean isSelectionEmpty();

    int size();

    boolean isEnabled();
    void setEnabled(boolean _enabled);

}
