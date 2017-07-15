package aiki.gui.threads;
import aiki.gui.MainWindow;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class SetFightPanel extends Thread {

    private MainWindow window;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public SetFightPanel(MainWindow _window) {
        window = _window;
    }

    @Override
    public void run() {
        window.setFight(true, true, true);
    }
}
