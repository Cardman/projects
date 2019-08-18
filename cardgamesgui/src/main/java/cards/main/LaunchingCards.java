package cards.main;
import java.awt.Image;
import java.io.File;

import javax.swing.SwingUtilities;

import cards.belote.HandBelote;
import cards.belote.sml.DocumentWriterBeloteUtil;
import cards.facade.enumerations.GameEnum;
import cards.facade.sml.DocumentReaderCardsUnionUtil;
import cards.gui.MainWindow;
import cards.gui.dialogs.FileConst;
import cards.president.HandPresident;
import cards.president.RulesPresident;
import cards.president.sml.DocumentWriterPresidentUtil;
import cards.tarot.HandTarot;
import cards.tarot.sml.DocumentWriterTarotUtil;
import code.gui.*;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
/**
    le lancement du logiciel*/
public class LaunchingCards extends SoftApplicationCore {

    private static final char LINE_RETURN = '\n';
    private static final String TEMP_FOLDER = "cards";

    //private static final Image ICON = getImage(FileConst.RESOURCES_IMAGES, FileConst.SUITS_TXT, FileConst.SUITS_PNG);

    private static int _nbInstances_;

    @Override
    protected void launch(String _language, StringMap<Object> _args) {
        increment();
        MainWindow window_;
        installer();
        TopLeftFrame coordonnees_=loadCoords(getTempFolder(), FileConst.COORDS);
        window_ = new MainWindow(_language);

        SoftApplicationCore.setLocation(window_, coordonnees_);
        window_.pack();
        window_.setVisible(true);

        if (!_args.isEmpty()) {
            window_.loadGameBegin(_args.getKeys().first(), _args.values().first());
        }
    }

    protected static void loadLaungage(String[] _args) {
        //loadLaungage(_args, _icon_);
//        ThreadInvoker.invokeNow(new LoadLanguage(getTempFolder(), this, _args, getIcon()));
        ThreadInvoker.invokeNow(new LoadLanguage(getTempFolder(), new LaunchingCards(), _args, null));
    }

    public static void increment() {
        _nbInstances_++;
    }

    public static void decrement() {
        _nbInstances_--;
    }

    public static boolean alreadyLaunched() {
        return _nbInstances_ > 0;
    }

    public static Image getIcon() {
        return getImage(FileConst.RESOURCES_IMAGES, FileConst.SUITS_TXT);
    }

    /**
    jouees pendant le fonctionnement du logiciel*/
    private static void installer() {

        File f;
        f=new File(StringList.concat(getTempFolderSl(),FileConst.DECK_FOLDER));
        f.mkdirs();
        f=new File(StringList.concat(getTempFolderSl(),FileConst.DECK_FOLDER,StreamTextFile.SEPARATEUR,GameEnum.BELOTE.name(),FileConst.DECK_EXT));
        HandBelote mainB_=HandBelote.pileBase();
        if(!f.exists()) {
            StreamTextFile.saveTextFile(f.getAbsolutePath(), DocumentWriterBeloteUtil.setHandBelote(mainB_));
        }
        f=new File(StringList.concat(getTempFolderSl(),FileConst.DECK_FOLDER,StreamTextFile.SEPARATEUR,GameEnum.TAROT.name(),FileConst.DECK_EXT));
        HandTarot mainT_=HandTarot.pileBase();
        if(!f.exists()) {
            StreamTextFile.saveTextFile(f.getAbsolutePath(), DocumentWriterTarotUtil.setHandTarot(mainT_));
        }
        int maxStacks_ = RulesPresident.getNbMaxStacksPlayers();
        for (int i = CustList.ONE_ELEMENT; i <= maxStacks_; i++) {
            f=new File(StringList.concat(getTempFolderSl(),FileConst.DECK_FOLDER,StreamTextFile.SEPARATEUR,GameEnum.PRESIDENT.name(),Long.toString(i),FileConst.DECK_EXT));
            HandPresident h_ = HandPresident.stack(i);
            if(!f.exists()) {
                StreamTextFile.saveTextFile(f.getAbsolutePath(), DocumentWriterPresidentUtil.setHandPresident(h_));
            }
        }
        f=new File(StringList.concat(getTempFolderSl(),FileConst.DECK_FOLDER,StreamTextFile.SEPARATEUR,FileConst.DECK_FILE));
        if(!f.exists()) {
            StringList dealsNumbers_ = new StringList();
            int nbGames_ = GameEnum.values().length;
            for (int i=CustList.FIRST_INDEX;i<nbGames_;i++) {
                dealsNumbers_.add("0");
            }
            StreamTextFile.saveTextFile(f.getAbsolutePath(), StringList.join(dealsNumbers_, LINE_RETURN));
        }
    }

    @Override
    public void launchWithoutLanguage(String _language, StringMap<Object> _args) {
        CustComponent.invokeLater(new LaunchingGame(this, _args, _language));
    }

    public static String getTempFolderSl() {
        return StringList.concat(getTempFolder(), StreamTextFile.SEPARATEUR);
    }

    public static String getTempFolder() {
        new File(StringList.concat(ConstFiles.getTmpUserFolderSl(),TEMP_FOLDER)).mkdirs();
        return StringList.concat(ConstFiles.getTmpUserFolderSl(),TEMP_FOLDER);
    }

    public static String getMainWindowClass() {
        return "cards";
    }

    @Override
    protected Image getImageIcon() {
        return getIcon();
    }

    @Override
    public Object getObject(String _fileName) {
        return DocumentReaderCardsUnionUtil.getObject(_fileName);
    }
}
