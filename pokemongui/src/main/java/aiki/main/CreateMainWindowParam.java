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
        //Timer t_ = null;
        boolean stoppedLoading_ = false;
        //t_ = new Timer(0, OpeningGame.getTaskPaintingLabel());
        try {
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
            DataBase.setLoading(true);
            opening_.start();
//            CreateMainWindow.copyZipFileToFolder(path_, Constants.getTmpUserFolder());
            if (!load.getLastSavedGame().isEmpty()) {
                window.loadRomGame(load, path, files);
            } else {
                window.loadOnlyRom(path_);
            }
            if (!DataBase.isLoading()) {
                stoppedLoading_ = true;
            }
            DataBase.setLoading(false);
            window.setLoadingConf(load, false);
        } catch (GameLoadException _0) {
            //NumericString.setCheckSyntax(false);
            DataBase.setLoading(false);
            window.setLoadingConf(load, false);
            _0.printStackTrace();
        } catch (RuntimeException _0) {
            error_ = window.getFacade().getData() == null;
            stoppedLoading_ = false;
            //NumericString.setCheckSyntax(false);
            DataBase.setLoading(false);
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
        SwingUtilities.invokeLater(new AfterLoadingBegin(window, stopLoad, error, fileName));
//        PackingWindowAfter.pack(window);
//        if (stoppedLoading_) {
//            return;
//        }
//        if (error_) {
//            window.showErrorMessageDialog(loadRom_);
//        } else {
//            window.showSuccessfulMessageDialog(loadRom_);
//        }
    }

    public MainWindow getWindow() {
        return window;
    }
}
