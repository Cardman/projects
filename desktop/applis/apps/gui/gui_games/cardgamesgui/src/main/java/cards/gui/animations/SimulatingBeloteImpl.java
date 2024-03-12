package cards.gui.animations;

import cards.belote.*;
import cards.belote.beans.BeloteStandards;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.facade.Games;
import cards.gui.containers.*;
import cards.gui.dialogs.FileConst;
import cards.gui.dialogs.FrameGeneralHelp;
import cards.gui.labels.BeloteCardConverter;
import cards.gui.panels.CarpetBelote;
import cards.gui.panels.MiniCarpet;
import cards.gui.panels.PanelTricksHandsBelote;
import cards.main.CardNatLgNamesNavigation;
import code.gui.*;

import code.gui.document.RenderedPage;
import code.gui.images.MetaDimension;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.threads.AbstractAtomicInteger;
import code.util.Bytes;
import code.util.IdList;
import code.util.StringList;
import code.util.core.StringUtil;





public final class SimulatingBeloteImpl extends AbstractSimulatingBelote {
    private final GameBelote gameBelote;
    private final ContainerSimuBelote container;
    private final StopEvent stopEvent;

    public SimulatingBeloteImpl(ContainerSimuBelote _container, Games _partieSimulee,
                                DisplayingBelote _displayingBelote, StopEvent _stopEvent, IntGameBelote _ia, AbstractAtomicInteger _s) {
        super(_displayingBelote, _ia, _s);
        gameBelote = _partieSimulee.partieBelote();
        container = _container;
        stopEvent = _stopEvent;
    }

    @Override
    public Bytes players(GameBelote _g) {
        prepare();
        container.sleepThread(500);
//        beginDemo();
        String event_;
        event_ = StringUtil.concat(container.fileSimu().getVal(MessagesGuiCards.SIMU_BEGIN_DEMO),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
        displayUserHand(_g.mainUtilisateurTriee(getDisplaying()));
        return super.players(_g);
    }

    @Override
    public void bid(GameBelote _g) {
        byte p_ = _g.playerHavingToBid();
        actingBid(p_);
        container.sleepThread(500);
        BidBeloteSuit contratTmp_ = getInt().strategieContrat(_g);
        actedBid(p_,contratTmp_);
        _g.ajouterContrat(contratTmp_);
    }

    @Override
    public boolean keepBidding(GameBelote _g) {
        boolean k_ = super.keepBidding(_g);
        if (k_) {
            byte nbPl_ = _g.getNombreDeJoueurs();
            secRound(nbPl_);
        }
        return k_;
    }

    @Override
    public int stoppedRound(int _nbBids, byte _nbPlayers) {
        nextRound(_nbBids,_nbPlayers);
        return stopped();
//        return stoppedDemo();
//        boolean stopped_ = stopped();
//        if (stopped_) {
//            stopDemo();
//        }
//        return stopped_;
    }

    @Override
    public boolean noBid(GameBelote _g) {
        boolean n_ = super.noBid(_g);
        if (n_) {
            noBid();
        }
        return n_;
    }

    @Override
    public int dealCardsStep(byte _donneur) {
        dealCards(_donneur);
        return 1;
    }

    @Override
    public int dealCardStep(int _step, int _gotCards, byte _p) {
        dealCard(_step, _gotCards, _p);
        return _step + 1;
    }

    @Override
    public void ecarter(GameBelote _gt) {
        HandBelote last_ = _gt.getDeal().derniereMain();
        HandBelote curHand_ = _gt.mainUtilisateurTriee(getDisplaying());
        merge(_gt, last_, curHand_);
        _gt.ecarter(getInt());
        HandBelote nextHand_ = _gt.mainUtilisateurTriee(getDisplaying());
        new ContainerSingUtil<CardBelote>(container.converter()).seeHand(container,nextHand_.getCards(),container.getPanelHand(),_gt.getPreneur(),DealBelote.NUMERO_UTILISATEUR);
        declareSlam(_gt.getBid());
    }
    public void declareSlam(BidBeloteSuit _bid) {
        if (_bid.getPoints() != RulesBelote.MOST) {
            return;
        }
        String event_ = StringUtil.concat(container.fileSimu().getVal(MessagesGuiCards.SIMU_DECLARING_SLAM_DEMO_DISCARD),ContainerGame.RETURN_LINE);
        event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }
    private void merge(GameBelote _gt, HandBelote _last, HandBelote _curHand) {
        ContainerSingUtil<CardBelote> csu_ = new ContainerSingUtil<CardBelote>(container.converter());
        csu_.seeDog(container, _last.getCards(), container.tapisBelote().getCenterDeck());
        csu_.seeHandDog(container, _curHand.getCards(),container.getPanelHand(),_gt.getPreneur(),DealBelote.NUMERO_UTILISATEUR);
        csu_.mergeDog(container,container.tapisBelote().getCenterDeck(), _last.total());
        csu_.seeHand(container, new BeloteSortingSummedTwoHands(_gt.getBid()).sorted(_curHand.getCards(), _last.getCards(), getDisplaying().getDisplaying()),container.getPanelHand(), _gt.getPreneur(),DealBelote.NUMERO_UTILISATEUR);
    }

    @Override
    public int completerDonne(GameBelote _g) {
        int starter_ = super.completerDonne(_g);
        if (_g.changeFirstLeader()) {
            declareSlam(_g.getPreneur(), _g.getBid());
        }
        displayUserHand(_g.mainUtilisateurTriee(getDisplaying()));
        container.sleepThread(1000);
        displayLineReturn();
        beginPlay();
        return starter_;
    }

    @Override
    public CardBelote play(GameBelote _g) {
        byte joueur_ = _g.playerHavingToPlay();
        if (_g.pliEnCoursEstVide()) {
            firstCardPlaying(joueur_);
        } else {
            nextCardPlaying(joueur_);
        }
        container.sleepThread(1000);
        CardBelote p_ = super.play(_g);
        if (_g.premierTour()) {
            declare(joueur_, _g.getAnnonce(joueur_));
        }
        belReb(_g.cartesBeloteRebelote(), p_, joueur_);
        played(joueur_, p_);
        if(joueur_ ==DealBelote.NUMERO_UTILISATEUR) {
            displayUserHand(_g.mainUtilisateurTriee(getDisplaying()));
        }

        return p_;
    }

    @Override
    public int ajouterDixDeDerPliEnCours(GameBelote _g) {
        int next_ = super.ajouterDixDeDerPliEnCours(_g);
        displayTrickWinner((byte) next_);
        container.sleepThread(4000);
//            _simu.pause();
        clearCarpet(_g.getNombreDeJoueurs());
        return next_;
    }

//    @Override
    public void displayLineReturn() {
        String event_ = ContainerGame.RETURN_LINE;
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }


//    @Override
    public void actingBid(byte _player) {
        StringList pseudos_=pseudosSimuleeBelote();
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(container.fileSimu().getVal(MessagesGuiCards.SIMU_DECLARE_BID), pseudos_.get(_player)),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void actedBid(byte _player, BidBeloteSuit _bid) {
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        StringList pseudos_=pseudosSimuleeBelote();
        String mess_ = container.fileSimu().getVal(MessagesGuiCards.SIMU_DEMO_ACTION);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_player), Games.toString(_bid,lg_)),ContainerGame.RETURN_LINE);
        event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void nextRound(int _nbBids, byte _nbPlayers) {
        if (_nbBids % _nbPlayers != 0) {
            return;
        }
        String event_ = StringUtil.concat(Long.toString(_nbBids),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void secRound(byte _nbPlayers) {
        String event_ = StringUtil.concat(Long.toString(_nbPlayers),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void noBid() {
        String event_ = StringUtil.concat(container.fileSimu().getVal(MessagesGuiCards.SIMU_NO_BID),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
        container.revalidate();
    }

//    @Override
//    public void pause() {
//        container.pause();
//    }

//    @Override
    public void prepare() {
        container.getOwner().getFrames().getCompoFactory().invokeNow(new PrepareSimuBelote(this));
//        GuiBaseUtil.invokeLater(new PrepareSimuBelote(this), container.getOwner().getFrames());
    }
    void prepareGui() {
        container.getPane().removeAll();
//        getState().set(STATE_ALIVE);
//        container.setArretDemo(false);
        //desactiver le menu Partie/aide au jeu
        MenuItemUtils.setEnabledMenu(container.getHelpGame(),false);
        //desactiver le menu Partie/Demo
//        MenuItemUtils.setEnabledMenu(container.getDemo(),false);
        container.window().changeMenuSimuEnabled(false);
        //Activer le menu Partie/Pause
//        MenuItemUtils.setEnabledMenu(container.getPause(),true);
        GameBelote partie_=partieBeloteSimulee();
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        AbsPanel contentPane_ = container.getOwner().getCompoFactory().newPageBox();
        AbsPanel container_=container.getOwner().getCompoFactory().newBorder();
        container_.add(container.getOwner().getCompoFactory().newPlainLabel(container.helpMenuTip()),GuiConstants.BORDER_LAYOUT_NORTH);
        StringList pseudos_ = pseudosSimuleeBelote();
        CarpetBelote tapis_ = CarpetBelote.initTapisBelote(lg_, partie_.getNombreDeJoueurs(), container.getDisplayingBelote().getDisplaying().isClockwise(), ContainerSingleBelote.displayedCards(partie_.getRegles()), container.getWindow().getFrames());
        container.getTapis().setTapisBelote(tapis_);
        container_.add(tapis_.getContainer(),GuiConstants.BORDER_LAYOUT_CENTER);
//        AbsPanel panneau_= container.getOwner().getCompoFactory().newLineBox();
//        panneau_.setBackground(GuiConstants.BLUE);
//        container.setPanelHand(panneau_);
//        container_.add(panneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
        AbsPanel panneau2_=container.getOwner().getCompoFactory().newPageBox();
        container.setEvents(container.getOwner().getCompoFactory().newTextArea(ContainerBelote.EMPTY,8, 30));
        container.getEvents().setEditable(false);
        panneau2_.add(container.getOwner().getCompoFactory().newAbsScrollPane(container.getEvents()));
        container.setMini(MiniCarpet.newCarpet(container.getWindow().getImageFactory(),partie_.getNombreDeJoueurs(), container.getDisplayingBelote().getDisplaying().isClockwise(),pseudos_, container.getWindow().getCompoFactory()));
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
        AbsScrollPane scroll_ = container.getOwner().getCompoFactory().newAbsScrollPane(container.buildDeclHands(nbPlayers_,pseudos_));
        panneau2_.add(scroll_);
        AbsPanel sousPanneau_=container.getOwner().getCompoFactory().newGrid(0,1);
        container.setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(sousPanneau_);
        container_.add(panneau2_,GuiConstants.BORDER_LAYOUT_EAST);
        container.tapisBelote().setTalonBelote(container.getWindow(),lg_,partie_.getDistribution().derniereMain(), partie_.getRules());
        contentPane_.add(container_);
        contentPane_.add(container.getWindow().getClock().getComponent());
        contentPane_.add(container.getWindow().getLastSavedGameDate());
        container.setContentPane(contentPane_);
        AbsPanel panneau_=container.getPanneauBoutonsJeu();
//        AbsButton stopButton_ = container.getOwner().getCompoFactory().newPlainButton(container.fileSimu().getVal(MessagesGuiCards.SIMU_STOP_DEMO));
//        stopButton_.addActionListener(stopEvent);
//        panneau_.add(stopButton_);
        panneau_.add(ContainerSingleImpl.stopButton(container,stopEvent));
        AbsPanel panneau1_=new ContainerSingUtil<CardBelote>(new BeloteCardConverter()).getGraphicCardsGenePanel(container.getWindow(),partie_.getDeal().hand().getCards());
//        panneau1_.setBackground(GuiConstants.BLUE);
//        panneau1_.validate();
        container.panelHand(panneau1_);
//        container.setPanelHand(panneau1_);
        container_.add(panneau1_,GuiConstants.BORDER_LAYOUT_SOUTH);
//        AbsPanel panneau1_=container.getPanelHand();
//        panneau1_.removeAll();
//        /*On place les cartes de l'utilisateur*/
//        for (GraphicCard<CardBelote> c: ContainerBelote.getGraphicCards(container.getWindow(), lg_,partie_.getDeal().hand().getCards())) {
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
//        ThreadUtil.sleep(container.getOwner().getThreadFactory(),_millis);
//    }

    @Override
    public int stopped() {
        int s_ = getState().get();
        ContainerSingleImpl.afterStopped(container,s_,STATE_STOPPED);
//        if (s_ == STATE_STOPPED) {
//            stopDemo();
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
////        if (s_ == STATE_STOPPED) {
////            stopDemo();
////            container.getOwner().getFrames().getCompoFactory().invokeNow(new StopDemo(container));
////        }
//        return s_;
//    }

//    @Override
//    public void stopDemo() {
//        container.getOwner().getFrames().getCompoFactory().invokeNow(new StopDemo(container));
////        GuiBaseUtil.invokeLater(new StopDemo(container), container.getOwner().getFrames());
//    }

//    @Override
//    public void endDeal() {
//        container.getOwner().getFrames().getCompoFactory().invokeNow(new EndDealSimuBelote(this));
////        GuiBaseUtil.invokeLater(new EndDealSimuBelote(this), container.getOwner().getFrames());
//    }

    void endGuiDeal() {
        container.getPane().removeAll();
        AbsPanel panneau_=container.getOwner().getCompoFactory().newPageBox();
        ResultsBelote res_ = new ResultsBelote();
        GameBelote currentGame_=partieBeloteSimulee();
        res_.setGame(currentGame_);
        StringList nicknames_=pseudosSimuleeBelote();
        res_.getRes().setUser(DealBelote.NUMERO_UTILISATEUR);
        res_.initialize(new StringList(nicknames_), container.getScores());
        Games.setMessages(res_.getRes(),container.getOwner().getFrames().currentLg());
        RenderedPage editor_;
        res_.getRes().setGeneral(container.readCoreResourceSuit());
        res_.getRes().setSpecific(container.readResource());
        CardNatLgNamesNavigation stds_ = container.retrieve(FileConst.RESOURCES_HTML_FILES_RESULTS_BELOTE).attendreResultat();
        ((BeloteStandards)stds_.getBeanNatLgNames()).setDataBase(res_);
        editor_ = FrameGeneralHelp.initialize(stds_, container.getWindow().getFrames());
        editor_.getScroll().setPreferredSize(new MetaDimension(300,300));
        panneau_.add(container.getOwner().getCompoFactory().newHorizontalSplitPane(editor_.getScroll(),container.getOwner().getCompoFactory().newAbsScrollPane(container.getOwner().getCompoFactory().newTextArea(container.getEvents().getText(),8, 30))));
//        AbsButton stopButton_ = container.getOwner().getCompoFactory().newPlainButton(container.fileSimu().getVal(MessagesGuiCards.SIMU_STOP_DEMO));
//        stopButton_.addActionListener(stopEvent);
//        panneau_.add(stopButton_);
        panneau_.add(ContainerSingleImpl.stopButton(container,stopEvent));
        panneau_.add(container.getOwner().getClock());
        panneau_.add(container.getOwner().getLastSavedGameDate());
        TricksHandsBelote tricksHands_ = new TricksHandsBelote();
        tricksHands_.tricks(currentGame_);
        tricksHands_.sortHands(getDisplaying(),currentGame_.getRules());
        panneau_.add(new PanelTricksHandsBelote(container.getOwner().getCommonFrame(), tricksHands_, currentGame_.getRules(), pseudosSimuleeBelote(), getDisplaying(), container.getOwner()).getContainer());
        container.setContentPane(panneau_);
        container.pack();
    }
//    @Override
    public void declareSlam(byte _taker, BidBeloteSuit _bid) {
        StringList pseudos_=pseudosSimuleeBelote();
        String mess_ = container.fileSimu().getVal(MessagesGuiCards.SIMU_DECLARING_SLAM);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_taker)),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, StringUtil.concat(event_,ContainerGame.RETURN_LINE,Games.toString(_bid, container.getOwner().getFrames().currentLg()))));
    }

//    @Override
    public void firstCardPlaying(byte _joueur) {
        StringList pseudos_=pseudosSimuleeBelote();
        String mess_ = container.fileSimu().getVal(MessagesGuiCards.SIMU_PLAY_CARD_FIRST);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_joueur)),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void nextCardPlaying(byte _joueur) {
        StringList pseudos_=pseudosSimuleeBelote();
        String mess_ = container.fileSimu().getVal(MessagesGuiCards.SIMU_PLAY_CARD_THEN);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_joueur)),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void belReb(HandBelote _hand, CardBelote _playedCard,byte _joueur) {
        if(_hand.contient(_playedCard)) {
            TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
            StringList pseudos_=pseudosSimuleeBelote();
            String mess_ = container.fileSimu().getVal(MessagesGuiCards.SIMU_DEMO_ACTION);
            String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_joueur),Games.toStringBeloteReb(lg_)),ContainerGame.RETURN_LINE);
            container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
        }
    }

//    @Override
    public void declare(byte _joueur, DeclareHandBelote _annonces) {
        if (_annonces.getDeclare() != DeclaresBelote.UNDEFINED) {
            TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
            StringList pseudos_=pseudosSimuleeBelote();
            String mess_ = container.fileSimu().getVal(MessagesGuiCards.SIMU_DEMO_ACTION_TWO);
            String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_joueur),
                    Games.toString(_annonces.getDeclare(),lg_), Games.toString(_annonces.getHand(),lg_)),
                    ContainerGame.RETURN_LINE);
            container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
        }
    }

//    @Override
    public void played(byte _joueur, CardBelote _playedCard) {
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        container.tapisBelote().setCarteBelote(container.getWindow().getImageFactory(), lg_,_joueur,_playedCard);
    }

//    @Override
    public void displayUserHand(HandBelote _main) {
        afficherMainUtilisateurSimuBelote(_main.getCards());
    }

//    @Override
    public void displayTrickWinner(byte _trickWinner) {
        StringList pseudos_=pseudosSimuleeBelote();
        String mess_ = container.fileSimu().getVal(MessagesGuiCards.SIMU_TRICK_WINNER);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_trickWinner)),ContainerGame.RETURN_LINE);
        event_ = StringUtil.concat(event_,ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void displayLastTrick(byte _trickWinner) {
        if (partieBeloteSimulee().getTricks().isEmpty()) {
            return;
        }
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        StringList pseudos_=pseudosSimuleeBelote();
        String mess_ = container.fileSimu().getVal(MessagesGuiCards.SIMU_BONUS_WIN);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, pseudos_.get(_trickWinner), Games.toStringBonusBelote(lg_)),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void clearCarpet(byte _nbPlayers) {
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        container.tapisBelote().setCartesBeloteJeu(container.getOwner().getImageFactory(), _nbPlayers, lg_);
    }

//    @Override
    public void beginPlay() {
        String event_ = StringUtil.concat(container.fileSimu().getVal(MessagesGuiCards.SIMU_BEGIN_PLAY_CARDS),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void dealCards(byte _donneur) {
        StringList pseudos_=pseudosSimuleeBelote();
        String event_ = container.fileSimu().getVal(MessagesGuiCards.SIMU_TAKE_TOP_CARD);
        String mess_ = container.fileSimu().getVal(MessagesGuiCards.SIMU_DEAL_REMAIN_CARDS);
        event_ = StringUtil.concat(event_, StringUtil.simpleStringsFormat(mess_, pseudos_.get(_donneur)),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void dealCard(int _step, int _gotCards, byte _p) {
        StringList pseudos_=pseudosSimuleeBelote();
        String mess_ = container.fileSimu().getVal(MessagesGuiCards.SIMU_DEAL_SET_CARDS);
        String event_ = StringUtil.concat(ContainerBelote.TAB, StringUtil.simpleStringsFormat(mess_, Long.toString(_step), Long.toString(_gotCards), pseudos_.get(_p)));
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

    /**Pseudos utilis_&eacute_;s*/
    private StringList pseudosSimuleeBelote() {
        GameBelote partie_=partieBeloteSimulee();
        return container.pseudosBelote(partie_.getNombreDeJoueurs());
    }
    private void afficherMainUtilisateurSimuBelote(IdList<CardBelote> _mainUtilisateur) {
        container.getOwner().getFrames().getCompoFactory().invokeNow(new SimulationRefreshHand<CardBelote>(container,container.converter(), _mainUtilisateur,container.getPanelHand()));
    }

    public StopEvent getStopEvent() {
        return stopEvent;
    }

    public GameBelote partieBeloteSimulee() {
        return gameBelote;
    }
}
