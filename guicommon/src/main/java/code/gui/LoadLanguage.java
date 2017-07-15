package code.gui;
import java.awt.Image;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class LoadLanguage extends Thread {

    private String dir;

    private SoftApplicationCore application;

    private String[] args;

    private Image icon;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public LoadLanguage(String _dir, SoftApplicationCore _application, String[] _args,
            Image _icon) {
        dir = _dir;
        application = _application;
        args = _args;
        icon = _icon;
    }

    @Override
    public void run() {
        application.loadLaungage(dir, args, icon);
    }
}
