package aiki.main;
import aiki.gui.WindowAiki;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class AfterLoadingBegin implements Runnable {

    private final WindowAiki window;

    private final String fileName;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public AfterLoadingBegin(WindowAiki _window,
                             String _fileName) {
        window = _window;
        fileName = _fileName;
    }

    @Override
    public void run() {
        window.pack();
        window.showSuccessfulMessageDialogThenLoadHelp(fileName);
//        if (stopLoading) {
//            return;
//        }
//        if (error) {
//            window.showErrorMessageDialog(fileName);
//        } else {
//            window.showSuccessfulMessageDialogThenLoadHelp(fileName);
//        }
    }
}
