package aiki.main;
import aiki.gui.MainWindow;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class ShowOpeningDialog implements Runnable {

    private MainWindow window;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public ShowOpeningDialog(MainWindow _window) {
        window = _window;
    }

    @Override
    public void run() {
        VideoLoading videoLoading_ = window.getVideoLoading();
        window.getDialog().init(window, videoLoading_.getVideo(window.getGenerator(), window.getFileCoreStream()), true);
    }
}
