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
        ThreadInvoker.invokeNow(frame.getThreadFactory(),new ShowLoadingDialog(frame));
        frame.getDialog().startAnimation();
        while (frame.getLoadFlag().get()) {
            setProgress(perCent.getPercent());
        }
        frame.getDialog().stopAnimation();
        frame.getDialog().setVisible(false);
        frame.getDialog().getPane().removeAll();
        frame.getDialog().stopTimer();
    }

    private void setProgress(int _perCentLoading) {
        frame.getDialog().setPerCent(_perCentLoading);
    }
}

