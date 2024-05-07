package aiki.gui.components.fight;







import aiki.comparators.ComparatorTr;
import aiki.db.DataBase;
import aiki.game.fight.*;
import aiki.game.fight.enums.UsefulValueLaw;
import aiki.gui.components.AbsMetaLabelPk;
import aiki.gui.components.fight.events.*;
import aiki.gui.threads.*;
import aiki.main.AikiNatLgNamesNavigation;
import aiki.main.PkNonModalEvent;
import aiki.sml.GamesPk;
import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.game.fight.animations.AnimationInt;
import aiki.game.fight.enums.ActionType;
import aiki.game.fight.enums.FightState;
import aiki.gui.WindowAiki;
import aiki.gui.components.AbilityLabel;
import aiki.gui.components.checks.MoveFighterCheckBox;
import aiki.gui.dialogs.FrameHtmlData;
import aiki.gui.dialogs.SelectHealingItem;
import aiki.gui.listeners.AbilityFightEvent;
import aiki.gui.listeners.FighterAction;
import aiki.gui.listeners.MoveEvent;
import aiki.gui.listeners.SelectPlaceEvent;
import code.gui.*;
import code.gui.events.ClosingChildFrameEvent;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.maths.Rate;
import code.sml.util.*;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class Battle extends GroupFrame implements AbsChildFrame {

    private static final String BATTLE = "aiki.gui.components.fight.battle";
//    private static final String ACTION_TYPE = "aiki.game.fight.enums.actiontype";

    private static final String RETURN_LINE = "\n";

    private static final String FRONT_TEAM = "frontTeam";

    private static final String BACK_TEAM = "backTeam";

    private static final String BACK_TEAM_SUB = "backTeamSub";

    private static final String TEAM_CST = "team";

    private static final String EVOS = "evos";

    private static final String BALLS = "balls";

    private static final String DATA_FIGHT = "dataFight";

    private static final String CATCH_PK = "catchPk";

    private static final String CST_FLEE = "flee";

    private static final String GO_TO_ROUND = "goToRound";

    private static final String VALIDATE_EVOS = "validateEvos";

    private static final String VALIDATE_SWITCH = "validateSwitch";

    private static final String NICKNAME_CST = "nickname";

    private static final String CANCEL_CATCH = "cancelCatch";

    private static final String GO_BACK = "goBack";

    private static final String NO_ITEM = "noItem";

    private static final String SELECTED_ITEM = "selectedItem";

    private static final String SELECT_ITEM = "selectItem";

    private static final String SELECT_ABILITY = "selectAbility";

    private static final String SELECT_MT = "selectMt";

    private static final String SELECT_MOVE_ROUND = "selectMoveRound";

    private static final String SELECT_MOVE_HEAL = "selectMoveHeal";

    private static final String SELECT_TARGET = "selectTarget";

    private static final String SELECT_ACTION = "selectAction";

    private static final String NO_EVO = "noEvo";

    private static final String TITLE = "title";

    private static final String FIGHT_DATA_MESSAGE = "fightDataMessage";

    private static final String ERRORS = "errors";

    private static final String ROUND = "round";

    private static final String CST_ACTIONS = "actions";

//    private static final JPanel UPPER = new JPanel();

    private StringMap<String> messages = new StringMap<String>();

    private final WindowAiki window;

//    private JSplitPane splitter;

    //    private final JPanel actionsBattle = new JPanel();
    //private JSplitPane actionsBattle;


    //private JScrollPane scrollUpper;

//    private int heightUpper;

    private final AbsPanel lower = getFrames().getCompoFactory().newLineBox();

    private final FrontBattle frontBattle;

    private FighterPanel fighterPanel;
    private FighterPanel fighterFleePanel;
    private FighterPanel fighterCaughtPanel;
    private FighterPanel fighterCaughtNicknamePanel;
    private FighterPanel fighterCatchingPanel;

    private PokemonPanel pokemonPanel;

    private AbsPanel movesLearnPanel;
    private AbsScrollPane movesLearnPanelScroll;

    private AbsPanel abilitiesLearnPanel;

    private final CustList<AbilityLabel> abilityLabels = new CustList<AbilityLabel>();
    private final CustList<AbsMetaLabelPk> abilityLabelsAbs = new CustList<AbsMetaLabelPk>();

    private FighterPanel fighterFrontPanel;

    private FighterPanel fighterBackPanel;
    private FighterPanel fighterBackPanelSub;

    private AbsPanel panelPlaces;

    private final CustList<PlaceLabel> placesLabels = new CustList<PlaceLabel>();
    private final CustList<AbsMetaLabelPk> placesLabelsAbs = new CustList<AbsMetaLabelPk>();

    private BallPanel ballPanel;

    private final FacadeGame facade;

    private final FrameHtmlData renderDataFight;
//    private final CustList<FrameHtmlData> htmlDialogs = new CustList<FrameHtmlData>();

    private AbsPanel actionType;

    private final CustList<ActionLabel> actionsLabels = new CustList<ActionLabel>();

    private AbsPanel actions;

    private final AbsPanel movesPanel = getFrames().getCompoFactory().newPageBox();

    private AbsPlainLabel selectedItem;

    private final AbsPanel targetsPanel = getFrames().getCompoFactory().newBorder();

    private TargetsPanel targets;

    private final CustList<MoveLabel> movesLabels = new CustList<MoveLabel>();
    private final CustList<AbsMetaLabelPk> movesLabelsAbs = new CustList<AbsMetaLabelPk>();

    private AbsPanel fleeWeb;

    private AbsButton catchBall;
    private AbsButton catchBallEnd;
    private final CustList<AbsPlainLabel> catchBallResult = new CustList<AbsPlainLabel>();

    private AbsButton validateActions;

    private AbsButton flee;

    private AbsPlainLabel webLabel;

//    private AbsPlainButton web;

    private AbsButton cancelCatch;

    private PlaceLabel plLabelBack;

    //private GridBagLayout grid;

//    private boolean enableClick = true;

//    private final AbsPlainLabel nickname;
    private final AbsTextField nicknameField;

//    private AbsButton nicknameLabel;

//    private String typedNickname = DataBase.EMPTY_STRING;

    private AbsPlainLabel errorLabel;

    private AbsWrappedTextArea commentsErrors;
    private AbsScrollPane commentsErrorsScroll;

    private AbsPlainLabel roundLabel;

    private AbsWrappedTextArea commentsRound;
    private AbsScrollPane commentsRoundScroll;

    private boolean enabledChangeLanguage;

    private RoundThread roundThread;

    private boolean enableAnimation;

//    private boolean enabledClicked;

    private final AbsScrollPane scroll;
    private final AbsPanel comments = getFrames().getCompoFactory().newPageBox();
    private final AbsPanel forms = getFrames().getCompoFactory().newLineBox();
    private final AbsPanel team = getFrames().getCompoFactory().newPageBox();
    public Battle(WindowAiki _window, FacadeGame _facade, FrontBattle _frontBattle) {
        super(_window.getLanguageKey(),_window.getFrames());
        frontBattle = _frontBattle;
        renderDataFight = new FrameHtmlData(_window, _window.getDataBattle());
//        super(JSplitPane.VERTICAL_SPLIT, new JScrollPane(UPPER), new JScrollPane(LOWER));
//        splitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(UPPER), new JScrollPane(LOWER));
//        setContentPane(splitter);
        facade = _facade;
        scroll = getFrames().getCompoFactory().newAbsScrollPane(lower);
        getCommonFrame().setContentPane(scroll);
        window = _window;
//        nickname = _window.getCompoFactory().newPlainLabel("");
        nicknameField = _window.getCompoFactory().newTextField();
        setDialogIcon(_window.getCommonFrame());
        getCommonFrame().setLocationRelativeTo(_window.getCommonFrame());
        //scrollUpper = new JScrollPane(upper);
        //actionsBattle = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollUpper, new JScrollPane(lower));
        //actionsBattle.setDividerLocation(256);
        //actionsBattle.removeAll();
        //actionsBattle.setLayout(new BoxLayout(actionsBattle, BoxLayout.PAGE_AXIS));
//        grid = new GridBagLayout();
//        setLayout(grid);
        //add(new JScrollPane(actionsBattle));
        addWindowListener(new ClosingChildFrameEvent(this));
//        setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
    }

    @Override
    public void closeWindow() {
        setVisible(false);
    }
    public void setDialogIcon(AbsCommonFrame _group) {
        setIconImage(_group.getImageIconFrame());
        setImageIconFrame(_group.getImageIconFrame());
    }
    public void initMessages() {
        String lg_ = window.getLanguageKey();
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, lg_, BATTLE);
    }

    public void setMessages() {
        setTitle(messages.getVal(CST_ACTIONS));
        setPanelTitle(fighterFrontPanel, FRONT_TEAM);
        setPanelTitle(fighterBackPanel, BACK_TEAM);
        setPanelTitle(fighterBackPanelSub, BACK_TEAM_SUB);
        setPanelTitle(fighterPanel, TEAM_CST);
        setPanelTitle(fighterFleePanel, TEAM_CST);
        setPanelTitle(fighterCaughtPanel, TEAM_CST);
        setPanelTitle(fighterCaughtNicknamePanel, TEAM_CST);
        setPanelTitle(fighterCatchingPanel, TEAM_CST);
        if (pokemonPanel !=null) {
            pokemonPanel.setPanelTitle(messages.getVal(EVOS));
            pokemonPanel.setNoEvoMessage(messages.getVal(NO_EVO));
        }
        if (ballPanel !=null) {
            ballPanel.setPanelTitle(messages.getVal(BALLS));
        }
        LanguageDialogButtons.translate(webLabel, messages, FIGHT_DATA_MESSAGE);
//        if (web != null) {
//            web.setText(messages.getVal(DATA_FIGHT));
//        }
        setText(catchBall, CATCH_PK);
        setText(catchBallEnd, CATCH_PK);
        if (flee != null) {
            if (facade.isWildFight()) {
                IdMap<UsefulValueLaw, Rate> r_ = facade.calculateFleeingRate();
                flee.setText(StringUtil.simpleStringsFormat(messages.getVal(CST_FLEE),
                        r_.getVal(UsefulValueLaw.MINI).toNumberString(), r_.getVal(UsefulValueLaw.MINI).percent().toNumberString(),
                        r_.getVal(UsefulValueLaw.MAXI).toNumberString(), r_.getVal(UsefulValueLaw.MAXI).percent().toNumberString(),
                        r_.getVal(UsefulValueLaw.MOY).toNumberString(), r_.getVal(UsefulValueLaw.MOY).percent().toNumberString(),
                        r_.getVal(UsefulValueLaw.VAR).toNumberString(), r_.getVal(UsefulValueLaw.VAR).percent().toNumberString()));
            }
        }
        if (validateActions != null && facade.isExistingFight() && !FightFacade.possibleCatch(facade.getFight())) {
            if (facade.getFight().getState() == FightState.ATTAQUES) {
                validateActions.setText(messages.getVal(GO_TO_ROUND));
            } else if (facade.getFight().getState() == FightState.SWITCH_APRES_ATTAQUE) {
                validateActions.setText(messages.getVal(GO_TO_ROUND));
            } else if (facade.getFight().getState() == FightState.APPRENDRE_EVOLUER) {
                validateActions.setText(messages.getVal(VALIDATE_EVOS));
            } else if (facade.getFight().getState() == FightState.SWITCH_PROPOSE) {
                validateActions.setText(messages.getVal(VALIDATE_SWITCH));
            } else {
                validateActions.setText(messages.getVal(GO_TO_ROUND));
            }
//            if (facade.getFight().getState() != FightState.SURNOM) {
//                if (facade.getFight().getState() != FightState.CAPTURE_KO) {
//                    if (facade.getFight().getState() == FightState.ATTAQUES) {
//                        validateActions.setText(messages.getVal(GO_TO_ROUND));
//                    } else if (facade.getFight().getState() == FightState.SWITCH_APRES_ATTAQUE) {
//                        validateActions.setText(messages.getVal(GO_TO_ROUND));
//                    } else if (facade.getFight().getState() == FightState.APPRENDRE_EVOLUER) {
//                        validateActions.setText(messages.getVal(VALIDATE_EVOS));
//                    } else if (facade.getFight().getState() == FightState.SWITCH_PROPOSE) {
//                        validateActions.setText(messages.getVal(VALIDATE_SWITCH));
//                    } else {
//                        validateActions.setText(messages.getVal(GO_TO_ROUND));
//                    }
//                }
//            }
        }
//        if (nicknameLabel != null) {
//            nicknameLabel.setText(messages.getVal(NICKNAME_CST));
//        }
        setText(cancelCatch, CANCEL_CATCH);
        if (plLabelBack != null) {
            String gb_ = messages.getVal(GO_BACK);
            plLabelBack.setText(gb_);
            plLabelBack.setPreferredSize(new MetaDimension(window.getCompoFactory().stringWidth(plLabelBack.getMetaFont(),gb_), plLabelBack.getMetaFont().getRealSize()));
            AbsMetaLabelPk.paintPk(window.getImageFactory(), plLabelBack);
        }
        frontBattle.translate();
        LanguageDialogButtons.translate(errorLabel, messages, ERRORS);
        LanguageDialogButtons.translate(roundLabel, messages, ROUND);
        window.pack();
    }

    private void setText(AbsButton _field, String _key) {
        LanguageDialogButtons.translate(_field, messages, _key);
    }

    private void setPanelTitle(FighterPanel _field, String _key) {
        if (_field !=null) {
            _field.setPanelTitle(messages.getVal(_key));
        }
    }

    public void initializeFight(boolean _display) {
        refresh();
//        heightUpper = frontBattle.getPreferredSize().height;
//        splitter.setDividerLocation(heightUpper);
//        splitter.setDividerLocation(150);
        if (_display) {
            display();
        }
    }

    public void display() {
        pack();
        setVisible(true);
    }

    private void refresh() {
//        enabledClicked = true;
        //removeAll();
        initComments();
        initCommentsErrors();
        facade.deselect();
//        actionsBattle.removeAll();
//        UPPER.removeAll();
        lower.removeAll();
        frontBattle.setTargets();
//        GridBagConstraints c_ = new GridBagConstraints();
//        grid.setConstraints(frontBattle, c_);
//        UPPER.add(frontBattle);
//        actionsBattle.add(new JScrollPane(upper));
//        actionsBattle.setLeftComponent(new JScrollPane(upper));
        initializeComponents();
        fleeWeb.removeAll();
//        c_ = new GridBagConstraints();
//        c_.gridwidth = GridBagConstraints.REMAINDER;
//        grid.setConstraints(fleeWeb, c_);
        if (!facade.getFight().getFightType().isExisting()) {
            validateActions = window.getCompoFactory().newPlainButton(WindowAiki.OK);
            validateActions.addActionListener(new PkNonModalEvent(window.getModal()),new EndFightEvent(this));
            //fleeWeb.add(validateActions);
            //upper.add(fleeWeb);
            //Panel forms_ = new Panel();
//            c_ = new GridBagConstraints();
//            c_.gridwidth = GridBagConstraints.REMAINDER;
//            grid.setConstraints(forms_, c_);
            lower.add(validateActions);
            lower.add(commentsRoundScroll);
        } else if (facade.getFight().getState() == FightState.ATTAQUES) {
            fleeWeb.add(webLabel);
//            fleeWeb.add(web);
            addBalls();
            addCatching();
            validateActions = window.getCompoFactory().newPlainButton();
            validateActions.addActionListener(new PkNonModalEvent(window.getModal()),new RoundAllThrowersEvent(this));
            fleeWeb.add(validateActions);
            addFlee();
            initRates();
//            UPPER.add(fleeWeb);
            forms.removeAll();
            team.removeAll();
            fighterFrontPanel.initFighters(facade.getPlayerFrontTeam());
            team.add(fighterFrontPanel.getContainer());
            fighterBackPanel.initFighters(facade.getPlayerBackTeam());
            team.add(fighterBackPanel.getContainer());
            actionType.removeAll();
//            team_.add(actionType);//removed
            forms.add(team);
            actions.removeAll();
            actions.add(actionType);
            forms.add(actions);
//            forms_.add(new JScrollPane(commentsErrors));
//            c_ = new GridBagConstraints();
//            c_.gridwidth = GridBagConstraints.REMAINDER;
//            grid.setConstraints(forms_, c_);
            lower.add(forms);
            lower.add(fleeWeb);
            AbsPanel comments_ = initCommentsPanel();
//            LOWER.add(new JScrollPane(commentsRound));
            lower.add(comments_);
//            actionsBattle.add(new JScrollPane(lower));
//            actionsBattle.setRightComponent(new JScrollPane(lower));
        } else if (FightFacade.possibleCatch(facade.getFight())) {
//        } else if (facade.getFight().getState() == FightState.SURNOM || facade.getFight().getState() == FightState.CAPTURE_KO) {
            //nicknameLabel = window.getCompoFactory().newPlainButton();
            fleeWeb.add(webLabel);
//            fleeWeb.add(web);
//            fleeWeb.add(nicknameLabel);
//            fleeWeb.add(nickname);
            addBalls();
            initRates();
            fleeWeb.add(catchBallEnd);
//            fleeWeb.add(nicknameField);
//            fighterCaughtNicknamePanel.initFighters(facade.getFoeToBeCaught());
//            fighterCaughtNicknamePanel.getListe().select(0);
//            fighterCaughtNicknamePanel.getListe().revalidate();
//            fighterCaughtNicknamePanel.getListe().repaint();
//            fighterCaughtNicknamePanel.getListe().fireEvents();
//            fleeWeb.add(fighterCaughtNicknamePanel.getContainer());
//            AbsButton ok_ = window.getCompoFactory().newPlainButton(WindowAiki.OK);
//            ok_.
//            fleeWeb.add(ok_);
//            UPPER.add(fleeWeb);
            lower.add(fleeWeb);
//            actionsBattle.add(new JScrollPane(lower));
//            actionsBattle.setRightComponent(new JScrollPane(lower));
//        } else if (facade.getFight().getState() == FightState.CAPTURE_KO) {
//            //nicknameLabel = window.getCompoFactory().newPlainButton();
//            fleeWeb.add(webLabel);
////            fleeWeb.add(web);
////            fleeWeb.add(nicknameLabel);
////            fleeWeb.add(nickname);
////            fleeWeb.add(nicknameField);
//            addBalls();
//            AbsButton ok_ = window.getCompoFactory().newPlainButton(WindowAiki.OK);
//            ok_.addActionListener(new CatchKoPokemonEvent(this));
//            fleeWeb.add(ok_);
////            cancelCatch = window.getCompoFactory().newPlainButton();
////            cancelCatch.addActionListener(new CancelCatchKoPokemonEvent(this));
////            fleeWeb.add(cancelCatch);
////            fleeWeb.add(new JScrollPane(commentsErrors));
////            UPPER.add(fleeWeb);
//            lower.add(fleeWeb);
////            actionsBattle.add(new JScrollPane(lower));
////            actionsBattle.setRightComponent(new JScrollPane(lower));
        } else if (facade.getFight().getState() == FightState.APPRENDRE_EVOLUER) {
            fleeWeb.add(webLabel);
//            fleeWeb.add(web);
            validateActions = window.getCompoFactory().newPlainButton();
            validateActions.addActionListener(new PkNonModalEvent(window.getModal()),new LearnAndEvolveEvent(this));
            fleeWeb.add(validateActions);
//            UPPER.add(fleeWeb);
            forms.removeAll();
            team.removeAll();
            fighterPanel.initFighters(facade.getPlayerTeam());
            team.add(fighterPanel.getContainer());
            forms.add(team);
            actions.removeAll();
            forms.add(actions);
//            forms_.add(new JScrollPane(commentsErrors));
//            c_ = new GridBagConstraints();
//            c_.gridwidth = GridBagConstraints.REMAINDER;
//            grid.setConstraints(forms_, c_);
            lower.add(forms);
            lower.add(fleeWeb);
//            LOWER.add(new JScrollPane(commentsRound));
            AbsPanel comments_ = initCommentsPanel();
            lower.add(comments_);
//            actionsBattle.add(new JScrollPane(lower));
//            actionsBattle.setRightComponent(new JScrollPane(lower));
        } else if (facade.getFight().getState() == FightState.SWITCH_PROPOSE) {
            forms.removeAll();
            team.removeAll();
            fighterFrontPanel.initFighters(facade.getPlayerFrontTeam());
            team.add(fighterFrontPanel.getContainer());
            fighterBackPanel.initFighters(facade.getPlayerBackTeam());
            team.add(fighterBackPanel.getContainer());
            forms.add(team);
            panelPlaces.removeAll();
            placesLabels.clear();
            placesLabelsAbs.clear();
            byte mult_ = facade.getFight().getMult();
            for (byte p = IndexConstants.FIRST_INDEX; p < mult_; p++) {
                PlaceLabel plLabel_ = new PlaceLabel(Long.toString(p), p, window.getCompoFactory());
                plLabel_.addMouseListener(new PkNonModalEvent(getWindow().getModal()),new SelectPlaceEvent(this, p));
                panelPlaces.add(plLabel_.getPaintableLabel());
                placesLabels.add(plLabel_);
                placesLabelsAbs.add(plLabel_);
                AbsMetaLabelPk.paintPk(window.getImageFactory(),plLabel_);
            }
            plLabelBack = new PlaceLabel("<-",Fighter.BACK, window.getCompoFactory());
            plLabelBack.addMouseListener(new PkNonModalEvent(getWindow().getModal()),new SelectPlaceEvent(this, Fighter.BACK));
            panelPlaces.add(plLabelBack.getPaintableLabel());
            placesLabels.add(plLabelBack);
            placesLabelsAbs.add(plLabelBack);
            AbsMetaLabelPk.paintPk(window.getImageFactory(),plLabelBack);
            forms.add(panelPlaces);
//            forms_.add(new JScrollPane(commentsErrors));
//            c_ = new GridBagConstraints();
//            c_.gridwidth = GridBagConstraints.REMAINDER;
//            grid.setConstraints(forms_, c_);
            lower.add(forms);
//            LOWER.add(new JScrollPane(commentsRound));
            AbsPanel comments_ = initCommentsPanel();
            lower.add(comments_);
            //actionsBattle.add(new JScrollPane(lower));
            fleeWeb.add(webLabel);
//            fleeWeb.add(web);
            validateActions = window.getCompoFactory().newPlainButton();
            validateActions.addActionListener(new PkNonModalEvent(window.getModal()),new SendSubstitutesEvent(this));
            fleeWeb.add(validateActions);
            addFlee();
//            UPPER.add(fleeWeb);
            lower.add(fleeWeb);
//            actionsBattle.setRightComponent(new JScrollPane(lower));
        } else if (facade.getFight().getState() == FightState.SWITCH_APRES_ATTAQUE) {
            fleeWeb.add(webLabel);
            addBalls();
            addCatching();
//            fleeWeb.add(web);
            validateActions = window.getCompoFactory().newPlainButton();
            validateActions.addActionListener(new PkNonModalEvent(window.getModal()),new RoundAllThrowersEvent(this));
            fleeWeb.add(validateActions);
            addFlee();
            initRates();
//            UPPER.add(fleeWeb);
            forms.removeAll();
            team.removeAll();
//            fighterFrontPanel.initFighters(new TreeMap<Byte,Fighter>(new));
//            team_.add(fighterFrontPanel);
            fighterBackPanel.initFighters(facade.getPlayerBackTeam());
            team.add(fighterBackPanel.getContainer());
            forms.add(team);
//            forms_.add(new JScrollPane(commentsErrors));
//            c_ = new GridBagConstraints();
//            c_.gridwidth = GridBagConstraints.REMAINDER;
//            grid.setConstraints(forms_, c_);
            lower.add(forms);
            lower.add(fleeWeb);
//            LOWER.add(new JScrollPane(commentsRound));
            AbsPanel comments_ = initCommentsPanel();
            lower.add(comments_);
//            actionsBattle.add(new JScrollPane(lower));
//            actionsBattle.setRightComponent(new JScrollPane(lower));
        } else {
            fleeWeb.add(webLabel);
//            fleeWeb.add(web);
            validateActions = window.getCompoFactory().newPlainButton();
            validateActions.addActionListener(new PkNonModalEvent(window.getModal()),new RoundWhileKoPlayerEvent(this));
            fleeWeb.add(validateActions);
//            UPPER.add(fleeWeb);
            lower.add(fleeWeb);
//            actionsBattle.add(new JScrollPane(lower));
//            actionsBattle.setRightComponent(new JScrollPane(lower));
        }
        setMessages();
        //add(actionsBattle);
        enabledChangeLanguage = true;
    }

    public void endFight() {
        window.drawGameWalking(false);
        window.pack();
    }

    public void roundAllThrowers() {
        enabledChangeLanguage = false;
//        if (!enabledClicked) {
//            return;
//        }
//        enabledClicked = false;
        facade.roundAllThrowers(enableAnimation);
        if (facade.isErrorFight()) {
//            enabledClicked = true;
            commentsErrors.setText(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE));
            return;
        }
        window.setSavedGame(false);
        commentsErrors.setText(DataBase.EMPTY_STRING);
        if (enableAnimation) {
            removeInteractiveComponents();
            window.pack();
            commentsRound.setText(DataBase.EMPTY_STRING);
            window.disableBasicFight();
            roundThread = new RoundBasicThread(facade, this);
            window.getThreadFactory().newStartedThread(roundThread);
        } else {
            afterRoundWithoutAnimation();
        }
    }

    public void sendSubstitutes() {
        enabledChangeLanguage = false;
//        if (!enabledClicked) {
//            return;
//        }
//        enabledClicked = false;
        facade.sendSubstitutes();
        if (facade.isErrorFight()) {
//            enabledClicked = true;
            commentsErrors.setText(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE));
            return;
        }
        window.setSavedGame(false);
        commentsErrors.setText(DataBase.EMPTY_STRING);
        afterRoundWithoutAnimation();
    }

    public void learnAndEvolve() {
        enabledChangeLanguage = false;
//        if (!enabledClicked) {
//            return;
//        }
//        enabledClicked = false;
        facade.learnAndEvolve();
        if (facade.isErrorFight()) {
//            enabledClicked = true;
            commentsErrors.setText(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE));
            return;
        }
        window.setSavedGame(false);
        commentsErrors.setText(DataBase.EMPTY_STRING);
        afterRoundWithoutAnimation();
    }

    public void catchKoPokemon() {
//        BallNumberRate ball_ = ballPanel.getSelectedBall();
//        if (ball_ == null) {
//            return;
//        }
        window.setSavedGame(false);
        facade.catchWildPokemon();
        afterRoundDirect();
//        enabledChangeLanguage = false;
//        if (window.getComment().isEmpty()) {
//            closeWindows();
//            window.drawGameWalking(false);
//            window.pack();
//        } else {
//            commentsRound.setText(window.getComment());
//            refresh();
//            pack();
//        }
    }

//    public void cancelCatchKoPokemon() {
//        window.setSavedGame(false);
//        facade.notCatchKoWildPokemon();
//        afterRoundDirect();
//    }

    private void afterRoundDirect() {
        afterRoundDirect(false);
//        enabledChangeLanguage = false;
//        if (window.getComment().isEmpty()) {
//            closeWindows();
//            window.drawGameWalking(false);
//            window.pack();
//        } else {
//            commentsRound.setText(window.getComment());
//            refresh();
//            pack();
//            setVisible(true);
//        }
    }
    private void afterPossibleRoundDirectNoLg() {
        if (!facade.isExistingFight()) {
            afterRoundDirectNoLg();
        } else {
            refresh();
            setVisible(true);
            pack();
        }
    }
    private void afterRoundDirectNoLg() {
        afterRoundDirect(enabledChangeLanguage);
    }
    private void afterRoundDirect(boolean _en) {
        enabledChangeLanguage = _en;
        if (window.getComment().isEmpty()) {
            closeWindows();
            window.drawGameWalking(false);
            window.pack();
        } else {
            commentsRound.setText(window.getComment());
            refresh();
            pack();
            setVisible(true);
        }
    }

    public void roundWhileKoPlayer() {
//        if (!enabledClicked) {
//            return;
//        }
//        enabledClicked = false;
        window.setSavedGame(false);
        facade.roundWhileKoPlayer(enableAnimation);
        enabledChangeLanguage = false;
        if (enableAnimation) {
            removeInteractiveComponents();
            window.pack();
            commentsRound.setText(DataBase.EMPTY_STRING);
            window.disableBasicFight();
            roundThread = new RoundKoUserThread(facade, this);
            window.getThreadFactory().newStartedThread(roundThread);
        } else {
            afterRoundWithoutAnimation();
        }
    }

    private void afterRoundWithoutAnimation() {
        if (!facade.isExistingFight()) {
            afterRoundDirectNoLg();
        } else {
            refresh();
            commentsRound.setText(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE));
            pack();
        }
    }

    private AbsPanel initCommentsPanel() {
        comments.removeAll();
        comments.add(errorLabel);
        comments.add(commentsErrorsScroll);
        comments.add(roundLabel);
        comments.add(commentsRoundScroll);
        return comments;
    }

    private void removeInteractiveComponents() {
        setVisible(false);
        //removeAll();
//        actionsBattle.removeAll();
        //upper.removeAll();
        lower.removeAll();
//        actionsBattle.add(new JScrollPane(upper));
//        actionsBattle.add(new JScrollPane(lower));
//        actionsBattle.setLeftComponent(new JScrollPane(upper));
//        actionsBattle.setRightComponent(new JScrollPane(lower));
        facade.deselect();
        frontBattle.setTargets();
//        GridBagConstraints c_ = new GridBagConstraints();
//        grid.setConstraints(frontBattle, c_);
        //upper.add(frontBattle);
        forms.removeAll();
        forms.add(commentsErrorsScroll);
//        c_ = new GridBagConstraints();
//        c_.gridwidth = GridBagConstraints.REMAINDER;
//        grid.setConstraints(forms_, c_);
        lower.add(forms);
        lower.add(commentsRoundScroll);
        //add(actionsBattle);
    }

    public void setComments() {
        commentsRound.setText(StringUtil.concat(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE),RETURN_LINE));
        window.setNoPaintingScene();
    }

    public void appendComments() {
        commentsRound.append(StringUtil.concat(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE),RETURN_LINE));
    }

    public void afterRound() {
        if (!facade.isExistingFight()) {
            closeWindows();
            afterRoundDirectNoLg();
//            if (window.getComment().isEmpty()) {
//                window.drawGameWalking(false);
//                window.pack();
//            } else {
//                commentsRound.setText(window.getComment());
//                refresh();
//                pack();
//                setVisible(true);
////                window.pack();
//            }
        } else {
            refresh();
            appendComments();
            pack();
            setVisible(true);
//            if (_comment) {
//                appendComments();
//            }
//            window.pack();
        }
        window.reenableBasicFight();
//        CustComponent.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                if (!facade.isExistingFight()) {
//                    closeWindows();
//                    if (window.getComment().isEmpty()) {
//                        window.drawGameWalking(false);
//                        window.pack();
//                    } else {
//                        commentsRound.setText(window.getComment());
//                        refresh();
//                    }
//                } else {
//                    refresh();
//                    appendComments();
////                    if (_comment) {
////                        appendComments();
////                    }
//                    window.pack();
//                }
//            }
//        });
//        if (!facade.isExistingFight()) {
//            closeWindows();
//            CustComponent.invokeLater(new Runnable() {
//                @Override
//                public void run() {
//                    //window.addToArea();
//                    if (window.getComment().isEmpty()) {
//                        window.drawGameWalking(false);
//                        window.pack();
//                    } else {
//                        commentsRound.setText(window.getComment());
//                        refresh();
//                    }
//                }
//            });
//        } else {
//            refresh();
//            if (_comment) {
//                appendComments();
//            }
//            PackingWindowAfter.pack(window);
//        }
    }

    private void addBalls() {
        if (facade.isWildFight()) {
            fleeWeb.add(nicknameField);
            fighterCaughtNicknamePanel.initFighters(facade.getFoeToBeCaught());
            fighterCaughtNicknamePanel.getListe().select(0);
            fighterCaughtNicknamePanel.getListe().revalidate();
            fighterCaughtNicknamePanel.getListe().repaint();
            fighterCaughtNicknamePanel.getListe().fireEvents();
            fleeWeb.add(fighterCaughtNicknamePanel.getContainer());
            fighterCaughtPanel.initFighters(facade.getFoeToBeCaught(false));
            fighterCaughtPanel.getListe().select(0);
            fighterCaughtPanel.getListe().revalidate();
            fighterCaughtPanel.getListe().repaint();
            fighterCaughtPanel.getListe().fireEvents();
            fleeWeb.add(fighterCaughtPanel.getContainer());
            int selectedIndex_ = fighterCaughtPanel.getListe().getSelectedIndex();
            if (selectedIndex_ > -1) {
                byte creatureSauvage_ = facade.getSingleFoeToBeCaught(false, selectedIndex_).getFirstPosit();
                ballPanel.initBalls(creatureSauvage_, -1);
            } else {
                ballPanel.initBalls(-1, -1);
            }
            ballPanel.setListener(this);
//            GridBagConstraints c_ = new GridBagConstraints();
//            grid.setConstraints(ballPanel, c_);
            fleeWeb.add(ballPanel.getContainer());
            if (FightFacade.possibleCatch(facade.getFight())) {
//            if (facade.getFight().getState() == FightState.CAPTURE_KO || facade.getFight().getState() == FightState.SURNOM)
                ballPanel.getListeBall().select(new Ints());
                ballPanel.getListeBall().revalidate();
                ballPanel.getListeBall().repaint();
                ballPanel.getListeBall().fireEvents();
                return;
            }
            byte creatureSauvage_ = facade.getSingleFoeToBeCaught(false, selectedIndex_).getFirstPosit();
            fighterCatchingPanel.initFighters(facade.getPlayerToCatch());
            fighterCatchingPanel.getListe().select(0);
            fighterCatchingPanel.getListe().revalidate();
            fighterCatchingPanel.getListe().repaint();
            fighterCatchingPanel.getListe().fireEvents();
            fleeWeb.add(fighterCatchingPanel.getContainer());
            byte creatureUt_ = facade.getSinglePlayerToCatch(fighterCatchingPanel.getListe().getSelectedIndex()).getFirstPosit();
            ballPanel.initBalls(creatureSauvage_, creatureUt_);
            ballPanel.getListeBall().select(0);
            ballPanel.getListeBall().computeDimensions();
            ballPanel.getListeBall().revalidate();
            ballPanel.getListeBall().repaint();
            ballPanel.getListeBall().fireEvents();
        }
    }

    private void addCatching() {
        if (facade.isWildFight()) {
            fleeWeb.add(catchBall);
        }
    }

    private void addFlee() {
        if (facade.isWildFight()) {
            fighterFleePanel.initFighters(facade.getPlayerTeam());
            fighterFleePanel.getListe().select(facade.getGame().getFight().getCurrentUserFlee());
            fighterFleePanel.getListe().revalidate();
            fighterFleePanel.getListe().repaint();
            fighterFleePanel.getListe().fireEvents();
            fleeWeb.add(fighterFleePanel.getContainer());
            fleeWeb.add(flee);
        }
    }

    private void initializeComponents() {
        if (errorLabel == null) {
            errorLabel = window.getCompoFactory().newPlainLabel("");
        }
        if (roundLabel == null) {
            roundLabel = window.getCompoFactory().newPlainLabel("");
        }
        if (flee == null) {
            flee = window.getCompoFactory().newPlainButton();
            flee.addActionListener(new PkNonModalEvent(window.getModal()),new FleeEvent(this));
        }
//        if (web == null) {
//            web = window.getCompoFactory().newPlainButton();
//            web.addActionListener(new ShowFightDataEvent(this));
//        }
        if (webLabel == null) {
            webLabel = window.getCompoFactory().newPlainLabel("");
        }
//        if (nicknameLabel == null) {
//            nicknameLabel = window.getCompoFactory().newPlainButton();
//            nicknameLabel.addActionListener(new NicknameEvent(this));
//        }
        if (fleeWeb == null) {
            fleeWeb = window.getCompoFactory().newPageBox();
        }
        if (actionType == null) {
            actionType = window.getCompoFactory().newPageBox();
        }
        if (panelPlaces == null) {
            panelPlaces = window.getCompoFactory().newPageBox();
        }
        if (actions == null) {
            actions = window.getCompoFactory().newPageBox();
        }
        if (targets == null) {
            targets = new TargetsPanel(window.getCompoFactory());
        }
        if (fighterFrontPanel == null) {
            fighterFrontPanel = new FighterPanel(window, facade.getFight().getMult(), DataBase.EMPTY_STRING, facade, facade.getPlayerFrontTeam());
            fighterFrontPanel.addListener(this, true, false);
        }
        if (fighterBackPanel == null) {
            fighterBackPanel = new FighterPanel(window, 2, DataBase.EMPTY_STRING, facade, facade.getPlayerBackTeam());
            fighterBackPanel.addListener(this, false, false);
        }
        if (fighterBackPanelSub == null) {
            fighterBackPanelSub = new FighterPanel(window, 2, DataBase.EMPTY_STRING, facade, facade.getPlayerBackTeam());
            fighterBackPanelSub.addListener(this, false, true);
        }
        if (fighterPanel == null) {
            fighterPanel = new FighterPanel(window, 2, DataBase.EMPTY_STRING, facade, facade.getPlayerTeam());
            fighterPanel.addListener(this);
        }
        if (fighterFleePanel == null) {
            fighterFleePanel = new FighterPanel(window, 2, DataBase.EMPTY_STRING, facade, facade.getPlayerTeam());
            fighterFleePanel.addFleeListener(this);
        }
        if (fighterCaughtPanel == null) {
            fighterCaughtPanel = new FighterPanel(window, 2, DataBase.EMPTY_STRING, facade, facade.getFoeToBeCaught(false));
            fighterCaughtPanel.addFighterCaughtListener(this);
        }
        if (fighterCaughtNicknamePanel == null) {
            fighterCaughtNicknamePanel = new FighterPanel(window, 2, DataBase.EMPTY_STRING, facade, facade.getFoeToBeCaught());
            fighterCaughtNicknamePanel.addFighterCaughtNicknameListener(this);
            nicknameField.addAutoComplete(new FightNicknameAutoCompleteListener(nicknameField,fighterCaughtNicknamePanel,facade));
        }
        if (fighterCatchingPanel == null) {
            fighterCatchingPanel = new FighterPanel(window, 2, DataBase.EMPTY_STRING, facade, facade.getPlayerToCatch());
            fighterCatchingPanel.addFighterCatchingListener(this);
        }
        if (pokemonPanel == null) {
            pokemonPanel = new PokemonPanel(window,2, DataBase.EMPTY_STRING, facade, messages.getVal(NO_EVO));
            pokemonPanel.addListener(this);
        }
        if (movesLearnPanel == null) {
            movesLearnPanel = window.getCompoFactory().newPageBox();
            movesLearnPanelScroll = getFrames().getCompoFactory().newAbsScrollPane(movesLearnPanel);
            movesLearnPanelScroll.setPreferredSize(new MetaDimension(150,150));
        }
        if (abilitiesLearnPanel == null) {
            abilitiesLearnPanel = window.getCompoFactory().newPageBox();
        }
        if (ballPanel == null) {
            ballPanel = new BallPanel(window, 5, DataBase.EMPTY_STRING, facade);
        }
        if (catchBall == null) {
            catchBall = window.getCompoFactory().newPlainButton();
            catchBall.addActionListener(new PkNonModalEvent(window.getModal()),new CatchNotKoPokemonEvent(this));
        }
        if (catchBallEnd == null) {
            catchBallEnd = window.getCompoFactory().newPlainButton();
            catchBallEnd.addActionListener(new PkNonModalEvent(window.getModal()),new CatchKoPokemonEvent(this));
        }
    }

    public void flee() {
//        if (!enabledClicked) {
//            return;
//        }
//        enabledClicked = false;
        facade.attemptFlee(enableAnimation);
        window.setSavedGame(false);
        enabledChangeLanguage = false;
        if (enableAnimation) {
            removeInteractiveComponents();
            window.pack();
            commentsRound.setText(DataBase.EMPTY_STRING);
            window.disableBasicFight();
            roundThread = new RoundFleeThread(facade, this);
            window.getThreadFactory().newStartedThread(roundThread);
        } else {
            afterPossibleRoundDirectNoLg();
//            if (!facade.isExistingFight()) {
//                afterRoundDirectNoLg();
////                if (window.getComment().isEmpty()) {
////                    closeWindows();
////                    window.drawGameWalking(false);
////                    window.pack();
////                } else {
////                    commentsRound.setText(window.getComment());
////                    refresh();
//////                    setVisible(true);
////                    pack();
//////                    window.pack();
////                }
////                closeWindows();
////                window.addToArea();
////                window.drawGameWalking(false);
////                window.pack();
//            } else {
//                refresh();
//                setVisible(true);
//                pack();
////                window.pack();
//            }
        }
    }

//    public void nicknamePokemon() {
//        if (!enabledClicked) {
//            return;
//        }
////        ConfirmDialog dial_ = new ConfirmDialog(window, typedNickname, _messages_.getVal(NICKNAME), _messages_.getVal(NICKNAME), Constants.getLanguage());
//        TextAnswerValue confirmDialog_ = window.getConfirmDialogText().input(window.getCommonFrame(), typedNickname, messages.getVal(NICKNAME_CST), messages.getVal(NICKNAME_CST));
//        if (confirmDialog_.getAnswer() != GuiConstants.YES_OPTION) {
//            return;
//        }
//        typedNickname = confirmDialog_.getTypedText();
//        nickname.setText(typedNickname);
//    }

    public void catchNotKoPokemon() {
//        BallNumberRate ball_ = ballPanel.getSelectedBall();
//        if (ball_ == null) {
//            return;
//        }
//        if (!enabledClicked) {
//            return;
//        }
//        enabledClicked = false;
        IntMap<CatchingBallFoeAction> attempted_ = facade.attempted();
        ByteTreeMap<FighterPosition> playerFrontTeam_ = facade.getUnionFrontTeam();
        ByteTreeMap<FighterPosition> foeFrontTeam_ = facade.getFoeFrontTeam();
        facade.attemptCatchingWildPokemon(enableAnimation);
        IntMap<CatchingBallFoeAction> result_ = facade.swallow(attempted_);
        enabledChangeLanguage = false;
        window.setSavedGame(false);
        if (enableAnimation) {
            removeInteractiveComponents();
            window.pack();
            commentsRound.setText(DataBase.EMPTY_STRING);
            window.disableBasicFight();
//            if (facade.getFight().getState() != FightState.SURNOM) {
//                roundThread = new RoundFailBallThread(facade, this, ball_.getName());
//            } else {
//                roundThread = new RoundBallThread(facade, this, ball_.getName(), result_);
//            }
            roundThread = new RoundBallThread(facade, this, result_, playerFrontTeam_, foeFrontTeam_);
            window.getThreadFactory().newStartedThread(roundThread);
        } else {
            afterPossibleRoundDirectNoLg();
//            if (!facade.isExistingFight()) {
//                afterRoundDirectNoLg();
////                if (window.getComment().isEmpty()) {
////                    closeWindows();
////                    window.drawGameWalking(false);
////                    window.pack();
////                } else {
////                    commentsRound.setText(window.getComment());
////                    refresh();
////                    pack();
////                }
//            } else {
//                refresh();
//                pack();
//            }
        }
    }

    public void showFightData() {
//        if (!enabledClicked) {
//            return;
//        }
//        AbstractThread fightThread_ = window.getPreparedFightThread();
        AikiNatLgNamesNavigation fightTask_ = window.getPreparedFightTask();
//        if (fightThread_ == null || fightThread_.isAlive() || fightTask_ == null) {
//            return;
//        }
//        if (!htmlDialogs.isEmpty()) {
//            if (htmlDialogs.first().getSession().isProcessing()) {
//                return;
//            }
//            reinitWebFight(fightTask_);
//            htmlDialogs.first().setVisible(true);
//            return;
//        }
//        RenderedPage session_;
//        session_ = new RenderedPage(getFrames().getCompoFactory().newAbsScrollPane(), window.getFrames(),new FixCharacterCaseConverter());
//        session_.setProcess(window.getVideoLoading().getVideo(window.getGenerator(),window.getFileCoreStream(),window.getFrames()));
//        FrameHtmlData dialog_ = new FrameHtmlData(window, window.getDataBattle());
        renderDataFight.setTitle(messages.getVal(TITLE));
//        dialog_.setTitle(messages.getVal(TITLE));
        renderDataFight.initSessionLg(facade,fightTask_,facade.getLanguage());
        renderDataFight.pack();
//        dialog_.initSessionLg(facade,fightTask_,facade.getLanguage());
//        htmlDialogs.add(dialog_);
    }
//
//    private void reinitWebFight(AikiNatLgNamesNavigation _task) {
//        htmlDialogs.first().setTitle(messages.getVal(TITLE));
//        htmlDialogs.first().initSessionLg(facade,_task,facade.getLanguage());
//        htmlDialogs.first().pack();
//    }

    public void refreshSession() {
        renderDataFight.setTitle(messages.getVal(TITLE));
        renderDataFight.refresh(window);
//        for (FrameHtmlData f: htmlDialogs) {
//            f.setTitle(messages.getVal(TITLE));
//            if (!f.getCommonFrame().isVisible()) {
//                continue;
//            }
//            f.refresh(window);
//            f.pack();
//        }
    }

    public void resetWindows() {
        if (facade.isChangeToFightScene()) {
            renderDataFight.setTitle(messages.getVal(TITLE));
//        dialog_.setTitle(messages.getVal(TITLE));
            AikiNatLgNamesNavigation fightTask_ = window.getPreparedFightTask();
            renderDataFight.initSessionLg(facade,fightTask_,facade.getLanguage());
            renderDataFight.pack();
//            if (!htmlDialogs.isEmpty()) {
//                if (htmlDialogs.first().getCommonFrame().isVisible()) {
////                    AbstractThread fightThread_ = window.getPreparedFightThread();
//                    AikiNatLgNamesNavigation fightTask_ = window.getPreparedFightTask();
////                    if (fightThread_ == null || fightThread_.isAlive() || fightTask_ == null) {
////                        return;
////                    }
////                    if (htmlDialogs.first().getSession().isProcessing()) {
////                        return;
////                    }
//                    reinitWebFight(fightTask_);
//                }
//            }
        } else {
            closeWindows();
        }
    }

    public void closeWindows() {
        renderDataFight.closeWindow();
//        if (!htmlDialogs.isEmpty()) {
//            htmlDialogs.first().setVisible(false);
//        }
    }

//    public void clearHtmlDialogs() {
//        htmlDialogs.clear();
//    }

    public void choosePlayerTarget(byte _number, MiniTargetLabel _index) {
        window.setSavedGame(false);
        facade.setFirstChosenMovePlayerTarget(_number);
        targets.repaintLabel(_index,this);
    }

    public void chooseFoeTarget(byte _number, MiniTargetLabel _index) {
        window.setSavedGame(false);
        facade.setFirstChosenMoveFoeTarget(_number);
        targets.repaintLabel(_index,this);
    }

    public void chooseFrontFighter() {
//        if (!enableClick) {
//            return;
//        }
//        enableClick = false;
        enabledChangeLanguage = false;
        facade.chooseFrontFighter((byte) fighterFrontPanel.getSelectedIndex());
        fighterBackPanel.deselect();
        afterSelectFighter();
    }

    public void chooseBackFighter() {
//        if (!enableClick) {
//            return;
//        }
//        enableClick = false;
        enabledChangeLanguage = false;
        facade.chooseBackFighter((byte) fighterBackPanel.getSelectedIndex());
        fighterFrontPanel.deselect();
        afterSelectFighter();
    }

    private void afterSelectFighter() {
        if (facade.getFight().getState() == FightState.ATTAQUES) {
            displayActionsBeforeRound();
        } else {
            for (PlaceLabel p: placesLabels) {
                p.setSelected(facade.getFight().getTemp().getChosenSubstitute());
            }
            AbsMetaLabelPk.repaintChildren(placesLabelsAbs, window.getImageFactory());
        }
//        enableClick = true;
    }

    public void chooseBackFighterSub() {
        enabledChangeLanguage = false;
        facade.chooseSubstituteFighter((byte) fighterBackPanelSub.getSelectedIndex());
        window.setSavedGame(false);
    }

    public void chooseFighterCaught() {
        if (fighterCaughtPanel.getSelectedIndex() < 0) {
            return;
        }
        enabledChangeLanguage = false;
        boolean caught_ = facade.getFight().getState() == FightState.SURNOM;
        FighterPosition fp_ = facade.getSingleFoeToBeCaught(caught_, fighterCaughtPanel.getSelectedIndex());
        CatchingBallFoeAction cat_ = facade.getGame().getFight().getCatchingBalls().get(fp_.getFirstPosit());
        if (caught_) {
            return;
        }
        CustList<String> names_ = new CustList<String>();
        for (BallNumberRate b: ballPanel.getListeBall().getList()) {
            names_.add(b.getName());
        }
        ballPanel.getListeBall().select(StringUtil.indexOf(names_,cat_.getCatchingBall()));
        ballPanel.getListeBall().revalidate();
        ballPanel.getListeBall().repaint();
        ballPanel.getListeBall().fireEvents();
        Bytes catching_ = new Bytes();
        for (FighterPosition b: fighterCatchingPanel.getListe().getList()) {
            catching_.add(b.getFirstPosit());
        }
        fighterCatchingPanel.getListe().select(catching_.indexOfNb(cat_.getPlayer()));
        fighterCatchingPanel.getListe().revalidate();
        fighterCatchingPanel.getListe().repaint();
        fighterCatchingPanel.getListe().fireEvents();
    }

    public void chooseFighterCaughtNickname() {
        if (fighterCaughtNicknamePanel.getSelectedIndex() < 0) {
            return;
        }
        enabledChangeLanguage = false;
        FighterPosition fp_ = facade.getSingleFoeToBeCaught(fighterCaughtNicknamePanel.getSelectedIndex());
        CatchingBallFoeAction cat_ = facade.getGame().getFight().getCatchingBalls().get(fp_.getFirstPosit());
        nicknameField.setText(cat_.getNickname());
    }
    public void chooseCatchingBall() {
        BallNumberRate ball_ = ballPanel.getSelectedBall();
        if (fighterCaughtPanel.getSelectedIndex() < 0) {
            return;
        }
        enabledChangeLanguage = false;
        FighterPosition fp_ = facade.getSingleFoeToBeCaught(false, fighterCaughtPanel.getSelectedIndex());
        CatchingBallFoeAction cat_ = facade.getGame().getFight().getCatchingBalls().get(fp_.getFirstPosit());
        if (ball_ == null) {
            cat_.setCatchingBall(DataBase.EMPTY_STRING);
        } else {
            cat_.setCatchingBall(ball_.getName());
        }
        catchBall.setEnabled(facade.enoughBall());
        catchBallEnd.setEnabled(facade.enoughBall());
        refreshRates();
    }

    public void initRates() {
        if (facade.isWildFight()) {
            catchBallResult.clear();
            CustList<CatchingBallFoeAction> catchingBalls_ = facade.getFight().getCatchingBalls();
            int s_ = catchingBalls_.size();
            for (int i = 0; i < s_; i++) {
                AbsPlainLabel lab_ = window.getCompoFactory().newPlainLabel(facade.getFight().getFighter(Fight.toFoeFighter((byte) i)).getName());
                catchBallResult.add(lab_);
                fleeWeb.add(lab_);
            }
            refreshRates();
        }
    }

    private void refreshRates() {
        CustList<CatchingBallFoeAction> catchingBalls_ = facade.getFight().getCatchingBalls();
        IntMap<BallNumberRatePk> result_ = facade.calculateCatchingRatesSum();
        int s_ = catchingBalls_.size();
        for (int i = 0; i < s_; i++) {
            if (!catchBallResult.isValidIndex(i)) {
                continue;
            }
            BallNumberRatePk res_ = result_.getVal(i);
            if (res_ == null) {
                catchBallResult.get(i).setText(facade.getFight().getFighter(Fight.toFoeFighter((byte) i)).getName());
            } else {
                String pk_ = facade.translatePokemon(res_.getNamePk());
                String it_ = facade.translateItem(res_.getName());
                String rate_ = res_.getRate().toNumberString();
                String percent_ = res_.getPercent();
                catchBallResult.get(i).setText(pk_+" "+it_+" "+rate_+" "+percent_+"%");
            }
        }
    }

    public void chooseFighterCatching() {
        if (fighterCaughtPanel.getSelectedIndex() < 0) {
            return;
        }
        if (fighterCatchingPanel.getSelectedIndex() < 0) {
            return;
        }
        enabledChangeLanguage = false;
        FighterPosition fp_ = facade.getSingleFoeToBeCaught(false, fighterCaughtPanel.getSelectedIndex());
        CatchingBallFoeAction cat_ = facade.getGame().getFight().getCatchingBalls().get(fp_.getFirstPosit());
        cat_.setPlayer(facade.getSinglePlayerToCatch(fighterCatchingPanel.getSelectedIndex()).getFirstPosit());
    }
    public void chooseFighterFlee() {
        enabledChangeLanguage = false;
        if (fighterFleePanel.getSelectedIndex() < 0) {
            facade.getGame().getFight().setCurrentUserFlee(Fighter.BACK);
        } else {
            facade.getGame().getFight().setCurrentUserFlee((byte) fighterFleePanel.getSelectedIndex());
        }
        if (facade.getGame().getFight().getCurrentUserFlee() != Fighter.BACK) {
            flee.setEnabled(true);
            IdMap<UsefulValueLaw, Rate> r_ = facade.calculateFleeingRate();
            flee.setText(StringUtil.simpleStringsFormat(messages.getVal(CST_FLEE),
                    r_.getVal(UsefulValueLaw.MINI).toNumberString(), r_.getVal(UsefulValueLaw.MINI).percent().toNumberString(),
                    r_.getVal(UsefulValueLaw.MAXI).toNumberString(), r_.getVal(UsefulValueLaw.MAXI).percent().toNumberString(),
                    r_.getVal(UsefulValueLaw.MOY).toNumberString(), r_.getVal(UsefulValueLaw.MOY).percent().toNumberString(),
                    r_.getVal(UsefulValueLaw.VAR).toNumberString(), r_.getVal(UsefulValueLaw.VAR).percent().toNumberString()));
        } else {
            flee.setEnabled(false);
        }
    }
    public void chooseFighter() {
        enabledChangeLanguage = false;
        facade.choosePokemonForLearningAndEvolving((byte) fighterPanel.getSelectedIndex());
        if (NumberUtil.eq(facade.getChosenIndex(), IndexConstants.INDEX_NOT_FOUND_ELT)) {
            actions.removeAll();
//            window.pack();
            pack();
            return;
        }
        actions.removeAll();
        initEvos();
        initLearntMovesAbilities();
        actions.add(pokemonPanel.getContainer());
        actions.add(movesLearnPanelScroll);
        actions.add(abilitiesLearnPanel);
//        window.pack();
        pack();
    }

    private void initEvos() {
//        enableClick = false;
//        pokemonPanel.initEvos();
        pokemonPanel = new PokemonPanel(window,2, DataBase.EMPTY_STRING, facade, messages.getVal(NO_EVO));
        pokemonPanel.addListener(this);
//        enableClick = true;
    }

    private void initLearntMovesAbilities() {
        StringMap<StringMap<String>> trMoves_;
        trMoves_ = facade.getData().getTranslatedMoves();
        TreeMap<String,BoolVal> moves_ = new TreeMap<String, BoolVal>(new ComparatorTr<String>(trMoves_.getVal(window.getLanguageKey())));
        NatStringTreeMap<BoolVal> retMoves_ = facade.getMoves();
        moves_.putAllMap(retMoves_);
        movesLearnPanel.removeAll();
        movesLearnPanel.add(window.getCompoFactory().newPlainLabel(messages.getVal(SELECT_MT)));
        for (String m: moves_.getKeys()) {
            boolean learnt_ = moves_.getVal(m) == BoolVal.TRUE;
            if (!learnt_) {
                continue;
            }
            String tr_ = facade.translateMove(m);
            MoveFighterCheckBox check_ = new MoveFighterCheckBox(m,tr_,learnt_,this);
//            check_.setBackground(GuiConstants.RED);
            movesLearnPanel.add(check_.getComponent());
        }
        for (String m: moves_.getKeys()) {
            boolean learnt_ = moves_.getVal(m) == BoolVal.TRUE;
            if (learnt_) {
                continue;
            }
            String tr_ = facade.translateMove(m);
            MoveFighterCheckBox check_ = new MoveFighterCheckBox(m,tr_,learnt_,this);
//            check_.setBackground(GuiConstants.WHITE);
            movesLearnPanel.add(check_.getComponent());
        }
//        for (String m: moves_.getKeys(true)) {
//            String tr_ = facade.translateMove(m);
//            MoveFighterCheckBox check_ = new MoveFighterCheckBox(m,tr_,this);
//            check_.setBackground(Color.RED);
//            check_.setSelected(true);
//            movesLearnPanel.add(check_);
//        }
//        for (String m: moves_.getKeys(false)) {
//            String tr_ = facade.translateMove(m);
//            MoveFighterCheckBox check_ = new MoveFighterCheckBox(m,tr_,this);
//            check_.setBackground(Color.WHITE);
//            check_.setSelected(false);
//            movesLearnPanel.add(check_);
//        }
        abilitiesLearnPanel.removeAll();
        abilityLabels.clear();
        abilityLabelsAbs.clear();
        abilitiesLearnPanel.add(window.getCompoFactory().newPlainLabel(messages.getVal(SELECT_ABILITY)));
        StringList abilities_ = facade.getAbilities();
        StringList abilitiesCopy_ = new StringList(abilities_);
//        abilitiesCopy_.sort(new Comparator<String>() {
//            @Override
//            public int compare(String _o1, String _o2) {
//                String transOne_ = facade.translateAbility(_o1);
//                String transTwo_ = facade.translateAbility(_o2);
//                return transOne_.compareTo(transTwo_);
//            }
//        });
        abilitiesCopy_.sortElts(new ComparatorTr<String>(facade.getTranslatedAbilitiesCurLanguage()));
        for (String a: abilitiesCopy_) {
            AbilityLabel ab_ = new AbilityLabel(facade.translateAbility(a), a, window.getCompoFactory());
//            ab_.setSelected(facade.getAbility());
            ab_.addMouseListener(new PkNonModalEvent(getWindow().getModal()),new AbilityFightEvent(this, a));
            abilityLabels.add(ab_);
            abilityLabelsAbs.add(ab_);
            abilitiesLearnPanel.add(ab_.getPaintableLabel());
        }
        AbsMetaLabelPk.repaintChildren(abilityLabelsAbs, window.getImageFactory());
        abilitiesLearnPanel.validate();
        changeAbility(facade.getAbility());
    }

    private void displayActionsBeforeRound() {
        actionType.removeAll();
        actionType.add(window.getCompoFactory().newPlainLabel(messages.getVal(SELECT_ACTION)));
        actionsLabels.clear();
        IdList<ActionType> actions_ = facade.getFight().getTemp().getPossibleActionsCurFighter();
//        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(Resources.MESSAGES_FOLDER, window.getLanguageKey(), ACTION_TYPE);
//        String loadedResourcesMessages_ = MessPkGr.ms().getVal(fileName_);
//        StringMap<String> map_ = ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
        StringMap<String> map_ = file(window.getFrames().currentLg());
        int maxWidth_ = 0;
        for (ActionType a: actions_) {
            String txt_ = map_.getVal(a.getKeyAction());
            ActionLabel action_ = new ActionLabel(txt_, a, window.getCompoFactory());
            action_.addMouseListener(new PkNonModalEvent(getWindow().getModal()),new FighterAction(this, a));
            action_.setSelected(a == facade.getFight().getTemp().getSelectedActionCurFighter());
            actionType.add(action_.getPaintableLabel());
            maxWidth_ = NumberUtil.max(maxWidth_, action_.stringWidth(txt_));
            actionsLabels.add(action_);
        }
        for (ActionLabel a: actionsLabels) {
            a.setPreferredSize(new MetaDimension(maxWidth_,10));
        }
        changeAction(facade.getFight().getTemp().getSelectedActionCurFighter());
    }

    public static StringMap<String> file(TranslationsLg _lg) {
        return GamesPk.getFightActionContentTr(GamesPk.getAppliTr(_lg)).getMapping();
    }

    public void changeAction(ActionType _action) {
        facade.changeAction(_action);
        for (ActionLabel a: actionsLabels) {
            a.setSelected(_action);
            AbsMetaLabelPk.paintPk(window.getImageFactory(), a);
        }
        actions.removeAll();
        actions.add(actionType);
        if (_action == ActionType.HEALING) {
            displayMovesToBeHealed();
            window.setSavedGame(false);
        } else if (_action == ActionType.MOVE) {
            displayMoves();
        } else if (_action == ActionType.SWITCH) {
            fighterBackPanelSub.initFighters(facade.getPlayerBackTeam());
            fighterBackPanelSub.getListe().select(facade.getGame().getFight().getTemp().getChosenSubstitute());
            fighterBackPanelSub.getListe().revalidate();
            fighterBackPanelSub.getListe().repaint();
            fighterBackPanelSub.getListe().fireEvents();
            actions.add(fighterBackPanelSub.getContainer());
        } else {
            window.setSavedGame(false);
        }
//        window.pack();
        pack();
    }

    private void displayMovesToBeHealed() {
        actions.removeAll();
        actions.add(actionType);
        selectedItem = window.getCompoFactory().newPlainLabel(messages.getVal(NO_ITEM));
        AbsButton button_ = window.getCompoFactory().newPlainButton(messages.getVal(SELECT_ITEM));
        button_.addActionListener(new PkNonModalEvent(window.getModal()),new SelectHealingItemEvent(this));
        String str_ = facade.getFight().getTemp().getChosenHealingMove();
        if (!str_.isEmpty()) {
            String mess_ = StringUtil.simpleStringsFormat(messages.getVal(SELECTED_ITEM), str_);
            selectedItem.setText(mess_);
        }
        actions.add(button_);
        actions.add(selectedItem);
        NatStringTreeMap<ChosenMoveInfos> moves_ = facade.getFight().getTemp().getCurrentFighterMoves();
//        if (!moves_.isEmpty()) {
//            boolean wasNull_ = movesPanel == null;
//            if (wasNull_) {
//                movesPanel = window.getCompoFactory().newPageBox();
//            } else {
//                movesPanel.removeAll();
//            }
            movesPanel.removeAll();
            movesPanel.add(window.getCompoFactory().newPlainLabel(messages.getVal(SELECT_MOVE_HEAL)));
            movesLabels.clear();
            movesLabelsAbs.clear();
            for (String m: moves_.getKeys()) {
                ChosenMoveInfos info_ = moves_.getVal(m);
                MoveLabel move_ = new MoveLabel(info_, m, facade, window.getCompoFactory());
                if (info_.isUsable()) {
                    move_.addMouseListener(new PkNonModalEvent(getWindow().getModal()),new MoveEvent(this, info_.getName()));
                }
//                move_.addMouseListener(new MoveEvent(this, info_.getName()));
                move_.setSelected(facade.getFight().getTemp().getChosenMoveFront());
                AbsMetaLabelPk.paintPk(window.getImageFactory(), move_);
                movesPanel.add(move_.getPaintableLabel());
                movesLabels.add(move_);
                movesLabelsAbs.add(move_);
            }
            actions.add(movesPanel);
//            if (wasNull_) {
//                actions.add(movesPanel);
//            }
//        }
    }

    public void selectHealingItem() {
//        int lineBack_ = facade.getLineHealingItem();
        SelectHealingItem.setSelectHealingItem(window, facade, true);
//        afterSelectInBattle(lineBack_);
    }

    public void afterSelectInBattle(int _lineBack) {
        boolean isSelectedIndex_ = SelectHealingItem.isSelectedIndex(window.getSelectHealingItem());
        boolean ok_ = SelectHealingItem.isOk(window.getSelectHealingItem());
        if (!ok_) {
            facade.setLineHealingItem(_lineBack);
            facade.clearSortingHealingItem();
        } else if (isSelectedIndex_) {
            facade.setChosenHealingItem();
            facade.clearSortingHealingItem();
            window.setSavedGame(false);
            String item_ = facade.getFight().getTemp().getChosenHealingMove();
            String mess_ = StringUtil.simpleStringsFormat(messages.getVal(SELECTED_ITEM), item_);
            selectedItem.setText(mess_);
            displayMovesToBeHealed();
//            window.pack();
            pack();
            //Label selected
        } else {
            selectedItem.setText(messages.getVal(NO_ITEM));
            movesLabels.clear();
            movesLabelsAbs.clear();
//            if (movesPanel != null) {
////                actions.remove(movesPanel);
//                movesPanel.setVisible(false);
//            }
            movesPanel.setVisible(false);
            facade.clearSortingHealingItem();
//            window.pack();
            pack();
            //Label not selected
        }
    }

    private void displayMoves() {
        NatStringTreeMap<ChosenMoveInfos> moves_ = facade.getFight().getTemp().getCurrentFighterMoves();
//        if (!moves_.isEmpty()) {
            AbsPanel movesPanel_ = window.getCompoFactory().newPageBox();
            movesPanel_.add(window.getCompoFactory().newPlainLabel(messages.getVal(SELECT_MOVE_ROUND)));
            movesLabels.clear();
            movesLabelsAbs.clear();
            for (String m: moves_.getKeys()) {
                ChosenMoveInfos info_ = moves_.getVal(m);
                MoveLabel move_ = new MoveLabel(info_, m, facade, window.getCompoFactory());
                move_.setSelected(facade.getFight().getTemp().getChosenMoveFront());
                if (info_.isUsable()) {
                    move_.addMouseListener(new PkNonModalEvent(getWindow().getModal()),new MoveEvent(this, info_.getName()));
                }
                movesPanel_.add(move_.getPaintableLabel());
                movesLabels.add(move_);
                movesLabelsAbs.add(move_);
            }
            AbsMetaLabelPk.repaintChildren(movesLabelsAbs, window.getImageFactory());
            actions.add(movesPanel_);
            targetsPanel.removeAll();
            CustList<ChosableTargetName> chosablePlayer_ = facade.getFight().getTemp().getChosablePlayerTargets();
            CustList<ChosableTargetName> chosableFoe_ = facade.getFight().getTemp().getChosableFoeTargets();
            if (FightFacade.indexes(chosablePlayer_).size() + FightFacade.indexes(chosableFoe_).size() > DataBase.ONE_POSSIBLE_CHOICE) {
                targets.setTargets(facade, this);
                AbsPlainLabel header_ = window.getCompoFactory().newPlainLabel(messages.getVal(SELECT_TARGET));
                targetsPanel.add(header_, GuiConstants.BORDER_LAYOUT_NORTH);
                updateGraphics(targets.getPlayerTargets(), Fight.CST_PLAYER);
                updateGraphics(targets.getFoeTargets(), Fight.CST_FOE);
                targetsPanel.add(targets.getContainer(), GuiConstants.BORDER_LAYOUT_CENTER);
            } else if (FightFacade.requiredSwitch(facade.getFight(),facade.getData())) {
                fighterBackPanelSub.initFighters(facade.getPlayerBackTeam());
                fighterBackPanelSub.getListe().select(facade.getGame().getFight().getTemp().getChosenSubstitute());
                fighterBackPanelSub.getListe().revalidate();
                fighterBackPanelSub.getListe().repaint();
                fighterBackPanelSub.getListe().fireEvents();
                targetsPanel.add(fighterBackPanelSub.getContainer(), GuiConstants.BORDER_LAYOUT_CENTER);
            } else {
                targets.getContainer().removeAll();
                targetsPanel.add(targets.getContainer(), GuiConstants.BORDER_LAYOUT_CENTER);
            }
            actions.add(targetsPanel);
//        }
    }

    private void updateGraphics(CustList<MiniTargetLabel> _list, byte _flag) {
        for (MiniTargetLabel m: _list) {
            for (TargetCoords t: facade.getFight().getTemp().getTargetCoords()) {
                if (t.getTeam() == _flag) {
                    m.setSelectedKey(t.getPosition());
                    AbsMetaLabelPk.paintPk(window.getImageFactory(),m);
                }
            }
        }
    }

    public void changeMove(String _move) {
        facade.chooseMove(_move);
        for (MoveLabel m: movesLabels) {
            m.setSelected(_move);
            AbsMetaLabelPk.paintPk(window.getImageFactory(), m);
        }
        if (facade.getFight().getTemp().getSelectedActionCurFighter() == ActionType.MOVE) {
//            if (targetsPanel != null) {
//                actions.remove(targetsPanel);
//            }
            targetsPanel.removeAll();
            CustList<ChosableTargetName> foeTargets_ = facade.getFight().getTemp().getChosableFoeTargets();
            CustList<ChosableTargetName> plTargets_ = facade.getFight().getTemp().getChosablePlayerTargets();
            if (FightFacade.indexes(foeTargets_).size() + FightFacade.indexes(plTargets_).size() > DataBase.ONE_POSSIBLE_CHOICE) {
                targets.setTargets(facade, this);
                AbsPlainLabel header_ = window.getCompoFactory().newPlainLabel(messages.getVal(SELECT_TARGET));
                targetsPanel.add(header_, GuiConstants.BORDER_LAYOUT_NORTH);
                targetsPanel.add(targets.getContainer(), GuiConstants.BORDER_LAYOUT_CENTER);
            } else if (FightFacade.requiredSwitch(facade.getFight(),facade.getData())) {
                fighterBackPanelSub.initFighters(facade.getPlayerBackTeam());
                fighterBackPanelSub.getListe().select(facade.getGame().getFight().getTemp().getChosenSubstitute());
                fighterBackPanelSub.getListe().revalidate();
                fighterBackPanelSub.getListe().repaint();
                fighterBackPanelSub.getListe().fireEvents();
                targetsPanel.add(fighterBackPanelSub.getContainer(), GuiConstants.BORDER_LAYOUT_CENTER);
            } else {
                window.setSavedGame(false);
                targets.getContainer().removeAll();
                targetsPanel.add(targets.getContainer(), GuiConstants.BORDER_LAYOUT_CENTER);
            }
            actions.add(targetsPanel);
        } else {
//        } else if (facade.getFight().getTemp().getSelectedActionCurFighter() == ActionType.HEALING) {
            window.setSavedGame(false);
            targets.getContainer().removeAll();
        }
//        window.pack();
        pack();
    }
//
//    public boolean isEnableClick() {
//        return enableClick;
//    }
//
//    public void setEnableClick(boolean _enableClick) {
//        enableClick = _enableClick;
//    }

    public void changeFrontPlace(byte _index) {
        window.setSavedGame(false);
        facade.setSubstituteEndRound(_index);
        for (PlaceLabel p: placesLabels) {
            p.setSelected(_index);
        }
        AbsMetaLabelPk.repaintChildren(placesLabelsAbs, window.getImageFactory());
    }

    public void chooseEvolution() {
//        if (!enableClick) {
//            return;
//        }
        if (pokemonPanel.getSelectedEvo() == null) {
            movesLearnPanel.removeAll();
            abilitiesLearnPanel.removeAll();
//            window.pack();
            pack();
            return;
        }
        window.setSavedGame(false);
        facade.setEvolution(pokemonPanel.getSelectedEvo());
        initLearntMovesAbilities();
//        window.pack();
        pack();
    }

    public void addOrForgetMove(String _move) {
        facade.addOrForgetMove(_move);
        window.setSavedGame(false);
    }

    public void changeAbility(String _ability) {
        if (_ability.isEmpty()) {
            return;
        }
        window.setSavedGame(false);
        facade.setAbility(_ability);
        for (AbilityLabel a: abilityLabels) {
            a.setSelected(_ability);
        }
        AbsMetaLabelPk.repaintChildren(abilityLabelsAbs,window.getImageFactory());
//        abilitiesLearnPanel.repaintChildren(window.getImageFactory());
    }

    private void initComments() {
        if (commentsRound != null) {
            return;
        }
        commentsRound = window.getCompoFactory().newWrappedTextArea(10,32);
        commentsRound.setEditable(false);
        commentsRoundScroll = getFrames().getCompoFactory().newAbsScrollPane(commentsRound);
    }

    private void initCommentsErrors() {
        if (commentsErrors != null) {
            return;
        }
        commentsErrors = window.getCompoFactory().newWrappedTextArea(6,32);
        commentsErrors.setEditable(false);
        commentsErrors.setForeground(GuiConstants.RED);
        commentsErrorsScroll = getFrames().getCompoFactory().newAbsScrollPane(commentsErrors);
    }

//    public void initRoundAnimation() {
//        frontBattle.initRoundAnimation();
//    }

    public void setCountAnim(int _c) {
        frontBattle.setCountAnim(_c);
    }
    public void drawAnimationInstantInitial(AnimationInt _animation) {
        frontBattle.drawAnimationInstantInitial(_animation);
    }

    public void drawAnimationInstant(AnimationInt _animation) {
        frontBattle.drawAnimationInstant(window.getImageFactory(),_animation);
    }

    public void setHerosOppositeSex(AbstractImage _herosOppositeSex, boolean _paintTwoHeros) {
        frontBattle.setHerosOppositeSex(_herosOppositeSex, _paintTwoHeros);
    }

    public void drawAnimationFightIni(AbstractImage _heros, CustList<AbstractImage> _other) {
        frontBattle.drawAnimationFightIni(_heros, _other);
    }

    public void drawAnimationFightIni(AbstractImage _heros, AbstractImage _other) {
        frontBattle.drawAnimationFightIni(_heros, _other);
    }

    public void drawAnimationFightIniInst() {
        frontBattle.drawAnimationFightIniInst();
    }

//    public boolean isAliveThread() {
//        return roundThreadLau != null && roundThreadLau.isAlive();
//    }

    public boolean isKeepAnimation() {
        return frontBattle.isKeepAnimation();
    }

    public boolean isEnabledChangeLanguage() {
        return enabledChangeLanguage;
    }

    public void enableAnimation(boolean _enableAnimation) {
        enableAnimation = _enableAnimation;
    }

    public void initBall(ByteTreeMap<FighterPosition> _playerFighters,ByteTreeMap<FighterPosition> _foeFighters) {
        frontBattle.initBall(_playerFighters,_foeFighters);
    }

    public void moveBall(AbstractImageFactory _fact, int _no, String _ball, boolean _caught) {
        frontBattle.moveBall(_fact, _no, _ball, _caught);
    }

    public void setWild(boolean _wild) {
        frontBattle.setPaintBallMove(_wild);
    }

//    public CustList<FrameHtmlData> getHtmlDialogs() {
//        return htmlDialogs;
//    }

    public FrameHtmlData getRenderDataFight() {
        return renderDataFight;
    }

    public boolean openedHtmlFrames() {
        return renderDataFight.getCommonFrame().isVisible();
//        if (htmlDialogs.isEmpty()) {
//            return false;
//        }
//        return htmlDialogs.first().getCommonFrame().isVisible();
    }

    public WindowAiki getWindow() {
        return window;
    }

    public FighterPanel getFighterBackPanel() {
        return fighterBackPanel;
    }

    public FighterPanel getFighterFleePanel() {
        return fighterFleePanel;
    }

    public FighterPanel getFighterCaughtPanel() {
        return fighterCaughtPanel;
    }

    public FighterPanel getFighterCaughtNicknamePanel() {
        return fighterCaughtNicknamePanel;
    }

    public FighterPanel getFighterPanel() {
        return fighterPanel;
    }

    public FighterPanel getFighterFrontPanel() {
        return fighterFrontPanel;
    }

    public AbsTextField getNicknameField() {
        return nicknameField;
    }

    public FighterPanel getFighterCatchingPanel() {
        return fighterCatchingPanel;
    }

    public CustList<AbilityLabel> getAbilityLabels() {
        return abilityLabels;
    }

    public FighterPanel getFighterBackPanelSub() {
        return fighterBackPanelSub;
    }

    public CustList<ActionLabel> getActionsLabels() {
        return actionsLabels;
    }

    public AbsButton getCatchBall() {
        return catchBall;
    }

    public AbsButton getCatchBallEnd() {
        return catchBallEnd;
    }

    public AbsButton getValidateActions() {
        return validateActions;
    }

    public AbsButton getFlee() {
        return flee;
    }

    public AbsButton getCancelCatch() {
        return cancelCatch;
    }

    public PokemonPanel getPokemonPanel() {
        return pokemonPanel;
    }

    public BallPanel getBallPanel() {
        return ballPanel;
    }

    public CustList<PlaceLabel> getPlacesLabels() {
        return placesLabels;
    }

    public CustList<MoveLabel> getMovesLabels() {
        return movesLabels;
    }

    public TargetsPanel getTargets() {
        return targets;
    }
}
