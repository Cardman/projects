package code.gui;

import code.util.CustList;
import code.util.Ints;

public interface AbsGraphicList<T> {
    void add(T _elt);
    void add(int _index, AbsPreparedLabel _lab, T _elt);
    void add(int _index, T _elt);
    void set(int _index, T _elt);
    int set(int _index, AbsPreparedLabel _lab, T _elt);
    void clear();
    void clearSelection();
    void clearRevalidate();
    int size();
    void remove(int _index);
    void setListener(ListSelection _list);
    void clearAllRange();
    void setSelectedIndice(int _index);
    void setVisibleRowCount(int _vis);
    int getSelectedIndex();
    boolean isSelectionEmpty();
    boolean isEmpty();

    CustList<T> getList();

    int getSelectedValuesLsLen();

    T get(int _i);

    T last();

    AbsCustComponent self();
    AbsCustComponent scroll();
    AbsCustComponent visible();

    Ints getSelectedIndexes();
}
