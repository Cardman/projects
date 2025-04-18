package cards.gui;






import cards.belote.*;
import cards.enumerations.*;
import cards.facade.*;
import cards.facade.enumerations.*;
import cards.gui.animations.*;
import cards.gui.containers.*;
import cards.gui.dialogs.*;
import cards.gui.dialogs.help.*;
import cards.gui.events.*;
//import cards.gui.interfaces.ResultCardsServer;
//import cards.gui.interfaces.*;
import cards.gui.menus.*;
//import cards.gui.menus.QuitMultiEvent;
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
import cards.gui.panels.Carpet;
import cards.main.CardsNonModalEvent;
import cards.president.*;
import cards.tarot.*;
import cards.tarot.enumerations.*;
import code.gui.*;
import code.gui.events.*;
import code.gui.files.*;
import code.gui.images.*;
import code.gui.initialize.*;
//import code.gui.initialize.AbstractSocket;
//import code.network.*;
//import code.network.enums.ErrorHostConnectionType;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.*;
//import code.stream.StreamFolderFile;
import code.stream.*;
import code.threads.*;
import code.util.*;
import code.util.core.*;
//import code.util.core.IndexConstants;
//import code.util.core.StringUtil;

/**

    fenetre principale non redimensionnable
 Au premier lancement, il y une barre de menus et quatre boutons de jeu*/
public final class WindowCards extends GroupFrame implements WindowCardsInt,AbsOpenQuit {
//public final class WindowCards extends NetGroupFrame

//    public static final String TOO_GAME = "tooGame";

//    public static final String ALREADY_PLAYED = "alreadyPlayed";

//    public static final String CANT_DECLARE_TITLE = "cantDeclareTitle";

//    public static final String CANT_PLAY_CARD_TITLE = "cantPlayCardTitle";

//    public static final String CONSULT_TITLE = "consultTitle";

//    public static final String END_TRICK = "endTrick";

    //    public static final String READY = "ready";

//    public static final String REASON = "reason";

//    public static final String WAIT_TURN = "waitTurn";

//    public static final String APP_CARDS = "cards";
//    public static final String TEMP_FOLDER = "cards";

//    private static final String DIALOG_ACCESS = "cards.gui.mainwindow";

//    private static final String TOO_MANY = "tooMany";

//    private static final String UNKNOWN_HOST = "unknownHost";

//    private static final String NOT_CONNECTED = "notConnected";

    private static final String EMPTY_STRING = "";
    private static final String UNIQ_CHAR = "_";

//    private static final char LINE_RETURN = '\n';

//    private BasicClient threadEmission;

    private final FrameGeneralHelp helpFrames;

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

    private EnabledMenu file;
    private EnabledMenu load;
    private EnabledMenu save;
    private EnabledMenu change;
    private EnabledMenu exit;

    //deal menu

    private EnabledMenu deal;
    private EnabledMenu consulting;
    private EnabledMenu pause;
    private EnabledMenu helpGame;
    private EnabledMenu tricksHands;
    private EnabledMenu teams;
    private EnabledMenu edit;
    private final IdMap<GameEnum,EnabledMenu> editGames = new IdMap<GameEnum,EnabledMenu>();
    private EnabledMenu demo;
    private final IdMap<GameEnum,EnabledMenu> demoGames = new IdMap<GameEnum,EnabledMenu>();
    private EnabledMenu training;
    private final IdMap<ChoiceTarot,EnabledMenu> trainingTarot = new IdMap<ChoiceTarot,EnabledMenu>();
//    private AbsMenuItem multiStop;

    //parameters menu

    private EnabledMenu parameters;
    private final IdMap<GameEnum,EnabledMenu> rulesGames = new IdMap<GameEnum,EnabledMenu>();
    private EnabledMenu players;
    private EnabledMenu launching;
    private EnabledMenu timing;
    private EnabledMenu interact;
//    private AbsMenuItem language;
//    private AbsMenu displaying;
//    private final IdMap<GameEnum,AbsMenuItem> displayingGames = new IdMap<GameEnum,AbsMenuItem>();

    //parameters help

    private EnabledMenu help;
    private final EnabledMenu generalHelp;

    //labels at main menu

    private final AbsPlainLabel welcomeLabel;
    private final AbsButton singleModeButton;
//    private AbsButton multiModeButton;
    private final AbsPlainLabel goHelpMenu;
//    private final Net net = new Net();

//    private final StringMap<StringMap<PreparedPagesCards>> preparedBelote;
//    private final StringMap<StringMap<PreparedPagesCards>> preparedPresident;
//    private final StringMap<StringMap<PreparedPagesCards>> preparedTarot;
    //private final boolean standalone;
    private AbstractFutureParam<HelpIndexesTree> helpInitializerTask;
//    private final DialogDisplayingBelote dialogDisplayingBelote;
//    private final DialogDisplayingTarot dialogDisplayingTarot;
//    private final DialogDisplayingPresident dialogDisplayingPresident;
//    private final DialogHelpBelote dialogHelpBelote;
//    private final DialogHelpPresident dialogHelpPresident;
//    private final DialogHelpTarot dialogHelpTarot;
    private final DialogRulesBelote dialogRulesBelote;
    private final DialogRulesPresident dialogRulesPresident;
    private final DialogRulesTarot dialogRulesTarot;
//    private final DialogTricksBelote dialogTricksBelote;
//    private final DialogTricksPresident dialogTricksPresident;
//    private final DialogTricksTarot dialogTricksTarot;
    private final EditorBelote editorBelote;
    private final EditorPresident editorPresident;
    private final EditorTarot editorTarot;
    private final EditorSolitaire editorClassic;
    private final EditorSolitaire editorFreeCell;
    private final EditorSolitaire editorSpider;
//    private final DialogTeamsPlayers dialogTeamsPlayers;
    private final DialogNicknames dialogNicknames;
    private final StringMap<DialogSoft> dialogSoft = new StringMap<DialogSoft>();
//    private final DialogServerCards dialogServer;
//    private final CardFactories cardFactories;
//    private ResultCardsServerInteract resultCardsServerInteract;
//    private StringMap<StringMap<String>> images = new StringMap<StringMap<String>>();
    private final WindowCardsCore core;
    private final FileSaveFrame fileSaveFrame;
    private final FileOpenFrame fileOpenFrame;
    private final FileOpenSaveFrame fileOpenSaveFrame;
    private final AbstractAtomicBoolean modal;
    private final DialogHelpBelote dialogHelpBelote;
    private final DialogHelpPresident dialogHelpPresident;
    private final DialogHelpTarot dialogHelpTarot;
    private final IdMap<GameEnum,AbsButton> soloGames = new IdMap<GameEnum, AbsButton>();
    private AbsPausingCardsAnims pausingCardsAnims;
    private AbsButton backMenu;
    private final StringMap<EnabledMenu> softMenus = new StringMap<EnabledMenu>();
    private final LanguageDialogButtons languageDialogButtons;
    private final ReportingFrame errorsFile = ReportingFrame.newInstance(getFrames());
    private String lastFile = "";
//    private final AbsActionListenerAct guardRender;
    private final LanguagesButtonsPair mainButton;

    public WindowCards(CardGamesStream _nicknames, AbstractProgramInfos _list) {
        this(_nicknames, _list,new IntArtCardGames());
    }
    public WindowCards(CardGamesStream _nicknames, AbstractProgramInfos _list, IntArtCardGames _ia) {
        this(_nicknames, _list,_list.getCompoFactory().newMenuItem(), _ia, new LanguagesButtonsPair(null,null,new LanguageComponentButtons(_list,new AlwaysActionListenerAct())));
    }
    public WindowCards(CardGamesStream _nicknames, AbstractProgramInfos _list, EnabledMenu _geneHelp, IntArtCardGames _ia, LanguagesButtonsPair _pair) {
        super(_list);
        mainButton = _pair;
//        guardRender = new AlwaysActionListenerAct();
        languageDialogButtons = new LanguageDialogButtons(_list,_pair.getLgMenu(), new AlwaysActionListenerAct());
        setPausingCardsAnims(new DefPausingCardsAnims());
        modal = _list.getThreadFactory().newAtomicBoolean();
        GuiBaseUtil.choose(this, _list);
        generalHelp = _geneHelp;
        fileSaveFrame = new FileSaveFrame(_list, modal);
        fileOpenFrame = new FileOpenFrame(_list, modal);
        fileOpenSaveFrame = new FileOpenSaveFrame(_list, modal);
//        dialogDisplayingBelote = new DialogDisplayingBelote(_list);
//        dialogDisplayingTarot = new DialogDisplayingTarot(_list);
//        dialogDisplayingPresident = new DialogDisplayingPresident(_list);
        dialogHelpBelote = new DialogHelpBelote(_list,modal);
        dialogHelpPresident = new DialogHelpPresident(_list,modal);
        dialogHelpTarot = new DialogHelpTarot(_list,modal);
//        dialogTricksBelote = new DialogTricksBelote(_list);
//        dialogTricksPresident = new DialogTricksPresident(_list);
//        dialogTricksTarot = new DialogTricksTarot(_list);
//        dialogTeamsPlayers = new DialogTeamsPlayers(_list);
//        dialogSoft = new DialogSoft(_list);
//        dialogServer = new DialogServerCards(_list);
//        cardFactories = _cardFactories;
//        preparedBelote = _belote;
//        preparedPresident = _president;
//        preparedTarot = _tarot;
//        pseudosJoueurs=new Nicknames(getLanguageKey());
//        setAccessFile(DIALOG_ACCESS);
        setFocusable(true);
        requestFocus();
        setFocusableWindowState(true);
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
//        initMessageName();
        goHelpMenu = getCompoFactory().newPlainLabel(getMenusMessages().getVal(MessagesGuiCards.CST_GO_HELP_MENU));
        singleModeButton = getCompoFactory().newPlainButton(getMenusMessages().getVal(MessagesGuiCards.CST_SINGLE_MODE));
        lastSavedGameDate.setText(StringUtil.simpleStringsFormat(getMenusMessages().getVal(MessagesGuiCards.LAST_SAVED_GAME), dateLastSaved));

//        pseudosJoueurs = DocumentReaderCardsUnionUtil.getNicknames(getLanguageKey(),StreamTextFile.contentsOfFile(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.PLAYERS),getFileCoreStream(),getStreams()));
//        if (!pseudosJoueurs.isValidNicknames()) {
//            pseudosJoueurs = new Nicknames(getLanguageKey());
//            pseudosJoueurs.sauvegarder(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.PLAYERS),getStreams());
//        }
        /*Parametre de lancement*/
        core = new WindowCardsCore(this,_nicknames, _list,_ia,modal,_pair.getLgMenu());
        initMenus();
        getConsulting().setEnabled(false);
        welcomeLabel = getCompoFactory().newPlainLabel(StringUtil.simpleStringsFormat(getMenusMessages().getVal(MessagesGuiCards.CST_WELCOME), pseudo()));
        editorBelote = new EditorBelote(_list, editGames.getVal(GameEnum.BELOTE));
        editorPresident = new EditorPresident(_list, editGames.getVal(GameEnum.PRESIDENT));
        editorTarot = new EditorTarot(_list, editGames.getVal(GameEnum.TAROT));
        editorClassic = new EditorSolitaire(_list, editGames.getVal(GameEnum.CLASSIC), GameEnum.CLASSIC);
        editorFreeCell = new EditorSolitaire(_list, editGames.getVal(GameEnum.FREECELL), GameEnum.FREECELL);
        editorSpider = new EditorSolitaire(_list, editGames.getVal(GameEnum.SPIDER), GameEnum.SPIDER);
        dialogRulesBelote = new DialogRulesBelote(_list, rulesGames.getVal(GameEnum.BELOTE));
        dialogRulesPresident = new DialogRulesPresident(_list, rulesGames.getVal(GameEnum.PRESIDENT));
        dialogRulesTarot = new DialogRulesTarot(_list, rulesGames.getVal(GameEnum.TAROT));
        dialogNicknames = new DialogNicknames(_list, players);
        dialogSoft.addEntry(MessagesGuiCards.CST_LAUNCHING, new DialogSoft(_list,MessagesGuiCards.CST_LAUNCHING, launching));
        dialogSoft.addEntry(MessagesGuiCards.CST_TIMING, new DialogSoft(_list,MessagesGuiCards.CST_TIMING, timing));
        dialogSoft.addEntry(MessagesGuiCards.CST_INTERACT, new DialogSoft(_list,MessagesGuiCards.CST_INTERACT, interact));
        helpFrames = new FrameGeneralHelp(this,generalHelp);

        if(core.getFacadeCards().getParametres().getLancement().isEmpty()) {
            menuPrincipal();
        } else {
            getTricksHands().setEnabled(false);
            getTeams().setEnabled(false);
//            MenuItemUtils.setEnabledMenu(getMultiStop(),false);
            getLoad().setEnabled(true);
            for (EnabledMenu m: getEditGames().values()) {
                m.setEnabled(true);
            }
            for (EnabledMenu m: getTrainingTarot().values()) {
                m.setEnabled(true);
            }
            beginGame(core.getFacadeCards().getParametres().getLancement().first());
//            if(core.getFacadeCards().getParametres().getLancement().first()==GameEnum.BELOTE) {
//                core.setContainerGame(new ContainerSingleBelote(this));
//            } else if(core.getFacadeCards().getParametres().getLancement().first()==GameEnum.PRESIDENT) {
//                core.setContainerGame(new ContainerSinglePresident(this));
//            } else {
//                core.setContainerGame(new ContainerSingleTarot(this));
//            }
//            MenuItemUtils.setEnabledMenu(change,true);
//            ((ContainerSingle) core.getContainerGame()).modify();
        }
//        exitMode(_list);
//        setDefaultCloseOperation(GuiConstants.EXIT_ON_CLOSE);
        addWindowListener(new QuittingEvent(this));
    }
    public static AbsCommonFrame frame(AbstractProgramInfos _pr) {
        return _pr.getFrameFactory().newCommonFrame();
    }
//    public static StringMap<String> getMessagesFromLocaleClass(String _folder, String _loc, String _class) {
//        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(_folder, _loc, _class);
//        String loadedResourcesMessages_ = MessGuiCardsGr.ms().getVal(fileName_);
//        return ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
//    }

    public static String getTempFolderSl(AbstractProgramInfos _tmpUserFolderSl) {
        return StringUtil.concat(getTempFolder(_tmpUserFolderSl), StreamTextFile.SEPARATEUR);
    }

    public static String getTempFolder(AbstractProgramInfos _tmpUserFolderSl) {
        return StreamFolderFile.getTempFolder(_tmpUserFolderSl, MessagesCardGames.getAppliFilesTr(_tmpUserFolderSl.getTranslations()).val().getMapping().getVal(MessagesCardGames.TEMP_FOLDER));
    }

//    public String getTooManyString() {
//        return getMessages().getVal(TOO_MANY);
//    }

    private AbsButton ajouterBoutonPrincipal(String _texte,GameEnum _nomJeu,AbsPanel _container) {
        AbsButton bouton_=getCompoFactory().newPlainButton(_texte);
//        bouton_.addMouseListener(new EcouteurBoutonPrincipal(_nomJeu));
        bouton_.addActionListener(new CardsNonModalEvent(this),new ListenerBeginGame(_nomJeu, this));
        _container.add(bouton_);
        return bouton_;
    }
//    public void clearHelpDialogs() {
//        helpFrames.clear();
//    }
    public SoftParams getParametresLogiciel() {
        return new SoftParams(core.getFacadeCards().getParametres());
    }
    public Nicknames getPseudosJoueurs() {
        return new Nicknames(core.getFacadeCards().getPseudosJoueurs());
    }
    public RulesBelote getReglesBelote() {
        return new RulesBelote(core.getFacadeCards().getReglesBelote());
    }

    public DisplayingBelote getDisplayingBelote() {
        return new DisplayingBelote(core.getFacadeCards().getDisplayingBelote());
    }
    public RulesPresident getReglesPresident() {
        return new RulesPresident(core.getFacadeCards().getReglesPresident());
    }

    public DisplayingPresident getDisplayingPresident() {
        return new DisplayingPresident(core.getFacadeCards().getDisplayingPresident());
    }

    public RulesTarot getReglesTarot() {
        return new RulesTarot(core.getFacadeCards().getReglesTarot());
    }

    public DisplayingTarot getDisplayingTarot() {
        return new DisplayingTarot(core.getFacadeCards().getDisplayingTarot());
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

    @Override
    public StringMap<String> getMenusMessages() {
        return MessagesCardGames.getMenus(MessagesCardGames.getAppliTr(getFrames().currentLg())).getMapping();
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
        beforeClose();
        LanguageDialogButtons.enable(mainButton.getMainButton(),true);
//        LanguageComponentButtons.enableButtons(mainButton.getButtons(),true);
        GuiBaseUtil.trEx(this, getFrames());
//        closeOpened();
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

    public void beforeClose() {
        changerNombreDePartiesEnQuittant();
        ecrireCoordonnees();
        closeOpened();
    }
//    @Override
//    public void dispose() {
//        changerNombreDePartiesEnQuittant();
//        ecrireCoordonnees();
//        closeOpened();
//        GuiBaseUtil.trEx(this);
//    }

    private void closeOpened() {
        getCommonFrame().setVisible(false);
//        if (helpFrames.getCommonFrame().isVisible()) {
////            helpFrames.first().dispose();
//            helpFrames.closeWindow();
////            helpFrames.first().setVisible(false);
////            generalHelp.setEnabled(true);
//        }
        helpFrames.closeWindow();
        languageDialogButtons.closeWindow();
        dialogRulesBelote.closeWindow();
        dialogRulesPresident.closeWindow();
        dialogRulesTarot.closeWindow();
        editorBelote.closeWindow();
        editorPresident.closeWindow();
        editorTarot.closeWindow();
        editorClassic.closeWindow();
        editorFreeCell.closeWindow();
        editorSpider.closeWindow();
        core.closeWindows();
        for (DialogSoft d:dialogSoft.values()) {
            d.closeWindow();
        }
        dialogNicknames.closeWindow();
        getFileSaveFrame().getClosing().windowClosing();
        getFileOpenFrame().getClosing().windowClosing();
        getFileOpenSaveFrame().getClosing().windowClosing();
    }

//    private int saving() {
//        //warning message
//        return confirm(getMessages().getVal(CST_SAVING),getMessages().getVal(CST_SAVING_TITLE));
//    }
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
        FileDialog.saveCoords(getTempFolder(getFrames()), MessagesCardGames.getAppliFilesTr(getFrames().getTranslations()).val().getMapping().getVal(MessagesCardGames.COORDS), point_.getXcoord(),point_.getYcoord(),getStreams());
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


    public static AbsPlainLabel whiteLabel(WindowCardsInt _win,int _nb) {
        AbsPlainLabel etiquette2_=_win.getCompoFactory().newPlainLabel(UNIQ_CHAR);
        etiquette2_.setPreferredSize(Carpet.getDimensionForSeveralCards(_nb));
        etiquette2_.setOpaque(true);
        etiquette2_.setForeground(GuiConstants.WHITE);
        etiquette2_.setBackground(GuiConstants.WHITE);
        return etiquette2_;
    }
    public static AbsPlainLabel getBlankCard(WindowCardsInt _win,StringList _nicknames, int _player) {
        AbsPlainLabel etiquette2_=_win.getCompoFactory().newPlainLabel(_nicknames.get(_player));
        etiquette2_.setOpaque(true);
        etiquette2_.setBackground(GuiConstants.WHITE);
        etiquette2_.setPreferredSize(Carpet.getMaxDimension());
        return etiquette2_;
    }
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
        change.setEnabled(false);
        //Activer le menu Partie/Demo
        changeMenuSimuEnabled(true);
//        MenuItemUtils.setEnabledMenu(getDemo(),true);
        //desactiver le menu Partie/aide au jeu
        getHelpGame().setEnabled(false);
        getTeams().setEnabled(false);
        //desactiver le menu Partie/conseil
        getConsulting().setEnabled(false);
        //Desactiver le menu Partie/Pause
        getPause().setEnabled(false);
        core.getContainerGame().setChangerPileFin(false);
        core.getContainerGame().finirParties();
        setTitle(Launching.WELCOME.toString(getFrames().currentLg()));
        getPane().removeAll();
        AbsPanel container_=getCompoFactory().newPageBox();
        /*Pour montrer qu'on a de l'attention a l'utilisateur*/
        container_.add(getCompoFactory().newPlainLabel(StringUtil.simpleStringsFormat(getMenusMessages().getVal(MessagesGuiCards.CST_WELCOME), pseudo())));
        /*Cree les boutons de jeu*/
        boutonsSolo(container_);
        AbsButton button_ = getCompoFactory().newPlainButton(getMenusMessages().getVal(MessagesGuiCards.CST_MAIN_MENU));
        button_.addActionListener(new BackToMainMenuEvent(this));
        container_.add(button_);
        backMenu = button_;
        //Ajout d'une etiquette pour indiquer ou aller pour avoir de l'aide
//        if (goHelpMenu == null) {
//            goHelpMenu = getCompoFactory().newPlainLabel(getMenusMessages().getVal(MessagesGuiCards.CST_GO_HELP_MENU));
//        }
        container_.add(goHelpMenu);
        getSave().setEnabled(false);
        getChange().setEnabled(false);
        container_.add(core.getClock().getComponent());
        container_.add(lastSavedGameDate);
        setContentPane(container_);
        pack();
    }

    private void boutonsSolo(AbsPanel _container) {
        TranslationsLg lg_ = getFrames().currentLg();
        soloGames.clear();
        for (GameEnum jeu2_:GameEnum.allValidPlusSolo()) {
            soloGames.addEntry(jeu2_,ajouterBoutonPrincipal(jeu2_.toString(lg_),jeu2_, _container));
        }
    }

    public void menuPrincipal() {
//        MenuItemUtils.setEnabledMenu(getMultiStop(),false);
        getTricksHands().setEnabled(false);
        getTeams().setEnabled(false);
        core.setContainerGame(noGame());
        change.setEnabled(false);
        //Activer le menu Partie/Demo
        changeMenuSimuEnabled(true);
//        MenuItemUtils.setEnabledMenu(getDemo(),true);
        //desactiver le menu Partie/aide au jeu
        getHelpGame().setEnabled(false);
        //desactiver le menu Partie/conseil
        getConsulting().setEnabled(false);
        //Desactiver le menu Partie/Pause
        getPause().setEnabled(false);
        getLoad().setEnabled(true);
        for (EnabledMenu m: getTrainingTarot().values()) {
            m.setEnabled(true);
        }
        for (EnabledMenu m: getEditGames().values()) {
            m.setEnabled(true);
        }
        for (EnabledMenu m: getRulesGames().values()) {
            m.setEnabled(true);
        }
        core.getContainerGame().finirParties();
        setTitle(Launching.WELCOME.toString(getFrames().currentLg()));
        getPane().removeAll();
        AbsPanel pane_ = getCompoFactory().newPageBox();
        /*Pour montrer qu'on a de l'attention a l'utilisateur*/
        pane_.add(welcomeLabel, GuiConstants.CENTER);
        /*Cree les boutons de jeu*/
        singleModeButton.addActionListener(new ChooseModeEvent(this));
        pane_.add(singleModeButton);
//        multiModeButton = getCompoFactory().newPlainButton(getMessages().getVal(CST_MULTI_MODE));
//        multiModeButton.addActionListener(new ChooseModeEvent(this, false));
//        pane_.add(multiModeButton);
        //Ajout d'une etiquette pour indiquer ou aller pour avoir de l'aide
//        if (goHelpMenu == null) {
//            goHelpMenu = getCompoFactory().newPlainLabel(getMenusMessages().getVal(MessagesGuiCards.CST_GO_HELP_MENU));
//        }
        pane_.add(goHelpMenu);
        pane_.add(core.getClock().getComponent());
        pane_.add(lastSavedGameDate);
        setContentPane(pane_);
        getSave().setEnabled(false);
        getChange().setEnabled(false);
    }
//    private void initMessageName() {
////        messages = ExtractFromFiles.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, Constants.getLanguage(), getClass());
//        setMessages(WindowCards.getMessagesFromLocaleClass(FileConst.FOLDER_MESSAGES_GUI, getLanguageKey(), getAccessFile()));
//    }
    public void loadGameBegin(StringList _file) {
        if (_file.isEmpty()) {
            return;
        }
        loadGameBegin(_file.first());
    }
    public void loadGameBegin(String _file) {
        core.setContainerGame(noGame());
        tryToLoadDeal(_file);
    }
    public ContainerNoGame noGame() {
        ContainerNoGame c_ = new ContainerNoGame(this);
        c_.update(this);
//        c_.setParametres(getParametresLogiciel());
//        c_.setReglesTarot(getReglesTarot());
//        c_.setReglesPresident(getReglesPresident());
//        c_.setReglesBelote(getReglesBelote());
//        c_.setDisplayingBelote(getDisplayingBelote());
//        c_.setDisplayingPresident(getDisplayingPresident());
//        c_.setDisplayingTarot(getDisplayingTarot());
//        c_.setPseudosJoueurs(getPseudosJoueurs());
//        c_.setMessages(getMessages());
        return c_;
    }
    private void initFileMenu() {
        /* Fichier */
        file=getCompoFactory().newMenu(getMenusMessages().getVal(MessagesGuiCards.CST_FILE));
        /* Fichier/Charger "accessible n'importe quand"*/
        load=getCompoFactory().newMenuItem(getMenusMessages().getVal(MessagesGuiCards.CST_LOAD));
        load.addActionListener(new CardsNonModalEvent(this),new LoadGameEventCards(this));
        load.setAccelerator(GuiConstants.VK_O, GuiConstants.CTRL_DOWN_MASK);
        file.addMenuItem(load);
        /* Fichier/Sauvegarder "accessible que lorsqu'on joue une partie de cartes"*/
        save=getCompoFactory().newMenuItem(getMenusMessages().getVal(MessagesGuiCards.CST_SAVE));
        save.addActionListener(new CardsNonModalEvent(this),new SaveGameEventCards(this));
        save.setAccelerator(GuiConstants.VK_S, GuiConstants.CTRL_DOWN_MASK);
        file.addMenuItem(save);
        file.addMenuItem(getCompoFactory().newSep());
        /* Fichier/Changer de jeu ACCESSIBLE n'importe quand sauf au menu principal,
        on y revient lorsque c'est accessible*/
        change=getCompoFactory().newMenuItem(getMenusMessages().getVal(MessagesGuiCards.CST_CHANGE));
        changeMode(change);
        file.addMenuItem(change);
        file.addMenuItem(getCompoFactory().newSep());
        exit=getCompoFactory().newMenuItem(getMenusMessages().getVal(MessagesGuiCards.CST_EXIT));
        exit.addActionListener(new QuitEvent(this));
        exit.setAccelerator(GuiConstants.VK_ESCAPE,0);
        file.addMenuItem(exit);
        getJMenuBar().add(file);
    }

    public void changeMode(EnabledMenu _change) {
        _change.setEnabled(false);
        _change.addActionListener(new CardsNonModalEvent(this),new ChangeGameEvent(this));
        _change.setAccelerator(GuiConstants.VK_J, GuiConstants.CTRL_DOWN_MASK);
    }

    public void loadGame() {
//        if (!load.isEnabled()) {
//            return;
//        }
//        if (containerGame instanceof ContainerMulti) {
//            return;
//        }
        //if(!(isPasse()||!core.getContainerGame().isThreadAnime()))
//        if(!isPasse()) {
//            return;
//        }
//        if(!partieSauvegardee)
        if(core.getContainerGame().playingSingleGame()&&!partieSauvegardee) {
            modal.set(true);
            FileOpenSaveFrame.setFileSaveDialogByFrame(true,EditorCards.folder(this,getFrames()),getFileOpenSaveFrame(),new MenuSoloGamesSaveFile(this),new MenuLoadGameContinueFile(this));
//            FileSaveFrame.setFileSaveDialogByFrame(true,EditorCards.folder(this,getFrames()),getFileSaveFrame(),new AdvButtonsSavePanel(new MenuSoloGamesSaveFile(this),new MenuLoadGameContinueFile(this)));
//            if(isPasse()||!core.getContainerGame().isThreadAnime()) {
//                int choix_=saving();
//                if(choix_!=GuiConstants.CANCEL_OPTION) {
//                    if(choix_==GuiConstants.YES_OPTION) {
//                        String fichier_=dialogueFichierSauvegarde();
//                        if(!fichier_.isEmpty()) {
//                            core.getContainerGame().saveCurrentGame(fichier_,getStreams());
//                            tryToLoadDeal();
//                        }
//                    } else {
//                        tryToLoadDeal();
//                    }
//                }
//            }
        } else {
//        } else if(isPasse()||!core.getContainerGame().isThreadAnime()) {
            tryToLoadDeal();
        }
    }
//    public boolean isPasse() {
//        return !(core.getContainerGame() instanceof ContainerSingle) || ((ContainerSingle) core.getContainerGame()).isPasse();
//    }

    public void tryToLoadDeal() {
        partieSauvegardee=true;
        modal.set(true);
        FileOpenFrame.setFileSaveDialogByFrame(true,EditorCards.folder(this,getFrames()),getFileOpenFrame(),new DefButtonsOpenPanelAct(new MenuLoadGameContinueFile(this)));
//        String nomFichier_=dialogueFichierChargement();
//        if (nomFichier_.isEmpty()) {
//            return;
//        }
//        tryToLoadDeal(nomFichier_);
    }

    public void tryToLoadDeal(String _nomFichier) {
        Games g_ = getCore().getFacadeCards().load(_nomFichier);
        tryToLoadDeal(_nomFichier, g_);
    }

    public void tryToLoadDeal(String _nomFichier, Games _g) {
        if (_g.enCoursDePartieBelote()) {
            ContainerSingleBelote containerGame_ = new ContainerSingleBelote(this);
            containerGame_.getPar().jouerBelote(_g.partieBelote());
            containerGame_.load();
            partieSauvegardee=false;
            core.setContainerGame(containerGame_);
            change.setEnabled(true);
            return;
        }
        if (_g.enCoursDePartiePresident()) {
            ContainerSinglePresident containerGame_ = new ContainerSinglePresident(this);
            containerGame_.getPar().jouerPresident(_g.partiePresident());
            containerGame_.load();
            partieSauvegardee=false;
            core.setContainerGame(containerGame_);
            change.setEnabled(true);
            return;
        }
        if (_g.enCoursDePartieTarot()) {
            ContainerSingleTarot containerGame_ = new ContainerSingleTarot(this);
            containerGame_.getPar().jouerTarot(_g.partieTarot());
            containerGame_.load();
            partieSauvegardee=false;
            core.setContainerGame(containerGame_);
            change.setEnabled(true);
            return;
        }
        if (_g.enCoursDePartieSolitaire()) {
            ContainerSolitaire containerGame_ = new ContainerSolitaire(this);
            containerGame_.getPar().jouerSolitaire(_g.partieSolitaire());
            containerGame_.load();
            partieSauvegardee=false;
            core.setContainerGame(containerGame_);
            change.setEnabled(true);
            return;
        }
        erreurDeChargement(_nomFichier, _g.getErrorFile());
    }

    public void saveGame() {
//        if (!save.isEnabled()) {
//            return;
//        }
//        if (containerGame instanceof ContainerMulti) {
//            return;
//        }
        //if(isPasse()||!core.getContainerGame().isThreadAnime())
//        if(isPasse()) {
//            modal.set(true);
//            FileSaveFrame.setFileSaveDialogByFrame(true,EditorCards.folder(this,getFrames()),getFileSaveFrame(),new DefButtonsSavePanelAct(new MenuSoloGamesSaveFile(this),new MenuSaveGameContinueFile(this)));
//            String fichier_=dialogueFichierSauvegarde();
//
//            if(!fichier_.isEmpty()) {
//                core.getContainerGame().saveCurrentGame(fichier_,getStreams());
//                dateSave();
//            }
//        }
        modal.set(true);
        FileSaveFrame.setFileSaveDialogByFrame(true,EditorCards.folder(this,getFrames()),getFileSaveFrame(),new DefButtonsSavePanelAct(new MenuSoloGamesSaveFile(this),new MenuSaveGameContinueFile(this)));
    }

    public void dateSave() {
        dateLastSaved = Clock.getDateTimeText(getThreadFactory());
        lastSavedGameDate.setText(StringUtil.simpleStringsFormat(getMenusMessages().getVal(MessagesGuiCards.LAST_SAVED_GAME), dateLastSaved));
        partieSauvegardee=true;
    }

    public void changeGame() {
//        if (!change.isEnabled()) {
//            return;
//        }
//        if (containerGame instanceof ContainerMulti) {
//            return;
//        }
        //if(!(isPasse()||!core.getContainerGame().isThreadAnime()))
//        if(!isPasse()) {
//            return;
//        }
        if(!partieSauvegardee) {
//        if(core.getContainerGame().playingSingleGame()&&!partieSauvegardee)
            modal.set(true);
            FileSaveFrame.setFileSaveDialogByFrame(true,EditorCards.folder(this,getFrames()),getFileSaveFrame(),new AdvButtonsSavePanel(new MenuSoloGamesSaveFile(this),new MenuSoloGamesContinueFile(this)));
        } else {
            menuSoloGames();
        }
//        if(core.getContainerGame().playingSingleGame()&&!partieSauvegardee) {
//            if(isPasse()||!core.getContainerGame().isThreadAnime()) {
//                int choix_=saving();
//                if(choix_!=GuiConstants.CANCEL_OPTION) {
//                    if(choix_==GuiConstants.YES_OPTION) {
//                        String fichier_=dialogueFichierSauvegarde();
//                        if(!fichier_.isEmpty()) {
//                            core.getContainerGame().saveCurrentGame(fichier_,getStreams());
//                            menuSoloGames();
////                            if (single) {
////                                menuSoloGames();
////                            } else {
////                                menuMultiGames();
////                            }
//                        }
//                    } else {
//                        menuSoloGames();
////                        if (single) {
////                            menuSoloGames();
////                        } else {
////                            menuMultiGames();
////                        }
//                    }
//                }
//            }
//        } else if(isPasse()||!core.getContainerGame().isThreadAnime()) {
//            menuSoloGames();
////            if (single) {
////                menuSoloGames();
////            } else {
////                menuMultiGames();
////            }
//        }
    }

    private void initDealMenu() {
        TranslationsLg lg_ = getFrames().currentLg();
        deal=getCompoFactory().newMenu(getMenusMessages().getVal(MessagesGuiCards.CST_DEAL));
        /* Partie/Conseil "accessible uniquement en cours de partie et
        dans les jeux non solitaires"*/
        EnabledMenu sousMenu_;
        consulting=getCompoFactory().newMenuItem(getMenusMessages().getVal(MessagesGuiCards.CST_CONSULTING));
        consulting.setAccelerator(GuiConstants.VK_F1,0);
        consulting.addActionListener(new ConsultEvent(this));
        deal.addMenuItem(consulting);
        /* Partie/Pause Permet de mettre le jeu en pause*/
        pause=getCompoFactory().newMenuItem(getMenusMessages().getVal(MessagesGuiCards.CST_PAUSE));
        pause.setAccelerator(GuiConstants.VK_DELETE,0);
//        pause.setAccelerator(CST_PAUSE);
        pause.addActionListener(new CardsNonModalEvent(this),new PauseEvent(this, pause));
        pause.setEnabled(false);
        deal.addMenuItem(pause);
        /* Partie/Pause Permet d avoir de l aide*/
        helpGame=getCompoFactory().newMenuItem(getMenusMessages().getVal(MessagesGuiCards.HELP_GAME));
        helpGame.setAccelerator(GuiConstants.VK_F2,0);
        helpGame.addActionListener(new CardsNonModalEvent(this),new DisplayHelpGameEvent(this));
        deal.addMenuItem(helpGame);
        tricksHands=getCompoFactory().newMenuItem(getMenusMessages().getVal(MessagesGuiCards.CST_TRICKS_HANDS));

        tricksHands.addActionListener(new CardsNonModalEvent(this),new DisplayTricksHandsEvent(this));
        deal.addMenuItem(tricksHands);
        teams=getCompoFactory().newMenuItem(getMenusMessages().getVal(MessagesGuiCards.CST_TEAMS));
        teams.addActionListener(new CardsNonModalEvent(this),new DisplayTeamsEvent(this));
        deal.addMenuItem(teams);
        /* Partie/Editer "Permet d'editer n'importe quelle partie de cartes et accessible n'importe quand"*/
        edit=getCompoFactory().newMenu(getMenusMessages().getVal(MessagesGuiCards.CST_EDIT));
        EnabledMenu sousSousMenu_ = getCompoFactory().newMenuItem(GameEnum.BELOTE.toString(lg_));
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
        sousSousMenu_ = getCompoFactory().newMenuItem(GameEnum.CLASSIC.toString(lg_));
        sousSousMenu_.addActionListener(new EditEvent(this, GameEnum.CLASSIC));
        edit.addMenuItem(sousSousMenu_);
        editGames.put(GameEnum.CLASSIC, sousSousMenu_);
        sousSousMenu_ = getCompoFactory().newMenuItem(GameEnum.FREECELL.toString(lg_));
        sousSousMenu_.addActionListener(new EditEvent(this, GameEnum.FREECELL));
        edit.addMenuItem(sousSousMenu_);
        editGames.put(GameEnum.FREECELL, sousSousMenu_);
        sousSousMenu_ = getCompoFactory().newMenuItem(GameEnum.SPIDER.toString(lg_));
        sousSousMenu_.addActionListener(new EditEvent(this, GameEnum.SPIDER));
        edit.addMenuItem(sousSousMenu_);
        editGames.put(GameEnum.SPIDER, sousSousMenu_);
        deal.addMenuItem(edit);
        /* Partie/Demo "Permet de voir la demostration d une partie"*/
        demo=getCompoFactory().newMenu(getMenusMessages().getVal(MessagesGuiCards.CST_DEMO));
        sousMenu_=getCompoFactory().newMenuItem(GameEnum.BELOTE.toString(lg_));
        sousMenu_.addActionListener(new CardsNonModalEvent(this),new SimulationEvent(this, GameEnum.BELOTE));
        sousMenu_.setAccelerator(GuiConstants.VK_B, GuiConstants.CTRL_DOWN_MASK + GuiConstants.SHIFT_DOWN_MASK);
        demo.addMenuItem(sousMenu_);
        demoGames.put(GameEnum.BELOTE, sousMenu_);
        sousMenu_=getCompoFactory().newMenuItem(GameEnum.PRESIDENT.toString(lg_));
        sousMenu_.addActionListener(new CardsNonModalEvent(this),new SimulationEvent(this, GameEnum.PRESIDENT));
        sousMenu_.setAccelerator(GuiConstants.VK_P, GuiConstants.CTRL_DOWN_MASK + GuiConstants.SHIFT_DOWN_MASK);
        demo.addMenuItem(sousMenu_);
        demoGames.put(GameEnum.PRESIDENT, sousMenu_);
        sousMenu_=getCompoFactory().newMenuItem(GameEnum.TAROT.toString(lg_));
        sousMenu_.addActionListener(new CardsNonModalEvent(this),new SimulationEvent(this, GameEnum.TAROT));
        sousMenu_.setAccelerator(GuiConstants.VK_T, GuiConstants.CTRL_DOWN_MASK + GuiConstants.SHIFT_DOWN_MASK);
        demo.addMenuItem(sousMenu_);
        demoGames.put(GameEnum.TAROT, sousMenu_);
        deal.addMenuItem(demo);
        /* Partie/Entrainement "accessible n'importe quand pour pouvoir s'entrainer"*/
        training=getCompoFactory().newMenu(getMenusMessages().getVal(MessagesGuiCards.CST_TRAINING));
        /* Partie/Entrainement au Tarot*/
        //Petitasauver,Petitachasser,Petitaemmeneraubout;
        for (ChoiceTarot ct_:allChoiceTarot()) {

            sousMenu_=getCompoFactory().newMenuItem(Games.toString(ct_,getFrames().currentLg()));
            sousMenu_.addActionListener(new CardsNonModalEvent(this),new ListenerTrainingTarot(this, ct_));
            training.addMenuItem(sousMenu_);
            trainingTarot.put(ct_, sousMenu_);
        }
        deal.addMenuItem(training);
//        multiStop = getCompoFactory().newMenuItem(getMessages().getVal(CST_MULTI_STOP));
//        multiStop.addActionListener(new QuitMultiEvent(this));
//        deal.addMenuItem(multiStop);
        getJMenuBar().add(deal);
    }
    public static ChoiceTarot[] allChoiceTarot() {
        return new ChoiceTarot[]{ChoiceTarot.SAVE_SMALL,ChoiceTarot.HUNT_SMALL,ChoiceTarot.LEAD_SMALL_BOUND};
    }

    public void consult() {
//        if (!consulting.isEnabled()) {
//            return;
//        }
        ContainerGame cg_ = core.getContainerGame();
        if (cg_ instanceof ContainerSingleBelote) {
            ((ContainerSingleBelote)cg_).conseil();
        }
        if (cg_ instanceof ContainerSinglePresident) {
            ((ContainerSinglePresident)cg_).conseil();
        }
        if (cg_ instanceof ContainerSingleTarot) {
            ((ContainerSingleTarot)cg_).conseil();
        }
//        if (!(cg_ instanceof ContainerSingle)) {
//            return;
//        }
//        if(!cg_.isThreadAnime()) {
//            ((ContainerSingle) cg_).conseil();
//        }
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
        ContainerGame cg_ = core.getContainerGame();
        int cp_ = ContainerSingleImpl.PAUSE_STOPPED;
        if (cg_ instanceof ContainerSingleBelote) {
            cp_ = getPausingCardsAnims().complement((ContainerSingleBelote) cg_);
        }
        if (cg_ instanceof ContainerSinglePresident) {
            cp_ = getPausingCardsAnims().complement((ContainerSinglePresident) cg_);
        }
        if (cg_ instanceof ContainerSingleTarot) {
            cp_ = getPausingCardsAnims().complement((ContainerSingleTarot) cg_);
        }
//        cg_.setPasse(!cg_.isPasse());
        if (cp_ == ContainerSingleImpl.PAUSE_STOPPED) {
            return;
        }
        if (cg_ instanceof ContainerSingleBelote) {
            if (cg_.getState() == CardAnimState.BID_BELOTE) {
                ((ContainerSingleBelote)cg_).thread(new AnimationBidBelotePause(((ContainerSingleBelote)cg_)));
            } else {
                ((ContainerSingleBelote)cg_).thread(new AnimationCardBelotePause(((ContainerSingleBelote)cg_)));
            }
        }
        if (cg_ instanceof ContainerSinglePresident) {
            ((ContainerSinglePresident)cg_).thread(new AnimationCardPresidentPause(((ContainerSinglePresident)cg_)));
        }
        if (cg_ instanceof ContainerSingleTarot) {
            if (cg_.getState() == CardAnimState.BID_TAROT) {
                ((ContainerSingleTarot)cg_).thread(new AnimationBidTarotPause(((ContainerSingleTarot)cg_)));
            } else {
                ((ContainerSingleTarot)cg_).thread(new AnimationCardTarotPause(((ContainerSingleTarot)cg_)));
            }
        }
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
//        if (!(core.getContainerGame() instanceof ContainerSingle)) {
//            return;
//        }
//        ((ContainerSingle) core.getContainerGame()).setPasse(!((ContainerSingle) core.getContainerGame()).isPasse());
    }
    public void displayHelpGame() {
//        if (!helpGame.isEnabled()) {
//            return;
//        }
        ContainerGame cg_ = core.getContainerGame();
        if (cg_ instanceof ContainerSingleBelote) {
            ((ContainerSingleBelote)cg_).aideAuJeu();
        }
        if (cg_ instanceof ContainerSinglePresident) {
            ((ContainerSinglePresident)cg_).aideAuJeu();
        }
        if (cg_ instanceof ContainerSingleTarot) {
            ((ContainerSingleTarot)cg_).aideAuJeu();
        }
    }
    public void displayTricksHands() {
//        if (!tricksHands.isEnabled()) {
//            return;
//        }
        ContainerGame cg_ = core.getContainerGame();
        if (cg_ instanceof ContainerSingleBelote) {
            ((ContainerSingleBelote)cg_).showTricksHands();
        }
        if (cg_ instanceof ContainerSinglePresident) {
            ((ContainerSinglePresident)cg_).showTricksHands();
        }
        if (cg_ instanceof ContainerSingleTarot) {
            ((ContainerSingleTarot)cg_).showTricksHands();
        }
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
            editeurBelote();
            EditorBelote.getPartie(getEditorBelote());
        }
        if (_game == GameEnum.PRESIDENT) {
            editeurPresident();
            EditorPresident.getPartie(getEditorPresident());
        }
        if (_game == GameEnum.TAROT) {
            editeurTarot();
            EditorTarot.getPartie(getEditorTarot());
        }
        if (_game == GameEnum.CLASSIC) {
            editeurSolitaireClassic();
            EditorSolitaire.getPartie(getEditorClassic());
        }
        if (_game == GameEnum.FREECELL) {
            editeurSolitaireFreeCell();
            EditorSolitaire.getPartie(getEditorFreeCell());
        }
        if (_game == GameEnum.SPIDER) {
            editeurSolitaireSpider();
            EditorSolitaire.getPartie(getEditorSpider());
        }
    }
    public void simulateGame(GameEnum _game) {
        if (_game == GameEnum.BELOTE) {
//            if (!demo.isEnabled()) {
//                return;
//            }
            /*if (containerGame instanceof ContainerMulti) {
                return;
            }*/
            core.setContainerGame(new ContainerSimuBelote(this));
        }
        if (_game == GameEnum.PRESIDENT) {
//            if (!demo.isEnabled()) {
//                return;
//            }
            /*if (containerGame instanceof ContainerMulti) {
                return;
            }*/
            core.setContainerGame(new ContainerSimuPresident(this));
        }
        if (_game == GameEnum.TAROT) {
//            if (!demo.isEnabled()) {
//                return;
//            }
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
//        if(core.getContainerGame().playingSingleGame()&&!partieSauvegardee) {
//            modal.set(true);
//            FileSaveFrame.setFileSaveDialogByFrame(true,EditorCards.folder(this,getFrames()),getFileSaveFrame(),new AdvButtonsSavePanel(new MenuSoloGamesSaveFile(this),new MenuTrainingTarotContinueFile(this,_ct)));
////            int choix_=saving();
////            if(choix_!=GuiConstants.CANCEL_OPTION) {
////                if(choix_==GuiConstants.YES_OPTION) {
////                    String fichier_=dialogueFichierSauvegarde();
////                    if(!fichier_.isEmpty()) {
////                        core.getContainerGame().saveCurrentGame(fichier_,getStreams());
////                    }
////                }
////                core.setContainerGame(new ContainerSingleTarot(this));
////                MenuItemUtils.setEnabledMenu(change,true);
////                ((ContainerSingleTarot) core.getContainerGame()).jouerDonneEntrainement(_ct);
////            }
//        } else {
//            core.setContainerGame(new ContainerSingleTarot(this));
//            MenuItemUtils.setEnabledMenu(change,true);
//            ((ContainerSingleTarot) core.getContainerGame()).jouerDonneEntrainement(_ct);
//        }
        ContainerSingleTarot cst_ = new ContainerSingleTarot(this);
        core.setContainerGame(cst_);
        change.setEnabled(true);
        cst_.jouerDonneEntrainement(_ct);
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
        TranslationsLg lg_ = getFrames().currentLg();
        parameters=getCompoFactory().newMenu(getMenusMessages().getVal(MessagesGuiCards.CST_PARAMETERS));
        EnabledMenu sousMenu_=getCompoFactory().newMenuItem(GameEnum.BELOTE.toString(lg_));
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
        players=getCompoFactory().newMenuItem(getMenusMessages().getVal(MessagesGuiCards.CST_PLAYERS));
        players.addActionListener(new ManageNicknameEvent(this));
        players.setAccelerator(GuiConstants.VK_J, GuiConstants.CTRL_DOWN_MASK + GuiConstants.ALT_DOWN_MASK);
        parameters.addMenuItem(players);
        launching=getCompoFactory().newMenuItem(getMenusMessages().getVal(MessagesGuiCards.CST_LAUNCHING));
        launching.addActionListener(new ManageSoftEvent(this, MessagesGuiCards.CST_LAUNCHING));
        launching.setAccelerator(GuiConstants.VK_L, GuiConstants.CTRL_DOWN_MASK);
        parameters.addMenuItem(launching);
        softMenus.addEntry(MessagesGuiCards.CST_LAUNCHING,launching);
        timing= getCompoFactory().newMenuItem(getMenusMessages().getVal(MessagesGuiCards.CST_TIMING));
        timing.addActionListener(new ManageSoftEvent(this, MessagesGuiCards.CST_TIMING));
        timing.setAccelerator(GuiConstants.VK_F4,0);
        parameters.addMenuItem(timing);
        softMenus.addEntry(MessagesGuiCards.CST_TIMING,timing);
        interact= getCompoFactory().newMenuItem(getMenusMessages().getVal(MessagesGuiCards.CST_INTERACT));
        interact.addActionListener(new ManageSoftEvent(this, MessagesGuiCards.CST_INTERACT));
        interact.setAccelerator(GuiConstants.VK_F5,0);
        parameters.addMenuItem(interact);
        softMenus.addEntry(MessagesGuiCards.CST_INTERACT,interact);
        languageDialogButtons.translate(getMenusMessages().getVal(MessagesGuiCards.CST_LANGUAGE));
        languageDialogButtons.commonParametersMenu(parameters,new ManageLanguageEventCards(this),GuiConstants.VK_F6,0);
        core.commonParametersMenu(parameters,this);
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
        TranslationsLg lg_ = getFrames().currentLg();
        if (_game == GameEnum.BELOTE) {
            DialogRulesBelote.initDialogRulesBelote(_game.toString(lg_), this, getReglesBelote(),new AfterValidateRulesBeloteSingle(this));
            DialogRulesBelote.setBeloteDialog(true,0,this);
        }
        if (_game == GameEnum.PRESIDENT) {
            DialogRulesPresident.initDialogRulesPresident(_game.toString(lg_), this, getReglesPresident(),new AfterValidateRulesPresidentSingle(this));
            DialogRulesPresident.setPresidentDialog(true, 0,this);
        }
        if (_game == GameEnum.TAROT) {
            DialogRulesTarot.initDialogRulesTarot(_game.toString(lg_), this, getReglesTarot(),new AfterValidateRulesTarotSingle(this));
            DialogRulesTarot.setTarotDialog(true,0,this);
        }
    }
    public void manageNicknames() {
        modal.set(true);
        DialogNicknames.initDialogNicknames(getMenusMessages().getVal(MessagesGuiCards.CST_PLAYERS), this);
    }
    public void manageSoft(String _key) {
        DialogSoft.initDialogSoft(_key,getMenusMessages().getVal(_key), this);
        DialogSoft.setDialogSoft(_key, this);
        DialogSoft.getParametres(dialogSoft.getVal(_key));
//        DialogSoft.initDialogSoft(getMessages().getVal(_key), this);
//        DialogSoft.setDialogSoft(_key, this);
//        parametres=DialogSoft.getParametres(getDialogSoft());
//        parametres.sauvegarder(StringUtil.concat(LaunchingCards.getTempFolderSl(getFrames()),FileConst.PARAMS),getStreams());
//        containerGame.setSettings(parametres);
    }
    public void manageLanguage() {
        core.manageLanguage(this,this, languageDialogButtons);
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
        help=getCompoFactory().newMenu(getMenusMessages().getVal(MessagesGuiCards.CST_HELP));
        /* Aide/Aide generale Explication du fonctionnement du logiciel et des regles utilisables*/
//        generalHelp=getCompoFactory().newMenuItem(getMessages().getVal(CST_GENERAL_HELP));
        generalHelp.setText(getMenusMessages().getVal(MessagesGuiCards.CST_GENERAL_HELP));
//        MenuItemUtils.setEnabledMenu(generalHelp,false);
        generalHelp.addActionListener(new DisplayHelpEvent(this));
        generalHelp.setAccelerator(GuiConstants.VK_F3,0);
        help.addMenuItem(generalHelp);
        getJMenuBar().add(help);

    }

    public void displayHelp() {
        //On indique a l utilisatteur comment utiliser le logiciel et jouer aux cartes
//        if (!helpFrames.isEmpty()) {
//            helpFrames.first().setTitle(getMessages().getVal(CST_GENERAL_HELP));
//            helpFrames.first().initialize(this);
//            return;
//        }
//        if (helpInitializerThread == null || helpInitializerThread.isAlive() || helpInitializerTask == null) {
//            return;
//        }
//        FrameGeneralHelp aide_=new FrameGeneralHelp(getMessages().getVal(CST_GENERAL_HELP),this,generalHelp);
        helpFrames.setTitle(getMenusMessages().getVal(MessagesGuiCards.CST_GENERAL_HELP));
        helpFrames.initialize(this);
    }

    /**Initialise la barre de menus*/
    private void initMenus() {
        setJMenuBar(getCompoFactory().newMenuBar());
        initFileMenu();
        initDealMenu();
        initParametersMenu();
        initHelpMenu();
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
//        return StringUtil.nullToEmpty(fichier_);
//    }
    /**Sauvegarder une partie dans un fichier*/
//    private String dialogueFichierChargement() {
//        String fichier_;
//        if (isSaveHomeFolder()) {
//            fichier_=getFileOpenDialogInt().input(getCommonFrame(), true, FileConst.GAME_EXT, getFrames().getHomePath());
//        } else {
//            fichier_=getFileOpenDialogInt().input(getCommonFrame(), true, FileConst.GAME_EXT, EMPTY_STRING);
//        }
//        return StringUtil.nullToEmpty(fichier_);
//    }
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
    private void editeurSolitaireClassic() {
        EditorSolitaire.initEditorSolitaire(this,getEditorClassic());
    }
    private void editeurSolitaireFreeCell() {
        EditorSolitaire.initEditorSolitaire(this,getEditorFreeCell());
    }
    private void editeurSolitaireSpider() {
        EditorSolitaire.initEditorSolitaire(this,getEditorSpider());
    }
    private void erreurDeChargement(String _fichier, String _cause) {
        //The issue of quality of game are caught here
        String mes_ = StringUtil.simpleStringsFormat(getMenusMessages().getVal(MessagesGuiCards.CST_FILE_NOT_LOADED), _fichier);
        errorsFile.display(getMenusMessages().getVal(MessagesGuiCards.CST_FILE_NOT_LOADED_TILE),StringUtil.concat(mes_,"\n",StringUtil.nullToEmpty(_cause)));
        lastFile = _fichier;
//        getFrames().getMessageDialogAbs().input(getCommonFrame(),mes_, getMenusMessages().getVal(MessagesGuiCards.CST_FILE_NOT_LOADED_TILE), GuiConstants.ERROR_MESSAGE);
    }

    /**On ecoute les boutons du menu principal et des menus jeux*/
    public void beginGame(GameEnum _jeuBouton) {
        if(_jeuBouton==GameEnum.BELOTE) {
            ContainerSingleBelote b_ = new ContainerSingleBelote(this);
            core.setContainerGame(b_);
            change.setEnabled(true);
            b_.setReglesBelote(getReglesBelote());
            b_.modify();
        } else if(_jeuBouton==GameEnum.PRESIDENT) {
            ContainerSinglePresident p_ = new ContainerSinglePresident(this);
            core.setContainerGame(p_);
            change.setEnabled(true);
            p_.setReglesPresident(getReglesPresident());
            p_.modify();
        } else if(_jeuBouton==GameEnum.TAROT){
            ContainerSingleTarot t_ = new ContainerSingleTarot(this);
            core.setContainerGame(t_);
            change.setEnabled(true);
            t_.modify();
        } else {
            ContainerSolitaire t_ = new ContainerSolitaire(this);
            t_.setSolitaireType(_jeuBouton.getSolitaireType());
            core.setContainerGame(t_);
            change.setEnabled(true);
            t_.modify();
        }
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
        return core.getFacadeCards().getPseudosJoueurs().getPseudo();
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
        AbstractProgramInfos infos_ = getFrames();
        String value_ = StringUtil.nullToEmpty(_language);
        StreamLanguageUtil.saveLanguage(WindowCards.getTempFolder(getFrames()), value_,infos_.getStreams());
        setLanguageKey(_language);
        translate();
    }

    private void translate() {
//        initMessageName();
        TranslationsLg lg_ = getFrames().currentLg();
        file.setText(getMenusMessages().getVal(MessagesGuiCards.CST_FILE));
        load.setText(getMenusMessages().getVal(MessagesGuiCards.CST_LOAD));
        save.setText(getMenusMessages().getVal(MessagesGuiCards.CST_SAVE));
        change.setText(getMenusMessages().getVal(MessagesGuiCards.CST_CHANGE));
        exit.setText(getMenusMessages().getVal(MessagesGuiCards.CST_EXIT));
        deal.setText(getMenusMessages().getVal(MessagesGuiCards.CST_DEAL));
        consulting.setText(getMenusMessages().getVal(MessagesGuiCards.CST_CONSULTING));
        pause.setText(getMenusMessages().getVal(MessagesGuiCards.CST_PAUSE));
        helpGame.setText(getMenusMessages().getVal(MessagesGuiCards.HELP_GAME));
        tricksHands.setText(getMenusMessages().getVal(MessagesGuiCards.CST_TRICKS_HANDS));
        teams.setText(getMenusMessages().getVal(MessagesGuiCards.CST_TEAMS));
        edit.setText(getMenusMessages().getVal(MessagesGuiCards.CST_EDIT));
        for (GameEnum g: GameEnum.allValidPlusSolo()) {
            editGames.getVal(g).setText(g.toString(lg_));
        }
        demo.setText(getMenusMessages().getVal(MessagesGuiCards.CST_DEMO));
        for (GameEnum g: GameEnum.allValid()) {
            demoGames.getVal(g).setText(g.toString(lg_));
        }
        training.setText(getMenusMessages().getVal(MessagesGuiCards.CST_TRAINING));
        for (ChoiceTarot c: allChoiceTarot()) {
            trainingTarot.getVal(c).setText(Games.toString(c,getFrames().currentLg()));
        }
//        multiStop.setText(getMessages().getVal(CST_MULTI_STOP));
        parameters.setText(getMenusMessages().getVal(MessagesGuiCards.CST_PARAMETERS));
        for (GameEnum g: GameEnum.allValid()) {
            rulesGames.getVal(g).setText(g.toString(lg_));
        }
        players.setText(getMenusMessages().getVal(MessagesGuiCards.CST_PLAYERS));
        launching.setText(getMenusMessages().getVal(MessagesGuiCards.CST_LAUNCHING));
        timing.setText(getMenusMessages().getVal(MessagesGuiCards.CST_TIMING));
        interact.setText(getMenusMessages().getVal(MessagesGuiCards.CST_INTERACT));
        languageDialogButtons.translate(getMenusMessages().getVal(MessagesGuiCards.CST_LANGUAGE));
        core.getDisplaying().setText(getMenusMessages().getVal(MessagesGuiCards.CST_DISPLAYING));
        translateDisplaying(lg_, core);
        help.setText(getMenusMessages().getVal(MessagesGuiCards.CST_HELP));
        generalHelp.setText(getMenusMessages().getVal(MessagesGuiCards.CST_GENERAL_HELP));
//        if (welcomeLabel != null) {
//            welcomeLabel.setText(StringUtil.simpleStringsFormat(getMenusMessages().getVal(MessagesGuiCards.CST_WELCOME), pseudo()));
//        }
        welcomeLabel.setText(StringUtil.simpleStringsFormat(getMenusMessages().getVal(MessagesGuiCards.CST_WELCOME), pseudo()));
//        if (singleModeButton != null) {
//            singleModeButton.setText(getMenusMessages().getVal(MessagesGuiCards.CST_SINGLE_MODE));
//        }
        singleModeButton.setText(getMenusMessages().getVal(MessagesGuiCards.CST_SINGLE_MODE));
//        if (multiModeButton != null) {
//            multiModeButton.setText(getMenusMessages().getVal(MessagesGuiCards.CST_MULTI_MODE));
//        }
//        if (goHelpMenu != null) {
//            goHelpMenu.setText(getMenusMessages().getVal(MessagesGuiCards.CST_GO_HELP_MENU));
//        }
        goHelpMenu.setText(getMenusMessages().getVal(MessagesGuiCards.CST_GO_HELP_MENU));
        lastSavedGameDate.setText(StringUtil.simpleStringsFormat(getMenusMessages().getVal(MessagesGuiCards.LAST_SAVED_GAME), dateLastSaved));
        String mes_ = StringUtil.simpleStringsFormat(getMenusMessages().getVal(MessagesGuiCards.CST_FILE_NOT_LOADED), lastFile);
        errorsFile.getReport().setText(mes_);
        errorsFile.getCommonFrame().setTitle(getMenusMessages().getVal(MessagesGuiCards.CST_FILE_NOT_LOADED_TILE));
    }

    public static void translateDisplaying(TranslationsLg _lg, WindowCardsCore _c) {
        translateDisplaying(_lg, _c, GameEnum.allValid());
    }

    public static void translateDisplaying(TranslationsLg _lg, WindowCardsCore _c, CustList<GameEnum> _gameEnums) {
        for (GameEnum g: _gameEnums) {
            _c.getDisplayingGames().getVal(g).setText(g.toString(_lg));
        }
    }

    public void changeStreamsMenusEnabled(boolean _enabled) {
        load.setEnabled(_enabled);
        save.setEnabled(_enabled);
        change.setEnabled(_enabled);
        pause.setEnabled(!_enabled);
    }

    public void changeMenuSimuEnabled(boolean _enabled) {
        for (EnabledMenu e: demoGames.values()) {
            e.setEnabled(_enabled);
        }
        for (EnabledMenu e: trainingTarot.values()) {
            e.setEnabled(_enabled);
        }
    }
//    @Override
//    public AbstractSocket initIndexInGame(boolean _first, AbstractSocket _socket) {
//        return _socket;
//    }

    public boolean isSaveHomeFolder() {
        return core.getFacadeCards().getParametres().isSaveHomeFolder();
    }

//    public void setSingle(boolean _single) {
//        single = _single;
//    }

    public EnabledMenu getLoad() {
        return load;
    }

    public EnabledMenu getSave() {
        return save;
    }

    public EnabledMenu getChange() {
        return change;
    }

    public EnabledMenu getConsulting() {
        return consulting;
    }

    public EnabledMenu getPause() {
        return pause;
    }

    public EnabledMenu getHelpGame() {
        return helpGame;
    }

    public EnabledMenu getTricksHands() {
        return tricksHands;
    }

    public EnabledMenu getTeams() {
        return teams;
    }

    public IdMap<GameEnum,EnabledMenu> getEditGames() {
        return editGames;
    }

//    public EnabledMenu getDemo() {
//        return demo;
//    }

    public IdMap<GameEnum,EnabledMenu> getDemoGames() {
        return demoGames;
    }

    public IdMap<ChoiceTarot,EnabledMenu> getTrainingTarot() {
        return trainingTarot;
    }

//    public AbsMenuItem getMultiStop() {
//        return multiStop;
//    }

    public IdMap<GameEnum,EnabledMenu> getRulesGames() {
        return rulesGames;
    }

    public EnabledMenu getPlayers() {
        return players;
    }

    public EnabledMenu getLaunching() {
        return launching;
    }

    public EnabledMenu getGeneralHelp() {
        return generalHelp;
    }

    @Override
    public String getApplicationName() {
        return MessagesCardGames.CARDS;
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


//    @Override
//    public StringMap<AbstractFutureParam<CardNatLgNamesNavigation>> getPrepared() {
//        return core.getPrepared();
//    }

//    public void setPrepare(StringMap<AbstractFutureParam<CardNatLgNamesNavigation>> _i) {
//        this.core.setPrepare(_i);
//    }

    public AbstractFutureParam<HelpIndexesTree> getHelpInitializerTask() {
        return helpInitializerTask;
    }

    public void setHelpInitializerTask(AbstractFutureParam<HelpIndexesTree> _helpInitializerTask) {
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

    public EditorSolitaire getEditorClassic() {
        return editorClassic;
    }

    public EditorSolitaire getEditorFreeCell() {
        return editorFreeCell;
    }

    public EditorSolitaire getEditorSpider() {
        return editorSpider;
    }

    public DialogTeamsPlayers getDialogTeamsPlayers() {
        return core.getDialogTeamsPlayers();
    }

    public DialogNicknames getDialogNicknames() {
        return dialogNicknames;
    }

    public StringMap<DialogSoft> getDialogSoft() {
        return dialogSoft;
    }

    public DialogSoft getDialogSoftInteract() {
        return dialogSoft.getVal(MessagesGuiCards.CST_INTERACT);
    }

    public DialogSoft getDialogSoftLaunching() {
        return dialogSoft.getVal(MessagesGuiCards.CST_LAUNCHING);
    }

    public DialogSoft getDialogSoftTiming() {
        return dialogSoft.getVal(MessagesGuiCards.CST_TIMING);
    }

//    public DialogServerCards getDialogServer() {
//        return dialogServer;
//    }

    public AbsButton getSingleModeButton() {
        return singleModeButton;
    }

//    public AbsButton getMultiModeButton() {
//        return multiModeButton;
//    }

    public FileSaveFrame getFileSaveFrame() {
        return fileSaveFrame;
    }

    public FileOpenFrame getFileOpenFrame() {
        return fileOpenFrame;
    }

    public FileOpenSaveFrame getFileOpenSaveFrame() {
        return fileOpenSaveFrame;
    }

    public AbstractAtomicBoolean getModal() {
        return modal;
    }

    public FrameGeneralHelp getHelpFrames() {
        return helpFrames;
    }

    @Override
    public WindowCardsCore baseWindow() {
        return getCore();
    }

    public IdMap<GameEnum, AbsButton> getSoloGames() {
        return soloGames;
    }

    public WindowCardsCore getCore() {
        return core;
    }

    public LanguageDialogButtons getLanguageDialogButtons() {
        return languageDialogButtons;
    }

    public AbsPausingCardsAnims getPausingCardsAnims() {
        return pausingCardsAnims;
    }

    public AbsButton getBackMenu() {
        return backMenu;
    }

    public EnabledMenu getTiming() {
        return timing;
    }

    public EnabledMenu getInteract() {
        return interact;
    }

    public void setPausingCardsAnims(AbsPausingCardsAnims _p) {
        this.pausingCardsAnims = _p;
    }

    public boolean isPartieSauvegardee() {
        return partieSauvegardee;
    }

//    public AbsActionListenerAct getGuardRender() {
//        return guardRender;
//    }
//    public ResultCardsServerInteract getResultCardsServerInteract() {
//        return resultCardsServerInteract;
//    }
//
//    public void setResultCardsServerInteract(ResultCardsServerInteract _r) {
//        this.resultCardsServerInteract = _r;
//    }
}
