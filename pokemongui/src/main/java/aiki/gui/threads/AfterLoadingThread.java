package aiki.gui.threads;
import aiki.gui.MainWindow;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class AfterLoadingThread implements Runnable {

    private MainWindow window;

    private String fileName;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    AfterLoadingThread(MainWindow _window, String _fileName) {
        window = _window;
        fileName = _fileName;
    }

    @Override
    public void run() {
        window.getDialog().setVisible(false);
        window.afterLoading();
        window.showSuccessfulMessageDialogThenLoadHelp(fileName);
    }
}
