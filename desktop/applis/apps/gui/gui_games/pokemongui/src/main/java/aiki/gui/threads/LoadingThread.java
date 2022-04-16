package aiki.gui.threads;

import aiki.main.LoadGame;
import aiki.main.OpeningGame;
import aiki.sml.LoadingData;
import aiki.db.PerCent;
import aiki.gui.WindowAiki;
import code.gui.FrameUtil;
import code.threads.AbstractFuture;
import code.threads.AbstractScheduledExecutorService;

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
        PerCent p_ = new PerCentIncr(window.getThreadFactory().newAtomicInteger());
        AbstractScheduledExecutorService sch_ = window.getThreadFactory().newScheduledExecutorService();
        LoadGame opening_ = new LoadGame(window,p_);
        LoadGame.init(window);
        AbstractFuture abs_ = sch_.scheduleAtFixedRateNanos(opening_, 0, 1);
        window.processLoad(fileName,perCent,loadingData);
        boolean wasLoading_ = window.getLoadFlag().get();
        window.getLoadFlag().set(false);
        abs_.cancel(false);
        sch_.shutdown();
        OpeningGame.end(window);
        if (!wasLoading_) {
            window.getDialog().getAbsDialog().setVisible(false);
            return;
        }
        FrameUtil.invokeLater(new AfterLoadingThread(window, fileName), window.getFrames());
        window = null;
    }
}
