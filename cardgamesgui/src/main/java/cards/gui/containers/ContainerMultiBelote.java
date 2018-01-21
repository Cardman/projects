package cards.gui.containers;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import code.gui.ConfirmDialog;
import code.gui.LabelButton;
import code.gui.NumComboBox;
import code.gui.SessionEditorPane;
import code.util.CustList;
import code.util.EqList;
import code.util.NatTreeMap;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.comparators.ComparatorBoolean;
import code.util.consts.Constants;
import cards.belote.BidBeloteSuit;
import cards.belote.DealBelote;
import cards.belote.DeclareHandBelote;
import cards.belote.GameBelote;
import cards.belote.HandBelote;
import cards.belote.RulesBelote;
import cards.belote.TricksHandsBelote;
import cards.belote.beans.BeloteStandards;
import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DealingBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.belote.enumerations.DeclaresBeloteRebelote;
import cards.consts.GameType;
import cards.consts.Status;
import cards.consts.Suit;
import cards.facade.enumerations.GameEnum;
import cards.gameresults.ResultsGame;
import cards.gui.MainWindow;
import cards.gui.containers.events.BidEvent;
import cards.gui.containers.events.ChangeBeloteDeclareEvent;
import cards.gui.containers.events.ChangeBeloteRebeloteEvent;
import cards.gui.containers.events.ChangePlaceEvent;
import cards.gui.containers.events.ChangeRulesEvent;
import cards.gui.containers.events.FoldEvent;
import cards.gui.containers.events.PlayFirstDealEvent;
import cards.gui.containers.events.PlayNextDealEvent;
import cards.gui.containers.events.ReadyEvent;
import cards.gui.dialogs.DialogRulesBelote;
import cards.gui.dialogs.DialogTeamsPlayers;
import cards.gui.dialogs.DialogTricksBelote;
import cards.gui.dialogs.FileConst;
import cards.gui.events.ListenerBidBeloteMulti;
import cards.gui.events.ListenerCardBeloteMultiGame;
import cards.gui.events.SelectPointsEvent;
import cards.gui.events.SelectSuitEvent;
import cards.gui.labels.GraphicBeloteCard;
import cards.gui.labels.LabelPoints;
import cards.gui.labels.MiniBeloteCard;
import cards.gui.labels.SuitLabel;
import cards.gui.panels.CarpetBelote;
import cards.gui.panels.MiniCarpet;
import cards.network.belote.actions.BiddingBelote;
import cards.network.belote.actions.PlayingCardBelote;
import cards.network.belote.displaying.CompletedHand;
import cards.network.belote.displaying.DealtHandBelote;
import cards.network.belote.displaying.RefreshHandBelote;
import cards.network.belote.displaying.errors.ErrorBiddingBelote;
import cards.network.belote.displaying.errors.ErrorPlayingBelote;
import cards.network.belote.displaying.players.RefreshHandPlayingBelote;
import cards.network.belote.displaying.players.RefreshingDoneBelote;
import cards.network.belote.unlock.AllowBiddingBelote;
import cards.network.belote.unlock.AllowPlayingBelote;
import cards.network.common.Dealt;
import cards.network.common.Ok;
import cards.network.common.PlayGame;
import cards.network.common.before.ChoosenPlace;
import cards.network.common.before.PlayersNamePresent;
import cards.network.common.before.Ready;
import cards.network.common.displaying.DoneBidding;
import cards.network.common.displaying.DonePause;
import cards.network.common.displaying.DonePlaying;
import cards.network.common.select.SelectTeams;
import cards.network.common.select.SelectTricksHands;
import cards.network.common.select.TeamsPlayers;
import cards.network.threads.Net;

public class ContainerMultiBelote extends ContainerBelote implements
        ContainerMulti {

    private int noClient;
    private byte indexInGame = CustList.INDEX_NOT_FOUND_ELT;
    private RulesBelote rulesBeloteMulti = new RulesBelote();
    private boolean annonceBelote;
    private boolean annonceBeloteRebelote;
    private NumComboBox choiceOfPlaceForPlayingGame;
    private JCheckBox ready;

    private DealingBelote repBelote;
    private int nbChoosenPlayers = CustList.INDEX_NOT_FOUND_ELT;
    private boolean hasCreatedServer;
    private boolean readyToPlay;
    private CustList<JLabel> playersPseudos = new CustList<JLabel>();
    private CustList<JLabel> playersPlaces = new CustList<JLabel>();
    private CustList<JCheckBox> playersReady = new CustList<JCheckBox>();
    private SessionEditorPane editor;
    private NatTreeMap<Integer, Byte> playersPlacesForGame = new NatTreeMap<Integer, Byte>();
    private NumberMap<Integer,String> playersPseudosForGame = new NumberMap<Integer,String>();
    private HandBelote playerHandBelote = new HandBelote();
    private BidBeloteSuit bidMax = new BidBeloteSuit();
    private JLabel canPlayLabel = new JLabel();

    public ContainerMultiBelote(MainWindow _window, boolean _hasCreatedServer) {
        super(_window);
        hasCreatedServer = _hasCreatedServer;
        if (hasCreatedServer) {
            Net.getGames().setRulesBelote(rulesBeloteMulti);
            Net.getGames().setRulesPresident(null);
            Net.getGames().setRulesTarot(null);
        }
    }

    private void addButtonsForCoinche(int _pts, EqList<BidBeloteSuit> _bids) {
        int square_ = 1;
        Numbers<Integer> points_ = RulesBelote.getPoints();
        int size_ = points_.size();
        while (square_ * square_ < size_) {
            square_ ++;
        }
        setPanneauBoutonsJeuPoints(new JPanel(new GridLayout(0, square_)));
        getPointsButtons().clear();
        for (int p_: points_) {
            LabelPoints label_ = new LabelPoints(p_);
            label_.setEnabledLabel(_pts < p_);
            label_.setToolTipText(Integer.toString(p_));
            label_.addMouseListener(new SelectPointsEvent(this, p_));
            getPointsButtons().add(label_);
            getPanneauBoutonsJeuPoints().add(label_);

//            JToggleButton button_ = new JToggleButton(Integer.toString(p_));
//            button_.setEnabled(_pts < p_);
//            button_.setToolTipText(Integer.toString(p_));
//            button_.addActionListener(new EcouteurPtsContratBelote(p_));
//            getPanneauBoutonsJeuPoints().add(button_);
        }
        getPanneauBoutonsJeu().add(getPanneauBoutonsJeuPoints());
        setBidOk(new LabelButton(MainWindow.OK));
        getBidOk().setEnabledLabel(false);
        getBidOk().addMouseListener(new BidEvent(this));
        JPanel panel_ = new JPanel();
        panel_.setLayout(new BoxLayout(panel_, BoxLayout.PAGE_AXIS));
        JPanel panelSuits_ = new JPanel();
        getBidsButtons().clear();
        for (Suit s: Suit.couleursOrdinaires()) {
            SuitLabel suitLabel_ = new SuitLabel();
            BidBeloteSuit bid_ = new BidBeloteSuit();
            bid_.setCouleur(s);
            bid_.setEnchere(BidBelote.SUIT);
            suitLabel_.setSuit(bid_);

            suitLabel_.addMouseListener(new SelectSuitEvent(this,bid_));
            //suitLabel_.addMouseListener(new EcouteurSuitContratBelote(s));
            suitLabel_.setPreferredSize(new Dimension(20,20));
            getBidsButtons().add(suitLabel_);
            panelSuits_.add(suitLabel_);
        }
        panel_.add(panelSuits_);
        JPanel panelBids_ = new JPanel();
        for (BidBelote b: BidBelote.getNonZeroBids()) {
            if (b.getCouleurDominante()) {
                continue;
            }
            boolean present_ = false;
            for (BidBeloteSuit allowedBid_: _bids) {
                if (allowedBid_.getEnchere() == b) {
                    present_ = true;
                    break;
                }
            }
            if (!present_) {
                continue;
            }
            SuitLabel suitLabel_ = new SuitLabel();
            BidBeloteSuit bid_ = new BidBeloteSuit();
            bid_.setEnchere(b);
            suitLabel_.setSuit(bid_);
//            suitLabel_.addMouseListener(new EcouteurSuitContratBelote(s));
            suitLabel_.addMouseListener(new SelectSuitEvent(this,bid_));

            panelBids_.add(suitLabel_);
            getBidsButtons().add(suitLabel_);

//            JToggleButton buttonSuit_ = new JToggleButton(b.toString());
//            buttonSuit_.addActionListener(new EcouteurGreaterBidBelote(b));
//            panelBids_.add(buttonSuit_);
        }
        panel_.add(panelBids_);
        JPanel panelOk_ = new JPanel();
        LabelButton buttonSuit_ = new LabelButton(BidBelote.FOLD.display());
        buttonSuit_.addMouseListener(new FoldEvent(this));
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
        bidLoc_.setCouleur(getSuit());
        bidLoc_.setEnchere(getBidType());
        bidLoc_.setPoints(getPts());
        BiddingBelote bid_ = new BiddingBelote();
        bid_.setPlace(indexInGame);
        bid_.setBidBelote(bidLoc_);
        getOwner().sendObject(bid_);
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
        getOwner().sendObject(bid_);
    }

    private void ajouterBoutonContratBeloteMulti(String _texte,
            BidBeloteSuit _action) {
        JPanel panneau_ = getPanneauBoutonsJeu();
        LabelButton bouton_ = new LabelButton(_texte);
//        bouton_.addActionListener(new EcouteurBoutonContratBeloteMulti(_action));
        bouton_.addMouseListener(new ListenerBidBeloteMulti(this,_action));
        panneau_.add(bouton_);
    }

    @Override
    public void updateFirst(PlayersNamePresent _players) {
        getMultiStop().setEnabled(true);
        getTricksHands().setEnabled(true);
        getTeams().setEnabled(true);
        getLoad().setEnabled(false);
        rulesBeloteMulti = _players.getRulesBelote();
        nbChoosenPlayers = _players.getNbPlayers();
        JPanel container_ = new JPanel();
        container_.setLayout(new BoxLayout(container_, BoxLayout.PAGE_AXIS));
        JPanel panel_ = new JPanel();
        panel_.setLayout(new GridLayout(0, 2));
        panel_.add(new JLabel(getMessages().getVal(MainWindow.PLACE)));
        choiceOfPlaceForPlayingGame = new NumComboBox();
        for (int i = CustList.FIRST_INDEX; i < nbChoosenPlayers; i++) {
            choiceOfPlaceForPlayingGame.addItem(i);
        }
        choiceOfPlaceForPlayingGame.setSelectedItem(_players.getPseudos()
                .size() - 1);
        indexInGame = choiceOfPlaceForPlayingGame
                .getCurrent().byteValue();
        choiceOfPlaceForPlayingGame.addActionListener(new ChangePlaceEvent(this));
        panel_.add(choiceOfPlaceForPlayingGame);
        ready = new JCheckBox(getMessages().getVal(MainWindow.READY));
        ready.addActionListener(new ReadyEvent(this));
        panel_.add(ready);
        container_.add(panel_);
        panel_ = new JPanel();
        panel_.setLayout(new GridLayout(0, 3));
        playersPseudos.clear();
        for (int i = CustList.FIRST_INDEX; i < nbChoosenPlayers; i++) {
            JLabel pseudo_ = new JLabel();
            playersPseudos.add(pseudo_);
            panel_.add(pseudo_);
            JLabel place_ = new JLabel();
            playersPlaces.add(place_);
            panel_.add(place_);
            JCheckBox ready_ = new JCheckBox();
            ready_.setEnabled(false);
            playersReady.add(ready_);
            panel_.add(ready_);
        }
        container_.add(panel_);

        editor = new SessionEditorPane();
        try {
            //editor.setMainClass(SoftApplication.getMainClass());
//            editor.setTextFilesWithPrefix(FileConst.RESOURCES_HTML_FOLDER + StreamTextFile.SEPARATEUR);
            editor.setLanguage(Constants.getLanguage());
            editor.setDataBase(rulesBeloteMulti);
            editor.initialize(FileConst.RESOURCES_HTML_FILES_RULES_BELOTE);
        } catch (RuntimeException _0) {
            _0.printStackTrace();
        }

        editor.setEditable(false);
        JScrollPane scroll_ = new JScrollPane(editor);
        scroll_.setPreferredSize(new Dimension(300,400));
        container_.add(scroll_);

        playersPlacesForGame = _players.getPlacesPlayers();
        playersPseudosForGame = new NumberMap<Integer,String>(_players.getPseudos());
        for (int i : _players.getPseudos().getKeys()) {
            playersPseudos.get(i).setText(_players.getPseudos().getVal(i));
        }
        for (int i : _players.getPlacesPlayers().getKeys()) {
            playersPlaces.get(i).setText(
                    _players.getPlacesPlayers().getVal(i).toString());
        }
        for (int i : _players.getReadyPlayers().getKeys()) {
            playersReady.get(i).setSelected(_players.getReadyPlayers().getVal(i));
        }
        if (hasCreatedServer) {
            LabelButton buttonRules_ = new LabelButton(getMessages().getVal(MainWindow.SELECT_RULES));
            buttonRules_.addMouseListener(new ChangeRulesEvent(this));
            container_.add(buttonRules_);
            LabelButton button_ = new LabelButton(getMessages().getVal(MainWindow.PLAY_BELOTE));
            button_.addMouseListener(new PlayFirstDealEvent(this));
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
        indexInGame = choiceOfPlaceForPlayingGame.getCurrent().byteValue();
        ChoosenPlace choice_ = new ChoosenPlace();
        choice_.setIndex(noClient);
        choice_.setPlace(indexInGame);
        getOwner().sendObject(choice_);
    }
    @Override
    public void updatePlaces(ChoosenPlace _choosePlace) {
        playersPlacesForGame = _choosePlace.getPlacesPlayers();
        playersPlaces.get(_choosePlace.getIndex()).setText(
                String.valueOf(_choosePlace.getPlace()));
    }

    @Override
    public void updateReady(Ready _readyPlayer) {
        playersReady.get(_readyPlayer.getIndex()).setSelected(
                _readyPlayer.isReady());
    }

    @Override
    public void updateAfter(PlayersNamePresent _players) {
        playersPlacesForGame = _players.getPlacesPlayers();
        playersPseudosForGame = new NumberMap<Integer,String>(_players.getPseudos());
        for (int i : _players.getPseudos().getKeys()) {
            playersPseudos.get(i).setText(_players.getPseudos().getVal(i));
        }
        for (int i : _players.getPlacesPlayers().getKeys()) {
            playersPlaces.get(i).setText(
                    _players.getPlacesPlayers().getVal(i).toString());
        }
        for (int i : _players.getReadyPlayers().getKeys()) {
            playersReady.get(i).setSelected(_players.getReadyPlayers().getVal(i));
        }
    }

    public void updateRules(RulesBelote _rules) {
        rulesBeloteMulti = _rules;
        try {
            //editor.setMainClass(SoftApplication.getMainClass());
//            editor.setTextFilesWithPrefix(FileConst.RESOURCES_HTML_FOLDER + StreamTextFile.SEPARATEUR);
            editor.setLanguage(Constants.getLanguage());
            editor.setDataBase(rulesBeloteMulti);
            editor.initializeHtml(FileConst.RESOURCES_HTML_FILES_RULES_BELOTE);
        } catch (RuntimeException _0) {
            _0.printStackTrace();
        }
    }

    public void updateForBeginningGame(DealtHandBelote _hand) {
        repBelote = _hand.getRep();
        placerIhmBeloteMulti(_hand.getDeck(), _hand.getDealer());

        playerHandBelote = _hand.getCards();
        playerHandBelote.trier(getDisplayingBelote().getCouleurs(),
                getDisplayingBelote().getDecroissant(),
                getDisplayingBelote().getOrdreAvantEncheres());
        setCarteEntree(false);
        setCarteSortie(false);
        setCanCall(false);
        setCanDiscard(false);
        setCanExcludeTrumps(false);
        setCanPlay(false);
        /* On place les cartes de l'utilisateur */
        updateCardsInPanelBeloteMulti(getPanelHand(), playerHandBelote);
        if (repBelote.getRemainingCards() > 0) {
            for (BidBeloteSuit b : _hand.getAllowedBids()) {
                ajouterBoutonContratBeloteMulti(b.display(), b);
            }
        } else {
            addButtonsForCoinche(_hand.getPoints(), _hand.getAllowedBids());
        }
        //PackingWindowAfter.pack(this, true);
        pack();
        Dealt dealt_ = new Dealt();
        dealt_.setPlace(indexInGame);
        getOwner().sendObject(dealt_);
    }

    public void canBid() {
        setCanBid(true);
    }

    public void canBidBelote(AllowBiddingBelote _bids) {
        canPlayLabel.setText(getMessages().getVal(MainWindow.CAN_PLAY));
        setCanBid(true);
        getPanneauBoutonsJeu().removeAll();
        if (repBelote.getRemainingCards() > 0) {
            for (BidBeloteSuit b : _bids.getBids()) {
                ajouterBoutonContratBeloteMulti(b.display(), b);
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
        String mes_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CANT_BID), _error.getBid().display());
//        JOptionPane.showMessageDialog(getOwner(),mes_,
//                getMessages().getVal(MainWindow.CANT_BID_TITLE), JOptionPane.INFORMATION_MESSAGE);
        ConfirmDialog.showMessage(getOwner(), mes_, getMessages().getVal(MainWindow.CANT_BID_TITLE),
                    Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);
    }

    public void displayLastBid(BiddingBelote _bid) {
        BiddingBelote bid_ = _bid;
        if (bid_.getBidBelote().estDemandable(bidMax)) {
            bidMax = bid_.getBidBelote();
        }
        getEvents().append(StringList.concat(getPseudoByPlace(_bid.getPlace()), INTRODUCTION_PTS,
                bid_.getBidBelote().display(), RETURN_LINE));
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().validate();
        //pack();
        DoneBidding dealt_ = new DoneBidding();
        dealt_.setPlace(indexInGame);
        getOwner().sendObject(dealt_);
    }

    public void canPlayBelote(AllowPlayingBelote _declaration) {
        canPlayLabel.setText(getMessages().getVal(MainWindow.CAN_PLAY));
        setCanPlay(true);
        getOwner().getTricksHands().setEnabled(true);
        getOwner().getTeams().setEnabled(true);
        annonceBeloteRebelote = false;
        if (_declaration.isPossibleBeloteRebelote()) {
            JPanel panneau_ = getPanneauBoutonsJeu();
            JCheckBox caseCoche_ = new JCheckBox(
                    DeclaresBeloteRebelote.BELOTE_REBELOTE.display());
            caseCoche_.setEnabled(_declaration.isAllowedBeloteRebelote());
            caseCoche_.addActionListener(new ChangeBeloteRebeloteEvent(this));
            panneau_.add(caseCoche_);
            //panneau_.validate();
        }
        if (!_declaration.isFirstRoundPlaying()) {
            annonceBelote = false;
            pack();
            //PackingWindowAfter.pack(this, true);
            return;
        }

        DeclareHandBelote annonceMain_ = _declaration.getDeclaration();
        if (annonceMain_.getAnnonce() != DeclaresBelote.UNDEFINED) {
            annonceBelote = false;
            JPanel panneau_ = getPanneauBoutonsJeu();
            JCheckBox caseCoche_ = new JCheckBox(StringList.concat(annonceMain_.getAnnonce().display(),
                    INTRODUCTION_PTS, annonceMain_.getMain().display()));
            caseCoche_.addActionListener(new ChangeBeloteDeclareEvent(this));
            panneau_.add(caseCoche_);
            //panneau_.validate();
        }
        byte relative_ = relative(_declaration.getTakerIndex());
        getMini().setStatus(Status.TAKER, relative_);
        pack();
        //PackingWindowAfter.pack(this, true);
    }
    @Override
    public void invertBeloteRebelote() {
        annonceBeloteRebelote = !annonceBeloteRebelote;
    }
    @Override
    public void invertBeloteDeclare() {
        annonceBelote = !annonceBelote;
    }
    public void displayPlayedCard(PlayingCardBelote _card) {
        canPlayLabel.setText(EMPTY_STRING);
        PlayingCardBelote card_ = _card;
        byte relative_ = relative(card_.getPlace());
        tapisBelote().setCarteBelote(relative_, card_.getPlayedCard());

        String pseudo_ = getPseudoByPlace(card_.getPlace());
        if (_card.isDeclaringBeloteRebelote()) {
            ajouterTexteDansZone(StringList.concat(pseudo_, INTRODUCTION_PTS, DeclaresBeloteRebelote.BELOTE_REBELOTE.display(), RETURN_LINE));
        }
        if (_card.isDeclaring()) {
            if (bidMax.getCouleurDominante()) {
                _card.getDeclare()
                        .getMain()
                        .trier(getDisplayingBelote().getCouleurs(),
                                getDisplayingBelote().getDecroissant(),
                                bidMax.getCouleur());
            } else {
                _card.getDeclare()
                        .getMain()
                        .trier(getDisplayingBelote().getCouleurs(),
                                getDisplayingBelote().getDecroissant(),
                                bidMax.getOrdre());
            }
            ajouterTexteDansZone(StringList.concat(pseudo_, INTRODUCTION_PTS, _card.getDeclare().getAnnonce().display(),
                    RETURN_LINE));
            if (!_card.getDeclare().getMain().estVide()) {
                getHandfuls().getVal(relative_).setText(
                        _card.getDeclare().getAnnonce().display());
            }
            JPanel panelToSet_ = getDeclaredHandfuls().getVal(relative_);
            panelToSet_.removeAll();
            for (CardBelote c: _card.getDeclare().getMain()) {
                MiniBeloteCard carte_ = new MiniBeloteCard(c);
                panelToSet_.add(carte_);
            }
        }
        relative_ = relative(card_.getTakerIndex());
        getMini().setStatus(Status.TAKER, relative_);
        //PackingWindowAfter.pack(this, true);
        pack();
        DonePlaying dealt_ = new DonePlaying();
        dealt_.setPlace(indexInGame);
        getOwner().sendObject(dealt_);
    }

    public void errorPlayingCard(ErrorPlayingBelote _error) {
        setCanPlay(true);
        if (!_error.getCards().estVide()) {
            JPanel panneau_ = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            HandBelote cartesBeloteRebelote_ = _error.getCards();
            for (GraphicBeloteCard c: getGraphicCards(cartesBeloteRebelote_)) {
                panneau_.add(c);
            }
//            JOptionPane.showMessageDialog(getOwner(), panneau_,
//                    getMessages().getVal(MainWindow.HAVE_TO_PLAY),
//                    JOptionPane.INFORMATION_MESSAGE);
            ConfirmDialog.showComponent(getOwner(), panneau_,
                    getMessages().getVal(MainWindow.HAVE_TO_PLAY),
                    Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);

            return;
        }
        String mes_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CANT_PLAY_CARD), _error.getCard().display());
        String mesReason_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.REASON), _error.getReason());
//        JOptionPane.showMessageDialog(
//                getOwner(),
//                mes_ + RETURN_LINE_CHAR + mesReason_,
//                getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE), JOptionPane.ERROR_MESSAGE);
        ConfirmDialog.showMessage(getOwner(),
                StringList.concat(mes_, RETURN_LINE, mesReason_),
                getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE),
                Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);
    }

    public void refreshHand(RefreshHandBelote _cards) {
        playerHandBelote.supprimerCartes();
        playerHandBelote.ajouterCartes(_cards.getRefreshedHand());
        if (bidMax.getCouleurDominante()) {
            playerHandBelote.trier(getDisplayingBelote().getCouleurs(),
                    getDisplayingBelote().getDecroissant(), bidMax.getCouleur());
        } else {
            playerHandBelote.trier(getDisplayingBelote().getCouleurs(),
                    getDisplayingBelote().getDecroissant(), bidMax.getOrdre());
        }

        setCarteEntree(false);
        setCarteSortie(false);
        setCanPlay(false);
        byte relative_ = relative(_cards.getTakerIndex());
        getMini().setStatus(Status.TAKER, relative_);

        /* On place les cartes de l'utilisateur */

        updateCardsInPanelBeloteMulti(getPanelHand(), playerHandBelote);
        pack();
        //PackingWindowAfter.pack(this, true);
        CompletedHand completed_ = new CompletedHand();
        completed_.setPlace(indexInGame);
        getOwner().sendObject(completed_);
    }

    public void refreshHand(RefreshHandPlayingBelote _card) {
        playerHandBelote.jouer(_card.getCard());
        if (bidMax.getCouleurDominante()) {
            playerHandBelote.trier(getDisplayingBelote().getCouleurs(),
                    getDisplayingBelote().getDecroissant(), bidMax.getCouleur());
        } else {
            playerHandBelote.trier(getDisplayingBelote().getCouleurs(),
                    getDisplayingBelote().getDecroissant(), bidMax.getOrdre());
        }
        getPanneauBoutonsJeu().removeAll();
        setCanPlay(false);
        getOwner().getTricksHands().setEnabled(false);
        getOwner().getTeams().setEnabled(false);
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
        getOwner().sendObject(ref_);

    }

    @Override
    public void pauseBetweenTrick() {
        tapisBelote().setCartesBeloteJeu((byte) nbChoosenPlayers);
        //pack();
        DonePause d_ = new DonePause();
        d_.setPlace(indexInGame);
        getOwner().sendObject(d_);
    }

    @Override
    public void showTeams() {
        if (!isCanPlay()) {
            return;
        }
        SelectTeams select_ = new SelectTeams();
        select_.setPlace(indexInGame);
        getOwner().sendObject(select_);
    }

    @Override
    public void showTricksHands() {
        if (!isCanPlay()) {
            return;
        }
        SelectTricksHands select_ = new SelectTricksHands();
        select_.setPlace(indexInGame);
        getOwner().sendObject(select_);
    }

    public void showTricksHands(TricksHandsBelote _tricks) {
        NatTreeMap<Byte, String> pseudos_ = new NatTreeMap<Byte, String>();
        byte p_ = 0;
        for (String s : pseudosBelote((byte) nbChoosenPlayers)) {
            pseudos_.put(p_, s);
            p_++;
        }
        for (byte p : playersPlacesForGame.values()) {
            pseudos_.put(p, getPseudoByPlace(p));
        }
        StringList list_ = new StringList(pseudos_.values());
        DialogTricksBelote.setDialogTricksBelote(
                getMessages().getVal(MainWindow.HANDS_TRICKS_BELOTE), getOwner());
        DialogTricksBelote.init(_tricks, (byte) nbChoosenPlayers, list_,
                getDisplayingBelote());
    }

    public void showTeams(TeamsPlayers _teams) {
        NatTreeMap<Byte, String> pseudos_ = new NatTreeMap<Byte, String>();
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
        DialogTeamsPlayers.setDialogTeamsPlayers(list_, _teams);
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
        Container container_ = new Container();
        container_.setLayout(new BorderLayout());
        container_.add(new JLabel(getMessages().getVal(MainWindow.HELP_GO_MENU),
                SwingConstants.CENTER), BorderLayout.NORTH);
        CarpetBelote tapis_ = new CarpetBelote();
        NatTreeMap<Byte, String> pseudos_ = new NatTreeMap<Byte, String>();
        byte p_ = 0;
        for (String s : pseudosBelote((byte) nbChoosenPlayers)) {
            pseudos_.put(p_, s);
            p_++;
        }
        for (byte p : playersPlacesForGame.values()) {
            byte relative_ = relative(p);
            pseudos_.put(relative_, getPseudoByPlace(p));
        }
        StringList list_ = new StringList(pseudos_.values());
        setMini(new MiniCarpet(nbChoosenPlayers, getDisplayingBelote().getHoraire(), list_));
        tapis_.initTapisBelote(nbChoosenPlayers, getDisplayingBelote().getHoraire(),
                list_, 1);
        getTapis().setTapisBelote(tapis_);
        container_.add(tapis_, BorderLayout.CENTER);
        JPanel panneau_ = new JPanel();
        panneau_.setBackground(Color.BLUE);
        panneau_.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setPanelHand(panneau_);
        container_.add(panneau_, BorderLayout.SOUTH);
        JPanel panneau2_ = new JPanel();
        panneau2_.setLayout(new BoxLayout(panneau2_, BoxLayout.PAGE_AXIS));
        setEvents(new JTextArea(EMPTY, 8, 30));
        byte relative_ = relative(_beginPlace);
        getEvents().append(StringList.concat(getMessages().getVal(MainWindow.PLAYER_HAVING_TO_PLAY), pseudos_.getVal(relative_), RETURN_LINE));
        getEvents().setEditable(false);
        panneau2_.add(new JScrollPane(getEvents()));
        panneau2_.add(getMini());
        setHandfuls(new NumberMap<Byte,JLabel>());
        setDeclaredHandfuls(new NumberMap<Byte,JPanel>());
        JPanel declaredHandfuls_ = new JPanel(new GridLayout(0, 1));
        for (byte i = CustList.FIRST_INDEX; i < nbChoosenPlayers; i++) {
            JPanel declaredHandfulGroup_ = new JPanel(new FlowLayout());
            JLabel lab_ = new JLabel(pseudos_.getVal(i));
            declaredHandfulGroup_.add(lab_);
            JLabel handful_ = new JLabel(EMPTY_STRING);
            declaredHandfulGroup_.add(handful_);
            getHandfuls().put(i, handful_);
            JPanel declaredHandful_ = new JPanel(new FlowLayout(
                    FlowLayout.LEFT, 0, 0));
            declaredHandfulGroup_.add(declaredHandful_);
            getDeclaredHandfuls().put(i, declaredHandful_);
            declaredHandfuls_.add(declaredHandfulGroup_);
        }
        JScrollPane scroll_ = new JScrollPane(declaredHandfuls_);
        panneau2_.add(scroll_);
        JPanel sousPanneau_ = new JPanel(new GridLayout(0, 1));
        setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(sousPanneau_);
        container_.add(panneau2_, BorderLayout.EAST);
        if (!_cardsOnDeck.estVide()) {
            tapisBelote().setTalonBelote(_cardsOnDeck);
        }
        JPanel panel_ = new JPanel();
        panel_.setLayout(new BoxLayout(panel_, BoxLayout.PAGE_AXIS));
        panel_.add(new JScrollPane(container_));
        canPlayLabel.setText(EMPTY_STRING);
        panel_.add(canPlayLabel);
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
        for (byte p = CustList.FIRST_INDEX; p < indexInGame; p++) {
            if (p == _otherPlayerIndex) {
                return iter_;
            }
            iter_++;
        }
        return iter_;
    }

    private void updateCardsInPanelBeloteMulti(JPanel _panel, HandBelote _hand) {
        _panel.removeAll();
        for (GraphicBeloteCard c: getGraphicCards(_hand)) {
            c.addMouseListener(new ListenerCardBeloteMultiGame(this,c.getCard()));
            _panel.add(c);
        }
        _panel.validate();
        _panel.repaint();
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

    public boolean isAnnonceBeloteRebelote() {
        return annonceBeloteRebelote;
    }

    public boolean isAnnonceBelote() {
        return annonceBelote;
    }

    @Override
    public boolean hasCreatedServer() {
        return hasCreatedServer;
    }

    @Override
    public byte getIndexInGame() {
        return indexInGame;
    }

    @Override
    public void endGame(ResultsGame _res) {
        /*Descativer aide au jeu*/
        getHelpGame().setEnabled(false);
        getOwner().getTricksHands().setEnabled(false);
        getOwner().getTeams().setEnabled(false);
        Container container_=new Container();
        JScrollPane ascenseur_;
        container_.setLayout(new BorderLayout());

        /*Le nombre de parties jouees depuis le lancement du logiciel*/
        setThreadAnime(false);

        JTabbedPane onglets_=new JTabbedPane();
        ResultsGame res_ = _res;
        setScores(res_.getScores());

        SessionEditorPane editor_ = new SessionEditorPane();
        BeloteStandards stds_;
        try {
            //editor_.setMainClass(SoftApplication.getMainClass());
//            editor_.setTextFilesWithPrefix(FileConst.RESOURCES_HTML_FOLDER + StreamTextFile.SEPARATEUR);
            editor_.setLanguage(Constants.getLanguage());
            editor_.setDataBase(res_);
            editor_.initialize(FileConst.RESOURCES_HTML_FILES_RESULTS_BELOTE);
        } catch (RuntimeException _0) {
            _0.printStackTrace();
        }
        editor_.setEditable(false);
        JScrollPane scroll_=new JScrollPane(editor_);
        scroll_.setPreferredSize(new Dimension(300,300));
        onglets_.add(getMessages().getVal(MainWindow.RESULTS_PAGE),scroll_);
        editor_ = new SessionEditorPane();
        try {
            //editor_.setMainClass(SoftApplication.getMainClass());
//            editor_.setTextFilesWithPrefix(FileConst.RESOURCES_HTML_FOLDER + StreamTextFile.SEPARATEUR);
            editor_.setLanguage(Constants.getLanguage());
            editor_.setDataBase(res_);
            stds_ = new BeloteStandards();
            editor_.initialize(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_BELOTE,stds_);
        } catch (RuntimeException _0) {
            _0.printStackTrace();
        }
        editor_.setEditable(false);
        ascenseur_=new JScrollPane(editor_);
        ascenseur_.setPreferredSize(new Dimension(300,300));
        onglets_.add(getMessages().getVal(MainWindow.DETAIL_RESULTS_PAGE),ascenseur_);
        container_.add(onglets_,BorderLayout.CENTER);
        JPanel panneau_=new JPanel();
        panneau_.setLayout(new BoxLayout(panneau_, BoxLayout.PAGE_AXIS));
        readyToPlay = false;
        ready = new JCheckBox(getMessages().getVal(MainWindow.READY));
        ready.addActionListener(new ReadyEvent(this));
        panneau_.add(ready);

        JPanel panel_ = new JPanel();
        panel_.setLayout(new GridLayout(0,3));

        for (int i = CustList.FIRST_INDEX;i<nbChoosenPlayers;i++) {
            panel_.add(playersPseudos.get(i));
            panel_.add(playersPlaces.get(i));
            panel_.add(playersReady.get(i));
        }
        panneau_.add(panel_);
        if (hasCreatedServer) {
            LabelButton button_ = new LabelButton(getMessages().getVal(MainWindow.PLAY_BELOTE));
            button_.addMouseListener(new PlayNextDealEvent(this));
            panneau_.add(button_);
        }
        panneau_.add(getWindow().getClock());
        panneau_.add(getWindow().getLastSavedGameDate());
        container_.add(panneau_,BorderLayout.SOUTH);

        setContentPane(container_);
        pack();
        //PackingWindowAfter.pack(this, true);
        Ok ok_ = new Ok();
        ok_.setPlace(indexInGame);
        getOwner().sendObject(ok_);
    }

    @Override
    public void dealNext() {
        boolean allReady_ = Net.allReady();
        if (!allReady_) {
            return;
        }
        boolean distinct_ = Net.distinctPlaces();
        if (!distinct_) {
            return;
        }
        long nb_=chargerNombreDeParties(GameEnum.BELOTE);
//        try {
//            nb_=chargerNombreDeParties(GameEnum.BELOTE);
//        } catch (Exception exc_) {
//            exc_.printStackTrace();
//            nb_=0;
//        }
        GameBelote game_=Net.getGames().partieBelote();
        DealBelote deal_=new DealBelote(nb_,game_.empiler());
        deal_.donneurSuivant(game_.getDistribution().getDonneur(),game_.getNombreDeJoueurs());
        deal_.initDonne(game_.getRegles(),getDisplayingBelote());
        Net.getGames().jouerBelote(new GameBelote(GameType.RANDOM,deal_,game_.getRegles()));
        getOwner().sendObject(new PlayGame());
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
        getOwner().sendObject(choice_);
    }

    @Override
    public void delegateServer() {
        hasCreatedServer = true;
        if (!Net.isProgressingGame()) {
            Container container_ = getContentPane();
            LabelButton buttonRules_ = new LabelButton(getMessages().getVal(MainWindow.SELECT_RULES));
            buttonRules_.addMouseListener(new ChangeRulesEvent(this));
            container_.add(buttonRules_);
            LabelButton button_ = new LabelButton(getMessages().getVal(MainWindow.PLAY_BELOTE));
            button_.addMouseListener(new PlayFirstDealEvent(this));
            container_.add(button_);
            pack();
            //PackingWindowAfter.pack(this, true);
        }
    }

    @Override
    public void changeRules() {
        DialogRulesBelote.initDialogRulesBelote(
                GameEnum.BELOTE.display(), getOwner(), rulesBeloteMulti);
        RulesBelote rulesBeloteMulti_ = DialogRulesBelote.getRegles();
        if (!DialogRulesBelote.isValidated()) {
            return;
        }
        rulesBeloteMulti = rulesBeloteMulti_;
        getOwner().sendObject(rulesBeloteMulti);
    }

    @Override
    public void dealFirst() {
        boolean allReady_ = Net.allReady();
        if (!allReady_) {
            return;
        }
        boolean distinct_ = Net.distinctPlaces();
        if (!distinct_) {
            return;
        }
        HandBelote pile_;
        /*
        Chargement de la pile de cartes depuis un fichier sinon
        on la cree
        */
        try {
            pile_ = chargerPileBelote();
            if (!pile_.validStack()) {
                pile_ = HandBelote.pileBase();
            }
        } catch (RuntimeException _0) {
            _0.printStackTrace();
            pile_ = HandBelote.pileBase();
        }
        DealBelote deal_ = new DealBelote(0, pile_);
        deal_.setRandomDealer(rulesBeloteMulti.getRepartition()
                .getNombreJoueurs());
        deal_.initDonne(rulesBeloteMulti, getDisplayingBelote());
        Net.getGames().jouerBelote(new GameBelote(
                GameType.RANDOM, deal_, rulesBeloteMulti));
        getOwner().sendObject(new PlayGame());
    }
}

