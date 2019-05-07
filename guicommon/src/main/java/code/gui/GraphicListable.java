package code.gui;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import code.util.CustList;
import code.util.Numbers;

public interface GraphicListable {

    ListSelection getListener();

    void setListener(ListSelection _listener);
    void addRange();
    void clearAllRange();
    void clearRange();
    Numbers<Integer> getSelectedIndexes();
    CustCellRender getRender();

    void setRender(CustCellRender _render);

    Object[] toArray();

    int getMaxWidth();
    int getFirstIndex();

    void setFirstIndex(int _firstIndex);

    int getLastIndex();

    void setLastIndex(int _lastIndex);

    JPanel getPanel();
    JScrollPane getScroll();

    CustList<JComponent> getListComponents();
}
