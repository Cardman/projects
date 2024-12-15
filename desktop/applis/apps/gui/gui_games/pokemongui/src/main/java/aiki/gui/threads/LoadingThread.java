package aiki.gui.threads;

import aiki.gui.WindowAikiInt;
import aiki.main.LoadGame;
import aiki.main.OpeningGame;
import aiki.gui.WindowAiki;
import code.threads.AbstractAtomicIntegerCoreAdd;
import code.threads.AbstractFuture;
import code.threads.AbstractScheduledExecutorService;

/**This class thread is independant from EDT,
Thread safe class*/
public final class LoadingThread implements Runnable {

    private final WindowAikiInt window;

    private final String fileName;

    private final AbstractAtomicIntegerCoreAdd perCent;

    /**This class thread is independant from EDT*/
    public LoadingThread(WindowAikiInt _window, String _fileName, AbstractAtomicIntegerCoreAdd _p) {
        window = _window;
        fileName = _fileName;
        perCent = _p;
    }

    @Override
    public void run() {
        AbstractAtomicIntegerCoreAdd p_ = window.getFrames().getThreadFactory().newAtomicInteger();
        AbstractScheduledExecutorService sch_ = window.getFrames().getThreadFactory().newScheduledExecutorService();
        LoadGame opening_ = new LoadGame(window,p_);
        LoadGame.init(window);
        AbstractFuture abs_ = sch_.scheduleAtFixedRateNanos(opening_, 0, 1);
        WindowAiki.processLoad(window,fileName,perCent);
        boolean wasLoading_ = window.getLoadFlag().get();
        window.getLoadFlag().set(false);
        abs_.cancel(false);
        sch_.shutdown();
        OpeningGame.end(window);
        if (!wasLoading_) {
            return;
        }
        window.getFrames().getCompoFactory().invokeNow(new AfterLoadingThread(window, fileName));
    }
}
