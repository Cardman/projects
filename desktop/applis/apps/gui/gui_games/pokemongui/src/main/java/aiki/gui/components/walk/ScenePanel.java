package aiki.gui.components.walk;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import aiki.beans.PokemonStandards;
import aiki.comparators.TrMovesComparator;
import aiki.db.DataBase;
import aiki.gui.threads.PreparedRenderedPages;
import aiki.sml.Resources;
import aiki.facade.FacadeGame;
import aiki.facade.enums.StorageActions;
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
import code.gui.*;
import code.gui.document.RenderedPage;
import code.maths.LgInt;
import code.network.NetCreate;
import code.network.SocketResults;
import code.network.enums.ErrorHostConnectionType;
import code.network.enums.IpType;
import code.sml.stream.ExtractFromFiles;
import code.stream.StreamFolderFile;
import code.util.CustList;
import code.util.EntryCust;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public class ScenePanel {

    private static final String SCENE_PANEL = "aiki.gui.components.walk.scenepanel";

    private static final String LOCALHOST_OLD_IP = "127.0.0.1";

    private static final String LOCALHOST_NEW_IP = "::1";

    private static final String RETURN_LINE = "\n";

    private static final String CST_TEAM = "team";

    private static final String CST_ITEMS = "items";

    private static final String CST_TM = "tm";

    private static final String CST_FISH = "fish";

    private static final String SEE_POKEMON = "seePokemon";

    private static final String SEE_EGG = "seeEgg";

    private static final String SEE_HOSTED = "seeHosted";

    private static final String SEE_GAME = "seeGame";

    private static final String GO_BACK_MENU = "goBackMenu";

    private static final String CST_SERVER = "server";

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

    private static final String TAKE_ITEM = "takeItem";

    private static final String CST_STORE = "store";

    private static final String WITHDRAW_PK = "withdrawPk";

    private static final String WITHDRAW_EGG = "withdrawEgg";

    private static final String SWITCH_PK_EGG = "siwtchPkEgg";

    private static final String CST_RELEASE = "release";

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

    private static final String SPACE = " ";

    private static final String BUG = "bug";

    private static final String UNKNOWN_HOST = "unknownHost";

    private static final String NOT_CONNECTED = "notConnected";

    private static final String TITLE_COMMENTS = "titleComments";

    private static final String CLICK_SCENE = "clickScene";

    private StringMap<String> messages = new StringMap<String>();

    private final MainWindow window;

    private Panel panelMenu;

    private final TextLabel endGame = new TextLabel("");

    private TextLabel useKeyPad;

    private LabelButton team;

    private LabelButton items;

    private LabelButton tm;

    private LabelButton fish;

    private LabelButton seeBoxes;

    private LabelButton seeEggs;

    private LabelButton host;

    private LabelButton game;

    private LabelButton goBack;

    private LabelButton server;

    private Panel movesLearnt;

    private Panel abilities;

    private final CustList<AbilityLabel> abilityLabels = new CustList<AbilityLabel>();

    private final Panel panelOptions;

    private TeamPanel teamPan;
    private StringMap<String> messagesTeamPanel = new StringMap<String>();

    private ItemsPanel itemsPan;

    private TmPanel tmPanel;

    private CustCheckBox buy;

    private LabelButton selectPkBox;

    private LabelButton selectEggBox;

    private LabelButton takeItem;

    private LabelButton store;

    private LabelButton release;

    private LabelButton withdraw;

    private LabelButton withdrawEgg;

    private LabelButton switchPk;

    private TextLabel selectedForSwitch;

    private final FacadeGame facade;

    private Panel beginGame;

    private Panel sceneInteract;

    private TextLabel placeName;

    private Scene scene;

    private KeyPadListener keyPadListener;

    private Panel interaction;

    private LabelButton buttonInteract;

    private Pad pad;

    private final TextLabel time;

    private CustCheckBox switchUsable;

    private LabelButton takeItemTeam;

    private LabelButton detailPk;

    private LabelButton healPk;

    private LabelButton nicknamePk;

    private boolean enabledClick = true;

    private RenderedPage receivedPk;

    private Panel panelNetWork;

    private boolean enabledReady;

    private CustCheckBox readyCheck;

    private final MapPanel mapPanel;

    private LabelButton chosenCity;

    private final AtomicBoolean paintingScene = new AtomicBoolean();

    private final Panel component = Panel.newLineBox();

    public ScenePanel(MainWindow _window, FacadeGame _facade) {
        facade = _facade;
        window = _window;
        Panel panelHoriz_ = Panel.newLineBox();
        initMenu();
        panelHoriz_.add(panelMenu);
        panelOptions = Panel.newBorder();
        panelHoriz_.add(panelOptions);
        component.add(panelHoriz_);
        time = new TextLabel("");
        component.add(time);
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
        disableFishing();
    }

    public void initMessages(String _lg) {
        messages = ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _lg,SCENE_PANEL);
        messagesTeamPanel = ExtractFromFiles.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _lg, TeamPanel.TEAM_PANEL);
    }

    public void setMessages() {
        endGame.setText(facade.getEndGameMessage());
        useKeyPad.setText(messages.getVal(CLICK_SCENE));
        team.setTextAndSize(messages.getVal(CST_TEAM));
        items.setTextAndSize(messages.getVal(CST_ITEMS));
        tm.setTextAndSize(messages.getVal(CST_TM));
//        difficulty.setText(messages.getVal(DIFFICULTY));
        if (fish != null) {
            fish.setTextAndSize(messages.getVal(CST_FISH));
        }
        seeBoxes.setTextAndSize(messages.getVal(SEE_POKEMON));
        seeEggs.setTextAndSize(messages.getVal(SEE_EGG));
        host.setTextAndSize(messages.getVal(SEE_HOSTED));
        game.setTextAndSize(messages.getVal(SEE_GAME));
        goBack.setTextAndSize(messages.getVal(GO_BACK_MENU));
        server.setTextAndSize(messages.getVal(CST_SERVER));
        if (interaction != null) {
            buttonInteract.setTextAndSize(messages.getVal(INTERACT));
        }
        if (teamPan != null) {
            teamPan.translate(messagesTeamPanel);
        }
    }

    public void addBeginGame(Panel _panel) {
        panelOptions.add(_panel, BorderLayout.CENTER);
        beginGame = _panel;
        beginGame.setVisible(true);
    }

    public void drawGameWalking(boolean _setPreferredSize) {
        panelOptions.removeAll();
        endGame.setText(facade.getEndGameMessage());
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
            scene.setDimensions(facade);
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
            sceneInteract = Panel.newBorder();
        } else {
            sceneInteract.removeAll();
        }
        if (placeName == null) {
            placeName = new TextLabel("");
        }
        placeName.setText(facade.getCurrentPlace());
        sceneInteract.add(placeName, BorderLayout.NORTH);
        sceneInteract.add(scene, BorderLayout.CENTER);
        initInteraction();
        if (fish != null) {
            enableIfPossibleFishing();
        }
        sceneInteract.add(interaction, BorderLayout.SOUTH);
        buttonInteract.setEnabledLabel(facade.getInterfaceType() != InterfaceType.RIEN);
        if (wasNull_) {
            component.add(sceneInteract, IndexConstants.FIRST_INDEX);
            sceneInteract.repaintSecondChildren();
        } else {
            panelMenu.repaintSecondChildren();
            time.repaintLabel();
            sceneInteract.repaintSecondChildren();
        }
        component.validate();
        scene.setFocus();
    }

    public String getComment() {
        return StringUtil.join(facade.getComment().getMessages(), RETURN_LINE);
    }

    private void initMenu() {
        panelMenu = Panel.newPageBox();
        endGame.setVisible(false);
        endGame.setOpaque(true);
        endGame.setBackground(Color.YELLOW);
        Panel menus_ = Panel.newGrid(0,1);
        menus_.add(endGame);
        useKeyPad = new TextLabel("");
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
        panelMenu.add(pad.getContainer());
        panelMenu.setVisible(false);
    }

    public void manageTeam() {
        facade.openMenu();
        panelMenu.setVisible(false);
        disableFishing();
        initTeam();
        teamPan.addListenerTeam(this);
        addButtonsTeam();
        addExit();
        window.pack();
    }

    public void selectItemForPokemon() {
        SelectItem.setSelectItem(window, facade, false, true);
        boolean isSelectedIndex_ = SelectItem.isSelectedIndex(window.getSelectItem());
        boolean ok_ = SelectItem.isOk(window.getSelectItem());
        if (!ok_) {
            facade.clearSortingItem();
            return;
        }
        if (!isSelectedIndex_) {
            facade.clearSortingItem();
            return;
        }
        boolean give_ = SelectItem.isGive(window.getSelectItem());
        facade.setGivingObject(give_);
        facade.chooseObject();
        facade.clearSortingItem();
        if (!give_) {
            if (!facade.usedObject()) {
                //error if bad use of item
                String it_ = facade.getPlayer().getSelectedObject();
                it_ = facade.translateItem(it_);
                String message_ = StringUtil.simpleStringsFormat(messages.getVal(ERROR_USING_ITEM), it_);
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
        disableFishing();
        panelOptions.removeAll();
        ByteTreeMap<UsablePokemon> pks_ = new ByteTreeMap<UsablePokemon>();
        for (byte i: facade.getPlayer().getIndexesOfPokemonTeam()) {
            pks_.put(i, facade.getPlayer().getTeam().get(i));
        }

        Panel set_ = Panel.newLineBox();
        teamPan = new TeamPanel(2, messages.getVal(POKEMON_SELECT), facade, pks_, true, messagesTeamPanel, window.getAikiFactory().getGeneUsPkPanel().create(true));
        teamPan.addListener(this);
        set_.add(teamPan.getContainer());
        movesLearnt = Panel.newGrid(0,1);
        ScrollPane scroll_ = new ScrollPane(movesLearnt);
        scroll_.setPreferredSize(new Dimension(100, 220));
        set_.add(scroll_);
        abilities = Panel.newGrid(0,1);
        set_.add(abilities);
        panelOptions.add(set_, BorderLayout.CENTER);
        addExit();
        window.pack();
    }

    public void selectTmToLearn() {
        SelectTm.setSelectTm(window, facade, false);
        boolean isSelectedIndex_ = SelectTm.isSelectedIndex(window.getSelectTm());
        boolean ok_ = SelectTm.isOk(window.getSelectTm());
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
            String message_ = StringUtil.simpleStringsFormat(messages.getVal(NO_POSSIBLE_LEARN), move_);
            setTextArea(message_, JOptionPane.WARNING_MESSAGE);
            return;
        }
        facade.openMenu();
        panelMenu.setVisible(false);
        disableFishing();
        panelOptions.removeAll();
        ByteTreeMap<UsablePokemon> pks_ = new ByteTreeMap<UsablePokemon>();
        for (byte i: facade.getPlayer().getIndexesOfPokemonTeam()) {
            pks_.put(i, facade.getPlayer().getTeam().get(i));
        }

        Panel set_ = Panel.newLineBox();
        teamPan = new TeamPanel(2, messages.getVal(POKEMON_SELECT), facade, pks_, true, messagesTeamPanel, window.getAikiFactory().getGeneUsPkPanel().create(true));
        teamPan.addListenerTm(this);
        set_.add(teamPan.getContainer());
        movesLearnt = Panel.newGrid(0,1);
        ScrollPane scroll_ = new ScrollPane(movesLearnt);
        scroll_.setPreferredSize(new Dimension(100, 220));
        set_.add(scroll_);
        abilities = Panel.newGrid(0,1);
        set_.add(abilities);
        panelOptions.add(set_, BorderLayout.CENTER);
        addExit();
        window.pack();
    }

    public void manageNetwork() {
        String lg_ = window.getLanguageKey();
        DialogServer.setDialogServer(window);
        String ip_ = DialogServer.getIpOrHostName(window.getDialogServer());
        if (ip_ == null || ip_.isEmpty()) {
            if (DialogServer.getIpType(window.getDialogServer()) == IpType.IP_V6) {
                ip_ = LOCALHOST_NEW_IP;
            } else {
                ip_ = LOCALHOST_OLD_IP;
            }
        }
        if (!DialogServer.isChoosen(window.getDialogServer())) {
            return;
        }
        String fileName_ = StringUtil.concat(StreamFolderFile.getCurrentPath(), Resources.PORT_INI);
        int port_ = NetCreate.tryToGetPort(fileName_, Net.getPort());
        if (DialogServer.isCreate(window.getDialogServer())) {
            window.createServer(ip_, DialogServer.getIpType(window.getDialogServer()), port_);
            return;
        }
        SocketResults connected_ = window.createClient(ip_, DialogServer.getIpType(window.getDialogServer()), false, port_);
        if (connected_.getError() != ErrorHostConnectionType.NOTHING) {
            if (connected_.getError() == ErrorHostConnectionType.UNKNOWN_HOST) {
                String formatted_ = messages.getVal(UNKNOWN_HOST);
                formatted_ = StringUtil.simpleStringsFormat(formatted_, ip_);
                ConfirmDialog.showMessage(window, messages.getVal(BUG), formatted_,lg_,JOptionPane.ERROR_MESSAGE);
                return;
            }
            ConfirmDialog.showMessage(window, messages.getVal(BUG), messages.getVal(NOT_CONNECTED), lg_,JOptionPane.ERROR_MESSAGE);
            return;
        }
        window.setIndexInGame(IndexConstants.SECOND_INDEX);
    }

    public void setPlaces() {
        //facade.initPlaces();
        panelMenu.setVisible(false);
        disableFishing();
//        JPanel panelPlaces_ = new JPanel();
//        panelPlaces_.setLayout(new GridLayout(0, 1));
//        panelPlaces_.add(new JLabel(messages.getVal(GO_BACK)));
//        for (int i = CustList.FIRST_INDEX; i < facade.getPlaces().size(); i++) {
//            LabelButton place_ = new LabelButton(facade.getPlaces().get(i));
//            place_.setEnabled(facade.isEnablePlace(i));
//            place_.addMouseListener(new SelectPlaceGoBackEvent(this, i));
//            panelPlaces_.add(place_);
//        }
//        panelOptions.add(panelPlaces_, BorderLayout.CENTER);
        mapPanel.init(facade, this);
        Panel box_ =Panel.newPageBox();
        box_.add(new TextLabel(messages.getVal(GO_BACK)));
        chosenCity = new LabelButton();
        chosenCity.setBackground(box_.getBackground());
        chosenCity.setForeground(box_.getForeground());
        box_.add(chosenCity);
        Panel line_ = Panel.newLineBox();
        //avoid vertical spaces between tiles in map
        line_.add(mapPanel.getContainer());
        line_.add(new TextLabel(DataBase.EMPTY_STRING));
        box_.add(line_);
        LabelButton ok_ = new LabelButton(MainWindow.OK);
        ok_.addMouseListener(new ChoosePlaceEvent(this));
        box_.add(ok_);
        panelOptions.add(box_, BorderLayout.CENTER);
        //panelOptions
        addExit();
        window.pack();
    }

    public void choosePlace() {
        facade.choosePlace();
        if (facade.getMiniMapCoords().getXcoords() != IndexConstants.INDEX_NOT_FOUND_ELT) {
            return;
        }
        window.setSavedGame(false);
        drawGameWalking(false);
        scene.repaintLabel();
        exitInteractionPack();
    }

    public void choosePlace(int _x, int _y) {
        facade.setMiniMapCoords(_x, _y);
        chosenCity.setTextAndSize(facade.getChosenCity());
        window.pack();
    }

    //called while connection to a server succeeds.
    public void setNetworkPanel() {
        window.getFolderLoad().setEnabledMenu(false);
        window.getZipLoad().setEnabledMenu(false);
        window.getGameLoad().setEnabledMenu(false);
        window.getNewGame().setEnabledMenu(false);
        facade.openMenu();
        panelMenu.setVisible(false);
        disableFishing();
        panelNetWork = Panel.newPageBox();
        panelOptions.add(panelNetWork, BorderLayout.CENTER);
        LabelButton exit_ = new LabelButton(messages.getVal(EXIT));
        exit_.addMouseListener(new ExitTradeEvent(window));
        if (window.getIndexInGame() == IndexConstants.FIRST_INDEX) {
            Panel panel_ = Panel.newLineBox();
            LabelButton trade_ = new LabelButton(messages.getVal(TRADE));
            trade_.addMouseListener(new ValidateTradingEvent(window));
            panel_.add(trade_);
            panel_.add(exit_);
            panelOptions.add(panel_, BorderLayout.SOUTH);
        } else {
            panelOptions.add(exit_, BorderLayout.SOUTH);
        }
    }

    public void setTradable(ByteTreeMap< PokemonPlayer> _team) {
        ByteTreeMap<UsablePokemon> teamPks_ = new ByteTreeMap<UsablePokemon>();
        for (EntryCust<Byte, PokemonPlayer> e: _team.entryList()) {
            teamPks_.put(e.getKey(), e.getValue());
        }
        teamPan = new TeamPanel(2, messages.getVal(POKEMON_SELECT), facade, teamPks_, true,messagesTeamPanel, window.getAikiFactory().getGeneUsPkPanel().create(true));
        teamPan.addListenerTrading(this);
        panelNetWork.add(teamPan.getContainer());
        Panel group_ = Panel.newBorder();
        group_.add(new TextLabel(messages.getVal(RECEIVED_POKEMON)), BorderLayout.NORTH);
        ScrollPane scrollSession_ = new ScrollPane();
        receivedPk = new RenderedPage(scrollSession_, window.getFrames());
//        receivedPk.setFiles(facade.getData().getWebPk(), Resources.ACCESS_TO_DEFAULT_FILES);
        receivedPk.setFrame(window);
//        receivedPk.prepare();
        JPanel panel_ = new JPanel();
        panel_.setLayout(new BoxLayout(panel_, BoxLayout.PAGE_AXIS));
        scrollSession_.setPreferredSize(new Dimension(400, 300));
        group_.add(scrollSession_, BorderLayout.CENTER);
        panelNetWork.add(group_);
        enabledReady = false;
        readyCheck = new CustCheckBox(messages.getVal(READY));
        readyCheck.setEnabled(enabledReady);
        readyCheck.addActionListener(new ReadyEvent(window, readyCheck));
        panelNetWork.add(readyCheck);
    }

    public void setTradableAfterTrading(ByteTreeMap< PokemonPlayer> _team) {
        enabledClick = false;
        ByteTreeMap<UsablePokemon> teamPks_ = new ByteTreeMap<UsablePokemon>();
        for (EntryCust<Byte, PokemonPlayer> e: _team.entryList()) {
            teamPks_.put(e.getKey(), e.getValue());
        }
        teamPan.initFighters(teamPks_,messagesTeamPanel);
        readyCheck.setEnabled(false);
        enabledReady = false;
        readyCheck.setSelected(false);
        enabledClick = true;
    }

    public void seeNetPokemonDetail() {
        Thread thread_ = window.getPreparedPkNetThread();
        PreparedRenderedPages task_ = window.getPreparedPkNetTask();
        if (thread_ == null || thread_.isAlive() || task_ == null) {
            return;
        }
        if (receivedPk.isProcessing()) {
            return;
        }
        ((PokemonStandards)task_.getBeanNatLgNames()).setDataBase(facade);
        receivedPk.initializeOnlyConf(task_, facade.getLanguage());
    }

    public void interact() {
        endGame.setText(facade.getEndGameMessage());
        endGame.setVisible(facade.isShowEndGame());
        placeName.setText(facade.getCurrentPlace());
        initInteraction();
        if (facade.getInterfaceType() != InterfaceType.RIEN) {
            buttonInteract.setEnabledLabel(true);
        } else if (interaction != null) {
            buttonInteract.setEnabledLabel(false);
        }
        setTextArea(StringUtil.join(facade.getGame().getCommentGame().getMessages(), RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
        setPaintingScene(false);
    }

    private void initInteraction() {
        if (interaction != null) {
            return;
        }
        interaction = Panel.newLineBox();
        buttonInteract = new LabelButton(messages.getVal(INTERACT));
        buttonInteract.addMouseListener(new InteractSceneEvent(this));
        interaction.add(buttonInteract);
        fish = new LabelButton(messages.getVal(CST_FISH));
        fish.addMouseListener(new FishingEvent(this));
        interaction.add(fish);
    }

    public void interactScene() {
        if (!buttonInteract.isEnabledLabel()) {
            return;
        }
        if (!facade.isEnabledMovingHero()) {
            return;
        }
        facade.interactNoFish();
        if (!facade.isChangeToFightScene()) {
            setTextArea(StringUtil.join(facade.getGame().getCommentGame().getMessages(), RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
        }
        showOptions();
        window.pack();
    }

    public void fishing() {
        if (facade.isFishArea()) {
            facade.initFishing();
            if (facade.isChangeToFightScene()) {
                window.setSavedGame(false);
                window.setFight(true, true);
            }
        }
    }

    private void showOptions() {
        panelOptions.removeAll();
        if (facade.getInterfaceType() == InterfaceType.ECH_BOITE) {
            selectedForSwitch = new TextLabel("");
            Panel storage_ = Panel.newGrid(0, 1);
            selectPkBox = new LabelButton(messages.getVal(SELECT_PK_BOX));
            selectPkBox.addMouseListener(new SelectPokemonBoxEvent(this));
            storage_.add(selectPkBox);
            selectEggBox = new LabelButton(messages.getVal(SELECT_EGG_BOX));
            selectEggBox.addMouseListener(new SelectEggBoxEvent(this));
            storage_.add(selectEggBox);
            Separator sep_ = new Separator();
            storage_.add(sep_);
            takeItem = new LabelButton(messages.getVal(TAKE_ITEM));
            takeItem.setEnabledLabel(false);
            takeItem.addMouseListener(new GearStorageEvent(this, StorageActions.TAKE_ITEM_BOX));
            storage_.add(takeItem);
            store = new LabelButton(messages.getVal(CST_STORE));
            store.setEnabledLabel(false);
            store.addMouseListener(new GearStorageEvent(this, StorageActions.STORE));
            storage_.add(store);
            withdraw = new LabelButton(messages.getVal(WITHDRAW_PK));
            withdraw.setEnabledLabel(false);
            withdraw.addMouseListener(new GearStorageEvent(this, StorageActions.WIDRAW_PK));
            storage_.add(withdraw);
            withdrawEgg = new LabelButton(messages.getVal(WITHDRAW_EGG));
            withdrawEgg.setEnabledLabel(false);
            withdrawEgg.addMouseListener(new GearStorageEvent(this, StorageActions.WIDRAW_EGG));
            storage_.add(withdrawEgg);
            switchPk = new LabelButton(messages.getVal(SWITCH_PK_EGG));
            switchPk.setEnabledLabel(false);
            switchPk.addMouseListener(new GearStorageEvent(this, StorageActions.SWITCH_TEAM_BOX));
            storage_.add(switchPk);
            sep_ = new Separator();
            storage_.add(sep_);
            release = new LabelButton(messages.getVal(CST_RELEASE));
            release.setEnabledLabel(false);
            release.addMouseListener(new GearStorageEvent(this, StorageActions.RELEASE));
            storage_.add(release);
            storage_.add(selectedForSwitch);
            Panel set_ = Panel.newLineBox();
            initTeam();
            teamPan.addListenerStorage(this);
            set_.add(teamPan.getContainer());
            set_.add(storage_);
            panelOptions.add(set_, BorderLayout.CENTER);
            panelMenu.setVisible(false);
            disableFishing();
        } else if (facade.getInterfaceType() == InterfaceType.MOVE_TUTORS) {
            initPkTeamMoveTutors();
            Panel set_ = Panel.newLineBox();
            set_.add(teamPan.getContainer());
            movesLearnt = Panel.newGrid(0,1);
            ScrollPane scroll_ = new ScrollPane(movesLearnt);
            scroll_.setPreferredSize(new Dimension(100, 220));
            set_.add(scroll_);
            panelOptions.add(set_, BorderLayout.CENTER);
            panelMenu.setVisible(false);
            disableFishing();
        } else if (facade.getInterfaceType() == InterfaceType.ACHATS_CT) {
            tmPanel = new TmPanel(5, messages.getVal(TM_TITLE), facade, window.getAikiFactory().getGeneTmPanel().create(true));
            Panel set_ = Panel.newPageBox();
            LabelButton selectItem_ = new LabelButton(messages.getVal(TM_SELECT));
            selectItem_.addMouseListener(new AddTmEvent(this));
            set_.add(selectItem_);
            LabelButton removeItem_ = new LabelButton(messages.getVal(TM_REMOVE));
            removeItem_.addMouseListener(new RemoveTmEvent(this));
            set_.add(removeItem_);
            set_.add(tmPanel.getContainer());
            LabelButton changeInv_ = new LabelButton(messages.getVal(TM_BUY));
            changeInv_.addMouseListener(new BuyTmEvent(this));
            set_.add(changeInv_);
            panelOptions.add(set_, BorderLayout.CENTER);
            panelMenu.setVisible(false);
            disableFishing();
        } else if (facade.getInterfaceType() == InterfaceType.ACHATS) {
            Panel set_ = Panel.newPageBox();
            buy = new CustCheckBox(messages.getVal(ITEM_BUY));
            buy.setSelected(true);
            buy.addActionListener(new BuyOrSellEvent(this));
            set_.add(buy);
            itemsPan = new ItemsPanel(2, messages.getVal(ITEM_TITLE), facade, window.getAikiFactory().getGeneItPanel().create(true));
            LabelButton selectItem_ = new LabelButton(messages.getVal(ITEM_SELECT));
            selectItem_.addMouseListener(new SelectItemForListEvent(this));
            set_.add(selectItem_);
            LabelButton addItem_ = new LabelButton(messages.getVal(ITEM_ADD));
            addItem_.addMouseListener(new ChangeItemListEvent(this, true));
            set_.add(addItem_);
            LabelButton removeItem_ = new LabelButton(messages.getVal(ITEM_REMOVE));
            removeItem_.addMouseListener(new ChangeItemListEvent(this, false));
            set_.add(removeItem_);
            set_.add(itemsPan.getContainer());
            LabelButton changeInv_ = new LabelButton(messages.getVal(ITEM_BUY_SELL));
            changeInv_.addMouseListener(new BuyItemsEvent(this));
            set_.add(changeInv_);
            panelOptions.add(set_, BorderLayout.CENTER);
            panelMenu.setVisible(false);
            disableFishing();
        } else if (facade.getInterfaceType() == InterfaceType.PENSION) {
            Panel set_ = Panel.newLineBox();
            ByteTreeMap<UsablePokemon> teamPks_ = new ByteTreeMap<UsablePokemon>();
            ByteTreeMap< PokemonPlayer> team_ = facade.getGame().getPlayer().getPokemonPlayerList();
            for (EntryCust<Byte, PokemonPlayer> e: team_.entryList()) {
                teamPks_.put(e.getKey(), e.getValue());
            }
            teamPan = new TeamPanel(2, messages.getVal(POKEMON_SELECT_TWO), facade, teamPks_, false,messagesTeamPanel, window.getAikiFactory().getGeneUsPkPanel().create(true));
            teamPan.addListenerHost(this);
            set_.add(teamPan.getContainer());
            Panel form_ = Panel.newPageBox();
            int nbRemSteps_ = facade.getRemaingingSteps();
            String buttonText_= StringUtil.simpleNumberFormat(messages.getVal(GET_EGG), nbRemSteps_);
            LabelButton receiveEgg_ = new LabelButton(buttonText_);
            receiveEgg_.addMouseListener(new ReceiveFromHostEvent(this, true));
            form_.add(receiveEgg_);
            buttonText_= StringUtil.simpleNumberFormat(messages.getVal(GET_EGG_PARENT), nbRemSteps_);
            LabelButton receiveParents_ = new LabelButton(buttonText_);
            receiveParents_.addMouseListener(new ReceiveFromHostEvent(this, false));
            form_.add(receiveParents_);
            LabelButton hostPk_ = new LabelButton(messages.getVal(HOST_PK));
            hostPk_.addMouseListener(new HostPokemonEvent(this));
            form_.add(hostPk_);
            set_.add(form_);
            panelOptions.add(set_, BorderLayout.CENTER);
            panelMenu.setVisible(false);
            disableFishing();
        } else if (facade.getInterfaceType() == InterfaceType.FOSSILE) {
            window.setSavedGame(false);
        } else if (facade.isChangeToFightScene()) {
            window.setSavedGame(false);
            window.setFight(true, false);
            return;
        }
        addExit();
    }

    private void disableFishing() {
        if (fish == null) {
            return;
        }
        fish.setEnabledLabel(false);
    }

//    private StringList getMessagesStorage() {
//        StringList messages_;
//        messages_ = new StringList();
//        if (facade.getSelectedPokemonFirstBox() != null) {
//            messages_.add(messages.getVal(SELECTED_PK));
//        } else {
//            messages_.add(messages.getVal(NOT_SELECTED_PK));
//        }
//        if (facade.getSelectedEggBox() != null) {
//            messages_.add(messages.getVal(SELECTED_EGG));
//        } else {
//            messages_.add(messages.getVal(NOT_SELECTED_EGG));
//        }
//        return messages_;
//    }

    public void hostPokemon() {
        facade.attemptForStoringPokemonToHost();
        boolean exited_ = false;
        String info_ = StringUtil.join(facade.getGame().getCommentGame().getMessages(), RETURN_LINE);
        if (facade.getFirstSelectPkToHost() == IndexConstants.INDEX_NOT_FOUND_ELT) {
            if (facade.getSecondSelectPkToHost() == IndexConstants.INDEX_NOT_FOUND_ELT) {
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
        String info_ = StringUtil.join(facade.getGame().getCommentGame().getMessages(), RETURN_LINE);
        if (!facade.getGame().isReinitInteraction()) {
            setTextArea(info_, JOptionPane.INFORMATION_MESSAGE);
            exitInteractionPack();
        } else {
            setTextArea(info_, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addTmToBuy() {
        SelectTm.setSelectTm(window, facade, true);
        boolean isSelectedIndex_ = SelectTm.isSelectedIndex(window.getSelectTm());
        boolean ok_ = SelectTm.isOk(window.getSelectTm());
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
            setTextArea(messages.getVal(NO_POSSIBLE_BUY), JOptionPane.ERROR_MESSAGE);
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
        boolean isSelectedIndex_ = SelectItem.isSelectedIndex(window.getSelectItem());
        boolean ok_ = SelectItem.isOk(window.getSelectItem());
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
            setTextArea(messages.getVal(NO_POSSIBLE_BUY), JOptionPane.ERROR_MESSAGE);
            return;
        }
        window.setSavedGame(false);
        exitInteractionPack();
        window.pack();
    }

    public void selectPokemonBox() {
        int lineBack_ = facade.getLineFirstBox();
        SelectPokemon.setSelectPokemon(window, facade, true, window.getSelectPokemon());
        SelectPokemon.isSelectedIndex(window.getSelectPokemon());
        if (!SelectPokemon.isStaticOk(window.getSelectPokemon())) {
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
        SelectEgg.setSelectEgg(window, facade, window.getSelectEgg());
        SelectEgg.isSelectedIndex(window.getSelectEgg());
        if (!SelectEgg.isOk(window.getSelectEgg())) {
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
        ByteTreeMap<PokemonPlayer> team_ = facade.getPlayer().getPokemonPlayerList();
        ByteTreeMap<UsablePokemon> pks_ = new ByteTreeMap<UsablePokemon>();
        for (EntryCust<Byte, PokemonPlayer> e: team_.entryList()) {
            pks_.put(e.getKey(), e.getValue());
        }
        teamPan = new TeamPanel(2, messages.getVal(POKEMON_SELECT), facade, pks_, true,messagesTeamPanel, window.getAikiFactory().getGeneUsPkPanel().create(true));
        teamPan.addListenerMoveTutor(this);
    }

    private void refreshPkTeamMoveTutors() {
        enabledClick = false;
        ByteTreeMap<PokemonPlayer> team_ = facade.getPlayer().getPokemonPlayerList();
        ByteTreeMap<UsablePokemon> pks_ = new ByteTreeMap<UsablePokemon>();
        for (EntryCust<Byte, PokemonPlayer> e: team_.entryList()) {
            pks_.put(e.getKey(), e.getValue());
        }
        teamPan.initFighters(pks_,messagesTeamPanel);
        enabledClick = true;
    }

    private void initTeam() {
        ByteTreeMap<UsablePokemon> pks_ = new ByteTreeMap<UsablePokemon>();
        byte i_ = IndexConstants.FIRST_INDEX;
        for (UsablePokemon p: facade.getPlayer().getTeam()) {
            pks_.put(i_, p);
            i_++;
        }
        teamPan = new TeamPanel(2, messages.getVal(POKEMON_SELECT), facade, pks_, true,messagesTeamPanel, window.getAikiFactory().getGeneUsPkPanel().create(true));
    }

    private void refreshTeam() {
        enabledClick = false;
        ByteTreeMap<UsablePokemon> pks_ = new ByteTreeMap<UsablePokemon>();
        byte i_ = IndexConstants.FIRST_INDEX;
        for (UsablePokemon p: facade.getPlayer().getTeam()) {
            pks_.put(i_, p);
            i_++;
        }
        teamPan.initFighters(pks_,messagesTeamPanel);
        enabledClick = true;
    }

    private void addButtonsTeam() {
        Panel set_ = Panel.newLineBox();
        Panel teamMenu_ = Panel.newPageBox();
        switchUsable = new CustCheckBox(messages.getVal(SWITCH_PK_TEAM));
//        enabledSwitchTeam = false;
//        switchUsable.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent _arg0) {
//                enabledSwitchTeam = !enabledSwitchTeam;
//            }
//        });
        teamMenu_.add(switchUsable);
        takeItemTeam = new LabelButton(messages.getVal(TAKE_ITEM_TEAM));
        takeItemTeam.setEnabledLabel(false);
        takeItemTeam.addMouseListener(new TakeItemFromTeamEvent(this));
        teamMenu_.add(takeItemTeam);
        detailPk = new LabelButton(messages.getVal(DETAIL_TEAM));
        detailPk.setEnabledLabel(false);
        detailPk.addMouseListener(new SeePokemonDetailEvent(this));
        teamMenu_.add(detailPk);
        healPk = new LabelButton(messages.getVal(HEAL_PK));
        healPk.setEnabledLabel(false);
        healPk.addMouseListener(new HealPokemonEvent(this));
        teamMenu_.add(healPk);
        nicknamePk = new LabelButton(messages.getVal(NICKNAME));
        nicknamePk.setEnabledLabel(false);
        nicknamePk.addMouseListener(new ChangeNicknameEvent(this));
        teamMenu_.add(nicknamePk);
        set_.add(teamPan.getContainer());
        set_.add(teamMenu_);
        panelOptions.add(set_, BorderLayout.CENTER);
    }

    public void takeItemFromTeam() {
        facade.takeObjectFromTeam();
        window.setSavedGame(false);
        setTextArea(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
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
        Thread thread_ = window.getPreparedPkThread();
        PreparedRenderedPages task_ = window.getPreparedPkTask();
        if (thread_ == null || thread_.isAlive() || task_ == null) {
            return;
        }
        RenderedPage session_;
        session_ = new RenderedPage(new ScrollPane(), window.getFrames());
        showHtmlDialog(window, session_,facade,task_,facade.getLanguage());
    }

    public void healPokemon() {
        int lineBack_ = facade.getLineHealingItem();
        SelectHealingItem.setSelectHealingItem(window, facade);
        boolean isSelectedIndex_ = SelectHealingItem.isSelectedIndex(window.getSelectHealingItem());
        boolean ok_ = SelectHealingItem.isOk(window.getSelectHealingItem());
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
                setTextArea(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (facade.getPlayer().getChosenMoves().isEmpty()) {
                window.setSavedGame(false);
                setTextArea(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            SelectHealedMove.setSelectHealedMove(window, facade);
        } else {
            facade.clearSortingHealingItem();
        }
    }

    public void changeNickname() {
        String lg_ = window.getLanguageKey();
        ConfirmDialog.showTextField(window, DataBase.EMPTY_STRING, messages.getVal(NICKNAME), messages.getVal(NICKNAME), lg_);
        if (ConfirmDialog.getStaticAnswer(window.getConfirmDialog()) != JOptionPane.YES_OPTION) {
            return;
        }
        facade.validateNickname(ConfirmDialog.getStaticText(window.getConfirmDialog()));
    }

    public void selectPokemonMoveTutor() {
        if (!enabledClick) {
            return;
        }
        facade.choosePokemonForMoveTutors((short) teamPan.getSelectedIndex());
        movesLearnt.removeAll();
        movesLearnt.add(new TextLabel(messages.getVal(SELECT_MT)));
        StringList selectedMoves_ = facade.getSelectedMoves();
        for (String m: selectedMoves_) {
            String tr_ = facade.translateMove(m);
            MoveTutorCheckBox check_ = new MoveTutorCheckBox(m,tr_, true,this);
            check_.setBackground(Color.RED);
            check_.setSelected(true);
            movesLearnt.add(check_.getComponent());
        }
        StringList unselectedMoves_ = facade.getUnselectedMoves();
        StringMap<Short> chosenMoves_ = facade.getPlayer().getChosenMoves();
        for (String m: unselectedMoves_) {
            String tr_ = facade.translateMove(m);
            MoveTutorCheckBox check_ = new MoveTutorCheckBox(m, StringUtil.concat(tr_,SPACE,Long.toString(chosenMoves_.getVal(m))),false,this);
            check_.setBackground(Color.WHITE);
            check_.setSelected(false);
            movesLearnt.add(check_.getComponent());
        }
        LabelButton cancel_ = new LabelButton(messages.getVal(CANCEL_MT));
        cancel_.addMouseListener(new CancelMtEvent(this));
        movesLearnt.add(cancel_);
        LabelButton ok_ = new LabelButton(messages.getVal(VALIDATE_MT));
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
        String comment_ = StringUtil.join(facade.getComment().getMessages(), RETURN_LINE);
        if (facade.getPlayer().getChosenTeamPokemon() != IndexConstants.INDEX_NOT_FOUND_ELT) {
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
//        String text_ = StringList.simpleFormat(messages.getVal(SELECTED_MOVES), facade.getSelectedMoves().size());
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
        LabelButton exit_ = new LabelButton(messages.getVal(EXIT));
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
        enableIfPossibleFishing();
        facade.exitInteract();
    }

    private void enableIfPossibleFishing() {
        if (fish == null) {
            return;
        }
        fish.setEnabledLabel(facade.isFishArea());
    }

    private void showHtmlDialog(MainWindow _parent, RenderedPage _session, FacadeGame _dataBase, PreparedRenderedPages _pre, String _lg) {
//        DialogHtmlData.setDialogHtmlData(_parent, messages.getVal(TITLE_DETAIL), _session, window.isSuccessfulCompile());
        DialogHtmlData.setDialogHtmlData(_parent, messages.getVal(TITLE_DETAIL), _session,_dataBase,_pre,_lg);
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
            setTextArea(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
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
            setTextArea(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
            window.setSavedGame(false);
            exitInteractionPack();
            return;
        }
        window.pack();
    }

    private void setMovesAbilities() {
        movesLearnt.removeAll();
        movesLearnt.add(new TextLabel(messages.getVal(SELECT_MT)));
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
            movesLearnt.add(check_.getComponent());
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
            movesLearnt.add(check_.getComponent());
        }
        StringList ab_ = facade.getPlayer().getNewAbilitiesToBeChosen();
        abilities.removeAll();
        abilityLabels.clear();
        if (!ab_.isEmpty()) {
            abilities.add(new TextLabel(messages.getVal(SELECT_ABILITY)));
            for (String a: ab_) {
                AbilityLabel lab_ = new AbilityLabel(facade.translateAbility(a), a);
                lab_.addMouseListener(new AbilityWalkEvent(this, a));
                abilities.add(lab_);
                abilityLabels.add(lab_);
            }
            abilities.add(new Separator());
        }
        LabelButton ok_ = new LabelButton(messages.getVal(EVOLVE));
        ok_.addMouseListener(new EvolvePokemonEvent(this));
        abilities.add(ok_);
    }

    public void evolvePokemon() {
        facade.evolvePokemon();
        String info_ = StringUtil.join(facade.getComment().getMessages(), RETURN_LINE);
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
        movesLearnt.add(new TextLabel(messages.getVal(SELECT_HEAL_MOVE)));
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
            LabelButton check_ = new LabelButton(StringUtil.concat(tr_,SPACE,Long.toString(moves_.getVal(m))));
            check_.addMouseListener(new HealMoveEvent(this, m));
            check_.setBackground(Color.WHITE);
            movesLearnt.add(check_);
        }
    }

    private void setBoostedMoves() {
        movesLearnt.removeAll();
        movesLearnt.add(new TextLabel(messages.getVal(SELECT_BOOST_MOVE)));
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
            LabelButton check_ = new LabelButton(StringUtil.concat(tr_,SPACE,Long.toString(moves_.getVal(m))));
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
        setTextArea(StringUtil.join(facade.getPlayer().getCommentGame().getMessages(), RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
        exitInteractionPack();
    }

    public void boostMove(String _move) {
        facade.gainPpMax(_move);
        window.setSavedGame(false);
        setTextArea(StringUtil.join(facade.getPlayer().getCommentGame().getMessages(), RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
        exitInteractionPack();
    }

    public void getAbility(String _ability) {
        facade.getPlayer().setChosenAbilityForEvolution(_ability);
        for (AbilityLabel l: abilityLabels) {
            l.setSelected(_ability);
            l.repaintLabel();
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
            setTextArea(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
            exitInteractionPack();
            return;
        }
        movesLearnt.removeAll();
        movesLearnt.add(new TextLabel(messages.getVal(SELECT_TM)));
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
            LabelButton check_ = new LabelButton(StringUtil.concat(tr_,SPACE,Long.toString(moves_.getVal(m))));
            check_.addMouseListener(new LearntMoveEvent(this, m));
            check_.setBackground(Color.WHITE);
            movesLearnt.add(check_);
        }
        window.pack();
    }

    public void learntMove(String _move) {
        facade.learnMove(_move);
        window.setSavedGame(false);
        setTextArea(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
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
        String lg_ = window.getLanguageKey();
        WrappedTextArea commentsWalking_ = new WrappedTextArea(4, 32);
        commentsWalking_.setEditable(false);
        commentsWalking_.setText(_text);
        ConfirmDialog.showComponent(window, new ScrollPane(commentsWalking_), messages.getVal(TITLE_COMMENTS), lg_, _messageType);
    }

    public Scene getScene() {
        return scene;
    }

    public boolean isPaintingScene() {
        return paintingScene.get();
    }

    public void setPaintingScene(boolean _paintingScene) {
        paintingScene.set(_paintingScene);
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
        panelMenu.repaintSecondChildren();
    }
    public Panel getComponent() {
        return component;
    }
}
