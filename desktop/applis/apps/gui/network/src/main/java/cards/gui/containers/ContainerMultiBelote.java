package cards.gui.containers;





import cards.belote.*;
import cards.belote.beans.BeloteStandards;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DealingBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.consts.GameType;
import cards.consts.Role;
import cards.consts.Suit;
import cards.facade.Games;
import cards.facade.enumerations.GameEnum;
import cards.gui.*;
import cards.gui.containers.events.BidEvent;
import cards.gui.containers.events.ChangePlaceEvent;
import cards.gui.containers.events.ChangeRulesEvent;
import cards.gui.containers.events.FoldEvent;
import cards.gui.containers.events.PlayFirstDealEvent;
import cards.gui.containers.events.PlayNextDealEvent;
import cards.gui.containers.events.ReadyEvent;
import cards.gui.dialogs.*;
import cards.gui.events.ListenerBidBeloteMulti;
import cards.gui.events.ListenerCardBeloteMultiGame;
import cards.gui.events.SelectPointsEvent;
import cards.gui.events.SelectSuitEvent;
import cards.gui.labels.*;
import cards.gui.panels.CarpetBelote;
import cards.gui.panels.MiniCarpet;
import cards.main.CardNatLgNamesNavigation;
import cards.network.belote.actions.BiddingBelote;
import cards.network.belote.actions.PlayingCardBelote;
import cards.network.belote.displaying.DealtHandBelote;
import cards.network.belote.displaying.RefreshHandBelote;
import cards.network.belote.displaying.errors.ErrorBiddingBelote;
import cards.network.belote.displaying.errors.ErrorPlayingBelote;
import cards.network.belote.displaying.players.RefreshHandPlayingBelote;
import cards.network.belote.displaying.players.RefreshingDoneBelote;
import cards.network.belote.unlock.AllowBiddingBelote;
import cards.network.belote.unlock.AllowPlayingBelote;
import cards.network.common.*;
import cards.network.common.before.ChoosenPlace;
import cards.network.common.before.PlayersNamePresent;
import cards.network.common.before.Ready;
import cards.network.threads.Net;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.images.MetaDimension;
import code.network.WindowNetWork;
import code.sml.util.TranslationsLg;
import code.util.CustList;
import code.util.*;
import code.util.Ints;
import code.util.StringList;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class ContainerMultiBelote extends ContainerBelote implements
        ContainerMulti,ContainerPlayableBelote {

    private int noClient;
    private byte indexInGame = IndexConstants.INDEX_NOT_FOUND_ELT;
    private RulesBelote rulesBeloteMulti = new RulesBelote();
//    private boolean annonceBelote;
//    private boolean annonceBeloteRebelote;
    private NumComboBox choiceOfPlaceForPlayingGame;
    private AbsCustCheckBox ready;

    private DealingBelote repBelote;
    private int nbChoosenPlayers = IndexConstants.INDEX_NOT_FOUND_ELT;
    private boolean hasCreatedServer;
    private boolean readyToPlay;
    private final CustList<AbsPlainLabel> playersPseudos = new CustList<AbsPlainLabel>();
    private final CustList<AbsPlainLabel> playersPlaces = new CustList<AbsPlainLabel>();
    private final CustList<AbsCustCheckBox> playersReady = new CustList<AbsCustCheckBox>();
    private RenderedPage editor;
    private IntTreeMap< Byte> playersPlacesForGame = new IntTreeMap< Byte>();
    private IntMap<String> playersPseudosForGame = new IntMap<String>();
    private HandBelote playerHandBelote = new HandBelote();
    private BidBeloteSuit bidMax = new BidBeloteSuit();
    private boolean canBid;
    private final AbsPlainLabel canPlayLabel = getOwner().getCompoFactory().newPlainLabel("");
    private final WindowNetWork win;

    public ContainerMultiBelote(WindowNetWork _window, boolean _hasCreatedServer) {
        super(_window);
        win = _window;
        hasCreatedServer = _hasCreatedServer;
        if (hasCreatedServer) {
            Net.getGames(_window.getNet()).setRulesBelote(getRulesBeloteMulti());
            Net.getGames(_window.getNet()).setRulesPresident(null);
            Net.getGames(_window.getNet()).setRulesTarot(null);
        }
    }

    private void addButtonsForCoinche(int _pts, CustList<BidBeloteSuit> _bids) {
        Ints points_ = RulesBelote.getPoints();
        int size_ = points_.size();
        setPanneauBoutonsJeuPoints(getOwner().getCompoFactory().newGrid());
        getPointsButtons().clear();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        for (int i = 0; i < size_; i++) {
            int p_ = points_.get(i);
            LabelPoints label_ = new LabelPoints(p_, getOwner().getCompoFactory());
            label_.setEnabledLabel(_pts < p_);
            label_.setToolTipText(Long.toString(p_));
            label_.getButton().addActionListener(new SelectPointsEvent(this, p_));
            getPointsButtons().add(label_);
            getPanneauBoutonsJeuPoints().add(label_.getButton(),WindowCardsCore.ctsRem(getWindow().getCompoFactory(),(i+1)%3==0));
        }
        getPanneauBoutonsJeu().add(getPanneauBoutonsJeuPoints());
        setBidOk(getOwner().getCompoFactory().newPlainButton(WindowNetWork.OK));
        getBidOk().setEnabled(false);
        getBidOk().addActionListener(new BidEvent(this));
        AbsPanel panel_ = getOwner().getCompoFactory().newPageBox();
        AbsPanel panelSuits_ = getOwner().getCompoFactory().newLineBox();
        getBidsButtons().clear();
        for (Suit s: Suit.couleursOrdinaires()) {
            SuitLabel suitLabel_ = new SuitLabel(getOwner().getCompoFactory());
            BidBeloteSuit bid_ = new BidBeloteSuit();
            bid_.setSuit(s);
            bid_.setBid(BidBelote.SUIT);
            suitLabel_.setSuit(bid_, lg_);

            suitLabel_.addMouseListener(new SelectSuitEvent(this,bid_));
            suitLabel_.setPreferredSize(new MetaDimension(20,20));
            getBidsButtons().add(suitLabel_);
            panelSuits_.add(suitLabel_.getPaintableLabel());
        }
        panel_.add(panelSuits_);
        AbsPanel panelBids_ = getOwner().getCompoFactory().newLineBox();
        for (BidBelote b: BidBelote.getNonZeroBids()) {
            if (b.getCouleurDominante()) {
                continue;
            }
            boolean present_ = false;
            for (BidBeloteSuit allowedBid_: _bids) {
                if (allowedBid_.getBid() == b) {
                    present_ = true;
                    break;
                }
            }
            if (!present_) {
                continue;
            }
            SuitLabel suitLabel_ = new SuitLabel(getOwner().getCompoFactory());
            BidBeloteSuit bid_ = new BidBeloteSuit();
            bid_.setBid(b);
            suitLabel_.setSuit(bid_, lg_);
            suitLabel_.addMouseListener(new SelectSuitEvent(this,bid_));

            panelBids_.add(suitLabel_.getPaintableLabel());
            getBidsButtons().add(suitLabel_);
        }
        panel_.add(panelBids_);
        AbsPanel panelOk_ = getOwner().getCompoFactory().newLineBox();
        AbsButton buttonSuit_ = getOwner().getCompoFactory().newPlainButton(Games.toString(BidBelote.FOLD,lg_));
        buttonSuit_.addActionListener(new FoldEvent(this));
        panelOk_.add(buttonSuit_);
        panelOk_.add(getBidOk());
        panel_.add(panelOk_);
        getPanneauBoutonsJeu().add(panel_);
    }

    @Override
    public void bid() {
        if (!isCanBid()) {
            return;
        }
        setCanBid(false);
        BidBeloteSuit bidLoc_ = new BidBeloteSuit();
        bidLoc_.setSuit(getSuit());
        bidLoc_.setBid(getBidType());
        bidLoc_.setPoints(getPts());
        BiddingBelote bid_ = new BiddingBelote();
        bid_.setPlace(indexInGame);
        bid_.setBidBelote(bidLoc_);
        String lg_ = getOwner().getLanguageKey();
        bid_.setLocale(lg_);
        window().sendObject(bid_);
    }

    @Override
    public void fold() {
        if (!isCanBid()) {
            return;
        }
        setCanBid(false);
        BidBeloteSuit bidLoc_ = new BidBeloteSuit();
        BiddingBelote bid_ = new BiddingBelote();
        bid_.setPlace(indexInGame);
        bid_.setBidBelote(bidLoc_);
        String lg_ = getOwner().getLanguageKey();
        bid_.setLocale(lg_);
        window().sendObject(bid_);
    }

    private void ajouterBoutonContratBeloteMulti(String _texte,
            BidBeloteSuit _action) {
        AbsPanel panneau_ = getPanneauBoutonsJeu();
        AbsButton bouton_ = getOwner().getCompoFactory().newPlainButton(_texte);
//        bouton_.addActionListener(new EcouteurBoutonContratBeloteMulti(_action));
        bouton_.addActionListener(new ListenerBidBeloteMulti(this,_action));
        panneau_.add(bouton_);
    }

    @Override
    public void updateFirst(PlayersNamePresent _players) {
        getPane().removeAll();
        MenuItemUtils.setEnabledMenu(window().getMultiStop(),true);
        MenuItemUtils.setEnabledMenu(window().getTricksHands(),true);
        MenuItemUtils.setEnabledMenu(window().getTeams(),true);
//        MenuItemUtils.setEnabledMenu(getLoad(),false);
        rulesBeloteMulti = _players.getRulesBelote();
        nbChoosenPlayers = _players.getNbPlayers();
        AbsPanel container_ = getOwner().getCompoFactory().newPageBox();
        AbsPanel panel_ = getOwner().getCompoFactory().newGrid(0, 2);
        panel_.add(getOwner().getCompoFactory().newPlainLabel(getMessages().getVal(WindowNetWork.PLACE)));
        choiceOfPlaceForPlayingGame = new NumComboBox(getOwner().getFrames());
        choiceOfPlaceForPlayingGame.setItems(nbChoosenPlayers);
        choiceOfPlaceForPlayingGame.setSelectedItem(_players.getPseudos()
                .size() - 1);
        indexInGame = (byte) NumberUtil.parseInt(choiceOfPlaceForPlayingGame
                .getSelectedItem());
        choiceOfPlaceForPlayingGame.setListener(new ChangePlaceEvent(this));
        panel_.add(choiceOfPlaceForPlayingGame.self());
        ready = getOwner().getCompoFactory().newCustCheckBox(getMessages().getVal(WindowNetWork.READY));
        ready.addActionListener(new ReadyEvent(this));
        panel_.add(ready);
        container_.add(panel_);
        panel_ = getOwner().getCompoFactory().newGrid(0, 3);
        playersPseudos.clear();
        for (int i = IndexConstants.FIRST_INDEX; i < nbChoosenPlayers; i++) {
            AbsPlainLabel pseudo_ = getOwner().getCompoFactory().newPlainLabel("");
            playersPseudos.add(pseudo_);
            panel_.add(pseudo_);
            AbsPlainLabel place_ = getOwner().getCompoFactory().newPlainLabel("");
            playersPlaces.add(place_);
            panel_.add(place_);
            AbsCustCheckBox ready_ = getOwner().getCompoFactory().newCustCheckBox();
            ready_.setEnabled(false);
            playersReady.add(ready_);
            panel_.add(ready_);
        }
        container_.add(panel_);

        rulesBeloteMulti.getCommon().setGeneral(readCoreResourceMix());
        rulesBeloteMulti.getCommon().setSpecific(readResource());
        CardNatLgNamesNavigation stds_ = retrieve(FileConst.RESOURCES_HTML_FILES_RULES_BELOTE).attendreResultat();
        ((BeloteStandards)stds_.getBeanNatLgNames()).setDataBaseRules(rulesBeloteMulti);
        editor = FrameGeneralHelp.initialize(stds_, getOwner().getFrames());

        editor.getScroll().setPreferredSize(new MetaDimension(300,400));
        container_.add(editor.getScroll());

        playersPlacesForGame = _players.getPlacesPlayers();
        playersPseudosForGame = new IntMap<String>(_players.getPseudos());
        for (int i : _players.getPseudos().getKeys()) {
            playersPseudos.get(i).setText(_players.getPseudos().getVal(i));
        }
        for (int i : _players.getPlacesPlayers().getKeys()) {
            playersPlaces.get(i).setText(
                    _players.getPlacesPlayers().getVal(i).toString());
        }
        for (int i : _players.getReadyPlayers().getKeys()) {
            playersReady.get(i).setSelected(_players.getReadyPlayers().getVal(i) == BoolVal.TRUE);
        }
        if (hasCreatedServer) {
            AbsButton buttonRules_ = getOwner().getCompoFactory().newPlainButton(getMessages().getVal(WindowNetWork.SELECT_RULES));
            buttonRules_.addActionListener(new ChangeRulesEvent(this));
            container_.add(buttonRules_);
            AbsButton button_ = getOwner().getCompoFactory().newPlainButton(getMessages().getVal(WindowNetWork.PLAY_BELOTE));
            button_.addActionListener(new PlayFirstDealEvent(this));
            container_.add(button_);
        }
        container_.add(getWindow().getClock());
        container_.add(getWindow().getLastSavedGameDate());
        setContentPane(container_);
        //PackingWindowAfter.pack(this, true);
        pack();
    }
    @Override
    public void changePlace() {
        if (readyToPlay) {
            return;
        }
        indexInGame = (byte) NumberUtil.parseInt(choiceOfPlaceForPlayingGame.getSelectedItem());
        ChoosenPlace choice_ = new ChoosenPlace();
        choice_.setIndex(noClient);
        choice_.setPlace(indexInGame);
        choice_.setPlacesPlayers(new IntTreeMap< Byte>());
        window().sendObject(choice_);
    }
    @Override
    public void updatePlaces(ChoosenPlace _choosePlace) {
        playersPlacesForGame = _choosePlace.getPlacesPlayers();
        playersPlaces.get(_choosePlace.getIndex()).setText(
                Long.toString(_choosePlace.getPlace()));
    }

    @Override
    public void updateReady(Ready _readyPlayer) {
        playersReady.get(_readyPlayer.getIndex()).setSelected(
                _readyPlayer.isReady());
    }

    @Override
    public void updateAfter(PlayersNamePresent _players) {
        playersPlacesForGame = _players.getPlacesPlayers();
        playersPseudosForGame = new IntMap<String>(_players.getPseudos());
        for (int i : _players.getPseudos().getKeys()) {
            playersPseudos.get(i).setText(_players.getPseudos().getVal(i));
        }
        for (int i : _players.getPlacesPlayers().getKeys()) {
            playersPlaces.get(i).setText(
                    _players.getPlacesPlayers().getVal(i).toString());
        }
        for (int i : _players.getReadyPlayers().getKeys()) {
            playersReady.get(i).setSelected(_players.getReadyPlayers().getVal(i) == BoolVal.TRUE);
        }
    }

    public void updateRules(RulesBelote _rules) {
        rulesBeloteMulti = _rules;
        rulesBeloteMulti.getCommon().setGeneral(readCoreResourceMix());
        rulesBeloteMulti.getCommon().setSpecific(readResource());
        CardNatLgNamesNavigation stds_ = retrieve(FileConst.RESOURCES_HTML_FILES_RULES_BELOTE).attendreResultat();
        ((BeloteStandards)stds_.getBeanNatLgNames()).setDataBaseRules(rulesBeloteMulti);
        FrameGeneralHelp.initialize(stds_, editor);
    }

    public void updateForBeginningGame(DealtHandBelote _hand) {
        repBelote = _hand.getRep();
        placerIhmBeloteMulti(_hand.getDeck(), _hand.getDealer());

        playerHandBelote = _hand.getCards();
        playerHandBelote.trier(getDisplayingBelote().getDisplaying().getSuits(),
                getDisplayingBelote().getDisplaying().isDecreasing(),
                getDisplayingBelote().getOrderBeforeBids());
        setCarteEntree(false);
        setCarteSortie(false);
        setCanCall(false);
        setCanDiscard(false);
        setCanExcludeTrumps(false);
        setCanPlay(false);
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        /* On place les cartes de l'utilisateur */
        updateCardsInPanelBeloteMulti(getPanelHand(), playerHandBelote);
        if (repBelote.getRemainingCards() > 0) {
            for (BidBeloteSuit b : _hand.getAllowedBids()) {
                ajouterBoutonContratBeloteMulti(Games.toString(b,lg_), b);
            }
        } else {
            addButtonsForCoinche(_hand.getPoints(), _hand.getAllowedBids());
        }
        //PackingWindowAfter.pack(this, true);
        pack();
        PlayerActionGame dealt_ = new PlayerActionGame(PlayerActionGameType.DEALT);
        dealt_.setPlace(indexInGame);
        dealt_.setLocale(lg_.getKey());
        window().sendObject(dealt_);
    }

    public void canBid() {
        setCanBid(true);
    }

    public void canBidBelote(AllowBiddingBelote _bids) {
        canPlayLabel.setText(getMessages().getVal(WindowNetWork.CAN_PLAY));
        setCanBid(true);
        getPanneauBoutonsJeu().removeAll();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        if (repBelote.getRemainingCards() > 0) {
            for (BidBeloteSuit b : _bids.getBids()) {
                ajouterBoutonContratBeloteMulti(Games.toString(b,lg_), b);
            }
        } else {
            addButtonsForCoinche(_bids.getPoints(), _bids.getBids());
        }
        //getPanneauBoutonsJeu().validate();
        pack();
        //PackingWindowAfter.pack(this, true);
    }

    public void afterBid() {
        canPlayLabel.setText(EMPTY_STRING);
        setCanBid(false);
    }

    public void errorForBidding(ErrorBiddingBelote _error) {
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        String mes_ = StringUtil.simpleStringsFormat(getMessages().getVal(WindowNetWork.CANT_BID), Games.toString(_error.getBid(),lg_));
//        JOptionPane.showMessageDialog(getOwner(),mes_,
//                getMessages().getVal(MainWindow.CANT_BID_TITLE), JOptionPane.INFORMATION_MESSAGE);
        getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(), mes_, getMessages().getVal(WindowNetWork.CANT_BID_TITLE),
                GuiConstants.ERROR_MESSAGE);
    }

    public void displayLastBid(BiddingBelote _bid) {
        if (_bid.getBidBelote().estDemandable(bidMax)) {
            bidMax = _bid.getBidBelote();
        }
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        getEvents().append(StringUtil.concat(getPseudoByPlace(_bid.getPlace()), INTRODUCTION_PTS,
                Games.toString(_bid.getBidBelote(),lg_), RETURN_LINE));
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().validate();
        //pack();
        PlayerActionGame dealt_ = new PlayerActionGame(PlayerActionGameType.DONE_BIDDING);
        dealt_.setPlace(indexInGame);
        dealt_.setLocale(lg_.getKey());
        window().sendObject(dealt_);
    }

    public void canPlayBelote(AllowPlayingBelote _declaration) {
        canPlayLabel.setText(getMessages().getVal(WindowNetWork.CAN_PLAY));
        setCanPlay(true);
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),true);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),true);
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
//        annonceBeloteRebelote = false;
        AbsCustCheckBox belReb_ = getBeloteRebelote();
        belReb_.setSelected(false);
        if (_declaration.isPossibleBeloteRebelote()) {
            AbsPanel panneau_ = getPanneauBoutonsJeu();
            belReb_.setText(Games.toStringBeloteReb(lg_));
            belReb_.setEnabled(_declaration.isAllowedBeloteRebelote());
//            belReb_.addActionListener(new ChangeBeloteRebeloteEvent(this));
            panneau_.add(belReb_);
        }
        AbsCustCheckBox beloteDeclare_ = getBeloteDeclare();
        beloteDeclare_.setSelected(false);
        if (!_declaration.isFirstRoundPlaying()) {
//            annonceBelote = false;
            pack();
            return;
        }

        DeclareHandBelote annonceMain_ = _declaration.getDeclaration();
        if (annonceMain_.getDeclare() != DeclaresBelote.UNDEFINED) {
//            annonceBelote = false;
            AbsPanel panneau_ = getPanneauBoutonsJeu();
//            AbsCustCheckBox caseCoche_ = getOwner().getCompoFactory().newCustCheckBox(StringUtil.concat(Games.toString(annonceMain_.getDeclare(),lg_),
//                    INTRODUCTION_PTS, Games.toString(annonceMain_.getHand(),lg_)));
            beloteDeclare_.setText(StringUtil.concat(Games.toString(annonceMain_.getDeclare(),lg_),
                    INTRODUCTION_PTS, Games.toString(annonceMain_.getHand(),lg_)));
//            caseCoche_.addActionListener(new ChangeBeloteDeclareEvent(this));
            panneau_.add(beloteDeclare_);
        }
        byte relative_ = relative(_declaration.getTakerIndex());
        getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, relative_);
        pack();
    }

    public void displayPlayedCard(PlayingCardBelote _card) {
        canPlayLabel.setText(EMPTY_STRING);
        byte relative_ = relative(_card.getPlace());
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        tapisBelote().setCarteBelote(getWindow().getImageFactory(), lg_, relative_, _card.getPlayedCard());

        String pseudo_ = getPseudoByPlace(_card.getPlace());
        if (_card.isDeclaringBeloteRebelote()) {
            ajouterTexteDansZone(StringUtil.concat(pseudo_, INTRODUCTION_PTS, Games.toStringBeloteReb(lg_), RETURN_LINE));
        }
        if (_card.isDeclaring()) {
            if (bidMax.getCouleurDominante()) {
                _card.getDeclare()
                        .getHand()
                        .trier(getDisplayingBelote().getDisplaying().getSuits(),
                                getDisplayingBelote().getDisplaying().isDecreasing(),
                                bidMax.getSuit());
            } else {
                _card.getDeclare()
                        .getHand()
                        .trier(getDisplayingBelote().getDisplaying().getSuits(),
                                getDisplayingBelote().getDisplaying().isDecreasing(),
                                bidMax.getOrdre());
            }
            ajouterTexteDansZone(StringUtil.concat(pseudo_, INTRODUCTION_PTS, Games.toString(_card.getDeclare().getDeclare(),lg_),
                    RETURN_LINE));
            if (!_card.getDeclare().getHand().estVide()) {
                getHandfuls().getVal(relative_).setText(
                        Games.toString(_card.getDeclare().getDeclare(),lg_));
            }
            AbsPanel panelToSet_ = getDeclaredHandfuls().getVal(relative_);
            panelToSet_.removeAll();
            for (CardBelote c: _card.getDeclare().getHand()) {
                MiniCard carte_ = new MiniCard(lg_, getOwner(),c.getId().nb());
                panelToSet_.add(carte_.getPaintableLabel());
            }
        }
        relative_ = relative(_card.getTakerIndex());
        getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, relative_);
        //PackingWindowAfter.pack(this, true);
        pack();
        PlayerActionGame dealt_ = new PlayerActionGame(PlayerActionGameType.DONE_PLAYING);
        dealt_.setPlace(indexInGame);
        dealt_.setLocale(lg_.getKey());
        window().sendObject(dealt_);
    }

    public void errorPlayingCard(ErrorPlayingBelote _error) {
        setCanPlay(true);
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        if (!_error.getCards().estVide()) {
//            AbsPanel panneau_ = getOwner().getCompoFactory().newLineBox();
//            HandBelote cartesBeloteRebelote_ = _error.getCards();
//            for (GraphicBeloteCard c: getGraphicCards(getWindow(),lg_, cartesBeloteRebelote_.getCards())) {
//                panneau_.add(c.getPaintableLabel());
//            }
            ajouterTexteDansZone(getMessages().getVal(WindowNetWork.HAVE_TO_PLAY)+RETURN_LINE);
//            getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(), panneau_,
//                    getMessages().getVal(WindowNetWork.HAVE_TO_PLAY),
//                    GuiConstants.ERROR_MESSAGE);

            return;
        }
        String mes_ = StringUtil.simpleStringsFormat(getMessages().getVal(WindowNetWork.CANT_PLAY_CARD), Games.toString(_error.getCard(),lg_));
        String mesReason_ = StringUtil.simpleStringsFormat(getMessages().getVal(WindowNetWork.REASON), _error.getReason());
        getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(),
                StringUtil.concat(mes_, RETURN_LINE, mesReason_),
                getMessages().getVal(WindowNetWork.CANT_PLAY_CARD_TITLE),
                GuiConstants.ERROR_MESSAGE);
    }

    public void refreshHand(RefreshHandBelote _cards) {
        playerHandBelote.supprimerCartes();
        playerHandBelote.ajouterCartes(_cards.getRefreshedHand());
        if (bidMax.getCouleurDominante()) {
            playerHandBelote.trier(getDisplayingBelote().getDisplaying().getSuits(),
                    getDisplayingBelote().getDisplaying().isDecreasing(), bidMax.getSuit());
        } else {
            playerHandBelote.trier(getDisplayingBelote().getDisplaying().getSuits(),
                    getDisplayingBelote().getDisplaying().isDecreasing(), bidMax.getOrdre());
        }

        setCarteEntree(false);
        setCarteSortie(false);
        setCanPlay(false);
        byte relative_ = relative(_cards.getTakerIndex());
        getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, relative_);

        /* On place les cartes de l'utilisateur */

        updateCardsInPanelBeloteMulti(getPanelHand(), playerHandBelote);
        pack();
        //PackingWindowAfter.pack(this, true);
        PlayerActionGame completed_ = new PlayerActionGame(PlayerActionGameType.COMPLETED_HAND);
        completed_.setPlace(indexInGame);
        window().sendObject(completed_);
    }

    public void refreshHand(RefreshHandPlayingBelote _card) {
        playerHandBelote.jouer(_card.getCard());
        if (bidMax.getCouleurDominante()) {
            playerHandBelote.trier(getDisplayingBelote().getDisplaying().getSuits(),
                    getDisplayingBelote().getDisplaying().isDecreasing(), bidMax.getSuit());
        } else {
            playerHandBelote.trier(getDisplayingBelote().getDisplaying().getSuits(),
                    getDisplayingBelote().getDisplaying().isDecreasing(), bidMax.getOrdre());
        }
        getPanneauBoutonsJeu().removeAll();
        setCanPlay(false);
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),false);
        /* On place les cartes de l'utilisateur */
        updateCardsInPanelBeloteMulti(getPanelHand(), playerHandBelote);
        pack();
        //PackingWindowAfter.pack(this, true);
        RefreshingDoneBelote ref_ = new RefreshingDoneBelote();
        ref_.setCard(_card.getCard());
        ref_.setDeclaring(_card.isDeclaring());
        ref_.setDeclaringBeloteRebelote(_card.isDeclaringBeloteRebelote());
        ref_.setDeclare(_card.getDeclare());
        ref_.setPlace(indexInGame);
        String lg_ = getOwner().getLanguageKey();
        ref_.setLocale(lg_);
        window().sendObject(ref_);

    }

    @Override
    public void pauseBetweenTrick() {
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        tapisBelote().setCartesBeloteJeu(getWindow().getImageFactory(), (byte) nbChoosenPlayers, lg_);
        //pack();
        PlayerActionGame d_ = new PlayerActionGame(PlayerActionGameType.DONE_PAUSE);
        d_.setPlace(indexInGame);
        d_.setLocale(lg_.getKey());
        window().sendObject(d_);
    }

    public void showTeams() {
        if (!isCanPlay()) {
            return;
        }
        String lg_ = getOwner().getLanguageKey();
        PlayerActionGame select_ = new PlayerActionGame(PlayerActionGameType.SELECT_TEAMS);
        select_.setPlace(indexInGame);
        select_.setLocale(lg_);
        window().sendObject(select_);
    }

    @Override
    public void showTricksHands() {
        if (!isCanPlay()) {
            return;
        }
        String lg_ = getOwner().getLanguageKey();
        PlayerActionGame select_ = new PlayerActionGame(PlayerActionGameType.SELECT_TRICKS_HANDS);
        select_.setPlace(indexInGame);
        select_.setLocale(lg_);
        window().sendObject(select_);
    }

    public void showTricksHands(TricksHandsBelote _tricks) {
        ByteTreeMap< String> pseudos_ = new ByteTreeMap< String>();
        byte p_ = 0;
        for (String s : pseudosBelote((byte) nbChoosenPlayers)) {
            pseudos_.put(p_, s);
            p_++;
        }
        for (byte p : playersPlacesForGame.values()) {
            pseudos_.put(p, getPseudoByPlace(p));
        }
        StringList list_ = new StringList(pseudos_.values());
        WindowNetWork ow_ = window();
        DialogTricksBelote.setDialogTricksBelote(
                getMessages().getVal(WindowNetWork.HANDS_TRICKS_BELOTE), ow_);
        DialogTricksBelote.init(_tricks, (byte) nbChoosenPlayers, list_,
                getDisplayingBelote(), ow_);
    }

    public void showTeams(TeamsPlayers _teams) {
        ByteTreeMap< String> pseudos_ = new ByteTreeMap< String>();
        byte p_ = 0;
        for (String s : pseudosBelote((byte) nbChoosenPlayers)) {
            pseudos_.put(p_, s);
            p_++;
        }
        for (byte p : playersPlacesForGame.values()) {
            pseudos_.put(p, getPseudoByPlace(p));
        }
        StringList list_ = new StringList(pseudos_.values());
        DialogTeamsPlayers.initDialogTeamsPlayers(getOwner());
        DialogTeamsPlayers.setDialogTeamsPlayers(list_, _teams, getOwner().getDialogTeamsPlayers(), getOwner().getCompoFactory());
    }

    @Override
    public void setNoClient(int _noClient) {
        noClient = _noClient;
    }

    @Override
    public int getNoClient() {
        return noClient;
    }

    private void placerIhmBeloteMulti(HandBelote _cardsOnDeck, byte _beginPlace) {
        getPane().removeAll();
        MenuItemUtils.setEnabledMenu(window().getMultiStop(),false);
        AbsPanel container_ = getOwner().getCompoFactory().newBorder();
        container_.add(getOwner().getCompoFactory().newPlainLabel(getMessages().getVal(WindowNetWork.HELP_GO_MENU)), GuiConstants.BORDER_LAYOUT_NORTH);
        ByteTreeMap< String> pseudos_ = new ByteTreeMap< String>();
        byte p_ = 0;
        for (String s : pseudosBelote((byte) nbChoosenPlayers)) {
            pseudos_.put(p_, s);
            p_++;
        }
        for (byte p : playersPlacesForGame.values()) {
            byte relative_ = relative(p);
            pseudos_.put(relative_, getPseudoByPlace(p));
        }
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        StringList list_ = new StringList(pseudos_.values());
        setMini(MiniCarpet.newCarpet(getWindow().getImageFactory(),nbChoosenPlayers, getDisplayingBelote().getDisplaying().isClockwise(), list_, getOwner().getCompoFactory()));
        CarpetBelote tapis_ = CarpetBelote.initTapisBelote(lg_, getDisplayingBelote().getDisplaying().isClockwise(),
                1, getOwner().getCompoFactory());
        getTapis().setTapisBelote(tapis_);
        container_.add(tapis_.getContainer(), GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel panneau_;
        panneau_= getOwner().getCompoFactory().newLineBox();
        panneau_.setBackground(GuiConstants.BLUE);
        setPanelHand(panneau_);
        container_.add(panneau_, GuiConstants.BORDER_LAYOUT_SOUTH);
        AbsPanel panneau2_ = getOwner().getCompoFactory().newPageBox();
        setEvents(getOwner().getCompoFactory().newTextArea(EMPTY, 8, 30));
        byte relative_ = relative(_beginPlace);
        getEvents().append(StringUtil.concat(getMessages().getVal(WindowNetWork.PLAYER_HAVING_TO_PLAY), pseudos_.getVal(relative_), RETURN_LINE));
        getEvents().setEditable(false);
        panneau2_.add(getOwner().getCompoFactory().newAbsScrollPane(getEvents()));
        panneau2_.add(getMiniPanel());
        setHandfuls(new ByteMap<AbsPlainLabel>());
        setDeclaredHandfuls(new ByteMap<AbsPanel>());
        AbsPanel declaredHandfuls_ = getOwner().getCompoFactory().newPageBox();
        for (byte i = IndexConstants.FIRST_INDEX; i < nbChoosenPlayers; i++) {
            AbsPanel declaredHandfulGroup_ = getOwner().getCompoFactory().newLineBox();
            AbsPlainLabel lab_ = getOwner().getCompoFactory().newPlainLabel(pseudos_.getVal(i));
            declaredHandfulGroup_.add(lab_);
            AbsPlainLabel handful_ = getOwner().getCompoFactory().newPlainLabel(EMPTY_STRING);
            declaredHandfulGroup_.add(handful_);
            getHandfuls().put(i, handful_);
            AbsPanel declaredHandful_ = getOwner().getCompoFactory().newLineBox();
            declaredHandfulGroup_.add(declaredHandful_);
            getDeclaredHandfuls().put(i, declaredHandful_);
            declaredHandfuls_.add(declaredHandfulGroup_);
        }
        AbsScrollPane scroll_ = getOwner().getCompoFactory().newAbsScrollPane(declaredHandfuls_);
        panneau2_.add(scroll_);
        AbsPanel sousPanneau_ = getOwner().getCompoFactory().newPageBox();
        setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(sousPanneau_);
        container_.add(panneau2_, GuiConstants.BORDER_LAYOUT_EAST);
        if (!_cardsOnDeck.estVide()) {
            tapisBelote().setTalonBelote(getWindow(),lg_,_cardsOnDeck);
        }
        AbsPanel panel_ = getOwner().getCompoFactory().newPageBox();
        panel_.add(getOwner().getCompoFactory().newAbsScrollPane(container_));
        canPlayLabel.setText(EMPTY_STRING);
        panel_.add(canPlayLabel);
        readyToPlay = false;
        ready = getOwner().getCompoFactory().newCustCheckBox(getMessages().getVal(WindowNetWork.READY));
        ready.addActionListener(new ReadyEvent(this));
        panel_.add(ready);
        panel_.add(getWindow().getClock());
        panel_.add(getWindow().getLastSavedGameDate());
        setContentPane(panel_);
    }

    private String getPseudoByPlace(byte _place) {
        for (int i : playersPlacesForGame.getKeys()) {
            if (playersPlacesForGame.getVal(i) == _place) {
                return playersPseudosForGame.getVal(i);
            }
        }
        return EMPTY_STRING;
    }

    private byte relative(byte _otherPlayerIndex) {
        byte iter_ = 0;
        for (byte p = indexInGame; p < nbChoosenPlayers; p++) {
            if (p == _otherPlayerIndex) {
                return iter_;
            }
            iter_++;
        }
        for (byte p = IndexConstants.FIRST_INDEX; p < indexInGame; p++) {
            if (p == _otherPlayerIndex) {
                return iter_;
            }
            iter_++;
        }
        return iter_;
    }

    private void updateCardsInPanelBeloteMulti(AbsPanel _panel, HandBelote _hand) {
        _panel.removeAll();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        for (GraphicBeloteCard c: getGraphicCards(getWindow(),lg_, _hand.getCards())) {
            c.addMouseListener(new ListenerCardBeloteMultiGame(this,c.getCard()));
            _panel.add(c.getPaintableLabel());
        }
        _panel.validate();
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

    @Override
    public boolean hasCreatedServer() {
        return hasCreatedServer;
    }

    @Override
    public byte getIndexInGame() {
        return indexInGame;
    }

    public void endGame(ResultsBelote _res) {
        getPane().removeAll();
        /*Descativer aide au jeu*/
        MenuItemUtils.setEnabledMenu(window().getMultiStop(),true);
//        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),false);
        AbsPanel container_=getOwner().getCompoFactory().newBorder();

        /*Le nombre de parties jouees depuis le lancement du logiciel*/
        setThreadAnime(false);

        AbsTabbedPane onglets_=getOwner().getCompoFactory().newAbsTabbedPane();
        setScores(_res.getRes().getScores());
        _res.getRes().setGeneral(readCoreResourceSuit());
        _res.getRes().setSpecific(readResource());
        String lg_ = getOwner().getLanguageKey();

        RenderedPage editor_;
        CardNatLgNamesNavigation sOne_ = retrieve(FileConst.RESOURCES_HTML_FILES_RESULTS_BELOTE).attendreResultat();
        ((BeloteStandards)sOne_.getBeanNatLgNames()).setDataBase(_res);
        editor_ = FrameGeneralHelp.initialize(sOne_, getOwner().getFrames());
        editor_.getScroll().setPreferredSize(new MetaDimension(300,300));
        onglets_.add(getMessages().getVal(WindowNetWork.RESULTS_PAGE),editor_.getScroll());
        CardNatLgNamesNavigation sTwo_ = retrieve(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_BELOTE).attendreResultat();
        ((BeloteStandards)sTwo_.getBeanNatLgNames()).setDataBase(_res);
        editor_ = FrameGeneralHelp.initialize(sTwo_, getOwner().getFrames());
        editor_.getScroll().setPreferredSize(new MetaDimension(300,300));
        onglets_.add(getMessages().getVal(WindowNetWork.DETAIL_RESULTS_PAGE),editor_.getScroll());
        container_.add(onglets_, GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel panneau_=getOwner().getCompoFactory().newPageBox();
        readyToPlay = false;
        ready = getOwner().getCompoFactory().newCustCheckBox(getMessages().getVal(WindowNetWork.READY));
        ready.addActionListener(new ReadyEvent(this));
        panneau_.add(ready);

        AbsPanel panel_ = getOwner().getCompoFactory().newGrid(0,3);

        for (int i = IndexConstants.FIRST_INDEX; i<nbChoosenPlayers; i++) {
            panel_.add(playersPseudos.get(i));
            panel_.add(playersPlaces.get(i));
            panel_.add(playersReady.get(i));
        }
        panneau_.add(panel_);
        if (hasCreatedServer) {
            AbsButton button_ = getOwner().getCompoFactory().newPlainButton(getMessages().getVal(WindowNetWork.PLAY_BELOTE));
            button_.addActionListener(new PlayNextDealEvent(this));
            panneau_.add(button_);
        }
        panneau_.add(getWindow().getClock());
        panneau_.add(getWindow().getLastSavedGameDate());
        container_.add(panneau_, GuiConstants.BORDER_LAYOUT_SOUTH);

        setContentPane(container_);
        pack();
        //PackingWindowAfter.pack(this, true);
        PlayerActionGame ok_ = new PlayerActionGame(PlayerActionGameType.OK);
        ok_.setPlace(indexInGame);
        ok_.setLocale(lg_);
        window().sendObject(ok_);
    }

    @Override
    public void dealNext() {
        boolean allReady_ = window().getSockets().allReady();
        if (!allReady_) {
            return;
        }
        boolean distinct_ = Net.distinctPlaces(window().getNet(), window().getSockets());
        if (!distinct_) {
            return;
        }
        long nb_=chargerNombreDeParties(GameEnum.BELOTE, getOwner().getFrames(), 0);
        GameBelote game_=Net.getGames(window().getNet()).partieBelote();
        DealBelote deal_=new DealBelote(nb_,game_.empiler());
        deal_.donneurSuivant(game_.getDistribution().getDealer(),game_.getNombreDeJoueurs());
        deal_.initDonne(game_.getRegles(),getDisplayingBelote(),getOwner().getGenerator());
        Net.getGames(window().getNet()).jouerBelote(new GameBelote(GameType.RANDOM,deal_,game_.getRegles()));
        window().sendObjectPlayGame();
    }

    @Override
    public void changeReady() {
        if (ComparatorBoolean.eq(readyToPlay, ready.isSelected())) {
            return;
        }
        readyToPlay = !readyToPlay;
        Ready choice_ = new Ready();
        choice_.setIndex(noClient);
        choice_.setReady(readyToPlay);
        window().sendObject(choice_);
    }

    @Override
    public void delegateServer() {
        hasCreatedServer = true;
        if (!Net.isProgressingGame(window().getNet())) {
            AbsPanel container_ = getPane();
            AbsButton buttonRules_ = getOwner().getCompoFactory().newPlainButton(getMessages().getVal(WindowNetWork.SELECT_RULES));
            buttonRules_.addActionListener(new ChangeRulesEvent(this));
            container_.add(buttonRules_);
            AbsButton button_ = getOwner().getCompoFactory().newPlainButton(getMessages().getVal(WindowNetWork.PLAY_BELOTE));
            button_.addActionListener(new PlayFirstDealEvent(this));
            container_.add(button_);
            pack();
            //PackingWindowAfter.pack(this, true);
        }
    }

    @Override
    public void changeRules() {
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        DialogRulesBelote.initDialogRulesBelote(
                GameEnum.BELOTE.toString(lg_), window(), rulesBeloteMulti, new AfterValidateRulesBeloteMulti(this));
    }

    @Override
    public void dealFirst() {
        boolean allReady_ = window().getSockets().allReady();
        if (!allReady_) {
            return;
        }
        boolean distinct_ = Net.distinctPlaces(window().getNet(),window().getSockets());
        if (!distinct_) {
            return;
        }
        HandBelote pile_;
        /*
        Chargement de la pile de cartes depuis un fichier sinon
        on la cree
        */
        pile_ = chargerPileBelote();
        DealBelote deal_ = new DealBelote(0, pile_);
        deal_.setRandomDealer(rulesBeloteMulti.getDealing().getId().getNombreJoueurs(),getOwner().getGenerator());
        deal_.initDonne(rulesBeloteMulti, getDisplayingBelote(),getOwner().getGenerator());
        Net.getGames(window().getNet()).jouerBelote(new GameBelote(
                GameType.RANDOM, deal_, rulesBeloteMulti));
        window().sendObjectPlayGame();
    }

    @Override
    public WindowNetWork window() {
        return win;
    }

    public boolean isCanBid() {
        return canBid;
    }
    public void setCanBid(boolean _canBid) {
        canBid = _canBid;
    }

    public RulesBelote getRulesBeloteMulti() {
        return rulesBeloteMulti;
    }

    public void setRulesBeloteMulti(RulesBelote _r) {
        this.rulesBeloteMulti = _r;
    }
}

