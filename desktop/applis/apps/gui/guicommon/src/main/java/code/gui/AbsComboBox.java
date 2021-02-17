package code.gui;
import code.util.*;

public abstract class AbsComboBox implements GraphicComboGrIntBase {

    private final GraphicComboGrInt combo;
    public AbsComboBox(GraphicComboGrInt _combo) {
        combo = _combo;
    }

    @Override
    public int getSelectedIndex() {
        return combo.getSelectedIndex();
    }

    @Override
    public int getItemCount() {
        return combo.getItemCount();
    }

    @Override
    public void selectItem(int _index) {
        combo.selectItem(_index);
    }


    @Override
    public ListSelection[] getListeners() {
        return combo.getListeners();
    }

    @Override
    public void addListener(ListSelection _listener) {
        combo.addListener(_listener);
    }

    @Override
    public void removeListener(ListSelection _listener) {
        combo.removeListener(_listener);
    }


    @Override
    public void addItem(String _object) {
        combo.addItem(_object);
    }

    @Override
    public void setListener(ListSelection _listener) {
        combo.setListener(_listener);
    }


    public GraphicComboGrInt getCombo() {
        return combo;
    }

    @Override
    public boolean isEnabled() {
        return combo.isEnabled();
    }

    @Override
    public void setEnabled(boolean _value) {
        combo.setEnabled(_value);
    }

    @Override
    public CustComponent getCurrentSelected() {
        return combo.getCurrentSelected();
    }

    @Override
    public void simpleSelectItem(int _index) {
        combo.simpleSelectItem(_index);
    }

    @Override
    public String getSelectedItem() {
        return combo.getSelectedItem();
    }

    @Override
    public void simpleRemoveAllItems() {
        combo.simpleRemoveAllItems();
    }

    @Override
    public void simpleRemoveItem(int _index) {
        combo.simpleRemoveItem(_index);
    }

    @Override
    public void update() {
        tryUp(combo);
    }

    public static void tryUp(GraphicComboGrInt _combo) {
        if (_combo instanceof GraphicComboGrIntBase) {
            ((GraphicComboGrIntBase) _combo).update();
        }
    }

    @Override
    public CustComponent self() {
        return combo.self();
    }

    @Override
    public CustComponent getGlobal() {
        return combo.getGlobal();
    }

    @Override
    public Ints getSelectedIndexes() {
        return combo.getSelectedIndexes();
    }

    @Override
    public void popup() {
        combo.popup();
    }
}
