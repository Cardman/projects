package cards.gui;






import cards.belote.*;
import cards.belote.sml.*;
import cards.enumerations.*;
import cards.facade.*;
import cards.facade.enumerations.*;
import cards.gui.animations.*;
import cards.gui.containers.*;
import cards.gui.dialogs.*;
import cards.gui.events.*;
//import cards.gui.interfaces.ResultCardsServer;
//import cards.gui.interfaces.*;
import cards.gui.menus.*;
//import cards.gui.menus.QuitMultiEvent;
import cards.main.*;
/*import cards.network.belote.actions.BiddingBelote;
import cards.network.belote.actions.PlayingCardBelote;
import cards.network.belote.displaying.RefreshHandBelote;
import cards.network.belote.displaying.players.RefreshHandPlayingBelote;
import cards.network.common.*;
import cards.network.common.before.*;
import cards.network.president.actions.PlayingCardPresident;
import cards.network.president.displaying.players.RefreshHandPlayingPresident;
import cards.network.sml.DocumentReaderCardsMultiUtil;
import cards.network.sml.DocumentWriterCardsMultiUtil;
import cards.network.tarot.actions.*;
import cards.network.tarot.displaying.players.RefreshHand;
import cards.network.threads.Net;
import cards.network.threads.SendReceiveServerCards;*/
import cards.president.*;
import cards.president.sml.*;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import cards.tarot.sml.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.images.*;
import code.gui.initialize.*;
//import code.gui.initialize.AbstractSocket;
//import code.network.*;
//import code.network.enums.ErrorHostConnectionType;
import code.scripts.messages.gui.*;
import code.sml.*;
import code.sml.util.*;
//import code.stream.StreamFolderFile;
import code.stream.*;
import code.util.*;
import code.util.core.*;
//import code.util.core.IndexConstants;
//import code.util.core.StringUtil;

/**

    fenetre principale non redimensionnable
 Au premier lancement, il y une barre de menus et quatre boutons de jeu*/
public final class WindowCards extends GroupFrame implements WindowCardsInt,AbsOpenQuit {
//public final class WindowCards extends NetGroupFrame

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

//    private static final String UNKNOWN_HOST = "unknownHost";

//    private static final String NOT_CONNECTED = "notConnected";

    private static final String EMPTY_STRING = "";
    private static final String LAST_SAVED_GAME = "lastSavedGame";

//    private static final char LINE_RETURN = '\n';

//    private BasicClient threadEmission;

    private final CustList<FrameGeneralHelp> helpFrames = new CustList<FrameGeneralHelp>();

//    private ContainerGame containerGame;
//    private final Clock clock;

    private final AbsPlainLabel lastSavedGameDate;

    private String dateLastSaved = EMPTY_STRING;

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
    private boolean partieSauvegardee;

//    private boolean single = true;
//    private boolean forceBye;

    //file menu

    private AbsMenu file;
    private AbsMenuItem load;
    private AbsMenuItem save;
    private AbsMenuItem change;
    private AbsMenuItem exit;

    //deal menu

    private AbsMenu deal;
    private AbsMenuItem consulting;
    private AbsCheckBoxMenuItem pause;
    private AbsMenuItem helpGame;
    private AbsMenuItem tricksHands;
    private AbsMenuItem teams;
    private AbsMenu edit;
    private final IdMap<GameEnum,AbsMenuItem> editGames = new IdMap<GameEnum,AbsMenuItem>();
    private AbsMenu demo;
    private final IdMap<GameEnum,AbsMenuItem> demoGames = new IdMap<GameEnum,AbsMenuItem>();
    private AbsMenu training;
    private final IdMap<ChoiceTarot,AbsMenuItem> trainingTarot = new IdMap<ChoiceTarot,AbsMenuItem>();
//    private AbsMenuItem multiStop;

    //parameters menu

    private AbsMenu parameters;
    private final IdMap<GameEnum,AbsMenuItem> rulesGames = new IdMap<GameEnum,AbsMenuItem>();
    private AbsMenuItem players;
    private AbsMenuItem launching;
//    private AbsMenuItem timing;
//    private AbsMenuItem interact;
//    private AbsMenuItem language;
//    private AbsMenu displaying;
//    private final IdMap<GameEnum,AbsMenuItem> displayingGames = new IdMap<GameEnum,AbsMenuItem>();

    //parameters help

    private AbsMenu help;
    private AbsMenuItem generalHelp;

    //labels at main menu

    private AbsPlainLabel welcomeLabel;
    private AbsPlainButton singleModeButton;
    private AbsPlainButton multiModeButton;
    private AbsPlainLabel goHelpMenu;
//    private final Net net = new Net();

//    private final StringMap<StringMap<PreparedPagesCards>> preparedBelote;
//    private final StringMap<StringMap<PreparedPagesCards>> preparedPresident;
//    private final StringMap<StringMap<PreparedPagesCards>> preparedTarot;
    //private final boolean standalone;
    private HelpInitializer helpInitializerTask;
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
    private final EditorBelote editorBelote;
    private final EditorPresident editorPresident;
    private final EditorTarot editorTarot;
//    private final DialogTeamsPlayers dialogTeamsPlayers;
    private final DialogNicknames dialogNicknames;
//    private final DialogSoft dialogSoft;
//    private final DialogServerCards dialogServer;
//    private final CardFactories cardFactories;
//    private ResultCardsServerInteract resultCardsServerInteract;
//    private StringMap<StringMap<String>> images = new StringMap<StringMap<String>>();
    private final WindowCardsCore core;
    public WindowCards(String _lg, AbstractProgramInfos _list,
                       StringMap<StringMap<PreparedPagesCards>> _belote,
                       StringMap<StringMap<PreparedPagesCards>> _president,
                       StringMap<StringMap<PreparedPagesCards>> _tarot) {
        super(_lg, _list);
        GuiBaseUtil.choose(_lg, this, _list.getCommon());
        core = new WindowCardsCore(_lg, _list, _belote, _president, _tarot);
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
        editorBelote = new EditorBelote(_list);
        editorPresident = new EditorPresident(_list);
        editorTarot = new EditorTarot(_list);
//        dialogTeamsPlayers = new DialogTeamsPlayers(_list);
        dialogNicknames = new DialogNicknames(_list);
//        dialogSoft = new DialogSoft(_list);
//        dialogServer = new DialogServerCards(_list);
//        cardFactories = _cardFactories;
//        preparedBelote = _belote;
//        preparedPresident = _president;
//        preparedTarot = _tarot;
//        pseudosJoueurs=new Nicknames(getLanguageKey());
        setAccessFile(DIALOG_ACCESS);
        setFocusable(true);
        requestFocus();
        setFocusableWindowState(true);
        setImageIconFrame(LaunchingCards.getIcon(getImageFactory()));
//        clock = new Clock(_list);
        lastSavedGameDate = getCompoFactory().newPlainLabel("");
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
        initMessageName();
        lastSavedGameDate.setText(StringUtil.simpleStringsFormat(getMessages().getVal(LAST_SAVED_GAME), dateLastSaved));

//        pseudosJoueurs = DocumentReaderCardsUnionUtil.getNicknames(getLanguageKey(),StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.PLAYERS),getFileCoreStream(),getStreams()));
//        if (!pseudosJoueurs.isValidNicknames()) {
//            pseudosJoueurs = new Nicknames(getLanguageKey());
//            pseudosJoueurs.sauvegarder(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.PLAYERS),getStreams());
//        }
        /*Parametre de lancement*/
        initMenus();

        if(core.getParametres().getLancement().isEmpty()) {
            menuPrincipal();
        } else {
            MenuItemUtils.setEnabledMenu(getTricksHands(),false);
            MenuItemUtils.setEnabledMenu(getTeams(),false);
//            MenuItemUtils.setEnabledMenu(getMultiStop(),false);
            MenuItemUtils.setEnabledMenu(getLoad(),true);
            MenuItemUtils.setEnabledMenu(getEdit(),true);
            MenuItemUtils.setEnabledMenu(getTraining(),true);
            if(core.getParametres().getLancement().first()==GameEnum.BELOTE) {
                core.setContainerGame(new ContainerSingleBelote(this));
            } else if(core.getParametres().getLancement().first()==GameEnum.PRESIDENT) {
                core.setContainerGame(new ContainerSinglePresident(this));
            } else {
                core.setContainerGame(new ContainerSingleTarot(this));
            }
            MenuItemUtils.setEnabledMenu(change,true);
            ((ContainerSingle) core.getContainerGame()).modify();
        }
//        exitMode(_list);
//        setDefaultCloseOperation(GuiConstants.EXIT_ON_CLOSE);
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

    private void ajouterBoutonPrincipal(String _texte,GameEnum _nomJeu,AbsPanel _container) {
        AbsPlainButton bouton_=getCompoFactory().newPlainButton(_texte);
//        bouton_.addMouseListener(new EcouteurBoutonPrincipal(_nomJeu));
        bouton_.addActionListener(new ListenerBeginGame(_nomJeu, this));
        _container.add(bouton_);
    }
    public void clearHelpDialogs() {
        helpFrames.clear();
    }
    public SoftParams getParametresLogiciel() {
        return new SoftParams(core.getParametres());
    }
    public Nicknames getPseudosJoueurs() {
        return new Nicknames(core.getPseudosJoueurs());
    }
    public RulesBelote getReglesBelote() {
        return new RulesBelote(core.getReglesBelote());
    }

    public DisplayingBelote getDisplayingBelote() {
        return new DisplayingBelote(core.getDisplayingBelote());
    }
    public RulesPresident getReglesPresident() {
        return new RulesPresident(core.getReglesPresident());
    }

    public DisplayingPresident getDisplayingPresident() {
        return new DisplayingPresident(core.getDisplayingPresident());
    }

    public RulesTarot getReglesTarot() {
        return new RulesTarot(core.getReglesTarot());
    }

    public DisplayingTarot getDisplayingTarot() {
        return new DisplayingTarot(core.getDisplayingTarot());
    }

//    @Override
//    public void pack() {
//        if (isVisible()) {
//            super.pack();
//        }
//    }
    public Clock getClock() {
        return core.getClock();
    }
    public AbsPlainLabel getLastSavedGameDate() {
        return lastSavedGameDate;
    }

    /**server and client
     Method allowing the client to send a serializable object by its socket
     @param _serializable the serializable object to be sent
     */
    /*public boolean sendObject(PlayerActionBeforeGameCards _serializable) {
        return trySendString(DocumentWriterCardsMultiUtil.playerActionBeforeGameCards(_serializable), getSocket());
    }
    public boolean sendObjectTakeCard() {
        return trySendString(DocumentWriterCardsMultiUtil.takeCard(), getSocket());
    }
    public boolean sendObjectPlayGame() {
        return trySendString(DocumentWriterCardsMultiUtil.playGame(), getSocket());
    }
    public boolean sendObject(PlayerActionGame _serializable) {
        return trySendString(DocumentWriterCardsMultiUtil.playerActionGame(_serializable), getSocket());
    }
    public boolean sendObject(RulesBelote _serializable) {
        return trySendString(DocumentWriterBeloteUtil.setRulesBelote(_serializable), getSocket());
    }
    public boolean sendObject(RulesPresident _serializable) {
        return trySendString(DocumentWriterPresidentUtil.setRulesPresident(_serializable), getSocket());
    }
    public boolean sendObject(RulesTarot _serializable) {
        return trySendString(DocumentWriterTarotUtil.setRulesTarot(_serializable), getSocket());
    }*/
    @Override
    public void quit() {
        GuiBaseUtil.trEx(this);
        closeOpened();
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
    public void dispose() {
        changerNombreDePartiesEnQuittant();
        ecrireCoordonnees();
        closeOpened();
        GuiBaseUtil.trEx(this);
    }

    private void closeOpened() {
        if (!helpFrames.isEmpty()) {
//            helpFrames.first().dispose();
            helpFrames.first().setVisible(false);
            generalHelp.setEnabled(true);
        }
    }

    private int saving() {
        //warning message
        return confirm(getMessages().getVal(CST_SAVING),getMessages().getVal(CST_SAVING_TITLE));
    }
    private void changerNombreDePartiesEnQuittant() {
        core.changerNombreDePartiesEnQuittant(this);
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
//        if(reglesPresident.getCommon().getMixedCards() ==MixCardsChoice.EACH_LAUNCHING) {
//            vl_.set(GameEnum.PRESIDENT.ordinal(), "0");
//        }
//        if(reglesBelote.getCommon().getMixedCards() ==MixCardsChoice.EACH_LAUNCHING) {
//            vl_.set(GameEnum.BELOTE.ordinal(), "0");
//        }
//        if(reglesTarot.getCommon().getMixedCards() ==MixCardsChoice.EACH_LAUNCHING) {
//            vl_.set(GameEnum.TAROT.ordinal(), "0");
//        }
//        StreamTextFile.saveTextFile(fileName_, StringUtil.join(vl_, LINE_RETURN),getStreams());
    }
    private void ecrireCoordonnees() {
        MetaPoint point_=getLocation();
        SoftApplicationCore.saveCoords(LaunchingCards.getTempFolder(getFrames()), FileConst.COORDS, point_.getXcoord(),point_.getYcoord(),getStreams());
    }
    /*public int getNoClient() {
        return ((ContainerMulti)containerGame).getNoClient();
    }

    @Override
    public void gearClient(AbstractSocket _newSocket) {
        int nb_ = getSockets().getSockets().size();
        getSockets().getSockets().put(nb_, _newSocket);
        SendReceiveServerCards sendReceiveServer_=new SendReceiveServerCards(_newSocket,this, getNet());
        getThreadFactory().newStartedThread(sendReceiveServer_);
        getSockets().getConnectionsServer().put(nb_ ,sendReceiveServer_);
        IndexOfArrivingCards index_ = new IndexOfArrivingCards();
        index_.setIndex(nb_);
        getSockets().getReadyPlayers().put(nb_ , BoolVal.FALSE);
        getSockets().getPlacesPlayers().put(nb_ ,(byte)(nb_));
        Net.sendObject(_newSocket,index_);
    }

    @Override
    public void loop(Document _readObject, AbstractSocket _socket) {
        Element elt_ = _readObject.getDocumentElement();
        String tagName_ = DocumentReaderCardsMultiUtil.tagName(elt_);
        if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_ENABLED_QUIT,tagName_)) {
            if (((ContainerMulti)containerGame).hasCreatedServer()) {
                getMultiStop().setEnabled(true);
            }
            return;
        }
        if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_DELEGATE_SERVER,tagName_)) {
            DelegateServer del_ = DocumentReaderCardsMultiUtil.getDelegateServer(elt_);
            Net.setGames(del_.getGames(), getNet());
            delegateServer();
            return;
        }
        PlayerActionBeforeGameCards playerActionBeforeGame_ = DocumentReaderCardsMultiUtil.getPlayerActionBeforeGame(elt_);
        if (playerActionBeforeGame_ instanceof IndexOfArrivingCards) {
            if (!StringUtil.quickEq(((IndexOfArrivingCards) playerActionBeforeGame_).getServerName(),Net.getCards())) {
                NewPlayerCards p_ = new NewPlayerCards();
                p_.setAcceptable(false);
                p_.setArriving(true);
                p_.setIndex(playerActionBeforeGame_.getIndex());
                p_.setPseudo(pseudo());
                p_.setLanguage(getLanguageKey());
                Net.sendObject(_socket,p_);
                return;
            }
            ContainerMulti container_ = (ContainerMulti)containerGame;
            container_.setNoClient(playerActionBeforeGame_.getIndex());
            NewPlayerCards p_ = new NewPlayerCards();
            p_.setAcceptable(true);
            p_.setArriving(true);
            p_.setIndex(container_.getNoClient());
            p_.setPseudo(pseudo());
            p_.setLanguage(getLanguageKey());
            Net.sendObject(_socket,p_);
            return;
        }
        ContainerMulti container_ = (ContainerMulti)containerGame;
        if (playerActionBeforeGame_ instanceof ChoosenPlace) {
            container_.updatePlaces((ChoosenPlace) playerActionBeforeGame_);
            return;
        }
        if (playerActionBeforeGame_ instanceof Ready) {
            container_.updateReady((Ready) playerActionBeforeGame_);
            return;
        }
        if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_PLAYERS_NAME_PRESENT,tagName_)) {
            PlayersNamePresent infos_ = DocumentReaderCardsMultiUtil.getPlayersNamePresent(elt_);
            if (infos_.isFirst()) {
                container_.updateFirst(infos_);
            } else {
                container_.updateAfter(infos_);
            }
            return;
        }
        if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_PAUSE,tagName_)) {
            container_.pauseBetweenTrick();
            return;
        }
        if (containerGame instanceof ContainerMultiTarot) {
            ContainerMultiTarot containerTarot_ = (ContainerMultiTarot) containerGame;
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_RESULTS_TAROT,tagName_)) {
                containerTarot_.endGame(DocumentReaderCardsMultiUtil.resultsTarot(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_RULES_TAROT,tagName_)) {
                containerTarot_.updateRules(DocumentReaderTarotUtil.getRulesTarot(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_DEALT_HAND_TAROT,tagName_)) {
                containerTarot_.updateForBeginningGame(DocumentReaderCardsMultiUtil.getDealtHandTarot(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_ALLOW_BIDDING_TAROT,tagName_)) {
                containerTarot_.canBidTarot(DocumentReaderCardsMultiUtil.getAllowBiddingTarot(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_ERROR_BIDDING,tagName_)) {
                containerTarot_.canBid();
                containerTarot_.errorForBidding(DocumentReaderCardsMultiUtil.getErrorBidding(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_ERROR_HANDFUL,tagName_)) {
                containerTarot_.errorPlayingCard(DocumentReaderCardsMultiUtil.getErrorHandful(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_ERROR_PLAYING,tagName_)) {
                containerTarot_.errorPlayingCard(DocumentReaderCardsMultiUtil.getErrorPlaying(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_CALLABLE_CARDS,tagName_)) {
                containerTarot_.displayCalling(DocumentReaderCardsMultiUtil.getCallableCards(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_DOG,tagName_)) {
                containerTarot_.displayDog(DocumentReaderCardsMultiUtil.getDog(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_ERROR_DISCARDING,tagName_)) {
                containerTarot_.errorDiscardingCard(DocumentReaderCardsMultiUtil.getErrorDiscarding(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_DISCARDED_TRUMPS,tagName_)) {
                containerTarot_.showDiscardedTrumps(DocumentReaderCardsMultiUtil.getDiscardedTrumps(elt_));
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_TEAMS_PLAYERS,tagName_)) {
                containerTarot_.showTeams(DocumentReaderCardsMultiUtil.getTeamsPlayers(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_TRICKS_HANDS_TAROT,tagName_)) {
                containerTarot_.showTricksHands(DocumentReaderTarotUtil.getTricksHandsTarot(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_ALLOW_PLAYING_TAROT,tagName_)) {
                containerTarot_.canPlayTarot(DocumentReaderCardsMultiUtil.getAllowPlayingTarot(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_DISPLAY_SLAM_BUTTON,tagName_)) {
                containerTarot_.displaySlamButton();
                return;
            }
            PlayerActionGame action_ = DocumentReaderCardsMultiUtil.getPlayerActionGame(elt_);
            if (action_ instanceof BiddingTarot) {
                containerTarot_.displayLastBid((BiddingTarot) action_);
                return;
            }
            if (action_ instanceof DiscardedCard) {
                containerTarot_.updateDiscardingOrCanceling((DiscardedCard) action_);
                return;
            }
            if (action_ instanceof CalledCards) {
                containerTarot_.displayCalledCard((CalledCards) action_);
                return;
            }
            if (action_ != null) {
                if (action_.getActionType() == PlayerActionGameType.SLAM) {
                    containerTarot_.displaySlam(action_);
                    return;
                }
            }
            if (action_ instanceof RefreshHand) {
                containerTarot_.refreshHand((RefreshHand) action_);
                return;
            }
            if (action_ instanceof PlayingCardTarot) {
                containerTarot_.displayPlayedCard((PlayingCardTarot) action_);
                return;
            }
        }
        if (containerGame instanceof ContainerMultiPresident) {
            ContainerMultiPresident containerPresident_ = (ContainerMultiPresident) containerGame;
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_RESULTS_PRESIDENT,tagName_)) {
                containerPresident_.endGame(DocumentReaderCardsMultiUtil.resultsPresident(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_RULES_PRESIDENT,tagName_)) {
                containerPresident_.updateRules(DocumentReaderPresidentUtil.getRulesPresident(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_ALLOW_DISCARDING,tagName_)) {
                containerPresident_.canDiscardPresident(DocumentReaderCardsMultiUtil.getAllowDiscarding(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_RECEIVED_GIVEN_CARDS,tagName_)) {
                containerPresident_.refreshLoserHand(DocumentReaderCardsMultiUtil.getReceivedGivenCards(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_ALLOW_PLAYING_PRESIDENT,tagName_)) {
                containerPresident_.canPlayPresident(DocumentReaderCardsMultiUtil.getAllowPlayingPresident(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_DEALT_HAND_PRESIDENT,tagName_)) {
                containerPresident_.updateForBeginningGame(DocumentReaderCardsMultiUtil.getDealtHandPresident(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_ERROR_PLAYING_PRESIDENT,tagName_)) {
                containerPresident_.errorPlayingCard(DocumentReaderCardsMultiUtil.getErrorPlayingPresident(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_TRICKS_HANDS_PRESIDENT,tagName_)) {
                containerPresident_.showTricksHands(DocumentReaderPresidentUtil.getTricksHandsPresident(elt_));
                return;
            }
            PlayerActionGame action_ = DocumentReaderCardsMultiUtil.getPlayerActionGame(elt_);
            if (action_ instanceof RefreshHandPlayingPresident) {
                containerPresident_.refreshHand((RefreshHandPlayingPresident) action_);
                return;
            }
            if (action_ instanceof PlayingCardPresident) {
                containerPresident_.displayPlayedCard((PlayingCardPresident) action_);
                return;
            }
        }
        if (containerGame instanceof ContainerMultiBelote) {
            ContainerMultiBelote containerBelote_ = (ContainerMultiBelote) containerGame;
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_RESULTS_BELOTE,tagName_)) {
                containerBelote_.endGame(DocumentReaderCardsMultiUtil.resultsBelote(elt_));
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_RULES_BELOTE,tagName_)) {
                containerBelote_.updateRules(DocumentReaderBeloteUtil.getRulesBelote(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_DEALT_HAND_BELOTE,tagName_)) {
                containerBelote_.updateForBeginningGame(DocumentReaderCardsMultiUtil.getDealtHandBelote(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_ALLOW_BIDDING_BELOTE,tagName_)) {
                containerBelote_.canBidBelote(DocumentReaderCardsMultiUtil.getAllowBiddingBelote(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_ERROR_BIDDING_BELOTE,tagName_)) {
                containerBelote_.canBid();
                containerBelote_.errorForBidding(DocumentReaderCardsMultiUtil.getErrorBiddingBelote(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_ERROR_PLAYING_BELOTE,tagName_)) {
                containerBelote_.errorPlayingCard(DocumentReaderCardsMultiUtil.getErrorPlayingBelote(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_TEAMS_PLAYERS,tagName_)) {
                containerBelote_.showTeams(DocumentReaderCardsMultiUtil.getTeamsPlayers(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_TRICKS_HANDS_BELOTE,tagName_)) {
                containerBelote_.showTricksHands(DocumentReaderBeloteUtil.getTricksHandsBelote(elt_));
                return;
            }
            if (StringUtil.quickEq(DocumentReaderCardsMultiUtil.TYPE_ALLOW_PLAYING_BELOTE,tagName_)) {
                containerBelote_.canPlayBelote(DocumentReaderCardsMultiUtil.getAllowPlayingBelote(elt_));
                return;
            }

            PlayerActionGame action_ = DocumentReaderCardsMultiUtil.getPlayerActionGame(elt_);
            if (action_ instanceof BiddingBelote) {
                containerBelote_.displayLastBid((BiddingBelote) action_);
                return;
            }
            if (action_ instanceof RefreshHandBelote) {
                containerBelote_.refreshHand((RefreshHandBelote) action_);
                return;
            }
            if (action_ instanceof RefreshHandPlayingBelote) {
                containerBelote_.refreshHand((RefreshHandPlayingBelote) action_);
                return;
            }
            if (action_ instanceof PlayingCardBelote) {
                containerBelote_.displayPlayedCard((PlayingCardBelote) action_);
                return;
            }
        }
    }

    @Override
    public void quitNetwork(Exiting _exit, AbstractSocket _socket) {
        menuPrincipal();
        closeConnexion(_exit,_socket);
        if (_exit != null && _exit.isClosing()) {
            basicDispose();
            return;
        }
        pack();
        if (_exit != null && _exit.isForced() && !_exit.isBusy()) {
            getFrames().getMessageDialogAbs().input(getCommonFrame(), getTooManyString(), getTooManyString(), getLanguageKey(), GuiConstants.ERROR_MESSAGE);
            //JOptionPane.showMessageDialog(window, window.getTooManyString(), window.getTooManyString(), JOptionPane.INFORMATION_MESSAGE);
        }
    }*/

//    public boolean isForceBye() {
//        return forceBye;
//    }
//    public void menuMultiGames() {
//        containerGame = noGame();
//        MenuItemUtils.setEnabledMenu(change,false);
//        //Activer le menu Partie/Demo
//        MenuItemUtils.setEnabledMenu(getDemo(),false);
//        //desactiver le menu Partie/aide au jeu
//        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
//        //desactiver le menu Partie/conseil
//        MenuItemUtils.setEnabledMenu(getConsulting(),false);
//        //Desactiver le menu Partie/Pause
//        MenuItemUtils.setEnabledMenu(getPause(),false);
//        MenuItemUtils.setEnabledMenu(getEdit(),false);
//        MenuItemUtils.setEnabledMenu(getTraining(),false);
//        for (AbsMenuItem m: getRulesGames().values()) {
//            MenuItemUtils.setEnabledMenu(m,false);
//        }
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
//        MenuItemUtils.setEnabledMenu(getLoad(),false);
//        MenuItemUtils.setEnabledMenu(getSave(),false);
//        MenuItemUtils.setEnabledMenu(getChange(),false);
//        container_.add(clock);
//        container_.add(lastSavedGameDate);
//        setContentPane(container_);
//        pack();
//    }
    public void menuSoloGames() {
        core.setContainerGame(noGame());
        MenuItemUtils.setEnabledMenu(change,false);
        //Activer le menu Partie/Demo
        MenuItemUtils.setEnabledMenu(getDemo(),true);
        //desactiver le menu Partie/aide au jeu
        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
        //desactiver le menu Partie/conseil
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        //Desactiver le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(getPause(),false);
        core.getContainerGame().setChangerPileFin(false);
        core.getContainerGame().finirParties();
        setTitle(Launching.WELCOME.toString(getLanguageKey()));
        AbsPanel container_=getCompoFactory().newGrid(0,1);
        /*Pour montrer qu'on a de l'attention a l'utilisateur*/
        container_.add(getCompoFactory().newPlainLabel(StringUtil.simpleStringsFormat(getMessages().getVal(CST_WELCOME), pseudo())));
        /*Cree les boutons de jeu*/
        boutonsSolo(container_);
        AbsPlainButton button_ = getCompoFactory().newPlainButton(getMessages().getVal(CST_MAIN_MENU));
        button_.addActionListener(new BackToMainMenuEvent(this));
        container_.add(button_);
        //Ajout d'une etiquette pour indiquer ou aller pour avoir de l'aide
        if (goHelpMenu == null) {
            goHelpMenu = getCompoFactory().newPlainLabel(getMessages().getVal(CST_GO_HELP_MENU));
        }
        container_.add(goHelpMenu);
        MenuItemUtils.setEnabledMenu(getSave(),false);
        MenuItemUtils.setEnabledMenu(getChange(),false);
        container_.add(core.getClock());
        container_.add(lastSavedGameDate);
        setContentPane(container_);
        pack();
    }

    private void boutonsSolo(AbsPanel _container) {
        String lg_ = getLanguageKey();
        for (GameEnum jeu2_:GameEnum.values()) {
            ajouterBoutonPrincipal(jeu2_.toString(lg_),jeu2_, _container);
        }
    }

    public void menuPrincipal() {
//        MenuItemUtils.setEnabledMenu(getMultiStop(),false);
        MenuItemUtils.setEnabledMenu(getTricksHands(),false);
        MenuItemUtils.setEnabledMenu(getTeams(),false);
        core.setContainerGame(noGame());
        MenuItemUtils.setEnabledMenu(change,false);
        //Activer le menu Partie/Demo
        MenuItemUtils.setEnabledMenu(getDemo(),true);
        //desactiver le menu Partie/aide au jeu
        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
        //desactiver le menu Partie/conseil
        MenuItemUtils.setEnabledMenu(getConsulting(),false);
        //Desactiver le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(getPause(),false);
        MenuItemUtils.setEnabledMenu(getLoad(),true);
        MenuItemUtils.setEnabledMenu(getEdit(),true);
        MenuItemUtils.setEnabledMenu(getTraining(),true);
        for (AbsMenuItem m: getRulesGames().values()) {
            MenuItemUtils.setEnabledMenu(m,true);
        }
        core.getContainerGame().finirParties();
        setTitle(Launching.WELCOME.toString(getLanguageKey()));
        AbsPanel pane_ = getCompoFactory().newGrid(0,1);
        /*Pour montrer qu'on a de l'attention a l'utilisateur*/
        welcomeLabel = getCompoFactory().newPlainLabel(StringUtil.simpleStringsFormat(getMessages().getVal(CST_WELCOME), pseudo()));
        pane_.add(welcomeLabel, GuiConstants.CENTER);
        /*Cree les boutons de jeu*/
        singleModeButton = getCompoFactory().newPlainButton(getMessages().getVal(CST_SINGLE_MODE));
        singleModeButton.addActionListener(new ChooseModeEvent(this));
        pane_.add(singleModeButton);
//        multiModeButton = getCompoFactory().newPlainButton(getMessages().getVal(CST_MULTI_MODE));
//        multiModeButton.addActionListener(new ChooseModeEvent(this, false));
//        pane_.add(multiModeButton);
        //Ajout d'une etiquette pour indiquer ou aller pour avoir de l'aide
        if (goHelpMenu == null) {
            goHelpMenu = getCompoFactory().newPlainLabel(getMessages().getVal(CST_GO_HELP_MENU));
        }
        pane_.add(goHelpMenu);
        pane_.add(core.getClock());
        pane_.add(lastSavedGameDate);
        setContentPane(pane_);
        MenuItemUtils.setEnabledMenu(getSave(),false);
        MenuItemUtils.setEnabledMenu(getChange(),false);
    }
    private void initMessageName() {
//        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), getClass());
        setMessages(WindowCards.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, getLanguageKey(), getAccessFile()));
    }
    public void loadGameBegin(String _file) {
        core.setContainerGame(noGame());
        tryToLoadDeal(_file);
    }
    public ContainerNoGame noGame() {
        ContainerNoGame c_ = new ContainerNoGame();
        c_.setParametres(getParametresLogiciel());
        c_.setReglesTarot(getReglesTarot());
        c_.setReglesPresident(getReglesPresident());
        c_.setReglesBelote(getReglesBelote());
        c_.setDisplayingBelote(getDisplayingBelote());
        c_.setDisplayingPresident(getDisplayingPresident());
        c_.setDisplayingTarot(getDisplayingTarot());
        c_.setPseudosJoueurs(getPseudosJoueurs());
        c_.setMessages(getMessages());
        return c_;
    }
    private void initFileMenu() {
        /* Fichier */
        file=getCompoFactory().newMenu(getMessages().getVal(CST_FILE));
        /* Fichier/Charger "accessible n'importe quand"*/
        load=getCompoFactory().newMenuItem(getMessages().getVal(CST_LOAD));
        load.addActionListener(new LoadGameEventCards(this));
        load.setAccelerator(GuiConstants.VK_O, GuiConstants.CTRL_DOWN_MASK);
        file.addMenuItem(load);
        /* Fichier/Sauvegarder "accessible que lorsqu'on joue une partie de cartes"*/
        save=getCompoFactory().newMenuItem(getMessages().getVal(CST_SAVE));
        save.addActionListener(new SaveGameEventCards(this));
        save.setAccelerator(GuiConstants.VK_S, GuiConstants.CTRL_DOWN_MASK);
        file.addMenuItem(save);
        file.addSeparator();
        /* Fichier/Changer de jeu ACCESSIBLE n'importe quand sauf au menu principal,
        on y revient lorsque c'est accessible*/
        change=getCompoFactory().newMenuItem(getMessages().getVal(CST_CHANGE));
        changeMode(this, change);
        file.addMenuItem(change);
        file.addSeparator();
        exit=getCompoFactory().newMenuItem(getMessages().getVal(CST_EXIT));
        exit.addActionListener(new QuitEvent(this));
        exit.setAccelerator(GuiConstants.VK_ESCAPE,0);
        file.addMenuItem(exit);
        getJMenuBar().add(file);
    }

    public static void changeMode(WindowCardsInt _w, AbsMenuItem _change) {
        MenuItemUtils.setEnabledMenu(_change,false);
        _change.addActionListener(new ChangeGameEvent(_w));
        _change.setAccelerator(GuiConstants.VK_J, GuiConstants.CTRL_DOWN_MASK);
    }

    public void loadGame() {
//        if (!load.isEnabled()) {
//            return;
//        }
//        if (containerGame instanceof ContainerMulti) {
//            return;
//        }
        if(core.getContainerGame().playingSingleGame()&&!partieSauvegardee) {
            if(isPasse()||!core.getContainerGame().isThreadAnime()) {
                int choix_=saving();
                if(choix_!=GuiConstants.CANCEL_OPTION) {
                    if(choix_==GuiConstants.YES_OPTION) {
                        String fichier_=dialogueFichierSauvegarde();
                        if(!fichier_.isEmpty()) {
                            core.getContainerGame().saveCurrentGame(fichier_,getStreams());
                            tryToLoadDeal();
                        }
                    } else {
                        tryToLoadDeal();
                    }
                }
            }
        } else if(isPasse()||!core.getContainerGame().isThreadAnime()) {
            tryToLoadDeal();
        }
    }
    public boolean isPasse() {
        return !(core.getContainerGame() instanceof ContainerSingle) || ((ContainerSingle) core.getContainerGame()).isPasse();
    }

    private void tryToLoadDeal() {
        partieSauvegardee=true;
        String nomFichier_=dialogueFichierChargement();
        if (nomFichier_.isEmpty()) {
            return;
        }
        tryToLoadDeal(nomFichier_);
    }

    private void tryToLoadDeal(String _nomFichier) {
        String content_ = StreamTextFile.contentsOfFile(_nomFichier, getFileCoreStream(), getStreams());
        Document doc_ = DocumentBuilder.parseSax(content_);
        Element elt_ = doc_.getDocumentElement();
        String tagName_ = elt_.getTagName();
        if (StringUtil.quickEq(tagName_, "GameBelote")) {
            GameBelote par_ = DocumentReaderBeloteUtil.getGameBelote(doc_);
            CheckerGameBeloteWithRules.check(par_);
            if (!par_.getError().isEmpty()) {
                erreurDeChargement(_nomFichier);
                return;
            }
            ContainerSingleBelote containerGame_ = new ContainerSingleBelote(this);
            containerGame_.getPar().jouerBelote(par_);
            containerGame_.load();
            partieSauvegardee=false;
            core.setContainerGame(containerGame_);
            MenuItemUtils.setEnabledMenu(change,true);
            return;
        }
        if (StringUtil.quickEq(tagName_, "GamePresident")) {
            GamePresident par_ = DocumentReaderPresidentUtil.getGamePresident(doc_);
            CheckerGamePresidentWithRules.check(par_);
            if (!par_.getError().isEmpty()) {
                erreurDeChargement(_nomFichier);
                return;
            }
            ContainerSinglePresident containerGame_ = new ContainerSinglePresident(this);
            containerGame_.getPar().jouerPresident(par_);
            containerGame_.load();
            partieSauvegardee=false;
            core.setContainerGame(containerGame_);
            MenuItemUtils.setEnabledMenu(change,true);
            return;
        }
        if (StringUtil.quickEq(tagName_, "GameTarot")) {
            GameTarot par_ = DocumentReaderTarotUtil.getGameTarot(doc_);
            CheckerGameTarotWithRules.check(par_);
            if (!par_.getError().isEmpty()) {
                erreurDeChargement(_nomFichier);
                return;
            }
            ContainerSingleTarot containerGame_ = new ContainerSingleTarot(this);
            containerGame_.getPar().jouerTarot(par_);
            containerGame_.load();
            partieSauvegardee=false;
            core.setContainerGame(containerGame_);
            MenuItemUtils.setEnabledMenu(change,true);
            return;
        }
        erreurDeChargement(_nomFichier);
    }

    public void saveGame() {
//        if (!save.isEnabled()) {
//            return;
//        }
//        if (containerGame instanceof ContainerMulti) {
//            return;
//        }
        if(isPasse()||!core.getContainerGame().isThreadAnime()) {
            String fichier_=dialogueFichierSauvegarde();

            if(!fichier_.isEmpty()) {
                core.getContainerGame().saveCurrentGame(fichier_,getStreams());
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
        if(core.getContainerGame().playingSingleGame()&&!partieSauvegardee) {
            if(isPasse()||!core.getContainerGame().isThreadAnime()) {
                int choix_=saving();
                if(choix_!=GuiConstants.CANCEL_OPTION) {
                    if(choix_==GuiConstants.YES_OPTION) {
                        String fichier_=dialogueFichierSauvegarde();
                        if(!fichier_.isEmpty()) {
                            core.getContainerGame().saveCurrentGame(fichier_,getStreams());
                            menuSoloGames();
//                            if (single) {
//                                menuSoloGames();
//                            } else {
//                                menuMultiGames();
//                            }
                        }
                    } else {
                        menuSoloGames();
//                        if (single) {
//                            menuSoloGames();
//                        } else {
//                            menuMultiGames();
//                        }
                    }
                }
            }
        } else if(isPasse()||!core.getContainerGame().isThreadAnime()) {
            menuSoloGames();
//            if (single) {
//                menuSoloGames();
//            } else {
//                menuMultiGames();
//            }
        }
    }

    private void initDealMenu() {
        String lg_ = getLanguageKey();
        deal=getCompoFactory().newMenu(getMessages().getVal(CST_DEAL));
        /* Partie/Conseil "accessible uniquement en cours de partie et
        dans les jeux non solitaires"*/
        AbsMenuItem sousMenu_;
        consulting=getCompoFactory().newMenuItem(getMessages().getVal(CST_CONSULTING));
        consulting.setAccelerator(GuiConstants.VK_F1,0);
        consulting.addActionListener(new ConsultEvent(this));
        deal.addMenuItem(consulting);
        /* Partie/Pause Permet de mettre le jeu en pause*/
        pause=getCompoFactory().newCheckBoxMenuItem(getMessages().getVal(CST_PAUSE));
        pause.setAccelerator(GuiConstants.VK_DELETE,0);
//        pause.setAccelerator(CST_PAUSE);
        pause.addActionListener(new PauseEvent(this));
        deal.addMenuItem(pause);
        /* Partie/Pause Permet d avoir de l aide*/
        helpGame=getCompoFactory().newMenuItem(getMessages().getVal(HELP_GAME));
        helpGame.setAccelerator(GuiConstants.VK_F2,0);
        helpGame.addActionListener(new DisplayHelpGameEvent(this));
        deal.addMenuItem(helpGame);
        tricksHands=getCompoFactory().newMenuItem(getMessages().getVal(CST_TRICKS_HANDS));

        tricksHands.addActionListener(new DisplayTricksHandsEvent(this));
        deal.addMenuItem(tricksHands);
        teams=getCompoFactory().newMenuItem(getMessages().getVal(CST_TEAMS));
        teams.addActionListener(new DisplayTeamsEvent(this));
        deal.addMenuItem(teams);
        /* Partie/Editer "Permet d'editer n'importe quelle partie de cartes et accessible n'importe quand"*/
        edit=getCompoFactory().newMenu(getMessages().getVal(CST_EDIT));
        AbsMenuItem sousSousMenu_ = getCompoFactory().newMenuItem(GameEnum.BELOTE.toString(lg_));
        sousSousMenu_.addActionListener(new EditEvent(this, GameEnum.BELOTE));
        edit.addMenuItem(sousSousMenu_);
        editGames.put(GameEnum.BELOTE, sousSousMenu_);
        sousSousMenu_ = getCompoFactory().newMenuItem(GameEnum.PRESIDENT.toString(lg_));
        sousSousMenu_.addActionListener(new EditEvent(this, GameEnum.PRESIDENT));
        edit.addMenuItem(sousSousMenu_);
        editGames.put(GameEnum.PRESIDENT, sousSousMenu_);
        sousSousMenu_ = getCompoFactory().newMenuItem(GameEnum.TAROT.toString(lg_));
        sousSousMenu_.addActionListener(new EditEvent(this, GameEnum.TAROT));
        edit.addMenuItem(sousSousMenu_);
        editGames.put(GameEnum.TAROT, sousSousMenu_);
        deal.addMenuItem(edit);
        /* Partie/Demo "Permet de voir la demostration d une partie"*/
        demo=getCompoFactory().newMenu(getMessages().getVal(CST_DEMO));
        sousMenu_=getCompoFactory().newMenuItem(GameEnum.BELOTE.toString(lg_));
        sousMenu_.addActionListener(new SimulationEvent(this, GameEnum.BELOTE));
        sousMenu_.setAccelerator(GuiConstants.VK_B, GuiConstants.CTRL_DOWN_MASK + GuiConstants.SHIFT_DOWN_MASK);
        demo.addMenuItem(sousMenu_);
        demoGames.put(GameEnum.BELOTE, sousSousMenu_);
        sousMenu_=getCompoFactory().newMenuItem(GameEnum.PRESIDENT.toString(lg_));
        sousMenu_.addActionListener(new SimulationEvent(this, GameEnum.PRESIDENT));
        sousMenu_.setAccelerator(GuiConstants.VK_P, GuiConstants.CTRL_DOWN_MASK + GuiConstants.SHIFT_DOWN_MASK);
        demo.addMenuItem(sousMenu_);
        demoGames.put(GameEnum.PRESIDENT, sousSousMenu_);
        sousMenu_=getCompoFactory().newMenuItem(GameEnum.TAROT.toString(lg_));
        sousMenu_.addActionListener(new SimulationEvent(this, GameEnum.TAROT));
        sousMenu_.setAccelerator(GuiConstants.VK_T, GuiConstants.CTRL_DOWN_MASK + GuiConstants.SHIFT_DOWN_MASK);
        demo.addMenuItem(sousMenu_);
        demoGames.put(GameEnum.TAROT, sousSousMenu_);
        deal.addMenuItem(demo);
        /* Partie/Entrainement "accessible n'importe quand pour pouvoir s'entrainer"*/
        training=getCompoFactory().newMenu(getMessages().getVal(CST_TRAINING));
        /* Partie/Entrainement au Tarot*/
        //Petitasauver,Petitachasser,Petitaemmeneraubout;
        for (ChoiceTarot ct_:ChoiceTarot.values()) {

            sousMenu_=getCompoFactory().newMenuItem(Games.toString(ct_,lg_));
            sousMenu_.addActionListener(new ListenerTrainingTarot(this, ct_));
            training.addMenuItem(sousMenu_);
            trainingTarot.put(ct_, sousMenu_);
        }
        deal.addMenuItem(training);
//        multiStop = getCompoFactory().newMenuItem(getMessages().getVal(CST_MULTI_STOP));
//        multiStop.addActionListener(new QuitMultiEvent(this));
//        deal.addMenuItem(multiStop);
        getJMenuBar().add(deal);
    }

    public void consult() {
//        if (!consulting.isEnabled()) {
//            return;
//        }
        if (!(core.getContainerGame() instanceof ContainerSingle)) {
            return;
        }
        if(!core.getContainerGame().isThreadAnime()) {
            ((ContainerSingle) core.getContainerGame()).conseil();
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
//        if (containerGame instanceof ContainerSingle) {
//            containerGame.setPasse(!containerGame.isPasse());
//            if (pause.isSelected()) {
//                return;
//            }
//            containerGame.setState(null);
//            if (containerGame instanceof ContainerSingleBelote) {
//                if (containerGame.getState() == CardAnimState.BID_BELOTE) {
//                    containerGame.thread(new AnimationBidBelotePause(((ContainerSingleBelote)containerGame)));
//                    return;
//                }
//                containerGame.thread(new AnimationCardBelotePause(((ContainerSingleBelote)containerGame)));
//                return;
//            }
//            if (containerGame instanceof ContainerSingleTarot) {
//                if (containerGame.getState() == CardAnimState.BID_TAROT) {
//                    containerGame.thread(new AnimationBidTarotPause(((ContainerSingleTarot)containerGame)));
//                    return;
//                }
//                containerGame.thread(new AnimationCardTarotPause(((ContainerSingleTarot)containerGame)));
//                return;
//            }
//            containerGame.thread(new AnimationCardPresidentPause(((ContainerSinglePresident)containerGame)));
//            return;
//        }
        if (!(core.getContainerGame() instanceof ContainerSingle)) {
            return;
        }
        ((ContainerSingle) core.getContainerGame()).setPasse(!((ContainerSingle) core.getContainerGame()).isPasse());
    }
    public void displayHelpGame() {
//        if (!helpGame.isEnabled()) {
//            return;
//        }
        if (!(core.getContainerGame() instanceof ContainerSingle)) {
            return;
        }
        ((ContainerSingle) core.getContainerGame()).aideAuJeu();
    }
    public void displayTricksHands() {
//        if (!tricksHands.isEnabled()) {
//            return;
//        }
        if (!(core.getContainerGame() instanceof ContainerPlayableGame)) {
            return;
        }
        ((ContainerPlayableGame) core.getContainerGame()).showTricksHands();
    }
    public void displayTeams() {
//        if (!teams.isEnabled()) {
//            return;
//        }
        if (core.getContainerGame() instanceof ContainerSingleBelote) {
            ((ContainerSingleBelote) core.getContainerGame()).showTeams();
        }
        /*if (containerGame instanceof ContainerMultiBelote) {
            ((ContainerMultiBelote)containerGame).showTeams();
        }*/
        if (core.getContainerGame() instanceof ContainerSingleTarot) {
            ((ContainerSingleTarot) core.getContainerGame()).showTeams();
        }
        /*if (containerGame instanceof ContainerMultiTarot) {
            ((ContainerMultiTarot)containerGame).showTeams();
        }*/

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
            /*if (containerGame instanceof ContainerMulti) {
                return;
            }*/
            if(core.getContainerGame().playingSingleGame()&&!partieSauvegardee) {
                if(isPasse()||!core.getContainerGame().isThreadAnime()) {
                    int choix_=saving();
                    if(choix_!=GuiConstants.CANCEL_OPTION) {
                        if(choix_==GuiConstants.YES_OPTION) {
                            String fichier_=dialogueFichierSauvegarde();
                            if(!fichier_.isEmpty()) {
                                core.getContainerGame().saveCurrentGame(fichier_,getStreams());
                                core.setContainerGame(new ContainerSingleBelote(this));
                                ((ContainerSingleBelote) core.getContainerGame()).editerBelote(partie_);
                                MenuItemUtils.setEnabledMenu(change,true);
                            }
                        } else {
                            core.setContainerGame(new ContainerSingleBelote(this));
                            ((ContainerSingleBelote) core.getContainerGame()).editerBelote(partie_);
                            MenuItemUtils.setEnabledMenu(change,true);
                        }
                    }
                }
            } else {
                core.setContainerGame(new ContainerSingleBelote(this));
                ((ContainerSingleBelote) core.getContainerGame()).editerBelote(partie_);
                MenuItemUtils.setEnabledMenu(change,true);
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
            /*if (containerGame instanceof ContainerMulti) {
                return;
            }*/
            if(core.getContainerGame().playingSingleGame()&&!partieSauvegardee) {
                if(isPasse()||!core.getContainerGame().isThreadAnime()) {
                    int choix_=saving();
                    if(choix_!=GuiConstants.CANCEL_OPTION) {
                        if(choix_==GuiConstants.YES_OPTION) {
                            String fichier_=dialogueFichierSauvegarde();
                            if(!fichier_.isEmpty()) {
                                core.getContainerGame().saveCurrentGame(fichier_,getStreams());

                                core.setContainerGame(new ContainerSinglePresident(this));
                                ((ContainerSinglePresident) core.getContainerGame()).editerPresident(partie_);
                                MenuItemUtils.setEnabledMenu(change,true);
                            }
                        } else {
                            core.setContainerGame(new ContainerSinglePresident(this));
                            ((ContainerSinglePresident) core.getContainerGame()).editerPresident(partie_);
                            MenuItemUtils.setEnabledMenu(change,true);
                        }
                    }
                }
            } else {
                core.setContainerGame(new ContainerSinglePresident(this));
                ((ContainerSinglePresident) core.getContainerGame()).editerPresident(partie_);
                MenuItemUtils.setEnabledMenu(change,true);
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
            /*if (containerGame instanceof ContainerMulti) {
                return;
            }*/
            if(core.getContainerGame().playingSingleGame()&&!partieSauvegardee) {
                if(isPasse()||!core.getContainerGame().isThreadAnime()) {
                    int choix_=saving();
                    if(choix_!=GuiConstants.CANCEL_OPTION) {
                        if(choix_==GuiConstants.YES_OPTION) {
                            String fichier_=dialogueFichierSauvegarde();
                            if(!fichier_.isEmpty()) {
                                core.getContainerGame().saveCurrentGame(fichier_,getStreams());

                                core.setContainerGame(new ContainerSingleTarot(this));
                                ((ContainerSingleTarot) core.getContainerGame()).editerTarot(partie_);
                                MenuItemUtils.setEnabledMenu(change,true);
                            }
                        } else {
                            core.setContainerGame(new ContainerSingleTarot(this));
                            ((ContainerSingleTarot) core.getContainerGame()).editerTarot(partie_);
                            MenuItemUtils.setEnabledMenu(change,true);
                        }
                    }
                }
            } else {
                core.setContainerGame(new ContainerSingleTarot(this));
                ((ContainerSingleTarot) core.getContainerGame()).editerTarot(partie_);
                MenuItemUtils.setEnabledMenu(change,true);
            }
        }
    }
    public void simulateGame(GameEnum _game) {
        if (_game == GameEnum.BELOTE) {
            if (!demo.isEnabled()) {
                return;
            }
            /*if (containerGame instanceof ContainerMulti) {
                return;
            }*/
            core.setContainerGame(new ContainerSimuBelote(this));
        } else if (_game == GameEnum.PRESIDENT) {
            if (!demo.isEnabled()) {
                return;
            }
            /*if (containerGame instanceof ContainerMulti) {
                return;
            }*/
            core.setContainerGame(new ContainerSimuPresident(this));
        } else if (_game == GameEnum.TAROT) {
            if (!demo.isEnabled()) {
                return;
            }
            /*if (containerGame instanceof ContainerMulti) {
                return;
            }*/
            core.setContainerGame(new ContainerSimuTarot(this));
        }
    }
    public void trainingTarot(ChoiceTarot _ct) {
        /*if (containerGame instanceof ContainerMulti) {
            return;
        }*/
        /*Si l'utilisateur a supprime le fichier de configurations alors a la fin
        de l'execution le fichier de configuration sera recree*/
        if(core.getContainerGame().playingSingleGame()&&!partieSauvegardee) {
            int choix_=saving();
            if(choix_!=GuiConstants.CANCEL_OPTION) {
                if(choix_==GuiConstants.YES_OPTION) {
                    String fichier_=dialogueFichierSauvegarde();
                    if(!fichier_.isEmpty()) {
                        core.getContainerGame().saveCurrentGame(fichier_,getStreams());
                    }
                }
                core.setContainerGame(new ContainerSingleTarot(this));
                MenuItemUtils.setEnabledMenu(change,true);
                ((ContainerSingleTarot) core.getContainerGame()).jouerDonneEntrainement(_ct);
            }
        } else {
            core.setContainerGame(new ContainerSingleTarot(this));
            MenuItemUtils.setEnabledMenu(change,true);
            ((ContainerSingleTarot) core.getContainerGame()).jouerDonneEntrainement(_ct);
        }
    }

    /*public void quitMulti() {
        if (!(containerGame instanceof ContainerMulti)) {
            return;
        }
        Quit quit_ = new Quit();
        quit_.setClosing(false);
        quit_.setPlace(((ContainerMulti)containerGame).getIndexInGame());
        quit_.setServer(((ContainerMulti)containerGame).hasCreatedServer());
        sendObject(quit_);
    }*/

    private void initParametersMenu() {
        /* Parametres */
        String lg_ = getLanguageKey();
        parameters=getCompoFactory().newMenu(getMessages().getVal(CST_PARAMETERS));
        AbsMenuItem sousMenu_=getCompoFactory().newMenuItem(GameEnum.BELOTE.toString(lg_));
        sousMenu_.addActionListener(new ManageRulesEvent(this, GameEnum.BELOTE));
        sousMenu_.setAccelerator(GuiConstants.VK_B, GuiConstants.SHIFT_DOWN_MASK);
        parameters.addMenuItem(sousMenu_);
        rulesGames.put(GameEnum.BELOTE, sousMenu_);
        sousMenu_=getCompoFactory().newMenuItem(GameEnum.PRESIDENT.toString(lg_));
        sousMenu_.addActionListener(new ManageRulesEvent(this, GameEnum.PRESIDENT));
        sousMenu_.setAccelerator(GuiConstants.VK_P, GuiConstants.SHIFT_DOWN_MASK);
        parameters.addMenuItem(sousMenu_);
        rulesGames.put(GameEnum.PRESIDENT, sousMenu_);
        sousMenu_=getCompoFactory().newMenuItem(GameEnum.TAROT.toString(lg_));
        sousMenu_.addActionListener(new ManageRulesEvent(this, GameEnum.TAROT));
        sousMenu_.setAccelerator(GuiConstants.VK_T, GuiConstants.SHIFT_DOWN_MASK);
        parameters.addMenuItem(sousMenu_);
        rulesGames.put(GameEnum.TAROT, sousMenu_);
        players=getCompoFactory().newMenuItem(getMessages().getVal(CST_PLAYERS));
        players.addActionListener(new ManageNicknameEvent(this));
        players.setAccelerator(GuiConstants.VK_J, GuiConstants.CTRL_DOWN_MASK + GuiConstants.ALT_DOWN_MASK);
        parameters.addMenuItem(players);
        launching=getCompoFactory().newMenuItem(getMessages().getVal(CST_LAUNCHING));
        launching.addActionListener(new ManageSoftEvent(this, CST_LAUNCHING));
        launching.setAccelerator(GuiConstants.VK_L, GuiConstants.CTRL_DOWN_MASK);
        parameters.addMenuItem(launching);
        core.commonParametersMenu(parameters,this,this);
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
        /* Partie/Editer "Permet d'editer n'importe quelle partie de cartes et accessible n'importe quand"*/
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
    public void manageRules(GameEnum _game) {
        String lg_ = getLanguageKey();
        if (_game == GameEnum.BELOTE) {
            DialogRulesBelote.initDialogRulesBelote(_game.toString(lg_), this, core.getReglesBelote());
            RulesBelote reglesBelote_=DialogRulesBelote.getRegles(getDialogRulesBelote());
            if (!DialogRulesBelote.isValidated(getDialogRulesBelote())) {
                return;
            }
            core.setReglesBelote(reglesBelote_);
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.RULES_BELOTE), DocumentWriterBeloteUtil.setRulesBelote(core.getReglesBelote()),getStreams());
            core.getContainerGame().setRulesBelote(core.getReglesBelote());
        } else if (_game == GameEnum.PRESIDENT) {
            DialogRulesPresident.initDialogRulesPresident(_game.toString(lg_), this, core.getReglesPresident());
            DialogRulesPresident.setPresidentDialog(true, 0,this);
            RulesPresident rules_ = DialogRulesPresident.getRegles(getDialogRulesPresident());
            if (!DialogRulesPresident.isValidated(getDialogRulesPresident())) {
                return;
            }
            core.setReglesPresident(rules_);
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.RULES_PRESIDENT), DocumentWriterPresidentUtil.setRulesPresident(core.getReglesPresident()),getStreams());
            core.getContainerGame().setRulesPresident(core.getReglesPresident());
        } else if (_game == GameEnum.TAROT) {
            DialogRulesTarot.initDialogRulesTarot(_game.toString(lg_), this, core.getReglesTarot());
            DialogRulesTarot.setTarotDialog(true,0,this);
            RulesTarot reglesTarot_=DialogRulesTarot.getRegles(getDialogRulesTarot());
            if (!DialogRulesTarot.isValidated(getDialogRulesTarot())) {
                return;
            }
            core.setReglesTarot(reglesTarot_);
            StreamTextFile.saveTextFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.RULES_TAROT), DocumentWriterTarotUtil.setRulesTarot(core.getReglesTarot()),getStreams());
            core.getContainerGame().setRulesTarot(core.getReglesTarot());
        }
    }
    public void manageNicknames() {
        DialogNicknames.initDialogNicknames(getMessages().getVal(CST_PLAYERS), this);
        core.setPseudosJoueurs(DialogNicknames.getPseudos(getDialogNicknames()));
        core.getPseudosJoueurs().sauvegarder(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.PLAYERS),getStreams());
        core.getContainerGame().setNicknames(core.getPseudosJoueurs());
    }
    public void manageSoft(String _key) {
        core.manageSoft(this,this,_key);
//        DialogSoft.initDialogSoft(getMessages().getVal(_key), this);
//        DialogSoft.setDialogSoft(_key, this);
//        parametres=DialogSoft.getParametres(getDialogSoft());
//        parametres.sauvegarder(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.PARAMS),getStreams());
//        containerGame.setSettings(parametres);
    }
    public void manageLanguage() {
        core.manageLanguage(this);
//        if (!canChangeLanguageAll()) {
//            FrameUtil.showDialogError(this, GuiConstants.ERROR_MESSAGE);
//            return;
//        }
//        LanguageDialog.setLanguageDialog(this, getMessages().getVal(CST_LANGUAGE));
//        String langue_ = LanguageDialog.getStaticLanguage(getLanguageDialog());
//        LanguageDialog.changeLanguage(langue_,getFrames(),LaunchingCards.getTempFolder(getFrames()));
    }
    public void displayingGame(GameEnum _game) {
        core.displayingGame(this,_game);
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
    private void initHelpMenu() {
        /* Aide */
        help=getCompoFactory().newMenu(getMessages().getVal(CST_HELP));
        /* Aide/Aide generale Explication du fonctionnement du logiciel et des regles utilisables*/
        generalHelp=getCompoFactory().newMenuItem(getMessages().getVal(CST_GENERAL_HELP));
        MenuItemUtils.setEnabledMenu(generalHelp,false);
        generalHelp.addActionListener(new DisplayHelpEvent(this));
        generalHelp.setAccelerator(GuiConstants.VK_F3,0);
        help.addMenuItem(generalHelp);
        getJMenuBar().add(help);

    }

    public void displayHelp() {
        //On indique a l utilisatteur comment utiliser le logiciel et jouer aux cartes
        if (!helpFrames.isEmpty()) {
            helpFrames.first().setTitle(getMessages().getVal(CST_GENERAL_HELP));
            helpFrames.first().initialize(this);
            return;
        }
//        if (helpInitializerThread == null || helpInitializerThread.isAlive() || helpInitializerTask == null) {
//            return;
//        }
        FrameGeneralHelp aide_=new FrameGeneralHelp(getMessages().getVal(CST_GENERAL_HELP),this,generalHelp);
        aide_.initialize(this);
        helpFrames.add(aide_);
    }

    /**Initialise la barre de menus*/
    private void initMenus() {
        setJMenuBar(getCompoFactory().newMenuBar());
        initFileMenu();
        initDealMenu();
        initParametersMenu();
        initHelpMenu();
    }

    private int confirm(String _message,String _titre) {
        //warning message
        return getConfirmDialogAns().input(getCommonFrame(),_message,_titre, getLanguageKey(),GuiConstants.YES_NO_CANCEL_OPTION);
    }
    /**Sauvegarder une partie dans un fichier*/
    private String dialogueFichierSauvegarde() {
        String fichier_;
        if (isSaveHomeFolder()) {
            fichier_=getFileSaveDialogInt().input(getCommonFrame(), getLanguageKey(), true, FileConst.GAME_EXT, getFrames().getHomePath());
        } else {
            fichier_=getFileSaveDialogInt().input(getCommonFrame(), getLanguageKey(), true, FileConst.GAME_EXT, EMPTY_STRING);
        }
        if (fichier_ == null) {
            fichier_ = EMPTY_STRING;
        }
        return fichier_;
    }
    /**Sauvegarder une partie dans un fichier*/
    private String dialogueFichierChargement() {
        String fichier_;
        if (isSaveHomeFolder()) {
            fichier_=getFileOpenDialogInt().input(getCommonFrame(),getLanguageKey(),true, FileConst.GAME_EXT, getFrames().getHomePath());
        } else {
            fichier_=getFileOpenDialogInt().input(getCommonFrame(),getLanguageKey(),true, FileConst.GAME_EXT, EMPTY_STRING);
        }
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
        getFrames().getMessageDialogAbs().input(getCommonFrame(),mes_, getMessages().getVal(CST_FILE_NOT_LOADED_TILE),lg_, GuiConstants.ERROR_MESSAGE);
    }

    /**On ecoute les boutons du menu principal et des menus jeux*/
    public void beginGame(GameEnum _jeuBouton) {
        if(_jeuBouton==GameEnum.BELOTE) {
            core.setContainerGame(new ContainerSingleBelote(this));
        } else if(_jeuBouton==GameEnum.PRESIDENT) {
            core.setContainerGame(new ContainerSinglePresident(this));
        } else if(_jeuBouton==GameEnum.TAROT) {
            core.setContainerGame(new ContainerSingleTarot(this));
        }
        MenuItemUtils.setEnabledMenu(change,true);
        ((ContainerSingle) core.getContainerGame()).modify();
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
        ResultCardsServer result_ = getResultCardsServerInteract().interact(this, _jeuBouton);
        if (result_ == null) {
            return;
        }
        GameEnum choosenGameMultiPlayers_ = _jeuBouton;
        if (choosenGameMultiPlayers_ == GameEnum.TAROT) {
            containerGame = new ContainerMultiTarot(this, result_.isCreate(), result_.getNbPlayers());
        } else if (choosenGameMultiPlayers_ == GameEnum.PRESIDENT) {
            containerGame = new ContainerMultiPresident(this, result_.isCreate(), result_.getNbPlayers());
        } else if (choosenGameMultiPlayers_ == GameEnum.BELOTE) {
            containerGame = new ContainerMultiBelote(this, result_.isCreate());
        }
        String fileName_ = StringUtil.concat(StreamFolderFile.getCurrentPath(getFileCoreStream()), FileConst.PORT_INI);
        int port_ = NetCreate.tryToGetPort(fileName_, Net.getPort(),getFileCoreStream(),getStreams());
        if (result_.isCreate()) {
            int nbChoosenPlayers_ = result_.getNbPlayers();
            Net.setNbPlayers(nbChoosenPlayers_, getNet());
            createServer(result_.getIp(), result_.getIpType(), port_);
            return;
        }
        SocketResults connected_ = createClient(result_.getIp(), result_.getIpType(), false, port_);
        if (connected_.getError() != ErrorHostConnectionType.NOTHING) {
            containerGame = new ContainerNoGame(this);
            if (connected_.getError() == ErrorHostConnectionType.UNKNOWN_HOST) {
                String formatted_ = getMessages().getVal(UNKNOWN_HOST);
                formatted_ = StringUtil.simpleStringsFormat(formatted_, result_.getIp());
                getFrames().getMessageDialogAbs().input(getCommonFrame(), getMessages().getVal(BUG), formatted_, getLanguageKey(), GuiConstants.ERROR_MESSAGE);
                return;
            }
            getFrames().getMessageDialogAbs().input(getCommonFrame(), getMessages().getVal(BUG), getMessages().getVal(NOT_CONNECTED), getLanguageKey(), GuiConstants.ERROR_MESSAGE);
            return;
        }*/
    }
    private String pseudo() {
        return core.getPseudosJoueurs().getPseudo();
    }
//    public void delegateServer() {
//        ((ContainerMulti)containerGame).delegateServer();
//    }
//
//    public BasicClient getThreadEmission() {
//        return threadEmission;
//    }
//
//    public void setThreadEmission(BasicClient _threadEmission) {
//        threadEmission = _threadEmission;
//    }

//    @Override
//    public boolean canChangeLanguage() {
//        if (!core.getContainerGame().isSimple()) {
//            return false;
//        }
//        if (!helpFrames.isEmpty()) {
//            return !helpFrames.first().isVisible();
//        }
//        return true;
//    }

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
//        multiStop.setText(getMessages().getVal(CST_MULTI_STOP));
        parameters.setText(getMessages().getVal(CST_PARAMETERS));
        for (GameEnum g: GameEnum.values()) {
            rulesGames.getVal(g).setText(g.toString(lg_));
        }
        players.setText(getMessages().getVal(CST_PLAYERS));
        launching.setText(getMessages().getVal(CST_LAUNCHING));
        core.getTiming().setText(getMessages().getVal(CST_TIMING));
        core.getInteract().setText(getMessages().getVal(CST_INTERACT));
        core.getLanguage().setText(getMessages().getVal(CST_LANGUAGE));
        core.getDisplaying().setText(getMessages().getVal(CST_DISPLAYING));
        for (GameEnum g: GameEnum.values()) {
            core.getDisplayingGames().getVal(g).setText(g.toString(lg_));
        }
        help.setText(getMessages().getVal(CST_HELP));
        generalHelp.setText(getMessages().getVal(CST_GENERAL_HELP));
        if (welcomeLabel != null) {
            welcomeLabel.setText(StringUtil.simpleStringsFormat(getMessages().getVal(CST_WELCOME), pseudo()));
        }
        if (singleModeButton != null) {
            singleModeButton.setText(getMessages().getVal(CST_SINGLE_MODE));
        }
        if (multiModeButton != null) {
            multiModeButton.setText(getMessages().getVal(CST_MULTI_MODE));
        }
        if (goHelpMenu != null) {
            goHelpMenu.setText(getMessages().getVal(CST_GO_HELP_MENU));
        }
        lastSavedGameDate.setText(StringUtil.simpleStringsFormat(getMessages().getVal(LAST_SAVED_GAME), dateLastSaved));
    }

//    @Override
//    public AbstractSocket initIndexInGame(boolean _first, AbstractSocket _socket) {
//        return _socket;
//    }

    public boolean isSaveHomeFolder() {
        return core.getParametres().isSaveHomeFolder();
    }
    public String save(AbsDialog _d) {
        String lg_ = getLanguageKey();
        String fichier_;
        if (isSaveHomeFolder()) {
            fichier_=getFileSaveDialogInt().input(getCommonFrame(),_d, lg_, true, FileConst.GAME_EXT, getFrames().getHomePath());
        } else {
            fichier_=getFileSaveDialogInt().input(getCommonFrame(),_d, lg_, true, FileConst.GAME_EXT, EMPTY_STRING);
        }
        if (fichier_ == null) {
            fichier_ = EMPTY_STRING;
        }
        return fichier_;
    }

//    public void setSingle(boolean _single) {
//        single = _single;
//    }

    public AbsMenu getFile() {
        return file;
    }

    public AbsMenuItem getLoad() {
        return load;
    }

    public AbsMenuItem getSave() {
        return save;
    }

    public AbsMenuItem getChange() {
        return change;
    }

    public AbsMenuItem getExit() {
        return exit;
    }

    public AbsMenu getDeal() {
        return deal;
    }

    public AbsMenuItem getConsulting() {
        return consulting;
    }

    public AbsCheckBoxMenuItem getPause() {
        return pause;
    }

    public AbsMenuItem getHelpGame() {
        return helpGame;
    }

    public AbsMenuItem getTricksHands() {
        return tricksHands;
    }

    public AbsMenuItem getTeams() {
        return teams;
    }

    public AbsMenu getEdit() {
        return edit;
    }

    public IdMap<GameEnum,AbsMenuItem> getEditGames() {
        return editGames;
    }

    public AbsMenu getDemo() {
        return demo;
    }

    public IdMap<GameEnum,AbsMenuItem> getDemoGames() {
        return demoGames;
    }

    public AbsMenu getTraining() {
        return training;
    }

    public IdMap<ChoiceTarot,AbsMenuItem> getTrainingTarot() {
        return trainingTarot;
    }

//    public AbsMenuItem getMultiStop() {
//        return multiStop;
//    }

    public AbsMenu getParameters() {
        return parameters;
    }

    public IdMap<GameEnum,AbsMenuItem> getRulesGames() {
        return rulesGames;
    }

    public AbsMenuItem getPlayers() {
        return players;
    }

    public AbsMenuItem getLaunching() {
        return launching;
    }

    public AbsMenuItem getTiming() {
        return core.getTiming();
    }

    public AbsMenuItem getInteract() {
        return core.getInteract();
    }

    public AbsMenuItem getLanguage() {
        return core.getLanguage();
    }

    public AbsMenu getDisplaying() {
        return core.getDisplaying();
    }

    public IdMap<GameEnum,AbsMenuItem> getDisplayingGames() {
        return core.getDisplayingGames();
    }

    public AbsMenu getHelp() {
        return help;
    }

    public AbsMenuItem getGeneralHelp() {
        return generalHelp;
    }

    @Override
    public String getApplicationName() {
        return LaunchingCards.getMainWindowClass();
    }

//    @Override
//    public Document getDoc(String _object) {
//        return DocumentReaderCardsMultiUtil.getDoc(_object);
//    }
//
//    @Override
//    public Exiting getExiting(Document _doc) {
//        return DocumentReaderCardsMultiUtil.getExiting(_doc);
//    }

//    public Net getNet() {
//        return net;
//    }

    public StringMap<StringMap<PreparedPagesCards>> getPreparedBelote() {
        return core.getPreparedBelote();
    }

    public StringMap<StringMap<PreparedPagesCards>> getPreparedPresident() {
        return core.getPreparedPresident();
    }

    public StringMap<StringMap<PreparedPagesCards>> getPreparedTarot() {
        return core.getPreparedTarot();
    }

    public HelpInitializer getHelpInitializerTask() {
        return helpInitializerTask;
    }

    public void setHelpInitializerTask(HelpInitializer _helpInitializerTask) {
        this.helpInitializerTask = _helpInitializerTask;
    }

    public DialogDisplayingBelote getDialogDisplayingBelote() {
        return core.getDialogDisplayingBelote();
    }

    public DialogDisplayingTarot getDialogDisplayingTarot() {
        return core.getDialogDisplayingTarot();
    }

    public DialogDisplayingPresident getDialogDisplayingPresident() {
        return core.getDialogDisplayingPresident();
    }

    public DialogHelpBelote getDialogHelpBelote() {
        return core.getDialogHelpBelote();
    }

    public DialogHelpPresident getDialogHelpPresident() {
        return core.getDialogHelpPresident();
    }

    public DialogHelpTarot getDialogHelpTarot() {
        return core.getDialogHelpTarot();
    }

    public DialogRulesBelote getDialogRulesBelote() {
        return core.getDialogRulesBelote();
    }

    public DialogRulesPresident getDialogRulesPresident() {
        return core.getDialogRulesPresident();
    }

    public DialogRulesTarot getDialogRulesTarot() {
        return core.getDialogRulesTarot();
    }

    public DialogTricksBelote getDialogTricksBelote() {
        return core.getDialogTricksBelote();
    }

    public DialogTricksPresident getDialogTricksPresident() {
        return core.getDialogTricksPresident();
    }

    public DialogTricksTarot getDialogTricksTarot() {
        return core.getDialogTricksTarot();
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
        return core.getDialogTeamsPlayers();
    }

    public DialogNicknames getDialogNicknames() {
        return dialogNicknames;
    }

    public DialogSoft getDialogSoft() {
        return core.getDialogSoft();
    }

//    public DialogServerCards getDialogServer() {
//        return dialogServer;
//    }

    public StringMap<StringMap<String>> getImages() {
        return core.getImages();
    }

    public AbsPlainButton getSingleModeButton() {
        return singleModeButton;
    }

    public AbsPlainButton getMultiModeButton() {
        return multiModeButton;
    }

    public void setImages(StringMap<StringMap<String>> _i) {
        this.core.setImages(_i);
    }

//    public ResultCardsServerInteract getResultCardsServerInteract() {
//        return resultCardsServerInteract;
//    }
//
//    public void setResultCardsServerInteract(ResultCardsServerInteract _r) {
//        this.resultCardsServerInteract = _r;
//    }
}
