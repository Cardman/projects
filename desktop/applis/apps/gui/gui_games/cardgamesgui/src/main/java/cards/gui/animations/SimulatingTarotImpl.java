package cards.gui.animations;

import cards.consts.Role;
import cards.facade.Games;
import cards.gui.containers.*;
import cards.gui.dialogs.FrameGeneralHelp;
import cards.gui.labels.TarotCardConverter;
import cards.gui.panels.CarpetTarot;
import cards.gui.panels.MiniCarpet;
import cards.gui.panels.PanelTricksHandsTarot;
import cards.main.CardNatLgNamesNavigation;
import cards.tarot.*;
import cards.tarot.beans.TarotStandards;
import cards.tarot.enumerations.*;
import code.gui.*;

import code.gui.document.RenderedPage;
import code.gui.files.MessagesGuiFct;
import code.gui.images.MetaDimension;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.threads.AbstractAtomicInteger;
import code.util.*;
import code.util.core.BoolVal;
import code.util.core.StringUtil;





public final class SimulatingTarotImpl extends AbstractSimulatingTarot {
    private final ContainerSimuTarot container;
    private final StopEvent stopEvent;
    private final GameTarot gameTarot;


    public SimulatingTarotImpl(ContainerSimuTarot _container, Games _partieSimulee,
                               DisplayingTarot _displayingTarot, StopEvent _stopEvent, IntGameTarot _ia, AbstractAtomicInteger _state) {
        super(_displayingTarot, _ia, _state);
        gameTarot = _partieSimulee.partieTarot();
        container = _container;
        stopEvent = _stopEvent;
    }

    @Override
    public byte dealer(GameTarot _gt) {
        prepare();
        container.sleepThread(500);
        String event_;
        event_ = StringUtil.concat(container.fileSimu().getVal(MessagesGuiCards.SIMU_BEGIN_DEMO),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
        displayUserHand(_gt.mainUtilisateurTriee(getDisplaying()));
        return super.dealer(_gt);
    }

    @Override
    public void bid(GameTarot _gt) {
        byte p_ = _gt.playerHavingToBid();
        BidTarot contratTmp_ = getInt().strategieContrat(_gt);
        actingBid(p_);
        container.sleepThread(1000);
        _gt.ajouterContrat(contratTmp_);
        actedBid(p_,contratTmp_);
    }

    @Override
    public boolean noBid(GameTarot _g) {
        boolean n_ = super.noBid(_g);
        if (n_) {
            noBid();
        }
        return n_;
    }

    @Override
    public byte constCallPlayerCall(byte _called) {
        byte n_ = super.constCallPlayerCall(_called);
        constCallPlayer(n_);
        return n_;
    }

    @Override
    public void intelligenceArtificielleAppel(GameTarot _gt) {
        super.intelligenceArtificielleAppel(_gt);
        callCard();
        callCard(_gt.getPreneur(),_gt.getCalledCards());
    }

    @Override
    public void ecarter(GameTarot _gt) {
        HandTarot last_ = _gt.getDeal().derniereMain();
        HandTarot curHand_ = _gt.mainUtilisateurTriee(getDisplaying());
        merge(_gt, last_, curHand_);
        HandTarot atouts_ = _gt.ecarter(getInt());
        HandTarot nextHand_ = _gt.mainUtilisateurTriee(getDisplaying());
        mergedDog(_gt.getPreneur(),nextHand_);
        autoCall();
        declareSlam(_gt.getContrat());
        container.getOwner().getFrames().getCompoFactory().invokeNow(new DisplaySimuDiscardedTarotTrumpCards(container,atouts_));
    }

    @Override
    public void appelApresEcart(GameTarot _gt) {
        HandTarot last_ = _gt.getDeal().derniereMain();
        HandTarot curHand_ = _gt.mainUtilisateurTriee(getDisplaying());
        merge(_gt, last_, curHand_);
        HandTarot atouts_ = _gt.appelApresEcart(getInt());
        HandTarot nextHand_ = _gt.mainUtilisateurTriee(getDisplaying());
        mergedDog(_gt.getPreneur(),nextHand_);
        callCard();
        callCard(_gt.getPreneur(),_gt.getCalledCards());
        declareSlam(_gt.getContrat());
        container.getOwner().getFrames().getCompoFactory().invokeNow(new DisplaySimuDiscardedTarotTrumpCards(container,atouts_));
    }

    @Override
    public void gererChienInconnu(GameTarot _gt) {
        super.gererChienInconnu(_gt);
        declareSlam(_gt.getContrat());
    }

    @Override
    public void firstLead(GameTarot _gt) {
        super.firstLead(_gt);
        displayLineReturn();
        beginPlay();
    }

    @Override
    public CardTarot play(GameTarot _g) {
        byte joueur_ = _g.playerHavingToPlay();
        beforeCards(joueur_);
        container.sleepThread(1000);
//                _simu.pause();
        CardTarot ct_ = _g.currentPlayerHasPlayed(getInt());
        endCards(joueur_, ct_);
        return ct_;
    }

    private void beforeCards(byte _joueur) {
        if (partieTarotSimulee().getProgressingTrick().estVide()) {
            firstCardPlaying(_joueur);
        } else {
            nextCardPlaying(_joueur);
        }
    }

    private void endCards(byte _joueur, CardTarot _ct) {
        if (partieTarotSimulee().premierTourNoMisere()) {
            declareHandfuls(_joueur,partieTarotSimulee().getAnnoncesPoignees(_joueur),partieTarotSimulee().getPoignee(_joueur));
            declareMiseres(_joueur,partieTarotSimulee().getAnnoncesMiseres(_joueur));
        }
//        ContainerTarot.callCard(container,partieTarotSimulee(),_joueur,pseudosSimuleeTarot().get(_joueur),_ct,new IndirectCardsCallEvents(container.getOwner().getCompoFactory()));
        if (partieTarotSimulee().getCalledCards().contient(_ct)) {
            displayCalled(_joueur);
        }
        played(_joueur,_ct);

        display(_joueur, partieTarotSimulee().mainUtilisateurTriee(getDisplaying()).getCards());

//        if(_joueur ==DealTarot.NUMERO_UTILISATEUR) {
//            displayUserHand(partieTarotSimulee().mainUtilisateurTriee(getDisplaying()));
//        }
    }

    @Override
    public byte ajouterPetitAuBoutPliEnCours(GameTarot _gt) {
        byte w_ = super.ajouterPetitAuBoutPliEnCours(_gt);
        displayTrickWinner(w_);
        displaySmallBound(_gt.getSmallBound(),w_);
        container.sleepThread(4000);
//            _simu.pause();
        clearCarpet(_gt.getNombreDeJoueurs());
        return w_;
    }

    //    @Override
    public void displayLineReturn() {
        String event_ = ContainerGame.RETURN_LINE;
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void actingBid(byte _player) {
        StringList pseudos_=pseudosSimuleeTarot();
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(container.fileSimu().getVal(MessagesGuiCards.SIMU_DECLARE_BID), pseudos_.get(_player)),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void actedBid(byte _player, BidTarot _bid) {
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        StringList pseudos_=pseudosSimuleeTarot();
        String mess_ = container.fileSimu().getVal(MessagesGuiCards.SIMU_DEMO_ACTION);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_,pseudos_.get(_player),Games.toString(_bid,lg_)),ContainerGame.RETURN_LINE);
        event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void noBid() {
        String event_ = StringUtil.concat(container.fileSimu().getVal(MessagesGuiCards.SIMU_NO_BID),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
        container.revalidate();
    }

//    @Override
    public void constCallPlayer(byte _called) {
        String mess_ = container.fileSimu().getVal(MessagesGuiCards.SIMU_PARTNERS_TAKER);
        StringList pseudos_=pseudosSimuleeTarot();
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_called)),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
//    public void pause() {
//        container.pause();
//    }

//    @Override
    public void prepare() {
        container.getOwner().getFrames().getCompoFactory().invokeNow(new PrepareSimuTarot(this));
    }

    void prepareGui() {
        container.getPane().removeAll();
//        container.setArretDemo(false);
        //desactiver le menu Partie/aide au jeu
        MenuItemUtils.setEnabledMenu(container.getHelpGame(),false);
        //desactiver le menu Partie/Demo
//        MenuItemUtils.setEnabledMenu(container.getDemo(),false);
        container.window().changeMenuSimuEnabled(false);
        //Activer le menu Partie/Pause
//        MenuItemUtils.setEnabledMenu(container.getPause(),true);
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        GameTarot partie_=partieTarotSimulee();
        AbsPanel contentPane_ = container.getOwner().getCompoFactory().newPageBox();
        AbsPanel container_=container.getOwner().getCompoFactory().newBorder();
        container_.add(container.getOwner().getCompoFactory().newPlainLabel(container.helpMenuTip()), MessagesGuiFct.BORDER_LAYOUT_NORTH);
        StringList pseudos_ = pseudosSimuleeTarot();
        CarpetTarot tapis_ = CarpetTarot.initTapisTarot(lg_, partie_.getNombreDeJoueurs(), container.getDisplayingTarot().getDisplaying().isClockwise(), partie_.getDistribution().derniereMain().total(), container.getOwner().getFrames());
        container.getTapis().setTapisTarot(tapis_);
        container_.add(tapis_.getContainer(), MessagesGuiFct.BORDER_LAYOUT_CENTER);
//        container.setPanelHand(container.getOwner().getCompoFactory().newLineBox());
//        AbsPanel panneau_=container.getOwner().getCompoFactory().newLineBox();
//        panneau_.add(container.getPanelHand());
//        container.setPanelDiscardedTrumps(container.getOwner().getCompoFactory().newLineBox());
//        container.getPanelDiscardedTrumps().setVisible(false);
//        panneau_.add(container.getPanelDiscardedTrumps());
//        panneau_.setBackground(GuiConstants.BLUE);
//        container_.add(panneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
        AbsPanel panneau2_=container.getOwner().getCompoFactory().newPageBox();
        container.setEvents(ContainerSingleImpl.readOnly(container,ContainerTarot.EMPTY));
        panneau2_.add(container.getOwner().getCompoFactory().newAbsScrollPane(container.getEvents()));
        container.setMini(MiniCarpet.newCarpet(container.getWindow().getImageFactory(),partie_.getNombreDeJoueurs(), container.getDisplayingTarot().getDisplaying().isClockwise(),pseudos_, container.getWindow().getCompoFactory()));
        panneau2_.add(container.getMiniPanel());
//        container.setHandfuls(new ByteMap<AbsPlainLabel>());
//        container.setDeclaredHandfuls(new ByteMap<AbsPanel>());
//        AbsPanel declaredHandfuls_ = container.getOwner().getCompoFactory().newGrid(0,1);
        int nbPlayers_ = partie_.getNombreDeJoueurs();
//        for (byte i = IndexConstants.FIRST_INDEX; i<nbPlayers_; i++) {
//            AbsPanel declaredHandfulGroup_ = container.getOwner().getCompoFactory().newLineBox();
//            AbsPlainLabel lab_ = container.getOwner().getCompoFactory().newPlainLabel(pseudos_.get(i));
//            declaredHandfulGroup_.add(lab_);
//            AbsPlainLabel handful_ = container.getOwner().getCompoFactory().newPlainLabel(ContainerGame.EMPTY_STRING);
//            declaredHandfulGroup_.add(handful_);
//            container.getHandfuls().put(i, handful_);
//            AbsPanel declaredHandful_ = container.getOwner().getCompoFactory().newLineBox();
//            declaredHandfulGroup_.add(declaredHandful_);
//            container.getDeclaredHandfuls().put(i, declaredHandful_);
//            declaredHandfuls_.add(declaredHandfulGroup_);
//        }
        panneau2_.add(container.getOwner().getCompoFactory().newAbsScrollPane(container.buildDeclHands(nbPlayers_,pseudos_)));
        AbsPanel sousPanneau_=container.getOwner().getCompoFactory().newGrid(0,1);
        container.setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(sousPanneau_);
        container.setPanelDiscardedTrumps(container.getOwner().getCompoFactory().newLineBox());
        container.getPanelDiscardedTrumps().setVisible(false);
        panneau2_.add(container.getPanelDiscardedTrumps());
        container_.add(panneau2_, MessagesGuiFct.BORDER_LAYOUT_EAST);
        container.tapisTarot().setTalonTarot(lg_,partie_.getDistribution().derniereMain(), container.getOwner());
        contentPane_.add(container_);
        contentPane_.add(container.getWindow().getClock().getComponent());
        contentPane_.add(container.getWindow().getLastSavedGameDate());
        container.setContentPane(contentPane_);
//        AbsPanel panneau_=container.getPanneauBoutonsJeu();
//        AbsButton stopButton_ = container.getOwner().getCompoFactory().newPlainButton(container.fileSimu().getVal(MessagesGuiCards.SIMU_STOP_DEMO));
//        stopButton_.addActionListener(stopEvent);
//        container.getPanneauBoutonsJeu().add(stopButton_);
        container.getPanneauBoutonsJeu().add(ContainerSingleImpl.stopButton(container,stopEvent));
//        panneau_.add(stopButton_);
        AbsPanel panneau1_=new ContainerSingUtil<CardTarot>(new TarotCardConverter()).getGraphicCardsGenePanel(container.getWindow().getFrames(),partie_.getDeal().hand().getCards());
//        panneau1_.setBackground(GuiConstants.BLUE);
//        panneau1_.validate();
//        container.panelHand(panneau1_);
//        container.setPanelHand(panneau1_);
//        container_.add(panneau1_,GuiConstants.BORDER_LAYOUT_SOUTH);
//        container.pack();
        container.engage(container_,panneau1_);

//        container.setPanelHand(panneau1_);
//        container_.add(panneau1_,GuiConstants.BORDER_LAYOUT_SOUTH);
//        AbsPanel panneau1_=container.getPanelHand();
//        panneau1_.removeAll();
//        /*On place les cartes de l'utilisateur*/
//        for (GraphicCard<CardTarot> c: ContainerTarot.getGraphicCards(container.getWindow(), lg_,partie_.getDeal().hand().getCards())) {
//            panneau1_.add(c.getPaintableLabel());
//        }
//        panneau1_.validate();
    }
//    @Override
//    public void beginDemo() {
//        String event_;
//        event_ = StringUtil.concat(container.fileSimu().getVal(MessagesGuiCards.SIMU_BEGIN_DEMO),ContainerGame.RETURN_LINE);
//        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
//    }

//    @Override
//    public void sleepSimu(long _millis) {
//        container.sleepThread(_millis);
////        ThreadUtil.sleep(container.getOwner().getThreadFactory(),_millis);
//    }


    @Override
    public int stopped() {
        int s_ = getState().get();
        ContainerSingleImpl.afterStopped(container,s_,STATE_STOPPED);

//        if (s_ == STATE_STOPPED) {
//            container.getOwner().getFrames().getCompoFactory().invokeNow(new StopDemo(container));
//        }
        return s_;
//        return getState().get();
//        return getState().get() == STATE_STOPPED;
//        return container.isArretDemo();
    }

//    @Override
//    public int stoppedDemo() {
//        int s_ = super.stoppedDemo();
//        ContainerSingleImpl.afterStopped(container,s_,STATE_STOPPED);
//
////        if (s_ == STATE_STOPPED) {
////            container.getOwner().getFrames().getCompoFactory().invokeNow(new StopDemo(container));
////        }
//        return s_;
//    }
//    @Override
//    public boolean stopped() {
//        return container.isArretDemo();
//    }

//    @Override
//    public void stopDemo() {
//        container.getOwner().getFrames().getCompoFactory().invokeNow(new StopDemo(container));
//    }

//    @Override
//    public void endDeal() {
//        container.getOwner().getFrames().getCompoFactory().invokeNow(new EndDealSimuTarot(this));
//    }

    void endGuiDeal() {
        container.getPane().removeAll();
        AbsPanel containerTarot_=container.getOwner().getCompoFactory().newBorder();
        AbsTabbedPane onglets_=container.getOwner().getCompoFactory().newAbsTabbedPane();
        ResultsTarot res_ = new ResultsTarot();
        GameTarot currentGame_=partieTarotSimulee();
        res_.setGame(currentGame_);
        StringList nicknames_=pseudosSimuleeTarot();
        res_.getRes().setUser(DealTarot.NUMERO_UTILISATEUR);
        res_.initialize(new StringList(nicknames_), container.getScores());
        container.setScores(res_.getRes().getScores());
        Games.setMessages(res_.getRes(),container.getOwner().getFrames().currentLg());
        res_.getRes().setGeneral(container.readCoreResourceSuit());
        res_.getRes().setSpecific(container.readResource());
        res_.getRes().setGeneralCards(container.readCoreResourceCards());
        CardNatLgNamesNavigation stds_ = container.retrieve(FrameGeneralHelp.RESOURCES_HTML_FILES_RESULTS_TAROT).attendreResultat();
        ((TarotStandards)stds_.getBeanNatLgNames()).setDataBase(res_);
        RenderedPage editor_ = FrameGeneralHelp.initialize(stds_, container.getWindow().getFrames(), container.window().getGuardRender());
        editor_.getScroll().setPreferredSize(new MetaDimension(300,300));

        onglets_.add(container.file().getVal(MessagesGuiCards.MAIN_RESULTS_PAGE),editor_.getScroll());
//        panneau_.add(container.getOwner().getCompoFactory().newHorizontalSplitPane(editor_.getScroll(),container.getOwner().getCompoFactory().newAbsScrollPane(container.getOwner().getCompoFactory().newTextArea(container.getEvents().getText(),8, 30))));
//        AbsButton stopButton_ = container.getOwner().getCompoFactory().newPlainButton(container.fileSimu().getVal(MessagesGuiCards.SIMU_STOP_DEMO));
//        stopButton_.addActionListener(stopEvent);
//        panneau_.add(stopButton_);
        AbsPanel panneau_=container.getOwner().getCompoFactory().newPageBox();
        panneau_.add(ContainerSingleImpl.stopButton(container,stopEvent));
        panneau_.add(container.getOwner().getClock().getComponent());
        panneau_.add(container.getOwner().getLastSavedGameDate());
        TricksHandsTarot tricksHands_ = new TricksHandsTarot();
        tricksHands_.tricks(currentGame_);
        tricksHands_.sortHands(getDisplaying(), currentGame_.getNombreDeJoueurs());
        AbsCustComponent panelCards_ = new PanelTricksHandsTarot(container.getOwner().getCommonFrame(),tricksHands_,
                currentGame_.getNombreDeJoueurs(),
                pseudosSimuleeTarot(),
                getDisplaying(),container.getOwner()).getContainer();
        panelCards_.setPreferredSize(new MetaDimension(850,850));
        onglets_.add(container.file().getVal(MessagesGuiCards.MAIN_HANDS_TRICKS),panelCards_);
        onglets_.add(container.file().getVal(MessagesGuiCards.MAIN_DETAIL_RESULTS_PAGE),container.getOwner().getCompoFactory().newAbsScrollPane(ContainerSingleImpl.readOnly(container,container.getEvents().getText())));
        containerTarot_.add(onglets_, MessagesGuiFct.BORDER_LAYOUT_CENTER);
//        containerTarot_.add(container.getOwner().getCompoFactory().newAbsScrollPane(container.getOwner().getCompoFactory().newTextArea(container.getEvents().getText(),8, 30)),GuiConstants.BORDER_LAYOUT_EAST);
        containerTarot_.add(panneau_, MessagesGuiFct.BORDER_LAYOUT_SOUTH);
        container.setContentPane(containerTarot_);
        container.pack();
    }
//    @Override
    public void callCard() {
        String event_ = StringUtil.concat(container.fileSimu().getVal(MessagesGuiCards.SIMU_TAKER_CALL),ContainerGame.RETURN_LINE);
        event_ = StringUtil.concat(event_,container.fileSimu().getVal(MessagesGuiCards.SIMU_TAKER_CALL_WARNING),ContainerGame.RETURN_LINE);
        event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void callCard(byte _taker,HandTarot _calledCards) {
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        StringList pseudos_=pseudosSimuleeTarot();
        String mess_ = container.fileSimu().getVal(MessagesGuiCards.SIMU_DEMO_ACTION);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_taker), Games.toString(_calledCards,lg_)),ContainerGame.RETURN_LINE);
        mess_ = container.fileSimu().getVal(MessagesGuiCards.SIMU_CALLED_PLAYER);
        event_ = StringUtil.concat(event_, StringUtil.simpleStringsFormat(mess_, Games.toString(_calledCards,lg_)),ContainerGame.RETURN_LINE);
        event_ = StringUtil.concat(event_, container.fileSimu().getVal(MessagesGuiCards.SIMU_CALLED_PLAYER_WARNING),ContainerGame.RETURN_LINE);
        event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

    private void merge(GameTarot _gt, HandTarot _last, HandTarot _curHand) {
        ContainerSingUtil<CardTarot> csu_ = new ContainerSingUtil<CardTarot>(container.converter());
        csu_.seeDog(container, _last.getCards(), container.tapisTarot().getCenterDeck());
        csu_.seeHandDog(container, _curHand.getCards(),container.getPanelHand(),_gt.getPreneur(),DealTarot.NUMERO_UTILISATEUR);
//        display(_gt.getPreneur(), _curHand.getCards());
//        HandTarot curHandAdd_ = new HandTarot();
//        curHandAdd_.ajouterCartes(_curHand);
//        curHandAdd_.ajouterCartes(_last);
//        curHandAdd_.trier(getDisplaying().getDisplaying().getSuits(), getDisplaying().getDisplaying().isDecreasing());
        csu_.mergeDog(container,container.tapisTarot().getCenterDeck(), _last.total());
        csu_.seeHand(container, new TarotSortingSummedTwoHands().sorted(_curHand.getCards(), _last.getCards(), getDisplaying().getDisplaying()),container.getPanelHand(), _gt.getPreneur(),DealTarot.NUMERO_UTILISATEUR);
//        if(_player ==DealTarot.NUMERO_UTILISATEUR) {
//            afficherMainUtilisateurSimuTarot(_nextHand);
//        }
    }

//    @Override

//    @Override
    public void autoCall() {
        GameTarot g_ = partieTarotSimulee();
        HandTarot calledCards_ = g_.getCalledCards();
        if (calledCards_.estVide()){
            return;
        }
        if (g_.getDeal().derniereMain().contient(calledCards_.premiereCarte())) {
            String event_ = StringUtil.concat(container.fileSimu().getVal(MessagesGuiCards.SIMU_ALONE_TAKER),ContainerGame.RETURN_LINE);
            event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
            container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
        }
    }

//    @Override

//    @Override

    //    @Override
    public void mergedDog(byte _taker, HandTarot _nextHand) {
        display(_taker, _nextHand.getCards());
    }

    private void display(byte _player, IdList<CardTarot> _nextHand) {
        new ContainerSingUtil<CardTarot>(container.converter()).seeHand(container,_nextHand,container.getPanelHand(),_player,DealTarot.NUMERO_UTILISATEUR);
//        if(_player ==DealTarot.NUMERO_UTILISATEUR) {
//            afficherMainUtilisateurSimuTarot(_nextHand);
//        }
    }

    //    @Override
    public void declareSlam(BidTarot _bid) {
        if (!_bid.isFaireTousPlis()) {
            return;
        }
        if (_bid.getJeuChien() == PlayingDog.WITH) {
            String event_ = StringUtil.concat(container.fileSimu().getVal(MessagesGuiCards.SIMU_DECLARING_SLAM_DEMO_DISCARD),ContainerGame.RETURN_LINE);
            event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
            container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
        } else {
            String event_ = StringUtil.concat(container.fileSimu().getVal(MessagesGuiCards.SIMU_DECLARING_SLAM_DEMO),ContainerGame.RETURN_LINE);
            event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
            container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
        }
    }

//    @Override
    public void firstCardPlaying(byte _joueur) {
        StringList pseudos_=pseudosSimuleeTarot();
        String mess_ = container.fileSimu().getVal(MessagesGuiCards.SIMU_PLAY_CARD_FIRST);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_joueur)),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void nextCardPlaying(byte _joueur) {
        StringList pseudos_=pseudosSimuleeTarot();
        String mess_ = container.fileSimu().getVal(MessagesGuiCards.SIMU_PLAY_CARD_THEN);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_joueur)),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void declareHandfuls(byte _joueur, IdList<Handfuls> _annoncesPoignees, HandTarot _poignee) {
        HandTarot handful_ = getInt().handfulCard(_poignee);
        if (!handful_.estVide()) {
            TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
            StringList pseudos_=pseudosSimuleeTarot();
            String mess_ = container.fileSimu().getVal(MessagesGuiCards.SIMU_DEMO_ACTION);
            String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_,pseudos_.get(_joueur),Games.toString(getInt().handful(_annoncesPoignees).first(),lg_)),ContainerGame.RETURN_LINE);
            event_ = StringUtil.concat(event_, StringUtil.simpleStringsFormat(mess_,pseudos_.get(_joueur),Games.toString(handful_,lg_)),ContainerGame.RETURN_LINE);
            container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
        }
    }

//    @Override
    public void declareMiseres(byte _joueur, IdList<Miseres> _annoncesMiseres) {
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        StringList pseudos_=pseudosSimuleeTarot();
        for(Miseres annonce_: getInt().misere(_annoncesMiseres)) {
            String mess_ = container.fileSimu().getVal(MessagesGuiCards.SIMU_DEMO_ACTION);
            String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_joueur),Games.toString(annonce_,lg_)),ContainerGame.RETURN_LINE);
            container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
        }
    }

//    @Override
    public void displayCalled(byte _joueur) {
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        StringList pseudos_=pseudosSimuleeTarot();
        String mess_ = container.fileSimu().getVal(MessagesGuiCards.SIMU_DEMO_ACTION);
        container.getMini().setStatus(container.getWindow().getImageFactory(), Role.CALLED_PLAYER, _joueur);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_,pseudos_.get(_joueur),Games.toString(Role.CALLED_PLAYER,lg_)),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void played(byte _joueur, CardTarot _playedCard) {
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        container.tapisTarot().setCarteTarot(container.getWindow().getImageFactory(),lg_,_joueur,_playedCard);
    }

//    @Override
    public void displayUserHand(HandTarot _main) {
        new ContainerSingUtil<CardTarot>(container.converter()).seeHand(container,_main.getCards(),container.getPanelHand(),DealTarot.NUMERO_UTILISATEUR,DealTarot.NUMERO_UTILISATEUR);
//        afficherMainUtilisateurSimuTarot(_main.getCards());
    }

//    @Override
    public void displayTrickWinner(byte _trickWinner) {
        StringList pseudos_=pseudosSimuleeTarot();
        String mess_ = container.fileSimu().getVal(MessagesGuiCards.SIMU_TRICK_WINNER);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_trickWinner)),ContainerGame.RETURN_LINE);
        event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void displaySmallBound(CustList<BoolVal> _smallBound, byte _trickWinner) {
        if (_smallBound.get(_trickWinner) != BoolVal.TRUE) {
            return;
        }
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        StringList pseudos_=pseudosSimuleeTarot();
        String mess_ = container.fileSimu().getVal(MessagesGuiCards.SIMU_BONUS_WIN);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_trickWinner), Games.toString(BonusTarot.SMALL_BOUND,lg_)),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void beginPlay() {
        String event_ = StringUtil.concat(container.fileSimu().getVal(MessagesGuiCards.SIMU_BEGIN_PLAY_CARDS),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void clearCarpet(byte _nbPlayers) {
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        container.tapisTarot().setCartesTarotJeu(container.getWindow().getImageFactory(),lg_,_nbPlayers);
    }
//    private void afficherMainUtilisateurSimuTarot(IdList<CardTarot> _mainUtilisateur) {
//        container.getOwner().getFrames().getCompoFactory().invokeNow(new SimulationRefreshHand<CardTarot>(container, container.converter(), _mainUtilisateur,container.getPanelHand()));
//    }
    private StringList pseudosSimuleeTarot() {
        GameTarot partie_=partieTarotSimulee();
        return container.pseudosTarot(partie_.getNombreDeJoueurs());
    }
    public GameTarot partieTarotSimulee(){
        return gameTarot;
    }

    public StopEvent getStopEvent() {
        return stopEvent;
    }
}
