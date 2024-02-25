package cards.gui.animations;

import cards.consts.Suit;
import cards.facade.Games;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerPresident;
import cards.gui.containers.ContainerSimuPresident;
import cards.gui.dialogs.EditorCards;
import cards.gui.dialogs.FileConst;
import cards.gui.dialogs.FrameGeneralHelp;
import cards.gui.labels.GraphicPresidentCard;
import cards.gui.panels.CarpetPresident;
import cards.main.CardNatLgNamesNavigation;
import cards.president.*;
import cards.president.beans.PresidentStandards;
import cards.president.enumerations.Playing;
import code.gui.*;

import code.gui.document.RenderedPage;
import code.gui.images.MetaDimension;
import code.scripts.messages.cards.MessagesGuiCards;
import code.sml.util.TranslationsLg;
import code.threads.AbstractAtomicInteger;
import code.threads.ThreadUtil;
import code.util.ByteMap;
import code.util.Bytes;
import code.util.CustList;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;





public final class SimulatingPresidentImpl extends AbstractSimulatingPresident {
    private final GamePresident gamePresident;
    private final ContainerSimuPresident container;
    private final StopEvent stopEvent;
    private final int maxDeals;

    public SimulatingPresidentImpl(ContainerSimuPresident _container, Games _partieSimulee, DisplayingPresident _displayingPresident, StopEvent _stopEvent, IntGamePresident _ia, AbstractAtomicInteger _state) {
        super(_displayingPresident, _ia, _state);
        gamePresident = _partieSimulee.partiePresident();
        container = _container;
        stopEvent = _stopEvent;
        maxDeals = NumberUtil.min(EditorCards.MAX_DEALS, _container.getDisplayingPresident().getNbDeals());
    }

    @Override
    public HandPresident userHand(GamePresident _g) {
        prepare();
        sleepSimu(500);
        beginDemo();
        displayUserHand(_g.mainUtilisateurTriee(getDisplaying()));
//        _simu.pause();
        return new HandPresident();
    }

    @Override
    public HandPresident displayUserHand(HandPresident _hand, GamePresident _g) {
        HandPresident h_ = super.displayUserHand(_hand, _g);
        displayUserHand(h_);
        return h_;
    }

    @Override
    public byte displaySwitchedUserHand(GamePresident _g, Bytes _winners, Bytes _loosers, int _noDeal, CustList<HandPresident> _swtichedCards) {
        displaySwitchedUserHand(_winners, _loosers, _swtichedCards);
        return super.displaySwitchedUserHand(_g, _winners, _loosers, _noDeal, _swtichedCards);
    }

    @Override
    public HandPresident playedCards(GamePresident _game) {
        HandPresident h_ = super.playedCards(_game);
        beforeCards();
        sleepSimu(100);
        return h_;
    }

    private void beforeCards() {
        GamePresident gp_ = partiePresidentSimulee();
        if (gp_.getProgressingTrick().estVide()) {
            setupDeck();
            gearStatusChange(gp_.getLastStatus(), gp_.getProgressingTrick().getEntameur());
        }
    }

    @Override
    public byte addCardsToCurrentTrickAndLoop(GamePresident _game, HandPresident _hand, HandPresident _userHand) {
        byte next_ = super.addCardsToCurrentTrickAndLoop(_game, _hand, _userHand);
        displayPlayedHand(_hand);
        gearStatusChange(_game.getLastStatus(), _game.nextPlayer());
        displayPlayedHandMessage(_hand,next_);
        endCards(_userHand, _hand, next_);
        return next_;
    }

    private void endCards(HandPresident _userHand, HandPresident _h, byte _nextPlayerBk) {
        if (_nextPlayerBk == DealPresident.NUMERO_UTILISATEUR) {
            _userHand.supprimerCartes(_h);
            displayUserHand(_userHand);
        }
        GamePresident gp_ = partiePresidentSimulee();
        if (gp_.getProgressingTrick().estVide()) {
            displayTrickLeader(_nextPlayerBk);
            sleepSimu(2000);
        }
    }

    @Override
    public Bytes getNewRanks(GamePresident _g) {
        endDeal();
        sleepSimu(5000);
        return super.getNewRanks(_g);
    }

    @Override
    public int prepareNext(int _no) {
        if (_no + 1 < maxDeals) {
            prepare();
        }
        return super.prepareNext(_no);
    }

//    @Override
    public void displayUserHand(HandPresident _hand) {
        container.getOwner().getFrames().getCompoFactory().invokeNow(new SimulationRefreshHandPresident(container, new HandPresident(_hand)));
    }

//    @Override
    public void displayLooserMessage(HandPresident _h, byte _l, byte _w) {
        StringList nicknames_=pseudosSimuleePresident();
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        String event_ = StringUtil.concat(nicknames_.get(_l),ContainerGame.INTRODUCTION_PTS,Games.toString(_h,lg_),ContainerGame.RETURN_LINE,nicknames_.get(_w),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void displayWinnerMessage(HandPresident _h, byte _l, byte _w) {
        StringList nicknames_=pseudosSimuleePresident();
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        String event_ = StringUtil.concat(nicknames_.get(_w),ContainerGame.INTRODUCTION_PTS,Games.toString(_h,lg_),ContainerGame.RETURN_LINE,nicknames_.get(_l),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void displayLineReturn() {
        String event_ = ContainerGame.RETURN_LINE;
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
//    public void pause() {
//        container.pause();
//    }

//    @Override
    public void prepare() {
        container.getOwner().getFrames().getCompoFactory().invokeNow(new PrepareSimuPresident(this));
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
        MenuItemUtils.setEnabledMenu(container.getPause(),true);
        GamePresident partie_ = partiePresidentSimulee();
        RulesPresident rules_ = partie_.getRules();
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        AbsPanel contentPane_ = container.getOwner().getCompoFactory().newPageBox();
        AbsPanel container_=container.getOwner().getCompoFactory().newBorder();
        container_.add(container.getOwner().getCompoFactory().newPlainLabel(container.helpMenuTip()),GuiConstants.BORDER_LAYOUT_NORTH);
        CarpetPresident tapis_=new CarpetPresident();
        StringList pseudos_ = pseudosSimuleePresident();
        int nbMax_ = rules_.getNbStacks() * Suit.couleursOrdinaires().size();
        tapis_.initTapisPresident(lg_,pseudos_,partie_.getLastStatus(), NumberUtil.min(nbMax_, rules_.getNbMaxCardsPerPlayer()), container.getOwner().getCompoFactory());
        container.getTapis().setTapisPresident(tapis_);
        container_.add(tapis_.getContainer(),GuiConstants.BORDER_LAYOUT_CENTER);
        AbsPanel panneau_= container.getOwner().getCompoFactory().newLineBox();
        panneau_.setBackground(GuiConstants.BLUE);
        container.setPanelHand(panneau_);
        container_.add(panneau_,GuiConstants.BORDER_LAYOUT_SOUTH);
        AbsPanel panneau2_=container.getOwner().getCompoFactory().newPageBox();
        AbsTextArea evt_ = container.getEvents();
        if (evt_ == null) {
            evt_ = container.getOwner().getCompoFactory().newTextArea("",8, 30);
        } else {
            evt_ = container.getOwner().getCompoFactory().newTextArea(evt_.getText(),8, 30);
        }
        container.setEvents(evt_);
        container.getEvents().setEditable(false);
        panneau2_.add(container.getOwner().getCompoFactory().newAbsScrollPane(container.getEvents()));
        container.setHandfuls(new ByteMap<AbsPlainLabel>());
        container.setDeclaredHandfuls(new ByteMap<AbsPanel>());
        AbsPanel sousPanneau_=container.getOwner().getCompoFactory().newGrid(0,1);
        AbsPanel panelCards_ = container.getOwner().getCompoFactory().newLineBox();
        AbsPanel panelDiscard_ = container.getOwner().getCompoFactory().newLineBox();
        panelDiscard_.setBackground(GuiConstants.BLUE);
        panelCards_.add(panelDiscard_);
        container.setPanelGivenCards(panelDiscard_);
        AbsPanel panelRec_ = container.getOwner().getCompoFactory().newLineBox();
        panelRec_.setBackground(GuiConstants.BLUE);
        panelCards_.add(panelRec_);
        container.setPanelReceivedCards(panelRec_);
        sousPanneau_.add(panelCards_);
        container.setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(container.getOwner().getCompoFactory().newAbsScrollPane(sousPanneau_));
        container.setActionsHistory(panneau2_);
        container_.add(panneau2_,GuiConstants.BORDER_LAYOUT_EAST);
        contentPane_.add(container_);
        contentPane_.add(container.getWindow().getClock());
        contentPane_.add(container.getWindow().getLastSavedGameDate());
        container.setContentPane(contentPane_);
        panneau_=container.getPanneauBoutonsJeu();
        AbsButton stopButton_ = container.getOwner().getCompoFactory().newPlainButton(container.fileSimu().getVal(MessagesGuiCards.SIMU_STOP_DEMO));
        stopButton_.addActionListener(stopEvent);
        panneau_.add(stopButton_);
        HandPresident notSorted_ = partie_.getDeal().hand();
        HandPresident h_ = partie_.mainUtilisateurTriee(notSorted_, container.getDisplayingPresident());
        AbsPanel panneau1_=container.getPanelHand();
        panneau1_.removeAll();
        /*On place les cartes de l'utilisateur*/
        for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(container.getWindow(), lg_,h_.getCards())) {
            panneau1_.add(c.getPaintableLabel());
        }
        panneau1_.validate();
    }

//    @Override
    public void beginDemo() {
        String event_;
        event_ = StringUtil.concat(container.fileSimu().getVal(MessagesGuiCards.SIMU_BEGIN_DEMO),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void sleepSimu(long _millis) {
        ThreadUtil.sleep(container.getOwner().getThreadFactory(),_millis);
    }

//
//    @Override
//    public int stopped() {
//        return getState().get();
////        return getState().get() == STATE_STOPPED;
////        return container.isArretDemo();
//    }
//
//    @Override
//    public int stoppedDemo() {
//        int s_ = super.stoppedDemo();
//        if (s_ == AbstractSimulatingBelote.STATE_STOPPED) {
//            stopDemo();
//        }
//        return s_;
//    }
//

    @Override
    public int stopped() {
        return getState().get();
//        return getState().get() == STATE_STOPPED;
//        return container.isArretDemo();
    }

    @Override
    public int stoppedDemo() {
        int s_ = super.stoppedDemo();
        if (s_ == STATE_STOPPED) {
            stopDemo();
        }
        return s_;
    }
//    @Override
//    public boolean stopped() {
//        return container.isArretDemo();
//    }
//
//    @Override
    public void stopDemo() {
        container.getOwner().getFrames().getCompoFactory().invokeNow(new StopDemo(container));
    }

//    @Override
    public void displaySwitchedUserHand(Bytes _winners, Bytes _loosers, CustList<HandPresident> _switchedCards) {
        container.getOwner().getFrames().getCompoFactory().invokeNow(new DisplaySwitchedCardsSimuPresident(this,_winners,_loosers,_switchedCards));
    }

    void displayGuiSwitchedUserHand(Bytes _winners, Bytes _loosers, CustList<HandPresident> _switchedCards) {
        for (byte l: _loosers) {
            byte w_ = GamePresident.getMatchingWinner(_winners, _loosers, l);
            HandPresident h_ = _switchedCards.get(l);
            displayLooserMessage(h_,l,w_);
        }
        displayLineReturn();
        for (byte w: _winners) {
            byte l_ = GamePresident.getMatchingLoser(_winners, _loosers, w);
            HandPresident h_ = _switchedCards.get(w);
            displayWinnerMessage(h_,l_,w);
        }
        displayLineReturn();
        ContainerPresident.fetchLooser(container,partiePresidentSimulee());
        ContainerPresident.fetchWinner(container,partiePresidentSimulee());
//        if (_loosers.containsObj(DealPresident.NUMERO_UTILISATEUR)) {
//            container.getReceivedCards().supprimerCartes();
//            byte w_ = GamePresident.getMatchingWinner(_winners, _loosers, DealPresident.NUMERO_UTILISATEUR);
//            container.getReceivedCards().ajouterCartes(_switchedCards.get(w_));
//            container.updateCardsInPanelPresidentReceived();
//            container.getGivenCards().supprimerCartes();
//            container.getGivenCards().ajouterCartes(_switchedCards.get(DealPresident.NUMERO_UTILISATEUR));
//            container.updateCardsInPanelPresidentGiven();
//        }
//        if (_winners.containsObj(DealPresident.NUMERO_UTILISATEUR)) {
//            container.getReceivedCards().supprimerCartes();
//            byte l_ = GamePresident.getMatchingLoser(_winners, _loosers, DealPresident.NUMERO_UTILISATEUR);
//            container.getReceivedCards().ajouterCartes(_switchedCards.get(l_));
//            container.updateCardsInPanelPresidentReceived();
//            container.getGivenCards().supprimerCartes();
//            container.getGivenCards().ajouterCartes(_switchedCards.get(DealPresident.NUMERO_UTILISATEUR));
//            container.updateCardsInPanelPresidentGiven();
//        }
    }

//    @Override
    public void setupDeck() {
        container.getOwner().getFrames().getCompoFactory().invokeNow(new SettingPresidentDeck(container));
    }

//    @Override
    public void gearStatusChange(ByteMap<Playing> _status, byte _starter) {
        container.getOwner().getFrames().getCompoFactory().invokeNow(new SettingPresidentStatus(container, _status, _starter));
    }

//    @Override
    public void displayPlayedHand(HandPresident _hand) {
        container.getOwner().getFrames().getCompoFactory().invokeNow(new SettingPresidentHand(container, _hand));
    }

//    @Override
    public void displayPlayedHandMessage(HandPresident _hand, byte _nextPlayer) {
        StringList nicknames_=pseudosSimuleePresident();
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        String event_ = StringUtil.concat(nicknames_.get(_nextPlayer),ContainerGame.INTRODUCTION_PTS,Games.toString(_hand,lg_),ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void displayTrickLeader(byte _player) {
        StringList nicknames_=pseudosSimuleePresident();
        String mess_ = container.fileSimu().getVal(MessagesGuiCards.SIMU_TRICK_WINNER);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, nicknames_.get(_player)),ContainerGame.RETURN_LINE,ContainerGame.RETURN_LINE);
        container.getOwner().getFrames().getCompoFactory().invokeNow(new AddTextEvents(container, event_));
    }

//    @Override
    public void endDeal() {
        container.getOwner().getFrames().getCompoFactory().invokeNow(new EndDealSimuPresident(this));
    }

    void endGuiDeal() {
        container.getPane().removeAll();
        AbsPanel panneau_=container.getOwner().getCompoFactory().newPageBox();
        ResultsPresident res_ = new ResultsPresident();
        GamePresident currentGame_=partiePresidentSimulee();
        res_.setGame(currentGame_);
        StringList nicknames_=pseudosSimuleePresident();
        res_.initialize(new StringList(nicknames_), container.getScores(), currentGame_.getNewRanks());
        res_.getRes().setUser(DealPresident.NUMERO_UTILISATEUR);
        Games.setMessages(res_.getRes(),container.getOwner().getFrames().currentLg());
        RenderedPage editor_;
        CardNatLgNamesNavigation stds_ = container.retrieve(FileConst.RESOURCES_HTML_FILES_RESULTS_PRESIDENT).attendreResultat();
        ((PresidentStandards)stds_.getBeanNatLgNames()).setDataBase(res_);
        editor_ = FrameGeneralHelp.initialize(stds_, container.getWindow().getFrames());
        editor_.getScroll().setPreferredSize(new MetaDimension(300,300));
        AbsScrollPane scrollTxt_=container.getOwner().getCompoFactory().newAbsScrollPane(container.getOwner().getCompoFactory().newTextArea(container.getEvents().getText(),8, 30));
        AbsSplitPane spl_ = container.getOwner().getCompoFactory().newHorizontalSplitPane(editor_.getScroll(),scrollTxt_);
        panneau_.add(spl_);
        AbsButton stopButton_ = container.getOwner().getCompoFactory().newPlainButton(container.fileSimu().getVal(MessagesGuiCards.SIMU_STOP_DEMO));
        stopButton_.addActionListener(stopEvent);
        panneau_.add(stopButton_);
        panneau_.add(container.getOwner().getClock());
        panneau_.add(container.getOwner().getLastSavedGameDate());
        container.setContentPane(panneau_);
        container.pack();
    }

    private StringList pseudosSimuleePresident() {
        GamePresident partie_=partiePresidentSimulee();
        return container.pseudosPresident(partie_.getNombreDeJoueurs());
    }
    public GamePresident partiePresidentSimulee() {
        return gamePresident;
    }

    public StopEvent getStopEvent() {
        return stopEvent;
    }

    public int getMaxDeals() {
        return maxDeals;
    }
}
