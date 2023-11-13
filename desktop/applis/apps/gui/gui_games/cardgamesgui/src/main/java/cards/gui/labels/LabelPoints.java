package cards.gui.labels;


import code.gui.AbsButton;
import code.gui.GuiConstants;
import code.gui.events.AbsActionListener;
import code.gui.initialize.AbsCompoFactory;

public final class LabelPoints {

    private int pts;

    private final AbsButton button;

    public LabelPoints(int _pts, AbsCompoFactory _fact) {
        button = _fact.newPlainButton(Long.toString(_pts));
        pts = _pts;
    }

    public void setSelected(int _pts) {
        if (pts == _pts) {
            button.setLineBorder(GuiConstants.RED, 1);
        } else {
            button.setLineBorder(GuiConstants.BLACK, 1);
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
    public AbsButton getButton() {
        return button;
    }
}
