package aiki.main;
import aiki.db.PerCent;
import aiki.gui.MainWindow;
import code.gui.ThreadInvoker;
import code.threads.ThreadUtil;

/**This class thread is independant from EDT,
Thread safe class*/
public final class OpeningGame implements Runnable {

    private static final int WAIT_VIDEO = 1000;

    private MainWindow window;
    private PerCent perCent;

    /**This class thread is independant from EDT*/
    public OpeningGame(MainWindow _window, PerCent _p) {
        window = _window;
        perCent = _p;
    }

    @Override
    public void run() {
        ThreadInvoker.invokeNow(new ShowOpeningDialog(window));
        //Avoid to have a null dialog
        ThreadUtil.sleep(WAIT_VIDEO);
        window.getDialog().startAnimation();
        while (window.getLoadFlag().get()) {
            setProgress(perCent.getPercent());
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
