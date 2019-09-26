package cards.gui.animations;

import cards.consts.Status;
import cards.facade.Games;
import cards.gameresults.sml.DocumentReaderCardsResultsUtil;
import cards.gui.MainWindow;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSimuTarot;
import cards.gui.containers.ContainerTarot;
import cards.gui.dialogs.FileConst;
import cards.gui.labels.GraphicTarotCard;
import cards.gui.panels.CarpetTarot;
import cards.gui.panels.MiniCarpet;
import cards.tarot.*;
import cards.tarot.beans.TarotStandards;
import cards.tarot.enumerations.*;
import code.gui.*;
import code.gui.Panel;
import code.gui.ScrollPane;
import code.gui.document.RenderedPage;
import code.stream.ThreadUtil;
import code.util.*;

import javax.swing.*;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;

public final class SimulatingTarotImpl implements SimulatingTarot {
    private final ContainerSimuTarot container;
    private final Games partieSimulee;
    private final DisplayingTarot displayingTarot;
    private final LabelButton stopButton;

    public SimulatingTarotImpl(ContainerSimuTarot _container, Games _partieSimulee,
                               DisplayingTarot _displayingTarot, LabelButton _stopButton) {
        container = _container;
        partieSimulee = _partieSimulee;
        displayingTarot = _displayingTarot;
        stopButton = _stopButton;
    }

    @Override
    public void displayLineReturn() {
        String event_ = ContainerGame.RETURN_LINE;
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public DisplayingTarot getDisplaying() {
        return displayingTarot;
    }

    @Override
    public void actingBid(byte _player) {
        StringList pseudos_=pseudosSimuleeTarot();
        String event_ = StringList.concat(StringList.simpleStringsFormat(container.getMessages().getVal(MainWindow.DECLARE_BID), pseudos_.get(_player)),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void actedBid(byte _player, BidTarot _bid) {
        String lg_ = container.getOwner().getLanguageKey();
        StringList pseudos_=pseudosSimuleeTarot();
        String mess_ = container.getMessages().getVal(MainWindow.DEMO_ACTION);
        String event_ = StringList.concat(StringList.simpleStringsFormat(mess_,pseudos_.get(_player),Games.toString(_bid,lg_)),ContainerGame.RETURN_LINE);
        event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void noBid() {
        String event_ = StringList.concat(container.getMessages().getVal(MainWindow.NO_BID),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
        container.revalidate();
    }

    @Override
    public void constCallPlayer(byte _called) {
        String mess_ = container.getMessages().getVal(MainWindow.PARTNERS_TAKER);
        StringList pseudos_=pseudosSimuleeTarot();
        String event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(_called)),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void pause() {
        container.pause();
    }

    @Override
    public void prepare() {
        CustComponent.invokeLater(new PrepareSimuTarot(this));
    }

    void prepareGui() {
        container.setArretDemo(false);
        //desactiver le menu Partie/aide au jeu
        container.getHelpGame().setEnabledMenu(false);
        //desactiver le menu Partie/Demo
        container.getDemo().setEnabledMenu(false);
        //Activer le menu Partie/Pause
        container.getPause().setEnabledMenu(true);
        String lg_ = container.getOwner().getLanguageKey();
        GameTarot partie_=partieTarotSimulee();
        Panel contentPane_ = Panel.newPageBox();
        Panel container_=Panel.newBorder();
        container_.add(new TextLabel(container.getMessages().getVal(MainWindow.HELP_GO_MENU),SwingConstants.CENTER),BorderLayout.NORTH);
        StringList pseudos_ = pseudosSimuleeTarot();
        CarpetTarot tapis_ = CarpetTarot.initTapisTarot(lg_, partie_.getNombreDeJoueurs(), container.getDisplayingTarot().isClockwise(), partie_.getDistribution().derniereMain().total());
        container.getTapis().setTapisTarot(tapis_);
        container_.add(tapis_.getContainer(),BorderLayout.CENTER);
        container.setPanelHand(Panel.newLineBox());
        Panel panneau_=Panel.newLineBox();
        panneau_.add(container.getPanelHand());
        container.setPanelDiscardedTrumps(Panel.newLineBox());
        container.getPanelDiscardedTrumps().setVisible(false);
        panneau_.add(container.getPanelDiscardedTrumps());
        panneau_.setBackground(Color.BLUE);
        container_.add(panneau_,BorderLayout.SOUTH);
        Panel panneau2_=Panel.newPageBox();
        container.setEvents(new TextArea(ContainerTarot.EMPTY,8, 30));
        container.getEvents().setEditable(false);
        panneau2_.add(new ScrollPane(container.getEvents()));
        container.setMini(MiniCarpet.newCarpet(partie_.getNombreDeJoueurs(),container.getDisplayingTarot().isClockwise(),pseudos_));
        panneau2_.add(container.getMiniPanel());
        container.setHandfuls(new ByteMap<TextLabel>());
        container.setDeclaredHandfuls(new ByteMap<Panel>());
        Panel declaredHandfuls_ = Panel.newGrid(0,1);
        int nbPlayers_ = partie_.getNombreDeJoueurs();
        for (byte i=CustList.FIRST_INDEX;i<nbPlayers_;i++) {
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
        panneau2_.add(new ScrollPane(declaredHandfuls_));
        Panel sousPanneau_=Panel.newGrid(0,1);
        container.setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(sousPanneau_);
        container_.add(panneau2_,BorderLayout.EAST);
        container.tapisTarot().setTalonTarot(lg_,partie_.getDistribution().derniereMain());
        contentPane_.add(container_);
        contentPane_.add(container.getWindow().getClock());
        contentPane_.add(container.getWindow().getLastSavedGameDate());
        container.setContentPane(contentPane_);
        panneau_=container.getPanneauBoutonsJeu();
        panneau_.add(stopButton);
        Panel panneau1_=container.getPanelHand();
        panneau1_.removeAll();
        /*On place les cartes de l'utilisateur*/
        for (GraphicTarotCard c: ContainerTarot.getGraphicCards(lg_,partie_.getDeal().hand())) {
            panneau1_.add(c);
        }
        panneau1_.repaintChildren();
        panneau1_.validate();
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
        CustComponent.invokeLater(new StopDemo(container));
    }

    @Override
    public void endDeal() {
        CustComponent.invokeLater(new EndDealSimuTarot(this));
    }

    void endGuiDeal() {
        String lg_ = container.getOwner().getLanguageKey();
        ResultsTarot res_ = new ResultsTarot();
        GameTarot currentGame_=partieTarotSimulee();
        res_.setGame(currentGame_);
        StringList nicknames_=pseudosSimuleeTarot();
        res_.setUser(DealTarot.NUMERO_UTILISATEUR);
        res_.initialize(new StringList(nicknames_), container.getScores());
        DocumentReaderCardsResultsUtil.setMessages(res_,lg_);
        ScrollPane scroll_=new ScrollPane();
        RenderedPage editor_ = new RenderedPage(scroll_);
        editor_.setLanguage(lg_);
        editor_.setDataBase(res_);
        editor_.initialize(FileConst.RESOURCES_HTML_FILES_RESULTS_TAROT, new TarotStandards());
        scroll_.setPreferredSize(new Dimension(300,300));

        Panel panneau_=Panel.newPageBox();
        panneau_.add(scroll_);
        panneau_.add(stopButton);
        panneau_.add(container.getOwner().getClock());
        panneau_.add(container.getOwner().getLastSavedGameDate());
        container.setContentPane(panneau_);
        container.pack();
    }
    @Override
    public void callCard() {
        String event_ = StringList.concat(container.getMessages().getVal(MainWindow.TAKER_CALL),ContainerGame.RETURN_LINE);
        event_ = StringList.concat(event_,container.getMessages().getVal(MainWindow.TAKER_CALL_WARNING),ContainerGame.RETURN_LINE);
        event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void callCard(byte _taker,HandTarot _calledCards) {
        String lg_ = container.getOwner().getLanguageKey();
        StringList pseudos_=pseudosSimuleeTarot();
        String mess_ = container.getMessages().getVal(MainWindow.DEMO_ACTION);
        String event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(_taker), Games.toString(_calledCards,lg_)),ContainerGame.RETURN_LINE);
        mess_ = container.getMessages().getVal(MainWindow.CALLED_PLAYER);
        event_ = StringList.concat(event_, StringList.simpleStringsFormat(mess_, Games.toString(_calledCards,lg_)),ContainerGame.RETURN_LINE);
        event_ = StringList.concat(event_, container.getMessages().getVal(MainWindow.CALLED_PLAYER_WARNING),ContainerGame.RETURN_LINE);
        event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void seeDog(HandTarot _calledCards) {
        String event_ = StringList.concat(container.getMessages().getVal(MainWindow.SHOWN_DOG),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
        ThreadUtil.sleep(1000);
        container.setCanDiscard(false);
        ThreadInvoker.invokeNow(new SimulationRefreshHandTarotDog(container, _calledCards));
        event_ = StringList.concat(container.getMessages().getVal(MainWindow.PLAYERS_SHOW_DOG),ContainerGame.RETURN_LINE);
        event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
        container.revalidate();
    }

    @Override
    public void autoCall(Bytes _called, byte _taker) {
        if (_called.containsObj(_taker)) {
            String event_ = StringList.concat(container.getMessages().getVal(MainWindow.ALONE_TAKER),ContainerGame.RETURN_LINE);
            event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
        }
    }

    @Override
    public void beforeSeeDog(byte _taker, HandTarot _curHand) {
        String event_ = StringList.concat(container.getMessages().getVal(MainWindow.TAKE_DOG),ContainerGame.RETURN_LINE);
        event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
        ThreadInvoker.invokeNow(new WithdrawCards(container));
        if(_taker==0) {
            afficherMainUtilisateurSimuTarot(_curHand);
        }
    }

    @Override
    public void mergeDog(byte _taker, HandTarot _curHandAdd, HandTarot _last) {
        String mess_ = container.getMessages().getVal(MainWindow.DISCARD_CARDS);
        String event_ = StringList.concat(StringList.simpleNumberFormat(mess_, _last.total()),ContainerGame.RETURN_LINE);
        event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
        ThreadInvoker.invokeNow(new SimulationDiscardTarot(container, _last));
        if(_taker==0) {
            afficherMainUtilisateurSimuTarot(_curHandAdd);
        }
    }

    @Override
    public void mergedDog(byte _taker, HandTarot _nextHand) {
        if(_taker==0) {
            afficherMainUtilisateurSimuTarot(_nextHand);
        }
    }

    @Override
    public void declareSlam(BooleanList _slam,byte _taker,BidTarot _bid) {
        if (!_slam.get(_taker)) {
            return;
        }
        if (_bid.getJeuChien() == PlayingDog.WITH) {
            String event_ = StringList.concat(container.getMessages().getVal(MainWindow.DECLARING_SLAM_DEMO_DISCARD),ContainerGame.RETURN_LINE);
            event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
        } else {
            String event_ = StringList.concat(container.getMessages().getVal(MainWindow.DECLARING_SLAM_DEMO),ContainerGame.RETURN_LINE);
            event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
        }
    }

    @Override
    public void firstCardPlaying(byte _joueur) {
        StringList pseudos_=pseudosSimuleeTarot();
        String mess_ = container.getMessages().getVal(MainWindow.PLAY_CARD_FIRST);
        String event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(_joueur)),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void nextCardPlaying(byte _joueur) {
        StringList pseudos_=pseudosSimuleeTarot();
        String mess_ = container.getMessages().getVal(MainWindow.PLAY_CARD_THEN);
        String event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(_joueur)),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void declareHandfuls(byte _joueur, EnumList<Handfuls> _annoncesPoignees, HandTarot _poignee) {
        if (!_poignee.estVide()) {
            String lg_ = container.getOwner().getLanguageKey();
            StringList pseudos_=pseudosSimuleeTarot();
            String mess_ = container.getMessages().getVal(MainWindow.DEMO_ACTION);
            String event_ = StringList.concat(StringList.simpleStringsFormat(mess_,pseudos_.get(_joueur),Games.toString(_annoncesPoignees.first(),lg_)),ContainerGame.RETURN_LINE);
            event_ = StringList.concat(event_, StringList.simpleStringsFormat(mess_,pseudos_.get(_joueur),Games.toString(_poignee,lg_)),ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
        }
    }

    @Override
    public void declareMiseres(byte _joueur, EnumList<Miseres> _annoncesMiseres) {
        String lg_ = container.getOwner().getLanguageKey();
        StringList pseudos_=pseudosSimuleeTarot();
        for(Miseres annonce_:_annoncesMiseres) {
            String mess_ = container.getMessages().getVal(MainWindow.DEMO_ACTION);
            String event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(_joueur),Games.toString(annonce_,lg_)),ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
        }
    }

    @Override
    public void displayCalled(byte _joueur) {
        String lg_ = container.getOwner().getLanguageKey();
        StringList pseudos_=pseudosSimuleeTarot();
        String mess_ = container.getMessages().getVal(MainWindow.DEMO_ACTION);
        container.getMini().setStatus(Status.CALLED_PLAYER, _joueur);
        String event_ = StringList.concat(StringList.simpleStringsFormat(mess_,pseudos_.get(_joueur),Games.toString(Status.CALLED_PLAYER,lg_)),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void played(byte _joueur, CardTarot _playedCard) {
        String lg_ = container.getOwner().getLanguageKey();
        container.tapisTarot().setCarteTarot(lg_,_joueur,_playedCard);
    }

    @Override
    public void displayUserHand(HandTarot _main) {
        afficherMainUtilisateurSimuTarot(_main);
    }

    @Override
    public void displayTrickWinner(byte _trickWinner) {
        StringList pseudos_=pseudosSimuleeTarot();
        String mess_ = container.getMessages().getVal(MainWindow.TRICK_WINNER);
        String event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(_trickWinner)),ContainerGame.RETURN_LINE);
        event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void displaySmallBound(BooleanList _smallBound, byte _trickWinner) {
        if (!_smallBound.get(_trickWinner)) {
            return;
        }
        String lg_ = container.getOwner().getLanguageKey();
        StringList pseudos_=pseudosSimuleeTarot();
        String mess_ = container.getMessages().getVal(MainWindow.BONUS_WIN);
        String event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(_trickWinner), Games.toString(BonusTarot.SMALL_BOUND,lg_)),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void beginPlay() {
        String event_ = StringList.concat(container.getMessages().getVal(MainWindow.BEGIN_PLAY_CARDS),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void clearCarpet(byte _nbPlayers) {
        String lg_ = container.getOwner().getLanguageKey();
        container.tapisTarot().setCartesTarotJeu(lg_,_nbPlayers);
    }
    private void afficherMainUtilisateurSimuTarot(HandTarot _mainUtilisateur) {
        ThreadInvoker.invokeNow(new SimulationRefreshHandTarot(container, _mainUtilisateur));
    }
    private StringList pseudosSimuleeTarot() {
        GameTarot partie_=partieTarotSimulee();
        return container.pseudosTarot(partie_.getNombreDeJoueurs());
    }
    private GameTarot partieTarotSimulee() {
        return partieSimulee.partieTarot();
    }
}
