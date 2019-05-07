package aiki.gui.threads;
import javax.swing.SwingUtilities;

import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;

/**This class thread is independant from EDT,
Thread safe class*/
public final class LoadingThread extends Thread {

    private MainWindow window;

    private String fileName;

    /**This class thread is independant from EDT*/
    public LoadingThread(MainWindow _window, String _fileName) {
        window = _window;
        fileName = _fileName;
    }

    @Override
    public void run() {
        window.processLoad(fileName);
        FacadeGame fg_ = window.getFacade();
        boolean wasLoading_ = fg_.isLoading();
        fg_.setLoading(false);
        if (!wasLoading_) {
            window.getDialog().setVisible(false);
            return;
        }
        SwingUtilities.invokeLater(new AfterLoadingThread(window, fileName));
        window = null;
    }
}
