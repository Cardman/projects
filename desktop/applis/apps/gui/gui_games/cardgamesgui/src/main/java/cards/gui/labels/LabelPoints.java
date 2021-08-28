package cards.gui.labels;
import java.awt.Color;

import code.gui.AbsPlainButton;
import code.gui.events.AbsActionListener;
import code.gui.initialize.AbsCompoFactory;

public final class LabelPoints {

    private int pts;

    private final AbsPlainButton button;

    public LabelPoints(int _pts, AbsCompoFactory _fact) {
        button = _fact.newPlainButton(Long.toString(_pts));
        pts = _pts;
    }

    public void setSelected(int _pts) {
        if (pts == _pts) {
            button.setLineBorder(Color.RED.getRGB(), 1);
        } else {
            button.setLineBorder(Color.BLACK.getRGB(), 1);
        }
    }

    public void addMouseList(AbsActionListener _l) {
        button.addActionListener(_l);
    }

    public void setToolTipText(String _title) {
        button.setToolTipText(_title);
    }
    public void setEnabledLabel(boolean _enabled) {
        button.setEnabled(_enabled);
    }
    public AbsPlainButton getButton() {
        return button;
    }
}
