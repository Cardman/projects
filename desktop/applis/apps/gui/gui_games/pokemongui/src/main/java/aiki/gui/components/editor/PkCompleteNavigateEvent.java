package aiki.gui.components.editor;

import code.gui.*;
import code.gui.events.*;
import code.util.core.*;

public final class PkCompleteNavigateEvent implements AbsActionListener {
    private final ScrollCustomGraphicList<String> element;
    private final int diff;

    public PkCompleteNavigateEvent(ScrollCustomGraphicList<String> _s, int _d) {
        this.element = _s;
        this.diff = _d;
    }

    @Override
    public void action() {
        int count_ = element.size();
        if (count_ == 0) {
            return;
        }
        element.select(NumberUtil.mod(element.getSelectedIndex()+diff, count_));
        element.repaint();
    }
}
