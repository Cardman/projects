package aiki.main;
import aiki.DataBase;
import aiki.gui.MainWindow;
import code.gui.GroupFrame;
import code.gui.ThreadInvoker;
import code.util.consts.Constants;

/**This class thread is independant from EDT,
Thread safe class*/
public final class OpeningGame extends Thread {

    private static final int WAIT_VIDEO = 1000;

    private GroupFrame window;

    /**This class thread is independant from EDT*/
    public OpeningGame(GroupFrame _window) {
        window = _window;
//        addPropertyChangeListener(new ProgressingPropertyEvent());
    }

    @Override
    public void run() {
//        MainWindow.getDialog().init(window, VideoLoading.getVideo(), true);
        ThreadInvoker.invokeNow(new ShowOpeningDialog(window));
        //Avoid to have a null dialog
        Constants.sleep(WAIT_VIDEO);
        MainWindow.getDialog().startAnimation();
        while (DataBase.isLoading()) {
            setProgress(DataBase.getPerCentLoading());
//            MainWindow.getDialog().setPerCent(String.valueOf(DataBase.getPerCentLoading()));
        }
        //Freeing resources
        MainWindow.getDialog().stopAnimation();
        MainWindow.getDialog().setVisible(false);
        MainWindow.getDialog().getPane().removeAll();
        MainWindow.getDialog().stopTimer();
        window = null;
//        ThreadInvoker.invokeNow(new DoneLoadingThread());
    }

    private static void setProgress(int _perCentLoading) {
        MainWindow.getDialog().setPerCent(_perCentLoading);
    }

//    @Override
//    protected void done() {
//        //Freeing resources
//        MainWindow.getDialog().setVisible(false);
//        MainWindow.getDialog().getContentPane().removeAll();
//        MainWindow.getDialog().stopTimer();
//        window = null;
//    }
}
