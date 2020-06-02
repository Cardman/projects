package aiki.main;
import aiki.gui.MainWindow;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class AfterLoadZip implements Runnable {

    private MainWindow window;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public AfterLoadZip(MainWindow _window) {
        window = _window;
    }

    @Override
    public void run() {
        window.afterLoadZip();
    }
}
