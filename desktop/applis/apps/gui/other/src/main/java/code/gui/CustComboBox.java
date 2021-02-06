package code.gui;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import code.util.StringList;
import code.util.Ints;
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
        if (!_list.isEmpty()) {
            selectItem(_selectedIndex);
        }
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
        combo.addItem(_object);
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
        if (_index > -1) {
            combo.removeItem(_index);
        }
    }
    @Override
    public void removeItem(int _index) {
        if (_index > -1) {
            combo.removeItem(_index);
        }
    }

    @Override
    public int getSelectedIndex() {
        return combo.getSelectedIndex();
    }

    public void setSelectedIndex(int _index) {
        if (_index < 0) {
            return;
        }
		combo.setSelectedIndex(_index);
    }

    @Override
    public void selectItem(int _index) {
		int old_ = combo.getSelectedIndex();
        simpleSelectItem(_index);
        CustComponent.invokeLater(new SelectionComboEvent(_index, _index, this,listener,old_));
    }
    public void simpleSelectItem(int _index) {
        if (_index < 0) {
            return;
        }
		combo.setSelectedIndex(_index);
    }

    public void update() {
        //
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
        if (combo.getSelectedIndex() == -1) {
            return new Ints();
        }
        return new Ints(combo.getSelectedIndex());
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
