package aiki.gui.threads;

import code.network.WindowNetWork;
import code.threads.AbstractAtomicIntegerCoreAdd;
import code.threads.AbstractScheduledExecutorService;

/**This class thread is independant from EDT,
Thread safe class*/
public final class LoadingThreadMulti implements Runnable {

    private WindowNetWork window;

    private final String fileName;

    private final AbstractAtomicIntegerCoreAdd perCent;

    /**This class thread is independant from EDT*/
    public LoadingThreadMulti(WindowNetWork _window, String _fileName, AbstractAtomicIntegerCoreAdd _p) {
        window = _window;
        fileName = _fileName;
        perCent = _p;
    }

    @Override
    public void run() {
//        PerCent p_ = new PerCentIncr(window.getThreadFactory().newAtomicInteger());
        AbstractScheduledExecutorService sch_ = window.getThreadFactory().newScheduledExecutorService();
//        LoadGame opening_ = new LoadGame(window,p_);
//        LoadGame.init(window);
//        AbstractFuture abs_ = sch_.scheduleAtFixedRateNanos(opening_, 0, 1);
        window.processLoad(fileName,perCent);
//        boolean wasLoading_ = window.getLoadFlag().get();
        window.getLoadFlag().set(false);
//        abs_.cancel(false);
        sch_.shutdown();
//        OpeningGame.end(window);
//        if (!wasLoading_) {
//            window.getDialog().getAbsDialog().setVisible(false);
//            return;
//        }
//        FrameUtil.invokeLater(new AfterLoadingThread(window, fileName), window.getFrames());
//        window = null;
    }
}
