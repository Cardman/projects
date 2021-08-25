package aiki.main;
import aiki.db.PerCent;
import aiki.gui.WindowAiki;
import code.gui.ThreadInvoker;
import code.threads.ThreadUtil;

/**This class thread is independant from EDT,
Thread safe class*/
public final class OpeningGame implements Runnable {

    private static final int WAIT_VIDEO = 1000;

    private WindowAiki window;
    private PerCent perCent;

    /**This class thread is independant from EDT*/
    public OpeningGame(WindowAiki _window, PerCent _p) {
        window = _window;
        perCent = _p;
    }

    @Override
    public void run() {
        ThreadInvoker.invokeNow(window.getThreadFactory(),new ShowOpeningDialog(window), window.getFrames());
        //Avoid to have a null dialog
        ThreadUtil.sleep(window.getThreadFactory(),WAIT_VIDEO);
        window.getDialog().startAnimation();
        while (window.getLoadFlag().get()) {
            setProgress(perCent.getPercent());
        }
        //Freeing resources
        window.getDialog().stopAnimation();
        window.getDialog().getAbsDialog().setVisible(false);
        window.getDialog().getAbsDialog().getPane().removeAll();
        window.getDialog().stopTimer();
        window = null;
    }

    private void setProgress(int _perCentLoading) {
        window.getDialog().setPerCent(_perCentLoading);
    }
}
