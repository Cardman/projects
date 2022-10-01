package aiki.gui.components.fight;







import aiki.comparators.ComparatorTrStrings;
import aiki.db.DataBase;
import aiki.game.fight.*;
import aiki.gui.threads.*;
import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.game.fight.animations.AnimationInt;
import aiki.game.fight.enums.ActionType;
import aiki.game.fight.enums.FightState;
import aiki.gui.WindowAiki;
import aiki.gui.components.AbilityLabel;
import aiki.gui.components.checks.MoveFighterCheckBox;
import aiki.gui.components.fight.events.CancelCatchKoPokemonEvent;
import aiki.gui.components.fight.events.CatchKoPokemonEvent;
import aiki.gui.components.fight.events.CatchNotKoPokemonEvent;
import aiki.gui.components.fight.events.EndFightEvent;
import aiki.gui.components.fight.events.FleeEvent;
import aiki.gui.components.fight.events.LearnAndEvolveEvent;
import aiki.gui.components.fight.events.NicknameEvent;
import aiki.gui.components.fight.events.RoundAllThrowersEvent;
import aiki.gui.components.fight.events.RoundWhileKoPlayerEvent;
import aiki.gui.components.fight.events.SelectHealingItemEvent;
import aiki.gui.components.fight.events.SendSubstitutesEvent;
import aiki.gui.components.fight.events.ShowFightDataEvent;
import aiki.gui.components.fight.events.ValidateCaughtPokemonNicknameEvent;
import aiki.gui.dialogs.FrameHtmlData;
import aiki.gui.dialogs.SelectHealingItem;
import aiki.gui.listeners.AbilityFightEvent;
import aiki.gui.listeners.FighterAction;
import aiki.gui.listeners.MoveEvent;
import aiki.gui.listeners.SelectPlaceEvent;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.events.ClosingChildFrameEvent;
import code.gui.images.AbstractImage;
import code.gui.images.AbstractImageFactory;
import code.gui.images.MetaDimension;
import code.maths.Rate;
import code.scripts.messages.aiki.MessPkGr;
import code.sml.util.ResourcesMessagesUtil;
import code.threads.AbstractThread;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class Battle extends ChildFrame {

    private static final String BATTLE = "aiki.gui.components.fight.battle";
    private static final String ACTION_TYPE = "aiki.game.fight.enums.actiontype";

    private static final String RETURN_LINE = "\n";

    private static final String FRONT_TEAM = "frontTeam";

    private static final String BACK_TEAM = "backTeam";

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

    private PokemonPanel pokemonPanel;

    private AbsPanel movesLearnPanel;
    private AbsScrollPane movesLearnPanelScroll;

    private AbsPanel abilitiesLearnPanel;

    private final CustList<AbilityLabel> abilityLabels = new CustList<AbilityLabel>();

    private FighterPanel fighterFrontPanel;

    private FighterPanel fighterBackPanel;

    private AbsPanel panelPlaces;

    private final CustList<PlaceLabel> placesLabels = new CustList<PlaceLabel>();

    private BallPanel ballPanel;

    private final FacadeGame facade;

    private final CustList<FrameHtmlData> htmlDialogs = new CustList<FrameHtmlData>();

    private AbsPanel actionType;

    private final CustList<ActionLabel> actionsLabels = new CustList<ActionLabel>();

    private AbsPanel actions;

    private AbsPanel movesPanel;

    private AbsPlainLabel selectedItem;

    private AbsPanel targetsPanel;

    private TargetsPanel targets;

    private final CustList<MoveLabel> movesLabels = new CustList<MoveLabel>();

    private AbsPanel fleeWeb;

    private AbsPlainButton catchBall;

    private AbsPlainButton validateActions;

    private AbsPlainButton flee;

    private AbsPlainLabel webLabel;

    private AbsPlainButton web;

    private AbsPlainButton cancelCatch;

    private PlaceLabel plLabelBack;

    //private GridBagLayout grid;

    private boolean enableClick = true;

    private final AbsPlainLabel nickname;

    private AbsPlainButton nicknameLabel;

    private String typedNickname = DataBase.EMPTY_STRING;

    private AbsPlainLabel errorLabel;

    private AbsWrappedTextArea commentsErrors;
    private AbsScrollPane commentsErrorsScroll;

    private AbsPlainLabel roundLabel;

    private AbsWrappedTextArea commentsRound;
    private AbsScrollPane commentsRoundScroll;

    private boolean enabledChangeLanguage;

    private RoundThread roundThread;
    private AbstractThread roundThreadLau;

    private boolean enableAnimation;

    private boolean enabledClicked;

    private final AbsScrollPane scroll;
    private final AbsPanel comments = getFrames().getCompoFactory().newPageBox();
    private final AbsPanel forms = getFrames().getCompoFactory().newLineBox();
    private final AbsPanel team = getFrames().getCompoFactory().newPageBox();
    public Battle(WindowAiki _window, FacadeGame _facade, FrontBattle _frontBattle) {
        super(_window.getLanguageKey(),_window);
//        super(JSplitPane.VERTICAL_SPLIT, new JScrollPane(UPPER), new JScrollPane(LOWER));
//        splitter = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(UPPER), new JScrollPane(LOWER));
//        setContentPane(splitter);
        facade = _facade;
        scroll = getFrames().getCompoFactory().newAbsScrollPane(lower);
        setContentPane(scroll);
        window = _window;
        nickname = _window.getCompoFactory().newPlainLabel("");
        setDialogIcon(_window.getCommonFrame());
        setLocationRelativeTo(_window);
        frontBattle = _frontBattle;
        //scrollUpper = new JScrollPane(upper);
        //actionsBattle = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollUpper, new JScrollPane(lower));
        //actionsBattle.setDividerLocation(256);
        //actionsBattle.removeAll();
        //actionsBattle.setLayout(new BoxLayout(actionsBattle, BoxLayout.PAGE_AXIS));
//        grid = new GridBagLayout();
//        setLayout(grid);
        //add(new JScrollPane(actionsBattle));
        addWindowListener(new ClosingChildFrameEvent(this));
        setDefaultCloseOperation(GuiConstants.DO_NOTHING_ON_CLOSE);
    }

    @Override
    public void closeWindow() {
        setVisible(false);
    }

    public void initMessages() {
        String lg_ = window.getLanguageKey();
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, lg_, BATTLE);
    }

    public void setMessages() {
        setTitle(messages.getVal(CST_ACTIONS));
        if (fighterFrontPanel !=null) {
            fighterFrontPanel.setPanelTitle(messages.getVal(FRONT_TEAM));
        }
        if (fighterBackPanel !=null) {
            fighterBackPanel.setPanelTitle(messages.getVal(BACK_TEAM));
        }
        if (fighterPanel !=null) {
            fighterPanel.setPanelTitle(messages.getVal(TEAM_CST));
        }
        if (pokemonPanel !=null) {
            pokemonPanel.setPanelTitle(messages.getVal(EVOS));
            pokemonPanel.setNoEvoMessage(messages.getVal(NO_EVO));
        }
        if (ballPanel !=null) {
            ballPanel.setPanelTitle(messages.getVal(BALLS));
        }
        if (webLabel != null) {
            webLabel.setText(messages.getVal(FIGHT_DATA_MESSAGE));
        }
        if (web != null) {
            web.setText(messages.getVal(DATA_FIGHT));
        }
        if (catchBall != null) {
            catchBall.setText(messages.getVal(CATCH_PK));
        }
        if (flee != null) {
            if (facade.isWildFight()) {
                Rate r_ = facade.calculateFleeingRate();
                flee.setText(StringUtil.simpleStringsFormat(messages.getVal(CST_FLEE), r_.toNumberString(), r_.percent().toNumberString()));
            }
        }
        if (validateActions != null && facade.isExistingFight()) {
            if (facade.getFight().getState() != FightState.SURNOM) {
                if (facade.getFight().getState() != FightState.CAPTURE_KO) {
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
                }
            }
        }
        if (nicknameLabel != null) {
            nicknameLabel.setText(messages.getVal(NICKNAME_CST));
        }
        if (cancelCatch != null) {
            cancelCatch.setText(messages.getVal(CANCEL_CATCH));
        }
        if (plLabelBack != null) {
            plLabelBack.setText(messages.getVal(GO_BACK));
            plLabelBack.repaintLabel(window.getImageFactory());
        }
        if (frontBattle != null) {
            frontBattle.translate();
        }
        if (errorLabel != null) {
            errorLabel.setText(messages.getVal(ERRORS));
        }
        if (roundLabel != null) {
            roundLabel.setText(messages.getVal(ROUND));
        }
        window.pack();
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
        enabledClicked = true;
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
            validateActions.addActionListener(new EndFightEvent(this));
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
            fleeWeb.add(web);
            addBalls();
            addCatching();
            validateActions = window.getCompoFactory().newPlainButton();
            validateActions.addActionListener(new RoundAllThrowersEvent(this));
            fleeWeb.add(validateActions);
            addFlee();
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
        } else if (facade.getFight().getState() == FightState.SURNOM) {
            //nicknameLabel = window.getCompoFactory().newPlainButton();
            fleeWeb.add(webLabel);
            fleeWeb.add(web);
            fleeWeb.add(nicknameLabel);
            fleeWeb.add(nickname);
            AbsPlainButton ok_ = window.getCompoFactory().newPlainButton(WindowAiki.OK);
            ok_.addActionListener(new ValidateCaughtPokemonNicknameEvent(this));
            fleeWeb.add(ok_);
//            UPPER.add(fleeWeb);
            lower.add(fleeWeb);
//            actionsBattle.add(new JScrollPane(lower));
//            actionsBattle.setRightComponent(new JScrollPane(lower));
        } else if (facade.getFight().getState() == FightState.CAPTURE_KO) {
            //nicknameLabel = window.getCompoFactory().newPlainButton();
            fleeWeb.add(webLabel);
            fleeWeb.add(web);
            fleeWeb.add(nicknameLabel);
            fleeWeb.add(nickname);
            addBalls();
            AbsPlainButton ok_ = window.getCompoFactory().newPlainButton(WindowAiki.OK);
            ok_.addActionListener(new CatchKoPokemonEvent(this));
            fleeWeb.add(ok_);
            cancelCatch = window.getCompoFactory().newPlainButton();
            cancelCatch.addActionListener(new CancelCatchKoPokemonEvent(this));
            fleeWeb.add(cancelCatch);
//            fleeWeb.add(new JScrollPane(commentsErrors));
//            UPPER.add(fleeWeb);
            lower.add(fleeWeb);
//            actionsBattle.add(new JScrollPane(lower));
//            actionsBattle.setRightComponent(new JScrollPane(lower));
        } else if (facade.getFight().getState() == FightState.APPRENDRE_EVOLUER) {
            fleeWeb.add(webLabel);
            fleeWeb.add(web);
            validateActions = window.getCompoFactory().newPlainButton();
            validateActions.addActionListener(new LearnAndEvolveEvent(this));
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
            byte mult_ = facade.getFight().getMult();
            for (byte p = IndexConstants.FIRST_INDEX; p < mult_; p++) {
                PlaceLabel plLabel_ = new PlaceLabel(Long.toString(p), p, window.getCompoFactory());
                plLabel_.addMouseListener(new SelectPlaceEvent(this, p));
                panelPlaces.add(plLabel_);
                placesLabels.add(plLabel_);
            }
            plLabelBack = new PlaceLabel(Fighter.BACK, window.getCompoFactory());
            plLabelBack.addMouseListener(new SelectPlaceEvent(this, Fighter.BACK));
            panelPlaces.add(plLabelBack);
            placesLabels.add(plLabelBack);
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
            fleeWeb.add(web);
            validateActions = window.getCompoFactory().newPlainButton();
            validateActions.addActionListener(new SendSubstitutesEvent(this));
            fleeWeb.add(validateActions);
            addFlee();
//            UPPER.add(fleeWeb);
            lower.add(fleeWeb);
//            actionsBattle.setRightComponent(new JScrollPane(lower));
        } else if (facade.getFight().getState() == FightState.SWITCH_APRES_ATTAQUE) {
            fleeWeb.add(webLabel);
            fleeWeb.add(web);
            validateActions = window.getCompoFactory().newPlainButton();
            validateActions.addActionListener(new RoundAllThrowersEvent(this));
            fleeWeb.add(validateActions);
            addFlee();
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
            fleeWeb.add(web);
            validateActions = window.getCompoFactory().newPlainButton();
            validateActions.addActionListener(new RoundWhileKoPlayerEvent(this));
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
        if (!enabledClicked) {
            return;
        }
        enabledClicked = false;
        facade.roundAllThrowers(enableAnimation);
        if (facade.isErrorFight()) {
            enabledClicked = true;
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
            roundThreadLau = window.getThreadFactory().newThread(roundThread);
            roundThreadLau.start();
        } else {
            afterRoundWithoutAnimation();
        }
    }

    public void sendSubstitutes() {
        enabledChangeLanguage = false;
        if (!enabledClicked) {
            return;
        }
        enabledClicked = false;
        facade.sendSubstitutes();
        if (facade.isErrorFight()) {
            enabledClicked = true;
            commentsErrors.setText(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE));
            return;
        }
        window.setSavedGame(false);
        commentsErrors.setText(DataBase.EMPTY_STRING);
        afterRoundWithoutAnimation();
    }

    public void learnAndEvolve() {
        enabledChangeLanguage = false;
        if (!enabledClicked) {
            return;
        }
        enabledClicked = false;
        facade.learnAndEvolve();
        if (facade.isErrorFight()) {
            enabledClicked = true;
            commentsErrors.setText(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE));
            return;
        }
        window.setSavedGame(false);
        commentsErrors.setText(DataBase.EMPTY_STRING);
        afterRoundWithoutAnimation();
    }

    public void validateCaughtPokemonNickname() {
        window.setSavedGame(false);
        facade.catchWildPokemon(typedNickname);
        afterRoundDirect();
    }

    public void catchKoPokemon() {
        BallNumberRate ball_ = ballPanel.getSelectedBall();
        if (ball_ == null) {
            return;
        }
        window.setSavedGame(false);
        facade.catchKoWildPokemon(ball_.getName(), typedNickname);
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

    public void cancelCatchKoPokemon() {
        window.setSavedGame(false);
        facade.notCatchKoWildPokemon();
        afterRoundDirect();
    }

    private void afterRoundDirect() {
        enabledChangeLanguage = false;
        if (window.getComment().isEmpty()) {
            closeWindows();
            window.drawGameWalking(false);
            window.pack();
        } else {
            commentsRound.setText(window.getComment());
            refresh();
            pack();
        }
    }

    public void roundWhileKoPlayer() {
        if (!enabledClicked) {
            return;
        }
        enabledClicked = false;
        window.setSavedGame(false);
        facade.roundWhileKoPlayer(enableAnimation);
        enabledChangeLanguage = false;
        if (enableAnimation) {
            removeInteractiveComponents();
            window.pack();
            commentsRound.setText(DataBase.EMPTY_STRING);
            window.disableBasicFight();
            roundThread = new RoundKoUserThread(facade, this);
            roundThreadLau = window.getThreadFactory().newThread(roundThread);
            roundThreadLau.start();
        } else {
            afterRoundWithoutAnimation();
        }
    }

    private void afterRoundWithoutAnimation() {
        if (!facade.isExistingFight()) {
            if (window.getComment().isEmpty()) {
                closeWindows();
                window.drawGameWalking(false);
                window.pack();
            } else {
                commentsRound.setText(window.getComment());
                refresh();
                pack();
            }
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
            if (window.getComment().isEmpty()) {
                window.drawGameWalking(false);
                window.pack();
            } else {
                commentsRound.setText(window.getComment());
                refresh();
                pack();
                setVisible(true);
//                window.pack();
            }
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
            ballPanel.initBalls();
//            GridBagConstraints c_ = new GridBagConstraints();
//            grid.setConstraints(ballPanel, c_);
            fleeWeb.add(ballPanel.getContainer());
        }
    }

    private void addCatching() {
        if (facade.isWildFight()) {
            fleeWeb.add(catchBall);
        }
    }

    private void addFlee() {
        if (facade.isWildFight()) {
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
            flee.addActionListener(new FleeEvent(this));
        }
        if (web == null) {
            web = window.getCompoFactory().newPlainButton();
            web.addActionListener(new ShowFightDataEvent(this));
        }
        if (webLabel == null) {
            webLabel = window.getCompoFactory().newPlainLabel("");
        }
        if (nicknameLabel == null) {
            nicknameLabel = window.getCompoFactory().newPlainButton();
            nicknameLabel.addActionListener(new NicknameEvent(this));
        }
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
            fighterFrontPanel.addListener(this, true);
        }
        if (fighterBackPanel == null) {
            fighterBackPanel = new FighterPanel(window, 2, DataBase.EMPTY_STRING, facade, facade.getPlayerBackTeam());
            fighterBackPanel.addListener(this, false);
        }
        if (fighterPanel == null) {
            fighterPanel = new FighterPanel(window, 2, DataBase.EMPTY_STRING, facade, facade.getPlayerTeam());
            fighterPanel.addListener(this);
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
            catchBall.addActionListener(new CatchNotKoPokemonEvent(this));
        }
    }

    public void flee() {
        if (!enabledClicked) {
            return;
        }
        enabledClicked = false;
        facade.attemptFlee(enableAnimation);
        window.setSavedGame(false);
        enabledChangeLanguage = false;
        if (enableAnimation) {
            removeInteractiveComponents();
            window.pack();
            commentsRound.setText(DataBase.EMPTY_STRING);
            window.disableBasicFight();
            roundThread = new RoundFleeThread(facade, this);
            roundThreadLau = window.getThreadFactory().newThread(roundThread);
            roundThreadLau.start();
        } else {
            if (!facade.isExistingFight()) {
                if (window.getComment().isEmpty()) {
                    closeWindows();
                    window.drawGameWalking(false);
                    window.pack();
                } else {
                    commentsRound.setText(window.getComment());
                    refresh();
//                    setVisible(true);
                    pack();
//                    window.pack();
                }
//                closeWindows();
//                window.addToArea();
//                window.drawGameWalking(false);
//                window.pack();
            } else {
                refresh();
                setVisible(true);
                pack();
//                window.pack();
            }
        }
    }

    public void nicknamePokemon() {
        if (!enabledClicked) {
            return;
        }
//        ConfirmDialog dial_ = new ConfirmDialog(window, typedNickname, _messages_.getVal(NICKNAME), _messages_.getVal(NICKNAME), Constants.getLanguage());
        TextAnswerValue confirmDialog_ = window.getConfirmDialogText().input(window.getCommonFrame(), typedNickname, messages.getVal(NICKNAME_CST), messages.getVal(NICKNAME_CST), window.getLanguageKey());
        if (confirmDialog_.getAnswer() != GuiConstants.YES_OPTION) {
            return;
        }
        typedNickname = confirmDialog_.getTypedText();
        nickname.setText(typedNickname);
    }

    public void catchNotKoPokemon() {
        BallNumberRate ball_ = ballPanel.getSelectedBall();
        if (ball_ == null) {
            return;
        }
        if (!enabledClicked) {
            return;
        }
        enabledClicked = false;
        facade.attemptCatchingWildPokemon(ball_.getName(), enableAnimation);
        enabledChangeLanguage = false;
        window.setSavedGame(false);
        if (enableAnimation) {
            removeInteractiveComponents();
            window.pack();
            commentsRound.setText(DataBase.EMPTY_STRING);
            window.disableBasicFight();
            if (facade.getFight().getState() != FightState.SURNOM) {
                roundThread = new RoundFailBallThread(facade, this, ball_.getName());
            } else {
                roundThread = new RoundBallThread(facade, this, ball_.getName());
            }
            roundThreadLau = window.getThreadFactory().newThread(roundThread);
            roundThreadLau.start();
        } else {
            if (!facade.isExistingFight()) {
                if (window.getComment().isEmpty()) {
                    closeWindows();
                    window.drawGameWalking(false);
                    window.pack();
                } else {
                    commentsRound.setText(window.getComment());
                    refresh();
                    pack();
                }
            } else {
                refresh();
                pack();
            }
        }
    }

    public void showFightData() {
        if (!enabledClicked) {
            return;
        }
//        AbstractThread fightThread_ = window.getPreparedFightThread();
        PreparedRenderedPages fightTask_ = window.getPreparedFightTask();
//        if (fightThread_ == null || fightThread_.isAlive() || fightTask_ == null) {
//            return;
//        }
        if (!htmlDialogs.isEmpty()) {
            if (!htmlDialogs.first().isVisible()) {
                if (htmlDialogs.first().getSession().isProcessing()) {
                    return;
                }
                reinitWebFight(fightTask_);
                htmlDialogs.first().setVisible(true);
            }
            return;
        }
        RenderedPage session_;
        session_ = new RenderedPage(getFrames().getCompoFactory().newAbsScrollPane(), window.getFrames());
        session_.setProcess(window.getVideoLoading().getVideo(window.getGenerator(),window.getFileCoreStream(),window.getFrames()));
        FrameHtmlData dialog_ = new FrameHtmlData(window, messages.getVal(TITLE), session_);
        dialog_.initSessionLg(facade,fightTask_,facade.getLanguage());
        htmlDialogs.add(dialog_);
    }

    private void reinitWebFight(PreparedRenderedPages _task) {
        htmlDialogs.first().setTitle(messages.getVal(TITLE));
        htmlDialogs.first().initSessionLg(facade,_task,facade.getLanguage());
        htmlDialogs.first().pack();
    }

    public void refreshSession() {
        for (FrameHtmlData f: htmlDialogs) {
            f.setTitle(messages.getVal(TITLE));
            if (!f.isVisible()) {
                continue;
            }
            f.refresh(window);
            f.pack();
        }
    }

    public void resetWindows() {
        if (facade.isChangeToFightScene()) {
            if (!htmlDialogs.isEmpty()) {
                if (htmlDialogs.first().isVisible()) {
//                    AbstractThread fightThread_ = window.getPreparedFightThread();
                    PreparedRenderedPages fightTask_ = window.getPreparedFightTask();
//                    if (fightThread_ == null || fightThread_.isAlive() || fightTask_ == null) {
//                        return;
//                    }
                    if (htmlDialogs.first().getSession().isProcessing()) {
                        return;
                    }
                    reinitWebFight(fightTask_);
                }
            }
        } else {
            closeWindows();
        }
    }

    public void closeWindows() {
        if (!htmlDialogs.isEmpty()) {
            htmlDialogs.first().setVisible(false);
        }
    }

    public void clearHtmlDialogs() {
        htmlDialogs.clear();
    }

    public void choosePlayerTarget(byte _number, int _index) {
        window.setSavedGame(false);
        facade.setFirstChosenMovePlayerTarget(_number);
        targets.repaintLabelPlayer(_index,this);
    }

    public void chooseFoeTarget(byte _number, int _index) {
        window.setSavedGame(false);
        facade.setFirstChosenMoveFoeTarget(_number);
        targets.repaintLabelFoe(_index,this);
    }

    public void chooseFrontFighter() {
        if (!enableClick) {
            return;
        }
        enableClick = false;
        enabledChangeLanguage = false;
        facade.chooseFrontFighter((byte) fighterFrontPanel.getSelectedIndex());
        fighterBackPanel.deselect();
        if (facade.getFight().getState() == FightState.ATTAQUES) {
            displayActionsBeforeRound();
        } else {
            for (PlaceLabel p: placesLabels) {
                p.setSelected(facade.getFight().getChosenSubstitute());
            }
            panelPlaces.repaintChildren(window.getImageFactory());
        }
        enableClick = true;
    }

    public void chooseBackFighter() {
        if (!enableClick) {
            return;
        }
        enableClick = false;
        enabledChangeLanguage = false;
        facade.chooseBackFighter((byte) fighterBackPanel.getSelectedIndex());
        fighterFrontPanel.deselect();
        window.setSavedGame(false);
        if (facade.getFight().getState() == FightState.ATTAQUES) {
            displayActionsBeforeRound();
            if (facade.getFight().getChosenIndexBack() == Fighter.BACK) {
                fighterBackPanel.deselect();
            }
        } else {
            for (PlaceLabel p: placesLabels) {
                p.setSelected(facade.getFight().getChosenSubstitute());
            }
            panelPlaces.repaintChildren(window.getImageFactory());
        }
        enableClick = true;
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
        enableClick = false;
//        pokemonPanel.initEvos();
        pokemonPanel = new PokemonPanel(window,2, DataBase.EMPTY_STRING, facade, messages.getVal(NO_EVO));
        pokemonPanel.addListener(this);
        enableClick = true;
    }

    private void initLearntMovesAbilities() {
        StringMap<StringMap<String>> trMoves_;
        trMoves_ = facade.getData().getTranslatedMoves();
        TreeMap<String,BoolVal> moves_ = new TreeMap<String, BoolVal>(new ComparatorTrStrings(trMoves_.getVal(window.getLanguageKey())));
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
            check_.setBackground(GuiConstants.RED);
            movesLearnPanel.add(check_.getComponent());
        }
        for (String m: moves_.getKeys()) {
            boolean learnt_ = moves_.getVal(m) == BoolVal.TRUE;
            if (learnt_) {
                continue;
            }
            String tr_ = facade.translateMove(m);
            MoveFighterCheckBox check_ = new MoveFighterCheckBox(m,tr_,learnt_,this);
            check_.setBackground(GuiConstants.WHITE);
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
        abilitiesCopy_.sortElts(new ComparatorTrStrings(facade.getTranslatedAbilitiesCurLanguage()));
        for (String a: abilitiesCopy_) {
            AbilityLabel ab_ = new AbilityLabel(facade.translateAbility(a), a, window.getCompoFactory());
//            ab_.setSelected(facade.getAbility());
            ab_.addMouseListener(new AbilityFightEvent(this, a));
            abilityLabels.add(ab_);
            abilitiesLearnPanel.add(ab_);
        }
        abilitiesLearnPanel.validate();
        abilitiesLearnPanel.repaintChildren(window.getImageFactory());
        changeAbility(facade.getAbility());
    }

    private void displayActionsBeforeRound() {
        actionType.removeAll();
        actionType.add(window.getCompoFactory().newPlainLabel(messages.getVal(SELECT_ACTION)));
        actionsLabels.clear();
        IdList<ActionType> actions_ = facade.getFight().getPossibleActionsCurFighter();
        String fileName_ = ResourcesMessagesUtil.getPropertiesPath(Resources.MESSAGES_FOLDER, window.getLanguageKey(), ACTION_TYPE);
        String loadedResourcesMessages_ = MessPkGr.ms().getVal(fileName_);
        StringMap<String> map_ = ResourcesMessagesUtil.getMessagesFromContent(loadedResourcesMessages_);
        int maxWidth_ = 0;
        for (ActionType a: actions_) {
            String txt_ = map_.getVal(a.name());
            ActionLabel action_ = new ActionLabel(txt_, a, window.getCompoFactory());
            action_.addMouseListener(new FighterAction(this, a));
            action_.setSelected(a == facade.getFight().getSelectedActionCurFighter());
            actionType.add(action_);
            maxWidth_ = NumberUtil.max(maxWidth_, action_.stringWidth(txt_));
            actionsLabels.add(action_);
        }
        for (ActionLabel a: actionsLabels) {
            a.setPreferredSize(new MetaDimension(maxWidth_,10));
        }
        changeAction(facade.getFight().getSelectedActionCurFighter());
    }

    public void changeAction(ActionType _action) {
        facade.changeAction(_action);
        for (ActionLabel a: actionsLabels) {
            a.setSelected(_action);
            a.repaintLabel(window.getImageFactory());
        }
        actions.removeAll();
        actions.add(actionType);
        if (_action == ActionType.HEALING) {
            displayMovesToBeHealed();
            window.setSavedGame(false);
        } else if (_action == ActionType.MOVE) {
            displayMoves();
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
        AbsPlainButton button_ = window.getCompoFactory().newPlainButton(messages.getVal(SELECT_ITEM));
        button_.addActionListener(new SelectHealingItemEvent(this));
        String str_ = facade.getFight().getChosenHealingMove();
        if (!str_.isEmpty()) {
            String mess_ = StringUtil.simpleStringsFormat(messages.getVal(SELECTED_ITEM), str_);
            selectedItem.setText(mess_);
        }
        actions.add(button_);
        actions.add(selectedItem);
        NatStringTreeMap<ChosenMoveInfos> moves_ = facade.getFight().getCurrentFighterMoves();
        if (!moves_.isEmpty()) {
            boolean wasNull_ = movesPanel == null;
            if (wasNull_) {
                movesPanel = window.getCompoFactory().newPageBox();
            } else {
                movesPanel.removeAll();
            }
            movesPanel.add(window.getCompoFactory().newPlainLabel(messages.getVal(SELECT_MOVE_HEAL)));
            movesLabels.clear();
            for (String m: moves_.getKeys()) {
                ChosenMoveInfos info_ = moves_.getVal(m);
                MoveLabel move_ = new MoveLabel(info_, m, facade, window.getCompoFactory());
                if (info_.isUsable()) {
                    move_.addMouseListener(new MoveEvent(this, info_.getName()));
                }
                move_.setSelected(facade.getFight().getChosenMoveFront());
                movesPanel.add(move_);
                movesLabels.add(move_);
            }
            if (wasNull_) {
                actions.add(movesPanel);
            }
        }
    }

    public void selectHealingItem() {
        int lineBack_ = facade.getLineHealingItem();
        SelectHealingItem.setSelectHealingItem(window, facade);
        boolean isSelectedIndex_ = SelectHealingItem.isSelectedIndex(window.getSelectHealingItem());
        boolean ok_ = SelectHealingItem.isOk(window.getSelectHealingItem());
        if (!ok_) {
            facade.setLineHealingItem(lineBack_);
            facade.clearSortingHealingItem();
        } else if (isSelectedIndex_) {
            facade.setChosenHealingItem();
            facade.clearSortingHealingItem();
            window.setSavedGame(false);
            String item_ = facade.getFight().getChosenHealingMove();
            String mess_ = StringUtil.simpleStringsFormat(messages.getVal(SELECTED_ITEM), item_);
            selectedItem.setText(mess_);
            displayMovesToBeHealed();
//            window.pack();
            pack();
            //Label selected
        } else {
            selectedItem.setText(messages.getVal(NO_ITEM));
            movesLabels.clear();
            if (movesPanel != null) {
//                actions.remove(movesPanel);
                movesPanel.setVisible(false);
            }
            facade.clearSortingHealingItem();
//            window.pack();
            pack();
            //Label not selected
        }
    }

    private void displayMoves() {
        NatStringTreeMap<ChosenMoveInfos> moves_ = facade.getFight().getCurrentFighterMoves();
        if (!moves_.isEmpty()) {
            AbsPanel movesPanel_ = window.getCompoFactory().newPageBox();
            movesPanel_.add(window.getCompoFactory().newPlainLabel(messages.getVal(SELECT_MOVE_ROUND)));
            movesLabels.clear();
            for (String m: moves_.getKeys()) {
                ChosenMoveInfos info_ = moves_.getVal(m);
                MoveLabel move_ = new MoveLabel(info_, m, facade, window.getCompoFactory());
                move_.setSelected(facade.getFight().getChosenMoveFront());
                if (info_.isUsable()) {
                    move_.addMouseListener(new MoveEvent(this, info_.getName()));
                }
                movesPanel_.add(move_);
                movesLabels.add(move_);
            }
            movesPanel_.repaintChildren(window.getImageFactory());
            actions.add(movesPanel_);
            boolean wasNull_ = targetsPanel == null;
            if (wasNull_) {
                targetsPanel = window.getCompoFactory().newBorder();
            } else {
                targetsPanel.removeAll();
            }
            CustList<ChosableTargetName> chosablePlayer_ = facade.getFight().getChosablePlayerTargets();
            CustList<ChosableTargetName> chosableFoe_ = facade.getFight().getChosableFoeTargets();
            if (FightFacade.indexes(chosablePlayer_).size() + FightFacade.indexes(chosableFoe_).size() > DataBase.ONE_POSSIBLE_CHOICE) {
                targets.setTargets(facade, this);
                AbsPlainLabel header_ = window.getCompoFactory().newPlainLabel(messages.getVal(SELECT_TARGET));
                targetsPanel.add(header_, GuiConstants.BORDER_LAYOUT_NORTH);
            } else {
                targets.getContainer().removeAll();
            }
            targetsPanel.add(targets.getContainer(), GuiConstants.BORDER_LAYOUT_CENTER);
            actions.add(targetsPanel);
        }
    }

    public void changeMove(String _move) {
        facade.chooseMove(_move);
        for (MoveLabel m: movesLabels) {
            m.setSelected(_move);
            m.repaintLabel(window.getImageFactory());
        }
        if (facade.getFight().getSelectedActionCurFighter() == ActionType.MOVE) {
//            if (targetsPanel != null) {
//                actions.remove(targetsPanel);
//            }
            boolean wasNull_ = targetsPanel == null;
            if (wasNull_) {
                targetsPanel = window.getCompoFactory().newBorder();
            } else {
                targetsPanel.removeAll();
            }
            CustList<ChosableTargetName> foeTargets_ = facade.getFight().getChosableFoeTargets();
            CustList<ChosableTargetName> plTargets_ = facade.getFight().getChosablePlayerTargets();
            if (FightFacade.indexes(foeTargets_).size() + FightFacade.indexes(plTargets_).size() > DataBase.ONE_POSSIBLE_CHOICE) {
                targets.setTargets(facade, this);
                AbsPlainLabel header_ = window.getCompoFactory().newPlainLabel(messages.getVal(SELECT_TARGET));
                targetsPanel.add(header_, GuiConstants.BORDER_LAYOUT_NORTH);
            } else {
                window.setSavedGame(false);
                targets.getContainer().removeAll();
            }
            targetsPanel.add(targets.getContainer(), GuiConstants.BORDER_LAYOUT_CENTER);
            if (wasNull_) {
                actions.add(targetsPanel);
            }
        } else if (facade.getFight().getSelectedActionCurFighter() == ActionType.HEALING) {
            window.setSavedGame(false);
            targets.getContainer().removeAll();
        }
//        window.pack();
        pack();
    }

    public boolean isEnableClick() {
        return enableClick;
    }

    public void setEnableClick(boolean _enableClick) {
        enableClick = _enableClick;
    }

    public void changeFrontPlace(byte _index) {
        window.setSavedGame(false);
        facade.setSubstituteEndRound(_index);
        for (PlaceLabel p: placesLabels) {
            p.setSelected(_index);
        }
        panelPlaces.repaintChildren(window.getImageFactory());
    }

    public void chooseEvolution() {
        if (!enableClick) {
            return;
        }
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
        abilitiesLearnPanel.repaintChildren(window.getImageFactory());
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

    public void drawAnimationFightIni(AbstractImage _heros, AbstractImage _other) {
        frontBattle.drawAnimationFightIni(_heros, _other);
    }

    public void drawAnimationFightIniInst() {
        frontBattle.drawAnimationFightIniInst();
    }

    public boolean isAliveThread() {
        return roundThreadLau != null && roundThreadLau.isAlive();
    }

    public boolean isKeepAnimation() {
        return frontBattle.isKeepAnimation();
    }

    public boolean isEnabledChangeLanguage() {
        return enabledChangeLanguage;
    }

    public void enableAnimation(boolean _enableAnimation) {
        enableAnimation = _enableAnimation;
    }

    public void initBall() {
        frontBattle.initBall();
    }

    public void moveBall(AbstractImageFactory _fact, String _ball) {
        frontBattle.moveBall(_fact,_ball);
    }

    public void setWild(boolean _wild) {
        frontBattle.setWild(_wild);
    }

    public CustList<FrameHtmlData> getHtmlDialogs() {
        return htmlDialogs;
    }

    public boolean openedHtmlFrames() {
        if (htmlDialogs.isEmpty()) {
            return false;
        }
        return htmlDialogs.first().isVisible();
    }

    public WindowAiki getWindow() {
        return window;
    }

    public FighterPanel getFighterBackPanel() {
        return fighterBackPanel;
    }

    public FighterPanel getFighterPanel() {
        return fighterPanel;
    }

    public FighterPanel getFighterFrontPanel() {
        return fighterFrontPanel;
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
