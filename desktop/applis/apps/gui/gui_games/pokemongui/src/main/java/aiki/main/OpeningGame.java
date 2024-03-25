package aiki.main;
import aiki.gui.WindowAiki;
import code.threads.AbstractAtomicIntegerCoreAdd;
import code.threads.ThreadUtil;

/**This class thread is independant from EDT,
Thread safe class*/
public final class OpeningGame implements Runnable {

    private static final int WAIT_VIDEO = 1000;

    private WindowAiki window;
    private AbstractAtomicIntegerCoreAdd perCent;

    /**This class thread is independant from EDT*/
    public OpeningGame(WindowAiki _window, AbstractAtomicIntegerCoreAdd _p) {
        window = _window;
        perCent = _p;
    }

    @Override
    public void run() {
        setProgress(perCent.get());
    }

    public static void init(WindowAiki _window) {
        _window.getFrames().getCompoFactory().invokeNow(new ShowOpeningDialog(_window));
        //Avoid to have a null dialog
        ThreadUtil.sleep(_window.getThreadFactory(),WAIT_VIDEO);
        _window.getDialog().startAnimation();
    }

    public static void end(WindowAiki _window) {
        _window.getDialog().stopAnimation();
        _window.getDialog().getAbsDialog().setVisible(false);
        _window.getDialog().getAbsDialog().getPane().removeAll();
        _window.getDialog().stopTimer();
    }

    private void setProgress(int _perCentLoading) {
        window.getDialog().setPerCent(_perCentLoading);
    }
}
