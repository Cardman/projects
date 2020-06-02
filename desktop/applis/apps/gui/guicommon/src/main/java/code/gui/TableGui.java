package code.gui;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.Point;
import java.awt.event.MouseListener;

public final class TableGui extends CustComponent {
    private JTable table;

    private DefaultTableModel model;

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

    public String getColumnName(int column) {
        return table.getColumnName(column);
    }

    public String getValueAt(int row, int column) {
        Object v_ = table.getValueAt(row, column);
        if (!(v_ instanceof String)) {
            return "";
        }
        return (String) v_;
    }

    public void setValueAt(String aValue, int row, int column) {
        table.setValueAt(aValue, row, column);
    }

    public void moveColumn(int column, int targetColumn) {
        table.moveColumn(column, targetColumn);
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
        if (_mult) {
            table.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        } else {
            table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
    }
    private ListSelectionModel getSelectionModel() {
        return table.getSelectionModel();
    }

    @Override
    protected JComponent getComponent() {
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

    public void addHeaderListener(MouseListener _list) {
        getTableHeader().addMouseListener(_list);
    }

    public void addListSelectionListener(ListSelectionListener _select) {
        getSelectionModel().addListSelectionListener(_select);
    }
}
