package code.player.main;


import code.gui.*;
import code.gui.initialize.LoadLanguageUtil;
import code.player.gui.CreateMainWindowPlayer;
import code.player.gui.WindowPlayer;

public class LaunchingPlayer extends AdvSoftApplicationCore {

    private static final String TEMP_FOLDER = "playersongs";

    public LaunchingPlayer(WithAppFactories _infos){
        super(_infos);
    }

    protected static void loadLaungage(String[] _args, LaunchingPlayer _soft) {
        LoadLanguageUtil.loadLaungage(_soft, TEMP_FOLDER, _args);
    }

    @Override
    protected void launch(String _language, String[] _args, EnabledMenu _lgMenu, AbsButton _main) {
        ThreadInvoker.invokeNow(getFrames().getThreadFactory(),new CreateMainWindowPlayer(_language,getFile(_args), getFrames(), _main), getFrames());
    }

    @Override
    protected String getApplicationName() {
        return WindowPlayer.APPS_MUSICPLAYER;
    }

}

