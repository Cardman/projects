package code.gui.events;

public interface AbsWindowListener {
    void windowOpened();

    void windowClosing();

    void windowClosed();

    void windowIconified();

    void windowDeiconified();

    void windowActivated();

    void windowDeactivated();
}
