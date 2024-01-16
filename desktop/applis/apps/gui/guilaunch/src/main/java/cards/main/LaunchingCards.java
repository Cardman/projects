package cards.main;

import cards.facade.FacadeCards;
import cards.gui.WindowCards;
import cards.gui.dialogs.FileConst;
import code.gui.AdvSoftApplicationCore;
import code.gui.AppFactories;
import code.gui.GuiBaseUtil;
import code.gui.TopLeftFrame;
import code.gui.files.FileDialog;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.LoadLanguageUtil;
import code.util.StringList;
import code.util.core.StringUtil;

/**
    le lancement du logiciel*/
public class LaunchingCards extends AdvSoftApplicationCore {

    //private static final Image ICON = getImage(FileConst.RESOURCES_IMAGES, FileConst.SUITS_TXT, FileConst.SUITS_PNG);

    public LaunchingCards(AbstractProgramInfos _frames, AppFactories _cf) {
        super(_frames,_cf);
    }

    public LaunchingCards(AbstractProgramInfos _frames, CardFactories _cf) {
        this(_frames,new AppFactories(null,_cf,null));
    }

    @Override
    protected void launch(String _language, String[] _args) {
        FacadeCards.install(WindowCards.getTempFolderSl(getFrames()),getFrames());
        TopLeftFrame coordonnees_= FileDialog.loadCoords(WindowCards.getTempFolder(getFrames()), FileConst.COORDS, getFrames().getFileCoreStream(), getFrames().getStreams());
        GuiBaseUtil.invokeLater(new LaunchingGame(getFile(_args), _language,coordonnees_, getFrames(),getAppFactories().getCardFactories()), getFrames());
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
