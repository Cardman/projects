package aiki.gui.threads;

import aiki.main.LoadGame;
import aiki.main.OpeningGame;
import aiki.gui.WindowAiki;
import code.gui.GuiBaseUtil;
import code.threads.AbstractAtomicIntegerCoreAdd;
import code.threads.AbstractFuture;
import code.threads.AbstractScheduledExecutorService;

/**This class thread is independant from EDT,
Thread safe class*/
public final class LoadingThread implements Runnable {

    private WindowAiki window;

    private final String fileName;

    private final AbstractAtomicIntegerCoreAdd perCent;

    /**This class thread is independant from EDT*/
    public LoadingThread(WindowAiki _window, String _fileName, AbstractAtomicIntegerCoreAdd _p) {
        window = _window;
        fileName = _fileName;
        perCent = _p;
    }

    @Override
    public void run() {
        AbstractAtomicIntegerCoreAdd p_ = window.getThreadFactory().newAtomicInteger();
        AbstractScheduledExecutorService sch_ = window.getThreadFactory().newScheduledExecutorService();
        LoadGame opening_ = new LoadGame(window,p_);
        LoadGame.init(window);
        AbstractFuture abs_ = sch_.scheduleAtFixedRateNanos(opening_, 0, 1);
        window.processLoad(fileName,perCent);
        boolean wasLoading_ = window.getLoadFlag().get();
        window.getLoadFlag().set(false);
        abs_.cancel(false);
        sch_.shutdown();
        OpeningGame.end(window);
        if (!wasLoading_) {
            window.getDialog().getAbsDialog().setVisible(false);
            return;
        }
        GuiBaseUtil.invokeLater(new AfterLoadingThread(window, fileName), window.getFrames());
        window = null;
    }
}
