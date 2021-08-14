package cards.gui;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import cards.belote.CheckerGameBeloteWithRules;
import cards.belote.DisplayingBelote;
import cards.belote.GameBelote;
import cards.belote.ResultsBelote;
import cards.belote.RulesBelote;
import cards.belote.TricksHandsBelote;
import cards.belote.sml.DocumentReaderBeloteUtil;
import cards.belote.sml.DocumentWriterBeloteUtil;
import cards.consts.MixCardsChoice;
import cards.enumerations.Launching;
import cards.facade.Games;
import cards.facade.Nicknames;
import cards.facade.SoftParams;
import cards.facade.enumerations.GameEnum;
import cards.facade.sml.DocumentReaderCardsUnionUtil;
import cards.gui.animations.HelpInitializer;
import cards.gui.animations.PreparedPagesCards;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerMulti;
import cards.gui.containers.ContainerMultiBelote;
import cards.gui.containers.ContainerMultiPresident;
import cards.gui.containers.ContainerMultiTarot;
import cards.gui.containers.ContainerSimuBelote;
import cards.gui.containers.ContainerSimuPresident;
import cards.gui.containers.ContainerSimuTarot;
import cards.gui.containers.ContainerSingleBelote;
import cards.gui.containers.ContainerSinglePresident;
import cards.gui.containers.ContainerSingleTarot;
import cards.gui.dialogs.*;
import cards.gui.events.BackToMainMenuEvent;
import cards.gui.events.ChooseModeEvent;
import cards.gui.events.ListenerBeginGame;
import cards.gui.menus.ChangeGameEvent;
import cards.gui.menus.ConsultEvent;
import cards.gui.menus.DisplayHelpEvent;
import cards.gui.menus.DisplayHelpGameEvent;
import cards.gui.menus.DisplayTeamsEvent;
import cards.gui.menus.DisplayTricksHandsEvent;
import cards.gui.menus.DisplayingGameEvent;
import cards.gui.menus.EditEvent;
import cards.gui.menus.ListenerTrainingTarot;
import cards.gui.menus.LoadGameEvent;
import cards.gui.menus.ManageLanguageEvent;
import cards.gui.menus.ManageNicknameEvent;
import cards.gui.menus.ManageRulesEvent;
import cards.gui.menus.ManageSoftEvent;
import cards.gui.menus.PauseEvent;
import cards.gui.menus.QuitEvent;
import cards.gui.menus.QuitMultiEvent;
import cards.gui.menus.SaveGameEvent;
import cards.gui.menus.SimulationEvent;
import cards.main.CardFactories;
import cards.main.LaunchingCards;
import cards.network.belote.actions.BiddingBelote;
import cards.network.belote.actions.PlayingCardBelote;
import cards.network.belote.displaying.DealtHandBelote;
import cards.network.belote.displaying.RefreshHandBelote;
import cards.network.belote.displaying.errors.ErrorBiddingBelote;
import cards.network.belote.displaying.errors.ErrorPlayingBelote;
import cards.network.belote.displaying.players.RefreshHandPlayingBelote;
import cards.network.belote.unlock.AllowBiddingBelote;
import cards.network.belote.unlock.AllowPlayingBelote;
import cards.network.common.DelegateServer;
import cards.network.common.PlayerActionGame;
import cards.network.common.PlayerActionGameType;
import cards.network.common.Quit;
import cards.network.common.before.ChoosenPlace;
import cards.network.common.before.IndexOfArriving;
import cards.network.common.before.NewPlayer;
import cards.network.common.before.PlayersNamePresent;
import cards.network.common.before.Ready;
import cards.network.common.displaying.Pause;
import cards.network.common.select.TeamsPlayers;
import cards.network.president.actions.PlayingCardPresident;
import cards.network.president.displaying.DealtHandPresident;
import cards.network.president.displaying.ReceivedGivenCards;
import cards.network.president.displaying.errors.ErrorPlayingPresident;
import cards.network.president.displaying.players.RefreshHandPlayingPresident;
import cards.network.president.unlock.AllowDiscarding;
import cards.network.president.unlock.AllowPlayingPresident;
import cards.network.sml.DocumentReaderCardsMultiUtil;
import cards.network.sml.DocumentWriterCardsMultiUtil;
import cards.network.tarot.Dog;
import cards.network.tarot.actions.BiddingTarot;
import cards.network.tarot.actions.CalledCards;
import cards.network.tarot.actions.DiscardedCard;
import cards.network.tarot.actions.DiscardedTrumps;
import cards.network.tarot.actions.PlayingCardTarot;
import cards.network.tarot.displaying.DealtHandTarot;
import cards.network.tarot.displaying.errors.ErrorBidding;
import cards.network.tarot.displaying.errors.ErrorDiscarding;
import cards.network.tarot.displaying.errors.ErrorHandful;
import cards.network.tarot.displaying.errors.ErrorPlaying;
import cards.network.tarot.displaying.players.RefreshHand;
import cards.network.tarot.unlock.AllowBiddingTarot;
import cards.network.tarot.unlock.AllowPlayingTarot;
import cards.network.tarot.unlock.CallableCards;
import cards.network.tarot.unlock.DisplaySlamButton;
import cards.network.threads.Net;
import cards.network.threads.SendReceiveServer;
import cards.president.CheckerGamePresidentWithRules;
import cards.president.DisplayingPresident;
import cards.president.GamePresident;
import cards.president.ResultsPresident;
import cards.president.RulesPresident;
import cards.president.TricksHandsPresident;
import cards.president.sml.DocumentReaderPresidentUtil;
import cards.president.sml.DocumentWriterPresidentUtil;
import cards.tarot.CheckerGameTarotWithRules;
import cards.tarot.DisplayingTarot;
import cards.tarot.GameTarot;
import cards.tarot.ResultsTarot;
import cards.tarot.RulesTarot;
import cards.tarot.TricksHandsTarot;
import cards.tarot.enumerations.ChoiceTarot;
import cards.tarot.sml.DocumentReaderTarotUtil;
import cards.tarot.sml.DocumentWriterTarotUtil;
import code.gui.*;
import code.gui.events.QuittingEvent;
import code.gui.initialize.AbstractProgramInfos;
import code.gui.initialize.AbstractSocket;
import code.network.*;
import code.network.enums.ErrorHostConnectionType;
import code.network.enums.IpType;
import code.scripts.messages.gui.MessGuiCardsGr;
import code.sml.util.ResourcesMessagesUtil;
import code.stream.StreamFolderFile;
import code.stream.StreamTextFile;
import code.threads.AbstractThread;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

/**

    fenetre principale non redimensionnable
 Au premier lancement, il y une barre de menus et quatre boutons de jeu*/
public final class MainWindow extends NetGroupFrame {

    public static final String TOO_GAME = "tooGame";

    public static final String CST_LAUNCHING = "launching";

    public static final String CST_TIMING = "timing";

    public static final String CAN_PLAY = "canPlay";

    public static final String PASS_TRICK = "passTrick";

    public static final String NO_PLAY_NOW = "noPlayNow";

    public static final String ALREADY_PLAYED = "alreadyPlayed";

    public static final String ALONE_TAKER = "aloneTaker";

    public static final String ASK_SLAM = "askSlam";

    public static final String ASK_SLAM_TITLE = "askSlamTitle";

    public static final String BEGIN_DEMO = "beginDemo";

    public static final String BEGIN_PLAY_CARDS = "beginPlayCards";

    public static final String BONUS_WIN = "bonusWin";

    public static final String CALLED_PLAYER = "calledPlayer";

    public static final String CALLED_PLAYER_WARNING = "calledPlayerWarning";

    public static final String CANT_BID = "cantBid";

    public static final String CANT_BID_TITLE = "cantBidTitle";

    public static final String CANT_DECLARE_DETAIL = "cantDeclareDetail";

    public static final String CANT_DECLARE_TITLE = "cantDeclareTitle";

    public static final String CANT_DISCARD = "cantDiscard";

    public static final String CANT_PLAY = "cantPlay";

    public static final String CANT_PLAY_CARD = "cantPlayCard";

    public static final String CANT_PLAY_CARD_TITLE = "cantPlayCardTitle";

    public static final String CONSULT_BELOTE_BID = "consultBeloteBid";

    public static final String CONSULT_BELOTE_BID_POINTS = "consultBeloteBidPoints";

    public static final String CONSULT_BELOTE_BID_SUIT = "consultBeloteBidSuit";

    public static final String CONSULT_BELOTE_BID_SUIT_POINTS = "consultBeloteBidSuitPoints";

    public static final String CONSULT_BELOTE_PLAYER = "consultBelotePlayer";

    public static final String CONSULT_PRESIDENT_PLAYER = "consultPresidentPlayer";

    public static final String CONSULT_PRESIDENT_PASS = "consultPresidentPass";

    public static final String CONSULT_PRESIDENT_GIVE = "consultPresidentGive";

    public static final String CONSULT_TAROT_BID = "consultTarotBid";

    public static final String CONSULT_TAROT_CALL = "consultTarotCall";

    public static final String CONSULT_TAROT_DISCARD = "consultTarotDiscard";

    public static final String CONSULT_TAROT_PLAYER = "consultTarotPlayer";

    public static final String CONSULT_TITLE = "consultTitle";

    public static final String DEAL_REMAIN_CARDS = "dealRemainCards";

    public static final String DEAL_SET_CARDS = "dealSetCards";

    public static final String DECLARE_BID = "declareBid";

    public static final String DECLARING_SLAM = "declaringSlam";

    public static final String DECLARING_SLAM_DEMO = "declaringSlamDemo";

    public static final String DECLARING_SLAM_DEMO_DISCARD = "declaringSlamDemoDiscard";

    public static final String DEMO_ACTION = "demoAction";

    public static final String DEMO_ACTION_TWO = "demoActionTwo";

    public static final String DETAIL_RESULTS_PAGE = "detailResultsPage";

    public static final String DISCARD_CARDS = "discardCards";

    public static final String END_DEAL = "endDeal";

    public static final String END_TRICK = "endTrick";

    public static final String GO_CARD_GAME = "goCardGame";

    public static final String HANDS_TRICKS = "handsTricks";

    public static final String HANDS_TRICKS_BELOTE = "handsTricksBelote";

    public static final String HANDS_TRICKS_PRESIDENT = "handsTricksPresident";

    public static final String HANDS_TRICKS_TAROT = "handsTricksTarot";

    public static final String HAS_TO_DISCARD = "hasToDiscard";

    public static final String HAVE_TO_PLAY = "haveToPlay";

    public static final String HELP_GAME = "helpGame";

    public static final String HELP_GO_MENU = "helpGoMenu";

    public static final String KEEP_PLAYING_DEAL = "keepPlayingDeal";

    public static final String KEEP_PLAYING_EDITED_DEAL = "keepPlayingEditedDeal";

    public static final String LOADING = "loading";

    public static final String NEXT_TRICK = "nextTrick";

    public static final String NO_BID = "noBid";

    public static final String OK = "ok";

    public static final String PARTNERS_TAKER = "partnersTaker";

    public static final String PLACE = "place";

    public static final String PLAY_BELOTE = "playBelote";

    public static final String PLAY_CARD_FIRST = "playCardFirst";

    public static final String PLAY_CARD_THEN = "playCardThen";

    public static final String PLAY_PRESIDENT = "playPresident";

    public static final String PLAY_TAROT = "playTarot";

    public static final String PLAYER_HAVING_TO_PLAY = "playerHavingToPlay";

    public static final String PLAYERS_SHOW_DOG = "playersShowDog";

    public static final String READY = "ready";

    public static final String REASON = "reason";

    public static final String REMOVE_TRUMPS_HANDFUL = "removeTrumpsHandful";

    public static final String REPLAY_DEAL = "replayDeal";

    public static final String RESULTS_PAGE = "resultsPage";

    public static final String SCORES_EVOLUTION = "scoresEvolution";

    public static final String SCORES_EVOLUTION_DETAIL = "scoresEvolutionDetail";

    public static final String SEE_DOG = "seeDog";

    public static final String SELECT_RULES = "selectRules";

    public static final String SHOWN_DOG = "shownDog";

    public static final String SLAM = "slam";

    public static final String SMALL_ALONE = "smallAlone";

    public static final String SMALL_ALONE_TEXT = "smallAloneText";

    public static final String STOP = "stop";

    public static final String STOP_DEMO = "stopDemo";

    public static final String TAKE_CARDS = "takeCards";

    public static final String TAKE_DOG = "takeDog";

    public static final String TAKE_TOP_CARD = "takeTopCard";

    public static final String TAKER_CALL = "takerCall";

    public static final String TAKER_CALL_WARNING = "takerCallWarning";

    public static final String TEAM_TAKER = "teamTaker";

    public static final String TRICK_WINNER = "trickWinner";

    public static final String WAIT_TURN = "waitTurn";

    public static final String RECEIVED_CARDS = "receivedCards";

    public static final String GIVEN_CARDS = "givenCards";

    static final String ACCESSIBLE = "accessible";

    static final String BUG = "bug";

    static final String CST_CHANGE = "change";

    static final String CST_CONSULTING = "consulting";

    static final String CST_DEAL = "deal";

    static final String CST_DEMO = "demo";

    static final String CST_DISPLAYING = "displaying";

    static final String CST_EDIT = "edit";

    static final String CST_EXIT = "exit";

    static final String CST_FILE = "file";

    static final String CST_FILE_NOT_LOADED = "fileNotLoaded";

    static final String CST_FILE_NOT_LOADED_TILE = "fileNotLoadedTile";

    static final String CST_GENERAL_HELP = "generalHelp";

    static final String CST_GO_HELP_MENU = "goHelpMenu";

    static final String CST_HELP = "help";

    static final String CST_INTERACT = "interact";

    static final String CST_LANGUAGE = "language";

    static final String CST_LOAD = "load";

    static final String CST_MAIN_MENU = "mainMenu";

    static final String CST_MULTI_MODE = "multiMode";

    static final String CST_MULTI_STOP = "multiStop";

    static final String CST_PARAMETERS = "parameters";

    //static final String PARAMETRES = "parametres";

    static final String CST_PAUSE = "pause";

    static final String CST_PLAYERS = "players";

    static final String CST_SAVE = "save";

    static final String CST_SAVING = "saving";

    static final String CST_SAVING_TITLE = "savingTitle";

    static final String CST_SINGLE_MODE = "singleMode";

    static final String CST_TEAMS = "teams";

    static final String CST_TRAINING = "training";

    static final String CST_TRICKS_HANDS = "tricksHands";

    static final String CST_WELCOME = "welcome";

    private static final String DIALOG_ACCESS = "cards.gui.mainwindow";

    private static final String TOO_MANY = "tooMany";

    private static final String UNKNOWN_HOST = "unknownHost";

    private static final String NOT_CONNECTED = "notConnected";

    private static final String LOCALHOST_OLD_IP = "127.0.0.1";

    private static final String LOCALHOST_NEW_IP = "::1";

    private static final String F_ONE = "F1";
    private static final String F_TWO = "F2";
    private static final String F_THREE = "F3";
    private static final String F_FOUR = "F4";
    private static final String F_FIVE = "F5";
    private static final String F_SIX = "F6";
    private static final String EMPTY_STRING = "";
    private static final String LAST_SAVED_GAME = "lastSavedGame";

    private static final char LINE_RETURN = '\n';

    private BasicClient threadEmission;
    private StringMap<String> messages = new StringMap<String>();

    private final CustList<FrameGeneralHelp> helpFrames = new CustList<FrameGeneralHelp>();

    private ContainerGame containerGame;
    private final Clock clock;

    private final TextLabel lastSavedGameDate;

    private String dateLastSaved = EMPTY_STRING;

    /**Parametres de lancement, de jouerie*/
    private SoftParams parametres=new SoftParams();
    /**
    des pseudonymes*/
    private Nicknames pseudosJoueurs;
    private RulesBelote reglesBelote=new RulesBelote();
    private DisplayingBelote displayingBelote = new DisplayingBelote();
    private RulesPresident reglesPresident=new RulesPresident();
    private DisplayingPresident displayingPresident = new DisplayingPresident();
    private RulesTarot reglesTarot=new RulesTarot();
    private DisplayingTarot displayingTarot = new DisplayingTarot();
    /**Est vrai si et seulement si une partie vient d'etre sauvegardee*/
    private boolean partieSauvegardee;

    private boolean single = true;
    private boolean forceBye;

    //file menu

    private Menu file;
    private MenuItem load;
    private MenuItem save;
    private MenuItem change;
    private MenuItem exit;

    //deal menu

    private Menu deal;
    private MenuItem consulting;
    private CheckBoxMenuItem pause;
    private MenuItem helpGame;
    private MenuItem tricksHands;
    private MenuItem teams;
    private Menu edit;
    private final EnumMap<GameEnum,MenuItem> editGames = new EnumMap<GameEnum,MenuItem>();
    private Menu demo;
    private final EnumMap<GameEnum,MenuItem> demoGames = new EnumMap<GameEnum,MenuItem>();
    private Menu training;
    private final EnumMap<ChoiceTarot,MenuItem> trainingTarot = new EnumMap<ChoiceTarot,MenuItem>();
    private MenuItem multiStop;

    //parameters menu

    private Menu parameters;
    private final EnumMap<GameEnum,MenuItem> rulesGames = new EnumMap<GameEnum,MenuItem>();
    private MenuItem players;
    private MenuItem launching;
    private MenuItem timing;
    private MenuItem interact;
    private MenuItem language;
    private Menu displaying;
    private final EnumMap<GameEnum,MenuItem> displayingGames = new EnumMap<GameEnum,MenuItem>();

    //parameters help

    private Menu help;
    private MenuItem generalHelp;

    //labels at main menu

    private TextLabel welcomeLabel;
    private LabelButton singleModeButton;
    private LabelButton multiModeButton;
    private TextLabel goHelpMenu;
    private final Net net = new Net();

    private final StringMap<StringMap<PreparedPagesCards>> preparedBelote;
    private final StringMap<StringMap<PreparedPagesCards>> preparedPresident;
    private final StringMap<StringMap<PreparedPagesCards>> preparedTarot;
    //private final boolean standalone;
    private HelpInitializer helpInitializerTask;
    private AbstractThread helpInitializerThread;
    private final DialogDisplayingBelote dialogDisplayingBelote = new DialogDisplayingBelote();
    private final DialogDisplayingTarot dialogDisplayingTarot = new DialogDisplayingTarot();
    private final DialogDisplayingPresident dialogDisplayingPresident = new DialogDisplayingPresident();
    private final DialogHelpBelote dialogHelpBelote = new DialogHelpBelote();
    private final DialogHelpPresident dialogHelpPresident = new DialogHelpPresident();
    private final DialogHelpTarot dialogHelpTarot = new DialogHelpTarot();
    private final DialogRulesBelote dialogRulesBelote = new DialogRulesBelote();
    private final DialogRulesPresident dialogRulesPresident = new DialogRulesPresident();
    private final DialogRulesTarot dialogRulesTarot = new DialogRulesTarot();
    private final DialogTricksBelote dialogTricksBelote = new DialogTricksBelote();
    private final DialogTricksPresident dialogTricksPresident = new DialogTricksPresident();
    private final DialogTricksTarot dialogTricksTarot = new DialogTricksTarot();
    private final EditorBelote editorBelote = new EditorBelote();
    private final EditorPresident editorPresident = new EditorPresident();
    private final EditorTarot editorTarot = new EditorTarot();
    private final DialogTeamsPlayers dialogTeamsPlayers = new DialogTeamsPlayers();
    private final DialogNicknames dialogNicknames = new DialogNicknames();
    private final DialogSoft dialogSoft = new DialogSoft();
    private final DialogServer dialogServer = new DialogServer();
    private final CardFactories cardFactories;

    public MainWindow(String _lg, AbstractProgramInfos _list,
                      StringMap<StringMap<PreparedPagesCards>> _belote,
                      StringMap<StringMap<PreparedPagesCards>> _president,
                      StringMap<StringMap<PreparedPagesCards>> _tarot,
                      CardFactories _cardFactories) {
        super(_lg, _list);
        cardFactories = _cardFactories;
        preparedBelote = _belote;
        preparedPresident = _president;
        preparedTarot = _tarot;
        pseudosJoueurs=new Nicknames(getLanguageKey());
        setAccessFile(DIALOG_ACCESS);
        setFocusable(true);
        requestFocus();
        setFocusableWindowState(true);
        setImageIconFrame(LaunchingCards.getIcon(getImageFactory()));
        clock = new Clock(_list.getThreadFactory());
        lastSavedGameDate = new TextLabel("");
        reglesBelote = DocumentReaderBeloteUtil.getRulesBelote(StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.RULES_BELOTE),getFileCoreStream(),getStreams()));
        if (!reglesBelote.isValidRules()) {
            reglesBelote = new RulesBelote();
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.RULES_BELOTE), DocumentWriterBeloteUtil.setRulesBelote(reglesBelote),getStreams());
        }
        displayingBelote = DocumentReaderBeloteUtil.getDisplayingBelote(StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.DISPLAY_BELOTE),getFileCoreStream(),getStreams()));
        displayingBelote.validate();
        reglesPresident = DocumentReaderPresidentUtil.getRulesPresident(StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.RULES_PRESIDENT),getFileCoreStream(),getStreams()));
        if (!reglesPresident.isValidRules()) {
            reglesPresident = new RulesPresident();
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.RULES_PRESIDENT), DocumentWriterPresidentUtil.setRulesPresident(reglesPresident),getStreams());
        }
        displayingPresident = DocumentReaderPresidentUtil.getDisplayingPresident(StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.DISPLAY_PRESIDENT),getFileCoreStream(),getStreams()));
        displayingPresident.validate();
        reglesTarot = DocumentReaderTarotUtil.getRulesTarot(StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.RULES_TAROT),getFileCoreStream(),getStreams()));
        if (!reglesTarot.isValidRules()) {
            reglesTarot = new RulesTarot();
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.RULES_TAROT), DocumentWriterTarotUtil.setRulesTarot(reglesTarot),getStreams());
        }
        displayingTarot = DocumentReaderTarotUtil.getDisplayingTarot(StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.DISPLAY_TAROT),getFileCoreStream(),getStreams()));
        displayingTarot.validate();
        parametres = DocumentReaderCardsUnionUtil.getSoftParams(StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.PARAMS),getFileCoreStream(),getStreams()));
        parametres.setDelays();
//        parametres.setLocale(_locale);
        initMessageName();
        lastSavedGameDate.setText(StringUtil.simpleStringsFormat(getMessages().getVal(LAST_SAVED_GAME), dateLastSaved));

        pseudosJoueurs = DocumentReaderCardsUnionUtil.getNicknames(getLanguageKey(),StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.PLAYERS),getFileCoreStream(),getStreams()));
        if (!pseudosJoueurs.isValidNicknames()) {
            pseudosJoueurs = new Nicknames(getLanguageKey());
            pseudosJoueurs.sauvegarder(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.PLAYERS),getStreams());
        }
        /*Parametre de lancement*/
        initMenus();

        if(parametres.getLancement().isEmpty()) {
            menuPrincipal();
        } else {
            getTricksHands().setEnabledMenu(false);
            getTeams().setEnabledMenu(false);
            getMultiStop().setEnabledMenu(false);
            getLoad().setEnabledMenu(true);
            getEdit().setEnabledMenu(true);
            getTraining().setEnabledMenu(true);
            if(parametres.getLancement().first()==GameEnum.BELOTE) {
                containerGame = new ContainerSingleBelote(this);
            } else if(parametres.getLancement().first()==GameEnum.PRESIDENT) {
                containerGame = new ContainerSinglePresident(this);
            } else {
                containerGame = new ContainerSingleTarot(this);
            }
            change.setEnabledMenu(true);
            containerGame.modify();
        }
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
    }
    public static StringMap<String> getMessagesFromLocaleClass(String _folder, String _loc, String _class) {
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(_folder, _loc, _class);
        String loadedResourcesMessages_ = MessGuiCardsGr.ms().getVal(fileName_);
        return ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
    }
    public String getTooManyString() {
        return getMessages().getVal(TOO_MANY);
    }

    private void ajouterBoutonPrincipal(String _texte,GameEnum _nomJeu,Panel _container) {
        LabelButton bouton_=new LabelButton(_texte);
//        bouton_.addMouseListener(new EcouteurBoutonPrincipal(_nomJeu));
        bouton_.addMouseListener(new ListenerBeginGame(_nomJeu, this));
        _container.add(bouton_);
    }
    public void clearHelpDialogs() {
        helpFrames.clear();
    }
    public SoftParams getParametresLogiciel() {
        return new SoftParams(parametres);
    }
    public Nicknames getPseudosJoueurs() {
        return new Nicknames(pseudosJoueurs);
    }
    public RulesBelote getReglesBelote() {
        return new RulesBelote(reglesBelote);
    }

    public DisplayingBelote getDisplayingBelote() {
        return new DisplayingBelote(displayingBelote);
    }
    public RulesPresident getReglesPresident() {
        return new RulesPresident(reglesPresident);
    }

    public DisplayingPresident getDisplayingPresident() {
        return new DisplayingPresident(displayingPresident);
    }

    public RulesTarot getReglesTarot() {
        return new RulesTarot(reglesTarot);
    }

    public DisplayingTarot getDisplayingTarot() {
        return new DisplayingTarot(displayingTarot);
    }

//    @Override
//    public void pack() {
//        if (isVisible()) {
//            super.pack();
//        }
//    }
    public Clock getClock() {
        return clock;
    }
    public TextLabel getLastSavedGameDate() {
        return lastSavedGameDate;
    }

    /**server and client
     Method allowing the client to send a serializable object by its socket
     @param _serializable the serializable object to be sent
     */
    public boolean sendObject(Object _serializable) {
        String str_ = setObject(_serializable);
        return trySendString(str_, getSocket());
    }
    @Override
    public void quit() {
        if (containerGame instanceof ContainerMulti) {
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
        if(containerGame.playingSingleGame()&&!partieSauvegardee) {
            int choix_=saving();
            if(choix_!=JOptionPane.CANCEL_OPTION) {
                if(choix_==JOptionPane.YES_OPTION) {
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
        }
    }
    @Override
    public void dispose() {
        changerNombreDePartiesEnQuittant();
        ecrireCoordonnees();
        if (!helpFrames.isEmpty()) {
//            helpFrames.first().dispose();
            helpFrames.first().setVisible(false);
        }
        basicDispose();
    }

    private int saving() {
        //warning message
        return confirm(getMessages().getVal(CST_SAVING),getMessages().getVal(CST_SAVING_TITLE));
    }
    private void changerNombreDePartiesEnQuittant() {
        String fileName_ = StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.DECK_FOLDER,StreamTextFile.SEPARATEUR,FileConst.DECK_FILE);
        String content_ = StreamTextFile.contentsOfFile(fileName_,getFileCoreStream(),getStreams());
        StringList vl_=new StringList();
        boolean read_ = true;
        StringList lines_ = new StringList();
        if (content_ != null) {
            lines_.addAllElts(StringUtil.splitChars(content_, LINE_RETURN));
        } else {
            read_ = false;
        }
        int total_ = GameEnum.values().length;
        if (lines_.size() < total_) {
            read_ = false;
        }
        if (read_) {
            for (int indice_ = IndexConstants.FIRST_INDEX; indice_<total_; indice_++) {
                vl_.add(lines_.get(indice_));
            }
        } else {
            vl_=new StringList();
            for (int indice_ = IndexConstants.FIRST_INDEX; indice_ < total_; indice_++) {
                vl_.add("0");
            }
        }
        //Si l'action de battre les cartes est faite a chaque lancement
        //de logiciel alors le nombre de parties est remis a zero lors
        //d'une fermeture de logiciel

        if(reglesPresident.getMixedCards()==MixCardsChoice.EACH_LAUNCHING) {
            vl_.set(GameEnum.PRESIDENT.ordinal(), "0");
        }
        if(reglesBelote.getCartesBattues()==MixCardsChoice.EACH_LAUNCHING) {
            vl_.set(GameEnum.BELOTE.ordinal(), "0");
        }
        if(reglesTarot.getCartesBattues()==MixCardsChoice.EACH_LAUNCHING) {
            vl_.set(GameEnum.TAROT.ordinal(), "0");
        }
        StreamTextFile.saveTextFile(fileName_, StringUtil.join(vl_, LINE_RETURN),getStreams());
    }
    private void ecrireCoordonnees() {
        Point point_=getLocation();
        SoftApplicationCore.saveCoords(LaunchingCards.getTempFolder(getFrames()), FileConst.COORDS, point_.x,point_.y,getStreams());
    }
    public int getNoClient() {
        return ((ContainerMulti)containerGame).getNoClient();
    }

    @Override
    public void gearClient(AbstractSocket _newSocket) {
        Net.getSockets(getNet()).put(Net.getSockets(getNet()).size(), _newSocket);
        SendReceiveServer sendReceiveServer_=new SendReceiveServer(_newSocket,this, getNet());
        getThreadFactory().newStartedThread(sendReceiveServer_);
        Net.getConnectionsServer(getNet()).put(Net.getSockets(getNet()).size()-1,sendReceiveServer_);
        IndexOfArriving index_ = new IndexOfArriving();
        index_.setIndex(Net.getSockets(getNet()).size()-1);
        Net.getReadyPlayers(getNet()).put(Net.getSockets(getNet()).size()-1,false);
        Net.getPlacesPlayers(getNet()).put(Net.getSockets(getNet()).size()-1,(byte)(Net.getSockets(getNet()).size()-1));
        Net.sendObject(_newSocket,index_);
    }

    @Override
    public void loop(Object _readObject, AbstractSocket _socket) {
        if (_readObject instanceof DelegateServer) {
            Net.setGames(((DelegateServer)_readObject).getGames(), getNet());
            delegateServer();
            return;
        }
        if (_readObject instanceof AttemptConnecting) {
            if (!StringUtil.quickEq(((AttemptConnecting)_readObject).getServerName(),Net.getCards())) {
                NewPlayer p_ = new NewPlayer();
                p_.setAcceptable(false);
                p_.setArriving(true);
                p_.setIndex(((AttemptConnecting)_readObject).getIndex());
                p_.setPseudo(pseudo());
                p_.setLanguage(getLanguageKey());
                Net.sendObject(_socket,p_);
                return;
            }
        }
        ContainerMulti container_ = (ContainerMulti)containerGame;
        if (_readObject instanceof IndexOfArriving) {
            container_.setNoClient(((IndexOfArriving)_readObject).getIndex());
            NewPlayer p_ = new NewPlayer();
            p_.setAcceptable(true);
            p_.setArriving(true);
            p_.setIndex(container_.getNoClient());
            p_.setPseudo(pseudo());
            p_.setLanguage(getLanguageKey());
            Net.sendObject(_socket,p_);
            return;
        }
        if (_readObject instanceof ChoosenPlace) {
            container_.updatePlaces((ChoosenPlace) _readObject);
            return;
        }
        if (_readObject instanceof Ready) {
            container_.updateReady((Ready) _readObject);
            return;
        }
        if (_readObject instanceof PlayersNamePresent) {
            PlayersNamePresent infos_ = (PlayersNamePresent) _readObject;
            if (infos_.isFirst()) {
                container_.updateFirst(infos_);
            } else {
                container_.updateAfter(infos_);
            }
            return;
        }
        if (_readObject == Pause.INSTANCE) {
            container_.pauseBetweenTrick();
            return;
        }

        if (containerGame instanceof ContainerMultiTarot) {
            ContainerMultiTarot containerTarot_ = (ContainerMultiTarot) containerGame;
            if (_readObject instanceof ResultsTarot) {
                containerTarot_.endGame((ResultsTarot) _readObject);
                return;
            }
            if (_readObject instanceof RulesTarot) {
                containerTarot_.updateRules((RulesTarot)_readObject);
                return;
            }
            if (_readObject instanceof DealtHandTarot) {
                DealtHandTarot hand_ = (DealtHandTarot) _readObject;
                containerTarot_.updateForBeginningGame(hand_);
                return;
            }
            if (_readObject instanceof AllowBiddingTarot) {
                containerTarot_.canBidTarot((AllowBiddingTarot)_readObject);
                return;
            }
            if (_readObject instanceof ErrorBidding) {
                containerTarot_.canBid();
                containerTarot_.errorForBidding((ErrorBidding) _readObject);
                return;
            }
            if (_readObject instanceof BiddingTarot) {
                containerTarot_.displayLastBid((BiddingTarot) _readObject);
                return;
            }
            if (_readObject instanceof CallableCards) {
                containerTarot_.displayCalling((CallableCards) _readObject);
                return;
            }
            if (_readObject instanceof Dog) {
                containerTarot_.displayDog((Dog)_readObject);
                return;
            }
            if (_readObject instanceof DiscardedCard) {
                containerTarot_.updateDiscardingOrCanceling((DiscardedCard)_readObject);
                return;
            }
            if (_readObject instanceof CalledCards) {
                containerTarot_.displayCalledCard((CalledCards)_readObject);
                return;
            }
            if (_readObject instanceof ErrorDiscarding) {
                containerTarot_.errorDiscardingCard((ErrorDiscarding) _readObject);
                return;
            }
            if (_readObject == DisplaySlamButton.INSTANCE) {
                containerTarot_.displaySlamButton();
                return;
            }
            if (_readObject instanceof PlayerActionGame) {
                if (((PlayerActionGame) _readObject).getActionType() == PlayerActionGameType.SLAM) {
                    containerTarot_.displaySlam((PlayerActionGame) _readObject);
                    return;
                }
            }
            if (_readObject instanceof DiscardedTrumps) {
                containerTarot_.showDiscardedTrumps((DiscardedTrumps)_readObject);
                return;
            }
            if (_readObject instanceof TeamsPlayers) {
                containerTarot_.showTeams((TeamsPlayers)_readObject);
                return;
            }
            if (_readObject instanceof TricksHandsTarot) {
                containerTarot_.showTricksHands((TricksHandsTarot)_readObject);
                return;
            }
            if (_readObject instanceof AllowPlayingTarot) {
                containerTarot_.canPlayTarot((AllowPlayingTarot)_readObject);
                return;
            }
            if (_readObject instanceof RefreshHand) {
                containerTarot_.refreshHand((RefreshHand)_readObject);
                return;
            }
            if (_readObject instanceof PlayingCardTarot) {
                containerTarot_.displayPlayedCard((PlayingCardTarot)_readObject);
                return;
            }
            if (_readObject instanceof ErrorHandful) {
                containerTarot_.errorPlayingCard((ErrorHandful)_readObject);
                return;
            }
            if (_readObject instanceof ErrorPlaying) {
                containerTarot_.errorPlayingCard((ErrorPlaying)_readObject);
                return;
            }

        }
        if (containerGame instanceof ContainerMultiPresident) {
            ContainerMultiPresident containerPresident_ = (ContainerMultiPresident) containerGame;
            if (_readObject instanceof ResultsPresident) {
                containerPresident_.endGame((ResultsPresident) _readObject);
                return;
            }
            if (_readObject instanceof RulesPresident) {
                containerPresident_.updateRules((RulesPresident)_readObject);
                return;
            }
            if (_readObject instanceof AllowDiscarding) {
                containerPresident_.canDiscardPresident((AllowDiscarding) _readObject);
                return;
            }
            if (_readObject instanceof ReceivedGivenCards) {
                containerPresident_.refreshLoserHand((ReceivedGivenCards) _readObject);
                return;
            }
            if (_readObject instanceof AllowPlayingPresident) {
                containerPresident_.canPlayPresident((AllowPlayingPresident) _readObject);
                return;
            }
            if (_readObject instanceof DealtHandPresident) {
                DealtHandPresident hand_ = (DealtHandPresident) _readObject;
                containerPresident_.updateForBeginningGame(hand_);
                return;
            }
            if (_readObject instanceof RefreshHandPlayingPresident) {
                containerPresident_.refreshHand((RefreshHandPlayingPresident)_readObject);
                return;
            }
            if (_readObject instanceof PlayingCardPresident) {
                containerPresident_.displayPlayedCard((PlayingCardPresident) _readObject);
                return;
            }
            if (_readObject instanceof ErrorPlayingPresident) {
                containerPresident_.errorPlayingCard((ErrorPlayingPresident) _readObject);
                return;
            }
            if (_readObject instanceof TricksHandsPresident) {
                containerPresident_.showTricksHands((TricksHandsPresident) _readObject);
                return;
            }
        }
        if (containerGame instanceof ContainerMultiBelote) {
            ContainerMultiBelote containerBelote_ = (ContainerMultiBelote) containerGame;
            if (_readObject instanceof ResultsBelote) {
                containerBelote_.endGame((ResultsBelote) _readObject);
                return;
            }
            if (_readObject instanceof RulesBelote) {
                containerBelote_.updateRules((RulesBelote)_readObject);
                return;
            }
            if (_readObject instanceof DealtHandBelote) {
                DealtHandBelote hand_ = (DealtHandBelote) _readObject;
                containerBelote_.updateForBeginningGame(hand_);
                return;
            }

            if (_readObject instanceof AllowBiddingBelote) {
                containerBelote_.canBidBelote((AllowBiddingBelote)_readObject);
                return;
            }

            if (_readObject instanceof ErrorBiddingBelote) {
                containerBelote_.canBid();
                containerBelote_.errorForBidding((ErrorBiddingBelote) _readObject);
                return;
            }
            if (_readObject instanceof BiddingBelote) {
                containerBelote_.displayLastBid((BiddingBelote) _readObject);
                return;
            }

            if (_readObject instanceof RefreshHandBelote) {
                containerBelote_.refreshHand((RefreshHandBelote)_readObject);
                return;
            }
            if (_readObject instanceof TeamsPlayers) {
                containerBelote_.showTeams((TeamsPlayers)_readObject);
                return;
            }
            if (_readObject instanceof TricksHandsBelote) {
                containerBelote_.showTricksHands((TricksHandsBelote)_readObject);
                return;
            }
            if (_readObject instanceof AllowPlayingBelote) {
                containerBelote_.canPlayBelote((AllowPlayingBelote)_readObject);
                return;
            }
            if (_readObject instanceof RefreshHandPlayingBelote) {
                containerBelote_.refreshHand((RefreshHandPlayingBelote)_readObject);
                return;
            }
            if (_readObject instanceof PlayingCardBelote) {
                containerBelote_.displayPlayedCard((PlayingCardBelote)_readObject);
                return;
            }
            if (_readObject instanceof ErrorPlayingBelote) {
                containerBelote_.errorPlayingCard((ErrorPlayingBelote)_readObject);
                return;
            }
        }
    }

    @Override
    public void quitNetwork(Exiting _exit, AbstractSocket _socket) {
        menuPrincipal();
        closeConnexion(_socket);
        if (_exit != null && _exit.isClosing()) {
            basicDispose();
            return;
        }
        pack();
        if (_exit != null && _exit.isForced() && !_exit.isBusy()) {
            ConfirmDialog.showMessage(this, getTooManyString(), getTooManyString(), getLanguageKey(), JOptionPane.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(window, window.getTooManyString(), window.getTooManyString(), JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public boolean isForceBye() {
        return forceBye;
    }
    public void menuMultiGames() {
        containerGame = new ContainerGame(this);
        change.setEnabledMenu(false);
        //Activer le menu Partie/Demo
        getDemo().setEnabledMenu(false);
        //desactiver le menu Partie/aide au jeu
        getHelpGame().setEnabledMenu(false);
        //desactiver le menu Partie/conseil
        getConsulting().setEnabledMenu(false);
        //Desactiver le menu Partie/Pause
        getPause().setEnabledMenu(false);
        getEdit().setEnabledMenu(false);
        getTraining().setEnabledMenu(false);
        for (MenuItem m: getRulesGames().values()) {
            m.setEnabledMenu(false);
        }
        containerGame.finirParties();
        setTitle(Launching.WELCOME.toString(getLanguageKey()));
        Panel container_=Panel.newGrid(0,1);
        /*Pour montrer qu'on a de l'attention a l'utilisateur*/
        container_.add(new TextLabel(StringUtil.simpleStringsFormat(getMessages().getVal(CST_WELCOME), pseudo()),SwingConstants.CENTER));
        /*Cree les boutons de jeu*/
        String lg_ = getLanguageKey();
        for (GameEnum jeu2_:GameEnum.values()) {
            ajouterBoutonPrincipal(jeu2_.toString(lg_),jeu2_,container_);
        }
        LabelButton button_ = new LabelButton(getMessages().getVal(CST_MAIN_MENU));
        button_.addMouseListener(new BackToMainMenuEvent(this));
        container_.add(button_);
        //Ajout d'une etiquette pour indiquer ou aller pour avoir de l'aide
        if (goHelpMenu == null) {
            goHelpMenu = new TextLabel(getMessages().getVal(CST_GO_HELP_MENU),SwingConstants.CENTER);
        }
        container_.add(goHelpMenu);
        getLoad().setEnabledMenu(false);
        getSave().setEnabledMenu(false);
        getChange().setEnabledMenu(false);
        container_.add(clock);
        container_.add(lastSavedGameDate);
        setContentPane(container_);
        pack();
    }
    public void menuSoloGames() {
        containerGame = new ContainerGame(this);
        change.setEnabledMenu(false);
        //Activer le menu Partie/Demo
        getDemo().setEnabledMenu(true);
        //desactiver le menu Partie/aide au jeu
        getHelpGame().setEnabledMenu(false);
        //desactiver le menu Partie/conseil
        getConsulting().setEnabledMenu(false);
        //Desactiver le menu Partie/Pause
        getPause().setEnabledMenu(false);
        containerGame.setChangerPileFin(false);
        containerGame.finirParties();
        setTitle(Launching.WELCOME.toString(getLanguageKey()));
        Panel container_=Panel.newGrid(0,1);
        /*Pour montrer qu'on a de l'attention a l'utilisateur*/
        container_.add(new TextLabel(StringUtil.simpleStringsFormat(getMessages().getVal(CST_WELCOME), pseudo()),SwingConstants.CENTER));
        /*Cree les boutons de jeu*/
        String lg_ = getLanguageKey();
        for (GameEnum jeu2_:GameEnum.values()) {
            ajouterBoutonPrincipal(jeu2_.toString(lg_),jeu2_,container_);
        }
        LabelButton button_ = new LabelButton(getMessages().getVal(CST_MAIN_MENU));
        button_.addMouseListener(new BackToMainMenuEvent(this));
        container_.add(button_);
        //Ajout d'une etiquette pour indiquer ou aller pour avoir de l'aide
        if (goHelpMenu == null) {
            goHelpMenu = new TextLabel(getMessages().getVal(CST_GO_HELP_MENU),SwingConstants.CENTER);
        }
        container_.add(goHelpMenu);
        getSave().setEnabledMenu(false);
        getChange().setEnabledMenu(false);
        container_.add(clock);
        container_.add(lastSavedGameDate);
        setContentPane(container_);
        pack();
    }

    public void menuPrincipal() {
        getMultiStop().setEnabledMenu(false);
        getTricksHands().setEnabledMenu(false);
        getTeams().setEnabledMenu(false);
        containerGame = new ContainerGame(this);
        change.setEnabledMenu(false);
        //Activer le menu Partie/Demo
        getDemo().setEnabledMenu(true);
        //desactiver le menu Partie/aide au jeu
        getHelpGame().setEnabledMenu(false);
        //desactiver le menu Partie/conseil
        getConsulting().setEnabledMenu(false);
        //Desactiver le menu Partie/Pause
        getPause().setEnabledMenu(false);
        getLoad().setEnabledMenu(true);
        getEdit().setEnabledMenu(true);
        getTraining().setEnabledMenu(true);
        for (MenuItem m: getRulesGames().values()) {
            m.setEnabledMenu(true);
        }
        containerGame.finirParties();
        setTitle(Launching.WELCOME.toString(getLanguageKey()));
        Panel pane_ = Panel.newGrid(0,1);
        /*Pour montrer qu'on a de l'attention a l'utilisateur*/
        welcomeLabel = new TextLabel(StringUtil.simpleStringsFormat(getMessages().getVal(CST_WELCOME), pseudo()));
        pane_.add(welcomeLabel,SwingConstants.CENTER);
        /*Cree les boutons de jeu*/
        singleModeButton = new LabelButton(getMessages().getVal(CST_SINGLE_MODE));
        singleModeButton.addMouseListener(new ChooseModeEvent(this, true));
        pane_.add(singleModeButton);
        multiModeButton = new LabelButton(getMessages().getVal(CST_MULTI_MODE));
        multiModeButton.addMouseListener(new ChooseModeEvent(this, false));
        pane_.add(multiModeButton);
        //Ajout d'une etiquette pour indiquer ou aller pour avoir de l'aide
        if (goHelpMenu == null) {
            goHelpMenu = new TextLabel(getMessages().getVal(CST_GO_HELP_MENU),SwingConstants.CENTER);
        }
        pane_.add(goHelpMenu);
        pane_.add(clock);
        pane_.add(lastSavedGameDate);
        setContentPane(pane_);
        getSave().setEnabledMenu(false);
        getChange().setEnabledMenu(false);
    }
    private void initMessageName() {
//        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), getClass());
        messages = MainWindow.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, getLanguageKey(), getAccessFile());
    }
    public void loadGameBegin(String _file, Object _deal) {
        containerGame = new ContainerGame(this);
        tryToLoadDeal(_file, _deal);
    }
    private void initFileMenu() {
        /* Fichier */
        file=new Menu(getMessages().getVal(CST_FILE));
        /* Fichier/Charger "accessible n'importe quand"*/
        load=new MenuItem(getMessages().getVal(CST_LOAD));
        load.addActionListener(new LoadGameEvent(this));
        load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        file.addMenuItem(load);
        /* Fichier/Sauvegarder "accessible que lorsqu'on joue une partie de cartes"*/
        save=new MenuItem(getMessages().getVal(CST_SAVE));
        save.addActionListener(new SaveGameEvent(this));
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        file.addMenuItem(save);
        file.addSeparator();
        /* Fichier/Changer de jeu ACCESSIBLE n'importe quand sauf au menu principal,
        on y revient lorsque c'est accessible*/
        change=new MenuItem(getMessages().getVal(CST_CHANGE));
        change.setEnabledMenu(false);
        change.addActionListener(new ChangeGameEvent(this));
        change.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,InputEvent.CTRL_DOWN_MASK));
        file.addMenuItem(change);
        file.addSeparator();
        exit=new MenuItem(getMessages().getVal(CST_EXIT));
        exit.addActionListener(new QuitEvent(this));
        exit.setAccelerator(KeyStroke.getKeyStroke((char)KeyEvent.VK_ESCAPE));
        file.addMenuItem(exit);
        getJMenuBar().add(file);
    }
    public void loadGame() {
//        if (!load.isEnabled()) {
//            return;
//        }
//        if (containerGame instanceof ContainerMulti) {
//            return;
//        }
        if(containerGame.playingSingleGame()&&!partieSauvegardee) {
            if(containerGame.isPasse()||!containerGame.isThreadAnime()) {
                int choix_=saving();
                if(choix_!=JOptionPane.CANCEL_OPTION) {
                    if(choix_==JOptionPane.YES_OPTION) {
                        String fichier_=dialogueFichierSauvegarde();
                        if(!fichier_.isEmpty()) {
                            containerGame.saveCurrentGame(fichier_);
                            tryToLoadDeal();
                        }
                    } else {
                        tryToLoadDeal();
                    }
                }
            }
        } else if(containerGame.isPasse()||!containerGame.isThreadAnime()) {
            tryToLoadDeal();
        }
    }

    private void tryToLoadDeal() {
        partieSauvegardee=true;
        String nomFichier_=dialogueFichierChargement();
        if (nomFichier_.isEmpty()) {
            return;
        }
        Object par_ = DocumentReaderCardsUnionUtil.getObject(nomFichier_,getFileCoreStream(),getStreams());
        tryToLoadDeal(nomFichier_, par_);
    }

    private void tryToLoadDeal(String _nomFichier, Object _deal) {
        ContainerGame containerGame_;

        if (_deal instanceof GameBelote) {
            CheckerGameBeloteWithRules.check((GameBelote) _deal);
            if (!((GameBelote) _deal).getError().isEmpty()) {
                erreurDeChargement(_nomFichier);
                return;
            }
            containerGame_ = new ContainerSingleBelote(this);
            containerGame_.getPar().jouerBelote((GameBelote) _deal);
            containerGame_.load();
            partieSauvegardee=false;
            containerGame = containerGame_;
            change.setEnabledMenu(true);
        } else if (_deal instanceof GamePresident) {
            CheckerGamePresidentWithRules.check((GamePresident) _deal);
            if (!((GamePresident) _deal).getError().isEmpty()) {
                erreurDeChargement(_nomFichier);
                return;
            }
            containerGame_ = new ContainerSinglePresident(this);
            containerGame_.getPar().jouerPresident((GamePresident) _deal);
            containerGame_.load();
            partieSauvegardee=false;
            containerGame = containerGame_;
            change.setEnabledMenu(true);
        } else if (_deal instanceof GameTarot) {
            CheckerGameTarotWithRules.check((GameTarot) _deal);
            if (!((GameTarot) _deal).getError().isEmpty()) {
                erreurDeChargement(_nomFichier);
                return;
            }
            containerGame_ = new ContainerSingleTarot(this);
            containerGame_.getPar().jouerTarot((GameTarot) _deal);
            containerGame_.load();
            partieSauvegardee=false;
            containerGame = containerGame_;
            change.setEnabledMenu(true);
        } else {
            erreurDeChargement(_nomFichier);
        }
    }

    public void saveGame() {
//        if (!save.isEnabled()) {
//            return;
//        }
//        if (containerGame instanceof ContainerMulti) {
//            return;
//        }
        if(containerGame.isPasse()||!containerGame.isThreadAnime()) {
            String fichier_=dialogueFichierSauvegarde();

            if(!fichier_.isEmpty()) {
                containerGame.saveCurrentGame(fichier_);
                dateLastSaved = Clock.getDateTimeText(getThreadFactory());
                lastSavedGameDate.setText(StringUtil.simpleStringsFormat(getMessages().getVal(LAST_SAVED_GAME), dateLastSaved));
                partieSauvegardee=true;
            }
        }
    }

    public void changeGame() {
//        if (!change.isEnabled()) {
//            return;
//        }
//        if (containerGame instanceof ContainerMulti) {
//            return;
//        }
        if(containerGame.playingSingleGame()&&!partieSauvegardee) {
            if(containerGame.isPasse()||!containerGame.isThreadAnime()) {
                int choix_=saving();
                if(choix_!=JOptionPane.CANCEL_OPTION) {
                    if(choix_==JOptionPane.YES_OPTION) {
                        String fichier_=dialogueFichierSauvegarde();
                        if(!fichier_.isEmpty()) {
                            containerGame.saveCurrentGame(fichier_);
                            if (single) {
                                menuSoloGames();
                            } else {
                                menuMultiGames();
                            }
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
        } else if(containerGame.isPasse()||!containerGame.isThreadAnime()) {
            if (single) {
                menuSoloGames();
            } else {
                menuMultiGames();
            }
        }
    }

    private void initDealMenu() {
        String lg_ = getLanguageKey();
        deal=new Menu(getMessages().getVal(CST_DEAL));
        /* Partie/Conseil "accessible uniquement en cours de partie et
        dans les jeux non solitaires"*/
        MenuItem sousMenu_;
        consulting=new MenuItem(getMessages().getVal(CST_CONSULTING));
        consulting.setAccelerator(KeyStroke.getKeyStroke(F_ONE));
        consulting.addActionListener(new ConsultEvent(this));
        deal.addMenuItem(consulting);
        /* Partie/Pause Permet de mettre le jeu en pause*/
        pause=new CheckBoxMenuItem(getMessages().getVal(CST_PAUSE));
        pause.setAccelerator(KeyStroke.getKeyStroke(CST_PAUSE));
        pause.addActionListener(new PauseEvent(this));
        deal.addMenuItem(pause);
        /* Partie/Pause Permet d avoir de l aide*/
        helpGame=new MenuItem(getMessages().getVal(HELP_GAME));
        helpGame.setAccelerator(KeyStroke.getKeyStroke(F_TWO));
        helpGame.addActionListener(new DisplayHelpGameEvent(this));
        deal.addMenuItem(helpGame);
        tricksHands=new MenuItem(getMessages().getVal(CST_TRICKS_HANDS));

        tricksHands.addActionListener(new DisplayTricksHandsEvent(this));
        deal.addMenuItem(tricksHands);
        teams=new MenuItem(getMessages().getVal(CST_TEAMS));
        teams.addActionListener(new DisplayTeamsEvent(this));
        deal.addMenuItem(teams);
        /* Partie/Editer "Permet d'editer n'importe quelle partie de cartes et accessible n'importe quand"*/
        edit=new Menu(getMessages().getVal(CST_EDIT));
        MenuItem sousSousMenu_ = new MenuItem(GameEnum.BELOTE.toString(lg_));
        sousSousMenu_.addActionListener(new EditEvent(this, GameEnum.BELOTE));
        edit.addMenuItem(sousSousMenu_);
        editGames.put(GameEnum.BELOTE, sousSousMenu_);
        sousSousMenu_ = new MenuItem(GameEnum.PRESIDENT.toString(lg_));
        sousSousMenu_.addActionListener(new EditEvent(this, GameEnum.PRESIDENT));
        edit.addMenuItem(sousSousMenu_);
        editGames.put(GameEnum.PRESIDENT, sousSousMenu_);
        sousSousMenu_ = new MenuItem(GameEnum.TAROT.toString(lg_));
        sousSousMenu_.addActionListener(new EditEvent(this, GameEnum.TAROT));
        edit.addMenuItem(sousSousMenu_);
        editGames.put(GameEnum.TAROT, sousSousMenu_);
        deal.addMenuItem(edit);
        /* Partie/Demo "Permet de voir la demostration d une partie"*/
        demo=new Menu(getMessages().getVal(CST_DEMO));
        sousMenu_=new MenuItem(GameEnum.BELOTE.toString(lg_));
        sousMenu_.addActionListener(new SimulationEvent(this, GameEnum.BELOTE));
        sousMenu_.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,InputEvent.CTRL_DOWN_MASK+InputEvent.SHIFT_DOWN_MASK));
        demo.addMenuItem(sousMenu_);
        demoGames.put(GameEnum.BELOTE, sousSousMenu_);
        sousMenu_=new MenuItem(GameEnum.PRESIDENT.toString(lg_));
        sousMenu_.addActionListener(new SimulationEvent(this, GameEnum.PRESIDENT));
        sousMenu_.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_DOWN_MASK+InputEvent.SHIFT_DOWN_MASK));
        demo.addMenuItem(sousMenu_);
        demoGames.put(GameEnum.PRESIDENT, sousSousMenu_);
        sousMenu_=new MenuItem(GameEnum.TAROT.toString(lg_));
        sousMenu_.addActionListener(new SimulationEvent(this, GameEnum.TAROT));
        sousMenu_.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,InputEvent.CTRL_DOWN_MASK+InputEvent.SHIFT_DOWN_MASK));
        demo.addMenuItem(sousMenu_);
        demoGames.put(GameEnum.TAROT, sousSousMenu_);
        deal.addMenuItem(demo);
        /* Partie/Entrainement "accessible n'importe quand pour pouvoir s'entrainer"*/
        training=new Menu(getMessages().getVal(CST_TRAINING));
        /* Partie/Entrainement au Tarot*/
        //Petitasauver,Petitachasser,Petitaemmeneraubout;
        for (ChoiceTarot ct_:ChoiceTarot.values()) {

            sousMenu_=new MenuItem(Games.toString(ct_,lg_));
            sousMenu_.addActionListener(new ListenerTrainingTarot(this, ct_));
            training.addMenuItem(sousMenu_);
            trainingTarot.put(ct_, sousMenu_);
        }
        deal.addMenuItem(training);
        multiStop = new MenuItem(getMessages().getVal(CST_MULTI_STOP));
        multiStop.addActionListener(new QuitMultiEvent(this));
        deal.addMenuItem(multiStop);
        getJMenuBar().add(deal);
    }
    public void consult() {
//        if (!consulting.isEnabled()) {
//            return;
//        }
        if(!containerGame.isThreadAnime()) {
            containerGame.conseil();
        }
    }
    public void pause() {
//        if (!pause.isEnabled()) {
//            return;
//        }
//        if (containerGame instanceof ContainerMulti) {
//            return;
//        }
        /*In order that the player can pause*/
        containerGame.setPasse(!containerGame.isPasse());
    }
    public void displayHelpGame() {
//        if (!helpGame.isEnabled()) {
//            return;
//        }
        containerGame.aideAuJeu();
    }
    public void displayTricksHands() {
//        if (!tricksHands.isEnabled()) {
//            return;
//        }
        containerGame.showTricksHands();
    }
    public void displayTeams() {
//        if (!teams.isEnabled()) {
//            return;
//        }
        containerGame.showTeams();
    }
    public void editGame(GameEnum _game) {
        if (_game == GameEnum.BELOTE) {
            if (!edit.isEnabled()) {
                return;
            }
            editeurBelote();
            GameBelote partie_ = EditorBelote.getPartie(getEditorBelote());
            if(partie_ == null) {
                return;
            }
            //Cas ou l'utilisateur veut jouer une partie editee
            if (containerGame instanceof ContainerMulti) {
                return;
            }
            if(containerGame.playingSingleGame()&&!partieSauvegardee) {
                if(containerGame.isPasse()||!containerGame.isThreadAnime()) {
                    int choix_=saving();
                    if(choix_!=JOptionPane.CANCEL_OPTION) {
                        if(choix_==JOptionPane.YES_OPTION) {
                            String fichier_=dialogueFichierSauvegarde();
                            if(!fichier_.isEmpty()) {
                                containerGame.saveCurrentGame(fichier_);
                                containerGame = new ContainerSingleBelote(this);
                                ((ContainerSingleBelote)containerGame).editerBelote(partie_);
                                change.setEnabledMenu(true);
                            }
                        } else {
                            containerGame = new ContainerSingleBelote(this);
                            ((ContainerSingleBelote)containerGame).editerBelote(partie_);
                            change.setEnabledMenu(true);
                        }
                    }
                }
            } else {
                containerGame = new ContainerSingleBelote(this);
                ((ContainerSingleBelote)containerGame).editerBelote(partie_);
                change.setEnabledMenu(true);
            }
        } else if (_game == GameEnum.PRESIDENT) {
            if (!edit.isEnabled()) {
                return;
            }
            editeurPresident();
            GamePresident partie_ = EditorPresident.getPartie(getEditorPresident());
            if(partie_ == null) {
                return;
            }
            //Cas ou l'utilisateur veut jouer une partie editee
            if (containerGame instanceof ContainerMulti) {
                return;
            }
            if(containerGame.playingSingleGame()&&!partieSauvegardee) {
                if(containerGame.isPasse()||!containerGame.isThreadAnime()) {
                    int choix_=saving();
                    if(choix_!=JOptionPane.CANCEL_OPTION) {
                        if(choix_==JOptionPane.YES_OPTION) {
                            String fichier_=dialogueFichierSauvegarde();
                            if(!fichier_.isEmpty()) {
                                containerGame.saveCurrentGame(fichier_);

                                containerGame = new ContainerSinglePresident(this);
                                ((ContainerSinglePresident) containerGame).editerPresident(partie_);
                                change.setEnabledMenu(true);
                            }
                        } else {
                            containerGame = new ContainerSinglePresident(this);
                            ((ContainerSinglePresident) containerGame).editerPresident(partie_);
                            change.setEnabledMenu(true);
                        }
                    }
                }
            } else {
                containerGame = new ContainerSinglePresident(this);
                ((ContainerSinglePresident) containerGame).editerPresident(partie_);
                change.setEnabledMenu(true);
            }
        } else if (_game == GameEnum.TAROT) {
            if (!edit.isEnabled()) {
                return;
            }
            editeurTarot();
            GameTarot partie_ = EditorTarot.getPartie(getEditorTarot());
            if(partie_ == null) {
                return;
            }
            //Cas ou l'utilisateur veut jouer une partie editee
            if (containerGame instanceof ContainerMulti) {
                return;
            }
            if(containerGame.playingSingleGame()&&!partieSauvegardee) {
                if(containerGame.isPasse()||!containerGame.isThreadAnime()) {
                    int choix_=saving();
                    if(choix_!=JOptionPane.CANCEL_OPTION) {
                        if(choix_==JOptionPane.YES_OPTION) {
                            String fichier_=dialogueFichierSauvegarde();
                            if(!fichier_.isEmpty()) {
                                containerGame.saveCurrentGame(fichier_);

                                containerGame = new ContainerSingleTarot(this);
                                ((ContainerSingleTarot) containerGame).editerTarot(partie_);
                                change.setEnabledMenu(true);
                            }
                        } else {
                            containerGame = new ContainerSingleTarot(this);
                            ((ContainerSingleTarot) containerGame).editerTarot(partie_);
                            change.setEnabledMenu(true);
                        }
                    }
                }
            } else {
                containerGame = new ContainerSingleTarot(this);
                ((ContainerSingleTarot) containerGame).editerTarot(partie_);
                change.setEnabledMenu(true);
            }
        }
    }
    public void simulateGame(GameEnum _game) {
        if (_game == GameEnum.BELOTE) {
            if (!demo.isEnabled()) {
                return;
            }
            if (containerGame instanceof ContainerMulti) {
                return;
            }
            containerGame = new ContainerSimuBelote(this);
        } else if (_game == GameEnum.PRESIDENT) {
            if (!demo.isEnabled()) {
                return;
            }
            if (containerGame instanceof ContainerMulti) {
                return;
            }
            containerGame = new ContainerSimuPresident(this);
        } else if (_game == GameEnum.TAROT) {
            if (!demo.isEnabled()) {
                return;
            }
            if (containerGame instanceof ContainerMulti) {
                return;
            }
            containerGame = new ContainerSimuTarot(this);
        }
    }
    public void trainingTarot(ChoiceTarot _ct) {
        if (containerGame instanceof ContainerMulti) {
            return;
        }
        /*Si l'utilisateur a supprime le fichier de configurations alors a la fin
        de l'execution le fichier de configuration sera recree*/
        if(containerGame.playingSingleGame()&&!partieSauvegardee) {
            int choix_=saving();
            if(choix_!=JOptionPane.CANCEL_OPTION) {
                if(choix_==JOptionPane.YES_OPTION) {
                    String fichier_=dialogueFichierSauvegarde();
                    if(!fichier_.isEmpty()) {
                        containerGame.saveCurrentGame(fichier_);
                    }
                }
                containerGame = new ContainerSingleTarot(this);
                change.setEnabledMenu(true);
                ((ContainerSingleTarot) containerGame).jouerDonneEntrainement(_ct);
            }
        } else {
            containerGame = new ContainerSingleTarot(this);
            change.setEnabledMenu(true);
            ((ContainerSingleTarot) containerGame).jouerDonneEntrainement(_ct);
        }
    }

    public void quitMulti() {
        if (!(containerGame instanceof ContainerMulti)) {
            return;
        }
        Quit quit_ = new Quit();
        quit_.setClosing(false);
        quit_.setPlace(((ContainerMulti)containerGame).getIndexInGame());
        quit_.setServer(((ContainerMulti)containerGame).hasCreatedServer());
        sendObject(quit_);
    }

    private void initParametersMenu() {
        /* Parametres */
        String lg_ = getLanguageKey();
        parameters=new Menu(getMessages().getVal(CST_PARAMETERS));
        MenuItem sousMenu_=new MenuItem(GameEnum.BELOTE.toString(lg_));
        sousMenu_.addActionListener(new ManageRulesEvent(this, GameEnum.BELOTE));
        sousMenu_.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,InputEvent.SHIFT_DOWN_MASK));
        parameters.addMenuItem(sousMenu_);
        rulesGames.put(GameEnum.BELOTE, sousMenu_);
        sousMenu_=new MenuItem(GameEnum.PRESIDENT.toString(lg_));
        sousMenu_.addActionListener(new ManageRulesEvent(this, GameEnum.PRESIDENT));
        sousMenu_.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.SHIFT_DOWN_MASK));
        parameters.addMenuItem(sousMenu_);
        rulesGames.put(GameEnum.PRESIDENT, sousMenu_);
        sousMenu_=new MenuItem(GameEnum.TAROT.toString(lg_));
        sousMenu_.addActionListener(new ManageRulesEvent(this, GameEnum.TAROT));
        sousMenu_.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,InputEvent.SHIFT_DOWN_MASK));
        parameters.addMenuItem(sousMenu_);
        rulesGames.put(GameEnum.TAROT, sousMenu_);
        players=new MenuItem(getMessages().getVal(CST_PLAYERS));
        players.addActionListener(new ManageNicknameEvent(this));
        players.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J,InputEvent.CTRL_DOWN_MASK+InputEvent.ALT_DOWN_MASK));
        parameters.addMenuItem(players);
        launching=new MenuItem(getMessages().getVal(CST_LAUNCHING));
        launching.addActionListener(new ManageSoftEvent(this, CST_LAUNCHING));
        launching.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,InputEvent.CTRL_DOWN_MASK));
        parameters.addMenuItem(launching);
        timing=new MenuItem(getMessages().getVal(CST_TIMING));
        timing.addActionListener(new ManageSoftEvent(this, CST_TIMING));
        timing.setAccelerator(KeyStroke.getKeyStroke(F_FOUR));
        parameters.addMenuItem(timing);
        interact=new MenuItem(getMessages().getVal(CST_INTERACT));
        interact.addActionListener(new ManageSoftEvent(this, CST_INTERACT));
        interact.setAccelerator(KeyStroke.getKeyStroke(F_FIVE));
        parameters.addMenuItem(interact);
        language=new MenuItem(getMessages().getVal(CST_LANGUAGE));
        language.addActionListener(new ManageLanguageEvent(this));
//        if (Standalone.isStandalone()) {
//            language.setAccelerator(KeyStroke.getKeyStroke(F_SIX));
//            parameters.add(language);
//        }
        language.setAccelerator(KeyStroke.getKeyStroke(F_SIX));
        parameters.addMenuItem(language);
        /* Partie/Editer "Permet d'editer n'importe quelle partie de cartes et accessible n'importe quand"*/
        displaying=new Menu(getMessages().getVal(CST_DISPLAYING));
        MenuItem sousSousMenu_ = new MenuItem(GameEnum.BELOTE.toString(lg_));
        sousSousMenu_.addActionListener(new DisplayingGameEvent(this, GameEnum.BELOTE));
        displaying.addMenuItem(sousSousMenu_);
        displayingGames.put(GameEnum.BELOTE, sousSousMenu_);
        sousSousMenu_ = new MenuItem(GameEnum.PRESIDENT.toString(lg_));
        sousSousMenu_.addActionListener(new DisplayingGameEvent(this, GameEnum.PRESIDENT));
        displaying.addMenuItem(sousSousMenu_);
        displayingGames.put(GameEnum.PRESIDENT, sousSousMenu_);
        sousSousMenu_ = new MenuItem(GameEnum.TAROT.toString(lg_));
        sousSousMenu_.addActionListener(new DisplayingGameEvent(this, GameEnum.TAROT));
        displaying.addMenuItem(sousSousMenu_);
        displayingGames.put(GameEnum.TAROT, sousSousMenu_);
        parameters.addMenuItem(displaying);
        getJMenuBar().add(parameters);
    }
    public void manageRules(GameEnum _game) {
        String lg_ = getLanguageKey();
        if (_game == GameEnum.BELOTE) {
            DialogRulesBelote.initDialogRulesBelote(_game.toString(lg_), this,reglesBelote);
            RulesBelote reglesBelote_=DialogRulesBelote.getRegles(getDialogRulesBelote());
            if (!DialogRulesBelote.isValidated(getDialogRulesBelote())) {
                return;
            }
            reglesBelote = reglesBelote_;
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.RULES_BELOTE), DocumentWriterBeloteUtil.setRulesBelote(reglesBelote),getStreams());
            containerGame.setRulesBelote(reglesBelote);
        } else if (_game == GameEnum.PRESIDENT) {
            DialogRulesPresident.initDialogRulesPresident(_game.toString(lg_), this, reglesPresident);
            DialogRulesPresident.setPresidentDialog(true, 0,this);
            RulesPresident rules_ = DialogRulesPresident.getRegles(getDialogRulesPresident());
            if (!DialogRulesPresident.isValidated(getDialogRulesPresident())) {
                return;
            }
            reglesPresident = rules_;
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.RULES_PRESIDENT), DocumentWriterPresidentUtil.setRulesPresident(reglesPresident),getStreams());
            containerGame.setRulesPresident(reglesPresident);
        } else if (_game == GameEnum.TAROT) {
            DialogRulesTarot.initDialogRulesTarot(_game.toString(lg_), this, reglesTarot);
            DialogRulesTarot.setTarotDialog(true,0,this);
            RulesTarot reglesTarot_=DialogRulesTarot.getRegles(getDialogRulesTarot());
            if (!DialogRulesTarot.isValidated(getDialogRulesTarot())) {
                return;
            }
            reglesTarot = reglesTarot_;
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.RULES_TAROT), DocumentWriterTarotUtil.setRulesTarot(reglesTarot),getStreams());
            containerGame.setRulesTarot(reglesTarot);
        }
    }
    public void manageNicknames() {
        DialogNicknames.initDialogNicknames(getMessages().getVal(CST_PLAYERS), this);
        pseudosJoueurs=DialogNicknames.getPseudos(getDialogNicknames());
        pseudosJoueurs.sauvegarder(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.PLAYERS),getStreams());
        containerGame.setNicknames(pseudosJoueurs);
    }
    public void manageSoft(String _key) {
        DialogSoft.initDialogSoft(getMessages().getVal(_key), this);
        DialogSoft.setDialogSoft(_key, this);
        parametres=DialogSoft.getParametres(getDialogSoft());
        parametres.sauvegarder(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.PARAMS),getStreams());
        containerGame.setSettings(parametres);
    }
    public void manageLanguage() {
        if (!canChangeLanguageAll()) {
            GroupFrame.showDialogError(this);
            return;
        }
        LanguageDialog.setLanguageDialog(this, getMessages().getVal(CST_LANGUAGE));
        String langue_ = LanguageDialog.getStaticLanguage(getLanguageDialog());
        if(langue_ == null || langue_.isEmpty()) {
            return;
        }
        GroupFrame.changeStaticLanguage(langue_, getFrames());
        SoftApplicationCore.saveLanguage(LaunchingCards.getTempFolder(getFrames()), langue_,getStreams());
    }
    public void displayingGame(GameEnum _game) {
        String lg_ = getLanguageKey();
        if (_game == GameEnum.BELOTE) {
            DialogDisplayingBelote.setDialogDisplayingBelote(_game.toString(lg_), this);
            displayingBelote=DialogDisplayingBelote.getDisplaying(getDialogDisplayingBelote());
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.DISPLAY_BELOTE), DocumentWriterBeloteUtil.setDisplayingBelote(displayingBelote),getStreams());
            containerGame.setDisplayingBelote(displayingBelote);
        } else if (_game == GameEnum.PRESIDENT) {
            DialogDisplayingPresident.setDialogDisplayingPresident(_game.toString(lg_), this);
            displayingPresident=DialogDisplayingPresident.getDisplaying(getDialogDisplayingPresident());
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.DISPLAY_PRESIDENT), DocumentWriterPresidentUtil.setDisplayingPresident(displayingPresident),getStreams());
            containerGame.setDisplayingPresident(displayingPresident);
        } else if (_game == GameEnum.TAROT) {
            DialogDisplayingTarot.setDialogDisplayingTarot(_game.toString(lg_), this);
            displayingTarot=DialogDisplayingTarot.getDisplaying(getDialogDisplayingTarot());
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.DISPLAY_TAROT), DocumentWriterTarotUtil.setDisplayingTarot(displayingTarot),getStreams());
            containerGame.setDisplayingTarot(displayingTarot);
        }
    }
    //private JMenu help;
    private void initHelpMenu() {
        /* Aide */
        help=new Menu(getMessages().getVal(CST_HELP));
        /* Aide/Aide generale Explication du fonctionnement du logiciel et des regles utilisables*/
        generalHelp=new MenuItem(getMessages().getVal(CST_GENERAL_HELP));
        generalHelp.addActionListener(new DisplayHelpEvent(this));
        generalHelp.setAccelerator(KeyStroke.getKeyStroke(F_THREE));
        help.addMenuItem(generalHelp);
        getJMenuBar().add(help);

    }

    public void displayHelp() {
        //On indique a l utilisatteur comment utiliser le logiciel et jouer aux cartes
        if (!helpFrames.isEmpty()) {
            if (!helpFrames.first().isVisible()) {
                helpFrames.first().setTitle(getMessages().getVal(CST_GENERAL_HELP));
                helpFrames.first().initialize(this);
            }
            return;
        }
        if (helpInitializerThread == null || helpInitializerThread.isAlive() || helpInitializerTask == null) {
            return;
        }
        FrameGeneralHelp aide_=new FrameGeneralHelp(getMessages().getVal(CST_GENERAL_HELP),this);
        aide_.initialize(this);
        helpFrames.add(aide_);
    }

    /**Initialise la barre de menus*/
    private void initMenus() {
        setJMenuBar(new MenuBar());
        initFileMenu();
        initDealMenu();
        initParametersMenu();
        initHelpMenu();
    }

    private int confirm(String _message,String _titre) {
        //warning message
        return ConfirmDialog.getAnswer(this,_message,_titre, getLanguageKey(),JOptionPane.YES_NO_CANCEL_OPTION);
    }
    /**Sauvegarder une partie dans un fichier*/
    private String dialogueFichierSauvegarde() {
        if (isSaveHomeFolder()) {
            FileSaveDialog.setFileSaveDialogByFrame(this, getLanguageKey(), true, FileConst.GAME_EXT, getFrames().getHomePath(), getFrames().getHomePath(), FileConst.EXCLUDED);
        } else {
            FileSaveDialog.setFileSaveDialogByFrame(this, getLanguageKey(), true, FileConst.GAME_EXT, EMPTY_STRING, getFrames().getHomePath(), FileConst.EXCLUDED);
        }
        String fichier_=FileSaveDialog.getStaticSelectedPath(getFileSaveDialog());
        if (fichier_ == null) {
            fichier_ = EMPTY_STRING;
        }
        return fichier_;
    }
    /**Sauvegarder une partie dans un fichier*/
    private String dialogueFichierChargement() {
        if (isSaveHomeFolder()) {
            FileOpenDialog.setFileOpenDialog(this,getLanguageKey(),true, FileConst.GAME_EXT, getFrames().getHomePath(), FileConst.EXCLUDED);
        } else {
            FileOpenDialog.setFileOpenDialog(this,getLanguageKey(),true, FileConst.GAME_EXT, EMPTY_STRING, FileConst.EXCLUDED);
        }
        String fichier_=FileOpenDialog.getStaticSelectedPath(getFileOpenDialog());
        if (fichier_ == null) {
            fichier_ = EMPTY_STRING;
        }
        return fichier_;
    }
    /**Editer une partie de belote*/
    private void editeurBelote() {
        EditorBelote.initEditorBelote(this);
    }
    /**Editer une partie de tarot*/
    private void editeurPresident() {
        EditorPresident.initEditorPresident(this);
    }
    /**Editer une partie de tarot*/
    private void editeurTarot() {
        EditorTarot.initEditorTarot(this);
    }
    private void erreurDeChargement(String _fichier) {
        //The issue of quality of game are caught here
        String lg_ = getLanguageKey();
        String mes_ = StringUtil.simpleStringsFormat(getMessages().getVal(CST_FILE_NOT_LOADED), _fichier);
        ConfirmDialog.showMessage(this,mes_, getMessages().getVal(CST_FILE_NOT_LOADED_TILE),lg_, JOptionPane.ERROR_MESSAGE);
    }

    /**On ecoute les boutons du menu principal et des menus jeux*/
    public void beginGame(GameEnum _jeuBouton) {
        if (single) {
            if(_jeuBouton==GameEnum.BELOTE) {
                containerGame = new ContainerSingleBelote(this);
            } else if(_jeuBouton==GameEnum.PRESIDENT) {
                containerGame = new ContainerSinglePresident(this);
            } else if(_jeuBouton==GameEnum.TAROT) {
                containerGame = new ContainerSingleTarot(this);
            }
            change.setEnabledMenu(true);
            containerGame.modify();
            return;
        }
        DialogServer.setDialogServer(this, _jeuBouton);
        String ip_ = DialogServer.getIpOrHostName(getDialogServer());
        if (ip_ == null || ip_.isEmpty()) {
            if (DialogServer.getIpType(getDialogServer()) == IpType.IP_V6) {
                ip_ = LOCALHOST_NEW_IP;
            } else {
                ip_ = LOCALHOST_OLD_IP;
            }
        }
        if (!DialogServer.isChoosen(getDialogServer())) {
            return;
        }
        GameEnum choosenGameMultiPlayers_ = _jeuBouton;
        if (choosenGameMultiPlayers_ == GameEnum.TAROT) {
            containerGame = new ContainerMultiTarot(this,DialogServer.isCreate(getDialogServer()), DialogServer.getNbPlayers(getDialogServer()));
        } else if (choosenGameMultiPlayers_ == GameEnum.PRESIDENT) {
            containerGame = new ContainerMultiPresident(this,DialogServer.isCreate(getDialogServer()), DialogServer.getNbPlayers(getDialogServer()));
        } else if (choosenGameMultiPlayers_ == GameEnum.BELOTE) {
            containerGame = new ContainerMultiBelote(this,DialogServer.isCreate(getDialogServer()));
        }
        String fileName_ = StringUtil.concat(StreamFolderFile.getCurrentPath(getFileCoreStream()), FileConst.PORT_INI);
        int port_ = NetCreate.tryToGetPort(fileName_, Net.getPort(),getFileCoreStream(),getStreams());
        if (DialogServer.isCreate(getDialogServer())) {
            int nbChoosenPlayers_ = DialogServer.getNbPlayers(getDialogServer());
            Net.setNbPlayers(nbChoosenPlayers_, getNet());
            createServer(ip_, DialogServer.getIpType(getDialogServer()), port_);
            return;
        }
        SocketResults connected_ = createClient(ip_, DialogServer.getIpType(getDialogServer()), false, port_);
        if (connected_.getError() != ErrorHostConnectionType.NOTHING) {
            containerGame = new ContainerGame(this);
            if (connected_.getError() == ErrorHostConnectionType.UNKNOWN_HOST) {
                String formatted_ = getMessages().getVal(UNKNOWN_HOST);
                formatted_ = StringUtil.simpleStringsFormat(formatted_, ip_);
                ConfirmDialog.showMessage(this, getMessages().getVal(BUG), formatted_, getLanguageKey(), JOptionPane.ERROR_MESSAGE);
                return;
            }
            ConfirmDialog.showMessage(this, getMessages().getVal(BUG), getMessages().getVal(NOT_CONNECTED), getLanguageKey(), JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    private String pseudo() {
        return pseudosJoueurs.getPseudo();
    }
    public void delegateServer() {
        ((ContainerMulti)containerGame).delegateServer();
    }

    public StringMap<String> getMessages() {
        return messages;
    }

    public BasicClient getThreadEmission() {
        return threadEmission;
    }

    public void setThreadEmission(BasicClient _threadEmission) {
        threadEmission = _threadEmission;
    }

    @Override
    public boolean canChangeLanguage() {
        if (!containerGame.isSimple()) {
            return false;
        }
        if (!helpFrames.isEmpty()) {
            return !helpFrames.first().isVisible();
        }
        return true;
    }

    @Override
    public void changeLanguage(String _language) {
        setLanguageKey(_language);
        translate();
    }

    private void translate() {
        initMessageName();
        String lg_ = getLanguageKey();
        file.setText(getMessages().getVal(CST_FILE));
        load.setText(getMessages().getVal(CST_LOAD));
        save.setText(getMessages().getVal(CST_SAVE));
        change.setText(getMessages().getVal(CST_CHANGE));
        exit.setText(getMessages().getVal(CST_EXIT));
        deal.setText(getMessages().getVal(CST_DEAL));
        consulting.setText(getMessages().getVal(CST_CONSULTING));
        pause.setText(getMessages().getVal(CST_PAUSE));
        helpGame.setText(getMessages().getVal(HELP_GAME));
        tricksHands.setText(getMessages().getVal(CST_TRICKS_HANDS));
        teams.setText(getMessages().getVal(CST_TEAMS));
        edit.setText(getMessages().getVal(CST_EDIT));
        for (GameEnum g: GameEnum.values()) {
            editGames.getVal(g).setText(g.toString(lg_));
        }
        demo.setText(getMessages().getVal(CST_DEMO));
        for (GameEnum g: GameEnum.values()) {
            demoGames.getVal(g).setText(g.toString(lg_));
        }
        training.setText(getMessages().getVal(CST_TRAINING));
        for (ChoiceTarot c: ChoiceTarot.values()) {
            trainingTarot.getVal(c).setText(Games.toString(c,lg_));
        }
        multiStop.setText(getMessages().getVal(CST_MULTI_STOP));
        parameters.setText(getMessages().getVal(CST_PARAMETERS));
        for (GameEnum g: GameEnum.values()) {
            rulesGames.getVal(g).setText(g.toString(lg_));
        }
        players.setText(getMessages().getVal(CST_PLAYERS));
        launching.setText(getMessages().getVal(CST_LAUNCHING));
        timing.setText(getMessages().getVal(CST_TIMING));
        interact.setText(getMessages().getVal(CST_INTERACT));
        language.setText(getMessages().getVal(CST_LANGUAGE));
        displaying.setText(getMessages().getVal(CST_DISPLAYING));
        for (GameEnum g: GameEnum.values()) {
            displayingGames.getVal(g).setText(g.toString(lg_));
        }
        help.setText(getMessages().getVal(CST_HELP));
        generalHelp.setText(getMessages().getVal(CST_GENERAL_HELP));
        if (welcomeLabel != null) {
            welcomeLabel.setText(StringUtil.simpleStringsFormat(getMessages().getVal(CST_WELCOME), pseudo()));
        }
        if (singleModeButton != null) {
            singleModeButton.setTextAndSize(getMessages().getVal(CST_SINGLE_MODE));
        }
        if (multiModeButton != null) {
            multiModeButton.setTextAndSize(getMessages().getVal(CST_MULTI_MODE));
        }
        if (goHelpMenu != null) {
            goHelpMenu.setText(getMessages().getVal(CST_GO_HELP_MENU));
        }
        lastSavedGameDate.setText(StringUtil.simpleStringsFormat(getMessages().getVal(LAST_SAVED_GAME), dateLastSaved));
    }

    public boolean isSaveHomeFolder() {
        return parametres.isSaveHomeFolder();
    }

    public void setSingle(boolean _single) {
        single = _single;
    }

    public Menu getFile() {
        return file;
    }

    public MenuItem getLoad() {
        return load;
    }

    public MenuItem getSave() {
        return save;
    }

    public MenuItem getChange() {
        return change;
    }

    public MenuItem getExit() {
        return exit;
    }

    public Menu getDeal() {
        return deal;
    }

    public MenuItem getConsulting() {
        return consulting;
    }

    public CheckBoxMenuItem getPause() {
        return pause;
    }

    public MenuItem getHelpGame() {
        return helpGame;
    }

    public MenuItem getTricksHands() {
        return tricksHands;
    }

    public MenuItem getTeams() {
        return teams;
    }

    public Menu getEdit() {
        return edit;
    }

    public EnumMap<GameEnum,MenuItem> getEditGames() {
        return editGames;
    }

    public Menu getDemo() {
        return demo;
    }

    public EnumMap<GameEnum,MenuItem> getDemoGames() {
        return demoGames;
    }

    public Menu getTraining() {
        return training;
    }

    public EnumMap<ChoiceTarot,MenuItem> getTrainingTarot() {
        return trainingTarot;
    }

    public MenuItem getMultiStop() {
        return multiStop;
    }

    public Menu getParameters() {
        return parameters;
    }

    public EnumMap<GameEnum,MenuItem> getRulesGames() {
        return rulesGames;
    }

    public MenuItem getPlayers() {
        return players;
    }

    public MenuItem getLaunching() {
        return launching;
    }

    public MenuItem getTiming() {
        return timing;
    }

    public MenuItem getInteract() {
        return interact;
    }

    public MenuItem getLanguage() {
        return language;
    }

    public Menu getDisplaying() {
        return displaying;
    }

    public EnumMap<GameEnum,MenuItem> getDisplayingGames() {
        return displayingGames;
    }

    public Menu getHelp() {
        return help;
    }

    public MenuItem getGeneralHelp() {
        return generalHelp;
    }

    @Override
    public String getApplicationName() {
        return LaunchingCards.getMainWindowClass();
    }

    @Override
    public String setObject(Object _object) {
        return DocumentWriterCardsMultiUtil.setObject(_object);
    }

    @Override
    public Object getObject(String _object) {
        return DocumentReaderCardsMultiUtil.getObject(_object);
    }

    public Net getNet() {
        return net;
    }

    public StringMap<StringMap<PreparedPagesCards>> getPreparedBelote() {
        return preparedBelote;
    }

    public StringMap<StringMap<PreparedPagesCards>> getPreparedPresident() {
        return preparedPresident;
    }

    public StringMap<StringMap<PreparedPagesCards>> getPreparedTarot() {
        return preparedTarot;
    }

    public HelpInitializer getHelpInitializerTask() {
        return helpInitializerTask;
    }

    public void setHelpInitializerTask(HelpInitializer _helpInitializerTask) {
        this.helpInitializerTask = _helpInitializerTask;
    }

    public AbstractThread getHelpInitializerThread() {
        return helpInitializerThread;
    }

    public void setHelpInitializerThread(AbstractThread _helpInitializerThread) {
        this.helpInitializerThread = _helpInitializerThread;
    }

    public DialogDisplayingBelote getDialogDisplayingBelote() {
        return dialogDisplayingBelote;
    }

    public DialogDisplayingTarot getDialogDisplayingTarot() {
        return dialogDisplayingTarot;
    }

    public DialogDisplayingPresident getDialogDisplayingPresident() {
        return dialogDisplayingPresident;
    }

    public DialogHelpBelote getDialogHelpBelote() {
        return dialogHelpBelote;
    }

    public DialogHelpPresident getDialogHelpPresident() {
        return dialogHelpPresident;
    }

    public DialogHelpTarot getDialogHelpTarot() {
        return dialogHelpTarot;
    }

    public DialogRulesBelote getDialogRulesBelote() {
        return dialogRulesBelote;
    }

    public DialogRulesPresident getDialogRulesPresident() {
        return dialogRulesPresident;
    }

    public DialogRulesTarot getDialogRulesTarot() {
        return dialogRulesTarot;
    }

    public DialogTricksBelote getDialogTricksBelote() {
        return dialogTricksBelote;
    }

    public DialogTricksPresident getDialogTricksPresident() {
        return dialogTricksPresident;
    }

    public DialogTricksTarot getDialogTricksTarot() {
        return dialogTricksTarot;
    }

    public EditorBelote getEditorBelote() {
        return editorBelote;
    }

    public EditorPresident getEditorPresident() {
        return editorPresident;
    }

    public EditorTarot getEditorTarot() {
        return editorTarot;
    }

    public DialogTeamsPlayers getDialogTeamsPlayers() {
        return dialogTeamsPlayers;
    }

    public DialogNicknames getDialogNicknames() {
        return dialogNicknames;
    }

    public DialogSoft getDialogSoft() {
        return dialogSoft;
    }

    public DialogServer getDialogServer() {
        return dialogServer;
    }

    public CardFactories getCardFactories() {
        return cardFactories;
    }
}
