package code.vi.sys.impl.other;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import code.adv.SafeRemoveAdvUtil;
import code.gui.*;
import code.vi.sys.impl.gui.CustComponent;
import code.util.*;
import code.util.core.StringUtil;

public final class CustComboBox extends CustComponent implements GraphicComboGrInt {

    private final JComboBox<String> combo = new JComboBox<>();

    private final IdMap<ListSelection,LocalItemListener> listeners = new IdMap<>();

    public CustComboBox(StringList _list) {
        this(_list,0);
    }

    public CustComboBox(StringList _list, int _selectedIndex) {
        FrameUtil.feed(this,_list);
        selectItem(_selectedIndex);
    }

    public String getSelectedItem() {
        return (String) combo.getSelectedItem();
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
    public CustList<ListSelection> getListeners() {
        return listeners.getKeys();
    }

    @Override
    public void addListener(ListSelection _listener) {
        innerAddListener(_listener);
    }

    private void innerAddListener(ListSelection _listener) {
        LocalItemListener listen_ = new LocalItemListener(combo, _listener);
        combo.addItemListener(listen_);
        listeners.addEntry(_listener,listen_);
    }
    @Override
    public void removeListener(ListSelection _listener) {
        LocalItemListener val_ = listeners.getVal(_listener);
        combo.removeItemListener(val_);
        listeners.removeKey(_listener);
    }

    public void setListener(ListSelection _listener) {
        FrameUtil.removeListeners(this);
        listeners.clear();
        innerAddListener(_listener);
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
        SafeRemoveAdvUtil.safeRemove(new SafeRemoveImpl(combo),_index);
    }

    @Override
    public int getSelectedIndex() {
        return combo.getSelectedIndex();
    }

    @Override
    public void selectItem(int _index) {
        simpleSelectItem(_index);
    }
    public void simpleSelectItem(int _index) {
        int index_ = Math.min(combo.getItemCount()-1,_index);
        index_ = Math.max(index_,-1);
		combo.setSelectedIndex(index_);
    }

    public AbsCustComponent getCurrentSelected() {
        return this;
    }

    @Override
    public AbsCustComponent getGlobal() {
        return this;
    }

    @Override
    public Ints getSelectedIndexes() {
        return Ints.singleOrEmpty(combo.getSelectedIndex());
    }

    public boolean isEnabled() {
        return combo.isEnabled();
    }

    public void setEnabled(boolean _enabled) {
        combo.setEnabled(_enabled);
    }

    @Override
    public JComponent getNatComponent() {
        return combo;
    }

    @Override
    public AbsCustComponent self() {
        return this;
    }

}
