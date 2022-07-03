package code.mock;

import code.gui.AbsCustComponent;
import code.gui.GraphicComboGrInt;
import code.gui.ListSelection;
import code.gui.SelectionInfo;
import code.util.CustList;
import code.util.IdList;
import code.util.Ints;
import code.util.StringList;

public final class MockComboBox extends MockInput implements GraphicComboGrInt {

    private final IdList<ListSelection> listSelections = new IdList<ListSelection>();
    private int selectedIndex = -1;
    private final StringList items = new StringList();
    private boolean popuped;

    public MockComboBox(StringList _list, int _selectedIndex) {
        items.addAllElts(_list);
        selectItem(_selectedIndex);
    }

    @Override
    public AbsCustComponent getCurrentSelected() {
        return this;
    }

    @Override
    public void simpleSelectItem(int _index) {
        int index_ = Math.min(getItemCount()-1,_index);
        index_ = Math.max(index_,-1);
        selectedIndex = index_;
        fireEvent();
    }


    @Override
    public String getSelectedItem() {
        int selectedIndex_ = getSelectedIndex();
        if (!items.isValidIndex(selectedIndex_)) {
            return null;
        }
        return items.get(selectedIndex_);
    }

    @Override
    public void simpleRemoveAllItems() {
        clearFire();
    }

    @Override
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

    @Override
    public AbsCustComponent self() {
        return this;
    }

    @Override
    public int getSelectedIndex() {
        return selectedIndex;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void removeItem(int _index) {
        remFire(_index);
    }

    @Override
    public void selectItem(int _index) {
        simpleSelectItem(_index);
    }

    @Override
    public void removeAllItems() {
        clearFire();
    }

    private void clearFire() {
        items.clear();
        selectedIndex = -1;
        fireEvent();
    }

    @Override
    public void addItem(String _object) {
        items.add(_object);
        if (items.size() == 1) {
            selectedIndex = 0;
            fireEvent();
        }
    }

    @Override
    public void setListener(ListSelection _l) {
        listSelections.clear();
        listSelections.add(_l);
    }

    @Override
    public CustList<ListSelection> getListeners() {
        return listSelections;
    }

    @Override
    public void addListener(ListSelection _l) {
        listSelections.add(_l);
    }

    @Override
    public void removeListener(ListSelection _l) {
        listSelections.removeObj(_l);
    }

    @Override
    public AbsCustComponent getGlobal() {
        return this;
    }

    @Override
    public Ints getSelectedIndexes() {
        return Ints.singleOrEmpty(getSelectedIndex());
    }

    private void fireEvent() {
        for (ListSelection l: getListeners()) {
            l.valueChanged(new SelectionInfo(selectedIndex, selectedIndex,true));
        }
    }
    @Override
    public void popup() {
        popuped = true;
    }

    public boolean isPopuped() {
        return popuped;
    }
}
