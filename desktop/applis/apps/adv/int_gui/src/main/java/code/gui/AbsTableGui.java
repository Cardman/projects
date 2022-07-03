package code.gui;

import code.gui.events.AbsListSelectionListener;
import code.gui.events.AbsMouseListener;
import code.gui.events.AbsMouseListenerCl;

public interface AbsTableGui extends AbsCustComponent {
    int[] getSelectedRows();

    int getSelectedRow();

    int getSelectedRowCount();

    int getRowCount();

    int getColumnCount();

    void addSelectInterval(int _a, int _b);

    void removeSelectInterval(int _a, int _b);

    void setRowCount(int _value);

    String getColumnName(int _index);

    String getValueAt(int _a, int _b);

    void setValueAt(String _value, int _a, int _b);

    void moveColumn(int _a, int _b);

    int columnAtPoint(int _a, int _b);

    int rowAtPoint(int _a, int _b);

    boolean isMultiSelect();

    void setMultiSelect(boolean _value);
    void setMultiSelect();
    void setSingleSelect();

    boolean isReorderingAllowed();

    void setReorderingAllowed(boolean _value);

    void applyChanges();

    void addHeaderListener(AbsMouseListener _list);
    void addHeaderListener(AbsMouseListenerCl _list);

    void addListSelectionListener(AbsListSelectionListener _list);

    void setColumnIdentifiers(String[] _cols);
}
