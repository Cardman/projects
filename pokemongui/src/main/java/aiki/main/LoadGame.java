package aiki.main;
import aiki.DataBase;
import aiki.gui.MainWindow;
import code.gui.ThreadInvoker;

/**This class thread is independant from EDT,
Thread safe class*/
public final class LoadGame extends Thread {

    //extends Thread

    /**This class thread is independant from EDT*/
    public LoadGame(MainWindow _frame) {
        MainWindow.getDialog().init(_frame, VideoLoading.getVideo(), false);
//        addPropertyChangeListener(new ProgressingPropertyEvent());
    }

    @Override
    public void run() {
//        MainWindow.getDialog().setVisible(true);
        ThreadInvoker.invokeNow(new ShowLoadingDialog());
        MainWindow.getDialog().startAnimation();
        while (DataBase.isLoading()) {
            setProgress(DataBase.getPerCentLoading());
//            MainWindow.getDialog().setPerCent(String.valueOf(DataBase.getPerCentLoading()));
        }
        MainWindow.getDialog().stopAnimation();
        MainWindow.getDialog().setVisible(false);
        MainWindow.getDialog().getContentPane().removeAll();
        MainWindow.getDialog().stopTimer();
//        ThreadInvoker.invokeNow(new DoneLoadingThread());
    }

    private static void setProgress(int _perCentLoading) {
        MainWindow.getDialog().setPerCent(String.valueOf(_perCentLoading));
    }

//    @Override
//    protected void done() {
//        //Freeing resources
//        MainWindow.getDialog().setVisible(false);
//        MainWindow.getDialog().getContentPane().removeAll();
//        MainWindow.getDialog().stopTimer();
//    }
}

