package aiki.main;
import aiki.db.PerCent;
import aiki.gui.MainWindow;
import code.gui.ThreadInvoker;

/**This class thread is independant from EDT,
Thread safe class*/
public final class LoadGame extends Thread {

    private MainWindow frame;

    private PerCent perCent;
    /**This class thread is independant from EDT*/
    public LoadGame(MainWindow _frame, PerCent _p) {
        frame = _frame;
        VideoLoading video_ = frame.getVideoLoading();
        frame.getDialog().init(frame, video_.getVideo(), false);
        perCent = _p;
    }

    @Override
    public void run() {
        ThreadInvoker.invokeNow(new ShowLoadingDialog(frame));
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

