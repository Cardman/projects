package code.gui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public final class TableGui extends CustComponent {
    private JTable table;

    private DefaultTableModel model;
    public TableGui() {
        this(new String[0]);
    }

    public TableGui(String... _cols) {
        DefaultTableModel d_ = newModel(_cols);
        table = new JTable(d_);
        model = d_;
    }
    private static DefaultTableModel newModel(String... _cols) {
        return new DefaultTableModel(_cols,0);
    }

    JTableHeader getTableHeader() {
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

    public Object getValueAt(int row, int column) {
        return table.getValueAt(row, column);
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

    ListSelectionModel getSelectionModel() {
        return table.getSelectionModel();
    }

    @Override
    public JComponent getComponent() {
        return table;
    }

    public void setColumnIdentifiers(String[] _cols) {
        model.setColumnIdentifiers(_cols);
    }
}
