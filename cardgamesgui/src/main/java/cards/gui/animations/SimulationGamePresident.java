package cards.gui.animations;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import code.gui.LabelButton;
import code.gui.ThreadInvoker;
import code.util.NumberMap;
import code.util.Numbers;
import code.util.StringList;
import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.consts.Suit;
import cards.facade.Games;
import cards.gui.MainWindow;
import cards.gui.containers.ContainerPresident;
import cards.gui.containers.ContainerSimuPresident;
import cards.gui.dialogs.FileConst;
import cards.gui.labels.GraphicPresidentCard;
import cards.gui.panels.CarpetPresident;
import cards.president.DealPresident;
import cards.president.GamePresident;
import cards.president.HandPresident;
import cards.president.RulesPresident;

/**Thread safe class*/
public final class SimulationGamePresident extends Thread implements SimulationGame {

    private Games partieSimulee = new Games();
    private ContainerSimuPresident container;
    private LabelButton stopButton;
    /**This class thread is independant from EDT*/
    public SimulationGamePresident(ContainerSimuPresident _container) {
        container = _container;
        GamePresident.setChargementSimulation(0);
        RulesPresident regles_ = container.getWindow().getReglesPresident();
        HandPresident pile_=HandPresident.stack(regles_.getNbStacks());
        DealPresident donne_=new DealPresident(0l,pile_);
        donne_.setRandomDealer(regles_);
        regles_.setMixedCards(MixCardsChoice.EACH_DEAL);
        donne_.initDonne(regles_);
        partieSimulee.jouerPresident(new GamePresident(GameType.EDIT,donne_,regles_, new Numbers<Byte>()));
//        partieSimulee.sauvegarderPartieEnCours("demos/deal10.cdgame");
        stopButton=new LabelButton(container.getMessages().getVal(MainWindow.STOP_DEMO));
        stopButton.addMouseListener(new StopEvent(this));
    }

    @Override
    public void stopSimulation() {
        container.setArretDemo(true);
        container.getOwner().menuSoloGames();
    }

    private GamePresident partiePresidentSimulee() {
        return partieSimulee.partiePresident();
    }
    /**Pseudos utilis_&eacute_;s*/
//    private List<String> pseudosSimuleePresident() {
//        List<String> pseudos_=new List<String>();
//        GamePresident partie_=partiePresidentSimulee();
//        partie_.getNombreDeJoueurs();
//        pseudos_.add(container.pseudo());
//        pseudos_.addAll(container.getPseudosJoueurs().getPseudosPresident());
//        return pseudos_;
//    }

    private StringList pseudosSimuleePresident() {
//        List<String> pseudos_=new List<String>();
        GamePresident partie_=partiePresidentSimulee();
//        partie_.getNombreDeJoueurs();
//        pseudos_.add(container.pseudo());
//        pseudos_.addAll(container.getPseudosJoueurs().getPseudosPresident());
        return container.pseudosPresident(partie_.getNombreDeJoueurs());
    }

    @Override
    public void run() {
        ThreadInvoker.invokeNow(new SettingSimulationComponent(this));
        SwingUtilities.invokeLater(new GoSimulateGame(new GoSimulatePresident(partieSimulee, container, stopButton), container));
    }

    private void afficherMainUtilisateurSimuPresident(HandPresident _mainUtilisateur) {
        JPanel panneau1_=container.getPanelHand();
        panneau1_.removeAll();
        /*On place les cartes de l'utilisateur*/
        for (GraphicPresidentCard c: ContainerPresident.getGraphicCards(_mainUtilisateur)) {
            panneau1_.add(c);
        }
        panneau1_.repaint();
        panneau1_.revalidate();
    }

    @Override
    public void setSimulationGui() {
        container.setArretDemo(false);
        //desactiver le menu Partie/aide au jeu
        container.getHelpGame().setEnabled(false);
        //desactiver le menu Partie/Demo
        container.getDemo().setEnabled(false);
        //Activer le menu Partie/Pause
        container.getPause().setEnabled(true);
        GamePresident partie_ = partiePresidentSimulee();
        RulesPresident rules_ = partie_.getRegles();
        int maxDeals_ = Math.min(FileConst.MAX_DEALS, container.getDisplayingPresident().getNbDeals());
        partie_.simulate(maxDeals_);
        JPanel contentPane_ = new JPanel();
        contentPane_.setLayout(new BoxLayout(contentPane_, BoxLayout.PAGE_AXIS));
        Container container_=new Container();
        container_.setLayout(new BorderLayout());
        container_.add(new JLabel(container.getMessages().getVal(MainWindow.HELP_GO_MENU),SwingConstants.CENTER),BorderLayout.NORTH);
        CarpetPresident tapis_=new CarpetPresident();
        StringList pseudos_ = pseudosSimuleePresident();
        int nbMax_ = rules_.getNbStacks() * Suit.couleursOrdinaires().size();
        tapis_.initTapisPresident(pseudos_,partie_.getLastStatusDeals().first().first().getVal(-1),Math.min(nbMax_, rules_.getNbMaxCardsPerPlayer()));
        container.getTapis().setTapisPresident(tapis_);
        container_.add(tapis_,BorderLayout.CENTER);
        JPanel panneau_=new JPanel();
        panneau_.setBackground(Color.BLUE);
        panneau_.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        container.setPanelHand(panneau_);
        container_.add(panneau_,BorderLayout.SOUTH);
        JPanel panneau2_=new JPanel();
        panneau2_.setLayout(new BoxLayout(panneau2_, BoxLayout.PAGE_AXIS));
        container.setEvents(new JTextArea(ContainerPresident.EMPTY,8, 30));
        container.getEvents().setEditable(false);
        panneau2_.add(new JScrollPane(container.getEvents()));
        container.setHandfuls(new NumberMap<Byte,JLabel>());
        container.setDeclaredHandfuls(new NumberMap<Byte,JPanel>());
        JPanel sousPanneau_=new JPanel(new GridLayout(0,1));
        JPanel panelCards_ = new JPanel();
        JPanel panelDiscard_ = new JPanel();
        panelDiscard_.setBackground(Color.BLUE);
        panelDiscard_.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        panelCards_.add(panelDiscard_);
        container.setPanelGivenCards(panelDiscard_);
        JPanel panelRec_ = new JPanel();
        panelRec_.setBackground(Color.BLUE);
        panelRec_.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        panelCards_.add(panelRec_);
        container.setPanelReceivedCards(panelRec_);
        sousPanneau_.add(panelCards_);
        container.setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(new JScrollPane(sousPanneau_));
        container.setActionsHistory(panneau2_);
        container_.add(panneau2_,BorderLayout.EAST);
        contentPane_.add(container_);
        contentPane_.add(container.getWindow().getClock());
        contentPane_.add(container.getWindow().getLastSavedGameDate());
        container.setContentPane(new JScrollPane(contentPane_));
        panneau_=container.getPanneauBoutonsJeu();
        panneau_.add(stopButton);
        HandPresident notSorted_ = partie_.getUserHands().first().first().first();
        HandPresident h_ = partie_.mainUtilisateurTriee(notSorted_, container.getDisplayingPresident());
        afficherMainUtilisateurSimuPresident(h_);
    }

}
