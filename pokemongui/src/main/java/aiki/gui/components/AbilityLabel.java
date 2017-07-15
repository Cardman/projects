package aiki.gui.components;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;

import code.util.StringList;

public class AbilityLabel extends JLabel {

    private boolean selected;

    private String key;

    public AbilityLabel(String _text, String _key) {
        setText(_text);
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
    protected void paintComponent(Graphics _g) {
        _g.setColor(Color.WHITE);
        _g.fillRect(0, 0, getWidth(), getHeight());
        _g.setColor(Color.BLACK);
        _g.drawString(getText(), 0, getHeight());
        if (selected) {
            _g.setColor(Color.RED);
            _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
