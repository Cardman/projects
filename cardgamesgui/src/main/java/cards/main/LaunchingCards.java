package cards.main;
import java.awt.Image;
import java.io.File;

import javax.swing.SwingUtilities;

import cards.belote.HandBelote;
import cards.facade.enumerations.GameEnum;
import cards.gui.MainWindow;
import cards.gui.dialogs.FileConst;
import cards.president.HandPresident;
import cards.president.RulesPresident;
import cards.tarot.HandTarot;
import code.gui.LoadLanguage;
import code.gui.SetStyle;
import code.gui.SoftApplication;
import code.gui.SoftApplicationCore;
import code.gui.ThreadInvoker;
import code.gui.TopLeftFrame;
import code.serialize.exceptions.BadObjectException;
import code.stream.StreamTextFile;
import code.util.CustList;
import code.util.Numbers;
import code.util.StringMap;
import code.util.consts.ConstFiles;
/**
    le lancement du logiciel*/
public class LaunchingCards extends SoftApplication {

    private static final char LINE_RETURN = '\n';
    private static final String TEMP_FOLDER = "cards";

    //private static final Image ICON = getImage(FileConst.RESOURCES_IMAGES, FileConst.SUITS_TXT, FileConst.SUITS_PNG);

    private static int _nbInstances_;

    @Override
    protected void launch(String _language, StringMap<Object> _args) {
        increment();
        MainWindow window_;
        installer();
        TopLeftFrame coordonnees_;
        try {
            coordonnees_=loadCoords(getTempFolder(), FileConst.COORDS);
            if (coordonnees_ == null) {
                coordonnees_ = new TopLeftFrame();
            }
        } catch(ClassCastException _0) {
            _0.printStackTrace();
            coordonnees_ = new TopLeftFrame();
        } catch(BadObjectException _0) {
            _0.printStackTrace();
            coordonnees_ = new TopLeftFrame();
        }
        window_ = new MainWindow();

        SoftApplicationCore.setLocation(window_, coordonnees_);
        window_.pack();
        SetStyle.setupStyle(window_);
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
        f=new File(getTempFolderSl()+FileConst.DECK_FOLDER);
        f.mkdirs();
        f=new File(getTempFolderSl()+FileConst.DECK_FOLDER+StreamTextFile.SEPARATEUR+GameEnum.BELOTE.name()+FileConst.DECK_EXT);
        HandBelote mainB_=HandBelote.pileBase();
        if(!f.exists()) {
            StreamTextFile.saveObject(f.getAbsolutePath(), mainB_);
        }
        f=new File(getTempFolderSl()+FileConst.DECK_FOLDER+StreamTextFile.SEPARATEUR+GameEnum.TAROT.name()+FileConst.DECK_EXT);
        HandTarot mainT_=HandTarot.pileBase();
        if(!f.exists()) {
            StreamTextFile.saveObject(f.getAbsolutePath(), mainT_);
        }
        int maxStacks_ = RulesPresident.getNbMaxStacksPlayers();
        for (int i = CustList.ONE_ELEMENT; i <= maxStacks_; i++) {
            f=new File(getTempFolderSl()+FileConst.DECK_FOLDER+StreamTextFile.SEPARATEUR+GameEnum.PRESIDENT.name()+i+FileConst.DECK_EXT);
            HandPresident h_ = HandPresident.stack(i);
            if(!f.exists()) {
                StreamTextFile.saveObject(f.getAbsolutePath(), h_);
            }
        }
        f=new File(getTempFolderSl()+FileConst.DECK_FOLDER+StreamTextFile.SEPARATEUR+FileConst.DECK_FILE);
        if(!f.exists()) {
            Numbers<Integer> dealsNumbers_ = new Numbers<Integer>();
            int nbGames_ = GameEnum.values().length;
            for (int i=CustList.FIRST_INDEX;i<nbGames_;i++) {
                dealsNumbers_.add(CustList.SIZE_EMPTY);
            }
            StreamTextFile.saveTextFile(f.getAbsolutePath(), dealsNumbers_.join(LINE_RETURN));
//            try {
//                BufferedWriter bw_=new BufferedWriter(new FileWriter(f));
//                int nbGames_ = GameEnum.values().length;
//                for(int i=CustList.FIRST_INDEX;i<nbGames_;i++) {
//                    bw_.write(new Integer(0).toString());
//                    bw_.newLine();
//                }
//                bw_.close();
//                //fichiersInstalles_.add(FileConst.DECK_FOLDER+StreamTextFile.SEPARATEUR+FileConst.DECK_FILE);
//            }catch(IOException e) {
//            }
        }
    }

    @Override
    public void launchWithoutLanguage(String _language, StringMap<Object> _args) {
        SwingUtilities.invokeLater(new LaunchingGame(this, _args, _language));
    }

    public static String getTempFolderSl() {
        return getTempFolder() + StreamTextFile.SEPARATEUR;
    }

    public static String getTempFolder() {
        new File(ConstFiles.getTmpUserFolderSl()+TEMP_FOLDER).mkdirs();
        return ConstFiles.getTmpUserFolderSl()+TEMP_FOLDER;
    }

    public static Class<?> getMainWindowClass() {
        return MainWindow.class;
    }

    @Override
    protected Image getImageIcon() {
        return getIcon();
    }
}
