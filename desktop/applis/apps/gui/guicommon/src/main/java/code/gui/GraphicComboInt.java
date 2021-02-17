package code.gui;


public interface GraphicComboInt {

    int getSelectedIndex();

    int getItemCount();

    void removeItem(int _index);
    void selectItem(int _index);

    void removeAllItems();
    void addItem(String _object);
    void setListener(ListSelection _listener);
    ListSelection[] getListeners();
    void addListener(ListSelection _listener);

    void removeListener(ListSelection _listener);

}
