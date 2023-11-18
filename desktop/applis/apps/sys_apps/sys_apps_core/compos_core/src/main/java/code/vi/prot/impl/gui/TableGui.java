package code.vi.prot.impl.gui;

import code.gui.AbsTableGui;
import code.gui.GuiBaseUtil;
import code.gui.events.AbsListSelectionListener;
import code.gui.events.AbsMouseListener;
import code.gui.events.AbsMouseListenerCl;
import code.util.CustList;
import code.util.IdMap;
import code.vi.prot.impl.gui.events.WrListSelectionListener;
import code.vi.prot.impl.gui.events.WrMouseListener;
import code.util.core.StringUtil;
import code.vi.prot.impl.gui.events.WrMouseListenerCl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;


public final class TableGui extends CustComponent implements AbsTableGui {
    private final JTable table;
    private final IdMap<AbsListSelectionListener, WrListSelectionListener> mapList = new IdMap<AbsListSelectionListener, WrListSelectionListener>();

    private final DefaultTableModel model;

    private boolean multiSelect = true;

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

    @Override
    public int anc() {
        return table.getSelectionModel().getAnchorSelectionIndex();
    }

    @Override
    public int lea() {
        return table.getSelectionModel().getLeadSelectionIndex();
    }

    @Override
    public void clearSelect() {
        table.getSelectionModel().clearSelection();
    }

    public void addSelectInterval(int _from, int _to) {
        table.getSelectionModel().addSelectionInterval(_from,_to);
    }

    public void removeSelectInterval(int _from, int _to) {
        table.getSelectionModel().removeSelectionInterval(_from,_to);
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
        CustList<AbsListSelectionListener> ls_ = GuiBaseUtil.removeListSelectionListeners(this);
        int[] rows_ = table.getSelectedRows();
        table.setValueAt(StringUtil.nullToEmpty(_aValue), _row, _column);
        GuiBaseUtil.setSelectedIndices(this,rows_);
        GuiBaseUtil.addListSelectionListeners(this,ls_);
    }

    public void moveColumn(int _column, int _targetColumn) {
        CustList<AbsListSelectionListener> ls_ = GuiBaseUtil.removeListSelectionListeners(this);
        int[] rows_ = table.getSelectedRows();
        table.moveColumn(_column, _targetColumn);
        GuiBaseUtil.setSelectedIndices(this,rows_);
        GuiBaseUtil.addListSelectionListeners(this,ls_);
    }

    public int columnAtPoint(int _x,int _y) {
        return table.columnAtPoint(new Point(_x,_y));
    }

    public int rowAtPoint(int _x,int _y) {
        return table.rowAtPoint(new Point(_x,_y));
    }

    public boolean isMultiSelect() {
        return multiSelect;
    }

    public void setMultiSelect(boolean _mult) {
        multiSelect = _mult;
        GuiBaseUtil.setSelectTable(this,_mult);
    }

    @Override
    public void setMultiSelect() {
        table.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }

    @Override
    public void setSingleSelect() {
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private ListSelectionModel getSelectionModel() {
        return table.getSelectionModel();
    }

    @Override
    public JComponent getNatComponent() {
        return table;
    }

    public void setColumnIdentifiers(String[] _cols) {
        CustList<AbsListSelectionListener> ls_ = GuiBaseUtil.removeListSelectionListeners(this);
        int[] rows_ = table.getSelectedRows();
        model.setColumnIdentifiers(_cols);
        GuiBaseUtil.setSelectedIndices(this,rows_);
        GuiBaseUtil.addListSelectionListeners(this,ls_);
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

    @Override
    public void addHeaderListener(AbsMouseListenerCl _list) {
        getTableHeader().addMouseListener(new WrMouseListenerCl(_list));
    }

    public void addListSelectionListener(AbsListSelectionListener _select) {
        WrListSelectionListener wr_ = new WrListSelectionListener(_select);
        mapList.addEntry(_select, wr_);
        getSelectionModel().addListSelectionListener(wr_);
    }

    @Override
    public void addListSelectionListenerMap(AbsListSelectionListener _list) {
        WrListSelectionListener wr_ = new WrListSelectionListener(_list);
        mapList.addEntry(_list, wr_);
    }

    @Override
    public void removeListSelectionListener(AbsListSelectionListener _list) {
        WrListSelectionListener wr_ = mapList.getVal(_list);
        getSelectionModel().removeListSelectionListener(wr_);
        mapList.removeKey(_list);
    }

    @Override
    public void removeListSelectionListenerMap(AbsListSelectionListener _list) {
        mapList.removeKey(_list);
    }

    @Override
    public CustList<AbsListSelectionListener> getListSelectionListeners() {
        return mapList.getKeys();
    }
}
