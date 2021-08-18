package aiki.gui.threads;
import aiki.gui.WindowAiki;

public final class ReinitComponents implements Runnable {

    private WindowAiki window;

    public ReinitComponents(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void run() {
        window.reinitComponents();
    }
}
