package aiki.main;
import aiki.gui.WindowAikiInt;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class AfterLoadZip implements Runnable {

    private WindowAikiInt window;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public AfterLoadZip(WindowAikiInt _window) {
        window = _window;
    }

    @Override
    public void run() {
        window.afterLoadZip();
    }
}
