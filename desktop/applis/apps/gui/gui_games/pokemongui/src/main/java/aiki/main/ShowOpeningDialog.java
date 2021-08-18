package aiki.main;
import aiki.gui.WindowAiki;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class ShowOpeningDialog implements Runnable {

    private WindowAiki window;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public ShowOpeningDialog(WindowAiki _window) {
        window = _window;
    }

    @Override
    public void run() {
        VideoLoading videoLoading_ = window.getVideoLoading();
        window.getDialog().init(window, videoLoading_.getVideo(window.getGenerator(), window.getFileCoreStream(), window.getFrames()), true);
    }
}
