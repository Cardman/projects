package cards.gui.containers;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import code.gui.ConfirmDialog;
import code.gui.LabelButton;
import code.gui.NumComboBox;
import code.gui.SessionEditorPane;
import code.util.CustList;
import code.util.EnumMap;
import code.util.NatTreeMap;
import code.util.NumberMap;
import code.util.StringList;
import code.util.comparators.ComparatorBoolean;
import code.util.consts.Constants;
import cards.consts.GameType;
import cards.consts.Status;
import cards.facade.enumerations.GameEnum;
import cards.gameresults.ResultsGame;
import cards.gui.MainWindow;
import cards.gui.containers.events.ChangePlaceEvent;
import cards.gui.containers.events.ChangeRulesEvent;
import cards.gui.containers.events.PlayFirstDealEvent;
import cards.gui.containers.events.PlayNextDealEvent;
import cards.gui.containers.events.ReadyEvent;
import cards.gui.containers.events.SlamEvent;
import cards.gui.containers.events.TakeDogEvent;
import cards.gui.containers.events.ValidateDogEvent;
import cards.gui.dialogs.DialogRulesTarot;
import cards.gui.dialogs.DialogTeamsPlayers;
import cards.gui.dialogs.DialogTricksTarot;
import cards.gui.dialogs.FileConst;
import cards.gui.events.ListenerBidTarotMulti;
import cards.gui.events.ListenerCardTarotMultiBeforeDog;
import cards.gui.events.ListenerCardTarotMultiDog;
import cards.gui.events.ListenerCardTarotMultiGame;
import cards.gui.events.ListenerCardTarotMultiHandful;
import cards.gui.events.ListenerHandfulTarot;
import cards.gui.events.ListenerMiseresTarot;
import cards.gui.events.ListenerNoHandfulTarot;
import cards.gui.labels.GraphicTarotCard;
import cards.gui.labels.MiniTarotCard;
import cards.gui.panels.CarpetTarot;
import cards.gui.panels.MiniCarpet;
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
import cards.network.tarot.Dog;
import cards.network.tarot.actions.BiddingSlamAfter;
import cards.network.tarot.actions.BiddingTarot;
import cards.network.tarot.actions.CalledCards;
import cards.network.tarot.actions.DiscardedCard;
import cards.network.tarot.actions.DiscardedTrumps;
import cards.network.tarot.actions.PlayingCardTarot;
import cards.network.tarot.actions.TakeCard;
import cards.network.tarot.actions.ValidateDog;
import cards.network.tarot.displaying.DealtHandTarot;
import cards.network.tarot.displaying.errors.ErrorBidding;
import cards.network.tarot.displaying.errors.ErrorDiscarding;
import cards.network.tarot.displaying.errors.ErrorHandful;
import cards.network.tarot.displaying.errors.ErrorPlaying;
import cards.network.tarot.displaying.players.CalledCardKnown;
import cards.network.tarot.displaying.players.DoneDisplaySlam;
import cards.network.tarot.displaying.players.RefreshHand;
import cards.network.tarot.displaying.players.RefreshingDone;
import cards.network.tarot.displaying.players.SeenDiscardedTrumps;
import cards.network.tarot.displaying.players.ShowDog;
import cards.network.tarot.unlock.AllowBiddingTarot;
import cards.network.tarot.unlock.AllowPlayingTarot;
import cards.network.tarot.unlock.CallableCards;
import cards.network.threads.Net;
import cards.tarot.DealTarot;
import cards.tarot.GameTarot;
import cards.tarot.HandTarot;
import cards.tarot.RulesTarot;
import cards.tarot.TricksHandsTarot;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;

public class ContainerMultiTarot extends ContainerTarot implements ContainerMulti{

    private int noClient;
    private byte indexInGame = CustList.INDEX_NOT_FOUND_ELT;
    private HandTarot cardsInDog = new HandTarot();
    private HandTarot playerHand = new HandTarot();
    private HandTarot takerCardsDog = new HandTarot();
    private int nbChoosenPlayers = CustList.INDEX_NOT_FOUND_ELT;
    private NumComboBox choiceOfPlaceForPlayingGame;
    private JCheckBox ready;

    private DealingTarot repTarot;
    private boolean hasCreatedServer;
    private boolean readyToPlay;
    private CustList<JLabel> playersPseudos = new CustList<JLabel>();
    private CustList<JLabel> playersPlaces = new CustList<JLabel>();
    private CustList<JCheckBox> playersReady = new CustList<JCheckBox>();
    private SessionEditorPane editor;
    private NatTreeMap<Integer,Byte> playersPlacesForGame = new NatTreeMap<Integer,Byte>();
    private NumberMap<Integer,String> playersPseudosForGame = new NumberMap<Integer,String>();
    private RulesTarot rulesTarotMulti=new RulesTarot();

    private EnumMap<Handfuls,Integer> requiredTrumps = new EnumMap<Handfuls,Integer>();
    private JLabel canPlayLabel = new JLabel();

    public ContainerMultiTarot(MainWindow _window, boolean _hasCreatedServer, int _nbPlayers) {
        super(_window);
        initButtonValidateDogTarotMulti();
        initBoutonJeuChelemTarotMulti();
        hasCreatedServer = _hasCreatedServer;
        if (hasCreatedServer) {
            Net.getGames().setRulesBelote(null);
            Net.getGames().setRulesPresident(null);
            rulesTarotMulti = new RulesTarot((byte)_nbPlayers);
            Net.getGames().setRulesTarot(rulesTarotMulti);
        }
    }

    private void ajouterBoutonContratTarotMulti(String _texte,BidTarot _action) {
        JPanel panneau_=getPanneauBoutonsJeu();
        LabelButton bouton_=new LabelButton(_texte);
//        bouton_.addActionListener(new EcouteurBoutonContratTarotMulti(_action));
        bouton_.addMouseListener(new ListenerBidTarotMulti(this,_action));
        panneau_.add(bouton_);
    }

    private void initBoutonJeuChelemTarotMulti() {
        LabelButton bouton_=new LabelButton(BidTarot.SLAM.toString());
//        bouton_.addActionListener(new EcouteurBoutonJeuAnnonceChelemTarotMulti());
        bouton_.addMouseListener(new SlamEvent(this));
        setSlamButton(bouton_);
    }

//    private void ajouterBoutonJeuChelemTarotMulti(String _texte) {
//        JPanel panneau_=getPanneauBoutonsJeu();
//        LabelButton bouton_=new LabelButton(_texte);
////        bouton_.addActionListener(new EcouteurBoutonJeuAnnonceChelemTarotMulti());
//        bouton_.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseReleased(MouseEvent _e) {
//                annonceTarotChelemMulti();
//            }
//        });
//        panneau_.add(bouton_);
//    }
    private void addButtonTakeDogCardsTarotMulti(String _texte,boolean _apte) {
        JPanel panneau_=getPanneauBoutonsJeu();
        LabelButton bouton_=new LabelButton(_texte);
        bouton_.addMouseListener(new TakeDogEvent(this));
        bouton_.setEnabledLabel(_apte);
        panneau_.add(bouton_);
    }

    private void initButtonValidateDogTarotMulti() {
        LabelButton bouton_=new LabelButton(getMessages().getVal(MainWindow.GO_CARD_GAME));
        bouton_.addMouseListener(new ValidateDogEvent(this));
        setValidateDog(bouton_);
    }

    @Override
    public void validateDog() {
        setCanDiscard(false);
        canPlayLabel.setText(EMPTY_STRING);
        if (!takerCardsDog.estVide()) {
            playerHand = takerCardsDog;
            setChienMulti(cardsInDog, false);
        }
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().validate();
        //pack();
        getScrollCallableCards().setVisible(false);
        ValidateDog v_ = new ValidateDog();
        v_.setPlace(indexInGame);
        getOwner().sendObject(v_);
    }
//    private void addButtonValidateDogTarotMulti(String _texte,boolean _apte) {
//        JPanel panneau_=getPanneauBoutonsJeu();
//        LabelButton bouton_=new LabelButton(_texte);
//        bouton_.addMouseListener(new MouseAdapter() {
//
//            @Override
//            public void mouseReleased(MouseEvent _e) {
//                setCanDiscard(false);
//                if (!takerCardsDog.estVide()) {
//                    playerHand = takerCardsDog;
//                    setChienMulti(cardsInDog, false);
//                }
//                getPanneauBoutonsJeu().removeAll();
//                getPanneauBoutonsJeu().validate();
//                //pack();
//                getScrollCallableCards().setVisible(false);
//                ValidateDog v_ = new ValidateDog();
//                v_.setPlace(indexInGame);
//                Net.sendObject(v_);
//            }
//
//        });
//        bouton_.setEnabledLabel(_apte);
//        panneau_.add(bouton_);
//        setValidateDog(bouton_);
//    }
    @Override
    public void updatePlaces(ChoosenPlace _choosePlace) {
        playersPlacesForGame = _choosePlace.getPlacesPlayers();
        playersPlaces.get(_choosePlace.getIndex()).setText(String.valueOf(_choosePlace.getPlace()));
    }
    @Override
    public void updateReady(Ready _readyPlayer) {
        playersReady.get(_readyPlayer.getIndex()).setSelected(_readyPlayer.isReady());
    }
    @Override
    public void updateFirst(PlayersNamePresent _players) {
        getMultiStop().setEnabled(true);
        getTricksHands().setEnabled(true);
        getTeams().setEnabled(true);
        getLoad().setEnabled(false);
        nbChoosenPlayers = _players.getNbPlayers();
        rulesTarotMulti = _players.getRulesTarot();
        JPanel container_=new JPanel();
        container_.setLayout(new BoxLayout(container_, BoxLayout.PAGE_AXIS));
        JPanel panel_ = new JPanel();
        panel_.setLayout(new BoxLayout(panel_, BoxLayout.PAGE_AXIS));
        panel_.add(new JLabel(getMessages().getVal(MainWindow.PLACE)));
        choiceOfPlaceForPlayingGame = new NumComboBox();
        for (int i = CustList.FIRST_INDEX;i<nbChoosenPlayers;i++) {
            choiceOfPlaceForPlayingGame.addItem(i);
        }
        choiceOfPlaceForPlayingGame.setSelectedItem(_players.getPseudos().size()-1);
        indexInGame = choiceOfPlaceForPlayingGame.getCurrent().byteValue();
        choiceOfPlaceForPlayingGame.addActionListener(new ChangePlaceEvent(this));
        panel_.add(choiceOfPlaceForPlayingGame);
        ready = new JCheckBox(getMessages().getVal(MainWindow.READY));
        ready.addActionListener(new ReadyEvent(this));
        panel_.add(ready);
        container_.add(panel_);
        panel_ = new JPanel();
        panel_.setLayout(new GridLayout(0,3));
        playersPseudos.clear();
        for (int i = CustList.FIRST_INDEX;i<nbChoosenPlayers;i++) {
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
            editor.setDataBase(rulesTarotMulti);
            editor.initialize(FileConst.RESOURCES_HTML_FILES_RULES_TAROT);
        } catch (RuntimeException _0) {
            _0.printStackTrace();
        }

        editor.setEditable(false);
        JScrollPane scroll_ = new JScrollPane(editor);
        scroll_.setPreferredSize(new Dimension(300,400));
        container_.add(scroll_);
        playersPlacesForGame = _players.getPlacesPlayers();
        playersPseudosForGame = new NumberMap<Integer,String>(_players.getPseudos());
        for (int i:_players.getPseudos().getKeys()) {
            playersPseudos.get(i).setText(_players.getPseudos().getVal(i));
        }
        for (int i:_players.getPlacesPlayers().getKeys()) {
            playersPlaces.get(i).setText(_players.getPlacesPlayers().getVal(i).toString());
        }
        for (int i:_players.getReadyPlayers().getKeys()) {
            playersReady.get(i).setSelected(_players.getReadyPlayers().getVal(i));
        }

        if (hasCreatedServer) {
            LabelButton buttonRules_ = new LabelButton(getMessages().getVal(MainWindow.SELECT_RULES));
            buttonRules_.addMouseListener(new ChangeRulesEvent(this));
            container_.add(buttonRules_);
            LabelButton button_ = new LabelButton(getMessages().getVal(MainWindow.PLAY_TAROT));
            button_.addMouseListener(new PlayFirstDealEvent(this));
            container_.add(button_);
        }

        container_.add(getWindow().getClock());
        container_.add(getWindow().getLastSavedGameDate());
        setContentPane(container_);
        pack();
        //PackingWindowAfter.pack(this, true);
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
    public void updateAfter(PlayersNamePresent _players) {
        playersPlacesForGame = _players.getPlacesPlayers();
        playersPseudosForGame = new NumberMap<Integer,String>(_players.getPseudos());
        for (int i:_players.getPseudos().getKeys()) {
            playersPseudos.get(i).setText(_players.getPseudos().getVal(i));
        }
        for (int i:_players.getPlacesPlayers().getKeys()) {
            playersPlaces.get(i).setText(_players.getPlacesPlayers().getVal(i).toString());
        }
        for (int i:_players.getReadyPlayers().getKeys()) {
            playersReady.get(i).setSelected(_players.getReadyPlayers().getVal(i));
        }
    }
    public void updateRules(RulesTarot _rules) {
        rulesTarotMulti = _rules;
        try {
            //editor.setMainClass(SoftApplication.getMainClass());
//            editor.setTextFilesWithPrefix(FileConst.RESOURCES_HTML_FOLDER + StreamTextFile.SEPARATEUR);
            editor.setLanguage(Constants.getLanguage());
            editor.setDataBase(rulesTarotMulti);
            editor.initializeHtml(FileConst.RESOURCES_HTML_FILES_RULES_TAROT);
        } catch (RuntimeException _0) {
            _0.printStackTrace();
        }
    }
    public void updateForBeginningGame(DealtHandTarot _hand) {
        repTarot = _hand.getRep();
        placerIhmTarotMulti(_hand.getDog(), _hand.getDealer());
        setCarteEntree(false);
        setCarteSortie(false);
        setCanCall(false);
        setCanDiscard(false);
        setCanExcludeTrumps(false);
        setCanPlay(false);
        playerHand = _hand.getCards();
        playerHand.trier(getDisplayingTarot().getCouleurs(), getDisplayingTarot().getDecroissant());
        /*On place les cartes de l'utilisateur*/
        updateCardsInPanelTarotJeuMulti(getPanelHand(), playerHand);
        for (BidTarot b: _hand.getAllowedBids()) {
            ajouterBoutonContratTarotMulti(b.toString(), b);
        }
        //getPanneauBoutonsJeu().validate();
        pack();
        //PackingWindowAfter.pack(this, true);
        Dealt dealt_ = new Dealt();
        dealt_.setPlace(indexInGame);
        getOwner().sendObject(dealt_);
    }

    public void canBid() {
        setCanBid(true);
    }
    public void canBidTarot(AllowBiddingTarot _bids) {
        setCanBid(true);
        canPlayLabel.setText(getMessages().getVal(MainWindow.CAN_PLAY));
        getPanneauBoutonsJeu().removeAll();
        for (BidTarot b: _bids.getBids()) {
            ajouterBoutonContratTarotMulti(b.toString(Constants.getLanguage()), b);
        }
        //getPanneauBoutonsJeu().validate();
        pack();
        //PackingWindowAfter.pack(this, true);
    }
    public void afterBid() {
        setCanBid(false);
    }
    public void errorForBidding(ErrorBidding _error) {
        String mes_ = StringList.simpleFormat(getMessages().getVal(MainWindow.CANT_BID), _error.getBid());
//        JOptionPane.showMessageDialog(getOwner(),mes_,
//                getMessages().getVal(MainWindow.CANT_BID_TITLE), JOptionPane.INFORMATION_MESSAGE);
        ConfirmDialog.showMessage(getOwner(),mes_,
                getMessages().getVal(MainWindow.CANT_BID_TITLE),
                Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);
    }
    public void displayLastBid(BiddingTarot _bid) {
        canPlayLabel.setText(EMPTY_STRING);
        BiddingTarot bid_ = _bid;

        getEvents().append(getPseudoByPlace(_bid.getPlace())+INTRODUCTION_PTS+bid_.getBid()+RETURN_LINE_CHAR);

        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().validate();
        //pack();
        DoneBidding dealt_ = new DoneBidding();
        dealt_.setPlace(indexInGame);
        getOwner().sendObject(dealt_);
    }

    public void displayCalling(CallableCards _cards) {
        setCanCall(true);
        byte relative_ = relative(_cards.getTakerIndex());
        getMini().setStatus(Status.TAKER, relative_);
        if (_cards.getCallableCards().estVide()) {
            return;
        }
        canPlayLabel.setText(getMessages().getVal(MainWindow.CAN_PLAY));
        setDiscardCall(_cards.isDiscarding());
        getPanneauBoutonsJeu().removeAll();
        getScrollCallableCards().setVisible(true);
        updateCardsInPanelTarotCallBeforeDogMulti(getPanelCallableCards(), _cards.getCallableCards());
        pack();
        //PackingWindowAfter.pack(this, true);
    }

    public void displayDogBeforeCall(Dog _dog) {
        getPanneauBoutonsJeu().removeAll();
        if (_dog.isTaker()) {
            //take the cards
            addButtonTakeDogCardsTarotMulti(getMessages().getVal(MainWindow.TAKE_CARDS), true);
            canPlayLabel.setText(getMessages().getVal(MainWindow.CAN_PLAY));
        }
        //getPanneauBoutonsJeu().validate();
        setDiscardCall(true);
        byte relative_ = relative(_dog.getTakerIndex());
        getMini().setStatus(Status.TAKER, relative_);
        cardsInDog = _dog.getDog();
        setChienMulti(cardsInDog, false);
        pack();
        //PackingWindowAfter.pack(this, true);
        if (!_dog.isHumanTaker()) {
            ShowDog show_ = new ShowDog();
            show_.setPlace(indexInGame);
            getOwner().sendObject(show_);
        }
    }

    public void displayDog(Dog _dog) {
        getPanneauBoutonsJeu().removeAll();
        if (_dog.isTaker()) {
            //take the cards
            addButtonTakeDogCardsTarotMulti(getMessages().getVal(MainWindow.TAKE_CARDS), true);
            canPlayLabel.setText(getMessages().getVal(MainWindow.CAN_PLAY));
        }
        //getPanneauBoutonsJeu().validate();
        byte relative_ = relative(_dog.getTakerIndex());
        getMini().setStatus(Status.TAKER, relative_);
        cardsInDog = _dog.getDog();
        setDiscardCall(_dog.isCallAfter());
        setChienMulti(cardsInDog, false);
        pack();
        //PackingWindowAfter.pack(this, true);
        if (!_dog.isHumanTaker()) {
            ShowDog show_ = new ShowDog();
            show_.setPlace(indexInGame);
            getOwner().sendObject(show_);
        }
    }
    public void displayCalledCard(CalledCards _call) {
        canPlayLabel.setText(EMPTY_STRING);
        byte relative_ = relative(_call.getPlace());
        getMini().setStatus(Status.TAKER, relative_);
        getEvents().append(getPseudoByPlace(_call.getPlace())+INTRODUCTION_PTS+_call.getCalledCards()+RETURN_LINE_CHAR);

        CalledCardKnown dealt_ = new CalledCardKnown();
        dealt_.setPlace(indexInGame);
        getOwner().sendObject(dealt_);
    }
    public void errorDiscardingCard(ErrorDiscarding _error) {
        String mesCard_ = StringList.simpleFormat(getMessages().getVal(MainWindow.CANT_DISCARD), _error.getCard());
        String mesReason_ = StringList.simpleFormat(getMessages().getVal(MainWindow.REASON), _error.getErrorMessage());
//        JOptionPane.showMessageDialog(getOwner(),mesCard_+RETURN_LINE_CHAR+mesReason_, getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE),JOptionPane.ERROR_MESSAGE);
        ConfirmDialog.showMessage(getOwner(),mesCard_+RETURN_LINE_CHAR+mesReason_,
                getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE),
                Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);
    }
    public void displaySlamButton() {
        getPanneauBoutonsJeu().removeAll();
        getSlamButton().setEnabledLabel(true);
        getSlamButton().setVisible(true);
        getPanneauBoutonsJeu().add(getSlamButton());
//        ajouterBoutonJeuChelemTarotMulti(BidTarot.SLAM.toString());
        getValidateDog().setEnabledLabel(true);
        getPanneauBoutonsJeu().add(getValidateDog());
//        addButtonValidateDogTarotMulti(getMessages().getVal(MainWindow.GO_CARD_GAME), true);
        //getPanneauBoutonsJeu().validate();
        pack();
        //PackingWindowAfter.pack(this, true);
    }
    public void showDiscardedTrumps(DiscardedTrumps _discardedTrumps) {
        HandTarot atouts_ = _discardedTrumps.getTrumps();
        getPanelDiscardedTrumps().removeAll();
        for (GraphicTarotCard c: getGraphicCards(atouts_)) {
            getPanelDiscardedTrumps().add(c);
        }
//        boolean entered_ = false;
//        for(CardTarot c:atouts_)
//        {
//            GraphicTarotCard carte_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
//            carte_.setPreferredSize(entered_);
//            getPanelDiscardedTrumps().add(carte_);
//            entered_ = true;
//        }
        getPanelDiscardedTrumps().validate();
        getPanelDiscardedTrumps().setVisible(true);
        getPanelDiscardedTrumps().repaint();
        //pack();
        SeenDiscardedTrumps dis_ = new SeenDiscardedTrumps();
        dis_.setDeclaringSlam(_discardedTrumps.isDeclaringSlam());
        dis_.setPlace(indexInGame);
        getOwner().sendObject(dis_);
    }
    public void displaySlam(BiddingSlamAfter _bidding) {
        BiddingSlamAfter bid_ = _bidding;
        byte relative_ = relative(bid_.getPlace());
        getMini().setStatus(Status.TAKER, relative_);
        getEvents().append(getPseudoByPlace(bid_.getPlace())+INTRODUCTION_PTS+MainWindow.SLAM+RETURN_LINE_CHAR);

        DoneDisplaySlam dis_ = new DoneDisplaySlam();
        dis_.setPlace(indexInGame);
        getOwner().sendObject(dis_);
    }
    public void updateDiscardingOrCanceling(DiscardedCard _discarded) {
        if (_discarded.isInHand()) {
            addCardDog(_discarded.getCard());
        } else {
            removeCardDog(_discarded.getCard());
        }
    }

    public void canPlayTarot(AllowPlayingTarot _declaration) {
        canPlayLabel.setText(getMessages().getVal(MainWindow.CAN_PLAY));
        setCanPlay(true);
        getOwner().getTricksHands().setEnabled(true);
        getOwner().getTeams().setEnabled(true);
        if (!_declaration.isFirstRoundPlaying()) {
            setChoosenHandful(Handfuls.NO);
            for (Miseres m: getSelectedMiseres().getKeys()) {
                getSelectedMiseres().put(m, false);
            }
            return;
        }
        requiredTrumps = _declaration.getRequiredTrumps();
        updateCardsInPanelTarotJeuMulti(getPanelHand(), playerHand);
        setCanExcludeTrumps(true);
        displayTrumpsForHandfulMulti(GameTarot.atoutsPoignee(playerHand.couleurs()));
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().setLayout(new BoxLayout(getPanneauBoutonsJeu(), BoxLayout.PAGE_AXIS));
        JPanel handFuls_ = new JPanel(new FlowLayout(FlowLayout.TRAILING,0,0));
        handFuls_.setLayout(new BoxLayout(handFuls_, BoxLayout.PAGE_AXIS));
        setInfoCurrentHandful(new JTextArea(EMPTY_STRING,1,15));
        JScrollPane scroll_ = new JScrollPane(getInfoCurrentHandful());
        scroll_.setPreferredSize(new Dimension(getEvents().getWidth(),70));
        handFuls_.add(scroll_);
        setChoosenHandful(Handfuls.NO);
        setSelectedMiseres(new EnumMap<Miseres,Boolean>());
        setListHandfuls(new ButtonGroup());
        for (Handfuls h: Handfuls.getNonDeclarableHandFuls()) {
            JRadioButton radio_ = new JRadioButton(h.toString());
            radio_.addMouseListener(new ListenerNoHandfulTarot(this, radio_, h));
            getListHandfuls().add(radio_);
            handFuls_.add(radio_);
        }
        for (Handfuls h: _declaration.getAllowedHandfuls()) {
            JRadioButton radio_ = new JRadioButton(h.toString());
            int diff_ = getCurrentIncludedTrumps().total()-requiredTrumps.getVal(h);
            radio_.setEnabled(diff_ >= 0);
            radio_.addMouseListener(new ListenerHandfulTarot(requiredTrumps.getVal(h), radio_, this, h));
            getListHandfuls().add(radio_);
            handFuls_.add(radio_);
        }
        getPanneauBoutonsJeu().add(handFuls_);
        JPanel miseres_ = new JPanel(new GridLayout(0,1));
        for(Miseres po_:_declaration.getAllowedMiseres()) {
            JCheckBox check_ = new JCheckBox(po_.toString());
            check_.addActionListener(new ListenerMiseresTarot(this,check_,po_));
            getSelectedMiseres().put(po_, false);
            miseres_.add(check_);
        }
        getPanneauBoutonsJeu().add(miseres_);
        //getPanneauBoutonsJeu().validate();
        byte relative_ = relative(_declaration.getTakerIndex());
        try {
            getMini().setStatus(Status.TAKER, relative_);
        } catch (RuntimeException _0) {
        }
        pack();
        //PackingWindowAfter.pack(this, true);
    }
    public void displayPlayedCard(PlayingCardTarot _card) {
        canPlayLabel.setText(EMPTY_STRING);
        PlayingCardTarot card_ = _card;
        byte relative_ = relative(card_.getPlace());
        tapisTarot().setCarteTarot(relative_, card_.getPlayedCard());
        String pseudo_ = getPseudoByPlace(card_.getPlace());
        if (_card.isaCalledCard()) {
            getMini().setStatus(Status.CALLED_PLAYER, relative_);
            ajouterTexteDansZone(pseudo_+INTRODUCTION_PTS+Status.CALLED_PLAYER.toString());

        }
        if (_card.getChoosenHandful() != Handfuls.NO) {
            ajouterTexteDansZone(pseudo_+INTRODUCTION_PTS+_card.getChoosenHandful()+RETURN_LINE_CHAR);

        }
        for(Miseres annonce_:_card.getMiseres()) {
            ajouterTexteDansZone(pseudo_+INTRODUCTION_PTS+annonce_+RETURN_LINE_CHAR);

        }
        if(!_card.getHandful().estVide()) {
            getHandfuls().getVal(relative_).setText(_card.getChoosenHandful().toString());
        }
        JPanel panelToSet_ = getDeclaredHandfuls().getVal(relative_);
        panelToSet_.removeAll();
        _card.getHandful().trier(getDisplayingTarot().getCouleurs(), getDisplayingTarot().getDecroissant());
        for(CardTarot c: _card.getHandful()) {
            MiniTarotCard carte_=new MiniTarotCard(c);
            panelToSet_.add(carte_);
        }
        panelToSet_.validate();
        relative_ = relative(card_.getTakerIndex());
        try {
            getMini().setStatus(Status.TAKER, relative_);
        } catch (RuntimeException _0) {
        }
        //pack();
        DonePlaying dealt_ = new DonePlaying();
        dealt_.setPlace(indexInGame);
        getOwner().sendObject(dealt_);
    }

    public void errorPlayingCard(ErrorHandful _error) {
        setCanExcludeTrumps(true);
        setCanPlay(true);
        String mes_ = StringList.simpleFormat(getMessages().getVal(MainWindow.CANT_DECLARE_DETAIL), _error.getHandful());
//        JOptionPane.showMessageDialog(
//                getOwner(),
//                mes_ + RETURN_LINE_CHAR + _error.getError(),
//                getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE), JOptionPane.ERROR_MESSAGE);
        ConfirmDialog.showMessage(getOwner(),
                mes_ + RETURN_LINE_CHAR + _error.getError(),
                getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE),
                Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);
    }

    public void errorPlayingCard(ErrorPlaying _error) {
        setCanPlay(true);
        String mes_ = StringList.simpleFormat(getMessages().getVal(MainWindow.CANT_PLAY_CARD), _error.getCard());
        String mesReason_ = StringList.simpleFormat(getMessages().getVal(MainWindow.REASON), _error.getReason());
//        JOptionPane.showMessageDialog(
//                getOwner(),
//                mes_ + RETURN_LINE_CHAR + mesReason_,
//                getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE), JOptionPane.ERROR_MESSAGE);
        ConfirmDialog.showMessage(getOwner(),
                mes_ + RETURN_LINE_CHAR + mesReason_,
                getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE),
                Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);
    }
    public void refreshHand(RefreshHand _card) {
        playerHand.jouer(_card.getCard());
        playerHand.trier(getDisplayingTarot().getCouleurs(), getDisplayingTarot().getDecroissant());
        getPanneauBoutonsJeu().removeAll();
        getScrollDeclaringHandful().setVisible(false);
        setCanPlay(false);
        canPlayLabel.setText(EMPTY_STRING);
        getOwner().getTricksHands().setEnabled(false);
        getOwner().getTeams().setEnabled(false);
        /*On place les cartes de l'utilisateur*/
        updateCardsInPanelTarotJeuMulti(getPanelHand(), playerHand);
        pack();
        //PackingWindowAfter.pack(this, true);
        RefreshingDone ref_ = new RefreshingDone();
        ref_.setCard(_card.getCard());
        ref_.setChoosenHandful(_card.getChoosenHandful());
        ref_.setHandful(_card.getHandful());
        ref_.setMiseres(_card.getMiseres());
        ref_.setaCalledCard(_card.isaCalledCard());
        ref_.setPlace(indexInGame);
        getOwner().sendObject(ref_);
    }
    @Override
    public void pauseBetweenTrick() {
        tapisTarot().setCartesTarotJeu((byte) nbChoosenPlayers);
        pack();
        //PackingWindowAfter.pack(this, true);
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
    public void showTricksHands(TricksHandsTarot _tricks) {
        NatTreeMap<Byte,String> pseudos_ = new NatTreeMap<Byte,String>();
        byte p_ = 0;
        for (String s: pseudosTarot((byte) nbChoosenPlayers)) {
            pseudos_.put(p_, s);
            p_++;
        }
        for (byte p: playersPlacesForGame.values()) {
            pseudos_.put(p, getPseudoByPlace(p));
        }
        StringList list_ = new StringList(pseudos_.values());
        DialogTricksTarot.setDialogTricksTarot(getMessages().getVal(MainWindow.HANDS_TRICKS_TAROT), getOwner());
        DialogTricksTarot.init(_tricks, (byte)nbChoosenPlayers, list_, getDisplayingTarot());
    }
    public void showTeams(TeamsPlayers _teams) {
        NatTreeMap<Byte,String> pseudos_ = new NatTreeMap<Byte,String>();
        byte p_ = 0;
        for (String s: pseudosTarot((byte) nbChoosenPlayers)) {
            pseudos_.put(p_, s);
            p_++;
        }
        for (byte p: playersPlacesForGame.values()) {
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
    @Override
    public boolean hasCreatedServer() {
        return hasCreatedServer;
    }
    private void placerIhmTarotMulti(HandTarot _dog, byte _beginPlace) {
        Container container_=new Container();
        container_.setLayout(new BorderLayout());
        container_.add(new JLabel(getMessages().getVal(MainWindow.HELP_GO_MENU),SwingConstants.CENTER),BorderLayout.NORTH);
        CarpetTarot tapis_=new CarpetTarot();
        NatTreeMap<Byte,String> pseudos_ = new NatTreeMap<Byte,String>();
        byte p_ = 0;
        for (String s: pseudosTarot((byte) nbChoosenPlayers)) {
            pseudos_.put(p_, s);
            p_++;
        }
        for (byte p: playersPlacesForGame.values()) {
            byte relative_ = relative(p);
            pseudos_.put(relative_, getPseudoByPlace(p));
        }
        StringList list_ = new StringList(pseudos_.values());
        setMini(new MiniCarpet(list_.size(), getDisplayingTarot().getHoraire(), list_));
        tapis_.initTapisTarot(nbChoosenPlayers,getDisplayingTarot().getHoraire(),_dog.total());
        getTapis().setTapisTarot(tapis_);
        container_.add(tapis_,BorderLayout.CENTER);
        setPanelHand(new JPanel());
        getPanelHand().setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        JPanel panneau_=new JPanel();
        panneau_.add(getPanelHand());
        panneau_.setBackground(Color.BLUE);
        setPanelDiscardedTrumps(new JPanel(new FlowLayout(FlowLayout.LEFT,0,0)));
        getPanelDiscardedTrumps().setVisible(false);
        panneau_.add(getPanelDiscardedTrumps());
        container_.add(panneau_,BorderLayout.SOUTH);
        JPanel panneau2_=new JPanel();
        panneau2_.setLayout(new BoxLayout(panneau2_, BoxLayout.PAGE_AXIS));
        setEvents(new JTextArea(EMPTY,8, 30));
        getEvents().setEditable(false);
        byte relative_ = relative(_beginPlace);
        getEvents().append(getMessages().getVal(MainWindow.PLAYER_HAVING_TO_PLAY)+pseudos_.getVal(relative_)+RETURN_LINE_CHAR);
        panneau2_.add(new JScrollPane(getEvents()));
        panneau2_.add(getMini());
        setDeclaringHandful(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT));
        getDeclaringHandful().setAlignmentY(Component.LEFT_ALIGNMENT);
        getDeclaringHandful().setContinuousLayout(true);
        getDeclaringHandful().setOneTouchExpandable(true);
        setIncludedTrumpsForHandful(new JPanel(new FlowLayout(FlowLayout.CENTER,0,0)));
        JScrollPane scroll_ = new JScrollPane(getIncludedTrumpsForHandful());
        scroll_.setPreferredSize(new Dimension(125,60));
        getDeclaringHandful().setLeftComponent(scroll_);
        setExcludedTrumpsForHandful(new JPanel(new FlowLayout(FlowLayout.CENTER,0,0)));
        scroll_ = new JScrollPane(getExcludedTrumpsForHandful());
        scroll_.setPreferredSize(new Dimension(125,60));
        getDeclaringHandful().setRightComponent(scroll_);
        setScrollDeclaringHandful(new JScrollPane(getDeclaringHandful()));
        getScrollDeclaringHandful().setPreferredSize(new Dimension(250,60));
        getScrollDeclaringHandful().setVisible(false);
        panneau2_.add(getScrollDeclaringHandful());
        setHandfuls(new NumberMap<Byte,JLabel>());
        setDeclaredHandfuls(new NumberMap<Byte,JPanel>());
        JPanel declaredHandfuls_ = new JPanel(new GridLayout(0,1));
        for (byte i = CustList.FIRST_INDEX;i<nbChoosenPlayers;i++) {
            relative_ = relative(i);
            JPanel declaredHandfulGroup_ = new JPanel(new FlowLayout());
            JLabel lab_ = new JLabel(pseudos_.getVal(relative_));
            declaredHandfulGroup_.add(lab_);
            JLabel handful_ = new JLabel(EMPTY_STRING);
            declaredHandfulGroup_.add(handful_);
            getHandfuls().put(relative_, handful_);
            JPanel declaredHandful_ = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
            declaredHandfulGroup_.add(declaredHandful_);
            getDeclaredHandfuls().put(relative_, declaredHandful_);
            declaredHandfuls_.add(declaredHandfulGroup_);
        }
        scroll_ = new JScrollPane(declaredHandfuls_);
        panneau2_.add(scroll_);

        setPanelCallableCards(new JPanel(new FlowLayout(FlowLayout.LEFT,0,0)));
        setScrollCallableCards(new JScrollPane(getPanelCallableCards()));
        getScrollCallableCards().setVisible(false);
        panneau2_.add(getScrollCallableCards());
        JPanel sousPanneau_=new JPanel(new FlowLayout(FlowLayout.TRAILING,0,0));
        sousPanneau_.setLayout(new BoxLayout(sousPanneau_, BoxLayout.PAGE_AXIS));
        setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(sousPanneau_);

        container_.add(panneau2_,BorderLayout.EAST);
        tapisTarot().setTalonTarot(_dog);
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
        for (int i:playersPlacesForGame.getKeys()) {
            if (playersPlacesForGame.getVal(i) == _place) {
                return playersPseudosForGame.getVal(i);
            }
        }
        return EMPTY_STRING;
    }


    private byte relative(byte _otherPlayerIndex) {
        byte iter_ = 0;
        for (byte p=indexInGame;p<nbChoosenPlayers;p++) {
            if (p == _otherPlayerIndex) {
                return iter_;
            }
            iter_ ++;
        }
        for (byte p = CustList.FIRST_INDEX;p<indexInGame;p++) {
            if (p == _otherPlayerIndex) {
                return iter_;
            }
            iter_ ++;
        }
        return iter_;
    }
    @Override
    public void annonceTarotChelem() {
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().validate();
        getScrollCallableCards().setVisible(false);
        pack();
        //PackingWindowAfter.pack(this, true);
        BiddingSlamAfter bid_ = new BiddingSlamAfter();
        bid_.setPlace(indexInGame);
        getOwner().sendObject(bid_);
    }
    public void displayTrumpsForHandfulMulti(HandTarot _trumps) {
        getScrollDeclaringHandful().setVisible(!_trumps.estVide());
        if (getCurrentIncludedTrumps().estVide() && getCurrentExcludedTrumps().estVide()) {
            setCurrentIncludedTrumps(_trumps);
        }
        getCurrentIncludedTrumps().trier(getDisplayingTarot().getCouleurs(), getDisplayingTarot().getDecroissant());
        getCurrentExcludedTrumps().trier(getDisplayingTarot().getCouleurs(), getDisplayingTarot().getDecroissant());
        updateCardsInPanelTarotHandfulMulti(getIncludedTrumpsForHandful(), getCurrentIncludedTrumps(), true);
        updateCardsInPanelTarotHandfulMulti(getExcludedTrumpsForHandful(), getCurrentExcludedTrumps(), false);
        pack();
        //PackingWindowAfter.pack(this, true);
        getDeclaringHandful().setDividerLocation(getDeclaringHandful().getWidth()*9/10);
    }
    private void setChienMulti(HandTarot _main,boolean _ecouteur) {
        JPanel panneau_=tapisTarot().getCenterDeck();
        panneau_.removeAll();
        panneau_.repaint();
        panneau_.setBackground(new Color(0,125,0));
        panneau_.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        _main.trier(getDisplayingTarot().getCouleurs(), getDisplayingTarot().getDecroissant());
        setCanDiscard(_ecouteur);
        updateCardsInPanelTarotDogMulti(tapisTarot().getCenterDeck(), _main, false);
    }
    @Override
    public void prendreCartesChien() {
        HandTarot allCards_ = new HandTarot();
        allCards_.ajouterCartes(playerHand);
        allCards_.ajouterCartes(cardsInDog);
        tapisTarot().retirerCartes();
        cardsInDog.supprimerCartes();
        takerCardsDog = allCards_;
        takerCardsDog.trier(getDisplayingTarot().getCouleurs(), getDisplayingTarot().getDecroissant());
        /*On place les cartes de l'utilisateur*/
        setCanDiscard(true);
        updateCardsInPanelTarotDogMulti(getPanelHand(), allCards_, true);
        JPanel boutons_=getPanneauBoutonsJeu();
        boutons_.removeAll();
        getValidateDog().setVisible(true);
        getValidateDog().setEnabledLabel(false);
        getPanneauBoutonsJeu().add(getValidateDog());
        getSlamButton().setEnabledLabel(true);
        getSlamButton().setVisible(false);
        getPanneauBoutonsJeu().add(getSlamButton());
//        addButtonValidateDogTarotMulti(getMessages().getVal(MainWindow.GO_CARD_GAME), false);
        getPanneauBoutonsJeu().validate();
        pack();
        //PackingWindowAfter.pack(this, true);
        getOwner().sendObject(new TakeCard());
    }
    private void addCardDog(CardTarot _ct) {
        takerCardsDog.jouer(_ct);
        takerCardsDog.trier(getDisplayingTarot().getCouleurs(), getDisplayingTarot().getDecroissant());
        cardsInDog.ajouter(_ct);
        setChienMulti(cardsInDog,true);
//        JPanel boutons_=getPanneauBoutonsJeu();
        boolean chienFait_ = cardsInDog.total()==repTarot.getNombreCartesChien();
//        if (boutons_.getComponentCount() > 0) {
////            LabelButton valide_=(LabelButton)boutons_.getComponent(0);
//            getValidateDog().setEnabledLabel(chienFait_);
//        }
        getValidateDog().setEnabledLabel(chienFait_);
        if(chienFait_) {
            getSlamButton().setEnabledLabel(true);
            getSlamButton().setVisible(true);
//            getPanneauBoutonsJeu().add(getSlamButton());
//            ajouterBoutonJeuChelemTarotMulti(BidTarot.SLAM.toString());
            getPanneauBoutonsJeu().validate();
        }
        /*On place les cartes de l'utilisateur*/
        setCanDiscard(true);
        updateCardsInPanelTarotDogMulti(getPanelHand(),takerCardsDog,true);
        pack();
        //PackingWindowAfter.pack(this, true);
    }
    private void removeCardDog(CardTarot _ct) {
        takerCardsDog.ajouter(_ct);
        takerCardsDog.trier(getDisplayingTarot().getCouleurs(), getDisplayingTarot().getDecroissant());
        cardsInDog.jouer(_ct);
        setChienMulti(cardsInDog,true);
//        JPanel boutons_=getPanneauBoutonsJeu();
//        LabelButton valide_=(LabelButton)boutons_.getComponent(0);
        getValidateDog().setEnabledLabel(false);
        getSlamButton().setVisible(false);
//        if(boutons_.getComponentCount()==2) {
//            boutons_.remove(1);
//            boutons_.validate();
//        }
        /*On place les cartes de l'utilisateur*/
        setCanDiscard(true);
        updateCardsInPanelTarotDogMulti(getPanelHand(),takerCardsDog,true);
        pack();
        //PackingWindowAfter.pack(this, true);
    }
    private void updateCardsInPanelTarotDogMulti(JPanel _panel, HandTarot _hand, boolean _inHand) {
        _panel.removeAll();
        for (GraphicTarotCard c: getGraphicCards(_hand)) {
            c.addMouseListener(new ListenerCardTarotMultiDog(this, c.getCard(),_inHand));
            _panel.add(c);
        }
//        boolean entered_ = false;
//        for(CardTarot c: _hand)
//        {
//            GraphicTarotCard carte_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
//            carte_.setPreferredSize(entered_);
////            carte_.addMouseListener(new EcouteurCarteTarotChienMulti(_hand.carte(indice_),_inHand));
//            carte_.addMouseListener(new ListenerCardTarotMultiDog(this, c,_inHand));
//            _panel.add(carte_);
//            entered_ = true;
//        }
        _panel.validate();
        _panel.repaint();
    }

    private void updateCardsInPanelTarotCallBeforeDogMulti(JPanel _panel, HandTarot _hand) {
        _panel.removeAll();
        for (GraphicTarotCard c: getGraphicCards(_hand)) {
            c.addMouseListener(new ListenerCardTarotMultiBeforeDog(this, c.getCard()));
            _panel.add(c);
        }
//        boolean entered_ = false;
//        for(CardTarot c:_hand) {
//            GraphicTarotCard carte_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
//            carte_.setPreferredSize(entered_);
////            carte_.addMouseListener(new EcouteurCarteTarotCallBeforeDogMulti(c));
//            carte_.addMouseListener(new ListenerCardTarotMultiBeforeDog(this, c));
//            _panel.add(carte_);
//            entered_ = true;
//        }
        _panel.validate();
        _panel.repaint();
    }
    private void updateCardsInPanelTarotHandfulMulti(JPanel _panel, HandTarot _hand, boolean _included) {
        _panel.removeAll();
        for(CardTarot c: _hand) {
            MiniTarotCard carte_=new MiniTarotCard(c);
//            carte_.addMouseListener(new EcouteurCarteTarotHandfulMulti(_hand.carte(indice_),_included));
            carte_.addMouseListener(new ListenerCardTarotMultiHandful(this, c,_included));
            _panel.add(carte_);
        }
        _panel.validate();
        _panel.repaint();
    }
    private void updateCardsInPanelTarotJeuMulti(JPanel _panel, HandTarot _hand) {
        _panel.removeAll();
        for (GraphicTarotCard c: getGraphicCards(_hand)) {
            c.addMouseListener(new ListenerCardTarotMultiGame(this, c.getCard()));
            _panel.add(c);
        }
//        boolean entered_ = false;
//        for(CardTarot c: _hand)
//        {
//            GraphicTarotCard carte_=new GraphicTarotCard(c,SwingConstants.RIGHT, !entered_);
//            carte_.setPreferredSize(entered_);
////            carte_.addMouseListener(new EcouteurCarteTarotJeuMulti(_hand.carte(indice_)));
//            carte_.addMouseListener(new ListenerCardTarotMultiGame(this, c));
//            _panel.add(carte_);
//            entered_ = true;
//        }
        _panel.validate();
        _panel.repaint();
    }

    @Override
    public byte getIndexInGame() {
        return indexInGame;
    }

    public HandTarot getTakerCardsDog() {
        return takerCardsDog;
    }

    public HandTarot getCardsInDog() {
        return cardsInDog;
    }

    public DealingTarot getRepTarot() {
        return repTarot;
    }

    public HandTarot getPlayerHand() {
        return playerHand;
    }

    public EnumMap<Handfuls,Integer> getRequiredTrumps() {
        return requiredTrumps;
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
        try {
            //editor_.setMainClass(SoftApplication.getMainClass());
//            editor_.setTextFilesWithPrefix(FileConst.RESOURCES_HTML_FOLDER + StreamTextFile.SEPARATEUR);
            editor_.setLanguage(Constants.getLanguage());
            editor_.setDataBase(res_);
            editor_.initialize(FileConst.RESOURCES_HTML_FILES_RESULTS_TAROT);
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
            editor_.initialize(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_TAROT);
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
            LabelButton button_ = new LabelButton(getMessages().getVal(MainWindow.PLAY_TAROT));
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
        long nb_=chargerNombreDeParties(GameEnum.TAROT);
//        try {
//            nb_=chargerNombreDeParties(GameEnum.TAROT);
//        } catch (Exception exc_) {
//            nb_=0;
//            exc_.printStackTrace();
//        }
        GameTarot game_ = Net.getGames().partieTarot();
        DealTarot deal_=new DealTarot(nb_,game_.empiler());
        deal_.donneurSuivant(game_.getDistribution().getDonneur(),game_.getRegles());
        deal_.initDonne(game_.getRegles());
        Net.getGames().jouerTarot(new GameTarot(GameType.RANDOM,deal_,game_.getRegles()));
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
            LabelButton button_ = new LabelButton(getMessages().getVal(MainWindow.PLAY_TAROT));
            button_.addMouseListener(new PlayFirstDealEvent(this));
            container_.add(button_);
            pack();
            //PackingWindowAfter.pack(this, true);
        }
    }
    @Override
    public void changeRules() {
        DialogRulesTarot.initDialogRulesTarot(GameEnum.TAROT.toString(), getOwner(), rulesTarotMulti);
        DialogRulesTarot.setTarotDialog(false,nbChoosenPlayers);
        RulesTarot rulesTarotMulti_ = DialogRulesTarot.getRegles();
        if (!DialogRulesTarot.isValidated()) {
            return;
        }
        rulesTarotMulti = rulesTarotMulti_;
        getOwner().sendObject(rulesTarotMulti_);
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
        HandTarot pile_;
        /*Chargement de la pile de cartes depuis un fichier sinon on la cree*/
        try {
            pile_ = chargerPileTarot();
            if (!pile_.validStack()) {
                pile_ = HandTarot.pileBase();
            }
        } catch (RuntimeException _0) {
            _0.printStackTrace();
            pile_ = HandTarot.pileBase();
        }
        DealTarot deal_ = new DealTarot(0,pile_);
        deal_.setRandomDealer(rulesTarotMulti);
        deal_.initDonne(rulesTarotMulti);
        Net.getGames().jouerTarot(new GameTarot(GameType.RANDOM,deal_,rulesTarotMulti));
        getOwner().sendObject(new PlayGame());
    }
}

