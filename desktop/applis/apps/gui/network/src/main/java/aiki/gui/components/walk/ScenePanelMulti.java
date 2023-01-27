package aiki.gui.components.walk;

import aiki.beans.PokemonStandards;

import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.components.walk.events.*;
import aiki.gui.dialogs.*;
import aiki.gui.listeners.*;
import aiki.gui.threads.PreparedRenderedPages;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;
import aiki.sml.Resources;
import code.bean.nat.FixCharacterCaseConverter;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;
import code.network.*;
import code.network.enums.*;
import code.stream.*;
import aiki.network.*;
import aiki.network.stream.*;

public class ScenePanelMulti {

    private static final String SCENE_PANEL = "aiki.gui.components.walk.scenepanel";

    private static final String LOCALHOST_OLD_IP = "127.0.0.1";

    private static final String LOCALHOST_NEW_IP = "::1";

//    private static final String RETURN_LINE = "\n";

//    private static final String CST_TEAM = "team";

//    private static final String CST_ITEMS = "items";

//    private static final String CST_TM = "tm";

//    private static final String CST_FISH = "fish";

//    private static final String SEE_POKEMON = "seePokemon";

//    private static final String SEE_EGG = "seeEgg";

//    private static final String SEE_HOSTED = "seeHosted";

//    private static final String SEE_GAME = "seeGame";

//    private static final String GO_BACK_MENU = "goBackMenu";

    private static final String CST_SERVER = "server";

    private static final String POKEMON_SELECT = "pokemonSelect";

//    private static final String POKEMON_SELECT_TWO = "pokemonSelectTwo";

    private static final String RECEIVED_POKEMON = "receivedPokemon";

//    private static final String INTERACT = "interact";

//    private static final String GO_BACK = "goBack";

    private static final String TRADE = "trade";

    private static final String EXIT = "exit";

    private static final String READY = "ready";

//    private static final String SELECT_PK_BOX = "selectPkBox";
//
//    private static final String SELECT_EGG_BOX = "selectEggBox";
//
//    private static final String TAKE_ITEM = "takeItem";
//
//    private static final String CST_STORE = "store";
//
//    private static final String WITHDRAW_PK = "withdrawPk";
//
//    private static final String WITHDRAW_EGG = "withdrawEgg";
//
//    private static final String SWITCH_PK_EGG = "siwtchPkEgg";
//
//    private static final String CST_RELEASE = "release";
//
//    private static final String TM_TITLE = "tmTitle";
//
//    private static final String TM_SELECT = "tmSelect";
//
//    private static final String TM_REMOVE = "tmRemove";
//
//    private static final String TM_BUY = "tmBuy";
//
//    private static final String ITEM_TITLE = "itemTitle";
//
//    private static final String ITEM_SELECT = "itemSelect";
//
//    private static final String ITEM_ADD = "itemAdd";
//
//    private static final String ITEM_REMOVE = "itemRemove";
//
//    private static final String ITEM_BUY = "itemBuy";
//
//    private static final String ITEM_BUY_SELL = "itemBuySell";
//
//    private static final String GET_EGG = "getEgg";
//
//    private static final String GET_EGG_PARENT = "getEggParent";
//
//    private static final String HOST_PK = "hostPk";
//
//    private static final String SWITCH_PK_TEAM = "switchPkTeam";
//
//    private static final String TAKE_ITEM_TEAM = "takeItemTeam";
//
//    private static final String DETAIL_TEAM = "detailTeam";
//
//    private static final String HEAL_PK = "healPk";
//
//    private static final String SELECT_MT = "selectMt";
//
//    private static final String CANCEL_MT = "cancelMt";
//
//    private static final String VALIDATE_MT = "validateMt";
//
//    private static final String SELECT_TM = "selectTm";
//
//    private static final String SELECT_ABILITY = "selectAbility";
//
//    private static final String EVOLVE = "evolve";
//
//    private static final String SELECT_HEAL_MOVE = "selectHealMove";
//
//    private static final String SELECT_BOOST_MOVE = "selectBoostMove";
//
//    private static final String NICKNAME = "nickname";
//
//    private static final String ERROR_USING_ITEM = "errorUsingItem";
//
//    private static final String NO_POSSIBLE_LEARN = "noPossibleLearn";
//
//    private static final String NO_POSSIBLE_BUY = "noPossibleBuy";
//
//    private static final String TITLE_DETAIL = "titleDetail";
//
//    private static final String SPACE = " ";

    private static final String BUG = "bug";

    private static final String UNKNOWN_HOST = "unknownHost";

    private static final String NOT_CONNECTED = "notConnected";

//    private static final String TITLE_COMMENTS = "titleComments";

    private static final String CLICK_SCENE = "clickScene";
    private final AbsCompoFactory compoFactory;

    private StringMap<String> messages = new StringMap<String>();

    private final WindowNetWork window;

    private AbsPanel panelMenu;

    private final AbsPlainLabel endGame;

    private AbsPlainLabel useKeyPad;

//    private AbsPlainButton team;
//
//    private AbsPlainButton items;
//
//    private AbsPlainButton tm;
//
//    private AbsPlainButton fish;
//
//    private AbsPlainButton seeBoxes;
//
//    private AbsPlainButton seeEggs;
//
//    private AbsPlainButton host;
//
//    private AbsPlainButton game;
//
//    private AbsPlainButton goBack;

    private AbsPlainButton server;

//    private final AbsPlainButton attract;
//    private AbsPanel movesLearnt;
//
//    private AbsPanel abilities;
//
//    private final CustList<AbilityLabel> abilityLabels = new CustList<AbilityLabel>();

    private final AbsPanel panelOptions;

    private TeamPanel teamPan;
    private StringMap<String> messagesTeamPanel = new StringMap<String>();

//    private ItemsPanel itemsPan;
//
//    private TmPanel tmPanel;
//
//    private AbsCustCheckBox buy;
//
//    private AbsPlainButton selectPkBox;
//
//    private AbsPlainButton selectEggBox;
//
//    private AbsPlainButton takeItem;
//
//    private AbsPlainButton store;
//
//    private AbsPlainButton release;
//
//    private AbsPlainButton withdraw;
//
//    private AbsPlainButton withdrawEgg;
//
//    private AbsPlainButton switchPk;
//
//    private AbsPlainLabel selectedForSwitch;

    private final FacadeGame facade;

//    private AbsPanel beginGame;

    private AbsPanel sceneInteract;

    private AbsPlainLabel placeName;

//    private Scene scene;

//    private KeyPadListener keyPadListener;

//    private AbsPanel interaction;
//
//    private AbsPlainButton buttonInteract;

//    private Pad pad;

//    private final AbsPlainLabel time;

//    private AbsCustCheckBox switchUsable;
//
//    private AbsPlainButton takeItemTeam;
//
//    private AbsPlainButton detailPk;
//
//    private AbsPlainButton healPk;
//
//    private AbsPlainButton nicknamePk;

    private boolean enabledClick = true;

    private RenderedPage receivedPk;

    private AbsPanel panelNetWork;

    private boolean enabledReady;

    private AbsCustCheckBox readyCheck;

//    private final MapPanel mapPanel;
//
//    private AbsPlainButton chosenCity;
//
//    private final AbstractAtomicBoolean paintingScene;

    private final AbsPanel component;

    public ScenePanelMulti(WindowNetWork _window, FacadeGame _facade) {
        compoFactory = _window.getCompoFactory();
//        attract = compoFactory.newPlainButton("OK");
//        attract.addActionListener(new AttractEvent(this));
        endGame = compoFactory.newPlainLabel("");
        component = compoFactory.newLineBox();
//        paintingScene = _window.getThreadFactory().newAtomicBoolean();
        facade = _facade;
        window = _window;
        initMenu();
        panelOptions = compoFactory.newBorder();
        horizTrade();
//        time = window.getCompoFactory().newPlainLabel("");
        component.add(window.getCompoFactory().newPlainLabel(""));
        //gamePanel = new GamePanel(facade);
//        mapPanel = new MapPanel();
        window.pack();
    }

    private void horizTrade() {
        AbsPanel panelHoriz_ = compoFactory.newLineBox();
        panelHoriz_.add(panelMenu);
        panelHoriz_.add(panelOptions);
        component.add(panelHoriz_);
    }

//    public void reinit() {
//        panelOptions.removeAll();
////        if (keyPadListener != null) {
////            keyPadListener.setSceneKepPad(null);
////        }
//        sceneInteract.removeAll();
////        if (scene != null) {
////            sceneInteract.remove(scene);
////        }
////        if (placeName != null) {
////            sceneInteract.remove(placeName);
////        }
////        if (interaction != null) {
////            sceneInteract.remove(interaction);
////        }
//        panelMenu.setVisible(false);
//        disableFishing();
//    }

    public void initMessages(String _lg) {
        messages = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _lg,SCENE_PANEL);
        messagesTeamPanel = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _lg, TeamPanel.TEAM_PANEL);
    }

    public void setMessages() {
        endGame.setText(facade.getEndGameMessage());
        useKeyPad.setText(messages.getVal(CLICK_SCENE));
//        team.setText(messages.getVal(CST_TEAM));
//        items.setText(messages.getVal(CST_ITEMS));
//        tm.setText(messages.getVal(CST_TM));
        //        difficulty.setText(messages.getVal(DIFFICULTY));
//        if (fish != null) {
//            fish.setText(messages.getVal(CST_FISH));
//        }
//        seeBoxes.setText(messages.getVal(SEE_POKEMON));
//        seeEggs.setText(messages.getVal(SEE_EGG));
//        host.setText(messages.getVal(SEE_HOSTED));
//        game.setText(messages.getVal(SEE_GAME));
//        goBack.setText(messages.getVal(GO_BACK_MENU));
        server.setText(messages.getVal(CST_SERVER));
//        if (interaction != null) {
//            buttonInteract.setText(messages.getVal(INTERACT));
//        }
        if (teamPan != null) {
            teamPan.translate(messagesTeamPanel);
        }
    }

//    public void addBeginGame(AbsPanel _panel) {
//        panelOptions.add(_panel, GuiConstants.BORDER_LAYOUT_CENTER);
//        beginGame = _panel;
//        beginGame.setVisible(true);
//    }

    public void drawGameWalking() {
        panelOptions.removeAll();
        endGame.setText(facade.getEndGameMessage());
        endGame.setVisible(facade.isShowEndGame());
        panelMenu.setVisible(true);
//        if (beginGame != null) {
//            beginGame.setVisible(false);
//        }
//        if (beginGame != null) {
//            panelOptions.remove(beginGame);
//        }
//        boolean wasNull_ = false;
//        if (scene == null) {
//            wasNull_ = true;
////            scene = new Scene(window.getCompoFactory());
////            scene.setDimensions(facade);
////            scene.addMouseListener(scene);
//
////            keyPadListener = new KeyPadListener(window, facade);
////            scene.addKeyListener(keyPadListener);
//
////            Task task_ = new Task(scene, facade, window);
////            AbstractScheduledExecutorService t_ = window.getThreadFactory().newScheduledExecutorService();
////            Timer t_ = new Timer(0, task_);
////            pad.getUp().addMouseListener(new MouseTask(Direction.UP,task_, t_, window));
////            pad.getDown().addMouseListener(new MouseTask(Direction.DOWN, task_, t_, window));
////            pad.getLeft().addMouseListener(new MouseTask(Direction.LEFT, task_, t_, window));
////            pad.getRight().addMouseListener(new MouseTask(Direction.RIGHT, task_, t_, window));
//        }
//        keyPadListener.setSceneKepPad(scene);
        facade.directInteraction();
        facade.changeCamera();
//        scene.load(window.getImageFactory(),facade, _setPreferredSize);
//        scene.setDelta(0, false);
//        if (wasNull_) {
//            scene.setPreferredSize();
//        }
//        wasNull_ = false;
        if (sceneInteract == null) {
//            wasNull_ = true;
            sceneInteract = compoFactory.newBorder();
        } else {
            sceneInteract.removeAll();
        }
        if (placeName == null) {
            placeName = window.getCompoFactory().newPlainLabel("");
        }
        placeName.setText(facade.getCurrentPlace());
        sceneInteract.add(placeName, GuiConstants.BORDER_LAYOUT_NORTH);
//        sceneInteract.add(scene.getPaintableLabel(), GuiConstants.BORDER_LAYOUT_CENTER);
//        initInteraction();
//        if (fish != null) {
//            enableIfPossibleFishing();
//        }
//        sceneInteract.add(interaction, GuiConstants.BORDER_LAYOUT_SOUTH);
//        buttonInteract.setEnabled(facade.getInterfaceType() != InterfaceType.RIEN);
//        if (wasNull_) {
//            component.add(sceneInteract, IndexConstants.FIRST_INDEX);
////            sceneInteract.repaintSecondChildren(window.getImageFactory());
////        } else {
////            panelMenu.repaintSecondChildren(window.getImageFactory());
////            time.repaintLabel();
////            sceneInteract.repaintSecondChildren(window.getImageFactory());
//        }
//        AbsMetaLabelPk.paintPk(window.getImageFactory(), scene);
        component.validate();
//        scene.setFocus();
    }

//    public String getComment() {
//        return StringUtil.join(facade.getComment().getMessages(), RETURN_LINE);
//    }

    private void initMenu() {
        panelMenu = compoFactory.newPageBox();
        endGame.setVisible(false);
        endGame.setOpaque(true);
        endGame.setBackground(GuiConstants.YELLOW);
        AbsPanel menus_ = compoFactory.newGrid(0,1);
        menus_.add(endGame);
        useKeyPad = window.getCompoFactory().newPlainLabel("");
        menus_.add(useKeyPad);
//        team = window.getCompoFactory().newPlainButton();
//        team.addActionListener(new ManageTeamEvent(this));
//        menus_.add(team);
//        items = window.getCompoFactory().newPlainButton();
//        items.addActionListener(new SelectItemForPokemonEvent(this));
//        menus_.add(items);
//        tm = window.getCompoFactory().newPlainButton();
//        tm.addActionListener(new SelectTmToLearnEvent(this));
//        menus_.add(tm);
//        menus_.add(window.getCompoFactory().newAbsPaintableLabel());
//        seeBoxes = window.getCompoFactory().newPlainButton();
//        seeBoxes.addActionListener(new ConsultPokemonEvent(window, facade));
//        menus_.add(seeBoxes);
//        seeEggs = window.getCompoFactory().newPlainButton();
//        seeEggs.addActionListener(new ConsultEggEvent(window, facade));
//        menus_.add(seeEggs);
//        host = window.getCompoFactory().newPlainButton();
//        host.addActionListener(new ConsultHostEvent(window, facade));
//        menus_.add(host);
//        game = window.getCompoFactory().newPlainButton();
//        game.addActionListener(new ShowGameProgressingEvent(window));
//        menus_.add(game);
//        menus_.add(window.getCompoFactory().newAbsPaintableLabel());
//        goBack = window.getCompoFactory().newPlainButton();
//        goBack.addActionListener(new SetPlacesEvent(this));
//        menus_.add(goBack);
//        menus_.add(window.getCompoFactory().newAbsPaintableLabel());
        server = window.getCompoFactory().newPlainButton();
        server.addActionListener(new ManageNetworkEvent(this));
        menus_.add(server);
        panelMenu.add(menus_);
//        pad = new Pad(window.getCompoFactory());
//        panelMenu.add(pad.getContainer());
        panelMenu.setVisible(false);
    }

//    public void manageTeam() {
//        facade.openMenu();
//        panelMenu.setVisible(false);
//        disableFishing();
//        initTeam();
//        teamPan.addListenerTeam(this);
//        addButtonsTeam();
//        addExit();
//        window.pack();
//    }

//    public void selectItemForPokemon() {
//        SelectItem.setSelectItem(window, facade, false, true);
//        boolean isSelectedIndex_ = SelectItem.isSelectedIndex(window.getSelectItem());
//        boolean ok_ = SelectItem.isOk(window.getSelectItem());
//        if (!ok_) {
//            facade.clearSortingItem();
//            return;
//        }
//        if (!isSelectedIndex_) {
//            facade.clearSortingItem();
//            return;
//        }
//        boolean give_ = SelectItem.isGive(window.getSelectItem());
//        facade.setGivingObject(give_);
//        facade.chooseObject();
//        facade.clearSortingItem();
//        if (!give_) {
//            if (!facade.usedObject()) {
//                //error if bad use of item
//                String it_ = facade.getPlayer().getSelectedObject();
//                it_ = facade.translateItem(it_);
//                String message_ = StringUtil.simpleStringsFormat(messages.getVal(ERROR_USING_ITEM), it_);
//                setTextArea(message_, GuiConstants.ERROR_MESSAGE);
//                return;
//            }
//            facade.getPlayer().getIndexesOfPokemonTeam().clear();
//            String usedItem_ = facade.getPlayer().getSelectedObject();
//            LgInt nb_ = facade.getPlayer().getInventory().getNumber(usedItem_);
//            facade.useObject();
//            if (facade.getPlayer().getSelectedObject().isEmpty()) {
//                LgInt nbAfter_ = facade.getPlayer().getInventory().getNumber(usedItem_);
//                if (LgInt.strLower(nbAfter_, nb_)) {
//                    //set saved game to false only if an item is used
//                    window.setSavedGame(false);
//                }
//                return;
//            }
//        }
//        facade.openMenu();
//        panelMenu.setVisible(false);
//        disableFishing();
//        panelOptions.removeAll();
//        ByteTreeMap<UsablePokemon> pks_ = new ByteTreeMap<UsablePokemon>();
//        for (byte i: facade.getPlayer().getIndexesOfPokemonTeam()) {
//            pks_.put(i, facade.getPlayer().getTeam().get(i));
//        }
//
//        AbsPanel set_ = compoFactory.newLineBox();
//        teamPan = initTeam(pks_, POKEMON_SELECT, true);
//        teamPan.addListener(this);
//        set_.add(teamPan.getContainer());
//        movesLearnt = compoFactory.newGrid(0,1);
//        AbsScrollPane scroll_ = compoFactory.newAbsScrollPane(movesLearnt);
//        scroll_.setPreferredSize(new MetaDimension(100, 220));
//        set_.add(scroll_);
//        abilities = compoFactory.newGrid(0,1);
//        set_.add(abilities);
//        panelOptions.add(set_, GuiConstants.BORDER_LAYOUT_CENTER);
//        addExit();
//        window.pack();
//    }

//    public void selectTmToLearn() {
//        SelectTm.setSelectTm(window, facade, false);
//        boolean isSelectedIndex_ = SelectTm.isSelectedIndex(window.getSelectTm());
//        boolean ok_ = SelectTm.isOk(window.getSelectTm());
//        if (!ok_) {
//            facade.clearSortingMove();
//            return;
//        }
//        if (!isSelectedIndex_) {
//            facade.clearSortingMove();
//            return;
//        }
//        String move_ = facade.getPlayer().getSelectedMove();
//        facade.chooseMoveByObject();
//        facade.clearSortingMove();
//        if (facade.getPlayer().getSelectedMove().isEmpty()) {
//            //no pokemon can learn
//            move_ = facade.translateMove(move_);
//            String message_ = StringUtil.simpleStringsFormat(messages.getVal(NO_POSSIBLE_LEARN), move_);
//            setTextArea(message_, GuiConstants.WARNING_MESSAGE);
//            return;
//        }
//        facade.openMenu();
//        panelMenu.setVisible(false);
//        disableFishing();
//        panelOptions.removeAll();
//        ByteTreeMap<UsablePokemon> pks_ = new ByteTreeMap<UsablePokemon>();
//        for (byte i: facade.getPlayer().getIndexesOfPokemonTeam()) {
//            pks_.put(i, facade.getPlayer().getTeam().get(i));
//        }
//
//        AbsPanel set_ = compoFactory.newLineBox();
//        teamPan = initTeam(pks_, POKEMON_SELECT, true);
//        teamPan.addListenerTm(this);
//        set_.add(teamPan.getContainer());
//        movesLearnt = compoFactory.newGrid(0,1);
//        AbsScrollPane scroll_ = compoFactory.newAbsScrollPane(movesLearnt);
//        scroll_.setPreferredSize(new MetaDimension(100, 220));
//        set_.add(scroll_);
//        abilities = compoFactory.newGrid(0,1);
//        set_.add(abilities);
//        panelOptions.add(set_, GuiConstants.BORDER_LAYOUT_CENTER);
//        addExit();
//        window.pack();
//    }

    public void manageNetwork() {
//        window.getLanguageKey();
        String lg_ = window.getLanguageKey();
        DialogServerAiki.setDialogServer(window);
        String ip_ = DialogServerAiki.getIpOrHostName(window.getDialogServerAiki());
        if (ip_ == null || ip_.isEmpty()) {
            if (DialogServerAiki.getIpType(window.getDialogServerAiki()) == IpType.IP_V6) {
                ip_ = LOCALHOST_NEW_IP;
            } else {
                ip_ = LOCALHOST_OLD_IP;
            }
        }
        if (!DialogServerAiki.isChoosen(window.getDialogServerAiki())) {
            return;
        }
        String fileName_ = StringUtil.concat(StreamFolderFile.getCurrentPath(window.getFileCoreStream()), Resources.PORT_INI);
        int port_ = NetCreate.tryToGetPort(fileName_, NetAiki.getPort(), window.getFileCoreStream(), window.getStreams());
        window.setCards(false);
        if (DialogServerAiki.isCreate(window.getDialogServerAiki())) {
            window.createServer(ip_, DialogServerAiki.getIpType(window.getDialogServerAiki()), port_);
            return;
        }
        SocketResults connected_ = window.createClient(ip_, DialogServerAiki.getIpType(window.getDialogServerAiki()), false, port_);
        if (connected_.getError() != ErrorHostConnectionType.NOTHING) {
            if (connected_.getError() == ErrorHostConnectionType.UNKNOWN_HOST) {
                String formatted_ = messages.getVal(UNKNOWN_HOST);
                formatted_ = StringUtil.simpleStringsFormat(formatted_, ip_);
                window.getFrames().getMessageDialogAbs().input(window.getCommonFrame(), messages.getVal(BUG), formatted_,lg_,GuiConstants.ERROR_MESSAGE);
                return;
            }
            window.getFrames().getMessageDialogAbs().input(window.getCommonFrame(), messages.getVal(BUG), messages.getVal(NOT_CONNECTED), lg_,GuiConstants.ERROR_MESSAGE);
            return;
        }
        window.setIndexInGame(IndexConstants.SECOND_INDEX);
    }

//    public void setPlaces() {
//        //facade.initPlaces();
//        panelMenu.setVisible(false);
//        disableFishing();
////        JPanel panelPlaces_ = new JPanel();
////        panelPlaces_.setLayout(new GridLayout(0, 1));
////        panelPlaces_.add(new JLabel(messages.getVal(GO_BACK)));
////        for (int i = CustList.FIRST_INDEX; i < facade.getPlaces().size(); i++) {
////            LabelButton place_ = window.getCompoFactory().newPlainButton(facade.getPlaces().get(i));
////            place_.setEnabled(facade.isEnablePlace(i));
////            place_.addMouseListener(new SelectPlaceGoBackEvent(this, i));
////            panelPlaces_.add(place_);
////        }
////        panelOptions.add(panelPlaces_, BorderLayout.CENTER);
//        mapPanel.init(window,facade, this);
//        AbsPanel box_ =compoFactory.newPageBox();
//        box_.add(window.getCompoFactory().newPlainLabel(messages.getVal(GO_BACK)));
//        chosenCity = window.getCompoFactory().newPlainButton();
//        chosenCity.setBackground(box_);
//        chosenCity.setForeground(box_);
//        box_.add(chosenCity);
//        AbsPanel line_ = compoFactory.newLineBox();
//        //avoid vertical spaces between tiles in map
//        line_.add(mapPanel.getContainer());
//        line_.add(window.getCompoFactory().newPlainLabel(DataBase.EMPTY_STRING));
//        box_.add(line_);
//        AbsPlainButton ok_ = window.getCompoFactory().newPlainButton(WindowAiki.OK);
//        ok_.addActionListener(new ChoosePlaceEvent(this));
//        box_.add(ok_);
//        panelOptions.add(box_, GuiConstants.BORDER_LAYOUT_CENTER);
//        //panelOptions
//        addExit();
//        window.pack();
//    }
//
//    public void choosePlace() {
//        facade.choosePlace();
//        if (facade.getMiniMapCoords().getXcoords() != IndexConstants.INDEX_NOT_FOUND_ELT) {
//            return;
//        }
//        window.setSavedGame(false);
//        drawGameWalking(false);
//        AbsMetaLabelPk.paintPk(window.getImageFactory(), scene);
//        exitInteractionPack();
//    }
//
//    public void choosePlace(int _x, int _y) {
//        facade.setMiniMapCoords(_x, _y);
//        chosenCity.setText(facade.getChosenCity());
//        window.pack();
//    }

    //called while connection to a server succeeds.
    public void setNetworkPanel() {
        MenuItemUtils.setEnabledMenu(window.getFolderLoad(),false);
        MenuItemUtils.setEnabledMenu(window.getZipLoad(),false);
        MenuItemUtils.setEnabledMenu(window.getGameLoad(),false);
//        MenuItemUtils.setEnabledMenu(window.getNewGame(),false);
        facade.openMenu();
        panelMenu.setVisible(false);
//        disableFishing();
        panelNetWork = compoFactory.newPageBox();
        panelOptions.add(panelNetWork, GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPlainButton exit_ = window.getCompoFactory().newPlainButton(messages.getVal(EXIT));
        exit_.addActionListener(new ExitTradeEvent(window));
        if (window.getIndexInGame() == IndexConstants.FIRST_INDEX) {
            AbsPanel panel_ = compoFactory.newLineBox();
            AbsPlainButton trade_ = window.getCompoFactory().newPlainButton(messages.getVal(TRADE));
            trade_.addActionListener(new ValidateTradingEvent(window));
            panel_.add(trade_);
            panel_.add(exit_);
            panelOptions.add(panel_, GuiConstants.BORDER_LAYOUT_SOUTH);
        } else {
            panelOptions.add(exit_, GuiConstants.BORDER_LAYOUT_SOUTH);
        }
    }

    public void setTradable(ByteTreeMap< PokemonPlayer> _team) {
        ByteTreeMap<UsablePokemon> teamPks_ = new ByteTreeMap<UsablePokemon>();
        for (EntryCust<Byte, PokemonPlayer> e: _team.entryList()) {
            teamPks_.put(e.getKey(), e.getValue());
        }
        teamPan = initTeam(teamPks_, POKEMON_SELECT, true);
        teamPan.getListe().setListener(new PokemonSelectionTrading(this));
        panelNetWork.add(teamPan.getContainer());
        AbsPanel group_ = compoFactory.newBorder();
        group_.add(window.getCompoFactory().newPlainLabel(messages.getVal(RECEIVED_POKEMON)), GuiConstants.BORDER_LAYOUT_NORTH);
        AbsScrollPane scrollSession_ = compoFactory.newAbsScrollPane();
        receivedPk = new RenderedPage(scrollSession_, window.getFrames(),new FixCharacterCaseConverter());
//        receivedPk.setFiles(facade.getData().getWebPk(), Resources.ACCESS_TO_DEFAULT_FILES);
        receivedPk.setFrame(window.getCommonFrame());
//        receivedPk.prepare();
        scrollSession_.setPreferredSize(new MetaDimension(400, 300));
        group_.add(scrollSession_, GuiConstants.BORDER_LAYOUT_CENTER);
        panelNetWork.add(group_);
        enabledReady = false;
        readyCheck = window.getCompoFactory().newCustCheckBox(messages.getVal(READY));
        readyCheck.setEnabled(enabledReady);
        readyCheck.addActionListener(new ReadyEventAiki(window, readyCheck));
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
//        AbstractThread thread_ = window.getPreparedPkNetThread();
        PreparedRenderedPages task_ = window.getPreparedPkNetTask();
//        if (thread_ == null || thread_.isAlive() || task_ == null) {
//            return;
//        }
        if (receivedPk.isProcessing()) {
            return;
        }
        ((PokemonStandards)task_.getBeanNatLgNames()).setDataBase(facade);
        FrameHtmlData.initializeOnlyConf(task_, facade.getLanguage(), ((PokemonStandards)task_.getBeanNatLgNames()), receivedPk);
    }

//    public void interact() {
//        endGame.setText(facade.getEndGameMessage());
//        endGame.setVisible(facade.isShowEndGame());
//        placeName.setText(facade.getCurrentPlace());
//        initInteraction();
//        if (facade.getInterfaceType() != InterfaceType.RIEN) {
//            buttonInteract.setEnabled(true);
//        } else if (interaction != null) {
//            buttonInteract.setEnabled(false);
//        }
//        setTextArea(StringUtil.join(facade.getGame().getCommentGame().getMessages(), RETURN_LINE), GuiConstants.INFORMATION_MESSAGE);
//        setPaintingScene(false);
//    }

//    private void initInteraction() {
//        if (interaction != null) {
//            return;
//        }
//        interaction = compoFactory.newLineBox();
//        buttonInteract = window.getCompoFactory().newPlainButton(messages.getVal(INTERACT));
//        buttonInteract.addActionListener(new InteractSceneEvent(this));
//        interaction.add(buttonInteract);
//        fish = window.getCompoFactory().newPlainButton(messages.getVal(CST_FISH));
//        fish.addActionListener(new FishingEvent(this));
//        interaction.add(fish);
//        interaction.add(attract);
//    }

//    public void interactScene() {
//        if (!buttonInteract.isEnabled()) {
//            return;
//        }
//        if (!facade.isEnabledMovingHero()) {
//            return;
//        }
//        facade.interactNoFish();
//        if (!facade.isChangeToFightScene()) {
//            setTextArea(StringUtil.join(facade.getGame().getCommentGame().getMessages(), RETURN_LINE), GuiConstants.INFORMATION_MESSAGE);
//        }
//        showOptions();
//        window.pack();
//    }

//    public void attract() {
//        facade.attract();
//        after();
//    }
//    public void fishing() {
//        if (facade.isFishArea()) {
//            facade.initFishing();
//            after();
//        }
//    }
//
//    private void after() {
//        if (facade.isChangeToFightScene()) {
//            window.setSavedGame(false);
//            window.setFight(true, true);
//        }
//    }

//    private void showOptions() {
//        panelOptions.removeAll();
//        if (facade.getInterfaceType() == InterfaceType.ECH_BOITE) {
//            selectedForSwitch = window.getCompoFactory().newPlainLabel("");
//            AbsPanel storage_ = compoFactory.newGrid(0, 1);
//            selectPkBox = window.getCompoFactory().newPlainButton(messages.getVal(SELECT_PK_BOX));
//            selectPkBox.addActionListener(new SelectPokemonBoxEvent(this));
//            storage_.add(selectPkBox);
//            selectEggBox = window.getCompoFactory().newPlainButton(messages.getVal(SELECT_EGG_BOX));
//            selectEggBox.addActionListener(new SelectEggBoxEvent(this));
//            storage_.add(selectEggBox);
//            storage_.add(window.getCompoFactory().newAbsPaintableLabel());
//            takeItem = window.getCompoFactory().newPlainButton(messages.getVal(TAKE_ITEM));
//            takeItem.setEnabled(false);
//            takeItem.addActionListener(new GearStorageEvent(this, StorageActions.TAKE_ITEM_BOX));
//            storage_.add(takeItem);
//            store = window.getCompoFactory().newPlainButton(messages.getVal(CST_STORE));
//            store.setEnabled(false);
//            store.addActionListener(new GearStorageEvent(this, StorageActions.STORE));
//            storage_.add(store);
//            withdraw = window.getCompoFactory().newPlainButton(messages.getVal(WITHDRAW_PK));
//            withdraw.setEnabled(false);
//            withdraw.addActionListener(new GearStorageEvent(this, StorageActions.WIDRAW_PK));
//            storage_.add(withdraw);
//            withdrawEgg = window.getCompoFactory().newPlainButton(messages.getVal(WITHDRAW_EGG));
//            withdrawEgg.setEnabled(false);
//            withdrawEgg.addActionListener(new GearStorageEvent(this, StorageActions.WIDRAW_EGG));
//            storage_.add(withdrawEgg);
//            switchPk = window.getCompoFactory().newPlainButton(messages.getVal(SWITCH_PK_EGG));
//            switchPk.setEnabled(false);
//            switchPk.addActionListener(new GearStorageEvent(this, StorageActions.SWITCH_TEAM_BOX));
//            storage_.add(switchPk);
//            storage_.add(window.getCompoFactory().newAbsPaintableLabel());
//            release = window.getCompoFactory().newPlainButton(messages.getVal(CST_RELEASE));
//            release.setEnabled(false);
//            release.addActionListener(new GearStorageEvent(this, StorageActions.RELEASE));
//            storage_.add(release);
//            storage_.add(selectedForSwitch);
//            AbsPanel set_ = compoFactory.newLineBox();
//            initTeam();
//            teamPan.addListenerStorage(this);
//            set_.add(teamPan.getContainer());
//            set_.add(storage_);
//            panelOptions.add(set_, GuiConstants.BORDER_LAYOUT_CENTER);
//            panelMenu.setVisible(false);
//            disableFishing();
//        } else if (facade.getInterfaceType() == InterfaceType.MOVE_TUTORS) {
//            initPkTeamMoveTutors();
//            AbsPanel set_ = compoFactory.newLineBox();
//            set_.add(teamPan.getContainer());
//            movesLearnt = compoFactory.newGrid(0,1);
//            AbsScrollPane scroll_ = compoFactory.newAbsScrollPane(movesLearnt);
//            scroll_.setPreferredSize(new MetaDimension(100, 220));
//            set_.add(scroll_);
//            panelOptions.add(set_, GuiConstants.BORDER_LAYOUT_CENTER);
//            panelMenu.setVisible(false);
//            disableFishing();
//        } else if (facade.getInterfaceType() == InterfaceType.ACHATS_CT) {
//            tmPanel = new TmPanel(window,5, messages.getVal(TM_TITLE), facade);
//            AbsPanel set_ = compoFactory.newPageBox();
//            AbsPlainButton selectItem_ = window.getCompoFactory().newPlainButton(messages.getVal(TM_SELECT));
//            selectItem_.addActionListener(new AddTmEvent(this));
//            set_.add(selectItem_);
//            AbsPlainButton removeItem_ = window.getCompoFactory().newPlainButton(messages.getVal(TM_REMOVE));
//            removeItem_.addActionListener(new RemoveTmEvent(this));
//            set_.add(removeItem_);
//            set_.add(tmPanel.getContainer());
//            AbsPlainButton changeInv_ = window.getCompoFactory().newPlainButton(messages.getVal(TM_BUY));
//            changeInv_.addActionListener(new BuyTmEvent(this));
//            set_.add(changeInv_);
//            panelOptions.add(set_, GuiConstants.BORDER_LAYOUT_CENTER);
//            panelMenu.setVisible(false);
//            disableFishing();
//        } else if (facade.getInterfaceType() == InterfaceType.ACHATS) {
//            AbsPanel set_ = compoFactory.newPageBox();
//            buy = window.getCompoFactory().newCustCheckBox(messages.getVal(ITEM_BUY));
//            buy.setSelected(true);
//            buy.addActionListener(new BuyOrSellEvent(this));
//            set_.add(buy);
//            itemsPan = new ItemsPanel(window, 2, messages.getVal(ITEM_TITLE), facade);
//            AbsPlainButton selectItem_ = window.getCompoFactory().newPlainButton(messages.getVal(ITEM_SELECT));
//            selectItem_.addActionListener(new SelectItemForListEvent(this));
//            set_.add(selectItem_);
//            AbsPlainButton addItem_ = window.getCompoFactory().newPlainButton(messages.getVal(ITEM_ADD));
//            addItem_.addActionListener(new ChangeItemListEvent(this, true));
//            set_.add(addItem_);
//            AbsPlainButton removeItem_ = window.getCompoFactory().newPlainButton(messages.getVal(ITEM_REMOVE));
//            removeItem_.addActionListener(new ChangeItemListEvent(this, false));
//            set_.add(removeItem_);
//            set_.add(itemsPan.getContainer());
//            AbsPlainButton changeInv_ = window.getCompoFactory().newPlainButton(messages.getVal(ITEM_BUY_SELL));
//            changeInv_.addActionListener(new BuyItemsEvent(this));
//            set_.add(changeInv_);
//            panelOptions.add(set_, GuiConstants.BORDER_LAYOUT_CENTER);
//            panelMenu.setVisible(false);
//            disableFishing();
//        } else if (facade.getInterfaceType() == InterfaceType.PENSION) {
//            AbsPanel set_ = compoFactory.newLineBox();
//            ByteTreeMap<UsablePokemon> teamPks_ = new ByteTreeMap<UsablePokemon>();
//            ByteTreeMap< PokemonPlayer> team_ = facade.getGame().getPlayer().getPokemonPlayerList();
//            for (EntryCust<Byte, PokemonPlayer> e: team_.entryList()) {
//                teamPks_.put(e.getKey(), e.getValue());
//            }
//            teamPan = initTeam(teamPks_, POKEMON_SELECT_TWO, false);
//            teamPan.addListenerHost(this);
//            set_.add(teamPan.getContainer());
//            AbsPanel form_ = compoFactory.newPageBox();
//            int nbRemSteps_ = facade.getRemaingingSteps();
//            String buttonText_= StringUtil.simpleNumberFormat(messages.getVal(GET_EGG), nbRemSteps_);
//            AbsPlainButton receiveEgg_ = window.getCompoFactory().newPlainButton(buttonText_);
//            receiveEgg_.addActionListener(new ReceiveFromHostEvent(this, true));
//            form_.add(receiveEgg_);
//            buttonText_= StringUtil.simpleNumberFormat(messages.getVal(GET_EGG_PARENT), nbRemSteps_);
//            AbsPlainButton receiveParents_ = window.getCompoFactory().newPlainButton(buttonText_);
//            receiveParents_.addActionListener(new ReceiveFromHostEvent(this, false));
//            form_.add(receiveParents_);
//            AbsPlainButton hostPk_ = window.getCompoFactory().newPlainButton(messages.getVal(HOST_PK));
//            hostPk_.addActionListener(new HostPokemonEvent(this));
//            form_.add(hostPk_);
//            set_.add(form_);
//            panelOptions.add(set_, GuiConstants.BORDER_LAYOUT_CENTER);
//            panelMenu.setVisible(false);
//            disableFishing();
//        } else if (facade.getInterfaceType() == InterfaceType.FOSSILE) {
//            window.setSavedGame(false);
//        } else if (facade.isChangeToFightScene()) {
//            window.setSavedGame(false);
//            window.setFight(true, false);
//            return;
//        }
//        addExit();
//    }

    private TeamPanel initTeam(ByteTreeMap<UsablePokemon> _team, String _key, boolean _single) {
        return new TeamPanel(window,2, messages.getVal(_key), facade, _team, messagesTeamPanel, _single);
    }

//    private void disableFishing() {
//        if (fish == null) {
//            return;
//        }
//        fish.setEnabled(false);
//    }

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

//    public void hostPokemon() {
//        facade.attemptForStoringPokemonToHost();
//        boolean exited_ = false;
//        String info_ = StringUtil.join(facade.getGame().getCommentGame().getMessages(), RETURN_LINE);
//        if (facade.getFirstSelectPkToHost() == IndexConstants.INDEX_NOT_FOUND_ELT) {
//            if (facade.getSecondSelectPkToHost() == IndexConstants.INDEX_NOT_FOUND_ELT) {
//                window.setSavedGame(false);
//                exited_ = true;
//                setTextArea(info_, GuiConstants.INFORMATION_MESSAGE);
//                exitInteractionPack();
//            }
//        }
//        if (!exited_) {
//            setTextArea(info_, GuiConstants.ERROR_MESSAGE);
//        }
//    }

//    public void receiveFromHost(boolean _onlyEgg) {
//        facade.receiveFromHost(_onlyEgg);
//        window.setSavedGame(false);
//        String info_ = StringUtil.join(facade.getGame().getCommentGame().getMessages(), RETURN_LINE);
//        if (!facade.getGame().isReinitInteraction()) {
//            setTextArea(info_, GuiConstants.INFORMATION_MESSAGE);
//            exitInteractionPack();
//        } else {
//            setTextArea(info_, GuiConstants.ERROR_MESSAGE);
//        }
//    }

//    public void addTmToBuy() {
//        SelectTm.setSelectTm(window, facade, true);
//        boolean isSelectedIndex_ = SelectTm.isSelectedIndex(window.getSelectTm());
//        boolean ok_ = SelectTm.isOk(window.getSelectTm());
//        if (!ok_) {
//            return;
//        }
//        if (!isSelectedIndex_) {
//            return;
//        }
//        facade.addTmToBuy();
//        tmPanel.initItems();
//        window.pack();
//    }
//
//    public void removeTmToBuy() {
//        if (!tmPanel.isSelected()) {
//            return;
//        }
//        facade.removeTmToBuy(tmPanel.getSelectedItem());
//        tmPanel.initItems();
//        window.pack();
//    }
//
//    public void buyTm() {
//        facade.buyTm();
//        if (!facade.canBeBoughtTm()) {
//            setTextArea(messages.getVal(NO_POSSIBLE_BUY), GuiConstants.ERROR_MESSAGE);
//            return;
//        }
//        window.setSavedGame(false);
//        exitInteractionPack();
//        window.pack();
//    }
//
//    public void clearItemList() {
//        facade.clearItemsToBuyOrSell();
//        itemsPan.initItems();
//        window.pack();
//    }
//
//    public void selectItemForList() {
//        SelectItem.setSelectItem(window, facade, true, !buy.isSelected());
//        boolean isSelectedIndex_ = SelectItem.isSelectedIndex(window.getSelectItem());
//        boolean ok_ = SelectItem.isOk(window.getSelectItem());
//        if (!ok_) {
//            return;
//        }
//        if (!isSelectedIndex_) {
//            return;
//        }
//        facade.addItemToBuyOrSell();
//        itemsPan.initItems();
//        window.pack();
//    }
//
//    public void addOrRemoveItemToBuyOrSell(boolean _add) {
//        if (!itemsPan.isSelected()) {
//            return;
//        }
//        facade.addOrRemoveItemToBuyOrSell(itemsPan.getSelectedItem(), _add);
//        itemsPan.initItems();
//        window.pack();
//    }
//
//    public void buyItems() {
//        facade.buyOrSellItems(buy.isSelected());
//        if (!facade.canBeBought()) {
//            setTextArea(messages.getVal(NO_POSSIBLE_BUY), GuiConstants.ERROR_MESSAGE);
//            return;
//        }
//        window.setSavedGame(false);
//        exitInteractionPack();
//        window.pack();
//    }
//
//    public void selectPokemonBox() {
//        int lineBack_ = facade.getLineFirstBox();
//        SelectPokemon.setSelectPokemon(window, facade, true, window.getSelectPokemon());
//        SelectPokemon.isSelectedIndex(window.getSelectPokemon());
//        if (!SelectPokemon.isStaticOk(window.getSelectPokemon())) {
//            facade.setLinePokemonFirstBox(lineBack_);
//        } else {
//            setEnablingStoring();
////            StringList messages_;
////            messages_ = getMessagesStorage();
////            setTextArea(messages_.join(RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
//        }
//        facade.clearSortingFirstBox();
//    }
//
//    public void selectEggBox() {
//        int lineBack_ = facade.getLineEgg();
//        SelectEgg.setSelectEgg(window, facade, window.getSelectEgg());
//        SelectEgg.isSelectedIndex(window.getSelectEgg());
//        if (!SelectEgg.isOk(window.getSelectEgg())) {
//            facade.setLineEggs(lineBack_);
//        } else {
//            setEnablingStoring();
////            StringList messages_;
////            messages_ = getMessagesStorage();
////            setTextArea(messages_.join(RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
//        }
//        facade.clearSortingEgg();
//    }
//
//    public void gearStorage(StorageActions _action) {
//        window.setSavedGame(false);
//        facade.gearStorage(_action);
//        refreshTeam();
//        setEnablingStoring();
//        window.pack();
//    }

//    private void setEnablingStoring() {
//        selectPkBox.setEnabled(true);
//        selectEggBox.setEnabled(true);
//        takeItem.setEnabled(facade.canTakeItemFromStorage());
//        store.setEnabled(facade.isSelectedPkTeamStorage());
//        //cancelSelect.setEnabledLabel(true);
//        withdraw.setEnabled(facade.getSelectedPokemonFirstBox() != null);
//        withdrawEgg.setEnabled(facade.getSelectedEggBox() != null);
//        //        boolean selectedBox_ = false;
////        if (facade.getSelectedPokemonFirstBox() != null) {
////            selectedBox_ = true;
////        }
////        if (facade.getSelectedEggBox() != null) {
////            selectedBox_ = true;
////        }
////        switchPk.setEnabledLabel(selectedBox_ && facade.isSelectedPkTeamStorage());
//        switchPk.setEnabled(facade.isSwitchable());
//        release.setEnabled(facade.isReleasable());
//    }

//    private void cancelChosenTeamPokemon() {
//        facade.cancelChosenTeamPokemon();
//        enabledClick = false;
//        teamPan.deselect();
//        enabledClick = true;
//    }

//    private void initPkTeamMoveTutors() {
//        ByteTreeMap<PokemonPlayer> team_ = facade.getPlayer().getPokemonPlayerList();
//        ByteTreeMap<UsablePokemon> pks_ = new ByteTreeMap<UsablePokemon>();
//        for (EntryCust<Byte, PokemonPlayer> e: team_.entryList()) {
//            pks_.put(e.getKey(), e.getValue());
//        }
//        teamPan = initTeam(pks_, POKEMON_SELECT, true);
//        teamPan.addListenerMoveTutor(this);
//    }
//
//    private void refreshPkTeamMoveTutors() {
//        enabledClick = false;
//        ByteTreeMap<PokemonPlayer> team_ = facade.getPlayer().getPokemonPlayerList();
//        ByteTreeMap<UsablePokemon> pks_ = new ByteTreeMap<UsablePokemon>();
//        for (EntryCust<Byte, PokemonPlayer> e: team_.entryList()) {
//            pks_.put(e.getKey(), e.getValue());
//        }
//        teamPan.initFighters(pks_,messagesTeamPanel);
//        enabledClick = true;
//    }
//
//    private void initTeam() {
//        ByteTreeMap<UsablePokemon> pks_ = new ByteTreeMap<UsablePokemon>();
//        byte i_ = IndexConstants.FIRST_INDEX;
//        for (UsablePokemon p: facade.getPlayer().getTeam()) {
//            pks_.put(i_, p);
//            i_++;
//        }
//        teamPan = initTeam(pks_, POKEMON_SELECT, true);
//    }

//    private void refreshTeam() {
//        enabledClick = false;
//        ByteTreeMap<UsablePokemon> pks_ = new ByteTreeMap<UsablePokemon>();
//        byte i_ = IndexConstants.FIRST_INDEX;
//        for (UsablePokemon p: facade.getPlayer().getTeam()) {
//            pks_.put(i_, p);
//            i_++;
//        }
//        teamPan.initFighters(pks_,messagesTeamPanel);
//        enabledClick = true;
//    }

//    private void addButtonsTeam() {
//        AbsPanel set_ = compoFactory.newLineBox();
//        AbsPanel teamMenu_ = compoFactory.newPageBox();
//        switchUsable = window.getCompoFactory().newCustCheckBox(messages.getVal(SWITCH_PK_TEAM));
////        enabledSwitchTeam = false;
////        switchUsable.addChangeListener(new ChangeListener() {
////            @Override
////            public void stateChanged(ChangeEvent _arg0) {
////                enabledSwitchTeam = !enabledSwitchTeam;
////            }
////        });
//        teamMenu_.add(switchUsable);
//        takeItemTeam = window.getCompoFactory().newPlainButton(messages.getVal(TAKE_ITEM_TEAM));
//        takeItemTeam.setEnabled(false);
//        takeItemTeam.addActionListener(new TakeItemFromTeamEvent(this));
//        teamMenu_.add(takeItemTeam);
//        detailPk = window.getCompoFactory().newPlainButton(messages.getVal(DETAIL_TEAM));
//        detailPk.setEnabled(false);
//        detailPk.addActionListener(new SeePokemonDetailEvent(this));
//        teamMenu_.add(detailPk);
//        healPk = window.getCompoFactory().newPlainButton(messages.getVal(HEAL_PK));
//        healPk.setEnabled(false);
//        healPk.addActionListener(new HealPokemonEvent(this));
//        teamMenu_.add(healPk);
//        nicknamePk = window.getCompoFactory().newPlainButton(messages.getVal(NICKNAME));
//        nicknamePk.setEnabled(false);
//        nicknamePk.addActionListener(new ChangeNicknameEvent(this));
//        teamMenu_.add(nicknamePk);
//        set_.add(teamPan.getContainer());
//        set_.add(teamMenu_);
//        panelOptions.add(set_, GuiConstants.BORDER_LAYOUT_CENTER);
//    }

//    public void takeItemFromTeam() {
//        facade.takeObjectFromTeam();
//        window.setSavedGame(false);
//        setTextArea(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE), GuiConstants.INFORMATION_MESSAGE);
//        refreshTeam();
//        //disable the button after taking
//        takeItemTeam.setEnabled(false);
//        window.pack();
//    }

//    public void seePokemonDetail() {
////        if (window.showErrorMessageDialog(ForwardingJavaCompiler.getMess(Constants.getLanguage()))) {
////            return;
////        }
////        UsablePokemon p_ = facade.getSelectedPkTeam();
////        if (!(p_ instanceof PokemonPlayer)) {
////            return;
////        }
////        AbstractThread thread_ = window.getPreparedPkThread();
//        PreparedRenderedPages task_ = window.getPreparedPkTask();
////        if (thread_ == null || thread_.isAlive() || task_ == null) {
////            return;
////        }
//        RenderedPage session_;
//        session_ = new RenderedPage(compoFactory.newAbsScrollPane(), window.getFrames());
//        showHtmlDialog(window, session_,facade,task_,facade.getLanguage());
//    }

//    public void healPokemon() {
//        int lineBack_ = facade.getLineHealingItem();
//        SelectHealingItem.setSelectHealingItem(window, facade);
//        boolean isSelectedIndex_ = SelectHealingItem.isSelectedIndex(window.getSelectHealingItem());
//        boolean ok_ = SelectHealingItem.isOk(window.getSelectHealingItem());
//        if (!ok_) {
//            facade.setLineHealingItem(lineBack_);
//            facade.clearSortingHealingItem();
//        } else if (isSelectedIndex_) {
//            facade.setGivingObject(false);
//            facade.setChosenHealingItemWalk();
//            facade.clearSortingHealingItem();
//            facade.getPlayer().getIndexesOfPokemonTeam().clear();
//            facade.useObject();
//            facade.selectPokemon((short) teamPan.getSelectedIndexSingle());
//            if (facade.getPlayer().getSelectedObject().isEmpty()) {
//                window.setSavedGame(false);
//                setTextArea(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE), GuiConstants.INFORMATION_MESSAGE);
//                return;
//            }
//            if (facade.getPlayer().getChosenMoves().isEmpty()) {
//                window.setSavedGame(false);
//                setTextArea(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE), GuiConstants.INFORMATION_MESSAGE);
//                return;
//            }
//            SelectHealedMove.setSelectHealedMove(window, facade);
//        } else {
//            facade.clearSortingHealingItem();
//        }
//    }

//    public void changeNickname() {
//        String lg_ = window.getLanguageKey();
//        TextAnswerValue confirmDialog_ = window.getConfirmDialogText().input(window.getCommonFrame(), DataBase.EMPTY_STRING, messages.getVal(NICKNAME), messages.getVal(NICKNAME), lg_);
//        if (confirmDialog_.getAnswer() != GuiConstants.YES_OPTION) {
//            return;
//        }
//        facade.validateNickname(confirmDialog_.getTypedText());
//    }

//    public void selectPokemonMoveTutor() {
//        if (!enabledClick) {
//            return;
//        }
//        facade.choosePokemonForMoveTutors((short) teamPan.getSelectedIndex());
//        movesLearnt.removeAll();
//        movesLearnt.add(window.getCompoFactory().newPlainLabel(messages.getVal(SELECT_MT)));
//        StringList selectedMoves_ = facade.getSelectedMoves();
//        for (String m: selectedMoves_) {
//            String tr_ = facade.translateMove(m);
//            MoveTutorCheckBox check_ = new MoveTutorCheckBox(m,tr_, true,this);
//            check_.setBackground(GuiConstants.RED);
//            check_.setSelected(true);
//            movesLearnt.add(check_.getComponent());
//        }
//        StringList unselectedMoves_ = facade.getUnselectedMoves();
//        StringMap<Short> chosenMoves_ = facade.getPlayer().getChosenMoves();
//        for (String m: unselectedMoves_) {
//            String tr_ = facade.translateMove(m);
//            MoveTutorCheckBox check_ = new MoveTutorCheckBox(m, StringUtil.concat(tr_,SPACE,Long.toString(chosenMoves_.getVal(m))),false,this);
//            check_.setBackground(GuiConstants.WHITE);
//            check_.setSelected(false);
//            movesLearnt.add(check_.getComponent());
//        }
//        AbsPlainButton cancel_ = window.getCompoFactory().newPlainButton(messages.getVal(CANCEL_MT));
//        cancel_.addActionListener(new CancelMtEvent(this));
//        movesLearnt.add(cancel_);
//        AbsPlainButton ok_ = window.getCompoFactory().newPlainButton(messages.getVal(VALIDATE_MT));
//        ok_.addActionListener(new ValidateMtEvent(this));
//        movesLearnt.add(ok_);
//        window.pack();
//    }
//
//    public void cancelMt() {
//        facade.cancelLearningMoveOnPokemon();
//        enabledClick = false;
//        teamPan.deselect();
//        enabledClick = true;
//    }
//
//    public void validateMt() {
//        facade.learnMovesByMoveTutor();
//        String comment_ = StringUtil.join(facade.getComment().getMessages(), RETURN_LINE);
//        if (facade.getPlayer().getChosenTeamPokemon() != IndexConstants.INDEX_NOT_FOUND_ELT) {
//            setTextArea(comment_, GuiConstants.ERROR_MESSAGE);
//            return;
//        }
//        window.setSavedGame(false);
//        setTextArea(comment_, GuiConstants.INFORMATION_MESSAGE);
//        refreshPkTeamMoveTutors();
//        movesLearnt.removeAll();
//        window.pack();
//    }
//
//    public void checkMoveTutor(String _key) {
//        facade.addOrDeleteMove(_key);
////        String text_ = StringList.simpleFormat(messages.getVal(SELECTED_MOVES), facade.getSelectedMoves().size());
////        setTextArea(text_);
//    }
//
//    public void selectPkTeamStorage() {
//        if (!enabledClick) {
//            return;
//        }
//        facade.setChosenTeamPokemon((short) teamPan.getSelectedIndex());
//        setEnablingStoring();
//    }
//
//    public void selectPkTeam() {
//        if (!enabledClick) {
//            return;
//        }
//        if (switchUsable.isSelected()) {
//            window.setSavedGame(false);
//            facade.switchTeamOrder((short) teamPan.getSelectedIndex());
//            switchUsable.setSelected(false);
////            enabledSwitchTeam = false;
//            refreshTeam();
//            window.pack();
//            return;
//        }
//        facade.setChosenTeamPokemon((short) teamPan.getSelectedIndex());
//        if (detailPk != null) {
//            detailPk.setEnabled(facade.isSelectedTeamPokemon());
//        }
//        if (takeItemTeam != null) {
////            takeItemTeam.setEnabledLabel(facade.isSelectedTeamPokemon());
//            takeItemTeam.setEnabled(facade.isSelectedTeamPokemonItem());
//        }
//        if (healPk != null) {
//            healPk.setEnabled(facade.isSelectedTeamPokemon());
//        }
//        if (nicknamePk != null) {
//            nicknamePk.setEnabled(facade.isSelectedTeamPokemon());
//        }
//    }

//    private void addExit() {
//        AbsPlainButton exit_ = window.getCompoFactory().newPlainButton(messages.getVal(EXIT));
//        exit_.addActionListener(new ExitInteractionEvent(this));
//        panelOptions.add(exit_, GuiConstants.BORDER_LAYOUT_SOUTH);
//    }

//    public void exitInteractionPack() {
//        exitInteraction();
//        window.pack();
//    }

    public void exitInteraction() {
        panelOptions.removeAll();
//        if (switchUsable != null) {
//            switchUsable.setSelected(false);
//        }
//        enabledSwitchTeam = false;
        panelMenu.setVisible(true);
//        enableIfPossibleFishing();
        facade.exitInteract();
    }

//    private void enableIfPossibleFishing() {
//        if (fish == null) {
//            return;
//        }
//        fish.setEnabled(facade.isFishArea());
//    }

//    private void showHtmlDialog(WindowAiki _parent, RenderedPage _session, FacadeGame _dataBase, PreparedRenderedPages _pre, String _lg) {
////        DialogHtmlData.setDialogHtmlData(_parent, messages.getVal(TITLE_DETAIL), _session, window.isSuccessfulCompile());
//        DialogHtmlData.setDialogHtmlData(_parent, messages.getVal(TITLE_DETAIL), _session,_dataBase,_pre,_lg);
//    }

//    public void selectPokemon() {
//        if (!teamPan.isSelected()) {
//            //facade.cancelUsingObjectOnPokemon();
//            return;
//        }
//        facade.selectPokemon((short) teamPan.getSelectedIndexSingle());
//        if (facade.isGivingObject()) {
//            window.setSavedGame(false);
//            exitInteractionPack();
//            return;
//        }
//        if (facade.getPlayer().getSelectedObject().isEmpty()) {
//            setTextArea(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE), GuiConstants.INFORMATION_MESSAGE);
//            window.setSavedGame(false);
//            exitInteractionPack();
//            return;
//        }
//        boolean used_ = false;
//        if (facade.usedObjectForEvolving()) {
//            setMovesAbilities();
//            used_ = true;
//        }
//        if (facade.usedObjectForHealingAmove()) {
//            setHealedMoves();
//            used_ = true;
//        }
//        if (facade.usedObjectForBoosting()) {
//            setBoostedMoves();
//            used_ = true;
//        }
//        if (!used_) {
//            setTextArea(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE), GuiConstants.INFORMATION_MESSAGE);
//            window.setSavedGame(false);
//            exitInteractionPack();
//            return;
//        }
//        window.pack();
//    }

//    private void setMovesAbilities() {
//        movesLearnt.removeAll();
//        movesLearnt.add(window.getCompoFactory().newPlainLabel(messages.getVal(SELECT_MT)));
////        Map<String,Boolean> selected_ = facade.getPlayer().getMovesToBeKeptEvo();
////        StringList kept_ = new StringList(selected_.getKeys(true));
//        StringList kept_ = facade.getKeptMovesToEvo();
////        kept_.sort(new Comparator<String>() {
////            @Override
////            public int compare(String _o1, String _o2) {
////                String trOne_ = facade.translateMove(_o1);
////                String trTwo_ = facade.translateMove(_o2);
////                return trOne_.compareTo(trTwo_);
////            }
////        });
//        kept_.sortElts(new TrMovesComparator(facade.getData()));
//        for (String m: kept_) {
//            String tr_ = facade.translateMove(m);
//            MoveEvoCheckBox check_ = new MoveEvoCheckBox(m,tr_,true, this);
//            check_.setBackground(GuiConstants.RED);
//            movesLearnt.add(check_.getComponent());
//        }
////        StringList unkept_ = new StringList(selected_.getKeys(false));
//        StringList unkept_ = facade.getUnKeptMovesToEvo();
////        unkept_.sort(new Comparator<String>() {
////            @Override
////            public int compare(String _o1, String _o2) {
////                String trOne_ = facade.translateMove(_o1);
////                String trTwo_ = facade.translateMove(_o2);
////                return trOne_.compareTo(trTwo_);
////            }
////        });
//        unkept_.sortElts(new TrMovesComparator(facade.getData()));
//        for (String m: unkept_) {
//            String tr_ = facade.translateMove(m);
//            MoveEvoCheckBox check_ = new MoveEvoCheckBox(m,tr_,false, this);
//            check_.setBackground(GuiConstants.WHITE);
//            movesLearnt.add(check_.getComponent());
//        }
//        StringList ab_ = facade.getPlayer().getNewAbilitiesToBeChosen();
//        abilities.removeAll();
//        abilityLabels.clear();
//        if (!ab_.isEmpty()) {
//            abilities.add(window.getCompoFactory().newPlainLabel(messages.getVal(SELECT_ABILITY)));
//            for (String a: ab_) {
//                AbilityLabel lab_ = new AbilityLabel(facade.translateAbility(a), a, window.getCompoFactory());
//                lab_.addMouseListener(new AbilityWalkEvent(this, a));
//                abilities.add(lab_.getPaintableLabel());
//                abilityLabels.add(lab_);
//            }
//            abilities.add(window.getCompoFactory().newAbsPaintableLabel());
//        }
//        AbsPlainButton ok_ = window.getCompoFactory().newPlainButton(messages.getVal(EVOLVE));
//        ok_.addActionListener(new EvolvePokemonEvent(this));
//        abilities.add(ok_);
//    }

//    public void evolvePokemon() {
//        facade.evolvePokemon();
//        String info_ = StringUtil.join(facade.getComment().getMessages(), RETURN_LINE);
//        if (!facade.usedObjectForEvolving()) {
//            setTextArea(info_, GuiConstants.INFORMATION_MESSAGE);
//            window.setSavedGame(false);
//            exitInteractionPack();
//        } else {
//            setTextArea(info_, GuiConstants.ERROR_MESSAGE);
//        }
//    }

//    private void setHealedMoves() {
//        movesLearnt.removeAll();
//        movesLearnt.add(window.getCompoFactory().newPlainLabel(messages.getVal(SELECT_HEAL_MOVE)));
//        StringMap<Short> moves_ = facade.getPlayer().getChosenMoves();
//        StringList keys_ = new StringList(moves_.getKeys());
////        keys_.sort(new Comparator<String>() {
////            @Override
////            public int compare(String _o1, String _o2) {
////                String trOne_ = facade.translateMove(_o1);
////                String trTwo_ = facade.translateMove(_o2);
////                return trOne_.compareTo(trTwo_);
////            }
////        });
//        keys_.sortElts(new TrMovesComparator(facade.getData()));
//        for (String m: keys_) {
//            String tr_ = facade.translateMove(m);
//            AbsPlainButton check_ = window.getCompoFactory().newPlainButton(StringUtil.concat(tr_,SPACE,Long.toString(moves_.getVal(m))));
//            check_.addActionListener(new HealMoveEvent(this, m));
//            check_.setBackground(GuiConstants.WHITE);
//            movesLearnt.add(check_);
//        }
//    }
//
//    private void setBoostedMoves() {
//        movesLearnt.removeAll();
//        movesLearnt.add(window.getCompoFactory().newPlainLabel(messages.getVal(SELECT_BOOST_MOVE)));
//        StringMap<Short> moves_ = facade.getPlayer().getChosenMoves();
//        StringList keys_ = new StringList(moves_.getKeys());
////        keys_.sort(new Comparator<String>() {
////            @Override
////            public int compare(String _o1, String _o2) {
////                String trOne_ = facade.translateMove(_o1);
////                String trTwo_ = facade.translateMove(_o2);
////                return trOne_.compareTo(trTwo_);
////            }
////        });
//        keys_.sortElts(new TrMovesComparator(facade.getData()));
//        for (String m: keys_) {
//            String tr_ = facade.translateMove(m);
//            AbsPlainButton check_ = window.getCompoFactory().newPlainButton(StringUtil.concat(tr_,SPACE,Long.toString(moves_.getVal(m))));
//            check_.addActionListener(new BoostMoveEvent(this, m));
//            check_.setBackground(GuiConstants.WHITE);
//            movesLearnt.add(check_);
//        }
//    }

//    public void learnMove(String _move) {
//        facade.addOrDeleteMoveEvo(_move);
//    }

//    public void healMove(String _move) {
//        facade.healMove(_move);
//        window.setSavedGame(false);
//        setTextArea(StringUtil.join(facade.getPlayer().getCommentGame().getMessages(), RETURN_LINE), GuiConstants.INFORMATION_MESSAGE);
//        exitInteractionPack();
//    }
//
//    public void boostMove(String _move) {
//        facade.gainPpMax(_move);
//        window.setSavedGame(false);
//        setTextArea(StringUtil.join(facade.getPlayer().getCommentGame().getMessages(), RETURN_LINE), GuiConstants.INFORMATION_MESSAGE);
//        exitInteractionPack();
//    }
//
//    public void getAbility(String _ability) {
//        facade.getPlayer().setChosenAbilityForEvolution(_ability);
//        for (AbilityLabel l: abilityLabels) {
//            l.setSelected(_ability);
//            AbsMetaLabelPk.paintPk(window.getImageFactory(), l);
//        }
//    }
//
//    public void selectPokemonHost() {
//        if (!enabledClick) {
//            return;
//        }
//        enabledClick = false;
//        facade.setSelectPkToHost((short) teamPan.getSelectedIndex());
//        enabledClick = true;
//    }

//    public void selectPokemonTm() {
//        facade.choosePokemonForLearningMove((byte) teamPan.getSelectedIndex());
//        if (facade.getPlayer().getSelectedMove().isEmpty()) {
//            window.setSavedGame(false);
//            setTextArea(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE), GuiConstants.INFORMATION_MESSAGE);
//            exitInteractionPack();
//            return;
//        }
//        movesLearnt.removeAll();
//        movesLearnt.add(window.getCompoFactory().newPlainLabel(messages.getVal(SELECT_TM)));
//        StringMap<Short> moves_ = facade.getPlayer().getChosenMoves();
//        StringList keys_ = new StringList(moves_.getKeys());
////        keys_.sort(new Comparator<String>() {
////            @Override
////            public int compare(String _o1, String _o2) {
////                String trOne_ = facade.translateMove(_o1);
////                String trTwo_ = facade.translateMove(_o2);
////                return trOne_.compareTo(trTwo_);
////            }
////        });
//        keys_.sortElts(new TrMovesComparator(facade.getData()));
//        for (String m: keys_) {
//            String tr_ = facade.translateMove(m);
//            AbsPlainButton check_ = window.getCompoFactory().newPlainButton(StringUtil.concat(tr_,SPACE,Long.toString(moves_.getVal(m))));
//            check_.addActionListener(new LearntMoveEvent(this, m));
//            check_.setBackground(GuiConstants.WHITE);
//            movesLearnt.add(check_);
//        }
//        window.pack();
//    }

//    public void learntMove(String _move) {
//        facade.learnMove(_move);
//        window.setSavedGame(false);
//        setTextArea(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE), GuiConstants.INFORMATION_MESSAGE);
//        exitInteractionPack();
//    }

//    public boolean isMenusVisible() {
//        if (panelMenu == null) {
//            return true;
//        }
//        if (scene == null) {
//            return true;
//        }
//        return panelMenu.isVisible();
//    }

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

//    public void setTextArea(String _text, int _messageType) {
//        if (_text.isEmpty()) {
//            return;
//        }
//        String lg_ = window.getLanguageKey();
//        AbsWrappedTextArea commentsWalking_ = window.getCompoFactory().newWrappedTextArea(4, 32);
//        commentsWalking_.setEditable(false);
//        commentsWalking_.setText(_text);
//        window.getFrames().getMessageDialogAbs().input(window.getCommonFrame(), compoFactory.newAbsScrollPane(commentsWalking_), messages.getVal(TITLE_COMMENTS), lg_, _messageType);
//    }

//    public Scene getScene() {
//        return scene;
//    }

//    public boolean isPaintingScene() {
//        return paintingScene.get();
//    }

//    public void setPaintingScene(boolean _paintingScene) {
//        paintingScene.set(_paintingScene);
//        if (_paintingScene) {
//            window.disableBasic();
//        } else {
//            window.reenableBasic();
//        }
//        boolean enabled9_ = !_paintingScene;
//        team.setEnabled(enabled9_);
//        boolean enabled8_ = !_paintingScene;
//        items.setEnabled(enabled8_);
//        boolean enabled7_ = !_paintingScene;
//        tm.setEnabled(enabled7_);
//        //        difficulty.setEnabledLabel(!_paintingScene);
//        boolean enabled6_ = !_paintingScene && facade.isFishArea();
//        fish.setEnabled(enabled6_);
//        boolean enabled5_ = !_paintingScene;
//        seeBoxes.setEnabled(enabled5_);
//        boolean enabled4_ = !_paintingScene;
//        seeEggs.setEnabled(enabled4_);
//        boolean enabled3_ = !_paintingScene;
//        host.setEnabled(enabled3_);
//        boolean enabled2_ = !_paintingScene;
//        game.setEnabled(enabled2_);
//        boolean enabled1_ = !_paintingScene;
//        goBack.setEnabled(enabled1_);
//        boolean enabled_ = !_paintingScene;
//        server.setEnabled(enabled_);
////        panelMenu.repaintSecondChildren(window.getImageFactory());
//    }
    public AbsPanel getComponent() {
        return component;
    }

    public WindowNetWork getWindow() {
        return window;
    }
}
