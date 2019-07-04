package cards.gui.animations;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import cards.consts.GameType;
import cards.consts.MixCardsChoice;
import cards.facade.Games;
import cards.gui.MainWindow;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSimuTarot;
import cards.gui.containers.ContainerTarot;
import cards.gui.labels.GraphicTarotCard;
import cards.gui.panels.CarpetTarot;
import cards.gui.panels.MiniCarpet;
import cards.tarot.DealTarot;
import cards.tarot.GameTarot;
import cards.tarot.HandTarot;
import cards.tarot.RulesTarot;
import cards.tarot.TrickTarot;
import cards.tarot.enumerations.PlayingDog;
import code.gui.LabelButton;
import code.gui.Panel;
import code.gui.ScrollPane;
import code.gui.ThreadInvoker;
import code.util.CustList;
import code.util.EqList;
import code.util.*;
import code.util.StringList;

/**This class thread is independant from EDT,
Thread safe class*/
public final class SimulationGameTarot extends Thread implements SimulationGame {

    private Games partieSimulee = new Games();

    private ContainerSimuTarot container;

    private LabelButton stopButton;

    /**This class thread is independant from EDT*/
    public SimulationGameTarot(ContainerSimuTarot _container) {
        container = _container;
        HandTarot pile_=HandTarot.pileBase();
        DealTarot donne_=new DealTarot(0l,pile_);
        RulesTarot regles_ = container.getWindow().getReglesTarot();
        donne_.setRandomDealer(regles_);
        regles_.setCartesBattues(MixCardsChoice.EACH_DEAL);
        donne_.initDonne(regles_);
        GameTarot gt_ = new GameTarot(GameType.EDIT,donne_,regles_);
        partieSimulee.jouerTarot(gt_);
        stopButton=new LabelButton(container.getMessages().getVal(MainWindow.STOP_DEMO));
        stopButton.addMouseListener(new StopEvent(this));
    }
    @Override
    public Games getGames() {
        return partieSimulee;
    }
    @Override
    public void stopSimulation() {
        container.setArretDemo(true);
        container.getOwner().menuSoloGames();
    }
    private GameTarot partieTarotSimulee() {
        return partieSimulee.partieTarot();
    }
    /**Pseudos utilis_&eacute_;s*/
    private StringList pseudosSimuleeTarot() {
        StringList pseudos_=new StringList();
        GameTarot partie_=partieTarotSimulee();
        partie_.getNombreDeJoueurs();
        pseudos_.add(container.pseudo());
        pseudos_.addAllElts(container.getPseudosJoueurs().getPseudosTarot());
        return pseudos_;
    }
    @Override
    public void run() {
        ThreadInvoker.invokeNow(new SettingSimulationComponent(this));
        //placerIhmTarotSimulee(mainsUtilisateurs_.get(0));
//        JPanel panneau_=container.getPanneauBoutonsJeu();
//        LabelButton bouton_=new LabelButton(container.getMessages().getVal(MainWindow.STOP_DEMO));
//        bouton_.addMouseListener(new MouseAdapter() {
//
//            @Override
//            public void mouseReleased(MouseEvent _e) {
//                container.setArretDemo(true);
//                container.getOwner().menuSoloGames();
//            }
//        });
//        panneau_.add(bouton_);
//        afficherMainUtilisateurSimuTarot(mainsUtilisateurs_.get(0));
        SwingUtilities.invokeLater(new GoSimulateGame(new GoSimulateTarot(partieSimulee, container, stopButton), container));
    }
    private void afficherMainUtilisateurSimuTarot(HandTarot _mainUtilisateur) {
        Panel panneau1_=container.getPanelHand();
        panneau1_.removeAll();
        String lg_ = container.getOwner().getLanguageKey();
        /*On place les cartes de l'utilisateur*/
        for (GraphicTarotCard c: ContainerTarot.getGraphicCards(lg_,_mainUtilisateur)) {
            panneau1_.add(c);
        }
        panneau1_.repaint();
        panneau1_.revalidate();
//        boolean entered_ = false;
//        for(CardTarot c: _mainUtilisateur)
//        {
//            GraphicTarotCard carte_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
//            carte_.setPreferredSize(entered_);
//            panneau1.add(carte_);
//            entered_ = true;
//        }
    }
    @Override
    public void setSimulationGui() {
        container.setArretDemo(false);
        //desactiver le menu Partie/aide au jeu
        container.getHelpGame().setEnabledMenu(false);
        //desactiver le menu Partie/Demo
        container.getDemo().setEnabledMenu(false);
        //Activer le menu Partie/Pause
        container.getPause().setEnabledMenu(true);
        String lg_ = container.getOwner().getLanguageKey();
        CustList<HandTarot> mainsUtilisateurs_=new CustList<HandTarot>();
        GameTarot partie_=partieTarotSimulee();
        partie_.simuler();
        if(partie_.getSimulationAvecContrats()) {
            CustList<TrickTarot> plisFaits_=partie_.unionPlis();
            mainsUtilisateurs_.add(0,new HandTarot());
            mainsUtilisateurs_.get(0).ajouter(plisFaits_.last().carteDuJoueur((byte)0));
            int indLastShownTrick_=plisFaits_.size()-2;
            for(int indicePli_=indLastShownTrick_;indicePli_>=CustList.FIRST_INDEX;indicePli_--) {
                TrickTarot pli_=plisFaits_.get(indicePli_);
                if(pli_.getVuParToutJoueur()) {
                    mainsUtilisateurs_.add(0,new HandTarot());
                    mainsUtilisateurs_.get(0).ajouterCartes(mainsUtilisateurs_.get(1));
                    mainsUtilisateurs_.get(0).ajouter(pli_.carteDuJoueur((byte)0));
                    mainsUtilisateurs_.get(0).trier(container.getDisplayingTarot().getCouleurs(),container.getDisplayingTarot().getDecroissant());
                }
            }
            if(partie_.getPreneur()==0&&partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
                mainsUtilisateurs_.add(0,new HandTarot());
                mainsUtilisateurs_.get(0).ajouterCartes(mainsUtilisateurs_.get(1));
                mainsUtilisateurs_.get(0).ajouterCartes(plisFaits_.get(0).getCartes());
                mainsUtilisateurs_.get(0).trier(container.getDisplayingTarot().getCouleurs(),container.getDisplayingTarot().getDecroissant());
                mainsUtilisateurs_.add(0,new HandTarot());
                mainsUtilisateurs_.get(0).ajouterCartes(mainsUtilisateurs_.get(1));
                mainsUtilisateurs_.get(0).supprimerCartes(partie_.getDistribution().derniereMain());
                mainsUtilisateurs_.get(0).trier(container.getDisplayingTarot().getCouleurs(),container.getDisplayingTarot().getDecroissant());
            }
        }else {
            mainsUtilisateurs_.add(partie_.getDistribution().main());
            mainsUtilisateurs_.get(0).trier(container.getDisplayingTarot().getCouleurs(),container.getDisplayingTarot().getDecroissant());
        }
        Panel contentPane_ = new Panel();
        contentPane_.setLayout(new BoxLayout(contentPane_.getComponent(), BoxLayout.PAGE_AXIS));
        Panel container_=new Panel();
        container_.setLayout(new BorderLayout());
        container_.add(new JLabel(container.getMessages().getVal(MainWindow.HELP_GO_MENU),SwingConstants.CENTER),BorderLayout.NORTH);
        CarpetTarot tapis_=new CarpetTarot();
        StringList pseudos_ = pseudosSimuleeTarot();
        tapis_.initTapisTarot(lg_,partie_.getNombreDeJoueurs(),container.getDisplayingTarot().getHoraire(),partie_.getDistribution().derniereMain().total());
        container.getTapis().setTapisTarot(tapis_);
        container_.add(tapis_,BorderLayout.CENTER);
        container.setPanelHand(new Panel());
        container.getPanelHand().setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        Panel panneau_=new Panel();
        panneau_.add(container.getPanelHand());
        container.setPanelDiscardedTrumps(new Panel(new FlowLayout(FlowLayout.LEFT,0,0)));
        container.getPanelDiscardedTrumps().setVisible(false);
        panneau_.add(container.getPanelDiscardedTrumps());
        panneau_.setBackground(Color.BLUE);
        container_.add(panneau_,BorderLayout.SOUTH);
        Panel panneau2_=new Panel();
        panneau2_.setLayout(new BoxLayout(panneau2_.getComponent(), BoxLayout.PAGE_AXIS));
        container.setEvents(new JTextArea(ContainerTarot.EMPTY,8, 30));
        container.getEvents().setEditable(false);
        panneau2_.add(new ScrollPane(container.getEvents()));
        container.setMini(new MiniCarpet(partie_.getNombreDeJoueurs(),container.getDisplayingTarot().getHoraire(),pseudos_));
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
            Panel declaredHandful_ = new Panel(new FlowLayout());
            declaredHandfulGroup_.add(declaredHandful_);
            container.getDeclaredHandfuls().put(i, declaredHandful_);
            declaredHandfuls_.add(declaredHandfulGroup_);
        }
        panneau2_.add(new ScrollPane(declaredHandfuls_));
        Panel sousPanneau_=new Panel(new GridLayout(0,1));
        container.setPanneauBoutonsJeu(sousPanneau_);
        panneau2_.add(sousPanneau_);
        container_.add(panneau2_,BorderLayout.EAST);
        container.tapisTarot().setTalonTarot(lg_,partie_.getDistribution().derniereMain());
        contentPane_.add(container_);
        contentPane_.add(container.getWindow().getClock());
        contentPane_.add(container.getWindow().getLastSavedGameDate());
        container.setContentPane(contentPane_);
        panneau_=container.getPanneauBoutonsJeu();
//        LabelButton bouton_=new LabelButton(container.getMessages().getVal(MainWindow.STOP_DEMO));
//        bouton_.addMouseListener(new MouseAdapter() {
//
//            @Override
//            public void mouseReleased(MouseEvent _e) {
//                container.setArretDemo(true);
//                container.getOwner().menuSoloGames();
//            }
//        });
        panneau_.add(stopButton);
        afficherMainUtilisateurSimuTarot(mainsUtilisateurs_.first());
    }

}
