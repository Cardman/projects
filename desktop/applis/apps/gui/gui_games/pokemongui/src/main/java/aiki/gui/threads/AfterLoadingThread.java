package aiki.gui.threads;
import aiki.gui.WindowAiki;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class AfterLoadingThread implements Runnable {

    private WindowAiki window;

    private String fileName;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    AfterLoadingThread(WindowAiki _window, String _fileName) {
        window = _window;
        fileName = _fileName;
    }

    @Override
    public void run() {
        window.getDialog().getAbsDialog().setVisible(false);
        window.afterLoading();
        window.showSuccessfulMessageDialogThenLoadHelp(fileName);
    }
}
