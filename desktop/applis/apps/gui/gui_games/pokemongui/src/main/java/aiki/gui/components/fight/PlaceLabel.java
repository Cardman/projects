package aiki.gui.components.fight;
import java.awt.Color;

import code.gui.AbsMetaLabel;
import code.gui.images.AbstractImage;
import code.gui.initialize.AbsCompoFactory;

public final class PlaceLabel extends AbsMetaLabel {

    private boolean selected;

    private byte number;

    private String text;

    public PlaceLabel(String _title, byte _number, AbsCompoFactory _compoFactory) {
        this(_number, _compoFactory);
        text = _title;
    }

    public PlaceLabel(byte _number, AbsCompoFactory _compoFactory) {
        super(_compoFactory);
        number = _number;
    }

    public void setSelected(boolean _selected) {
        selected = _selected;
    }

    public void setSelected(byte _number) {
        selected = number == _number;
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(Color.BLACK);
        _g.drawString(text, 0, getHeight());
        if (selected) {
            _g.setColor(Color.RED);
            _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }

    public void setText(String _val) {
        text = _val;
    }
}
