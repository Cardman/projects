package code.gui;

import javax.swing.JPanel;

import code.util.ints.SimpleIterable;

public interface GraphicComboInt {

    int getSelectedIndex();
    Object getSelectedItem();
    int getItemCount();
    SimpleIterable getList();
    void removeItem(int _index);
    void selectItem(int _index);
    Object getItem(int _index);
    void removeAllItems();
    ListSelection getListener();
    void addItem(String _object);
    void setListener(ListSelection _listener);
    int getMaxWidth();
    JPanel getPanel();
    void popup();
}
