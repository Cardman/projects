package aiki.main;
import aiki.gui.WindowAiki;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class AfterLoadingBegin implements Runnable {

    private WindowAiki window;

    private boolean stopLoading;

    private boolean error;

    private String fileName;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public AfterLoadingBegin(WindowAiki _window, boolean _stopLoading,
                             boolean _error, String _fileName) {
        window = _window;
        stopLoading = _stopLoading;
        error = _error;
        fileName = _fileName;
    }

    @Override
    public void run() {
        window.pack();
        if (stopLoading) {
            return;
        }
        if (error) {
            window.showErrorMessageDialog(fileName);
        } else {
            window.showSuccessfulMessageDialogThenLoadHelp(fileName);
        }
    }
}
