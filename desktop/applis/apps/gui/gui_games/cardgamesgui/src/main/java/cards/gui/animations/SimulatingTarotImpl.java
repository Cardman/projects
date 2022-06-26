package cards.gui.animations;

import cards.consts.Role;
import cards.facade.Games;
import cards.gameresults.sml.DocumentReaderCardsResultsUtil;
import cards.gui.WindowCards;
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

import code.gui.document.PreparedAnalyzed;
import code.gui.document.RenderedPage;
import code.gui.images.MetaDimension;
import code.scripts.imgs.cards.CardsInit;
import code.threads.ThreadUtil;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;





public final class SimulatingTarotImpl implements SimulatingTarot {
    private final ContainerSimuTarot container;
    private final Games partieSimulee;
    private final DisplayingTarot displayingTarot;
    private final StopEvent stopEvent;


    public SimulatingTarotImpl(ContainerSimuTarot _container, Games _partieSimulee,
                               DisplayingTarot _displayingTarot, StopEvent _stopEvent) {
        container = _container;
        partieSimulee = _partieSimulee;
        displayingTarot = _displayingTarot;
        stopEvent = _stopEvent;
    }

    @Override
    public void displayLineReturn() {
        String event_ = ContainerGame.RETURN_LINE;
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
    }

    @Override
    public DisplayingTarot getDisplaying() {
        return displayingTarot;
    }

    @Override
    public void actingBid(byte _player) {
        StringList pseudos_=pseudosSimuleeTarot();
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(container.getMessages().getVal(WindowCards.DECLARE_BID), pseudos_.get(_player)),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
    }

    @Override
    public void actedBid(byte _player, BidTarot _bid) {
        String lg_ = container.getOwner().getLanguageKey();
        StringList pseudos_=pseudosSimuleeTarot();
        String mess_ = container.getMessages().getVal(WindowCards.DEMO_ACTION);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_,pseudos_.get(_player),Games.toString(_bid,lg_)),ContainerGame.RETURN_LINE);
        event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
    }

    @Override
    public void noBid() {
        String event_ = StringUtil.concat(container.getMessages().getVal(WindowCards.NO_BID),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
        container.revalidate();
    }

    @Override
    public void constCallPlayer(byte _called) {
        String mess_ = container.getMessages().getVal(WindowCards.PARTNERS_TAKER);
        StringList pseudos_=pseudosSimuleeTarot();
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_called)),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
    }

//    @Override
//    public void pause() {
//        container.pause();
//    }

    @Override
    public void prepare() {
        FrameUtil.invokeLater(new PrepareSimuTarot(this), container.getOwner().getFrames());
    }

    void prepareGui() {
        container.setArretDemo(false);
        //desactiver le menu Partie/aide au jeu
        MenuItemUtils.setEnabledMenu(container.getHelpGame(),false);
        //desactiver le menu Partie/Demo
        MenuItemUtils.setEnabledMenu(container.getDemo(),false);
        //Activer le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(container.getPause(),true);
        String lg_ = container.getOwner().getLanguageKey();
        GameTarot partie_=partieTarotSimulee();
        AbsPanel contentPane_ = container.getOwner().getCompoFactory().newPageBox();
        AbsPanel container_=container.getOwner().getCompoFactory().newBorder();
        container_.add(container.getOwner().getCompoFactory().newPlainLabel(container.getMessages().getVal(WindowCards.HELP_GO_MENU)),GuiConstants.BORDER_LAYOUT_NORTH);
        StringList pseudos_ = pseudosSimuleeTarot();
        CarpetTarot tapis_ = CarpetTarot.initTapisTarot(lg_, partie_.getNombreDeJoueurs(), container.getDisplayingTarot().isClockwise(), partie_.getDistribution().derniereMain().total(), container.getOwner().getCompoFactory());
        container.getTapis().setTapisTarot(tapis_);
        container_.add(tapis_.getContainer(),GuiConstants.BORDER_LAYOUT_CENTER);
        container.setPanelHand(container.getOwner().getCompoFactory().newLineBox());
        AbsPanel panneau_=container.getOwner().getCompoFactory().newLineBox();
        panneau_.add(container.getPanelHand());
        container.setPanelDiscardedTrumps(container.getOwner().getCompoFactory().newLineBox());
        container.getPanelDiscardedTrumps().setVisible(false);
        panneau_.add(container.getPanelDiscardedTrumps());
        panneau_.setBackground(GuiConstants.BLUE);
        container_.add(panneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
        AbsPanel panneau2_=container.getOwner().getCompoFactory().newPageBox();
        container.setEvents(container.getOwner().getCompoFactory().newTextArea(ContainerTarot.EMPTY,8, 30));
        container.getEvents().setEditable(false);
        panneau2_.add(container.getOwner().getCompoFactory().newAbsScrollPane(container.getEvents()));
        container.setMini(MiniCarpet.newCarpet(container.getWindow().getImageFactory(),partie_.getNombreDeJoueurs(),container.getDisplayingTarot().isClockwise(),pseudos_, container.getWindow().getCompoFactory()));
        panneau2_.add(container.getMiniPanel());
        container.setHandfuls(new ByteMap<AbsPlainLabel>());
        container.setDeclaredHandfuls(new ByteMap<AbsPanel>());
        AbsPanel declaredHandfuls_ = container.getOwner().getCompoFactory().newGrid(0,1);
        int nbPlayers_ = partie_.getNombreDeJoueurs();
        for (byte i = IndexConstants.FIRST_INDEX; i<nbPlayers_; i++) {
            AbsPanel declaredHandfulGroup_ = container.getOwner().getCompoFactory().newLineBox();
            AbsPlainLabel lab_ = container.getOwner().getCompoFactory().newPlainLabel(pseudos_.get(i));
            declaredHandfulGroup_.add(lab_);
            AbsPlainLabel handful_ = container.getOwner().getCompoFactory().newPlainLabel(ContainerGame.EMPTY_STRING);
            declaredHandfulGroup_.add(handful_);
            container.getHandfuls().put(i, handful_);
            AbsPanel declaredHandful_ = container.getOwner().getCompoFactory().newLineBox();
            declaredHandfulGroup_.add(declaredHandful_);
            container.getDeclaredHandfuls().put(i, declaredHandful_);
            declaredHandfuls_.add(declaredHandfulGroup_);
        }
        panneau2_.add(container.getOwner().getCompoFactory().newAbsScrollPane(declaredHandfuls_));
        AbsPanel sousPanneau_=container.getOwner().getCompoFactory().newGrid(0,1);
        container.setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(sousPanneau_);
        container_.add(panneau2_,GuiConstants.BORDER_LAYOUT_EAST);
        container.tapisTarot().setTalonTarot(lg_,partie_.getDistribution().derniereMain(), container.getOwner().getCompoFactory());
        contentPane_.add(container_);
        contentPane_.add(container.getWindow().getClock());
        contentPane_.add(container.getWindow().getLastSavedGameDate());
        container.setContentPane(contentPane_);
        panneau_=container.getPanneauBoutonsJeu();
        AbsPlainButton stopButton_ = container.getOwner().getCompoFactory().newPlainButton(container.getMessages().getVal(WindowCards.STOP_DEMO));
        stopButton_.addActionListener(stopEvent);
        panneau_.add(stopButton_);
        AbsPanel panneau1_=container.getPanelHand();
        panneau1_.removeAll();
        /*On place les cartes de l'utilisateur*/
        for (GraphicTarotCard c: ContainerTarot.getGraphicCards(container.getWindow(), lg_,partie_.getDeal().hand().getCards())) {
            panneau1_.add(c);
        }
        panneau1_.repaintChildren(container.getOwner().getImageFactory());
        panneau1_.validate();
    }
    @Override
    public void beginDemo() {
        String event_;
        event_ = StringUtil.concat(container.getMessages().getVal(WindowCards.BEGIN_DEMO),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
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
        FrameUtil.invokeLater(new StopDemo(container), container.getOwner().getFrames());
    }

    @Override
    public void endDeal() {
        FrameUtil.invokeLater(new EndDealSimuTarot(this), container.getOwner().getFrames());
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
        AbsScrollPane scroll_=container.getOwner().getCompoFactory().newAbsScrollPane();
        RenderedPage editor_ = new RenderedPage(scroll_, container.getWindow().getFrames());
        res_.getRes().setGeneral(container.readCoreResource());
        res_.getRes().setSpecific(container.readResource());
        PreparedAnalyzed stds_ = container.retrieve(FileConst.RESOURCES_HTML_FILES_RESULTS_TAROT);
        ((TarotStandards)stds_.getBeanNatLgNames()).setDataBase(res_);
        editor_.initialize(stds_);
        scroll_.setPreferredSize(new MetaDimension(300,300));

        AbsPanel panneau_=container.getOwner().getCompoFactory().newPageBox();
        AbsScrollPane scrollTxt_=container.getOwner().getCompoFactory().newAbsScrollPane(container.getOwner().getCompoFactory().newTextArea(container.getEvents().getText(),8, 30));
        AbsSplitPane spl_ = container.getOwner().getCompoFactory().newHorizontalSplitPane(scroll_,scrollTxt_);
        panneau_.add(spl_);
        AbsPlainButton stopButton_ = container.getOwner().getCompoFactory().newPlainButton(container.getMessages().getVal(WindowCards.STOP_DEMO));
        stopButton_.addActionListener(stopEvent);
        panneau_.add(stopButton_);
        panneau_.add(container.getOwner().getClock());
        panneau_.add(container.getOwner().getLastSavedGameDate());
        container.setContentPane(panneau_);
        container.pack();
    }
    @Override
    public void callCard() {
        String event_ = StringUtil.concat(container.getMessages().getVal(WindowCards.TAKER_CALL),ContainerGame.RETURN_LINE);
        event_ = StringUtil.concat(event_,container.getMessages().getVal(WindowCards.TAKER_CALL_WARNING),ContainerGame.RETURN_LINE);
        event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
    }

    @Override
    public void callCard(byte _taker,HandTarot _calledCards) {
        String lg_ = container.getOwner().getLanguageKey();
        StringList pseudos_=pseudosSimuleeTarot();
        String mess_ = container.getMessages().getVal(WindowCards.DEMO_ACTION);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_taker), Games.toString(_calledCards,lg_)),ContainerGame.RETURN_LINE);
        mess_ = container.getMessages().getVal(WindowCards.CALLED_PLAYER);
        event_ = StringUtil.concat(event_, StringUtil.simpleStringsFormat(mess_, Games.toString(_calledCards,lg_)),ContainerGame.RETURN_LINE);
        event_ = StringUtil.concat(event_, container.getMessages().getVal(WindowCards.CALLED_PLAYER_WARNING),ContainerGame.RETURN_LINE);
        event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
    }

    @Override
    public void seeDog(HandTarot _calledCards) {
        String event_ = StringUtil.concat(container.getMessages().getVal(WindowCards.SHOWN_DOG),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
        ThreadUtil.sleep(container.getOwner().getThreadFactory(),1000);
        container.setCanDiscard(false);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new SimulationRefreshHandTarotDog(container, _calledCards), container.getOwner().getFrames());
        event_ = StringUtil.concat(container.getMessages().getVal(WindowCards.PLAYERS_SHOW_DOG),ContainerGame.RETURN_LINE);
        event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
        container.revalidate();
    }

    @Override
    public void autoCall(Bytes _called, byte _taker) {
        if (_called.containsObj(_taker)) {
            String event_ = StringUtil.concat(container.getMessages().getVal(WindowCards.ALONE_TAKER),ContainerGame.RETURN_LINE);
            event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
        }
    }

    @Override
    public void beforeSeeDog(byte _taker, HandTarot _curHand) {
        String event_ = StringUtil.concat(container.getMessages().getVal(WindowCards.TAKE_DOG),ContainerGame.RETURN_LINE);
        event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new WithdrawCards(container), container.getOwner().getFrames());
        if(_taker==0) {
            afficherMainUtilisateurSimuTarot(_curHand);
        }
    }

    @Override
    public void mergeDog(byte _taker, HandTarot _curHandAdd, HandTarot _last) {
        String mess_ = container.getMessages().getVal(WindowCards.DISCARD_CARDS);
        String event_ = StringUtil.concat(StringUtil.simpleNumberFormat(mess_, _last.total()),ContainerGame.RETURN_LINE);
        event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new SimulationDiscardTarot(container, _last), container.getOwner().getFrames());
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
    public void declareSlam(CustList<Boolean> _slam,byte _taker,BidTarot _bid) {
        if (!_slam.get(_taker)) {
            return;
        }
        if (_bid.getJeuChien() == PlayingDog.WITH) {
            String event_ = StringUtil.concat(container.getMessages().getVal(WindowCards.DECLARING_SLAM_DEMO_DISCARD),ContainerGame.RETURN_LINE);
            event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
        } else {
            String event_ = StringUtil.concat(container.getMessages().getVal(WindowCards.DECLARING_SLAM_DEMO),ContainerGame.RETURN_LINE);
            event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
        }
    }

    @Override
    public void firstCardPlaying(byte _joueur) {
        StringList pseudos_=pseudosSimuleeTarot();
        String mess_ = container.getMessages().getVal(WindowCards.PLAY_CARD_FIRST);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_joueur)),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
    }

    @Override
    public void nextCardPlaying(byte _joueur) {
        StringList pseudos_=pseudosSimuleeTarot();
        String mess_ = container.getMessages().getVal(WindowCards.PLAY_CARD_THEN);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_joueur)),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
    }

    @Override
    public void declareHandfuls(byte _joueur, EnumList<Handfuls> _annoncesPoignees, HandTarot _poignee) {
        if (!_poignee.estVide()) {
            String lg_ = container.getOwner().getLanguageKey();
            StringList pseudos_=pseudosSimuleeTarot();
            String mess_ = container.getMessages().getVal(WindowCards.DEMO_ACTION);
            String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_,pseudos_.get(_joueur),Games.toString(_annoncesPoignees.first(),lg_)),ContainerGame.RETURN_LINE);
            event_ = StringUtil.concat(event_, StringUtil.simpleStringsFormat(mess_,pseudos_.get(_joueur),Games.toString(_poignee,lg_)),ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
        }
    }

    @Override
    public void declareMiseres(byte _joueur, EnumList<Miseres> _annoncesMiseres) {
        String lg_ = container.getOwner().getLanguageKey();
        StringList pseudos_=pseudosSimuleeTarot();
        for(Miseres annonce_:_annoncesMiseres) {
            String mess_ = container.getMessages().getVal(WindowCards.DEMO_ACTION);
            String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_joueur),Games.toString(annonce_,lg_)),ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
        }
    }

    @Override
    public void displayCalled(byte _joueur) {
        String lg_ = container.getOwner().getLanguageKey();
        StringList pseudos_=pseudosSimuleeTarot();
        String mess_ = container.getMessages().getVal(WindowCards.DEMO_ACTION);
        container.getMini().setStatus(container.getWindow().getImageFactory(),Role.CALLED_PLAYER, _joueur);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_,pseudos_.get(_joueur),Games.toString(Role.CALLED_PLAYER,lg_)),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
    }

    @Override
    public void played(byte _joueur, CardTarot _playedCard) {
        String lg_ = container.getOwner().getLanguageKey();
        container.tapisTarot().setCarteTarot(container.getWindow().getImageFactory(),lg_,_joueur,_playedCard, container.getWindow().getImages());
    }

    @Override
    public void displayUserHand(HandTarot _main) {
        afficherMainUtilisateurSimuTarot(_main);
    }

    @Override
    public void displayTrickWinner(byte _trickWinner) {
        StringList pseudos_=pseudosSimuleeTarot();
        String mess_ = container.getMessages().getVal(WindowCards.TRICK_WINNER);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_trickWinner)),ContainerGame.RETURN_LINE);
        event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
    }

    @Override
    public void displaySmallBound(CustList<Boolean> _smallBound, byte _trickWinner) {
        if (!_smallBound.get(_trickWinner)) {
            return;
        }
        String lg_ = container.getOwner().getLanguageKey();
        StringList pseudos_=pseudosSimuleeTarot();
        String mess_ = container.getMessages().getVal(WindowCards.BONUS_WIN);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_trickWinner), Games.toString(BonusTarot.SMALL_BOUND,lg_)),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
    }

    @Override
    public void beginPlay() {
        String event_ = StringUtil.concat(container.getMessages().getVal(WindowCards.BEGIN_PLAY_CARDS),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
    }

    @Override
    public void clearCarpet(byte _nbPlayers) {
        String lg_ = container.getOwner().getLanguageKey();
        container.tapisTarot().setCartesTarotJeu(container.getWindow().getImageFactory(),lg_,_nbPlayers);
    }
    private void afficherMainUtilisateurSimuTarot(HandTarot _mainUtilisateur) {
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new SimulationRefreshHandTarot(container, _mainUtilisateur), container.getOwner().getFrames());
    }
    private StringList pseudosSimuleeTarot() {
        GameTarot partie_=partieTarotSimulee();
        return container.pseudosTarot(partie_.getNombreDeJoueurs());
    }
    private GameTarot partieTarotSimulee() {
        return partieSimulee.partieTarot();
    }
}
