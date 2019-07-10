package cards.gui.animations;

import cards.belote.*;
import cards.belote.beans.BeloteStandards;
import cards.belote.enumerations.BonusBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.belote.enumerations.DeclaresBeloteRebelote;
import cards.facade.Games;
import cards.gameresults.sml.DocumentReaderCardsResultsUtil;
import cards.gui.MainWindow;
import cards.gui.containers.ContainerBelote;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSimuBelote;
import cards.gui.dialogs.FileConst;
import cards.gui.labels.GraphicBeloteCard;
import cards.gui.panels.CarpetBelote;
import cards.gui.panels.MiniCarpet;
import code.gui.LabelButton;
import code.gui.Panel;
import code.gui.ScrollPane;
import code.gui.ThreadInvoker;
import code.gui.ThreadUtil;
import code.gui.document.RenderedPage;
import code.util.ByteMap;
import code.util.CustList;
import code.util.StringList;

import javax.swing.*;
import java.awt.*;

public final class SimulatingBeloteImpl implements SimulatingBelote {
    private final ContainerSimuBelote container;
    private final Games partieSimulee;
    private final DisplayingBelote displayingBelote;
    private final LabelButton stopButton;

    public SimulatingBeloteImpl(ContainerSimuBelote _container, Games _partieSimulee,
                               DisplayingBelote _displayingBelote, LabelButton _stopButton) {
        container = _container;
        partieSimulee = _partieSimulee;
        displayingBelote = _displayingBelote;
        stopButton = _stopButton;
    }
    @Override
    public void displayLineReturn() {
        String event_ = ContainerGame.RETURN_LINE;
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public DisplayingBelote getDisplaying() {
        return displayingBelote;
    }

    @Override
    public void actingBid(byte _player) {
        StringList pseudos_=pseudosSimuleeBelote();
        String event_ = StringList.concat(StringList.simpleStringsFormat(container.getMessages().getVal(MainWindow.DECLARE_BID), pseudos_.get(_player)),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void actedBid(byte _player, BidBeloteSuit _bid) {
        String lg_ = container.getOwner().getLanguageKey();
        StringList pseudos_=pseudosSimuleeBelote();
        String mess_ = container.getMessages().getVal(MainWindow.DEMO_ACTION);
        String event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(_player), Games.toString(_bid,lg_)),ContainerGame.RETURN_LINE);
        event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void nextRound(int _nbBids, byte _nbPlayers) {
        if (_nbBids % _nbPlayers != 0) {
            return;
        }
        String event_ = StringList.concat(Integer.toString(_nbBids),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void secRound(byte _nbPlayers) {
        String event_ = StringList.concat(Integer.toString(_nbPlayers),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void noBid() {
        String event_ = StringList.concat(container.getMessages().getVal(MainWindow.NO_BID),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
        container.revalidate();
    }

    @Override
    public void pause() {
        container.pause();
    }

    @Override
    public void prepare() {
        SwingUtilities.invokeLater(new PrepareSimuBelote(this));
    }
    void prepareGui() {
        container.setArretDemo(false);
        //desactiver le menu Partie/aide au jeu
        container.getHelpGame().setEnabledMenu(false);
        //desactiver le menu Partie/Demo
        container.getDemo().setEnabledMenu(false);
        //Activer le menu Partie/Pause
        container.getPause().setEnabledMenu(true);
        GameBelote partie_=partieBeloteSimulee();
        String lg_ = container.getOwner().getLanguageKey();
        Panel contentPane_ = new Panel();
        contentPane_.setLayout(new BoxLayout(contentPane_.getComponent(), BoxLayout.PAGE_AXIS));
        Panel container_=new Panel();
        container_.setLayout(new BorderLayout());
        container_.add(new JLabel(container.getMessages().getVal(MainWindow.HELP_GO_MENU),SwingConstants.CENTER),BorderLayout.NORTH);
        CarpetBelote tapis_=new CarpetBelote();
        StringList pseudos_ = pseudosSimuleeBelote();
        tapis_.initTapisBelote(lg_,partie_.getNombreDeJoueurs(),container.getDisplayingBelote().getHoraire(),pseudos_,1);
        container.getTapis().setTapisBelote(tapis_);
        container_.add(tapis_,BorderLayout.CENTER);
        Panel panneau_=new Panel();
        panneau_.setBackground(Color.BLUE);
        panneau_.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        container.setPanelHand(panneau_);
        container_.add(panneau_,BorderLayout.SOUTH);
        Panel panneau2_=new Panel();
        panneau2_.setLayout(new BoxLayout(panneau2_.getComponent(), BoxLayout.PAGE_AXIS));
        container.setEvents(new JTextArea(ContainerBelote.EMPTY,8, 30));
        container.getEvents().setEditable(false);
        panneau2_.add(new code.gui.ScrollPane(container.getEvents()));
        container.setMini(new MiniCarpet(partie_.getNombreDeJoueurs(),container.getDisplayingBelote().getHoraire(),pseudos_));
        panneau2_.add(container.getMini());
        container.setHandfuls(new ByteMap<JLabel>());
        container.setDeclaredHandfuls(new ByteMap<Panel>());
        Panel declaredHandfuls_ = new Panel(new GridLayout(0,1));
        int nbPlayers_ = partie_.getNombreDeJoueurs();
        for (byte i=CustList.FIRST_INDEX;i<nbPlayers_;i++) {
            Panel declaredHandfulGroup_ = new Panel(new FlowLayout());
            JLabel lab_ = new JLabel(pseudos_.get(i));
            declaredHandfulGroup_.add(lab_);
            JLabel handful_ = new JLabel(ContainerGame.EMPTY_STRING);
            declaredHandfulGroup_.add(handful_);
            container.getHandfuls().put(i, handful_);
            Panel declaredHandful_ = new Panel(new FlowLayout(FlowLayout.LEFT,0,0));
            declaredHandfulGroup_.add(declaredHandful_);
            container.getDeclaredHandfuls().put(i, declaredHandful_);
            declaredHandfuls_.add(declaredHandfulGroup_);
        }
        code.gui.ScrollPane scroll_ = new ScrollPane(declaredHandfuls_);
        panneau2_.add(scroll_);
        Panel sousPanneau_=new Panel(new GridLayout(0,1));
        container.setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(sousPanneau_);
        container_.add(panneau2_,BorderLayout.EAST);
        if (!partie_.getDistribution().derniereMain().estVide()) {
            container.tapisBelote().setTalonBelote(lg_,partie_.getDistribution().derniereMain());
        }
        contentPane_.add(container_);
        contentPane_.add(container.getWindow().getClock());
        contentPane_.add(container.getWindow().getLastSavedGameDate());
        container.setContentPane(contentPane_);
        panneau_=container.getPanneauBoutonsJeu();
        panneau_.add(stopButton);
        Panel panneau1_=container.getPanelHand();
        panneau1_.removeAll();
        /*On place les cartes de l'utilisateur*/
        for (GraphicBeloteCard c: ContainerBelote.getGraphicCards(lg_,partie_.getDeal().main())) {
            panneau1_.add(c);
        }
        panneau1_.repaint();
        panneau1_.revalidate();
    }
    @Override
    public void beginDemo() {
        String event_;
        event_ = StringList.concat(container.getMessages().getVal(MainWindow.BEGIN_DEMO),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void sleepSimu(long _millis) {
        ThreadUtil.sleep(_millis);
    }

    @Override
    public boolean stopped() {
        return container.isArretDemo();
    }

    @Override
    public void stopDemo() {
        SwingUtilities.invokeLater(new StopDemo(container));
    }

    @Override
    public void endDeal() {
        SwingUtilities.invokeLater(new EndDealSimuBelote(this));
    }

    void endGuiDeal() {
        String lg_ = container.getOwner().getLanguageKey();
        Panel panneau_=new Panel();
        panneau_.setLayout(new BoxLayout(panneau_.getComponent(), BoxLayout.PAGE_AXIS));
        ResultsBelote res_ = new ResultsBelote();
        GameBelote currentGame_=partieBeloteSimulee();
        res_.setGame(currentGame_);
        StringList nicknames_=pseudosSimuleeBelote();
        res_.setUser(DealBelote.NUMERO_UTILISATEUR);
        res_.initialize(new StringList(nicknames_), container.getScores());
        DocumentReaderCardsResultsUtil.setMessages(res_,lg_);
        JScrollPane scroll_=new JScrollPane();
        RenderedPage editor_ = new RenderedPage(scroll_);
        editor_.setLanguage(lg_);
        editor_.setDataBase(res_);
        editor_.initialize(FileConst.RESOURCES_HTML_FILES_RESULTS_BELOTE, new BeloteStandards());
        scroll_.setPreferredSize(new Dimension(300,300));
        panneau_.add(scroll_);
        panneau_.add(stopButton);
        panneau_.add(container.getOwner().getClock());
        panneau_.add(container.getOwner().getLastSavedGameDate());
        container.setContentPane(panneau_);
        container.pack();
    }
    @Override
    public void declareSlam(byte _taker, BidBeloteSuit _bid) {
        //later
    }

    @Override
    public void firstCardPlaying(byte _joueur) {
        StringList pseudos_=pseudosSimuleeBelote();
        String mess_ = container.getMessages().getVal(MainWindow.PLAY_CARD_FIRST);
        String event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(_joueur)),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void nextCardPlaying(byte _joueur) {
        StringList pseudos_=pseudosSimuleeBelote();
        String mess_ = container.getMessages().getVal(MainWindow.PLAY_CARD_THEN);
        String event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(_joueur)),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void belReb(HandBelote _hand, CardBelote _playedCard,byte _joueur) {
        if(_hand.contient(_playedCard)) {
            String lg_ = container.getOwner().getLanguageKey();
            StringList pseudos_=pseudosSimuleeBelote();
            String mess_ = container.getMessages().getVal(MainWindow.DEMO_ACTION);
            String event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(_joueur),Games.toString(DeclaresBeloteRebelote.BELOTE_REBELOTE,lg_)),ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
        }
    }

    @Override
    public void declare(byte _joueur, DeclareHandBelote _annonces) {
        if (_annonces.getAnnonce() != DeclaresBelote.UNDEFINED) {
            String lg_ = container.getOwner().getLanguageKey();
            StringList pseudos_=pseudosSimuleeBelote();
            String mess_ = container.getMessages().getVal(MainWindow.DEMO_ACTION_TWO);
            String event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(_joueur), Games.toString(_annonces.getAnnonce(),lg_), Games.toString(_annonces.getMain(),lg_)),ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
        }
    }

    @Override
    public void played(byte _joueur, CardBelote _playedCard) {
        String lg_ = container.getOwner().getLanguageKey();
        container.tapisBelote().setCarteBelote(lg_,_joueur,_playedCard);
    }

    @Override
    public void displayUserHand(HandBelote _main) {
        afficherMainUtilisateurSimuBelote(_main);
    }

    @Override
    public void displayTrickWinner(byte _trickWinner) {
        StringList pseudos_=pseudosSimuleeBelote();
        String mess_ = container.getMessages().getVal(MainWindow.TRICK_WINNER);
        String event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(_trickWinner)),ContainerGame.RETURN_LINE);
        event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void displayLastTrick(byte _trickWinner) {
        String lg_ = container.getOwner().getLanguageKey();
        StringList pseudos_=pseudosSimuleeBelote();
        String mess_ = container.getMessages().getVal(MainWindow.BONUS_WIN);
        String event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(_trickWinner), Games.toString(BonusBelote.LAST_TRICK,lg_)),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void clearCarpet(byte _nbPlayers) {
        String lg_ = container.getOwner().getLanguageKey();
        container.tapisBelote().setCartesBeloteJeu(_nbPlayers, lg_);
    }

    @Override
    public void beginPlay() {
        String event_ = StringList.concat(container.getMessages().getVal(MainWindow.BEGIN_PLAY_CARDS),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void dealCards(byte _donneur) {
        StringList pseudos_=pseudosSimuleeBelote();
        String event_ = container.getMessages().getVal(MainWindow.TAKE_TOP_CARD);
        String mess_ = container.getMessages().getVal(MainWindow.DEAL_REMAIN_CARDS);
        event_ = StringList.concat(event_,StringList.simpleStringsFormat(mess_, pseudos_.get(_donneur)),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void dealCard(int _step, int _gotCards, byte _p) {
        StringList pseudos_=pseudosSimuleeBelote();
        String mess_ = container.getMessages().getVal(MainWindow.DEAL_SET_CARDS);
        String event_ = StringList.concat(ContainerBelote.TAB,StringList.simpleStringsFormat(mess_, Long.toString(_step), Long.toString(_gotCards), pseudos_.get(_p)));
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    private GameBelote partieBeloteSimulee() {
        return partieSimulee.partieBelote();
    }
    /**Pseudos utilis_&eacute_;s*/
    private StringList pseudosSimuleeBelote() {
        GameBelote partie_=partieBeloteSimulee();
        return container.pseudosBelote(partie_.getNombreDeJoueurs());
    }
    private void afficherMainUtilisateurSimuBelote(HandBelote _mainUtilisateur) {
        ThreadInvoker.invokeNow(new SimulationRefreshHandBelote(container, _mainUtilisateur));
    }
}
