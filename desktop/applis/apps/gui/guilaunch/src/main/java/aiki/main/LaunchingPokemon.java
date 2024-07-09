package aiki.main;

import aiki.facade.SexListImpl;
import aiki.gui.WindowAiki;
import aiki.sml.LoadedGameConf;
import aiki.sml.LoadingGame;
import code.gui.*;
import code.util.StringList;

public class LaunchingPokemon extends AdvSoftApplicationCore {

    public LaunchingPokemon(WithAppFactories _frames) {
        super(_frames);
    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main) {
        StringList args_ = _args.getFileNames();
        LoadedGameConf load_ = getAppFactories().getAikiFactory().getConfPkStream().load(WindowAiki.getTempFolder(getFrames()), args_, new SexListImpl());
        LoadingGame param_ = load_.getLoadingGame();
        //String path_ = getFolderJarPath();
//        TopLeftFrame topLeft_ = FileDialog.loadCoords(WindowAiki.getTempFolder(getFrames()), Resources.COORDS, getFrames().getFileCoreStream(), getFrames().getStreams());
        //path_ = pathConfig_;
//        String path_ = StreamFolderFile.getCurrentPath(getFrames().getFileCoreStream());
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
        getFrames().getCompoFactory().invokeNow(new CreateMainWindowAiki(param_, args_, _lgMenu, getFrames(),getAppFactories().getAikiFactory(), _main));
    }

}
