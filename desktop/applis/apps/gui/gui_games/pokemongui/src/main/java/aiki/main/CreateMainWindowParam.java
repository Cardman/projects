package aiki.main;

import aiki.db.DataBase;
import aiki.gui.WindowAiki;
import aiki.sml.LoadingGame;
import code.stream.AbstractFile;
import code.stream.PathsUtil;
import code.threads.AbstractAtomicIntegerCoreAdd;
import code.threads.AbstractFuture;
import code.threads.AbstractScheduledExecutorService;
import code.util.StringList;
import code.util.core.StringUtil;

/**This class thread is independant from EDT,
Thread safe class*/
public final class CreateMainWindowParam implements Runnable {

    private final WindowAiki window;

    private final LoadingGame load;

    private final String path;

    private final StringList files;

    /**This class thread is independant from EDT*/
    public CreateMainWindowParam(WindowAiki _window, LoadingGame _load, String _path, StringList _files) {
        window = _window;
        load = _load;
        path = _path;
        files = _files;
    }

    @Override
    public void run() {
        String loadRom_;
        String path_;
        if (!load.getLastRom().isEmpty()) {
            String lastRom_ = StringUtil.replaceBackSlash(load.getLastRom());
            AbstractFile file_ = window.getFileCoreStream().newFile(lastRom_);
            if (!PathsUtil.isAbsolute(lastRom_, window.getFileCoreStream())) {
                path_ = StringUtil.concat(path,load.getLastRom());
            } else {
                path_ = file_.getAbsolutePath();
            }
            path_ = StringUtil.replaceBackSlash(path_);
        } else {
            path_ = DataBase.EMPTY_STRING;
        }
        loadRom_ = path_;
        AbstractAtomicIntegerCoreAdd p_ = window.getThreadFactory().newAtomicInteger();
        AbstractScheduledExecutorService sch_ = window.getThreadFactory().newScheduledExecutorService();
        window.getLoadFlag().set(true);
        OpeningGame opening_ = new OpeningGame(window,p_);
        OpeningGame.init(window);
        AbstractFuture abs_ = sch_.scheduleAtFixedRateNanos(opening_, 0, 1);
        if (!load.getLastSavedGame().isEmpty()) {
            window.loadRomGame(load, path, files, true,p_);
        } else {
            window.loadOnlyRom(path_,p_);
        }
        window.getLoadFlag().set(false);
        abs_.cancel(false);
        sch_.shutdown();
        OpeningGame.end(window);
        window.setLoadingConf(load, false);
        window.getFrames().getCompoFactory().invokeNow(new AfterLoadingBegin(window, loadRom_));
    }

    public WindowAiki getWindow() {
        return window;
    }
}
