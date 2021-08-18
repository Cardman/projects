package aiki.main;
import aiki.gui.WindowAiki;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class AfterLoadGame implements Runnable {

    private WindowAiki window;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public AfterLoadGame(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void run() {
        window.afterLoadGame();
    }
}
