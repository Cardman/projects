package aiki.gui;






import aiki.db.*;
import aiki.gui.dialogs.*;
import aiki.gui.threads.*;
import aiki.main.*;
import aiki.network.stream.*;
import aiki.sml.*;
import aiki.facade.FacadeGame;
import aiki.game.Game;
import aiki.game.player.enums.Sex;
import aiki.gui.components.fight.FrontBattle;
import aiki.gui.components.fight.FrontClickEvent;
import aiki.gui.components.labels.HeroLabel;
import aiki.gui.components.walk.ScenePanel;
import aiki.gui.events.ConfirmNewGameEvent;
import aiki.gui.events.LoadGameEventAiki;
import aiki.gui.events.LoadZipEvent;
import aiki.gui.events.ManageDifficultyEvent;
import aiki.gui.events.ManageLanguageEventAiki;
import aiki.gui.events.ManageParamsEvent;
import aiki.gui.events.ProponeNewGameEvent;
import aiki.gui.events.SaveGameEventAiki;
import aiki.gui.events.ShowDataFightEvent;
import aiki.gui.events.ShowDataWebEvent;
import aiki.gui.listeners.HeroSelect;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.PokemonPlayer;
import aiki.network.NetAiki;
import aiki.network.SendReceiveServerAiki;
import aiki.network.sml.DocumentReaderAikiMultiUtil;
import aiki.network.sml.DocumentWriterAikiMultiUtil;
import code.expressionlanguage.filenames.AbstractNameValidating;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.events.QuitEvent;
import code.gui.events.QuittingEvent;
import code.gui.images.MetaDimension;
import code.gui.images.MetaPoint;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.AbstractSocket;
import code.network.*;
import code.scripts.messages.gui.MessGuiPkGr;
import code.sml.Document;
import code.sml.Element;
import code.sml.util.ResourcesMessagesUtil;
import code.stream.AbstractFile;
import code.stream.AbstractFileCoreStream;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.stream.core.TechStreams;
import code.threads.AbstractThread;
import code.util.CustList;
import code.util.IdMap;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class WindowAiki extends NetGroupFrame {
    //implemented SettingInfosAfterCompiler

    public static final String OK = "ok";
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

    private static final String CST_NICKNAME = "nickname";

    private static final String SAVING = "saving";

    private static final String SAVING_TITLE = "savingTitle";

    private static final String ERROR_LOADING = "errorLoading";

    private static final String SUCCESSFUL_LOADING = "successfulLoading";

    private static final String TOO_MANY = "tooMany";

    private static final String NO_TRADE = "noTrade";

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

    private static final String F_ONE = "F1";
    private static final String F_TWO = "F2";
    private static final String F_THREE = "F3";

//    private static final String LOG_FILE = LaunchingPokemon.getTempFolderSl()+"errors_compiling.txt";

//    private static final String CLASS_FILES_EXT = DataBase.CLASS_FILES_EXT;

//    private static final String LOCALE = "locale";

    //private static final boolean COMPILE = false;

    private StringMap<String> messages = new StringMap<String>();
    private final ProgressingDialog dialog = new ProgressingDialog(getFrames());
    
//    private Timer timer;

    private BasicClient threadEmission;

    private LoadingGame loadingConf;

    private AbsMenu file;

    private AbsMenuItem zipLoad;

    private AbsMenuItem folderLoad;

    private AbsMenuItem gameLoad;

    private AbsMenuItem gameSave;

    private AbsMenuItem language;

    private AbsMenuItem params;

    private AbsMenuItem quit;

    private AbsMenu dataGame;

    private AbsMenuItem dataWeb;

    private AbsMenuItem dataBattle;

    private AbsMenuItem newGame;

    private AbsMenuItem difficulty;

    private final AbsPanel mainPanel;

    private final CustList<FrameHtmlData> htmlDialogs = new CustList<FrameHtmlData>();

    private byte indexInGame = IndexConstants.INDEX_NOT_FOUND_ELT;

    /**Est vrai si et seulement si une partie vient d'etre sauvegardee*/
    private boolean savedGame;

    private final FacadeGame facade;

    private AbsPanel beginGame;

    private final IdMap<Sex,HeroLabel> herosLabels = new IdMap<Sex,HeroLabel>();

    private AbsTextField nickname;

    private Sex chosenSex;

    private final Clock time;

    private final AbsPlainLabel lastSavedGameDate;

    private String dateLastSaved = DataBase.EMPTY_STRING;

    private final AbsPlainLabel helpInfo;

    private final AbsPlainLabel availableHelps;

    private boolean inBattle;

    private final ScenePanel scenePanel;

//    private Battle battle;
    private FrontBattle battle;

    private boolean enabledMove;

    private FightIntroThread fightIntroThread;
    private AbstractThread fightIntroThreadLau;

    private final VideoLoading videoLoading = new VideoLoading();
    private final LoadFlag loadFlag;
    private PreparedRenderedPages preparedDataWebTask;
    private PreparedRenderedPages preparedFightTask;
    private PreparedRenderedPages preparedPkTask;
    private PreparedRenderedPages preparedPkNetTask;
    private PreparedRenderedPages preparedDiffTask;
    private PreparedRenderedPages preparedProgTask;
//    private AbstractThread preparedDataWebThread;
//    private AbstractThread preparedFightThread;
//    private AbstractThread preparedPkThread;
//    private AbstractThread preparedPkNetThread;
//    private AbstractThread preparedDiffThread;
//    private AbstractThread preparedProgThread;
    private AbstractThread exporting;
//    private KeyPadListener keyPadListener;

//    private ForwardingJavaCompiler compiling;

    //private boolean startThread;

//    private boolean successfulCompile;

//    private final boolean standalone;

    private final NetAiki net = new NetAiki();
    private final SelectEgg selectEgg;
    private final SelectPokemon selectPokemon;
    private final SelectHealedMove selectHealedMove;
    private final SelectHealingItem selectHealingItem;
    private final SelectItem selectItem;
    private final SelectTm selectTm;
    private final ConsultHosts consultHosts;
    private final DialogDifficulty dialogDifficulty;
    private final DialogGameProgess dialogGameProgess;
    private final DialogHtmlData dialogHtmlData;
    private final DialogSoftParams softParams;
    private final DialogServerAiki dialogServer;
    private final AikiFactory aikiFactory;

    public WindowAiki(String _lg, AbstractProgramInfos _list, AikiFactory _aikiFactory) {
        super(_lg, _list);
        selectEgg = new SelectEgg(_list);
        selectPokemon = new SelectPokemon(_list);
        selectHealedMove = new SelectHealedMove(_list);
        selectHealingItem = new SelectHealingItem(_list);
        selectItem = new SelectItem(_list);
        selectTm = new SelectTm(_list);
        consultHosts = new ConsultHosts(_list);
        dialogDifficulty = new DialogDifficulty(_list);
        dialogGameProgess = new DialogGameProgess(_list);
        dialogHtmlData = new DialogHtmlData(_list);
        softParams = new DialogSoftParams(_list);
        dialogServer = new DialogServerAiki(_list);
        loadFlag = new LoadFlagImpl(_list.getThreadFactory().newAtomicBoolean());
        aikiFactory = _aikiFactory;
        setAccessFile(DIALOG_ACCESS);
        setFocusable(true);
        setFocusableWindowState(true);
        facade = new FacadeGame();
        StringList lgs_ = Constants.getAvailableLanguages();
        facade.setLanguages(lgs_);
        StringMap<String> displayLanguages_ = LoadRes.dis();
        facade.setDisplayLanguages(displayLanguages_);
        facade.setSimplyLanguage(_lg);
        setImageIconFrame(LaunchingPokemon.getIcon(getImageFactory()));
        mainPanel = getCompoFactory().newPageBox();
        scenePanel = new ScenePanel(this, facade);
        initBattle();
        initMenuBar();
        MenuItemUtils.setEnabledMenu(gameLoad,false);
        MenuItemUtils.setEnabledMenu(gameSave,false);
        MenuItemUtils.setEnabledMenu(dataGame,false);
        battle.setVisibleFrontBattle(false);
        mainPanel.add(battle);
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
        setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
        facade.setData(new DataBase(_list.getGenerator()));
        initMessages();
        setTitle(messages.getVal(TITLE));
    }
    public static StringMap<String> getMessagesFromLocaleClass(String _folder, String _loc, String _class) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(_folder, _loc, _class);
        String loadedResourcesMessages_ = MessGuiPkGr.ms().getVal(fileName_);
        return ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
    }

    /**server and client
     Method allowing the client to send a serializable object by its socket
     */
    public void sendObjectOk() {
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
    }
    @Override
    public void quit() {
        if (indexInGame != IndexConstants.INDEX_NOT_FOUND_ELT) {
            QuitAiki quit_ = new QuitAiki();
            quit_.setClosing(true);
            quit_.setPlace(indexInGame);
            quit_.setLocale(getLanguageKey());
            sendObject(quit_);
            return;
        }
        if (battle != null && isAliveThread()) {
            return;
        }
//        if (compiling.isAlive()) {
//            int adv_ = Constants.getPercentCompile();
//            String message_ = getCompilingString();
//            String formatted_ = MessageFormat.format(message_, adv_);
//            showErrorMessageDialog(formatted_);
//            return;
//        }
        if (loadingConf == null) {
            //LaunchingPokemon.decrement();
            basicDispose();
            return;
        }
        if (loadingConf.isSaveGameAtExit()) {
            if (loadingConf.getLastSavedGame().isEmpty()) {
                String name_ = StringUtil.concat(LaunchingPokemon.getTempFolderSl(getFrames()),LoadingGame.DEFAULT_SAVE_GAME,Resources.GAME_EXT);
                loadingConf.setLastSavedGame(name_);
                save(name_);
                if (!getFileCoreStream().newFile(name_).exists()) {
                    name_ = StringUtil.concat(StreamFolderFile.getCurrentPath(getFileCoreStream()),LoadingGame.DEFAULT_SAVE_GAME,Resources.GAME_EXT);
                    int index_ = 0;
                    while (getFileCoreStream().newFile(name_).exists()) {
                        name_ = StringUtil.concat(StreamFolderFile.getCurrentPath(getFileCoreStream()),LoadingGame.DEFAULT_SAVE_GAME,Long.toString(index_),Resources.GAME_EXT);
                        index_++;
                    }
                    loadingConf.setLastSavedGame(name_);
                    save(name_);
                }
            } else {
                String path_ = getFileCoreStream().newFile(loadingConf.getLastSavedGame()).getAbsolutePath();
                path_ = StringUtil.replaceBackSlash(path_);
                save(path_);
            }
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingPokemon.getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE), DocumentWriterAikiCoreUtil.setLoadingGame(loadingConf),getStreams());
            //LaunchingPokemon.decrement();
            basicDispose();
        } else if (indexInGame == IndexConstants.INDEX_NOT_FOUND_ELT && !savedGame) {
            if (facade.getGame() == null) {
                StreamTextFile.saveTextFile(StringUtil.concat(LaunchingPokemon.getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE), DocumentWriterAikiCoreUtil.setLoadingGame(loadingConf),getStreams());
                //LaunchingPokemon.decrement();
                basicDispose();
                return;
            }
            int choix_=saving();
            if(choix_!=GuiConstants.CANCEL_OPTION) {
                if(choix_==GuiConstants.YES_OPTION) {
                    String file_ = fileDialogSave();
                    if (!file_.isEmpty()) {
                        loadingConf.setLastSavedGame(file_);
                        save(file_);
                    }
                }
                savedGame = true;
                StreamTextFile.saveTextFile(StringUtil.concat(LaunchingPokemon.getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE), DocumentWriterAikiCoreUtil.setLoadingGame(loadingConf),getStreams());
                //LaunchingPokemon.decrement();
//                ecrireCoordonnees();
//                CustList<FrameHtmlData> frames_ = new CustList<>();
//                frames_.addAll(htmlDialogs);
//                frames_.addAll(battle.getHtmlDialogs());
//                for (FrameHtmlData f: frames_) {
//                    f.dispose();
//                }
//                clearHtmlDialogs();
//                battle.clearHtmlDialogs();
                basicDispose();
            }
        } else {
            //LaunchingPokemon.decrement();
//            ecrireCoordonnees();
//            CustList<FrameHtmlData> frames_ = new CustList<>();
//            frames_.addAll(htmlDialogs);
//            frames_.addAll(battle.getHtmlDialogs());
//            for (FrameHtmlData f: frames_) {
//                f.dispose();
//            }
//            clearHtmlDialogs();
//            battle.clearHtmlDialogs();
            basicDispose();
//            Constants.exit();
//            if (Standalone.isStandalone()) {
//                Constants.exit();
//            }
        }
    }
    @Override
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
        basicDispose();
        //facade = null;
    }
    public void initMessages() {
        facade.getData().setLanguage(facade.getLanguage());
        DocumentReaderAikiCoreUtil.initMessages(facade.getData(),facade.getLanguage());
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, getLanguageKey(), getAccessFile());
        file.setText(messages.getVal(CST_FILE));
        zipLoad.setText(messages.getVal(ZIP_LOAD));
        folderLoad.setText(messages.getVal(FOLDER_LOAD));
        gameLoad.setText(messages.getVal(GAME_LOAD));
        gameSave.setText(messages.getVal(GAME_SAVE));
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

    public String getTooManyString() {
        return messages.getVal(TOO_MANY);
    }

    public String getNoTradeString() {
        return messages.getVal(NO_TRADE);
    }

    public String getOpenedHtmlString() {
        return messages.getVal(OPEN_HTML);
    }

    private void addBeginGame() {
        if (beginGame == null) {
            beginGame = getCompoFactory().newPageBox();
        }
        beginGame.removeAll();
        AbsPanel heros_ = getCompoFactory().newLineBox();
        for (Sex s: Sex.values()) {
            ImageHeroKey i_;
            i_ = new ImageHeroKey(EnvironmentType.ROAD, s);
            int[][] imgTxt_ = facade.getData().getFrontHeros().getVal(i_);
            HeroLabel label_ = new HeroLabel(getImageFactory(),imgTxt_, getCompoFactory());
            label_.setPreferredSize(new MetaDimension(imgTxt_[0].length, imgTxt_.length));
            label_.addMouseListener(new HeroSelect(this, s));
            herosLabels.put(s, label_);
            heros_.add(label_);
        }
        beginGame.add(heros_);
        AbsPanel nickname_ = getCompoFactory().newLineBox();
        nickname_.add(getCompoFactory().newPlainLabel(messages.getVal(CST_NICKNAME)));
        if (nickname == null) {
            nickname = getCompoFactory().newTextField(16);
        }
        nickname_.add(nickname);
        beginGame.add(nickname_);
        AbsPlainButton ok_ = getCompoFactory().newPlainButton(OK);
        ok_.addActionListener(new ConfirmNewGameEvent(this));
        beginGame.add(ok_);
        beginGame.repaintSecondChildren(getImageFactory());
        scenePanel.addBeginGame(beginGame);
    }

    public void confirmNewGame() {
        newGame();
        pack();
    }

    public void clearHtmlDialogs() {
        htmlDialogs.clear();
    }

    public void changeSex(Sex _sex) {
        chosenSex = _sex;
        herosLabels.getVal(_sex).setSelected(true);
        herosLabels.getVal(_sex.getOppositeSex()).setSelected(false);
        beginGame.repaintSecondChildren(getImageFactory());
    }

    private void newGame() {
        if (chosenSex == null) {
            return;
        }
        facade.newGame(nickname.getText(), chosenSex);
        drawGame();
        savedGame = false;
        MenuItemUtils.setEnabledMenu(gameSave,true);
        loadingConf.setLastSavedGame(DataBase.EMPTY_STRING);
    }

    /**thread safe method*/
    public void loadOnlyRom(String _file, PerCent _p, LoadingData _loadingData) {
        if (!_file.isEmpty()) {
            //startThread = true;
            StringMap<String> files_ = StreamFolderFile.getFiles(_file,getFileCoreStream(),getStreams());
            DocumentReaderAikiCoreUtil.loadRomAndCheck(getGenerator(),facade,_file, files_,_p,loadFlag);
            if (!facade.isLoadedData()) {
                LoadRes.loadResources(getGenerator(), facade, _p, loadFlag, _loadingData);
            }
            if (!loadFlag.get()) {
                return;
            }
        } else {
            LoadRes.loadResources(getGenerator(), facade, _p, loadFlag, _loadingData);
            if (!loadFlag.get()) {
                return;
            }
        }
        facade.initializePaginatorTranslations();
        ThreadInvoker.invokeNow(getThreadFactory(),new AfterLoadZip(this), getFrames());
    }

    /**thread safe method*/
    public void loadRomGame(LoadingGame _configuration, String _path, StringList _files, boolean _param, PerCent _p, LoadingData _loadingData) {
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
            StringMap<String> files_ = StreamFolderFile.getFiles(path_,getFileCoreStream(),getStreams());
            DocumentReaderAikiCoreUtil.loadRomAndCheck(getGenerator(),facade,path_, files_,_p,loadFlag);
            if (!facade.isLoadedData()) {
                LoadRes.loadResources(getGenerator(), facade, _p, loadFlag, _loadingData);
            }
            if (!loadFlag.get()) {
                return;
            }
        } else {
            LoadRes.loadResources(getGenerator(), facade, _p, loadFlag, _loadingData);
            if (!loadFlag.get()) {
                return;
            }
        }
        facade.initializePaginatorTranslations();
        ThreadInvoker.invokeNow(getThreadFactory(),new AfterLoadZip(this), getFrames());
        Game g_ = null;
        if (!_files.isEmpty()){
            String file_ = StreamTextFile.contentsOfFile(_files.first(), getFrames().getFileCoreStream(), getFrames().getStreams());
            g_ = DocumentReaderAikiCoreUtil.getGameOrNull(file_);
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
            Game game_ = load(path_, db_,getFileCoreStream(),getStreams());
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
        ThreadInvoker.invokeNow(getThreadFactory(),new AfterLoadGame(this), getFrames());
    }

    public void afterLoadZip() {
        MenuItemUtils.setEnabledMenu(dataGame,true);
        MenuItemUtils.setEnabledMenu(gameLoad,true);
        MenuItemUtils.setEnabledMenu(gameSave,false);
        if (exporting != null && exporting.isAlive()) {
            return;
        }
        if (loadingConf == null) {
            return;
        }
        AbstractNameValidating def_ = getFrames().getValidator();
        if (!def_.okPath(StreamFolderFile.getRelativeRootPath(loadingConf.getExport(), getFileCoreStream()),'/','\\')) {
            loadingConf.setExport("");
        }
        exporting = getThreadFactory().newThread(new ExportRomThread(facade,loadingConf,getThreadFactory(), getFileCoreStream(),getStreams()));
        exporting.start();
    }

    public void afterLoadGame() {
        MenuItemUtils.setEnabledMenu(gameSave,true);
        drawGame();
        savedGame = true;
    }

    private void drawGame() {
        scenePanel.setMessages();
        if (facade.isChangeToFightScene()) {
            if (battle != null) {
                battle.setWild(false);
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
        zipLoad = getCompoFactory().newMenuItem();
        zipLoad.addActionListener(new LoadZipEvent(this,false));
        zipLoad.setAccelerator(GuiConstants.VK_M, GuiConstants.CTRL_DOWN_MASK);
        file.addMenuItem(zipLoad);
        folderLoad = getCompoFactory().newMenuItem();
        folderLoad.addActionListener(new LoadZipEvent(this,true));
        folderLoad.setAccelerator(GuiConstants.VK_D, GuiConstants.CTRL_DOWN_MASK);
        file.addMenuItem(folderLoad);
        gameLoad = getCompoFactory().newMenuItem();
        gameLoad.addActionListener(new LoadGameEventAiki(this));
        gameLoad.setAccelerator(GuiConstants.VK_O, GuiConstants.CTRL_DOWN_MASK);
        file.addMenuItem(gameLoad);
        gameSave = getCompoFactory().newMenuItem();
        gameSave.addActionListener(new SaveGameEventAiki(this));
        gameSave.setAccelerator(GuiConstants.VK_S, GuiConstants.CTRL_DOWN_MASK);
        file.addMenuItem(gameSave);
        file.addSeparator();
        language = getCompoFactory().newMenuItem();
        language.addActionListener(new ManageLanguageEventAiki(this));
//        if (Standalone.isStandalone()) {
//            file.add(language);
//        }
        file.addMenuItem(language);
        params = getCompoFactory().newMenuItem();
        params.setAccelerator(GuiConstants.VK_L, GuiConstants.CTRL_DOWN_MASK);
        params.addActionListener(new ManageParamsEvent(this));
        file.addMenuItem(params);
        file.addSeparator();
        quit = getCompoFactory().newMenuItem();
        quit.setAccelerator((char) GuiConstants.VK_ESCAPE);
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
        dataWeb = getCompoFactory().newMenuItem();
        dataWeb.setAccelerator(F_ONE);
        dataWeb.addActionListener(new ShowDataWebEvent(this));
        dataGame.addMenuItem(dataWeb);
        dataBattle = getCompoFactory().newMenuItem();
        MenuItemUtils.setEnabledMenu(dataBattle,false);
        dataBattle.setAccelerator(F_TWO);
        dataBattle.addActionListener(new ShowDataFightEvent(this));
        dataGame.addMenuItem(dataBattle);
        newGame = getCompoFactory().newMenuItem();
        newGame.addActionListener(new ProponeNewGameEvent(this));
        newGame.setAccelerator(GuiConstants.VK_N, GuiConstants.CTRL_DOWN_MASK);
        dataGame.addMenuItem(newGame);
        difficulty = getCompoFactory().newMenuItem();
        MenuItemUtils.setEnabledMenu(difficulty,false);
        difficulty.addActionListener(new ManageDifficultyEvent(this));
        difficulty.setAccelerator(F_THREE);
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
        if (!NumberUtil.eq(indexInGame, IndexConstants.INDEX_NOT_FOUND_ELT)) {
            return;
        }
        if (!savedGame && facade.getGame() != null) {
            int choix_=saving();
            if(choix_==GuiConstants.CANCEL_OPTION) {
                return;
            }
            loadingConf.setLastSavedGame(DataBase.EMPTY_STRING);
            if(choix_==GuiConstants.YES_OPTION) {
                String file_ = fileDialogSave();
                if (!file_.isEmpty()) {
                    loadingConf.setLastSavedGame(file_);
                    save(file_);
                    dateLastSaved = Clock.getDateTimeText(getThreadFactory());
                    lastSavedGameDate.setText(StringUtil.simpleStringsFormat(messages.getVal(LAST_SAVED_GAME), dateLastSaved));
                    savedGame = true;
                }
            }
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingPokemon.getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE), DocumentWriterAikiCoreUtil.setLoadingGame(loadingConf),getStreams());
        }
        String fileName_;
        if (_folder) {
            fileName_ = getFolderOpenDialogInt().input(getCommonFrame(), getLanguageKey(), false);
        } else {
            fileName_ = fileDialogLoad(Resources.ZIPPED_DATA_EXT, true);
        }
        if (fileName_.isEmpty()) {
            return;
        }
        PerCent p_ = new PerCentIncr(getThreadFactory().newAtomicInteger());
        loadFlag.set(true);
        LoadingThread load_ = new LoadingThread(this, fileName_,p_, new DefLoadingData(facade.getLanguages(), facade.getDisplayLanguages()));
        getThreadFactory().newStartedThread(load_);
    }

    public void loadGame() {
        if (!NumberUtil.eq(indexInGame, IndexConstants.INDEX_NOT_FOUND_ELT)) {
            return;
        }
        if (!savedGame && facade.getGame() != null) {
            int choix_=saving();
            if(choix_==GuiConstants.CANCEL_OPTION) {
                return;
            }
            loadingConf.setLastSavedGame(DataBase.EMPTY_STRING);
            if(choix_==GuiConstants.YES_OPTION) {
                String file_ = fileDialogSave();
                if (!file_.isEmpty()) {
                    loadingConf.setLastSavedGame(file_);
                    save(file_);
                    dateLastSaved = Clock.getDateTimeText(getThreadFactory());
//                    lastSavedGameDate.setText(MessageFormat.format(messages.getVal(LAST_SAVED_GAME), dateLastSaved));
                    lastSavedGameDate.setText(StringUtil.simpleStringsFormat(messages.getVal(LAST_SAVED_GAME), dateLastSaved));
                    savedGame = true;
                }
            }
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingPokemon.getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE), DocumentWriterAikiCoreUtil.setLoadingGame(loadingConf),getStreams());
        }
        String fileName_ = fileDialogLoad(Resources.GAME_EXT, false);
        if (fileName_.isEmpty()) {
            return;
        }
        boolean error_ = false;
        DataBase db_ = facade.getData();
        Game game_ = load(fileName_, db_,getFileCoreStream(),getStreams());
        if (game_ != null) {
            facade.load(game_);
            MenuItemUtils.setEnabledMenu(gameSave,true);
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
            showErrorMessageDialog(fileName_);
        }
    }

    public static Game load(String _fileName, DataBase _data, AbstractFileCoreStream _fact, TechStreams _streams) {
        Game game_ = DocumentReaderAikiCoreUtil.getGame(StreamTextFile.contentsOfFile(_fileName,_fact,_streams));
        if (game_ == null) {
            return null;
        }
        if (!game_.checkAndInitialize(_data)) {
            return null;
        }
        return game_;
    }

    public void saveGame() {
        String fileName_ = fileDialogSave();
        if (fileName_.isEmpty()) {
            return;
        }
        save(fileName_);
        fileName_ = StringUtil.replaceBackSlash(fileName_);
        loadingConf.setLastSavedGame(fileName_);
        dateLastSaved = Clock.getDateTimeText(getThreadFactory());
        lastSavedGameDate.setText(StringUtil.simpleStringsFormat(messages.getVal(LAST_SAVED_GAME), dateLastSaved));
        savedGame = true;
    }

    //Save option
    public void save(String _fileName) {
        Game game_ = facade.getGame();
        if (game_ == null) {
            return;
        }
        game_.setZippedRom(facade.getZipName());
        StreamTextFile.saveTextFile(_fileName, DocumentWriterAikiCoreUtil.setGame(game_),getStreams());
    }

    public void manageLanguage() {
        if (!canChangeLanguageAll()) {
            FrameUtil.showDialogError(this, GuiConstants.ERROR_MESSAGE);
            return;
        }
        LanguageDialog.setLanguageDialog(this, messages.getVal(CST_LANGUAGE));
        String langue_ = LanguageDialog.getStaticLanguage(getLanguageDialog());
        LanguageDialog.changeLanguage(langue_,getFrames(),LaunchingPokemon.getTempFolder(getFrames()));
    }

    public void manageParams() {
        DialogSoftParams.setSoftParams(this, loadingConf);
        DialogSoftParams.setParams(loadingConf, getSoftParams());
        if (DialogSoftParams.isOk(getSoftParams())) {
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingPokemon.getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE), DocumentWriterAikiCoreUtil.setLoadingGame(loadingConf),getStreams());
        }
    }

    public void proponeNewGame() {
        if (!NumberUtil.eq(indexInGame, IndexConstants.INDEX_NOT_FOUND_ELT)) {
            return;
        }
        addBeginGame();
        pack();
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

    @Override
    public boolean canChangeLanguage() {
        if (!inBattle) {
            if (isPaintingScene()) {
                return false;
            }
            return scenePanel.isMenusVisible();
        } else {
            if (battle != null) {
                if (isAliveThread()) {
                    return false;
                }
                return battle.isEnabledChangeLanguage();
            }
        }
        return true;
    }

    @Override
    public void changeLanguage(String _language) {
        setLanguageKey(_language);
        facade.setLanguage(_language);
        initMessages();
        if (inBattle) {
            battle.setMessages();
        } else {
            scenePanel.setMessages();
        }
        pack();
        for (FrameHtmlData f: htmlDialogs) {
            f.setTitle(messages.getVal(TITLE_WEB));
            if (!f.isVisible()) {
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
        if (!dataWeb.isEnabled()) {
            return;
        }
//        if (preparedDataWebThread == null || preparedDataWebThread.isAlive() || preparedDataWebTask == null) {
//            return;
//        }
//        if (showErrorMessageDialog(ForwardingJavaCompiler.getMess(Constants.getLanguage()))) {
//            return;
//        }
        if (!htmlDialogs.isEmpty()) {
            if (!htmlDialogs.first().isVisible()) {
                if (htmlDialogs.first().getSession().isProcessing()) {
                    return;
                }
                reinitWebData();
                htmlDialogs.first().setVisible(true);
            }
            return;
        }

        //JTextArea area_ = new JTextArea();
        RenderedPage session_;
        session_ = new RenderedPage(getCompoFactory().newAbsScrollPane(), getFrames());
        session_.setProcess(videoLoading.getVideo(getGenerator(),getFileCoreStream(),getFrames()));
        FrameHtmlData dialog_ = new FrameHtmlData(this, messages.getVal(TITLE_WEB), session_);
//        dialog_.initSession(facade.getData().getWebFiles(), successfulCompile, Resources.CONFIG_DATA, Resources.ACCESS_TO_DEFAULT_DATA);
        dialog_.initSessionLg(facade,preparedDataWebTask,facade.getLanguage());
        htmlDialogs.add(dialog_);
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
        return htmlDialogs.first().isVisible();
    }
    private void ecrireCoordonnees() {
        MetaPoint point_=getLocation();
        SoftApplicationCore.saveCoords(LaunchingPokemon.getTempFolder(getFrames()),Resources.COORDS, point_.getXcoord(),point_.getYcoord(),getStreams());
    }

    public void processLoad(String _fileName, PerCent _p, LoadingData _load) {
        StringMap<String> files_ = StreamFolderFile.getFiles(_fileName,getFileCoreStream(),getStreams());
        DocumentReaderAikiCoreUtil.loadRomAndCheck(getGenerator(),facade,_fileName, files_,_p,loadFlag);
        if (!facade.isLoadedData()) {
            LoadRes.loadResources(getGenerator(), facade, _p, loadFlag, _load);
        }
        if (!loadFlag.get()) {
            return;
        }
        facade.clearGame();
        facade.initializePaginatorTranslations();
        inBattle = false;
        ThreadInvoker.invokeNow(getThreadFactory(),new ReinitComponents(this), getFrames());
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
        ThreadInvoker.invokeNow(getThreadFactory(),new AfterLoadZip(this), getFrames());
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

    @Override
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
    }

    private int saving() {
        //warning message
        return confirm(messages.getVal(SAVING),messages.getVal(SAVING_TITLE));
    }

    private int confirm(String _message,String _titre) {
        //warning message
        return getConfirmDialogAns().input(getCommonFrame(),_message,_titre, getLanguageKey(),GuiConstants.YES_NO_CANCEL_OPTION);
    }

    /**Sauvegarder une partie dans un fichier*/
    private String fileDialogSave() {
        String path_;
        boolean saveConfig_ = false;
        if (loadingConf.isSaveHomeFolder()) {
            saveConfig_ = true;
            path_=getFileSaveDialogInt().input(getCommonFrame(), getLanguageKey(), true, Resources.GAME_EXT, getFrames().getHomePath());
        } else {
            path_=getFileSaveDialogInt().input(getCommonFrame(), getLanguageKey(), true, Resources.GAME_EXT, DataBase.EMPTY_STRING);
        }
        if (path_ == null) {
            path_ = DataBase.EMPTY_STRING;
        } else if (saveConfig_) {
            loadingConf.setLastSavedGame(path_);
            loadingConf.setLastRom(facade.getZipName());
            String configPath_ = StringUtil.replaceExtension(path_, Resources.GAME_EXT, Resources.CONF_EXT);
            //String configPath_ = path_.replaceAll(StringList.quote(Resources.GAME_EXT)+StringList.END_REG_EXP, Resources.CONF_EXT);
            StreamTextFile.saveTextFile(configPath_, DocumentWriterAikiCoreUtil.setLoadingGame(loadingConf),getStreams());
            //configPath_ +=
        }
        return path_;
    }

    /**Sauvegarder une partie dans un fichier*/
    private String fileDialogLoad(String _ext, boolean _zipFile) {
        String path_;
        if (_zipFile) {
            if (loadingConf != null && loadingConf.isLoadHomeFolder()) {
                path_=getFileOpenDialogInt().input(getCommonFrame(),getLanguageKey(),true, _ext, getFrames().getHomePath());
            } else {
                path_=getFileOpenDialogInt().input(getCommonFrame(),getLanguageKey(),true, _ext, StreamFolderFile.getCurrentPath(getFileCoreStream()));
            }
//            FileOpenDialog.setFileOpenDialog(this,Constants.getLanguage(),true, _ext, SoftApplication.getFolderJarPath(), Resources.EXCLUDED);
        } else {
            if (loadingConf.isSaveHomeFolder()) {
                path_=getFileOpenDialogInt().input(getCommonFrame(),getLanguageKey(),true, _ext, getFrames().getHomePath());
            } else {
                path_=getFileOpenDialogInt().input(getCommonFrame(),getLanguageKey(),true, _ext, DataBase.EMPTY_STRING);
            }
        }
        if (path_ == null) {
            path_ = DataBase.EMPTY_STRING;
        }
        return path_;
    }

    public void setFight(boolean _animate, boolean _wild) {
        MenuItemUtils.setEnabledMenu(difficulty,false);
        facade.setChangeToFightScene(false);
        enabledMove = false;
        battle.setVisibleFrontBattle(true);
        scenePanel.getComponent().setVisible(false);
//        mainPanel.remove(scenePanel);
        //initBattle();
        battle.enableAnimation(loadingConf.isEnableAnimation());
        battle.initializeFight(false);
        if (!_animate) {
            battle.repaintLabel(getImageFactory());
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
            fightIntroThreadLau = getThreadFactory().newThread(fightIntroThread);
            fightIntroThreadLau.start();
        } else {
            battle.setComments();
            battle.display();
        }
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
        battle.addMouseListener(new FrontClickEvent(battle));
    }

    public LoadingGame getLoadingConf() {
        return loadingConf;
    }

    public void setLoadingConf(LoadingGame _loadingConf, boolean _save) {
        loadingConf = _loadingConf;
        if (_save) {
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingPokemon.getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE), DocumentWriterAikiCoreUtil.setLoadingGame(loadingConf),getStreams());
        }
    }

    public void resetIndexInGame() {
        setIndexInGame(IndexConstants.INDEX_NOT_FOUND_ELT);
    }

    public void setIndexInGame(byte _indexInGame) {
        indexInGame = _indexInGame;
    }

    public byte getIndexInGame() {
        return indexInGame;
    }

    public boolean isEnabledMove() {
        return enabledMove;
    }

    public void setEnabledMove(boolean _enabledMove) {
        enabledMove = _enabledMove;
    }

//    public boolean isStartThread() {
//        return startThread;
//    }

    public boolean isAliveThread() {
        return battle.isAliveThread() || fightIntroThreadLau != null && fightIntroThreadLau.isAlive();
    }

    public boolean isClickButtonsPad() {
        if (loadingConf == null) {
            return true;
        }
        return loadingConf.isClickButtonsPad();
    }

    public boolean isEnabledKeyPad() {
        if (loadingConf == null) {
            return false;
        }
        return loadingConf.isEnabledKeyPad();
    }

    public BasicClient getThreadEmission() {
        return threadEmission;
    }

    public void setThreadEmission(BasicClient _threadEmission) {
        threadEmission = _threadEmission;
    }

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
        battle.showFightData();
    }

    public boolean showErrorMessageDialog(String _fileName) {
        if (_fileName.isEmpty()) {
            return false;
        }
        getFrames().getMessageDialogAbs().input(getCommonFrame(), _fileName, messages.getVal(ERROR_LOADING), getLanguageKey(), GuiConstants.ERROR_MESSAGE);
        return true;
    }

    public void showSuccessfulMessageDialogThenLoadHelp(String _fileName) {
        getFrames().getMessageDialogAbs().input(getCommonFrame(), _fileName, messages.getVal(SUCCESSFUL_LOADING), getLanguageKey(), GuiConstants.INFORMATION_MESSAGE);
        availableHelps.setText(messages.getVal(AVAILAIBLE_HELPS));
        helpInfo.setText(messages.getVal(HELP_INFO));
        pack();
//        SecurityManagerUtil.setForbiddenCalls(DataBase.getBeansPackage());
//        ForwardingJavaCompiler.startCompiling();
    }

    public boolean isPaintingScene() {
        return scenePanel.isPaintingScene();
    }

    public void setPaintingScene(boolean _paintingScene) {
//        difficulty.setEnabled(!_paintingScene);
        ThreadInvoker.invokeNow(getThreadFactory(),new ChangeEnabledDifficulty(difficulty, !_paintingScene), getFrames());
        ThreadInvoker.invokeNow(getThreadFactory(),new PaintingScene(scenePanel, _paintingScene), getFrames());
//        scenePanel.setPaintingScene(_paintingScene);
    }

    public void setNoPaintingScene() {
        scenePanel.setPaintingScene(false);
    }

    public boolean isAnimateMovingHero() {
        if (loadingConf == null) {
            return false;
        }
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

    public void setTextArea(String _text, int _messageType) {
        scenePanel.setTextArea(_text, _messageType);
    }

    public void disableBasic() {
        disableCom();
    }

    public void disableBasicFight() {
        MenuItemUtils.setEnabledMenu(gameSave,false);
        disableCom();
    }

    private void disableCom() {
        en(false);
    }

    public void reenableBasic() {
        reenCom();
    }

    public void reenableBasicFight() {
        MenuItemUtils.setEnabledMenu(gameSave,true);
        reenCom();
    }

    private void reenCom() {
        en(true);
    }

    private void en(boolean _b) {
        MenuItemUtils.setEnabledMenu(newGame,_b);
        MenuItemUtils.setEnabledMenu(params,_b);
        MenuItemUtils.setEnabledMenu(zipLoad,_b);
        MenuItemUtils.setEnabledMenu(gameLoad,_b);
    }

    public ProgressingDialog getDialog() {
        return dialog;
    }

    public AbsMenuItem getFolderLoad() {
        return folderLoad;
    }

    public AbsMenuItem getZipLoad() {
        return zipLoad;
    }

    public AbsMenuItem getGameLoad() {
        return gameLoad;
    }

    public AbsMenuItem getNewGame() {
        return newGame;
    }

    public FacadeGame getFacade() {
        return facade;
    }
    public VideoLoading getVideoLoading() {
        return videoLoading;
    }

    @Override
    public String getApplicationName() {
        return LaunchingPokemon.getMainWindowClass();
    }

    @Override
    public Document getDoc(String _object) {
        return DocumentReaderAikiMultiUtil.getDoc(_object);
    }

    @Override
    public Exiting getExiting(Document _doc) {
        return DocumentReaderAikiMultiUtil.getExiting(_doc);
    }

    public LoadFlag getLoadFlag() {
        return loadFlag;
    }

    public PreparedRenderedPages getPreparedDataWebTask() {
        return preparedDataWebTask;
    }

    public void setPreparedDataWebTask(PreparedRenderedPages _preparedDataWebTask) {
        preparedDataWebTask = _preparedDataWebTask;
    }

    public PreparedRenderedPages getPreparedFightTask() {
        return preparedFightTask;
    }

    public void setPreparedFightTask(PreparedRenderedPages _preparedFightTask) {
        preparedFightTask = _preparedFightTask;
    }

    public PreparedRenderedPages getPreparedPkTask() {
        return preparedPkTask;
    }

    public void setPreparedPkTask(PreparedRenderedPages _preparedPkTask) {
        preparedPkTask = _preparedPkTask;
    }

    public PreparedRenderedPages getPreparedPkNetTask() {
        return preparedPkNetTask;
    }

    public void setPreparedPkNetTask(PreparedRenderedPages _preparedPkTask) {
        preparedPkNetTask = _preparedPkTask;
    }

    public PreparedRenderedPages getPreparedDiffTask() {
        return preparedDiffTask;
    }

    public void setPreparedDiffTask(PreparedRenderedPages _preparedDiffTask) {
        preparedDiffTask = _preparedDiffTask;
    }

    public PreparedRenderedPages getPreparedProgTask() {
        return preparedProgTask;
    }

    public void setPreparedProgTask(PreparedRenderedPages _preparedProgTask) {
        preparedProgTask = _preparedProgTask;
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

    public NetAiki getNet() {
        return net;
    }

    public SelectEgg getSelectEgg() {
        return selectEgg;
    }

    public SelectPokemon getSelectPokemon() {
        return selectPokemon;
    }

    public SelectHealedMove getSelectHealedMove() {
        return selectHealedMove;
    }

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

    public DialogHtmlData getDialogHtmlData() {
        return dialogHtmlData;
    }

    public DialogSoftParams getSoftParams() {
        return softParams;
    }

    public DialogServerAiki getDialogServer() {
        return dialogServer;
    }

    public AikiFactory getAikiFactory() {
        return aikiFactory;
    }

    public FrontBattle getBattle() {
        return battle;
    }
}
