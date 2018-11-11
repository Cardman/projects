package aiki.main;
import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;
import code.gui.ThreadInvoker;

/**This class thread is independant from EDT,
Thread safe class*/
public final class LoadGame extends Thread {

    private MainWindow frame;

    /**This class thread is independant from EDT*/
    public LoadGame(MainWindow _frame) {
        frame = _frame;
        VideoLoading video_ = frame.getVideoLoading();
        frame.getDialog().init(frame, video_.getVideo(), false);
    }

    @Override
    public void run() {
        ThreadInvoker.invokeNow(new ShowLoadingDialog(frame));
        frame.getDialog().startAnimation();
        FacadeGame fg_ = frame.getFacade();
        while (fg_.isLoading()) {
            setProgress(fg_.getPerCentLoading());
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

