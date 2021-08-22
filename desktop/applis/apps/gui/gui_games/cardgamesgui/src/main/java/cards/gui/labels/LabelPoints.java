package cards.gui.labels;
import java.awt.Color;

import code.gui.LabelButton;

public final class LabelPoints extends LabelButton {

    private boolean selected;

    private int pts;

    public LabelPoints(int _pts) {
        super(Long.toString(_pts), true);
        pts = _pts;
    }

    public void setSelected(int _pts) {
        selected = pts == _pts;
        if (selected) {
            setLineBorder(Color.RED, 1);
        } else {
            setLineBorder(Color.BLACK, 1);
        }
    }
}
