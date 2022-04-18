package code.vi.prot.impl.gui.events;

import code.gui.events.AbsWindowListenerClosing;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class WrWindowListenerClos extends WindowAdapter {
    private final AbsWindowListenerClosing windowListener;

    public WrWindowListenerClos(AbsWindowListenerClosing _windowListener) {
        this.windowListener = _windowListener;
    }

    @Override
    public void windowClosing(WindowEvent _e) {
        windowListener.windowClosing();
    }

}
