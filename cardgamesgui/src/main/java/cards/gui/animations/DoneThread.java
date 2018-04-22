package cards.gui.animations;
import code.gui.SplashWindow;

public final class DoneThread extends Thread {

    private SplashWindow progressingWindow;

    public DoneThread(SplashWindow _progressingWindow) {
        progressingWindow = _progressingWindow;
    }

    @Override
    public void run() {
        progressingWindow.setVisible(false);
        progressingWindow.getPane().removeAll();
        progressingWindow.removeAll();
        progressingWindow.dispose();
    }
}
