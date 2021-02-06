package code.gui;

import code.util.CustList;
import code.util.Ints;

public interface AbsGraphicList<T> extends AbsBasicGraphicList {
    void add(T _elt);
    void add(int _index, T _elt);
    void addLab(int _index, PreparedLabel _lab);
    void set(int _index, T _elt);
    void set(int _index, PreparedLabel _lab, T _elt);
    void addListeners(int _index, PreparedLabel _lab);
    void simpleAddLab(int _index, PreparedLabel _lab);
    void clearAllRange();

    CustCellRender<T> getRender();

    void setRender(CustCellRender<T> _render);

    CustList<T> getList();

    T getSelectedValue();

    CustList<T> getSelectedValuesLs();

    T get(int _i);

    T last();

    CustComponent self();
    CustComponent scroll();
    CustComponent visible();
    AbsGraphicListPainter getGraphicListPainter();
}
