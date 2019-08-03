package aiki.gui.components;
import java.awt.Color;
import java.awt.Dimension;

import code.gui.CustGraphics;
import code.gui.PaintableLabel;
import code.util.StringList;

public class AbilityLabel extends PaintableLabel {

    private boolean selected;

    private String key;

    private String text;

    public AbilityLabel(String _text, String _key) {
        text =_text;
        setPreferredSize(new Dimension(150, 10));
        key = _key;
    }

    public void setSelected(boolean _selected) {
        selected = _selected;
    }

    public void setSelected(String _key) {
        selected = StringList.quickEq(key, _key);
    }

    @Override
    public void paintComponent(CustGraphics _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0, 0, getWidth(), getHeight());
        _g.setColor(Color.BLACK);
        _g.drawString(text, 0, getHeight());
        if (selected) {
            _g.setColor(Color.RED);
            _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
