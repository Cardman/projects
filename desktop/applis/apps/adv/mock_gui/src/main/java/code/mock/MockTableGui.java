package code.mock;

import code.gui.AbsTableGui;
import code.gui.events.AbsListSelectionListener;
import code.gui.events.AbsMouseListener;
import code.gui.events.AbsMouseListenerIntRel;
import code.util.CustList;
import code.util.IdList;
import code.util.Ints;
import code.util.StringList;
import code.util.core.NumberUtil;

public final class MockTableGui extends MockCustComponent implements AbsTableGui {
    private final CustList<AbsMouseListener> headers = new CustList<AbsMouseListener>();
    private final CustList<AbsMouseListenerIntRel> headersCl = new CustList<AbsMouseListenerIntRel>();
    private final IdList<AbsListSelectionListener> selection = new IdList<AbsListSelectionListener>();
    private boolean multiSelect = true;
    private boolean reorderingAllowed;
    private final StringList columnNames = new StringList();
    private final CustList<StringList> rows = new CustList<StringList>();
    private final Ints selected = new Ints();
    private int anchor;
    private int lead;

    public MockTableGui(String... _cols) {
        setColumnIdentifiers(_cols);
    }
    @Override
    public int[] getSelectedRows() {
        int nb_ = selected.size();
        int[] s_ = new int[nb_];
        for (int i = 0; i < nb_; i++) {
            s_[i] = selected.get(i);
        }
        return s_;
    }

    @Override
    public int getSelectedRow() {
        if (selected.isEmpty()) {
            return -1;
        }
        return selected.first();
    }

    @Override
    public int getSelectedRowCount() {
        return selected.size();
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public void clearSelect() {
        removeSelectInterval(0,getRowCount()-1);
    }

    @Override
    public void addSelectInterval(int _a, int _b) {
        anchor = _a;
        lead = _b;
        int m_ = NumberUtil.max(0, NumberUtil.min(_a,_b));
        int p_ = NumberUtil.min(rows.size()-1, NumberUtil.max(_a,_b));
        for (int j = m_;j <= p_;j++) {
            selected.add(j);
        }
        selected.sort();
    }

    @Override
    public void removeSelectInterval(int _a, int _b) {
        anchor = _a;
        lead = _b;
        int m_ = NumberUtil.max(0, NumberUtil.min(_a,_b));
        int p_ = NumberUtil.min(rows.size()-1, NumberUtil.max(_a,_b));
        for (int j = m_;j <= p_;j++) {
            selected.removeAllLong(j);
        }
    }

    @Override
    public int anc() {
        return anchor;
    }

    @Override
    public int lea() {
        return lead;
    }

    @Override
    public void setRowCount(int _value) {
        int rc_ = rows.size();
        if (_value > rc_) {
            int nbCols_ = columnNames.size();
            for (int j = rc_; j < _value; j++) {
                StringList row_ = new StringList();
                for (int k = 0; k < nbCols_; k++) {
                    row_.add("");
                }
                rows.add(row_);
            }
        } else {
            int j_ = rc_ -1;
            while (j_ >= NumberUtil.max(_value,0)) {
                rows.remove(j_);
                j_--;
            }
        }
    }

    @Override
    public String getColumnName(int _index) {
        return columnNames.get(_index);
    }

    @Override
    public String getValueAt(int _a, int _b) {
        return rows.get(_a).get(_b);
    }

    @Override
    public void setValueAt(String _value, int _a, int _b) {
        rows.get(_a).set(_b,_value);
    }

    @Override
    public void moveColumn(int _a, int _b) {
        if (_a == _b) {
            return;
        }
        String old_ = columnNames.get(_a);
        int rc_ = rows.size();
        if (_a < _b) {
            for (int k = _a; k < _b; k++) {
                columnNames.set(k,columnNames.get(k+1));
            }
            for (int r = 0; r < rc_; r++) {
                String oldc_ = rows.get(r).get(_a);
                for (int k = _a; k < _b; k++) {
                    rows.get(r).set(k,rows.get(r).get(k+1));
                }
                rows.get(r).set(_b,oldc_);
            }
            columnNames.set(_b,old_);
            return;
        }
        for (int k = _a; k > _b; k--) {
            columnNames.set(k,columnNames.get(k-1));
        }
        for (int r = 0; r < rc_; r++) {
            String oldc_ = rows.get(r).get(_a);
            for (int k = _a; k > _b; k--) {
                rows.get(r).set(k,rows.get(r).get(k-1));
            }
            rows.get(r).set(_b,oldc_);
        }
        columnNames.set(_b,old_);
    }

    @Override
    public int columnAtPoint(int _a, int _b) {
        return _a;
    }

    @Override
    public int rowAtPoint(int _a, int _b) {
        return _b;
    }

    @Override
    public boolean isMultiSelect() {
        return multiSelect;
    }

    @Override
    public void setMultiSelect(boolean _value) {
        if (_value) {
            setMultiSelect();
        } else {
            setSingleSelect();
        }
    }

    @Override
    public void setMultiSelect() {
        multiSelect = true;
    }

    @Override
    public void setSingleSelect() {
        multiSelect = false;
    }

    @Override
    public boolean isReorderingAllowed() {
        return reorderingAllowed;
    }

    @Override
    public void setReorderingAllowed(boolean _value) {
        reorderingAllowed = _value;
    }

    @Override
    public void addHeaderListener(AbsMouseListener _l) {
        headers.add(_l);
    }

    public CustList<AbsMouseListener> getHeaders() {
        return headers;
    }

    @Override
    public void addHeaderListener(AbsMouseListenerIntRel _l) {
        headersCl.add(_l);
    }

    public CustList<AbsMouseListenerIntRel> getHeadersCl() {
        return headersCl;
    }

    @Override
    public void addListSelectionListener(AbsListSelectionListener _l) {
        addListSelectionListenerMap(_l);
    }

    @Override
    public void addListSelectionListenerMap(AbsListSelectionListener _list) {
        selection.add(_list);
    }

    @Override
    public void removeListSelectionListener(AbsListSelectionListener _list) {
        removeListSelectionListenerMap(_list);
    }

    @Override
    public void removeListSelectionListenerMap(AbsListSelectionListener _list) {
        selection.removeObj(_list);
    }

    @Override
    public CustList<AbsListSelectionListener> getListSelectionListeners() {
        return selection;
    }

    public CustList<AbsListSelectionListener> getSelection() {
        return getListSelectionListeners();
    }

    @Override
    public void setColumnIdentifiers(String[] _cols) {
        columnNames.clear();
        for (String c: _cols) {
            columnNames.add(c);
        }
    }
}
