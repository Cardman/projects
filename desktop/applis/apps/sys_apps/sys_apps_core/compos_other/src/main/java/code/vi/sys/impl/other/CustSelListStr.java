package code.vi.sys.impl.other;

import code.expressionlanguage.Argument;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.gui.AbsPreparedLabel;
import code.gui.SpecSelectionStruct;
import code.gui.images.MetaDimension;
import code.vi.sys.impl.gui.CustComponent;
import code.util.CustList;

import javax.swing.*;
import java.awt.*;

public final class CustSelListStr implements ListCellRenderer<Struct> {
    private final AbsPreparedLabel label;
    private final Struct labStruct;
    private final SpecSelectionStruct listener;

    public CustSelListStr(AbsPreparedLabel _label,
                          Struct _labStruct,
                          SpecSelectionStruct _listener) {
        this.label = _label;
        labStruct = _labStruct;
        this.listener = _listener;
    }

    @Override
    public Component getListCellRendererComponent(
            JList<? extends Struct> _list,
            Struct _value,
            int _index,
            boolean _isSelected,
            boolean _cellHasFocus) {
        int width_ = Math.max(_list.getWidth(), _list.getFixedCellWidth());
        int height_ = Math.max(_list.getFixedCellHeight(),0);
        listener.execute(new CustList<>(
                new Argument(_value),
                new Argument(new IntStruct(_index)),
                new Argument(labStruct),
                new Argument(BooleanStruct.of(_isSelected))
        ), new MetaDimension(width_,height_));
        return ((CustComponent) label).getNatComponent();
    }
}
