package cards.gui.labels;


import cards.facade.Games;
import cards.tarot.enumerations.Handfuls;
import code.gui.AbsButton;
import code.gui.GuiConstants;
import code.gui.initialize.AbsCompoFactory;
import code.sml.util.TranslationsLg;

public final class HandfulLabel {

    private Handfuls bid;

    private final AbsButton button;

    public HandfulLabel(AbsCompoFactory _compoFactory) {
        button = _compoFactory.newPlainButton();
    }

    public void setSuit(Handfuls _bid, TranslationsLg _lg) {
        bid = _bid;
        setText(Games.toString(bid,_lg));
    }

    public void setText(String _emptyString) {
        button.setText(_emptyString);
    }

    public void disable() {
        button.setEnabled(false);
    }
    public void setSelected(Handfuls _bid) {
        if (!button.isEnabled()) {
            return;
        }
        if (bid == _bid) {
            button.setForeground(GuiConstants.RED);
        } else {
            button.setForeground(GuiConstants.BLACK);
        }
    }

    public AbsButton getButton() {
        return button;
    }

}
