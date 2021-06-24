package cards.main;

import cards.belote.HandBelote;
import cards.belote.sml.DocumentWriterBeloteUtil;
import cards.facade.enumerations.GameEnum;
import cards.facade.sml.DocumentReaderCardsUnionUtil;
import cards.gui.dialogs.FileConst;
import cards.president.HandPresident;
import cards.president.RulesPresident;
import cards.president.sml.DocumentWriterPresidentUtil;
import cards.tarot.HandTarot;
import cards.tarot.sml.DocumentWriterTarotUtil;
import code.gui.*;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.LoadLanguageUtil;
import code.scripts.messages.gui.MessCardVideoGr;
import code.stream.AbstractFile;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

/**
    le lancement du logiciel*/
public class LaunchingCards extends AdvSoftApplicationCore {

    private static final char LINE_RETURN = '\n';
    private static final String TEMP_FOLDER = "cards";

    //private static final Image ICON = getImage(FileConst.RESOURCES_IMAGES, FileConst.SUITS_TXT, FileConst.SUITS_PNG);
    private final CardFactories factories;

    public LaunchingCards(AbstractProgramInfos _frames, CardFactories _factories) {
        super(_frames);
        factories = _factories;
    }

    @Override
    protected void launch(String _language, StringMap<Object> _args) {
        StreamFolderFile.makeParent(StringUtil.concat(getTempFolderSl(getFrames()),FileConst.DECK_FOLDER), getFrames().getFileCoreStream());
        AbstractFile f = getFrames().getFileCoreStream().newFile(StringUtil.concat(getTempFolderSl(getFrames()), FileConst.DECK_FOLDER, StreamTextFile.SEPARATEUR, GameEnum.BELOTE.name(), FileConst.DECK_EXT));
        HandBelote mainB_=HandBelote.pileBase();
        if(!f.exists()) {
            StreamTextFile.saveTextFile(f.getAbsolutePath(), DocumentWriterBeloteUtil.setHandBelote(mainB_), getFrames().getStreams());
        }
        f=getFrames().getFileCoreStream().newFile(StringUtil.concat(getTempFolderSl(getFrames()),FileConst.DECK_FOLDER,StreamTextFile.SEPARATEUR,GameEnum.TAROT.name(),FileConst.DECK_EXT));
        HandTarot mainT_=HandTarot.pileBase();
        if(!f.exists()) {
            StreamTextFile.saveTextFile(f.getAbsolutePath(), DocumentWriterTarotUtil.setHandTarot(mainT_), getFrames().getStreams());
        }
        int maxStacks_ = RulesPresident.getNbMaxStacksPlayers();
        for (int i = IndexConstants.ONE_ELEMENT; i <= maxStacks_; i++) {
            f=getFrames().getFileCoreStream().newFile(StringUtil.concat(getTempFolderSl(getFrames()),FileConst.DECK_FOLDER,StreamTextFile.SEPARATEUR,GameEnum.PRESIDENT.name(),Long.toString(i),FileConst.DECK_EXT));
            HandPresident h_ = HandPresident.stack(i);
            if(!f.exists()) {
                StreamTextFile.saveTextFile(f.getAbsolutePath(), DocumentWriterPresidentUtil.setHandPresident(h_), getFrames().getStreams());
            }
        }
        f=getFrames().getFileCoreStream().newFile(StringUtil.concat(getTempFolderSl(getFrames()),FileConst.DECK_FOLDER,StreamTextFile.SEPARATEUR,FileConst.DECK_FILE));
        if(!f.exists()) {
            StringList dealsNumbers_ = new StringList();
            int nbGames_ = GameEnum.values().length;
            for (int i = IndexConstants.FIRST_INDEX; i<nbGames_; i++) {
                dealsNumbers_.add("0");
            }
            StreamTextFile.saveTextFile(f.getAbsolutePath(), StringUtil.join(dealsNumbers_, LINE_RETURN), getFrames().getStreams());
        }
        TopLeftFrame coordonnees_=loadCoords(getTempFolder(getFrames()), FileConst.COORDS, getFrames().getFileCoreStream(), getFrames().getStreams());
        CustComponent.invokeLater(new LaunchingGame(_args, _language,coordonnees_, getFrames(),factories));
    }

    protected static void loadLaungage(String[] _args, LaunchingCards _soft) {
        //loadLaungage(_args, _icon_);
//        ThreadInvoker.invokeNow(new LoadLanguage(getTempFolder(), this, _args, getIcon()));
        LoadLanguageUtil.loadLaungage(_soft, TEMP_FOLDER, _args);
    }

    public static AbstractImage getIcon(AbstractImageFactory _fact) {
        return getImage(MessCardVideoGr.ms().getVal(StringUtil.concat(FileConst.RESOURCES_IMAGES, StreamTextFile.SEPARATEUR, FileConst.SUITS_TXT)), _fact);
    }

    public static String getTempFolderSl(AbstractProgramInfos _tmpUserFolderSl) {
        return StringUtil.concat(getTempFolder(_tmpUserFolderSl), StreamTextFile.SEPARATEUR);
    }
    public static String getTempFolder(AbstractProgramInfos _tmpUserFolderSl) {
        return getTempFolder(_tmpUserFolderSl,TEMP_FOLDER);
    }

    @Override
    protected String getApplicationName() {
        return getMainWindowClass();
    }
    public static String getMainWindowClass() {
        return "cards";
    }

    @Override
    public Object getObject(String _fileName) {
        return DocumentReaderCardsUnionUtil.getObject(_fileName, getFrames().getFileCoreStream(), getFrames().getStreams());
    }
}
