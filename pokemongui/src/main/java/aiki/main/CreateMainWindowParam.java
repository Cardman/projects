package aiki.main;
import java.io.File;

import javax.swing.SwingUtilities;

import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.game.params.LoadingGame;
import aiki.gui.MainWindow;
import code.util.StringList;
import code.util.StringMap;

/**This class thread is independant from EDT,
Thread safe class*/
public final class CreateMainWindowParam extends Thread {

    private MainWindow window;

    private LoadingGame load;

    private boolean error;

    private boolean stopLoad;

    private String fileName;

    private String path;

    private StringMap<Object> files;

    /**This class thread is independant from EDT*/
    public CreateMainWindowParam(MainWindow _window, LoadingGame _load, String _path, StringMap<Object> _files) {
        window = _window;
        load = _load;
        path = _path;
        files = _files;
    }

    @Override
    public void run() {
        boolean error_ = false;
        String loadRom_ = DataBase.EMPTY_STRING;
        boolean stoppedLoading_ = false;
        FacadeGame fg_ = window.getFacade();
        String path_;
        if (!load.getLastRom().isEmpty()) {
            File file_ = new File(StringList.replaceBackSlash(load.getLastRom()));
            if (!file_.isAbsolute()) {
                path_ = StringList.concat(path,load.getLastRom());
            } else {
                path_ = file_.getAbsolutePath();
            }
            path_ = StringList.replaceBackSlash(path_);
        } else {
            path_ = DataBase.EMPTY_STRING;
        }
        loadRom_ = path_;
        OpeningGame opening_ = new OpeningGame(window);
        fg_.setLoading(true);
        opening_.start();
        if (!load.getLastSavedGame().isEmpty()) {
            window.loadRomGame(load, path, files, true);
        } else {
            window.loadOnlyRom(path_);
        }
        if (!fg_.isLoading()) {
            stoppedLoading_ = true;
        }
        fg_.setLoading(false);
        window.setLoadingConf(load, false);
        error = error_;
        stopLoad = stoppedLoading_;
        fileName = loadRom_;
        SwingUtilities.invokeLater(new AfterLoadingBegin(window, stopLoad, error, fileName));
    }

    public MainWindow getWindow() {
        return window;
    }
}
