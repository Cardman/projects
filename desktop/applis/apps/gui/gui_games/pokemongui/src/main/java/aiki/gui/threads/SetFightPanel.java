package aiki.gui.threads;
import aiki.gui.WindowAiki;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class SetFightPanel implements Runnable {

    private WindowAiki window;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public SetFightPanel(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void run() {
        window.setFight(true);
    }
}
