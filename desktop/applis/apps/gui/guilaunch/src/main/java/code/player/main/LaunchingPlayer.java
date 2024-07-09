package code.player.main;


import code.gui.*;
import code.player.gui.CreateMainWindowPlayer;

public class LaunchingPlayer extends AdvSoftApplicationCore {

    public static final String TEMP_FOLDER = "playersongs";

    public LaunchingPlayer(WithAppFactories _infos){
        super(_infos);
    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main) {
        getFrames().getCompoFactory().invokeNow(new CreateMainWindowPlayer(_language,_args.getFileNames(), getFrames(), _main));
    }

}

