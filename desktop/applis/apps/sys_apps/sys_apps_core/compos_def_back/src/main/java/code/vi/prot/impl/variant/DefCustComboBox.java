package code.vi.prot.impl.variant;

import code.adv.SafeRemoveAdvUtil;
import code.gui.AbsCustComponent;
import code.gui.FrameUtil;
import code.gui.GraphicComboGrInt;
import code.gui.ListSelection;
import code.vi.prot.impl.gui.CustComponent;
import code.util.CustList;
import code.util.IdMap;
import code.util.Ints;
import code.util.StringList;
import code.util.core.StringUtil;

import javax.swing.*;

public final class DefCustComboBox extends CustComponent implements GraphicComboGrInt {

    private final JComboBox comboDef = new JComboBox();

    private final IdMap<ListSelection,DefLocalItemListener> listenersDef = new IdMap<ListSelection,DefLocalItemListener>();

    public DefCustComboBox(StringList _list) {
        this(_list,0);
    }

    public DefCustComboBox(StringList _list, int _selectedIndex) {
        feed(_list);
        selectItem(_selectedIndex);
    }

    private void feed(StringList _list) {
        FrameUtil.feed(this, _list);
    }

    public String getSelectedItem() {
        return (String) comboDef.getSelectedItem();
    }

    @Override
    public void addItem(String _object) {
        comboDef.addItem(StringUtil.nullToEmpty(_object));
    }

    @Override
    public int getItemCount() {
        return comboDef.getItemCount();
    }

    @Override
    public void simpleRemoveAllItems() {
        comboDef.removeAllItems();
    }

    @Override
    public void removeAllItems() {
        comboDef.removeAllItems();
    }

    @Override
    public CustList<ListSelection> getListeners() {
        return listenersDef.getKeys();
    }

    @Override
    public void addListener(ListSelection _listener) {
        innerAddListener(_listener);
    }

    private void innerAddListener(ListSelection _listener) {
        DefLocalItemListener listen_ = new DefLocalItemListener(comboDef, _listener);
        comboDef.addItemListener(listen_);
        listenersDef.addEntry(_listener,listen_);
    }
    @Override
    public void removeListener(ListSelection _listener) {
        DefLocalItemListener val_ = listenersDef.getVal(_listener);
        comboDef.removeItemListener(val_);
        listenersDef.removeKey(_listener);
    }

    public void setListener(ListSelection _listener) {
        FrameUtil.removeListeners(this);
        listenersDef.clear();
        innerAddListener(_listener);
    }

    @Override
    public void popup() {
        comboDef.showPopup();
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
        SafeRemoveAdvUtil.safeRemove(new DefSafeRemoveImpl(comboDef),_index);
    }

    @Override
    public int getSelectedIndex() {
        return comboDef.getSelectedIndex();
    }

    @Override
    public void selectItem(int _index) {
        simpleSelectItem(_index);
    }
    public void simpleSelectItem(int _index) {
        int index_ = Math.min(comboDef.getItemCount()-1,_index);
        index_ = Math.max(index_,-1);
        comboDef.setSelectedIndex(index_);
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
        return Ints.singleOrEmpty(comboDef.getSelectedIndex());
    }

    public boolean isEnabled() {
        return comboDef.isEnabled();
    }

    public void setEnabled(boolean _enabled) {
        comboDef.setEnabled(_enabled);
    }

    @Override
    public JComponent getNatComponent() {
        return comboDef;
    }

    @Override
    public AbsCustComponent self() {
        return this;
    }

}
