package aiki.main;
import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;
import code.gui.ThreadInvoker;
import code.gui.ThreadUtil;

/**This class thread is independant from EDT,
Thread safe class*/
public final class OpeningGame extends Thread {

    private static final int WAIT_VIDEO = 1000;

    private MainWindow window;

    /**This class thread is independant from EDT*/
    public OpeningGame(MainWindow _window) {
        window = _window;
    }

    @Override
    public void run() {
        ThreadInvoker.invokeNow(new ShowOpeningDialog(window));
        //Avoid to have a null dialog
        ThreadUtil.sleep(WAIT_VIDEO);
        window.getDialog().startAnimation();
        FacadeGame fg_ = window.getFacade();
        while (fg_.isLoading()) {
            setProgress(fg_.getPerCentLoading());
        }
        //Freeing resources
        window.getDialog().stopAnimation();
        window.getDialog().setVisible(false);
        window.getDialog().getPane().removeAll();
        window.getDialog().stopTimer();
        window = null;
    }

    private void setProgress(int _perCentLoading) {
        window.getDialog().setPerCent(_perCentLoading);
    }
}
