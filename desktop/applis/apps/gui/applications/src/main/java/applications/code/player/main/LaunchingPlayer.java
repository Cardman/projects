package applications.code.player.main;


import applications.code.gui.AdvSoftApplicationCore;
import applications.code.gui.WithAppFactories;
import applications.gui.MessagesApplications;
import code.gui.AbsButton;
import code.gui.EnabledMenu;
import code.gui.InterpretedFile;
import code.gui.LanguagesButtonsPair;
import code.gui.images.ConverterGraphicBufferedImage;
import code.player.gui.CreateMainWindowPlayer;

public class LaunchingPlayer extends AdvSoftApplicationCore {

//    public static final String TEMP_FOLDER = "playersongs";

    public LaunchingPlayer(WithAppFactories _infos){
        super(_infos);
    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main, LanguagesButtonsPair _pair) {
        getFrames().getCompoFactory().invokeNow(new CreateMainWindowPlayer(_language,_args.getFileNames(), getFrames(), _pair, ConverterGraphicBufferedImage.decodeToImage(getFrames().getImageFactory(),MessagesApplications.player())));
    }

}

