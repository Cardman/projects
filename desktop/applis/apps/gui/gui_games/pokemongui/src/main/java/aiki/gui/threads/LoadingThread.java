package aiki.gui.threads;

import aiki.sml.LoadingData;
import aiki.db.PerCent;
import aiki.gui.WindowAiki;
import code.gui.FrameUtil;

/**This class thread is independant from EDT,
Thread safe class*/
public final class LoadingThread implements Runnable {

    private WindowAiki window;

    private final String fileName;

    private final PerCent perCent;
    private final LoadingData loadingData;
    /**This class thread is independant from EDT*/
    public LoadingThread(WindowAiki _window, String _fileName, PerCent _p, LoadingData _load) {
        window = _window;
        fileName = _fileName;
        perCent = _p;
        loadingData = _load;
    }

    @Override
    public void run() {
        window.processLoad(fileName,perCent,loadingData);
        boolean wasLoading_ = window.getLoadFlag().get();
        window.getLoadFlag().set(false);
        if (!wasLoading_) {
            window.getDialog().getAbsDialog().setVisible(false);
            return;
        }
        FrameUtil.invokeLater(new AfterLoadingThread(window, fileName), window.getFrames());
        window = null;
    }
}
