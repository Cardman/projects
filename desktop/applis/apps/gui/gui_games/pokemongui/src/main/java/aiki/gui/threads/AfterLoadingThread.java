package aiki.gui.threads;
import aiki.gui.WindowAikiInt;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class AfterLoadingThread implements Runnable {

    private final WindowAikiInt window;

    private final String fileName;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    AfterLoadingThread(WindowAikiInt _window, String _fileName) {
        window = _window;
        fileName = _fileName;
    }

    @Override
    public void run() {
//        window.progressDial().getAbsDialog().setVisible(false);
//        window.getModal().set(false);
//        window.afterLoading();
        window.showSuccessfulMessageDialogThenLoadHelp(fileName);
    }
}
