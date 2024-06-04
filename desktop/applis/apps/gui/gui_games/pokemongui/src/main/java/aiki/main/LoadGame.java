package aiki.main;
import aiki.gui.WindowAikiInt;
import code.threads.AbstractAtomicIntegerCoreAdd;

/**This class thread is independant from EDT,
Thread safe class*/
public final class LoadGame implements Runnable {

    private final WindowAikiInt frame;

    private final AbstractAtomicIntegerCoreAdd perCent;
    /**This class thread is independant from EDT*/
    public LoadGame(WindowAikiInt _frame, AbstractAtomicIntegerCoreAdd _p) {
        frame = _frame;
        VideoLoading video_ = frame.common().getVideoLoading();
        frame.progressDial().init(frame.getLoadFlag(),_frame, video_.getVideo(frame.getFrames().getGenerator(),frame.getFrames().getFileCoreStream(), frame.getFrames()), false, _frame.getCommonFrame());
        perCent = _p;
    }

    @Override
    public void run() {
        setProgress(perCent.get());
    }

    public static void init(WindowAikiInt _frame) {
        _frame.getFrames().getCompoFactory().invokeNow(new ShowLoadingDialog(_frame));
        _frame.progressDial().startAnimation();
    }

    private void setProgress(int _perCentLoading) {
        frame.progressDial().setPerCent(_perCentLoading);
    }
}

