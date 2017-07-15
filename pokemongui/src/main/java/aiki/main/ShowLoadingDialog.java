package aiki.main;
import aiki.gui.MainWindow;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class ShowLoadingDialog extends Thread {

    @Override
    public void run() {
        MainWindow.getDialog().setVisible(true);
    }
}
