package code.gui;
import javax.swing.Timer;

public final class DoneWebThread extends Thread {

    private ProgressingWebDialog dialog;

    private Timer timer;

    public DoneWebThread(ProgressingWebDialog _dialog, Timer _timer) {
        dialog = _dialog;
        timer = _timer;
    }

    @Override
    public void run() {
        timer.stop();
        dialog.setVisible(false);
        dialog.getPane().removeAll();
    }
}
