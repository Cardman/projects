package cards.gui.containers;






import cards.consts.GameType;
import cards.facade.Games;
import cards.facade.MessagesCardGames;
import cards.facade.enumerations.GameEnum;
import cards.gui.containers.events.GiveCardsEvent;
import cards.gui.containers.events.NoPlayPresidentEvent;
import cards.gui.dialogs.*;
import cards.gui.events.ListenerCardPresidentMultiGame;
import cards.gui.labels.GraphicCard;
import cards.gui.panels.CarpetPresident;
import cards.main.CardNatLgNamesNavigation;
import cards.network.common.before.*;
import cards.network.president.actions.DiscardedCardsPresident;
import cards.network.president.actions.PlayingCardPresident;
import cards.network.president.displaying.*;
import cards.network.president.unlock.AllowDiscarding;
import cards.network.president.unlock.AllowPlayingPresident;
import cards.network.threads.Net;
import cards.president.*;
import cards.president.beans.PresidentStandards;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.Playing;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.events.AbsActionListenerAct;
import code.gui.events.AlwaysActionListenerAct;
import code.gui.files.MessagesGuiFct;
import code.gui.images.MetaDimension;
import code.network.NetGroupFrame;
import code.network.WindowNetWork;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.util.*;
import code.util.core.*;

public final class ContainerMultiPresident extends ContainerPresident implements
        ContainerMulti,ContainerPlayablePresident {

    private final ContainerMultiContent containerMultiContent;
//    private int noClient;
//    private byte indexInGame = IndexConstants.INDEX_NOT_FOUND_ELT;
    private RulesPresident rulesPresidentMulti = new RulesPresident();
//    private NumComboBox choiceOfPlaceForPlayingGame;
//    private AbsCustCheckBox ready;

//    private DealingPresident repPresident;
//    private int nbChoosenPlayers = IndexConstants.INDEX_NOT_FOUND_ELT;
//    private final boolean hasCreatedServer;
//    private boolean readyToPlay;
//    private final CustList<AbsPlainLabel> playersPseudos = new CustList<AbsPlainLabel>();
//    private final CustList<AbsPlainLabel> playersPlaces = new CustList<AbsPlainLabel>();
//    private final CustList<AbsCustCheckBox> playersReady = new CustList<AbsCustCheckBox>();
//    private RenderedPage editor;
//    private IntTreeMap< Byte> playersPlacesForGame = new IntTreeMap< Byte>();
//    private IntMap<String> playersPseudosForGame = new IntMap<String>();
    private HandPresident playerHandPresident = new HandPresident();
//    private final AbsPlainLabel canPlayLabel = getOwner().getCompoFactory().newPlainLabel("");
    private int nbCardsDiscard;
//    private final WindowNetWork win;
//    private boolean enabledPass;
    private boolean reversedGame;
    private HandPresident allowed = new HandPresident();
    private DialogPresidentContent dialogPresidentContent;
    private AbsButton selectRules;

    public ContainerMultiPresident(WindowNetWork _window, boolean _hasCreatedServer) {
        super(_window);
        containerMultiContent = new ContainerMultiContent(_hasCreatedServer, _window);
        containerMultiContent.setMessages(MessagesCardGames.getMulti(MessagesCardGames.getAppliTr(_window.getFrames().currentLg())).getMapping());
        _window.update(this);
//        win = _window;
//        hasCreatedServer = _hasCreatedServer;
//        if (containerMultiContent.isHasCreatedServer()) {
//            Net.getGames(_window.getNet()).setRulesBelote(null);
//            setRulesPresidentMulti(new RulesPresident(_nbPlayers));
//            Net.getGames(_window.getNet()).setRulesPresident(getRulesPresidentMulti());
//            Net.getGames(_window.getNet()).setRulesTarot(null);
//        }
    }

    @Override
    public void updateFirst(IndexOfArrivingCards _players) {
        getPane().removeAll();
        MenuItemUtils.setEnabledMenu(getContainerMultiContent().window().getMultiStop(),true);
        MenuItemUtils.setEnabledMenu(getContainerMultiContent().window().getTricksHands(),true);
        MenuItemUtils.setEnabledMenu(getContainerMultiContent().window().getTeams(),true);
//        MenuItemUtils.setEnabledMenu(getLoad(),false);
        rulesPresidentMulti = _players.getRulesPresident();
        Net.getGames(getContainerMultiContent().window().getNet()).setRulesPresident(getRulesPresidentMulti());
        AbsPanel container_ = containerMultiContent.resultUsers(this,_players);
//        nbChoosenPlayers = _players.getNbPlayers();
//        AbsPanel container_ = getOwner().getCompoFactory().newPageBox();
//        AbsPanel panel_ = getOwner().getCompoFactory().newGrid(0, 2);
//        panel_.add(getOwner().getCompoFactory().newPlainLabel(containerMultiContent.getMessages().getVal(WindowNetWork.PLACE)));
//        choiceOfPlaceForPlayingGame = new NumComboBox(getOwner().getFrames());
//        choiceOfPlaceForPlayingGame.setItems(nbChoosenPlayers);
//        choiceOfPlaceForPlayingGame.setSelectedItem(_players.getPseudos().size() - 1);
//        indexInGame = (byte) NumberUtil.parseInt(choiceOfPlaceForPlayingGame.getSelectedItem());
//        choiceOfPlaceForPlayingGame.setListener(new ChangePlaceEvent(this));
//        panel_.add(choiceOfPlaceForPlayingGame.self());
//        ready = getOwner().getCompoFactory().newCustCheckBox(containerMultiContent.getMessages().getVal(WindowNetWork.READY));
//        ready.addActionListener(new ReadyEvent(this));
//        panel_.add(ready);
//        container_.add(panel_);
//        panel_ = getOwner().getCompoFactory().newGrid(0, 3);
//        playersPseudos.clear();
//        for (int i = IndexConstants.FIRST_INDEX; i < nbChoosenPlayers; i++) {
//            AbsPlainLabel pseudo_ = getOwner().getCompoFactory().newPlainLabel("");
//            playersPseudos.add(pseudo_);
//            panel_.add(pseudo_);
//            AbsPlainLabel place_ = getOwner().getCompoFactory().newPlainLabel("");
//            playersPlaces.add(place_);
//            panel_.add(place_);
//            AbsCustCheckBox ready_ = getOwner().getCompoFactory().newCustCheckBox();
//            ready_.setEnabled(false);
//            playersReady.add(ready_);
//            panel_.add(ready_);
//        }
//        container_.add(panel_);

        rulesPresidentMulti.getCommon().setGeneral(WindowNetWork.readCoreResourceMix(this));
        rulesPresidentMulti.getCommon().setSpecific(readResource());
        CardNatLgNamesNavigation stds_ = retrieve(FrameGeneralHelp.RESOURCES_HTML_FILES_RULES_PRESIDENT).attendreResultat();
        ((PresidentStandards)stds_.getBeanNatLgNames()).setDataBaseRules(rulesPresidentMulti);
        containerMultiContent.setEditor(FrameGeneralHelp.initialize(stds_, getOwner().getFrames(), containerMultiContent.window().getGuardRender()));

        containerMultiContent.getEditor().getScroll().setPreferredSize(new MetaDimension(300,400));
        container_.add(containerMultiContent.getEditor().getScroll());

        getContainerMultiContent().updateAfter(this,_players,MessagesGuiCards.PLAY_PRESIDENT, container_);
//        playersPlacesForGame = _players.getPlacesPlayers();
//        playersPseudosForGame = new IntMap<String>(_players.getPseudos());
//        for (int i : _players.getPseudos().getKeys()) {
//            playersPseudos.get(i).setText(_players.getPseudos().getVal(i));
//        }
//        for (int i : _players.getPlacesPlayers().getKeys()) {
//            playersPlaces.get(i).setText(_players.getPlacesPlayers().getVal(i).toString());
//        }
//        for (int i : _players.getReadyPlayers().getKeys()) {
//            playersReady.get(i).setSelected(_players.getReadyPlayers().getVal(i) == BoolVal.TRUE);
//        }
//        if (containerMultiContent.isHasCreatedServer()) {
//            updateButton(container_);
//            AbsButton button_ = getOwner().getCompoFactory().newPlainButton(containerMultiContent.getMessages().getVal(MessagesGuiCards.PLAY_PRESIDENT));
//            button_.addActionListener(new PlayFirstDealEvent(this));
//            container_.add(button_);
//        }
//        container_.add(getWindow().getClock());
//        container_.add(getWindow().getLastSavedGameDate());
//        setContentPane(container_);
        //PackingWindowAfter.pack(this, true);
//        pack();
    }

    public void updateButton(AbsPanel _container) {
        dialogPresidentContent = new DialogPresidentContent(getOwner().getFrames());
        AbsTabbedPane jt_ = dialogPresidentContent.initJt(null, false, containerMultiContent.getNbChoosenPlayers(), getOwner());
        AbsPanel border_ = getOwner().getCompoFactory().newBorder();
        border_.add(jt_, MessagesGuiFct.BORDER_LAYOUT_CENTER);
        selectRules= getOwner().getCompoFactory().newPlainButton(containerMultiContent.getMessages().getVal(MessagesGuiCards.SELECT_RULES));
        selectRules.addActionListener(new AfterValidateRulesPresidentMulti(dialogPresidentContent,this));
        border_.add(selectRules, MessagesGuiFct.BORDER_LAYOUT_SOUTH);
        _container.add(border_);
    }
//    @Override
//    public void changePlace() {
//        if (readyToPlay) {
//            return;
//        }
//        indexInGame = (byte) NumberUtil.parseInt(choiceOfPlaceForPlayingGame.getSelectedItem());
//        ChoosenPlace choice_ = new ChoosenPlace();
//        choice_.setIndex(noClient);
//        choice_.setPlace(indexInGame);
//        choice_.setPlacesPlayers(new IntTreeMap< Byte>());
//        window().sendObject(choice_);
//    }

//    @Override
//    public void updatePlaces(ChoosenPlace _choosePlace) {
//        playersPlacesForGame = _choosePlace.getPlacesPlayers();
//        playersPlaces.get(_choosePlace.getIndex()).setText(Long.toString(_choosePlace.getPlace()));
//    }

//    @Override
//    public void updateReady(Ready _readyPlayer) {
//        playersReady.get(_readyPlayer.getIndex()).setSelected(_readyPlayer.isReady());
//    }

//    @Override
//    public void updateAfter(PlayersNamePresent _players) {
//        playersPlacesForGame = _players.getPlacesPlayers();
//        playersPseudosForGame = new IntMap<String>(_players.getPseudos());
//        for (int i : _players.getPseudos().getKeys()) {
//            playersPseudos.get(i).setText(_players.getPseudos().getVal(i));
//        }
//        for (int i : _players.getPlacesPlayers().getKeys()) {
//            playersPlaces.get(i).setText(_players.getPlacesPlayers().getVal(i).toString());
//        }
//        for (int i : _players.getReadyPlayers().getKeys()) {
//            playersReady.get(i).setSelected(_players.getReadyPlayers().getVal(i) == BoolVal.TRUE);
//        }
//    }

    public void updateRules(RulesPresident _rules) {
        rulesPresidentMulti = _rules;
        Net.getGames(getContainerMultiContent().window().getNet()).setRulesPresident(getRulesPresidentMulti());
        CardNatLgNamesNavigation stds_ = retrieve(FrameGeneralHelp.RESOURCES_HTML_FILES_RULES_PRESIDENT).attendreResultat();
        ((PresidentStandards)stds_.getBeanNatLgNames()).setDataBaseRules(rulesPresidentMulti);
        FrameGeneralHelp.initialize(stds_, containerMultiContent.getEditor());
    }

    public void updateForBeginningGame(DealtHandPresident _hand) {
        placerIhmPresidentMulti(_hand.getStatus(), _hand.getMaxCards());

        playerHandPresident = _hand.getCards();
        playerHandPresident.sortCards(getDisplayingPresident().getDisplaying().isDecreasing(), false);
//        setCarteEntree(false);
//        setCarteSortie(false);
        /* On place les cartes de l'utilisateur */
        reversedGame = false;
        updateCardsInPanelPresidentMulti(false);
//        if (repPresident.getRemainingCards() > 0) {
//            for (BidPresidentSuit b : _hand.getAllowedBids()) {
//                ajouterBoutonContratPresidentMulti(b.toString(), b);
//            }
//        } else {
//            addButtonsForCoinche(_hand.getPoints(), _hand.getAllowedBids());
//        }
        pack();
        getContainerMultiContent().sendDealt();
//        PlayerActionGame dealt_ = new PlayerActionGame(PlayerActionGameType.DEALT);
//        dealt_.setPlace(containerMultiContent.getIndexInGame());
//        String lg_ = getOwner().getLanguageKey();
//        dealt_.setLocale(lg_);
//        window().sendObject(dealt_);
    }

    public void canDiscardPresident(AllowDiscarding _allow) {
        nbCardsDiscard = _allow.getReceivedCards().total();
        setGivingCardsOk(getOwner().getCompoFactory().newPlainButton(file().getVal(MessagesGuiCards.MAIN_OK)));
        getGivingCardsOk().setEnabled(false);
        getGivingCardsOk().addActionListener(guard(),new GiveCardsEvent(this));
        //        getPanneauBoutonsJeu().add(getGivingCardsOk());
        getActionsHistory().add(getGivingCardsOk());
        getReceivedCards().supprimerCartes();
        getReceivedCards().ajouterCartes(_allow.getReceivedCards());
        updateCardsInPanelPresidentReceived();
        getGivenCards().supprimerCartes();
        getVirtualHand().supprimerCartes();
        getVirtualHand().ajouterCartes(playerHandPresident);
        updateCardsInPanelPresidentDiscard(this);
        pack();
    }

    @Override
    public void discard(byte _index) {
        super.discard(_index);
        updateCardsInPanelPresidentDiscard(this);
        updateDis();
        pack();
    }

    @Override
    public void cancelDiscard(byte _index) {
        super.cancelDiscard(_index);
        updateCardsInPanelPresidentDiscard(this);
        updateDis();
        pack();
    }

    private void updateDis() {
        getGivingCardsOk().setEnabled(nbCardsDiscard == getGivenCards().total());
    }

    @Override
    public void discard() {
        //The deal is now ready
        getGivingCardsOk().setVisible(false);
        HandPresident cards_ = getOwner().baseWindow().getIa().getPresident().strategieEchangeUser(getGivenCards());
        playerHandPresident.supprimerCartes(cards_);
        playerHandPresident.sortCards(getDisplayingPresident().getDisplaying().isDecreasing(), false);
        reversedGame = false;
        updateCardsInPanelPresidentMulti(false);
        getNoPlay().setVisible(true);
        pack();
//        String lg_ = getOwner().getLanguageKey();
        DiscardedCardsPresident dis_ = new DiscardedCardsPresident();
        dis_.setPlace(containerMultiContent.getIndexInGame());
        dis_.setDiscarded(cards_);
//        dis_.setLocale(lg_);
        getContainerMultiContent().window().sendObject(dis_);
    }

    public void refreshLoserHand(ReceivedGivenCards _readObject) {
        playerHandPresident = _readObject.getNewHand();
        playerHandPresident.sortCards(getDisplayingPresident().getDisplaying().isDecreasing(), false);
        reversedGame = false;
        updateCardsInPanelPresidentMulti(false);
        getReceivedCards().supprimerCartes();
        getReceivedCards().ajouterCartes(_readObject.getReceived());
        updateCardsInPanelPresidentReceived();
        getGivenCards().supprimerCartes();
        getGivenCards().ajouterCartes(_readObject.getGiven());
        updateCardsInPanelPresidentGiven();
        getNoPlay().setVisible(true);
        pack();
        NetGroupFrame.trySendString(Net.exportRefreshedHandPresident(containerMultiContent.getIndexInGame()),getContainerMultiContent().window().getSocket());
    }

    public void canPlayPresident(AllowPlayingPresident _readObject) {
//        setRaisonCourante(EMPTY);
        containerMultiContent.getCanPlayLabel().setText(containerMultiContent.getMessages().getVal(MessagesGuiCards.CAN_PLAY));
        reversedGame = _readObject.isReversed();
        allowed = _readObject.getCards();
        updateCardsInPanelPresidentMulti(true);
        getPanneauBoutonsJeu().removeAll();
        updateCardsInPanelPresidentReceived();
        updateCardsInPanelPresidentGiven();
        getPanneauBoutonsJeu().add(getPanelGivenCards());
        getPanneauBoutonsJeu().add(getPanelReceivedCards());
        noPlayText(_readObject.getStatus());
//        enabledPass = _readObject.isEnabledPass();
        getNoPlay().setEnabled(_readObject.isEnabledPass());
        getNoPlay().setVisible(true);
        //        getPanneauBoutonsJeu().add(getNoPlay());
        getPanneauBoutonsJeu().validate();
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),true);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),true);
        pack();
    }

//    public void errorPlayingCard(ErrorPlayingPresident _readObject) {
//        TranslationsLg lg_ = getOwner().getFrames().currentLg();
//        getNoPlay().setEnabled(enabledPass);
//        updateCardsInPanelPresidentMulti(true);
//        if (_readObject.isPassIssue()) {
//            String title_ = containerMultiContent.getMessages().getVal(WindowNetWork.CANT_PLAY_CARD_TITLE);
//            getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(), _readObject.getReason(), title_, GuiConstants.ERROR_MESSAGE);
//        } else {
//            String mes_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CANT_PLAY_CARD), Games.toString(_readObject.getCard(),lg_));
//            String finalMessage_ = StringUtil.concat(mes_,RETURN_LINE,_readObject.getReason());
//            String title_ = containerMultiContent.getMessages().getVal(WindowNetWork.CANT_PLAY_CARD_TITLE);
//            getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(), finalMessage_, title_, GuiConstants.ERROR_MESSAGE);
//        }
//    }

    public void displayPlayedCard(PlayingCardPresident _card) {
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        containerMultiContent.getCanPlayLabel().setText(EMPTY_STRING);
        byte relative_ = containerMultiContent.relative(_card.getNextPlayer());
        ByteMap<Playing> status_ = status(_card.getStatus(), containerMultiContent);
        tapisPresident().setTalonPresident(getWindow().getImageFactory(),lg_,_card.getPlayedHand());
//        tapisPresident().repaintValidate();
        tapisPresident().setStatus(getWindow().getImageFactory(),lg_,status_, relative_);
//        tapisPresident().repaintValidate();

        String pseudo_ = containerMultiContent.getPseudoByPlace(_card.getPlace());
        ajouterTexteDansZone(StringUtil.concat(pseudo_, INTRODUCTION_PTS, Games.toString(_card.getPlayedHand(),lg_), RETURN_LINE));
        //PackingWindowAfter.pack(this, true);
        pack();
        NetGroupFrame.trySendString(Net.exportDonePlaying(containerMultiContent.getIndexInGame()),getContainerMultiContent().window().getSocket());
    }

    @Override
    public void noPlay() {
//        if (!isCanPlay()) {
//            return;
//        }
//        String lg_ = getOwner().getLanguageKey();
        updateCardsInPanelPresidentMulti(false);
        getNoPlay().setEnabled(false);
        PlayingCardPresident pl_ = new PlayingCardPresident();
        pl_.setRefreshing(false);
//        pl_.setLocale(lg_);
        pl_.setPlace(containerMultiContent.getIndexInGame());
        pl_.setPass(true);
        pl_.setPlayedCard(CardPresident.WHITE);
        pl_.setPlayedHand(new HandPresident());
        pl_.setStatus(new ByteMap< Playing>());
        getContainerMultiContent().window().sendObject(pl_);
    }

    public void refreshHand(PlayingCardPresident _card) {
//        if (_card.getPlace() == indexInGame) {
//            playerHandPresident.supprimerCartes(_card.getPlayedHand());
//        }
        playerHandPresident.supprimerCartes(_card.getPlayedHand());
        playerHandPresident.sortCards(getDisplayingPresident().getDisplaying().isDecreasing(), _card.isReversed());
        getPanneauBoutonsJeu().removeAll();
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),false);
        /* On place les cartes de l'utilisateur */
        reversedGame = _card.isReversed();
        updateCardsInPanelPresidentMulti(false);
        pack();
        //PackingWindowAfter.pack(this, true);
        PlayingCardPresident ref_ = new PlayingCardPresident();
        ref_.setRefreshing(true);
        ref_.setPlayedHand(_card.getPlayedHand());
        ref_.setStatus(_card.getStatus());
        ref_.setNextPlayer(_card.getNextPlayer());
        ref_.setPlace(containerMultiContent.getIndexInGame());
        ref_.setPlayedCard(CardPresident.WHITE);
//        String lg_ = getOwner().getLanguageKey();
//        ref_.setLocale(lg_);
        getContainerMultiContent().window().sendObject(ref_);

    }

    @Override
    public void pauseBetweenTrick() {
        tapisPresident().setTalonPresident(getWindow().getImageFactory());
//        tapisPresident().repaintValidate();
        //pack();
//        String lg_ = getOwner().getLanguageKey();
        containerMultiContent.sendPause();
//        PlayerActionGame d_ = new PlayerActionGame(PlayerActionGameType.DONE_PAUSE);
//        d_.setPlace(containerMultiContent.getIndexInGame());
//        d_.setLocale(lg_);
//        window().sendObject(d_);
    }

    @Override
    public void showTricksHands() {
//        if (!isCanPlay()) {
//            return;
//        }
        containerMultiContent.showTricksHands();
//        PlayerActionGame select_ = new PlayerActionGame(PlayerActionGameType.SELECT_TRICKS_HANDS);
//        select_.setPlace(containerMultiContent.getIndexInGame());
//        String lg_ = getOwner().getLanguageKey();
//        select_.setLocale(lg_);
//        window().sendObject(select_);
    }

    public void showTricksHands(TricksHandsPresident _tricks) {
//        ByteTreeMap< String> pseudos_ = new ByteTreeMap< String>();
//        byte p_ = 0;
//        for (String s : pseudosPresident((byte) containerMultiContent.getNbChoosenPlayers())) {
//            pseudos_.put(p_, s);
//            p_++;
//        }
//        for (byte p : containerMultiContent.getPlayersPlacesForGame().values()) {
//            pseudos_.put(p, containerMultiContent.getPseudoByPlace(p));
//        }
//        StringList list_ = new StringList(nicknames().values());
        WindowNetWork ow_ = containerMultiContent.window();
        DialogTricksPresident.setDialogTricksPresident(
                file().getVal(MessagesGuiCards.MAIN_HANDS_TRICKS_PRESIDENT), ow_);
        DialogTricksPresident.init(_tricks, (byte) containerMultiContent.getNbChoosenPlayers(), nicknames(),
                getDisplayingPresident(),ow_);
    }

//    @Override
//    public void setNoClient(int _noClient) {
//        noClient = _noClient;
//    }
//
//    @Override
//    public int getNoClient() {
//        return noClient;
//    }

    private void placerIhmPresidentMulti(ByteMap<Playing> _status, int _nbMax) {
        getPane().removeAll();
        MenuItemUtils.setEnabledMenu(getContainerMultiContent().window().getMultiStop(),false);
        AbsPanel container_ = getOwner().getCompoFactory().newBorder();
        container_.add(getOwner().getCompoFactory().newPlainLabel(helpMenuTip()), MessagesGuiFct.BORDER_LAYOUT_NORTH);
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        CarpetPresident tapis_ = new CarpetPresident();
//        ByteTreeMap<String> pseudos_ = nicknames();
        ByteMap<Playing> status_ = status(_status, containerMultiContent);
//        StringList list_ = new StringList(pseudos_.values());
        StringList list_ = nicknames();
        tapis_.initTapisPresident(lg_,list_, status_, _nbMax, getOwner().getCompoFactory());
        getTapis().setTapisPresident(tapis_);
        container_.add(tapis_.getContainer(), MessagesGuiFct.BORDER_LAYOUT_CENTER);
//        AbsPanel panneau_ = panelHand();
//        AbsPanel panneau_ = getOwner().getCompoFactory().newLineBox();
//        panneau_.setBackground(GuiConstants.BLUE);
//        setPanelHand(panneau_);
//        panelHand(panneau_);
        container_.add(panelHand(), MessagesGuiFct.BORDER_LAYOUT_SOUTH);
        AbsPanel panneau2_=getOwner().getCompoFactory().newPageBox();
        panneau2_.add(events());
//        setEvents(getOwner().getCompoFactory().newTextArea(EMPTY,8, 30));
//        getEvents().setEditable(false);
//        panneau2_.add(getOwner().getCompoFactory().newAbsScrollPane(getEvents()));
        initHandfuls();
//        setHandfuls(new ByteMap<AbsPlainLabel>());
//        setDeclaredHandfuls(new ByteMap<AbsPanel>());
//        JPanel declaredHandfuls_ = new JPanel(new GridLayout(0,1));
//        int nbPlayers_ = partie_.getNombreDeJoueurs();
        AbsPanel sousPanneau_=getOwner().getCompoFactory().newPageBox();
//        AbsPanel panelCards_ = getOwner().getCompoFactory().newLineBox();
        AbsPanel panelDiscard_;
        panelDiscard_= getOwner().getCompoFactory().newLineBox();
        panelDiscard_.setTitledBorder(file().getVal(MessagesGuiCards.MAIN_GIVEN_CARDS));
        sousPanneau_.add(panelDiscard_);
        setPanelGivenCards(panelDiscard_);
        AbsPanel panelRec_;
        panelRec_= getOwner().getCompoFactory().newLineBox();
        panelRec_.setTitledBorder(file().getVal(MessagesGuiCards.MAIN_RECEIVED_CARDS));
        sousPanneau_.add(panelRec_);
        setPanelReceivedCards(panelRec_);
//        sousPanneau_.add(panelCards_);
        setNoPlay(getOwner().getCompoFactory().newPlainButton(EMPTY));
        getNoPlay().addActionListener(new NoPlayPresidentEvent(this));
        setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(getOwner().getCompoFactory().newAbsScrollPane(sousPanneau_));
        getNoPlay().setVisible(false);
        panneau2_.add(getNoPlay());
        setActionsHistory(panneau2_);
        container_.add(panneau2_, MessagesGuiFct.BORDER_LAYOUT_EAST);
        AbsPanel panel_ = getOwner().getCompoFactory().newPageBox();
        panel_.add(getOwner().getCompoFactory().newAbsScrollPane(container_));
        containerMultiContent.getCanPlayLabel().setText(EMPTY_STRING);
        panel_.add(containerMultiContent.getCanPlayLabel());
//        readyToPlay = false;
//        ready = getOwner().getCompoFactory().newCustCheckBox(containerMultiContent.getMessages().getVal(WindowNetWork.READY));
//        ready.addActionListener(new ReadyEvent(this));
//        panel_.add(ready);
        containerMultiContent.endReady(this,panel_);
        panel_.add(getWindow().getClock().getComponent());
        panel_.add(getWindow().getLastSavedGameDate());
        setContentPane(panel_);
    }

    private static ByteMap<Playing> status(ByteMap<Playing> _status, ContainerMultiContent _containerMultiContent) {
        ByteMap<Playing> status_ = new ByteMap<Playing>();
        for (byte p: _status.getKeys()) {
            status_.put(_containerMultiContent.relative(p), _status.getVal(p));
        }
        return status_;
    }

    private StringList nicknames() {
        return new StringList(containerMultiContent.nicknames(pseudosPresident((byte) containerMultiContent.getNbChoosenPlayers())).values());
    }

//    private String getPseudoByPlace(byte _place) {
//        for (int i : playersPlacesForGame.getKeys()) {
//            if (playersPlacesForGame.getVal(i) == _place) {
//                return playersPseudosForGame.getVal(i);
//            }
//        }
//        return EMPTY_STRING;
//    }

//    private byte relative(int _otherPlayerIndex) {
//        byte iter_ = 0;
//        for (byte p = indexInGame; p < nbChoosenPlayers; p++) {
//            if (p == _otherPlayerIndex) {
//                return iter_;
//            }
//            iter_++;
//        }
//        for (byte p = IndexConstants.FIRST_INDEX; p < indexInGame; p++) {
//            if (p == _otherPlayerIndex) {
//                return iter_;
//            }
//            iter_++;
//        }
//        return iter_;
//    }

    public void updateCardsInPanelPresidentMulti(boolean _listener) {
        updateCardsInPanelPresidentMulti(getPanelHand(), playerHandPresident, _listener);
    }
    private void updateCardsInPanelPresidentMulti(AbsPanel _panel, HandPresident _hand, boolean _listener) {
        _panel.removeAll();
        int str_ = 0;
        int iter_ = IndexConstants.FIRST_INDEX;
        byte index_ = IndexConstants.SECOND_INDEX;
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        for (GraphicCard<CardPresident> c: getGraphicCards(this,_hand.getCards())) {
            int curStr_ = c.getCard().strength(reversedGame);
            if (iter_ > IndexConstants.FIRST_INDEX) {
                if (curStr_ == str_) {
                    index_++;
                } else {
                    index_ = IndexConstants.SECOND_INDEX;
                }
            }
            if (_listener) {
                if (!allowed.getCardsByStrength(curStr_,reversedGame).estVide()){
                    c.addMouseListener(new ListenerCardPresidentMultiGame(this,c.getCard(), index_));
                } else {
                    StringBuilder mes_ = new StringBuilder(StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CANT_PLAY_CARD), Games.toString(c.getCard(),lg_)));
//                    mes_.append(ContainerGame.RETURN_LINE);
//                    mes_.append(Games.autorisePresident(gamePresident_, c.getCard(), index_, lg_));
                    String finalMessage_ = mes_.toString();
                    c.getPaintableLabel().setToolTipText(finalMessage_);
                }
            }
            str_ = curStr_;
            iter_++;
            _panel.add(c.getPaintableLabel());
        }
        _panel.setSize(_panel.getPreferredSizeValue());
//        _panel.removeAll();
//        int str_ = 0;
//        int iter_ = IndexConstants.FIRST_INDEX;
//        byte index_ = IndexConstants.SECOND_INDEX;
//        for (GraphicCard<CardPresident> c: getGraphicCards(this,_hand.getCards())) {
//            int curStr_ = c.getCard().strength(_reversed);
//            if (iter_ > IndexConstants.FIRST_INDEX) {
//                if (curStr_ == str_) {
//                    index_++;
//                } else {
//                    index_ = IndexConstants.SECOND_INDEX;
//                }
//            }
//            if (_listener) {
//                c.addMouseListener(new ListenerCardPresidentMultiGame(this,c.getCard(), index_));
//            }
//            str_ = curStr_;
//            iter_++;
//            _panel.add(c.getPaintableLabel());
//        }
//        _panel.setSize(_panel.getPreferredSizeValue());
    }

//    @Override
//    public boolean hasCreatedServer() {
//        return hasCreatedServer;
//    }

//    @Override
//    public byte getIndexInGame() {
//        return indexInGame;
//    }

    public void endGame(ResultsPresident _res) {
        CustList<Longs> sc_ = _res.getRes().scores();
        _res.getRes().setUser(getContainerMultiContent().getIndexInGame());
        _res.getGame().setRules(getRulesPresidentMulti());
        CheckerGamePresidentWithRules.check(_res.getGame(), new StringMap<String>());
        _res.initialize(nicknames(), sc_);
        containerMultiContent.messagesEndGame(_res.getRes(),nicknames(),readCoreResourceSuit(),readResource(), readCoreResourceCards());
//        Games.setMessages(_res.getRes(),getOwner().getFrames().currentLg());
//        _res.getRes().setNicknames(nicknames());
        getPane().removeAll();
        containerMultiContent.chgEnabledMenuEndGame();
        /*Descativer aide au jeu*/
//        MenuItemUtils.setEnabledMenu(getContainerMultiContent().window().getMultiStop(),true);
//        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
//        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),false);
//        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),false);
        AbsPanel container_=getOwner().getCompoFactory().newBorder();

        /*Le nombre de parties jouees depuis le lancement du logiciel*/
//        setThreadAnime(false);

        AbsTabbedPane onglets_=getOwner().getCompoFactory().newAbsTabbedPane();
//        String lg_ = getOwner().getLanguageKey();
        setScores(_res.getRes().getScores());

        RenderedPage editor_;
        CardNatLgNamesNavigation stds_ = retrieve(FrameGeneralHelp.RESOURCES_HTML_FILES_RESULTS_PRESIDENT).attendreResultat();
        ((PresidentStandards)stds_.getBeanNatLgNames()).setDataBase(_res);
        editor_ = FrameGeneralHelp.initialize(stds_, getOwner().getFrames(), getContainerMultiContent().window().getGuardRender());
        editor_.getScroll().setPreferredSize(new MetaDimension(300,300));
        onglets_.add(file().getVal(MessagesGuiCards.MAIN_RESULTS_PAGE),editor_.getScroll());
        container_.add(onglets_, MessagesGuiFct.BORDER_LAYOUT_CENTER);
//        AbsPanel panneau_=getOwner().getCompoFactory().newPageBox();
//        containerMultiContent.endReady(this,panneau_);
//        readyToPlay = false;
//        ready = getOwner().getCompoFactory().newCustCheckBox(containerMultiContent.getMessages().getVal(WindowNetWork.READY));
//        ready.addActionListener(new ReadyEvent(this));
//        panneau_.add(ready);
//
//        AbsPanel panel_ = containerMultiContent.endNickname();
//        AbsPanel panel_ = getOwner().getCompoFactory().newGrid(0,3);
//
//        int nbCh_ = containerMultiContent.getNbChoosenPlayers();
//        for (int i = IndexConstants.FIRST_INDEX; i< nbCh_; i++) {
//            panel_.add(playersPseudos.get(i));
//            panel_.add(playersPlaces.get(i));
//            panel_.add(playersReady.get(i));
//        }
//        panneau_.add(panel_);
//        if (containerMultiContent.isHasCreatedServer()) {
//            AbsButton button_ = getOwner().getCompoFactory().newPlainButton(containerMultiContent.getMessages().getVal(MessagesGuiCards.PLAY_PRESIDENT));
//            button_.addActionListener(new PlayNextDealEvent(this));
//            panneau_.add(button_);
//        }
//        panneau_.add(getWindow().getClock());
//        panneau_.add(getWindow().getLastSavedGameDate());
//        container_.add(panneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
//
//        setContentPane(container_);
//        pack();
        //PackingWindowAfter.pack(this, true);
        containerMultiContent.sendOk(this, container_, MessagesGuiCards.PLAY_PRESIDENT);
//        PlayerActionGame ok_ = new PlayerActionGame(PlayerActionGameType.OK);
//        ok_.setPlace(containerMultiContent.getIndexInGame());
//        ok_.setLocale(lg_);
//        window().sendObject(ok_);
    }

    @Override
    public void dealNext() {
//        boolean allReady_ = window().getSockets().allReady();
//        if (!allReady_) {
//            return;
//        }
//        boolean distinct_ = Net.distinctPlaces(window().getNet(), window().getSockets());
//        if (!distinct_) {
//            return;
//        }
//        if (containerMultiContent.notAllReadyDistinct()) {
//            return;
//        }
        GamePresident game_=Net.getGames(containerMultiContent.window().getNet()).partiePresident();
        long nb_=chargerNombreDeParties(GameEnum.PRESIDENT, getOwner().getFrames(), game_.getRules().getNbStacks());
        Bytes rk_ = game_.getNewRanks();
        DealPresident deal_=getOwner().baseWindow().getIa().getPresident().empiler(nb_,game_,getOwner().getGenerator());
//        DealPresident deal_=new DealPresident(nb_,game_.empiler());
//        deal_.donneurSuivant(game_.getDeal().getDealer(), game_.getRules());
//        deal_.initDonne(game_.getRules(),getOwner().getGenerator());
        Net.getGames(containerMultiContent.window().getNet()).jouerPresident(new GamePresident(GameType.RANDOM,deal_, game_.getRules(), rk_));
        containerMultiContent.window().sendObjectPlayGame();
    }

//    @Override
//    public void changeReady() {
//        if (ComparatorBoolean.eq(readyToPlay, ready.isSelected())) {
//            return;
//        }
//        readyToPlay = !readyToPlay;
//        Ready choice_ = new Ready();
//        choice_.setIndex(noClient);
//        choice_.setReady(readyToPlay);
//        window().sendObject(choice_);
//    }

//    @Override
//    public void delegateServer() {
//        hasCreatedServer = true;
//        if (!Net.isProgressingGame(window().getNet())) {
//            AbsPanel container_ = getPane();
//            updateButton(container_);
//            AbsButton button_ = getOwner().getCompoFactory().newPlainButton(containerMultiContent.getMessages().getVal(WindowNetWork.PLAY_PRESIDENT));
//            button_.addActionListener(new PlayFirstDealEvent(this));
//            container_.add(button_);
//            pack();
//            //PackingWindowAfter.pack(this, true);
//        }
//    }

    @Override
    public void dealFirst() {
//        boolean allReady_ = window().getSockets().allReady();
//        if (!allReady_) {
//            return;
//        }
//        boolean distinct_ = Net.distinctPlaces(window().getNet(), window().getSockets());
//        if (!distinct_) {
//            return;
//        }
//        if (containerMultiContent.notAllReadyDistinct()) {
//            return;
//        }
//        HandPresident pile_;
        /*
        Chargement de la pile de cartes depuis un fichier sinon
        on la cree
        */
//        int nbStack_ = rulesPresidentMulti.getNbStacks();
//        pile_ = chargerPilePresident(nbStack_);
//        DealPresident deal_ = new DealPresident(0, pile_);
//        deal_.setRandomDealer(rulesPresidentMulti,getOwner().getGenerator());
//        deal_.initDonne(rulesPresidentMulti,getOwner().getGenerator());
        Net.getGames(containerMultiContent.window().getNet()).jouerPresident(getOwner().baseWindow().getFirstDealPresident().deal(this,rulesPresidentMulti,0));
//        Net.getGames(window().getNet()).jouerPresident(new GamePresident(
//                GameType.RANDOM, deal_, rulesPresidentMulti, new Bytes()));
        containerMultiContent.window().sendObjectPlayGame();
    }

//    @Override
//    public WindowNetWork window() {
//        return win;
//    }


    public boolean isReversedGame() {
        return reversedGame;
    }

    public HandPresident getPlayerHandPresident() {
        return playerHandPresident;
    }

    public DialogPresidentContent getDialogPresidentContent() {
        return dialogPresidentContent;
    }

    public AbsButton getSelectRules() {
        return selectRules;
    }

    public RulesPresident getRulesPresidentMulti() {
        return rulesPresidentMulti;
    }

    public void setRulesPresidentMulti(RulesPresident _r) {
        this.rulesPresidentMulti = _r;
        Net.getGames(getContainerMultiContent().window().getNet()).setRulesPresident(getRulesPresidentMulti());
    }

    public StringMap<String> readResource() {
        return MessagesCardGames.getCommonPresidentTr(readResourceAppli()).getMapping();
//        return MessagesPresidentPresident.ms().getVal(StringUtil.concat(PresidentResoucesAccess.NOM_DOSSIER, "/",getOwner().getLanguageKey(), "/", PresidentResoucesAccess.NOM_FICHIER));
    }

    @Override
    public AbsActionListenerAct guard() {
        return new AlwaysActionListenerAct();
    }

    public ContainerMultiContent getContainerMultiContent() {
        return containerMultiContent;
    }

}
