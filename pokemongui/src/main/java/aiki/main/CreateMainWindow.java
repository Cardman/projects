package aiki.main;
import code.gui.SetStyle;
import code.gui.SoftApplicationCore;
import code.gui.TopLeftFrame;
import code.util.StringMap;
import aiki.game.params.LoadingGame;
import aiki.gui.MainWindow;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class CreateMainWindow extends Thread {

    private MainWindow window;

    private LoadingGame load;

    private StringMap<Object> withParam;

    private String path;

    private TopLeftFrame topLeft;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public CreateMainWindow(LoadingGame _load, StringMap<Object> _withParam, String _path, TopLeftFrame _topLeft) {
        load = _load;
        withParam = _withParam;
        path = _path;
        topLeft = _topLeft;
    }

    @Override
    public void run() {
        window = new MainWindow();
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
