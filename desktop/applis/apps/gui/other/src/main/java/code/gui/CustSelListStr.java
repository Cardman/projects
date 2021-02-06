package code.gui;

import code.expressionlanguage.Argument;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;

import javax.swing.*;
import java.awt.*;

public final class CustSelListStr implements ListCellRenderer<Struct> {
    private final PreparedLabel label;
    private final Struct labStruct;
    private final SpecSelectionStruct width;
    private final SpecSelectionStruct height;
    private final SpecSelectionStruct listener;

    public CustSelListStr(PreparedLabel _label,
                          Struct _labStruct,
                          SpecSelectionStruct _listener, SpecSelectionStruct _width, SpecSelectionStruct _height) {
        this.label = _label;
        labStruct = _labStruct;
        this.listener = _listener;
        width = _width;
        height = _height;
    }

    @Override
    public Component getListCellRendererComponent(
            JList<? extends Struct> _list,
            Struct _value,
            int _index,
            boolean _isSelected,
            boolean _cellHasFocus) {
        int defWidth_ = Math.max(_list.getWidth(), _list.getFixedCellWidth());
        int width_ = defWidth_;
        if (width != null) {
            Struct str_ = new IntStruct(defWidth_);
            Argument argumentWidth_ = new Argument(str_);
            Argument res_ = width.execute(new CustList<>(
                    new Argument(_value),
                    argumentWidth_), null);
            width_ = NumParsers.convertToNumber(res_.getStruct()).intStruct();
        }
        int height_ = Math.max(_list.getFixedCellHeight(),0);
        if (height != null) {
            Argument h_ = height.execute(new CustList<>(
                    new Argument(_value)), null);
            height_ = NumParsers.convertToNumber(h_.getStruct()).intStruct();
        }

        Rectangle rect_ = new Rectangle();
        rect_.width = width_;
        rect_.height = height_;
        listener.execute(new CustList<>(
                new Argument(_value),
                new Argument(new IntStruct(_index)),
                new Argument(labStruct),
                new Argument(BooleanStruct.of(_isSelected))
        ), rect_);
        return label.getComponent();
    }
}
