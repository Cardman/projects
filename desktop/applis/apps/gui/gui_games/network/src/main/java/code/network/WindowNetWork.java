package code.network;

import aiki.db.*;
import aiki.facade.*;
import aiki.gui.*;
import aiki.gui.components.walk.*;
import aiki.gui.dialogs.*;
import aiki.gui.events.*;
import aiki.main.*;
import aiki.sml.*;
import cards.belote.*;
import cards.enumerations.*;
import cards.facade.*;
import cards.facade.enumerations.*;
import cards.gui.*;
import cards.gui.containers.*;
import cards.gui.dialogs.*;
import cards.gui.events.*;
import cards.gui.menus.*;
import cards.network.belote.actions.*;
import cards.network.common.*;
import cards.network.common.before.*;
import cards.network.president.actions.*;
import cards.network.tarot.actions.*;
import cards.network.threads.*;
import cards.president.*;
import cards.tarot.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.files.*;
import code.gui.images.*;
import code.gui.initialize.*;
import code.netw.*;
import code.scripts.messages.cards.*;
import code.sml.util.*;
import code.stream.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;
import aiki.map.pokemon.*;
import aiki.network.*;
import aiki.network.stream.*;

public final class WindowNetWork extends NetGroupFrame implements WindowCardsInt, WindowAikiInt {

//    public static final String TOO_GAME = "tooGame";

//    public static final String CST_LAUNCHING = "launching";

//    public static final String CST_TIMING = "timing";

//    public static final String CAN_PLAY = "canPlay";

//    public static final String PASS_TRICK = "passTrick";

//    public static final String NO_PLAY_NOW = "noPlayNow";

//    public static final String ERROR_LOADING = "errorLoading";
//    public static final String ALREADY_PLAYED = "alreadyPlayed";
//
//    public static final String ALONE_TAKER = "aloneTaker";
//
//    public static final String ASK_SLAM = "askSlam";
//
//    public static final String ASK_SLAM_TITLE = "askSlamTitle";
//
//    public static final String BEGIN_DEMO = "beginDemo";
//
//    public static final String BEGIN_PLAY_CARDS = "beginPlayCards";
//
//    public static final String BONUS_WIN = "bonusWin";
//
//    public static final String CALLED_PLAYER = "calledPlayer";
//
//    public static final String CALLED_PLAYER_WARNING = "calledPlayerWarning";

//    public static final String CANT_BID = "cantBid";

//    public static final String CANT_BID_TITLE = "cantBidTitle";

//    public static final String CANT_DECLARE_DETAIL = "cantDeclareDetail";

//    public static final String CANT_DECLARE_TITLE = "cantDeclareTitle";

//    public static final String CANT_DISCARD = "cantDiscard";

//    public static final String CANT_PLAY = "cantPlay";

//    public static final String CANT_PLAY_CARD = "cantPlayCard";

//    public static final String CANT_PLAY_CARD_TITLE = "cantPlayCardTitle";

//    public static final String CONSULT_BELOTE_BID = "consultBeloteBid";
//
//    public static final String CONSULT_BELOTE_BID_POINTS = "consultBeloteBidPoints";
//
//    public static final String CONSULT_BELOTE_BID_SUIT = "consultBeloteBidSuit";
//
//    public static final String CONSULT_BELOTE_BID_SUIT_POINTS = "consultBeloteBidSuitPoints";
//
//    public static final String CONSULT_BELOTE_PLAYER = "consultBelotePlayer";
//
//    public static final String CONSULT_PRESIDENT_PLAYER = "consultPresidentPlayer";
//
//    public static final String CONSULT_PRESIDENT_PASS = "consultPresidentPass";
//
//    public static final String CONSULT_PRESIDENT_GIVE = "consultPresidentGive";
//
//    public static final String CONSULT_TAROT_BID = "consultTarotBid";
//
//    public static final String CONSULT_TAROT_CALL = "consultTarotCall";
//
//    public static final String CONSULT_TAROT_DISCARD = "consultTarotDiscard";
//
//    public static final String CONSULT_TAROT_PLAYER = "consultTarotPlayer";
//
//    public static final String CONSULT_TITLE = "consultTitle";
//
//    public static final String DEAL_REMAIN_CARDS = "dealRemainCards";
//
//    public static final String DEAL_SET_CARDS = "dealSetCards";
//
//    public static final String DECLARE_BID = "declareBid";
//
//    public static final String DECLARING_SLAM = "declaringSlam";
//
//    public static final String DECLARING_SLAM_DEMO = "declaringSlamDemo";
//
//    public static final String DECLARING_SLAM_DEMO_DISCARD = "declaringSlamDemoDiscard";
//
//    public static final String DEMO_ACTION = "demoAction";
//
//    public static final String DEMO_ACTION_TWO = "demoActionTwo";

//    public static final String DETAIL_RESULTS_PAGE = "detailResultsPage";

//    public static final String DISCARD_CARDS = "discardCards";
//
//    public static final String END_DEAL = "endDeal";
//
//    public static final String END_TRICK = "endTrick";

//    public static final String GO_CARD_GAME = "goCardGame";

//    public static final String HANDS_TRICKS = "handsTricks";

//    public static final String HANDS_TRICKS_BELOTE = "handsTricksBelote";

//    public static final String HANDS_TRICKS_PRESIDENT = "handsTricksPresident";

//    public static final String HANDS_TRICKS_TAROT = "handsTricksTarot";

//    public static final String HAS_TO_DISCARD = "hasToDiscard";

//    public static final String HAVE_TO_PLAY = "haveToPlay";

//    public static final String HELP_GAME = "helpGame";

//    public static final String HELP_GO_MENU = "helpGoMenu";

//    public static final String KEEP_PLAYING_DEAL = "keepPlayingDeal";
//
//    public static final String KEEP_PLAYING_EDITED_DEAL = "keepPlayingEditedDeal";
//
//    public static final String LOADING = "loading";
//
//    public static final String NEXT_TRICK = "nextTrick";
//
//    public static final String NO_BID = "noBid";

//    public static final String OK = "ok";

//    public static final String PARTNERS_TAKER = "partnersTaker";

//    public static final String PLACE = "place";

//    public static final String PLAY_BELOTE = "playBelote";

//    public static final String PLAY_CARD_FIRST = "playCardFirst";
//
//    public static final String PLAY_CARD_THEN = "playCardThen";

//    public static final String PLAY_PRESIDENT = "playPresident";

//    public static final String PLAY_TAROT = "playTarot";

//    public static final String PLAYER_HAVING_TO_PLAY = "playerHavingToPlay";

//    public static final String PLAYERS_SHOW_DOG = "playersShowDog";

//    public static final String READY = "ready";

//    public static final String REASON = "reason";

//    public static final String REMOVE_TRUMPS_HANDFUL = "removeTrumpsHandful";

//    public static final String REPLAY_DEAL = "replayDeal";

//    public static final String RESULTS_PAGE = "resultsPage";

//    public static final String SCORES_EVOLUTION = "scoresEvolution";
//
//    public static final String SCORES_EVOLUTION_DETAIL = "scoresEvolutionDetail";
//
//    public static final String SEE_DOG = "seeDog";

//    public static final String SELECT_RULES = "selectRules";

//    public static final String SHOWN_DOG = "shownDog";

//    public static final String SLAM = "slam";

//    public static final String SMALL_ALONE = "smallAlone";
//
//    public static final String SMALL_ALONE_TEXT = "smallAloneText";
//
//    public static final String STOP = "stop";
//
//    public static final String STOP_DEMO = "stopDemo";

//    public static final String TAKE_CARDS = "takeCards";

//    public static final String TAKE_DOG = "takeDog";
//
//    public static final String TAKE_TOP_CARD = "takeTopCard";
//
//    public static final String TAKER_CALL = "takerCall";
//
//    public static final String TAKER_CALL_WARNING = "takerCallWarning";
//
//    public static final String TEAM_TAKER = "teamTaker";
//
//    public static final String TRICK_WINNER = "trickWinner";
//
//    public static final String WAIT_TURN = "waitTurn";

//    public static final String RECEIVED_CARDS = "receivedCards";

//    public static final String GIVEN_CARDS = "givenCards";
//    public static final String APPS_NETWORK = "network";

//    static final String ACCESSIBLE = "accessible";

//    static final String BUG = "bug";

//    static final String CST_CHANGE = "change";

//    static final String CST_CONSULTING = "consulting";

//    static final String CST_DEAL = "deal";

//    static final String CST_DEMO = "demo";

//    static final String CST_DISPLAYING = "displaying";

//    static final String CST_EDIT = "edit";

//    static final String CST_EXIT = "exit";

//    static final String CST_FILE = "file";

//    static final String CST_FILE_NOT_LOADED = "fileNotLoaded";

//    static final String CST_FILE_NOT_LOADED_TILE = "fileNotLoadedTile";

//    static final String CST_GENERAL_HELP = "generalHelp";

//    static final String CST_GO_HELP_MENU = "goHelpMenu";

//    static final String CST_HELP = "help";

//    static final String CST_INTERACT = "interact";

//    static final String CST_LANGUAGE = "language";

//    static final String CST_LOAD = "load";

//    static final String CST_MAIN_MENU = "mainMenu";

//    static final String CST_MULTI_MODE = "multiMode";

//    static final String CST_MULTI_STOP = "multiStop";

//    static final String CST_PARAMETERS = "parameters";

    //static final String PARAMETRES = "parametres";

//    static final String CST_PAUSE = "pause";

//    static final String CST_PLAYERS = "players";

//    static final String CST_SAVE = "save";

//    static final String CST_SAVING = "saving";

//    static final String CST_SAVING_TITLE = "savingTitle";

//    static final String CST_SINGLE_MODE = "singleMode";

//    static final String CST_TEAMS = "teams";

//    static final String CST_TRAINING = "training";

//    static final String CST_TRICKS_HANDS = "tricksHands";

//    static final String CST_WELCOME = "welcome";

//    private static final String DIALOG_ACCESS = "cards.gui.mainwindow";

//    private static final String TOO_MANY = "tooMany";

//    private static final String NO_TRADE = "noTrade";
//    private static final String UNKNOWN_HOST = "unknownHost";

//    private static final String NOT_CONNECTED = "notConnected";

//    private static final String F_ONE = "F1";
//    private static final String F_TWO = "F2";
//    private static final String F_THREE = "F3";
//    private static final String F_FOUR = "F4";
//    private static final String F_FIVE = "F5";
//    private static final String F_SIX = "F6";
//    private static final String EMPTY_STRING = "";
//    private static final String LAST_SAVED_GAME = "lastSavedGame";
//    private static final String FOLDER_LOAD = "folderLoad";
//    private static final String ZIP_LOAD = "zipLoad";
//    private static final String GAME_LOAD = "gameLoad";
//    private static final String GAME_SAVE = "gameSave";

//    private static final char LINE_RETURN = '\n';

//    private final CustList<FrameGeneralHelp> helpFrames = new CustList<FrameGeneralHelp>();

//    private ContainerGame containerGame;
    private final AbstractAtomicBooleanCore loadFlag;
//    private final Clock clock;

//    private final AbsPlainLabel lastSavedGameDate;

//    private String dateLastSaved = EMPTY_STRING;

    /**Parametres de lancement, de jouerie*/
//    private SoftParams parametres=new SoftParams();
    /**
     des pseudonymes*/
//    private Nicknames pseudosJoueurs;
//    private RulesBelote reglesBelote=new RulesBelote();
//    private DisplayingBelote displayingBelote = new DisplayingBelote();
//    private RulesPresident reglesPresident=new RulesPresident();
//    private DisplayingPresident displayingPresident = new DisplayingPresident();
//    private RulesTarot reglesTarot=new RulesTarot();
//    private DisplayingTarot displayingTarot = new DisplayingTarot();
    /**Est vrai si et seulement si une partie vient d'etre sauvegardee*/
//    private boolean partieSauvegardee;

//    private boolean single = true;
//    private boolean forceBye;

    //file menu

    private EnabledMenu file;
//    private AbsMenuItem load;
//    private AbsMenuItem save;
//    private EnabledMenu change;
    private EnabledMenu exit;

    //deal menu

    private EnabledMenu deal;
//    private AbsMenuItem consulting;
//    private AbsCheckBoxMenuItem pause;
//    private AbsMenuItem helpGame;
    private EnabledMenu tricksHands;
    private EnabledMenu teams;
//    private AbsMenu edit;
//    private final IdMap<GameEnum,AbsMenuItem> editGames = new IdMap<GameEnum,AbsMenuItem>();
//    private AbsMenu demo;
//    private final IdMap<GameEnum,AbsMenuItem> demoGames = new IdMap<GameEnum,AbsMenuItem>();
//    private AbsMenu training;
//    private final IdMap<ChoiceTarot,AbsMenuItem> trainingTarot = new IdMap<ChoiceTarot,AbsMenuItem>();
    private EnabledMenu multiStop;

    //parameters menu

    private EnabledMenu parameters;
//    private final IdMap<GameEnum,AbsMenuItem> rulesGames = new IdMap<GameEnum,AbsMenuItem>();
//    private AbsMenuItem players;
//    private AbsMenuItem launching;
//    private AbsMenuItem timing;
//    private AbsMenuItem interact;
//    private AbsMenuItem language;
//    private AbsMenu displaying;
//    private final IdMap<GameEnum,AbsMenuItem> displayingGames = new IdMap<GameEnum,AbsMenuItem>();

    //parameters help

//    private AbsMenu help;
//    private AbsMenuItem generalHelp;

    //labels at main menu

//    private AbsPlainLabel welcomeLabel;
//    private AbsPlainButton singleModeButton;
    private AbsButton multiModeButton;
    private BasicClient basicClient;
//    private AbsPlainLabel goHelpMenu;
    private final Net net;

//    private final StringMap<StringMap<PreparedPagesCards>> preparedBelote;
//    private final StringMap<StringMap<PreparedPagesCards>> preparedPresident;
//    private final StringMap<StringMap<PreparedPagesCards>> preparedTarot;
    //private final boolean standalone;
//    private HelpInitializer helpInitializerTask;
//    private final DialogDisplayingBelote dialogDisplayingBelote;
//    private final DialogDisplayingTarot dialogDisplayingTarot;
//    private final DialogDisplayingPresident dialogDisplayingPresident;
//    private final DialogHelpBelote dialogHelpBelote;
//    private final DialogHelpPresident dialogHelpPresident;
//    private final DialogHelpTarot dialogHelpTarot;
//    private final DialogRulesBelote dialogRulesBelote;
//    private final DialogRulesPresident dialogRulesPresident;
//    private final DialogRulesTarot dialogRulesTarot;
//    private final DialogTricksBelote dialogTricksBelote;
//    private final DialogTricksPresident dialogTricksPresident;
//    private final DialogTricksTarot dialogTricksTarot;
//    private final EditorBelote editorBelote;
//    private final EditorPresident editorPresident;
//    private final EditorTarot editorTarot;
//    private final DialogTeamsPlayers dialogTeamsPlayers;
//    private final DialogNicknames dialogNicknames;
//    private final DialogSoft dialogSoft;
//    private final CardFactories cardFactories;
//    private ResultCardsServerInteract resultCardsServerInteract;
//    private StringMap<StringMap<String>> images = new StringMap<StringMap<String>>();
    private final NetAiki netAiki = new NetAiki();
//    private StringMap<String> messagesAiki = new StringMap<String>();
//    private final FacadeGame facade;
    private final ScenePanelMulti scenePanel;
    private final WindowAikiCore aiki;
//    private AbsMenu file;

//    private AbsMenuItem zipLoad;
//
//    private AbsMenuItem folderLoad;
//
//    private AbsMenuItem gameLoad;
//
//    private AbsMenuItem gameSave;
    private final WindowCardsCore netg;
//    private boolean savedGame;
    private int indexInGame = IndexConstants.INDEX_NOT_FOUND_ELT;
    private boolean cards;
    private final LanguagesButtonsPair buttonClick;
    private final AbstractAtomicBoolean modal;
    private final ProgressingDialog dialog;
    private final LanguageDialogButtons languageDialogButtons;
//    private final AbsActionListenerAct guardRender;
    private final DialogServerContent dialogServerContent;
    private final FileSaveFrame fileSaveFrame;
    private final FileOpenFrame fileOpenRomFrame;
    private final FolderOpenFrame fileOpenFolderFrame;
//    private final FileOpenSaveFrame fileOpenSaveFrame;
//    private final FolderOpenSaveFrame folderOpenSaveFrame;
    private final ReportingFrame errorsFile = ReportingFrame.newInstance(getFrames());
    public WindowNetWork(CardGamesStream _nicknames, AbstractProgramInfos _list,
                         AikiFactory _aikiFactory, IntArtCardGames _ia, LanguagesButtonsPair _pair) {
        super(_list);
        buttonClick = _pair;
        dialogServerContent = new DialogServerContent(this, _list);
//        guardRender = new AlwaysActionListenerAct();
        languageDialogButtons = new LanguageDialogButtons(_list,_pair.getLgMenu(), new AlwaysActionListenerAct());
        modal = _list.getThreadFactory().newAtomicBoolean();
        dialog = new ProgressingDialog(getFrames(),modal);
        fileSaveFrame = new FileSaveFrame(_list, modal);
        fileOpenRomFrame = new FileOpenFrame(_list, modal);
        fileOpenFolderFrame = new FolderOpenFrame(_list, modal);
//        fileOpenSaveFrame = new FileOpenSaveFrame(_list, modal);
//        folderOpenSaveFrame = new FolderOpenSaveFrame(_list, modal);
        net = new Net(_ia);
        aiki = new WindowAikiCore(_aikiFactory, _list, errorsFile);
        netg = new WindowCardsCore(this,_nicknames, _list, _ia,modal,_pair.getLgMenu());
        loadFlag = _list.getThreadFactory().newAtomicBoolean();
//        facade = new FacadeGame();
//        facade.setLanguages(_list.getLanguages());
//        facade.setDisplayLanguages(_list.getDisplayLanguages());
//        facade.setSimplyLanguage(_lg);
        scenePanel = new ScenePanelMulti(this, aiki.getFacade());
//        dialogDisplayingBelote = new DialogDisplayingBelote(_list);
//        dialogDisplayingTarot = new DialogDisplayingTarot(_list);
//        dialogDisplayingPresident = new DialogDisplayingPresident(_list);
//        dialogHelpBelote = new DialogHelpBelote(_list);
//        dialogHelpPresident = new DialogHelpPresident(_list);
//        dialogHelpTarot = new DialogHelpTarot(_list);
//        dialogRulesBelote = new DialogRulesBelote(_list);
//        dialogRulesPresident = new DialogRulesPresident(_list);
//        dialogRulesTarot = new DialogRulesTarot(_list);
//        dialogTricksBelote = new DialogTricksBelote(_list);
//        dialogTricksPresident = new DialogTricksPresident(_list);
//        dialogTricksTarot = new DialogTricksTarot(_list);
//        editorBelote = new EditorBelote(_list);
//        editorPresident = new EditorPresident(_list);
//        editorTarot = new EditorTarot(_list);
//        dialogTeamsPlayers = new DialogTeamsPlayers(_list);
//        dialogNicknames = new DialogNicknames(_list);
//        dialogSoft = new DialogSoft(_list);
//        cardFactories = _cardFactories;
//        preparedBelote = _belote;
//        preparedPresident = _president;
//        preparedTarot = _tarot;
//        pseudosJoueurs=new Nicknames(getLanguageKey());
//        setAccessFile(DIALOG_ACCESS);
        setFocusable(true);
        requestFocus();
        setFocusableWindowState(true);
//        setImageIconFrame(LaunchingCards.getIcon(getImageFactory()));
//        clock = new Clock(_list);
//        lastSavedGameDate = getCompoFactory().newPlainLabel("");
//        reglesBelote = DocumentReaderBeloteUtil.getRulesBelote(StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.RULES_BELOTE),getFileCoreStream(),getStreams()));
//        if (!reglesBelote.isValidRules()) {
//            reglesBelote = new RulesBelote();
//            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.RULES_BELOTE), DocumentWriterBeloteUtil.setRulesBelote(reglesBelote),getStreams());
//        }
//        displayingBelote = DocumentReaderBeloteUtil.getDisplayingBelote(StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.DISPLAY_BELOTE),getFileCoreStream(),getStreams()));
//        displayingBelote.validate();
//        reglesPresident = DocumentReaderPresidentUtil.getRulesPresident(StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.RULES_PRESIDENT),getFileCoreStream(),getStreams()));
//        if (!reglesPresident.isValidRules()) {
//            reglesPresident = new RulesPresident();
//            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.RULES_PRESIDENT), DocumentWriterPresidentUtil.setRulesPresident(reglesPresident),getStreams());
//        }
//        displayingPresident = DocumentReaderPresidentUtil.getDisplayingPresident(StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.DISPLAY_PRESIDENT),getFileCoreStream(),getStreams()));
//        displayingPresident.validate();
//        reglesTarot = DocumentReaderTarotUtil.getRulesTarot(StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.RULES_TAROT),getFileCoreStream(),getStreams()));
//        if (!reglesTarot.isValidRules()) {
//            reglesTarot = new RulesTarot();
//            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.RULES_TAROT), DocumentWriterTarotUtil.setRulesTarot(reglesTarot),getStreams());
//        }
//        displayingTarot = DocumentReaderTarotUtil.getDisplayingTarot(StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.DISPLAY_TAROT),getFileCoreStream(),getStreams()));
//        displayingTarot.validate();
//        parametres = DocumentReaderCardsUnionUtil.getSoftParams(StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.PARAMS),getFileCoreStream(),getStreams()));
//        parametres.setDelays();
//        parametres.setLocale(_locale);
        aiki.getLastSavedGameDate().setText(StringUtil.simpleStringsFormat(MessagesPkGame.getWindowPkContentTr(MessagesPkGame.getAppliTr(getFrames().currentLg())).getMapping().getVal(MessagesRenderWindowPk.LAST_SAVED_GAME), aiki.getDateLastSaved()));

//        pseudosJoueurs = DocumentReaderCardsUnionUtil.getNicknames(getLanguageKey(),StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.PLAYERS),getFileCoreStream(),getStreams()));
//        if (!pseudosJoueurs.isValidNicknames()) {
//            pseudosJoueurs = new Nicknames(getLanguageKey());
//            pseudosJoueurs.sauvegarder(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.PLAYERS),getStreams());
//        }
        /*Parametre de lancement*/
        initMenus();
        multiModeButton = getCompoFactory().newPlainButton(getMenusMessages().getVal(MessagesGuiCards.CST_MULTI_MODE));
//        translate();

        menuPrincipal();
//        if(parametres.getLancement().isEmpty()) {
//            menuPrincipal();
//        } else {
//            MenuItemUtils.setEnabledMenu(getTricksHands(),false);
//            MenuItemUtils.setEnabledMenu(getTeams(),false);
////            MenuItemUtils.setEnabledMenu(getMultiStop(),false);
//            MenuItemUtils.setEnabledMenu(getLoad(),true);
//            MenuItemUtils.setEnabledMenu(getEdit(),true);
//            MenuItemUtils.setEnabledMenu(getTraining(),true);
//            if(parametres.getLancement().first()==GameEnum.BELOTE) {
//                containerGame = new ContainerSingleBelote(this);
//            } else if(parametres.getLancement().first()==GameEnum.PRESIDENT) {
//                containerGame = new ContainerSinglePresident(this);
//            } else {
//                containerGame = new ContainerSingleTarot(this);
//            }
//            MenuItemUtils.setEnabledMenu(change,true);
//            ((ContainerSingle)containerGame).modify();
//        }
//        exitMode(_list);
        //        setDefaultCloseOperation(GuiConstants.EXIT_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
    }
//    public static StringMap<String> getMessagesFromLocaleClass(String _folder, String _loc, String _class) {
//        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(_folder, _loc, _class);
//        String loadedResourcesMessages_ = MessGuiCardsGr.ms().getVal(fileName_);
//        return ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
//    }
    public String getTooManyString() {
        StringMap<String> mapping_ = MessagesNetWork.getMessages(MessagesNetWork.getAppliTr(getFrames().currentLg())).getMapping();
        return mapping_.getVal(MessagesNetWork.TOO_MANY);
    }

    private void ajouterBoutonPrincipal(String _texte,GameEnum _nomJeu,AbsPanel _container) {
        AbsButton bouton_=getCompoFactory().newPlainButton(_texte);
//        bouton_.addMouseListener(new EcouteurBoutonPrincipal(_nomJeu));
        bouton_.addActionListener(new ListenerBeginGameMulti(_nomJeu, this));
        _container.add(bouton_);
    }
//    public void clearHelpDialogs() {
//        helpFrames.clear();
//    }
    public Nicknames getPseudosJoueurs() {
        return new Nicknames(netg.getFacadeCards().getPseudosJoueurs());
    }
    public RulesBelote getReglesBelote() {
        return new RulesBelote(netg.getFacadeCards().getReglesBelote());
    }

    public DisplayingBelote getDisplayingBelote() {
        return new DisplayingBelote(netg.getFacadeCards().getDisplayingBelote());
    }
    public RulesPresident getReglesPresident() {
        return new RulesPresident(netg.getFacadeCards().getReglesPresident());
    }

    public DisplayingPresident getDisplayingPresident() {
        return new DisplayingPresident(netg.getFacadeCards().getDisplayingPresident());
    }

    public RulesTarot getReglesTarot() {
        return new RulesTarot(netg.getFacadeCards().getReglesTarot());
    }

    public DisplayingTarot getDisplayingTarot() {
        return new DisplayingTarot(netg.getFacadeCards().getDisplayingTarot());
    }

    //    @Override
//    public void pack() {
//        if (isVisible()) {
//            super.pack();
//        }
//    }
    public Clock getClock() {
        return netg.getClock();
    }

    @Override
    public StringMap<String> getMenusMessages() {
        return MessagesCardGames.getMenus(MessagesCardGames.getAppliTr(getFrames().currentLg())).getMapping();
    }
    public AbsPlainLabel getLastSavedGameDate() {
        return aiki.getLastSavedGameDate();
    }

    /**server and client
     Method allowing the client to send a serializable object by its socket
     @param _serializable the serializable object to be sent
     */
    public boolean sendObject(ChoosenPlace _serializable) {
        return trySendString(Net.exportServerChosenPlace(_serializable.getIndex(),_serializable.getPlace(),_serializable.getPlacesPlayers()),getSocket());
    }
    public boolean sendObject(ReadyCards _serializable) {
        return trySendString(Net.exportServerReady(_serializable.getIndex(),_serializable.getContent().isReady()),getSocket());
    }
//    public boolean sendObjectTakeCard() {
//        return trySendString(DocumentWriterCardsMultiUtil.takeCard(), getSocket());
//    }
    public boolean sendObjectPlayGame() {
        return trySendString(Net.exportPlayGame(),getSocket());
    }
    public boolean sendObject(BiddingBelote _serializable) {
        return trySendString(Net.exportServerBiddingBelote(_serializable),getSocket());
    }
    public boolean sendObject(DiscardedCardsPresident _serializable) {
        return trySendString(Net.exportDiscardedCardsPresident(_serializable.getPlace(),_serializable.getDiscarded()),getSocket());
    }
    public boolean sendObject(BiddingTarot _serializable) {
        return trySendString(Net.exportServerBiddingTarot(_serializable),getSocket());
    }
    public boolean sendObject(PlayingCardBelote _serializable) {
        return trySendString(Net.exportServerPlayingBelote(_serializable),getSocket());
    }
    public boolean sendObject(PlayingCardPresident _serializable) {
        return trySendString(Net.exportServerPlayingPresident(_serializable),getSocket());
    }
    public boolean sendObject(PlayingCardTarot _serializable) {
        return trySendString(Net.exportServerPlayingTarot(_serializable),getSocket());
    }
    public boolean sendObject(QuitCards _serializable) {
        return trySendString(Net.exportQuitting(_serializable),getSocket());
    }
    public boolean sendObjectBelote(DiscardedCardBelote _serializable) {
        return trySendString(Net.exportDiscardedCardBelote(_serializable),getSocket());
    }
    public boolean sendObjectTarot(DiscardedCardTarot _serializable) {
        return trySendString(Net.exportDiscardedCardTarot(_serializable),getSocket());
    }
    public boolean sendObject(RulesBelote _serializable) {
        return trySendString(Net.exportServerRulesBelote(_serializable).toString(),getSocket());
    }
    public boolean sendObject(RulesPresident _serializable) {
        return trySendString(Net.exportServerRulesPresident(_serializable).toString(),getSocket());
    }
    public boolean sendObject(RulesTarot _serializable) {
        return trySendString(Net.exportServerRulesTarot(_serializable).toString(),getSocket());
    }
    @Override
    public void quit() {
        GuiBaseUtil.trEx(this, getFrames());
        LanguageDialogButtons.enable(getButtonClick().getMainButton(),true);
//        LanguageComponentButtons.enableButtons(getButtonClick().getButtons(),true);
        netg.changerNombreDePartiesEnQuittant(this);
        ecrireCoordonnees();
        netg.closeWindows();
        languageDialogButtons.closeWindow();
        /*if (containerGame instanceof ContainerMulti) {
            if (!getMultiStop().isEnabled()) {
                return;
            }
            Quit bye_ = new Quit();
            bye_.setClosing(true);
            bye_.setServer(((ContainerMulti)containerGame).hasCreatedServer());
            bye_.setPlace(((ContainerMulti)containerGame).getIndexInGame());
            if (!sendObject(bye_)) {
                menuPrincipal();
                basicDispose();
            }
            return;
        }
        /*Si l'utilisateur a supprime le fichier de configurations alors a la fin
        de l'execution le fichier de configuration sera recree*/
        /*if(containerGame.playingSingleGame()&&!partieSauvegardee) {
            int choix_=saving();
            if(choix_!=GuiConstants.CANCEL_OPTION) {
                if(choix_==GuiConstants.YES_OPTION) {
                    String file_ = dialogueFichierSauvegarde();
                    if(!file_.isEmpty()) {
                        containerGame.saveCurrentGame(file_);
                    }
                }
                partieSauvegardee = true;
                //LaunchingCards.decrement();
                basicDispose();
//                if (Standalone.isStandalone()) {
//                    Constants.exit();
//                }
            }
        } else {
            //LaunchingCards.decrement();
            basicDispose();
//            if (Standalone.isStandalone()) {
//                Constants.exit();
//            }
        }*/
    }
//    @Override
//    public void dispose() {
//        netg.changerNombreDePartiesEnQuittant(this);
//        ecrireCoordonnees();
//        if (!helpFrames.isEmpty()) {
////            helpFrames.first().dispose();
//            helpFrames.first().setVisible(false);
//        }
//        GuiBaseUtil.trEx(this);
//    }

//    private int saving() {
//        //warning message
//        return confirm(getMessages().getVal(CST_SAVING),getMessages().getVal(CST_SAVING_TITLE));
//    }
//    private void changerNombreDePartiesEnQuittant() {
//        String fileName_ = StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.DECK_FOLDER,StreamTextFile.SEPARATEUR,FileConst.DECK_FILE);
//        String content_ = StreamTextFile.contentsOfFile(fileName_,getFileCoreStream(),getStreams());
//        StringList vl_=new StringList();
//        boolean read_ = true;
//        StringList lines_ = new StringList();
//        if (content_ != null) {
//            lines_.addAllElts(StringUtil.splitChars(content_, LINE_RETURN));
//        } else {
//            read_ = false;
//        }
//        int total_ = GameEnum.values().length;
//        if (lines_.size() < total_) {
//            read_ = false;
//        }
//        if (read_) {
//            for (int indice_ = IndexConstants.FIRST_INDEX; indice_<total_; indice_++) {
//                vl_.add(lines_.get(indice_));
//            }
//        } else {
//            vl_=new StringList();
//            for (int indice_ = IndexConstants.FIRST_INDEX; indice_ < total_; indice_++) {
//                vl_.add("0");
//            }
//        }
//        //Si l'action de battre les cartes est faite a chaque lancement
//        //de logiciel alors le nombre de parties est remis a zero lors
//        //d'une fermeture de logiciel
//
//        if(reglesPresident.getCommon().getMixedCards() == MixCardsChoice.EACH_LAUNCHING) {
//            vl_.set(GameEnum.PRESIDENT.ordinal(), "0");
//        }
//        if(reglesBelote.getCommon().getMixedCards() ==MixCardsChoice.EACH_LAUNCHING) {
//            vl_.set(GameEnum.BELOTE.ordinal(), "0");
//        }
//        if(reglesTarot.getCommon().getMixedCards() ==MixCardsChoice.EACH_LAUNCHING) {
//            vl_.set(GameEnum.TAROT.ordinal(), "0");
//        }
//        StreamTextFile.saveTextFile(fileName_, StringUtil.join(vl_, LINE_RETURN),getStreams());
//    }
    private void ecrireCoordonnees() {
        MetaPoint point_=getLocation();
        FileDialog.saveCoords(WindowCards.getTempFolder(getFrames()), MessagesCardGames.getAppliFilesTr(getFrames().getTranslations()).val().getMapping().getVal(MessagesCardGames.COORDS), point_.getXcoord(),point_.getYcoord(),getStreams());
    }
//    public int getNoClient() {
//        return ((ContainerMulti) netg.getContainerGame()).getNoClient();
//    }

    @Override
    public void gearClient(AbstractSocket _newSocket) {
        if (!isCards()) {
            if (getSockets().getSockets().size() == NetAiki.NB_PLAYERS) {
                Exiting forcedBye_ = new Exiting();
                forcedBye_.setForced(true);
                forcedBye_.setClosing(false);
                forcedBye_.setTooManyPlayers(true);
                expExit(forcedBye_, _newSocket);
                return;
            }
            /*if (_common.getNicknames().size() == NetAiki.NB_PLAYERS) {*/
            getSockets().getSockets().put(getSockets().getSockets().size(), _newSocket);
            SendReceiveServerAiki sendReceiveServer_=new SendReceiveServerAiki(_newSocket,this, getNetAiki());
            getThreadFactory().newStartedThread(sendReceiveServer_);
            int i_ = getSockets().getSockets().size() - 1;
            getSockets().getConnectionsServer().put(i_,sendReceiveServer_);
            getSockets().getReadyPlayers().put(i_, BoolVal.FALSE);
            getSockets().getPlacesPlayers().put(i_, i_);
            trySendString(NetAiki.exportIndexArrive(i_),_newSocket);
            return;
        }
        int nb_ = getSockets().getSockets().size();
        int nbPlayers_ = Net.getNbPlayers(getNet());
        if (nbPlayers_ == nb_ || Net.isProgressingGame(getNet())) {
            Exiting forcedBye_ = new Exiting();
            forcedBye_.setForced(true);
            forcedBye_.setClosing(false);
            expExit(forcedBye_, _newSocket);
            return;
        }
//        getSockets().getNicknames().put(nb_,getDialogServerContent().getNickname().getText());
        getSockets().getSockets().put(nb_, _newSocket);
        SendReceiveServerCards sendReceiveServer_=new SendReceiveServerCards(_newSocket,this, getNet());
        getThreadFactory().newStartedThread(sendReceiveServer_);
        getSockets().getConnectionsServer().put(nb_ ,sendReceiveServer_);
//        IndexOfArrivingCards index_ = new IndexOfArrivingCards();
//        index_.setIndex(nb_);
//        index_.setNbPlayers(nbPlayers_);
//        if (Net.getGames(getNet()).getRulesBelote() != null) {
//            index_.setRulesBelote(Net.getGames(getNet()).getRulesBelote());
//            index_.setRulesPresident(new RulesPresident());
//            index_.setRulesTarot(new RulesTarot());
//        } else if (Net.getGames(getNet()).getRulesPresident() != null) {
//            index_.setRulesBelote(new RulesBelote());
//            index_.setRulesPresident(Net.getGames(getNet()).getRulesPresident());
//            index_.setRulesTarot(new RulesTarot());
//        } else if (Net.getGames(getNet()).getRulesTarot() != null) {
//            index_.setRulesBelote(new RulesBelote());
//            index_.setRulesPresident(new RulesPresident());
//            index_.setRulesTarot(Net.getGames(getNet()).getRulesTarot());
//        } else {
//            index_.setRulesBelote(new RulesBelote());
//            index_.setRulesPresident(new RulesPresident());
//            index_.setRulesTarot(new RulesTarot());
//        }
//            pl_.setNbPlayers(Net.getNbPlayers(_instance));
//        pl_.setPseudos(new IntMap<String>(_common.getNicknames()));
//        index_.setPlacesPlayers(getSockets().getPlacesPlayers());
//        index_.setReadyPlayers(new IntMap<BoolVal>(getSockets().getReadyPlayers()));
        getSockets().getReadyPlayers().put(nb_ , BoolVal.FALSE);
        getSockets().getPlacesPlayers().put(nb_ , nb_);
        NetGroupFrame.trySendString(Net.exportIndexArrive(nb_, nbPlayers_, getSockets(), Net.getGames(getNet()), getDialogServerContent().getChosen()),_newSocket);
    }

    private void expExit(Exiting _forcedBye, AbstractSocket _newSocket) {
        trySendString(NetCommon.exportExiting(_forcedBye), _newSocket);
    }

    public void quitNetwork(Exiting _exit, AbstractSocket _socket) {
        if (!isCards()) {
            quitNetworkAiki(_exit, _socket);
            return;
        }
        menuPrincipal();
        getDialogServerContent().closeConnexion(_exit,_socket);
        if (_exit.isClosing()) {
//        if (_exit != null && _exit.isClosing()) {
            GuiBaseUtil.trEx(this, getFrames());
            return;
        }
        pack();
        if (_exit.isForced()) {
//        if (_exit != null && _exit.isForced()) {
//        if (_exit != null && _exit.isForced() && !_exit.isBusy()) {
            errorsFile.display(getTooManyString(), getTooManyString());
//            getFrames().getMessageDialogAbs().input(getCommonFrame(), getTooManyString(), getTooManyString(), GuiConstants.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(window, window.getTooManyString(), window.getTooManyString(), JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void okReceived() {
        aiki.getFacade().applyTrading();
        IntTreeMap< PokemonPlayer> tree_ = aiki.getFacade().getExchangeData().getTeam(aiki.getFacade().getGame().getPlayer().getTeam());
        scenePanel.setTradableAfterTrading(tree_);
        pack();
    }

    public void pokemonPlayer(PokemonPlayer _pk) {
        aiki.getFacade().receivePokemonPlayer(_pk);
        scenePanel.seeNetPokemonDetail();
    }

    public void netNetPokemon(NetPokemon _net) {
        if (indexInGame == IndexConstants.SECOND_INDEX) {
            scenePanel.setNetworkPanel();
        }
        scenePanel.setTradable(_net.getTradablePokemon());
        pack();
    }

    public void initTrading(AbstractSocket _socket) {
        aiki.getFacade().initTrading();
        CheckCompatibility ch_ = new CheckCompatibility();
        ch_.setData(aiki.getFacade().getExchangeData());
        ch_.setIndex(indexInGame);
        ch_.setTeam(aiki.getFacade().getGame().getPlayer().getTeam());
        NetAiki.sendObject(_socket,ch_);
    }

    public void newPlayer(AbstractSocket _socket) {
//        NewPlayerAiki p_ = new NewPlayerAiki();
//            p_.setAcceptable(true);
//            p_.setArriving(true);
//        p_.setIndex(indexInGame);
        //p_.setPseudo(pseudo());
//            p_.setLanguage(getLanguageKey());
//            p_.setPseudo(facade.getGame().getPlayer().getNickname());
        if (indexInGame == IndexConstants.FIRST_INDEX) {
            scenePanel.setNetworkPanel();
        }
        pack();
        NetAiki.sendObject(_socket, indexInGame);
    }

//    @Override
    public void quitNetworkAiki(Exiting _exit, AbstractSocket _socket) {
        exitFromTrading();
        resetIndexInGame();
        getDialogServerContent().closeConnexion(_exit,_socket);
        if (_exit.isClosing()) {
//        if (_exit != null && _exit.isClosing()) {
            GuiBaseUtil.trEx(this, getFrames());
            return;
        }
        pack();
        if (_exit.isForced()) {
//        if (_exit != null && _exit.isForced()) {
//        if (_exit != null && _exit.isForced() && !_exit.isBusy()) {
            if (_exit.isTooManyPlayers()) {
                errorsFile.display(getTooManyString(), getTooManyString());
//                getFrames().getMessageDialogAbs().input(getCommonFrame(), getTooManyString(), getTooManyString(), GuiConstants.ERROR_MESSAGE);
                //JOptionPane.showMessageDialog(window, MainWindow.getTooManyString(), MainWindow.getTooManyString(), JOptionPane.INFORMATION_MESSAGE);
            } else {
                errorsFile.display(getNoTradeString(), getNoTradeString());
//                getFrames().getMessageDialogAbs().input(getCommonFrame(), getNoTradeString(), getNoTradeString(), GuiConstants.ERROR_MESSAGE);
                //JOptionPane.showMessageDialog(window, MainWindow.getNoTradeString(), MainWindow.getNoTradeString(), JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public void resetIndexInGame() {
        setIndexInGame(IndexConstants.INDEX_NOT_FOUND_ELT);
    }

    public void setIndexInGame(int _indexInGame) {
        indexInGame = _indexInGame;
    }

    public int getIndexInGame() {
        return indexInGame;
    }
    public void exitFromTrading() {
//        setSavedGame(false);
        aiki.getFacade().closeTrading();
        scenePanel.exitInteraction();
        en(true);
    }

    private void en(boolean _b) {
//        MenuItemUtils.setEnabledMenu(newGame,_b);
//        MenuItemUtils.setEnabledMenu(params,_b);
        aiki.getZipLoad().setEnabled(_b);
        aiki.getGameLoad().setEnabled(_b);
    }
//    public String getTooManyString() {
//        return messages.getVal(TOO_MANY);
//    }

    public String getNoTradeString() {
        return MessagesPkGame.getScenePanelContentTr(MessagesPkGame.getAppliTr(getFrames().currentLg())).getMapping().getVal(MessagesRenderScenePanel.NO_TRADE);
    }

//    public boolean isForceBye() {
//        return forceBye;
//    }
    public void menuMultiGames() {
        netg.setContainerGame(noGame());
//        MenuItemUtils.setEnabledMenu(change,false);
        //Activer le menu Partie/Demo
//        MenuItemUtils.setEnabledMenu(getDemo(),false);
        //desactiver le menu Partie/aide au jeu
//        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
        //desactiver le menu Partie/conseil
//        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        //Desactiver le menu Partie/Pause
//        MenuItemUtils.setEnabledMenu(getPause(),false);
//        MenuItemUtils.setEnabledMenu(getEdit(),false);
//        MenuItemUtils.setEnabledMenu(getTraining(),false);
//        for (AbsMenuItem m: getRulesGames().values()) {
//            MenuItemUtils.setEnabledMenu(m,false);
//        }
        netg.getContainerGame().finirParties();
        setTitle(Launching.WELCOME.toString(getFrames().currentLg()));
        getPane().removeAll();
        AbsPanel container_=getCompoFactory().newPageBox();
        /*Pour montrer qu'on a de l'attention a l'utilisateur*/
//        welcomeLabel = getCompoFactory().newPlainLabel(StringUtil.simpleStringsFormat(getMenusMessages().getVal(MessagesGuiCards.CST_WELCOME), pseudo()));
//        container_.add(welcomeLabel);
        /*Cree les boutons de jeu*/
        boutonsMulti(container_);
        AbsButton button_ = getCompoFactory().newPlainButton(getMenusMessages().getVal(MessagesGuiCards.CST_MAIN_MENU));
        button_.addActionListener(new BackToMainMenuEvent(this));
        container_.add(button_);
        //Ajout d'une etiquette pour indiquer ou aller pour avoir de l'aide
//        if (goHelpMenu == null) {
//            goHelpMenu = getCompoFactory().newPlainLabel(getMessages().getVal(CST_GO_HELP_MENU));
//        }
//        container_.add(goHelpMenu);
//        MenuItemUtils.setEnabledMenu(getLoad(),false);
//        MenuItemUtils.setEnabledMenu(getSave(),false);
//        MenuItemUtils.setEnabledMenu(getChange(),false);
        container_.add(netg.getClock().getComponent());
        container_.add(aiki.getLastSavedGameDate());
        setContentPane(container_);
        pack();
    }

    private void boutonsMulti(AbsPanel _container) {
        TranslationsLg lg_ = getFrames().currentLg();
        for (GameEnum jeu2_:GameEnum.allValid()) {
            ajouterBoutonPrincipal(jeu2_.toString(lg_),jeu2_, _container);
        }
    }
//    public void menuSoloGames() {
//        containerGame = new ContainerNoGame(this);
//        MenuItemUtils.setEnabledMenu(change,false);
//        //Activer le menu Partie/Demo
//        MenuItemUtils.setEnabledMenu(getDemo(),true);
//        //desactiver le menu Partie/aide au jeu
//        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
//        //desactiver le menu Partie/conseil
//        MenuItemUtils.setEnabledMenu(getConsulting(),false);
//        //Desactiver le menu Partie/Pause
//        MenuItemUtils.setEnabledMenu(getPause(),false);
//        containerGame.setChangerPileFin(false);
//        containerGame.finirParties();
//        setTitle(Launching.WELCOME.toString(getLanguageKey()));
//        AbsPanel container_=getCompoFactory().newGrid(0,1);
//        /*Pour montrer qu'on a de l'attention a l'utilisateur*/
//        container_.add(getCompoFactory().newPlainLabel(StringUtil.simpleStringsFormat(getMessages().getVal(CST_WELCOME), pseudo())));
//        /*Cree les boutons de jeu*/
//        String lg_ = getLanguageKey();
//        for (GameEnum jeu2_:GameEnum.values()) {
//            ajouterBoutonPrincipal(jeu2_.toString(lg_),jeu2_,container_);
//        }
//        AbsPlainButton button_ = getCompoFactory().newPlainButton(getMessages().getVal(CST_MAIN_MENU));
//        button_.addActionListener(new BackToMainMenuEvent(this));
//        container_.add(button_);
//        //Ajout d'une etiquette pour indiquer ou aller pour avoir de l'aide
//        if (goHelpMenu == null) {
//            goHelpMenu = getCompoFactory().newPlainLabel(getMessages().getVal(CST_GO_HELP_MENU));
//        }
//        container_.add(goHelpMenu);
//        MenuItemUtils.setEnabledMenu(getSave(),false);
//        MenuItemUtils.setEnabledMenu(getChange(),false);
//        container_.add(clock);
//        container_.add(lastSavedGameDate);
//        setContentPane(container_);
//        pack();
//    }

    public void menuPrincipal() {
//        MenuItemUtils.setEnabledMenu(getMultiStop(),false);
        getTricksHands().setEnabled(false);
        getTeams().setEnabled(false);
        getMultiStop().setEnabled(false);
        netg.setContainerGame(noGame());
//        MenuItemUtils.setEnabledMenu(change,false);
        //Activer le menu Partie/Demo
//        MenuItemUtils.setEnabledMenu(getDemo(),true);
        //desactiver le menu Partie/aide au jeu
//        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
        //desactiver le menu Partie/conseil
//        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        //Desactiver le menu Partie/Pause
//        MenuItemUtils.setEnabledMenu(getPause(),false);
//        MenuItemUtils.setEnabledMenu(getLoad(),true);
//        MenuItemUtils.setEnabledMenu(getEdit(),true);
//        MenuItemUtils.setEnabledMenu(getTraining(),true);
//        for (AbsMenuItem m: getRulesGames().values()) {
//            MenuItemUtils.setEnabledMenu(m,true);
//        }
        netg.getContainerGame().finirParties();
        setTitle(Launching.WELCOME.toString(getFrames().currentLg()));
        getPane().removeAll();
        AbsPanel pane_ = getCompoFactory().newPageBox();
        /*Pour montrer qu'on a de l'attention a l'utilisateur*/
//        welcomeLabel = getCompoFactory().newPlainLabel(StringUtil.simpleStringsFormat(getMenusMessages().getVal(MessagesGuiCards.CST_WELCOME), pseudo()));
//        pane_.add(welcomeLabel, GuiConstants.CENTER);
        /*Cree les boutons de jeu*/
//        singleModeButton = getCompoFactory().newPlainButton(getMessages().getVal(CST_SINGLE_MODE));
//        singleModeButton.addActionListener(new ChooseModeEvent(this, true));
//        pane_.add(singleModeButton);
        multiModeButton = getCompoFactory().newPlainButton(getMenusMessages().getVal(MessagesGuiCards.CST_MULTI_MODE));
        multiModeButton.addActionListener(new ChooseModeMultEvent(this));
        pane_.add(multiModeButton);
        //Ajout d'une etiquette pour indiquer ou aller pour avoir de l'aide
//        if (goHelpMenu == null) {
//            goHelpMenu = getCompoFactory().newPlainLabel(getMessages().getVal(CST_GO_HELP_MENU));
//        }
//        pane_.add(goHelpMenu);
        pane_.add(netg.getClock().getComponent());
        pane_.add(aiki.getLastSavedGameDate());
        setContentPane(pane_);
//        MenuItemUtils.setEnabledMenu(getSave(),false);
//        MenuItemUtils.setEnabledMenu(getChange(),false);
    }
//    private void initMessageName() {
//        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), getClass());
//        setMessages(WindowNetWork.getMessagesFromLocaleClass(FrameGeneralHelp.FOLDER_MESSAGES_GUI, getFrames().getLanguage(), getAccessFile()));
//        messagesAiki = WindowAiki.getMessagesFromLocaleClass(getLanguageKey());
//    }
//    public void loadGameBegin(String _file) {
//        containerGame = new ContainerNoGame(this);
//        tryToLoadDeal(_file);
//    }
    private void initFileMenu() {
        /* Fichier */
        file=getCompoFactory().newMenu(MessagesPkGame.getWindowPkContentTr(MessagesPkGame.getAppliTr(getFrames().currentLg())).getMapping().getVal(MessagesRenderWindowPk.CST_FILE));
        aiki.fileMenu(file,this,this, new AlwaysActionListenerAct());
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
        /* Fichier/Charger "accessible n'importe quand"*/
//        load=getCompoFactory().newMenuItem(getMessages().getVal(CST_LOAD));
//        load.addActionListener(new LoadGameEventCards(this));
//        load.setAccelerator(GuiConstants.VK_O, GuiConstants.CTRL_DOWN_MASK);
//        file.addMenuItem(load);
        /* Fichier/Sauvegarder "accessible que lorsqu'on joue une partie de cartes"*/
//        save=getCompoFactory().newMenuItem(getMessages().getVal(CST_SAVE));
//        save.addActionListener(new SaveGameEventCards(this));
//        save.setAccelerator(GuiConstants.VK_S, GuiConstants.CTRL_DOWN_MASK);
//        file.addMenuItem(save);
//        file.addSeparator();
        /* Fichier/Changer de jeu ACCESSIBLE n'importe quand sauf au menu principal,
        on y revient lorsque c'est accessible*/
//        change=getCompoFactory().newMenuItem(getMessages().getVal(CST_CHANGE));
//        WindowCards.changeMode(new AlwaysActionListenerAct(),this,change);
//        MenuItemUtils.setEnabledMenu(change,false);
//        change.addActionListener(new ChangeGameEvent(this));
//        change.setAccelerator(GuiConstants.VK_J, GuiConstants.CTRL_DOWN_MASK);
//        file.addMenuItem(change);
        file.addMenuItem(getCompoFactory().newSep());
        exit=getCompoFactory().newMenuItem(getMenusMessages().getVal(MessagesGuiCards.CST_EXIT));
        exit.addActionListener(new QuitEvent(this));
        exit.setAccelerator(GuiConstants.VK_ESCAPE,0);
        file.addMenuItem(exit);
        getJMenuBar().add(file);
    }

//    public void loadGame() {
////        if (!load.isEnabled()) {
////            return;
////        }
////        if (containerGame instanceof ContainerMulti) {
////            return;
////        }
//        if(containerGame.playingSingleGame()&&!partieSauvegardee) {
//            if(containerGame.isPasse()||!containerGame.isThreadAnime()) {
//                int choix_=saving();
//                if(choix_!=GuiConstants.CANCEL_OPTION) {
//                    if(choix_==GuiConstants.YES_OPTION) {
//                        String fichier_=dialogueFichierSauvegarde();
//                        if(!fichier_.isEmpty()) {
//                            containerGame.saveCurrentGame(fichier_);
//                            tryToLoadDeal();
//                        }
//                    } else {
//                        tryToLoadDeal();
//                    }
//                }
//            }
//        } else if(containerGame.isPasse()||!containerGame.isThreadAnime()) {
//            tryToLoadDeal();
//        }
//    }
//
//    private void tryToLoadDeal() {
//        partieSauvegardee=true;
//        String nomFichier_=dialogueFichierChargement();
//        if (nomFichier_.isEmpty()) {
//            return;
//        }
//        tryToLoadDeal(nomFichier_);
//    }
//
//    private void tryToLoadDeal(String _nomFichier) {
//        String content_ = StreamTextFile.contentsOfFile(_nomFichier, getFileCoreStream(), getStreams());
//        Document doc_ = DocumentBuilder.parseSax(content_);
//        Element elt_ = doc_.getDocumentElement();
//        String tagName_ = elt_.getTagName();
//        if (StringUtil.quickEq(tagName_, "GameBelote")) {
//            GameBelote par_ = DocumentReaderBeloteUtil.getGameBelote(doc_);
//            CheckerGameBeloteWithRules.check(par_);
//            if (!par_.getError().isEmpty()) {
//                erreurDeChargement(_nomFichier);
//                return;
//            }
//            ContainerSingleBelote containerGame_ = new ContainerSingleBelote(this);
//            containerGame_.getPar().jouerBelote(par_);
//            containerGame_.load();
//            partieSauvegardee=false;
//            containerGame = containerGame_;
//            MenuItemUtils.setEnabledMenu(change,true);
//            return;
//        }
//        if (StringUtil.quickEq(tagName_, "GamePresident")) {
//            GamePresident par_ = DocumentReaderPresidentUtil.getGamePresident(doc_);
//            CheckerGamePresidentWithRules.check(par_);
//            if (!par_.getError().isEmpty()) {
//                erreurDeChargement(_nomFichier);
//                return;
//            }
//            ContainerSinglePresident containerGame_ = new ContainerSinglePresident(this);
//            containerGame_.getPar().jouerPresident(par_);
//            containerGame_.load();
//            partieSauvegardee=false;
//            containerGame = containerGame_;
//            MenuItemUtils.setEnabledMenu(change,true);
//            return;
//        }
//        if (StringUtil.quickEq(tagName_, "GameTarot")) {
//            GameTarot par_ = DocumentReaderTarotUtil.getGameTarot(doc_);
//            CheckerGameTarotWithRules.check(par_);
//            if (!par_.getError().isEmpty()) {
//                erreurDeChargement(_nomFichier);
//                return;
//            }
//            ContainerSingleTarot containerGame_ = new ContainerSingleTarot(this);
//            containerGame_.getPar().jouerTarot(par_);
//            containerGame_.load();
//            partieSauvegardee=false;
//            containerGame = containerGame_;
//            MenuItemUtils.setEnabledMenu(change,true);
//            return;
//        }
//        erreurDeChargement(_nomFichier);
//    }

//    public void saveGame() {
////        if (!save.isEnabled()) {
////            return;
////        }
////        if (containerGame instanceof ContainerMulti) {
////            return;
////        }
//        if(containerGame.isPasse()||!containerGame.isThreadAnime()) {
//            String fichier_=dialogueFichierSauvegarde();
//
//            if(!fichier_.isEmpty()) {
//                containerGame.saveCurrentGame(fichier_);
//                dateLastSaved = Clock.getDateTimeText(getThreadFactory());
//                lastSavedGameDate.setText(StringUtil.simpleStringsFormat(getMessages().getVal(LAST_SAVED_GAME), dateLastSaved));
//                partieSauvegardee=true;
//            }
//        }
//    }

//    public void changeGame() {
//        if (!change.isEnabled()) {
//            return;
//        }
//        if (containerGame instanceof ContainerMulti) {
//            return;
//        }
        /*if(containerGame.playingSingleGame()&&!partieSauvegardee) {
            if(containerGame.isPasse()||!containerGame.isThreadAnime()) {
                int choix_=saving();
                if(choix_!=GuiConstants.CANCEL_OPTION) {
                    if(choix_==GuiConstants.YES_OPTION) {
                        String fichier_=dialogueFichierSauvegarde();
                        if(!fichier_.isEmpty()) {
//                            containerGame.saveCurrentGame(fichier_);
                            menuMultiGames();
//                            if (single) {
//                                menuSoloGames();
//                            } else {
//                                menuMultiGames();
//                            }
                        }
                    } else {
                        if (single) {
                            menuSoloGames();
                        } else {
                            menuMultiGames();
                        }
                    }
                }
            }
        } else*/
//        if(containerGame.isPasse()||!containerGame.isThreadAnime()) {
////            if (single) {
////                menuSoloGames();
////            } else {
////                menuMultiGames();
////            }
//            menuMultiGames();
//        }
//        menuMultiGames();
//    }

    private void initDealMenu() {
//        String lg_ = getLanguageKey();
        deal=getCompoFactory().newMenu(getMenusMessages().getVal(MessagesGuiCards.CST_DEAL));
        /* Partie/Conseil "accessible uniquement en cours de partie et
        dans les jeux non solitaires"*/
//        AbsMenuItem sousMenu_;
//        consulting=getCompoFactory().newMenuItem(getMessages().getVal(CST_CONSULTING));
//        consulting.setAccelerator(F_ONE);
//        consulting.addActionListener(new ConsultEvent(this));
//        deal.addMenuItem(consulting);
        /* Partie/Pause Permet de mettre le jeu en pause*/
//        pause=getCompoFactory().newCheckBoxMenuItem(getMessages().getVal(CST_PAUSE));
//        pause.setAccelerator(CST_PAUSE);
//        pause.addActionListener(new PauseEvent(this));
//        deal.addMenuItem(pause);
        /* Partie/Pause Permet d avoir de l aide*/
//        helpGame=getCompoFactory().newMenuItem(getMessages().getVal(HELP_GAME));
//        helpGame.setAccelerator(F_TWO);
//        helpGame.addActionListener(new DisplayHelpGameEvent(this));
//        deal.addMenuItem(helpGame);
        tricksHands=getCompoFactory().newMenuItem(getMenusMessages().getVal(MessagesGuiCards.CST_TRICKS_HANDS));

        tricksHands.addActionListener(new DisplayTricksHandsEvent(this));
        deal.addMenuItem(tricksHands);
        teams=getCompoFactory().newMenuItem(getMenusMessages().getVal(MessagesGuiCards.CST_TEAMS));
        teams.addActionListener(new DisplayTeamsEvent(this));
        deal.addMenuItem(teams);
        /* Partie/Editer "Permet d'editer n'importe quelle partie de cartes et accessible n'importe quand"*/
//        edit=getCompoFactory().newMenu(getMessages().getVal(CST_EDIT));
//        AbsMenuItem sousSousMenu_ = getCompoFactory().newMenuItem(GameEnum.BELOTE.toString(lg_));
//        sousSousMenu_.addActionListener(new EditEvent(this, GameEnum.BELOTE));
//        edit.addMenuItem(sousSousMenu_);
//        editGames.put(GameEnum.BELOTE, sousSousMenu_);
//        sousSousMenu_ = getCompoFactory().newMenuItem(GameEnum.PRESIDENT.toString(lg_));
//        sousSousMenu_.addActionListener(new EditEvent(this, GameEnum.PRESIDENT));
//        edit.addMenuItem(sousSousMenu_);
//        editGames.put(GameEnum.PRESIDENT, sousSousMenu_);
//        sousSousMenu_ = getCompoFactory().newMenuItem(GameEnum.TAROT.toString(lg_));
//        sousSousMenu_.addActionListener(new EditEvent(this, GameEnum.TAROT));
//        edit.addMenuItem(sousSousMenu_);
//        editGames.put(GameEnum.TAROT, sousSousMenu_);
//        deal.addMenuItem(edit);
        /* Partie/Demo "Permet de voir la demostration d une partie"*/
//        demo=getCompoFactory().newMenu(getMessages().getVal(CST_DEMO));
//        sousMenu_=getCompoFactory().newMenuItem(GameEnum.BELOTE.toString(lg_));
//        sousMenu_.addActionListener(new SimulationEvent(this, GameEnum.BELOTE));
//        sousMenu_.setAccelerator(GuiConstants.VK_B, GuiConstants.CTRL_DOWN_MASK + GuiConstants.SHIFT_DOWN_MASK);
//        demo.addMenuItem(sousMenu_);
//        demoGames.put(GameEnum.BELOTE, sousSousMenu_);
//        sousMenu_=getCompoFactory().newMenuItem(GameEnum.PRESIDENT.toString(lg_));
//        sousMenu_.addActionListener(new SimulationEvent(this, GameEnum.PRESIDENT));
//        sousMenu_.setAccelerator(GuiConstants.VK_P, GuiConstants.CTRL_DOWN_MASK + GuiConstants.SHIFT_DOWN_MASK);
//        demo.addMenuItem(sousMenu_);
//        demoGames.put(GameEnum.PRESIDENT, sousSousMenu_);
//        sousMenu_=getCompoFactory().newMenuItem(GameEnum.TAROT.toString(lg_));
//        sousMenu_.addActionListener(new SimulationEvent(this, GameEnum.TAROT));
//        sousMenu_.setAccelerator(GuiConstants.VK_T, GuiConstants.CTRL_DOWN_MASK + GuiConstants.SHIFT_DOWN_MASK);
//        demo.addMenuItem(sousMenu_);
//        demoGames.put(GameEnum.TAROT, sousSousMenu_);
//        deal.addMenuItem(demo);
        /* Partie/Entrainement "accessible n'importe quand pour pouvoir s'entrainer"*/
//        training=getCompoFactory().newMenu(getMessages().getVal(CST_TRAINING));
        /* Partie/Entrainement au Tarot*/
        //Petitasauver,Petitachasser,Petitaemmeneraubout;
//        for (ChoiceTarot ct_:ChoiceTarot.values()) {
//
//            sousMenu_=getCompoFactory().newMenuItem(Games.toString(ct_,lg_));
//            sousMenu_.addActionListener(new ListenerTrainingTarot(this, ct_));
//            training.addMenuItem(sousMenu_);
//            trainingTarot.put(ct_, sousMenu_);
//        }
//        deal.addMenuItem(training);
        multiStop = getCompoFactory().newMenuItem(MessagesNetWork.getMessages(MessagesNetWork.getAppliTr(getFrames().currentLg())).getMapping().getVal(MessagesNetWork.CST_MULTI_STOP));
        multiStop.addActionListener(new QuitMultiEvent(this));
        deal.addMenuItem(multiStop);
        getJMenuBar().add(deal);
    }

//    public void consult() {
////        if (!consulting.isEnabled()) {
////            return;
////        }
//        if (!(containerGame instanceof ContainerSingle)) {
//            return;
//        }
//        if(!containerGame.isThreadAnime()) {
//            ((ContainerSingle)containerGame).conseil();
//        }
//    }
//    public void pause() {
////        if (!pause.isEnabled()) {
////            return;
////        }
////        if (containerGame instanceof ContainerMulti) {
////            return;
////        }
//        /*In order that the player can pause*/
////        if (containerGame instanceof ContainerSingle) {
////            containerGame.setPasse(!containerGame.isPasse());
////            if (pause.isSelected()) {
////                return;
////            }
////            containerGame.setState(null);
////            if (containerGame instanceof ContainerSingleBelote) {
////                if (containerGame.getState() == CardAnimState.BID_BELOTE) {
////                    containerGame.thread(new AnimationBidBelotePause(((ContainerSingleBelote)containerGame)));
////                    return;
////                }
////                containerGame.thread(new AnimationCardBelotePause(((ContainerSingleBelote)containerGame)));
////                return;
////            }
////            if (containerGame instanceof ContainerSingleTarot) {
////                if (containerGame.getState() == CardAnimState.BID_TAROT) {
////                    containerGame.thread(new AnimationBidTarotPause(((ContainerSingleTarot)containerGame)));
////                    return;
////                }
////                containerGame.thread(new AnimationCardTarotPause(((ContainerSingleTarot)containerGame)));
////                return;
////            }
////            containerGame.thread(new AnimationCardPresidentPause(((ContainerSinglePresident)containerGame)));
////            return;
////        }
//        containerGame.setPasse(!containerGame.isPasse());
//    }
//    public void displayHelpGame() {
////        if (!helpGame.isEnabled()) {
////            return;
////        }
//        if (!(containerGame instanceof ContainerSingle)) {
//            return;
//        }
//        ((ContainerSingle)containerGame).aideAuJeu();
//    }
    public void displayTricksHands() {
//        if (!tricksHands.isEnabled()) {
//            return;
//        }
//        if (!(netg.getContainerGame() instanceof ContainerPlayableGame)) {
//            return;
//        }
        if (netg.getContainerGame() instanceof ContainerMultiBelote) {
            ((ContainerMultiBelote) netg.getContainerGame()).showTricksHands();
        }
        if (netg.getContainerGame() instanceof ContainerMultiPresident) {
            ((ContainerMultiPresident) netg.getContainerGame()).showTricksHands();
        }
        if (netg.getContainerGame() instanceof ContainerMultiTarot) {
            ((ContainerMultiTarot) netg.getContainerGame()).showTricksHands();
        }
//        ((ContainerPlayableGame) netg.getContainerGame()).showTricksHands();
    }
    public void displayTeams() {
//        if (!teams.isEnabled()) {
//            return;
//        }
        /*if (containerGame instanceof ContainerSingleBelote) {
            ((ContainerSingleBelote)containerGame).showTeams();
        }*/
        if (netg.getContainerGame() instanceof ContainerMultiBelote) {
            ((ContainerMultiBelote) netg.getContainerGame()).showTeams();
        }
        /*if (containerGame instanceof ContainerSingleTarot) {
            ((ContainerSingleTarot)containerGame).showTeams();
        }*/
        if (netg.getContainerGame() instanceof ContainerMultiTarot) {
            ((ContainerMultiTarot) netg.getContainerGame()).showTeams();
        }

    }
//    public void editGame(GameEnum _game) {
//        if (_game == GameEnum.BELOTE) {
//            if (!edit.isEnabled()) {
//                return;
//            }
//            editeurBelote();
//            GameBelote partie_ = EditorBelote.getPartie(getEditorBelote());
//            if(partie_ == null) {
//                return;
//            }
//            //Cas ou l'utilisateur veut jouer une partie editee
//            /*if (containerGame instanceof ContainerMulti) {
//                return;
//            }*/
//            if(containerGame.playingSingleGame()&&!partieSauvegardee) {
//                if(containerGame.isPasse()||!containerGame.isThreadAnime()) {
//                    int choix_=saving();
//                    if(choix_!=GuiConstants.CANCEL_OPTION) {
//                        if(choix_==GuiConstants.YES_OPTION) {
//                            String fichier_=dialogueFichierSauvegarde();
//                            if(!fichier_.isEmpty()) {
//                                containerGame.saveCurrentGame(fichier_);
//                                containerGame = new ContainerSingleBelote(this);
//                                ((ContainerSingleBelote)containerGame).editerBelote(partie_);
//                                MenuItemUtils.setEnabledMenu(change,true);
//                            }
//                        } else {
//                            containerGame = new ContainerSingleBelote(this);
//                            ((ContainerSingleBelote)containerGame).editerBelote(partie_);
//                            MenuItemUtils.setEnabledMenu(change,true);
//                        }
//                    }
//                }
//            } else {
//                containerGame = new ContainerSingleBelote(this);
//                ((ContainerSingleBelote)containerGame).editerBelote(partie_);
//                MenuItemUtils.setEnabledMenu(change,true);
//            }
//        } else if (_game == GameEnum.PRESIDENT) {
//            if (!edit.isEnabled()) {
//                return;
//            }
//            editeurPresident();
//            GamePresident partie_ = EditorPresident.getPartie(getEditorPresident());
//            if(partie_ == null) {
//                return;
//            }
//            //Cas ou l'utilisateur veut jouer une partie editee
//            /*if (containerGame instanceof ContainerMulti) {
//                return;
//            }*/
//            if(containerGame.playingSingleGame()&&!partieSauvegardee) {
//                if(containerGame.isPasse()||!containerGame.isThreadAnime()) {
//                    int choix_=saving();
//                    if(choix_!=GuiConstants.CANCEL_OPTION) {
//                        if(choix_==GuiConstants.YES_OPTION) {
//                            String fichier_=dialogueFichierSauvegarde();
//                            if(!fichier_.isEmpty()) {
//                                containerGame.saveCurrentGame(fichier_);
//
//                                containerGame = new ContainerSinglePresident(this);
//                                ((ContainerSinglePresident) containerGame).editerPresident(partie_);
//                                MenuItemUtils.setEnabledMenu(change,true);
//                            }
//                        } else {
//                            containerGame = new ContainerSinglePresident(this);
//                            ((ContainerSinglePresident) containerGame).editerPresident(partie_);
//                            MenuItemUtils.setEnabledMenu(change,true);
//                        }
//                    }
//                }
//            } else {
//                containerGame = new ContainerSinglePresident(this);
//                ((ContainerSinglePresident) containerGame).editerPresident(partie_);
//                MenuItemUtils.setEnabledMenu(change,true);
//            }
//        } else if (_game == GameEnum.TAROT) {
//            if (!edit.isEnabled()) {
//                return;
//            }
//            editeurTarot();
//            GameTarot partie_ = EditorTarot.getPartie(getEditorTarot());
//            if(partie_ == null) {
//                return;
//            }
//            //Cas ou l'utilisateur veut jouer une partie editee
//            /*if (containerGame instanceof ContainerMulti) {
//                return;
//            }*/
//            if(containerGame.playingSingleGame()&&!partieSauvegardee) {
//                if(containerGame.isPasse()||!containerGame.isThreadAnime()) {
//                    int choix_=saving();
//                    if(choix_!=GuiConstants.CANCEL_OPTION) {
//                        if(choix_==GuiConstants.YES_OPTION) {
//                            String fichier_=dialogueFichierSauvegarde();
//                            if(!fichier_.isEmpty()) {
//                                containerGame.saveCurrentGame(fichier_);
//
//                                containerGame = new ContainerSingleTarot(this);
//                                ((ContainerSingleTarot) containerGame).editerTarot(partie_);
//                                MenuItemUtils.setEnabledMenu(change,true);
//                            }
//                        } else {
//                            containerGame = new ContainerSingleTarot(this);
//                            ((ContainerSingleTarot) containerGame).editerTarot(partie_);
//                            MenuItemUtils.setEnabledMenu(change,true);
//                        }
//                    }
//                }
//            } else {
//                containerGame = new ContainerSingleTarot(this);
//                ((ContainerSingleTarot) containerGame).editerTarot(partie_);
//                MenuItemUtils.setEnabledMenu(change,true);
//            }
//        }
//    }
//    public void simulateGame(GameEnum _game) {
//        if (_game == GameEnum.BELOTE) {
//            if (!demo.isEnabled()) {
//                return;
//            }
//            /*if (containerGame instanceof ContainerMulti) {
//                return;
//            }*/
//            containerGame = new ContainerSimuBelote(this);
//        } else if (_game == GameEnum.PRESIDENT) {
//            if (!demo.isEnabled()) {
//                return;
//            }
//            /*if (containerGame instanceof ContainerMulti) {
//                return;
//            }*/
//            containerGame = new ContainerSimuPresident(this);
//        } else if (_game == GameEnum.TAROT) {
//            if (!demo.isEnabled()) {
//                return;
//            }
//            /*if (containerGame instanceof ContainerMulti) {
//                return;
//            }*/
//            containerGame = new ContainerSimuTarot(this);
//        }
//    }
//    public void trainingTarot(ChoiceTarot _ct) {
//        /*if (containerGame instanceof ContainerMulti) {
//            return;
//        }*/
//        /*Si l'utilisateur a supprime le fichier de configurations alors a la fin
//        de l'execution le fichier de configuration sera recree*/
//        if(containerGame.playingSingleGame()&&!partieSauvegardee) {
//            int choix_=saving();
//            if(choix_!=GuiConstants.CANCEL_OPTION) {
//                if(choix_==GuiConstants.YES_OPTION) {
//                    String fichier_=dialogueFichierSauvegarde();
//                    if(!fichier_.isEmpty()) {
//                        containerGame.saveCurrentGame(fichier_);
//                    }
//                }
//                containerGame = new ContainerSingleTarot(this);
//                MenuItemUtils.setEnabledMenu(change,true);
//                ((ContainerSingleTarot) containerGame).jouerDonneEntrainement(_ct);
//            }
//        } else {
//            containerGame = new ContainerSingleTarot(this);
//            MenuItemUtils.setEnabledMenu(change,true);
//            ((ContainerSingleTarot) containerGame).jouerDonneEntrainement(_ct);
//        }
//    }

    public void quitMulti() {
//        if (!(netg.getContainerGame() instanceof ContainerMulti)) {
//            return;
//        }
        QuitCards quit_ = new QuitCards();
        quit_.getContent().setClosing(false);
        quit_.setPlace(((ContainerMulti) netg.getContainerGame()).getContainerMultiContent().getIndexInGame());
        quit_.getContent().setServer(((ContainerMulti) netg.getContainerGame()).getContainerMultiContent().isHasCreatedServer());
        sendObject(quit_);
    }

    private void initParametersMenu() {
        /* Parametres */
//        String lg_ = getLanguageKey();
        parameters=getCompoFactory().newMenu(getMenusMessages().getVal(MessagesGuiCards.CST_PARAMETERS));
//        AbsMenuItem sousMenu_=getCompoFactory().newMenuItem(GameEnum.BELOTE.toString(lg_));
//        sousMenu_.addActionListener(new ManageRulesEvent(this, GameEnum.BELOTE));
//        sousMenu_.setAccelerator(GuiConstants.VK_B, GuiConstants.SHIFT_DOWN_MASK);
//        parameters.addMenuItem(sousMenu_);
//        rulesGames.put(GameEnum.BELOTE, sousMenu_);
//        sousMenu_=getCompoFactory().newMenuItem(GameEnum.PRESIDENT.toString(lg_));
//        sousMenu_.addActionListener(new ManageRulesEvent(this, GameEnum.PRESIDENT));
//        sousMenu_.setAccelerator(GuiConstants.VK_P, GuiConstants.SHIFT_DOWN_MASK);
//        parameters.addMenuItem(sousMenu_);
//        rulesGames.put(GameEnum.PRESIDENT, sousMenu_);
//        sousMenu_=getCompoFactory().newMenuItem(GameEnum.TAROT.toString(lg_));
//        sousMenu_.addActionListener(new ManageRulesEvent(this, GameEnum.TAROT));
//        sousMenu_.setAccelerator(GuiConstants.VK_T, GuiConstants.SHIFT_DOWN_MASK);
//        parameters.addMenuItem(sousMenu_);
//        rulesGames.put(GameEnum.TAROT, sousMenu_);
//        players=getCompoFactory().newMenuItem(getMessages().getVal(CST_PLAYERS));
//        players.addActionListener(new ManageNicknameEvent(this));
//        players.setAccelerator(GuiConstants.VK_J, GuiConstants.CTRL_DOWN_MASK + GuiConstants.ALT_DOWN_MASK);
//        parameters.addMenuItem(players);
//        launching=getCompoFactory().newMenuItem(getMessages().getVal(CST_LAUNCHING));
//        launching.addActionListener(new ManageSoftEvent(this, CST_LAUNCHING));
//        launching.setAccelerator(GuiConstants.VK_L, GuiConstants.CTRL_DOWN_MASK);
//        parameters.addMenuItem(launching);
        languageDialogButtons.translate(getMenusMessages().getVal(MessagesGuiCards.CST_LANGUAGE));
        languageDialogButtons.commonParametersMenu(parameters,new ManageLanguageEventCards(this),GuiConstants.VK_F6,0);
        netg.commonParametersMenu(parameters,this);
//        timing=getCompoFactory().newMenuItem(getMessages().getVal(CST_TIMING));
//        timing.addActionListener(new ManageSoftEvent(this, CST_TIMING));
//        timing.setAccelerator(F_FOUR);
//        parameters.addMenuItem(timing);
//        interact=getCompoFactory().newMenuItem(getMessages().getVal(CST_INTERACT));
//        interact.addActionListener(new ManageSoftEvent(this, CST_INTERACT));
//        interact.setAccelerator(F_FIVE);
//        parameters.addMenuItem(interact);
//        language=getCompoFactory().newMenuItem(getMessages().getVal(CST_LANGUAGE));
//        language.addActionListener(new ManageLanguageEventCards(this));
////        if (Standalone.isStandalone()) {
////            language.setAccelerator(KeyStroke.getKeyStroke(F_SIX));
////            parameters.add(language);
////        }
//        language.setAccelerator(F_SIX);
//        parameters.addMenuItem(language);
//        /* Partie/Editer "Permet d'editer n'importe quelle partie de cartes et accessible n'importe quand"*/
//        displaying=getCompoFactory().newMenu(getMessages().getVal(CST_DISPLAYING));
//        AbsMenuItem sousSousMenu_ = getCompoFactory().newMenuItem(GameEnum.BELOTE.toString(lg_));
//        sousSousMenu_.addActionListener(new DisplayingGameEvent(this, GameEnum.BELOTE));
//        displaying.addMenuItem(sousSousMenu_);
//        displayingGames.put(GameEnum.BELOTE, sousSousMenu_);
//        sousSousMenu_ = getCompoFactory().newMenuItem(GameEnum.PRESIDENT.toString(lg_));
//        sousSousMenu_.addActionListener(new DisplayingGameEvent(this, GameEnum.PRESIDENT));
//        displaying.addMenuItem(sousSousMenu_);
//        displayingGames.put(GameEnum.PRESIDENT, sousSousMenu_);
//        sousSousMenu_ = getCompoFactory().newMenuItem(GameEnum.TAROT.toString(lg_));
//        sousSousMenu_.addActionListener(new DisplayingGameEvent(this, GameEnum.TAROT));
//        displaying.addMenuItem(sousSousMenu_);
//        displayingGames.put(GameEnum.TAROT, sousSousMenu_);
//        parameters.addMenuItem(displaying);
//        getJMenuBar().add(parameters);
    }
//    public void manageRules(GameEnum _game) {
//        String lg_ = getLanguageKey();
//        if (_game == GameEnum.BELOTE) {
//            DialogRulesBelote.initDialogRulesBelote(_game.toString(lg_), this,reglesBelote);
//            RulesBelote reglesBelote_=DialogRulesBelote.getRegles(getDialogRulesBelote());
//            if (!DialogRulesBelote.isValidated(getDialogRulesBelote())) {
//                return;
//            }
//            reglesBelote = reglesBelote_;
//            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.RULES_BELOTE), DocumentWriterBeloteUtil.setRulesBelote(reglesBelote),getStreams());
//            containerGame.setRulesBelote(reglesBelote);
//        } else if (_game == GameEnum.PRESIDENT) {
//            DialogRulesPresident.initDialogRulesPresident(_game.toString(lg_), this, reglesPresident);
//            DialogRulesPresident.setPresidentDialog(true, 0,this);
//            RulesPresident rules_ = DialogRulesPresident.getRegles(getDialogRulesPresident());
//            if (!DialogRulesPresident.isValidated(getDialogRulesPresident())) {
//                return;
//            }
//            reglesPresident = rules_;
//            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.RULES_PRESIDENT), DocumentWriterPresidentUtil.setRulesPresident(reglesPresident),getStreams());
//            containerGame.setRulesPresident(reglesPresident);
//        } else if (_game == GameEnum.TAROT) {
//            DialogRulesTarot.initDialogRulesTarot(_game.toString(lg_), this, reglesTarot);
//            DialogRulesTarot.setTarotDialog(true,0,this);
//            RulesTarot reglesTarot_=DialogRulesTarot.getRegles(getDialogRulesTarot());
//            if (!DialogRulesTarot.isValidated(getDialogRulesTarot())) {
//                return;
//            }
//            reglesTarot = reglesTarot_;
//            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.RULES_TAROT), DocumentWriterTarotUtil.setRulesTarot(reglesTarot),getStreams());
//            containerGame.setRulesTarot(reglesTarot);
//        }
//    }
//    public void manageNicknames() {
//        DialogNicknames.initDialogNicknames(getMessages().getVal(CST_PLAYERS), this);
//        pseudosJoueurs=DialogNicknames.getPseudos(getDialogNicknames());
//        pseudosJoueurs.sauvegarder(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.PLAYERS),getStreams());
//        containerGame.setNicknames(pseudosJoueurs);
//    }
//    public void manageSoft(String _key) {
//        netg.manageSoft(this,_key);
////        DialogSoft.initDialogSoft(getMessages().getVal(_key), this);
////        DialogSoft.setDialogSoft(_key, this);
////        parametres=DialogSoft.getParametres(getDialogSoft());
////        parametres.sauvegarder(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.PARAMS),getStreams());
////        containerGame.setSettings(parametres);
//    }
    public void manageLanguage() {
        netg.manageLanguage(this,this, languageDialogButtons);
//        if (!canChangeLanguageAll()) {
//            FrameUtil.showDialogError(this, GuiConstants.ERROR_MESSAGE);
//            return;
//        }
//        LanguageDialog.setLanguageDialog(this, getMessages().getVal(CST_LANGUAGE));
//        String langue_ = LanguageDialog.getStaticLanguage(getLanguageDialog());
//        LanguageDialog.changeLanguage(langue_,getFrames(),LaunchingCards.getTempFolder(getFrames()));
    }
    public void displayingGame(GameEnum _game) {
        netg.displayingGame(this,_game);
//        String lg_ = getLanguageKey();
//        if (_game == GameEnum.BELOTE) {
//            DialogDisplayingBelote.setDialogDisplayingBelote(_game.toString(lg_), this);
//            displayingBelote=DialogDisplayingBelote.getDisplaying(getDialogDisplayingBelote());
//            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.DISPLAY_BELOTE), DocumentWriterBeloteUtil.setDisplayingBelote(displayingBelote),getStreams());
//            containerGame.setDisplayingBelote(displayingBelote);
//        } else if (_game == GameEnum.PRESIDENT) {
//            DialogDisplayingPresident.setDialogDisplayingPresident(_game.toString(lg_), this);
//            displayingPresident=DialogDisplayingPresident.getDisplaying(getDialogDisplayingPresident());
//            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.DISPLAY_PRESIDENT), DocumentWriterPresidentUtil.setDisplayingPresident(displayingPresident),getStreams());
//            containerGame.setDisplayingPresident(displayingPresident);
//        } else if (_game == GameEnum.TAROT) {
//            DialogDisplayingTarot.setDialogDisplayingTarot(_game.toString(lg_), this);
//            displayingTarot=DialogDisplayingTarot.getDisplaying(getDialogDisplayingTarot());
//            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.DISPLAY_TAROT), DocumentWriterTarotUtil.setDisplayingTarot(displayingTarot),getStreams());
//            containerGame.setDisplayingTarot(displayingTarot);
//        }
    }
    //private JMenu help;
//    private void initHelpMenu() {
//        /* Aide */
//        help=getCompoFactory().newMenu(getMessages().getVal(CST_HELP));
//        /* Aide/Aide generale Explication du fonctionnement du logiciel et des regles utilisables*/
//        generalHelp=getCompoFactory().newMenuItem(getMessages().getVal(CST_GENERAL_HELP));
//        MenuItemUtils.setEnabledMenu(generalHelp,false);
//        generalHelp.addActionListener(new DisplayHelpEvent(this));
//        generalHelp.setAccelerator(F_THREE);
//        help.addMenuItem(generalHelp);
//        getJMenuBar().add(help);
//
//    }

//    public void displayHelp() {
//        //On indique a l utilisatteur comment utiliser le logiciel et jouer aux cartes
//        if (!helpFrames.isEmpty()) {
//            if (!helpFrames.first().isVisible()) {
//                helpFrames.first().setTitle(getMessages().getVal(CST_GENERAL_HELP));
//                helpFrames.first().initialize(this);
//            }
//            return;
//        }
////        if (helpInitializerThread == null || helpInitializerThread.isAlive() || helpInitializerTask == null) {
////            return;
////        }
//        FrameGeneralHelp aide_=new FrameGeneralHelp(getMessages().getVal(CST_GENERAL_HELP),this);
//        aide_.initialize(this);
//        helpFrames.add(aide_);
//    }

    /**Initialise la barre de menus*/
    private void initMenus() {
        setJMenuBar(getCompoFactory().newMenuBar());
        initFileMenu();
        initDealMenu();
        initParametersMenu();
//        initHelpMenu();
    }

//    private int confirm(String _message,String _titre) {
//        //warning message
//        return getConfirmDialogAns().input(getCommonFrame(),_message,_titre, getLanguageKey(),GuiConstants.YES_NO_CANCEL_OPTION);
//    }
    /**Sauvegarder une partie dans un fichier*/
//    private String dialogueFichierSauvegarde() {
//        String fichier_;
//        if (isSaveHomeFolder()) {
//            fichier_=getFileSaveDialogInt().input(getCommonFrame(), getLanguageKey(), true, FileConst.GAME_EXT, getFrames().getHomePath());
//        } else {
//            fichier_=getFileSaveDialogInt().input(getCommonFrame(), getLanguageKey(), true, FileConst.GAME_EXT, EMPTY_STRING);
//        }
//        if (fichier_ == null) {
//            fichier_ = EMPTY_STRING;
//        }
//        return fichier_;
//    }
//    /**Sauvegarder une partie dans un fichier*/
//    private String dialogueFichierChargement() {
//        String fichier_;
//        if (isSaveHomeFolder()) {
//            fichier_=getFileOpenDialogInt().input(getCommonFrame(),getLanguageKey(),true, FileConst.GAME_EXT, getFrames().getHomePath());
//        } else {
//            fichier_=getFileOpenDialogInt().input(getCommonFrame(),getLanguageKey(),true, FileConst.GAME_EXT, EMPTY_STRING);
//        }
//        if (fichier_ == null) {
//            fichier_ = EMPTY_STRING;
//        }
//        return fichier_;
//    }
//    /**Editer une partie de belote*/
//    private void editeurBelote() {
//        EditorBelote.initEditorBelote(this);
//    }
//    /**Editer une partie de tarot*/
//    private void editeurPresident() {
//        EditorPresident.initEditorPresident(this);
//    }
//    /**Editer une partie de tarot*/
//    private void editeurTarot() {
//        EditorTarot.initEditorTarot(this);
//    }
//    private void erreurDeChargement(String _fichier) {
//        //The issue of quality of game are caught here
//        String lg_ = getLanguageKey();
//        String mes_ = StringUtil.simpleStringsFormat(getMessages().getVal(CST_FILE_NOT_LOADED), _fichier);
//        getFrames().getMessageDialogAbs().input(getCommonFrame(),mes_, getMessages().getVal(CST_FILE_NOT_LOADED_TILE),lg_, GuiConstants.ERROR_MESSAGE);
//    }

    /**On ecoute les boutons du menu principal et des menus jeux*/
    public void beginGame(GameEnum _jeuBouton) {
//        if(_jeuBouton==GameEnum.BELOTE) {
//            containerGame = new ContainerSingleBelote(this);
//        } else if(_jeuBouton==GameEnum.PRESIDENT) {
//            containerGame = new ContainerSinglePresident(this);
//        } else if(_jeuBouton==GameEnum.TAROT) {
//            containerGame = new ContainerSingleTarot(this);
//        }
//        MenuItemUtils.setEnabledMenu(change,true);
//        ((ContainerSingle)containerGame).modify();
        /*if (single) {
            if(_jeuBouton==GameEnum.BELOTE) {
                containerGame = new ContainerSingleBelote(this);
            } else if(_jeuBouton==GameEnum.PRESIDENT) {
                containerGame = new ContainerSinglePresident(this);
            } else if(_jeuBouton==GameEnum.TAROT) {
                containerGame = new ContainerSingleTarot(this);
            }
            MenuItemUtils.setEnabledMenu(change,true);
            ((ContainerSingle)containerGame).modify();
            return;
        }
        */
        String fileName_ = StringUtil.concat(StreamFolderFile.getCurrentPath(getFileCoreStream()), MessagesCardGames.getAppliFilesTr(getFrames().getTranslations()).val().getMapping().getVal(MessagesCardGames.PORT_INI));
        int port_ = NetCreate.tryToGetPort(fileName_, Net.getPort(), getStreams());
        DialogServerContent.setDialogServer(this,_jeuBouton,port_);
//        ResultCardsServer result_ = getResultCardsServerInteract().interact(this, _jeuBouton);
//        if (result_ == null) {
//            return;
//        }
//        setCards(true);
//        GameEnum choosenGameMultiPlayers_ = _jeuBouton;
//        if (choosenGameMultiPlayers_ == GameEnum.TAROT) {
//            netg.setContainerGame(new ContainerMultiTarot(this, result_.isCreate(), result_.getNbPlayers()));
//        } else if (choosenGameMultiPlayers_ == GameEnum.PRESIDENT) {
//            netg.setContainerGame(new ContainerMultiPresident(this, result_.isCreate(), result_.getNbPlayers()));
//        } else if (choosenGameMultiPlayers_ == GameEnum.BELOTE) {
//            netg.setContainerGame(new ContainerMultiBelote(this, result_.isCreate(), result_.getNbPlayers()));
//        }
//        String fileName_ = StringUtil.concat(StreamFolderFile.getCurrentPath(getFileCoreStream()), FileConst.PORT_INI);
//        int port_ = NetCreate.tryToGetPort(fileName_, Net.getPort(),getFileCoreStream(),getStreams());
//        if (result_.isCreate()) {
//            int nbChoosenPlayers_ = result_.getNbPlayers();
//            Net.setNbPlayers(nbChoosenPlayers_, getNet());
//            createServer(result_.getIp(), result_.getIpType(), port_);
//            return;
//        }
//        SocketResults connected_ = createClient(result_.getIp(), result_.getIpType(), false, port_);
//        if (connected_.getError() != ErrorHostConnectionType.NOTHING) {
//            netg.setContainerGame(noGame());
//            StringMap<String> mapping_ = NetWork.getMessages(NetWork.getAppliTr(getFrames().currentLg())).getMapping();
//            if (connected_.getError() == ErrorHostConnectionType.UNKNOWN_HOST) {
//                String formatted_ = mapping_.getVal(MessagesNetWork.UNKNOWN_HOST);
//                formatted_ = StringUtil.simpleStringsFormat(formatted_, result_.getIp());
//                getFrames().getMessageDialogAbs().input(getCommonFrame(), mapping_.getVal(MessagesNetWork.BUG), formatted_, GuiConstants.ERROR_MESSAGE);
//                return;
//            }
//            getFrames().getMessageDialogAbs().input(getCommonFrame(), mapping_.getVal(MessagesNetWork.BUG), mapping_.getVal(MessagesNetWork.NOT_CONNECTED), GuiConstants.ERROR_MESSAGE);
//            return;
//        }
    }
    public String pseudo() {
        return netg.getFacadeCards().getPseudosJoueurs().getPseudo();
    }

//    @Override
//    public boolean canChangeLanguage() {
//        if (!netg.getContainerGame().isSimple()) {
//            return false;
//        }
////        if (!helpFrames.isEmpty()) {
////            return !helpFrames.first().isVisible();
////        }
//        return true;
//    }

    @Override
    public void changeLanguage(String _language) {
        AbstractProgramInfos infos_ = getFrames();
        String value_ = StringUtil.nullToEmpty(_language);
        StreamLanguageUtil.saveLanguage(WindowCards.getTempFolder(getFrames()), value_,infos_.getStreams());
        setLanguageKey(_language);
        translate();
    }

    private void translate() {
        TranslationsLg lg_ = getFrames().currentLg();
        StringMap<String> fileMenu_ = MessagesPkGame.getWindowPkContentTr(MessagesPkGame.getAppliTr(getFrames().currentLg())).getMapping();
        aiki.getZipLoad().setText(fileMenu_.getVal(MessagesRenderWindowPk.ZIP_LOAD));
        aiki.getFolderLoad().setText(fileMenu_.getVal(MessagesRenderWindowPk.FOLDER_LOAD));
        aiki.getGameLoad().setText(fileMenu_.getVal(MessagesRenderWindowPk.GAME_LOAD));
        aiki.getGameSave().setText(fileMenu_.getVal(MessagesRenderWindowPk.GAME_SAVE));
        file.setText(fileMenu_.getVal(MessagesRenderWindowPk.CST_FILE));
//        load.setText(getMessages().getVal(CST_LOAD));
//        save.setText(getMessages().getVal(CST_SAVE));
//        change.setText(getMessages().getVal(CST_CHANGE));
        exit.setText(getMenusMessages().getVal(MessagesGuiCards.CST_EXIT));
        deal.setText(getMenusMessages().getVal(MessagesGuiCards.CST_DEAL));
//        consulting.setText(getMessages().getVal(CST_CONSULTING));
//        pause.setText(getMessages().getVal(CST_PAUSE));
//        helpGame.setText(getMessages().getVal(HELP_GAME));
        tricksHands.setText(getMenusMessages().getVal(MessagesGuiCards.CST_TRICKS_HANDS));
        teams.setText(getMenusMessages().getVal(MessagesGuiCards.CST_TEAMS));
//        edit.setText(getMessages().getVal(CST_EDIT));
//        for (GameEnum g: GameEnum.values()) {
//            editGames.getVal(g).setText(g.toString(lg_));
//        }
//        demo.setText(getMessages().getVal(CST_DEMO));
//        for (GameEnum g: GameEnum.values()) {
//            demoGames.getVal(g).setText(g.toString(lg_));
//        }
//        training.setText(getMessages().getVal(CST_TRAINING));
//        for (ChoiceTarot c: ChoiceTarot.values()) {
//            trainingTarot.getVal(c).setText(Games.toString(c,lg_));
//        }
        multiStop.setText(MessagesNetWork.getMessages(MessagesNetWork.getAppliTr(getFrames().currentLg())).getMapping().getVal(MessagesNetWork.CST_MULTI_STOP));
        parameters.setText(getMenusMessages().getVal(MessagesGuiCards.CST_PARAMETERS));
//        for (GameEnum g: GameEnum.values()) {
//            rulesGames.getVal(g).setText(g.toString(lg_));
//        }
//        players.setText(getMessages().getVal(CST_PLAYERS));
//        launching.setText(getMessages().getVal(CST_LAUNCHING));
//        netg.getTiming().setText(getMessages().getVal(CST_TIMING));
//        netg.getInteract().setText(getMessages().getVal(CST_INTERACT));
//        netg.getLanguage().setText(getMessages().getVal(CST_LANGUAGE));
        languageDialogButtons.translate(getMenusMessages().getVal(MessagesGuiCards.CST_LANGUAGE));
        netg.getDisplaying().setText(getMenusMessages().getVal(MessagesGuiCards.CST_DISPLAYING));
        WindowCards.translateDisplaying(lg_,netg);
//        for (GameEnum g: GameEnum.allValid()) {
//            netg.getDisplayingGames().getVal(g).setText(g.toString(lg_));
//        }
//        help.setText(getMessages().getVal(CST_HELP));
//        generalHelp.setText(getMessages().getVal(CST_GENERAL_HELP));
//        if (welcomeLabel != null) {
//            welcomeLabel.setText(StringUtil.simpleStringsFormat(getMenusMessages().getVal(MessagesGuiCards.CST_WELCOME), pseudo()));
//        }
//        if (singleModeButton != null) {
//            singleModeButton.setText(getMessages().getVal(CST_SINGLE_MODE));
//        }
        multiModeButton.setText(getMenusMessages().getVal(MessagesGuiCards.CST_MULTI_MODE));
        scenePanel.initMessages();
//        if (goHelpMenu != null) {
//            goHelpMenu.setText(getMessages().getVal(CST_GO_HELP_MENU));
//        }
        //StringUtil.simpleStringsFormat(messages.getVal(MessagesRenderWindowPk.LAST_SAVED_GAME), dateLastSaved)
        aiki.getLastSavedGameDate().setText(StringUtil.simpleStringsFormat(MessagesPkGame.getWindowPkContentTr(MessagesPkGame.getAppliTr(getFrames().currentLg())).getMapping().getVal(MessagesRenderWindowPk.LAST_SAVED_GAME), aiki.getDateLastSaved()));
    }

    @Override
    public boolean updateConf() {
        return false;
    }

    @Override
    public void loadZip(boolean _f) {
//        if (!NumberUtil.eq(indexInGame, IndexConstants.INDEX_NOT_FOUND_ELT)) {
//            return;
//        }
        loadPhase(_f);
//        String fileName_;
//        if (_f) {
//            fileName_ = StringUtil.nullToEmpty(getFolderOpenDialogInt().input(getCommonFrame(), false));
//        } else {
//            fileName_ = fileDialogLoad(Resources.ZIPPED_DATA_EXT, true);
//        }
//        if (fileName_.isEmpty()) {
//            return;
//        }
//        AbstractAtomicIntegerCoreAdd p_ = getThreadFactory().newAtomicInteger();
//        loadFlag.set(true);
//        LoadingThreadMulti load_ = new LoadingThreadMulti(this, fileName_,p_);
//        getThreadFactory().newStartedThread(load_);
    }

    public void loadPhase(boolean _folder) {
//        String fileName_;
        if (_folder) {
            FolderOpenFrame.setFolderOpenDialog(false,getFileOpenFolderFrame(),new DefButtonsOpenFolderPanelAct(new PkContinueRomFile(this)));
//            fileName_ = StringUtil.nullToEmpty(getFolderOpenDialogInt().input(getCommonFrame(), false));
        } else {
            FileOpenFrame.setFileSaveDialogByFrame(true, StreamFolderFile.getCurrentPath(getFileCoreStream()), getFileOpenRomFrame(), new DefButtonsOpenPanelAct(new PkContinueRomFile(this)));
//            fileName_ = fileDialogLoad(Resources.ZIPPED_DATA_EXT, true);
        }
//        if (fileName_.isEmpty()) {
//            return;
//        }
//        loadRom(fileName_);
    }
//    private String fileDialogLoad(String _ext, boolean _zipFile) {
//        String path_;
//        if (_zipFile) {
//            if (loadingConf != null && loadingConf.isLoadHomeFolder()) {
//                path_=getFileOpenDialogInt().input(getCommonFrame(),getLanguageKey(),true, _ext, getFrames().getHomePath());
//            } else {
//                path_=getFileOpenDialogInt().input(getCommonFrame(), true, _ext, StreamFolderFile.getCurrentPath(getFileCoreStream()));
//            }
//            FileOpenDialog.setFileOpenDialog(this,Constants.getLanguage(),true, _ext, SoftApplication.getFolderJarPath(), Resources.EXCLUDED);
//        } else {
//            if (loadingConf.isSaveHomeFolder()) {
//                path_=getFileOpenDialogInt().input(getCommonFrame(),getLanguageKey(),true, _ext, getFrames().getHomePath());
//            } else {
//                path_=getFileOpenDialogInt().input(getCommonFrame(), true, _ext, DataBase.EMPTY_STRING);
//            }
//        }
//        return StringUtil.nullToEmpty(path_);
//    }

    public void loadGame() {
//        if (!NumberUtil.eq(indexInGame, IndexConstants.INDEX_NOT_FOUND_ELT)) {
//            return;
//        }
        FileOpenFrame.setFileSaveDialogByFrame(true, DataBase.EMPTY_STRING, getFileOpenRomFrame(), new DefButtonsOpenPanelAct(new PkContinueGameFile(this)));
//        if (!savedGame && facade.getGame() != null) {
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
//            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingPokemon.getTempFolderSl(getFrames()),Resources.LOAD_CONFIG_FILE), DocumentWriterAikiCoreUtil.setLoadingGame(loadingConf),getStreams());
//        }
//        String fileName_ = fileDialogLoad(Resources.GAME_EXT, false);
//        if (fileName_.isEmpty()) {
//            return;
//        }
//        boolean error_ = false;
//        DataBase db_ = facade.getData();
//        Game game_ = DefGamePkStream.checkGame(db_,facade.getSexList(), aiki.getAikiFactory().getGamePkStream().load(fileName_, facade.getSexList()));
//        if (game_ != null) {
//            facade.load(game_);
//            MenuItemUtils.setEnabledMenu(aiki.getGameSave(),true);
//            facade.changeCamera();
//            drawGame();
////            savedGame = true;
////            if (battle != null) {
////                battle.resetWindows();
////            }
//        } else {
//            error_ = true;
//        }
//        if (error_) {
//            showErrorMessageDialog(fileName_);
//        }
    }

    @Override
    public void reset() {
        drawGame();
    }


//    public boolean showErrorMessageDialog(String _fileName) {
//        if (_fileName.isEmpty()) {
//            return false;
//        }
//        StringMap<String> mapping_ = GamesPk.getWindowPkContentTr(GamesPk.getAppliTr(getFrames().currentLg())).getMapping();
//        errorsFile.display(mapping_.getVal(MessagesRenderWindowPk.ERROR_LOADING),_fileName);
//        getFrames().getMessageDialogAbs().input(getCommonFrame(), _fileName, messages.getVal(ERROR_LOADING), GuiConstants.ERROR_MESSAGE);
//        return true;
//    }

    public void showSuccessfulMessageDialogThenLoadHelp(String _fileName) {
        StringMap<String> mapping_ = MessagesPkGame.getWindowPkContentTr(MessagesPkGame.getAppliTr(getFrames().currentLg())).getMapping();
        errorsFile.display(mapping_.getVal(MessagesRenderWindowPk.SUCCESSFUL_LOADING),_fileName);
//        getFrames().getMessageDialogAbs().input(getCommonFrame(), _fileName, messages.getVal(SUCCESSFUL_LOADING), GuiConstants.INFORMATION_MESSAGE);
//        availableHelps.setText(messages.getVal(MessagesRenderWindowPk.AVAILAIBLE_HELPS));
//        helpInfo.setText(messages.getVal(MessagesRenderWindowPk.HELP_INFO));
        pack();
//        SecurityManagerUtil.setForbiddenCalls(DataBase.getBeansPackage());
//        ForwardingJavaCompiler.startCompiling();
    }
//    public boolean showErrorMessageDialog(String _fileName) {
//        if (_fileName.isEmpty()) {
//            return false;
//        }
//        getFrames().getMessageDialogAbs().input(getCommonFrame(), _fileName, messagesAiki.getVal(ERROR_LOADING), GuiConstants.ERROR_MESSAGE);
//        return true;
//    }

    private void drawGame() {
        scenePanel.setMessages();
//        if (facade.isChangeToFightScene()) {
//            if (battle != null) {
//                battle.setWild(false);
//            }
//            setFight(false, false);
//            return;
//        }
        drawGameWalking();
        pack();
    }
    public void drawGameWalking() {
//        MenuItemUtils.setEnabledMenu(difficulty,true);
//        battle.setVisibleFrontBattle(false);
        scenePanel.getComponent().setVisible(true);
//        inBattle = false;
//        MenuItemUtils.setEnabledMenu(dataBattle,false);
        scenePanel.drawGameWalking();
    }
    public void saveGame() {
        FileSaveFrame.setFileSaveDialogByFrame(true,DataBase.EMPTY_STRING,getFileSaveFrame(),new DefButtonsSavePanelAct(new PkSaveSimpleFile(this),new PkContinueFile(this)));
//        String fileName_ = fileDialogSave();
//        if (fileName_.isEmpty()) {
//            return;
//        }
//        save(fileName_);
//        fileName_ = StringUtil.replaceBackSlash(fileName_);
//        loadingConf.setLastSavedGame(fileName_);
//        dateLastSaved = Clock.getDateTimeText(getThreadFactory());
//        lastSavedGameDate.setText(StringUtil.simpleStringsFormat(GamesPk.getWindowPkContentTr(GamesPk.getAppliTr(getFrames().currentLg())).getMapping().getVal(MessagesRenderWindowPk.LAST_SAVED_GAME), dateLastSaved));
//        savedGame = true;
    }
//    private String fileDialogSave() {
//        String path_;
//        boolean saveConfig_ = false;
//        if (loadingConf.isSaveHomeFolder()) {
//            saveConfig_ = true;
//            path_=getFileSaveDialogInt().input(getCommonFrame(), getLanguageKey(), true, Resources.GAME_EXT, getFrames().getHomePath());
//        } else {
//            path_=getFileSaveDialogInt().input(getCommonFrame(), true, Resources.GAME_EXT, DataBase.EMPTY_STRING);
//        }
//        } else if (saveConfig_) {
//            loadingConf.setLastSavedGame(path_);
//            loadingConf.setLastRom(facade.getZipName());
//            String configPath_ = StringUtil.replaceExtension(path_, Resources.GAME_EXT, Resources.CONF_EXT);
//            //String configPath_ = path_.replaceAll(StringList.quote(Resources.GAME_EXT)+StringList.END_REG_EXP, Resources.CONF_EXT);
//            StreamTextFile.saveTextFile(configPath_, DocumentWriterAikiCoreUtil.setLoadingGame(loadingConf),getStreams());
//            //configPath_ +=
//        return StringUtil.nullToEmpty(path_);
//    }
    public void save(String _fileName) {
        aiki.save(_fileName);
//        Game game_ = facade.getGame();
//        if (game_ == null) {
//            return;
//        }
//        game_.setZippedRom(facade.getZipName());
//        aiki.getAikiFactory().getGamePkStream().save(_fileName,game_);
//        dateLastSaved = Clock.getDateTimeText(getThreadFactory());
//        lastSavedGameDate.setText(StringUtil.simpleStringsFormat(GamesPk.getWindowPkContentTr(GamesPk.getAppliTr(getFrames().currentLg())).getMapping().getVal(MessagesRenderWindowPk.LAST_SAVED_GAME), dateLastSaved));
    }
    public void sendObjectOk() {
        trySendString(NetAiki.exportServerOk(),getSocket());
    }
    public void sendObject(QuitAiki _serializable) {
        trySendString(NetAiki.exportQuitAiki(_serializable),getSocket());
    }
    public void sendReady() {
        ReadyAiki choice_ = new ReadyAiki();
        choice_.setIndex(getIndexInGame());
        scenePanel.readyTrade();
        choice_.getContent().setReady(scenePanel.isReadyTrade());
        trySendString(NetAiki.exportReadyAiki(choice_),getSocket());
    }
    public void sendObject(SentPokemon _serializable) {
        trySendString(NetAiki.exportSentPokemon(_serializable), getSocket());
    }
    @Override
    public AbstractSocket initIndexInGame(AbstractSocket _socket) {
        basicClient = new BasicClient(_socket, this);
        getThreadFactory().newStartedThread(getBasicClient());
        return _socket;
    }

    public BasicClient getBasicClient() {
        return basicClient;
    }

    public ContainerNoGame noGame() {
        ContainerNoGame c_ = new ContainerNoGame(this);
        update(c_);
//        c_.setReglesTarot(getReglesTarot());
//        c_.setReglesPresident(getReglesPresident());
//        c_.setReglesBelote(getReglesBelote());
//        c_.setParametres(getParametresLogiciel());
//        c_.setDisplayingBelote(getDisplayingBelote());
//        c_.setDisplayingPresident(getDisplayingPresident());
//        c_.setDisplayingTarot(getDisplayingTarot());
//        c_.setMessages(getMessages());
//        c_.setPseudosJoueurs(getPseudosJoueurs());
        return c_;
    }
    public void update(ContainerGame _cont) {
        _cont.setPseudosJoueurs(getPseudosJoueurs());
//        _cont.setMessages(getMessages());
    }
//    public boolean isSaveHomeFolder() {
//        return parametres.isSaveHomeFolder();
//    }
//    public String save(AbsDialog _d) {
//        String lg_ = getLanguageKey();
//        String fichier_;
//        if (isSaveHomeFolder()) {
//            fichier_=getFileSaveDialogInt().input(getCommonFrame(),_d, lg_, true, FileConst.GAME_EXT, getFrames().getHomePath());
//        } else {
//            fichier_=getFileSaveDialogInt().input(getCommonFrame(),_d, lg_, true, FileConst.GAME_EXT, EMPTY_STRING);
//        }
//        if (fichier_ == null) {
//            fichier_ = EMPTY_STRING;
//        }
//        return fichier_;
//    }

//    public void setSingle(boolean _single) {
//        single = _single;
//    }

//    public AbsMenu getFile() {
//        return file;
//    }

//    public AbsMenuItem getLoad() {
//        return load;
//    }
//
//    public AbsMenuItem getSave() {
//        return save;
//    }

//    public EnabledMenu getChange() {
//        return change;
//    }

    public EnabledMenu getExit() {
        return exit;
    }

//    public AbsMenu getDeal() {
//        return deal;
//    }

//    public AbsMenuItem getConsulting() {
//        return consulting;
//    }

//    public AbsCheckBoxMenuItem getPause() {
//        return pause;
//    }

//    public AbsMenuItem getHelpGame() {
//        return helpGame;
//    }

    public EnabledMenu getTricksHands() {
        return tricksHands;
    }

    public EnabledMenu getTeams() {
        return teams;
    }

//    public AbsMenu getEdit() {
//        return edit;
//    }

//    public IdMap<GameEnum,AbsMenuItem> getEditGames() {
//        return editGames;
//    }

//    public AbsMenu getDemo() {
//        return demo;
//    }

//    public IdMap<GameEnum,AbsMenuItem> getDemoGames() {
//        return demoGames;
//    }

//    public AbsMenu getTraining() {
//        return training;
//    }

//    public IdMap<ChoiceTarot,AbsMenuItem> getTrainingTarot() {
//        return trainingTarot;
//    }

    public EnabledMenu getMultiStop() {
        return multiStop;
    }

//    public AbsMenu getParameters() {
//        return parameters;
//    }

//    public IdMap<GameEnum,AbsMenuItem> getRulesGames() {
//        return rulesGames;
//    }

//    public AbsMenuItem getPlayers() {
//        return players;
//    }

//    public AbsMenuItem getLaunching() {
//        return launching;
//    }

//    public AbsMenuItem getTiming() {
//        return timing;
//    }

//    public AbsMenuItem getInteract() {
//        return interact;
//    }

//    public AbsMenuItem getLanguage() {
//        return language;
//    }

//    public EnabledMenu getDisplaying() {
//        return netg.getDisplaying();
//    }

//    public IdMap<GameEnum,AbsMenuItem> getDisplayingGames() {
//        return displayingGames;
//    }

//    public AbsMenu getHelp() {
//        return help;
//    }

//    public AbsMenuItem getGeneralHelp() {
//        return generalHelp;
//    }

    @Override
    public String getApplicationName() {
        return MessagesNetWork.APPS_NETWORK;
    }

    public Net getNet() {
        return net;
    }

//    @Override
//    public StringMap<AbstractFutureParam<CardNatLgNamesNavigation>> getPrepared() {
//        return netg.getPrepared();
//    }

//    public void setPrepare(StringMap<AbstractFutureParam<CardNatLgNamesNavigation>> _i) {
//        this.netg.setPrepare(_i);
//    }

    //    public HelpInitializer getHelpInitializerTask() {
//        return helpInitializerTask;
//    }
//
//    public void setHelpInitializerTask(HelpInitializer _helpInitializerTask) {
//        this.helpInitializerTask = _helpInitializerTask;
//    }

    public DialogDisplayingBelote getDialogDisplayingBelote() {
        return netg.getDialogDisplayingBelote();
    }

    public DialogDisplayingTarot getDialogDisplayingTarot() {
        return netg.getDialogDisplayingTarot();
    }

    public DialogDisplayingPresident getDialogDisplayingPresident() {
        return netg.getDialogDisplayingPresident();
    }

//    public DialogHelpBelote getDialogHelpBelote() {
//        return netg.getDialogHelpBelote();
//    }
//
//    public DialogHelpPresident getDialogHelpPresident() {
//        return netg.getDialogHelpPresident();
//    }
//
//    public DialogHelpTarot getDialogHelpTarot() {
//        return netg.getDialogHelpTarot();
//    }

    public DialogTricksBelote getDialogTricksBelote() {
        return netg.getDialogTricksBelote();
    }

    public DialogTricksPresident getDialogTricksPresident() {
        return netg.getDialogTricksPresident();
    }

    public DialogTricksTarot getDialogTricksTarot() {
        return netg.getDialogTricksTarot();
    }

//    public EditorBelote getEditorBelote() {
//        return editorBelote;
//    }
//
//    public EditorPresident getEditorPresident() {
//        return editorPresident;
//    }
//
//    public EditorTarot getEditorTarot() {
//        return editorTarot;
//    }

    public DialogTeamsPlayers getDialogTeamsPlayers() {
        return netg.getDialogTeamsPlayers();
    }

//    public DialogNicknames getDialogNicknames() {
//        return dialogNicknames;
//    }

    public DialogServerContent getDialogServerContent() {
        return dialogServerContent;
    }
    //    public AbsPlainButton getSingleModeButton() {
//        return singleModeButton;
//    }

    public AbsButton getMultiModeButton() {
        return multiModeButton;
    }

//    public ResultCardsServerInteract getResultCardsServerInteract() {
//        return resultCardsServerInteract;
//    }
//
//    public void setResultCardsServerInteract(ResultCardsServerInteract _r) {
//        this.resultCardsServerInteract = _r;
//    }
    public NetAiki getNetAiki() {
        return netAiki;
    }

//    public void processLoad(String _fileName, AbstractAtomicIntegerCoreAdd _p) {
//        AbstractAtomicBooleanCore loaded_ = aiki.getAikiFactory().getDataBaseStream().loadRomAndCheck(getFrames(), aiki.getAikiFactory().getTaskLoad(), facade, _fileName, _p, loadFlag);
//        if (!loaded_.get()) {
//            return;
//        }
//        StringMap<String> files_ = StreamFolderFile.getFiles(_fileName,getFileCoreStream(),getStreams());
//        GamesPk.loadRomAndCheck(getGenerator(),facade,_fileName, files_,_p,loadFlag);
//        if (!facade.isLoadedData()) {
//            FacadeGame.postLoad(facade,aiki.getAikiFactory().getTaskLoad().attendreResultat());
//            loadFlag.set(true);
//            _p.set(100);
//        }
//        if (!loadFlag.get()) {
//            return;
//        }
//        facade.clearGame();
//        facade.initializePaginatorTranslations();
//        inBattle = false;
//        ThreadInvoker.invokeNow(getThreadFactory(),new ReinitComponents(this), getFrames());
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
//        getFrames().getCompoFactory().invokeNow(new AfterLoadZip(this));
//        loadingConf.setLastRom(_fileName);
//        pack();
//        //reInitAllSession
//        for (FrameHtmlData f: htmlDialogs) {
//            f.reInitAllSession(Resources.CONFIG_DATA, facade.getLanguage(), facade.getData(), facade.getData().getWebFiles());
//            f.pack();
//        }
//        if (battle != null) {
//            battle.closeWindows();
//        }
//    }

    @Override
    public void iniGui(String _fileName) {
        aiki.getFacade().clearGame();
        aiki.getFacade().initializePaginatorTranslations();
        getFrames().getCompoFactory().invokeNow(new AfterLoadZip(this));
    }

    @Override
    public IntTileRender getTileRender() {
        return aiki.getTileRender();
    }

    public void afterLoadZip() {
//        MenuItemUtils.setEnabledMenu(dataGame,true);
        aiki.getGameLoad().setEnabled(true);
        aiki.getGameSave().setEnabled(false);
//        if (exporting != null && exporting.isAlive()) {
//            return;
//        }
//        if (loadingConf == null) {
//            return;
//        }
//        AbstractNameValidating def_ = getFrames().getValidator();
//        if (!def_.okPath(StreamFolderFile.getRelativeRootPath(loadingConf.getExport(), getFileCoreStream()),'/','\\')) {
//            loadingConf.setExport("");
//        }
//        exporting = getThreadFactory().newThread(new ExportRomThread(facade,loadingConf,getThreadFactory(), getFileCoreStream(),getStreams()));
//        exporting.start();
    }

    public LanguageDialogButtons getLanguageDialogButtons() {
        return languageDialogButtons;
    }

    public FileSaveFrame getFileSaveFrame() {
        return fileSaveFrame;
    }

    public FileOpenFrame getFileOpenRomFrame() {
        return fileOpenRomFrame;
    }

    public FolderOpenFrame getFileOpenFolderFrame() {
        return fileOpenFolderFrame;
    }

    public EnabledMenu getFolderLoad() {
        return aiki.getFolderLoad();
    }

    public EnabledMenu getZipLoad() {
        return aiki.getZipLoad();
    }

    public EnabledMenu getGameLoad() {
        return aiki.getGameLoad();
    }

    public AbstractAtomicBooleanCore getLoadFlag() {
        return loadFlag;
    }

    @Override
    public FacadeGame facade() {
        return aiki.getFacade();
    }

    @Override
    public WindowAikiCore common() {
        return aiki;
    }

    @Override
    public ProgressingDialog progressDial() {
        return dialog;
    }

    public AbstractAtomicBooleanCore getModal() {
        return modal;
    }

    @Override
    public StringMap<int[][]> getVideoBase() {
        return aiki.getVideoBase();
    }

    public boolean isCards() {
        return cards;
    }

    public void setCards(boolean _c) {
        this.cards = _c;
    }

    public LanguagesButtonsPair getButtonClick() {
        return buttonClick;
    }

//    public void setButtonClick(AbsButton _b) {
//        this.buttonClick = _b;
//    }

    public static StringMap<String> readCoreResourceMix(ContainerSingleImpl _cs) {
        return MessagesCardGames.getCommonMixTr(_cs.readResourceAppli()).getMapping();
//        return ResourceFiles.ressourceFichier(StringUtil.concat(CoreResourcesAccess.NOM_DOSSIER,ResourceFiles.SEPARATEUR,getOwner().getLanguageKey(),ResourceFiles.SEPARATEUR,CoreResourcesAccess.NOM_FICHIER));
    }
    @Override
    public WindowCardsCore baseWindow() {
        return getNetg();
    }

    public WindowCardsCore getNetg() {
        return netg;
    }

    public WindowAikiCore getAiki() {
        return aiki;
    }

    public ScenePanelMulti getScenePanel() {
        return scenePanel;
    }

//    public AbsActionListenerAct getGuardRender() {
//        return guardRender;
//    }
}
