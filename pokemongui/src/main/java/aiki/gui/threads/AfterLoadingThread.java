package aiki.gui.threads;
import aiki.gui.MainWindow;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class AfterLoadingThread extends Thread {

    private MainWindow window;

    private String fileName;

    private boolean error;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public AfterLoadingThread(MainWindow _window, String _fileName, boolean _error) {
        window = _window;
        fileName = _fileName;
        error = _error;
    }

    @Override
    public void run() {
        window.getDialog().setVisible(false);
        window.afterLoading();
        if (error) {
            window.showErrorMessageDialog(fileName);
        } else {
            window.showSuccessfulMessageDialogThenLoadHelp(fileName);
        }
    }
}
