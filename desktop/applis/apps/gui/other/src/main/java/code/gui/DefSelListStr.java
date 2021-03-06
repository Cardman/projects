package code.gui;

import code.expressionlanguage.structs.Struct;

import javax.swing.*;
import java.awt.*;

public final class DefSelListStr {
    private final SpecSelectionCtx specSelectionCtx;
    private final DefaultListCellRenderer def;

    public DefSelListStr(SpecSelectionCtx _specSelectionCtx, DefaultListCellRenderer _def) {
        specSelectionCtx = _specSelectionCtx;
        def = _def;
    }

    public Component getListCellRendererComponent(
            JList<? extends Struct> _list,
            Struct _value,
            int _index,
            boolean _isSelected,
            boolean _cellHasFocus) {
        String str_ = specSelectionCtx.convertStr(_value);
        return def.getListCellRendererComponent(_list,
                str_,
                _index, _isSelected, _cellHasFocus);
    }
}
