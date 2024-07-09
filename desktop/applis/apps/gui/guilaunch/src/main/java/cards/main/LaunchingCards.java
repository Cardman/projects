package cards.main;

import cards.facade.FacadeCards;
import cards.gui.WindowCards;
import code.gui.*;

/**
    le lancement du logiciel*/
public class LaunchingCards extends AdvSoftApplicationCore {

    //private static final Image ICON = getImage(FileConst.RESOURCES_IMAGES, FileConst.SUITS_TXT, FileConst.SUITS_PNG);

    public LaunchingCards(WithAppFactories _frames) {
        super(_frames);
    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main) {
        FacadeCards.coreFolder(WindowCards.getTempFolderSl(getFrames()),getFrames());
//        TopLeftFrame coordonnees_= FileDialog.loadCoords(WindowCards.getTempFolder(getFrames()), FileConst.COORDS, getFrames().getFileCoreStream(), getFrames().getStreams());
        getFrames().getCompoFactory().invokeNow(new LaunchingGame(_args.getFileNames(), getFrames(),getAppFactories().getCardFactories(), _lgMenu, _main));
    }

}
