package aiki.gui;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import aiki.db.DataBase;
import aiki.db.ImageHeroKey;
import aiki.db.LoadFlag;
import aiki.db.PerCent;
import aiki.gui.dialogs.*;
import aiki.gui.threads.*;
import aiki.main.*;
import aiki.sml.*;
import aiki.facade.FacadeGame;
import aiki.game.Game;
import aiki.game.player.enums.Sex;
import aiki.gui.components.fight.FrontBattle;
import aiki.gui.components.fight.FrontClickEvent;
import aiki.gui.components.labels.HeroLabel;
import aiki.gui.components.walk.ScenePanel;
import aiki.gui.events.ConfirmNewGameEvent;
import aiki.gui.events.LoadGameEvent;
import aiki.gui.events.LoadZipEvent;
import aiki.gui.events.ManageDifficultyEvent;
import aiki.gui.events.ManageLanguageEvent;
import aiki.gui.events.ManageParamsEvent;
import aiki.gui.events.ProponeNewGameEvent;
import aiki.gui.events.QuitEvent;
import aiki.gui.events.SaveGameEvent;
import aiki.gui.events.ShowDataFightEvent;
import aiki.gui.events.ShowDataWebEvent;
import aiki.gui.listeners.HeroSelect;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.PokemonPlayer;
import aiki.network.Net;
import aiki.network.SendReceiveServer;
import aiki.network.sml.DocumentReaderAikiMultiUtil;
import aiki.network.sml.DocumentWriterAikiMultiUtil;
import aiki.network.stream.CheckCompatibility;
import aiki.network.stream.IndexOfArriving;
import aiki.network.stream.InitTrading;
import aiki.network.stream.NetPokemon;
import aiki.network.stream.NewPlayer;
import aiki.network.stream.Ok;
import aiki.network.stream.Quit;
import code.expressionlanguage.filenames.AbstractNameValidating;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.events.QuittingEvent;
import code.gui.initialize.AbstractProgramInfos;
import code.network.AttemptConnecting;
import code.network.BasicClient;
import code.network.Exiting;
import code.network.NetGroupFrame;
import code.resources.ResourceFiles;
import code.scripts.messages.gui.MessGuiPkGr;
import code.sml.stream.ExtractFromFiles;
import code.sml.util.ResourcesMessagesUtil;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.threads.ThreadUtil;
import code.util.CustList;
import code.util.EnumMap;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.Constants;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class MainWindow extends NetGroupFrame {
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
    private final ProgressingDialogPokemon dialog = new ProgressingDialogPokemon(this);
    
//    private Timer timer;

    private BasicClient threadEmission;

    private LoadingGame loadingConf;

    private Menu file;

    private MenuItem zipLoad;

    private MenuItem folderLoad;

    private MenuItem gameLoad;

    private MenuItem gameSave;

    private MenuItem language;

    private MenuItem params;

    private MenuItem quit;

    private Menu dataGame;

    private MenuItem dataWeb;

    private MenuItem dataBattle;

    private MenuItem newGame;

    private MenuItem difficulty;

    private final Panel mainPanel;

    private final CustList<FrameHtmlData> htmlDialogs = new CustList<FrameHtmlData>();

    private byte indexInGame = IndexConstants.INDEX_NOT_FOUND_ELT;

    /**Est vrai si et seulement si une partie vient d'etre sauvegardee*/
    private boolean savedGame;

    private final FacadeGame facade;

    private Panel beginGame;

    private final EnumMap<Sex,HeroLabel> herosLabels = new EnumMap<Sex,HeroLabel>();

    private TextField nickname;

    private Sex chosenSex;

    private final Clock time;

    private final TextLabel lastSavedGameDate;

    private String dateLastSaved = DataBase.EMPTY_STRING;

    private final TextLabel helpInfo;

    private final TextLabel availableHelps;

    private boolean inBattle;

    private final ScenePanel scenePanel;

//    private Battle battle;
    private FrontBattle battle;

    private boolean enabledMove;

    private FightIntroThread fightIntroThread;
    private Thread fightIntroThreadLau;

    private final VideoLoading videoLoading = new VideoLoading();
    private final LoadFlag loadFlag = new LoadFlagImpl();
    private PreparedRenderedPages preparedDataWebTask;
    private PreparedRenderedPages preparedFightTask;
    private PreparedRenderedPages preparedPkTask;
    private PreparedRenderedPages preparedPkNetTask;
    private PreparedRenderedPages preparedDiffTask;
    private PreparedRenderedPages preparedProgTask;
    private Thread preparedDataWebThread;
    private Thread preparedFightThread;
    private Thread preparedPkThread;
    private Thread preparedPkNetThread;
    private Thread preparedDiffThread;
    private Thread preparedProgThread;
    private Thread exporting;
//    private KeyPadListener keyPadListener;

//    private ForwardingJavaCompiler compiling;

    //private boolean startThread;

//    private boolean successfulCompile;

//    private final boolean standalone;

    private final Net net = new Net();
    private final SelectEgg selectEgg = new SelectEgg();
    private final SelectPokemon selectPokemon = new SelectPokemon();
    private final SelectHealedMove selectHealedMove = new SelectHealedMove();
    private final SelectHealingItem selectHealingItem = new SelectHealingItem();
    private final SelectItem selectItem = new SelectItem();
    private final SelectTm selectTm = new SelectTm();
    private final ConsultHosts consultHosts = new ConsultHosts();
    private final DialogDifficulty dialogDifficulty = new DialogDifficulty();
    private final DialogGameProgess dialogGameProgess = new DialogGameProgess();
    private final DialogHtmlData dialogHtmlData = new DialogHtmlData();
    private final SoftParams softParams = new SoftParams();
    private final DialogServer dialogServer = new DialogServer();
    private final AikiFactory aikiFactory;

    public MainWindow(String _lg, AbstractProgramInfos _list, AikiFactory _aikiFactory) {
        super(_lg, _list);
        aikiFactory = _aikiFactory;
        setAccessFile(DIALOG_ACCESS);
        setFocusable(true);
        setFocusableWindowState(true);
        facade = new FacadeGame();
        StringList lgs_ = Constants.getAvailableLanguages();
        facade.setLanguages(lgs_);
        StringMap<String> displayLanguages_ = new StringMap<String>();
        for (String s: lgs_) {
            displayLanguages_.put(s, Constants.getDisplayLanguage(s));
        }
        facade.setDisplayLanguages(displayLanguages_);
        facade.setSimplyLanguage(_lg);
        setImageIconFrame(LaunchingPokemon.getIcon());
        mainPanel = Panel.newPageBox();
        scenePanel = new ScenePanel(this, facade);
        initBattle();
        initMenuBar();
        gameLoad.setEnabledMenu(false);
        gameSave.setEnabledMenu(false);
        dataGame.setEnabledMenu(false);
        battle.setVisibleFrontBattle(false);
        mainPanel.add(battle);
        mainPanel.add(scenePanel.getComponent());
        time = new Clock();
        mainPanel.add(time);
        lastSavedGameDate = new TextLabel("");
        mainPanel.add(lastSavedGameDate);
        helpInfo = new TextLabel("");
        mainPanel.add(helpInfo);
        availableHelps = new TextLabel("");
        mainPanel.add(availableHelps);
        setContentPane(mainPanel);
        //setVisible(true);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
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
    @Override
    public void quit() {
        if (indexInGame != IndexConstants.INDEX_NOT_FOUND_ELT) {
            Quit quit_ = new Quit();
            quit_.setClosing(true);
            quit_.setPlace(indexInGame);
            quit_.setLocale(getLanguageKey());
            sendObject(quit_);
            return;
        }
        if (battle != null) {
            while (isAliveThread()) {
                ThreadUtil.sleep(0);
            }
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
                if (!new File(name_).exists()) {
                    name_ = StringUtil.concat(StreamFolderFile.getCurrentPath(),LoadingGame.DEFAULT_SAVE_GAME,Resources.GAME_EXT);
                    int index_ = 0;
                    while (new File(name_).exists()) {
                        name_ = StringUtil.concat(StreamFolderFile.getCurrentPath(),LoadingGame.DEFAULT_SAVE_GAME,Long.toString(index_),Resources.GAME_EXT);
                        index_++;
                    }
                    loadingConf.setLastSavedGame(name_);
                    save(name_);
                }
            } else {
                String path_ = new File(loadingConf.getLastSavedGame()).getAbsolutePath();
                path_ = StringUtil.replaceBackSlash(path_);
                save(path_);
            }
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingPokemon.getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE), DocumentWriterAikiCoreUtil.setLoadingGame(loadingConf));
            //LaunchingPokemon.decrement();
            basicDispose();
        } else if (indexInGame == IndexConstants.INDEX_NOT_FOUND_ELT && !savedGame) {
            if (facade.getGame() == null) {
                StreamTextFile.saveTextFile(StringUtil.concat(LaunchingPokemon.getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE), DocumentWriterAikiCoreUtil.setLoadingGame(loadingConf));
                //LaunchingPokemon.decrement();
                basicDispose();
                return;
            }
            int choix_=saving();
            if(choix_!=JOptionPane.CANCEL_OPTION) {
                if(choix_==JOptionPane.YES_OPTION) {
                    String file_ = fileDialogSave();
                    if (!file_.isEmpty()) {
                        loadingConf.setLastSavedGame(file_);
                        save(file_);
                    }
                }
                savedGame = true;
                StreamTextFile.saveTextFile(StringUtil.concat(LaunchingPokemon.getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE), DocumentWriterAikiCoreUtil.setLoadingGame(loadingConf));
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
        while (isPaintingScene()) {
            continue;
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
        messages = MainWindow.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, getLanguageKey(), getAccessFile());
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
            beginGame = Panel.newPageBox();
        }
        beginGame.removeAll();
        Panel heros_ = Panel.newLineBox();
        for (Sex s: Sex.values()) {
            ImageHeroKey i_;
            i_ = new ImageHeroKey(EnvironmentType.ROAD, s);
            int[][] imgTxt_ = facade.getData().getFrontHeros().getVal(i_);
            HeroLabel label_ = new HeroLabel(imgTxt_);
            label_.setPreferredSize(new Dimension(imgTxt_[0].length, imgTxt_.length));
            label_.addMouseListener(new HeroSelect(this, s));
            herosLabels.put(s, label_);
            heros_.add(label_);
        }
        beginGame.add(heros_);
        Panel nickname_ = Panel.newLineBox();
        nickname_.add(new TextLabel(messages.getVal(CST_NICKNAME)));
        if (nickname == null) {
            nickname = new TextField(16);
        }
        nickname_.add(nickname);
        beginGame.add(nickname_);
        LabelButton ok_ = new LabelButton(OK);
        ok_.addMouseListener(new ConfirmNewGameEvent(this));
        beginGame.add(ok_);
        beginGame.repaintSecondChildren();
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
        beginGame.repaintSecondChildren();
    }

    private void newGame() {
        if (chosenSex == null) {
            return;
        }
        facade.newGame(nickname.getText(), chosenSex);
        drawGame();
        savedGame = false;
        gameSave.setEnabledMenu(true);
        loadingConf.setLastSavedGame(DataBase.EMPTY_STRING);
    }

    /**thread safe method*/
    public void loadOnlyRom(String _file, PerCent _p) {
        if (!_file.isEmpty()) {
            //startThread = true;
            StringMap<String> files_ = StreamFolderFile.getFiles(_file);
            DocumentReaderAikiCoreUtil.loadRomAndCheck(getGenerator(),facade,_file, files_,_p,loadFlag);
            if (!facade.isLoadedData()) {
                DocumentReaderAikiCoreUtil.loadResources(getGenerator(),facade,_p,loadFlag);
            }
            if (!loadFlag.get()) {
                return;
            }
        } else {
            DocumentReaderAikiCoreUtil.loadResources(getGenerator(),facade,_p,loadFlag);
            if (!loadFlag.get()) {
                return;
            }
        }
        facade.initializePaginatorTranslations();
        ThreadInvoker.invokeNow(new AfterLoadZip(this));
    }

    /**thread safe method*/
    public void loadRomGame(LoadingGame _configuration, String _path, StringMap<Object> _files, boolean _param, PerCent _p) {
        String path_;
        if (!_configuration.getLastRom().isEmpty()) {
            String lastRom_ = StringUtil.replaceBackSlash(_configuration.getLastRom());
            File file_ = new File(lastRom_);
            if (!StreamFolderFile.isAbsolute(lastRom_)) {
                path_ = StringUtil.concat(_path,_configuration.getLastRom());
            } else {
                path_ = file_.getAbsolutePath();
            }
            path_ = StringUtil.replaceBackSlash(path_);
            StringMap<String> files_ = StreamFolderFile.getFiles(path_);
            DocumentReaderAikiCoreUtil.loadRomAndCheck(getGenerator(),facade,path_, files_,_p,loadFlag);
            if (!facade.isLoadedData()) {
                DocumentReaderAikiCoreUtil.loadResources(getGenerator(),facade,_p,loadFlag);
            }
            if (!loadFlag.get()) {
                return;
            }
        } else {
            DocumentReaderAikiCoreUtil.loadResources(getGenerator(),facade,_p,loadFlag);
            if (!loadFlag.get()) {
                return;
            }
        }
        facade.initializePaginatorTranslations();
        ThreadInvoker.invokeNow(new AfterLoadZip(this));
        if (!_files.isEmpty() && _files.values().first() instanceof Game) {
            if (!facade.checkAndSetGame((Game) _files.values().first())) {
                loadFlag.set(false);
                if (_param) {
                    setLoadingConf(_configuration, false);
                }
                return;
            }
        } else {
            String lastSave_ = StringUtil.replaceBackSlash(_configuration.getLastSavedGame());
            File file_ = new File(lastSave_);
            if (!StreamFolderFile.isAbsolute(lastSave_)) {
                path_ = StringUtil.concat(_path,_configuration.getLastSavedGame());
            } else {
                path_ = file_.getAbsolutePath();
            }
            path_ = StringUtil.replaceBackSlash(path_);
            DataBase db_ = facade.getData();
            Game game_ = load(path_, db_);
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
        ThreadInvoker.invokeNow(new AfterLoadGame(this));
    }

    public void afterLoadZip() {
        dataGame.setEnabledMenu(true);
        gameLoad.setEnabledMenu(true);
        gameSave.setEnabledMenu(false);
        if (exporting != null && exporting.isAlive()) {
            return;
        }
        if (loadingConf == null) {
            return;
        }
        AbstractNameValidating def_ = getFrames().getValidator();
        if (!def_.okPath(StreamFolderFile.getRelativeRootPath(loadingConf.getExport()),'/','\\')) {
            loadingConf.setExport("");
        }
        exporting = new Thread(new ExportRomThread(facade,loadingConf));
        exporting.start();
    }

    public void afterLoadGame() {
        gameSave.setEnabledMenu(true);
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
        difficulty.setEnabledMenu(true);
        battle.setVisibleFrontBattle(false);
        scenePanel.getComponent().setVisible(true);
        inBattle = false;
        dataBattle.setEnabledMenu(false);
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
        MenuBar bar_ = new MenuBar();
        file = new Menu();
        zipLoad = new MenuItem();
        zipLoad.addActionListener(new LoadZipEvent(this,false));
        zipLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
        file.addMenuItem(zipLoad);
        folderLoad = new MenuItem();
        folderLoad.addActionListener(new LoadZipEvent(this,true));
        folderLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
        file.addMenuItem(folderLoad);
        gameLoad = new MenuItem();
        gameLoad.addActionListener(new LoadGameEvent(this));
        gameLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        file.addMenuItem(gameLoad);
        gameSave = new MenuItem();
        gameSave.addActionListener(new SaveGameEvent(this));
        gameSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        file.addMenuItem(gameSave);
        file.addSeparator();
        language = new MenuItem();
        language.addActionListener(new ManageLanguageEvent(this));
//        if (Standalone.isStandalone()) {
//            file.add(language);
//        }
        file.addMenuItem(language);
        params = new MenuItem();
        params.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
        params.addActionListener(new ManageParamsEvent(this));
        file.addMenuItem(params);
        file.addSeparator();
        quit = new MenuItem();
        quit.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_ESCAPE));
        quit.addActionListener(new QuitEvent(this));
        file.addMenuItem(quit);
        bar_.add(file);
        dataGame = new Menu();
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
        dataWeb = new MenuItem();
        dataWeb.setAccelerator(KeyStroke.getKeyStroke(F_ONE));
        dataWeb.addActionListener(new ShowDataWebEvent(this));
        dataGame.addMenuItem(dataWeb);
        dataBattle = new MenuItem();
        dataBattle.setEnabledMenu(false);
        dataBattle.setAccelerator(KeyStroke.getKeyStroke(F_TWO));
        dataBattle.addActionListener(new ShowDataFightEvent(this));
        dataGame.addMenuItem(dataBattle);
        newGame = new MenuItem();
        newGame.addActionListener(new ProponeNewGameEvent(this));
        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        dataGame.addMenuItem(newGame);
        difficulty = new MenuItem();
        difficulty.setEnabledMenu(false);
        difficulty.addActionListener(new ManageDifficultyEvent(this));
        difficulty.setAccelerator(KeyStroke.getKeyStroke(F_THREE));
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
        if (battle != null) {
            while (isAliveThread()) {
                ThreadUtil.sleep(0);
            }
        }
        while (isPaintingScene()) {
            ThreadUtil.sleep(0);
        }
        if (!savedGame && facade.getGame() != null) {
            int choix_=saving();
            if(choix_==JOptionPane.CANCEL_OPTION) {
                return;
            }
            loadingConf.setLastSavedGame(DataBase.EMPTY_STRING);
            if(choix_==JOptionPane.YES_OPTION) {
                String file_ = fileDialogSave();
                if (!file_.isEmpty()) {
                    loadingConf.setLastSavedGame(file_);
                    save(file_);
                    dateLastSaved = Clock.getDateTimeText();
                    lastSavedGameDate.setText(StringUtil.simpleStringsFormat(messages.getVal(LAST_SAVED_GAME), dateLastSaved));
                    savedGame = true;
                }
            }
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingPokemon.getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE), DocumentWriterAikiCoreUtil.setLoadingGame(loadingConf));
        }
        String fileName_;
        if (_folder) {
            FolderOpenDialog.setFolderOpenDialog(this, getLanguageKey(), false);
            fileName_ = FolderOpenDialog.getStaticSelectedPath(getFolderOpenDialog());
        } else {
            fileName_ = fileDialogLoad(Resources.ZIPPED_DATA_EXT, true);
        }
        if (fileName_.isEmpty()) {
            return;
        }
        PerCent p_ = new PerCentIncr();
        loadFlag.set(true);
        LoadingThread load_ = new LoadingThread(this, fileName_,p_);
        LoadGame opening_ = new LoadGame(this,p_);
        CustComponent.newThread(load_).start();
        CustComponent.newThread(opening_).start();
    }

    public void loadGame() {
        if (!NumberUtil.eq(indexInGame, IndexConstants.INDEX_NOT_FOUND_ELT)) {
            return;
        }
        if (battle != null) {
            while (isAliveThread()) {
                ThreadUtil.sleep(0);
            }
        }
        while (isPaintingScene()) {
            ThreadUtil.sleep(0);
        }
        if (!savedGame && facade.getGame() != null) {
            int choix_=saving();
            if(choix_==JOptionPane.CANCEL_OPTION) {
                return;
            }
            loadingConf.setLastSavedGame(DataBase.EMPTY_STRING);
            if(choix_==JOptionPane.YES_OPTION) {
                String file_ = fileDialogSave();
                if (!file_.isEmpty()) {
                    loadingConf.setLastSavedGame(file_);
                    save(file_);
                    dateLastSaved = Clock.getDateTimeText();
//                    lastSavedGameDate.setText(MessageFormat.format(messages.getVal(LAST_SAVED_GAME), dateLastSaved));
                    lastSavedGameDate.setText(StringUtil.simpleStringsFormat(messages.getVal(LAST_SAVED_GAME), dateLastSaved));
                    savedGame = true;
                }
            }
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingPokemon.getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE), DocumentWriterAikiCoreUtil.setLoadingGame(loadingConf));
        }
        String fileName_ = fileDialogLoad(Resources.GAME_EXT, false);
        if (fileName_.isEmpty()) {
            return;
        }
        boolean error_ = false;
        DataBase db_ = facade.getData();
        Game game_ = load(fileName_, db_);
        if (game_ != null) {
            facade.load(game_);
            gameSave.setEnabledMenu(true);
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

    public static Game load(String _fileName,DataBase _data) {
        Game game_ = DocumentReaderAikiCoreUtil.getGame(StreamTextFile.contentsOfFile(_fileName));
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
        if (battle != null) {
            while (isAliveThread()) {
                ThreadUtil.sleep(0);
            }
        }
        save(fileName_);
        fileName_ = StringUtil.replaceBackSlash(fileName_);
        loadingConf.setLastSavedGame(fileName_);
        dateLastSaved = Clock.getDateTimeText();
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
        StreamTextFile.saveTextFile(_fileName, DocumentWriterAikiCoreUtil.setGame(game_));
    }

    public void manageLanguage() {
        if (!canChangeLanguageAll()) {
            GroupFrame.showDialogError(this);
            return;
        }
        LanguageDialog.setLanguageDialog(this, messages.getVal(CST_LANGUAGE));
        String langue_ = LanguageDialog.getStaticLanguage(getLanguageDialog());
        if(langue_ == null || langue_.isEmpty()) {
            return;
        }
        GroupFrame.changeStaticLanguage(langue_, getFrames());
        SoftApplicationCore.saveLanguage(LaunchingPokemon.getTempFolder(getFrames()), langue_);
    }

    public void manageParams() {
        if (battle != null) {
            while (isAliveThread()) {
                ThreadUtil.sleep(0);
            }
        }
        while (isPaintingScene()) {
            ThreadUtil.sleep(0);
        }
        SoftParams.setSoftParams(this, loadingConf);
        SoftParams.setParams(loadingConf, getSoftParams());
        if (SoftParams.isOk(getSoftParams())) {
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingPokemon.getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE), DocumentWriterAikiCoreUtil.setLoadingGame(loadingConf));
        }
    }

    public void proponeNewGame() {
        if (battle != null) {
            while (isAliveThread()) {
                ThreadUtil.sleep(0);
            }
        }
        while (isPaintingScene()) {
            ThreadUtil.sleep(0);
        }
        if (!NumberUtil.eq(indexInGame, IndexConstants.INDEX_NOT_FOUND_ELT)) {
            return;
        }
        addBeginGame();
        pack();
    }

    public void manageDifficulty() {
        if (preparedDiffThread == null || preparedDiffThread.isAlive() || preparedDiffTask == null) {
            return;
        }
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
        if (preparedDataWebThread == null || preparedDataWebThread.isAlive() || preparedDataWebTask == null) {
            return;
        }
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
        session_ = new RenderedPage(new ScrollPane(), getFrames());
        session_.setProcess(videoLoading.getVideo(getGenerator()));
        FrameHtmlData dialog_ = new FrameHtmlData(this, messages.getVal(TITLE_WEB), session_);
//        dialog_.initSession(facade.getData().getWebFiles(), successfulCompile, Resources.CONFIG_DATA, Resources.ACCESS_TO_DEFAULT_DATA);
        dialog_.initSessionLg(facade,preparedDataWebTask,facade.getLanguage());
        htmlDialogs.add(dialog_);
    }

    public void showGameProgressing() {
        if (preparedProgThread == null || preparedProgThread.isAlive() || preparedProgTask == null) {
            return;
        }
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
        Point point_=getLocation();
        SoftApplicationCore.saveCoords(LaunchingPokemon.getTempFolder(getFrames()),Resources.COORDS, point_.x,point_.y);
    }

    public void processLoad(String _fileName, PerCent _p) {
        StringMap<String> files_ = StreamFolderFile.getFiles(_fileName);
        DocumentReaderAikiCoreUtil.loadRomAndCheck(getGenerator(),facade,_fileName, files_,_p,loadFlag);
        if (!facade.isLoadedData()) {
            DocumentReaderAikiCoreUtil.loadResources(getGenerator(),facade,_p,loadFlag);
        }
        if (!loadFlag.get()) {
            return;
        }
        facade.clearGame();
        facade.initializePaginatorTranslations();
        inBattle = false;
        ThreadInvoker.invokeNow(new ReinitComponents(this));
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
        ThreadInvoker.invokeNow(new AfterLoadZip(this));
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
        getFolderLoad().setEnabledMenu(true);
        getZipLoad().setEnabledMenu(true);
        getGameLoad().setEnabledMenu(true);
        getNewGame().setEnabledMenu(true);
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
    public void initIndexInGame(boolean _first) {
        if (_first) {
            setIndexInGame(IndexConstants.FIRST_INDEX);
        }
    }

    @Override
    public void gearClient(Socket _newSocket) {
        Net.getSockets(getNet()).put(Net.getSockets(getNet()).size(), _newSocket);
        SendReceiveServer sendReceiveServer_=new SendReceiveServer(_newSocket,this, getNet());
        CustComponent.newThread(sendReceiveServer_).start();
        Net.getConnectionsServer(getNet()).put(Net.getSockets(getNet()).size()-1,sendReceiveServer_);
        IndexOfArriving index_ = new IndexOfArriving();
        index_.setIndex(Net.getSockets(getNet()).size()-1);
        Net.getReadyPlayers(getNet()).put(Net.getSockets(getNet()).size()-1,false);
        Net.getPlacesPlayers(getNet()).put(Net.getSockets(getNet()).size()-1,(byte)(Net.getSockets(getNet()).size()-1));
        Net.sendObject(_newSocket,index_);
    }

    @Override
    public void loop(Object _readObject, Socket _socket) {
        if (_readObject instanceof AttemptConnecting) {
            if (!StringUtil.quickEq(((AttemptConnecting)_readObject).getServerName(),Net.getPokemon())) {
                NewPlayer p_ = new NewPlayer();
                p_.setAcceptable(false);
                p_.setArriving(true);
                p_.setIndex(indexInGame);
                p_.setLanguage(getLanguageKey());
                p_.setPseudo(facade.getGame().getPlayer().getNickname());
                Net.sendObject(_socket,p_);
                return;
            }
        }
        if (_readObject instanceof IndexOfArriving) {
            NewPlayer p_ = new NewPlayer();
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
            Net.sendObject(_socket,p_);
            return;
        }
        if (_readObject == InitTrading.INSTANCE) {
            if (indexInGame == IndexConstants.FIRST_INDEX) {
                facade.initTrading();
                CheckCompatibility ch_ = new CheckCompatibility();
                ch_.setData(facade.getExchangeData());
                ch_.setIndex(indexInGame);
                ch_.setTeam(facade.getGame().getPlayer().getTeam());
                Net.sendObject(_socket,ch_);
                return;
            }
            if (indexInGame == IndexConstants.SECOND_INDEX) {
                facade.initTrading();
                CheckCompatibility ch_ = new CheckCompatibility();
                ch_.setData(facade.getExchangeData());
                ch_.setIndex(indexInGame);
                ch_.setTeam(facade.getGame().getPlayer().getTeam());
                Net.sendObject(_socket,ch_);
                return;
            }
            return;
        }
        if (_readObject instanceof NetPokemon) {
            NetPokemon net_ = (NetPokemon) _readObject;
            if (indexInGame == IndexConstants.SECOND_INDEX) {
                scenePanel.setNetworkPanel();
            }
            scenePanel.setTradable(net_.getTradablePokemon());
            pack();
            return;
        }
        if (_readObject instanceof PokemonPlayer) {
            PokemonPlayer pk_ = (PokemonPlayer) _readObject;
            facade.receivePokemonPlayer(pk_);
            scenePanel.seeNetPokemonDetail();
            return;
        }
        if (_readObject == Ok.INSTANCE) {
            facade.applyTrading();
            ByteTreeMap< PokemonPlayer> tree_ = facade.getExchangeData().getTeam(facade.getGame().getPlayer().getTeam());
            scenePanel.setTradableAfterTrading(tree_);
            pack();
            return;
        }
    }

    @Override
    public void quitNetwork(Exiting _exit, Socket _socket) {
        exitFromTrading();
        resetIndexInGame();
        closeConnexion(_socket);
        if (_exit != null && _exit.isClosing()) {
            basicDispose();
            return;
        }
        pack();
        if (_exit != null && _exit.isForced() && !_exit.isBusy()) {
            if (_exit.isTooManyPlayers()) {
                ConfirmDialog.showMessage(this, getTooManyString(), getTooManyString(), getLanguageKey(), JOptionPane.ERROR_MESSAGE);
                //JOptionPane.showMessageDialog(window, MainWindow.getTooManyString(), MainWindow.getTooManyString(), JOptionPane.INFORMATION_MESSAGE);
            } else {
                ConfirmDialog.showMessage(this, getNoTradeString(), getNoTradeString(), getLanguageKey(), JOptionPane.ERROR_MESSAGE);
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
        return ConfirmDialog.getAnswer(this,_message,_titre, getLanguageKey(),JOptionPane.YES_NO_CANCEL_OPTION);
    }

    /**Sauvegarder une partie dans un fichier*/
    private String fileDialogSave() {
        boolean saveConfig_ = false;
        if (loadingConf.isSaveHomeFolder()) {
            saveConfig_ = true;
            FileSaveDialog.setFileSaveDialogByFrame(this, getLanguageKey(), true, Resources.GAME_EXT, getFrames().getHomePath(), getFrames().getHomePath(), Resources.EXCLUDED);
        } else {
            FileSaveDialog.setFileSaveDialogByFrame(this, getLanguageKey(), true, Resources.GAME_EXT, DataBase.EMPTY_STRING, getFrames().getHomePath(), Resources.EXCLUDED);
        }
        String path_ = FileSaveDialog.getStaticSelectedPath(getFileSaveDialog());
        if (path_ == null) {
            path_ = DataBase.EMPTY_STRING;
        } else if (saveConfig_) {
            loadingConf.setLastSavedGame(path_);
            loadingConf.setLastRom(facade.getZipName());
            String configPath_ = StringUtil.replaceExtension(path_, Resources.GAME_EXT, Resources.CONF_EXT);
            //String configPath_ = path_.replaceAll(StringList.quote(Resources.GAME_EXT)+StringList.END_REG_EXP, Resources.CONF_EXT);
            StreamTextFile.saveTextFile(configPath_, DocumentWriterAikiCoreUtil.setLoadingGame(loadingConf));
            //configPath_ +=
        }
        return path_;
    }

    /**Sauvegarder une partie dans un fichier*/
    private String fileDialogLoad(String _ext, boolean _zipFile) {
        if (_zipFile) {
            if (loadingConf != null && loadingConf.isLoadHomeFolder()) {
                FileOpenDialog.setFileOpenDialog(this,getLanguageKey(),true, _ext, getFrames().getHomePath(), Resources.EXCLUDED);
            } else {
                FileOpenDialog.setFileOpenDialog(this,getLanguageKey(),true, _ext, StreamFolderFile.getCurrentPath(), Resources.EXCLUDED);
            }
//            FileOpenDialog.setFileOpenDialog(this,Constants.getLanguage(),true, _ext, SoftApplication.getFolderJarPath(), Resources.EXCLUDED);
        } else {
            if (loadingConf.isSaveHomeFolder()) {
                FileOpenDialog.setFileOpenDialog(this,getLanguageKey(),true, _ext, getFrames().getHomePath(), Resources.EXCLUDED);
            } else {
                FileOpenDialog.setFileOpenDialog(this,getLanguageKey(),true, _ext, DataBase.EMPTY_STRING, Resources.EXCLUDED);
            }
        }
        String path_=FileOpenDialog.getStaticSelectedPath(getFileOpenDialog());
        if (path_ == null) {
            path_ = DataBase.EMPTY_STRING;
        }
        return path_;
    }

    public void setFight(boolean _animate, boolean _wild) {
        difficulty.setEnabledMenu(false);
        facade.setChangeToFightScene(false);
        enabledMove = false;
        battle.setVisibleFrontBattle(true);
        scenePanel.getComponent().setVisible(false);
//        mainPanel.remove(scenePanel);
        //initBattle();
        battle.enableAnimation(loadingConf.isEnableAnimation());
        battle.initializeFight(false);
        if (!_animate) {
            battle.repaintLabel();
        }
//        mainPanel.add(battle, CustList.FIRST_INDEX);
        inBattle = true;
        dataBattle.setEnabledMenu(true);
        pack();
        if (loadingConf.isEnableAnimation() && _animate) {
            if (_wild) {
                fightIntroThread = new FightWildIntroThread(facade, battle.getBattle());
            } else {
                fightIntroThread = new FightTrainerIntroThread(facade, battle.getBattle());
            }
            fightIntroThreadLau = CustComponent.newThread(fightIntroThread);
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
        difficulty.setEnabledMenu(true);
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
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingPokemon.getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE), DocumentWriterAikiCoreUtil.setLoadingGame(loadingConf));
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
        ConfirmDialog.showMessage(this, _fileName, messages.getVal(ERROR_LOADING), getLanguageKey(), JOptionPane.ERROR_MESSAGE);
        return true;
    }

    public void showSuccessfulMessageDialogThenLoadHelp(String _fileName) {
        ConfirmDialog.showMessage(this, _fileName, messages.getVal(SUCCESSFUL_LOADING), getLanguageKey(), JOptionPane.INFORMATION_MESSAGE);
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
        ThreadInvoker.invokeNow(new ChangeEnabledDifficulty(difficulty, !_paintingScene));
        ThreadInvoker.invokeNow(new PaintingScene(scenePanel, _paintingScene));
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

    public ProgressingDialogPokemon getDialog() {
        return dialog;
    }

    public MenuItem getFolderLoad() {
        return folderLoad;
    }

    public MenuItem getZipLoad() {
        return zipLoad;
    }

    public MenuItem getGameLoad() {
        return gameLoad;
    }

    public MenuItem getNewGame() {
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
    public String setObject(Object _object) {
        return DocumentWriterAikiMultiUtil.setObject(_object);
    }

    @Override
    public Object getObject(String _object) {
        return DocumentReaderAikiMultiUtil.getObject(_object);
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

    public Thread getPreparedDataWebThread() {
        return preparedDataWebThread;
    }

    public void setPreparedDataWebThread(Thread _preparedDataWebThread) {
        preparedDataWebThread = _preparedDataWebThread;
    }

    public Thread getPreparedFightThread() {
        return preparedFightThread;
    }

    public void setPreparedFightThread(Thread _preparedFightThread) {
        preparedFightThread = _preparedFightThread;
    }

    public Thread getPreparedPkThread() {
        return preparedPkThread;
    }

    public void setPreparedPkThread(Thread _preparedPkThread) {
        preparedPkThread = _preparedPkThread;
    }

    public Thread getPreparedPkNetThread() {
        return preparedPkNetThread;
    }

    public void setPreparedPkNetThread(Thread _preparedPkThread) {
        preparedPkNetThread = _preparedPkThread;
    }

    public Thread getPreparedDiffThread() {
        return preparedDiffThread;
    }

    public void setPreparedDiffThread(Thread _preparedDiffThread) {
        preparedDiffThread = _preparedDiffThread;
    }

    public Thread getPreparedProgThread() {
        return preparedProgThread;
    }

    public void setPreparedProgThread(Thread _preparedProgThread) {
        preparedProgThread = _preparedProgThread;
    }

    public Net getNet() {
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

    public SoftParams getSoftParams() {
        return softParams;
    }

    public DialogServer getDialogServer() {
        return dialogServer;
    }

    public AikiFactory getAikiFactory() {
        return aikiFactory;
    }
}
