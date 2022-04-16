package aiki.main;

import aiki.db.DataBase;
import aiki.db.PerCent;
import aiki.gui.WindowAiki;
import aiki.gui.threads.PerCentIncr;
import aiki.sml.LoadingData;
import aiki.sml.LoadingGame;
import code.gui.FrameUtil;
import code.stream.AbstractFile;
import code.stream.StreamFolderFile;
import code.threads.AbstractFuture;
import code.threads.AbstractScheduledExecutorService;
import code.util.StringList;
import code.util.core.StringUtil;

/**This class thread is independant from EDT,
Thread safe class*/
public final class CreateMainWindowNoParam implements Runnable {

    private WindowAiki window;

    private LoadingGame load;

    private String path;
    private final LoadingData loadingData;

    /**This class thread is independant from EDT*/
    public CreateMainWindowNoParam(WindowAiki _window, LoadingGame _load, String _path, LoadingData _loading) {
        window = _window;
        load = _load;
        path = _path;
        loadingData = _loading;
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
        AbstractScheduledExecutorService sch_ = window.getThreadFactory().newScheduledExecutorService();
        window.getLoadFlag().set(true);
        OpeningGame opening_ = new OpeningGame(window,p_);
        OpeningGame.init(window);
        AbstractFuture abs_ = sch_.scheduleAtFixedRateNanos(opening_, 0, 1);
        if (load.loadRomAndGame()) {
            window.loadRomGame(load, path, new StringList(), false,p_,loadingData);
        } else {
            window.loadOnlyRom(path_,p_,loadingData);
        }
        if (!window.getLoadFlag().get()) {
            stoppedLoading_ = true;
        }
        window.getLoadFlag().set(false);
        abs_.cancel(false);
        sch_.shutdown();
        OpeningGame.end(window);
        window.setLoadingConf(load, true);
        FrameUtil.invokeLater(new AfterLoadingBegin(window, stoppedLoading_, false, loadRom_), window.getFrames());
    }
}
