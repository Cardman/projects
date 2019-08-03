package code.gui;


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
    Panel getPanel();
    void popup();
}
