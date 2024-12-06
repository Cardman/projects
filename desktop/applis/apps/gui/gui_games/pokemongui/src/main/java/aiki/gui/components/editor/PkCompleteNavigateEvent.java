package aiki.gui.components.editor;

import code.gui.events.*;
import code.util.core.*;

public final class PkCompleteNavigateEvent implements AbsActionListener {
    private final GeneComponentModelText textPane;
    private final int diff;

    public PkCompleteNavigateEvent(GeneComponentModelText _s, int _d) {
        this.textPane = _s;
        this.diff = _d;
    }

    @Override
    public void action() {
        int count_;
        if (!textPane.getPopupMenu().isVisible()) {
            count_ = 0;
        } else {
            count_ = textPane.getElement().size();
        }
        if (count_ == 0) {
            if (diff == 1) {
                textPane.getTextPane().downAction();
            } else {
                textPane.getTextPane().upAction();
            }
            return;
        }
        textPane.getElement().select(NumberUtil.mod(textPane.getElement().getSelectedIndex()+diff, count_));
        textPane.getElement().repaint();
    }
}
