package code.gui;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import code.util.CustList;
import code.util.Numbers;
import code.util.ints.SimpleIterable;

public interface GraphicListable {

    ListSelection getListener();

    void setListener(ListSelection _listener);
    void addRange();
    void clearAllRange();
    void clearRange();
    Numbers<Integer> getSelectedIndexes();
    CustCellRender getRender();

    void setRender(CustCellRender _render);
    SimpleIterable getList();

    int getMaxWidth();
    int getFirstIndex();

    void setFirstIndex(int _firstIndex);

    int getLastIndex();

    void setLastIndex(int _lastIndex);

    JPanel getPanel();
    JScrollPane getScroll();

    CustList<Component> getListComponents();
}
