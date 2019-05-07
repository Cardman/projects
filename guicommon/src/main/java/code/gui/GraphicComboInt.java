package code.gui;

import javax.swing.JPanel;

import code.util.ints.SimpleIterable;

public interface GraphicComboInt {

    int getSelectedIndex();

    int getItemCount();

    void removeItem(int _index);
    void selectItem(int _index);

    void removeAllItems();
    ListSelection getListener();
    void addItem(String _object);
    void setListener(ListSelection _listener);
    int getMaxWidth();
    JPanel getPanel();
    void popup();
}
