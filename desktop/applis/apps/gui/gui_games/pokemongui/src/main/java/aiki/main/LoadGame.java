package aiki.main;
import aiki.gui.WindowAiki;
import code.gui.ThreadInvoker;
import code.threads.AbstractAtomicIntegerCoreAdd;

/**This class thread is independant from EDT,
Thread safe class*/
public final class LoadGame implements Runnable {

    private WindowAiki frame;

    private AbstractAtomicIntegerCoreAdd perCent;
    /**This class thread is independant from EDT*/
    public LoadGame(WindowAiki _frame, AbstractAtomicIntegerCoreAdd _p) {
        frame = _frame;
        VideoLoading video_ = frame.getVideoLoading();
        frame.getDialog().init(frame.getLoadFlag(),frame, video_.getVideo(frame.getGenerator(),frame.getFileCoreStream(), frame.getFrames()), false);
        perCent = _p;
    }

    @Override
    public void run() {
        setProgress(perCent.get());
    }

    public static void init(WindowAiki _frame) {
        ThreadInvoker.invokeNow(_frame.getThreadFactory(),new ShowLoadingDialog(_frame), _frame.getFrames());
        _frame.getDialog().startAnimation();
    }

    private void setProgress(int _perCentLoading) {
        frame.getDialog().setPerCent(_perCentLoading);
    }
}

