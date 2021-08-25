package aiki.main;

import aiki.db.DataBase;
import aiki.db.PerCent;
import aiki.sml.LoadingGame;
import aiki.gui.WindowAiki;
import aiki.gui.threads.PerCentIncr;
import code.gui.FrameUtil;
import code.stream.AbstractFile;
import code.stream.StreamFolderFile;
import code.util.StringMap;
import code.util.core.StringUtil;

/**This class thread is independant from EDT,
Thread safe class*/
public final class CreateMainWindowNoParam implements Runnable {

    private WindowAiki window;

    private LoadingGame load;

    private String path;

    /**This class thread is independant from EDT*/
    public CreateMainWindowNoParam(WindowAiki _window, LoadingGame _load, String _path) {
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
            String lastRom_ = StringUtil.replaceBackSlash(load.getLastRom());
            AbstractFile file_ = window.getFileCoreStream().newFile(lastRom_);
            if (!StreamFolderFile.isAbsolute(lastRom_, window.getFileCoreStream())) {
                path_ = StringUtil.concat(path,load.getLastRom());
            } else {
                path_ = file_.getAbsolutePath();
            }
            path_ = StringUtil.replaceBackSlash(path_);
        } else {
            path_ = DataBase.EMPTY_STRING;
        }
        loadRom_ = path_;
        PerCent p_ = new PerCentIncr(window.getThreadFactory().newAtomicInteger());
        window.getLoadFlag().set(true);
        OpeningGame opening_ = new OpeningGame(window,p_);
        window.getThreadFactory().newStartedThread(opening_);
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
        FrameUtil.invokeLater(new AfterLoadingBegin(window, stoppedLoading_, false, loadRom_));
    }
}
