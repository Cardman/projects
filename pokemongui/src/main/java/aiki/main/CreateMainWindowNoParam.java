package aiki.main;
import java.io.File;

import javax.swing.SwingUtilities;

import code.util.StringList;
import code.util.StringMap;
import aiki.DataBase;
import aiki.exceptions.GameLoadException;
import aiki.game.params.LoadingGame;
import aiki.gui.MainWindow;

/**This class thread is independant from EDT,
Thread safe class*/
public final class CreateMainWindowNoParam extends Thread {

    private MainWindow window;

    private LoadingGame load;

    private boolean error;

    private boolean stopLoad;

    private String fileName;

    private String path;

    /**This class thread is independant from EDT*/
    public CreateMainWindowNoParam(MainWindow _window, LoadingGame _load, String _path) {
        window = _window;
        load = _load;
        path = _path;
    }

    @Override
    public void run() {
        //Timer t_ = null;
        boolean stoppedLoading_ = false;
        //t_ = new Timer(0, OpeningGame.getTaskPaintingLabel());
        stoppedLoading_ = false;
        boolean error_ = false;
        String loadRom_ = DataBase.EMPTY_STRING;
        try {
            if (!load.isLoadLastRom()) {
                window.setLoadingConf(load, true);
                return;
            }
            String path_;
            if (!load.getLastRom().isEmpty()) {
                File file_ = new File(StringList.replaceBackSlash(load.getLastRom()));
                if (!file_.isAbsolute()) {
                    path_ = path+load.getLastRom();
                } else {
                    path_ = file_.getAbsolutePath();
                }
                path_ = StringList.replaceBackSlash(path_);
            } else {
                path_ = DataBase.EMPTY_STRING;
            }
            loadRom_ = path_;
            OpeningGame opening_ = new OpeningGame(window);
            DataBase.setLoading(true);
            //ThreadInvoker.invokeNow(opening_);
            opening_.start();
//            CreateMainWindow.copyZipFileToFolder(path_, Constants.getTmpUserFolder());
            if (load.loadRomAndGame()) {
                window.loadRomGame(load, path, new StringMap<Object>());
            } else {
                window.loadOnlyRom(path_);
            }
            if (!DataBase.isLoading()) {
                stoppedLoading_ = true;
            }
            DataBase.setLoading(false);
        } catch (GameLoadException _0) {
            DataBase.setLoading(false);
            //NumericString.setCheckSyntax(false);
            _0.printStackTrace();
        } catch (RuntimeException _0) {
            error_ = window.getFacade().getData() == null;
            DataBase.setLoading(false);
            //NumericString.setCheckSyntax(false);
            _0.printStackTrace();
        } catch (VirtualMachineError _0) {
            error_ = true;
            DataBase.setLoading(false);
            //NumericString.setCheckSyntax(false);
            _0.printStackTrace();
        }
        //SoftApplication.setLocation(window, topLeft);
        error = error_;
        stopLoad = stoppedLoading_;
        fileName = loadRom_;
        window.setLoadingConf(load, true);
        SwingUtilities.invokeLater(new AfterLoadingBegin(window, stopLoad, error, fileName));
    }
}
