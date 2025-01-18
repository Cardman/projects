package aiki.gui.components.fight;


import aiki.gui.components.AbsMetaLabelPk;
import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;

public final class PlaceLabel extends AbsMetaLabelPk {

    private boolean selected;

    private final int number;

    private String text;

    public PlaceLabel(String _title, int _number, AbsCompoFactory _compoFactory) {
        this(_number, _compoFactory);
        text = _title;
        setPreferredSize(new MetaDimension(_compoFactory.stringWidth(getMetaFont(),text), getMetaFont().getRealSize()));
    }

    public PlaceLabel(int _number, AbsCompoFactory _compoFactory) {
        super(_compoFactory);
        number = _number;
    }

    public void setSelected(int _number) {
        selected = number == _number;
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(GuiConstants.BLACK);
        _g.drawString(text, 0, getHeight());
        if (selected) {
            _g.setColor(GuiConstants.RED);
            _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }

    public void setText(String _val) {
        text = _val;
    }

}
