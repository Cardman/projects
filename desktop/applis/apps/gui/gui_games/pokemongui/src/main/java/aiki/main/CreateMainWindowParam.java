package aiki.main;
import java.io.File;

import aiki.db.DataBase;
import aiki.db.PerCent;
import aiki.sml.LoadingGame;
import aiki.gui.MainWindow;
import aiki.gui.threads.PerCentIncr;
import code.gui.CustComponent;
import code.stream.StreamFolderFile;
import code.util.StringMap;
import code.util.core.StringUtil;

/**This class thread is independant from EDT,
Thread safe class*/
public final class CreateMainWindowParam implements Runnable {

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
            String lastRom_ = StringUtil.replaceBackSlash(load.getLastRom());
            File file_ = new File(lastRom_);
            if (!StreamFolderFile.isAbsolute(lastRom_)) {
                path_ = StringUtil.concat(path,load.getLastRom());
            } else {
                path_ = file_.getAbsolutePath();
            }
            path_ = StringUtil.replaceBackSlash(path_);
        } else {
            path_ = DataBase.EMPTY_STRING;
        }
        loadRom_ = path_;
        PerCent p_ = new PerCentIncr();
        window.getLoadFlag().set(true);
        OpeningGame opening_ = new OpeningGame(window,p_);
        window.getThreadFactory().newStartedThread(opening_);
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
        CustComponent.invokeLater(new AfterLoadingBegin(window, stoppedLoading_, false, loadRom_));
    }

    public MainWindow getWindow() {
        return window;
    }
}
