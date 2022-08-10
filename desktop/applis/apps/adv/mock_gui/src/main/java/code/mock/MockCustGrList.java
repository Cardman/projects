package code.mock;

import code.gui.*;
import code.util.CustList;
import code.util.IdList;
import code.util.Ints;

public class MockCustGrList<T> extends MockInput implements AbsGraphicList<T>, AbsGraphicListDef {
    private final CustList<T> elts = new CustList<T>();
    private final IdList<ListSelection> listSelections = new IdList<ListSelection>();
    private int visibleRowCount = 8;
    private Ints selectedIndexes = new Ints();
    @Override
    public void add(T _t) {
        elts.add(elts.size(),_t);
    }

    @Override
    public void add(int _i, AbsPreparedLabel _l, T _t) {
        elts.add(_i,_t);
    }

    @Override
    public void add(int _i, T _t) {
        elts.add(_i,_t);
    }

    @Override
    public void set(int _i, T _t) {
        elts.set(_i,_t);
    }

    @Override
    public int set(int _i, AbsPreparedLabel _l, T _t) {
        elts.set(_i,_t);
        return _i;
    }

    @Override
    public void clear() {
        elts.clear();
    }

    @Override
    public void clearSelection() {
        clearAllRange();
    }

    @Override
    public void clearRevalidate() {
        clear();
        fireEvent();
    }

    @Override
    public int size() {
        return elts.size();
    }

    @Override
    public void remove(int _i) {
        elts.remove(_i);
    }

    @Override
    public int getVisibleRowCount() {
        return visibleRowCount;
    }

    @Override
    public void setListener(ListSelection _l) {
        listSelections.clear();
        listSelections.add(_l);
    }

    @Override
    public void clearAllRange() {
        selectedIndexes.clear();
        fireEvent();
    }

    @Override
    public void setSelectedIndice(int _i) {
        selectedIndexes.clear();
        if (elts.isValidIndex(_i)) {
            selectedIndexes.add(_i);
        }
        fireEvent();
    }

    @Override
    public void setVisibleRowCount(int _i) {
        visibleRowCount = _i;
    }

    @Override
    public int getSelectedIndex() {
        return (int) selectedIndexes.getMinimum(-1);
    }

    @Override
    public boolean isSelectionEmpty() {
        return selectedIndexes.isEmpty();
    }

    @Override
    public boolean isEmpty() {
        return elts.isEmpty();
    }

    @Override
    public CustList<T> getList() {
        return elts;
    }

    @Override
    public int getSelectedValuesLsLen() {
        return getSelectedIndexes().size();
    }

    @Override
    public T get(int _i) {
        return elts.get(_i);
    }

    @Override
    public T last() {
        return elts.last();
    }

    @Override
    public AbsCustComponent self() {
        return this;
    }

    @Override
    public AbsCustComponent scroll() {
        return this;
    }

    @Override
    public AbsCustComponent getGlobal() {
        return scroll();
    }

    @Override
    public AbsCustComponent visible() {
        return this;
    }

    @Override
    public Ints getSelectedIndexes() {
        return selectedIndexes;
    }

    @Override
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

    private void fireEvent() {
        for (ListSelection l: getListeners()) {
            l.valueChanged(new SelectionInfo((int)selectedIndexes.getMinimum(-1), (int)selectedIndexes.getMaximum(-1),true));
        }
    }
}
