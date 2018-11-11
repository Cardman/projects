package aiki.main;
import aiki.gui.MainWindow;

public final class DoneLoadingThread extends Thread {

    private MainWindow window;

    public DoneLoadingThread(MainWindow _window) {
        window = _window;
    }
    @Override
    public void run() {
        //Freeing resources
        window.getDialog().setVisible(false);
        window.getDialog().getPane().removeAll();
        window.getDialog().stopTimer();
    }
}
