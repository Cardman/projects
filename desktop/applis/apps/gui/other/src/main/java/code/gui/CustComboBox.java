package code.gui;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import code.util.StringList;
import code.util.Ints;
import code.util.core.StringUtil;

public final class CustComboBox extends CustComponent implements GraphicComboGrInt {

    private final JComboBox<String> combo = new JComboBox<>();

    private ListSelection listener;

    public CustComboBox(StringList _list) {
        this(_list,0);
    }

    public CustComboBox(StringList _list, int _selectedIndex) {
		for (String s: _list){
			addItem(s);
		}
        selectItem(_selectedIndex);
    }

    public String getSelectedItem() {
        Object obj_ = combo.getSelectedItem();
        if (obj_ instanceof String) {
            return (String)obj_;
        }
        return null;
    }

    @Override
    public void addItem(String _object) {
        combo.addItem(StringUtil.nullToEmpty(_object));
    }

    @Override
    public int getItemCount() {
        return combo.getItemCount();
    }

    @Override
    public void simpleRemoveAllItems() {
        combo.removeAllItems();
    }

    @Override
    public void removeAllItems() {
        combo.removeAllItems();
    }

    @Override
    public ListSelection getListener() {
        return listener;
    }

    @Override
    public void setListener(ListSelection _listener) {
		combo.addItemListener(new LocalItemListener(combo,_listener));
        listener = _listener;
    }

    @Override
    public void popup() {
        combo.showPopup();
    }
    @Override
    public void simpleRemoveItem(int _index) {
        innerRemoveAtIndex(_index);
    }
    @Override
    public void removeItem(int _index) {
        innerRemoveAtIndex(_index);
    }

    private void innerRemoveAtIndex(int _index) {
        if (isOk(_index)) {
            combo.removeItemAt(_index);
        }
    }

    @Override
    public int getSelectedIndex() {
        return combo.getSelectedIndex();
    }

    @Override
    public void selectItem(int _index) {
		int old_ = combo.getSelectedIndex();
        simpleSelectItem(_index);
        CustComponent.invokeLater(new SelectionComboEvent(_index, _index, this,listener,old_));
    }
    public void simpleSelectItem(int _index) {
        if (_index < 0) {
            if (_index == -1) {
                combo.setSelectedIndex(_index);
            }
            return;
        }
        if (!isOk(_index)) {
            return;
        }
		combo.setSelectedIndex(_index);
    }
    boolean isOk(int _index) {
        if (_index < 0) {
            return false;
        }
        return _index < combo.getItemCount();
    }

    public CustComponent getCurrentSelected() {
        return this;
    }

    @Override
    public CustComponent getGlobal() {
        return this;
    }

    @Override
    public Ints getSelectedIndexes() {
        int selectedIndex_ = combo.getSelectedIndex();
        if (selectedIndex_ == -1) {
            return new Ints();
        }
        return new Ints(selectedIndex_);
    }

    public boolean isEnabled() {
        return combo.isEnabled();
    }

    public void setEnabled(boolean _enabled) {
        combo.setEnabled(_enabled);
    }

    @Override
    protected JComponent getComponent() {
        return combo;
    }

    @Override
    public CustComponent self() {
        return this;
    }

}
