package cards.gui.animations;

import cards.consts.Suit;
import cards.facade.Games;
import cards.gameresults.sml.DocumentReaderCardsResultsUtil;
import cards.gui.MainWindow;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerPresident;
import cards.gui.containers.ContainerSimuPresident;
import cards.gui.dialogs.FileConst;
import cards.gui.labels.GraphicPresidentCard;
import cards.gui.panels.CarpetPresident;
import cards.president.*;
import cards.president.beans.PresidentStandards;
import cards.president.enumerations.Playing;
import code.gui.*;
import code.gui.Panel;
import code.gui.ScrollPane;
import code.gui.TextArea;
import code.gui.document.RenderedPage;
import code.util.ByteMap;
import code.util.Bytes;
import code.util.CustList;
import code.util.StringList;

import javax.swing.*;
import java.awt.*;

public final class SimulatingPresidentImpl implements SimulatingPresident {
    private final ContainerSimuPresident container;
    private final Games partieSimulee;
    private final DisplayingPresident displayingPresident;
    private final LabelButton stopButton;

    public SimulatingPresidentImpl(ContainerSimuPresident _container, Games _partieSimulee, DisplayingPresident _displayingPresident, LabelButton _stopButton) {
        container = _container;
        partieSimulee = _partieSimulee;
        displayingPresident = _displayingPresident;
        stopButton = _stopButton;
    }

    @Override
    public void displayUserHand(HandPresident _hand) {
        ThreadInvoker.invokeNow(new SimulationRefreshHandPresident(container, new HandPresident(_hand)));
    }

    @Override
    public DisplayingPresident getDisplaying() {
        return displayingPresident;
    }

    @Override
    public void displayLooserMessage(HandPresident _h, byte _l, byte _w) {
        StringList nicknames_=pseudosSimuleePresident();
        String lg_ = container.getOwner().getLanguageKey();
        String event_ = StringList.concat(nicknames_.get(_l),ContainerGame.INTRODUCTION_PTS,Games.toString(_h,lg_),ContainerGame.RETURN_LINE,nicknames_.get(_w),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void displayWinnerMessage(HandPresident _h, byte _l, byte _w) {
        StringList nicknames_=pseudosSimuleePresident();
        String lg_ = container.getOwner().getLanguageKey();
        String event_ = StringList.concat(nicknames_.get(_w),ContainerGame.INTRODUCTION_PTS,Games.toString(_h,lg_),ContainerGame.RETURN_LINE,nicknames_.get(_l),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void displayLineReturn() {
        String event_ = ContainerGame.RETURN_LINE;
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void pause() {
        container.pause();
    }

    @Override
    public void prepare() {
        SwingUtilities.invokeLater(new PrepareSimuPresident(this));
    }

    void prepareGui() {
        container.setArretDemo(false);
        //desactiver le menu Partie/aide au jeu
        container.getHelpGame().setEnabledMenu(false);
        //desactiver le menu Partie/Demo
        container.getDemo().setEnabledMenu(false);
        //Activer le menu Partie/Pause
        container.getPause().setEnabledMenu(true);
        GamePresident partie_ = partiePresidentSimulee();
        RulesPresident rules_ = partie_.getRegles();
        String lg_ = container.getOwner().getLanguageKey();
        Panel contentPane_ = Panel.newPageBox();
        Panel container_=Panel.newBorder();
        container_.add(new TextLabel(container.getMessages().getVal(MainWindow.HELP_GO_MENU),SwingConstants.CENTER),BorderLayout.NORTH);
        CarpetPresident tapis_=new CarpetPresident();
        StringList pseudos_ = pseudosSimuleePresident();
        int nbMax_ = rules_.getNbStacks() * Suit.couleursOrdinaires().size();
        tapis_.initTapisPresident(lg_,pseudos_,partie_.getLastStatus(),Math.min(nbMax_, rules_.getNbMaxCardsPerPlayer()));
        container.getTapis().setTapisPresident(tapis_);
        container_.add(tapis_.getContainer(),BorderLayout.CENTER);
        Panel panneau_=Panel.newFlow(FlowLayout.LEFT,0,0);
        panneau_.setBackground(Color.BLUE);
        container.setPanelHand(panneau_);
        container_.add(panneau_,BorderLayout.SOUTH);
        Panel panneau2_=Panel.newPageBox();
        container.setEvents(new TextArea(ContainerPresident.EMPTY,8, 30));
        container.getEvents().setEditable(false);
        panneau2_.add(new ScrollPane(container.getEvents()));
        container.setHandfuls(new ByteMap<TextLabel>());
        container.setDeclaredHandfuls(new ByteMap<Panel>());
        Panel sousPanneau_=Panel.newGrid(0,1);
        Panel panelCards_ = new Panel();
        Panel panelDiscard_ = Panel.newFlow(FlowLayout.LEFT,0,0);
        panelDiscard_.setBackground(Color.BLUE);
        panelCards_.add(panelDiscard_);
        container.setPanelGivenCards(panelDiscard_);
        Panel panelRec_ = Panel.newFlow(FlowLayout.LEFT,0,0);
        panelRec_.setBackground(Color.BLUE);
        panelCards_.add(panelRec_);
        container.setPanelReceivedCards(panelRec_);
        sousPanneau_.add(panelCards_);
        container.setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(new ScrollPane(sousPanneau_));
        container.setActionsHistory(panneau2_);
        container_.add(panneau2_,BorderLayout.EAST);
        contentPane_.add(container_);
        contentPane_.add(container.getWindow().getClock());
        contentPane_.add(container.getWindow().getLastSavedGameDate());
        container.setContentPane(contentPane_);
        panneau_=container.getPanneauBoutonsJeu();
        panneau_.add(stopButton);
        HandPresident notSorted_ = partie_.getDeal().hand();
        HandPresident h_ = partie_.mainUtilisateurTriee(notSorted_, container.getDisplayingPresident());
        Panel panneau1_=container.getPanelHand();
        panneau1_.removeAll();
        /*On place les cartes de l'utilisateur*/
        for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(lg_,h_)) {
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
    public void displaySwitchedUserHand(Bytes _winners, Bytes _loosers, int _noDeal,CustList<HandPresident> _switchedCards) {
        SwingUtilities.invokeLater(new DisplaySwitchedCardsSimuPresident(this,_winners,_loosers,_switchedCards));
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
        ThreadInvoker.invokeNow(new SettingPresidentDeck(container));
    }

    @Override
    public void gearStatusChange(ByteMap<Playing> _status, byte _starter) {
        ThreadInvoker.invokeNow(new SettingPresidentStatus(container, _status, _starter));
    }

    @Override
    public void displayPlayedHand(HandPresident _hand) {
        ThreadInvoker.invokeNow(new SettingPresidentHand(container, _hand));
    }

    @Override
    public void displayPlayedHandMessage(HandPresident _hand, byte _nextPlayer) {
        StringList nicknames_=pseudosSimuleePresident();
        String lg_ = container.getOwner().getLanguageKey();
        String event_ = StringList.concat(nicknames_.get(_nextPlayer),ContainerGame.INTRODUCTION_PTS,Games.toString(_hand,lg_),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void displayTrickLeader(byte _player) {
        StringList nicknames_=pseudosSimuleePresident();
        String mess_ = container.getMessages().getVal(MainWindow.TRICK_WINNER);
        String event_ = StringList.concat(StringList.simpleStringsFormat(mess_, nicknames_.get(_player)),ContainerGame.RETURN_LINE,ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
    }

    @Override
    public void endDeal() {
        SwingUtilities.invokeLater(new EndDealSimuPresident(this));
    }

    void endGuiDeal() {
        String lg_ = container.getOwner().getLanguageKey();
        Panel panneau_=Panel.newPageBox();
        ResultsPresident res_ = new ResultsPresident();
        GamePresident currentGame_=partiePresidentSimulee();
        res_.setGame(currentGame_);
        StringList nicknames_=pseudosSimuleePresident();
        res_.initialize(new StringList(nicknames_), container.getScores(), currentGame_.getNewRanks());
        res_.setUser(DealPresident.NUMERO_UTILISATEUR);
        DocumentReaderCardsResultsUtil.setMessages(res_,lg_);
        ScrollPane scroll_=new ScrollPane();
        RenderedPage editor_ = new RenderedPage(scroll_);
        editor_.setLanguage(lg_);
        editor_.setDataBase(res_);
        editor_.initialize(FileConst.RESOURCES_HTML_FILES_RESULTS_PRESIDENT, new PresidentStandards());
        scroll_.setPreferredSize(new Dimension(300,300));
        panneau_.add(scroll_);
        panneau_.add(stopButton);
        panneau_.add(container.getOwner().getClock());
        panneau_.add(container.getOwner().getLastSavedGameDate());
        container.setContentPane(panneau_);
        container.pack();
    }

    private GamePresident partiePresidentSimulee() {
        return partieSimulee.partiePresident();
    }

    private StringList pseudosSimuleePresident() {
        GamePresident partie_=partiePresidentSimulee();
        return container.pseudosPresident(partie_.getNombreDeJoueurs());
    }
}
