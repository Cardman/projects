package code.gui;
import code.gui.images.AbstractImage;
import code.stream.StreamFolderFile;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class LoadLanguage implements Runnable {

    private SoftApplicationCore application;

    private String dir;

    private String[] args;

    private AbstractImage icon;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public LoadLanguage(SoftApplicationCore _application, String _dir, String[] _args,
                        AbstractImage _icon) {
        application = _application;
        dir = StreamFolderFile.getTempFolder(_application.getFrames(),_dir);
        args = _args;
        icon = _icon;
    }

    @Override
    public void run() {
        application.loadLaungage(dir, args, icon);
    }
}
