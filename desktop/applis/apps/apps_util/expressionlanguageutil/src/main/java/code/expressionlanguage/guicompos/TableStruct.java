package code.expressionlanguage.guicompos;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.StackCall;
import code.expressionlanguage.structs.*;
import code.gui.AbsCustComponent;
import code.gui.AbsTableGui;
import code.gui.events.AbsListSelectionListener;
import code.gui.events.AbsMouseListener;
import code.gui.initialize.AbsCompoFactory;
import code.util.CustList;
import code.util.core.NumberUtil;

public final class TableStruct extends CustComponentStruct {
    private final AbsTableGui table;
    public TableStruct(String _className, Struct _array, AbsCompoFactory _compoFactory) {
        super(_className);
        table = _compoFactory.newTableGui(getColNames(_array));
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
            str_[i] = NumParsers.getString(s_).getInstance();
        }
        return str_;
    }
    public static int[] retrieveBoundsAdd(int[] _selected, int _oldFirst, int _oldLast, int _newAnc, int _newLead) {
        int[] arr_ = retrieveBoundsAdd(_selected, _newAnc, _newLead);
        return ancLea(arr_, _oldFirst, _newAnc, _oldLast, _newLead, new long[]{Long.MAX_VALUE, -1});
    }
    public static int[] retrieveBoundsAddSingle(int[] _selected, int _oldFirst, int _oldLast, int _newLead) {
        int[] addSel_ = retrieveBoundsAdd(_selected, _newLead, _newLead);
        int[] remSel_;
        if (_selected.length > 0) {
            remSel_ = retrieveBoundsRem(_selected, _selected[0], _selected[_selected.length-1]);
        } else {
            remSel_ = new int[0];
        }
        long[] minMax_ = new long[]{Long.MAX_VALUE,-1};
        feedIfDefined(remSel_,minMax_);
        feedIfDefined(addSel_,minMax_);
        return ancLea(addSel_, _oldFirst, _newLead, _oldLast, _newLead, minMax_);
    }

    public static int[] retrieveBoundsRem(int[] _selected, int _oldFirst, int _oldLast, int _newAnc, int _newLead) {
        int[] arr_ = retrieveBoundsRem(_selected, _newAnc, _newLead);
        return ancLea(arr_, _oldFirst, _newAnc, _oldLast, _newLead, new long[]{Long.MAX_VALUE, -1});
    }


    public static int[] retrieveBoundsRemSingle(int[] _selected, int _oldFirst, int _oldLast, int _newAnc, int _newLead) {
        int[] arr_;
        int f_ = NumberUtil.min(_newAnc,_newLead);
        int l_ = NumberUtil.max(_newAnc,_newLead);
        if (_selected.length > 0) {
            int maxSel_ = _selected[_selected.length - 1];
            if (f_ > _selected[0] && l_ < maxSel_) {
                arr_ = retrieveBoundsRem(_selected, f_, maxSel_);
            } else {
                arr_ = retrieveBoundsRem(_selected, f_, l_);
            }
        } else {
            arr_ = retrieveBoundsRem(_selected, f_, l_);
        }
        return ancLea(arr_, _oldFirst, _newAnc, _oldLast, _newLead, new long[]{Long.MAX_VALUE, -1});
    }

    private static int[] ancLea(int[] _arr, int _oldFirst, int _newAnc, int _oldLast, int _newLead, long[] _minMax) {
        feedIfDefined(_arr, _minMax);
        notifAncLea(_oldFirst, _newAnc, _oldLast, _newLead, _minMax);
        if (_minMax[0]> _minMax[1]) {
            return new int[0];
        }
        return new int[]{(int) _minMax[0], (int) _minMax[1]};
    }

    private static void feedIfDefined(int[] _arr, long[] _minMax) {
        if (_arr.length > 0) {
            _minMax[0]=NumberUtil.min(_minMax[0],_arr[0]);
            _minMax[1]=NumberUtil.max(_minMax[1],_arr[_arr.length-1]);
        }
    }

    private static void notifAncLea(int _oldFirst, int _newAnc, int _oldLast, int _newLead, long[] _minMax) {
        feed(_oldFirst, _oldFirst != _newAnc,_minMax);
        feed(_newAnc, _oldFirst != _newAnc,_minMax);
        feed(_oldLast, _oldLast != _newLead,_minMax);
        feed(_newLead, _oldLast != _newLead,_minMax);
    }

    private static void feed(int _previous, boolean _suppCond, long[] _minMax) {
        if (_suppCond && _previous >= 0) {
            _minMax[0]=NumberUtil.min(_minMax[0],_previous);
            _minMax[1]=NumberUtil.max(_minMax[1],_previous);
        }
    }

    public static int[] retrieveBoundsAdd(int[] _selected, int _newAnc, int _newLead) {
        int from_ = NumberUtil.min(_newAnc,_newLead);
        int to_ = NumberUtil.max(_newAnc,_newLead);
        boolean wasNotSel_ = false;
        for (int f= from_; f <= to_; f++) {
            if (!containsSorted(_selected,f)) {
                wasNotSel_ = true;
                break;
            }
        }
        if (!wasNotSel_) {
            return new int[0];
        }
        int[] sel_ = nextSelectRangeAdd(_selected, _newAnc, _newLead);
        int nextMin_ = sel_[0];
        int nextMax_ = sel_[sel_.length-1];
        return new int[]{nextMin_,nextMax_};
    }

    public static int[] retrieveBoundsRem(int[] _selected, int _newAnc, int _newLead) {
        int from_ = NumberUtil.min(_newAnc,_newLead);
        int to_ = NumberUtil.max(_newAnc,_newLead);
        boolean wasSel_ = false;
        for (int f= from_; f <= to_; f++) {
            if (containsSorted(_selected,f)) {
                wasSel_ = true;
                break;
            }
        }
        if (!wasSel_) {
            return new int[0];
        }
        int[] sel_ = nextSelectRangeRem(_selected, _newAnc, _newLead);
        int nextMin_ = sel_[0];
        int nextMax_ = sel_[sel_.length-1];
        return new int[]{nextMin_,nextMax_};
    }
    private static boolean containsSorted(int[] _arr, int _v) {
        if (_arr.length <= 0) {
            return false;
        }
        int l_ = 0;
        int u_ = _arr.length-1;
        if (_v < _arr[0] || _v > _arr[u_]) {
            return false;
        }
        while (l_ <= u_) {
            int m_ = (l_ + u_) / 2;
            int t_ = _arr[m_];
            if (t_ == _v) {
                return true;
            }
            if (t_ < _v) {
                l_ = m_+1;
            } else {
                u_ = m_-1;
            }
        }
        return false;
    }
    private static int[] nextSelectRangeAdd(int[] _selected, int _newAnc, int _newLead) {
        int from_ = NumberUtil.min(_newAnc,_newLead);
        int to_ = NumberUtil.max(_newAnc,_newLead);
        if (_selected.length <= 0) {
            return new int[]{from_, to_};
        }
        int minSel_ = _selected[0];
        int maxSel_ = _selected[_selected.length-1];
        int nextMin_;
        int nextMax_;
        if (to_ < minSel_ || from_ > maxSel_ || from_ >= minSel_ && to_ <= maxSel_) {
            nextMin_ = from_;
            nextMax_ = to_;
        } else if (from_ >= minSel_) {
            nextMin_ = maxSel_+1;
            nextMax_ = to_;
        } else {
            nextMin_ = from_;
            nextMax_ = minSel_-1;
        }
        return new int[]{nextMin_,nextMax_};
    }
    private static int[] nextSelectRangeRem(int[] _selected, int _newAnc, int _newLead) {
        int from_ = NumberUtil.min(_newAnc,_newLead);
        int to_ = NumberUtil.max(_newAnc,_newLead);
        int nextMin_;
        int nextMax_;
        int minSel_ = _selected[0];
        int maxSel_ = _selected[_selected.length-1];
        if (from_ >= minSel_ && to_ <= maxSel_) {
            nextMin_ = from_;
            nextMax_ = to_;
        } else if (from_ >= minSel_) {
            nextMin_ = from_;
            nextMax_ = maxSel_+1;
        } else {
            nextMin_ = minSel_-1;
            nextMax_ = to_;
        }
        return new int[]{nextMin_,nextMax_};
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

    public int[] addSelectInterval(Struct _from, Struct _to, StackCall _stackCall) {
        if (_stackCall.getStopper().getLogger() != null) {
            if (table.isMultiSelect()) {
                int[] rg_ = retrieveBoundsAdd(table.getSelectedRows(), table.anc(), table.lea(), ((NumberStruct) _from).intStruct(), ((NumberStruct) _to).intStruct());
                table.addSelectInterval(((NumberStruct)_from).intStruct(),((NumberStruct)_to).intStruct());
                return rg_;
            }
            int[] rg_ = retrieveBoundsAddSingle(table.getSelectedRows(), table.anc(), table.lea(), ((NumberStruct) _to).intStruct());
            table.addSelectInterval(((NumberStruct)_from).intStruct(),((NumberStruct)_to).intStruct());
            return rg_;
        }
        table.addSelectInterval(((NumberStruct)_from).intStruct(),((NumberStruct)_to).intStruct());
        return new int[0];
    }

    public int[] removeSelectInterval(Struct _from, Struct _to, StackCall _stackCall) {
        if (_stackCall.getStopper().getLogger() != null) {
            if (table.isMultiSelect()) {
                int[] rg_ = retrieveBoundsRem(table.getSelectedRows(), table.anc(), table.lea(), ((NumberStruct) _from).intStruct(), ((NumberStruct) _to).intStruct());
                table.removeSelectInterval(((NumberStruct)_from).intStruct(),((NumberStruct)_to).intStruct());
                return rg_;
            }
            int[] rg_ = retrieveBoundsRemSingle(table.getSelectedRows(), table.anc(), table.lea(), -1, ((NumberStruct) _to).intStruct());
            table.removeSelectInterval(((NumberStruct)_from).intStruct(),((NumberStruct)_to).intStruct());
            return rg_;
        }
        table.removeSelectInterval(((NumberStruct)_from).intStruct(),((NumberStruct)_to).intStruct());
        return new int[0];
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
//        if (v_ == null) {
//            return new StringStruct("");
//        }
        return new StringStruct(v_);
    }

    public Struct getValueAt(Struct _row, Struct _column) {
        String v_ = table.getValueAt(((NumberStruct)_row).intStruct(), ((NumberStruct)_column).intStruct());
//        if (v_ == null) {
//            return new StringStruct("");
//        }
        return new StringStruct(v_);
    }

    public void setValueAt(Struct _aValue, Struct _row, Struct _column) {
        table.setValueAt(getString(_aValue), ((NumberStruct)_row).intStruct(), ((NumberStruct)_column).intStruct());
    }

    private String getString(Struct _s) {
        return NumParsers.getString(_s).getInstance();
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

    public void addListSelectionListener(Struct _select, StackCall _stackCall) {
        if (_select instanceof AbsListSelectionListener) {
            if (_stackCall.getStopper().getLogger() != null) {
                table.addListSelectionListenerMap((AbsListSelectionListener) _select);
            } else {
                table.addListSelectionListener((AbsListSelectionListener) _select);
            }
        }
    }

    public void remListSelectionListener(Struct _select, StackCall _stackCall) {
        if (_select instanceof AbsListSelectionListener) {
            if (_stackCall.getStopper().getLogger() != null) {
                table.removeListSelectionListenerMap((AbsListSelectionListener) _select);
            } else {
                table.removeListSelectionListener((AbsListSelectionListener) _select);
            }
        }
    }
    public ArrayStruct getListSelect(ContextEl _ctx) {
        String aliasWheelListener_ = ((LgNamesGui) _ctx.getStandards()).getGuiAliases().getAliasTableListener();
        CustList<AbsListSelectionListener> listSel_ = table.getListSelectionListeners();
        CustList<Struct> res_ = new CustList<Struct>();
        int lenBase_ = listSel_.size();
        for (int i = 0; i < lenBase_; i++) {
            if (listSel_.get(i) instanceof Struct) {
                res_.add((Struct)listSel_.get(i));
            }
        }
        return nulls(aliasWheelListener_, res_);
    }

    public AbsTableGui getTable() {
        return table;
    }

    @Override
    protected AbsCustComponent getComponent() {
        return getTable();
    }
}
