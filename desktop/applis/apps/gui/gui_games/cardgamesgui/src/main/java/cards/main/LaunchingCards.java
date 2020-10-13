package cards.main;
import java.awt.image.BufferedImage;
import java.io.File;

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
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.LoadLanguageUtil;
import code.gui.initialize.ProgramInfos;
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

    public LaunchingCards() {
        this(new ProgramInfos());
    }

    public LaunchingCards(AbstractProgramInfos _frames) {
        super(_frames);
    }

    @Override
    protected void launch(String _language, StringMap<Object> _args) {
        File f;
        f=new File(StringUtil.concat(getTempFolderSl(getFrames()),FileConst.DECK_FOLDER));
        f.mkdirs();
        f=new File(StringUtil.concat(getTempFolderSl(getFrames()),FileConst.DECK_FOLDER,StreamTextFile.SEPARATEUR,GameEnum.BELOTE.name(),FileConst.DECK_EXT));
        HandBelote mainB_=HandBelote.pileBase();
        if(!f.exists()) {
            StreamTextFile.saveTextFile(f.getAbsolutePath(), DocumentWriterBeloteUtil.setHandBelote(mainB_));
        }
        f=new File(StringUtil.concat(getTempFolderSl(getFrames()),FileConst.DECK_FOLDER,StreamTextFile.SEPARATEUR,GameEnum.TAROT.name(),FileConst.DECK_EXT));
        HandTarot mainT_=HandTarot.pileBase();
        if(!f.exists()) {
            StreamTextFile.saveTextFile(f.getAbsolutePath(), DocumentWriterTarotUtil.setHandTarot(mainT_));
        }
        int maxStacks_ = RulesPresident.getNbMaxStacksPlayers();
        for (int i = IndexConstants.ONE_ELEMENT; i <= maxStacks_; i++) {
            f=new File(StringUtil.concat(getTempFolderSl(getFrames()),FileConst.DECK_FOLDER,StreamTextFile.SEPARATEUR,GameEnum.PRESIDENT.name(),Long.toString(i),FileConst.DECK_EXT));
            HandPresident h_ = HandPresident.stack(i);
            if(!f.exists()) {
                StreamTextFile.saveTextFile(f.getAbsolutePath(), DocumentWriterPresidentUtil.setHandPresident(h_));
            }
        }
        f=new File(StringUtil.concat(getTempFolderSl(getFrames()),FileConst.DECK_FOLDER,StreamTextFile.SEPARATEUR,FileConst.DECK_FILE));
        if(!f.exists()) {
            StringList dealsNumbers_ = new StringList();
            int nbGames_ = GameEnum.values().length;
            for (int i = IndexConstants.FIRST_INDEX; i<nbGames_; i++) {
                dealsNumbers_.add("0");
            }
            StreamTextFile.saveTextFile(f.getAbsolutePath(), StringUtil.join(dealsNumbers_, LINE_RETURN));
        }
        TopLeftFrame coordonnees_=loadCoords(getTempFolder(getFrames()), FileConst.COORDS);
        CustComponent.invokeLater(new LaunchingGame(_args, _language,coordonnees_, getFrames()));
    }

    protected static void loadLaungage(String[] _args) {
        //loadLaungage(_args, _icon_);
//        ThreadInvoker.invokeNow(new LoadLanguage(getTempFolder(), this, _args, getIcon()));
        LoadLanguageUtil.loadLaungage(new LaunchingCards(), TEMP_FOLDER, _args);
    }

    public static BufferedImage getIcon() {
        return getImage(FileConst.RESOURCES_IMAGES, FileConst.SUITS_TXT);
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
    protected BufferedImage getImageIcon() {
        return getIcon();
    }

    @Override
    public Object getObject(String _fileName) {
        return DocumentReaderCardsUnionUtil.getObject(_fileName);
    }
}
