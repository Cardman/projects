package cards.gui.containers;




import cards.consts.GameType;
import cards.consts.Role;
import cards.facade.Games;
import cards.facade.enumerations.GameEnum;
import cards.gui.*;
import cards.gui.containers.events.PlayFirstDealEvent;
import cards.gui.containers.events.PlayNextDealEvent;
import cards.gui.containers.events.SlamEvent;
import cards.gui.containers.events.TakeDogEvent;
import cards.gui.containers.events.ValidateDogEvent;
import cards.gui.dialogs.*;
import cards.gui.events.*;
import cards.gui.labels.GraphicCard;
import cards.gui.panels.CarpetTarot;
import cards.gui.panels.MiniCarpet;
import cards.main.CardNatLgNamesNavigation;
import cards.network.common.*;
import cards.network.common.before.*;
import cards.network.tarot.DiscardPhaseTarot;
import cards.network.tarot.actions.*;
import cards.network.tarot.displaying.DealtHandTarot;
import cards.network.tarot.unlock.AllowBiddingTarot;
import cards.network.tarot.unlock.AllowPlayingTarot;
import cards.network.tarot.unlock.CallableCards;
import cards.network.threads.Net;
import cards.tarot.*;
import cards.tarot.beans.TarotStandards;
import cards.tarot.enumerations.*;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.events.*;
import code.gui.images.MetaDimension;
import code.network.WindowNetWork;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.util.*;
import code.util.core.*;

public class ContainerMultiTarot extends ContainerTarot implements ContainerMulti,ContainerPlayableTarot{

    private final ContainerMultiContent containerMultiContent;
//    private int noClient;
    private boolean discardCall;
//    private byte indexInGame = IndexConstants.INDEX_NOT_FOUND_ELT;
    private HandTarot cardsInDog = new HandTarot();
    private HandTarot playerHand = new HandTarot();
//    private int nbChoosenPlayers = IndexConstants.INDEX_NOT_FOUND_ELT;
//    private NumComboBox choiceOfPlaceForPlayingGame;
//    private AbsCustCheckBox ready;

    private DealingTarot repTarot;
//    private final boolean hasCreatedServer;
//    private boolean readyToPlay;
//    private final CustList<AbsPlainLabel> playersPseudos = new CustList<AbsPlainLabel>();
//    private final CustList<AbsPlainLabel> playersPlaces = new CustList<AbsPlainLabel>();
//    private final CustList<AbsCustCheckBox> playersReady = new CustList<AbsCustCheckBox>();
//    private RenderedPage editor;
//    private IntTreeMap<Byte> playersPlacesForGame = new IntTreeMap<Byte>();
//    private IntMap<String> playersPseudosForGame = new IntMap<String>();
    private RulesTarot rulesTarotMulti=new RulesTarot();

//    private final AbsPlainLabel canPlayLabel = getOwner().getCompoFactory().newPlainLabel("");
//    private final WindowNetWork win;
    private HandTarot callableCards = new HandTarot();
    private HandTarot allowed = new HandTarot();

    public ContainerMultiTarot(WindowNetWork _window, boolean _hasCreatedServer, int _nbPlayers) {
        super(_window);
        containerMultiContent = new ContainerMultiContent(_hasCreatedServer, _window);
        containerMultiContent.setMessages(Games.getMulti(Games.getAppliTr(_window.getFrames().currentLg())).getMapping());
        _window.update(this);
        initButtonValidateDogTarotMulti();
        initBoutonJeuChelemTarotMulti();
//        hasCreatedServer = _hasCreatedServer;
        if (containerMultiContent.isHasCreatedServer()) {
            Net.getGames(_window.getNet()).setRulesBelote(null);
            Net.getGames(_window.getNet()).setRulesPresident(null);
            setRulesTarotMulti(new RulesTarot((byte)_nbPlayers));
            Net.getGames(_window.getNet()).setRulesTarot(getRulesTarotMulti());
        }
    }
    private void initBoutonJeuChelemTarotMulti() {
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(Games.toString(BidTarot.SLAM,lg_));
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
        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(_texte);
        bouton_.addActionListener(new TakeDogEvent(this));
        bouton_.setEnabled(_apte);
        panneau_.add(bouton_);
        setTakeCardDog(bouton_);
    }

    private void initButtonValidateDogTarotMulti() {
        AbsButton bouton_=getOwner().getCompoFactory().newPlainButton(file().getVal(MessagesGuiCards.MAIN_GO_CARD_GAME));
        bouton_.addActionListener(new ValidateDogEvent(this));
        setValidateDog(bouton_);
    }

    @Override
    public void validateDog() {
//        setCanDiscard(false);
        updateCardsInPanelTarotJeuMulti(false);
        containerMultiContent.getCanPlayLabel().setText(EMPTY_STRING);
        if (!getTakerCardsDog().estVide()) {
            playerHand = getTakerCardsDog();
            setChienMulti(false);
        }
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().validate();
        //pack();
        getScrollCallableCards().setVisible(false);
        CallAfterDiscardTarot v_ = new CallAfterDiscardTarot(PlayerActionGameType.VALIDATE_DOG);
        HandTarot h_ = new HandTarot();
        h_.ajouter(getCalledCard());
        v_.setCalledCards(h_);
        v_.setPlace(getContainerMultiContent().getIndexInGame());
//        String lg_ = getOwner().getLanguageKey();
//        v_.setLocale(lg_);
        getContainerMultiContent().window().sendObject(v_);
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
//    @Override
//    public void updatePlaces(ChoosenPlace _choosePlace) {
//        playersPlacesForGame = _choosePlace.getPlacesPlayers();
//        playersPlaces.get(_choosePlace.getIndex()).setText(Long.toString(_choosePlace.getPlace()));
//    }
//    @Override
//    public void updateReady(Ready _readyPlayer) {
//        playersReady.get(_readyPlayer.getIndex()).setSelected(_readyPlayer.isReady());
//    }
    @Override
    public void updateFirst(IndexOfArrivingCards _players) {
        getPane().removeAll();
        MenuItemUtils.setEnabledMenu(getContainerMultiContent().window().getMultiStop(),true);
        MenuItemUtils.setEnabledMenu(getContainerMultiContent().window().getTricksHands(),true);
        MenuItemUtils.setEnabledMenu(getContainerMultiContent().window().getTeams(),true);
//        MenuItemUtils.setEnabledMenu(getLoad(),false);
        AbsPanel container_ = containerMultiContent.resultUsers(this,_players);
//        nbChoosenPlayers = _players.getNbPlayers();
        rulesTarotMulti = _players.getRulesTarot();
        Net.getGames(getContainerMultiContent().window().getNet()).setRulesTarot(getRulesTarotMulti());
//        AbsPanel container_=getOwner().getCompoFactory().newPageBox();
//        AbsPanel panel_ = getOwner().getCompoFactory().newPageBox();
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


        rulesTarotMulti.getCommon().setGeneral(WindowNetWork.readCoreResourceMix(this));
        rulesTarotMulti.getCommon().setSpecific(readResource());
        CardNatLgNamesNavigation stds_ = retrieve(FileConst.RESOURCES_HTML_FILES_RULES_TAROT).attendreResultat();
        ((TarotStandards)stds_.getBeanNatLgNames()).setDataBaseRules(rulesTarotMulti);
        containerMultiContent.setEditor(FrameGeneralHelp.initialize(stds_, getOwner().getFrames(), containerMultiContent.window().getGuardRender()));

        containerMultiContent.getEditor().getScroll().setPreferredSize(new MetaDimension(300,400));
        container_.add(containerMultiContent.getEditor().getScroll());
        getContainerMultiContent().updateAfter(_players);
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

        if (containerMultiContent.isHasCreatedServer()) {
            updateButton(container_);
            AbsButton button_ = getOwner().getCompoFactory().newPlainButton(containerMultiContent.getMessages().getVal(MessagesGuiCards.PLAY_TAROT));
            button_.addActionListener(new PlayFirstDealEvent(this));
            container_.add(button_);
        }

        container_.add(getWindow().getClock());
        container_.add(getWindow().getLastSavedGameDate());
        setContentPane(container_);
        pack();
        //PackingWindowAfter.pack(this, true);
    }
    private void updateButton(AbsPanel _container) {
        DialogTarotContent content_ = new DialogTarotContent(getOwner().getFrames());
        AbsTabbedPane jt_ = content_.initJt(null, false, containerMultiContent.getNbChoosenPlayers(), getOwner());
        AbsPanel border_ = getOwner().getCompoFactory().newBorder();
        border_.add(jt_, GuiConstants.BORDER_LAYOUT_CENTER);
        AbsButton bouton_= getOwner().getCompoFactory().newPlainButton(containerMultiContent.getMessages().getVal(MessagesGuiCards.SELECT_RULES));
        bouton_.addActionListener(new AfterValidateRulesTarotMulti(content_,this));
        content_.setValidateButton(bouton_);
        border_.add(bouton_,GuiConstants.BORDER_LAYOUT_SOUTH);
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
    public void updateRules(RulesTarot _rules) {
        rulesTarotMulti = _rules;
        Net.getGames(getContainerMultiContent().window().getNet()).setRulesTarot(getRulesTarotMulti());
        rulesTarotMulti.getCommon().setGeneral(WindowNetWork.readCoreResourceMix(this));
        rulesTarotMulti.getCommon().setSpecific(readResource());
        CardNatLgNamesNavigation stds_ = retrieve(FileConst.RESOURCES_HTML_FILES_RULES_TAROT).attendreResultat();
        ((TarotStandards)stds_.getBeanNatLgNames()).setDataBaseRules(rulesTarotMulti);
        FrameGeneralHelp.initialize(stds_, containerMultiContent.getEditor());
    }
    public void updateForBeginningGame(DealtHandTarot _hand) {
        repTarot = _hand.getRep();
//        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        placerIhmTarotMulti(_hand.getDog(), _hand.getDealer());
//        setCarteEntree(false);
//        setCarteSortie(false);
//        setCanDiscard(false);
//        setCanExcludeTrumps(false);
//        setCanPlay(false);
        playerHand = _hand.getCards();
        playerHand.trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
        HandTarot h_ = new HandTarot();
        h_.ajouterCartes(playerHand);
        setTakerCardsDog(h_);
        /*On place les cartes de l'utilisateur*/
        updateCardsInPanelTarotJeuMulti(false);
//        for (BidTarot b: _hand.getAllowedBids()) {
//            ajouterBoutonContratTarotMulti(Games.toString(b,lg_), b);
//        }
        //getPanneauBoutonsJeu().validate();
        pack();
        //PackingWindowAfter.pack(this, true);
        getContainerMultiContent().sendDealt();
//        PlayerActionGame dealt_ = new PlayerActionGame(PlayerActionGameType.DEALT);
//        dealt_.setPlace(getContainerMultiContent().getIndexInGame());
//        dealt_.setLocale(lg_.getKey());
//        window().sendObject(dealt_);
    }

//    public void canBid() {
//        setCanBid(true);
//    }
    public void canBidTarot(AllowBiddingTarot _bids) {
//        setCanBid(true);
        containerMultiContent.getCanPlayLabel().setText(containerMultiContent.getMessages().getVal(MessagesGuiCards.CAN_PLAY));
        getPanneauBoutonsJeu().removeAll();
        ajouterBoutonContratsTarot(this,_bids.getBids(),_bids.getMaxBid());
//        TranslationsLg lg_ = getOwner().getFrames().currentLg();
//        for (BidTarot b: _bids.getBids()) {
//            ajouterBoutonContratTarotMulti(Games.toString(b,lg_), b, b.estDemandable(_bids.getMaxBid()));
//        }
        //getPanneauBoutonsJeu().validate();
        pack();
        //PackingWindowAfter.pack(this, true);
    }
//    public void afterBid() {
//        setCanBid(false);
//    }
//    public void errorForBidding(ErrorBidding _error) {
//        TranslationsLg lg_ = getOwner().getFrames().currentLg();
//        String mes_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CANT_BID), Games.toString(_error.getBid(),lg_));
////        JOptionPane.showMessageDialog(getOwner(),mes_,
////                getMessages().getVal(MainWindow.CANT_BID_TITLE), JOptionPane.INFORMATION_MESSAGE);
//        getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(),mes_,
//                containerMultiContent.getMessages().getVal(WindowNetWork.CANT_BID_TITLE),
//                GuiConstants.ERROR_MESSAGE);
//    }
    public void displayLastBid(BiddingTarot _bid) {
        if (_bid.getBid().isJouerDonne()) {
            setContratUtilisateur(_bid.getBid());
        }
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        containerMultiContent.getCanPlayLabel().setText(EMPTY_STRING);

        getEvents().append(StringUtil.concat(containerMultiContent.getPseudoByPlace(_bid.getPlace()),INTRODUCTION_PTS,Games.toString(_bid.getBid(),lg_),RETURN_LINE));

        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().validate();
        //pack();
        PlayerActionGame dealt_ = new PlayerActionGame(PlayerActionGameType.DONE_BIDDING);
        dealt_.setPlace(getContainerMultiContent().getIndexInGame());
//        dealt_.setLocale(lg_.getKey());
        getContainerMultiContent().window().sendObject(dealt_);
    }

    public void displayCalling(CallableCards _cards) {
        byte relative_ = containerMultiContent.relative(_cards.getTakerIndex());
        getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, relative_);
        callableCards = _cards.getCallableCards();
        if (callableCards.estVide()) {
            return;
        }
        containerMultiContent.getCanPlayLabel().setText(containerMultiContent.getMessages().getVal(MessagesGuiCards.CAN_PLAY));
        setDiscardCall(_cards.isDiscarding());
        getPanneauBoutonsJeu().removeAll();
        getScrollCallableCards().setVisible(true);
        updateCardsInPanelTarotCallBeforeDogMulti(true);
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

    public void displayDog(DiscardPhaseTarot _dog) {
//        String lg_ = getOwner().getLanguageKey();
        getPanneauBoutonsJeu().removeAll();
        //getPanneauBoutonsJeu().validate();
        byte relative_ = containerMultiContent.relative(_dog.getDiscardPhase().getTakerIndex());
        getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, relative_);
        cardsInDog = _dog.getDiscardCard();
        callableCards = _dog.getCallableCards();
        setDiscardCall(_dog.isCallAfter());
        if (_dog.getDiscardPhase().getTaker() == DiscardPhaseTarot.TAKER_HUM_WRITE) {
            if (isDiscardCall()) {
                addButtonTakeDogCardsTarotMulti(file().getVal(MessagesGuiCards.MAIN_TAKE_CARDS),true);
                containerMultiContent.getCanPlayLabel().setText(containerMultiContent.getMessages().getVal(MessagesGuiCards.CAN_PLAY));
                getScrollCallableCards().setVisible(true);
                setChienMulti(false);
            } else {
                //take the cards
                addButtonTakeDogCardsTarotMulti(file().getVal(MessagesGuiCards.MAIN_TAKE_CARDS), callableCards.estVide());
                containerMultiContent.getCanPlayLabel().setText(containerMultiContent.getMessages().getVal(MessagesGuiCards.CAN_PLAY));
                getScrollCallableCards().setVisible(true);
                updateCardsInPanelTarotCallBeforeDogMulti(true);
                if (callableCards.estVide()) {
                    setChienMulti(false);
                }
            }

        } else {
            setChienMulti(false);
        }
        pack();
        //PackingWindowAfter.pack(this, true);
//        if (_dog.getDiscardPhase().getTaker() == Dog.TAKER_NO) {
//            PlayerActionGame show_ = new PlayerActionGame(PlayerActionGameType.SHOW_DOG);
//            show_.setPlace(indexInGame);
//            show_.setLocale(lg_);
//            window().sendObject(show_);
//        }
    }
//    public void displayCalledCard(CalledCards _call) {
//        TranslationsLg lg_ = getOwner().getFrames().currentLg();
//        canPlayLabel.setText(EMPTY_STRING);
//        byte relative_ = relative(_call.getPlace());
//        getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, relative_);
//        getEvents().append(StringUtil.concat(getPseudoByPlace(_call.getPlace()),INTRODUCTION_PTS,Games.toString(_call.getCalledCards(),lg_),RETURN_LINE));
//
//        PlayerActionGame dealt_ = new PlayerActionGame(PlayerActionGameType.CALLED_CARD_KNOWN);
//        dealt_.setPlace(indexInGame);
//        dealt_.setLocale(lg_.getKey());
//        window().sendObject(dealt_);
//    }
//    public void errorDiscardingCard(ErrorDiscarding _error) {
//        TranslationsLg lg_ = getOwner().getFrames().currentLg();
//        String mesCard_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CANT_DISCARD), Games.toString(_error.getCard(),lg_));
//        String mesReason_ = StringUtil.simpleStringsFormat(containerMultiContent.getMessages().getVal(WindowNetWork.REASON), _error.getErrorMessage());
////        JOptionPane.showMessageDialog(getOwner(),mesCard_+RETURN_LINE_CHAR+mesReason_, getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE),JOptionPane.ERROR_MESSAGE);
//        getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(), StringUtil.concat(mesCard_,RETURN_LINE,mesReason_),
//                containerMultiContent.getMessages().getVal(WindowNetWork.CANT_PLAY_CARD_TITLE),
//                GuiConstants.ERROR_MESSAGE);
//    }
    public void displaySlamButton() {
        getPanneauBoutonsJeu().removeAll();
        getSlamButton().setEnabled(true);
//        getSlamButton().setVisible(true);
        getPanneauBoutonsJeu().add(getSlamButton());
//        ajouterBoutonJeuChelemTarotMulti(BidTarot.SLAM.toString());
        getValidateDog().setEnabled(true);
        getPanneauBoutonsJeu().add(getValidateDog());
//        addButtonValidateDogTarotMulti(getMessages().getVal(MainWindow.GO_CARD_GAME), true);
        //getPanneauBoutonsJeu().validate();
        pack();
        //PackingWindowAfter.pack(this, true);
    }
//    public void showDiscardedTrumps(DiscardedTrumps _discardedTrumps) {
//        String lg_ = refreshDiscard(_discardedTrumps.getTrumps());
//        //pack();
//        SeenDiscardedTrumps dis_ = new SeenDiscardedTrumps();
//        dis_.setDeclaringSlam(_discardedTrumps.isDeclaringSlam());
//        dis_.setPlace(indexInGame);
//        dis_.setLocale(lg_);
//        window().sendObject(dis_);
//    }

    private void refreshDiscard(HandTarot _atouts) {
        ContainerSingleTarot.discardedTrumps(this,_atouts);
//        getPanelDiscardedTrumps().removeAll();
//        for (GraphicCard<CardTarot> c: getGraphicCards(getWindow(), getOwner().getFrames().currentLg(), _atouts.getCards())) {
//            getPanelDiscardedTrumps().add(c.getPaintableLabel());
//        }
////        boolean entered_ = false;
////        for(CardTarot c:atouts_)
////        {
////            GraphicTarotCard carte_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
////            carte_.setPreferredSize(entered_);
////            getPanelDiscardedTrumps().add(carte_);
////            entered_ = true;
////        }
//        getPanelDiscardedTrumps().validate();
//        getPanelDiscardedTrumps().setVisible(true);
    }
//    public void displaySlam(PlayerActionGame _bidding) {
//        //getTakerIndex
////        byte relative_ = relative(_bidding.getPlace());
////        getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, relative_);
////        getEvents().append(StringUtil.concat(getPseudoByPlace(_bidding.getPlace()),INTRODUCTION_PTS, WindowNetWork.SLAM,RETURN_LINE));
//
//        PlayerActionGame dis_ = new PlayerActionGame(PlayerActionGameType.DONE_DISPLAY_SLAM);
//        String lg_ = getOwner().getLanguageKey();
//        dis_.setLocale(lg_);
//        dis_.setPlace(indexInGame);
//        window().sendObject(dis_);
//    }
//    public void updateDiscardingOrCanceling(DiscardedCard _discarded) {
//        if (_discarded.isInHand()) {
//            addCardDog(_discarded.getCard());
//        } else {
//            removeCardDog(_discarded.getCard());
//        }
//    }


    @Override
    public void refreshCurrentHand() {
        updateCardsInPanelTarotJeuMulti(true);
    }
    public void canPlayTarot(AllowPlayingTarot _declaration) {
        containerMultiContent.getCanPlayLabel().setText(containerMultiContent.getMessages().getVal(MessagesGuiCards.CAN_PLAY));
//        TranslationsLg lg_ = getOwner().getFrames().currentLg();
//        setCanPlay(true);
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),true);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),true);
        allowed = _declaration.getCards();
        if (!_declaration.isFirstRoundPlaying()) {
            setChoosenHandful(Handfuls.NO);
            updateCardsInPanelTarotJeuMulti(true);
            getSelectedMiseres().clear();
//            for (Miseres m: getSelectedMiseres().getKeys()) {
//                getSelectedMiseres().put(m, BoolVal.FALSE);
//            }
            return;
        }
        if (_declaration.getCurrentBid().isFaireTousPlis()) {
            byte relative_ = containerMultiContent.relative(_declaration.getTakerIndex());
            getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, relative_);
            getEvents().append(StringUtil.concat(StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_DECLARING_SLAM), containerMultiContent.getPseudoByPlace(_declaration.getTakerIndex())),RETURN_LINE));
//            getEvents().append(StringUtil.concat(containerMultiContent.getPseudoByPlace(_declaration.getTakerIndex()),INTRODUCTION_PTS, WindowNetWork.SLAM,RETURN_LINE));
        }
        updateCardsInPanelTarotJeuMulti(true);
//        setCanExcludeTrumps(true);
        displayTrumpsForHandfulMulti(GameTarotCommonPlaying.atoutsPoignee(playerHand.couleurs()));
        getPanneauBoutonsJeu().removeAll();
//        IdList<Handfuls> all_ = new IdList<Handfuls>();
//        IdList<Handfuls> enabled_ = new IdList<Handfuls>();
//        all_.addAllElts(Handfuls.getNonDeclarableHandFuls());
//        enabled_.addAllElts(Handfuls.getNonDeclarableHandFuls());
//        for (Handfuls h: _declaration.getAllowedHandfuls()) {
//            int diff_ = getCurrentIncludedTrumps().total()-requiredTrumps.getVal(h);
//            if (diff_ >= 0) {
//                enabled_.add(h);
//            }
//            all_.add(h);
//        }
        updateHandfulButtons(this,getRulesTarotMulti());
//        updateHandfulButtons(this,all_,enabled_,requiredTrumps);
//        AbsPanel handFuls_ = getOwner().getCompoFactory().newPageBox();
//        AbsTextArea txt_ = getOwner().getCompoFactory().newTextArea(EMPTY_STRING, 1, 15);
//        txt_.setEditable(false);
//        setInfoCurrentHandful(txt_);
//        AbsScrollPane scroll_ = getOwner().getCompoFactory().newAbsScrollPane(getInfoCurrentHandful());
//        scroll_.setPreferredSize(new MetaDimension(getEvents().getWidth(),70));
//        handFuls_.add(scroll_);
//        setChoosenHandful(Handfuls.NO);
//        setSelectedMiseres(new IdMap<Miseres,AbsCustCheckBox>());
//        CustList<AbsRadioButton> list_ = new CustList<AbsRadioButton>();
//        for (Handfuls h: Handfuls.getNonDeclarableHandFuls()) {
//            AbsRadioButton radio_ = getOwner().getCompoFactory().newRadioButton(Games.toString(h,lg_));
//            list_.add(radio_);
//            radio_.addMouseListener(new ListenerNoHandfulTarot(this, radio_, h,list_));
//            handFuls_.add(radio_);
//        }
//        for (Handfuls h: _declaration.getAllowedHandfuls()) {
//            AbsRadioButton radio_ = getOwner().getCompoFactory().newRadioButton(Games.toString(h,lg_));
//            list_.add(radio_);
//            int diff_ = getCurrentIncludedTrumps().total()-requiredTrumps.getVal(h);
//            radio_.setEnabled(diff_ >= 0);
//            radio_.addMouseListener(new ListenerHandfulTarot(requiredTrumps.getVal(h), radio_, this, h,list_));
//            handFuls_.add(radio_);
//        }
//        getPanneauBoutonsJeu().add(handFuls_);
        miseres(rulesTarotMulti,getPanneauBoutonsJeu());
//        AbsPanel miseres_ = getOwner().getCompoFactory().newPageBox();
//        for(Miseres po_:_declaration.getAllowedMiseres()) {
//            AbsCustCheckBox check_ = getOwner().getCompoFactory().newCustCheckBox(Games.toString(po_,lg_));
//            check_.addActionListener(new ListenerMiseresTarot(this,check_,po_));
//            getSelectedMiseres().put(po_, check_);
//            miseres_.add(check_);
//        }
//        getPanneauBoutonsJeu().add(miseres_);
        //getPanneauBoutonsJeu().validate();
        byte relative_ = containerMultiContent.relative(_declaration.getTakerIndex());
        getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, relative_);
        refreshDiscard(_declaration.getDiscardedTrumps());
        called(_declaration.getCalledCards(),containerMultiContent.getPseudoByPlace(_declaration.getTakerIndex()));
        //PackingWindowAfter.pack(this, true);
    }
    public void displayPlayedCard(PlayingCardTarot _card) {
        containerMultiContent.getCanPlayLabel().setText(EMPTY_STRING);
        byte relative_ = containerMultiContent.relative(_card.getPlace());
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        tapisTarot().setCarteTarot(getWindow().getImageFactory(),lg_,relative_, _card.getPlayedCard());
        String pseudo_ = containerMultiContent.getPseudoByPlace(_card.getPlace());
        if (_card.isCalledCard()) {
            getMini().setStatus(getWindow().getImageFactory(),Role.CALLED_PLAYER, relative_);
            ajouterTexteDansZone(StringUtil.concat(pseudo_,INTRODUCTION_PTS,Games.toString(Role.CALLED_PLAYER,lg_)));

        }
        IdList<Handfuls> hs_ = new IdList<Handfuls>();
        if (_card.getChoosenHandful() != Handfuls.NO) {
            hs_.add(_card.getChoosenHandful());
//            ajouterTexteDansZone(StringUtil.concat(pseudo_,INTRODUCTION_PTS,Games.toString(_card.getChoosenHandful(),lg_),RETURN_LINE));

        }
        firstRound(relative_,pseudo_,hs_, _card.getMiseres(), _card.getHandful(),new DirectCardsCallEvents());
//        for(Miseres annonce_:_card.getMiseres()) {
//            ajouterTexteDansZone(StringUtil.concat(pseudo_,INTRODUCTION_PTS,Games.toString(annonce_,lg_),RETURN_LINE));
//
//        }
//        if(!_card.getHandful().estVide()) {
//            getHandfuls().getVal(relative_).setText(Games.toString(_card.getChoosenHandful(),lg_));
//        }
//        AbsPanel panelToSet_ = getDeclaredHandfuls().getVal(relative_);
//        panelToSet_.removeAll();
//        _card.getHandful().trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
//        for(CardTarot c: _card.getHandful()) {
//            MiniCard carte_=new MiniCard(lg_, getOwner(), c.getId().nb());
//            panelToSet_.add(carte_.getPaintableLabel());
//            AbsMetaLabelCard.paintCard(getWindow().getImageFactory(),carte_);
//        }
//        panelToSet_.setSize(panelToSet_.getPreferredSizeValue());
        pack();
        relative_ = containerMultiContent.relative(_card.getTakerIndex());
        getMini().setStatus(getWindow().getImageFactory(),Role.TAKER, relative_);
        //pack();
        PlayerActionGame dealt_ = new PlayerActionGame(PlayerActionGameType.DONE_PLAYING);
        dealt_.setPlace(getContainerMultiContent().getIndexInGame());
//        dealt_.setLocale(lg_.getKey());
        getContainerMultiContent().window().sendObject(dealt_);
    }

//    public void errorPlayingCard(ErrorHandful _error) {
////        setCanExcludeTrumps(true);
//        updateCardsInPanelTarotHandful(this);
//        updateCardsInPanelTarotJeuMulti(true);
////        setCanPlay(true);
//        TranslationsLg lg_ = getOwner().getFrames().currentLg();
//        String mes_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CANT_DECLARE_DETAIL), Games.toString(_error.getHandful(),lg_));
////        JOptionPane.showMessageDialog(
////                getOwner(),
////                mes_ + RETURN_LINE_CHAR + _error.getError(),
////                getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE), JOptionPane.ERROR_MESSAGE);
//        getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(),
//                StringUtil.concat(mes_, RETURN_LINE, _error.getError()),
//                containerMultiContent.getMessages().getVal(WindowNetWork.CANT_PLAY_CARD_TITLE),
//                GuiConstants.ERROR_MESSAGE);
//    }
//
//    public void errorPlayingCard(ErrorPlaying _error) {
////        setCanPlay(true);
//        updateCardsInPanelTarotJeuMulti(true);
//        TranslationsLg lg_ = getOwner().getFrames().currentLg();
//        String mes_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CANT_PLAY_CARD), Games.toString(_error.getCard(),lg_));
//        String mesReason_ = StringUtil.simpleStringsFormat(containerMultiContent.getMessages().getVal(WindowNetWork.REASON), _error.getReason());
//        getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(),
//                StringUtil.concat(mes_, RETURN_LINE, mesReason_),
//                containerMultiContent.getMessages().getVal(WindowNetWork.CANT_PLAY_CARD_TITLE),
//                GuiConstants.ERROR_MESSAGE);
//    }
    public void refreshHand(PlayingCardTarot _card) {
//        String lg_ = getOwner().getLanguageKey();
        playerHand.jouer(_card.getPlayedCard());
        playerHand.trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
        getPanneauBoutonsJeu().removeAll();
        getScrollDeclaringHandful().setVisible(false);
//        setCanPlay(false);
        containerMultiContent.getCanPlayLabel().setText(EMPTY_STRING);
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),false);
        /*On place les cartes de l'utilisateur*/
        updateCardsInPanelTarotJeuMulti(false);
        pack();
        //PackingWindowAfter.pack(this, true);
        PlayingCardTarot ref_ = new PlayingCardTarot();
        ref_.setRefreshing(true);
        ref_.setExcludedTrumps(new HandTarot());
        ref_.setPlayedCard(_card.getPlayedCard());
        ref_.setChoosenHandful(_card.getChoosenHandful());
        ref_.setHandful(_card.getHandful());
        ref_.setMiseres(_card.getMiseres());
        ref_.setCalledCard(_card.isCalledCard());
//        ref_.setLocale(lg_);
        ref_.setPlace(getContainerMultiContent().getIndexInGame());
        getContainerMultiContent().window().sendObject(ref_);
    }
    @Override
    public void pauseBetweenTrick() {
//        String lg_ = getOwner().getLanguageKey();
        tapisTarot().setCartesTarotJeu(getWindow().getImageFactory(),getOwner().getFrames().currentLg(),(byte) containerMultiContent.getNbChoosenPlayers());
        pack();
        //PackingWindowAfter.pack(this, true);
        containerMultiContent.sendPause();
//        PlayerActionGame d_ = new PlayerActionGame(PlayerActionGameType.DONE_PAUSE);
//        d_.setPlace(getContainerMultiContent().getIndexInGame());
//        d_.setLocale(lg_);
//        window().sendObject(d_);
    }

    public void showTeams() {
//        if (!isCanPlay()) {
//            return;
//        }
//        String lg_ = getOwner().getLanguageKey();
        containerMultiContent.showTeams();
//        PlayerActionGame select_ = new PlayerActionGame(PlayerActionGameType.SELECT_TEAMS);
//        select_.setPlace(getContainerMultiContent().getIndexInGame());
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
//        select_.setPlace(getContainerMultiContent().getIndexInGame());
//        select_.setLocale(lg_);
//        window().sendObject(select_);
    }
    public void showTricksHands(TricksHandsTarot _tricks) {
//        ByteTreeMap<String> pseudos_ = new ByteTreeMap<String>();
//        byte p_ = 0;
//        for (String s: pseudosTarot((byte) containerMultiContent.getNbChoosenPlayers())) {
//            pseudos_.put(p_, s);
//            p_++;
//        }
//        for (byte p: containerMultiContent.getPlayersPlacesForGame().values()) {
//            pseudos_.put(p, containerMultiContent.getPseudoByPlace(p));
//        }
//        StringList list_ = new StringList(nicknames().values());
        WindowNetWork ow_ = containerMultiContent.window();
        DialogTricksTarot.setDialogTricksTarot(file().getVal(MessagesGuiCards.MAIN_HANDS_TRICKS_TAROT), ow_);
        DialogTricksTarot.init(_tricks, (byte) containerMultiContent.getNbChoosenPlayers(), nicknames(), getDisplayingTarot(),ow_);
    }
    public void showTeams(TeamsPlayers _teams) {
//        ByteTreeMap<String> pseudos_ = new ByteTreeMap<String>();
//        byte p_ = 0;
//        for (String s: pseudosTarot((byte) containerMultiContent.getNbChoosenPlayers())) {
//            pseudos_.put(p_, s);
//            p_++;
//        }
//        for (byte p: containerMultiContent.getPlayersPlacesForGame().values()) {
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
//    @Override
//    public int getNoClient() {
//        return noClient;
//    }
//    @Override
//    public boolean hasCreatedServer() {
//        return hasCreatedServer;
//    }
    private void placerIhmTarotMulti(HandTarot _dog, byte _beginPlace) {
        getPane().removeAll();
        MenuItemUtils.setEnabledMenu(getContainerMultiContent().window().getMultiStop(),false);
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        AbsPanel container_=getOwner().getCompoFactory().newBorder();
        container_.add(getOwner().getCompoFactory().newPlainLabel(helpMenuTip()),GuiConstants.BORDER_LAYOUT_NORTH);
//        ByteTreeMap<String> pseudos_ = nicknames();
//        StringList list_ = new StringList(pseudos_.values());
        StringList list_ = nicknames();
        setMini(MiniCarpet.newCarpet(getWindow().getImageFactory(),list_.size(), getDisplayingTarot().getDisplaying().isClockwise(), list_, getOwner().getCompoFactory()));
        CarpetTarot tapis_ = CarpetTarot.initTapisTarot(lg_, containerMultiContent.getNbChoosenPlayers(), getDisplayingTarot().getDisplaying().isClockwise(), _dog.total(), getOwner().getFrames());
        getTapis().setTapisTarot(tapis_);
        container_.add(tapis_.getContainer(),GuiConstants.BORDER_LAYOUT_CENTER);
//        panelHand(getOwner().getCompoFactory().newLineBox());
//        AbsPanel panneau_=getOwner().getCompoFactory().newLineBox();
////        panneau_.add(getPanelHand());
//        panneau_.add(panelHand());
//        panneau_.setBackground(GuiConstants.BLUE);

        container_.add(panelHand(),GuiConstants.BORDER_LAYOUT_SOUTH);
        AbsPanel panneau2_=getOwner().getCompoFactory().newPageBox();
        panneau2_.add(events());
//        setEvents(getOwner().getCompoFactory().newTextArea(EMPTY,8, 30));
//        getEvents().setEditable(false);
        byte relative_ = containerMultiContent.relative(_beginPlace);
        getEvents().append(StringUtil.concat(containerMultiContent.getMessages().getVal(MessagesGuiCards.PLAYER_HAVING_TO_PLAY),list_.get(relative_),RETURN_LINE));
//        getEvents().append(StringUtil.concat(containerMultiContent.getMessages().getVal(MessagesGuiCards.PLAYER_HAVING_TO_PLAY),pseudos_.getVal(relative_),RETURN_LINE));
//        panneau2_.add(getOwner().getCompoFactory().newAbsScrollPane(getEvents()));
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
//        AbsPanel declaredHandfuls_ = getOwner().getCompoFactory().newPageBox();
        int nbCh_ = containerMultiContent.getNbChoosenPlayers();
//        AbsPanel declaredHandfuls_ = buildDeclHands(nbCh_,list_, getOwner().getFrames());
//        for (byte i = IndexConstants.FIRST_INDEX; i< nbCh_; i++) {
//            relative_ = containerMultiContent.relative(i);
//            AbsPanel declaredHandfulGroup_ = getOwner().getCompoFactory().newLineBox();
//            AbsPlainLabel lab_ = getOwner().getCompoFactory().newPlainLabel(pseudos_.getVal(relative_));
//            declaredHandfulGroup_.add(lab_);
//            AbsPlainLabel handful_ = getOwner().getCompoFactory().newPlainLabel(EMPTY_STRING);
//            declaredHandfulGroup_.add(handful_);
//            getHandfuls().put(relative_, handful_);
//            AbsPanel declaredHandful_ = getOwner().getCompoFactory().newLineBox();
//            declaredHandfulGroup_.add(declaredHandful_);
//            getDeclaredHandfuls().put(relative_, declaredHandful_);
//            declaredHandfuls_.add(declaredHandfulGroup_);
//        }
//        AbsScrollPane scroll_ = getOwner().getCompoFactory().newAbsScrollPane(buildDeclHands(nbCh_,list_, getOwner().getFrames()));
//        panneau2_.add(scroll_);
        panel(nbCh_,list_, container_,panneau2_);
//        setPanelCallableCards(getOwner().getCompoFactory().newLineBox());
//        setScrollCallableCards(getOwner().getCompoFactory().newAbsScrollPane(getPanelCallableCards()));
//        getScrollCallableCards().setVisible(false);
//        panneau2_.add(getScrollCallableCards());
//        AbsPanel sousPanneau_=getOwner().getCompoFactory().newPageBox();
//        setPanneauBoutonsJeu(sousPanneau_);
//        panneau2_.add(sousPanneau_);
//        setPanelDiscardedTrumps(getOwner().getCompoFactory().newLineBox());
//        getPanelDiscardedTrumps().setVisible(false);
//        panneau2_.add(getPanelDiscardedTrumps());
//        container_.add(panneau2_,GuiConstants.BORDER_LAYOUT_EAST);
        tapisTarot().setTalonTarot(lg_,_dog, getOwner());
        AbsPanel panel_ = getOwner().getCompoFactory().newPageBox();
        panel_.add(getOwner().getCompoFactory().newAbsScrollPane(container_));
        containerMultiContent.getCanPlayLabel().setText(EMPTY_STRING);
        panel_.add(containerMultiContent.getCanPlayLabel());
//        readyToPlay = false;
//        ready = getOwner().getCompoFactory().newCustCheckBox(containerMultiContent.getMessages().getVal(WindowNetWork.READY));
//        ready.addActionListener(new ReadyEvent(this));
//        panel_.add(ready);
//        containerMultiContent.endReady(this,panel_);
        panel_.add(getWindow().getClock());
        panel_.add(getWindow().getLastSavedGameDate());
        setContentPane(panel_);
    }

    private StringList nicknames() {
        return new StringList(containerMultiContent.nicknames(pseudosTarot((byte) containerMultiContent.getNbChoosenPlayers())).values());
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
    @Override
    public void annonceChelem() {
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().validate();
        getScrollCallableCards().setVisible(false);
        pack();
        if (!getTakerCardsDog().estVide()) {
            playerHand = getTakerCardsDog();
        }
        //PackingWindowAfter.pack(this, true);
//        String lg_ = getOwner().getLanguageKey();
        CallAfterDiscardTarot bid_ = new CallAfterDiscardTarot(PlayerActionGameType.SLAM);
        HandTarot h_ = new HandTarot();
        h_.ajouter(getCalledCard());
        bid_.setCalledCards(h_);
        bid_.setPlace(getContainerMultiContent().getIndexInGame());
//        bid_.setLocale(lg_);
        getContainerMultiContent().window().sendObject(bid_);
    }

    @Override
    public void displayTrumps() {
        displayTrumpsForHandfulMulti(GameTarotCommonPlaying.atoutsPoignee(getPlayerHand().couleurs()));
    }

    @Override
    public int required() {
        return getRulesTarotMulti().getAllowedHandfuls().getVal(getChoosenHandful());
    }

    public void displayTrumpsForHandfulMulti(HandTarot _trumps) {
        displayTrumpsForHandful(this,_trumps);
//        getScrollDeclaringHandful().setVisible(!_trumps.estVide());
//        if (getCurrentIncludedTrumps().estVide() && getCurrentExcludedTrumps().estVide()) {
//            setCurrentIncludedTrumps(_trumps);
//        }
//        getCurrentIncludedTrumps().trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
//        getCurrentExcludedTrumps().trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
//        updateCardsInPanelTarotHandful(this);
//        pack();
//        //PackingWindowAfter.pack(this, true);
//        getDeclaringHandful().setDividerLocation(getDeclaringHandful().getWidth()*9/10);
    }
    public void setChienMulti(boolean _ecouteur) {
        new ContainerSingleWithDiscardUtil<CardTarot>(this).updateCardsInPanels(_ecouteur);
//        AbsPanel panneau_=tapisTarot().getCenterDeck();
//        panneau_.removeAll();
//        panneau_.validate();
//        panneau_.setBackground(GuiConstants.newColor(0,125,0));
//        _main.trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
////        setCanDiscard(_ecouteur);
//        updateCardsInPanelTarotDogMulti(tapisTarot().getCenterDeck(), _main, _ecouteur);
    }
    @Override
    public void prendreCartesChien() {
        HandTarot allCards_ = new HandTarot();
        allCards_.ajouterCartes(playerHand);
        allCards_.ajouterCartes(cardsInDog);
        tapisTarot().retirerCartes();
        cardsInDog.supprimerCartes();
        setTakerCardsDog(allCards_);
        getTakerCardsDog().trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
        /*On place les cartes de l'utilisateur*/
//        setCanDiscard(true);
        if (!discardCall) {
            getValidateDog().setEnabled(false);
            getPanneauBoutonsJeu().add(getValidateDog());
//            getSlamButton().setVisible(true);
            getSlamButton().setEnabled(false);
            getPanneauBoutonsJeu().add(getSlamButton());
            //addButtonValidateDogTarot(getMessages().getVal(MainWindow.GO_CARD_GAME), false);
        }
        possibleCallAfterDiscard();
//        updateCardsInPanelTarotDogMulti(getPanelHand(), allCards_, true);
        AbsPanel boutons_=getPanneauBoutonsJeu();
        boutons_.removeAll();
//        getValidateDog().setVisible(true);
        getValidateDog().setEnabled(false);
        getPanneauBoutonsJeu().add(getValidateDog());
        getSlamButton().setEnabled(false);
//        getSlamButton().setVisible(true);
        getPanneauBoutonsJeu().add(getSlamButton());
//        addButtonValidateDogTarotMulti(getMessages().getVal(MainWindow.GO_CARD_GAME), false);
        getPanneauBoutonsJeu().validate();
        pack();
        //PackingWindowAfter.pack(this, true);
//        window().sendObjectTakeCard();
    }

    @Override
    public void discard(CardTarot _t) {
        getTakerCardsDog().jouer(_t);
        getTakerCardsDog().trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
        cardsInDog.ajouter(_t);
    }

    @Override
    public void restore(CardTarot _t) {
        getTakerCardsDog().ajouter(_t);
        getTakerCardsDog().trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
        cardsInDog.jouer(_t);
    }

//    private void addCardDog(CardTarot _ct) {
//        getTakerCardsDog().jouer(_ct);
//        getTakerCardsDog().trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
//        cardsInDog.ajouter(_ct);
//        setChienMulti(cardsInDog,true);
////        JPanel boutons_=getPanneauBoutonsJeu();
//        boolean chienFait_ = cardsInDog.total()==repTarot.getNombreCartesChien();
////        if (boutons_.getComponentCount() > 0) {
//////            LabelButton valide_=(LabelButton)boutons_.getComponent(0);
////            getValidateDog().setEnabledLabel(chienFait_);
////        }
//        getValidateDog().setEnabled(chienFait_);
//        if(chienFait_) {
//            getSlamButton().setEnabled(true);
////            getSlamButton().setVisible(true);
//            //            getPanneauBoutonsJeu().add(getSlamButton());
////            ajouterBoutonJeuChelemTarotMulti(BidTarot.SLAM.toString());
//            getPanneauBoutonsJeu().validate();
//        }
//        /*On place les cartes de l'utilisateur*/
////        setCanDiscard(true);
//        updateCardsInPanelTarotDogMulti(getPanelHand(), getTakerCardsDog(), true);
//        pack();
//        //PackingWindowAfter.pack(this, true);
//    }
//    private void removeCardDog(CardTarot _ct) {
//        getTakerCardsDog().ajouter(_ct);
//        getTakerCardsDog().trier(getDisplayingTarot().getDisplaying().getSuits(), getDisplayingTarot().getDisplaying().isDecreasing());
//        cardsInDog.jouer(_ct);
//        setChienMulti(cardsInDog,true);
////        JPanel boutons_=getPanneauBoutonsJeu();
////        LabelButton valide_=(LabelButton)boutons_.getComponent(0);
//        getValidateDog().setEnabled(false);
//        getSlamButton().setEnabled(false);
//        //        if(boutons_.getComponentCount()==2) {
////            boutons_.remove(1);
////            boutons_.validate();
////        }
//        /*On place les cartes de l'utilisateur*/
////        setCanDiscard(true);
//        updateCardsInPanelTarotDogMulti(getPanelHand(), getTakerCardsDog(), true);
//        pack();
//        //PackingWindowAfter.pack(this, true);
//    }


    private void possibleCallAfterDiscard() {
        if (discardCall) {
            placerBoutonsAppelApres();
        }
        new ContainerSingleWithDiscardUtil<CardTarot>(this).updateCardsInPanels(true);
    }
    public void placerBoutonsAppelApres() {
        //Activer les conseils
        getPanneauBoutonsJeu().removeAll();
        setCalledCard(CardTarot.WHITE);
        ContainerSingleTarot.updateCardsInPanelTarotCallAfterDog(this,callableCards);
        getScrollCallableCards().setVisible(true);
        getValidateDog().setEnabled(false);
        getPanneauBoutonsJeu().add(getValidateDog());
        getSlamButton().setEnabled(false);
//        getSlamButton().setVisible(true);
        getPanneauBoutonsJeu().add(getSlamButton());
        updateButtons();
        pack();
    }
    @Override
    public IdList<CardTarot> ecartables() {
        return GameTarot.ecartables(repTarot.getNombreCartesChien(), getTakerCardsDog()).getCards();
    }

    @Override
    public IdList<CardTarot> hand() {
        return getTakerCardsDog().getCards();
    }

    @Override
    public IdList<CardTarot> discarded() {
        return cardsInDog.getCards();
    }

    @Override
    public String errMessage(IdList<CardTarot> _must, CardTarot _t) {
        return ContainerSingleTarot.err(this, _must,_t,cardsInDog.getCards());
    }

    @Override
    public void afterHands(CardTarot _c) {
        if (!discardCall) {
            boolean chienFait_ = cardsInDog.total()== repTarot.getNombreCartesChien();
            updateButtons(chienFait_);
        } else {
            ContainerSingleTarot.updateCardsInPanelTarotCallAfterDog(this,callableCards);
            updateButtons();
        }
        pack();
        DiscardedCardTarot discard_ = new DiscardedCardTarot();
        discard_.setCard(_c);
        discard_.setPlace(getContainerMultiContent().getIndexInGame());
//        discard_.setLocale("");
        getContainerMultiContent().window().sendObjectTarot(discard_);
    }

    public void updateButtons() {
        boolean chienFait_;
        if (getContratUtilisateur().getJeuChien() == PlayingDog.WITH) {
            chienFait_ = cardsInDog.total()== repTarot.getNombreCartesChien() && getCalledCard() != CardTarot.WHITE;
        } else {
            chienFait_ = getCalledCard() != CardTarot.WHITE;
        }
        updateButtons(chienFait_);
    }

    private void updateButtons(boolean _chienFait) {
        getValidateDog().setEnabled(_chienFait);
        boolean slam_ = _chienFait && getContratUtilisateur() != BidTarot.SLAM;
        getSlamButton().setEnabled(slam_);
    }
//    private void updateCardsInPanelTarotDogMulti(AbsPanel _panel, HandTarot _hand, boolean _ecouteur) {
//        _panel.removeAll();
//        TranslationsLg lg_ = getOwner().getFrames().currentLg();
//        HandTarot dis_ = GameTarot.ecartables(repTarot.getNombreCartesChien(), getTakerCardsDog());
//        for (GraphicCard<CardTarot> c: getGraphicCards(getWindow(),lg_,_hand.getCards())) {
//            if (_ecouteur) {
//                String err_ = ContainerSingleTarot.err(this, dis_.getCards(),c.getCard(),cardsInDog.getCards());
//                if (err_.isEmpty()) {
//                    c.addMouseListener(new ListenerCardTarotMultiDog(this,c.getCard()));
//                } else {
//                    c.getPaintableLabel().setToolTipText(err_);
//                }
//            }
//            _panel.add(c.getPaintableLabel());
//        }
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
//        _panel.setSize(_panel.getPreferredSizeValue());
//    }

    public void updateCardsInPanelTarotCallBeforeDogMulti(boolean _canCall) {
        getPanelCallableCards().removeAll();
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        for (GraphicCard<CardTarot> c: getGraphicCards(getWindow(),lg_,callableCards.getCards())) {
            if (_canCall) {
                c.addMouseListener(new ListenerCardTarotMultiBeforeDog(this, c.getCard()));
            }
            getPanelCallableCards().add(c.getPaintableLabel());
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
        getPanelCallableCards().setSize(getPanelCallableCards().getPreferredSizeValue());
    }
    public void updateCardsInPanelTarotJeuMulti(boolean _canPlay) {
        getPanelHand().removeAll();
        String err_ = errorHandful(_canPlay,rulesTarotMulti);
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        for (GraphicCard<CardTarot> c: getGraphicCards(getWindow(),lg_,playerHand.getCards())) {
//            if (_canPlay) {
//                c.addMouseListener(new ListenerCardTarotMultiGame(this, c.getCard()));
//            }
            if (_canPlay) {
                if (err_.isEmpty() && allowed.contient(c.getCard())) {
                    c.addMouseListener(new ListenerCardTarotMultiGame(this, c.getCard()));
                } else {
                    String mes_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CANT_PLAY_CARD), Games.toString(c.getCard(),lg_));
//                    String finalMessage_ = StringUtil.concat(mes_,ContainerGame.RETURN_LINE,Games.autoriseTarot(partie_, lg_));
                    if (err_.isEmpty()) {
                        c.getPaintableLabel().setToolTipText(mes_);
                    } else {
                        c.getPaintableLabel().setToolTipText(StringUtil.concat(mes_,ContainerGame.RETURN_LINE,err_));
                    }
                }
            }
            getPanelHand().add(c.getPaintableLabel());
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
        getPanelHand().setSize(getPanelHand().getPreferredSizeValue());
    }

//    @Override
//    public byte getIndexInGame() {
//        return indexInGame;
//    }

    public HandTarot getCardsInDog() {
        return cardsInDog;
    }

    public DealingTarot getRepTarot() {
        return repTarot;
    }

    public HandTarot getPlayerHand() {
        return playerHand;
    }

    public void endGame(ResultsTarot _res) {
        Games.setMessages(_res.getRes(),getOwner().getFrames().currentLg());
        _res.getRes().setNicknames(nicknames());
        getPane().removeAll();
        /*Descativer aide au jeu*/
        MenuItemUtils.setEnabledMenu(getContainerMultiContent().window().getMultiStop(),true);
//        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),false);
        AbsPanel container_=getOwner().getCompoFactory().newBorder();
        /*Le nombre de parties jouees depuis le lancement du logiciel*/
//        setThreadAnime(false);
        AbsTabbedPane onglets_=getOwner().getCompoFactory().newAbsTabbedPane();
//        String lg_ = getOwner().getLanguageKey();
        setScores(_res.getRes().getScores());
        _res.getRes().setGeneral(readCoreResourceSuit());
        _res.getRes().setSpecific(readResource());
        _res.getRes().setGeneralCards(readCoreResourceCards());
        RenderedPage editor_;
        CardNatLgNamesNavigation sOne_ = retrieve(FileConst.RESOURCES_HTML_FILES_RESULTS_TAROT).attendreResultat();
        ((TarotStandards)sOne_.getBeanNatLgNames()).setDataBase(_res);
        editor_ = FrameGeneralHelp.initialize(sOne_, getOwner().getFrames(), getContainerMultiContent().window().getGuardRender());
        editor_.getScroll().setPreferredSize(new MetaDimension(300,300));
        onglets_.add(file().getVal(MessagesGuiCards.MAIN_RESULTS_PAGE),editor_.getScroll());
        CardNatLgNamesNavigation sTwo_ = retrieve(FileConst.RESOURCES_HTML_FILES_DETAILS_RESULTS_TAROT).attendreResultat();
        ((TarotStandards)sTwo_.getBeanNatLgNames()).setDataBase(_res);
        editor_ = FrameGeneralHelp.initialize(sTwo_, getOwner().getFrames(), getContainerMultiContent().window().getGuardRender());
        editor_.getScroll().setPreferredSize(new MetaDimension(300,300));
        onglets_.add(file().getVal(MessagesGuiCards.MAIN_DETAIL_RESULTS_PAGE),editor_.getScroll());
        container_.add(onglets_,GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel panneau_=getOwner().getCompoFactory().newPageBox();
        containerMultiContent.endReady(this,panneau_);
//        readyToPlay = false;
//        ready = getOwner().getCompoFactory().newCustCheckBox(containerMultiContent.getMessages().getVal(WindowNetWork.READY));
//        ready.addActionListener(new ReadyEvent(this));
//        panneau_.add(ready);

        AbsPanel panel_ = containerMultiContent.endNickname();

//        int nbCh_ = containerMultiContent.getNbChoosenPlayers();
//        for (int i = IndexConstants.FIRST_INDEX; i< nbCh_; i++) {
//            panel_.add(playersPseudos.get(i));
//            panel_.add(playersPlaces.get(i));
//            panel_.add(playersReady.get(i));
//        }
        panneau_.add(panel_);

        if (containerMultiContent.isHasCreatedServer()) {
            AbsButton button_ = getOwner().getCompoFactory().newPlainButton(containerMultiContent.getMessages().getVal(MessagesGuiCards.PLAY_TAROT));
            button_.addActionListener(new PlayNextDealEvent(this));
            panneau_.add(button_);
        }
        panneau_.add(getWindow().getClock());
        panneau_.add(getWindow().getLastSavedGameDate());
        container_.add(panneau_,GuiConstants.BORDER_LAYOUT_SOUTH);

        setContentPane(container_);
        pack();
        containerMultiContent.sendOk();
        //PackingWindowAfter.pack(this, true);
//        PlayerActionGame ok_ = new PlayerActionGame(PlayerActionGameType.OK);
//        ok_.setPlace(getContainerMultiContent().getIndexInGame());
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
        if (containerMultiContent.notAllReadyDistinct()) {
            return;
        }
        long nb_=chargerNombreDeParties(GameEnum.TAROT, getOwner().getFrames(), 0);
//        GameTarot game_ = Net.getGames(window().getNet()).partieTarot();
//        DealTarot deal_=new DealTarot(nb_,game_.empiler());
//        deal_.donneurSuivant(game_.getDistribution().getDealer(),game_.getRegles());
//        deal_.initDonne(game_.getRegles(),getOwner().getGenerator());
        GameTarot game_=Net.getGames(containerMultiContent.window().getNet()).partieTarot();
        DealTarot deal_=getOwner().baseWindow().getIa().getTarot().empiler(nb_, game_,getOwner().getGenerator());
        Net.getGames(containerMultiContent.window().getNet()).jouerTarot(new GameTarot(GameType.RANDOM,deal_,game_.getRegles()));
//        Net.getGames(window().getNet()).jouerTarot(new GameTarot(GameType.RANDOM,deal_,game_.getRegles()));
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
//            AbsButton button_ = getOwner().getCompoFactory().newPlainButton(containerMultiContent.getMessages().getVal(WindowNetWork.PLAY_TAROT));
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
        if (containerMultiContent.notAllReadyDistinct()) {
            return;
        }

//        HandTarot pile_;
        /*Chargement de la pile de cartes depuis un fichier sinon on la cree*/
//        pile_ = chargerPileTarot();
//        DealTarot deal_ = new DealTarot(0,pile_);
//        deal_.setRandomDealer(rulesTarotMulti,getOwner().getGenerator());
//        deal_.initDonne(rulesTarotMulti,getOwner().getGenerator());
//        Net.getGames(window().getNet()).jouerTarot(new GameTarot(GameType.RANDOM,deal_,rulesTarotMulti));
        Net.getGames(containerMultiContent.window().getNet()).jouerTarot(getWindow().baseWindow().getFirstDealTarot().deal(this,rulesTarotMulti,0));
        containerMultiContent.window().sendObjectPlayGame();
    }

    public RulesTarot getRulesTarotMulti() {
        return rulesTarotMulti;
    }

    public void setRulesTarotMulti(RulesTarot _r) {
        this.rulesTarotMulti = _r;
        Net.getGames(getContainerMultiContent().window().getNet()).setRulesTarot(getRulesTarotMulti());
    }

    @Override
    public AbsActionListenerAct guard() {
        return new AlwaysActionListenerAct();
    }

    @Override
    public AbsActionListener bid(BidTarot _action) {
        return new ListenerBidTarotMulti(this,_action);
    }

    public ContainerMultiContent getContainerMultiContent() {
        return containerMultiContent;
    }

//    @Override
//    public WindowNetWork window() {
//        return win;
//    }
    public boolean isDiscardCall() {
        return discardCall;
    }
    protected void setDiscardCall(boolean _discardCall) {
        discardCall = _discardCall;
    }
}

