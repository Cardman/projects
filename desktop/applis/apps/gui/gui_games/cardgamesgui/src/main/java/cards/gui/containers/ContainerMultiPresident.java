package cards.gui.containers;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JOptionPane;

import cards.consts.GameType;
import cards.facade.Games;
import cards.facade.enumerations.GameEnum;
import cards.gui.WindowCards;
import cards.gui.containers.events.ChangePlaceEvent;
import cards.gui.containers.events.ChangeRulesEvent;
import cards.gui.containers.events.GiveCardsEvent;
import cards.gui.containers.events.NoPlayPresidentEvent;
import cards.gui.containers.events.PlayFirstDealEvent;
import cards.gui.containers.events.PlayNextDealEvent;
import cards.gui.containers.events.ReadyEvent;
import cards.gui.dialogs.DialogRulesPresident;
import cards.gui.dialogs.DialogTricksPresident;
import cards.gui.dialogs.FileConst;
import cards.gui.events.ListenerCardPresidentDiscard;
import cards.gui.events.ListenerCardPresidentMultiGame;
import cards.gui.labels.GraphicPresidentCard;
import cards.gui.panels.CarpetPresident;
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
import cards.president.DealPresident;
import cards.president.GamePresident;
import cards.president.HandPresident;
import cards.president.ResultsPresident;
import cards.president.RulesPresident;
import cards.president.TricksHandsPresident;
import cards.president.beans.PresidentStandards;
import cards.president.enumerations.CardPresident;
import cards.president.enumerations.Playing;
import code.gui.*;
import code.gui.document.PreparedAnalyzed;
import code.gui.document.RenderedPage;
import code.util.CustList;
import code.util.*;
import code.util.StringList;
import code.util.comparators.ComparatorBoolean;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public class ContainerMultiPresident extends ContainerPresident implements
        ContainerMulti {

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

    public ContainerMultiPresident(WindowCards _window, boolean _hasCreatedServer, int _nbPlayers) {
        super(_window);
        hasCreatedServer = _hasCreatedServer;
        if (hasCreatedServer) {
            Net.getGames(_window.getNet()).setRulesBelote(null);
            rulesPresidentMulti = new RulesPresident(_nbPlayers);
            Net.getGames(_window.getNet()).setRulesPresident(rulesPresidentMulti);
            Net.getGames(_window.getNet()).setRulesTarot(null);
        }
    }

    @Override
    public void updateFirst(PlayersNamePresent _players) {
        getMultiStop().setEnabledMenu(true);
        getTricksHands().setEnabledMenu(true);
        getTeams().setEnabledMenu(true);
        getLoad().setEnabledMenu(false);
        rulesPresidentMulti = _players.getRulesPresident();
        nbChoosenPlayers = _players.getNbPlayers();
        AbsPanel container_ = getOwner().getCompoFactory().newPageBox();
        AbsPanel panel_ = getOwner().getCompoFactory().newGrid(0, 2);
        panel_.add(getOwner().getCompoFactory().newPlainLabel(getMessages().getVal(WindowCards.PLACE)));
        choiceOfPlaceForPlayingGame = new NumComboBox(getOwner().getFrames(),getOwner().getFrames().getGeneComboBox());
        for (int i = IndexConstants.FIRST_INDEX; i < nbChoosenPlayers; i++) {
            choiceOfPlaceForPlayingGame.addItem(i);
        }
        choiceOfPlaceForPlayingGame.setSelectedItem(_players.getPseudos()
                .size() - 1);
        indexInGame = choiceOfPlaceForPlayingGame.getCurrent().byteValue();
        choiceOfPlaceForPlayingGame.setListener(new ChangePlaceEvent(this));
        panel_.add(choiceOfPlaceForPlayingGame.self());
        ready = getOwner().getCompoFactory().newCustCheckBox(getMessages().getVal(WindowCards.READY));
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

        AbsScrollPane scroll_ = getOwner().getCompoFactory().newAbsScrollPane();
        editor = new RenderedPage(scroll_, getOwner().getFrames());
        rulesPresidentMulti.setGeneral(readCoreResource());
        rulesPresidentMulti.setSpecific(readResource());
        PreparedAnalyzed stds_ = retrieve(FileConst.RESOURCES_HTML_FILES_RULES_PRESIDENT);
        ((PresidentStandards)stds_.getBeanNatLgNames()).setDataBaseRules(rulesPresidentMulti);
        editor.initialize(stds_);

        scroll_.setPreferredSize(new Dimension(300,400));
        container_.add(scroll_);

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
            playersReady.get(i).setSelected(_players.getReadyPlayers().getVal(i));
        }
        if (hasCreatedServer) {
            AbsPlainButton buttonRules_ = getOwner().getCompoFactory().newPlainButton(getMessages().getVal(WindowCards.SELECT_RULES));
            buttonRules_.addActionListener(new ChangeRulesEvent(this));
            container_.add(buttonRules_);
            AbsPlainButton button_ = getOwner().getCompoFactory().newPlainButton(getMessages().getVal(WindowCards.PLAY_PRESIDENT));
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
        indexInGame = choiceOfPlaceForPlayingGame.getCurrent().byteValue();
        ChoosenPlace choice_ = new ChoosenPlace();
        choice_.setIndex(noClient);
        choice_.setPlace(indexInGame);
        choice_.setPlacesPlayers(new IntTreeMap< Byte>());
        getOwner().sendObject(choice_);
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
            playersReady.get(i).setSelected(_players.getReadyPlayers().getVal(i));
        }
    }

    public void updateRules(RulesPresident _rules) {
        rulesPresidentMulti = _rules;
        PreparedAnalyzed stds_ = retrieve(FileConst.RESOURCES_HTML_FILES_RULES_PRESIDENT);
        ((PresidentStandards)stds_.getBeanNatLgNames()).setDataBaseRules(rulesPresidentMulti);
        editor.initialize(stds_);
    }

    public void updateForBeginningGame(DealtHandPresident _hand) {
        placerIhmPresidentMulti(_hand.getStatus(), _hand.getMaxCards());

        playerHandPresident = _hand.getCards();
        playerHandPresident.sortCards(getDisplayingPresident().isDecreasing(), false);
        setCarteEntree(false);
        setCarteSortie(false);
        setCanDiscard(false);
        setCanPlay(false);
        /* On place les cartes de l'utilisateur */
        updateCardsInPanelPresidentMulti(getPanelHand(), playerHandPresident, false);
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
        getOwner().sendObject(dealt_);
    }

    public void canDiscardPresident(AllowDiscarding _allow) {
        setCanDiscard(true);
        nbCardsDiscard = _allow.getReceivedCards().total();
        setGivingCardsOk(getOwner().getCompoFactory().newPlainButton(WindowCards.OK));
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
        updateCardsInPanelPresidentDiscard(getPanelGivenCards(), getGivenCards(), false);
        updateCardsInPanelPresidentDiscard(getPanelHand(), getVirtualHand(), true);
        pack();
    }

    @Override
    public void discard(byte _index) {
        super.discard(_index);
        updateCardsInPanelPresidentDiscard(getPanelHand(), getVirtualHand(), true);
        updateCardsInPanelPresidentDiscard(getPanelGivenCards(), getGivenCards(), false);
        getGivingCardsOk().setEnabled(nbCardsDiscard == getGivenCards().total());
        pack();
    }

    @Override
    public void cancelDiscard(byte _index) {
        super.cancelDiscard(_index);
        updateCardsInPanelPresidentDiscard(getPanelHand(), getVirtualHand(), true);
        updateCardsInPanelPresidentDiscard(getPanelGivenCards(), getGivenCards(), false);
        getGivingCardsOk().setEnabled(false);
        pack();
    }

    @Override
    public void discard() {
        //The deal is now ready
        setCanDiscard(false);
        getGivingCardsOk().setVisible(false);
        setCanPlay(false);
        playerHandPresident.supprimerCartes(getGivenCards());
        playerHandPresident.sortCards(getDisplayingPresident().isDecreasing(), false);
        updateCardsInPanelPresidentMulti(getPanelHand(), playerHandPresident, false);
        getNoPlay().setVisible(true);
        pack();
        String lg_ = getOwner().getLanguageKey();
        DiscardedCards dis_ = new DiscardedCards();
        dis_.setPlace(indexInGame);
        dis_.setDiscarded(getGivenCards());
        dis_.setLocale(lg_);
        getOwner().sendObject(dis_);
    }

    public void refreshLoserHand(ReceivedGivenCards _readObject) {
        playerHandPresident = _readObject.getNewHand();
        playerHandPresident.sortCards(getDisplayingPresident().isDecreasing(), false);
        updateCardsInPanelPresidentMulti(getPanelHand(), playerHandPresident, false);
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
        getOwner().sendObject(r_);
    }

    public void canPlayPresident(AllowPlayingPresident _readObject) {
        setRaisonCourante(EMPTY);
        setCanPlay(true);
        canPlayLabel.setText(getMessages().getVal(WindowCards.CAN_PLAY));
        updateCardsInPanelPresidentMulti(getPanelHand(), playerHandPresident, _readObject.isReversed());
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().add(assemble());
        if (_readObject.getStatus() == Playing.HAS_TO_EQUAL) {
            getNoPlay().setText(getMessages().getVal(WindowCards.NO_PLAY_NOW));
        } else {
            getNoPlay().setText(getMessages().getVal(WindowCards.PASS_TRICK));
        }
        getNoPlay().setEnabled(_readObject.isEnabledPass());
        getNoPlay().setVisible(true);
        //        getPanneauBoutonsJeu().add(getNoPlay());
        getPanneauBoutonsJeu().validate();
        getOwner().getTricksHands().setEnabledMenu(true);
        getOwner().getTeams().setEnabledMenu(true);
        pack();
    }

    public void errorPlayingCard(ErrorPlayingPresident _readObject) {
        String lg_ = getOwner().getLanguageKey();
        setCanPlay(true);
        if (_readObject.isPassIssue()) {
            String title_ = getMessages().getVal(WindowCards.CANT_PLAY_CARD_TITLE);
            ConfirmDialog.showMessage(getOwner(), _readObject.getReason(), title_, lg_, JOptionPane.ERROR_MESSAGE);
        } else {
            String mes_ = StringUtil.simpleStringsFormat(getMessages().getVal(WindowCards.CANT_PLAY_CARD), Games.toString(_readObject.getCard(),lg_));
            String finalMessage_ = StringUtil.concat(mes_,RETURN_LINE,_readObject.getReason());
            String title_ = getMessages().getVal(WindowCards.CANT_PLAY_CARD_TITLE);
            ConfirmDialog.showMessage(getOwner(), finalMessage_, title_, lg_, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void displayPlayedCard(PlayingCardPresident _card) {
        String lg_ = getOwner().getLanguageKey();
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
        dealt_.setLocale(lg_);
        getOwner().sendObject(dealt_);
    }

    @Override
    public void noPlay() {
        if (!isCanPlay()) {
            return;
        }
        String lg_ = getOwner().getLanguageKey();
        setCanPlay(false);
        PlayingCardPresident pl_ = new PlayingCardPresident();
        pl_.setLocale(lg_);
        pl_.setPlace(indexInGame);
        pl_.setPass(true);
        pl_.setPlayedCard(CardPresident.WHITE);
        pl_.setPlayedHand(new HandPresident());
        pl_.setStatus(new ByteMap< Playing>());
        getOwner().sendObject(pl_);
    }

    public void refreshHand(RefreshHandPlayingPresident _card) {
//        if (_card.getPlace() == indexInGame) {
//            playerHandPresident.supprimerCartes(_card.getPlayedHand());
//        }
        playerHandPresident.supprimerCartes(_card.getPlayedHand());
        playerHandPresident.sortCards(getDisplayingPresident().isDecreasing(), _card.isReversed());
        getPanneauBoutonsJeu().removeAll();
        setCanPlay(false);
        getOwner().getTricksHands().setEnabledMenu(false);
        getOwner().getTeams().setEnabledMenu(false);
        /* On place les cartes de l'utilisateur */
        updateCardsInPanelPresidentMulti(getPanelHand(), playerHandPresident, _card.isReversed());
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
        getOwner().sendObject(ref_);

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
        getOwner().sendObject(d_);
    }

    @Override
    public void showTricksHands() {
        if (!isCanPlay()) {
            return;
        }
        PlayerActionGame select_ = new PlayerActionGame(PlayerActionGameType.SELECT_TRICKS_HANDS);
        select_.setPlace(indexInGame);
        String lg_ = getOwner().getLanguageKey();
        select_.setLocale(lg_);
        getOwner().sendObject(select_);
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
        WindowCards ow_ = getOwner();
        DialogTricksPresident.setDialogTricksPresident(
                getMessages().getVal(WindowCards.HANDS_TRICKS_PRESIDENT), ow_);
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
        AbsPanel container_ = getOwner().getCompoFactory().newBorder();
        container_.add(getOwner().getCompoFactory().newPlainLabel(getMessages().getVal(WindowCards.HELP_GO_MENU)), BorderLayout.NORTH);
        String lg_ = getOwner().getLanguageKey();
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
        container_.add(tapis_.getContainer(), BorderLayout.CENTER);
        AbsPanel panneau_;
        panneau_= getOwner().getCompoFactory().newLineBox();
        panneau_.setBackground(Color.BLUE);
        setPanelHand(panneau_);
        container_.add(panneau_, BorderLayout.SOUTH);
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
        panelDiscard_.setTitledBorder(getMessages().getVal(WindowCards.GIVEN_CARDS));
        panelCards_.add(panelDiscard_);
        setPanelGivenCards(panelDiscard_);
        AbsPanel panelRec_;
        panelRec_= getOwner().getCompoFactory().newLineBox();
        panelRec_.setTitledBorder(getMessages().getVal(WindowCards.RECEIVED_CARDS));
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
        container_.add(panneau2_,BorderLayout.EAST);
        AbsPanel panel_ = getOwner().getCompoFactory().newPageBox();
        panel_.add(getOwner().getCompoFactory().newAbsScrollPane(container_));
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
        for (byte p = IndexConstants.FIRST_INDEX; p < indexInGame; p++) {
            if (p == _otherPlayerIndex) {
                return iter_;
            }
            iter_++;
        }
        return iter_;
    }

    private void updateCardsInPanelPresidentMulti(AbsPanel _panel, HandPresident _hand, boolean _reversed) {
        _panel.removeAll();
        int str_ = 0;
        int iter_ = IndexConstants.FIRST_INDEX;
        byte index_ = IndexConstants.SECOND_INDEX;
        String lg_ = getOwner().getLanguageKey();
        for (GraphicPresidentCard c: getGraphicCards(getWindow(),lg_,_hand)) {
            int curStr_ = c.getCard().strength(_reversed);
            if (iter_ > IndexConstants.FIRST_INDEX) {
                if (curStr_ == str_) {
                    index_++;
                } else {
                    index_ = IndexConstants.SECOND_INDEX;
                }
            }
            c.addMouseListener(new ListenerCardPresidentMultiGame(this,c.getCard(), index_));
            str_ = curStr_;
            iter_++;
            _panel.add(c);
        }
        _panel.validate();
        _panel.repaintChildren(getWindow().getImageFactory());
    }

    private void updateCardsInPanelPresidentDiscard(AbsPanel _panel, HandPresident _hand, boolean _inHand) {
        _panel.removeAll();
        byte index_ = IndexConstants.FIRST_INDEX;
        String lg_ = getOwner().getLanguageKey();
        for (GraphicPresidentCard c: getGraphicCards(getWindow(),lg_,_hand)) {
            c.addMouseListener(new ListenerCardPresidentDiscard(this,c.getCard(),index_,_inHand,c));
            _panel.add(c);
            index_++;
        }
        if (!_inHand) {
            int rec_ = getReceivedCards().total();
            while (index_ < rec_) {
                AbsPlainLabel l_ = getOwner().getCompoFactory().newPlainLabel("");
                if (index_ > IndexConstants.FIRST_INDEX) {
                    l_.setPreferredSize(GraphicPresidentCard.getDimension(true));
                } else {
                    l_.setPreferredSize(GraphicPresidentCard.getDimension(false));
                }
                l_.setBackground(_panel.getBackground());
                l_.setForeground(_panel.getForeground());
                _panel.add(l_);
                index_++;
            }
        }
        _panel.validate();
        _panel.repaintChildren(getWindow().getImageFactory());
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
        /*Descativer aide au jeu*/
        getHelpGame().setEnabledMenu(false);
        getOwner().getTricksHands().setEnabledMenu(false);
        getOwner().getTeams().setEnabledMenu(false);
        AbsPanel container_=getOwner().getCompoFactory().newBorder();

        /*Le nombre de parties jouees depuis le lancement du logiciel*/
        setThreadAnime(false);

        AbsTabbedPane onglets_=getOwner().getCompoFactory().newAbsTabbedPane();
        String lg_ = getOwner().getLanguageKey();
        setScores(_res.getScores());

        AbsScrollPane scroll_=getOwner().getCompoFactory().newAbsScrollPane();
        RenderedPage editor_ = new RenderedPage(scroll_, getOwner().getFrames());
        PreparedAnalyzed stds_ = retrieve(FileConst.RESOURCES_HTML_FILES_RESULTS_PRESIDENT);
        ((PresidentStandards)stds_.getBeanNatLgNames()).setDataBase(_res);
        editor_.initialize(stds_);
        scroll_.setPreferredSize(new Dimension(300,300));
        onglets_.add(getMessages().getVal(WindowCards.RESULTS_PAGE),scroll_);
        container_.add(onglets_,BorderLayout.CENTER);
        AbsPanel panneau_=getOwner().getCompoFactory().newPageBox();
        readyToPlay = false;
        ready = getOwner().getCompoFactory().newCustCheckBox(getMessages().getVal(WindowCards.READY));
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
            AbsPlainButton button_ = getOwner().getCompoFactory().newPlainButton(getMessages().getVal(WindowCards.PLAY_PRESIDENT));
            button_.addActionListener(new PlayNextDealEvent(this));
            panneau_.add(button_);
        }
        panneau_.add(getWindow().getClock());
        panneau_.add(getWindow().getLastSavedGameDate());
        container_.add(panneau_,BorderLayout.SOUTH);

        setContentPane(container_);
        pack();
        //PackingWindowAfter.pack(this, true);
        PlayerActionGame ok_ = new PlayerActionGame(PlayerActionGameType.OK);
        ok_.setPlace(indexInGame);
        ok_.setLocale(lg_);
        getOwner().sendObject(ok_);
    }

    @Override
    public void dealNext() {
        boolean allReady_ = Net.allReady(getOwner().getNet());
        if (!allReady_) {
            return;
        }
        boolean distinct_ = Net.distinctPlaces(getOwner().getNet());
        if (!distinct_) {
            return;
        }
        long nb_=chargerNombreDeParties(GameEnum.PRESIDENT, getOwner().getFrames());
        GamePresident game_=Net.getGames(getOwner().getNet()).partiePresident();
        Bytes rk_ = game_.getNewRanks();
        DealPresident deal_=new DealPresident(nb_,game_.empiler());
        deal_.donneurSuivant(game_.getDistribution().getDonneur(),game_.getRegles());
        deal_.initDonne(game_.getRegles(),getOwner().getGenerator());
        Net.getGames(getOwner().getNet()).jouerPresident(new GamePresident(GameType.RANDOM,deal_,game_.getRegles(), rk_));
        getOwner().sendObject(PlayGame.INSTANCE);
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
        if (!Net.isProgressingGame(getOwner().getNet())) {
            AbsPanel container_ = getPane();
            AbsPlainButton buttonRules_ = getOwner().getCompoFactory().newPlainButton(getMessages().getVal(WindowCards.SELECT_RULES));
            buttonRules_.addActionListener(new ChangeRulesEvent(this));
            container_.add(buttonRules_);
            AbsPlainButton button_ = getOwner().getCompoFactory().newPlainButton(getMessages().getVal(WindowCards.PLAY_PRESIDENT));
            button_.addActionListener(new PlayFirstDealEvent(this));
            container_.add(button_);
            pack();
            //PackingWindowAfter.pack(this, true);
        }
    }

    @Override
    public void changeRules() {
        String lg_ = getOwner().getLanguageKey();
        DialogRulesPresident.initDialogRulesPresident(
                GameEnum.PRESIDENT.toString(lg_), getOwner(), rulesPresidentMulti);
        DialogRulesPresident.setPresidentDialog(false,nbChoosenPlayers, getOwner());
        RulesPresident rulesPresidentMulti_ = DialogRulesPresident.getRegles(getOwner().getDialogRulesPresident());
        if (!DialogRulesPresident.isValidated(getOwner().getDialogRulesPresident())) {
            return;
        }
        rulesPresidentMulti = rulesPresidentMulti_;
        getOwner().sendObject(rulesPresidentMulti);
    }

    @Override
    public void dealFirst() {
        boolean allReady_ = Net.allReady(getOwner().getNet());
        if (!allReady_) {
            return;
        }
        boolean distinct_ = Net.distinctPlaces(getOwner().getNet());
        if (!distinct_) {
            return;
        }
        HandPresident pile_;
        /*
        Chargement de la pile de cartes depuis un fichier sinon
        on la cree
        */
        int nbStack_ = rulesPresidentMulti.getNbStacks();
        pile_ = chargerPilePresident(nbStack_, getOwner().getFrames());
        if (!pile_.validStack(nbStack_)) {
            pile_ = HandPresident.stack(nbStack_);
        }
        DealPresident deal_ = new DealPresident(0, pile_);
        deal_.setRandomDealer(rulesPresidentMulti,getOwner().getGenerator());
        deal_.initDonne(rulesPresidentMulti,getOwner().getGenerator());
        Net.getGames(getOwner().getNet()).jouerPresident(new GamePresident(
                GameType.RANDOM, deal_, rulesPresidentMulti, new Bytes()));
        getOwner().sendObject(PlayGame.INSTANCE);
    }
}
