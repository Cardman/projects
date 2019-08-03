package aiki.main;
import aiki.sml.LoadingGame;
import aiki.gui.MainWindow;
import code.gui.SetStyle;
import code.gui.SoftApplicationCore;
import code.gui.TopLeftFrame;
import code.util.StringMap;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class CreateMainWindow extends Thread {

    private MainWindow window;

    private LoadingGame load;

    private StringMap<Object> withParam;

    private String path;

    private TopLeftFrame topLeft;

    private String lg;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public CreateMainWindow(LoadingGame _load, StringMap<Object> _withParam, String _path, TopLeftFrame _topLeft, String _lg) {
        load = _load;
        withParam = _withParam;
        path = _path;
        topLeft = _topLeft;
        lg = _lg;
    }

    @Override
    public void run() {
        window = new MainWindow(lg);
        SoftApplicationCore.setLocation(window, topLeft);
        window.pack();
        SetStyle.setupStyle(window);
        window.setVisible(true);
        if (!withParam.isEmpty()) {
            new CreateMainWindowParam(window, load, path, withParam).start();
        } else {
            new CreateMainWindowNoParam(window, load, path).start();
        }
    }
}
