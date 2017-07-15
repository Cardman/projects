package code.gui;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public abstract class CommonCellRenderer extends JLabel implements ListCellRenderer {

    public abstract Component getListCellRendererComponent(Object _value,
            int _index, boolean _isSelected, boolean _cellHasFocus);

    @Override
    public Component getListCellRendererComponent(JList _list, Object _value,
            int _index, boolean _isSelected, boolean _cellHasFocus) {
        return getListCellRendererComponent(_value, _index, _isSelected, _cellHasFocus);
    }
}
