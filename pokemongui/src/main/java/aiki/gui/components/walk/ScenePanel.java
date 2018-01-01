package aiki.gui.components.walk;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.ToolTipManager;

import aiki.DataBase;
import aiki.Resources;
import aiki.comparators.TrMovesComparator;
import aiki.facade.FacadeGame;
import aiki.facade.enums.StorageActions;
import aiki.game.Game;
import aiki.game.enums.InterfaceType;
import aiki.gui.MainWindow;
import aiki.gui.components.AbilityLabel;
import aiki.gui.components.checks.MoveEvoCheckBox;
import aiki.gui.components.checks.MoveTutorCheckBox;
import aiki.gui.components.walk.events.AddTmEvent;
import aiki.gui.components.walk.events.BuyItemsEvent;
import aiki.gui.components.walk.events.BuyOrSellEvent;
import aiki.gui.components.walk.events.BuyTmEvent;
import aiki.gui.components.walk.events.CancelMtEvent;
import aiki.gui.components.walk.events.ChangeItemListEvent;
import aiki.gui.components.walk.events.ChangeNicknameEvent;
import aiki.gui.components.walk.events.ChoosePlaceEvent;
import aiki.gui.components.walk.events.ConsultEggEvent;
import aiki.gui.components.walk.events.ConsultHostEvent;
import aiki.gui.components.walk.events.ConsultPokemonEvent;
import aiki.gui.components.walk.events.EvolvePokemonEvent;
import aiki.gui.components.walk.events.ExitInteractionEvent;
import aiki.gui.components.walk.events.ExitTradeEvent;
import aiki.gui.components.walk.events.FishingEvent;
import aiki.gui.components.walk.events.GearStorageEvent;
import aiki.gui.components.walk.events.HealPokemonEvent;
import aiki.gui.components.walk.events.HostPokemonEvent;
import aiki.gui.components.walk.events.InteractSceneEvent;
import aiki.gui.components.walk.events.ManageNetworkEvent;
import aiki.gui.components.walk.events.ManageTeamEvent;
import aiki.gui.components.walk.events.ReadyEvent;
import aiki.gui.components.walk.events.ReceiveFromHostEvent;
import aiki.gui.components.walk.events.RemoveTmEvent;
import aiki.gui.components.walk.events.SeePokemonDetailEvent;
import aiki.gui.components.walk.events.SelectEggBoxEvent;
import aiki.gui.components.walk.events.SelectItemForListEvent;
import aiki.gui.components.walk.events.SelectItemForPokemonEvent;
import aiki.gui.components.walk.events.SelectPokemonBoxEvent;
import aiki.gui.components.walk.events.SelectTmToLearnEvent;
import aiki.gui.components.walk.events.SetPlacesEvent;
import aiki.gui.components.walk.events.ShowGameProgressingEvent;
import aiki.gui.components.walk.events.TakeItemFromTeamEvent;
import aiki.gui.components.walk.events.ValidateMtEvent;
import aiki.gui.components.walk.events.ValidateTradingEvent;
import aiki.gui.dialogs.DialogHtmlData;
import aiki.gui.dialogs.DialogServer;
import aiki.gui.dialogs.SelectEgg;
import aiki.gui.dialogs.SelectHealedMove;
import aiki.gui.dialogs.SelectHealingItem;
import aiki.gui.dialogs.SelectItem;
import aiki.gui.dialogs.SelectPokemon;
import aiki.gui.dialogs.SelectTm;
import aiki.gui.listeners.AbilityWalkEvent;
import aiki.gui.listeners.BoostMoveEvent;
import aiki.gui.listeners.HealMoveEvent;
import aiki.gui.listeners.KeyPadListener;
import aiki.gui.listeners.LearntMoveEvent;
import aiki.gui.listeners.MouseTask;
import aiki.gui.listeners.Task;
import aiki.map.enums.Direction;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;
import aiki.network.Net;
import aiki.network.stream.SentPokemon;
import code.gui.ConfirmDialog;
import code.gui.GroupFrame;
import code.gui.LabelButton;
import code.gui.Separator;
import code.gui.SessionEditorPane;
import code.gui.WrappedTextArea;
import code.maths.LgInt;
import code.network.NetCreate;
import code.network.SocketResults;
import code.network.enums.ErrorHostConnectionType;
import code.network.enums.IpType;
import code.sml.util.ExtractFromFiles;
import code.util.CustList;
import code.util.EntryCust;
import code.util.NatTreeMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.ConstFiles;
import code.util.consts.Constants;

public class ScenePanel extends JPanel {

    private static final String SCENE_PANEL = "aiki.gui.components.walk.ScenePanel";

    private static final String LOCALHOST_OLD_IP = "127.0.0.1";

    private static final String LOCALHOST_NEW_IP = "::1";

    private static final String RETURN_LINE = "\n";

    private static final String TEAM = "team";

    private static final String ITEMS = "items";

    private static final String TM = "tm";

//    private static final String DIFFICULTY = "difficulty";

    private static final String FISH = "fish";

    private static final String SEE_POKEMON = "seePokemon";

    private static final String SEE_EGG = "seeEgg";

    private static final String SEE_HOSTED = "seeHosted";

    private static final String SEE_GAME = "seeGame";

    private static final String GO_BACK_MENU = "goBackMenu";

    private static final String SERVER = "server";

    private static final String POKEMON_SELECT = "pokemonSelect";

    private static final String POKEMON_SELECT_TWO = "pokemonSelectTwo";

    private static final String RECEIVED_POKEMON = "receivedPokemon";

    private static final String INTERACT = "interact";

    private static final String GO_BACK = "goBack";

    private static final String TRADE = "trade";

    private static final String EXIT = "exit";

    private static final String READY = "ready";

    private static final String SELECT_PK_BOX = "selectPkBox";

    private static final String SELECT_EGG_BOX = "selectEggBox";

//    private static final String SELECTED_PK = "selectedPk";

//    private static final String SELECTED_EGG = "selectedEgg";

//    private static final String NOT_SELECTED_PK = "notSelectedPk";

//    private static final String NOT_SELECTED_EGG = "notSelectedEgg";

    private static final String TAKE_ITEM = "takeItem";

    private static final String STORE = "store";

    private static final String WITHDRAW_PK = "withdrawPk";

    private static final String WITHDRAW_EGG = "withdrawEgg";

    private static final String SWITCH_PK_EGG = "siwtchPkEgg";

    private static final String RELEASE = "release";

    private static final String TM_TITLE = "tmTitle";

    private static final String TM_SELECT = "tmSelect";

    private static final String TM_REMOVE = "tmRemove";

    private static final String TM_BUY = "tmBuy";

    private static final String ITEM_TITLE = "itemTitle";

    private static final String ITEM_SELECT = "itemSelect";

    private static final String ITEM_ADD = "itemAdd";

    private static final String ITEM_REMOVE = "itemRemove";

    private static final String ITEM_BUY = "itemBuy";

    private static final String ITEM_BUY_SELL = "itemBuySell";

    private static final String GET_EGG = "getEgg";

    private static final String GET_EGG_PARENT = "getEggParent";

    private static final String HOST_PK = "hostPk";

    private static final String SWITCH_PK_TEAM = "switchPkTeam";

    private static final String TAKE_ITEM_TEAM = "takeItemTeam";

    private static final String DETAIL_TEAM = "detailTeam";

    private static final String HEAL_PK = "healPk";

    private static final String SELECT_MT = "selectMt";

    private static final String CANCEL_MT = "cancelMt";

    private static final String VALIDATE_MT = "validateMt";

    //private static final String SELECTED_MOVES = "selectedMoves";

    private static final String SELECT_TM = "selectTm";

    private static final String SELECT_ABILITY = "selectAbility";

    private static final String EVOLVE = "evolve";

    private static final String SELECT_HEAL_MOVE = "selectHealMove";

    private static final String SELECT_BOOST_MOVE = "selectBoostMove";

    private static final String NICKNAME = "nickname";

    private static final String ERROR_USING_ITEM = "errorUsingItem";

    private static final String NO_POSSIBLE_LEARN = "noPossibleLearn";

    private static final String NO_POSSIBLE_BUY = "noPossibleBuy";

    private static final String TITLE_DETAIL = "titleDetail";

//    private static final String TITLE_DIFFICULTY = "titleDifficulty";

    private static final String SPACE = " ";

    private static final String BUG = "bug";

    private static final String UNKNOWN_HOST = "unknownHost";

    private static final String NOT_CONNECTED = "notConnected";

    private static final String TITLE_COMMENTS = "titleComments";

    private static final String CLICK_SCENE = "clickScene";

    private static StringMap<String> _messages_ = new StringMap<String>();

    private final MainWindow window;

    private JPanel panelMenu;

    private JLabel endGame = new JLabel();

    private JLabel useKeyPad;

    private LabelButton team;

    private LabelButton items;

    private LabelButton tm;

//    private LabelButton difficulty;

    private LabelButton fish;

    private LabelButton seeBoxes;

    private LabelButton seeEggs;

    private LabelButton host;

    private LabelButton game;

    private LabelButton goBack;

    private LabelButton server;

    private JPanel movesLearnt;

    private JPanel abilities;

    private CustList<AbilityLabel> abilityLabels = new CustList<AbilityLabel>();

    private JPanel panelOptions;

    private TeamPanel teamPan;

    private ItemsPanel itemsPan;

    private TmPanel tmPanel;

    private JCheckBox buy;

    private LabelButton selectPkBox;

    private LabelButton selectEggBox;

    private LabelButton takeItem;

    private LabelButton store;

    private LabelButton release;

    //private LabelButton cancelSelect;

    private LabelButton withdraw;

    private LabelButton withdrawEgg;

    private LabelButton switchPk;

    private JLabel selectedForSwitch;

    private FacadeGame facade;

    private JPanel beginGame;

    private JPanel sceneInteract;

    private JLabel placeName;

    private Scene scene;

    private KeyPadListener keyPadListener;

    private JPanel interaction;

    private LabelButton buttonInteract;

    private JTextArea commentsWalking;

    private Pad pad;

    private JLabel time;

    private JCheckBox switchUsable;

    private LabelButton takeItemTeam;

    private LabelButton detailPk;

    private LabelButton healPk;

    private LabelButton nicknamePk;

//    private boolean enabledSwitchTeam;

    private boolean enabledClick = true;

    //private GamePanel gamePanel;

    private SessionEditorPane receivedPk;

    private JPanel panelNetWork;

    private boolean enabledReady;

    private JCheckBox readyCheck;

    private MapPanel mapPanel;

    private LabelButton chosenCity;

    private volatile boolean paintingScene;

    public ScenePanel(MainWindow _window, FacadeGame _facade) {
        facade = _facade;
        window = _window;
        JPanel panelHoriz_ = new JPanel();
        initMenu();
        panelHoriz_.add(panelMenu);
        panelOptions = new JPanel();
        panelOptions.setLayout(new BorderLayout());
        panelHoriz_.add(panelOptions);
        add(panelHoriz_);
        time = new JLabel();
        add(time);
        //gamePanel = new GamePanel(facade);
        mapPanel = new MapPanel();
        window.pack();
    }

    public void reinit() {
        panelOptions.removeAll();
        if (keyPadListener != null) {
            keyPadListener.setSceneKepPad(null);
        }
        sceneInteract.removeAll();
//        if (scene != null) {
//            sceneInteract.remove(scene);
//        }
//        if (placeName != null) {
//            sceneInteract.remove(placeName);
//        }
//        if (interaction != null) {
//            sceneInteract.remove(interaction);
//        }
        panelMenu.setVisible(false);
    }

    public static void initMessages() {
        _messages_ = ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, Constants.getLanguage(),SCENE_PANEL);
        TeamPanel.initMessages();
    }

    public void setMessages() {
        endGame.setText(Game.getEndGameMessage());
        useKeyPad.setText(_messages_.getVal(CLICK_SCENE));
        team.setTextAndSize(_messages_.getVal(TEAM));
        items.setTextAndSize(_messages_.getVal(ITEMS));
        tm.setTextAndSize(_messages_.getVal(TM));
//        difficulty.setText(_messages_.getVal(DIFFICULTY));
        if (fish != null) {
            fish.setTextAndSize(_messages_.getVal(FISH));
        }
        seeBoxes.setTextAndSize(_messages_.getVal(SEE_POKEMON));
        seeEggs.setTextAndSize(_messages_.getVal(SEE_EGG));
        host.setTextAndSize(_messages_.getVal(SEE_HOSTED));
        game.setTextAndSize(_messages_.getVal(SEE_GAME));
        goBack.setTextAndSize(_messages_.getVal(GO_BACK_MENU));
        server.setTextAndSize(_messages_.getVal(SERVER));
        if (interaction != null) {
            buttonInteract.setTextAndSize(_messages_.getVal(INTERACT));
        }
        if (teamPan != null) {
            teamPan.translate();
        }
    }

    public void addBeginGame(JPanel _panel) {
        panelOptions.add(_panel, BorderLayout.CENTER);
        beginGame = _panel;
        beginGame.setVisible(true);
    }

    public void drawGameWalking(boolean _setPreferredSize) {
        endGame.setText(Game.getEndGameMessage());
        endGame.setVisible(facade.isShowEndGame());
        panelMenu.setVisible(true);
        if (beginGame != null) {
            beginGame.setVisible(false);
        }
//        if (beginGame != null) {
//            panelOptions.remove(beginGame);
//        }
        boolean wasNull_ = false;
        if (scene == null) {
            wasNull_ = true;
            scene = new Scene();
            scene.addMouseListener(scene);

            keyPadListener = new KeyPadListener(window, facade);
            scene.addKeyListener(keyPadListener);

            Task task_ = new Task(scene, facade, window);
            Timer t_ = new Timer(0, task_);
            pad.getUp().addMouseListener(new MouseTask(Direction.UP,task_, t_, window));
            pad.getDown().addMouseListener(new MouseTask(Direction.DOWN, task_, t_, window));
            pad.getLeft().addMouseListener(new MouseTask(Direction.LEFT, task_, t_, window));
            pad.getRight().addMouseListener(new MouseTask(Direction.RIGHT, task_, t_, window));
        }
        keyPadListener.setSceneKepPad(scene);
        facade.directInteraction();
        facade.changeCamera();
        scene.load(facade, _setPreferredSize);
        scene.setDelta(0, false);
        if (wasNull_) {
            scene.setPreferredSize();
        }
        wasNull_ = false;
        if (sceneInteract == null) {
            wasNull_ = true;
            sceneInteract = new JPanel();
            sceneInteract.setLayout(new BorderLayout());
        } else {
            sceneInteract.removeAll();
        }
        if (placeName == null) {
            placeName = new JLabel();
        }
        placeName.setText(facade.getCurrentPlace());
        sceneInteract.add(placeName, BorderLayout.NORTH);
        sceneInteract.add(scene, BorderLayout.CENTER);
        initInteraction();
        if (fish != null) {
            fish.setEnabledLabel(facade.isFishArea());
        }
        sceneInteract.add(interaction, BorderLayout.SOUTH);
        if (facade.getInterfaceType() != InterfaceType.RIEN) {
            buttonInteract.setEnabledLabel(true);
        } else {
            buttonInteract.setEnabledLabel(false);
        }
        if (wasNull_) {
            add(sceneInteract, CustList.FIRST_INDEX);
        } else {
            repaint();
        }
        scene.setFocus();
    }

    public void addToArea() {
        commentsWalking.setText(facade.getComment().getMessages().join(RETURN_LINE));
    }

    public String getComment() {
        return facade.getComment().getMessages().join(RETURN_LINE);
    }

    public void removeListeners() {
        pad.getUp().removeMouseListener(pad.getUp().getMouseListeners()[0]);
        pad.getDown().removeMouseListener(pad.getDown().getMouseListeners()[0]);
        pad.getLeft().removeMouseListener(pad.getLeft().getMouseListeners()[0]);
        pad.getRight().removeMouseListener(pad.getRight().getMouseListeners()[0]);
    }

    public void paintScene() {
        scene.repaint();
    }

    private void initMenu() {
        panelMenu = new JPanel();
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.PAGE_AXIS));
        endGame.setVisible(false);
        endGame.setOpaque(true);
        endGame.setBackground(Color.YELLOW);
        JPanel menus_ = new JPanel();
        menus_.setLayout(new GridLayout(0,1));
        menus_.add(endGame);
        useKeyPad = new JLabel();
        menus_.add(useKeyPad);
        team = new LabelButton();
        team.addMouseListener(new ManageTeamEvent(this));
        menus_.add(team);
        items = new LabelButton();
        items.addMouseListener(new SelectItemForPokemonEvent(this));
        menus_.add(items);
        tm = new LabelButton();
        tm.addMouseListener(new SelectTmToLearnEvent(this));
        menus_.add(tm);
        Separator sep_ = new Separator();
        menus_.add(sep_);
//        difficulty = new LabelButton();
//        difficulty.addMouseListener(new MouseAdapter() {
//
//            @Override
//            public void mouseReleased(MouseEvent _e) {
//                if (window.getCompiling().isAlive()) {
//                    int adv_ = Constants.getPercentCompile();
//                    String message_ = MainWindow.getCompilingString();
//                    String formatted_ = StringList.simpleFormat(message_, adv_);
//                    window.showErrorMessageDialog(formatted_);
//                    return;
//                }
////                if (panelOptions.getComponentCount() > 0) {
////                    return;
////                }
//                DialogDifficulty.setDialogDifficulty(window, _messages_.getVal(TITLE_DIFFICULTY), facade);
//                //dial_.setVisible(true);
//            }
//        });
//        menus_.add(difficulty);
//        sep_ = new Separator();
//        menus_.add(sep_);
//        fish = new LabelButton();
//        fish.addMouseListener(new MouseAdapter() {
//
//            @Override
//            public void mouseReleased(MouseEvent _e) {
//                if (facade.isFishArea()) {
//                    facade.initFishing();
//                    if (facade.isChangeToFightScene()) {
//                        window.setSavedGame(false);
//                        window.setFight(true, true, true);
//                    }
//                }
//            }
//        });
//        menus_.add(fish);
//        sep_ = new Separator();
//        menus_.add(sep_);
        seeBoxes = new LabelButton();
        seeBoxes.addMouseListener(new ConsultPokemonEvent(window, facade));
        menus_.add(seeBoxes);
        seeEggs = new LabelButton();
        seeEggs.addMouseListener(new ConsultEggEvent(window, facade));
        menus_.add(seeEggs);
        host = new LabelButton();
        host.addMouseListener(new ConsultHostEvent(window, facade));
        menus_.add(host);
        game = new LabelButton();
        game.addMouseListener(new ShowGameProgressingEvent(window));
        menus_.add(game);
        sep_ = new Separator();
        menus_.add(sep_);
        goBack = new LabelButton();
        goBack.addMouseListener(new SetPlacesEvent(this));
        menus_.add(goBack);
        sep_ = new Separator();
        menus_.add(sep_);
        server = new LabelButton();
        server.addMouseListener(new ManageNetworkEvent(this));
        menus_.add(server);
        panelMenu.add(menus_);
        pad = new Pad();
        panelMenu.add(pad);
        panelMenu.setVisible(false);
    }

    public void manageTeam() {
        facade.openMenu();
        panelMenu.setVisible(false);
        initTeam();
        teamPan.addListenerTeam(this);
        addButtonsTeam();
        addExit();
        window.pack();
    }

    public void selectItemForPokemon() {
        SelectItem.setSelectItem(window, facade, false, true);
        boolean isSelectedIndex_ = SelectItem.isSelectedIndex();
        boolean ok_ = SelectItem.isOk();
        if (!ok_) {
            facade.clearSortingItem();
            return;
        }
        if (!isSelectedIndex_) {
            facade.clearSortingItem();
            return;
        }
        boolean give_ = SelectItem.isGive();
        facade.setGivingObject(give_);
        facade.chooseObject();
        facade.clearSortingItem();
        if (!give_) {
            if (!facade.usedObject()) {
                //error if bad use of item
                String it_ = facade.getPlayer().getSelectedObject();
                it_ = facade.translateItem(it_);
                String message_ = StringList.simpleStringsFormat(_messages_.getVal(ERROR_USING_ITEM), it_);
                setTextArea(message_, JOptionPane.ERROR_MESSAGE);
                return;
            }
            facade.getPlayer().getIndexesOfPokemonTeam().clear();
            String usedItem_ = facade.getPlayer().getSelectedObject();
            LgInt nb_ = facade.getPlayer().getInventory().getNumber(usedItem_);
            facade.useObject();
            if (facade.getPlayer().getSelectedObject().isEmpty()) {
                LgInt nbAfter_ = facade.getPlayer().getInventory().getNumber(usedItem_);
                if (LgInt.strLower(nbAfter_, nb_)) {
                    //set saved game to false only if an item is used
                    window.setSavedGame(false);
                }
                return;
            }
        }
        facade.openMenu();
        panelMenu.setVisible(false);
        panelOptions.removeAll();
        NatTreeMap<Byte,UsablePokemon> pks_ = new NatTreeMap<Byte,UsablePokemon>();
        for (byte i: facade.getPlayer().getIndexesOfPokemonTeam()) {
            pks_.put(i, facade.getPlayer().getTeam().get(i));
        }

        JPanel set_ = new JPanel();
        teamPan = new TeamPanel(2, _messages_.getVal(POKEMON_SELECT), facade, pks_, true);
        teamPan.addListener(this);
        set_.add(teamPan);
        movesLearnt = new JPanel();
        movesLearnt.setLayout(new GridLayout(0,1));
        JScrollPane scroll_ = new JScrollPane(movesLearnt);
        scroll_.setPreferredSize(new Dimension(100, 220));
        set_.add(scroll_);
        abilities = new JPanel();
        abilities.setLayout(new GridLayout(0,1));
        set_.add(abilities);
        panelOptions.add(set_, BorderLayout.CENTER);
        addExit();
        window.pack();
    }

    public void selectTmToLearn() {
        SelectTm.setSelectTm(window, facade, false);
        boolean isSelectedIndex_ = SelectTm.isSelectedIndex();
        boolean ok_ = SelectTm.isOk();
        if (!ok_) {
            facade.clearSortingMove();
            return;
        }
        if (!isSelectedIndex_) {
            facade.clearSortingMove();
            return;
        }
        String move_ = facade.getPlayer().getSelectedMove();
        facade.chooseMoveByObject();
        facade.clearSortingMove();
        if (facade.getPlayer().getSelectedMove().isEmpty()) {
            //no pokemon can learn
            move_ = facade.translateMove(move_);
            String message_ = StringList.simpleStringsFormat(_messages_.getVal(NO_POSSIBLE_LEARN), move_);
            setTextArea(message_, JOptionPane.WARNING_MESSAGE);
            return;
        }
        facade.openMenu();
        panelMenu.setVisible(false);
        panelOptions.removeAll();
        NatTreeMap<Byte,UsablePokemon> pks_ = new NatTreeMap<Byte,UsablePokemon>();
        for (byte i: facade.getPlayer().getIndexesOfPokemonTeam()) {
            pks_.put(i, facade.getPlayer().getTeam().get(i));
        }

        JPanel set_ = new JPanel();
        teamPan = new TeamPanel(2, _messages_.getVal(POKEMON_SELECT), facade, pks_, true);
        teamPan.addListenerTm(this);
        set_.add(teamPan);
        movesLearnt = new JPanel();
        movesLearnt.setLayout(new GridLayout(0,1));
        JScrollPane scroll_ = new JScrollPane(movesLearnt);
        scroll_.setPreferredSize(new Dimension(100, 220));
        set_.add(scroll_);
        abilities = new JPanel();
        abilities.setLayout(new GridLayout(0,1));
        set_.add(abilities);
        panelOptions.add(set_, BorderLayout.CENTER);
        addExit();
        window.pack();
    }

    public void manageNetwork() {
        DialogServer.setDialogServer(window);
        String ip_ = DialogServer.getIpOrHostName();
        if (ip_ == null || ip_.isEmpty()) {
            if (DialogServer.getIpType() == IpType.IP_V6) {
                ip_ = LOCALHOST_NEW_IP;
            } else {
                ip_ = LOCALHOST_OLD_IP;
            }
        }
        if (!DialogServer.isChoosen()) {
            return;
        }
        String fileName_ = ConstFiles.getInitFolder() + Resources.PORT_INI;
        int port_ = NetCreate.tryToGetPort(fileName_, Net.getPort());
        if (DialogServer.isCreate()) {
            window.createServer(ip_, DialogServer.getIpType(), port_);
            return;
        }
        SocketResults connected_ = window.createClient(ip_, DialogServer.getIpType(), false, port_);
        if (connected_.getError() != ErrorHostConnectionType.NOTHING) {
            if (connected_.getError() == ErrorHostConnectionType.UNKNOWN_HOST) {
                String formatted_ = _messages_.getVal(UNKNOWN_HOST);
                formatted_ = StringList.simpleStringsFormat(formatted_, ip_);
                ConfirmDialog.showMessage(window, _messages_.getVal(BUG), formatted_, Constants.getLanguage(),JOptionPane.ERROR_MESSAGE);
                return;
            }
            ConfirmDialog.showMessage(window, _messages_.getVal(BUG), _messages_.getVal(NOT_CONNECTED), Constants.getLanguage(),JOptionPane.ERROR_MESSAGE);
            return;
        }
        window.setIndexInGame(CustList.SECOND_INDEX);
    }

    public void setPlaces() {
        //facade.initPlaces();
        panelMenu.setVisible(false);
//        JPanel panelPlaces_ = new JPanel();
//        panelPlaces_.setLayout(new GridLayout(0, 1));
//        panelPlaces_.add(new JLabel(_messages_.getVal(GO_BACK)));
//        for (int i = CustList.FIRST_INDEX; i < facade.getPlaces().size(); i++) {
//            LabelButton place_ = new LabelButton(facade.getPlaces().get(i));
//            place_.setEnabled(facade.isEnablePlace(i));
//            place_.addMouseListener(new SelectPlaceGoBackEvent(this, i));
//            panelPlaces_.add(place_);
//        }
//        panelOptions.add(panelPlaces_, BorderLayout.CENTER);
        mapPanel.init(facade, this);
        JPanel box_ = new JPanel();
        box_.setLayout(new BoxLayout(box_, BoxLayout.PAGE_AXIS));
        box_.add(new JLabel(_messages_.getVal(GO_BACK)));
        chosenCity = new LabelButton();
        chosenCity.setBackground(box_.getBackground());
        chosenCity.setForeground(box_.getForeground());
        box_.add(chosenCity);
        JPanel line_ = new JPanel();
        //avoid vertical spaces between tiles in map
        line_.add(mapPanel);
        line_.add(new JLabel(DataBase.EMPTY_STRING));
        box_.add(line_);
        LabelButton ok_ = new LabelButton(MainWindow.OK);
        ok_.addMouseListener(new ChoosePlaceEvent(this));
        box_.add(ok_);
        panelOptions.add(box_, BorderLayout.CENTER);
        //panelOptions
        addExit();
        window.pack();
    }

    public void choosePlace(int _place) {
        window.setSavedGame(false);
        facade.choosePlace(_place);
        drawGameWalking(false);
        scene.repaint();
        exitInteractionPack();
    }

    public void choosePlace() {
        facade.choosePlace();
        if (facade.getMiniMapCoords().getXcoords() != CustList.INDEX_NOT_FOUND_ELT) {
            return;
        }
        window.setSavedGame(false);
        drawGameWalking(false);
        scene.repaint();
        exitInteractionPack();
    }

    public void choosePlace(int _x, int _y) {
        facade.setMiniMapCoords(_x, _y);
        chosenCity.setTextAndSize(facade.getChosenCity());
        window.pack();
    }

    //called while connection to a server succeeds.
    public void setNetworkPanel() {
        window.getZipLoad().setEnabled(false);
        window.getGameLoad().setEnabled(false);
        window.getNewGame().setEnabled(false);
        facade.openMenu();
        panelMenu.setVisible(false);
        panelNetWork = new JPanel();
        panelNetWork.setLayout(new BoxLayout(panelNetWork, BoxLayout.PAGE_AXIS));
        panelOptions.add(panelNetWork, BorderLayout.CENTER);
        LabelButton exit_ = new LabelButton(_messages_.getVal(EXIT));
        exit_.addMouseListener(new ExitTradeEvent(window));
        if (window.getIndexInGame() == CustList.FIRST_INDEX) {
            JPanel panel_ = new JPanel();
            LabelButton trade_ = new LabelButton(_messages_.getVal(TRADE));
            trade_.addMouseListener(new ValidateTradingEvent(window));
            panel_.add(trade_);
            panel_.add(exit_);
            panelOptions.add(panel_, BorderLayout.SOUTH);
        } else {
            panelOptions.add(exit_, BorderLayout.SOUTH);
        }
    }

    public void setTradable(NatTreeMap<Byte, PokemonPlayer> _team) {
        NatTreeMap<Byte,UsablePokemon> teamPks_ = new NatTreeMap<Byte,UsablePokemon>();
        for (EntryCust<Byte, PokemonPlayer> e: _team.entryList()) {
            teamPks_.put(e.getKey(), e.getValue());
        }
        teamPan = new TeamPanel(2, _messages_.getVal(POKEMON_SELECT), facade, teamPks_, true);
        teamPan.addListenerTrading(this);
        panelNetWork.add(teamPan);
        JPanel group_ = new JPanel();
        group_.setLayout(new BorderLayout());
        group_.add(new JLabel(_messages_.getVal(RECEIVED_POKEMON)), BorderLayout.NORTH);
        receivedPk = new SessionEditorPane();
        receivedPk.setEditable(false);
        receivedPk.getCaret().setSelectionVisible(false);
        receivedPk.setLanguage(facade.getLanguage());
        receivedPk.setDataBase(facade);
//        receivedPk.setFiles(facade.getData().getWebPk(), Resources.ACCESS_TO_DEFAULT_FILES);
        receivedPk.setFiles(Resources.ACCESS_TO_DEFAULT_FILES);
        receivedPk.setFrame(window);
        ToolTipManager.sharedInstance().registerComponent(receivedPk);
//        receivedPk.prepare();
        JPanel panel_ = new JPanel();
        panel_.setLayout(new BoxLayout(panel_, BoxLayout.PAGE_AXIS));
        JScrollPane scrollSession_ = new JScrollPane(receivedPk);
        scrollSession_.setPreferredSize(new Dimension(400, 300));
        group_.add(scrollSession_, BorderLayout.CENTER);
        panelNetWork.add(group_);
        enabledReady = false;
        readyCheck = new JCheckBox(_messages_.getVal(READY));
        readyCheck.setEnabled(enabledReady);
        readyCheck.addActionListener(new ReadyEvent(window, readyCheck));
        panelNetWork.add(readyCheck);
    }

    public void setTradableAfterTrading(NatTreeMap<Byte, PokemonPlayer> _team) {
        enabledClick = false;
        NatTreeMap<Byte,UsablePokemon> teamPks_ = new NatTreeMap<Byte,UsablePokemon>();
        for (EntryCust<Byte, PokemonPlayer> e: _team.entryList()) {
            teamPks_.put(e.getKey(), e.getValue());
        }
        teamPan.initFighters(teamPks_);
        readyCheck.setEnabled(false);
        enabledReady = false;
        readyCheck.setSelected(false);
        enabledClick = true;
    }

    public void seeNetPokemonDetail() {
        receivedPk.initializeHtml(Resources.ACCESS_TO_DEFAULT_PK);
//        try {
////            if (window.isSuccessfulCompile()) {
////                receivedPk.initialize(Resources.CONFIG_PK);
////            } else {
////                receivedPk.initialize(Resources.ACCESS_TO_DEFAULT_PK);
////            }
////            receivedPk.initializeOnlyConf(Resources.ACCESS_TO_DEFAULT_PK);
//            receivedPk.initializeHtml(Resources.ACCESS_TO_DEFAULT_PK);
//        } catch (Throwable _0) {
//            _0.printStackTrace();
//        }
    }

    public void interact() {
        endGame.setText(Game.getEndGameMessage());
        endGame.setVisible(facade.isShowEndGame());
        placeName.setText(facade.getCurrentPlace());
        initInteraction();
        if (facade.getInterfaceType() != InterfaceType.RIEN) {
            buttonInteract.setEnabledLabel(true);
        } else if (interaction != null) {
            buttonInteract.setEnabledLabel(false);
        }
        setTextArea(facade.getGame().getCommentGame().getMessages().join(RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
        setPaintingScene(false);
    }

    public void repaintInteraction() {
        if (interaction != null) {
            interaction.revalidate();
            interaction.repaint();
        }
    }

    private void initInteraction() {
        if (interaction != null) {
            return;
        }
        interaction = new JPanel();
        buttonInteract = new LabelButton(_messages_.getVal(INTERACT));
        buttonInteract.addMouseListener(new InteractSceneEvent(this));
        interaction.add(buttonInteract);
        fish = new LabelButton(_messages_.getVal(FISH));
        fish.addMouseListener(new FishingEvent(this));
        interaction.add(fish);
        commentsWalking = new WrappedTextArea(4, 32);
        commentsWalking.setEditable(false);
        //interaction.add(new JScrollPane(commentsWalking));
    }

    public void interactScene() {
        if (!buttonInteract.isEnabled()) {
            return;
        }
        facade.interact();
        if (!facade.isChangeToFightScene()) {
            setTextArea(facade.getGame().getCommentGame().getMessages().join(RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
        }
        showOptions();
        window.pack();
    }

    public void fishing() {
        if (facade.isFishArea()) {
            facade.initFishing();
            if (facade.isChangeToFightScene()) {
                window.setSavedGame(false);
                window.setFight(true, true, true);
            }
        }
    }

    private void showOptions() {
        panelOptions.removeAll();
        if (facade.getInterfaceType() == InterfaceType.ECH_BOITE) {
            selectedForSwitch = new JLabel();
            JPanel storage_ = new JPanel();
            storage_.setLayout(new GridLayout(0, 1));
            selectPkBox = new LabelButton(_messages_.getVal(SELECT_PK_BOX));
            selectPkBox.addMouseListener(new SelectPokemonBoxEvent(this));
            storage_.add(selectPkBox);
            selectEggBox = new LabelButton(_messages_.getVal(SELECT_EGG_BOX));
            selectEggBox.addMouseListener(new SelectEggBoxEvent(this));
            storage_.add(selectEggBox);
            Separator sep_ = new Separator();
            storage_.add(sep_);
            takeItem = new LabelButton(_messages_.getVal(TAKE_ITEM));
            takeItem.setEnabledLabel(false);
            takeItem.addMouseListener(new GearStorageEvent(this, StorageActions.TAKE_ITEM_BOX));
            storage_.add(takeItem);
            store = new LabelButton(_messages_.getVal(STORE));
            store.setEnabledLabel(false);
            store.addMouseListener(new GearStorageEvent(this, StorageActions.STORE));
            storage_.add(store);
            withdraw = new LabelButton(_messages_.getVal(WITHDRAW_PK));
            withdraw.setEnabledLabel(false);
            withdraw.addMouseListener(new GearStorageEvent(this, StorageActions.WIDRAW_PK));
            storage_.add(withdraw);
            withdrawEgg = new LabelButton(_messages_.getVal(WITHDRAW_EGG));
            withdrawEgg.setEnabledLabel(false);
            withdrawEgg.addMouseListener(new GearStorageEvent(this, StorageActions.WIDRAW_EGG));
            storage_.add(withdrawEgg);
            switchPk = new LabelButton(_messages_.getVal(SWITCH_PK_EGG));
            switchPk.setEnabledLabel(false);
            switchPk.addMouseListener(new GearStorageEvent(this, StorageActions.SWITCH_TEAM_BOX));
            storage_.add(switchPk);
            sep_ = new Separator();
            storage_.add(sep_);
            release = new LabelButton(_messages_.getVal(RELEASE));
            release.setEnabledLabel(false);
            release.addMouseListener(new GearStorageEvent(this, StorageActions.RELEASE));
            storage_.add(release);
            storage_.add(selectedForSwitch);
            JPanel set_ = new JPanel();
            initTeam();
            teamPan.addListenerStorage(this);
            set_.add(teamPan);
            set_.add(storage_);
            panelOptions.add(set_, BorderLayout.CENTER);
            panelMenu.setVisible(false);
        } else if (facade.getInterfaceType() == InterfaceType.MOVE_TUTORS) {
            initPkTeamMoveTutors();
            JPanel set_ = new JPanel();
            set_.add(teamPan);
            movesLearnt = new JPanel();
            movesLearnt.setLayout(new GridLayout(0,1));
            JScrollPane scroll_ = new JScrollPane(movesLearnt);
            scroll_.setPreferredSize(new Dimension(100, 220));
            set_.add(scroll_);
            panelOptions.add(set_, BorderLayout.CENTER);
            panelMenu.setVisible(false);
        } else if (facade.getInterfaceType() == InterfaceType.ACHATS_CT) {
            tmPanel = new TmPanel(5, _messages_.getVal(TM_TITLE), facade);
            JPanel set_ = new JPanel();
            set_.setLayout(new BoxLayout(set_, BoxLayout.PAGE_AXIS));
            LabelButton selectItem_ = new LabelButton(_messages_.getVal(TM_SELECT));
            selectItem_.addMouseListener(new AddTmEvent(this));
            set_.add(selectItem_);
            LabelButton removeItem_ = new LabelButton(_messages_.getVal(TM_REMOVE));
            removeItem_.addMouseListener(new RemoveTmEvent(this));
            set_.add(removeItem_);
            set_.add(tmPanel);
            LabelButton changeInv_ = new LabelButton(_messages_.getVal(TM_BUY));
            changeInv_.addMouseListener(new BuyTmEvent(this));
            set_.add(changeInv_);
            panelOptions.add(set_, BorderLayout.CENTER);
            panelMenu.setVisible(false);
        } else if (facade.getInterfaceType() == InterfaceType.ACHATS) {
            JPanel set_ = new JPanel();
            set_.setLayout(new BoxLayout(set_, BoxLayout.PAGE_AXIS));
            buy = new JCheckBox(_messages_.getVal(ITEM_BUY));
            buy.setSelected(true);
            buy.addActionListener(new BuyOrSellEvent(this));
            set_.add(buy);
            itemsPan = new ItemsPanel(2, _messages_.getVal(ITEM_TITLE), facade);
            LabelButton selectItem_ = new LabelButton(_messages_.getVal(ITEM_SELECT));
            selectItem_.addMouseListener(new SelectItemForListEvent(this));
            set_.add(selectItem_);
            LabelButton addItem_ = new LabelButton(_messages_.getVal(ITEM_ADD));
            addItem_.addMouseListener(new ChangeItemListEvent(this, true));
            set_.add(addItem_);
            LabelButton removeItem_ = new LabelButton(_messages_.getVal(ITEM_REMOVE));
            removeItem_.addMouseListener(new ChangeItemListEvent(this, false));
            set_.add(removeItem_);
            set_.add(itemsPan);
            LabelButton changeInv_ = new LabelButton(_messages_.getVal(ITEM_BUY_SELL));
            changeInv_.addMouseListener(new BuyItemsEvent(this));
            set_.add(changeInv_);
            panelOptions.add(set_, BorderLayout.CENTER);
            panelMenu.setVisible(false);
        } else if (facade.getInterfaceType() == InterfaceType.PENSION) {
            JPanel set_ = new JPanel();
            NatTreeMap<Byte,UsablePokemon> teamPks_ = new NatTreeMap<Byte,UsablePokemon>();
            NatTreeMap<Byte, PokemonPlayer> team_ = facade.getGame().getPlayer().getPokemonPlayerList();
            for (EntryCust<Byte, PokemonPlayer> e: team_.entryList()) {
                teamPks_.put(e.getKey(), e.getValue());
            }
            teamPan = new TeamPanel(2, _messages_.getVal(POKEMON_SELECT_TWO), facade, teamPks_, false);
            teamPan.addListenerHost(this);
            set_.add(teamPan);
            JPanel form_ = new JPanel();
            form_.setLayout(new BoxLayout(form_, BoxLayout.PAGE_AXIS));
            int nbRemSteps_ = facade.getRemaingingSteps();
            String buttonText_= StringList.simpleNumberFormat(_messages_.getVal(GET_EGG), nbRemSteps_);
            LabelButton receiveEgg_ = new LabelButton(buttonText_);
            receiveEgg_.addMouseListener(new ReceiveFromHostEvent(this, true));
            form_.add(receiveEgg_);
            buttonText_= StringList.simpleNumberFormat(_messages_.getVal(GET_EGG_PARENT), nbRemSteps_);
            LabelButton receiveParents_ = new LabelButton(buttonText_);
            receiveParents_.addMouseListener(new ReceiveFromHostEvent(this, false));
            form_.add(receiveParents_);
            LabelButton hostPk_ = new LabelButton(_messages_.getVal(HOST_PK));
            hostPk_.addMouseListener(new HostPokemonEvent(this));
            form_.add(hostPk_);
            set_.add(form_);
            panelOptions.add(set_, BorderLayout.CENTER);
            panelMenu.setVisible(false);
        } else if (facade.getInterfaceType() == InterfaceType.FOSSILE) {
            window.setSavedGame(false);
        } else if (facade.isChangeToFightScene()) {
            window.setSavedGame(false);
            window.setFight(true, true, false);
            return;
        }
        addExit();
    }

//    private StringList getMessagesStorage() {
//        StringList messages_;
//        messages_ = new StringList();
//        if (facade.getSelectedPokemonFirstBox() != null) {
//            messages_.add(_messages_.getVal(SELECTED_PK));
//        } else {
//            messages_.add(_messages_.getVal(NOT_SELECTED_PK));
//        }
//        if (facade.getSelectedEggBox() != null) {
//            messages_.add(_messages_.getVal(SELECTED_EGG));
//        } else {
//            messages_.add(_messages_.getVal(NOT_SELECTED_EGG));
//        }
//        return messages_;
//    }

    public void hostPokemon() {
        facade.attemptForStoringPokemonToHost();
        boolean exited_ = false;
        String info_ = facade.getGame().getCommentGame().getMessages().join(RETURN_LINE);
        if (facade.getFirstSelectPkToHost() == CustList.INDEX_NOT_FOUND_ELT) {
            if (facade.getSecondSelectPkToHost() == CustList.INDEX_NOT_FOUND_ELT) {
                window.setSavedGame(false);
                exited_ = true;
                setTextArea(info_, JOptionPane.INFORMATION_MESSAGE);
                exitInteractionPack();
            }
        }
        if (!exited_) {
            setTextArea(info_, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void receiveFromHost(boolean _onlyEgg) {
        facade.receiveFromHost(_onlyEgg);
        window.setSavedGame(false);
        String info_ = facade.getGame().getCommentGame().getMessages().join(RETURN_LINE);
        if (!facade.getGame().isReinitInteraction()) {
            setTextArea(info_, JOptionPane.INFORMATION_MESSAGE);
            exitInteractionPack();
        } else {
            setTextArea(info_, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addTmToBuy() {
        SelectTm.setSelectTm(window, facade, true);
        boolean isSelectedIndex_ = SelectTm.isSelectedIndex();
        boolean ok_ = SelectTm.isOk();
        if (!ok_) {
            return;
        }
        if (!isSelectedIndex_) {
            return;
        }
        facade.addTmToBuy();
        tmPanel.initItems();
        window.pack();
    }

    public void removeTmToBuy() {
        if (!tmPanel.isSelected()) {
            return;
        }
        facade.removeTmToBuy(tmPanel.getSelectedItem());
        tmPanel.initItems();
        window.pack();
    }

    public void buyTm() {
        facade.buyTm();
        if (!facade.canBeBoughtTm()) {
            setTextArea(_messages_.getVal(NO_POSSIBLE_BUY), JOptionPane.ERROR_MESSAGE);
            return;
        }
        window.setSavedGame(false);
        exitInteractionPack();
        window.pack();
    }

    public void clearItemList() {
        facade.clearItemsToBuyOrSell();
        itemsPan.initItems();
        window.pack();
    }

    public void selectItemForList() {
        SelectItem.setSelectItem(window, facade, true, !buy.isSelected());
        boolean isSelectedIndex_ = SelectItem.isSelectedIndex();
        boolean ok_ = SelectItem.isOk();
        if (!ok_) {
            return;
        }
        if (!isSelectedIndex_) {
            return;
        }
        facade.addItemToBuyOrSell();
        itemsPan.initItems();
        window.pack();
    }

    public void addOrRemoveItemToBuyOrSell(boolean _add) {
        if (!itemsPan.isSelected()) {
            return;
        }
        facade.addOrRemoveItemToBuyOrSell(itemsPan.getSelectedItem(), _add);
        itemsPan.initItems();
        window.pack();
    }

    public void buyItems() {
        facade.buyOrSellItems(buy.isSelected());
        if (!facade.canBeBought()) {
            setTextArea(_messages_.getVal(NO_POSSIBLE_BUY), JOptionPane.ERROR_MESSAGE);
            return;
        }
        window.setSavedGame(false);
        exitInteractionPack();
        window.pack();
    }

    public void selectPokemonBox() {
        int lineBack_ = facade.getLineFirstBox();
        SelectPokemon.setSelectPokemon(window, facade, true);
        SelectPokemon.isSelectedIndex();
        if (!SelectPokemon.isStaticOk()) {
            facade.setLinePokemonFirstBox(lineBack_);
        } else {
            setEnablingStoring();
//            StringList messages_;
//            messages_ = getMessagesStorage();
//            setTextArea(messages_.join(RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
        }
        facade.clearSortingFirstBox();
    }

    public void selectEggBox() {
        int lineBack_ = facade.getLineEgg();
        SelectEgg.setSelectEgg(window, facade);
        SelectEgg.isSelectedIndex();
        if (!SelectEgg.isOk()) {
            facade.setLineEggs(lineBack_);
        } else {
            setEnablingStoring();
//            StringList messages_;
//            messages_ = getMessagesStorage();
//            setTextArea(messages_.join(RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
        }
        facade.clearSortingEgg();
    }

    public void gearStorage(StorageActions _action) {
        window.setSavedGame(false);
        facade.gearStorage(_action);
        refreshTeam();
        setEnablingStoring();
        window.pack();
    }

    private void setEnablingStoring() {
        selectPkBox.setEnabledLabel(true);
        selectEggBox.setEnabledLabel(true);
        takeItem.setEnabledLabel(facade.canTakeItemFromStorage());
        store.setEnabledLabel(facade.isSelectedPkTeamStorage());
        //cancelSelect.setEnabledLabel(true);
        withdraw.setEnabledLabel(facade.getSelectedPokemonFirstBox() != null);
        withdrawEgg.setEnabledLabel(facade.getSelectedEggBox() != null);
//        boolean selectedBox_ = false;
//        if (facade.getSelectedPokemonFirstBox() != null) {
//            selectedBox_ = true;
//        }
//        if (facade.getSelectedEggBox() != null) {
//            selectedBox_ = true;
//        }
//        switchPk.setEnabledLabel(selectedBox_ && facade.isSelectedPkTeamStorage());
        switchPk.setEnabledLabel(facade.isSwitchable());
        release.setEnabledLabel(facade.isReleasable());
    }

//    private void cancelChosenTeamPokemon() {
//        facade.cancelChosenTeamPokemon();
//        enabledClick = false;
//        teamPan.deselect();
//        enabledClick = true;
//    }

    private void initPkTeamMoveTutors() {
        NatTreeMap<Byte,PokemonPlayer> team_ = facade.getPlayer().getPokemonPlayerList();
        NatTreeMap<Byte,UsablePokemon> pks_ = new NatTreeMap<Byte,UsablePokemon>();
        for (EntryCust<Byte, PokemonPlayer> e: team_.entryList()) {
            pks_.put(e.getKey(), e.getValue());
        }
        teamPan = new TeamPanel(2, _messages_.getVal(POKEMON_SELECT), facade, pks_, true);
        teamPan.addListenerMoveTutor(this);
    }

    private void refreshPkTeamMoveTutors() {
        enabledClick = false;
        NatTreeMap<Byte,PokemonPlayer> team_ = facade.getPlayer().getPokemonPlayerList();
        NatTreeMap<Byte,UsablePokemon> pks_ = new NatTreeMap<Byte,UsablePokemon>();
        for (EntryCust<Byte, PokemonPlayer> e: team_.entryList()) {
            pks_.put(e.getKey(), e.getValue());
        }
        teamPan.initFighters(pks_);
        enabledClick = true;
    }

    private void initTeam() {
        NatTreeMap<Byte,UsablePokemon> pks_ = new NatTreeMap<Byte,UsablePokemon>();
        byte i_ = CustList.FIRST_INDEX;
        for (UsablePokemon p: facade.getPlayer().getTeam()) {
            pks_.put(i_, p);
            i_ ++;
        }
        teamPan = new TeamPanel(2, _messages_.getVal(POKEMON_SELECT), facade, pks_, true);
    }

    private void refreshTeam() {
        enabledClick = false;
        NatTreeMap<Byte,UsablePokemon> pks_ = new NatTreeMap<Byte,UsablePokemon>();
        byte i_ = CustList.FIRST_INDEX;
        for (UsablePokemon p: facade.getPlayer().getTeam()) {
            pks_.put(i_, p);
            i_ ++;
        }
        teamPan.initFighters(pks_);
        enabledClick = true;
    }

    private void addButtonsTeam() {
        JPanel set_ = new JPanel();
        JPanel teamMenu_ = new JPanel();
        teamMenu_.setLayout(new BoxLayout(teamMenu_, BoxLayout.PAGE_AXIS));
        switchUsable = new JCheckBox(_messages_.getVal(SWITCH_PK_TEAM));
//        enabledSwitchTeam = false;
//        switchUsable.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent _arg0) {
//                enabledSwitchTeam = !enabledSwitchTeam;
//            }
//        });
        teamMenu_.add(switchUsable);
        takeItemTeam = new LabelButton(_messages_.getVal(TAKE_ITEM_TEAM));
        takeItemTeam.setEnabledLabel(false);
        takeItemTeam.addMouseListener(new TakeItemFromTeamEvent(this));
        teamMenu_.add(takeItemTeam);
        detailPk = new LabelButton(_messages_.getVal(DETAIL_TEAM));
        detailPk.setEnabledLabel(false);
        detailPk.addMouseListener(new SeePokemonDetailEvent(this));
        teamMenu_.add(detailPk);
        healPk = new LabelButton(_messages_.getVal(HEAL_PK));
        healPk.setEnabledLabel(false);
        healPk.addMouseListener(new HealPokemonEvent(this));
        teamMenu_.add(healPk);
        nicknamePk = new LabelButton(_messages_.getVal(NICKNAME));
        nicknamePk.setEnabledLabel(false);
        nicknamePk.addMouseListener(new ChangeNicknameEvent(this));
        teamMenu_.add(nicknamePk);
        set_.add(teamPan);
        set_.add(teamMenu_);
        panelOptions.add(set_, BorderLayout.CENTER);
    }

    public void takeItemFromTeam() {
        facade.takeObjectFromTeam();
        window.setSavedGame(false);
        setTextArea(facade.getComment().getMessages().join(RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
        refreshTeam();
        //disable the button after taking
        takeItemTeam.setEnabledLabel(false);
        window.pack();
    }

    public void seePokemonDetail() {
//        if (window.showErrorMessageDialog(ForwardingJavaCompiler.getMess(Constants.getLanguage()))) {
//            return;
//        }
//        UsablePokemon p_ = facade.getSelectedPkTeam();
//        if (!(p_ instanceof PokemonPlayer)) {
//            return;
//        }
        SessionEditorPane session_;
        session_ = new SessionEditorPane();
        session_.getCaret().setSelectionVisible(false);
        session_.setLanguage(facade.getLanguage());
        session_.setDataBase(facade);
//        session_.setFiles(facade.getData().getWebPk(), Resources.ACCESS_TO_DEFAULT_FILES);
        session_.setFiles(Resources.ACCESS_TO_DEFAULT_FILES);
//        try {
//            session_.setFiles(facade.getData().getWebPk());
//            if (window.isSuccessfulCompile()) {
//                session_.initialize(Resources.CONFIG_PK);
//            } else {
//                session_.initialize(Resources.ACCESS_TO_DEFAULT_PK);
//            }
//        } catch (Exception e_) {
//            e_.printStackTrace();
//        }
//        try {
//            session_.initialize(Resources.CONFIG_PK);
//        } catch (Exception e_) {
//            try {
//                CompilingBeans.loadDefaultClassesAndClear();
//                session_.setRelativeFiles(Resources.ACCESS_TO_DEFAULT_FILES);
//                session_.initialize(Resources.ACCESS_TO_DEFAULT_PK);
//            } catch (Exception e) {
//                e.printStackTrace();
//                return;
//            }
//        }
        showHtmlDialog(window, session_);
    }

    public void healPokemon() {
        int lineBack_ = facade.getLineHealingItem();
        SelectHealingItem.setSelectHealingItem(window, facade);
        boolean isSelectedIndex_ = SelectHealingItem.isSelectedIndex();
        boolean ok_ = SelectHealingItem.isOk();
        if (!ok_) {
            facade.setLineHealingItem(lineBack_);
            facade.clearSortingHealingItem();
        } else if (isSelectedIndex_) {
            facade.setGivingObject(false);
            facade.setChosenHealingItemWalk();
            facade.clearSortingHealingItem();
            facade.getPlayer().getIndexesOfPokemonTeam().clear();
            facade.useObject();
            facade.selectPokemon((short) teamPan.getSelectedIndexSingle());
            if (facade.getPlayer().getSelectedObject().isEmpty()) {
                window.setSavedGame(false);
                setTextArea(facade.getComment().getMessages().join(RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (facade.getPlayer().getChosenMoves().isEmpty()) {
                window.setSavedGame(false);
                setTextArea(facade.getComment().getMessages().join(RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            SelectHealedMove.setSelectHealedMove(window, facade);
        } else {
            facade.clearSortingHealingItem();
        }
    }

    public void changeNickname() {
//        ConfirmDialog dial_ = new ConfirmDialog(window, DataBase.EMPTY_STRING, _messages_.getVal(NICKNAME), _messages_.getVal(NICKNAME), Constants.getLanguage());
        ConfirmDialog.showTextField(window, DataBase.EMPTY_STRING, _messages_.getVal(NICKNAME), _messages_.getVal(NICKNAME), Constants.getLanguage());
        if (ConfirmDialog.getStaticAnswer() != JOptionPane.YES_OPTION) {
            return;
        }
        facade.validateNickname(ConfirmDialog.getStaticText());
    }

    public void selectPokemonMoveTutor() {
        if (!enabledClick) {
            return;
        }
        facade.choosePokemonForMoveTutors((short) teamPan.getSelectedIndex());
        movesLearnt.removeAll();
        movesLearnt.add(new JLabel(_messages_.getVal(SELECT_MT)));
        StringList selectedMoves_ = facade.getSelectedMoves();
        for (String m: selectedMoves_) {
            String tr_ = facade.translateMove(m);
            MoveTutorCheckBox check_ = new MoveTutorCheckBox(m,tr_, true,this);
            check_.setBackground(Color.RED);
            check_.setSelected(true);
            movesLearnt.add(check_);
        }
        StringList unselectedMoves_ = facade.getUnselectedMoves();
        StringMap<Short> chosenMoves_ = facade.getPlayer().getChosenMoves();
        for (String m: unselectedMoves_) {
            String tr_ = facade.translateMove(m);
            MoveTutorCheckBox check_ = new MoveTutorCheckBox(m,tr_+SPACE+chosenMoves_.getVal(m),false,this);
            check_.setBackground(Color.WHITE);
            check_.setSelected(false);
            movesLearnt.add(check_);
        }
        LabelButton cancel_ = new LabelButton(_messages_.getVal(CANCEL_MT));
        cancel_.addMouseListener(new CancelMtEvent(this));
        movesLearnt.add(cancel_);
        LabelButton ok_ = new LabelButton(_messages_.getVal(VALIDATE_MT));
        ok_.addMouseListener(new ValidateMtEvent(this));
        movesLearnt.add(ok_);
        window.pack();
    }

    public void cancelMt() {
        facade.cancelLearningMoveOnPokemon();
        enabledClick = false;
        teamPan.deselect();
        enabledClick = true;
    }

    public void validateMt() {
        facade.learnMovesByMoveTutor();
        String comment_ = facade.getComment().getMessages().join(RETURN_LINE);
        if (facade.getPlayer().getChosenTeamPokemon() != CustList.INDEX_NOT_FOUND_ELT) {
            setTextArea(comment_, JOptionPane.ERROR_MESSAGE);
            return;
        }
        window.setSavedGame(false);
        setTextArea(comment_, JOptionPane.INFORMATION_MESSAGE);
        refreshPkTeamMoveTutors();
        movesLearnt.removeAll();
        window.pack();
    }

    public void checkMoveTutor(String _key) {
        facade.addOrDeleteMove(_key);
//        String text_ = StringList.simpleFormat(_messages_.getVal(SELECTED_MOVES), facade.getSelectedMoves().size());
//        setTextArea(text_);
    }

    public void selectPkTeamStorage() {
        if (!enabledClick) {
            return;
        }
        facade.setChosenTeamPokemon((short) teamPan.getSelectedIndex());
        setEnablingStoring();
    }

    public void selectPkTeam() {
        if (!enabledClick) {
            return;
        }
        if (switchUsable.isSelected()) {
            window.setSavedGame(false);
            facade.switchTeamOrder((short) teamPan.getSelectedIndex());
            switchUsable.setSelected(false);
//            enabledSwitchTeam = false;
            refreshTeam();
            window.pack();
            return;
        }
        facade.setChosenTeamPokemon((short) teamPan.getSelectedIndex());
        if (detailPk != null) {
            detailPk.setEnabledLabel(facade.isSelectedTeamPokemon());
        }
        if (takeItemTeam != null) {
//            takeItemTeam.setEnabledLabel(facade.isSelectedTeamPokemon());
            takeItemTeam.setEnabledLabel(facade.isSelectedTeamPokemonItem());
        }
        if (healPk != null) {
            healPk.setEnabledLabel(facade.isSelectedTeamPokemon());
        }
        if (nicknamePk != null) {
            nicknamePk.setEnabledLabel(facade.isSelectedTeamPokemon());
        }
    }

    private void addExit() {
        LabelButton exit_ = new LabelButton(_messages_.getVal(EXIT));
        exit_.addMouseListener(new ExitInteractionEvent(this));
        panelOptions.add(exit_, BorderLayout.SOUTH);
    }

    public void exitInteractionPack() {
        exitInteraction();
        window.pack();
    }

    public void exitInteraction() {
        panelOptions.removeAll();
        if (switchUsable != null) {
            switchUsable.setSelected(false);
        }
//        enabledSwitchTeam = false;
        panelMenu.setVisible(true);
        facade.exitInteract();
    }

    private static void showHtmlDialog(GroupFrame _parent, SessionEditorPane _session) {
//        DialogHtmlData.setDialogHtmlData(_parent, _messages_.getVal(TITLE_DETAIL), _session, window.isSuccessfulCompile());
        DialogHtmlData.setDialogHtmlData(_parent, _messages_.getVal(TITLE_DETAIL), _session);
    }

    public void selectPokemon() {
        if (!teamPan.isSelected()) {
            //facade.cancelUsingObjectOnPokemon();
            return;
        }
        facade.selectPokemon((short) teamPan.getSelectedIndexSingle());
        if (facade.isGivingObject()) {
            window.setSavedGame(false);
            exitInteractionPack();
            return;
        }
        if (facade.getPlayer().getSelectedObject().isEmpty()) {
            setTextArea(facade.getComment().getMessages().join(RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
            window.setSavedGame(false);
            exitInteractionPack();
            return;
        }
        boolean used_ = false;
        if (facade.usedObjectForEvolving()) {
            setMovesAbilities();
            used_ = true;
        }
        if (facade.usedObjectForHealingAmove()) {
            setHealedMoves();
            used_ = true;
        }
        if (facade.usedObjectForBoosting()) {
            setBoostedMoves();
            used_ = true;
        }
        if (!used_) {
            setTextArea(facade.getComment().getMessages().join(RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
            window.setSavedGame(false);
            exitInteractionPack();
            return;
        }
        window.pack();
    }

    private void setMovesAbilities() {
        movesLearnt.removeAll();
        movesLearnt.add(new JLabel(_messages_.getVal(SELECT_MT)));
//        Map<String,Boolean> selected_ = facade.getPlayer().getMovesToBeKeptEvo();
//        StringList kept_ = new StringList(selected_.getKeys(true));
        StringList kept_ = facade.getKeptMovesToEvo();
//        kept_.sort(new Comparator<String>() {
//            @Override
//            public int compare(String _o1, String _o2) {
//                String trOne_ = facade.translateMove(_o1);
//                String trTwo_ = facade.translateMove(_o2);
//                return trOne_.compareTo(trTwo_);
//            }
//        });
        kept_.sortElts(new TrMovesComparator(facade.getData()));
        for (String m: kept_) {
            String tr_ = facade.translateMove(m);
            MoveEvoCheckBox check_ = new MoveEvoCheckBox(m,tr_,true, this);
            check_.setBackground(Color.RED);
            movesLearnt.add(check_);
        }
//        StringList unkept_ = new StringList(selected_.getKeys(false));
        StringList unkept_ = facade.getUnKeptMovesToEvo();
//        unkept_.sort(new Comparator<String>() {
//            @Override
//            public int compare(String _o1, String _o2) {
//                String trOne_ = facade.translateMove(_o1);
//                String trTwo_ = facade.translateMove(_o2);
//                return trOne_.compareTo(trTwo_);
//            }
//        });
        unkept_.sortElts(new TrMovesComparator(facade.getData()));
        for (String m: unkept_) {
            String tr_ = facade.translateMove(m);
            MoveEvoCheckBox check_ = new MoveEvoCheckBox(m,tr_,false, this);
            check_.setBackground(Color.WHITE);
            movesLearnt.add(check_);
        }
        StringList ab_ = facade.getPlayer().getNewAbilitiesToBeChosen();
        abilities.removeAll();
        abilityLabels.clear();
        if (!ab_.isEmpty()) {
            abilities.add(new JLabel(_messages_.getVal(SELECT_ABILITY)));
            for (String a: ab_) {
                AbilityLabel lab_ = new AbilityLabel(facade.translateAbility(a), a);
                lab_.addMouseListener(new AbilityWalkEvent(this, a));
                abilities.add(lab_);
                abilityLabels.add(lab_);
            }
            abilities.add(new Separator());
        }
        LabelButton ok_ = new LabelButton(_messages_.getVal(EVOLVE));
        ok_.addMouseListener(new EvolvePokemonEvent(this));
        abilities.add(ok_);
    }

    public void evolvePokemon() {
        facade.evolvePokemon();
        String info_ = facade.getComment().getMessages().join(RETURN_LINE);
        if (!facade.usedObjectForEvolving()) {
            setTextArea(info_, JOptionPane.INFORMATION_MESSAGE);
            window.setSavedGame(false);
            exitInteractionPack();
        } else {
            setTextArea(info_, JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setHealedMoves() {
        movesLearnt.removeAll();
        movesLearnt.add(new JLabel(_messages_.getVal(SELECT_HEAL_MOVE)));
        StringMap<Short> moves_ = facade.getPlayer().getChosenMoves();
        StringList keys_ = new StringList(moves_.getKeys());
//        keys_.sort(new Comparator<String>() {
//            @Override
//            public int compare(String _o1, String _o2) {
//                String trOne_ = facade.translateMove(_o1);
//                String trTwo_ = facade.translateMove(_o2);
//                return trOne_.compareTo(trTwo_);
//            }
//        });
        keys_.sortElts(new TrMovesComparator(facade.getData()));
        for (String m: keys_) {
            String tr_ = facade.translateMove(m);
            LabelButton check_ = new LabelButton(tr_+SPACE+moves_.getVal(m));
            check_.addMouseListener(new HealMoveEvent(this, m));
            check_.setBackground(Color.WHITE);
            movesLearnt.add(check_);
        }
    }

    private void setBoostedMoves() {
        movesLearnt.removeAll();
        movesLearnt.add(new JLabel(_messages_.getVal(SELECT_BOOST_MOVE)));
        StringMap<Short> moves_ = facade.getPlayer().getChosenMoves();
        StringList keys_ = new StringList(moves_.getKeys());
//        keys_.sort(new Comparator<String>() {
//            @Override
//            public int compare(String _o1, String _o2) {
//                String trOne_ = facade.translateMove(_o1);
//                String trTwo_ = facade.translateMove(_o2);
//                return trOne_.compareTo(trTwo_);
//            }
//        });
        keys_.sortElts(new TrMovesComparator(facade.getData()));
        for (String m: keys_) {
            String tr_ = facade.translateMove(m);
            LabelButton check_ = new LabelButton(tr_+SPACE+moves_.getVal(m));
            check_.addMouseListener(new BoostMoveEvent(this, m));
            check_.setBackground(Color.WHITE);
            movesLearnt.add(check_);
        }
    }

    public void learnMove(String _move) {
        facade.addOrDeleteMoveEvo(_move);
    }

    public void healMove(String _move) {
        facade.healMove(_move);
        window.setSavedGame(false);
        setTextArea(facade.getPlayer().getCommentGame().getMessages().join(RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
        exitInteractionPack();
    }

    public void boostMove(String _move) {
        facade.gainPpMax(_move);
        window.setSavedGame(false);
        setTextArea(facade.getPlayer().getCommentGame().getMessages().join(RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
        exitInteractionPack();
    }

    public void getAbility(String _ability) {
        facade.getPlayer().setChosenAbilityForEvolution(_ability);
        for (AbilityLabel l: abilityLabels) {
            l.setSelected(_ability);
            l.repaint();
        }
    }

    public void selectPokemonHost() {
        if (!enabledClick) {
            return;
        }
        enabledClick = false;
        facade.setSelectPkToHost((short) teamPan.getSelectedIndex());
        enabledClick = true;
    }

    public void selectPokemonTm() {
        facade.choosePokemonForLearningMove((byte) teamPan.getSelectedIndex());
        if (facade.getPlayer().getSelectedMove().isEmpty()) {
            window.setSavedGame(false);
            setTextArea(facade.getComment().getMessages().join(RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
            exitInteractionPack();
            return;
        }
        movesLearnt.removeAll();
        movesLearnt.add(new JLabel(_messages_.getVal(SELECT_TM)));
        StringMap<Short> moves_ = facade.getPlayer().getChosenMoves();
        StringList keys_ = new StringList(moves_.getKeys());
//        keys_.sort(new Comparator<String>() {
//            @Override
//            public int compare(String _o1, String _o2) {
//                String trOne_ = facade.translateMove(_o1);
//                String trTwo_ = facade.translateMove(_o2);
//                return trOne_.compareTo(trTwo_);
//            }
//        });
        keys_.sortElts(new TrMovesComparator(facade.getData()));
        for (String m: keys_) {
            String tr_ = facade.translateMove(m);
            LabelButton check_ = new LabelButton(tr_+SPACE+moves_.getVal(m));
            check_.addMouseListener(new LearntMoveEvent(this, m));
            check_.setBackground(Color.WHITE);
            movesLearnt.add(check_);
        }
        window.pack();
    }

    public void learntMove(String _move) {
        facade.learnMove(_move);
        window.setSavedGame(false);
        setTextArea(facade.getComment().getMessages().join(RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
        exitInteractionPack();
    }

    public boolean isMenusVisible() {
        if (panelMenu == null) {
            return true;
        }
        if (scene == null) {
            return true;
        }
        return panelMenu.isVisible();
    }

    public void selectPkTeamTrade() {
        if (!enabledClick) {
            return;
        }
        if (readyCheck.isSelected()) {
            return;
        }
        facade.setIndexTeamTrading(teamPan.getSelectedIndexSingle());
        if (facade.isSelectedIndexTeamTrading()) {
            enabledReady = true;
            SentPokemon sent_;
            sent_ = new SentPokemon();
            sent_.setIndex(window.getIndexInGame());
            sent_.setPokemon(facade.getSentPokemon());
            window.sendObject(sent_);
        } else {
            enabledReady = false;
            //disable ready button
        }
        readyCheck.setEnabled(enabledReady);
    }

    public void setTextArea(String _text, int _messageType) {
        if (_text.isEmpty()) {
            return;
        }
        commentsWalking.setText(_text);
        ConfirmDialog.showComponent(window, new JScrollPane(commentsWalking), _messages_.getVal(TITLE_COMMENTS), Constants.getLanguage(), _messageType);
    }

    public Scene getScene() {
        return scene;
    }

    public boolean isPaintingScene() {
        return paintingScene;
    }

    public void setPaintingScene(boolean _paintingScene) {
        paintingScene = _paintingScene;
        team.setEnabledLabel(!_paintingScene);
        items.setEnabledLabel(!_paintingScene);
        tm.setEnabledLabel(!_paintingScene);
//        difficulty.setEnabledLabel(!_paintingScene);
        fish.setEnabledLabel(!_paintingScene && facade.isFishArea());
        seeBoxes.setEnabledLabel(!_paintingScene);
        seeEggs.setEnabledLabel(!_paintingScene);
        host.setEnabledLabel(!_paintingScene);
        game.setEnabledLabel(!_paintingScene);
        goBack.setEnabledLabel(!_paintingScene);
        server.setEnabledLabel(!_paintingScene);
        fish.repaint();
        panelMenu.repaint();
    }
}
