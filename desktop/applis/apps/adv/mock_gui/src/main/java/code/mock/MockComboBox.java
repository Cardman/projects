package code.mock;

import code.gui.AbsCustComponent;
import code.gui.ListSelection;
import code.gui.SelectionInfo;
import code.util.CustList;
import code.util.IdList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.NumberUtil;

public final class MockComboBox extends MockInput {

    private final IdList<ListSelection> listSelections = new IdList<ListSelection>();
    private int selectedIndex = -1;
    private final StringList items = new StringList();
    private boolean popuped;

    public MockComboBox(StringList _list, int _selectedIndex) {
        items.addAllElts(_list);
        selectItem(_selectedIndex);
    }

    public AbsCustComponent getCurrentSelected() {
        return this;
    }

    public void simpleSelectItem(int _index) {
        int index_ = NumberUtil.min(getItemCount()-1,_index);
        index_ = NumberUtil.max(index_,-1);
        selectedIndex = index_;
        fireEvent();
    }


    public String getSelectedItem() {
        int selectedIndex_ = getSelectedIndex();
        if (!items.isValidIndex(selectedIndex_)) {
            return null;
        }
        return items.get(selectedIndex_);
    }

    public void simpleRemoveAllItems() {
        clearFire();
    }

    public void simpleRemoveItem(int _index) {
        remFire(_index);
    }

    private void remFire(int _i) {
        items.remove(_i);
        if (_i == selectedIndex) {
            if (selectedIndex == items.size()) {
                selectedIndex--;
            }
            fireEvent();
        }
    }

    public AbsCustComponent self() {
        return this;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public int getItemCount() {
        return items.size();
    }

    public void removeItem(int _index) {
        remFire(_index);
    }

    public void selectItem(int _index) {
        simpleSelectItem(_index);
    }

    public void removeAllItems() {
        clearFire();
    }

    private void clearFire() {
        items.clear();
        selectedIndex = -1;
        fireEvent();
    }

    public void addItem(String _object) {
        items.add(_object);
        if (items.size() == 1) {
            selectedIndex = 0;
            fireEvent();
        }
    }

    public void setListener(ListSelection _l) {
        listSelections.clear();
        listSelections.add(_l);
    }

    public CustList<ListSelection> getListeners() {
        return listSelections;
    }

    public void addListener(ListSelection _l) {
        listSelections.add(_l);
    }

    public void removeListener(ListSelection _l) {
        listSelections.removeObj(_l);
    }

    public AbsCustComponent getGlobal() {
        return this;
    }

    public Ints getSelectedIndexes() {
        return Ints.singleOrEmpty(getSelectedIndex());
    }

    private void fireEvent() {
        for (ListSelection l: getListeners()) {
            l.valueChanged(new SelectionInfo(selectedIndex, selectedIndex,true));
        }
    }
    public void popup() {
        popuped = true;
    }

    public boolean isPopuped() {
        return popuped;
    }
}
