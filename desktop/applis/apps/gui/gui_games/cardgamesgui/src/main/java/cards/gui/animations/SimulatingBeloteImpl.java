package cards.gui.animations;

import cards.belote.*;
import cards.belote.beans.BeloteStandards;
import cards.belote.enumerations.BonusBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.belote.enumerations.DeclaresBeloteRebelote;
import cards.facade.Games;
import cards.gameresults.sml.DocumentReaderCardsResultsUtil;
import cards.gui.WindowCards;
import cards.gui.containers.ContainerBelote;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSimuBelote;
import cards.gui.dialogs.FileConst;
import cards.gui.labels.GraphicBeloteCard;
import cards.gui.panels.CarpetBelote;
import cards.gui.panels.MiniCarpet;
import code.gui.*;
import code.gui.Panel;
import code.gui.ScrollPane;
import code.gui.document.PreparedAnalyzed;
import code.gui.document.RenderedPage;
import code.threads.ThreadUtil;
import code.util.ByteMap;
import code.util.StringList;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

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
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_));
    }

    @Override
    public DisplayingBelote getDisplaying() {
        return displayingBelote;
    }

    @Override
    public void actingBid(byte _player) {
        StringList pseudos_=pseudosSimuleeBelote();
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(container.getMessages().getVal(WindowCards.DECLARE_BID), pseudos_.get(_player)),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_));
    }

    @Override
    public void actedBid(byte _player, BidBeloteSuit _bid) {
        String lg_ = container.getOwner().getLanguageKey();
        StringList pseudos_=pseudosSimuleeBelote();
        String mess_ = container.getMessages().getVal(WindowCards.DEMO_ACTION);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_player), Games.toString(_bid,lg_)),ContainerGame.RETURN_LINE);
        event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_));
    }

    @Override
    public void nextRound(int _nbBids, byte _nbPlayers) {
        if (_nbBids % _nbPlayers != 0) {
            return;
        }
        String event_ = StringUtil.concat(Long.toString(_nbBids),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_));
    }

    @Override
    public void secRound(byte _nbPlayers) {
        String event_ = StringUtil.concat(Long.toString(_nbPlayers),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_));
    }

    @Override
    public void noBid() {
        String event_ = StringUtil.concat(container.getMessages().getVal(WindowCards.NO_BID),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_));
        container.revalidate();
    }

    @Override
    public void pause() {
        container.pause();
    }

    @Override
    public void prepare() {
        FrameUtil.invokeLater(new PrepareSimuBelote(this));
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
        Panel contentPane_ = Panel.newPageBox();
        Panel container_=Panel.newBorder();
        container_.add(new TextLabel(container.getMessages().getVal(WindowCards.HELP_GO_MENU),SwingConstants.CENTER),BorderLayout.NORTH);
        StringList pseudos_ = pseudosSimuleeBelote();
        CarpetBelote tapis_ = CarpetBelote.initTapisBelote(lg_, partie_.getNombreDeJoueurs(), container.getDisplayingBelote().isClockwise(), pseudos_, 1, container.getWindow().getCompoFactory());
        container.getTapis().setTapisBelote(tapis_);
        container_.add(tapis_.getContainer(),BorderLayout.CENTER);
        Panel panneau_= Panel.newLineBox();
        panneau_.setBackground(Color.BLUE);
        container.setPanelHand(panneau_);
        container_.add(panneau_,BorderLayout.SOUTH);
        Panel panneau2_=Panel.newPageBox();
        container.setEvents(new TextArea(ContainerBelote.EMPTY,8, 30));
        container.getEvents().setEditable(false);
        panneau2_.add(new ScrollPane(container.getEvents()));
        container.setMini(MiniCarpet.newCarpet(container.getWindow().getImageFactory(),partie_.getNombreDeJoueurs(),container.getDisplayingBelote().isClockwise(),pseudos_, container.getWindow().getCompoFactory()));
        panneau2_.add(container.getMiniPanel());
        container.setHandfuls(new ByteMap<TextLabel>());
        container.setDeclaredHandfuls(new ByteMap<Panel>());
        Panel declaredHandfuls_ = Panel.newGrid(0,1);
        int nbPlayers_ = partie_.getNombreDeJoueurs();
        for (byte i = IndexConstants.FIRST_INDEX; i<nbPlayers_; i++) {
            Panel declaredHandfulGroup_ = Panel.newLineBox();
            TextLabel lab_ = new TextLabel(pseudos_.get(i));
            declaredHandfulGroup_.add(lab_);
            TextLabel handful_ = new TextLabel(ContainerGame.EMPTY_STRING);
            declaredHandfulGroup_.add(handful_);
            container.getHandfuls().put(i, handful_);
            Panel declaredHandful_ = Panel.newLineBox();
            declaredHandfulGroup_.add(declaredHandful_);
            container.getDeclaredHandfuls().put(i, declaredHandful_);
            declaredHandfuls_.add(declaredHandfulGroup_);
        }
        ScrollPane scroll_ = new ScrollPane(declaredHandfuls_);
        panneau2_.add(scroll_);
        Panel sousPanneau_=Panel.newGrid(0,1);
        container.setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(sousPanneau_);
        container_.add(panneau2_,BorderLayout.EAST);
        if (!partie_.getDistribution().derniereMain().estVide()) {
            container.tapisBelote().setTalonBelote(container.getWindow(),lg_,partie_.getDistribution().derniereMain());
        }
        contentPane_.add(container_);
        contentPane_.add(container.getWindow().getClock().getComponent());
        contentPane_.add(container.getWindow().getLastSavedGameDate());
        container.setContentPane(contentPane_);
        panneau_=container.getPanneauBoutonsJeu();
        panneau_.add(stopButton);
        Panel panneau1_=container.getPanelHand();
        panneau1_.removeAll();
        /*On place les cartes de l'utilisateur*/
        for (GraphicBeloteCard c: ContainerBelote.getGraphicCards(container.getWindow(), lg_,partie_.getDeal().hand())) {
            panneau1_.add(c);
        }
        panneau1_.repaintChildren(container.getOwner().getImageFactory());
        panneau1_.validate();
    }
    @Override
    public void beginDemo() {
        String event_;
        event_ = StringUtil.concat(container.getMessages().getVal(WindowCards.BEGIN_DEMO),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_));
    }

    @Override
    public void sleepSimu(long _millis) {
        ThreadUtil.sleep(container.getOwner().getThreadFactory(),_millis);
    }

    @Override
    public boolean stopped() {
        return container.isArretDemo();
    }

    @Override
    public void stopDemo() {
        FrameUtil.invokeLater(new StopDemo(container));
    }

    @Override
    public void endDeal() {
        FrameUtil.invokeLater(new EndDealSimuBelote(this));
    }

    void endGuiDeal() {
        String lg_ = container.getOwner().getLanguageKey();
        Panel panneau_=Panel.newPageBox();
        ResultsBelote res_ = new ResultsBelote();
        GameBelote currentGame_=partieBeloteSimulee();
        res_.setGame(currentGame_);
        StringList nicknames_=pseudosSimuleeBelote();
        res_.setUser(DealBelote.NUMERO_UTILISATEUR);
        res_.initialize(new StringList(nicknames_), container.getScores());
        DocumentReaderCardsResultsUtil.setMessages(res_,lg_);
        ScrollPane scroll_=new ScrollPane();
        RenderedPage editor_ = new RenderedPage(scroll_, container.getWindow().getFrames());
        res_.getRes().setGeneral(container.readCoreResource());
        res_.getRes().setSpecific(container.readResource());
        PreparedAnalyzed stds_ = container.retrieve(FileConst.RESOURCES_HTML_FILES_RESULTS_BELOTE);
        ((BeloteStandards)stds_.getBeanNatLgNames()).setDataBase(res_);
        editor_.initialize(stds_);
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
        String mess_ = container.getMessages().getVal(WindowCards.PLAY_CARD_FIRST);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_joueur)),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_));
    }

    @Override
    public void nextCardPlaying(byte _joueur) {
        StringList pseudos_=pseudosSimuleeBelote();
        String mess_ = container.getMessages().getVal(WindowCards.PLAY_CARD_THEN);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_joueur)),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_));
    }

    @Override
    public void belReb(HandBelote _hand, CardBelote _playedCard,byte _joueur) {
        if(_hand.contient(_playedCard)) {
            String lg_ = container.getOwner().getLanguageKey();
            StringList pseudos_=pseudosSimuleeBelote();
            String mess_ = container.getMessages().getVal(WindowCards.DEMO_ACTION);
            String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_joueur),Games.toString(DeclaresBeloteRebelote.BELOTE_REBELOTE,lg_)),ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_));
        }
    }

    @Override
    public void declare(byte _joueur, DeclareHandBelote _annonces) {
        if (_annonces.getDeclare() != DeclaresBelote.UNDEFINED) {
            String lg_ = container.getOwner().getLanguageKey();
            StringList pseudos_=pseudosSimuleeBelote();
            String mess_ = container.getMessages().getVal(WindowCards.DEMO_ACTION_TWO);
            String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_joueur),
                    Games.toString(_annonces.getDeclare(),lg_), Games.toString(_annonces.getHand(),lg_)),
                    ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_));
        }
    }

    @Override
    public void played(byte _joueur, CardBelote _playedCard) {
        String lg_ = container.getOwner().getLanguageKey();
        container.tapisBelote().setCarteBelote(container.getWindow().getImageFactory(), lg_,_joueur,_playedCard);
    }

    @Override
    public void displayUserHand(HandBelote _main) {
        afficherMainUtilisateurSimuBelote(_main);
    }

    @Override
    public void displayTrickWinner(byte _trickWinner) {
        StringList pseudos_=pseudosSimuleeBelote();
        String mess_ = container.getMessages().getVal(WindowCards.TRICK_WINNER);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_trickWinner)),ContainerGame.RETURN_LINE);
        event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_));
    }

    @Override
    public void displayLastTrick(byte _trickWinner) {
        String lg_ = container.getOwner().getLanguageKey();
        StringList pseudos_=pseudosSimuleeBelote();
        String mess_ = container.getMessages().getVal(WindowCards.BONUS_WIN);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_trickWinner), Games.toString(BonusBelote.LAST_TRICK,lg_)),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_));
    }

    @Override
    public void clearCarpet(byte _nbPlayers) {
        String lg_ = container.getOwner().getLanguageKey();
        container.tapisBelote().setCartesBeloteJeu(container.getOwner().getImageFactory(), _nbPlayers, lg_);
    }

    @Override
    public void beginPlay() {
        String event_ = StringUtil.concat(container.getMessages().getVal(WindowCards.BEGIN_PLAY_CARDS),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_));
    }

    @Override
    public void dealCards(byte _donneur) {
        StringList pseudos_=pseudosSimuleeBelote();
        String event_ = container.getMessages().getVal(WindowCards.TAKE_TOP_CARD);
        String mess_ = container.getMessages().getVal(WindowCards.DEAL_REMAIN_CARDS);
        event_ = StringUtil.concat(event_, StringUtil.simpleStringsFormat(mess_, pseudos_.get(_donneur)),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_));
    }

    @Override
    public void dealCard(int _step, int _gotCards, byte _p) {
        StringList pseudos_=pseudosSimuleeBelote();
        String mess_ = container.getMessages().getVal(WindowCards.DEAL_SET_CARDS);
        String event_ = StringUtil.concat(ContainerBelote.TAB, StringUtil.simpleStringsFormat(mess_, Long.toString(_step), Long.toString(_gotCards), pseudos_.get(_p)));
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_));
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
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new SimulationRefreshHandBelote(container, _mainUtilisateur));
    }
}
