package cards.gui.containers;





import cards.belote.*;
import cards.belote.beans.BeloteStandards;
import cards.belote.enumerations.CardBelote;
import cards.consts.GameType;
import cards.consts.Role;
import cards.facade.Games;
import cards.facade.enumerations.GameEnum;
import cards.gui.*;
import cards.gui.containers.events.*;
import cards.gui.dialogs.*;
import cards.gui.events.ListenerBidBeloteMulti;
import cards.gui.events.ListenerCardBeloteMultiGame;
import cards.gui.labels.*;
import cards.gui.panels.CarpetBelote;
import cards.gui.panels.MiniCarpet;
import cards.main.CardNatLgNamesNavigation;
import cards.network.belote.DiscardPhaseBelote;
import cards.network.belote.actions.BiddingBelote;
import cards.network.belote.actions.DiscardedCardBelote;
import cards.network.belote.actions.PlayingCardBelote;
import cards.network.belote.displaying.DealtHandBelote;
import cards.network.belote.displaying.RefreshHandBelote;
import cards.network.belote.unlock.AllowBiddingBelote;
import cards.network.belote.unlock.AllowPlayingBelote;
import cards.network.common.before.*;
import cards.network.threads.Net;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.events.*;
import code.gui.images.MetaDimension;
import code.network.NetGroupFrame;
import code.network.WindowNetWork;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.util.*;
import code.util.core.*;

public final class ContainerMultiBelote extends ContainerBelote implements
        ContainerMulti,ContainerPlayableBelote {

    private final ContainerMultiContent containerMultiContent;
//    private int noClient;
//    private byte indexInGame = IndexConstants.INDEX_NOT_FOUND_ELT;
    private RulesBelote rulesBeloteMulti = new RulesBelote();
//    private boolean annonceBelote;
//    private boolean annonceBeloteRebelote;
//    private NumComboBox choiceOfPlaceForPlayingGame;
//    private AbsCustCheckBox ready;

    private HandBelote cardsInDog = new HandBelote();
    private HandBelote playerHand = new HandBelote();
//    private DealingBelote repBelote;
//    private int nbChoosenPlayers = IndexConstants.INDEX_NOT_FOUND_ELT;
//    private final boolean hasCreatedServer;
//    private boolean readyToPlay;
//    private final CustList<AbsPlainLabel> playersPseudos = new CustList<AbsPlainLabel>();
//    private final CustList<AbsPlainLabel> playersPlaces = new CustList<AbsPlainLabel>();
//    private final CustList<AbsCustCheckBox> playersReady = new CustList<AbsCustCheckBox>();
//    private RenderedPage editor;
//    private IntTreeMap< Byte> playersPlacesForGame = new IntTreeMap< Byte>();
//    private IntMap<String> playersPseudosForGame = new IntMap<String>();
    private BidBeloteSuit bidMax = new BidBeloteSuit();
    private HandBelote belReb = new HandBelote();
    private HandBelote allowed = new HandBelote();
    private DialogBeloteContent dialogBeloteContent;
    private AbsButton selectRules;
//    private boolean canBid;
//    private final AbsPlainLabel canPlayLabel = getOwner().getCompoFactory().newPlainLabel("");
//    private final WindowNetWork win;

    public ContainerMultiBelote(WindowNetWork _window, boolean _hasCreatedServer) {
        super(_window);
        containerMultiContent = new ContainerMultiContent(_hasCreatedServer, _window);
        containerMultiContent.setMessages(Games.getMulti(Games.getAppliTr(_window.getFrames().currentLg())).getMapping());
        _window.update(this);
//        win = _window;
//        hasCreatedServer = _hasCreatedServer;
        initButtonValidateDiscardBelote();
        initSlamButtonBelote();
//        if (containerMultiContent.isHasCreatedServer()) {
//            setRulesBeloteMulti(new RulesBelote(_nbPlayers));
//            Net.getGames(_window.getNet()).setRulesBelote(getRulesBeloteMulti());
//            Net.getGames(_window.getNet()).setRulesPresident(null);
//            Net.getGames(_window.getNet()).setRulesTarot(null);
//        }
    }

//    private void addButtonsForCoinche(int _pts) {
//        IdList<BidBelote> all_ = new IdList<BidBelote>();
//        for (BidBeloteSuit s: _bids) {
//
//            all_.add(s.getBid());
//        }
//        addButtonsForCoinche(this, _pts,all_);
//        addButtonsForCoinche(this, _pts,getRulesBeloteMulti().getListeEncheresAutorisees());
//        Ints points_ = RulesBelote.getPoints();
//        int size_ = points_.size();
//        setPanneauBoutonsJeuPoints(getOwner().getCompoFactory().newGrid());
//        getPointsButtons().clear();
//        TranslationsLg lg_ = getOwner().getFrames().currentLg();
//        for (int i = 0; i < size_; i++) {
//            int p_ = points_.get(i);
//            LabelPoints label_ = new LabelPoints(p_, getOwner().getCompoFactory());
//            label_.setEnabledLabel(_pts < p_);
//            label_.setToolTipText(Long.toString(p_));
//            label_.getButton().addActionListener(new SelectPointsEvent(this, p_));
//            getPointsButtons().add(label_);
//            getPanneauBoutonsJeuPoints().add(label_.getButton(),WindowCardsCore.ctsRem(getWindow().getCompoFactory(),(i+1)%3==0));
//        }
//        getPanneauBoutonsJeu().add(getPanneauBoutonsJeuPoints());
//        setBidOk(getOwner().getCompoFactory().newPlainButton(file().getVal(MessagesGuiCards.MAIN_OK)));
//        getBidOk().setEnabled(false);
//        getBidOk().addActionListener(new BidEvent(this));
//        AbsPanel panel_ = getOwner().getCompoFactory().newGrid();
//        AbsPanel panel_ = getOwner().getCompoFactory().newPageBox();
//        AbsPanel panelSuits_ = getOwner().getCompoFactory().newLineBox();
//        getBidsButtons().clear();
//        for (Suit s: Suit.couleursOrdinaires()) {
//            SuitLabel suitLabel_ = new SuitLabel(getOwner().getCompoFactory());
//            BidBeloteSuit bid_ = new BidBeloteSuit();
//            bid_.setSuit(s);
//            bid_.setBid(BidBelote.SUIT);
//            suitLabel_.setSuit(bid_, lg_);
//
//            suitLabel_.addMouseListener(new SelectSuitEvent(this,bid_));
//            suitLabel_.setPreferredSize(new MetaDimension(20,20));
//            getBidsButtons().add(suitLabel_);
//            panelSuits_.add(suitLabel_.getPaintableLabel());
//        }
//        panel_.add(panelSuits_);
//        AbsPanel panelBids_ = getOwner().getCompoFactory().newLineBox();

//        CustList<BidBeloteSuit> bidsAll_ = GameBeloteBid.baseBidsDealAll(all_);
//        for (BidBeloteSuit b: bidsAll_) {
//            SuitLabel suitLabel_ = new SuitLabel(getOwner().getCompoFactory());
//            suitLabel_.setSuit(b, lg_);
//            suitLabel_.addMouseListener(new SelectSuitEvent(this,b));
//            panel_.add(suitLabel_.getPaintableLabel(),WindowCardsCore.ctsRem(getWindow().getCompoFactory(),(panel_.getComponentCount()+1)%4==0 || (panel_.getComponentCount()+1)%bidsAll_.size()==0));
//            panel_.add(suitLabel_.getPaintableLabel(), ContainerSingleBelote.ctsRem(panel_, bidsAll_, getWindow().getCompoFactory()));
//            getBidsButtons().add(suitLabel_);
//            AbsMetaLabelCard.paintCard(getOwner().getImageFactory(), suitLabel_);
//            getBids().add(b);
//        }

//        for (BidBelote b: BidBelote.getNonZeroBids()) {
//            if (b.getCouleurDominante()) {
//                continue;
//            }
//            boolean present_ = false;
//            for (BidBeloteSuit allowedBid_: _bids) {
//                if (allowedBid_.getBid() == b) {
//                    present_ = true;
//                    break;
//                }
//            }
//            if (!present_) {
//                continue;
//            }
//            SuitLabel suitLabel_ = new SuitLabel(getOwner().getCompoFactory());
//            BidBeloteSuit bid_ = new BidBeloteSuit();
//            bid_.setBid(b);
//            suitLabel_.setSuit(bid_, lg_);
//            suitLabel_.addMouseListener(new SelectSuitEvent(this,bid_));
//
//            panelBids_.add(suitLabel_.getPaintableLabel());
//            getBidsButtons().add(suitLabel_);
//        }
//        panel_.add(panelBids_);
//        AbsPanel panelOk_ = getOwner().getCompoFactory().newLineBox();
//        AbsButton buttonSuit_ = getOwner().getCompoFactory().newPlainButton(Games.toString(BidBelote.FOLD,lg_));
//        buttonSuit_.addActionListener(new FoldEvent(this));
//        panelOk_.add(buttonSuit_);
//        panel_.add(buttonSuit_,WindowCardsCore.cts(getWindow().getCompoFactory()));
//        panelOk_.add(getBidOk());
//        panel_.add(panelOk_);
//        panel_.add(getBidOk(),WindowCardsCore.cts(getWindow().getCompoFactory()));
//        getPanneauBoutonsJeu().add(panel_);
//    }

    @Override
    public void bid() {
        getPanneauBoutonsJeu().removeAll();
        pack();
//        if (!isCanBid()) {
//            return;
//        }
//        setCanBid(false);
        BidBeloteSuit bidLoc_ = new BidBeloteSuit();
        bidLoc_.setSuit(getSuit());
        bidLoc_.setBid(getBidType());
        bidLoc_.setPoints(getPts());
        BiddingBelote bid_ = new BiddingBelote();
        bid_.setPlace(containerMultiContent.getIndexInGame());
        bid_.setBidBelote(bidLoc_);
//        String lg_ = getOwner().getLanguageKey();
//        bid_.setLocale(lg_);
        getContainerMultiContent().window().sendObject(bid_);
    }

    @Override
    public void fold() {
        getPanneauBoutonsJeu().removeAll();
        pack();
//        if (!isCanBid()) {
//            return;
//        }
//        setCanBid(false);
        BidBeloteSuit bidLoc_ = new BidBeloteSuit();
        BiddingBelote bid_ = new BiddingBelote();
        bid_.setPlace(containerMultiContent.getIndexInGame());
        bid_.setBidBelote(bidLoc_);
//        String lg_ = getOwner().getLanguageKey();
//        bid_.setLocale(lg_);
        getContainerMultiContent().window().sendObject(bid_);
    }

//    private void ajouterBoutonContratBeloteMulti(String _texte,
//            BidBeloteSuit _action, boolean _enabled) {
//        AbsPanel panneau_ = getPanneauBoutonsJeu();
//        AbsButton bouton_ = getOwner().getCompoFactory().newPlainButton(_texte);
//        bouton_.setEnabled(_enabled);
//        if (!_enabled) {
//            TranslationsLg lg_ = getOwner().getFrames().currentLg();
//            String mes_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CANT_BID), Games.toString(_action,lg_));
//            bouton_.setToolTipText(mes_);
//        }
//        bouton_.addActionListener(new EcouteurBoutonContratBeloteMulti(_action));
//        bouton_.addActionListener(new ListenerBidBeloteMulti(this,_action));
//        panneau_.add(bouton_);
//    }

    @Override
    public void updateFirst(IndexOfArrivingCards _players) {
        getPane().removeAll();
        MenuItemUtils.setEnabledMenu(getContainerMultiContent().window().getMultiStop(),true);
        MenuItemUtils.setEnabledMenu(getContainerMultiContent().window().getTricksHands(),true);
        MenuItemUtils.setEnabledMenu(getContainerMultiContent().window().getTeams(),true);
//        MenuItemUtils.setEnabledMenu(getLoad(),false);
        rulesBeloteMulti = _players.getRulesBelote();
        Net.getGames(getContainerMultiContent().window().getNet()).setRulesBelote(getRulesBeloteMulti());
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

        rulesBeloteMulti.getCommon().setGeneral(WindowNetWork.readCoreResourceMix(this));
        rulesBeloteMulti.getCommon().setSpecific(readResource());
        CardNatLgNamesNavigation stds_ = retrieve(FileConst.RESOURCES_HTML_FILES_RULES_BELOTE).attendreResultat();
        ((BeloteStandards)stds_.getBeanNatLgNames()).setDataBaseRules(rulesBeloteMulti);
        containerMultiContent.setEditor(FrameGeneralHelp.initialize(stds_, getOwner().getFrames(), containerMultiContent.window().getGuardRender()));

        containerMultiContent.getEditor().getScroll().setPreferredSize(new MetaDimension(300,400));
        container_.add(containerMultiContent.getEditor().getScroll());

        getContainerMultiContent().updateAfter(this,_players,MessagesGuiCards.PLAY_BELOTE, container_);
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
//            AbsButton button_ = getOwner().getCompoFactory().newPlainButton(containerMultiContent.getMessages().getVal(MessagesGuiCards.PLAY_BELOTE));
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
        dialogBeloteContent = new DialogBeloteContent(getOwner().getFrames());
        AbsTabbedPane jt_ = dialogBeloteContent.initJt(getWindow(),false, containerMultiContent.getNbChoosenPlayers(), null);
        AbsPanel border_ = getOwner().getCompoFactory().newBorder();
        border_.add(jt_, GuiConstants.BORDER_LAYOUT_CENTER);
        selectRules = getOwner().getCompoFactory().newPlainButton(containerMultiContent.getMessages().getVal(MessagesGuiCards.SELECT_RULES));
        selectRules.addActionListener(new AfterValidateRulesBeloteMulti(dialogBeloteContent,this));
        border_.add(selectRules,GuiConstants.BORDER_LAYOUT_SOUTH);
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

    public void updateRules(RulesBelote _rules) {
        rulesBeloteMulti = _rules;
        Net.getGames(getContainerMultiContent().window().getNet()).setRulesBelote(getRulesBeloteMulti());
        rulesBeloteMulti.getCommon().setGeneral(WindowNetWork.readCoreResourceMix(this));
        rulesBeloteMulti.getCommon().setSpecific(readResource());
        CardNatLgNamesNavigation stds_ = retrieve(FileConst.RESOURCES_HTML_FILES_RULES_BELOTE).attendreResultat();
        ((BeloteStandards)stds_.getBeanNatLgNames()).setDataBaseRules(rulesBeloteMulti);
        FrameGeneralHelp.initialize(stds_, containerMultiContent.getEditor());
    }

    public void updateForBeginningGame(DealtHandBelote _hand) {
//        repBelote = _hand.getRep();
        placerIhmBeloteMulti(_hand.getDeck(), _hand.getDealer());

        playerHand = _hand.getCards();
        playerHand.trier(getDisplayingBelote().getDisplaying().getSuits(),
                getDisplayingBelote().getDisplaying().isDecreasing(),
                getDisplayingBelote().getOrderBeforeBids());
        HandBelote h_ = new HandBelote();
        h_.ajouterCartes(playerHand);
        setTakerCardsDiscard(h_);
//        setCarteEntree(false);
//        setCarteSortie(false);
//        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        /* On place les cartes de l'utilisateur */
        updateCardsInPanelBeloteMulti(false);
//        if (!repBelote.withBidPointsForAllPlayers()) {
//            for (BidBeloteSuit b : _hand.getAllowedBids()) {
//                ajouterBoutonContratBeloteMulti(Games.toString(b,lg_), b);
//            }
//        } else {
//            addButtonsForCoinche(_hand.getPoints(), _hand.getAllowedBids());
//        }
        //PackingWindowAfter.pack(this, true);
        pack();
        getContainerMultiContent().sendDealt();
//        PlayerActionGame dealt_ = new PlayerActionGame(PlayerActionGameType.DEALT);
//        dealt_.setPlace(containerMultiContent.getIndexInGame());
//        dealt_.setLocale(lg_.getKey());
//        window().sendObject(dealt_);
    }

//    public void canBid() {
//        setCanBid(true);
//    }

    public void canBidBelote(AllowBiddingBelote _bids) {
        containerMultiContent.getCanPlayLabel().setText(containerMultiContent.getMessages().getVal(MessagesGuiCards.CAN_PLAY));
//        setCanBid(true);
        getPanneauBoutonsJeu().removeAll();
        bidButtons(this,getRulesBeloteMulti(),_bids.getBid().getPoints(),_bids.getBid(),_bids.getBids());
//        TranslationsLg lg_ = getOwner().getFrames().currentLg();
//        if (!repBelote.withBidPointsForAllPlayers()) {
//            for (BidBeloteSuit b : _bids.getBids()) {
//                ajouterBoutonContratBelote(this,Games.toString(b, lg_),b,b.estDemandable(_bids.getBid()));
//                ajouterBoutonContratBeloteMulti(Games.toString(b,lg_), b, b.estDemandable(_bids.getBid()));
//            }
//        } else {
//            addButtonsForCoinche(this, _bids.getBid().getPoints(),getRulesBeloteMulti().getListeEncheresAutorisees());
//            addButtonsForCoinche(_bids.getBid().getPoints());
//        }
        //getPanneauBoutonsJeu().validate();
        pack();
        //PackingWindowAfter.pack(this, true);
    }

//    public void afterBid() {
//        canPlayLabel.setText(EMPTY_STRING);
//        setCanBid(false);
//    }

//    public void errorForBidding(ErrorBiddingBelote _error) {
//        TranslationsLg lg_ = getOwner().getFrames().currentLg();
//        String mes_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CANT_BID), Games.toString(_error.getBid(),lg_));
////        JOptionPane.showMessageDialog(getOwner(),mes_,
////                getMessages().getVal(MainWindow.CANT_BID_TITLE), JOptionPane.INFORMATION_MESSAGE);
//        getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(), mes_, containerMultiContent.getMessages().getVal(WindowNetWork.CANT_BID_TITLE),
//                GuiConstants.ERROR_MESSAGE);
//    }

    public void displayLastBid(BiddingBelote _bid) {
        containerMultiContent.getCanPlayLabel().setText(EMPTY_STRING);
        if (_bid.getBidBelote().jouerDonne()) {
            bidMax = _bid.getBidBelote();
        }
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        getEvents().append(StringUtil.concat(containerMultiContent.getPseudoByPlace(_bid.getPlace()), INTRODUCTION_PTS,
                Games.toString(_bid.getBidBelote(),lg_), RETURN_LINE));
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().validate();
        NetGroupFrame.trySendString(Net.exportDoneBidding(containerMultiContent.getIndexInGame()),getContainerMultiContent().window().getSocket());
    }

    public void voirEcart(DiscardPhaseBelote _dog) {
//        String lg_ = getOwner().getLanguageKey();
        getPanneauBoutonsJeu().removeAll();
        //getPanneauBoutonsJeu().validate();
        byte relative_ = containerMultiContent.relative(_dog.getDiscardPhase().getTakerIndex());
        getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, relative_);
        cardsInDog = _dog.getDiscard();
        if (_dog.getDiscardPhase().getTaker() == DiscardPhaseBelote.TAKER_HUM_WRITE) {
            //take the cards
            addButtonTakeDogCardsBeloteMulti(file().getVal(MessagesGuiCards.MAIN_TAKE_CARDS), true);
            containerMultiContent.getCanPlayLabel().setText(containerMultiContent.getMessages().getVal(MessagesGuiCards.CAN_PLAY));
        }
        new ContainerSingleWithDiscardUtil<CardBelote>(this).updateCardsInPanels(false);
        pack();
        //PackingWindowAfter.pack(this, true);
//        if (_dog.getDiscardPhase().getTaker() == DiscardPhaseBelote.TAKER_NO) {
//            PlayerActionGame show_ = new PlayerActionGame(PlayerActionGameType.SHOW_DOG);
//            show_.setPlace(indexInGame);
//            show_.setLocale(lg_);
//            window().sendObject(show_);
//        }
    }

    @Override
    public void prendreCartesChien() {
        HandBelote allCards_ = new HandBelote();
        allCards_.ajouterCartes(playerHand);
        allCards_.ajouterCartes(cardsInDog);
        tapisBelote().retirerCartes();
        cardsInDog.supprimerCartes();
        setTakerCardsDiscard(allCards_);
        getTakerCardsDiscard().trier(getDisplayingBelote(), bidMax);
        new ContainerSingleWithDiscardUtil<CardBelote>(this).updateCardsInPanels(true);
        getPanneauBoutonsJeu().removeAll();
        getValidateDiscard().setEnabled(false);
        getPanneauBoutonsJeu().add(getValidateDiscard());
        getSlamButton().setEnabled(false);
        getPanneauBoutonsJeu().add(getSlamButton());
        pack();
    }

    @Override
    public void validateDiscard() {
        updateCardsInPanelBeloteMulti(false);
        containerMultiContent.getCanPlayLabel().setText(EMPTY_STRING);
        refreshPlayerHand();
//        if (!getTakerCardsDiscard().estVide()) {
//            playerHand = getTakerCardsDiscard();
//            setChienMulti(false);
//        }
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().validate();
        NetGroupFrame.trySendString(Net.exportDiscardSimple(),getContainerMultiContent().window().getSocket());
    }
    private void initButtonValidateDiscardBelote() {
        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(file().getVal(MessagesGuiCards.MAIN_GO_CARD_GAME));
        bouton_.addActionListener(new ValidateDiscardEvent(this));
        setValidateDiscard(bouton_);
    }
    private void initSlamButtonBelote() {
        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(Integer.toString(RulesBelote.MOST));
        bouton_.addActionListener(new SlamEvent(this));
        setSlamButton(bouton_);
    }
    private void addButtonTakeDogCardsBeloteMulti(String _texte, boolean _apte) {
        AbsPanel panneau_=getPanneauBoutonsJeu();
        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new TakeDiscardEvent(this));
        bouton_.setEnabled(_apte);
        setTakeCardDiscard(bouton_);
        panneau_.add(bouton_);
    }

    @Override
    public void annonceChelem() {
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().validate();
        pack();
        refreshPlayerHand();
        NetGroupFrame.trySendString(Net.exportDiscardSlam(),getContainerMultiContent().window().getSocket());
    }

    private void refreshPlayerHand() {
        playerHand = getTakerCardsDiscard();
//        if (!getTakerCardsDiscard().estVide()) {
//            playerHand = getTakerCardsDiscard();
//        }
    }

    @Override
    public void discard(CardBelote _t) {
        getTakerCardsDiscard().jouer(_t);
        getTakerCardsDiscard().trier(getDisplayingBelote(),bidMax);
        cardsInDog.ajouter(_t);
    }

    @Override
    public void restore(CardBelote _t) {
        getTakerCardsDiscard().ajouter(_t);
        getTakerCardsDiscard().trier(getDisplayingBelote(),bidMax);
        cardsInDog.jouer(_t);
    }

    @Override
    public void afterHands(CardBelote _c) {
        boolean chienFait_ = cardsInDog.total()== rulesBeloteMulti.getDealing().getDiscarded();
        updateButtons(chienFait_);
        pack();
        DiscardedCardBelote discard_ = new DiscardedCardBelote();
        discard_.setCard(_c);
        discard_.setPlace(getContainerMultiContent().getIndexInGame());
//        discard_.setLocale("");
        getContainerMultiContent().window().sendObjectBelote(discard_);
    }

    private void updateButtons(boolean _chienFait) {
        getValidateDiscard().setEnabled(_chienFait);
//        boolean slam_ = _chienFait;// && partie_.getContrat() != BidBelote.SLAM;
        getSlamButton().setEnabled(_chienFait);
    }
    @Override
    public IdList<CardBelote> hand() {
        return getTakerCardsDiscard().getCards();
    }

    @Override
    public IdList<CardBelote> discarded() {
        return cardsInDog.getCards();
    }

    @Override
    public String errMessage(IdList<CardBelote> _must, CardBelote _t) {
        return "";
    }

    public void canPlayBelote(AllowPlayingBelote _declaration) {
        containerMultiContent.getCanPlayLabel().setText(containerMultiContent.getMessages().getVal(MessagesGuiCards.CAN_PLAY));
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),true);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),true);
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
//        annonceBeloteRebelote = false;
        AbsCustCheckBox belReb_ = getBeloteRebelote();
        belReb_.setSelected(false);
        allowed = _declaration.getCards();
        updateCardsInPanelBeloteMulti(true);
        if (_declaration.isPossibleBeloteRebelote()) {
            AbsPanel panneau_ = getPanneauBoutonsJeu();
            belReb_.setText(Games.toStringBeloteReb(lg_));
            belReb_.setEnabled(_declaration.isAllowedBeloteRebelote());
//            belReb_.addActionListener(new ChangeBeloteRebeloteEvent(this));
            panneau_.add(belReb_);
            belReb = _declaration.getBelReb();
        } else {
            belReb = new HandBelote();
        }
        AbsCustCheckBox beloteDeclare_ = getBeloteDeclare();
        beloteDeclare_.setSelected(false);
        if (!_declaration.isFirstRoundPlaying()) {
//            annonceBelote = false;
            pack();
            return;
        }
        if (_declaration.getCurrentBid().getPoints() == RulesBelote.MOST) {
            byte relative_ = containerMultiContent.relative(_declaration.getTakerIndex());
            getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, relative_);
            getEvents().append(StringUtil.concat(StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_DECLARING_SLAM), containerMultiContent.getPseudoByPlace(_declaration.getTakerIndex())),RETURN_LINE));
//            getEvents().append(StringUtil.concat(containerMultiContent.getPseudoByPlace(_declaration.getTakerIndex()),INTRODUCTION_PTS, WindowNetWork.SLAM,RETURN_LINE));
        }

        DeclareHandBelote annonceMain_ = _declaration.getDeclaration();
        declare(annonceMain_);
//        if (annonceMain_.getDeclare() != DeclaresBelote.UNDEFINED) {
//            annonceBelote = false;
//            AbsPanel panneau_ = getPanneauBoutonsJeu();
//            AbsCustCheckBox caseCoche_ = getOwner().getCompoFactory().newCustCheckBox(StringUtil.concat(Games.toString(annonceMain_.getDeclare(),lg_),
//                    INTRODUCTION_PTS, Games.toString(annonceMain_.getHand(),lg_)));
//            beloteDeclare_.setText(StringUtil.concat(Games.toString(annonceMain_.getDeclare(),lg_),
//                    INTRODUCTION_PTS, Games.toString(annonceMain_.getHand(),lg_)));
//            caseCoche_.addActionListener(new ChangeBeloteDeclareEvent(this));
//            panneau_.add(beloteDeclare_);
//        }
        byte relative_ = containerMultiContent.relative(_declaration.getTakerIndex());
        getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, relative_);
        pack();
    }

    public void displayPlayedCard(PlayingCardBelote _card) {
        containerMultiContent.getCanPlayLabel().setText(EMPTY_STRING);
        byte relative_ = containerMultiContent.relative(_card.getPlace());
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        tapisBelote().setCarteBelote(getWindow().getImageFactory(), lg_, relative_, _card.getPlayedCard());

        String pseudo_ = containerMultiContent.getPseudoByPlace(_card.getPlace());
        if (_card.isDeclaringBeloteRebelote()) {
            ajouterTexteDansZone(StringUtil.concat(pseudo_, INTRODUCTION_PTS, Games.toStringBeloteReb(lg_), RETURN_LINE));
        }
        if (_card.isDeclaring()) {
            firstRound(relative_,pseudo_,bidMax,_card.getDeclare(),new DirectCardsCallEvents());
//            _card.getDeclare().getHand().trier(getDisplayingBelote(),bidMax);
//            if (bidMax.getCouleurDominante()) {
//                _card.getDeclare()
//                        .getHand()
//                        .trier(getDisplayingBelote().getDisplaying().getSuits(),
//                                getDisplayingBelote().getDisplaying().isDecreasing(),
//                                bidMax.getSuit());
//            } else {
//                _card.getDeclare()
//                        .getHand()
//                        .trier(getDisplayingBelote().getDisplaying().getSuits(),
//                                getDisplayingBelote().getDisplaying().isDecreasing(),
//                                bidMax.getOrdre());
//            }
//            ajouterTexteDansZone(StringUtil.concat(pseudo_, INTRODUCTION_PTS, Games.toString(_card.getDeclare().getDeclare(),lg_),
//                    RETURN_LINE));
//            if (!_card.getDeclare().getHand().estVide()) {
//                getHandfuls().getVal(relative_).setText(
//                        Games.toString(_card.getDeclare().getDeclare(),lg_));
//            }
//            AbsPanel panelToSet_ = getDeclaredHandfuls().getVal(relative_);
//            panelToSet_.removeAll();
//            for (CardBelote c: _card.getDeclare().getHand()) {
//                MiniCard carte_ = new MiniCard(lg_, getOwner(),c.getId().nb());
//                panelToSet_.add(carte_.getPaintableLabel());
//                AbsMetaLabelCard.paintCard(getWindow().getImageFactory(),carte_);
//            }
//            panelToSet_.setSize(panelToSet_.getPreferredSizeValue());
            pack();
        }
        relative_ = containerMultiContent.relative(_card.getTakerIndex());
        getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, relative_);
        //PackingWindowAfter.pack(this, true);
        pack();
        NetGroupFrame.trySendString(Net.exportDonePlaying(containerMultiContent.getIndexInGame()),getContainerMultiContent().window().getSocket());
    }

//    public void errorPlayingCard(ErrorPlayingBelote _error) {
//        updateCardsInPanelBeloteMulti( true);
//        TranslationsLg lg_ = getOwner().getFrames().currentLg();
//        if (!_error.getCards().estVide()) {
////            AbsPanel panneau_ = getOwner().getCompoFactory().newLineBox();
////            HandBelote cartesBeloteRebelote_ = _error.getCards();
////            for (GraphicBeloteCard c: getGraphicCards(getWindow(),lg_, cartesBeloteRebelote_.getCards())) {
////                panneau_.add(c.getPaintableLabel());
////            }
//            ajouterTexteDansZone(file().getVal(MessagesGuiCards.MAIN_HAVE_TO_PLAY)+RETURN_LINE);
////            getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(), panneau_,
////                    getMessages().getVal(WindowNetWork.HAVE_TO_PLAY),
////                    GuiConstants.ERROR_MESSAGE);
//
//            return;
//        }
//        String mes_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CANT_PLAY_CARD), Games.toString(_error.getCard(),lg_));
//        String mesReason_ = StringUtil.simpleStringsFormat(containerMultiContent.getMessages().getVal(WindowNetWork.REASON), _error.getReason());
//        getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(),
//                StringUtil.concat(mes_, RETURN_LINE, mesReason_),
//                containerMultiContent.getMessages().getVal(WindowNetWork.CANT_PLAY_CARD_TITLE),
//                GuiConstants.ERROR_MESSAGE);
//    }

    public void refreshHand(RefreshHandBelote _cards) {
        playerHand.supprimerCartes();
        playerHand.ajouterCartes(_cards.getRefreshedHand());
        playerHand.trier(getDisplayingBelote(),bidMax);
//        if (bidMax.getCouleurDominante()) {
//            playerHandBelote.trier(getDisplayingBelote().getDisplaying().getSuits(),
//                    getDisplayingBelote().getDisplaying().isDecreasing(), bidMax.getSuit());
//        } else {
//            playerHandBelote.trier(getDisplayingBelote().getDisplaying().getSuits(),
//                    getDisplayingBelote().getDisplaying().isDecreasing(), bidMax.getOrdre());
//        }

//        setCarteEntree(false);
//        setCarteSortie(false);
        byte relative_ = containerMultiContent.relative(_cards.getTakerIndex());
        getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, relative_);

        /* On place les cartes de l'utilisateur */

        updateCardsInPanelBeloteMulti( false);
        pack();
        NetGroupFrame.trySendString(Net.exportCompletedHandBelote(containerMultiContent.getIndexInGame()),getContainerMultiContent().window().getSocket());
    }

    public void refreshHand(PlayingCardBelote _card) {
        playerHand.jouer(_card.getPlayedCard());
        playerHand.trier(getDisplayingBelote(),bidMax);
//        if (bidMax.getCouleurDominante()) {
//            playerHandBelote.trier(getDisplayingBelote().getDisplaying().getSuits(),
//                    getDisplayingBelote().getDisplaying().isDecreasing(), bidMax.getSuit());
//        } else {
//            playerHandBelote.trier(getDisplayingBelote().getDisplaying().getSuits(),
//                    getDisplayingBelote().getDisplaying().isDecreasing(), bidMax.getOrdre());
//        }
        getPanneauBoutonsJeu().removeAll();
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),false);
        /* On place les cartes de l'utilisateur */
        updateCardsInPanelBeloteMulti(false);
        pack();
        //PackingWindowAfter.pack(this, true);
        PlayingCardBelote ref_ = new PlayingCardBelote();
        ref_.setRefreshing(true);
        ref_.setPlayedCard(_card.getPlayedCard());
        ref_.setDeclaring(_card.isDeclaring());
        ref_.setDeclaringBeloteRebelote(_card.isDeclaringBeloteRebelote());
        ref_.setDeclare(_card.getDeclare());
        ref_.setPlace(containerMultiContent.getIndexInGame());
//        String lg_ = getOwner().getLanguageKey();
//        ref_.setLocale(lg_);
        getContainerMultiContent().window().sendObject(ref_);

    }

    @Override
    public void pauseBetweenTrick() {
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        tapisBelote().setCartesBeloteJeu(getWindow().getImageFactory(), (byte) containerMultiContent.getNbChoosenPlayers(), lg_);
        //pack();
        containerMultiContent.sendPause();
//        PlayerActionGame d_ = new PlayerActionGame(PlayerActionGameType.DONE_PAUSE);
//        d_.setPlace(containerMultiContent.getIndexInGame());
//        d_.setLocale(lg_.getKey());
//        window().sendObject(d_);
    }

    public void showTeams() {
//        if (!isCanPlay()) {
//            return;
//        }
//        String lg_ = getOwner().getLanguageKey();
        containerMultiContent.showTeams();
//        PlayerActionGame select_ = new PlayerActionGame(PlayerActionGameType.SELECT_TEAMS);
//        select_.setPlace(containerMultiContent.getIndexInGame());
//        select_.setLocale(lg_);
//        window().sendObject(select_);
    }

    @Override
    public void showTricksHands() {
//        if (!isCanPlay()) {
//            return;
//        }
//        String lg_ = getOwner().getLanguageKey();
        containerMultiContent.showTricksHands();
//        PlayerActionGame select_ = new PlayerActionGame(PlayerActionGameType.SELECT_TRICKS_HANDS);
//        select_.setPlace(containerMultiContent.getIndexInGame());
//        select_.setLocale(lg_);
//        window().sendObject(select_);
    }

    public void showTricksHands(TricksHandsBelote _tricks) {
//        ByteTreeMap< String> pseudos_ = new ByteTreeMap< String>();
//        byte p_ = 0;
//        for (String s : pseudosBelote((byte) containerMultiContent.getNbChoosenPlayers())) {
//            pseudos_.put(p_, s);
//            p_++;
//        }
//        for (byte p : containerMultiContent.getPlayersPlacesForGame().values()) {
//            pseudos_.put(p, containerMultiContent.getPseudoByPlace(p));
//        }
//        StringList list_ = new StringList(nicknames().values());
        WindowNetWork ow_ = containerMultiContent.window();
        DialogTricksBelote.setDialogTricksBelote(
                file().getVal(MessagesGuiCards.MAIN_HANDS_TRICKS_BELOTE), ow_);
        DialogTricksBelote.init(_tricks, rulesBeloteMulti, nicknames(),
                getDisplayingBelote(), ow_);
    }

    public void showTeams(TeamsPlayers _teams) {
//        ByteTreeMap< String> pseudos_ = new ByteTreeMap< String>();
//        byte p_ = 0;
//        for (String s : pseudosBelote((byte) containerMultiContent.getNbChoosenPlayers())) {
//            pseudos_.put(p_, s);
//            p_++;
//        }
//        for (byte p : containerMultiContent.getPlayersPlacesForGame().values()) {
//            pseudos_.put(p, containerMultiContent.getPseudoByPlace(p));
//        }
//        StringList list_ = new StringList(nicknames().values());
        DialogTeamsPlayers.initDialogTeamsPlayers(getOwner());
        DialogTeamsPlayers.setDialogTeamsPlayers(nicknames(), _teams, getOwner().getDialogTeamsPlayers(), getOwner().getCompoFactory());
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

    private void placerIhmBeloteMulti(HandBelote _cardsOnDeck, byte _beginPlace) {
        getPane().removeAll();
        MenuItemUtils.setEnabledMenu(getContainerMultiContent().window().getMultiStop(),false);
        AbsPanel container_ = getOwner().getCompoFactory().newBorder();
        container_.add(getOwner().getCompoFactory().newPlainLabel(helpMenuTip()), GuiConstants.BORDER_LAYOUT_NORTH);
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        StringList list_ = nicknames();
        setMini(MiniCarpet.newCarpet(getWindow().getImageFactory(), containerMultiContent.getNbChoosenPlayers(), getDisplayingBelote().getDisplaying().isClockwise(), list_, getOwner().getCompoFactory()));
        CarpetBelote tapis_ = CarpetBelote.initTapisBelote(lg_, containerMultiContent.getNbChoosenPlayers(), getDisplayingBelote().getDisplaying().isClockwise(),
                ContainerSingleBelote.displayedCards(rulesBeloteMulti), getOwner().getFrames());
        getTapis().setTapisBelote(tapis_);
        container_.add(tapis_.getContainer(), GuiConstants.BORDER_LAYOUT_CENTER);
//        AbsPanel panneau_ = panelHand();
//        AbsPanel panneau_ = getOwner().getCompoFactory().newLineBox();
//        panneau_.setBackground(GuiConstants.BLUE);
//        setPanelHand(panneau_);
//        panelHand(panneau_);
        container_.add(panelHand(), GuiConstants.BORDER_LAYOUT_SOUTH);
        AbsPanel panneau2_ = getOwner().getCompoFactory().newPageBox();
//        setEvents(getOwner().getCompoFactory().newTextArea(EMPTY, 8, 30));
        panneau2_.add(events());
        byte relative_ = containerMultiContent.relative(_beginPlace);
        getEvents().append(StringUtil.concat(containerMultiContent.getMessages().getVal(MessagesGuiCards.PLAYER_HAVING_TO_PLAY), list_.get(relative_), RETURN_LINE));
//        getEvents().append(StringUtil.concat(containerMultiContent.getMessages().getVal(MessagesGuiCards.PLAYER_HAVING_TO_PLAY), pseudos_.getVal(relative_), RETURN_LINE));
//        getEvents().setEditable(false);
//        panneau2_.add(getOwner().getCompoFactory().newAbsScrollPane(getEvents()));
        panneau2_.add(getMiniPanel());
        setHandfuls(new ByteMap<AbsPlainLabel>());
        setDeclaredHandfuls(new ByteMap<AbsPanel>());
        int nbCh_ = containerMultiContent.getNbChoosenPlayers();
//        AbsPanel declaredHandfuls_ = buildDeclHands(nbCh_,list_, getOwner().getFrames());
//        AbsPanel declaredHandfuls_ = getOwner().getCompoFactory().newPageBox();
//        for (byte i = IndexConstants.FIRST_INDEX; i < nbCh_; i++) {
//            AbsPanel declaredHandfulGroup_ = getOwner().getCompoFactory().newLineBox();
//            AbsPlainLabel lab_ = getOwner().getCompoFactory().newPlainLabel(pseudos_.getVal(i));
//            declaredHandfulGroup_.add(lab_);
//            AbsPlainLabel handful_ = getOwner().getCompoFactory().newPlainLabel(EMPTY_STRING);
//            declaredHandfulGroup_.add(handful_);
//            getHandfuls().put(i, handful_);
//            AbsPanel declaredHandful_ = getOwner().getCompoFactory().newLineBox();
//            declaredHandfulGroup_.add(declaredHandful_);
//            getDeclaredHandfuls().put(i, declaredHandful_);
//            declaredHandfuls_.add(declaredHandfulGroup_);
//        }
//        AbsScrollPane scroll_ = getOwner().getCompoFactory().newAbsScrollPane(buildDeclHands(nbCh_,list_, getOwner().getFrames()));
//        panneau2_.add(scroll_);
        panel(nbCh_,list_, container_, panneau2_);
//        AbsPanel sousPanneau_ = getOwner().getCompoFactory().newPageBox();
//        setPanneauBoutonsJeu(sousPanneau_);
//        panneau2_.add(sousPanneau_);
//        container_.add(panneau2_, GuiConstants.BORDER_LAYOUT_EAST);
        tapisBelote().setTalonBelote(getWindow(),lg_,_cardsOnDeck, rulesBeloteMulti);
        AbsPanel panel_ = getOwner().getCompoFactory().newPageBox();
        panel_.add(getOwner().getCompoFactory().newAbsScrollPane(container_));
        containerMultiContent.getCanPlayLabel().setText(EMPTY_STRING);
        panel_.add(containerMultiContent.getCanPlayLabel());
//        readyToPlay = false;
//        ready = getOwner().getCompoFactory().newCustCheckBox(containerMultiContent.getMessages().getVal(WindowNetWork.READY));
//        ready.addActionListener(new ReadyEvent(this));
//        panel_.add(ready);
        containerMultiContent.endReady(this,panel_);
        panel_.add(getWindow().getClock());
        panel_.add(getWindow().getLastSavedGameDate());
        setContentPane(panel_);
    }

    private StringList nicknames() {
        return new StringList(containerMultiContent.nicknames(pseudosBelote((byte) containerMultiContent.getNbChoosenPlayers())).values());
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
    public void updateCardsInPanelBeloteMulti(boolean _listener) {
        updateCardsInPanelBeloteMulti(getPanelHand(), playerHand, _listener);
    }

    public void updateCardsInPanelBeloteMulti(AbsPanel _panel, HandBelote _hand, boolean _listener) {
        _panel.removeAll();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
//        GameBelote game_ = partieBelote();
//        HandBelote autorise_;
//        if (_listener) {
//            autorise_ = allowed;
//        } else {
//            autorise_ = new HandBelote();
//        }
        for (GraphicCard<CardBelote> c: getGraphicCards(getWindow(),lg_,_hand.getCards())) {
            //c.addMouseListener(new ListenerCardBeloteSingleGame(this,c.getCard()));
            if (_listener) {
                if (allowed.contient(c.getCard())) {
                    c.addMouseListener(new ListenerCardBeloteMultiGame(this,c.getCard()));
                } else {
                    String mes_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CANT_PLAY_CARD), Games.toString(c.getCard(),lg_));
//                    String finalMessage_ = StringUtil.concat(mes_,ContainerGame.RETURN_LINE,Games.autoriseBelote(game_,lg_));
                    c.getPaintableLabel().setToolTipText(mes_);
                }
            }
            _panel.add(c.getPaintableLabel());
        }
        _panel.setSize(_panel.getPreferredSizeValue());
//        _panel.removeAll();
//        TranslationsLg lg_ = getOwner().getFrames().currentLg();
//        for (GraphicCard<CardBelote> c: getGraphicCards(getWindow(),lg_, _hand.getCards())) {
//            if (_listener) {
//                c.addMouseListener(new ListenerCardBeloteMultiGame(this,c.getCard()));
//            }
//            _panel.add(c.getPaintableLabel());
//        }
//        _panel.setSize(_panel.getPreferredSizeValue());
//        boolean entered_ = false;
//        for (CardBelote c: _hand) {
//            GraphicBeloteCard carte_ = new GraphicBeloteCard(
//                    c, SwingConstants.RIGHT, !entered_);
//            carte_.setPreferredSize(entered_);
////            carte_.addMouseListener(new EcouteurCarteBeloteJeuMulti(_hand
////                    .carte(indice_)));
//            carte_.addMouseListener(new ListenerCardBeloteMultiGame(this,c));
//            _panel.add(carte_);
//            entered_ = true;
//        }
    }

//    public boolean isAnnonceBeloteRebelote() {
//        return annonceBeloteRebelote;
//    }

//    public boolean isAnnonceBelote() {
//        return annonceBelote;
//    }

//    @Override
//    public boolean hasCreatedServer() {
//        return hasCreatedServer;
//    }

//    @Override
//    public byte getIndexInGame() {
//        return indexInGame;
//    }

    public void endGame(ResultsBelote _res) {
        CustList<Longs> sc_ = _res.getRes().scores();
        _res.initialize(new StringList(), sc_);
        _res.getRes().setUser(getContainerMultiContent().getIndexInGame());
        _res.getGame().setRules(getRulesBeloteMulti());
        CheckerGameBeloteWithRules.check(_res.getGame());
        containerMultiContent.messagesEndGame(_res.getRes(),nicknames(),readCoreResourceSuit(),readResource(), readCoreResourceCards());
//        Games.setMessages(_res.getRes(),getOwner().getFrames().currentLg());
//        _res.getRes().setNicknames(nicknames());
        getPane().removeAll();
        /*Descativer aide au jeu*/
        containerMultiContent.chgEnabledMenuEndGame();
//        MenuItemUtils.setEnabledMenu(getContainerMultiContent().window().getMultiStop(),true);
//        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
//        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),false);
//        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),false);
        AbsPanel container_=getOwner().getCompoFactory().newBorder();

        /*Le nombre de parties jouees depuis le lancement du logiciel*/
//        setThreadAnime(false);

        AbsTabbedPane onglets_=getOwner().getCompoFactory().newAbsTabbedPane();
        setScores(_res.getRes().getScores());
//        _res.getRes().setGeneral(readCoreResourceSuit());
//        _res.getRes().setSpecific(readResource());
//        String lg_ = getOwner().getLanguageKey();

        RenderedPage editor_;
        CardNatLgNamesNavigation sOne_ = retrieve(FileConst.RESOURCES_HTML_FILES_RESULTS_BELOTE).attendreResultat();
        ((BeloteStandards)sOne_.getBeanNatLgNames()).setDataBase(_res);
        editor_ = FrameGeneralHelp.initialize(sOne_, getOwner().getFrames(), getContainerMultiContent().window().getGuardRender());
        editor_.getScroll().setPreferredSize(new MetaDimension(300,300));
        onglets_.add(file().getVal(MessagesGuiCards.MAIN_RESULTS_PAGE),editor_.getScroll());
        CardNatLgNamesNavigation sTwo_ = retrieve(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_BELOTE).attendreResultat();
        ((BeloteStandards)sTwo_.getBeanNatLgNames()).setDataBase(_res);
        editor_ = FrameGeneralHelp.initialize(sTwo_, getOwner().getFrames(), getContainerMultiContent().window().getGuardRender());
        editor_.getScroll().setPreferredSize(new MetaDimension(300,300));
        onglets_.add(file().getVal(MessagesGuiCards.MAIN_DETAIL_RESULTS_PAGE),editor_.getScroll());
        container_.add(onglets_, GuiConstants.BORDER_LAYOUT_CENTER);
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
//            AbsButton button_ = getOwner().getCompoFactory().newPlainButton(containerMultiContent.getMessages().getVal(MessagesGuiCards.PLAY_BELOTE));
//            button_.addActionListener(new PlayNextDealEvent(this));
//            panneau_.add(button_);
//        }
//        panneau_.add(getWindow().getClock());
//        panneau_.add(getWindow().getLastSavedGameDate());
//        container_.add(panneau_, GuiConstants.BORDER_LAYOUT_SOUTH);
//
//        setContentPane(container_);
//        pack();
        //PackingWindowAfter.pack(this, true);
        containerMultiContent.sendOk(this, container_, MessagesGuiCards.PLAY_BELOTE);
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
        long nb_=chargerNombreDeParties(GameEnum.BELOTE, getOwner().getFrames(), ContainerSingleBelote.nbStacks(rulesBeloteMulti));
        GameBelote game_=Net.getGames(containerMultiContent.window().getNet()).partieBelote();
        DealBelote deal_=getOwner().baseWindow().getIa().getBelote().empiler(nb_, game_,getOwner().getGenerator());
        Net.getGames(containerMultiContent.window().getNet()).jouerBelote(new GameBelote(GameType.RANDOM,deal_,game_.getRegles()));
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
//            AbsButton button_ = getOwner().getCompoFactory().newPlainButton(containerMultiContent.getMessages().getVal(WindowNetWork.PLAY_BELOTE));
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
        Net.getGames(containerMultiContent.window().getNet()).jouerBelote(getWindow().baseWindow().getFirstDealBelote().deal(this,rulesBeloteMulti,0));
        containerMultiContent.window().sendObjectPlayGame();
    }

//    @Override
//    public WindowNetWork window() {
//        return win;
//    }

    public HandBelote getBelReb() {
        return belReb;
    }

    @Override
    public AbsActionListenerAct guard() {
        return new AlwaysActionListenerAct();
    }

    @Override
    public AbsActionListener bid(BidBeloteSuit _action) {
        return new ListenerBidBeloteMulti(this,_action);
    }

    public ContainerMultiContent getContainerMultiContent() {
        return containerMultiContent;
    }

//    public boolean isCanBid() {
//        return canBid;
//    }
//    public void setCanBid(boolean _canBid) {
//        canBid = _canBid;
//    }

    public RulesBelote getRulesBeloteMulti() {
        return rulesBeloteMulti;
    }

    public DialogBeloteContent getDialogBeloteContent() {
        return dialogBeloteContent;
    }

    public AbsButton getSelectRules() {
        return selectRules;
    }

    public void setRulesBeloteMulti(RulesBelote _r) {
        this.rulesBeloteMulti = _r;
        Net.getGames(getContainerMultiContent().window().getNet()).setRulesBelote(getRulesBeloteMulti());
    }
}

