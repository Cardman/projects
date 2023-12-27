package code.player.main;


import code.gui.*;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.LoadLanguageUtil;
import code.player.gui.CreateMainWindowPlayer;
import code.player.gui.WindowPlayer;
import code.util.StringList;
import code.util.core.StringUtil;

public class LaunchingPlayer extends AdvSoftApplicationCore {

    private static final String TEMP_FOLDER = "playersongs";

    public LaunchingPlayer(AbstractProgramInfos _infos){
        this(_infos,new AppFactories(null,null,null));
    }
    public LaunchingPlayer(AbstractProgramInfos _frames,AppFactories _app) {
        super(_frames,_app);
    }

    protected static void loadLaungage(String[] _args, LaunchingPlayer _soft) {
        LoadLanguageUtil.loadLaungage(_soft, TEMP_FOLDER, _args);
    }

    @Override
    protected void launch(String _language, String[] _args) {
        ThreadInvoker.invokeNow(getFrames().getThreadFactory(),new CreateMainWindowPlayer(_language,getFile(_args), getFrames()), getFrames());
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
        return WindowPlayer.APPS_MUSICPLAYER;
    }

}

