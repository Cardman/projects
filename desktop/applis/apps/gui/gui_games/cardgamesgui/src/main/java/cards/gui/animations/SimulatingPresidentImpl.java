package cards.gui.animations;

import cards.consts.Suit;
import cards.facade.Games;
import cards.gui.WindowCards;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerPresident;
import cards.gui.containers.ContainerSimuPresident;
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
import code.sml.util.TranslationsLg;
import code.threads.ThreadUtil;
import code.util.ByteMap;
import code.util.Bytes;
import code.util.CustList;
import code.util.StringList;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;





public final class SimulatingPresidentImpl extends AbstractSimulatingPresident {
    private final ContainerSimuPresident container;
    private final StopEvent stopEvent;

    public SimulatingPresidentImpl(ContainerSimuPresident _container, Games _partieSimulee, DisplayingPresident _displayingPresident, StopEvent _stopEvent) {
        super(_displayingPresident, _partieSimulee.partiePresident());
        container = _container;
        stopEvent = _stopEvent;
    }

    @Override
    public void displayUserHand(HandPresident _hand) {
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new SimulationRefreshHandPresident(container, new HandPresident(_hand)), container.getOwner().getFrames());
    }

    @Override
    public void displayLooserMessage(HandPresident _h, byte _l, byte _w) {
        StringList nicknames_=pseudosSimuleePresident();
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        String event_ = StringUtil.concat(nicknames_.get(_l),ContainerGame.INTRODUCTION_PTS,Games.toString(_h,lg_),ContainerGame.RETURN_LINE,nicknames_.get(_w),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
    }

    @Override
    public void displayWinnerMessage(HandPresident _h, byte _l, byte _w) {
        StringList nicknames_=pseudosSimuleePresident();
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        String event_ = StringUtil.concat(nicknames_.get(_w),ContainerGame.INTRODUCTION_PTS,Games.toString(_h,lg_),ContainerGame.RETURN_LINE,nicknames_.get(_l),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
    }

    @Override
    public void displayLineReturn() {
        String event_ = ContainerGame.RETURN_LINE;
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
    }

//    @Override
//    public void pause() {
//        container.pause();
//    }

    @Override
    public void prepare() {
        GuiBaseUtil.invokeLater(new PrepareSimuPresident(this), container.getOwner().getFrames());
    }

    void prepareGui() {
        container.setArretDemo(false);
        //desactiver le menu Partie/aide au jeu
        MenuItemUtils.setEnabledMenu(container.getHelpGame(),false);
        //desactiver le menu Partie/Demo
        MenuItemUtils.setEnabledMenu(container.getDemo(),false);
        //Activer le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(container.getPause(),true);
        GamePresident partie_ = partiePresidentSimulee();
        RulesPresident rules_ = partie_.getRules();
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        AbsPanel contentPane_ = container.getOwner().getCompoFactory().newPageBox();
        AbsPanel container_=container.getOwner().getCompoFactory().newBorder();
        container_.add(container.getOwner().getCompoFactory().newPlainLabel(container.getMessages().getVal(WindowCards.HELP_GO_MENU)),GuiConstants.BORDER_LAYOUT_NORTH);
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
        String text_ = container.getEvents().getText();
        container.setEvents(container.getOwner().getCompoFactory().newTextArea(text_,8, 30));
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
        AbsButton stopButton_ = container.getOwner().getCompoFactory().newPlainButton(container.getMessages().getVal(WindowCards.STOP_DEMO));
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
        GuiBaseUtil.invokeLater(new StopDemo(container), container.getOwner().getFrames());
    }

    @Override
    public void displaySwitchedUserHand(Bytes _winners, Bytes _loosers, int _noDeal,CustList<HandPresident> _switchedCards) {
        GuiBaseUtil.invokeLater(new DisplaySwitchedCardsSimuPresident(this,_winners,_loosers,_switchedCards), container.getOwner().getFrames());
    }

    void displayGuiSwitchedUserHand(Bytes _winners, Bytes _loosers, CustList<HandPresident> _switchedCards) {
        if (_loosers.containsObj(DealPresident.NUMERO_UTILISATEUR)) {
            container.getReceivedCards().supprimerCartes();
            byte w_ = GamePresident.getMatchingWinner(_winners, _loosers, DealPresident.NUMERO_UTILISATEUR);
            container.getReceivedCards().ajouterCartes(_switchedCards.get(w_));
            container.updateCardsInPanelPresidentReceived();
            container.getGivenCards().supprimerCartes();
            container.getGivenCards().ajouterCartes(_switchedCards.get(DealPresident.NUMERO_UTILISATEUR));
            container.updateCardsInPanelPresidentGiven();
        }
        if (_winners.containsObj(DealPresident.NUMERO_UTILISATEUR)) {
            container.getReceivedCards().supprimerCartes();
            byte l_ = GamePresident.getMatchingLoser(_winners, _loosers, DealPresident.NUMERO_UTILISATEUR);
            container.getReceivedCards().ajouterCartes(_switchedCards.get(l_));
            container.updateCardsInPanelPresidentReceived();
            container.getGivenCards().supprimerCartes();
            container.getGivenCards().ajouterCartes(_switchedCards.get(DealPresident.NUMERO_UTILISATEUR));
            container.updateCardsInPanelPresidentGiven();
        }
    }

    @Override
    public void setupDeck() {
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new SettingPresidentDeck(container), container.getOwner().getFrames());
    }

    @Override
    public void gearStatusChange(ByteMap<Playing> _status, byte _starter) {
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new SettingPresidentStatus(container, _status, _starter), container.getOwner().getFrames());
    }

    @Override
    public void displayPlayedHand(HandPresident _hand) {
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new SettingPresidentHand(container, _hand), container.getOwner().getFrames());
    }

    @Override
    public void displayPlayedHandMessage(HandPresident _hand, byte _nextPlayer) {
        StringList nicknames_=pseudosSimuleePresident();
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        String event_ = StringUtil.concat(nicknames_.get(_nextPlayer),ContainerGame.INTRODUCTION_PTS,Games.toString(_hand,lg_),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
    }

    @Override
    public void displayTrickLeader(byte _player) {
        StringList nicknames_=pseudosSimuleePresident();
        String mess_ = container.getMessages().getVal(WindowCards.TRICK_WINNER);
        String event_ = StringUtil.concat(StringUtil.simpleStringsFormat(mess_, nicknames_.get(_player)),ContainerGame.RETURN_LINE,ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new AddTextEvents(container, event_), container.getOwner().getFrames());
    }

    @Override
    public void endDeal() {
        GuiBaseUtil.invokeLater(new EndDealSimuPresident(this), container.getOwner().getFrames());
    }

    void endGuiDeal() {
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
        AbsButton stopButton_ = container.getOwner().getCompoFactory().newPlainButton(container.getMessages().getVal(WindowCards.STOP_DEMO));
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
}
