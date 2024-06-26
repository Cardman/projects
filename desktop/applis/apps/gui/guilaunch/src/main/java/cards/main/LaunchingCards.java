package cards.main;

import cards.facade.FacadeCards;
import cards.gui.WindowCards;
import cards.gui.dialogs.FileConst;
import code.gui.*;
import code.gui.files.FileDialog;
import code.gui.initialize.LoadLanguageUtil;

/**
    le lancement du logiciel*/
public class LaunchingCards extends AdvSoftApplicationCore {

    //private static final Image ICON = getImage(FileConst.RESOURCES_IMAGES, FileConst.SUITS_TXT, FileConst.SUITS_PNG);

    public LaunchingCards(WithAppFactories _frames) {
        super(_frames);
    }

    @Override
    protected void launch(String _language, String[] _args, EnabledMenu _lgMenu) {
        FacadeCards.coreFolder(WindowCards.getTempFolderSl(getFrames()),getFrames());
        TopLeftFrame coordonnees_= FileDialog.loadCoords(WindowCards.getTempFolder(getFrames()), FileConst.COORDS, getFrames().getFileCoreStream(), getFrames().getStreams());
        getFrames().getCompoFactory().invokeNow(new LaunchingGame(getFile(_args), coordonnees_, getFrames(),getAppFactories().getCardFactories(), _lgMenu));
    }

    protected static void loadLaungage(String[] _args, LaunchingCards _soft) {
        //loadLaungage(_args, _icon_);
//        ThreadInvoker.invokeNow(new LoadLanguage(getTempFolder(), this, _args, getIcon()));
        LoadLanguageUtil.loadLaungage(_soft, WindowCards.TEMP_FOLDER, _args);
    }

    @Override
    protected String getApplicationName() {
        return WindowCards.APP_CARDS;
    }

}
