package cards.gui.containers;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import cards.consts.GameType;
import cards.consts.Status;
import cards.facade.Games;
import cards.facade.enumerations.GameEnum;
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
import cards.tarot.*;
import cards.tarot.beans.TarotStandards;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.DealingTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.util.CustList;
import code.util.EnumMap;
import code.util.*;
import code.util.StringList;
import code.util.comparators.ComparatorBoolean;

public class ContainerMultiTarot extends ContainerTarot implements ContainerMulti{

    private int noClient;
    private byte indexInGame = CustList.INDEX_NOT_FOUND_ELT;
    private HandTarot cardsInDog = new HandTarot();
    private HandTarot playerHand = new HandTarot();
    private HandTarot takerCardsDog = new HandTarot();
    private int nbChoosenPlayers = CustList.INDEX_NOT_FOUND_ELT;
    private NumComboBox choiceOfPlaceForPlayingGame;
    private CustCheckBox ready;

    private DealingTarot repTarot;
    private boolean hasCreatedServer;
    private boolean readyToPlay;
    private CustList<TextLabel> playersPseudos = new CustList<TextLabel>();
    private CustList<TextLabel> playersPlaces = new CustList<TextLabel>();
    private CustList<CustCheckBox> playersReady = new CustList<CustCheckBox>();
    private RenderedPage editor;
    private IntTreeMap<Byte> playersPlacesForGame = new IntTreeMap<Byte>();
    private IntMap<String> playersPseudosForGame = new IntMap<String>();
    private RulesTarot rulesTarotMulti=new RulesTarot();

    private EnumMap<Handfuls,Integer> requiredTrumps = new EnumMap<Handfuls,Integer>();
    private TextLabel canPlayLabel = new TextLabel("");

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
        Panel panneau_=getPanneauBoutonsJeu();
        LabelButton bouton_=new LabelButton(_texte);
//        bouton_.addActionListener(new EcouteurBoutonContratTarotMulti(_action));
        bouton_.addMouseListener(new ListenerBidTarotMulti(this,_action));
        panneau_.add(bouton_);
    }

    private void initBoutonJeuChelemTarotMulti() {
        String lg_ = getOwner().getLanguageKey();
        LabelButton bouton_=new LabelButton(Games.toString(BidTarot.SLAM,lg_));
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
        Panel panneau_=getPanneauBoutonsJeu();
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
        String lg_ = getOwner().getLanguageKey();
        v_.setLocale(lg_);
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
        String lg_ = getOwner().getLanguageKey();
        getMultiStop().setEnabledMenu(true);
        getTricksHands().setEnabledMenu(true);
        getTeams().setEnabledMenu(true);
        getLoad().setEnabledMenu(false);
        nbChoosenPlayers = _players.getNbPlayers();
        rulesTarotMulti = _players.getRulesTarot();
        Panel container_=Panel.newPageBox();
        Panel panel_ = Panel.newPageBox();
        panel_.add(new TextLabel(getMessages().getVal(MainWindow.PLACE)));
        choiceOfPlaceForPlayingGame = new NumComboBox();
        for (int i = CustList.FIRST_INDEX;i<nbChoosenPlayers;i++) {
            choiceOfPlaceForPlayingGame.addItem(i);
        }
        choiceOfPlaceForPlayingGame.setSelectedItem(_players.getPseudos().size()-1);
        indexInGame = choiceOfPlaceForPlayingGame.getCurrent().byteValue();
        choiceOfPlaceForPlayingGame.setListener(new ChangePlaceEvent(this));
        panel_.add(choiceOfPlaceForPlayingGame);
        ready = new CustCheckBox(getMessages().getVal(MainWindow.READY));
        ready.addActionListener(new ReadyEvent(this));
        panel_.add(ready);
        container_.add(panel_);
        panel_ = Panel.newGrid(0,3);
        playersPseudos.clear();
        for (int i = CustList.FIRST_INDEX;i<nbChoosenPlayers;i++) {
            TextLabel pseudo_ = new TextLabel("");
            playersPseudos.add(pseudo_);
            panel_.add(pseudo_);
            TextLabel place_ = new TextLabel("");
            playersPlaces.add(place_);
            panel_.add(place_);
            CustCheckBox ready_ = new CustCheckBox();
            ready_.setEnabled(false);
            playersReady.add(ready_);
            panel_.add(ready_);
        }
        container_.add(panel_);

        ScrollPane scroll_ = new ScrollPane();
        editor = new RenderedPage(scroll_);

        editor.setLanguage(lg_);
        editor.setDataBase(rulesTarotMulti);
        editor.initialize(FileConst.RESOURCES_HTML_FILES_RULES_TAROT, new TarotStandards());

        scroll_.setPreferredSize(new Dimension(300,400));
        container_.add(scroll_);
        playersPlacesForGame = _players.getPlacesPlayers();
        playersPseudosForGame = new IntMap<String>(_players.getPseudos());
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
        choice_.setPlacesPlayers(new IntTreeMap< Byte>());
        getOwner().sendObject(choice_);
    }
    @Override
    public void updateAfter(PlayersNamePresent _players) {
        playersPlacesForGame = _players.getPlacesPlayers();
        playersPseudosForGame = new IntMap<String>(_players.getPseudos());
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
        String lg_ = getOwner().getLanguageKey();
        editor.setLanguage(lg_);
        editor.setDataBase(rulesTarotMulti);
        editor.initializeHtml(FileConst.RESOURCES_HTML_FILES_RULES_TAROT, new TarotStandards());
    }
    public void updateForBeginningGame(DealtHandTarot _hand) {
        repTarot = _hand.getRep();
        String lg_ = getOwner().getLanguageKey();
        placerIhmTarotMulti(_hand.getDog(), _hand.getDealer());
        setCarteEntree(false);
        setCarteSortie(false);
        setCanCall(false);
        setCanDiscard(false);
        setCanExcludeTrumps(false);
        setCanPlay(false);
        playerHand = _hand.getCards();
        playerHand.trier(getDisplayingTarot().getSuits(), getDisplayingTarot().isDecreasing());
        /*On place les cartes de l'utilisateur*/
        updateCardsInPanelTarotJeuMulti(getPanelHand(), playerHand);
        for (BidTarot b: _hand.getAllowedBids()) {
            ajouterBoutonContratTarotMulti(Games.toString(b,lg_), b);
        }
        //getPanneauBoutonsJeu().validate();
        pack();
        //PackingWindowAfter.pack(this, true);
        Dealt dealt_ = new Dealt();
        dealt_.setPlace(indexInGame);
        dealt_.setLocale(lg_);
        getOwner().sendObject(dealt_);
    }

    public void canBid() {
        setCanBid(true);
    }
    public void canBidTarot(AllowBiddingTarot _bids) {
        setCanBid(true);
        canPlayLabel.setText(getMessages().getVal(MainWindow.CAN_PLAY));
        getPanneauBoutonsJeu().removeAll();
        String lg_ = getOwner().getLanguageKey();
        for (BidTarot b: _bids.getBids()) {
            ajouterBoutonContratTarotMulti(Games.toString(b,lg_), b);
        }
        //getPanneauBoutonsJeu().validate();
        pack();
        //PackingWindowAfter.pack(this, true);
    }
    public void afterBid() {
        setCanBid(false);
    }
    public void errorForBidding(ErrorBidding _error) {
        String lg_ = getOwner().getLanguageKey();
        String mes_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CANT_BID), Games.toString(_error.getBid(),lg_));
//        JOptionPane.showMessageDialog(getOwner(),mes_,
//                getMessages().getVal(MainWindow.CANT_BID_TITLE), JOptionPane.INFORMATION_MESSAGE);
        ConfirmDialog.showMessage(getOwner(),mes_,
                getMessages().getVal(MainWindow.CANT_BID_TITLE),
                lg_, JOptionPane.ERROR_MESSAGE);
    }
    public void displayLastBid(BiddingTarot _bid) {
        String lg_ = getOwner().getLanguageKey();
        canPlayLabel.setText(EMPTY_STRING);
        BiddingTarot bid_ = _bid;

        getEvents().append(StringList.concat(getPseudoByPlace(_bid.getPlace()),INTRODUCTION_PTS,Games.toString(bid_.getBid(),lg_),RETURN_LINE));

        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().validate();
        //pack();
        DoneBidding dealt_ = new DoneBidding();
        dealt_.setPlace(indexInGame);
        dealt_.setLocale(lg_);
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
        String lg_ = getOwner().getLanguageKey();
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
            show_.setLocale(lg_);
            getOwner().sendObject(show_);
        }
    }

    public void displayDog(Dog _dog) {
        String lg_ = getOwner().getLanguageKey();
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
            show_.setLocale(lg_);
            getOwner().sendObject(show_);
        }
    }
    public void displayCalledCard(CalledCards _call) {
        String lg_ = getOwner().getLanguageKey();
        canPlayLabel.setText(EMPTY_STRING);
        byte relative_ = relative(_call.getPlace());
        getMini().setStatus(Status.TAKER, relative_);
        getEvents().append(StringList.concat(getPseudoByPlace(_call.getPlace()),INTRODUCTION_PTS,Games.toString(_call.getCalledCards(),lg_),RETURN_LINE));

        CalledCardKnown dealt_ = new CalledCardKnown();
        dealt_.setPlace(indexInGame);
        dealt_.setLocale(lg_);
        getOwner().sendObject(dealt_);
    }
    public void errorDiscardingCard(ErrorDiscarding _error) {
        String lg_ = getOwner().getLanguageKey();
        String mesCard_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CANT_DISCARD), Games.toString(_error.getCard(),lg_));
        String mesReason_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.REASON), _error.getErrorMessage());
//        JOptionPane.showMessageDialog(getOwner(),mesCard_+RETURN_LINE_CHAR+mesReason_, getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE),JOptionPane.ERROR_MESSAGE);
        ConfirmDialog.showMessage(getOwner(),StringList.concat(mesCard_,RETURN_LINE,mesReason_),
                getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE),
                lg_, JOptionPane.ERROR_MESSAGE);
    }
    public void displaySlamButton() {
        getPanneauBoutonsJeu().removeAll();
        getSlamButton().setEnabledLabel(true);
        getSlamButton().setVisibleButton(true);
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
        String lg_ = getOwner().getLanguageKey();
        for (GraphicTarotCard c: getGraphicCards(lg_,atouts_)) {
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
        dis_.setLocale(lg_);
        getOwner().sendObject(dis_);
    }
    public void displaySlam(BiddingSlamAfter _bidding) {
        BiddingSlamAfter bid_ = _bidding;
        byte relative_ = relative(bid_.getPlace());
        getMini().setStatus(Status.TAKER, relative_);
        getEvents().append(StringList.concat(getPseudoByPlace(bid_.getPlace()),INTRODUCTION_PTS,MainWindow.SLAM,RETURN_LINE));

        DoneDisplaySlam dis_ = new DoneDisplaySlam();
        String lg_ = getOwner().getLanguageKey();
        dis_.setLocale(lg_);
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
        String lg_ = getOwner().getLanguageKey();
        setCanPlay(true);
        getOwner().getTricksHands().setEnabledMenu(true);
        getOwner().getTeams().setEnabledMenu(true);
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
        displayTrumpsForHandfulMulti(GameTarotCommonPlaying.atoutsPoignee(playerHand.couleurs()));
        getPanneauBoutonsJeu().removeAll();
        Panel handFuls_ = Panel.newPageBox();
        setInfoCurrentHandful(new TextArea(EMPTY_STRING,1,15));
        ScrollPane scroll_ = new ScrollPane(getInfoCurrentHandful());
        scroll_.setPreferredSize(new Dimension(getEvents().getWidth(),70));
        handFuls_.add(scroll_);
        setChoosenHandful(Handfuls.NO);
        setSelectedMiseres(new EnumMap<Miseres,Boolean>());
        CustList<RadioButton> list_ = new CustList<RadioButton>();
        for (Handfuls h: Handfuls.getNonDeclarableHandFuls()) {
            RadioButton radio_ = new RadioButton(Games.toString(h,lg_));
            list_.add(radio_);
            radio_.addMouseListener(new ListenerNoHandfulTarot(this, radio_, h,list_));
            handFuls_.add(radio_);
        }
        for (Handfuls h: _declaration.getAllowedHandfuls()) {
            RadioButton radio_ = new RadioButton(Games.toString(h,lg_));
            list_.add(radio_);
            int diff_ = getCurrentIncludedTrumps().total()-requiredTrumps.getVal(h);
            radio_.setEnabled(diff_ >= 0);
            radio_.addMouseListener(new ListenerHandfulTarot(requiredTrumps.getVal(h), radio_, this, h,list_));
            handFuls_.add(radio_);
        }
        getPanneauBoutonsJeu().add(handFuls_);
        Panel miseres_ = Panel.newGrid(0,1);
        for(Miseres po_:_declaration.getAllowedMiseres()) {
            CustCheckBox check_ = new CustCheckBox(Games.toString(po_,lg_));
            check_.addActionListener(new ListenerMiseresTarot(this,check_,po_));
            getSelectedMiseres().put(po_, false);
            miseres_.add(check_);
        }
        getPanneauBoutonsJeu().add(miseres_);
        //getPanneauBoutonsJeu().validate();
        byte relative_ = relative(_declaration.getTakerIndex());
        getMini().setStatus(Status.TAKER, relative_);
        pack();
        //PackingWindowAfter.pack(this, true);
    }
    public void displayPlayedCard(PlayingCardTarot _card) {
        canPlayLabel.setText(EMPTY_STRING);
        PlayingCardTarot card_ = _card;
        byte relative_ = relative(card_.getPlace());
        String lg_ = getOwner().getLanguageKey();
        tapisTarot().setCarteTarot(lg_,relative_, card_.getPlayedCard());
        String pseudo_ = getPseudoByPlace(card_.getPlace());
        if (_card.isCalledCard()) {
            getMini().setStatus(Status.CALLED_PLAYER, relative_);
            ajouterTexteDansZone(StringList.concat(pseudo_,INTRODUCTION_PTS,Games.toString(Status.CALLED_PLAYER,lg_)));

        }
        if (_card.getChoosenHandful() != Handfuls.NO) {
            ajouterTexteDansZone(StringList.concat(pseudo_,INTRODUCTION_PTS,Games.toString(_card.getChoosenHandful(),lg_),RETURN_LINE));

        }
        for(Miseres annonce_:_card.getMiseres()) {
            ajouterTexteDansZone(StringList.concat(pseudo_,INTRODUCTION_PTS,Games.toString(annonce_,lg_),RETURN_LINE));

        }
        if(!_card.getHandful().estVide()) {
            getHandfuls().getVal(relative_).setText(Games.toString(_card.getChoosenHandful(),lg_));
        }
        Panel panelToSet_ = getDeclaredHandfuls().getVal(relative_);
        panelToSet_.removeAll();
        _card.getHandful().trier(getDisplayingTarot().getSuits(), getDisplayingTarot().isDecreasing());
        for(CardTarot c: _card.getHandful()) {
            MiniTarotCard carte_=new MiniTarotCard(lg_, c);
            panelToSet_.add(carte_);
        }
        panelToSet_.validate();
        relative_ = relative(card_.getTakerIndex());
        getMini().setStatus(Status.TAKER, relative_);
        //pack();
        DonePlaying dealt_ = new DonePlaying();
        dealt_.setPlace(indexInGame);
        dealt_.setLocale(lg_);
        getOwner().sendObject(dealt_);
    }

    public void errorPlayingCard(ErrorHandful _error) {
        setCanExcludeTrumps(true);
        setCanPlay(true);
        String lg_ = getOwner().getLanguageKey();
        String mes_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CANT_DECLARE_DETAIL), Games.toString(_error.getHandful(),lg_));
//        JOptionPane.showMessageDialog(
//                getOwner(),
//                mes_ + RETURN_LINE_CHAR + _error.getError(),
//                getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE), JOptionPane.ERROR_MESSAGE);
        ConfirmDialog.showMessage(getOwner(),
                StringList.concat(mes_, RETURN_LINE, _error.getError()),
                getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE),
                lg_, JOptionPane.ERROR_MESSAGE);
    }

    public void errorPlayingCard(ErrorPlaying _error) {
        setCanPlay(true);
        String lg_ = getOwner().getLanguageKey();
        String mes_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CANT_PLAY_CARD), Games.toString(_error.getCard(),lg_));
        String mesReason_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.REASON), _error.getReason());
        ConfirmDialog.showMessage(getOwner(),
                StringList.concat(mes_, RETURN_LINE, mesReason_),
                getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE),
                lg_, JOptionPane.ERROR_MESSAGE);
    }
    public void refreshHand(RefreshHand _card) {
        String lg_ = getOwner().getLanguageKey();
        playerHand.jouer(_card.getCard());
        playerHand.trier(getDisplayingTarot().getSuits(), getDisplayingTarot().isDecreasing());
        getPanneauBoutonsJeu().removeAll();
        getScrollDeclaringHandful().setVisible(false);
        setCanPlay(false);
        canPlayLabel.setText(EMPTY_STRING);
        getOwner().getTricksHands().setEnabledMenu(false);
        getOwner().getTeams().setEnabledMenu(false);
        /*On place les cartes de l'utilisateur*/
        updateCardsInPanelTarotJeuMulti(getPanelHand(), playerHand);
        pack();
        //PackingWindowAfter.pack(this, true);
        RefreshingDone ref_ = new RefreshingDone();
        ref_.setCard(_card.getCard());
        ref_.setChoosenHandful(_card.getChoosenHandful());
        ref_.setHandful(_card.getHandful());
        ref_.setMiseres(_card.getMiseres());
        ref_.setCalledCard(_card.isCalledCard());
        ref_.setLocale(lg_);
        ref_.setPlace(indexInGame);
        getOwner().sendObject(ref_);
    }
    @Override
    public void pauseBetweenTrick() {
        String lg_ = getOwner().getLanguageKey();
        tapisTarot().setCartesTarotJeu(lg_,(byte) nbChoosenPlayers);
        pack();
        //PackingWindowAfter.pack(this, true);
        DonePause d_ = new DonePause();
        d_.setPlace(indexInGame);
        d_.setLocale(lg_);
        getOwner().sendObject(d_);
    }
    @Override
    public void showTeams() {
        if (!isCanPlay()) {
            return;
        }
        String lg_ = getOwner().getLanguageKey();
        SelectTeams select_ = new SelectTeams();
        select_.setPlace(indexInGame);
        select_.setLocale(lg_);
        getOwner().sendObject(select_);
    }
    @Override
    public void showTricksHands() {
        if (!isCanPlay()) {
            return;
        }
        String lg_ = getOwner().getLanguageKey();
        SelectTricksHands select_ = new SelectTricksHands();
        select_.setPlace(indexInGame);
        select_.setLocale(lg_);
        getOwner().sendObject(select_);
    }
    public void showTricksHands(TricksHandsTarot _tricks) {
        ByteTreeMap<String> pseudos_ = new ByteTreeMap<String>();
        byte p_ = 0;
        for (String s: pseudosTarot((byte) nbChoosenPlayers)) {
            pseudos_.put(p_, s);
            p_++;
        }
        for (byte p: playersPlacesForGame.values()) {
            pseudos_.put(p, getPseudoByPlace(p));
        }
        StringList list_ = new StringList(pseudos_.values());
        MainWindow ow_ = getOwner();
        DialogTricksTarot.setDialogTricksTarot(getMessages().getVal(MainWindow.HANDS_TRICKS_TAROT), ow_);
        DialogTricksTarot.init(_tricks, (byte)nbChoosenPlayers, list_, getDisplayingTarot(),ow_);
    }
    public void showTeams(TeamsPlayers _teams) {
        ByteTreeMap<String> pseudos_ = new ByteTreeMap<String>();
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
        String lg_ = getOwner().getLanguageKey();
        Panel container_=Panel.newBorder();
        container_.add(new TextLabel(getMessages().getVal(MainWindow.HELP_GO_MENU),SwingConstants.CENTER),BorderLayout.NORTH);
        ByteTreeMap<String> pseudos_ = new ByteTreeMap<String>();
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
        setMini(MiniCarpet.newCarpet(list_.size(), getDisplayingTarot().isClockwise(), list_));
        CarpetTarot tapis_ = CarpetTarot.initTapisTarot(lg_, nbChoosenPlayers, getDisplayingTarot().isClockwise(), _dog.total());
        getTapis().setTapisTarot(tapis_);
        container_.add(tapis_.getContainer(),BorderLayout.CENTER);
        setPanelHand(Panel.newFlow(FlowLayout.LEFT,0,0));
        Panel panneau_=new Panel();
        panneau_.add(getPanelHand());
        panneau_.setBackground(Color.BLUE);
        setPanelDiscardedTrumps(Panel.newFlow(FlowLayout.LEFT,0,0));
        getPanelDiscardedTrumps().setVisible(false);
        panneau_.add(getPanelDiscardedTrumps());
        container_.add(panneau_,BorderLayout.SOUTH);
        Panel panneau2_=Panel.newPageBox();
        setEvents(new TextArea(EMPTY,8, 30));
        getEvents().setEditable(false);
        byte relative_ = relative(_beginPlace);
        getEvents().append(StringList.concat(getMessages().getVal(MainWindow.PLAYER_HAVING_TO_PLAY),pseudos_.getVal(relative_),RETURN_LINE));
        panneau2_.add(new ScrollPane(getEvents()));
        panneau2_.add(getMiniPanel());
        setIncludedTrumpsForHandful(Panel.newFlow(FlowLayout.CENTER,0,0));
        ScrollPane scrollInc_ = new ScrollPane(getIncludedTrumpsForHandful());
        scrollInc_.setPreferredSize(new Dimension(125,60));
        setExcludedTrumpsForHandful(Panel.newFlow(FlowLayout.CENTER,0,0));
        ScrollPane scrollExc_ = new ScrollPane(getExcludedTrumpsForHandful());
        scrollExc_.setPreferredSize(new Dimension(125,60));
        setDeclaringHandful(new SplitPane(JSplitPane.HORIZONTAL_SPLIT,scrollInc_,scrollExc_));
        getDeclaringHandful().setAlignmentY(Component.LEFT_ALIGNMENT);
        getDeclaringHandful().setContinuousLayout(true);
        getDeclaringHandful().setOneTouchExpandable(true);
        setScrollDeclaringHandful(new ScrollPane(getDeclaringHandful()));
        getScrollDeclaringHandful().setPreferredSize(new Dimension(250,60));
        getScrollDeclaringHandful().setVisible(false);
        panneau2_.add(getScrollDeclaringHandful());
        setHandfuls(new ByteMap<TextLabel>());
        setDeclaredHandfuls(new ByteMap<Panel>());
        Panel declaredHandfuls_ = Panel.newGrid(0,1);
        for (byte i = CustList.FIRST_INDEX;i<nbChoosenPlayers;i++) {
            relative_ = relative(i);
            Panel declaredHandfulGroup_ = Panel.newFlow();
            TextLabel lab_ = new TextLabel(pseudos_.getVal(relative_));
            declaredHandfulGroup_.add(lab_);
            TextLabel handful_ = new TextLabel(EMPTY_STRING);
            declaredHandfulGroup_.add(handful_);
            getHandfuls().put(relative_, handful_);
            Panel declaredHandful_ = Panel.newFlow(FlowLayout.LEFT,0,0);
            declaredHandfulGroup_.add(declaredHandful_);
            getDeclaredHandfuls().put(relative_, declaredHandful_);
            declaredHandfuls_.add(declaredHandfulGroup_);
        }
        ScrollPane scroll_ = new ScrollPane(declaredHandfuls_);
        panneau2_.add(scroll_);

        setPanelCallableCards(Panel.newFlow(FlowLayout.LEFT,0,0));
        setScrollCallableCards(new ScrollPane(getPanelCallableCards()));
        getScrollCallableCards().setVisible(false);
        panneau2_.add(getScrollCallableCards());
        Panel sousPanneau_=Panel.newPageBox();
        setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(sousPanneau_);

        container_.add(panneau2_,BorderLayout.EAST);
        tapisTarot().setTalonTarot(lg_,_dog);
        Panel panel_ = Panel.newPageBox();
        panel_.add(new ScrollPane(container_));
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
            iter_++;
        }
        for (byte p = CustList.FIRST_INDEX;p<indexInGame;p++) {
            if (p == _otherPlayerIndex) {
                return iter_;
            }
            iter_++;
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
        String lg_ = getOwner().getLanguageKey();
        BiddingSlamAfter bid_ = new BiddingSlamAfter();
        bid_.setPlace(indexInGame);
        bid_.setLocale(lg_);
        getOwner().sendObject(bid_);
    }
    public void displayTrumpsForHandfulMulti(HandTarot _trumps) {
        getScrollDeclaringHandful().setVisible(!_trumps.estVide());
        if (getCurrentIncludedTrumps().estVide() && getCurrentExcludedTrumps().estVide()) {
            setCurrentIncludedTrumps(_trumps);
        }
        getCurrentIncludedTrumps().trier(getDisplayingTarot().getSuits(), getDisplayingTarot().isDecreasing());
        getCurrentExcludedTrumps().trier(getDisplayingTarot().getSuits(), getDisplayingTarot().isDecreasing());
        updateCardsInPanelTarotHandfulMulti(getIncludedTrumpsForHandful(), getCurrentIncludedTrumps(), true);
        updateCardsInPanelTarotHandfulMulti(getExcludedTrumpsForHandful(), getCurrentExcludedTrumps(), false);
        pack();
        //PackingWindowAfter.pack(this, true);
        getDeclaringHandful().setDividerLocation(getDeclaringHandful().getWidth()*9/10);
    }
    private void setChienMulti(HandTarot _main,boolean _ecouteur) {
        Panel panneau_=tapisTarot().getCenterDeck();
        panneau_.removeAll();
        panneau_.repaint();
        panneau_.setBackground(new Color(0,125,0));
        _main.trier(getDisplayingTarot().getSuits(), getDisplayingTarot().isDecreasing());
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
        takerCardsDog.trier(getDisplayingTarot().getSuits(), getDisplayingTarot().isDecreasing());
        /*On place les cartes de l'utilisateur*/
        setCanDiscard(true);
        updateCardsInPanelTarotDogMulti(getPanelHand(), allCards_, true);
        Panel boutons_=getPanneauBoutonsJeu();
        boutons_.removeAll();
        getValidateDog().setVisibleButton(true);
        getValidateDog().setEnabledLabel(false);
        getPanneauBoutonsJeu().add(getValidateDog());
        getSlamButton().setEnabledLabel(true);
        getSlamButton().setVisibleButton(false);
        getPanneauBoutonsJeu().add(getSlamButton());
//        addButtonValidateDogTarotMulti(getMessages().getVal(MainWindow.GO_CARD_GAME), false);
        getPanneauBoutonsJeu().validate();
        pack();
        //PackingWindowAfter.pack(this, true);
        getOwner().sendObject(new TakeCard());
    }
    private void addCardDog(CardTarot _ct) {
        takerCardsDog.jouer(_ct);
        takerCardsDog.trier(getDisplayingTarot().getSuits(), getDisplayingTarot().isDecreasing());
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
            getSlamButton().setVisibleButton(true);
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
        takerCardsDog.trier(getDisplayingTarot().getSuits(), getDisplayingTarot().isDecreasing());
        cardsInDog.jouer(_ct);
        setChienMulti(cardsInDog,true);
//        JPanel boutons_=getPanneauBoutonsJeu();
//        LabelButton valide_=(LabelButton)boutons_.getComponent(0);
        getValidateDog().setEnabledLabel(false);
        getSlamButton().setVisibleButton(false);
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
    private void updateCardsInPanelTarotDogMulti(Panel _panel, HandTarot _hand, boolean _inHand) {
        _panel.removeAll();
        String lg_ = getOwner().getLanguageKey();
        for (GraphicTarotCard c: getGraphicCards(lg_,_hand)) {
            c.addMouseListener(new ListenerCardTarotMultiDog(this, c.getCard(),_inHand,c));
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

    private void updateCardsInPanelTarotCallBeforeDogMulti(Panel _panel, HandTarot _hand) {
        _panel.removeAll();
        String lg_ = getOwner().getLanguageKey();
        for (GraphicTarotCard c: getGraphicCards(lg_,_hand)) {
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
    private void updateCardsInPanelTarotHandfulMulti(Panel _panel, HandTarot _hand, boolean _included) {
        _panel.removeAll();
        String lg_ = getOwner().getLanguageKey();
        for(CardTarot c: _hand) {
            MiniTarotCard carte_=new MiniTarotCard(lg_, c);
//            carte_.addMouseListener(new EcouteurCarteTarotHandfulMulti(_hand.carte(indice_),_included));
            carte_.addMouseListener(new ListenerCardTarotMultiHandful(this, c,_included));
            _panel.add(carte_);
        }
        _panel.validate();
        _panel.repaint();
    }
    private void updateCardsInPanelTarotJeuMulti(Panel _panel, HandTarot _hand) {
        _panel.removeAll();
        String lg_ = getOwner().getLanguageKey();
        for (GraphicTarotCard c: getGraphicCards(lg_,_hand)) {
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

    public void endGame(ResultsTarot _res) {

        /*Descativer aide au jeu*/
        getHelpGame().setEnabledMenu(false);
        getOwner().getTricksHands().setEnabledMenu(false);
        getOwner().getTeams().setEnabledMenu(false);
        Panel container_=Panel.newBorder();
        ScrollPane ascenseur_;
        /*Le nombre de parties jouees depuis le lancement du logiciel*/
        setThreadAnime(false);
        TabbedPane onglets_=new TabbedPane();
        String lg_ = getOwner().getLanguageKey();
        setScores(_res.getScores());
        ScrollPane scroll_=new ScrollPane();
        RenderedPage editor_ = new RenderedPage(scroll_);
        editor_.setLanguage(lg_);
        editor_.setDataBase(_res);
        editor_.initialize(FileConst.RESOURCES_HTML_FILES_RESULTS_TAROT, new TarotStandards());
        scroll_.setPreferredSize(new Dimension(300,300));
        onglets_.add(getMessages().getVal(MainWindow.RESULTS_PAGE),scroll_);
        ascenseur_=new ScrollPane();
        editor_ = new RenderedPage(ascenseur_);
        editor_.setLanguage(lg_);
        editor_.setDataBase(_res);
        editor_.initialize(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_TAROT, new TarotStandards());
        ascenseur_.setPreferredSize(new Dimension(300,300));
        onglets_.add(getMessages().getVal(MainWindow.DETAIL_RESULTS_PAGE),ascenseur_);
        container_.add(onglets_,BorderLayout.CENTER);
        Panel panneau_=Panel.newPageBox();
        readyToPlay = false;
        ready = new CustCheckBox(getMessages().getVal(MainWindow.READY));
        ready.addActionListener(new ReadyEvent(this));
        panneau_.add(ready);

        Panel panel_ = Panel.newGrid(0,3);

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
        ok_.setLocale(lg_);
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
        GameTarot game_ = Net.getGames().partieTarot();
        DealTarot deal_=new DealTarot(nb_,game_.empiler());
        deal_.donneurSuivant(game_.getDistribution().getDealer(),game_.getRegles());
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
            Panel container_ = getPane();
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
        String lg_ = getOwner().getLanguageKey();
        DialogRulesTarot.initDialogRulesTarot(GameEnum.TAROT.toString(lg_), getOwner(), rulesTarotMulti);
        DialogRulesTarot.setTarotDialog(false,nbChoosenPlayers, getOwner());
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
        pile_ = chargerPileTarot();
        if (!pile_.validStack()) {
            pile_ = HandTarot.pileBase();
        }
        DealTarot deal_ = new DealTarot(0,pile_);
        deal_.setRandomDealer(rulesTarotMulti);
        deal_.initDonne(rulesTarotMulti);
        Net.getGames().jouerTarot(new GameTarot(GameType.RANDOM,deal_,rulesTarotMulti));
        getOwner().sendObject(new PlayGame());
    }
}

