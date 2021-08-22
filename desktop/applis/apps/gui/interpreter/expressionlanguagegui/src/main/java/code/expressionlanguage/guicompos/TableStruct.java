package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.structs.*;
import code.gui.CustComponent;
import code.gui.TableGui;
import code.gui.events.AbsListSelectionListener;
import code.gui.events.AbsMouseListener;

import javax.swing.event.ListSelectionListener;

public final class TableStruct extends CustComponentStruct {
    private TableGui table;
    protected TableStruct(String _className, Struct _array) {
        super(_className);
        table = new TableGui(getColNames(_array));
    }

    private static String[] getColNames(Struct _array) {
        if (!(_array instanceof ArrayStruct)) {
            return new String[0];
        }
        ArrayStruct a_ = (ArrayStruct) _array;
        int len_ = a_.getLength();
        String[] str_ = new String[len_];
        for (int i = 0; i < len_; i++) {
            Struct s_ = a_.get(i);
            if (s_ instanceof StringStruct) {
                str_[i] = ((StringStruct)s_).getInstance();
            } else {
                str_[i] = "";
            }
        }
        return str_;
    }

    public IntStruct getSelectedRow() {
        return new IntStruct(table.getSelectedRow());
    }

    public ArrayStruct getSelectedRows(ContextEl _cont) {
        String int_ = _cont.getStandards().getContent().getPrimTypes().getAliasPrimInteger();
        int_ = StringExpUtil.getPrettyArrayType(int_);
        int[] rows_ = table.getSelectedRows();
        int len_ = rows_.length;
        ArrayStruct arr_ = new ArrayStruct(len_,int_);
        for (int i =0; i < len_; i++) {
            arr_.set(i, new IntStruct(rows_[i]));
        }
        return arr_;
    }

    public IntStruct getSelectedRowCount() {
        return new IntStruct(table.getSelectedRowCount());
    }

    public void addSelectInterval(Struct _from, Struct _to) {
        table.addSelectInterval(((NumberStruct)_from).intStruct(),((NumberStruct)_to).intStruct());
    }

    public void removeSelectInterval(Struct _from, Struct _to) {
        table.removeSelectInterval(((NumberStruct)_from).intStruct(),((NumberStruct)_to).intStruct());
    }
    public void setRowCount(Struct _rowCount) {
        table.setRowCount(((NumberStruct)_rowCount).intStruct());
    }
    public IntStruct getRowCount() {
        return new IntStruct(table.getRowCount());
    }

    public IntStruct getColumnCount() {
        return new IntStruct(table.getColumnCount());
    }

    public Struct getColumnName(Struct _column) {
        String v_ = table.getColumnName(((NumberStruct) _column).intStruct());
        if (v_ == null) {
            return new StringStruct("");
        }
        return new StringStruct(v_);
    }

    public Struct getValueAt(Struct _row, Struct _column) {
        String v_ = table.getValueAt(((NumberStruct)_row).intStruct(), ((NumberStruct)_column).intStruct());
        if (v_ == null) {
            return new StringStruct("");
        }
        return new StringStruct(v_);
    }

    public void setValueAt(Struct _aValue, Struct _row, Struct _column) {
        table.setValueAt(getString(_aValue), ((NumberStruct)_row).intStruct(), ((NumberStruct)_column).intStruct());
    }

    private String getString(Struct _s) {
        if (_s instanceof StringStruct) {
            return ((StringStruct)_s).getInstance();
        }
        return "";
    }
    public void moveColumn(Struct _column, Struct _targetColumn) {
        table.moveColumn(((NumberStruct)_column).intStruct(), ((NumberStruct)_targetColumn).intStruct());
    }

    public IntStruct columnAtPoint(Struct _x,Struct _y) {
        return new IntStruct(table.columnAtPoint(((NumberStruct)_x).intStruct(),((NumberStruct)_y).intStruct()));
    }

    public IntStruct rowAtPoint(Struct _x,Struct _y) {
        return new IntStruct(table.rowAtPoint(((NumberStruct)_x).intStruct(),((NumberStruct)_y).intStruct()));
    }

    public void applyChanges() {
        table.applyChanges();
    }

    public BooleanStruct isMultiSelect() {
        return BooleanStruct.of(table.isMultiSelect());
    }
    public void setMultiSelect(Struct _mult) {
        table.setMultiSelect(BooleanStruct.isTrue(_mult));
    }

    public void setColumnIdentifiers(Struct _cols) {
        table.setColumnIdentifiers(getColNames(_cols));
    }

    public BooleanStruct isReorderingAllowed() {
        return BooleanStruct.of(table.isReorderingAllowed());
    }
    public void setReorderingAllowed(Struct _b) {
        table.setReorderingAllowed(BooleanStruct.isTrue(_b));
    }

    public void addHeaderListener(Struct _list) {
        if (_list instanceof AbsMouseListener) {
            table.addHeaderListener((AbsMouseListener) _list);
        }
    }

    public void addListSelectionListener(Struct _select) {
        if (_select instanceof AbsListSelectionListener) {
            table.addListSelectionListener((AbsListSelectionListener) _select);
        }
    }
    @Override
    protected CustComponent getComponent() {
        return table;
    }
}
