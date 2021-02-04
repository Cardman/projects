package code.gui;

import code.util.CustList;
import code.util.Ints;

public interface AbsGraphicList<T> {
    void add(T _elt);
    void add(int _index, T _elt);
    void clear();
    void clearRevalidate();
    void remove(int _index);
    int getVisibleRowCount();
    void setVisibleRowCount(int _visibleRowCount);
    void clearAllRange();
    void setSelectedIndice(int _min);
    void clearSelection();
    Ints getSelectedIndexes();

    ListSelection getListener();

    void setListener(ListSelection _listener);

    CustCellRender<T> getRender();

    void setRender(CustCellRender<T> _render);

    CustList<T> getList();

    int getSelectedIndex();

    T getSelectedValue();

    CustList<T> getSelectedValuesLs();

    T get(int _i);

    boolean isEmpty();

    T last();

    boolean isSelectionEmpty();

    int size();

    CustComponent self();
}
