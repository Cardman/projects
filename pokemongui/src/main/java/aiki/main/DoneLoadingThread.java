package aiki.main;
import aiki.gui.MainWindow;

public final class DoneLoadingThread extends Thread {

    @Override
    public void run() {
        //Freeing resources
        MainWindow.getDialog().setVisible(false);
        MainWindow.getDialog().getContentPane().removeAll();
        MainWindow.getDialog().stopTimer();
    }
}
