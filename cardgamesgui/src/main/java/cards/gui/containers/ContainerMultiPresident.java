package cards.gui.containers;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import cards.consts.GameType;
import cards.facade.enumerations.GameEnum;
import cards.gameresults.ResultsGame;
import cards.gui.MainWindow;
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
import cards.network.common.Dealt;
import cards.network.common.Ok;
import cards.network.common.PlayGame;
import cards.network.common.before.ChoosenPlace;
import cards.network.common.before.PlayersNamePresent;
import cards.network.common.before.Ready;
import cards.network.common.displaying.DonePause;
import cards.network.common.displaying.DonePlaying;
import cards.network.common.select.SelectTricksHands;
import cards.network.president.actions.DiscardedCards;
import cards.network.president.actions.PlayingCardPresident;
import cards.network.president.displaying.DealtHandPresident;
import cards.network.president.displaying.ReceivedGivenCards;
import cards.network.president.displaying.RefreshedHandPresident;
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
import code.gui.ConfirmDialog;
import code.gui.LabelButton;
import code.gui.NumComboBox;
import code.gui.Panel;
import code.gui.ScrollPane;
import code.gui.TabbedPane;
import code.gui.document.RenderedPage;
import code.util.CustList;
import code.util.NatTreeMap;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.comparators.ComparatorBoolean;

public class ContainerMultiPresident extends ContainerPresident implements
        ContainerMulti {

    private int noClient;
    private byte indexInGame = CustList.INDEX_NOT_FOUND_ELT;
    private RulesPresident rulesPresidentMulti = new RulesPresident();
    private NumComboBox choiceOfPlaceForPlayingGame;
    private JCheckBox ready;

//    private DealingPresident repPresident;
    private int nbChoosenPlayers = CustList.INDEX_NOT_FOUND_ELT;
    private boolean hasCreatedServer;
    private boolean readyToPlay;
    private CustList<JLabel> playersPseudos = new CustList<JLabel>();
    private CustList<JLabel> playersPlaces = new CustList<JLabel>();
    private CustList<JCheckBox> playersReady = new CustList<JCheckBox>();
    private RenderedPage editor;
    private NatTreeMap<Integer, Byte> playersPlacesForGame = new NatTreeMap<Integer, Byte>();
    private NumberMap<Integer,String> playersPseudosForGame = new NumberMap<Integer,String>();
    private HandPresident playerHandPresident = new HandPresident();
    private JLabel canPlayLabel = new JLabel();
    private int nbCardsDiscard;

    public ContainerMultiPresident(MainWindow _window, boolean _hasCreatedServer, int _nbPlayers) {
        super(_window);
        hasCreatedServer = _hasCreatedServer;
        if (hasCreatedServer) {
            Net.getGames().setRulesBelote(null);
            rulesPresidentMulti = new RulesPresident(_nbPlayers);
            Net.getGames().setRulesPresident(rulesPresidentMulti);
            Net.getGames().setRulesTarot(null);
        }
    }

    @Override
    public void updateFirst(PlayersNamePresent _players) {
        String lg_ = getOwner().getLanguageKey();
        getMultiStop().setEnabledMenu(true);
        getTricksHands().setEnabledMenu(true);
        getTeams().setEnabledMenu(true);
        getLoad().setEnabledMenu(false);
        rulesPresidentMulti = _players.getRulesPresident();
        nbChoosenPlayers = _players.getNbPlayers();
        Panel container_ = new Panel();
        container_.setLayout(new BoxLayout(container_.getComponent(), BoxLayout.PAGE_AXIS));
        Panel panel_ = new Panel();
        panel_.setLayout(new GridLayout(0, 2));
        panel_.add(new JLabel(getMessages().getVal(MainWindow.PLACE)));
        choiceOfPlaceForPlayingGame = new NumComboBox();
        for (int i = CustList.FIRST_INDEX; i < nbChoosenPlayers; i++) {
            choiceOfPlaceForPlayingGame.addItem(i);
        }
        choiceOfPlaceForPlayingGame.setSelectedItem(_players.getPseudos()
                .size() - 1);
        indexInGame = choiceOfPlaceForPlayingGame.getCurrent().byteValue();
        choiceOfPlaceForPlayingGame.setListener(new ChangePlaceEvent(this));
        panel_.add(choiceOfPlaceForPlayingGame);
        ready = new JCheckBox(getMessages().getVal(MainWindow.READY));
        ready.addActionListener(new ReadyEvent(this));
        panel_.add(ready);
        container_.add(panel_);
        panel_ = new Panel();
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

        JScrollPane scroll_ = new JScrollPane();
        editor = new RenderedPage(scroll_);
        editor.setLanguage(lg_);
        editor.setDataBase(rulesPresidentMulti);
        editor.initialize(FileConst.RESOURCES_HTML_FILES_RULES_PRESIDENT, new PresidentStandards());

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
            LabelButton button_ = new LabelButton(getMessages().getVal(MainWindow.PLAY_PRESIDENT));
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
        choice_.setPlacesPlayers(new NatTreeMap<Integer, Byte>());
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

    public void updateRules(RulesPresident _rules) {
        rulesPresidentMulti = _rules;
        String lg_ = getOwner().getLanguageKey();
        editor.setLanguage(lg_);
        editor.setDataBase(rulesPresidentMulti);
        editor.initializeHtml(FileConst.RESOURCES_HTML_FILES_RULES_PRESIDENT, new PresidentStandards());
    }

    public void updateForBeginningGame(DealtHandPresident _hand) {
        placerIhmPresidentMulti(_hand.getStatus(), _hand.getMaxCards());

        playerHandPresident = _hand.getCards();
        playerHandPresident.sortCards(getDisplayingPresident().getDecroissant(), false);
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
        Dealt dealt_ = new Dealt();
        dealt_.setPlace(indexInGame);
        String lg_ = getOwner().getLanguageKey();
        dealt_.setLocale(lg_);
        getOwner().sendObject(dealt_);
    }

    public void canDiscardPresident(AllowDiscarding _allow) {
        setCanDiscard(true);
        nbCardsDiscard = _allow.getReceivedCards().total();
        setGivingCardsOk(new LabelButton(MainWindow.OK));
        getGivingCardsOk().setEnabledLabel(false);
        getGivingCardsOk().addMouseListener(new GiveCardsEvent(this));
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
        getGivingCardsOk().setEnabledLabel(nbCardsDiscard == getGivenCards().total());
        pack();
    }

    @Override
    public void cancelDiscard(byte _index) {
        super.cancelDiscard(_index);
        updateCardsInPanelPresidentDiscard(getPanelHand(), getVirtualHand(), true);
        updateCardsInPanelPresidentDiscard(getPanelGivenCards(), getGivenCards(), false);
        getGivingCardsOk().setEnabledLabel(false);
        pack();
    }

    @Override
    public void discard() {
        //The deal is now ready
        setCanDiscard(false);
        getGivingCardsOk().setVisibleButton(false);
        setCanPlay(false);
        playerHandPresident.supprimerCartes(getGivenCards());
        playerHandPresident.sortCards(getDisplayingPresident().getDecroissant(), false);
        updateCardsInPanelPresidentMulti(getPanelHand(), playerHandPresident, false);
        getNoPlay().setVisibleButton(true);
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
        playerHandPresident.sortCards(getDisplayingPresident().getDecroissant(), false);
        updateCardsInPanelPresidentMulti(getPanelHand(), playerHandPresident, false);
        getReceivedCards().supprimerCartes();
        getReceivedCards().ajouterCartes(_readObject.getReceived());
        updateCardsInPanelPresidentReceived();
        getGivenCards().supprimerCartes();
        getGivenCards().ajouterCartes(_readObject.getGiven());
        updateCardsInPanelPresidentGiven();
        getNoPlay().setVisibleButton(true);
        pack();
        String lg_ = getOwner().getLanguageKey();
        RefreshedHandPresident r_ = new RefreshedHandPresident();
        r_.setPlace(indexInGame);
        r_.setLocale(lg_);
        getOwner().sendObject(r_);
    }

    public void canPlayPresident(AllowPlayingPresident _readObject) {
        setRaisonCourante(EMPTY);
        setCanPlay(true);
        canPlayLabel.setText(getMessages().getVal(MainWindow.CAN_PLAY));
        updateCardsInPanelPresidentMulti(getPanelHand(), playerHandPresident, _readObject.isReversed());
        getPanneauBoutonsJeu().removeAll();
        getPanneauBoutonsJeu().add(assemble());
        if (_readObject.getStatus() == Playing.HAS_TO_EQUAL) {
            getNoPlay().setTextAndSize(getMessages().getVal(MainWindow.NO_PLAY_NOW));
        } else {
            getNoPlay().setTextAndSize(getMessages().getVal(MainWindow.PASS_TRICK));
        }
        getNoPlay().setEnabledLabel(_readObject.isEnabledPass());
        getNoPlay().setVisibleButton(true);
//        getPanneauBoutonsJeu().add(getNoPlay());
        getPanneauBoutonsJeu().validate();
        getPanneauBoutonsJeu().repaint();
        getOwner().getTricksHands().setEnabledMenu(true);
        getOwner().getTeams().setEnabledMenu(true);
        pack();
    }

    public void errorPlayingCard(ErrorPlayingPresident _readObject) {
        String lg_ = getOwner().getLanguageKey();
        setCanPlay(true);
        if (_readObject.isPassIssue()) {
            String title_ = getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE);
            ConfirmDialog.showMessage(getOwner(), _readObject.getReason(), title_, lg_, JOptionPane.ERROR_MESSAGE);
        } else {
            String mes_ = StringList.simpleStringsFormat(getMessages().getVal(MainWindow.CANT_PLAY_CARD), _readObject.getCard().toString(lg_));
            String finalMessage_ = StringList.concat(mes_,RETURN_LINE,_readObject.getReason());
            String title_ = getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE);
            ConfirmDialog.showMessage(getOwner(), finalMessage_, title_, lg_, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void displayPlayedCard(PlayingCardPresident _card) {
        String lg_ = getOwner().getLanguageKey();
        canPlayLabel.setText(EMPTY_STRING);
        byte relative_ = relative(_card.getNextPlayer());
        NumberMap<Byte,Playing> status_ = new NumberMap<Byte,Playing>();
        for (byte p: _card.getStatus().getKeys()) {
            status_.put(relative(p), _card.getStatus().getVal(p));
        }
        tapisPresident().setTalonPresident(lg_,_card.getPlayedHand());
//        tapisPresident().repaintValidate();
        tapisPresident().setStatus(lg_,status_, relative_);
//        tapisPresident().repaintValidate();

        String pseudo_ = getPseudoByPlace(_card.getPlace());
        ajouterTexteDansZone(StringList.concat(pseudo_, INTRODUCTION_PTS, _card.getPlayedHand().toString(lg_), RETURN_LINE));
        //PackingWindowAfter.pack(this, true);
        pack();
        DonePlaying dealt_ = new DonePlaying();
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
        pl_.setStatus(new NumberMap<Byte, Playing>());
        getOwner().sendObject(pl_);
    }

    public void refreshHand(RefreshHandPlayingPresident _card) {
//        if (_card.getPlace() == indexInGame) {
//            playerHandPresident.supprimerCartes(_card.getPlayedHand());
//        }
        playerHandPresident.supprimerCartes(_card.getPlayedHand());
        playerHandPresident.sortCards(getDisplayingPresident().getDecroissant(), _card.isReversed());
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
        tapisPresident().setTalonPresident();
//        tapisPresident().repaintValidate();
        //pack();
        String lg_ = getOwner().getLanguageKey();
        DonePause d_ = new DonePause();
        d_.setPlace(indexInGame);
        d_.setLocale(lg_);
        getOwner().sendObject(d_);
    }

    @Override
    public void showTricksHands() {
        if (!isCanPlay()) {
            return;
        }
        SelectTricksHands select_ = new SelectTricksHands();
        select_.setPlace(indexInGame);
        String lg_ = getOwner().getLanguageKey();
        select_.setLocale(lg_);
        getOwner().sendObject(select_);
    }

    public void showTricksHands(TricksHandsPresident _tricks) {
        NatTreeMap<Byte, String> pseudos_ = new NatTreeMap<Byte, String>();
        byte p_ = 0;
        for (String s : pseudosPresident((byte) nbChoosenPlayers)) {
            pseudos_.put(p_, s);
            p_++;
        }
        for (byte p : playersPlacesForGame.values()) {
            pseudos_.put(p, getPseudoByPlace(p));
        }
        StringList list_ = new StringList(pseudos_.values());
        MainWindow ow_ = getOwner();
        DialogTricksPresident.setDialogTricksPresident(
                getMessages().getVal(MainWindow.HANDS_TRICKS_PRESIDENT), ow_);
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

    private void placerIhmPresidentMulti(NumberMap<Byte,Playing> _status, int _nbMax) {
        Panel container_ = new Panel();
        container_.setLayout(new BorderLayout());
        container_.add(new JLabel(getMessages().getVal(MainWindow.HELP_GO_MENU),
                SwingConstants.CENTER), BorderLayout.NORTH);
        String lg_ = getOwner().getLanguageKey();
        CarpetPresident tapis_ = new CarpetPresident();
        NatTreeMap<Byte, String> pseudos_ = new NatTreeMap<Byte, String>();
        byte p_ = 0;
        for (String s : pseudosPresident((byte) nbChoosenPlayers)) {
            pseudos_.put(p_, s);
            p_++;
        }
        for (byte p : playersPlacesForGame.values()) {
            byte relative_ = relative(p);
            pseudos_.put(relative_, getPseudoByPlace(p));
        }
        NumberMap<Byte,Playing> status_ = new NumberMap<Byte,Playing>();
        for (byte p: _status.getKeys()) {
            status_.put(relative(p), _status.getVal(p));
        }
        StringList list_ = new StringList(pseudos_.values());
        tapis_.initTapisPresident(lg_,list_, status_, _nbMax);
        getTapis().setTapisPresident(tapis_);
        container_.add(tapis_, BorderLayout.CENTER);
        Panel panneau_ = new Panel();
        panneau_.setBackground(Color.BLUE);
        panneau_.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setPanelHand(panneau_);
        container_.add(panneau_, BorderLayout.SOUTH);
        Panel panneau2_=new Panel();
        panneau2_.setLayout(new BoxLayout(panneau2_.getComponent(), BoxLayout.PAGE_AXIS));
        setEvents(new JTextArea(EMPTY,8, 30));
        getEvents().setEditable(false);
        panneau2_.add(new ScrollPane(getEvents()));
        setHandfuls(new NumberMap<Byte,JLabel>());
        setDeclaredHandfuls(new NumberMap<Byte,Panel>());
//        JPanel declaredHandfuls_ = new JPanel(new GridLayout(0,1));
//        int nbPlayers_ = partie_.getNombreDeJoueurs();
        Panel sousPanneau_=new Panel();
        sousPanneau_.setLayout(new BoxLayout(sousPanneau_.getComponent(), BoxLayout.PAGE_AXIS));
        Panel panelCards_ = new Panel();
        Panel panelDiscard_ = new Panel();
        panelDiscard_.setBorder(BorderFactory.createTitledBorder(getMessages().getVal(MainWindow.GIVEN_CARDS)));
        panelDiscard_.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        panelCards_.add(panelDiscard_);
        setPanelGivenCards(panelDiscard_);
        Panel panelRec_ = new Panel();
        panelRec_.setBorder(BorderFactory.createTitledBorder(getMessages().getVal(MainWindow.RECEIVED_CARDS)));
        panelRec_.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        panelCards_.add(panelRec_);
        setPanelReceivedCards(panelRec_);
        sousPanneau_.add(panelCards_);
        setNoPlay(new LabelButton(EMPTY));
        getNoPlay().addMouseListener(new NoPlayPresidentEvent(this));
        setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(new ScrollPane(sousPanneau_));
        getNoPlay().setVisibleButton(false);
        panneau2_.add(getNoPlay());
        setActionsHistory(panneau2_);
        container_.add(panneau2_,BorderLayout.EAST);
        Panel panel_ = new Panel();
        panel_.setLayout(new BoxLayout(panel_.getComponent(), BoxLayout.PAGE_AXIS));
        panel_.add(new ScrollPane(container_));
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

    private void updateCardsInPanelPresidentMulti(Panel _panel, HandPresident _hand, boolean _reversed) {
        _panel.removeAll();
        int str_ = 0;
        int iter_ = CustList.FIRST_INDEX;
        byte index_ = CustList.SECOND_INDEX;
        String lg_ = getOwner().getLanguageKey();
        for (GraphicPresidentCard c: getGraphicCards(lg_,_hand)) {
            int curStr_ = c.getCard().strength(_reversed);
            if (iter_ > CustList.FIRST_INDEX) {
                if (curStr_ == str_) {
                    index_ ++;
                } else {
                    index_ = CustList.SECOND_INDEX;
                }
            }
            c.addMouseListener(new ListenerCardPresidentMultiGame(this,c.getCard(), index_));
            str_ = curStr_;
            iter_ ++;
            _panel.add(c);
        }
        _panel.validate();
        _panel.repaint();
    }

    private void updateCardsInPanelPresidentDiscard(Panel _panel, HandPresident _hand, boolean _inHand) {
        _panel.removeAll();
        byte index_ = CustList.FIRST_INDEX;
        String lg_ = getOwner().getLanguageKey();
        for (GraphicPresidentCard c: getGraphicCards(lg_,_hand)) {
            c.addMouseListener(new ListenerCardPresidentDiscard(this,c.getCard(),index_,_inHand));
            _panel.add(c);
            index_ ++;
        }
        if (!_inHand) {
            int rec_ = getReceivedCards().total();
            while (index_ < rec_) {
                JLabel l_ = new JLabel();
                if (index_ > CustList.FIRST_INDEX) {
                    l_.setPreferredSize(GraphicPresidentCard.getDimension(true));
                } else {
                    l_.setPreferredSize(GraphicPresidentCard.getDimension(false));
                }
                l_.setBackground(_panel.getBackground());
                l_.setForeground(_panel.getForeground());
                _panel.add(l_);
                index_ ++;
            }
        }
        _panel.validate();
        _panel.repaint();
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
        Panel container_=new Panel();
        container_.setLayout(new BorderLayout());

        /*Le nombre de parties jouees depuis le lancement du logiciel*/
        setThreadAnime(false);

        TabbedPane onglets_=new TabbedPane();
        String lg_ = getOwner().getLanguageKey();
        ResultsGame res_ = _res;
        setScores(res_.getScores());

        JScrollPane scroll_=new JScrollPane();
        RenderedPage editor_ = new RenderedPage(scroll_);
        editor_.setLanguage(lg_);
        editor_.setDataBase(res_);
        editor_.initialize(FileConst.RESOURCES_HTML_FILES_RESULTS_PRESIDENT, new PresidentStandards());
        scroll_.setPreferredSize(new Dimension(300,300));
        onglets_.add(getMessages().getVal(MainWindow.RESULTS_PAGE),scroll_);
        container_.add(onglets_,BorderLayout.CENTER);
        Panel panneau_=new Panel();
        panneau_.setLayout(new BoxLayout(panneau_.getComponent(), BoxLayout.PAGE_AXIS));
        readyToPlay = false;
        ready = new JCheckBox(getMessages().getVal(MainWindow.READY));
        ready.addActionListener(new ReadyEvent(this));
        panneau_.add(ready);

        Panel panel_ = new Panel();
        panel_.setLayout(new GridLayout(0,3));

        for (int i = CustList.FIRST_INDEX;i<nbChoosenPlayers;i++) {
            panel_.add(playersPseudos.get(i));
            panel_.add(playersPlaces.get(i));
            panel_.add(playersReady.get(i));
        }
        panneau_.add(panel_);
        if (hasCreatedServer) {
            LabelButton button_ = new LabelButton(getMessages().getVal(MainWindow.PLAY_PRESIDENT));
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
        long nb_=chargerNombreDeParties(GameEnum.PRESIDENT);
        GamePresident game_=Net.getGames().partiePresident();
        Numbers<Byte> rk_ = game_.getNewRanks();
        DealPresident deal_=new DealPresident(nb_,game_.empiler());
        deal_.donneurSuivant(game_.getDistribution().getDonneur(),game_.getRegles());
        deal_.initDonne(game_.getRegles());
        Net.getGames().jouerPresident(new GamePresident(GameType.RANDOM,deal_,game_.getRegles(), rk_));
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
            LabelButton button_ = new LabelButton(getMessages().getVal(MainWindow.PLAY_PRESIDENT));
            button_.addMouseListener(new PlayFirstDealEvent(this));
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
        RulesPresident rulesPresidentMulti_ = DialogRulesPresident.getRegles();
        if (!DialogRulesPresident.isValidated()) {
            return;
        }
        rulesPresidentMulti = rulesPresidentMulti_;
        getOwner().sendObject(rulesPresidentMulti);
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
        HandPresident pile_;
        /*
        Chargement de la pile de cartes depuis un fichier sinon
        on la cree
        */
        int nbStack_ = rulesPresidentMulti.getNbStacks();
        pile_ = chargerPilePresident(nbStack_);
        if (!pile_.validStack(nbStack_)) {
            pile_ = HandPresident.stack(nbStack_);
        }
        DealPresident deal_ = new DealPresident(0, pile_);
        deal_.setRandomDealer(rulesPresidentMulti);
        deal_.initDonne(rulesPresidentMulti);
        Net.getGames().jouerPresident(new GamePresident(
                GameType.RANDOM, deal_, rulesPresidentMulti, new Numbers<Byte>()));
        getOwner().sendObject(new PlayGame());
    }
}
