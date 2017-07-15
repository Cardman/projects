package aiki.gui.threads;
import aiki.gui.MainWindow;

public final class ReinitComponents extends Thread {

    private MainWindow window;

    public ReinitComponents(MainWindow _window) {
        window = _window;
    }

    @Override
    public void run() {
        window.reinitComponents();
    }
}
