package code.mock;

import code.gui.*;
import code.util.CustList;
import code.util.IdList;
import code.util.Ints;

public class MockCustGrList<T> extends MockInput {
    private final CustList<T> elts = new CustList<T>();
    private final IdList<ListSelection> listSelections = new IdList<ListSelection>();
    private int visibleRowCount = 8;
    private Ints selectedIndexes = new Ints();

    public void add(T _t) {
        elts.add(elts.size(),_t);
    }

    public void add(int _i, T _t) {
        elts.add(_i,_t);
    }

    public void set(int _i, T _t) {
        elts.set(_i,_t);
    }

    public void clear() {
        elts.clear();
    }

    public void clearSelection() {
        clearAllRange();
    }

    public void clearRevalidate() {
        clear();
        fireEvent();
    }

    public int size() {
        return elts.size();
    }

    public void remove(int _i) {
        elts.remove(_i);
    }

    public int getVisibleRowCount() {
        return visibleRowCount;
    }

    public void setListener(ListSelection _l) {
        listSelections.clear();
        listSelections.add(_l);
    }

    public void clearAllRange() {
        selectedIndexes.clear();
        fireEvent();
    }

    public void setSelectedIndice(int _i) {
        selectedIndexes.clear();
        if (elts.isValidIndex(_i)) {
            selectedIndexes.add(_i);
        }
        fireEvent();
    }

    public void setVisibleRowCount(int _i) {
        visibleRowCount = _i;
    }

    public int getSelectedIndex() {
        return (int) selectedIndexes.getMinimum(-1);
    }

    public boolean isSelectionEmpty() {
        return selectedIndexes.isEmpty();
    }

    public boolean isEmpty() {
        return elts.isEmpty();
    }

    public CustList<T> getList() {
        return elts;
    }

    public int getSelectedValuesLsLen() {
        return getSelectedIndexes().size();
    }

    public T get(int _i) {
        return elts.get(_i);
    }

    public T last() {
        return elts.last();
    }

    public AbsCustComponent scroll() {
        return this;
    }

    public AbsCustComponent getGlobal() {
        return scroll();
    }

    public AbsCustComponent visible() {
        return this;
    }

    public Ints getSelectedIndexes() {
        return selectedIndexes;
    }

    public void setSelectedIndexes(Ints _ints) {
        Ints selected_ = new Ints();
        for (int i: _ints) {
            if (elts.isValidIndex(i)) {
                selected_.add(i);
            }
        }
        selectedIndexes = selected_;
        fireEvent();
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

    private void fireEvent() {
        for (ListSelection l: getListeners()) {
            l.valueChanged(new SelectionInfo((int)selectedIndexes.getMinimum(-1), (int)selectedIndexes.getMaximum(-1),true));
        }
    }
}
