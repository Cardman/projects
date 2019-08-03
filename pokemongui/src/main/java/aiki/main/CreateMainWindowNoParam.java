package aiki.main;
import java.io.File;

import javax.swing.SwingUtilities;

import aiki.db.DataBase;
import aiki.db.PerCent;
import aiki.sml.LoadingGame;
import aiki.gui.MainWindow;
import aiki.gui.threads.PerCentIncr;
import code.gui.CustComponent;
import code.util.StringList;
import code.util.StringMap;

/**This class thread is independant from EDT,
Thread safe class*/
public final class CreateMainWindowNoParam implements Runnable {

    private MainWindow window;

    private LoadingGame load;

    private String path;

    /**This class thread is independant from EDT*/
    public CreateMainWindowNoParam(MainWindow _window, LoadingGame _load, String _path) {
        window = _window;
        load = _load;
        path = _path;
    }

    @Override
    public void run() {
        boolean stoppedLoading_ = false;
        String loadRom_;
        if (!load.isLoadLastRom()) {
            window.setLoadingConf(load, true);
            return;
        }
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
        CustComponent.newThread(opening_).start();
        if (load.loadRomAndGame()) {
            window.loadRomGame(load, path, new StringMap<Object>(), false,p_);
        } else {
            window.loadOnlyRom(path_,p_);
        }
        if (!window.getLoadFlag().get()) {
            stoppedLoading_ = true;
        }
        window.getLoadFlag().set(false);
        window.setLoadingConf(load, true);
        CustComponent.invokeLater(new AfterLoadingBegin(window, stoppedLoading_, false, loadRom_));
    }
}
