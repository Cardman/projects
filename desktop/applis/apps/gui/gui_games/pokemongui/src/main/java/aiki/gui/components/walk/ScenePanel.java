package aiki.gui.components.walk;

//import aiki.beans.PokemonStandards;
import aiki.comparators.TrMovesComparator;
import aiki.db.DataBase;
import aiki.gui.components.AbsMetaLabelPk;
import aiki.gui.components.PkDetailContent;
import aiki.gui.components.checks.CheckBox;
import aiki.gui.components.walk.events.*;
import aiki.gui.dialogs.*;
import aiki.gui.listeners.*;
import aiki.main.PkNonModalEvent;
import aiki.map.levels.*;
import aiki.sml.MessagesPkGame;
import aiki.sml.MessagesRenderScenePanel;
import aiki.facade.FacadeGame;
import aiki.facade.enums.StorageActions;
import aiki.game.enums.InterfaceType;
import aiki.gui.WindowAiki;
import aiki.gui.components.AbilityLabel;
import aiki.gui.components.checks.MoveEvoCheckBox;
import aiki.gui.components.checks.MoveTutorCheckBox;
//import aiki.gui.dialogs.DialogServerAiki;
import aiki.map.enums.Direction;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.UsablePokemon;
//import aiki.network.NetAiki;
//import aiki.network.stream.SentPokemon;
import aiki.util.*;
import code.gui.*;
import code.gui.files.MessagesGuiFct;
import code.gui.images.MetaDimension;
import code.gui.initialize.AbsCompoFactory;
import code.maths.LgInt;
//import code.network.NetCreate;
//import code.network.SocketResults;
//import code.network.enums.ErrorHostConnectionType;
//import code.network.enums.IpType;
//import code.stream.StreamFolderFile;
import code.threads.AbstractAtomicBoolean;
import code.threads.AbstractBaseExecutorService;
import code.util.CustList;
import code.util.EntryCust;
import code.util.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class ScenePanel {

//    private static final String SCENE_PANEL = "aiki.gui.components.walk.scenepanel";

//    private static final String LOCALHOST_OLD_IP = "127.0.0.1";

//    private static final String LOCALHOST_NEW_IP = "::1";

    private static final String RETURN_LINE = "\n";

//    private static final String CST_TEAM = "team";
//
//    private static final String CST_ITEMS = "items";
//
//    private static final String CST_TM = "tm";
//
//    private static final String CST_FISH = "fish";
//
//    private static final String SEE_POKEMON = "seePokemon";
//
//    private static final String SEE_EGG = "seeEgg";
//
//    private static final String SEE_HOSTED = "seeHosted";
//
//    private static final String SEE_GAME = "seeGame";
//
//    private static final String GO_BACK_MENU = "goBackMenu";

//    private static final String CST_SERVER = "server";

//    private static final String POKEMON_SELECT = "pokemonSelect";
//
//    private static final String POKEMON_SELECT_TWO = "pokemonSelectTwo";

//    private static final String RECEIVED_POKEMON = "receivedPokemon";

//    private static final String INTERACT = "interact";
//
//    private static final String GO_BACK = "goBack";

//    private static final String TRADE = "trade";

//    private static final String EXIT = "exit";

//    private static final String READY = "ready";

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

//    private static final String NICKNAME = "nickname";

//    private static final String ERROR_USING_ITEM = "errorUsingItem";
//
//    private static final String NO_POSSIBLE_LEARN = "noPossibleLearn";
//
//    private static final String NO_POSSIBLE_BUY = "noPossibleBuy";

//    private static final String TITLE_DETAIL = "titleDetail";

    private static final String SPACE = " ";

//    private static final String BUG = "bug";

//    private static final String UNKNOWN_HOST = "unknownHost";

//    private static final String NOT_CONNECTED = "notConnected";

//    private static final String TITLE_COMMENTS = "titleComments";
//
//    private static final String CLICK_SCENE = "clickScene";
    private final AbsCompoFactory compoFactory;

    private StringMap<String> messages = new StringMap<String>();

    private final WindowAiki window;

    private AbsPanel panelMenu;

    private final AbsPlainLabel endGame;

    private AbsPlainLabel useKeyPad;

    private AbsButton team;

    private AbsButton items;

    private AbsButton tm;

    private AbsButton fish;

    private AbsButton seeBoxes;

    private AbsButton seeEggs;

    private AbsButton host;

    private AbsButton game;

    private AbsButton goBack;

//    private AbsPlainButton server;

    private final AbsButton attract;
    private AbsPanel movesLearnt;
    private final CustList<AbsCustComponent> movesLearntList = new CustList<AbsCustComponent>();
    private final CustList<CheckBox> movesLearntListLabel = new CustList<CheckBox>();

    private AbsPanel abilities;

    private final CustList<AbilityLabel> abilityLabels = new CustList<AbilityLabel>();

    private final AbsPanel panelOptions;
    private final AbsScrollPane scrollPaneDetail;

    private TeamPanel teamPan;
//    private StringMap<String> messagesTeamPanel = new StringMap<String>();

    private ItemsPanel itemsPan;

    private TmPanel tmPanel;

    private AbsButton buy;
    private boolean buying;

    private AbsButton selectPkBox;

    private AbsButton selectEggBox;

    private AbsButton takeItem;

    private AbsButton store;

    private AbsButton release;

    private AbsButton withdraw;

    private AbsButton withdrawEgg;

    private AbsButton switchPk;

//    private AbsPlainLabel selectedForSwitch;

    private final FacadeGame facade;

//    private AbsPanel beginGame;

    private final AbsPanel sceneInteract;

    private AbsPlainLabel placeName;

    private Scene scene;

//    private KeyPadListener keyPadListener;

    private AbsPanel interaction;
    private AbsPanel gymTrainer;

    private AbsButton buttonInteract;

    private Pad pad;

//    private final AbsPlainLabel time;

    private AbsCustCheckBox switchUsable;

    private AbsButton takeItemTeam;

    private AbsButton detailPk;

    private AbsButton healPk;

//    private AbsButton nicknamePk;
    private AbsTextField nicknameField;

//    private boolean enabledClick = true;

//    private RenderedPage receivedPk;

//    private AbsPanel panelNetWork;

//    private boolean enabledReady;

//    private AbsCustCheckBox readyCheck;

    private final MapPanel mapPanel;

    private AbsPlainLabel chosenCity;

    private final AbstractAtomicBoolean paintingScene;

    private final AbsPanel component;
    private final PkDetailContent pkDetailContent;
    private final ReportingFrame resultScene;
    private AbsButton exitOptions;
    private AbsButton evolveStone;
    private AbsButton validateMoveToPlace;
    private AbsButton receiveEgg;
    private AbsButton receiveParents;
    private AbsButton hostPk;
    private AbsButton addTmBuy;
    private AbsButton removeTmBuy;
    private AbsButton tmBuy;
    private AbsButton addItemBuy;
    private AbsButton removeItemBuy;
    private AbsButton buySell;
    private AbsButton selectItemBuy;
    private AbsButton cancelMoveTutor;
    private AbsButton okMoveTutor;

    public ScenePanel(WindowAiki _window, FacadeGame _facade) {
        compoFactory = _window.getCompoFactory();
        sceneInteract = compoFactory.newBorder();
        resultScene = ReportingFrame.newInstance(_window.getFrames());
        pkDetailContent = new PkDetailContent(_window.getFrames());
        attract = compoFactory.newPlainButton();
        attract.addActionListener(new PkNonModalEvent(_window.getModal()),new AttractEvent(this));
        endGame = compoFactory.newPlainLabel("");
        component = compoFactory.newLineBox();
        paintingScene = _window.getThreadFactory().newAtomicBoolean();
        facade = _facade;
        window = _window;
        component.add(sceneInteract);
        fish = window.getCompoFactory().newPlainButton();
        fish.addActionListener(new PkNonModalEvent(_window.getModal()),new FishingEvent(this));
        AbsPanel panelHoriz_ = compoFactory.newLineBox();
        initMenu();
        panelHoriz_.add(panelMenu);
        panelOptions = compoFactory.newBorder();
        scrollPaneDetail = compoFactory.newAbsScrollPane();
        panelHoriz_.add(panelOptions);
        component.add(panelHoriz_);
//        time = window.getCompoFactory().newPlainLabel("");
//        component.add(time);
        //gamePanel = new GamePanel(facade);
        mapPanel = new MapPanel();
        window.pack();
    }

    public void reinit() {
        panelOptions.removeAll();
        panelOptions.add(scrollPaneDetail, MessagesGuiFct.BORDER_LAYOUT_EAST);
//        if (keyPadListener != null) {
//            keyPadListener.setSceneKepPad(null);
//        }
//        if (sceneInteract != null) {
//            sceneInteract.removeAll();
//        }
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

    public void initMessages() {
        messages = MessagesPkGame.getScenePanelContentTr(MessagesPkGame.getAppliTr(window.getFrames().currentLg())).getMapping();
//        messagesTeamPanel = WindowAiki.getMessagesFromLocaleClass(Resources.MESSAGES_FOLDER, _lg, TeamPanel.TEAM_PANEL);
    }

    public void setMessages() {
        endGame.setText(facade.getEndGameMessage());
        attract.setText(messages.getVal(MessagesRenderScenePanel.OK));
        useKeyPad.setText(messages.getVal(MessagesRenderScenePanel.CLICK_SCENE));
        team.setText(messages.getVal(MessagesRenderScenePanel.CST_TEAM));
        items.setText(messages.getVal(MessagesRenderScenePanel.CST_ITEMS));
        tm.setText(messages.getVal(MessagesRenderScenePanel.CST_TM));
        //        difficulty.setText(messages.getVal(DIFFICULTY));
//        if (fish != null) {
            fish.setText(messages.getVal(MessagesRenderScenePanel.CST_FISH));
//        }
        seeBoxes.setText(messages.getVal(MessagesRenderScenePanel.SEE_POKEMON));
        seeEggs.setText(messages.getVal(MessagesRenderScenePanel.SEE_EGG));
        host.setText(messages.getVal(MessagesRenderScenePanel.SEE_HOSTED));
        game.setText(messages.getVal(MessagesRenderScenePanel.SEE_GAME));
        goBack.setText(messages.getVal(MessagesRenderScenePanel.GO_BACK_MENU));
//        server.setText(messages.getVal(CST_SERVER));
        LanguageDialogButtons.translate(buttonInteract,messages,MessagesRenderScenePanel.INTERACT);
        translateTeamPan(teamPan, messages);
    }

    public static void translateTeamPan(TeamPanel _teamPan, StringMap<String> _messages) {
        if (_teamPan != null) {
            _teamPan.translate(_messages);
        }
    }
//
//    public void addBeginGame(AbsPanel _panel) {
//        panelOptions.removeAll();
//        panelOptions.add(_panel, GuiConstants.BORDER_LAYOUT_CENTER);
//        beginGame = _panel;
//        beginGame.setVisible(true);
//    }

    public void drawGameWalking(boolean _setPreferredSize) {
        panelOptions.removeAll();
        panelOptions.add(scrollPaneDetail, MessagesGuiFct.BORDER_LAYOUT_EAST);
        endGame.setText(facade.getEndGameMessage());
        endGame.setVisible(facade.isShowEndGame());
        panelMenu.setVisible(true);
//        if (beginGame != null) {
//            beginGame.setVisible(false);
//        }
//        if (beginGame != null) {
//            panelOptions.remove(beginGame);
//        }
        boolean wasNull_ = false;
        if (scene == null) {
            wasNull_ = true;
            scene = new Scene(window.getCompoFactory(),window.getTileRender());
            scene.setDimensions(facade);
            scene.addMouseListener(new PkNonModalEvent(window.getModal()),scene);

//            keyPadListener = new KeyPadListener(window, facade);
            scene.getPaintableLabel().registerKeyboardAction(window.getCompoFactory().wrap(new PkNonModalEvent(window.getModal()),new KeyPadListener(window, facade,scene, Direction.UP)),GuiConstants.VK_UP,0);
            scene.getPaintableLabel().registerKeyboardAction(window.getCompoFactory().wrap(new PkNonModalEvent(window.getModal()),new KeyPadListener(window, facade,scene, Direction.DOWN)),GuiConstants.VK_DOWN,0);
            scene.getPaintableLabel().registerKeyboardAction(window.getCompoFactory().wrap(new PkNonModalEvent(window.getModal()),new KeyPadListener(window, facade,scene, Direction.LEFT)),GuiConstants.VK_LEFT,0);
            scene.getPaintableLabel().registerKeyboardAction(window.getCompoFactory().wrap(new PkNonModalEvent(window.getModal()),new KeyPadListener(window, facade,scene, Direction.RIGHT)),GuiConstants.VK_RIGHT,0);
//            scene.addKeyListener(keyPadListener);

            Task task_ = new Task(scene, facade, window);
            AbstractBaseExecutorService t_ = window.getThreadFactory().newExecutorService();
//            Timer t_ = new Timer(0, task_);
            pad.getUp().addMouseListener(new PkNonModalEvent(window.getModal()), new MouseTask(Direction.UP,task_, t_));
            pad.getDown().addMouseListener(new PkNonModalEvent(window.getModal()), new MouseTask(Direction.DOWN, task_, t_));
            pad.getLeft().addMouseListener(new PkNonModalEvent(window.getModal()), new MouseTask(Direction.LEFT, task_, t_));
            pad.getRight().addMouseListener(new PkNonModalEvent(window.getModal()), new MouseTask(Direction.RIGHT, task_, t_));
            pad.getMiddle().addMouseListener(new PkNonModalEvent(window.getModal()), new MouseTask(null, task_, t_));
            AbsMetaLabelPk.paintPk(window.getImageFactory(),pad.getUp());
            AbsMetaLabelPk.paintPk(window.getImageFactory(),pad.getDown());
            AbsMetaLabelPk.paintPk(window.getImageFactory(),pad.getLeft());
            AbsMetaLabelPk.paintPk(window.getImageFactory(),pad.getRight());
            AbsMetaLabelPk.paintPk(window.getImageFactory(),pad.getMiddle());
        }
//        keyPadListener.setSceneKepPad(scene);
        facade.directInteraction();
        facade.changeCamera();
        scene.load(window.getImageFactory(),facade, _setPreferredSize);
        scene.setDelta(0, false);
        if (wasNull_) {
            scene.setPreferredSize();
        }
//        wasNull_ = false;
//        if (sceneInteract == null) {
//            wasNull_ = true;
//            sceneInteract = compoFactory.newBorder();
//        } else {
//            sceneInteract.removeAll();
//        }
        sceneInteract.removeAll();
        if (placeName == null) {
            placeName = window.getCompoFactory().newPlainLabel("");
        }
        placeName.setText(facade.getCurrentPlace());
        sceneInteract.add(placeName, MessagesGuiFct.BORDER_LAYOUT_NORTH);
        sceneInteract.add(scene.getPaintableLabel(), MessagesGuiFct.BORDER_LAYOUT_CENTER);
        initInteraction();
//        if (fish != null) {
            enableIfPossibleFishing();
//        }
        sceneInteract.add(interaction, MessagesGuiFct.BORDER_LAYOUT_SOUTH);
        buttonInteract.setEnabled(facade.getInterfaceType() != InterfaceType.RIEN);
//        if (wasNull_) {
//            component.add(sceneInteract, IndexConstants.FIRST_INDEX);
////            sceneInteract.repaintSecondChildren(window.getImageFactory());
////        } else {
////            panelMenu.repaintSecondChildren(window.getImageFactory());
////            time.repaintLabel();
////            sceneInteract.repaintSecondChildren(window.getImageFactory());
//        }
        AbsMetaLabelPk.paintPk(window.getImageFactory(), scene);
        component.validate();
        scene.setFocus();
    }

    public String getComment() {
        return StringUtil.join(facade.getComment().getMessages(), RETURN_LINE);
    }

    private void initMenu() {
        panelMenu = compoFactory.newPageBox();
        endGame.setVisible(false);
        endGame.setOpaque(true);
        endGame.setBackground(GuiConstants.YELLOW);
        AbsPanel menus_ = compoFactory.newGrid(0,1);
        menus_.add(endGame);
        useKeyPad = window.getCompoFactory().newPlainLabel("");
        menus_.add(useKeyPad);
        team = window.getCompoFactory().newPlainButton();
        team.addActionListener(new PkNonModalEvent(window.getModal()),new ManageTeamEvent(this));
        menus_.add(team);
        items = window.getCompoFactory().newPlainButton();
        items.addActionListener(new PkNonModalEvent(window.getModal()),new SelectItemForPokemonEvent(this));
        menus_.add(items);
        tm = window.getCompoFactory().newPlainButton();
        tm.addActionListener(new PkNonModalEvent(window.getModal()),new SelectTmToLearnEvent(this));
        menus_.add(tm);
        menus_.add(window.getCompoFactory().newAbsPaintableLabel());
        seeBoxes = window.getCompoFactory().newPlainButton();
        seeBoxes.addActionListener(new PkNonModalEvent(window.getModal()),new ConsultPokemonEvent(window, facade));
        menus_.add(seeBoxes);
        seeEggs = window.getCompoFactory().newPlainButton();
        seeEggs.addActionListener(new PkNonModalEvent(window.getModal()),new ConsultEggEvent(window, facade));
        menus_.add(seeEggs);
        host = window.getCompoFactory().newPlainButton();
        host.addActionListener(new PkNonModalEvent(window.getModal()),new ConsultHostEvent(window, facade));
        menus_.add(host);
        game = window.getCompoFactory().newPlainButton();
        game.addActionListener(new PkNonModalEvent(window.getModal()),new ShowGameProgressingEvent(window));
        menus_.add(game);
        menus_.add(window.getCompoFactory().newAbsPaintableLabel());
        goBack = window.getCompoFactory().newPlainButton();
        goBack.addActionListener(new PkNonModalEvent(window.getModal()),new SetPlacesEvent(this));
        menus_.add(goBack);
        menus_.add(window.getCompoFactory().newAbsPaintableLabel());
//        server = window.getCompoFactory().newPlainButton();
//        server.addActionListener(new ManageNetworkEvent(this));
//        menus_.add(server);
        panelMenu.add(menus_);
        pad = new Pad(window.getCompoFactory());
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
        SelectItem.setSelectItem(window, facade, SelectItem.USE);
    }

    public void afterSelectItemPk() {
        boolean isSelectedIndex_ = SelectItem.isSelectedIndex(window.getSelectItem());
//        boolean ok_ = SelectItem.isOk(window.getSelectItem());
//        if (!ok_) {
//            facade.clearSortingItem();
//            return;
//        }
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
                String message_ = StringUtil.simpleStringsFormat(messages.getVal(MessagesRenderScenePanel.ERROR_USING_ITEM), it_);
                setTextArea(message_);
                return;
            }
            facade.getPlayer().getIndexesOfPokemonTeam().clear();
            String usedItem_ = facade.getPlayer().getSelectedObject();
            LgInt nb_ = new LgInt(facade.getPlayer().getInventory().getNumber(usedItem_));
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
        initTeamItems(new PokemonSelectionItems(this));
    }

    public void selectTmToLearn() {
        SelectTm.setSelectTm(window, facade, false);
    }

    public void afterSelectLearn() {
        boolean isSelectedIndex_ = SelectTm.isSelectedIndex(window.getSelectTm());
//        boolean ok_ = SelectTm.isOk(window.getSelectTm());
//        if (!ok_) {
//            facade.clearSortingMove();
//            return;
//        }
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
            String message_ = StringUtil.simpleStringsFormat(messages.getVal(MessagesRenderScenePanel.NO_POSSIBLE_LEARN), move_);
            setTextArea(message_);
            return;
        }
        initTeamItems(new PokemonSelectionTm(this));
    }

    private void initTeamItems(ListSelection _l) {
        facade.openMenu();
        panelMenu.setVisible(false);
        disableFishing();
        panelOptions.removeAll();
        panelOptions.add(scrollPaneDetail, MessagesGuiFct.BORDER_LAYOUT_EAST);
        IntTreeMap<UsablePokemon> pks_ = new IntTreeMap<UsablePokemon>();
        for (int i: facade.getPlayer().getIndexesOfPokemonTeam()) {
            pks_.put(i, facade.getPlayer().getTeam().get(i));
        }

        AbsPanel set_ = compoFactory.newLineBox();
        teamPan = initTeam(pks_, MessagesRenderScenePanel.POKEMON_SELECT, true);
        teamPan.getListe().setListener(_l);
        set_.add(teamPan.getContainer());
        movesLearnt = compoFactory.newGrid(0,1);
        AbsScrollPane scroll_ = compoFactory.newAbsScrollPane(movesLearnt);
        scroll_.setPreferredSize(new MetaDimension(100, 220));
        set_.add(scroll_);
        abilities = compoFactory.newGrid(0,1);
        set_.add(abilities);
        panelOptions.add(set_, MessagesGuiFct.BORDER_LAYOUT_CENTER);
        addExit();
        window.pack();
    }

//    public void manageNetwork() {
//        window.getLanguageKey();
////        String lg_ = window.getLanguageKey();
////        DialogServerAiki.setDialogServer(window);
////        String ip_ = DialogServerAiki.getIpOrHostName(window.getDialogServer());
////        if (ip_ == null || ip_.isEmpty()) {
////            if (DialogServerAiki.getIpType(window.getDialogServer()) == IpType.IP_V6) {
////                ip_ = LOCALHOST_NEW_IP;
////            } else {
////                ip_ = LOCALHOST_OLD_IP;
////            }
////        }
////        if (!DialogServerAiki.isChoosen(window.getDialogServer())) {
////            return;
////        }
////        String fileName_ = StringUtil.concat(StreamFolderFile.getCurrentPath(window.getFileCoreStream()), Resources.PORT_INI);
////        int port_ = NetCreate.tryToGetPort(fileName_, NetAiki.getPort(), window.getFileCoreStream(), window.getStreams());
////        if (DialogServerAiki.isCreate(window.getDialogServer())) {
////            window.createServer(ip_, DialogServerAiki.getIpType(window.getDialogServer()), port_);
////            return;
////        }
////        SocketResults connected_ = window.createClient(ip_, DialogServerAiki.getIpType(window.getDialogServer()), false, port_);
////        if (connected_.getError() != ErrorHostConnectionType.NOTHING) {
////            if (connected_.getError() == ErrorHostConnectionType.UNKNOWN_HOST) {
////                String formatted_ = messages.getVal(UNKNOWN_HOST);
////                formatted_ = StringUtil.simpleStringsFormat(formatted_, ip_);
////                window.getFrames().getMessageDialogAbs().input(window.getCommonFrame(), messages.getVal(BUG), formatted_,lg_,GuiConstants.ERROR_MESSAGE);
////                return;
////            }
////            window.getFrames().getMessageDialogAbs().input(window.getCommonFrame(), messages.getVal(BUG), messages.getVal(NOT_CONNECTED), lg_,GuiConstants.ERROR_MESSAGE);
////            return;
////        }
////        window.setIndexInGame(IndexConstants.SECOND_INDEX);
//    }

    public void setPlaces() {
        //facade.initPlaces();
        panelMenu.setVisible(false);
        disableFishing();
//        JPanel panelPlaces_ = new JPanel();
//        panelPlaces_.setLayout(new GridLayout(0, 1));
//        panelPlaces_.add(new JLabel(messages.getVal(GO_BACK)));
//        for (int i = CustList.FIRST_INDEX; i < facade.getPlaces().size(); i++) {
//            LabelButton place_ = window.getCompoFactory().newPlainButton(facade.getPlaces().get(i));
//            place_.setEnabled(facade.isEnablePlace(i));
//            place_.addMouseListener(new SelectPlaceGoBackEvent(this, i));
//            panelPlaces_.add(place_);
//        }
//        panelOptions.add(panelPlaces_, BorderLayout.CENTER);
        mapPanel.init(window,facade, this);
        AbsPanel box_ =compoFactory.newPageBox();
        box_.add(window.getCompoFactory().newPlainLabel(messages.getVal(MessagesRenderScenePanel.GO_BACK)));
        chosenCity = window.getCompoFactory().newPlainLabel("");
        chosenCity.setBackground(box_.getBackgroundValue());
        chosenCity.setForeground(box_.getForegroundValue());
        box_.add(chosenCity);
        AbsPanel line_ = compoFactory.newLineBox();
        //avoid vertical spaces between tiles in map
        line_.add(mapPanel.getContainer());
        line_.add(window.getCompoFactory().newPlainLabel(DataBase.EMPTY_STRING));
        box_.add(line_);
        validateMoveToPlace = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.OK));
        validateMoveToPlace.addActionListener(new PkNonModalEvent(window.getModal()),new ChoosePlaceEvent(this));
        box_.add(validateMoveToPlace);
        panelOptions.add(box_, MessagesGuiFct.BORDER_LAYOUT_CENTER);
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
        AbsMetaLabelPk.paintPk(window.getImageFactory(), scene);
        exitInteractionPack();
    }

    public void choosePlace(int _x, int _y) {
        facade.setMiniMapCoords(_x, _y);
        chosenCity.setText(facade.getChosenCity());
        window.pack();
    }

    //called while connection to a server succeeds.
    /*public void setNetworkPanel() {
        MenuItemUtils.setEnabledMenu(window.getFolderLoad(),false);
        MenuItemUtils.setEnabledMenu(window.getZipLoad(),false);
        MenuItemUtils.setEnabledMenu(window.getGameLoad(),false);
        MenuItemUtils.setEnabledMenu(window.getNewGame(),false);
        facade.openMenu();
        panelMenu.setVisible(false);
        disableFishing();
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
    }*/

    /*public void setTradable(IntTreeMap< PokemonPlayer> _team) {
        IntTreeMap<UsablePokemon> teamPks_ = new IntTreeMap<UsablePokemon>();
        for (EntryCust<Integer, PokemonPlayer> e: _team.entryList()) {
            teamPks_.put(e.getKey(), e.getValue());
        }
        teamPan = initTeam(teamPks_, POKEMON_SELECT, true);
        teamPan.addListenerTrading(this);
        panelNetWork.add(teamPan.getContainer());
        AbsPanel group_ = compoFactory.newBorder();
        group_.add(window.getCompoFactory().newPlainLabel(messages.getVal(RECEIVED_POKEMON)), GuiConstants.BORDER_LAYOUT_NORTH);
        AbsScrollPane scrollSession_ = compoFactory.newAbsScrollPane();
        receivedPk = new RenderedPage(scrollSession_, window.getFrames());
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
    }*/

    /*public void setTradableAfterTrading(IntTreeMap< PokemonPlayer> _team) {
        enabledClick = false;
        IntTreeMap<UsablePokemon> teamPks_ = new IntTreeMap<UsablePokemon>();
        for (EntryCust<Integer, PokemonPlayer> e: _team.entryList()) {
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
        receivedPk.initializeOnlyConf(task_, facade.getLanguage(), ((PokemonStandards)task_.getBeanNatLgNames()));
    }*/

    public void interact() {
        endGame.setText(facade.getEndGameMessage());
        endGame.setVisible(facade.isShowEndGame());
        placeName.setText(facade.getCurrentPlace());
        initInteraction();
        buttonInteract.setEnabled(facade.getInterfaceType() != InterfaceType.RIEN);
        setTextArea(StringUtil.join(facade.getGame().getCommentGame().getMessages(), RETURN_LINE));
        getPaintingScene().set(false);
        setPaintingScene(false);
    }

    private void initInteraction() {
        if (interaction != null) {
            return;
        }
        interaction = compoFactory.newLineBox();
        buttonInteract = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.INTERACT));
        buttonInteract.addActionListener(new PkNonModalEvent(window.getModal()),new InteractSceneEvent(this));
        interaction.add(buttonInteract);
        fish = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.CST_FISH));
        fish.addActionListener(new PkNonModalEvent(window.getModal()),new FishingEvent(this));
        interaction.add(fish);
        interaction.add(attract);
        gymTrainer = compoFactory.newLineBox();
        interaction.add(gymTrainer);
    }

    public void initGymTrainerFight(LevelIndoorGym _l, Coords _c) {
        facade.getGame().initGymTrainerFight(facade.getData(),_c,_l);
        facade.getComment().addComment(facade.getGame().getCommentGame());
        facade.setupMovingHeros();
        showOptions();
        window.pack();
    }
    public void interactScene() {
//        if (!buttonInteract.isEnabled()) {
//            return;
//        }
        if (!facade.isEnabledMovingHero()) {
            return;
        }
        facade.interactNoFish();
        if (!facade.isChangeToFightScene()) {
            setTextArea(StringUtil.join(facade.getGame().getCommentGame().getMessages(), RETURN_LINE));
        }
        showOptions();
        window.pack();
    }

    public void attract() {
        facade.attract();
        after();
    }
    public void fishing() {
//        if (facade.isFishArea()) {
//            facade.initFishing();
//            after();
//        }
        facade.initFishing();
        after();
    }

    private void after() {
        if (facade.isChangeToFightScene()) {
            window.setSavedGame(false);
            window.setFight(true);
        }
    }

    private void showOptions() {
        panelOptions.removeAll();
        panelOptions.add(scrollPaneDetail, MessagesGuiFct.BORDER_LAYOUT_EAST);
        if (facade.getInterfaceType() == InterfaceType.ECH_BOITE) {
//            selectedForSwitch = window.getCompoFactory().newPlainLabel("");
            AbsPanel storage_ = compoFactory.newGrid(0, 1);
            selectPkBox = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.SELECT_PK_BOX));
            selectPkBox.addActionListener(new PkNonModalEvent(window.getModal()),new SelectPokemonBoxEvent(this));
            storage_.add(selectPkBox);
            selectEggBox = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.SELECT_EGG_BOX));
            selectEggBox.addActionListener(new PkNonModalEvent(window.getModal()),new SelectEggBoxEvent(this));
            storage_.add(selectEggBox);
            storage_.add(window.getCompoFactory().newAbsPaintableLabel());
            takeItem = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.TAKE_ITEM));
            takeItem.setEnabled(false);
            takeItem.addActionListener(new PkNonModalEvent(window.getModal()),new GearStorageEvent(this, StorageActions.TAKE_ITEM_BOX));
            storage_.add(takeItem);
            store = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.CST_STORE));
            store.setEnabled(false);
            store.addActionListener(new PkNonModalEvent(window.getModal()),new GearStorageEvent(this, StorageActions.STORE));
            storage_.add(store);
            withdraw = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.WITHDRAW_PK));
            withdraw.setEnabled(false);
            withdraw.addActionListener(new PkNonModalEvent(window.getModal()),new GearStorageEvent(this, StorageActions.WIDRAW_PK));
            storage_.add(withdraw);
            withdrawEgg = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.WITHDRAW_EGG));
            withdrawEgg.setEnabled(false);
            withdrawEgg.addActionListener(new PkNonModalEvent(window.getModal()),new GearStorageEvent(this, StorageActions.WIDRAW_EGG));
            storage_.add(withdrawEgg);
            switchPk = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.SWITCH_PK_EGG));
            switchPk.setEnabled(false);
            switchPk.addActionListener(new PkNonModalEvent(window.getModal()),new GearStorageEvent(this, StorageActions.SWITCH_TEAM_BOX));
            storage_.add(switchPk);
            storage_.add(window.getCompoFactory().newAbsPaintableLabel());
            release = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.CST_RELEASE));
            release.setEnabled(false);
            release.addActionListener(new PkNonModalEvent(window.getModal()),new GearStorageEvent(this, StorageActions.RELEASE));
            storage_.add(release);
//            storage_.add(selectedForSwitch);
            storage_.add(window.getCompoFactory().newPlainLabel(""));
            AbsPanel set_ = compoFactory.newLineBox();
            initTeam();
            teamPan.addListenerStorage(this);
            set_.add(teamPan.getContainer());
            set_.add(storage_);
            panelOptions.add(set_, MessagesGuiFct.BORDER_LAYOUT_CENTER);
            panelMenu.setVisible(false);
            disableFishing();
        } else if (facade.getInterfaceType() == InterfaceType.MOVE_TUTORS) {
            initPkTeamMoveTutors();
            AbsPanel set_ = compoFactory.newLineBox();
            set_.add(teamPan.getContainer());
            movesLearnt = compoFactory.newGrid(0,1);
            AbsScrollPane scroll_ = compoFactory.newAbsScrollPane(movesLearnt);
            scroll_.setPreferredSize(new MetaDimension(100, 220));
            set_.add(scroll_);
            panelOptions.add(set_, MessagesGuiFct.BORDER_LAYOUT_CENTER);
            panelMenu.setVisible(false);
            disableFishing();
        } else if (facade.getInterfaceType() == InterfaceType.ACHATS_CT) {
            tmPanel = new TmPanel(window,5, messages.getVal(MessagesRenderScenePanel.TM_TITLE), facade);
            AbsPanel set_ = compoFactory.newPageBox();
            addTmBuy = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.TM_SELECT));
            addTmBuy.addActionListener(new PkNonModalEvent(window.getModal()),new AddTmEvent(this));
            set_.add(addTmBuy);
            removeTmBuy = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.TM_REMOVE));
            removeTmBuy.addActionListener(new PkNonModalEvent(window.getModal()),new RemoveTmEvent(this));
            set_.add(removeTmBuy);
            set_.add(tmPanel.getContainer());
            tmBuy = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.TM_BUY));
            tmBuy.addActionListener(new PkNonModalEvent(window.getModal()),new BuyTmEvent(this));
            set_.add(tmBuy);
            panelOptions.add(set_, MessagesGuiFct.BORDER_LAYOUT_CENTER);
            panelMenu.setVisible(false);
            disableFishing();
        } else if (facade.getInterfaceType() == InterfaceType.ACHATS) {
            AbsPanel set_ = compoFactory.newPageBox();
            buying = true;
            buy = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.ITEM_BUY));
            buy.setLineBorder(GuiConstants.RED);
            buy.addActionListener(new PkNonModalEvent(window.getModal()),new BuyOrSellEvent(this));
            set_.add(buy);
            itemsPan = new ItemsPanel(window, 2, messages.getVal(MessagesRenderScenePanel.ITEM_TITLE), facade);
            selectItemBuy = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.ITEM_SELECT));
            selectItemBuy.addActionListener(new PkNonModalEvent(window.getModal()),new SelectItemForListEvent(this));
            set_.add(selectItemBuy);
            addItemBuy = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.ITEM_ADD));
            addItemBuy.addActionListener(new PkNonModalEvent(window.getModal()),new ChangeItemListEvent(this, true));
            set_.add(addItemBuy);
            removeItemBuy = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.ITEM_REMOVE));
            removeItemBuy.addActionListener(new PkNonModalEvent(window.getModal()),new ChangeItemListEvent(this, false));
            set_.add(removeItemBuy);
            set_.add(itemsPan.getContainer());
            buySell = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.ITEM_BUY_SELL));
            buySell.addActionListener(new PkNonModalEvent(window.getModal()),new BuyItemsEvent(this));
            set_.add(buySell);
            panelOptions.add(set_, MessagesGuiFct.BORDER_LAYOUT_CENTER);
            panelMenu.setVisible(false);
            disableFishing();
        } else if (facade.getInterfaceType() == InterfaceType.PENSION) {
            AbsPanel set_ = compoFactory.newLineBox();
            IntTreeMap<UsablePokemon> teamPks_ = new IntTreeMap<UsablePokemon>();
            IntTreeMap< PokemonPlayer> team_ = facade.getGame().getPlayer().getPokemonPlayerList();
            for (EntryCust<Integer, PokemonPlayer> e: team_.entryList()) {
                teamPks_.put(e.getKey(), e.getValue());
            }
            teamPan = initTeam(teamPks_, MessagesRenderScenePanel.POKEMON_SELECT_TWO, false);
            teamPan.addListenerHost(this);
            set_.add(teamPan.getContainer());
            AbsPanel form_ = compoFactory.newPageBox();
            long nbRemSteps_ = facade.getRemaingingSteps();
            String buttonText_= StringUtil.simpleNumberFormat(messages.getVal(MessagesRenderScenePanel.GET_EGG), nbRemSteps_);
            receiveEgg = window.getCompoFactory().newPlainButton(buttonText_);
            receiveEgg.addActionListener(new PkNonModalEvent(window.getModal()),new ReceiveFromHostEvent(this, true));
            form_.add(receiveEgg);
            buttonText_= StringUtil.simpleNumberFormat(messages.getVal(MessagesRenderScenePanel.GET_EGG_PARENT), nbRemSteps_);
            receiveParents = window.getCompoFactory().newPlainButton(buttonText_);
            receiveParents.addActionListener(new PkNonModalEvent(window.getModal()),new ReceiveFromHostEvent(this, false));
            form_.add(receiveParents);
            hostPk = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.HOST_PK));
            hostPk.addActionListener(new PkNonModalEvent(window.getModal()),new HostPokemonEvent(this));
            form_.add(hostPk);
            set_.add(form_);
            panelOptions.add(set_, MessagesGuiFct.BORDER_LAYOUT_CENTER);
            panelMenu.setVisible(false);
            disableFishing();
        } else if (facade.getInterfaceType() == InterfaceType.FOSSILE) {
            window.setSavedGame(false);
        } else if (facade.isChangeToFightScene()) {
            window.setSavedGame(false);
            window.setFight(true);
            return;
        }
        addExit();
    }

    private TeamPanel initTeam(IntTreeMap<UsablePokemon> _team, String _key, boolean _single) {
        return new TeamPanel(window, messages.getVal(_key), facade, _team, messages, _single, new PkNonModalEvent(window.getModal()));
    }

    private void disableFishing() {
//        if (fish == null) {
//            return;
//        }
        fish.setEnabled(false);
        attract.setEnabled(false);
        possibleRemoveAll();
    }

    private void possibleRemoveAll() {
        if (gymTrainer != null) {
            gymTrainer.removeAll();
        }
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
        int sum_ = NumberUtil.signum(facade.getFirstSelectPkToHost()) + NumberUtil.signum(facade.getSecondSelectPkToHost());
        if (sum_ == -2) {
            window.setSavedGame(false);
            exited_ = true;
            setTextArea(info_);
            exitInteractionPack();
        }
//        if (facade.getFirstSelectPkToHost() == IndexConstants.INDEX_NOT_FOUND_ELT) {
//            if (facade.getSecondSelectPkToHost() == IndexConstants.INDEX_NOT_FOUND_ELT) {
//                window.setSavedGame(false);
//                exited_ = true;
//                setTextArea(info_);
//                exitInteractionPack();
//            }
//        }
        if (!exited_) {
            setTextArea(info_);
        }
    }

    public void receiveFromHost(boolean _onlyEgg) {
        facade.receiveFromHost(_onlyEgg);
        window.setSavedGame(false);
        String info_ = StringUtil.join(facade.getGame().getCommentGame().getMessages(), RETURN_LINE);
        if (!facade.getGame().isReinitInteraction()) {
            setTextArea(info_);
            exitInteractionPack();
        } else {
            setTextArea(info_);
        }
    }

    public void addTmToBuy() {
        SelectTm.setSelectTm(window, facade, true);
    }

    public void afterSelectBuy() {
        boolean isSelectedIndex_ = SelectTm.isSelectedIndex(window.getSelectTm());
//        boolean ok_ = SelectTm.isOk(window.getSelectTm());
//        if (!ok_) {
//            return;
//        }
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
            setTextArea(messages.getVal(MessagesRenderScenePanel.NO_POSSIBLE_BUY));
            return;
        }
        window.setSavedGame(false);
        exitInteractionPack();
        window.pack();
    }

    public void clearItemList() {
        buying = !buying;
        if (buying) {
            buy.setLineBorder(GuiConstants.RED);
        } else {
            buy.setLineBorder(GuiConstants.BLACK);
        }
        facade.clearItemsToBuyOrSell();
        itemsPan.initItems();
        window.pack();
    }

    public void selectItemForList() {
        if (buying) {
            SelectItem.setSelectItem(window, facade, SelectItem.BUY);
        } else {
            SelectItem.setSelectItem(window, facade, SelectItem.SELL);
        }
    }

    public void afterSelectItemBuy() {
        boolean isSelectedIndex_ = SelectItem.isSelectedIndex(window.getSelectItem());
//        boolean ok_ = SelectItem.isOk(window.getSelectItem());
//        if (!ok_) {
//            return;
//        }
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
        facade.buyOrSellItems(buying);
        if (buying && !facade.canBeBought()) {
            setTextArea(messages.getVal(MessagesRenderScenePanel.NO_POSSIBLE_BUY));
            return;
        }
        window.setSavedGame(false);
        exitInteractionPack();
        window.pack();
    }

    public void selectPokemonBox() {
        SelectPokemon.setSelectPokemon(window, facade, true, window.getSelectPokemon(), false);
    }

    public void afterSelectPk() {
//        if (!SelectPokemon.isStaticOk(window.getSelectPokemon())) {
//            facade.setLinePokemonFirstBox(_lineBack);
//        } else {
//            setEnablingStoring();
////            StringList messages_;
////            messages_ = getMessagesStorage();
////            setTextArea(messages_.join(RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
//        }
        setEnablingStoring();
        facade.clearSortingFirstBox();
    }

    public void selectEggBox() {
        SelectEgg.setSelectEgg(window, facade, window.getSelectEgg(), false);
    }

    public void afterSelectEgg() {
//        SelectEgg.isSelectedIndex(window.getSelectEgg());
//        if (!SelectEgg.isOk(window.getSelectEgg())) {
//            facade.setLineEggs(_lineBack);
//        } else {
//            setEnablingStoring();
////            StringList messages_;
////            messages_ = getMessagesStorage();
////            setTextArea(messages_.join(RETURN_LINE), JOptionPane.INFORMATION_MESSAGE);
//        }
        setEnablingStoring();
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
        selectPkBox.setEnabled(true);
        selectEggBox.setEnabled(true);
        takeItem.setEnabled(facade.canTakeItemFromStorage());
        store.setEnabled(facade.isSelectedPkTeamStorage());
        //cancelSelect.setEnabledLabel(true);
        withdraw.setEnabled(facade.getSelectedPokemonFirstBox() != null);
        withdrawEgg.setEnabled(facade.getSelectedEggBox() != null);
        //        boolean selectedBox_ = false;
//        if (facade.getSelectedPokemonFirstBox() != null) {
//            selectedBox_ = true;
//        }
//        if (facade.getSelectedEggBox() != null) {
//            selectedBox_ = true;
//        }
//        switchPk.setEnabledLabel(selectedBox_ && facade.isSelectedPkTeamStorage());
        switchPk.setEnabled(facade.isSwitchable());
        release.setEnabled(facade.isReleasable());
    }

//    private void cancelChosenTeamPokemon() {
//        facade.cancelChosenTeamPokemon();
//        enabledClick = false;
//        teamPan.deselect();
//        enabledClick = true;
//    }

    private void initPkTeamMoveTutors() {
        IntTreeMap<PokemonPlayer> team_ = facade.getPlayer().getPokemonPlayerList();
        IntTreeMap<UsablePokemon> pks_ = new IntTreeMap<UsablePokemon>();
        for (EntryCust<Integer, PokemonPlayer> e: team_.entryList()) {
            pks_.put(e.getKey(), e.getValue());
        }
        teamPan = initTeam(pks_, MessagesRenderScenePanel.POKEMON_SELECT, true);
        teamPan.addListenerMoveTutor(this);
    }

    private void refreshPkTeamMoveTutors() {
//        enabledClick = false;
        IntTreeMap<PokemonPlayer> team_ = facade.getPlayer().getPokemonPlayerList();
        IntTreeMap<UsablePokemon> pks_ = new IntTreeMap<UsablePokemon>();
        for (EntryCust<Integer, PokemonPlayer> e: team_.entryList()) {
            pks_.put(e.getKey(), e.getValue());
        }
        teamPan.initFighters(pks_,messages);
//        enabledClick = true;
    }

    private void initTeam() {
        IntTreeMap<UsablePokemon> pks_ = new IntTreeMap<UsablePokemon>();
        int i_ = IndexConstants.FIRST_INDEX;
        for (UsablePokemon p: facade.getPlayer().getTeam()) {
            pks_.put(i_, p);
            i_++;
        }
        teamPan = initTeam(pks_, MessagesRenderScenePanel.POKEMON_SELECT, true);
    }

    private void refreshTeam() {
//        enabledClick = false;
        IntTreeMap<UsablePokemon> pks_ = new IntTreeMap<UsablePokemon>();
        int i_ = IndexConstants.FIRST_INDEX;
        for (UsablePokemon p: facade.getPlayer().getTeam()) {
            pks_.put(i_, p);
            i_++;
        }
        teamPan.initFighters(pks_,messages);
//        enabledClick = true;
    }

    private void addButtonsTeam() {
        AbsPanel set_ = compoFactory.newLineBox();
        AbsPanel teamMenu_ = compoFactory.newPageBox();
        switchUsable = window.getCompoFactory().newCustCheckBox(messages.getVal(MessagesRenderScenePanel.SWITCH_PK_TEAM));
        switchUsable.setEnabled(false);
//        enabledSwitchTeam = false;
//        switchUsable.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent _arg0) {
//                enabledSwitchTeam = !enabledSwitchTeam;
//            }
//        });
        teamMenu_.add(switchUsable);
        takeItemTeam = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.TAKE_ITEM_TEAM));
        takeItemTeam.setEnabled(false);
        takeItemTeam.addActionListener(new PkNonModalEvent(window.getModal()),new TakeItemFromTeamEvent(this));
        teamMenu_.add(takeItemTeam);
        detailPk = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.DETAIL_TEAM));
        detailPk.setEnabled(false);
        detailPk.addActionListener(new PkNonModalEvent(window.getModal()),new SeePokemonDetailEvent(this));
        teamMenu_.add(detailPk);
        healPk = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.HEAL_PK));
        healPk.setEnabled(false);
        healPk.addActionListener(new PkNonModalEvent(window.getModal()),new HealPokemonEvent(this));
        teamMenu_.add(healPk);
        nicknameField = window.getCompoFactory().newTextField();
        nicknameField.setEnabled(false);
        nicknameField.addAutoComplete(new WalkNicknameAutoCompleteListener(nicknameField,facade));
        teamMenu_.add(nicknameField);
//        nicknamePk = window.getCompoFactory().newPlainButton(messages.getVal(NICKNAME));
//        nicknamePk.setEnabled(false);
//        nicknamePk.addActionListener(new ChangeNicknameEvent(this));
//        teamMenu_.add(nicknamePk);
        set_.add(teamPan.getContainer());
        set_.add(teamMenu_);
        panelOptions.add(set_, MessagesGuiFct.BORDER_LAYOUT_CENTER);
    }

    public void takeItemFromTeam() {
        facade.takeObjectFromTeam();
        window.setSavedGame(false);
        setTextArea(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE));
        refreshTeam();
        //disable the button after taking
        takeItemTeam.setEnabled(false);
        window.pack();
    }

    public void seePokemonDetail() {
        window.getModal().set(true);
        pkDetailContent.group(window,facade, window.getCommonFrame(), window.getModal());
        scrollPaneDetail.setViewportView(pkDetailContent.getContent());
        scrollPaneDetail.recalculateViewport();
        scrollPaneDetail.setSize(scrollPaneDetail.getPreferredSizeValue());
        window.pack();
//        if (window.showErrorMessageDialog(ForwardingJavaCompiler.getMess(Constants.getLanguage()))) {
//            return;
//        }
//        UsablePokemon p_ = facade.getSelectedPkTeam();
//        if (!(p_ instanceof PokemonPlayer)) {
//            return;
//        }
//        AbstractThread thread_ = window.getPreparedPkThread();
//        AikiNatLgNamesNavigation task_ = window.getPreparedPkTask();
//        if (thread_ == null || thread_.isAlive() || task_ == null) {
//            return;
//        }
//        showHtmlDialog(window, facade,task_,facade.getLanguage());
    }

    public void healPokemon() {
//        int lineBack_ = facade.getLineHealingItem();
        SelectHealingItem.setSelectHealingItem(window, facade, false);
//        afterSelect(lineBack_);
    }

//    public void afterSelect(int _lineBack) {
//        boolean isSelectedIndex_ = SelectHealingItem.isSelectedIndex(window.getSelectHealingItem());
//        boolean ok_ = SelectHealingItem.isOk(window.getSelectHealingItem());
//        if (!ok_) {
//            facade.setLineHealingItem(_lineBack);
//            facade.clearSortingHealingItem();
//        } else if (isSelectedIndex_) {
//            prepareMoveSelect();
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
    public void preparePk() {
        facade.selectPokemon(teamPan.getSelectedIndexSingle());
    }

    public void prepareItem() {
        facade.setGivingObject(false);
        facade.setChosenHealingItemWalk();
        facade.clearSortingHealingItem();
        facade.getPlayer().getIndexesOfPokemonTeam().clear();
        facade.useObject();
    }

    public void selectPokemonMoveTutor() {
//        if (!enabledClick) {
//            return;
//        }
        int selectedIndex_ = teamPan.getSelectedIndex();
        if (selectedIndex_ < 0) {
            movesLearntList.clear();
            movesLearntListLabel.clear();
            movesLearnt.removeAll();
            window.pack();
            return;
        }
        facade.choosePokemonForMoveTutors(selectedIndex_);
        movesLearntList.clear();
        movesLearntListLabel.clear();
        movesLearnt.removeAll();
        movesLearnt.add(window.getCompoFactory().newPlainLabel(messages.getVal(MessagesRenderScenePanel.SELECT_MT)));
        StringList selectedMoves_ = facade.getSelectedMoves();
        for (String m: selectedMoves_) {
            String tr_ = facade.translateMove(m);
            MoveTutorCheckBox check_ = new MoveTutorCheckBox(m,tr_, true,this);
//            check_.setBackground(GuiConstants.RED);
            check_.setSelected(true);
            movesLearnt.add(check_.getComponent());
            movesLearntList.add(check_.getComponent());
            movesLearntListLabel.add(check_);
        }
        StringList unselectedMoves_ = facade.getUnselectedMoves();
        StringMap<Long> chosenMoves_ = facade.getPlayer().getChosenMoves();
        for (String m: unselectedMoves_) {
            String tr_ = facade.translateMove(m);
            MoveTutorCheckBox check_ = new MoveTutorCheckBox(m, StringUtil.concat(tr_,SPACE,Long.toString(chosenMoves_.getVal(m))),false,this);
//            check_.setBackground(GuiConstants.WHITE);
            check_.setSelected(false);
            movesLearnt.add(check_.getComponent());
            movesLearntList.add(check_.getComponent());
            movesLearntListLabel.add(check_);
        }
        cancelMoveTutor = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.CANCEL_MT));
        cancelMoveTutor.addActionListener(new PkNonModalEvent(window.getModal()),new CancelMtEvent(this));
        movesLearnt.add(cancelMoveTutor);
        okMoveTutor = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.VALIDATE_MT));
        okMoveTutor.addActionListener(new PkNonModalEvent(window.getModal()),new ValidateMtEvent(this));
        movesLearnt.add(okMoveTutor);
        window.pack();
    }

    public void cancelMt() {
        facade.cancelLearningMoveOnPokemon();
//        enabledClick = false;
        teamPan.deselect();
//        enabledClick = true;
    }

    public void validateMt() {
        facade.learnMovesByMoveTutor();
        String comment_ = StringUtil.join(facade.getComment().getMessages(), RETURN_LINE);
        if (facade.getPlayer().getChosenTeamPokemon() != IndexConstants.INDEX_NOT_FOUND_ELT) {
            setTextArea(comment_);
            return;
        }
        window.setSavedGame(false);
        setTextArea(comment_);
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
//        if (!enabledClick) {
//            return;
//        }
        facade.setChosenTeamPokemon(teamPan.getSelectedIndex());
        setEnablingStoring();
    }

    public void selectPkTeam() {
//        if (!enabledClick) {
//            return;
//        }
        switchUsable.setEnabled(true);
        if (switchUsable.isSelected()) {
            window.setSavedGame(false);
            facade.switchTeamOrder(teamPan.getSelectedIndex());
            switchUsable.setSelected(false);
//            enabledSwitchTeam = false;
            refreshTeam();
            window.pack();
            return;
        }
        facade.setChosenTeamPokemon(teamPan.getSelectedIndex());
        LanguageDialogButtons.enable(detailPk,facade.isSelectedTeamPokemon());
        LanguageDialogButtons.enable(takeItemTeam,facade.isSelectedTeamPokemonItem());
        LanguageDialogButtons.enable(healPk,facade.isSelectedTeamPokemon());
        LanguageDialogButtons.enable(nicknameField,facade.isSelectedTeamPokemon());
        LanguageDialogButtons.translate(nicknameField,facade.nickname());
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
//        if (nicknameField != null) {
//            nicknameField.setEnabled(facade.isSelectedTeamPokemon());
//            nicknameField.setText(facade.nickname());
//        }
    }

    private void addExit() {
        exitOptions = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.EXIT));
        exitOptions.addActionListener(new PkNonModalEvent(window.getModal()),new ExitInteractionEvent(this));
        panelOptions.add(exitOptions, MessagesGuiFct.BORDER_LAYOUT_SOUTH);
    }

    public void exitInteractionPack() {
        window.getModal().set(false);
        exitInteraction();
        window.pack();
    }

    public void exitInteraction() {
        panelOptions.removeAll();
        panelOptions.add(scrollPaneDetail, MessagesGuiFct.BORDER_LAYOUT_EAST);
        scrollPaneDetail.setNullViewportView();
        if (switchUsable != null) {
            switchUsable.setSelected(false);
        }
//        enabledSwitchTeam = false;
        panelMenu.setVisible(true);
        enableIfPossibleFishing();
        facade.exitInteract();
    }

    private void enableIfPossibleFishing() {
//        if (fish == null) {
//            return;
//        }
        fish.setEnabled(facade.isFishArea());
        attract.setEnabled(true);
        possibleRemoveAll();
        refreshButtons(false);
    }

//    private void showHtmlDialog(WindowAiki _parent, FacadeGame _dataBase, AikiNatLgNamesNavigation _pre, String _lg) {
////        DialogHtmlData.setDialogHtmlData(_parent, messages.getVal(TITLE_DETAIL), _session, window.isSuccessfulCompile());
//        DialogHtmlData.setDialogHtmlData(_parent, messages.getVal(TITLE_DETAIL), _dataBase,_pre,_lg);
//    }

    public void selectPokemon() {
        int index_ = teamPan.getSelectedIndexSingle();
//        if (index_ == IndexConstants.INDEX_NOT_FOUND_ELT) {
//            return;
//        }
        teamPan.getListe().setEnabled(false);
//        if (!teamPan.isSelected()) {
//            //facade.cancelUsingObjectOnPokemon();
//            return;
//        }
        facade.selectPokemon(index_);
        if (facade.isGivingObject()) {
            window.setSavedGame(false);
            exitInteractionPack();
            return;
        }
        if (displayComments()) {
            setTextArea(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE));
            window.setSavedGame(false);
            exitInteractionPack();
            return;
        }
        window.pack();
//        if (facade.getPlayer().getSelectedObject().isEmpty()) {
//            setTextArea(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE));
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
//            setTextArea(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE));
//            window.setSavedGame(false);
//            exitInteractionPack();
//            return;
//        }
//        window.pack();
    }
    private boolean displayComments() {
//        if (facade.getPlayer().getSelectedObject().isEmpty()) {
//            return true;
//        }
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
        return !used_;
    }

    private void setMovesAbilities() {
        movesLearntList.clear();
        movesLearntListLabel.clear();
        movesLearnt.removeAll();
        movesLearnt.add(window.getCompoFactory().newPlainLabel(messages.getVal(MessagesRenderScenePanel.SELECT_MT)));
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
//            check_.setBackground(GuiConstants.RED);
            movesLearnt.add(check_.getComponent());
            movesLearntList.add(check_.getComponent());
            movesLearntListLabel.add(check_);
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
//            check_.setBackground(GuiConstants.WHITE);
            movesLearnt.add(check_.getComponent());
            movesLearntList.add(check_.getComponent());
            movesLearntListLabel.add(check_);
        }
        StringList ab_ = facade.getPlayer().getNewAbilitiesToBeChosen();
        abilities.removeAll();
        abilityLabels.clear();
        if (!ab_.isEmpty()) {
            abilities.add(window.getCompoFactory().newPlainLabel(messages.getVal(MessagesRenderScenePanel.SELECT_ABILITY)));
            for (String a: ab_) {
                AbilityLabel lab_ = new AbilityLabel(facade.translateAbility(a), a, window.getCompoFactory());
                lab_.addMouseListener(new PkNonModalEvent(window.getModal()),new AbilityWalkEvent(this, a));
                abilities.add(lab_.getPaintableLabel());
                abilityLabels.add(lab_);
                AbsMetaLabelPk.paintPk(window.getImageFactory(), lab_);
            }
            abilities.add(window.getCompoFactory().newAbsPaintableLabel());
        }
        evolveStone = window.getCompoFactory().newPlainButton(messages.getVal(MessagesRenderScenePanel.EVOLVE));
        enableEvo();
        evolveStone.addActionListener(new PkNonModalEvent(window.getModal()),new EvolvePokemonEvent(this));
        abilities.add(evolveStone);
    }
    private void enableEvo() {
        evolveStone.setEnabled(okMoves() && okAbilities());
    }
    private boolean okMoves() {
        return movesLearntList.isEmpty() || facade.okMoves();
    }
    private boolean okAbilities() {
        if (abilityLabels.isEmpty()) {
            return true;
        }
        int count_ = 0;
        for (AbilityLabel a: abilityLabels) {
            if (a.isSelected()) {
                count_++;
            }
        }
        return count_ == 1;
    }
    public void evolvePokemon() {
        facade.evolvePokemon();
        String info_ = StringUtil.join(facade.getComment().getMessages(), RETURN_LINE);
        setTextArea(info_);
        window.setSavedGame(false);
        exitInteractionPack();
//        if (!facade.usedObjectForEvolving()) {
//            setTextArea(info_);
//            window.setSavedGame(false);
//            exitInteractionPack();
//        } else {
//            setTextArea(info_);
//        }
    }

    private void setHealedMoves() {
        movesLearntList.clear();
        movesLearnt.removeAll();
        movesLearnt.add(window.getCompoFactory().newPlainLabel(messages.getVal(MessagesRenderScenePanel.SELECT_HEAL_MOVE)));
        StringMap<Long> moves_ = facade.getPlayer().getChosenMoves();
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
            AbsButton check_ = window.getCompoFactory().newPlainButton(StringUtil.concat(tr_,SPACE,Long.toString(moves_.getVal(m))));
            check_.addActionListener(new PkNonModalEvent(window.getModal()),new HealMoveEvent(this, m));
            check_.setBackground(GuiConstants.WHITE);
            movesLearnt.add(check_);
            movesLearntList.add(check_);
        }
    }

    private void setBoostedMoves() {
        movesLearnt.removeAll();
        movesLearnt.add(window.getCompoFactory().newPlainLabel(messages.getVal(MessagesRenderScenePanel.SELECT_BOOST_MOVE)));
        movesLearntList.clear();
        StringMap<Long> moves_ = facade.getPlayer().getChosenMoves();
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
            AbsButton check_ = window.getCompoFactory().newPlainButton(StringUtil.concat(tr_,SPACE,Long.toString(moves_.getVal(m))));
            check_.addActionListener(new PkNonModalEvent(window.getModal()),new BoostMoveEvent(this, m));
            check_.setBackground(GuiConstants.WHITE);
            movesLearnt.add(check_);
            movesLearntList.add(check_);
        }
    }

    public void learnMove(String _move) {
        facade.addOrDeleteMoveEvo(_move);
        enableEvo();
    }

    public void healMove(String _move) {
        healMoveAndLog(_move);
        exitInteractionPack();
    }

    public void healMoveAndLog(String _move) {
        facade.healMove(_move);
        window.setSavedGame(false);
        setTextArea(StringUtil.join(facade.getPlayer().getCommentGame().getMessages(), RETURN_LINE));
    }

    public void boostMove(String _move) {
        facade.gainPpMax(_move);
        window.setSavedGame(false);
        setTextArea(StringUtil.join(facade.getPlayer().getCommentGame().getMessages(), RETURN_LINE));
        exitInteractionPack();
    }

    public void getAbility(String _ability) {
        facade.getPlayer().setChosenAbilityForEvolution(_ability);
        for (AbilityLabel l: abilityLabels) {
            l.setSelected(_ability);
            AbsMetaLabelPk.paintPk(window.getImageFactory(), l);
        }
        enableEvo();
    }

    public void selectPokemonHost() {
//        if (!enabledClick) {
//            return;
//        }
//        enabledClick = false;
        facade.setSelectPkToHost(teamPan.getSelectedIndex());
//        enabledClick = true;
    }

    public void selectPokemonTm() {
        facade.choosePokemonForLearningMove(teamPan.getSelectedIndex());
        if (facade.getPlayer().getSelectedMove().isEmpty()) {
            window.setSavedGame(false);
            setTextArea(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE));
            exitInteractionPack();
            return;
        }
        teamPan.getListe().setEnabled(false);
        movesLearnt.removeAll();
        movesLearnt.add(window.getCompoFactory().newPlainLabel(messages.getVal(MessagesRenderScenePanel.SELECT_TM)));
        movesLearntList.clear();
        StringMap<Long> moves_ = facade.getPlayer().getChosenMoves();
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
            AbsButton check_ = window.getCompoFactory().newPlainButton(StringUtil.concat(tr_,SPACE,Long.toString(moves_.getVal(m))));
            check_.addActionListener(new PkNonModalEvent(window.getModal()),new LearntMoveEvent(this, m));
            check_.setBackground(GuiConstants.WHITE);
            movesLearnt.add(check_);
            movesLearntList.add(check_);
        }
        window.pack();
    }

    public void learntMove(String _move) {
        facade.learnMove(_move);
        window.setSavedGame(false);
        setTextArea(StringUtil.join(facade.getComment().getMessages(), RETURN_LINE));
        exitInteractionPack();
    }
//
//    public boolean isMenusVisible() {
//        if (panelMenu == null) {
//            return true;
//        }
//        if (scene == null) {
//            return true;
//        }
//        return panelMenu.isVisible();
//    }

//    public void selectPkTeamTrade() {
//        if (!enabledClick) {
//            return;
//        }
////        if (readyCheck.isSelected()) {
////            return;
////        }
//        facade.setIndexTeamTrading(teamPan.getSelectedIndexSingle());
//        /*if (facade.isSelectedIndexTeamTrading()) {
//            enabledReady = true;
//            SentPokemon sent_;
//            sent_ = new SentPokemon();
//            sent_.setIndex(window.getIndexInGame());
//            sent_.setPokemon(facade.getSentPokemon());
//            window.sendObject(sent_);
//        } else {
//            enabledReady = false;
//            //disable ready button
//        }
//        readyCheck.setEnabled(enabledReady);*/
//    }

    public void setTextArea(String _text) {
        if (_text.isEmpty()) {
            return;
        }
//        AbsWrappedTextArea commentsWalking_ = window.getCompoFactory().newWrappedTextArea(4, 32);
//        commentsWalking_.setEditable(false);
//        commentsWalking_.setText(_text);
        resultScene.display(messages.getVal(MessagesRenderScenePanel.TITLE_COMMENTS),_text);
//        window.getFrames().getMessageDialogAbs().input(window.getCommonFrame(), compoFactory.newAbsScrollPane(commentsWalking_), messages.getVal(TITLE_COMMENTS), _messageType);
    }

    public Scene getScene() {
        return scene;
    }

//    public boolean isPaintingScene() {
//        return paintingScene.get();
//    }

    public AbstractAtomicBoolean getPaintingScene() {
        return paintingScene;
    }

    public void setPaintingScene(boolean _paintingScene) {
        if (_paintingScene) {
            window.disableBasic();
        } else {
            window.reenableBasic();
        }
        boolean enabled9_ = !_paintingScene;
        team.setEnabled(enabled9_);
        boolean enabled8_ = !_paintingScene;
        items.setEnabled(enabled8_);
        boolean enabled7_ = !_paintingScene;
        tm.setEnabled(enabled7_);
        //        difficulty.setEnabledLabel(!_paintingScene);
        boolean enabled6_ = !_paintingScene && facade.isFishArea();
        fish.setEnabled(enabled6_);
        attract.setEnabled(!_paintingScene);
        boolean enabled5_ = !_paintingScene;
        seeBoxes.setEnabled(enabled5_);
        boolean enabled4_ = !_paintingScene;
        seeEggs.setEnabled(enabled4_);
        boolean enabled3_ = !_paintingScene;
        host.setEnabled(enabled3_);
        boolean enabled2_ = !_paintingScene;
        game.setEnabled(enabled2_);
        boolean enabled1_ = !_paintingScene;
        goBack.setEnabled(enabled1_);
        possibleRemoveAll();
        refreshButtons(_paintingScene);
        if (!_paintingScene) {
            window.pack();
        }
//        boolean enabled_ = !_paintingScene;
//        server.setEnabled(enabled_);
//        panelMenu.repaintSecondChildren(window.getImageFactory());
    }

    private void refreshButtons(boolean _paintingScene) {
        if (gymTrainer == null) {
            return;
        }
        if (_paintingScene) {
            return;
        }
        Level levelByCoords_ = facade.getMap().getLevelByCoords(facade.getGame().getPlayerCoords());
        if (levelByCoords_ instanceof LevelIndoorGym) {
            PointEqList pts_ = new PointEqList(((LevelIndoorGym)levelByCoords_).getGymTrainers().getKeys());
            pts_.removeAllElements(facade.getGame().getBeatGymTrainer().getVal(facade.getGame().getPlayerCoords().getNumberPlace()));
            for (Point p: pts_) {
                Coords c_ = new Coords(facade.getGame().getPlayerCoords());
                c_.getLevel().setPoint(p);
                AbsButton but_ = window.getCompoFactory().newPlainButton(p.display());
                but_.addActionListener(new PkNonModalEvent(window.getModal()),new InitGymTrainerFightEvent(this,(LevelIndoorGym)levelByCoords_,c_));
                gymTrainer.add(but_);
            }
        }
    }

    public AbsPanel getGymTrainer() {
        return gymTrainer;
    }

    public Pad getPad() {
        return pad;
    }

    public AbsButton getSeeBoxes() {
        return seeBoxes;
    }

    public AbsButton getSeeEggs() {
        return seeEggs;
    }

    public AbsButton getTeam() {
        return team;
    }

    public AbsButton getItems() {
        return items;
    }

    public AbsButton getTm() {
        return tm;
    }

    public AbsButton getFish() {
        return fish;
    }

    public AbsButton getHost() {
        return host;
    }

    public AbsButton getGame() {
        return game;
    }

    public AbsButton getGoBack() {
        return goBack;
    }

    public AbsButton getAttract() {
        return attract;
    }

    public TeamPanel getTeamPan() {
        return teamPan;
    }

    public TmPanel getTmPanel() {
        return tmPanel;
    }

    public AbsButton getAddTmBuy() {
        return addTmBuy;
    }

    public AbsButton getRemoveTmBuy() {
        return removeTmBuy;
    }

    public AbsButton getTmBuy() {
        return tmBuy;
    }

    public ItemsPanel getItemsPan() {
        return itemsPan;
    }

    public AbsButton getBuy() {
        return buy;
    }

    public AbsButton getAddItemBuy() {
        return addItemBuy;
    }

    public AbsButton getRemoveItemBuy() {
        return removeItemBuy;
    }

    public AbsButton getSelectItemBuy() {
        return selectItemBuy;
    }

    public AbsButton getBuySell() {
        return buySell;
    }

    public AbsButton getSelectPkBox() {
        return selectPkBox;
    }

    public AbsButton getSelectEggBox() {
        return selectEggBox;
    }

    public AbsButton getTakeItem() {
        return takeItem;
    }

    public AbsButton getStore() {
        return store;
    }

    public AbsButton getRelease() {
        return release;
    }

    public AbsButton getWithdraw() {
        return withdraw;
    }

    public AbsButton getWithdrawEgg() {
        return withdrawEgg;
    }

    public AbsButton getSwitchPk() {
        return switchPk;
    }

    public AbsButton getButtonInteract() {
        return buttonInteract;
    }

    public AbsCustCheckBox getSwitchUsable() {
        return switchUsable;
    }

    public AbsButton getTakeItemTeam() {
        return takeItemTeam;
    }

    public AbsButton getDetailPk() {
        return detailPk;
    }

    public AbsButton getHealPk() {
        return healPk;
    }

    public AbsTextField getNicknameField() {
        return nicknameField;
    }

    public MapPanel getMapPanel() {
        return mapPanel;
    }

    public AbsButton getValidateMoveToPlace() {
        return validateMoveToPlace;
    }

    public AbsButton getExitOptions() {
        return exitOptions;
    }

    public CustList<AbsCustComponent> getMovesLearntList() {
        return movesLearntList;
    }

    public CustList<CheckBox> getMovesLearntListLabel() {
        return movesLearntListLabel;
    }

    public CustList<AbilityLabel> getAbilityLabels() {
        return abilityLabels;
    }

    public AbsButton getEvolveStone() {
        return evolveStone;
    }

    public AbsButton getReceiveEgg() {
        return receiveEgg;
    }

    public AbsButton getReceiveParents() {
        return receiveParents;
    }

    public AbsButton getHostPk() {
        return hostPk;
    }

    public AbsButton getCancelMoveTutor() {
        return cancelMoveTutor;
    }

    public AbsButton getOkMoveTutor() {
        return okMoveTutor;
    }

    public ReportingFrame getResultScene() {
        return resultScene;
    }

    public AbsPanel getPanelOptions() {
        return panelOptions;
    }

    public AbsPanel getComponent() {
        return component;
    }

    public PkDetailContent getPkDetailContent() {
        return pkDetailContent;
    }

    public WindowAiki getWindow() {
        return window;
    }
}
