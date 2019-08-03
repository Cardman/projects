package aiki.gui.threads;
import aiki.gui.MainWindow;

public final class ReinitComponents implements Runnable {

    private MainWindow window;

    public ReinitComponents(MainWindow _window) {
        window = _window;
    }

    @Override
    public void run() {
        window.reinitComponents();
    }
}
