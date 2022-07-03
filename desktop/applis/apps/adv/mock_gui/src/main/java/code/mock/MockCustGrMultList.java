package code.mock;

import code.gui.*;
import code.util.CustList;
import code.util.Ints;

public final class MockCustGrMultList extends MockInput implements Input, AbsGraphicList<String> {

    private final MockCustGrList<String> compo = new MockCustGrList<String>();

    @Override
    public void add(String _s) {
        compo.add(_s);
    }

    @Override
    public void add(int _i, AbsPreparedLabel _l, String _s) {
        compo.add(_i,_s);
    }

    @Override
    public void add(int _i, String _s) {
        compo.add(_i,_s);
    }

    @Override
    public void set(int _i, String _s) {
        compo.set(_i,_s);
    }

    @Override
    public int set(int _i, AbsPreparedLabel _l, String _s) {
        compo.set(_i,_s);
        return _i;
    }

    @Override
    public void clear() {
        compo.clear();
    }

    @Override
    public void clearSelection() {
        compo.clearSelection();
    }

    @Override
    public void clearRevalidate() {
        compo.clearRevalidate();
    }

    @Override
    public int size() {
        return compo.size();
    }

    @Override
    public void remove(int _i) {
        compo.remove(_i);
    }

    @Override
    public void setListener(ListSelection _l) {
        compo.setListener(_l);
    }

    @Override
    public void clearAllRange() {
        compo.clearAllRange();
    }

    @Override
    public void setSelectedIndice(int _i) {
        compo.setSelectedIndice(_i);
    }

    @Override
    public void setVisibleRowCount(int _i) {
        compo.setVisibleRowCount(_i);
    }

    @Override
    public int getSelectedIndex() {
        return compo.getSelectedIndex();
    }

    @Override
    public boolean isSelectionEmpty() {
        return compo.isSelectionEmpty();
    }

    @Override
    public boolean isEmpty() {
        return compo.isEmpty();
    }

    @Override
    public CustList<String> getList() {
        return compo.getList();
    }

    @Override
    public int getSelectedValuesLsLen() {
        return compo.getSelectedValuesLsLen();
    }

    @Override
    public String get(int _i) {
        return compo.get(_i);
    }

    @Override
    public String last() {
        return compo.last();
    }

    @Override
    public AbsCustComponent self() {
        return compo.self();
    }

    @Override
    public AbsCustComponent scroll() {
        return compo.scroll();
    }

    @Override
    public AbsCustComponent visible() {
        return compo.visible();
    }

    @Override
    public AbsCustComponent getGlobal() {
        return compo.scroll();
    }

    @Override
    public Ints getSelectedIndexes() {
        return compo.getSelectedIndexes();
    }
}
