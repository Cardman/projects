package code.gui;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.structs.Struct;
import code.gui.initialize.StrConv;

import javax.swing.*;
import java.awt.*;

public final class DefSelListStr implements ListCellRenderer<Struct> {
    private final SpecSelectionCtx specSelectionCtx;
    private final DefaultListCellRenderer def;

    public DefSelListStr(SpecSelectionCtx _specSelectionCtx, DefaultListCellRenderer _def) {
        specSelectionCtx = _specSelectionCtx;
        def = _def;
    }

    @Override
    public Component getListCellRendererComponent(
            JList<? extends Struct> _list,
            Struct _value,
            int _index,
            boolean _isSelected,
            boolean _cellHasFocus) {
        ContextEl ctx_ = specSelectionCtx.ctx();
        String str_ = StrConv.convertStr(_value, ctx_);
        return def.getListCellRendererComponent(_list,
                str_,
                _index, _isSelected, _cellHasFocus);
    }
}
