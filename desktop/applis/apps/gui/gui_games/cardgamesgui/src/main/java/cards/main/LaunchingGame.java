package cards.main;
import cards.gui.MainWindow;
import cards.gui.dialogs.FileConst;
import code.gui.SoftApplicationCore;
import code.gui.TopLeftFrame;
import code.util.StringMap;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class LaunchingGame implements Runnable {

    private StringMap<Object> args;

    private String language;

    private TopLeftFrame topLeft;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public LaunchingGame(StringMap<Object> _args, String _language, TopLeftFrame _topLeft) {
        args = _args;
        language = _language;
        topLeft = _topLeft;
    }

    @Override
    public void run() {
        MainWindow window_;
        window_ = new MainWindow(language);

        SoftApplicationCore.setLocation(window_, topLeft);
        window_.pack();
        window_.setVisible(true);

        if (!args.isEmpty()) {
            window_.loadGameBegin(args.getKeys().first(), args.values().first());
        }
    }
}
