package aiki.main;
import aiki.gui.WindowAiki;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class AfterLoadZip implements Runnable {

    private WindowAiki window;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public AfterLoadZip(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void run() {
        window.afterLoadZip();
    }
}
