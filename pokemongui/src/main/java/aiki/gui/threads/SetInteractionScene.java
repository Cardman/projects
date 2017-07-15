package aiki.gui.threads;
import aiki.gui.MainWindow;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class SetInteractionScene extends Thread {

    private MainWindow window;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public SetInteractionScene(MainWindow _window) {
        window = _window;
    }

    @Override
    public void run() {
        window.interact();
    }
}
