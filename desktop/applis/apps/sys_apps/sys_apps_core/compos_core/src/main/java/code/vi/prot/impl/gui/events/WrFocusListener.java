package code.vi.prot.impl.gui.events;

import code.gui.AbsFocusListener;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public final class WrFocusListener implements FocusListener {
    private final AbsFocusListener focusListener;

    public WrFocusListener(AbsFocusListener _f) {
        this.focusListener = _f;
    }

    @Override
    public void focusGained(FocusEvent _e) {
        focusListener.focusGained();
    }

    @Override
    public void focusLost(FocusEvent _e) {
        focusListener.focusLost();
    }
}
