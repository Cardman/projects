package code.sys.impl.variant;

import code.expressionlanguage.structs.Struct;
import code.gui.SpecSelectionCtx;

import javax.swing.*;
import java.awt.*;

public final class DefDefSelListStr implements ListCellRenderer {
    private final SpecSelectionCtx specSelectionCtx;
    private final DefaultListCellRenderer def;

    public DefDefSelListStr(SpecSelectionCtx _specSelectionCtx, DefaultListCellRenderer _def) {
        specSelectionCtx = _specSelectionCtx;
        def = _def;
    }

    @Override
    public Component getListCellRendererComponent(
            JList _list,
            Object _value,
            int _index,
            boolean _isSelected,
            boolean _cellHasFocus) {
        String str_ = specSelectionCtx.convertStr((Struct)_value);
        return def.getListCellRendererComponent(_list,
                str_,
                _index, _isSelected, _cellHasFocus);
    }
}
