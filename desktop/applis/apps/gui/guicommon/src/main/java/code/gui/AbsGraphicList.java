package code.gui;

import code.util.CustList;

public interface AbsGraphicList<T> {
    void add(T _elt);
    void add(int _index, PreparedLabel _lab, T _elt);
    void add(int _index, T _elt);
    void set(int _index, T _elt);
    void set(int _index, PreparedLabel _lab, T _elt);
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

    CustCellRender<T> getRender();

    void setRender(CustCellRender<T> _render);

    CustList<T> getList();

    T getSelectedValue();

    CustList<T> getSelectedValuesLs();

    T get(int _i);

    T last();

    AbsCustComponent self();
    AbsCustComponent scroll();
    AbsCustComponent visible();
}
