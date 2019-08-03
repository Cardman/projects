package aiki.main;
import aiki.gui.MainWindow;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class AfterLoadGame implements Runnable {

    private MainWindow window;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public AfterLoadGame(MainWindow _window) {
        window = _window;
    }

    @Override
    public void run() {
        window.afterLoadGame();
    }
}
