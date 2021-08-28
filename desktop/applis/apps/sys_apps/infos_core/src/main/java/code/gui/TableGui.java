package code.gui;

import code.gui.events.AbsListSelectionListener;
import code.gui.events.AbsMouseListener;
import code.gui.events.WrListSelectionListener;
import code.gui.events.WrMouseListener;
import code.util.core.StringUtil;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;


public final class TableGui extends CustComponent implements AbsTableGui {
    private final JTable table;

    private final DefaultTableModel model;

    public TableGui(String... _cols) {
        DefaultTableModel d_ = newModel(_cols);
        table = new JTable(d_);
        model = d_;
    }
    private static DefaultTableModel newModel(String... _cols) {
        return new DefaultTableModel(_cols,0);
    }

    private JTableHeader getTableHeader() {
        return table.getTableHeader();
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    public int[] getSelectedRows() {
        return table.getSelectedRows();
    }

    public int getSelectedRowCount() {
        return table.getSelectedRowCount();
    }

    public void addSelectInterval(int _from, int _to) {
        table.getSelectionModel().addSelectionInterval(_from,_to);
    }

    public void removeSelectInterval(int _from, int _to) {
        table.getSelectionModel().removeIndexInterval(_from,_to);
    }
    public void setRowCount(int _rowCount) {
        model.setRowCount(_rowCount);
    }
    public int getRowCount() {
        return table.getRowCount();
    }

    public int getColumnCount() {
        return table.getColumnCount();
    }

    public String getColumnName(int _column) {
        return table.getColumnName(_column);
    }

    public String getValueAt(int _row, int _column) {
        return String.valueOf(table.getValueAt(_row, _column));
    }

    public void setValueAt(String _aValue, int _row, int _column) {
        table.setValueAt(StringUtil.nullToEmpty(_aValue), _row, _column);
    }

    public void moveColumn(int _column, int _targetColumn) {
        table.moveColumn(_column, _targetColumn);
    }

    public int columnAtPoint(int _x,int _y) {
        return table.columnAtPoint(new Point(_x,_y));
    }

    public int rowAtPoint(int _x,int _y) {
        return table.rowAtPoint(new Point(_x,_y));
    }

    public void applyChanges() {
        model.fireTableDataChanged();
        model.fireTableStructureChanged();
    }

    public boolean isMultiSelect() {
        return table.getSelectionModel().getSelectionMode() == ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
    }
    public void setMultiSelect(boolean _mult) {
        int value_ = GuiConstants.getSelectTable(_mult);
        table.getSelectionModel().setSelectionMode(value_);
    }

    private ListSelectionModel getSelectionModel() {
        return table.getSelectionModel();
    }

    @Override
    protected JComponent getNatComponent() {
        return table;
    }

    public void setColumnIdentifiers(String[] _cols) {
        model.setColumnIdentifiers(_cols);
    }

    public boolean isReorderingAllowed() {
        return getTableHeader().getReorderingAllowed();
    }
    public void setReorderingAllowed(boolean _b) {
        getTableHeader().setReorderingAllowed(_b);
    }

    public void addHeaderListener(AbsMouseListener _list) {
        getTableHeader().addMouseListener(new WrMouseListener(_list));
    }

    public void addListSelectionListener(AbsListSelectionListener _select) {
        getSelectionModel().addListSelectionListener(new WrListSelectionListener(_select));
    }

}
