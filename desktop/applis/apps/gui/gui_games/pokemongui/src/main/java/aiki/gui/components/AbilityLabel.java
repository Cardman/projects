package aiki.gui.components;

import code.gui.GuiConstants;
import code.gui.images.AbstractImage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.util.core.StringUtil;

public final class AbilityLabel extends AbsMetaLabelPk {

    private boolean selected;

    private String key;

    private String text;

    public AbilityLabel(String _text, String _key, AbsCompoFactory _compoFactory) {
        super(_compoFactory);
        text =_text;
        setPreferredSize(new MetaDimension(150, 10));
        key = _key;
    }

    public void setSelected(boolean _selected) {
        selected = _selected;
    }

    public void setSelected(String _key) {
        selected = StringUtil.quickEq(key, _key);
    }

    @Override
    public void paintComponent(AbstractImage _g) {
        _g.setColor(GuiConstants.WHITE);
        _g.fillRect(0, 0, getWidth(), getHeight());
        _g.setColor(GuiConstants.BLACK);
        _g.drawString(text, 0, getHeight());
        if (selected) {
            _g.setColor(GuiConstants.RED);
            _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
