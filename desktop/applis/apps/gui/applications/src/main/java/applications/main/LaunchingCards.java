package applications.main;

import applications.code.gui.AdvSoftApplicationCore;
import applications.code.gui.WithAppFactories;
import applications.gui.MessagesApplications;
import cards.facade.FacadeCards;
import cards.gui.WindowCards;
import cards.main.LaunchingGame;
import code.gui.AbsButton;
import code.gui.EnabledMenu;
import code.gui.InterpretedFile;
import code.gui.LanguagesButtonsPair;
import code.gui.images.ConverterGraphicBufferedImage;

/**
    le lancement du logiciel*/
public class LaunchingCards extends AdvSoftApplicationCore {

    //private static final Image ICON = getImage(FileConst.RESOURCES_IMAGES, FileConst.SUITS_TXT, FileConst.SUITS_PNG);

    public LaunchingCards(WithAppFactories _frames) {
        super(_frames);
    }

    @Override
    protected void launch(String _language, InterpretedFile _args, EnabledMenu _lgMenu, AbsButton _main, LanguagesButtonsPair _pair) {
        FacadeCards.coreFolder(WindowCards.getTempFolderSl(getFrames()),getFrames());
//        TopLeftFrame coordonnees_= FileDialog.loadCoords(WindowCards.getTempFolder(getFrames()), FileConst.COORDS, getFrames().getFileCoreStream(), getFrames().getStreams());
        getFrames().getCompoFactory().invokeNow(new LaunchingGame(_args.getFileNames(), getFrames(),getAppFactories().getCardFactories(), _pair, ConverterGraphicBufferedImage.decodeToImage(getFrames().getImageFactory(), MessagesApplications.cards())));
    }

}
