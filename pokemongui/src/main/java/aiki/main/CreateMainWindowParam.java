package aiki.main;
import java.io.File;

import javax.swing.SwingUtilities;

import aiki.db.DataBase;
import aiki.db.PerCent;
import aiki.sml.LoadingGame;
import aiki.gui.MainWindow;
import aiki.gui.threads.PerCentIncr;
import code.util.StringList;
import code.util.StringMap;

/**This class thread is independant from EDT,
Thread safe class*/
public final class CreateMainWindowParam extends Thread {

    private MainWindow window;

    private LoadingGame load;

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
        String loadRom_;
        boolean stoppedLoading_ = false;
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
        PerCent p_ = new PerCentIncr();
        window.getLoadFlag().set(true);
        OpeningGame opening_ = new OpeningGame(window,p_);
        opening_.start();
        if (!load.getLastSavedGame().isEmpty()) {
            window.loadRomGame(load, path, files, true,p_);
        } else {
            window.loadOnlyRom(path_,p_);
        }
        if (!window.getLoadFlag().get()) {
            stoppedLoading_ = true;
        }
        window.getLoadFlag().set(false);
        window.setLoadingConf(load, false);
        SwingUtilities.invokeLater(new AfterLoadingBegin(window, stoppedLoading_, false, loadRom_));
    }

    public MainWindow getWindow() {
        return window;
    }
}
