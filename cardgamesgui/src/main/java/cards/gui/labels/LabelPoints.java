package cards.gui.labels;
import java.awt.Color;

import javax.swing.BorderFactory;

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
        if (selected) {
            getComponent().setBorder(BorderFactory.createLineBorder(Color.RED, 1));
        } else {
            getComponent().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        }
    }

    public void setToolTipText(String _string) {
        getComponent().setToolTipText(_string);
    }
}
