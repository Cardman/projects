package aiki.gui;






import aiki.db.*;
import aiki.gui.components.AbsMetaLabelPk;
import aiki.gui.components.walk.IntTileRender;
import aiki.gui.dialogs.*;
import aiki.gui.events.*;
import aiki.gui.listeners.AbsTaskEnabled;
import aiki.gui.listeners.DefTaskEnabled;
import aiki.gui.threads.*;
import aiki.main.*;
//import aiki.network.stream.*;
import aiki.sml.*;
import aiki.facade.FacadeGame;
import aiki.game.Game;
import aiki.game.player.enums.Sex;
import aiki.gui.components.fight.FrontBattle;
import aiki.gui.components.fight.FrontClickEvent;
import aiki.gui.components.walk.ScenePanel;
/*import aiki.map.pokemon.PokemonPlayer;
import aiki.network.NetAiki;
import aiki.network.SendReceiveServerAiki;
import aiki.network.sml.DocumentReaderAikiMultiUtil;
import aiki.network.sml.DocumentWriterAikiMultiUtil;*/
import code.bean.nat.FixCharacterCaseConverter;
import code.expressionlanguage.filenames.AbstractNameValidating;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.events.QuitEvent;
//import code.gui.events.QuittingEvent;
import code.gui.events.QuittingEvent;
import code.gui.files.*;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaPoint;
import code.gui.initialize.AbstractProgramInfos;
//import code.gui.initialize.AbstractSocket;
//import code.network.*;
import code.scripts.messages.gui.MessGuiPkGr;
//import code.sml.Document;
//import code.sml.Element;
import code.scripts.messages.gui.MessPkVideoGr;
import code.sml.util.ResourcesMessagesUtil;
import code.stream.*;
import code.threads.*;
//import code.util.CustList;
//import code.util.IdMap;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.*;
//import code.util.core.IndexConstants;
//import code.util.core.NumberUtil;
//import code.util.core.StringUtil;

public final class WindowAiki extends GroupFrame implements WindowAikiInt,AbsOpenQuit {
//public final class WindowAiki extends NetGroupFrame
    //implemented SettingInfosAfterCompiler

    public static final String OK = "ok";
    public static final String APPS_AIKI = "aiki";
    public static final String TEMP_FOLDER = "pokemon";
    private static final String DIALOG_ACCESS = "aiki.gui.mainwindow";

    private static final String TITLE = "title";

    private static final String CST_FILE = "file";
    private static final String FOLDER_LOAD = "folderLoad";
    private static final String ZIP_LOAD = "zipLoad";
    private static final String GAME_LOAD = "gameLoad";
    private static final String GAME_SAVE = "gameSave";
    private static final String CST_LANGUAGE = "language";
    private static final String CST_PARAMS = "params";
    private static final String DATA_GAME = "dataGame";
    private static final String NEW_GAME = "newGame";
    private static final String DATA_WEB = "dataWeb";

    private static final String SAVING = "saving";

    private static final String SAVING_TITLE = "savingTitle";

    private static final String ERROR_LOADING = "errorLoading";

    private static final String SUCCESSFUL_LOADING = "successfulLoading";

//    private static final String TOO_MANY = "tooMany";

//    private static final String NO_TRADE = "noTrade";

    private static final String LAST_SAVED_GAME = "lastSavedGame";

    private static final String OPEN_HTML = "openHtml";

    private static final String TITLE_WEB = "titleWeb";

    private static final String TITLE_BATTLE = "titleBattle";

    private static final String CST_DIFFICULTY = "difficulty";

    private static final String TITLE_DIFFICULTY = "titleDifficulty";

    private static final String CST_QUIT = "quit";

//    private static final String PARAMETRES = "parametres";

    private static final String HELP_INFO = "helpInfo";

    private static final String GAME_PROGRESS = "gameProgress";

    private static final String AVAILAIBLE_HELPS = "availableHelps";

//    private static final String EXCLUDED = "jdk";

//    private static final String LOG_FILE = LaunchingPokemon.getTempFolderSl()+"errors_compiling.txt";

//    private static final String CLASS_FILES_EXT = DataBase.CLASS_FILES_EXT;

//    private static final String LOCALE = "locale";

    //private static final boolean COMPILE = false;

    private StringMap<String> messages = new StringMap<String>();
    private final ProgressingDialog dialog = new ProgressingDialog(getFrames(),this);
    
//    private Timer timer;

//    private BasicClient threadEmission;

    private LoadingGame loadingConf = DocumentReaderAikiCoreUtil.newLoadingGame();

    private EnabledMenu file;
//
//    private AbsMenuItem zipLoad;
//
//    private AbsMenuItem folderLoad;
//
//    private AbsMenuItem gameLoad;
//
//    private AbsMenuItem gameSave;

    private EnabledMenu language;

    private EnabledMenu params;

    private EnabledMenu quit;

    private EnabledMenu dataGame;

    private final EnabledMenu dataWeb;

    private EnabledMenu dataBattle;

    private EnabledMenu newGame;

    private EnabledMenu difficulty;

    private final AbsPanel mainPanel;

    private final CustList<FrameHtmlData> htmlDialogs = new CustList<FrameHtmlData>();

//    private byte indexInGame = IndexConstants.INDEX_NOT_FOUND_ELT;

    /**Est vrai si et seulement si une partie vient d'etre sauvegardee*/
    private boolean savedGame;

    private final FacadeGame facade;

    private Sex chosenSex = Sex.NO;

    private final Clock time;

    private final AbsPlainLabel lastSavedGameDate;

    private String dateLastSaved = DataBase.EMPTY_STRING;

    private final AbsPlainLabel helpInfo;

    private final AbsPlainLabel availableHelps;

    private boolean inBattle;

    private final ScenePanel scenePanel;

//    private Battle battle;
    private FrontBattle battle;

//    private boolean enabledMove;

    private FightIntroThread fightIntroThread;

    private final VideoLoading videoLoading = new VideoLoading();
    private final AbstractAtomicBooleanCore loadFlag;
    private AbstractFutureParam<AikiNatLgNamesNavigation> preparedDataWebTask;
    private AikiNatLgNamesNavigation preparedFightTask;
    private AikiNatLgNamesNavigation preparedPkTask;
    private AikiNatLgNamesNavigation preparedPkNetTask;
    private AikiNatLgNamesNavigation preparedDiffTask;
    private AikiNatLgNamesNavigation preparedProgTask;
//    private AbstractThread preparedDataWebThread;
//    private AbstractThread preparedFightThread;
//    private AbstractThread preparedPkThread;
//    private AbstractThread preparedPkNetThread;
//    private AbstractThread preparedDiffThread;
//    private AbstractThread preparedProgThread;
//    private AbstractThread exporting;
//    private KeyPadListener keyPadListener;

//    private ForwardingJavaCompiler compiling;

    //private boolean startThread;

//    private boolean successfulCompile;

//    private final boolean standalone;

    //private final NetAiki net = new NetAiki();
    private final SelectEgg selectEgg;
    private final SelectPokemon selectPokemon;
//    private final SelectHealedMove selectHealedMove;
    private final SelectHealingItem selectHealingItem;
    private final SelectItem selectItem;
    private final SelectTm selectTm;
    private final ConsultHosts consultHosts;
    private final DialogDifficulty dialogDifficulty;
    private final DialogGameProgess dialogGameProgess;
//    private final DialogHtmlData dialogHtmlData;
    private final DialogSoftParams softParams;
//    private final DialogServerAiki dialogServer;
    private final WindowAikiCore core;
    private final AbstractBaseExecutorService expThread;
    private LanguageDialogButtons languageDialogButtons;
    private final AbstractAtomicBoolean modal;
    private final FileSaveFrame fileSaveFrame;
    private final FileOpenFrame fileOpenFrame;
    private final FileOpenFrame fileOpenRomFrame;
    private final FolderOpenFrame fileOpenFolderFrame;
    private final FileOpenSaveFrame fileOpenSaveFrame;
    private final FolderOpenSaveFrame folderOpenSaveFrame;
    private final ReportingFrame resultFile = ReportingFrame.newInstance(getFrames());
    private AbsTaskEnabled taskEnabled;
    private final DialogHeros dialogHeros = new DialogHeros(getFrames(),this);
    public WindowAiki(String _lg, AbstractProgramInfos _list, AikiFactory _fact) {
        super(_lg, _list);
        setTaskEnabled(new DefTaskEnabled());
        modal = _list.getThreadFactory().newAtomicBoolean();
        dataWeb = _fact.getGeneralHelp();
        fileSaveFrame = new FileSaveFrame(_list, modal);
        fileOpenFrame = new FileOpenFrame(_list, modal);
        fileOpenRomFrame = new FileOpenFrame(_list, modal);
        fileOpenFolderFrame = new FolderOpenFrame(_list, modal);
        fileOpenSaveFrame = new FileOpenSaveFrame(_list, modal);
        folderOpenSaveFrame = new FolderOpenSaveFrame(_list, modal);
        core = new WindowAikiCore(_fact);
        GuiBaseUtil.choose(_lg, this, _list.getCommon());
        expThread = _list.getThreadFactory().newExecutorService();
        selectEgg = new SelectEgg(_list, this);
        selectPokemon = new SelectPokemon(_list, this);
//        selectHealedMove = new SelectHealedMove(_list);
        selectHealingItem = new SelectHealingItem(_list, this);
        selectItem = new SelectItem(_list, this);
        selectTm = new SelectTm(_list, this);
        consultHosts = new ConsultHosts(_list);
        dialogDifficulty = new DialogDifficulty(_list);
        dialogGameProgess = new DialogGameProgess(_list);
//        dialogHtmlData = new DialogHtmlData(_list);
        softParams = new DialogSoftParams(_list);
//        dialogServer = new DialogServerAiki(_list);
        loadFlag = _list.getThreadFactory().newAtomicBoolean();
        setAccessFile(DIALOG_ACCESS);
        setFocusable(true);
        setFocusableWindowState(true);
        facade = new FacadeGame();
        facade.setLanguages(_list.getLanguages());
        facade.setDisplayLanguages(_list.getDisplayLanguages());
        facade.setSimplyLanguage(_lg);
        setImageIconFrame(getIcon(getImageFactory()));
        mainPanel = getCompoFactory().newPageBox();
        scenePanel = new ScenePanel(this, facade);
        initBattle();
        initMenuBar();
        MenuItemUtils.setEnabledMenu(core.getGameLoad(),false);
        MenuItemUtils.setEnabledMenu(core.getGameSave(),false);
        MenuItemUtils.setEnabledMenu(dataGame,false);
        battle.setVisibleFrontBattle(false);
        mainPanel.add(battle.getPaintableLabel());
        mainPanel.add(scenePanel.getComponent());
        time = new Clock(_list);
        mainPanel.add(time);
        lastSavedGameDate = getCompoFactory().newPlainLabel("");
        mainPanel.add(lastSavedGameDate);
        helpInfo = getCompoFactory().newPlainLabel("");
        mainPanel.add(helpInfo);
        availableHelps = getCompoFactory().newPlainLabel("");
        mainPanel.add(availableHelps);
        setContentPane(mainPanel);
        //setVisible(true);
//        exitMode(_list);
//        setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
        facade.setData(new DataBase(_list.getGenerator()));
        initMessages();
        setTitle(messages.getVal(TITLE));
    }
    public static StringMap<String> getMessagesFromLocaleClass(String _loc) {
        return getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _loc, DIALOG_ACCESS);
    }
    public static StringMap<String> getMessagesFromLocaleClass(String _folder, String _loc, String _class) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(_folder, _loc, _class);
        String loadedResourcesMessages_ = MessGuiPkGr.ms().getVal(fileName_);
        return ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
    }

    public static AbstractImage getIcon(AbstractImageFactory _fact) {
        return FileDialog.getImage(MessPkVideoGr.ms().getVal(StringUtil.concat(Resources.RESOURCES_FOLDER, StreamTextFile.SEPARATEUR, Resources.ICON_TXT)), _fact);
    }

    public static String getTempFolderSl(AbstractProgramInfos _tmpUserFolderSl) {
        return StringUtil.concat(getTempFolder(_tmpUserFolderSl), StreamTextFile.SEPARATEUR);
    }

    public static String getTempFolder(AbstractProgramInfos _tmpUserFolderSl) {
        return StreamFolderFile.getTempFolder(_tmpUserFolderSl,TEMP_FOLDER);
    }

    /**server and client
     Method allowing the client to send a serializable object by its socket
     */
    /*public void sendObjectOk() {
        trySendString(DocumentWriterAikiMultiUtil.ok(), getSocket());
    }
    public void sendObject(QuitAiki _serializable) {
        trySendString(DocumentWriterAikiMultiUtil.playerActionGameAiki(_serializable), getSocket());
    }
    public void sendObject(ReadyAiki _serializable) {
        trySendString(DocumentWriterAikiMultiUtil.playerActionBeforeGameAiki(_serializable), getSocket());
    }
    public void sendObject(SentPokemon _serializable) {
        trySendString(DocumentWriterAikiMultiUtil.sentPokemon(_serializable), getSocket());
    }*/
    @Override
    public void quit() {
        AbsButton b_ = getFrames().getButtons().getVal(APPS_AIKI);
        LanguageDialogButtons.enable(b_,false);
//        if (b_ != null) {
//            b_.setEnabled(false);
//        }
        if (loadingConf.isSaveGameAtExit()) {
//        if (loadingConf != null && loadingConf.isSaveGameAtExit()) {
            if (loadingConf.getLastSavedGame().isEmpty()) {
                String name_ = StringUtil.concat(getTempFolderSl(getFrames()),LoadingGame.DEFAULT_SAVE_GAME,Resources.GAME_EXT);
                loadingConf.setLastSavedGame(name_);
                save(name_);
            } else {
                String path_ = StringUtil.replaceBackSlash(getFileCoreStream().newFile(loadingConf.getLastSavedGame()).getAbsolutePath());
                save(path_);
            }
            core.getAikiFactory().getConfPkStream().save(StringUtil.concat(getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE), loadingConf);
        }
        LanguageDialogButtons.enable(b_,true);
//        if (b_ != null) {
//            b_.setEnabled(true);
//        }
        if (!htmlDialogs.isEmpty()) {
            htmlDialogs.get(0).closeWindow();
            dataWeb.setEnabled(true);
        }
        getConsultHosts().getAbsDialog().setVisible(false);
        battle.getBattle().closeWindow();
        scenePanel.getPkDetailContent().getContent().setVisible(false);
        dialogHeros.getFrame().setVisible(false);
        if (!battle.getHtmlDialogs().isEmpty()) {
            battle.getHtmlDialogs().get(0).closeWindow();
            dataBattle.setEnabled(inBattle);
        }
        GuiBaseUtil.trEx(this);
//        if (indexInGame != IndexConstants.INDEX_NOT_FOUND_ELT) {
//            QuitAiki quit_ = new QuitAiki();
//            quit_.setClosing(true);
//            quit_.setPlace(indexInGame);
//            quit_.setLocale(getLanguageKey());
//            sendObject(quit_);
//            return;
//        }
//        if (battle != null && isAliveThread()) {
//            return;
//        }
////        if (compiling.isAlive()) {
////            int adv_ = Constants.getPercentCompile();
////            String message_ = getCompilingString();
////            String formatted_ = MessageFormat.format(message_, adv_);
////            showErrorMessageDialog(formatted_);
////            return;
////        }
//        if (loadingConf == null) {
//            //LaunchingPokemon.decrement();
//            basicDispose();
//            return;
//        }
//        if (loadingConf.isSaveGameAtExit()) {
//            if (loadingConf.getLastSavedGame().isEmpty()) {
//                String name_ = StringUtil.concat(LaunchingPokemon.getTempFolderSl(getFrames()),LoadingGame.DEFAULT_SAVE_GAME,Resources.GAME_EXT);
//                loadingConf.setLastSavedGame(name_);
//                save(name_);
//                if (!getFileCoreStream().newFile(name_).exists()) {
//                    name_ = StringUtil.concat(StreamFolderFile.getCurrentPath(getFileCoreStream()),LoadingGame.DEFAULT_SAVE_GAME,Resources.GAME_EXT);
//                    int index_ = 0;
//                    while (getFileCoreStream().newFile(name_).exists()) {
//                        name_ = StringUtil.concat(StreamFolderFile.getCurrentPath(getFileCoreStream()),LoadingGame.DEFAULT_SAVE_GAME,Long.toString(index_),Resources.GAME_EXT);
//                        index_++;
//                    }
//                    loadingConf.setLastSavedGame(name_);
//                    save(name_);
//                }
//            } else {
//                String path_ = getFileCoreStream().newFile(loadingConf.getLastSavedGame()).getAbsolutePath();
//                path_ = StringUtil.replaceBackSlash(path_);
//                save(path_);
//            }
//            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingPokemon.getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE), DocumentWriterAikiCoreUtil.setLoadingGame(loadingConf),getStreams());
//            //LaunchingPokemon.decrement();
//            basicDispose();
//        } else if (indexInGame == IndexConstants.INDEX_NOT_FOUND_ELT && !savedGame) {
//            if (facade.getGame() == null) {
//                StreamTextFile.saveTextFile(StringUtil.concat(LaunchingPokemon.getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE), DocumentWriterAikiCoreUtil.setLoadingGame(loadingConf),getStreams());
//                //LaunchingPokemon.decrement();
//                basicDispose();
//                return;
//            }
//            int choix_=saving();
//            if(choix_!=GuiConstants.CANCEL_OPTION) {
//                if(choix_==GuiConstants.YES_OPTION) {
//                    String file_ = fileDialogSave();
//                    if (!file_.isEmpty()) {
//                        loadingConf.setLastSavedGame(file_);
//                        save(file_);
//                    }
//                }
//                savedGame = true;
//                StreamTextFile.saveTextFile(StringUtil.concat(LaunchingPokemon.getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE), DocumentWriterAikiCoreUtil.setLoadingGame(loadingConf),getStreams());
//                //LaunchingPokemon.decrement();
////                ecrireCoordonnees();
////                CustList<FrameHtmlData> frames_ = new CustList<>();
////                frames_.addAll(htmlDialogs);
////                frames_.addAll(battle.getHtmlDialogs());
////                for (FrameHtmlData f: frames_) {
////                    f.dispose();
////                }
////                clearHtmlDialogs();
////                battle.clearHtmlDialogs();
//                basicDispose();
//            }
//        } else {
//            //LaunchingPokemon.decrement();
////            ecrireCoordonnees();
////            CustList<FrameHtmlData> frames_ = new CustList<>();
////            frames_.addAll(htmlDialogs);
////            frames_.addAll(battle.getHtmlDialogs());
////            for (FrameHtmlData f: frames_) {
////                f.dispose();
////            }
////            clearHtmlDialogs();
////            battle.clearHtmlDialogs();
//            basicDispose();
////            Constants.exit();
////            if (Standalone.isStandalone()) {
////                Constants.exit();
////            }
//        }
    }
//    @Override
    public void dispose() {
        if (isPaintingScene()) {
            return;
        }
        ecrireCoordonnees();
        CustList<FrameHtmlData> frames_ = new CustList<FrameHtmlData>();
        frames_.addAllElts(htmlDialogs);
        frames_.addAllElts(battle.getHtmlDialogs());
        for (FrameHtmlData f: frames_) {
//            f.dispose();
            f.setVisible(false);
        }
        battle.getBattle().setVisible(false);
        //clearHtmlDialogs();
        //battle.clearHtmlDialogs();
        //removeAll();
        GuiBaseUtil.trEx(this);
        //facade = null;
    }
    public void initMessages() {
        facade.getData().setLanguage(facade.getLanguage());
        GamesPk.initMessages(facade.getData(),facade.getLanguage());
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, getLanguageKey(), getAccessFile());
        file.setText(messages.getVal(CST_FILE));
        core.getZipLoad().setText(messages.getVal(ZIP_LOAD));
        core.getFolderLoad().setText(messages.getVal(FOLDER_LOAD));
        core.getGameLoad().setText(messages.getVal(GAME_LOAD));
        core.getGameSave().setText(messages.getVal(GAME_SAVE));
        language.setText(messages.getVal(CST_LANGUAGE));
        params.setText(messages.getVal(CST_PARAMS));
        dataGame.setText(messages.getVal(DATA_GAME));
        quit.setText(messages.getVal(CST_QUIT));
        newGame.setText(messages.getVal(NEW_GAME));
        //dataGame.setText(messages.getVal(NEW_GAME));
        dataWeb.setText(messages.getVal(DATA_WEB));
        dataBattle.setText(messages.getVal(TITLE_BATTLE));
        difficulty.setText(messages.getVal(CST_DIFFICULTY));
//        lastSavedGameDate.setText(MessageFormat.format(messages.getVal(LAST_SAVED_GAME), dateLastSaved));
        lastSavedGameDate.setText(StringUtil.simpleStringsFormat(messages.getVal(LAST_SAVED_GAME), dateLastSaved));
        if (!helpInfo.getText().isEmpty()) {
            helpInfo.setText(messages.getVal(HELP_INFO));
        }
        if (!availableHelps.getText().isEmpty()) {
            availableHelps.setText(messages.getVal(AVAILAIBLE_HELPS));
        }
        scenePanel.initMessages(getLanguageKey());
        battle.getBattle().initMessages();
    }

    public String getOpenedHtmlString() {
        return messages.getVal(OPEN_HTML);
    }
//
//    private void addBeginGame() {
//        beginGame.removeAll();
//        labsBegin.clear();
//        AbsPanel heros_ = getCompoFactory().newLineBox();
//        for (Sex s: facade.getSexList().all()) {
//            ImageHeroKey i_;
//            i_ = new ImageHeroKey(EnvironmentType.ROAD, s);
//            int[][] imgTxt_ = facade.getData().getFrontHeros().getVal(i_);
//            HeroLabel label_ = new HeroLabel(getImageFactory(),imgTxt_, getCompoFactory());
//            label_.setPreferredSize(new MetaDimension(imgTxt_[0].length, imgTxt_.length));
//            label_.addMouseListener(new PkNonModalEvent(modal),new HeroSelect(this, s));
//            herosLabels.put(s, label_);
//            labsBegin.add(label_);
//            heros_.add(label_.getPaintableLabel());
//        }
//        beginGame.add(heros_);
//        beginGame.add(getCompoFactory().newPlainLabel(messages.getVal(CST_NICKNAME)));
//        beginGame.add(nickname);
//        confirmNewGame = getCompoFactory().newPlainButton(OK);
//        confirmNewGame.addActionListener(new PkNonModalEvent(modal),new ConfirmNewGameEvent(this));
//        beginGame.add(confirmNewGame);
//        AbsMetaLabelPk.repaintChildren(labsBegin,getImageFactory());
//        scenePanel.addBeginGame(beginGame);
//    }

    public void confirmNewGame(String _txt) {
        getDialogHeros().getFrame().setVisible(false);
        newGame(_txt);
        pack();
    }

    public void clearHtmlDialogs() {
        htmlDialogs.clear();
    }

//    public void changeSex(Sex _sex) {
//        chosenSex = _sex;
//        chosenSexAct = true;
//        herosLabels.getVal(_sex.getOppositeSex()).setSelected(false);
//        herosLabels.getVal(_sex).setSelected(true);
//        AbsMetaLabelPk.repaintChildren(labsBegin,getImageFactory());
//    }

    private void newGame(String _txt) {
//        if (!chosenSexAct) {
//            return;
//        }
        getModal().set(false);
        facade.newGame(_txt, chosenSex);
        drawGame();
        savedGame = false;
        MenuItemUtils.setEnabledMenu(core.getGameSave(),true);
        loadingConf.setLastSavedGame(DataBase.EMPTY_STRING);
    }

    /**thread safe method*/
    public void loadOnlyRom(String _file, AbstractAtomicIntegerCoreAdd _p) {
        if (!_file.isEmpty()) {
            //startThread = true;
            AbstractAtomicBooleanCore loaded_ = core.getAikiFactory().getDataBaseStream().loadRomAndCheck(getFrames(), core.getAikiFactory().getTaskLoad(), facade, _file, _p, loadFlag);
//            StringMap<String> files_ = StreamFolderFile.getFiles(_file,getFileCoreStream(),getStreams());
//            GamesPk.loadRomAndCheck(getGenerator(),facade,_file, files_,_p,loadFlag);
//            if (!facade.isLoadedData()) {
//                FacadeGame.postLoad(facade,core.getAikiFactory().getTaskLoad().attendreResultat());
//                _p.set(100);
//                loadFlag.set(true);
//            }
//            if (!loadFlag.get()) {
//                return;
//            }
            if (!loaded_.get()) {
                return;
            }
        } else {
            FacadeGame.postLoad(facade,core.getAikiFactory().getTaskLoad().attendreResultat());
            _p.set(100);
            loadFlag.set(true);
        }
        facade.initializePaginatorTranslations();
        getFrames().getCompoFactory().invokeNow(new AfterLoadZip(this));
    }

    /**thread safe method*/
    public void loadRomGame(LoadingGame _configuration, String _path, StringList _files, boolean _param, AbstractAtomicIntegerCoreAdd _p) {
        String path_;
        if (!_configuration.getLastRom().isEmpty()) {
            String lastRom_ = StringUtil.replaceBackSlash(_configuration.getLastRom());
            AbstractFile file_ = getFileCoreStream().newFile(lastRom_);
            if (!StreamFolderFile.isAbsolute(lastRom_,getFileCoreStream())) {
                path_ = StringUtil.concat(_path,_configuration.getLastRom());
            } else {
                path_ = file_.getAbsolutePath();
            }
            path_ = StringUtil.replaceBackSlash(path_);

            AbstractAtomicBooleanCore loaded_ = core.getAikiFactory().getDataBaseStream().loadRomAndCheck(getFrames(), core.getAikiFactory().getTaskLoad(), facade, path_, _p, loadFlag);
            if (!loaded_.get()) {
                return;
            }
//            StringMap<String> files_ = StreamFolderFile.getFiles(path_,getFileCoreStream(),getStreams());
//            GamesPk.loadRomAndCheck(getGenerator(),facade,path_, files_,_p,loadFlag);
//            if (!facade.isLoadedData()) {
//                FacadeGame.postLoad(facade,core.getAikiFactory().getTaskLoad().attendreResultat());
//                _p.set(100);
//                loadFlag.set(true);
//            }
//            if (!loadFlag.get()) {
//                return;
//            }
        } else {
            FacadeGame.postLoad(facade,core.getAikiFactory().getTaskLoad().attendreResultat());
            _p.set(100);
            loadFlag.set(true);
        }
        facade.initializePaginatorTranslations();
        getFrames().getCompoFactory().invokeNow(new AfterLoadZip(this));
        Game g_ = null;
        if (!_files.isEmpty()){
            g_ = core.getAikiFactory().getGamePkStream().loadThen(_files.first(),facade.getSexList());
        }
        if (g_ != null) {
            if (!facade.checkAndSetGame(g_)) {
                loadFlag.set(false);
                if (_param) {
                    setLoadingConf(_configuration, false);
                }
                return;
            }
        } else {
            String lastSave_ = StringUtil.replaceBackSlash(_configuration.getLastSavedGame());
            AbstractFile file_ = getFileCoreStream().newFile(lastSave_);
            if (!StreamFolderFile.isAbsolute(lastSave_,getFileCoreStream())) {
                path_ = StringUtil.concat(_path,_configuration.getLastSavedGame());
            } else {
                path_ = file_.getAbsolutePath();
            }
            path_ = StringUtil.replaceBackSlash(path_);
            DataBase db_ = facade.getData();
            Game game_ = DefGamePkStream.checkGame(db_,facade.getSexList(), core.getAikiFactory().getGamePkStream().load(path_, facade.getSexList()));
            if (game_ == null) {
                loadFlag.set(false);
                if (_param) {
                    setLoadingConf(_configuration, false);
                }
                return;
            }
            facade.load(game_);
        }
        facade.changeCamera();
        getFrames().getCompoFactory().invokeNow(new AfterLoadGame(this));
    }

    public void afterLoadZip() {
        MenuItemUtils.setEnabledMenu(dataGame,true);
        MenuItemUtils.setEnabledMenu(core.getGameLoad(),true);
        MenuItemUtils.setEnabledMenu(core.getGameSave(),false);
//        if (exporting != null && exporting.isAlive()) {
//            return;
//        }
//        if (loadingConf == null) {
//            return;
//        }
        AbstractNameValidating def_ = getFrames().getValidator();
        if (!def_.okPath(StreamFolderFile.getRelativeRootPath(loadingConf.getExport(), getFileCoreStream()),'/','\\')) {
            loadingConf.setExport("");
        }
        expThread.submitLater(new ExportRomThread(facade,loadingConf, core.getAikiFactory().getDataBaseStream(), getFrames()));
//        exporting = getThreadFactory().newThread(new ExportRomThread(facade,loadingConf,getThreadFactory(), getFileCoreStream(),getStreams()));
//        exporting.start();
    }

    public void afterLoadGame() {
        MenuItemUtils.setEnabledMenu(core.getGameSave(),true);
        drawGame();
        savedGame = true;
    }

    private void drawGame() {
        scenePanel.setMessages();
        if (facade.isChangeToFightScene()) {
            if (battle != null) {
                battle.setPaintBallMove(false);
            }
            setFight(false, false);
            return;
        }
        drawGameWalking(true);
        pack();
    }

    public void drawGameWalking(boolean _setPreferredSize) {
//        if (battle != null) {
//            mainPanel.remove(battle);
//        }
        MenuItemUtils.setEnabledMenu(difficulty,true);
        battle.setVisibleFrontBattle(false);
        scenePanel.getComponent().setVisible(true);
        inBattle = false;
        MenuItemUtils.setEnabledMenu(dataBattle,false);
        scenePanel.drawGameWalking(_setPreferredSize);
    }

//    public void addToArea() {
//        String comment_ = getComment();
//        scenePanel.setTextArea(comment_);
//    }

    public String getComment() {
        return scenePanel.getComment();
    }

//    public void setSceneKepPad(Scene _scene) {
//        keyPadListener.setSceneKepPad(_scene);
//    }

    private void initMenuBar() {
        AbsMenuBar bar_ = getCompoFactory().newMenuBar();
        file = getCompoFactory().newMenu();
        core.fileMenu(file,this,this, new PkNonModalEvent(modal));
//        zipLoad = getCompoFactory().newMenuItem();
//        zipLoad.addActionListener(new LoadZipEvent(this,false));
//        zipLoad.setAccelerator(GuiConstants.VK_M, GuiConstants.CTRL_DOWN_MASK);
//        file.addMenuItem(zipLoad);
//        folderLoad = getCompoFactory().newMenuItem();
//        folderLoad.addActionListener(new LoadZipEvent(this,true));
//        folderLoad.setAccelerator(GuiConstants.VK_D, GuiConstants.CTRL_DOWN_MASK);
//        file.addMenuItem(folderLoad);
//        gameLoad = getCompoFactory().newMenuItem();
//        gameLoad.addActionListener(new LoadGameEventAiki(this));
//        gameLoad.setAccelerator(GuiConstants.VK_O, GuiConstants.CTRL_DOWN_MASK);
//        file.addMenuItem(gameLoad);
//        gameSave = getCompoFactory().newMenuItem();
//        gameSave.addActionListener(new SaveGameEventAiki(this));
//        gameSave.setAccelerator(GuiConstants.VK_S, GuiConstants.CTRL_DOWN_MASK);
//        file.addMenuItem(gameSave);
        file.addMenuItem(getCompoFactory().newSep());
        language = getCompoFactory().newMenuItem();
        languageDialogButtons = new LanguageDialogButtons(getFrames(),language);
        languageDialogButtons.commonParametersMenu(file,new ManageLanguageEventAiki(this),GuiConstants.VK_F6,0);
//        language.addActionListener(new ManageLanguageEventAiki(this));
//        if (Standalone.isStandalone()) {
//            file.add(language);
//        }
//        file.addMenuItem(language);
        params = getCompoFactory().newMenuItem();
        params.setAccelerator(GuiConstants.VK_L, GuiConstants.CTRL_DOWN_MASK);
        params.addActionListener(new PkNonModalEvent(modal),new ManageParamsEvent(this));
        file.addMenuItem(params);
        file.addMenuItem(getCompoFactory().newSep());
        quit = getCompoFactory().newMenuItem();
        quit.setAccelerator(GuiConstants.VK_ESCAPE,0);
        quit.addActionListener(new QuitEvent(this));
        file.addMenuItem(quit);
        bar_.add(file);
        dataGame = getCompoFactory().newMenu();
//        dataGame = new JMenuItem();
//        dataGame.addMouseListener(new MouseAdapter() {
//
//            @Override
//            public void mouseReleased(MouseEvent _e) {
////                if (!newGame.isEnabled()) {
////                    return;
////                }
//                if (!dataGame.isEnabled()) {
//                    return;
//                }
//                if (battle != null) {
//                    while (isAliveThread()) {
//                        continue;
//                    }
//                }
//                if (!Numbers.eq(indexInGame, CustList.INDEX_NOT_FOUND_ELT)) {
//                    return;
//                }
//                addBeginGame();
//                pack();
//            }
//        });
//        dataWeb = getCompoFactory().newMenuItem();
        dataWeb.setAccelerator(GuiConstants.VK_F1,0);
        dataWeb.addActionListener(new PkNonModalEvent(modal),new ShowDataWebEvent(this));
        dataGame.addMenuItem(dataWeb);
        dataBattle = getCompoFactory().newMenuItem();
        MenuItemUtils.setEnabledMenu(dataBattle,false);
        dataBattle.setAccelerator(GuiConstants.VK_F2,0);
        dataBattle.addActionListener(new PkNonModalEvent(modal),new ShowDataFightEvent(this));
        dataGame.addMenuItem(dataBattle);
        newGame = getCompoFactory().newMenuItem();
        newGame.addActionListener(new PkNonModalEvent(modal),new ProponeNewGameEvent(this));
        newGame.setAccelerator(GuiConstants.VK_N, GuiConstants.CTRL_DOWN_MASK);
        dataGame.addMenuItem(newGame);
        difficulty = getCompoFactory().newMenuItem();
        MenuItemUtils.setEnabledMenu(difficulty,false);
        difficulty.addActionListener(new PkNonModalEvent(modal),new ManageDifficultyEvent(this));
        difficulty.setAccelerator(GuiConstants.VK_F3,0);
        dataGame.addMenuItem(difficulty);
        bar_.add(dataGame);
        setJMenuBar(bar_);
    }

    public void loadZip(boolean _folder) {
        if (openedHmlFrames()) {
            //error message if data html
            String message_ = getOpenedHtmlString();
            showErrorMessageDialog(message_);
            return;
        }
//        if (showErrorMessageDialog(ForwardingJavaCompiler.getMess(Constants.getLanguage()))) {
//            return;
//        }
//        if (!zipLoad.isEnabled()) {
//            return;
//        }
//        if (!NumberUtil.eq(indexInGame, IndexConstants.INDEX_NOT_FOUND_ELT)) {
//            return;
//        }
        if (!savedGame && facade.getGame() != null) {
            if (_folder) {
                FolderOpenSaveFrame.setFileSaveDialogByFrame(false,folderSave(),getFolderOpenSaveFrame(),new PkSaveFile(this),new PkContinueRomFile(this));
            } else {
                FileOpenSaveFrame.setFileSaveDialogByFrame(true, folderSave(), fileDialogLoadZip(), getFileOpenSaveFrame(), new PkSaveFile(this), new PkContinueRomFile(this));
            }
            return;
//            int choix_=saving();
//            if(choix_==GuiConstants.CANCEL_OPTION) {
//                return;
//            }
//            loadingConf.setLastSavedGame(DataBase.EMPTY_STRING);
//            if(choix_==GuiConstants.YES_OPTION) {
//                String file_ = fileDialogSave();
//                if (!file_.isEmpty()) {
//                    exportSaveFile(file_);
//                }
//            }
//            updateConf();
        }
        loadPhase(_folder);
    }

    public void loadPhase(boolean _folder) {
//        String fileName_;
        if (_folder) {
            FolderOpenFrame.setFolderOpenDialog(false,getFileOpenFolderFrame(),new DefButtonsOpenFolderPanelAct(new PkContinueRomFile(this)));
//            fileName_ = StringUtil.nullToEmpty(getFolderOpenDialogInt().input(getCommonFrame(), false));
        } else {
            FileOpenFrame.setFileSaveDialogByFrame(true, fileDialogLoadZip(), getFileOpenRomFrame(), new DefButtonsOpenPanelAct(new PkContinueRomFile(this)));
//            fileName_ = fileDialogLoad(Resources.ZIPPED_DATA_EXT, true);
        }
//        if (fileName_.isEmpty()) {
//            return;
//        }
//        loadRom(fileName_);
    }

    public void loadRom(String _fileName) {
        AbstractAtomicIntegerCoreAdd p_ = getThreadFactory().newAtomicInteger();
        loadFlag.set(true);
        LoadingThread load_ = new LoadingThread(this, _fileName,p_);
        getThreadFactory().newStartedThread(load_);
    }

    public void updateConf() {
        core.getAikiFactory().getConfPkStream().save(StringUtil.concat(getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE), loadingConf);
    }

    public void exportSaveFile(String _file) {
        save(_file);
        afterSave();
    }

    public void afterSave() {
        dateLastSaved = Clock.getDateTimeText(getThreadFactory());
        lastSavedGameDate.setText(StringUtil.simpleStringsFormat(messages.getVal(LAST_SAVED_GAME), dateLastSaved));
        savedGame = true;
    }

    public void loadGame() {
//        if (!NumberUtil.eq(indexInGame, IndexConstants.INDEX_NOT_FOUND_ELT)) {
//            return;
//        }
        if (!savedGame && facade.getGame() != null) {
            FileOpenSaveFrame.setFileSaveDialogByFrame(true, folderSave(), fileDialogLoadZip(), getFileOpenSaveFrame(), new PkSaveFile(this), new PkContinueRomFile(this));
            return;
//            int choix_=saving();
//            if(choix_==GuiConstants.CANCEL_OPTION) {
//                return;
//            }
//            loadingConf.setLastSavedGame(DataBase.EMPTY_STRING);
//            if(choix_==GuiConstants.YES_OPTION) {
//                String file_ = fileDialogSave();
//                if (!file_.isEmpty()) {
//                    loadingConf.setLastSavedGame(file_);
//                    save(file_);
//                    dateLastSaved = Clock.getDateTimeText(getThreadFactory());
////                    lastSavedGameDate.setText(MessageFormat.format(messages.getVal(LAST_SAVED_GAME), dateLastSaved));
//                    lastSavedGameDate.setText(StringUtil.simpleStringsFormat(messages.getVal(LAST_SAVED_GAME), dateLastSaved));
//                    savedGame = true;
//                }
//            }
//            core.getAikiFactory().getConfPkStream().save(StringUtil.concat(getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE), loadingConf);
        }
        FileOpenFrame.setFileSaveDialogByFrame(true, fileDialogLoadZip(), getFileOpenRomFrame(), new DefButtonsOpenPanelAct(new PkContinueGameFile(this)));
//        String fileName_ = fileDialogLoad(Resources.GAME_EXT, false);
//        if (fileName_.isEmpty()) {
//            return;
//        }
//        loadGame(fileName_);
    }

    public void loadGame(String _fileName) {
        boolean error_ = false;
        DataBase db_ = facade.getData();
        Game game_ = DefGamePkStream.checkGame(db_,facade.getSexList(), core.getAikiFactory().getGamePkStream().load(_fileName, facade.getSexList()));
        if (game_ != null) {
            facade.load(game_);
            MenuItemUtils.setEnabledMenu(core.getGameSave(),true);
            facade.changeCamera();
            drawGame();
            savedGame = true;
            if (battle != null) {
                battle.resetWindows();
            }
        } else {
            error_ = true;
        }
        if (error_) {
            showErrorMessageDialog(_fileName);
        }
    }

    public void saveGame() {
        FileSaveFrame.setFileSaveDialogByFrame(true,folderSave(),getFileSaveFrame(),new DefButtonsSavePanelAct(new PkSaveSimpleFile(this),new PkContinueFile(this)));
//        String fileName_ = fileDialogSave();
//        if (fileName_.isEmpty()) {
//            return;
//        }
//        save(fileName_);
//        fileName_ = StringUtil.replaceBackSlash(fileName_);
//        loadingConf.setLastSavedGame(fileName_);
//        dateLastSaved = Clock.getDateTimeText(getThreadFactory());
//        lastSavedGameDate.setText(StringUtil.simpleStringsFormat(messages.getVal(LAST_SAVED_GAME), dateLastSaved));
//        savedGame = true;
    }

    //Save option
    public void save(String _fileName) {
        loadingConf.setLastSavedGame(_fileName);
        Game game_ = facade.getGame();
        if (game_ == null) {
            return;
        }
        game_.setZippedRom(facade.getZipName());
        core.getAikiFactory().getGamePkStream().save(_fileName,game_);
    }

    public void manageLanguage() {
//        if (!canChangeLanguageAll()) {
//            GuiBaseUtil.showDialogError(GuiConstants.ERROR_MESSAGE, this.getCommonFrame());
//            return;
//        }
        languageDialogButtons.init(getCommonFrame(), getFrames(),messages.getVal(CST_LANGUAGE),this);
//        String langue_ = GuiBaseUtil.getStaticLanguage(getLanguageDialog());
//        AbstractProgramInfos infos_ = getFrames();
//        String value_ = StringUtil.nullToEmpty(langue_);
//        GuiBaseUtil.changeStaticLanguage(value_, infos_, infos_.getCommon());
//        StreamLanguageUtil.saveLanguage(getTempFolder(getFrames()), value_,infos_.getStreams());
    }

    public void manageParams() {
        DialogSoftParams.setSoftParams(this, loadingConf);
    }

    public void afterClickParam() {
        DialogSoftParams.setParams(loadingConf, getSoftParams());
        if (DialogSoftParams.isOk(getSoftParams())) {
            core.getAikiFactory().getConfPkStream().save(StringUtil.concat(getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE), loadingConf);
        }
    }

    public void proponeNewGame() {
//        if (!NumberUtil.eq(indexInGame, IndexConstants.INDEX_NOT_FOUND_ELT)) {
//            return;
//        }
        dialogHeros.display(this);
//        addBeginGame();
//        pack();
    }

    public void manageDifficulty() {
//        if (preparedDiffThread == null || preparedDiffThread.isAlive() || preparedDiffTask == null) {
//            return;
//        }
//        if (showErrorMessageDialog(ForwardingJavaCompiler.getMess(Constants.getLanguage()))) {
//            return;
//        }
        DialogDifficulty.setDialogDifficulty(this, messages.getVal(TITLE_DIFFICULTY), facade, preparedDiffTask);
    }

//    @Override
//    public boolean canChangeLanguage() {
//        if (!inBattle) {
//            if (isPaintingScene()) {
//                return false;
//            }
//            return scenePanel.isMenusVisible();
//        } else {
//            if (battle != null) {
//                if (isAliveThread()) {
//                    return false;
//                }
//                return battle.isEnabledChangeLanguage();
//            }
//        }
//        return true;
//    }

    @Override
    public void changeLanguage(String _language) {
        AbstractProgramInfos infos_ = getFrames();
        String value_ = StringUtil.nullToEmpty(_language);
        getFrames().getFrames().first().setMessages(GuiBaseUtil.group(_language, infos_.getCommon()));
        StreamLanguageUtil.saveLanguage(WindowAiki.getTempFolder(getFrames()), value_,infos_.getStreams());
        setLanguageKey(_language);
        facade.setLanguage(_language);
        initMessages();
        battle.setMessages();
        scenePanel.setMessages();
//        if (inBattle) {
//            battle.setMessages();
//        } else {
//            scenePanel.setMessages();
//        }
        pack();
        for (FrameHtmlData f: htmlDialogs) {
            f.setTitle(messages.getVal(TITLE_WEB));
            if (!f.getCommonFrame().isVisible()) {
                continue;
            }
            f.refresh(this);
            f.pack();
        }
        if (battle != null) {
            battle.refreshSession();
        }
    }

    //EDT (mouse event, key event, ...) can not happen at the same time
    public void showDataWeb() {
//        if (preparedDataWebThread == null || preparedDataWebThread.isAlive() || preparedDataWebTask == null) {
//            return;
//        }
//        if (showErrorMessageDialog(ForwardingJavaCompiler.getMess(Constants.getLanguage()))) {
//            return;
//        }
        if (!htmlDialogs.isEmpty()) {
            if (htmlDialogs.first().getSession().isProcessing()) {
                return;
            }
            reinitWebData();
            htmlDialogs.first().setVisible(true);
            return;
        }

        //JTextArea area_ = new JTextArea();
        RenderedPage session_;
        session_ = new RenderedPage(getCompoFactory().newAbsScrollPane(), getFrames(),new FixCharacterCaseConverter());
        session_.setProcess(videoLoading.getVideo(getGenerator(),getFileCoreStream(),getFrames()));
        FrameHtmlData dialog_ = new FrameHtmlData(this, messages.getVal(TITLE_WEB), session_, dataWeb);
//        dialog_.initSession(facade.getData().getWebFiles(), successfulCompile, Resources.CONFIG_DATA, Resources.ACCESS_TO_DEFAULT_DATA);
        dialog_.initSessionLg(facade,preparedDataWebTask,facade.getLanguage());
        htmlDialogs.add(dialog_);
    }

    public EnabledMenu getDataWeb() {
        return dataWeb;
    }

    public void showGameProgressing() {
//        if (preparedProgThread == null || preparedProgThread.isAlive() || preparedProgTask == null) {
//            return;
//        }
//        if (showErrorMessageDialog(ForwardingJavaCompiler.getMess(Constants.getLanguage()))) {
//            return;
//        }
        DialogGameProgess.setGameProgress(this, messages.getVal(GAME_PROGRESS), facade,preparedProgTask);
    }

    private void reinitWebData() {
        htmlDialogs.first().setTitle(messages.getVal(TITLE_WEB));
//        htmlDialogs.first().getSession().setFiles(facade.getData().getWebFiles(), Resources.ACCESS_TO_DEFAULT_FILES);
//        htmlDialogs.first().getSession().setFiles(Resources.ACCESS_TO_DEFAULT_FILES);
//        htmlDialogs.first().getSession().setDataBase(facade.getData());
//        htmlDialogs.first().getSession().initializeOnlyConf(Resources.ACCESS_TO_DEFAULT_DATA, new PokemonStandards());
        htmlDialogs.first().initSessionLg(facade,preparedDataWebTask,facade.getLanguage());
        htmlDialogs.first().pack();
    }

    private boolean openedHmlFrames() {
        if (battle != null) {
            if (battle.openedHtmlFrames()) {
                return true;
            }
        }
        if (htmlDialogs.isEmpty()) {
            return false;
        }
        return htmlDialogs.first().getCommonFrame().isVisible();
    }
    private void ecrireCoordonnees() {
        MetaPoint point_=getLocation();
        FileDialog.saveCoords(getTempFolder(getFrames()),Resources.COORDS, point_.getXcoord(),point_.getYcoord(),getStreams());
    }

    public void processLoad(String _fileName, AbstractAtomicIntegerCoreAdd _p) {
        AbstractAtomicBooleanCore loaded_ = core.getAikiFactory().getDataBaseStream().loadRomAndCheck(getFrames(), core.getAikiFactory().getTaskLoad(), facade, _fileName, _p, loadFlag);
        if (!loaded_.get()) {
            return;
        }
//        StringMap<String> files_ = StreamFolderFile.getFiles(_fileName,getFileCoreStream(),getStreams());
//        GamesPk.loadRomAndCheck(getGenerator(),facade,_fileName, files_,_p,loadFlag);
//        if (!facade.isLoadedData()) {
//            FacadeGame.postLoad(facade,core.getAikiFactory().getTaskLoad().attendreResultat());
//            loadFlag.set(true);
//            _p.set(100);
//        }
//        if (!loadFlag.get()) {
//            return;
//        }
        facade.clearGame();
        facade.initializePaginatorTranslations();
        inBattle = false;
        getFrames().getCompoFactory().invokeNow(new ReinitComponents(this));
//        battle.setVisible(false);
//        scenePanel.reinit();
//        String ext_ = StringList.escape(CLASS_FILES_EXT)+StringList.END_REG_EXP;
//        compiling = new ForwardingJavaCompiler(this, facade.getData().getJavaBeans(), StreamZipFile.getFilesInJar().filter(ext_));
//        compiling = new ForwardingJavaCompiler(this, facade.getData().getJavaBeans(), StreamZipFile.getFilesInJar().filterEndsWith(CLASS_FILES_EXT));
//        ForwardingJavaCompiler.initialize(this, facade.isCompileFiles());
//        for (EntryCust<String, String> e: facade.getData().getJavaBeans().entryList()) {
//            ForwardingJavaCompiler.addSourceCode(e.getKey(), e.getValue());
//        }
//        ThreadInvoker.invokeNow(new AfterCompiling(this, false, false));
        getFrames().getCompoFactory().invokeNow(new AfterLoadZip(this));
        loadingConf.setLastRom(_fileName);
//        pack();
//        //reInitAllSession
//        for (FrameHtmlData f: htmlDialogs) {
//            f.reInitAllSession(Resources.CONFIG_DATA, facade.getLanguage(), facade.getData(), facade.getData().getWebFiles());
//            f.pack();
//        }
//        if (battle != null) {
//            battle.closeWindows();
//        }
    }

    public void reinitComponents() {
        battle.setVisibleFrontBattle(false);
        scenePanel.reinit();
    }

    public void afterLoading() {
        pack();
//        for (FrameHtmlData f: htmlDialogs) {
//            f.reInitAllSession(Resources.CONFIG_DATA, facade.getLanguage(), facade.getData(), facade.getData().getWebFiles());
//            f.pack();
//        }
        if (battle != null) {
            battle.closeWindows();
        }
    }

    public void exitFromTrading() {
        setSavedGame(false);
        facade.closeTrading();
        scenePanel.exitInteraction();
        en(true);
    }

//    public boolean loop(Object _readObject) {
//        if (_readObject instanceof IndexOfArriving) {
//            return true;
//        }
//        if (_readObject instanceof InitTrading) {
//            if (indexInGame == CustList.FIRST_INDEX) {
//                return true;
//            }
//            if (indexInGame == CustList.SECOND_INDEX) {
//                return true;
//            }
//            return false;
//        }
//        if (_readObject instanceof NetPokemon) {
//            return true;
//        }
//        if (_readObject instanceof PokemonPlayer) {
//            return true;
//        }
//        if (_readObject instanceof Ok) {
//            return true;
//        }
//        return false;
//    }

    /*@Override
    public AbstractSocket initIndexInGame(boolean _first, AbstractSocket _socket) {
        if (_first) {
            setIndexInGame(IndexConstants.FIRST_INDEX);
        }
        return _socket;
    }

    @Override
    public void gearClient(AbstractSocket _newSocket) {
        getSockets().getSockets().put(getSockets().getSockets().size(), _newSocket);
        SendReceiveServerAiki sendReceiveServer_=new SendReceiveServerAiki(_newSocket,this, getNet());
        getThreadFactory().newStartedThread(sendReceiveServer_);
        getSockets().getConnectionsServer().put(getSockets().getSockets().size()-1,sendReceiveServer_);
        IndexOfArrivingAiki index_ = new IndexOfArrivingAiki();
        index_.setIndex(getSockets().getSockets().size()-1);
        getSockets().getReadyPlayers().put(getSockets().getSockets().size()-1, BoolVal.FALSE);
        getSockets().getPlacesPlayers().put(getSockets().getSockets().size()-1,(byte)(getSockets().getSockets().size()-1));
        NetAiki.sendObject(_newSocket,index_);
    }
    @Override
    public void loop(Document _readObject, AbstractSocket _socket) {
        Element elt_ = _readObject.getDocumentElement();
        PlayerActionBeforeGameAiki playerActionBeforeGame_ = DocumentReaderAikiMultiUtil.getPlayerActionBeforeGame(elt_);
        if (playerActionBeforeGame_ instanceof IndexOfArrivingAiki) {
            if (!StringUtil.quickEq(((IndexOfArrivingAiki) playerActionBeforeGame_).getServerName(), NetAiki.getPokemon())) {
                NewPlayerAiki p_ = new NewPlayerAiki();
                p_.setAcceptable(false);
                p_.setArriving(true);
                p_.setIndex(indexInGame);
                p_.setLanguage(getLanguageKey());
                p_.setPseudo(facade.getGame().getPlayer().getNickname());
                NetAiki.sendObject(_socket,p_);
                return;
            }
            NewPlayerAiki p_ = new NewPlayerAiki();
            p_.setAcceptable(true);
            p_.setArriving(true);
            p_.setIndex(indexInGame);
            //p_.setPseudo(pseudo());
            p_.setLanguage(getLanguageKey());
            p_.setPseudo(facade.getGame().getPlayer().getNickname());
            if (indexInGame == IndexConstants.FIRST_INDEX) {
                scenePanel.setNetworkPanel();
            }
            pack();
            NetAiki.sendObject(_socket,p_);
            return;
        }
        String tagName_ = DocumentReaderAikiMultiUtil.tagName(elt_);
        if (StringUtil.quickEq(DocumentReaderAikiMultiUtil.TYPE_INIT_TRADING,tagName_)) {
            if (indexInGame == IndexConstants.FIRST_INDEX) {
                facade.initTrading();
                CheckCompatibility ch_ = new CheckCompatibility();
                ch_.setData(facade.getExchangeData());
                ch_.setIndex(indexInGame);
                ch_.setTeam(facade.getGame().getPlayer().getTeam());
                NetAiki.sendObject(_socket,ch_);
                return;
            }
            if (indexInGame == IndexConstants.SECOND_INDEX) {
                facade.initTrading();
                CheckCompatibility ch_ = new CheckCompatibility();
                ch_.setData(facade.getExchangeData());
                ch_.setIndex(indexInGame);
                ch_.setTeam(facade.getGame().getPlayer().getTeam());
                NetAiki.sendObject(_socket,ch_);
                return;
            }
            return;
        }
        if (StringUtil.quickEq(DocumentReaderAikiMultiUtil.TYPE_OK,tagName_)) {
            facade.applyTrading();
            ByteTreeMap< PokemonPlayer> tree_ = facade.getExchangeData().getTeam(facade.getGame().getPlayer().getTeam());
            scenePanel.setTradableAfterTrading(tree_);
            pack();
        }
        if (StringUtil.quickEq(DocumentReaderAikiMultiUtil.TYPE_NET_POKEMON,tagName_)) {
            NetPokemon net_ = DocumentReaderAikiMultiUtil.getNetPokemon(elt_);
            if (indexInGame == IndexConstants.SECOND_INDEX) {
                scenePanel.setNetworkPanel();
            }
            scenePanel.setTradable(net_.getTradablePokemon());
            pack();
            return;
        }
        if (StringUtil.quickEq(DocumentReaderAikiMultiUtil.TYPE_POKEMON_PLAYER,tagName_)) {
            PokemonPlayer pk_ = DocumentReaderAikiCoreUtil.getPokemonPlayer(elt_);
            facade.receivePokemonPlayer(pk_);
            scenePanel.seeNetPokemonDetail();
        }
    }

    @Override
    public void quitNetwork(Exiting _exit, AbstractSocket _socket) {
        exitFromTrading();
        resetIndexInGame();
        closeConnexion(_exit,_socket);
        if (_exit != null && _exit.isClosing()) {
            basicDispose();
            return;
        }
        pack();
        if (_exit != null && _exit.isForced() && !_exit.isBusy()) {
            if (_exit.isTooManyPlayers()) {
                getFrames().getMessageDialogAbs().input(getCommonFrame(), getTooManyString(), getTooManyString(), getLanguageKey(), GuiConstants.ERROR_MESSAGE);
                //JOptionPane.showMessageDialog(window, MainWindow.getTooManyString(), MainWindow.getTooManyString(), JOptionPane.INFORMATION_MESSAGE);
            } else {
                getFrames().getMessageDialogAbs().input(getCommonFrame(), getNoTradeString(), getNoTradeString(), getLanguageKey(), GuiConstants.ERROR_MESSAGE);
                //JOptionPane.showMessageDialog(window, MainWindow.getNoTradeString(), MainWindow.getNoTradeString(), JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }*/

//    private int saving() {
//        //warning message
//        return confirm(messages.getVal(SAVING),messages.getVal(SAVING_TITLE));
//    }

//    private int confirm(String _message,String _titre) {
//        //warning message
//        return getConfirmDialogAns().input(getCommonFrame(),_message,_titre, GuiConstants.YES_NO_CANCEL_OPTION);
//    }
//
//    /**Sauvegarder une partie dans un fichier*/
//    private String fileDialogSave() {
//        String path_;
//        boolean saveConfig_ = false;
//        if (loadingConf.isSaveHomeFolder()) {
//            saveConfig_ = true;
//            path_=StringUtil.nullToEmpty(getFileSaveDialogInt().input(getCommonFrame(), true, Resources.GAME_EXT, getFrames().getHomePath()));
//        } else {
//            path_=StringUtil.nullToEmpty(getFileSaveDialogInt().input(getCommonFrame(), true, Resources.GAME_EXT, DataBase.EMPTY_STRING));
//        }
//        if (saveConfig_) {
//            loadingConf.setLastSavedGame(path_);
//            loadingConf.setLastRom(facade.getZipName());
//            String configPath_ = StringUtil.replaceExtension(path_, Resources.GAME_EXT, Resources.CONF_EXT);
//            //String configPath_ = path_.replaceAll(StringList.quote(Resources.GAME_EXT)+StringList.END_REG_EXP, Resources.CONF_EXT);
//            core.getAikiFactory().getConfPkStream().save(configPath_, loadingConf);
//            //configPath_ +=
//        }
//        return path_;
//    }
    public String folderSave() {
        if (loadingConf.isSaveHomeFolder()) {
            return getFrames().getHomePath();
        }
        return DataBase.EMPTY_STRING;
    }
//
//    /**Sauvegarder une partie dans un fichier*/
//    private String fileDialogLoad(String _ext, boolean _zipFile) {
//        String path_;
//        if (_zipFile) {
//            if (loadingConf != null && loadingConf.isLoadHomeFolder()) {
//                path_=getFileOpenDialogInt().input(getCommonFrame(), true, _ext, getFrames().getHomePath());
//            } else {
//                path_=getFileOpenDialogInt().input(getCommonFrame(), true, _ext, StreamFolderFile.getCurrentPath(getFileCoreStream()));
//            }
////            FileOpenDialog.setFileOpenDialog(this,Constants.getLanguage(),true, _ext, SoftApplication.getFolderJarPath(), Resources.EXCLUDED);
//        } else {
//            if (loadingConf.isSaveHomeFolder()) {
//                path_=getFileOpenDialogInt().input(getCommonFrame(), true, _ext, getFrames().getHomePath());
//            } else {
//                path_=getFileOpenDialogInt().input(getCommonFrame(), true, _ext, DataBase.EMPTY_STRING);
//            }
//        }
//        return StringUtil.nullToEmpty(path_);
//    }
    private String fileDialogLoadZip() {
        if (loadingConf.isLoadHomeFolder()) {
//        if (loadingConf != null && loadingConf.isLoadHomeFolder()) {
            return getFrames().getHomePath();
        } else {
            return StreamFolderFile.getCurrentPath(getFileCoreStream());
        }
    }
//    private String fileDialogLoad() {
//        if (loadingConf.isSaveHomeFolder()) {
//            return getFrames().getHomePath();
//        }
//        return DataBase.EMPTY_STRING;
//    }
    public void setFight(boolean _animate, boolean _wild) {
        MenuItemUtils.setEnabledMenu(difficulty,false);
        facade.setChangeToFightScene(false);
//        enabledMove = false;
        battle.setVisibleFrontBattle(true);
        scenePanel.getComponent().setVisible(false);
//        mainPanel.remove(scenePanel);
        //initBattle();
        battle.enableAnimation(loadingConf.isEnableAnimation());
        battle.initializeFight(false);
        if (!_animate) {
            AbsMetaLabelPk.paintPk(getImageFactory(), battle);
        }
//        mainPanel.add(battle, CustList.FIRST_INDEX);
        inBattle = true;
        MenuItemUtils.setEnabledMenu(dataBattle,true);
        pack();
        if (loadingConf.isEnableAnimation() && _animate) {
            disableBasicFight();
            if (_wild) {
                fightIntroThread = new FightWildIntroThread(facade, battle.getBattle());
            } else {
                fightIntroThread = new FightTrainerIntroThread(facade, battle.getBattle());
            }
            getThreadFactory().newStartedThread(fightIntroThread);
        } else {
            battle.setComments();
            battle.display();
        }
    }

    public FightIntroThread getFightIntroThread() {
        return fightIntroThread;
    }

    public void setComments() {
        battle.setComments();
    }

    public void interact() {
        MenuItemUtils.setEnabledMenu(difficulty,true);
        scenePanel.interact();
    }

    private void initBattle() {
        battle = new FrontBattle(this, facade);
        battle.addMouseListener(new PkNonModalEvent(modal),new FrontClickEvent(battle));
    }

    public LoadingGame getLoadingConf() {
        return loadingConf;
    }

    public void setLoadingConf(LoadingGame _loadingConf, boolean _save) {
        loadingConf = _loadingConf;
        if (_save) {
            core.getAikiFactory().getConfPkStream().save(StringUtil.concat(getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE), loadingConf);
        }
    }

//    public void resetIndexInGame() {
//        setIndexInGame(IndexConstants.INDEX_NOT_FOUND_ELT);
//    }

//    public void setIndexInGame(byte _indexInGame) {
//        indexInGame = _indexInGame;
//    }

//    public byte getIndexInGame() {
//        return indexInGame;
//    }

//    public boolean isEnabledMove() {
//        return enabledMove;
//    }
//
//    public void setEnabledMove(boolean _enabledMove) {
//        enabledMove = _enabledMove;
//    }

//    public boolean isStartThread() {
//        return startThread;
//    }

//    public boolean isAliveThread() {
//        return battle.isAliveThread() || fightIntroThreadLau != null && fightIntroThreadLau.isAlive();
//    }

    public boolean isClickButtonsPad() {
//        if (loadingConf == null) {
//            return true;
//        }
        return loadingConf.isClickButtonsPad();
    }

    public boolean isEnabledKeyPad() {
//        if (loadingConf == null) {
//            return false;
//        }
        return loadingConf.isEnabledKeyPad();
    }

//    public BasicClient getThreadEmission() {
//        return threadEmission;
//    }
//
//    public void setThreadEmission(BasicClient _threadEmission) {
//        threadEmission = _threadEmission;
//    }

//    public Timer getTimer() {
//        return timer;
//    }
//
//    public void setTimer(Timer _timer) {
//        timer = _timer;
//    }

    public void setSavedGame(boolean _savedGame) {
        savedGame = _savedGame;
    }

    public boolean isInBattle() {
        return inBattle;
    }

//    public boolean isSuccessfulCompile() {
//        return successfulCompile;
//    }

//    public void setSuccessfulCompile(boolean _successfulCompile, boolean _display) {
//        if (_display) {
//            successfulCompile = _successfulCompile;
//            availableHelps.setText(messages.getVal(AVAILAIBLE_HELPS));
//            helpInfo.setText(messages.getVal(HELP_INFO));
//        } else {
//            availableHelps.setText(DataBase.EMPTY_STRING);
//            helpInfo.setText(DataBase.EMPTY_STRING);
//        }
//        pack();
////        availableHelps.repaint();
////        helpInfo.repaint();
////        scenePanel.repaintInteraction();
//        //revalidate();
//        //repaint();
//    }

    public void showFightData() {
//        if (!inBattle) {
//            getFrames().getMessageDialogAbs().input(getCommonFrame(), "", messages.getVal(ERROR_LOADING), getLanguageKey(), GuiConstants.ERROR_MESSAGE);
//            dataBattle.setEnabled(false);
//            return;
//        }
        battle.showFightData();
    }

    public boolean showErrorMessageDialog(String _fileName) {
        if (_fileName.isEmpty()) {
            return false;
        }
        resultFile.display(messages.getVal(ERROR_LOADING),_fileName);
//        getFrames().getMessageDialogAbs().input(getCommonFrame(), _fileName, messages.getVal(ERROR_LOADING), GuiConstants.ERROR_MESSAGE);
        return true;
    }

    public void showSuccessfulMessageDialogThenLoadHelp(String _fileName) {
        resultFile.display(messages.getVal(SUCCESSFUL_LOADING),_fileName);
//        getFrames().getMessageDialogAbs().input(getCommonFrame(), _fileName, messages.getVal(SUCCESSFUL_LOADING), GuiConstants.INFORMATION_MESSAGE);
        availableHelps.setText(messages.getVal(AVAILAIBLE_HELPS));
        helpInfo.setText(messages.getVal(HELP_INFO));
        pack();
//        SecurityManagerUtil.setForbiddenCalls(DataBase.getBeansPackage());
//        ForwardingJavaCompiler.startCompiling();
    }

    public boolean isPaintingScene() {
        return scenePanel.isPaintingScene();
    }

    public void setPaintingScene() {
//        difficulty.setEnabled(!_paintingScene);
        getFrames().getCompoFactory().invokeNow(new ChangeEnabledDifficulty(difficulty, false));
        getFrames().getCompoFactory().invokeNow(new PaintingScene(scenePanel));
//        scenePanel.setPaintingScene(_paintingScene);
    }

    public void setNoPaintingScene() {
        scenePanel.getPaintingScene().set(false);
        scenePanel.setPaintingScene(false);
    }

    public boolean isAnimateMovingHero() {
//        if (loadingConf == null) {
//            return false;
//        }
        return loadingConf.isEnableMovingHerosAnimation();
    }

//    @Override
//    public void setInfos(boolean _success, String _results) {
//        if (!_success) {
//            facade.initializeDefaultHtmlFiles();
//            successfulCompile = false;
//        } else {
//            facade.initializeHtmlFiles();
//            successfulCompile = true;
//        }
//        StreamTextFile.saveTextFile(LOG_FILE, _results);
//        CustComponent.invokeLater(new AfterCompiling(this, _success, true));
//    }

    public void setTextArea(String _text) {
        scenePanel.setTextArea(_text);
    }

    public void disableBasic() {
        disableCom();
    }

    public void disableBasicFight() {
        MenuItemUtils.setEnabledMenu(core.getGameSave(),false);
        disableCom();
    }

    private void disableCom() {
        en(false);
    }

    public void reenableBasic() {
        reenCom();
    }

    public void reenableBasicFight() {
        MenuItemUtils.setEnabledMenu(core.getGameSave(),true);
        reenCom();
    }

    private void reenCom() {
        en(true);
    }

    private void en(boolean _b) {
        MenuItemUtils.setEnabledMenu(newGame,_b);
        MenuItemUtils.setEnabledMenu(params,_b);
        MenuItemUtils.setEnabledMenu(core.getZipLoad(),_b);
        MenuItemUtils.setEnabledMenu(core.getGameLoad(),_b);
    }

    public ProgressingDialog getDialog() {
        return dialog;
    }

    public EnabledMenu getFolderLoad() {
        return core.getFolderLoad();
    }

    public EnabledMenu getZipLoad() {
        return core.getZipLoad();
    }

    public EnabledMenu getGameLoad() {
        return core.getGameLoad();
    }

    public EnabledMenu getNewGame() {
        return newGame;
    }

    public Sex getChosenSex() {
        return chosenSex;
    }

    public void setChosenSex(Sex _c) {
        this.chosenSex = _c;
    }

    public FacadeGame getFacade() {
        return facade;
    }
    public VideoLoading getVideoLoading() {
        return videoLoading;
    }

    @Override
    public String getApplicationName() {
        return APPS_AIKI;
    }

    /*@Override
    public Document getDoc(String _object) {
        return DocumentReaderAikiMultiUtil.getDoc(_object);
    }

    @Override
    public Exiting getExiting(Document _doc) {
        return DocumentReaderAikiMultiUtil.getExiting(_doc);
    }*/

    public AbstractAtomicBooleanCore getLoadFlag() {
        return loadFlag;
    }

    public AbstractFutureParam<AikiNatLgNamesNavigation> getPreparedDataWebTask() {
        return preparedDataWebTask;
    }

    public void setPreparedDataWebTask(AbstractFutureParam<AikiNatLgNamesNavigation> _preparedDataWebTask) {
        preparedDataWebTask = _preparedDataWebTask;
    }

    public AikiNatLgNamesNavigation getPreparedFightTask() {
        return preparedFightTask;
    }

    public void setPreparedFightTask(AikiNatLgNamesNavigation _preparedFightTask) {
        preparedFightTask = _preparedFightTask;
    }

    public AikiNatLgNamesNavigation getPreparedPkTask() {
        return preparedPkTask;
    }

    public void setPreparedPkTask(AikiNatLgNamesNavigation _preparedPkTask) {
        preparedPkTask = _preparedPkTask;
    }

    public AikiNatLgNamesNavigation getPreparedPkNetTask() {
        return preparedPkNetTask;
    }

    public void setPreparedPkNetTask(AikiNatLgNamesNavigation _preparedPkTask) {
        preparedPkNetTask = _preparedPkTask;
    }

    public AikiNatLgNamesNavigation getPreparedDiffTask() {
        return preparedDiffTask;
    }

    public void setPreparedDiffTask(AikiNatLgNamesNavigation _preparedDiffTask) {
        preparedDiffTask = _preparedDiffTask;
    }

    public AikiNatLgNamesNavigation getPreparedProgTask() {
        return preparedProgTask;
    }

    public void setPreparedProgTask(AikiNatLgNamesNavigation _preparedProgTask) {
        preparedProgTask = _preparedProgTask;
    }

    public IntTileRender getTileRender() {
        return core.getTileRender();
    }

    public void setTileRender(IntTileRender _t) {
        this.core.setTileRender(_t);
    }

    public AbsTaskEnabled getTaskEnabled() {
        return taskEnabled;
    }

    public void setTaskEnabled(AbsTaskEnabled _t) {
        this.taskEnabled = _t;
    }
//    public AbstractThread getPreparedDataWebThread() {
//        return preparedDataWebThread;
//    }
//
//    public void setPreparedDataWebThread(AbstractThread _preparedDataWebThread) {
//        preparedDataWebThread = _preparedDataWebThread;
//    }
//
//    public AbstractThread getPreparedFightThread() {
//        return preparedFightThread;
//    }
//
//    public void setPreparedFightThread(AbstractThread _preparedFightThread) {
//        preparedFightThread = _preparedFightThread;
//    }
//
//    public AbstractThread getPreparedPkThread() {
//        return preparedPkThread;
//    }
//
//    public void setPreparedPkThread(AbstractThread _preparedPkThread) {
//        preparedPkThread = _preparedPkThread;
//    }
//
//    public AbstractThread getPreparedPkNetThread() {
//        return preparedPkNetThread;
//    }
//
//    public void setPreparedPkNetThread(AbstractThread _preparedPkThread) {
//        preparedPkNetThread = _preparedPkThread;
//    }
//
//    public AbstractThread getPreparedDiffThread() {
//        return preparedDiffThread;
//    }
//
//    public void setPreparedDiffThread(AbstractThread _preparedDiffThread) {
//        preparedDiffThread = _preparedDiffThread;
//    }
//
//    public AbstractThread getPreparedProgThread() {
//        return preparedProgThread;
//    }
//
//    public void setPreparedProgThread(AbstractThread _preparedProgThread) {
//        preparedProgThread = _preparedProgThread;
//    }

    /*public NetAiki getNet() {
        return net;
    }*/

    public SelectEgg getSelectEgg() {
        return selectEgg;
    }

    public SelectPokemon getSelectPokemon() {
        return selectPokemon;
    }

//    public SelectHealedMove getSelectHealedMove() {
//        return selectHealedMove;
//    }

    public SelectHealingItem getSelectHealingItem() {
        return selectHealingItem;
    }

    public SelectItem getSelectItem() {
        return selectItem;
    }

    public SelectTm getSelectTm() {
        return selectTm;
    }

    public ConsultHosts getConsultHosts() {
        return consultHosts;
    }

    public DialogDifficulty getDialogDifficulty() {
        return dialogDifficulty;
    }

    public DialogGameProgess getDialogGameProgess() {
        return dialogGameProgess;
    }

//    public DialogHtmlData getDialogHtmlData() {
//        return dialogHtmlData;
//    }

    public DialogSoftParams getSoftParams() {
        return softParams;
    }

//    public DialogServerAiki getDialogServer() {
//        return dialogServer;
//    }

    public FrontBattle getBattle() {
        return battle;
    }

    public EnabledMenu getDataBattle() {
        return dataBattle;
    }

    public ScenePanel getScenePanel() {
        return scenePanel;
    }

    public AbstractAtomicBooleanCore getModal() {
        return modal;
    }

    public DialogHeros getDialogHeros() {
        return dialogHeros;
    }

    public FileOpenFrame getFileOpenFrame() {
        return fileOpenFrame;
    }

    public FileOpenFrame getFileOpenRomFrame() {
        return fileOpenRomFrame;
    }

    public FileOpenSaveFrame getFileOpenSaveFrame() {
        return fileOpenSaveFrame;
    }

    public FolderOpenFrame getFileOpenFolderFrame() {
        return fileOpenFolderFrame;
    }

    public FolderOpenSaveFrame getFolderOpenSaveFrame() {
        return folderOpenSaveFrame;
    }

    public FileSaveFrame getFileSaveFrame() {
        return fileSaveFrame;
    }

    public WindowAikiCore getCore() {
        return core;
    }
}
