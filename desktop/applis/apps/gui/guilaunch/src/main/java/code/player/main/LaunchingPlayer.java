package code.player.main;


import code.gui.*;
import code.player.gui.CreateMainWindowPlayer;
import code.player.gui.WindowPlayer;

public class LaunchingPlayer extends AdvSoftApplicationCore {

    public static final String TEMP_FOLDER = "playersongs";

    public LaunchingPlayer(WithAppFactories _infos){
        super(_infos);
    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main) {
        ThreadInvoker.invokeNow(getFrames().getThreadFactory(),new CreateMainWindowPlayer(_language,_args.getFileNames(), getFrames(), _main), getFrames());
    }

    @Override
    protected String getApplicationName() {
        return WindowPlayer.APPS_MUSICPLAYER;
    }

}

