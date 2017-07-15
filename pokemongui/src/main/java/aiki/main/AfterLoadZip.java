package aiki.main;
import aiki.gui.MainWindow;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class AfterLoadZip extends Thread {

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
