package code.vi.sys.impl.gui.events;

import code.gui.events.AbsWindowListener;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public final class WrWindowListener implements WindowListener {
    private final AbsWindowListener windowListener;

    public WrWindowListener(AbsWindowListener _windowListener) {
        this.windowListener = _windowListener;
    }

    @Override
    public void windowOpened(WindowEvent _e) {
        windowListener.windowOpened();
    }

    @Override
    public void windowClosing(WindowEvent _e) {
        windowListener.windowClosing();
    }

    @Override
    public void windowClosed(WindowEvent _e) {
        windowListener.windowClosed();
    }

    @Override
    public void windowIconified(WindowEvent _e) {
        windowListener.windowIconified();
    }

    @Override
    public void windowDeiconified(WindowEvent _e) {
        windowListener.windowDeiconified();
    }

    @Override
    public void windowActivated(WindowEvent _e) {
        windowListener.windowActivated();
    }

    @Override
    public void windowDeactivated(WindowEvent _e) {
        windowListener.windowDeiconified();
    }
}
