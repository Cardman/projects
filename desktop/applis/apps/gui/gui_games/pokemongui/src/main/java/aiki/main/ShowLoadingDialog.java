package aiki.main;
import aiki.gui.WindowAiki;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class ShowLoadingDialog implements Runnable {

    private WindowAiki window;

    public ShowLoadingDialog(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void run() {
        window.getDialog().getAbsDialog().setVisible(true);
    }
}
