package code.gui;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public final class TableGui extends CustComponent {
    private JTable table;
    public TableGui() {
        table = new JTable();
    }
    public TableGui(DefaultTableModel _d) {
        table = new JTable(_d);
    }

    public void addNotify() {
        table.addNotify();
    }

    public void removeNotify() {
        table.removeNotify();
    }

    public void setTableHeader(JTableHeader tableHeader) {
        table.setTableHeader(tableHeader);
    }

    public JTableHeader getTableHeader() {
        return table.getTableHeader();
    }

    public void setRowHeight(int rowHeight) {
        table.setRowHeight(rowHeight);
    }

    public int getRowHeight() {
        return table.getRowHeight();
    }

    public void setRowHeight(int row, int rowHeight) {
        table.setRowHeight(row, rowHeight);
    }

    public int getRowHeight(int row) {
        return table.getRowHeight(row);
    }

    public void setRowMargin(int rowMargin) {
        table.setRowMargin(rowMargin);
    }

    public int getRowMargin() {
        return table.getRowMargin();
    }

    public void setIntercellSpacing(Dimension intercellSpacing) {
        table.setIntercellSpacing(intercellSpacing);
    }

    public Dimension getIntercellSpacing() {
        return table.getIntercellSpacing();
    }

    public void setGridColor(Color gridColor) {
        table.setGridColor(gridColor);
    }

    public Color getGridColor() {
        return table.getGridColor();
    }

    public void setShowGrid(boolean showGrid) {
        table.setShowGrid(showGrid);
    }

    public void setShowHorizontalLines(boolean showHorizontalLines) {
        table.setShowHorizontalLines(showHorizontalLines);
    }

    public void setShowVerticalLines(boolean showVerticalLines) {
        table.setShowVerticalLines(showVerticalLines);
    }

    public boolean getShowHorizontalLines() {
        return table.getShowHorizontalLines();
    }

    public boolean getShowVerticalLines() {
        return table.getShowVerticalLines();
    }

    public void setAutoResizeMode(int mode) {
        table.setAutoResizeMode(mode);
    }

    public int getAutoResizeMode() {
        return table.getAutoResizeMode();
    }

    public void setAutoCreateColumnsFromModel(boolean autoCreateColumnsFromModel) {
        table.setAutoCreateColumnsFromModel(autoCreateColumnsFromModel);
    }

    public boolean getAutoCreateColumnsFromModel() {
        return table.getAutoCreateColumnsFromModel();
    }

    public void createDefaultColumnsFromModel() {
        table.createDefaultColumnsFromModel();
    }

    public void setDragEnabled(boolean b) {
        table.setDragEnabled(b);
    }

    public boolean getDragEnabled() {
        return table.getDragEnabled();
    }

    public DropMode getDropMode() {
        return table.getDropMode();
    }

    public void setAutoCreateRowSorter(boolean autoCreateRowSorter) {
        table.setAutoCreateRowSorter(autoCreateRowSorter);
    }

    public boolean getAutoCreateRowSorter() {
        return table.getAutoCreateRowSorter();
    }

    public void setUpdateSelectionOnSort(boolean update) {
        table.setUpdateSelectionOnSort(update);
    }

    public void setSelectionMode(int selectionMode) {
        table.setSelectionMode(selectionMode);
    }

    public void setRowSelectionAllowed(boolean rowSelectionAllowed) {
        table.setRowSelectionAllowed(rowSelectionAllowed);
    }

    public boolean getRowSelectionAllowed() {
        return table.getRowSelectionAllowed();
    }

    public void setColumnSelectionAllowed(boolean columnSelectionAllowed) {
        table.setColumnSelectionAllowed(columnSelectionAllowed);
    }

    public boolean getColumnSelectionAllowed() {
        return table.getColumnSelectionAllowed();
    }

    public void setCellSelectionEnabled(boolean cellSelectionEnabled) {
        table.setCellSelectionEnabled(cellSelectionEnabled);
    }

    public boolean getCellSelectionEnabled() {
        return table.getCellSelectionEnabled();
    }

    public void selectAll() {
        table.selectAll();
    }

    public void clearSelection() {
        table.clearSelection();
    }

    public void setRowSelectionInterval(int index0, int index1) {
        table.setRowSelectionInterval(index0, index1);
    }

    public void setColumnSelectionInterval(int index0, int index1) {
        table.setColumnSelectionInterval(index0, index1);
    }

    public void addRowSelectionInterval(int index0, int index1) {
        table.addRowSelectionInterval(index0, index1);
    }

    public void addColumnSelectionInterval(int index0, int index1) {
        table.addColumnSelectionInterval(index0, index1);
    }

    public void removeRowSelectionInterval(int index0, int index1) {
        table.removeRowSelectionInterval(index0, index1);
    }

    public void removeColumnSelectionInterval(int index0, int index1) {
        table.removeColumnSelectionInterval(index0, index1);
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }

    public int getSelectedColumn() {
        return table.getSelectedColumn();
    }

    public int[] getSelectedRows() {
        return table.getSelectedRows();
    }

    public int[] getSelectedColumns() {
        return table.getSelectedColumns();
    }

    public int getSelectedRowCount() {
        return table.getSelectedRowCount();
    }

    public int getSelectedColumnCount() {
        return table.getSelectedColumnCount();
    }

    public boolean isRowSelected(int row) {
        return table.isRowSelected(row);
    }

    public boolean isColumnSelected(int column) {
        return table.isColumnSelected(column);
    }

    public boolean isCellSelected(int row, int column) {
        return table.isCellSelected(row, column);
    }

    public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
        table.changeSelection(rowIndex, columnIndex, toggle, extend);
    }

    public Color getSelectionForeground() {
        return table.getSelectionForeground();
    }

    public void setSelectionForeground(Color selectionForeground) {
        table.setSelectionForeground(selectionForeground);
    }

    public Color getSelectionBackground() {
        return table.getSelectionBackground();
    }

    public void setSelectionBackground(Color selectionBackground) {
        table.setSelectionBackground(selectionBackground);
    }

    public TableColumn getColumn(Object identifier) {
        return table.getColumn(identifier);
    }

    public int convertColumnIndexToModel(int viewColumnIndex) {
        return table.convertColumnIndexToModel(viewColumnIndex);
    }

    public int convertColumnIndexToView(int modelColumnIndex) {
        return table.convertColumnIndexToView(modelColumnIndex);
    }

    public int convertRowIndexToView(int modelRowIndex) {
        return table.convertRowIndexToView(modelRowIndex);
    }

    public int convertRowIndexToModel(int viewRowIndex) {
        return table.convertRowIndexToModel(viewRowIndex);
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

    public void setValueAt(Object aValue, int row, int column) {
        table.setValueAt(aValue, row, column);
    }

    public boolean isCellEditable(int row, int column) {
        return table.isCellEditable(row, column);
    }

    public void addColumn(TableColumn aColumn) {
        table.addColumn(aColumn);
    }

    public void removeColumn(TableColumn aColumn) {
        table.removeColumn(aColumn);
    }

    public void moveColumn(int column, int targetColumn) {
        table.moveColumn(column, targetColumn);
    }

    public int columnAtPoint(Point point) {
        return table.columnAtPoint(point);
    }

    public int rowAtPoint(Point point) {
        return table.rowAtPoint(point);
    }

    public Rectangle getCellRect(int row, int column, boolean includeSpacing) {
        return table.getCellRect(row, column, includeSpacing);
    }

    public void doLayout() {
        table.doLayout();
    }

    public void sizeColumnsToFit(int resizingColumn) {
        table.sizeColumnsToFit(resizingColumn);
    }

    public String getToolTipText(MouseEvent event) {
        return table.getToolTipText(event);
    }

    public void setSurrendersFocusOnKeystroke(boolean surrendersFocusOnKeystroke) {
        table.setSurrendersFocusOnKeystroke(surrendersFocusOnKeystroke);
    }

    public boolean getSurrendersFocusOnKeystroke() {
        return table.getSurrendersFocusOnKeystroke();
    }

    public boolean editCellAt(int row, int column) {
        return table.editCellAt(row, column);
    }

    public boolean isEditing() {
        return table.isEditing();
    }

    public Component getEditorComponent() {
        return table.getEditorComponent();
    }

    public int getEditingColumn() {
        return table.getEditingColumn();
    }

    public int getEditingRow() {
        return table.getEditingRow();
    }

    public void setModel(TableModel dataModel) {
        table.setModel(dataModel);
    }

    public TableModel getModel() {
        return table.getModel();
    }

    public void setColumnModel(TableColumnModel columnModel) {
        table.setColumnModel(columnModel);
    }

    public TableColumnModel getColumnModel() {
        return table.getColumnModel();
    }

    public void setSelectionModel(ListSelectionModel newModel) {
        table.setSelectionModel(newModel);
    }

    public ListSelectionModel getSelectionModel() {
        return table.getSelectionModel();
    }

    public void setPreferredScrollableViewportSize(Dimension size) {
        table.setPreferredScrollableViewportSize(size);
    }

    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return table.getScrollableUnitIncrement(visibleRect, orientation, direction);
    }

    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return table.getScrollableBlockIncrement(visibleRect, orientation, direction);
    }

    public boolean getScrollableTracksViewportWidth() {
        return table.getScrollableTracksViewportWidth();
    }

    public boolean getScrollableTracksViewportHeight() {
        return table.getScrollableTracksViewportHeight();
    }

    public void setFillsViewportHeight(boolean fillsViewportHeight) {
        table.setFillsViewportHeight(fillsViewportHeight);
    }

    public boolean getFillsViewportHeight() {
        return table.getFillsViewportHeight();
    }

    public TableCellEditor getCellEditor() {
        return table.getCellEditor();
    }

    public void setCellEditor(TableCellEditor anEditor) {
        table.setCellEditor(anEditor);
    }

    public void setEditingColumn(int aColumn) {
        table.setEditingColumn(aColumn);
    }

    public void setEditingRow(int aRow) {
        table.setEditingRow(aRow);
    }

    public TableCellRenderer getCellRenderer(int row, int column) {
        return table.getCellRenderer(row, column);
    }

    public TableCellEditor getCellEditor(int row, int column) {
        return table.getCellEditor(row, column);
    }

    public void removeEditor() {
        table.removeEditor();
    }

    @Override
    public JComponent getComponent() {
        return table;
    }
}
