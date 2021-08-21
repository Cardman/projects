package aiki.gui.threads;

import aiki.db.PerCent;
import aiki.gui.WindowAiki;
import code.gui.CustComponent;

/**This class thread is independant from EDT,
Thread safe class*/
public final class LoadingThread implements Runnable {

    private WindowAiki window;

    private String fileName;

    private PerCent perCent;
    /**This class thread is independant from EDT*/
    public LoadingThread(WindowAiki _window, String _fileName, PerCent _p) {
        window = _window;
        fileName = _fileName;
        perCent = _p;
    }

    @Override
    public void run() {
        window.processLoad(fileName,perCent);
        boolean wasLoading_ = window.getLoadFlag().get();
        window.getLoadFlag().set(false);
        if (!wasLoading_) {
            window.getDialog().getAbsDialog().setVisible(false);
            return;
        }
        CustComponent.invokeLater(new AfterLoadingThread(window, fileName));
        window = null;
    }
}
