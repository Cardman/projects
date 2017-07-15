package cards.gui.containers;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
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
import code.util.NatTreeMap;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.StringList;
import code.util.comparators.ComparatorBoolean;
import code.util.consts.Constants;
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
import cards.president.RulesPresident;
import cards.president.TricksHandsPresident;
import cards.president.enumerations.Playing;

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
    private SessionEditorPane editor;
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
        getMultiStop().setEnabled(true);
        getTricksHands().setEnabled(true);
        getTeams().setEnabled(true);
        getLoad().setEnabled(false);
        rulesPresidentMulti = _players.getRulesPresident();
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
        indexInGame = choiceOfPlaceForPlayingGame.getCurrent().byteValue();
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
            editor.setDataBase(rulesPresidentMulti);
            editor.initialize(FileConst.RESOURCES_HTML_FILES_RULES_PRESIDENT);
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
        try {
            //editor.setMainClass(SoftApplication.getMainClass());
//            editor.setTextFilesWithPrefix(FileConst.RESOURCES_HTML_FOLDER + StreamTextFile.SEPARATEUR);
            editor.setLanguage(Constants.getLanguage());
            editor.setDataBase(rulesPresidentMulti);
            editor.initializeHtml(FileConst.RESOURCES_HTML_FILES_RULES_PRESIDENT);
        } catch (RuntimeException _0) {
            _0.printStackTrace();
        }
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
        getGivingCardsOk().setVisible(false);
        setCanPlay(false);
        playerHandPresident.supprimerCartes(getGivenCards());
        playerHandPresident.sortCards(getDisplayingPresident().getDecroissant(), false);
        updateCardsInPanelPresidentMulti(getPanelHand(), playerHandPresident, false);
        getNoPlay().setVisible(true);
        pack();
        DiscardedCards dis_ = new DiscardedCards();
        dis_.setPlace(indexInGame);
        dis_.setDiscarded(getGivenCards());
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
        getNoPlay().setVisible(true);
        pack();
        RefreshedHandPresident r_ = new RefreshedHandPresident();
        r_.setPlace(indexInGame);
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
            getNoPlay().setText(getMessages().getVal(MainWindow.NO_PLAY_NOW));
        } else {
            getNoPlay().setText(getMessages().getVal(MainWindow.PASS_TRICK));
        }
        getNoPlay().setEnabledLabel(_readObject.isEnabledPass());
        getNoPlay().setVisible(true);
//        getPanneauBoutonsJeu().add(getNoPlay());
        getPanneauBoutonsJeu().validate();
        getPanneauBoutonsJeu().repaint();
        getOwner().getTricksHands().setEnabled(true);
        getOwner().getTeams().setEnabled(true);
        pack();
    }

    public void errorPlayingCard(ErrorPlayingPresident _readObject) {
        setCanPlay(true);
        if (_readObject.isPassIssue()) {
            String title_ = getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE);
            ConfirmDialog.showMessage(getOwner(), _readObject.getReason(), title_, Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);
        } else {
            String mes_ = StringList.simpleFormat(getMessages().getVal(MainWindow.CANT_PLAY_CARD), _readObject.getCard());
            String finalMessage_ = mes_+ContainerPresident.RETURN_LINE_CHAR+_readObject.getReason();
            String title_ = getMessages().getVal(MainWindow.CANT_PLAY_CARD_TITLE);
            ConfirmDialog.showMessage(getOwner(), finalMessage_, title_, Constants.getLanguage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public void displayPlayedCard(PlayingCardPresident _card) {
        canPlayLabel.setText(EMPTY_STRING);
        byte relative_ = relative(_card.getNextPlayer());
        NumberMap<Byte,Playing> status_ = new NumberMap<Byte,Playing>();
        for (byte p: _card.getStatus().getKeys()) {
            status_.put(relative(p), _card.getStatus().getVal(p));
        }
        tapisPresident().setTalonPresident(_card.getPlayedHand());
//        tapisPresident().repaintValidate();
        tapisPresident().setStatus(status_, relative_);
//        tapisPresident().repaintValidate();

        String pseudo_ = getPseudoByPlace(_card.getPlace());
        ajouterTexteDansZone(pseudo_ + INTRODUCTION_PTS + _card.getPlayedHand() + RETURN_LINE_CHAR);
        //PackingWindowAfter.pack(this, true);
        pack();
        DonePlaying dealt_ = new DonePlaying();
        dealt_.setPlace(indexInGame);
        getOwner().sendObject(dealt_);
    }

    @Override
    public void noPlay() {
        if (!isCanPlay()) {
            return;
        }
        setCanPlay(false);
        PlayingCardPresident pl_ = new PlayingCardPresident();
        pl_.setLocale(Constants.getLanguage());
        pl_.setPlace(indexInGame);
        pl_.setPass(true);
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
        getOwner().getTricksHands().setEnabled(false);
        getOwner().getTeams().setEnabled(false);
        /* On place les cartes de l'utilisateur */
        updateCardsInPanelPresidentMulti(getPanelHand(), playerHandPresident, _card.isReversed());
        pack();
        //PackingWindowAfter.pack(this, true);
        RefreshingDonePresident ref_ = new RefreshingDonePresident();
        ref_.setPlayedHand(_card.getPlayedHand());
        ref_.setStatus(_card.getStatus());
        ref_.setNextPlayer(_card.getNextPlayer());
        ref_.setPlace(indexInGame);
        getOwner().sendObject(ref_);

    }

    @Override
    public void pauseBetweenTrick() {
        tapisPresident().setTalonPresident();
//        tapisPresident().repaintValidate();
        //pack();
        DonePause d_ = new DonePause();
        d_.setPlace(indexInGame);
        getOwner().sendObject(d_);
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
        DialogTricksPresident.setDialogTricksPresident(
                getMessages().getVal(MainWindow.HANDS_TRICKS_PRESIDENT), getOwner());
        DialogTricksPresident.init(_tricks, (byte) nbChoosenPlayers, list_,
                getDisplayingPresident());
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
        Container container_ = new Container();
        container_.setLayout(new BorderLayout());
        container_.add(new JLabel(getMessages().getVal(MainWindow.HELP_GO_MENU),
                SwingConstants.CENTER), BorderLayout.NORTH);
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
        tapis_.initTapisPresident(list_, status_, _nbMax);
        getTapis().setTapisPresident(tapis_);
        container_.add(tapis_, BorderLayout.CENTER);
        JPanel panneau_ = new JPanel();
        panneau_.setBackground(Color.BLUE);
        panneau_.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setPanelHand(panneau_);
        container_.add(panneau_, BorderLayout.SOUTH);
        JPanel panneau2_=new JPanel();
        panneau2_.setLayout(new BoxLayout(panneau2_, BoxLayout.PAGE_AXIS));
        setEvents(new JTextArea(EMPTY,8, 30));
        getEvents().setEditable(false);
        panneau2_.add(new JScrollPane(getEvents()));
        setHandfuls(new NumberMap<Byte,JLabel>());
        setDeclaredHandfuls(new NumberMap<Byte,JPanel>());
//        JPanel declaredHandfuls_ = new JPanel(new GridLayout(0,1));
//        int nbPlayers_ = partie_.getNombreDeJoueurs();
        JPanel sousPanneau_=new JPanel();
        sousPanneau_.setLayout(new BoxLayout(sousPanneau_, BoxLayout.PAGE_AXIS));
        JPanel panelCards_ = new JPanel();
        JPanel panelDiscard_ = new JPanel();
        panelDiscard_.setBorder(BorderFactory.createTitledBorder(getMessages().getVal(MainWindow.GIVEN_CARDS)));
        panelDiscard_.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        panelCards_.add(panelDiscard_);
        setPanelGivenCards(panelDiscard_);
        JPanel panelRec_ = new JPanel();
        panelRec_.setBorder(BorderFactory.createTitledBorder(getMessages().getVal(MainWindow.RECEIVED_CARDS)));
        panelRec_.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        panelCards_.add(panelRec_);
        setPanelReceivedCards(panelRec_);
        sousPanneau_.add(panelCards_);
        setNoPlay(new LabelButton(EMPTY));
        getNoPlay().addMouseListener(new NoPlayPresidentEvent(this));
        setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(new JScrollPane(sousPanneau_));
        getNoPlay().setVisible(false);
        panneau2_.add(getNoPlay());
        setActionsHistory(panneau2_);
        container_.add(panneau2_,BorderLayout.EAST);
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

    private void updateCardsInPanelPresidentMulti(JPanel _panel, HandPresident _hand, boolean _reversed) {
        _panel.removeAll();
        int str_ = 0;
        int iter_ = CustList.FIRST_INDEX;
        byte index_ = CustList.SECOND_INDEX;
        for (GraphicPresidentCard c: getGraphicCards(_hand)) {
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

    private void updateCardsInPanelPresidentDiscard(JPanel _panel, HandPresident _hand, boolean _inHand) {
        _panel.removeAll();
        byte index_ = CustList.FIRST_INDEX;
        for (GraphicPresidentCard c: getGraphicCards(_hand)) {
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

    @Override
    public void endGame(ResultsGame _res) {
        /*Descativer aide au jeu*/
        getHelpGame().setEnabled(false);
        getOwner().getTricksHands().setEnabled(false);
        getOwner().getTeams().setEnabled(false);
        Container container_=new Container();
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
            editor_.initialize(FileConst.RESOURCES_HTML_FILES_RESULTS_PRESIDENT);
        } catch (RuntimeException _0) {
            _0.printStackTrace();
        }
        editor_.setEditable(false);
        JScrollPane scroll_=new JScrollPane(editor_);
        scroll_.setPreferredSize(new Dimension(300,300));
        onglets_.add(getMessages().getVal(MainWindow.RESULTS_PAGE),scroll_);
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
//        try {
//            nb_=chargerNombreDeParties(GameEnum.PRESIDENT);
//        } catch (Exception exc_) {
//            exc_.printStackTrace();
//            nb_=0;
//        }
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
            Container container_ = getContentPane();
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
        DialogRulesPresident.initDialogRulesPresident(
                GameEnum.PRESIDENT.toString(), getOwner(), rulesPresidentMulti);
        DialogRulesPresident.setPresidentDialog(false,nbChoosenPlayers);
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
        try {
            pile_ = chargerPilePresident(nbStack_);
            if (!pile_.validStack(nbStack_)) {
                pile_ = HandPresident.stack(nbStack_);
            }
        } catch (RuntimeException _0) {
            _0.printStackTrace();
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
