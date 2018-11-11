package aiki.main;
import aiki.gui.MainWindow;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class ShowLoadingDialog extends Thread {

    private MainWindow window;

    public ShowLoadingDialog(MainWindow _window) {
        window = _window;
    }

    @Override
    public void run() {
        window.getDialog().setVisible(true);
    }
}
