package code.gui;


public interface GraphicComboGrInt extends WithPopup,GraphicComboInt, Input {

    boolean isEnabled();
    void setEnabled(boolean _value);
    AbsCustComponent getCurrentSelected();
    void simpleSelectItem(int _index);
    String getSelectedItem();
    void simpleRemoveAllItems();
    void simpleRemoveItem(int _index);
    AbsCustComponent self();
}
