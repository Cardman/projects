package aiki.main;
import aiki.db.PerCent;
import aiki.gui.WindowAiki;
import code.gui.ThreadInvoker;

/**This class thread is independant from EDT,
Thread safe class*/
public final class LoadGame implements Runnable {

    private WindowAiki frame;

    private PerCent perCent;
    /**This class thread is independant from EDT*/
    public LoadGame(WindowAiki _frame, PerCent _p) {
        frame = _frame;
        VideoLoading video_ = frame.getVideoLoading();
        frame.getDialog().init(frame, video_.getVideo(frame.getGenerator(),frame.getFileCoreStream(), frame.getFrames()), false);
        perCent = _p;
    }

    @Override
    public void run() {
        setProgress(perCent.getPercent());
    }

    public static void init(WindowAiki _frame) {
        ThreadInvoker.invokeNow(_frame.getThreadFactory(),new ShowLoadingDialog(_frame), _frame.getFrames());
        _frame.getDialog().startAnimation();
    }

    private void setProgress(int _perCentLoading) {
        frame.getDialog().setPerCent(_perCentLoading);
    }
}

