package cards.gui.labels;


import code.gui.AbsButton;
import code.gui.GuiConstants;
import code.gui.initialize.AbsCompoFactory;

public final class LabelPoints {

    private final int pts;

    private final AbsButton button;

    public LabelPoints(int _pts, AbsCompoFactory _fact) {
        button = _fact.newPlainButton(Long.toString(_pts));
        pts = _pts;
    }

    public void setSelected(int _pts) {
        if (pts == _pts) {
            button.setForeground(GuiConstants.RED);
        } else {
            button.setForeground(GuiConstants.BLACK);
        }
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
