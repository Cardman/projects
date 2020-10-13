package code.gui;
import java.awt.image.BufferedImage;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class LoadLanguage implements Runnable {

    private SoftApplicationCore application;

    private String dir;

    private String[] args;

    private BufferedImage icon;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public LoadLanguage(SoftApplicationCore _application, String _dir, String[] _args,
                        BufferedImage _icon) {
        application = _application;
        dir = SoftApplicationCore.getTempFolder(_application.getFrames(),_dir);
        args = _args;
        icon = _icon;
    }

    @Override
    public void run() {
        application.loadLaungage(dir, args, icon);
    }
}
