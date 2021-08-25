package cards.gui.labels;
import java.awt.Color;

import code.gui.LabelButton;
import code.gui.events.AbsMouseListener;

public final class LabelPoints {

    private int pts;

    private final LabelButton button;

    public LabelPoints(int _pts) {
        button = new LabelButton(Long.toString(_pts));
        pts = _pts;
    }

    public void setSelected(int _pts) {
        if (pts == _pts) {
            button.setLineBorder(Color.RED, 1);
        } else {
            button.setLineBorder(Color.BLACK, 1);
        }
    }

    public void addMouseList(AbsMouseListener _l) {
        button.addMouseList(_l);
    }

    public void setToolTipText(String _title) {
        button.setToolTipText(_title);
    }
    public void setEnabledLabel(boolean _enabled) {
        button.setEnabledLabel(_enabled);
    }
    public LabelButton getButton() {
        return button;
    }
}
