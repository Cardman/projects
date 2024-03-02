package cards.gui.containers;






import cards.consts.GameType;
import cards.facade.Games;
import cards.facade.enumerations.GameEnum;
import cards.gui.containers.events.ChangePlaceEvent;
import cards.gui.containers.events.GiveCardsEvent;
import cards.gui.containers.events.NoPlayPresidentEvent;
import cards.gui.containers.events.PlayFirstDealEvent;
import cards.gui.containers.events.PlayNextDealEvent;
import cards.gui.containers.events.ReadyEvent;
import cards.gui.dialogs.*;
import cards.gui.events.ListenerCardPresidentMultiGame;
import cards.gui.labels.GraphicCard;
import cards.gui.panels.CarpetPresident;
import cards.main.CardNatLgNamesNavigation;
import cards.network.common.*;
import cards.network.common.before.ChoosenPlace;
import cards.network.common.before.PlayersNamePresent;
import cards.network.common.before.Ready;
import cards.network.president.actions.DiscardedCards;
import cards.network.president.actions.PlayingCardPresident;
import cards.network.president.displaying.*;
import cards.network.president.displaying.errors.ErrorPlayingPresident;
import cards.network.president.displaying.players.RefreshHandPlayingPresident;
import cards.network.president.displaying.players.RefreshingDonePresident;
import cards.network.president.unlock.AllowDiscarding;
import cards.network.president.unlock.AllowPlayingPresident;
import cards.network.threads.Net;
import cards.president.*;
import cards.president.beans.PresidentStandards;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.Playing;
import code.gui.*;
import code.gui.document.RenderedPage;
import code.gui.images.MetaDimension;
import code.network.WindowNetWork;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.comparators.ComparatorBoolean;
import code.util.core.BoolVal;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public class ContainerMultiPresident extends ContainerPresident implements
        ContainerMulti,ContainerPlayablePresident {

    private int noClient;
    private byte indexInGame = IndexConstants.INDEX_NOT_FOUND_ELT;
    private RulesPresident rulesPresidentMulti = new RulesPresident();
    private NumComboBox choiceOfPlaceForPlayingGame;
    private AbsCustCheckBox ready;

//    private DealingPresident repPresident;
    private int nbChoosenPlayers = IndexConstants.INDEX_NOT_FOUND_ELT;
    private boolean hasCreatedServer;
    private boolean readyToPlay;
    private final CustList<AbsPlainLabel> playersPseudos = new CustList<AbsPlainLabel>();
    private final CustList<AbsPlainLabel> playersPlaces = new CustList<AbsPlainLabel>();
    private final CustList<AbsCustCheckBox> playersReady = new CustList<AbsCustCheckBox>();
    private RenderedPage editor;
    private IntTreeMap< Byte> playersPlacesForGame = new IntTreeMap< Byte>();
    private IntMap<String> playersPseudosForGame = new IntMap<String>();
    private HandPresident playerHandPresident = new HandPresident();
    private final AbsPlainLabel canPlayLabel = getOwner().getCompoFactory().newPlainLabel("");
    private int nbCardsDiscard;
    private final WindowNetWork win;
    private boolean enabledPass;
    private boolean reversedGame;

    public ContainerMultiPresident(WindowNetWork _window, boolean _hasCreatedServer, int _nbPlayers) {
        super(_window);
        win = _window;
        hasCreatedServer = _hasCreatedServer;
        if (hasCreatedServer) {
            Net.getGames(_window.getNet()).setRulesBelote(null);
            setRulesPresidentMulti(new RulesPresident(_nbPlayers));
            Net.getGames(_window.getNet()).setRulesPresident(getRulesPresidentMulti());
            Net.getGames(_window.getNet()).setRulesTarot(null);
        }
    }

    @Override
    public void updateFirst(PlayersNamePresent _players) {
        getPane().removeAll();
        MenuItemUtils.setEnabledMenu(window().getMultiStop(),true);
        MenuItemUtils.setEnabledMenu(window().getTricksHands(),true);
        MenuItemUtils.setEnabledMenu(window().getTeams(),true);
//        MenuItemUtils.setEnabledMenu(getLoad(),false);
        rulesPresidentMulti = _players.getRulesPresident();
        nbChoosenPlayers = _players.getNbPlayers();
        AbsPanel container_ = getOwner().getCompoFactory().newPageBox();
        AbsPanel panel_ = getOwner().getCompoFactory().newGrid(0, 2);
        panel_.add(getOwner().getCompoFactory().newPlainLabel(getMessages().getVal(WindowNetWork.PLACE)));
        choiceOfPlaceForPlayingGame = new NumComboBox(getOwner().getFrames());
        choiceOfPlaceForPlayingGame.setItems(nbChoosenPlayers);
        choiceOfPlaceForPlayingGame.setSelectedItem(_players.getPseudos()
                .size() - 1);
        indexInGame = (byte) NumberUtil.parseInt(choiceOfPlaceForPlayingGame.getSelectedItem());
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

        rulesPresidentMulti.getCommon().setGeneral(WindowNetWork.readCoreResourceMix(this));
        rulesPresidentMulti.getCommon().setSpecific(readResource());
        CardNatLgNamesNavigation stds_ = retrieve(FileConst.RESOURCES_HTML_FILES_RULES_PRESIDENT).attendreResultat();
        ((PresidentStandards)stds_.getBeanNatLgNames()).setDataBaseRules(rulesPresidentMulti);
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
            updateButton(container_);
            AbsButton button_ = getOwner().getCompoFactory().newPlainButton(getMessages().getVal(WindowNetWork.PLAY_PRESIDENT));
            button_.addActionListener(new PlayFirstDealEvent(this));
            container_.add(button_);
        }
        container_.add(getWindow().getClock());
        container_.add(getWindow().getLastSavedGameDate());
        setContentPane(container_);
        //PackingWindowAfter.pack(this, true);
        pack();
    }

    private void updateButton(AbsPanel _container) {
        DialogPresidentContent content_ = new DialogPresidentContent(getOwner().getFrames());
        AbsTabbedPane jt_ = content_.initJt(null, false, nbChoosenPlayers, getOwner());
        AbsPanel border_ = getOwner().getCompoFactory().newBorder();
        border_.add(jt_, GuiConstants.BORDER_LAYOUT_CENTER);
        AbsButton bouton_= getOwner().getCompoFactory().newPlainButton(getMessages().getVal(WindowNetWork.SELECT_RULES));
        bouton_.addActionListener(new AfterValidateRulesPresidentMulti(content_,this));
        border_.add(bouton_,GuiConstants.BORDER_LAYOUT_SOUTH);
        _container.add(border_);
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

    public void updateRules(RulesPresident _rules) {
        rulesPresidentMulti = _rules;
        CardNatLgNamesNavigation stds_ = retrieve(FileConst.RESOURCES_HTML_FILES_RULES_PRESIDENT).attendreResultat();
        ((PresidentStandards)stds_.getBeanNatLgNames()).setDataBaseRules(rulesPresidentMulti);
        FrameGeneralHelp.initialize(stds_, editor);
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
        PlayerActionGame dealt_ = new PlayerActionGame(PlayerActionGameType.DEALT);
        dealt_.setPlace(indexInGame);
        String lg_ = getOwner().getLanguageKey();
        dealt_.setLocale(lg_);
        window().sendObject(dealt_);
    }

    public void canDiscardPresident(AllowDiscarding _allow) {
        nbCardsDiscard = _allow.getReceivedCards().total();
        setGivingCardsOk(getOwner().getCompoFactory().newPlainButton(WindowNetWork.OK));
        getGivingCardsOk().setEnabled(false);
        getGivingCardsOk().addActionListener(new GiveCardsEvent(this));
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
        String lg_ = getOwner().getLanguageKey();
        DiscardedCards dis_ = new DiscardedCards();
        dis_.setPlace(indexInGame);
        dis_.setDiscarded(cards_);
        dis_.setLocale(lg_);
        window().sendObject(dis_);
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
        String lg_ = getOwner().getLanguageKey();
        PlayerActionGame r_ = new PlayerActionGame(PlayerActionGameType.REFHESHED_HAND_PRESIDENT);
        r_.setPlace(indexInGame);
        r_.setLocale(lg_);
        window().sendObject(r_);
    }

    public void canPlayPresident(AllowPlayingPresident _readObject) {
//        setRaisonCourante(EMPTY);
        canPlayLabel.setText(getMessages().getVal(WindowNetWork.CAN_PLAY));
        reversedGame = _readObject.isReversed();
        updateCardsInPanelPresidentMulti(true);
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().add(assemble());
        noPlayText(_readObject.getStatus());
        enabledPass = _readObject.isEnabledPass();
        getNoPlay().setEnabled(enabledPass);
        getNoPlay().setVisible(true);
        //        getPanneauBoutonsJeu().add(getNoPlay());
        getPanneauBoutonsJeu().validate();
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),true);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),true);
        pack();
    }

    public void errorPlayingCard(ErrorPlayingPresident _readObject) {
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        getNoPlay().setEnabled(enabledPass);
        updateCardsInPanelPresidentMulti(true);
        if (_readObject.isPassIssue()) {
            String title_ = getMessages().getVal(WindowNetWork.CANT_PLAY_CARD_TITLE);
            getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(), _readObject.getReason(), title_, GuiConstants.ERROR_MESSAGE);
        } else {
            String mes_ = StringUtil.simpleStringsFormat(file().getVal(MessagesGuiCards.MAIN_CANT_PLAY_CARD), Games.toString(_readObject.getCard(),lg_));
            String finalMessage_ = StringUtil.concat(mes_,RETURN_LINE,_readObject.getReason());
            String title_ = getMessages().getVal(WindowNetWork.CANT_PLAY_CARD_TITLE);
            getOwner().getFrames().getMessageDialogAbs().input(getOwner().getCommonFrame(), finalMessage_, title_, GuiConstants.ERROR_MESSAGE);
        }
    }

    public void displayPlayedCard(PlayingCardPresident _card) {
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        canPlayLabel.setText(EMPTY_STRING);
        byte relative_ = relative(_card.getNextPlayer());
        ByteMap<Playing> status_ = new ByteMap<Playing>();
        for (byte p: _card.getStatus().getKeys()) {
            status_.put(relative(p), _card.getStatus().getVal(p));
        }
        tapisPresident().setTalonPresident(getWindow().getImageFactory(),lg_,_card.getPlayedHand());
//        tapisPresident().repaintValidate();
        tapisPresident().setStatus(getWindow().getImageFactory(),lg_,status_, relative_);
//        tapisPresident().repaintValidate();

        String pseudo_ = getPseudoByPlace(_card.getPlace());
        ajouterTexteDansZone(StringUtil.concat(pseudo_, INTRODUCTION_PTS, Games.toString(_card.getPlayedHand(),lg_), RETURN_LINE));
        //PackingWindowAfter.pack(this, true);
        pack();
        PlayerActionGame dealt_ = new PlayerActionGame(PlayerActionGameType.DONE_PLAYING);
        dealt_.setPlace(indexInGame);
        dealt_.setLocale(lg_.getKey());
        window().sendObject(dealt_);
    }

    @Override
    public void noPlay() {
//        if (!isCanPlay()) {
//            return;
//        }
        String lg_ = getOwner().getLanguageKey();
        updateCardsInPanelPresidentMulti(false);
        getNoPlay().setEnabled(false);
        PlayingCardPresident pl_ = new PlayingCardPresident();
        pl_.setLocale(lg_);
        pl_.setPlace(indexInGame);
        pl_.setPass(true);
        pl_.setPlayedCard(CardPresident.WHITE);
        pl_.setPlayedHand(new HandPresident());
        pl_.setStatus(new ByteMap< Playing>());
        window().sendObject(pl_);
    }

    public void refreshHand(RefreshHandPlayingPresident _card) {
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
        RefreshingDonePresident ref_ = new RefreshingDonePresident();
        ref_.setPlayedHand(_card.getPlayedHand());
        ref_.setStatus(_card.getStatus());
        ref_.setNextPlayer(_card.getNextPlayer());
        ref_.setPlace(indexInGame);
        ref_.setPlayedCard(CardPresident.WHITE);
        String lg_ = getOwner().getLanguageKey();
        ref_.setLocale(lg_);
        window().sendObject(ref_);

    }

    @Override
    public void pauseBetweenTrick() {
        tapisPresident().setTalonPresident(getWindow().getImageFactory());
//        tapisPresident().repaintValidate();
        //pack();
        String lg_ = getOwner().getLanguageKey();
        PlayerActionGame d_ = new PlayerActionGame(PlayerActionGameType.DONE_PAUSE);
        d_.setPlace(indexInGame);
        d_.setLocale(lg_);
        window().sendObject(d_);
    }

    @Override
    public void showTricksHands() {
//        if (!isCanPlay()) {
//            return;
//        }
        PlayerActionGame select_ = new PlayerActionGame(PlayerActionGameType.SELECT_TRICKS_HANDS);
        select_.setPlace(indexInGame);
        String lg_ = getOwner().getLanguageKey();
        select_.setLocale(lg_);
        window().sendObject(select_);
    }

    public void showTricksHands(TricksHandsPresident _tricks) {
        ByteTreeMap< String> pseudos_ = new ByteTreeMap< String>();
        byte p_ = 0;
        for (String s : pseudosPresident((byte) nbChoosenPlayers)) {
            pseudos_.put(p_, s);
            p_++;
        }
        for (byte p : playersPlacesForGame.values()) {
            pseudos_.put(p, getPseudoByPlace(p));
        }
        StringList list_ = new StringList(pseudos_.values());
        WindowNetWork ow_ = window();
        DialogTricksPresident.setDialogTricksPresident(
                file().getVal(MessagesGuiCards.MAIN_HANDS_TRICKS_PRESIDENT), ow_);
        DialogTricksPresident.init(_tricks, (byte) nbChoosenPlayers, list_,
                getDisplayingPresident(),ow_);
    }

    @Override
    public void setNoClient(int _noClient) {
        noClient = _noClient;
    }

    @Override
    public int getNoClient() {
        return noClient;
    }

    private void placerIhmPresidentMulti(ByteMap<Playing> _status, int _nbMax) {
        getPane().removeAll();
        MenuItemUtils.setEnabledMenu(window().getMultiStop(),false);
        AbsPanel container_ = getOwner().getCompoFactory().newBorder();
        container_.add(getOwner().getCompoFactory().newPlainLabel(helpMenuTip()), GuiConstants.BORDER_LAYOUT_NORTH);
        TranslationsLg lg_ = getOwner().getFrames().currentLg();
        CarpetPresident tapis_ = new CarpetPresident();
        ByteTreeMap< String> pseudos_ = new ByteTreeMap< String>();
        byte p_ = 0;
        for (String s : pseudosPresident((byte) nbChoosenPlayers)) {
            pseudos_.put(p_, s);
            p_++;
        }
        for (byte p : playersPlacesForGame.values()) {
            byte relative_ = relative(p);
            pseudos_.put(relative_, getPseudoByPlace(p));
        }
        ByteMap<Playing> status_ = new ByteMap<Playing>();
        for (byte p: _status.getKeys()) {
            status_.put(relative(p), _status.getVal(p));
        }
        StringList list_ = new StringList(pseudos_.values());
        tapis_.initTapisPresident(lg_,list_, status_, _nbMax, getOwner().getCompoFactory());
        getTapis().setTapisPresident(tapis_);
        container_.add(tapis_.getContainer(), GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel panneau_;
        panneau_= getOwner().getCompoFactory().newLineBox();
        panneau_.setBackground(GuiConstants.BLUE);
        setPanelHand(panneau_);
        container_.add(panneau_, GuiConstants.BORDER_LAYOUT_SOUTH);
        AbsPanel panneau2_=getOwner().getCompoFactory().newPageBox();
        setEvents(getOwner().getCompoFactory().newTextArea(EMPTY,8, 30));
        getEvents().setEditable(false);
        panneau2_.add(getOwner().getCompoFactory().newAbsScrollPane(getEvents()));
        setHandfuls(new ByteMap<AbsPlainLabel>());
        setDeclaredHandfuls(new ByteMap<AbsPanel>());
//        JPanel declaredHandfuls_ = new JPanel(new GridLayout(0,1));
//        int nbPlayers_ = partie_.getNombreDeJoueurs();
        AbsPanel sousPanneau_=getOwner().getCompoFactory().newPageBox();
        AbsPanel panelCards_ = getOwner().getCompoFactory().newLineBox();
        AbsPanel panelDiscard_;
        panelDiscard_= getOwner().getCompoFactory().newLineBox();
        panelDiscard_.setTitledBorder(file().getVal(MessagesGuiCards.MAIN_GIVEN_CARDS));
        panelCards_.add(panelDiscard_);
        setPanelGivenCards(panelDiscard_);
        AbsPanel panelRec_;
        panelRec_= getOwner().getCompoFactory().newLineBox();
        panelRec_.setTitledBorder(file().getVal(MessagesGuiCards.MAIN_RECEIVED_CARDS));
        panelCards_.add(panelRec_);
        setPanelReceivedCards(panelRec_);
        sousPanneau_.add(panelCards_);
        setNoPlay(getOwner().getCompoFactory().newPlainButton(EMPTY));
        getNoPlay().addActionListener(new NoPlayPresidentEvent(this));
        setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(getOwner().getCompoFactory().newAbsScrollPane(sousPanneau_));
        getNoPlay().setVisible(false);
        panneau2_.add(getNoPlay());
        setActionsHistory(panneau2_);
        container_.add(panneau2_,GuiConstants.BORDER_LAYOUT_EAST);
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

    public void updateCardsInPanelPresidentMulti(boolean _listener) {
        updateCardsInPanelPresidentMulti(getPanelHand(), playerHandPresident, reversedGame, _listener);
    }
    private void updateCardsInPanelPresidentMulti(AbsPanel _panel, HandPresident _hand, boolean _reversed, boolean _listener) {
        _panel.removeAll();
        int str_ = 0;
        int iter_ = IndexConstants.FIRST_INDEX;
        byte index_ = IndexConstants.SECOND_INDEX;
        for (GraphicCard<CardPresident> c: getGraphicCards(this,_hand.getCards())) {
            int curStr_ = c.getCard().strength(_reversed);
            if (iter_ > IndexConstants.FIRST_INDEX) {
                if (curStr_ == str_) {
                    index_++;
                } else {
                    index_ = IndexConstants.SECOND_INDEX;
                }
            }
            if (_listener) {
                c.addMouseListener(new ListenerCardPresidentMultiGame(this,c.getCard(), index_));
            }
            str_ = curStr_;
            iter_++;
            _panel.add(c.getPaintableLabel());
        }
        _panel.validate();
    }

    @Override
    public boolean hasCreatedServer() {
        return hasCreatedServer;
    }

    @Override
    public byte getIndexInGame() {
        return indexInGame;
    }

    public void endGame(ResultsPresident _res) {
        getPane().removeAll();
        /*Descativer aide au jeu*/
        MenuItemUtils.setEnabledMenu(window().getMultiStop(),true);
//        MenuItemUtils.setEnabledMenu(getHelpGame(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTricksHands(),false);
        MenuItemUtils.setEnabledMenu(getOwner().getTeams(),false);
        AbsPanel container_=getOwner().getCompoFactory().newBorder();

        /*Le nombre de parties jouees depuis le lancement du logiciel*/
//        setThreadAnime(false);

        AbsTabbedPane onglets_=getOwner().getCompoFactory().newAbsTabbedPane();
        String lg_ = getOwner().getLanguageKey();
        setScores(_res.getRes().getScores());

        RenderedPage editor_;
        CardNatLgNamesNavigation stds_ = retrieve(FileConst.RESOURCES_HTML_FILES_RESULTS_PRESIDENT).attendreResultat();
        ((PresidentStandards)stds_.getBeanNatLgNames()).setDataBase(_res);
        editor_ = FrameGeneralHelp.initialize(stds_, getOwner().getFrames());
        editor_.getScroll().setPreferredSize(new MetaDimension(300,300));
        onglets_.add(file().getVal(MessagesGuiCards.MAIN_RESULTS_PAGE),editor_.getScroll());
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
            AbsButton button_ = getOwner().getCompoFactory().newPlainButton(getMessages().getVal(WindowNetWork.PLAY_PRESIDENT));
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
        GamePresident game_=Net.getGames(window().getNet()).partiePresident();
        long nb_=chargerNombreDeParties(GameEnum.PRESIDENT, getOwner().getFrames(), game_.getRules().getNbStacks());
        Bytes rk_ = game_.getNewRanks();
        DealPresident deal_=getOwner().baseWindow().getIa().getPresident().empiler(nb_,game_,getOwner().getGenerator());
//        DealPresident deal_=new DealPresident(nb_,game_.empiler());
//        deal_.donneurSuivant(game_.getDeal().getDealer(), game_.getRules());
//        deal_.initDonne(game_.getRules(),getOwner().getGenerator());
        Net.getGames(window().getNet()).jouerPresident(new GamePresident(GameType.RANDOM,deal_, game_.getRules(), rk_));
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
            updateButton(container_);
            AbsButton button_ = getOwner().getCompoFactory().newPlainButton(getMessages().getVal(WindowNetWork.PLAY_PRESIDENT));
            button_.addActionListener(new PlayFirstDealEvent(this));
            container_.add(button_);
            pack();
            //PackingWindowAfter.pack(this, true);
        }
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
        Net.getGames(window().getNet()).jouerPresident(getOwner().baseWindow().getFirstDealPresident().deal(this,rulesPresidentMulti,0));
//        Net.getGames(window().getNet()).jouerPresident(new GamePresident(
//                GameType.RANDOM, deal_, rulesPresidentMulti, new Bytes()));
        window().sendObjectPlayGame();
    }

    @Override
    public WindowNetWork window() {
        return win;
    }

    public RulesPresident getRulesPresidentMulti() {
        return rulesPresidentMulti;
    }

    public void setRulesPresidentMulti(RulesPresident _r) {
        this.rulesPresidentMulti = _r;
    }

    public StringMap<String> readResource() {
        return Games.getCommonPresidentTr(readResourceAppli()).getMapping();
//        return MessagesPresidentPresident.ms().getVal(StringUtil.concat(PresidentResoucesAccess.NOM_DOSSIER, "/",getOwner().getLanguageKey(), "/", PresidentResoucesAccess.NOM_FICHIER));
    }
}
