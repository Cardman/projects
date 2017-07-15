package cards.gui.labels;
import java.awt.Color;
import java.awt.Graphics;

import code.gui.LabelButton;

public class LabelPoints extends LabelButton {

    private boolean selected;

    private int pts;

    public LabelPoints(int _pts) {
        super(Integer.toString(_pts), true);
        pts = _pts;
    }

    public void setSelected(int _pts) {
        selected = pts == _pts;
    }

    @Override
    protected void paintComponent(Graphics _g) {
        super.paintComponent(_g);
        if (selected) {
            _g.setColor(Color.RED);
            _g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        }
    }
}
