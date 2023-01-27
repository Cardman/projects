package cards.gui.containers;




import cards.consts.GameType;
import cards.consts.Role;
import cards.facade.Games;
import cards.facade.enumerations.GameEnum;
import cards.gui.*;
import cards.gui.containers.events.ChangePlaceEvent;
import cards.gui.containers.events.ChangeRulesEvent;
import cards.gui.containers.events.PlayFirstDealEvent;
import cards.gui.containers.events.PlayNextDealEvent;
import cards.gui.containers.events.ReadyEvent;
import cards.gui.containers.events.SlamEvent;
import cards.gui.containers.events.TakeDogEvent;
import cards.gui.containers.events.ValidateDogEvent;
import cards.gui.dialogs.*;
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
import cards.network.common.*;
import cards.network.common.before.ChoosenPlace;
import cards.network.common.before.PlayersNamePresent;
import cards.network.common.before.Ready;
import cards.network.tarot.Dog;
import cards.network.tarot.actions.*;
import cards.network.tarot.displaying.DealtHandTarot;
import cards.network.tarot.displaying.errors.*;
import cards.network.tarot.displaying.players.*;
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
import code.gui.images.MetaDimension;
import code.network.WindowNetWork;
import code.util.CustList;
import code.util.IdMap;
import code.util.*;
import code.util.StringList;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public class ContainerMultiTarot extends ContainerTarot implements ContainerMulti,ContainerPlayableTarot{

    private int noClient;
    private byte indexInGame = IndexConstants.INDEX_NOT_FOUND_ELT;
    private HandTarot cardsInDog = new HandTarot();
    private HandTarot playerHand = new HandTarot();
    private HandTarot takerCardsDog = new HandTarot();
    private int nbChoosenPlayers = IndexConstants.INDEX_NOT_FOUND_ELT;
    private NumComboBox choiceOfPlaceForPlayingGame;
    private AbsCustCheckBox ready;

    private DealingTarot repTarot;
    private boolean hasCreatedServer;
    private boolean readyToPlay;
    private final CustList<AbsPlainLabel> playersPseudos = new CustList<AbsPlainLabel>();
    private final CustList<AbsPlainLabel> playersPlaces = new CustList<AbsPlainLabel>();
    private final CustList<AbsCustCheckBox> playersReady = new CustList<AbsCustCheckBox>();
    private RenderedPage editor;
    private IntTreeMap<Byte> playersPlacesForGame = new IntTreeMap<Byte>();
    private IntMap<String> playersPseudosForGame = new IntMap<String>();
    private RulesTarot rulesTarotMulti=new RulesTarot();

    private IdMap<Handfuls,Integer> requiredTrumps = new IdMap<Handfuls,Integer>();
    private final AbsPlainLabel canPlayLabel = getOwner().getCompoFactory().newPlainLabel("");
    private final WindowNetWork win;
    public ContainerMultiTarot(WindowNetWork _window, boolean _hasCreatedServer, int _nbPlayers) {
        super(_window);
        win = _window;
        initButtonValidateDogTarotMulti();
        initBoutonJeuChelemTarotMulti();
        hasCreatedServer = _hasCreatedServer;
        if (hasCreatedServer) {
            Net.getGames(_window.getNet()).setRulesBelote(null);
            Net.getGames(_window.getNet()).setRulesPresident(null);
            rulesTarotMulti = new RulesTarot((byte)_nbPlayers);
            Net.getGames(_window.getNet()).setRulesTarot(rulesTarotMulti);
        }
    }

    private void ajouterBoutonContratTarotMulti(String _texte,BidTarot _action) {
        AbsPanel panneau_=getPanneauBoutonsJeu();
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
//        bouton_.addActionListener(new EcouteurBoutonContratTarotMulti(_action));
        bouton_.addActionListener(new ListenerBidTarotMulti(this,_action));
        panneau_.add(bouton_);
    }

    private void initBoutonJeuChelemTarotMulti() {
        String lg_ = getOwner().getLanguageKey();
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(Games.toString(BidTarot.SLAM,lg_));
//        bouton_.addActionListener(new EcouteurBoutonJeuAnnonceChelemTarotMulti());
        bouton_.addActionListener(new SlamEvent(this));
        setSlamButton(bouton_);
    }

//    private void ajouterBoutonJeuChelemTarotMulti(String _texte) {
//        JPanel panneau_=getPanneauBoutonsJeu();
//        LabelButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
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
        AbsPanel panneau_=getPanneauBoutonsJeu();
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new TakeDogEvent(this));
        bouton_.setEnabled(_apte);
        panneau_.add(bouton_);
    }

    private void initButtonValidateDogTarotMulti() {
        AbsPlainButton bouton_=getOwner().getCompoFactory().newPlainButton(getMessages().getVal(WindowNetWork.GO_CARD_GAME));
        bouton_.addActionListener(new ValidateDogEvent(this));
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
        PlayerActionGame v_ = new PlayerActionGame(PlayerActionGameType.VALIDATE_DOG);
        v_.setPlace(indexInGame);
        String lg_ = getOwner().getLanguageKey();
        v_.setLocale(lg_);
        window().sendObject(v_);
    }
//    private void addButtonValidateDogTarotMulti(String _texte,boolean _apte) {
//        JPanel panneau_=getPanneauBoutonsJeu();
//        LabelButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
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
        playersPlaces.get(_choosePlace.getIndex()).setText(Long.toString(_choosePlace.getPlace()));
    }
    @Override
    public void updateReady(Ready _readyPlayer) {
        playersReady.get(_readyPlayer.getIndex()).setSelected(_readyPlayer.isReady());
    }
    @Override
    public void updateFirst(PlayersNamePresent _players) {
        MenuItemUtils.setEnabledMenu(window().getMultiStop(),true);
        MenuItemUtils.setEnabledMenu(window().getTricksHands(),true);
        MenuItemUtils.setEnabledMenu(window().getTeams(),true);
//        MenuItemUtils.setEnabledMenu(getLoad(),false);
        nbChoosenPlayers = _players.getNbPlayers();
        rulesTarotMulti = _players.getRulesTarot();
        AbsPanel container_=getOwner().getCompoFactory().newPageBox();
        AbsPanel panel_ = getOwner().getCompoFactory().newPageBox();
        panel_.add(getOwner().getCompoFactory().newPlainLabel(getMessages().getVal(WindowNetWork.PLACE)));
        choiceOfPlaceForPlayingGame = new NumComboBox(getOwner().getFrames(),getOwner().getFrames().getGeneComboBox());
        for (int i = IndexConstants.FIRST_INDEX; i<nbChoosenPlayers; i++) {
            choiceOfPlaceForPlayingGame.addItem(i);
        }
        choiceOfPlaceForPlayingGame.setSelectedItem(_players.getPseudos().size()-1);
        indexInGame = choiceOfPlaceForPlayingGame.getCurrent().byteValue();
        choiceOfPlaceForPlayingGame.setListener(new ChangePlaceEvent(this));
        panel_.add(choiceOfPlaceForPlayingGame.self());
        ready = getOwner().getCompoFactory().newCustCheckBox(getMessages().getVal(WindowNetWork.READY));
        ready.addActionListener(new ReadyEvent(this));
        panel_.add(ready);
        container_.add(panel_);
        panel_ = getOwner().getCompoFactory().newGrid(0,3);
        playersPseudos.clear();
        for (int i = IndexConstants.FIRST_INDEX; i<nbChoosenPlayers; i++) {
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


        rulesTarotMulti.getCommon().setGeneral(readCoreResource());
        rulesTarotMulti.getCommon().setSpecific(readResource());
        PreparedAnalyzedCards stds_ = retrieve(FileConst.RESOURCES_HTML_FILES_RULES_TAROT);
        ((TarotStandards)stds_.getBeanNatLgNames()).setDataBaseRules(rulesTarotMulti);
        editor = FrameGeneralHelp.initialize(stds_, getOwner().getFrames());

        editor.getScroll().setPreferredSize(new MetaDimension(300,400));
        container_.add(editor.getScroll());
        playersPlacesForGame = _players.getPlacesPlayers();
        playersPseudosForGame = new IntMap<String>(_players.getPseudos());
        for (int i:_players.getPseudos().getKeys()) {
            playersPseudos.get(i).setText(_players.getPseudos().getVal(i));
        }
        for (int i:_players.getPlacesPlayers().getKeys()) {
            playersPlaces.get(i).setText(_players.getPlacesPlayers().getVal(i).toString());
        }
        for (int i:_players.getReadyPlayers().getKeys()) {
            playersReady.get(i).setSelected(_players.getReadyPlayers().getVal(i) == BoolVal.TRUE);
        }

        if (hasCreatedServer) {
            AbsPlainButton buttonRules_ = getOwner().getCompoFactory().newPlainButton(getMessages().getVal(WindowNetWork.SELECT_RULES));
            buttonRules_.addActionListener(new ChangeRulesEvent(this));
            container_.add(buttonRules_);
            AbsPlainButton button_ = getOwner().getCompoFactory().newPlainButton(getMessages().getVal(WindowNetWork.PLAY_TAROT));
            button_.addActionListener(new PlayFirstDealEvent(this));
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
        window().sendObject(choice_);
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
            playersReady.get(i).setSelected(_players.getReadyPlayers().getVal(i) == BoolVal.TRUE);
        }
    }
    public void updateRules(RulesTarot _rules) {
        rulesTarotMulti = _rules;
        rulesTarotMulti.getCommon().setGeneral(readCoreResource());
        rulesTarotMulti.getCommon().setSpecific(readResource());
        PreparedAnalyzedCards stds_ = retrieve(FileConst.RESOURCES_HTML_FILES_RULES_TAROT);
        ((TarotStandards)stds_.getBeanNatLgNames()).setDataBaseRules(rulesTarotMulti);
        FrameGeneralHelp.initialize(stds_, editor);
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
        playerHand.trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
        /*On place les cartes de l'utilisateur*/
        updateCardsInPanelTarotJeuMulti(getPanelHand(), playerHand);
        for (BidTarot b: _hand.getAllowedBids()) {
            ajouterBoutonContratTarotMulti(Games.toString(b,lg_), b);
        }
        //getPanneauBoutonsJeu().validate();
        pack();
        //PackingWindowAfter.pack(this, true);
        PlayerActionGame dealt_ = new PlayerActionGame(PlayerActionGameType.DEALT);
        dealt_.setPlace(indexInGame);
        dealt_.setLocale(lg_);
        window().sendObject(dealt_);
    }

    public void canBid() {
        setCanBid(true);
    }
    public void canBidTarot(AllowBiddingTarot _bids) {
        setCanBid(true);
        canPlayLabel.setText(getMessages().getVal(WindowNetWork.CAN_PLAY));
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
        String mes_ = StringUtil.simpleStringsFormat(getMessages().getVal(WindowNetWork.CANT_BID), Games.toString(_error.getBid(),lg_));
//        JOptionPane.showMessageDialog(getOwner(),mes_,
//                getMessages().getVal(MainWindow.CANT_BID_TITLE), JOptionPane.INFORMATION_MESSAGE);
        getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(),mes_,
                getMessages().getVal(WindowNetWork.CANT_BID_TITLE),
                lg_, GuiConstants.ERROR_MESSAGE);
    }
    public void displayLastBid(BiddingTarot _bid) {
        String lg_ = getOwner().getLanguageKey();
        canPlayLabel.setText(EMPTY_STRING);

        getEvents().append(StringUtil.concat(getPseudoByPlace(_bid.getPlace()),INTRODUCTION_PTS,Games.toString(_bid.getBid(),lg_),RETURN_LINE));

        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().validate();
        //pack();
        PlayerActionGame dealt_ = new PlayerActionGame(PlayerActionGameType.DONE_BIDDING);
        dealt_.setPlace(indexInGame);
        dealt_.setLocale(lg_);
        window().sendObject(dealt_);
    }

    public void displayCalling(CallableCards _cards) {
        setCanCall(true);
        byte relative_ = relative(_cards.getTakerIndex());
        getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, relative_);
        if (_cards.getCallableCards().estVide()) {
            return;
        }
        canPlayLabel.setText(getMessages().getVal(WindowNetWork.CAN_PLAY));
        setDiscardCall(_cards.isDiscarding());
        getPanneauBoutonsJeu().removeAll();
        getScrollCallableCards().setVisible(true);
        updateCardsInPanelTarotCallBeforeDogMulti(getPanelCallableCards(), _cards.getCallableCards());
        pack();
        //PackingWindowAfter.pack(this, true);
    }

//    public void displayDogBeforeCall(Dog _dog) {
//        String lg_ = getOwner().getLanguageKey();
//        getPanneauBoutonsJeu().removeAll();
//        if (_dog.isTaker()) {
//            //take the cards
//            addButtonTakeDogCardsTarotMulti(getMessages().getVal(WindowNetWork.TAKE_CARDS), true);
//            canPlayLabel.setText(getMessages().getVal(WindowNetWork.CAN_PLAY));
//        }
//        //getPanneauBoutonsJeu().validate();
//        setDiscardCall(true);
//        byte relative_ = relative(_dog.getTakerIndex());
//        getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, relative_);
//        cardsInDog = _dog.getDog();
//        setChienMulti(cardsInDog, false);
//        pack();
//        //PackingWindowAfter.pack(this, true);
//        if (!_dog.isHumanTaker()) {
//            PlayerActionGame show_ = new PlayerActionGame(PlayerActionGameType.SHOW_DOG);
//            show_.setPlace(indexInGame);
//            show_.setLocale(lg_);
//            window().sendObject(show_);
//        }
//    }

    public void displayDog(Dog _dog) {
        String lg_ = getOwner().getLanguageKey();
        getPanneauBoutonsJeu().removeAll();
        if (_dog.isTaker()) {
            //take the cards
            addButtonTakeDogCardsTarotMulti(getMessages().getVal(WindowNetWork.TAKE_CARDS), true);
            canPlayLabel.setText(getMessages().getVal(WindowNetWork.CAN_PLAY));
        }
        //getPanneauBoutonsJeu().validate();
        byte relative_ = relative(_dog.getTakerIndex());
        getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, relative_);
        cardsInDog = _dog.getDog();
        setDiscardCall(_dog.isCallAfter());
        setChienMulti(cardsInDog, false);
        pack();
        //PackingWindowAfter.pack(this, true);
        if (!_dog.isHumanTaker()) {
            PlayerActionGame show_ = new PlayerActionGame(PlayerActionGameType.SHOW_DOG);
            show_.setPlace(indexInGame);
            show_.setLocale(lg_);
            window().sendObject(show_);
        }
    }
    public void displayCalledCard(CalledCards _call) {
        String lg_ = getOwner().getLanguageKey();
        canPlayLabel.setText(EMPTY_STRING);
        byte relative_ = relative(_call.getPlace());
        getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, relative_);
        getEvents().append(StringUtil.concat(getPseudoByPlace(_call.getPlace()),INTRODUCTION_PTS,Games.toString(_call.getCalledCards(),lg_),RETURN_LINE));

        PlayerActionGame dealt_ = new PlayerActionGame(PlayerActionGameType.CALLED_CARD_KNOWN);
        dealt_.setPlace(indexInGame);
        dealt_.setLocale(lg_);
        window().sendObject(dealt_);
    }
    public void errorDiscardingCard(ErrorDiscarding _error) {
        String lg_ = getOwner().getLanguageKey();
        String mesCard_ = StringUtil.simpleStringsFormat(getMessages().getVal(WindowNetWork.CANT_DISCARD), Games.toString(_error.getCard(),lg_));
        String mesReason_ = StringUtil.simpleStringsFormat(getMessages().getVal(WindowNetWork.REASON), _error.getErrorMessage());
//        JOptionPane.showMessageDialog(getOwner(),mesCard_+RETURN_LINE_CHAR+mesReason_, getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE),JOptionPane.ERROR_MESSAGE);
        getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(), StringUtil.concat(mesCard_,RETURN_LINE,mesReason_),
                getMessages().getVal(WindowNetWork.CANT_PLAY_CARD_TITLE),
                lg_, GuiConstants.ERROR_MESSAGE);
    }
    public void displaySlamButton() {
        getPanneauBoutonsJeu().removeAll();
        getSlamButton().setEnabled(true);
        getSlamButton().setVisible(true);
        getPanneauBoutonsJeu().add(getSlamButton());
//        ajouterBoutonJeuChelemTarotMulti(BidTarot.SLAM.toString());
        getValidateDog().setEnabled(true);
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
        for (GraphicTarotCard c: getGraphicCards(getWindow(), lg_,atouts_.getCards())) {
            getPanelDiscardedTrumps().add(c.getPaintableLabel());
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
        //pack();
        SeenDiscardedTrumps dis_ = new SeenDiscardedTrumps();
        dis_.setDeclaringSlam(_discardedTrumps.isDeclaringSlam());
        dis_.setPlace(indexInGame);
        dis_.setLocale(lg_);
        window().sendObject(dis_);
    }
    public void displaySlam(PlayerActionGame _bidding) {
        byte relative_ = relative(_bidding.getPlace());
        getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, relative_);
        getEvents().append(StringUtil.concat(getPseudoByPlace(_bidding.getPlace()),INTRODUCTION_PTS, WindowNetWork.SLAM,RETURN_LINE));

        PlayerActionGame dis_ = new PlayerActionGame(PlayerActionGameType.DONE_DISPLAY_SLAM);
        String lg_ = getOwner().getLanguageKey();
        dis_.setLocale(lg_);
        dis_.setPlace(indexInGame);
        window().sendObject(dis_);
    }
    public void updateDiscardingOrCanceling(DiscardedCard _discarded) {
        if (_discarded.isInHand()) {
            addCardDog(_discarded.getCard());
        } else {
            removeCardDog(_discarded.getCard());
        }
    }

    public void canPlayTarot(AllowPlayingTarot _declaration) {
        canPlayLabel.setText(getMessages().getVal(WindowNetWork.CAN_PLAY));
        String lg_ = getOwner().getLanguageKey();
        setCanPlay(true);
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),true);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),true);
        if (!_declaration.isFirstRoundPlaying()) {
            setChoosenHandful(Handfuls.NO);
            for (Miseres m: getSelectedMiseres().getKeys()) {
                getSelectedMiseres().put(m, BoolVal.FALSE);
            }
            return;
        }
        requiredTrumps = _declaration.getRequiredTrumps();
        updateCardsInPanelTarotJeuMulti(getPanelHand(), playerHand);
        setCanExcludeTrumps(true);
        displayTrumpsForHandfulMulti(GameTarotCommonPlaying.atoutsPoignee(playerHand.couleurs()));
        getPanneauBoutonsJeu().removeAll();
        AbsPanel handFuls_ = getOwner().getCompoFactory().newPageBox();
        setInfoCurrentHandful(getOwner().getCompoFactory().newTextArea(EMPTY_STRING,1,15));
        AbsScrollPane scroll_ = getOwner().getCompoFactory().newAbsScrollPane(getInfoCurrentHandful());
        scroll_.setPreferredSize(new MetaDimension(getEvents().getWidth(),70));
        handFuls_.add(scroll_);
        setChoosenHandful(Handfuls.NO);
        setSelectedMiseres(new IdMap<Miseres,BoolVal>());
        CustList<AbsRadioButton> list_ = new CustList<AbsRadioButton>();
        for (Handfuls h: Handfuls.getNonDeclarableHandFuls()) {
            AbsRadioButton radio_ = getOwner().getCompoFactory().newRadioButton(Games.toString(h,lg_));
            list_.add(radio_);
            radio_.addMouseListener(new ListenerNoHandfulTarot(this, radio_, h,list_));
            handFuls_.add(radio_);
        }
        for (Handfuls h: _declaration.getAllowedHandfuls()) {
            AbsRadioButton radio_ = getOwner().getCompoFactory().newRadioButton(Games.toString(h,lg_));
            list_.add(radio_);
            int diff_ = getCurrentIncludedTrumps().total()-requiredTrumps.getVal(h);
            radio_.setEnabled(diff_ >= 0);
            radio_.addMouseListener(new ListenerHandfulTarot(requiredTrumps.getVal(h), radio_, this, h,list_));
            handFuls_.add(radio_);
        }
        getPanneauBoutonsJeu().add(handFuls_);
        AbsPanel miseres_ = getOwner().getCompoFactory().newGrid(0,1);
        for(Miseres po_:_declaration.getAllowedMiseres()) {
            AbsCustCheckBox check_ = getOwner().getCompoFactory().newCustCheckBox(Games.toString(po_,lg_));
            check_.addActionListener(new ListenerMiseresTarot(this,check_,po_));
            getSelectedMiseres().put(po_, BoolVal.FALSE);
            miseres_.add(check_);
        }
        getPanneauBoutonsJeu().add(miseres_);
        //getPanneauBoutonsJeu().validate();
        byte relative_ = relative(_declaration.getTakerIndex());
        getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, relative_);
        pack();
        //PackingWindowAfter.pack(this, true);
    }
    public void displayPlayedCard(PlayingCardTarot _card) {
        canPlayLabel.setText(EMPTY_STRING);
        byte relative_ = relative(_card.getPlace());
        String lg_ = getOwner().getLanguageKey();
        tapisTarot().setCarteTarot(getWindow().getImageFactory(),lg_,relative_, _card.getPlayedCard(), getWindow().getImages());
        String pseudo_ = getPseudoByPlace(_card.getPlace());
        if (_card.isCalledCard()) {
            getMini().setStatus(getWindow().getImageFactory(),Role.CALLED_PLAYER, relative_);
            ajouterTexteDansZone(StringUtil.concat(pseudo_,INTRODUCTION_PTS,Games.toString(Role.CALLED_PLAYER,lg_)));

        }
        if (_card.getChoosenHandful() != Handfuls.NO) {
            ajouterTexteDansZone(StringUtil.concat(pseudo_,INTRODUCTION_PTS,Games.toString(_card.getChoosenHandful(),lg_),RETURN_LINE));

        }
        for(Miseres annonce_:_card.getMiseres()) {
            ajouterTexteDansZone(StringUtil.concat(pseudo_,INTRODUCTION_PTS,Games.toString(annonce_,lg_),RETURN_LINE));

        }
        if(!_card.getHandful().estVide()) {
            getHandfuls().getVal(relative_).setText(Games.toString(_card.getChoosenHandful(),lg_));
        }
        AbsPanel panelToSet_ = getDeclaredHandfuls().getVal(relative_);
        panelToSet_.removeAll();
        _card.getHandful().trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
        for(CardTarot c: _card.getHandful()) {
            MiniTarotCard carte_=new MiniTarotCard(lg_, c, getOwner().getCompoFactory());
            panelToSet_.add(carte_.getPaintableLabel());
        }
        panelToSet_.validate();
        relative_ = relative(_card.getTakerIndex());
        getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, relative_);
        //pack();
        PlayerActionGame dealt_ = new PlayerActionGame(PlayerActionGameType.DONE_PLAYING);
        dealt_.setPlace(indexInGame);
        dealt_.setLocale(lg_);
        window().sendObject(dealt_);
    }

    public void errorPlayingCard(ErrorHandful _error) {
        setCanExcludeTrumps(true);
        setCanPlay(true);
        String lg_ = getOwner().getLanguageKey();
        String mes_ = StringUtil.simpleStringsFormat(getMessages().getVal(WindowNetWork.CANT_DECLARE_DETAIL), Games.toString(_error.getHandful(),lg_));
//        JOptionPane.showMessageDialog(
//                getOwner(),
//                mes_ + RETURN_LINE_CHAR + _error.getError(),
//                getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE), JOptionPane.ERROR_MESSAGE);
        getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(),
                StringUtil.concat(mes_, RETURN_LINE, _error.getError()),
                getMessages().getVal(WindowNetWork.CANT_PLAY_CARD_TITLE),
                lg_, GuiConstants.ERROR_MESSAGE);
    }

    public void errorPlayingCard(ErrorPlaying _error) {
        setCanPlay(true);
        String lg_ = getOwner().getLanguageKey();
        String mes_ = StringUtil.simpleStringsFormat(getMessages().getVal(WindowNetWork.CANT_PLAY_CARD), Games.toString(_error.getCard(),lg_));
        String mesReason_ = StringUtil.simpleStringsFormat(getMessages().getVal(WindowNetWork.REASON), _error.getReason());
        getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(),
                StringUtil.concat(mes_, RETURN_LINE, mesReason_),
                getMessages().getVal(WindowNetWork.CANT_PLAY_CARD_TITLE),
                lg_, GuiConstants.ERROR_MESSAGE);
    }
    public void refreshHand(RefreshHand _card) {
        String lg_ = getOwner().getLanguageKey();
        playerHand.jouer(_card.getCard());
        playerHand.trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
        getPanneauBoutonsJeu().removeAll();
        getScrollDeclaringHandful().setVisible(false);
        setCanPlay(false);
        canPlayLabel.setText(EMPTY_STRING);
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),false);
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
        window().sendObject(ref_);
    }
    @Override
    public void pauseBetweenTrick() {
        String lg_ = getOwner().getLanguageKey();
        tapisTarot().setCartesTarotJeu(getWindow().getImageFactory(),lg_,(byte) nbChoosenPlayers);
        pack();
        //PackingWindowAfter.pack(this, true);
        PlayerActionGame d_ = new PlayerActionGame(PlayerActionGameType.DONE_PAUSE);
        d_.setPlace(indexInGame);
        d_.setLocale(lg_);
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
        WindowNetWork ow_ = window();
        DialogTricksTarot.setDialogTricksTarot(getMessages().getVal(WindowNetWork.HANDS_TRICKS_TAROT), ow_);
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
    @Override
    public boolean hasCreatedServer() {
        return hasCreatedServer;
    }
    private void placerIhmTarotMulti(HandTarot _dog, byte _beginPlace) {
        MenuItemUtils.setEnabledMenu(window().getMultiStop(),false);
        String lg_ = getOwner().getLanguageKey();
        AbsPanel container_=getOwner().getCompoFactory().newBorder();
        container_.add(getOwner().getCompoFactory().newPlainLabel(getMessages().getVal(WindowNetWork.HELP_GO_MENU)),GuiConstants.BORDER_LAYOUT_NORTH);
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
        setMini(MiniCarpet.newCarpet(getWindow().getImageFactory(),list_.size(), getDisplayingTarot().getDisplaying().isClockwise(), list_, getOwner().getCompoFactory()));
        CarpetTarot tapis_ = CarpetTarot.initTapisTarot(lg_, nbChoosenPlayers, getDisplayingTarot().getDisplaying().isClockwise(), _dog.total(), getOwner().getCompoFactory());
        getTapis().setTapisTarot(tapis_);
        container_.add(tapis_.getContainer(),GuiConstants.BORDER_LAYOUT_CENTER);
        setPanelHand(getOwner().getCompoFactory().newLineBox());
        AbsPanel panneau_=getOwner().getCompoFactory().newLineBox();
        panneau_.add(getPanelHand());
        panneau_.setBackground(GuiConstants.BLUE);
        setPanelDiscardedTrumps(getOwner().getCompoFactory().newLineBox());
        getPanelDiscardedTrumps().setVisible(false);
        panneau_.add(getPanelDiscardedTrumps());
        container_.add(panneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
        AbsPanel panneau2_=getOwner().getCompoFactory().newPageBox();
        setEvents(getOwner().getCompoFactory().newTextArea(EMPTY,8, 30));
        getEvents().setEditable(false);
        byte relative_ = relative(_beginPlace);
        getEvents().append(StringUtil.concat(getMessages().getVal(WindowNetWork.PLAYER_HAVING_TO_PLAY),pseudos_.getVal(relative_),RETURN_LINE));
        panneau2_.add(getOwner().getCompoFactory().newAbsScrollPane(getEvents()));
        panneau2_.add(getMiniPanel());
        setIncludedTrumpsForHandful(getOwner().getCompoFactory().newLineBox());
        AbsScrollPane scrollInc_ = getOwner().getCompoFactory().newAbsScrollPane(getIncludedTrumpsForHandful());
        scrollInc_.setPreferredSize(new MetaDimension(125,60));
        setExcludedTrumpsForHandful(getOwner().getCompoFactory().newLineBox());
        AbsScrollPane scrollExc_ = getOwner().getCompoFactory().newAbsScrollPane(getExcludedTrumpsForHandful());
        scrollExc_.setPreferredSize(new MetaDimension(125,60));
        setDeclaringHandful(getOwner().getCompoFactory().newHorizontalSplitPane(scrollInc_,scrollExc_));
        getDeclaringHandful().setContinuousLayout(true);
        getDeclaringHandful().setOneTouchExpandable(true);
        setScrollDeclaringHandful(getOwner().getCompoFactory().newAbsScrollPane(getDeclaringHandful()));
        getScrollDeclaringHandful().setPreferredSize(new MetaDimension(250,60));
        getScrollDeclaringHandful().setVisible(false);
        panneau2_.add(getScrollDeclaringHandful());
        setHandfuls(new ByteMap<AbsPlainLabel>());
        setDeclaredHandfuls(new ByteMap<AbsPanel>());
        AbsPanel declaredHandfuls_ = getOwner().getCompoFactory().newGrid(0,1);
        for (byte i = IndexConstants.FIRST_INDEX; i<nbChoosenPlayers; i++) {
            relative_ = relative(i);
            AbsPanel declaredHandfulGroup_ = getOwner().getCompoFactory().newLineBox();
            AbsPlainLabel lab_ = getOwner().getCompoFactory().newPlainLabel(pseudos_.getVal(relative_));
            declaredHandfulGroup_.add(lab_);
            AbsPlainLabel handful_ = getOwner().getCompoFactory().newPlainLabel(EMPTY_STRING);
            declaredHandfulGroup_.add(handful_);
            getHandfuls().put(relative_, handful_);
            AbsPanel declaredHandful_ = getOwner().getCompoFactory().newLineBox();
            declaredHandfulGroup_.add(declaredHandful_);
            getDeclaredHandfuls().put(relative_, declaredHandful_);
            declaredHandfuls_.add(declaredHandfulGroup_);
        }
        AbsScrollPane scroll_ = getOwner().getCompoFactory().newAbsScrollPane(declaredHandfuls_);
        panneau2_.add(scroll_);

        setPanelCallableCards(getOwner().getCompoFactory().newLineBox());
        setScrollCallableCards(getOwner().getCompoFactory().newAbsScrollPane(getPanelCallableCards()));
        getScrollCallableCards().setVisible(false);
        panneau2_.add(getScrollCallableCards());
        AbsPanel sousPanneau_=getOwner().getCompoFactory().newPageBox();
        setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(sousPanneau_);

        container_.add(panneau2_,GuiConstants.BORDER_LAYOUT_EAST);
        tapisTarot().setTalonTarot(lg_,_dog, getOwner().getCompoFactory());
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
        for (byte p = IndexConstants.FIRST_INDEX; p<indexInGame; p++) {
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
        PlayerActionGame bid_ = new PlayerActionGame(PlayerActionGameType.SLAM);
        bid_.setPlace(indexInGame);
        bid_.setLocale(lg_);
        window().sendObject(bid_);
    }
    public void displayTrumpsForHandfulMulti(HandTarot _trumps) {
        getScrollDeclaringHandful().setVisible(!_trumps.estVide());
        if (getCurrentIncludedTrumps().estVide() && getCurrentExcludedTrumps().estVide()) {
            setCurrentIncludedTrumps(_trumps);
        }
        getCurrentIncludedTrumps().trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
        getCurrentExcludedTrumps().trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
        updateCardsInPanelTarotHandfulMulti(getIncludedTrumpsForHandful(), getCurrentIncludedTrumps(), true);
        updateCardsInPanelTarotHandfulMulti(getExcludedTrumpsForHandful(), getCurrentExcludedTrumps(), false);
        pack();
        //PackingWindowAfter.pack(this, true);
        getDeclaringHandful().setDividerLocation(getDeclaringHandful().getWidth()*9/10);
    }
    private void setChienMulti(HandTarot _main,boolean _ecouteur) {
        AbsPanel panneau_=tapisTarot().getCenterDeck();
        panneau_.removeAll();
        panneau_.validate();
        panneau_.setBackground(GuiConstants.newColor(0,125,0));
        _main.trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
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
        takerCardsDog.trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
        /*On place les cartes de l'utilisateur*/
        setCanDiscard(true);
        updateCardsInPanelTarotDogMulti(getPanelHand(), allCards_, true);
        AbsPanel boutons_=getPanneauBoutonsJeu();
        boutons_.removeAll();
        getValidateDog().setVisible(true);
        getValidateDog().setEnabled(false);
        getPanneauBoutonsJeu().add(getValidateDog());
        getSlamButton().setEnabled(true);
        getSlamButton().setVisible(false);
        getPanneauBoutonsJeu().add(getSlamButton());
//        addButtonValidateDogTarotMulti(getMessages().getVal(MainWindow.GO_CARD_GAME), false);
        getPanneauBoutonsJeu().validate();
        pack();
        //PackingWindowAfter.pack(this, true);
        window().sendObjectTakeCard();
    }
    private void addCardDog(CardTarot _ct) {
        takerCardsDog.jouer(_ct);
        takerCardsDog.trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
        cardsInDog.ajouter(_ct);
        setChienMulti(cardsInDog,true);
//        JPanel boutons_=getPanneauBoutonsJeu();
        boolean chienFait_ = cardsInDog.total()==repTarot.getNombreCartesChien();
//        if (boutons_.getComponentCount() > 0) {
////            LabelButton valide_=(LabelButton)boutons_.getComponent(0);
//            getValidateDog().setEnabledLabel(chienFait_);
//        }
        getValidateDog().setEnabled(chienFait_);
        if(chienFait_) {
            getSlamButton().setEnabled(true);
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
        takerCardsDog.trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
        cardsInDog.jouer(_ct);
        setChienMulti(cardsInDog,true);
//        JPanel boutons_=getPanneauBoutonsJeu();
//        LabelButton valide_=(LabelButton)boutons_.getComponent(0);
        getValidateDog().setEnabled(false);
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
    private void updateCardsInPanelTarotDogMulti(AbsPanel _panel, HandTarot _hand, boolean _inHand) {
        _panel.removeAll();
        String lg_ = getOwner().getLanguageKey();
        for (GraphicTarotCard c: getGraphicCards(getWindow(),lg_,_hand.getCards())) {
            c.addMouseListener(new ListenerCardTarotMultiDog(this, c.getCard(),_inHand,c));
            _panel.add(c.getPaintableLabel());
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
    }

    private void updateCardsInPanelTarotCallBeforeDogMulti(AbsPanel _panel, HandTarot _hand) {
        _panel.removeAll();
        String lg_ = getOwner().getLanguageKey();
        for (GraphicTarotCard c: getGraphicCards(getWindow(),lg_,_hand.getCards())) {
            c.addMouseListener(new ListenerCardTarotMultiBeforeDog(this, c.getCard()));
            _panel.add(c.getPaintableLabel());
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
    }
    private void updateCardsInPanelTarotHandfulMulti(AbsPanel _panel, HandTarot _hand, boolean _included) {
        _panel.removeAll();
        String lg_ = getOwner().getLanguageKey();
        for(CardTarot c: _hand) {
            MiniTarotCard carte_=new MiniTarotCard(lg_, c, getOwner().getCompoFactory());
//            carte_.addMouseListener(new EcouteurCarteTarotHandfulMulti(_hand.carte(indice_),_included));
            carte_.addMouseListener(new ListenerCardTarotMultiHandful(this, c,_included));
            _panel.add(carte_.getPaintableLabel());
        }
        _panel.validate();
    }
    private void updateCardsInPanelTarotJeuMulti(AbsPanel _panel, HandTarot _hand) {
        _panel.removeAll();
        String lg_ = getOwner().getLanguageKey();
        for (GraphicTarotCard c: getGraphicCards(getWindow(),lg_,_hand.getCards())) {
            c.addMouseListener(new ListenerCardTarotMultiGame(this, c.getCard()));
            _panel.add(c.getPaintableLabel());
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

    public IdMap<Handfuls,Integer> getRequiredTrumps() {
        return requiredTrumps;
    }

    public void endGame(ResultsTarot _res) {

        /*Descativer aide au jeu*/
        MenuItemUtils.setEnabledMenu(window().getMultiStop(),true);
//        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),false);
        AbsPanel container_=getOwner().getCompoFactory().newBorder();
        /*Le nombre de parties jouees depuis le lancement du logiciel*/
        setThreadAnime(false);
        AbsTabbedPane onglets_=getOwner().getCompoFactory().newAbsTabbedPane();
        String lg_ = getOwner().getLanguageKey();
        setScores(_res.getRes().getScores());
        _res.getRes().setGeneral(readCoreResource());
        _res.getRes().setSpecific(readResource());
        RenderedPage editor_;
        PreparedAnalyzedCards sOne_ = retrieve(FileConst.RESOURCES_HTML_FILES_RESULTS_TAROT);
        ((TarotStandards)sOne_.getBeanNatLgNames()).setDataBase(_res);
        editor_ = FrameGeneralHelp.initialize(sOne_, getOwner().getFrames());
        editor_.getScroll().setPreferredSize(new MetaDimension(300,300));
        onglets_.add(getMessages().getVal(WindowNetWork.RESULTS_PAGE),editor_.getScroll());
        PreparedAnalyzedCards sTwo_ = retrieve(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_TAROT);
        ((TarotStandards)sTwo_.getBeanNatLgNames()).setDataBase(_res);
        editor_ = FrameGeneralHelp.initialize(sTwo_, getOwner().getFrames());
        editor_.getScroll().setPreferredSize(new MetaDimension(300,300));
        onglets_.add(getMessages().getVal(WindowNetWork.DETAIL_RESULTS_PAGE),editor_.getScroll());
        container_.add(onglets_,GuiConstants.BORDER_LAYOUT_CENTER);
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
            AbsPlainButton button_ = getOwner().getCompoFactory().newPlainButton(getMessages().getVal(WindowNetWork.PLAY_TAROT));
            button_.addActionListener(new PlayNextDealEvent(this));
            panneau_.add(button_);
        }
        panneau_.add(getWindow().getClock());
        panneau_.add(getWindow().getLastSavedGameDate());
        container_.add(panneau_,GuiConstants.BORDER_LAYOUT_SOUTH);

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
        long nb_=chargerNombreDeParties(GameEnum.TAROT, getOwner().getFrames());
        GameTarot game_ = Net.getGames(window().getNet()).partieTarot();
        DealTarot deal_=new DealTarot(nb_,game_.empiler());
        deal_.donneurSuivant(game_.getDistribution().getDealer(),game_.getRegles());
        deal_.initDonne(game_.getRegles(),getOwner().getGenerator());
        Net.getGames(window().getNet()).jouerTarot(new GameTarot(GameType.RANDOM,deal_,game_.getRegles()));
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
            AbsPlainButton buttonRules_ = getOwner().getCompoFactory().newPlainButton(getMessages().getVal(WindowNetWork.SELECT_RULES));
            buttonRules_.addActionListener(new ChangeRulesEvent(this));
            container_.add(buttonRules_);
            AbsPlainButton button_ = getOwner().getCompoFactory().newPlainButton(getMessages().getVal(WindowNetWork.PLAY_TAROT));
            button_.addActionListener(new PlayFirstDealEvent(this));
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
        RulesTarot rulesTarotMulti_ = DialogRulesTarot.getRegles(window().getDialogRulesTarot());
        if (!DialogRulesTarot.isValidated(getOwner().getDialogRulesTarot())) {
            return;
        }
        rulesTarotMulti = rulesTarotMulti_;
        window().sendObject(rulesTarotMulti_);
    }
    @Override
    public void dealFirst() {
        boolean allReady_ = window().getSockets().allReady();
        if (!allReady_) {
            return;
        }
        boolean distinct_ = Net.distinctPlaces(window().getNet(), window().getSockets());
        if (!distinct_) {
            return;
        }
        HandTarot pile_;
        /*Chargement de la pile de cartes depuis un fichier sinon on la cree*/
        pile_ = chargerPileTarot(getOwner().getFrames());
        if (!pile_.validStack()) {
            pile_ = HandTarot.pileBase();
        }
        DealTarot deal_ = new DealTarot(0,pile_);
        deal_.setRandomDealer(rulesTarotMulti,getOwner().getGenerator());
        deal_.initDonne(rulesTarotMulti,getOwner().getGenerator());
        Net.getGames(window().getNet()).jouerTarot(new GameTarot(GameType.RANDOM,deal_,rulesTarotMulti));
        window().sendObjectPlayGame();
    }

    @Override
    public WindowNetWork window() {
        return win;
    }
}

