package aiki.main;

import aiki.facade.SexListImpl;
import aiki.gui.WindowAiki;
import aiki.sml.LoadedGameConf;
import aiki.sml.Resources;
import aiki.sml.LoadingGame;
import code.gui.*;
import code.gui.files.FileDialog;
import code.gui.initialize.LoadLanguageUtil;
import code.stream.StreamFolderFile;
import code.util.StringList;
import code.util.core.StringUtil;

public class LaunchingPokemon extends AdvSoftApplicationCore {

    public LaunchingPokemon(WithAppFactories _frames) {
        super(_frames);
    }

    protected static void loadLaungage(String[] _args, LaunchingPokemon _soft) {
        //loadLaungage(_args, _icon_);
//        ThreadInvoker.invokeNow(new LoadLanguage(getTempFolder(), this, _args, getIcon()));
        LoadLanguageUtil.loadLaungage(_soft, WindowAiki.TEMP_FOLDER, _args);
    }

    @Override
    protected void launch(String _language, String[] _args, EnabledMenu _lgMenu) {
        StringList args_ = getFile(_args);
        LoadedGameConf load_ = getAppFactories().getAikiFactory().getConfPkStream().load(WindowAiki.getTempFolder(getFrames()), args_, new SexListImpl());
        LoadingGame param_ = load_.getLoadingGame();
        //String path_ = getFolderJarPath();
        TopLeftFrame topLeft_ = FileDialog.loadCoords(WindowAiki.getTempFolder(getFrames()), Resources.COORDS, getFrames().getFileCoreStream(), getFrames().getStreams());
        //path_ = pathConfig_;
        String path_ = StreamFolderFile.getCurrentPath(getFrames().getFileCoreStream());
//        if (!_args.isEmpty()) {
//            //open with
////            CreateMainWindow create_ = new CreateMainWindow(param_, true, path_, topLeft_);
////            create_.start();
//            ThreadInvoker.invokeNow(new CreateMainWindow(param_, _args, path_, topLeft_));
//            return;
//        }
//        path_ = Constants.getInitFolder();
//        CreateMainWindow create_ = new CreateMainWindow(param_, false, path_, topLeft_);
//        create_.start();
        ThreadInvoker.invokeNow(getFrames().getThreadFactory(),new CreateMainWindowAiki(param_, args_, path_, topLeft_, _language, getFrames(),getAppFactories().getAikiFactory()), getFrames());
    }

    protected StringList getFile(String[] _args) {
        StringList files_ = new StringList();
        if (_args.length > 0) {
            String fileName_ = getFrames().getFileCoreStream().newFile(_args[0]).getAbsolutePath();
            fileName_ = StringUtil.replaceBackSlash(fileName_);
            files_.add(fileName_);
        }
        return files_;
    }

    @Override
    protected String getApplicationName() {
        return WindowAiki.APPS_AIKI;
    }

}
