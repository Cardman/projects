package cards.main;

import cards.belote.HandBelote;
import cards.belote.sml.DocumentWriterBeloteUtil;
import cards.facade.FacadeCards;
import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;
import cards.gui.dialogs.FileConst;
import cards.president.HandPresident;
import cards.president.RulesPresident;
import cards.president.sml.DocumentWriterPresidentUtil;
import cards.tarot.HandTarot;
import cards.tarot.sml.DocumentWriterTarotUtil;
import code.gui.AdvSoftApplicationCore;
import code.gui.AppFactories;
import code.gui.GuiBaseUtil;
import code.gui.TopLeftFrame;
import code.gui.files.FileDialog;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.LoadLanguageUtil;
import code.stream.AbstractFile;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

/**
    le lancement du logiciel*/
public class LaunchingCards extends AdvSoftApplicationCore {

    private static final char LINE_RETURN = '\n';

    //private static final Image ICON = getImage(FileConst.RESOURCES_IMAGES, FileConst.SUITS_TXT, FileConst.SUITS_PNG);

    public LaunchingCards(AbstractProgramInfos _frames, AppFactories _cf) {
        super(_frames,_cf);
    }

    public LaunchingCards(AbstractProgramInfos _frames, CardFactories _cf) {
        this(_frames,new AppFactories(null,_cf,null));
    }

    @Override
    protected void launch(String _language, String[] _args) {
        StreamFolderFile.makeParent(StringUtil.concat(WindowCards.getTempFolderSl(getFrames()), FacadeCards.DECK_FOLDER), getFrames().getFileCoreStream());
        AbstractFile f = getFrames().getFileCoreStream().newFile(StringUtil.concat(WindowCards.getTempFolderSl(getFrames()), FacadeCards.DECK_FOLDER, StreamTextFile.SEPARATEUR, GameEnum.BELOTE.name(), FileConst.DECK_EXT));
        HandBelote mainB_=HandBelote.pileBase();
        if(!f.exists()) {
            StreamTextFile.saveTextFile(f.getAbsolutePath(), DocumentWriterBeloteUtil.setHandBelote(mainB_), getFrames().getStreams());
        }
        f=getFrames().getFileCoreStream().newFile(StringUtil.concat(WindowCards.getTempFolderSl(getFrames()),FacadeCards.DECK_FOLDER,StreamTextFile.SEPARATEUR,GameEnum.TAROT.name(),FileConst.DECK_EXT));
        HandTarot mainT_=HandTarot.pileBase();
        if(!f.exists()) {
            StreamTextFile.saveTextFile(f.getAbsolutePath(), DocumentWriterTarotUtil.setHandTarot(mainT_), getFrames().getStreams());
        }
        int maxStacks_ = RulesPresident.getNbMaxStacksPlayers();
        for (int i = IndexConstants.ONE_ELEMENT; i <= maxStacks_; i++) {
            f=getFrames().getFileCoreStream().newFile(StringUtil.concat(WindowCards.getTempFolderSl(getFrames()),FacadeCards.DECK_FOLDER,StreamTextFile.SEPARATEUR,GameEnum.PRESIDENT.name(),Long.toString(i),FileConst.DECK_EXT));
            HandPresident h_ = HandPresident.stack(i);
            if(!f.exists()) {
                StreamTextFile.saveTextFile(f.getAbsolutePath(), DocumentWriterPresidentUtil.setHandPresident(h_), getFrames().getStreams());
            }
        }
        f=getFrames().getFileCoreStream().newFile(StringUtil.concat(WindowCards.getTempFolderSl(getFrames()),FacadeCards.DECK_FOLDER,StreamTextFile.SEPARATEUR,FacadeCards.DECK_FILE));
        if(!f.exists()) {
            StringList dealsNumbers_ = new StringList();
            int nbGames_ = GameEnum.values().length;
            for (int i = IndexConstants.FIRST_INDEX; i<nbGames_; i++) {
                dealsNumbers_.add("0");
            }
            StreamTextFile.saveTextFile(f.getAbsolutePath(), StringUtil.join(dealsNumbers_, LINE_RETURN), getFrames().getStreams());
        }
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
