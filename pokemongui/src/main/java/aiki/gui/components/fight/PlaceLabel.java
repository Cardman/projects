package aiki.gui.components.fight;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class PlaceLabel extends JLabel {

    private boolean selected;

    private byte number;

    public PlaceLabel(String _title, byte _number) {
        this(_number);
        setText(_title);
    }

    public PlaceLabel(byte _number) {
        number = _number;
    }

    public void setSelected(boolean _selected) {
        selected = _selected;
    }

    public void setSelected(byte _number) {
        selected = number == _number;
    }

    @Override
    protected void paintComponent(Graphics _g) {
        _g.setColor(Color.BLACK);
        _g.drawString(getText(), 0, getHeight());
        if (selected) {
            _g.setColor(Color.RED);
            _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
