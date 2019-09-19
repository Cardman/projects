package code.gui;
import java.awt.image.BufferedImage;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class LoadLanguage implements Runnable {

    private String dir;

    private SoftApplicationCore application;

    private String[] args;

    private BufferedImage icon;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public LoadLanguage(String _dir, SoftApplicationCore _application, String[] _args,
            BufferedImage _icon) {
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
