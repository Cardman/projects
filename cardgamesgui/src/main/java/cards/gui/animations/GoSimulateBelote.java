package cards.gui.animations;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import cards.belote.BidBeloteSuit;
import cards.belote.DealBelote;
import cards.belote.DeclareHandBelote;
import cards.belote.GameBelote;
import cards.belote.HandBelote;
import cards.belote.ResultsBelote;
import cards.belote.TrickBelote;
import cards.belote.beans.BeloteStandards;
import cards.belote.enumerations.BonusBelote;
import cards.belote.enumerations.CardBelote;
import cards.belote.enumerations.DeclaresBelote;
import cards.belote.enumerations.DeclaresBeloteRebelote;
import cards.consts.Status;
import cards.facade.Games;
import cards.gui.MainWindow;
import cards.gui.containers.ContainerBelote;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSimuBelote;
import cards.gui.dialogs.FileConst;
import code.gui.LabelButton;
import code.gui.ThreadInvoker;
import code.gui.document.RenderedPage;
import code.util.CustList;
import code.util.EqList;
import code.util.StringList;
import code.util.consts.Constants;

/**This class thread is independant from EDT,
Thread safe class*/
public final class GoSimulateBelote extends Thread implements GoSimulate {

    private Games partieSimulee = new Games();
    private ContainerSimuBelote container;
    private LabelButton stopButton;

    /**This class thread is independant from EDT*/
    public GoSimulateBelote(Games _partieSimulee, ContainerSimuBelote _container, LabelButton _stopButton) {
        partieSimulee = _partieSimulee;
        container = _container;
        stopButton = _stopButton;
    }

    private GameBelote partieBeloteSimulee() {
        return partieSimulee.partieBelote();
    }
    /**Pseudos utilis_&eacute_;s*/
    private StringList pseudosSimuleeBelote() {
//        CustList<String> pseudos_=new CustList<String>();
//        GameBelote partie_=partieBeloteSimulee();
//        partie_.getNombreDeJoueurs();
//        pseudos_.add(container.pseudo());
//        pseudos_.addAll(container.getPseudosJoueurs().getPseudosBelote());
//        return pseudos_;
        GameBelote partie_=partieBeloteSimulee();
//        partie_.getNombreDeJoueurs();
//        pseudos_.add(container.pseudo());
//        pseudos_.addAll(container.getPseudosJoueurs().getPseudosTarot());
//        return pseudos_;
        return container.pseudosBelote(partie_.getNombreDeJoueurs());
    }

    @Override
    public void run() {
        byte indiceMainDepart_;
        EqList<HandBelote> mainsUtilisateurs_=new EqList<HandBelote>();
        GameBelote partie_=partieBeloteSimulee();
        byte nombreJoueurs_=partie_.getNombreDeJoueurs();
        if(partie_.getSimulationAvecContrats()) {
            CustList<TrickBelote> plisFaits_=partie_.unionPlis();
            mainsUtilisateurs_.add(0,new HandBelote());
            mainsUtilisateurs_.get(0).ajouter(plisFaits_.get(plisFaits_.size()-1).carteDuJoueur((byte)0, nombreJoueurs_));
            int indLastShownTrick_=plisFaits_.size()-2;
            for(int indicePli_=indLastShownTrick_;indicePli_>=CustList.FIRST_INDEX;indicePli_--) {
                TrickBelote pli_=plisFaits_.get(indicePli_);
                mainsUtilisateurs_.add(0,new HandBelote());
                mainsUtilisateurs_.get(0).ajouterCartes(mainsUtilisateurs_.get(1));
                mainsUtilisateurs_.get(0).ajouter(pli_.carteDuJoueur((byte)0, nombreJoueurs_));
                mainsUtilisateurs_.get(0).setOrdre(container.getDisplayingBelote().getOrdreAvantEncheres());
                mainsUtilisateurs_.get(0).trier(container.getDisplayingBelote().getCouleurs(),container.getDisplayingBelote().getDecroissant(),container.getDisplayingBelote().getOrdreAvantEncheres());
            }
            mainsUtilisateurs_.add(0,new HandBelote());
            mainsUtilisateurs_.get(0).ajouterCartes(mainsUtilisateurs_.get(1));
            mainsUtilisateurs_.get(0).supprimerCartes(partie_.getDistribution().derniereMain());
            mainsUtilisateurs_.get(0).setOrdre(container.getDisplayingBelote().getOrdreAvantEncheres());
            mainsUtilisateurs_.get(0).trier(container.getDisplayingBelote().getCouleurs(),container.getDisplayingBelote().getDecroissant(),container.getDisplayingBelote().getOrdreAvantEncheres());
        } else {
            mainsUtilisateurs_.add(partie_.getDistribution().main());
            mainsUtilisateurs_.get(0).setOrdre(container.getDisplayingBelote().getOrdreAvantEncheres());
            mainsUtilisateurs_.get(0).trier(container.getDisplayingBelote().getCouleurs(),container.getDisplayingBelote().getDecroissant(),container.getDisplayingBelote().getOrdreAvantEncheres());
        }
        GameBelote.setChargementSimulation(100);
        Constants.sleep(500);
        String event_;
        event_ = StringList.concat(container.getMessages().getVal(MainWindow.BEGIN_DEMO),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//        container.ajouterTexteDansZone(event_);
//        container.ajouterTexteDansZone(container.getMessages().getVal(MainWindow.BEGIN_DEMO)+ContainerBelote.RETURN_LINE_CHAR);
        container.pause();
        EqList<BidBeloteSuit> contrats_=partie_.tousContrats();
        int tailleContrat_=contrats_.size();
        StringList pseudos_=pseudosSimuleeBelote();
        byte preneur_=partie_.getPreneur();
        indiceMainDepart_=2;
        byte donneur_=partie_.getDistribution().getDonneur();
        byte entameur_=(byte)((donneur_+1)%nombreJoueurs_);
        byte nbBids_ = 0;
        boolean dealAll_ = partie_.getRegles().dealAll();
        for (int indiceContrat_ = CustList.FIRST_INDEX;indiceContrat_<tailleContrat_;indiceContrat_++) {
            if (indiceContrat_ % nombreJoueurs_ == 0) {
                nbBids_++;
                event_ = StringList.concat(Integer.toString(nbBids_),ContainerGame.RETURN_LINE);
                ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                container.ajouterTexteDansZone(event_);
//                container.ajouterTexteDansZone(Integer.toString(nbBids_)+ContainerBelote.RETURN_LINE_CHAR);
            }
            byte joueur_=(byte)((entameur_+indiceContrat_)%nombreJoueurs_);
            event_ = StringList.concat(StringList.simpleStringsFormat(container.getMessages().getVal(MainWindow.DECLARE_BID), pseudos_.get(joueur_)),ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//            container.ajouterTexteDansZone(event_);
//            container.ajouterTexteDansZone(StringList.simpleFormat(container.getMessages().getVal(MainWindow.DECLARE_BID), pseudos_.get(joueur_))+ContainerBelote.RETURN_LINE_CHAR);
            Constants.sleep(1000);
            String mess_ = container.getMessages().getVal(MainWindow.DEMO_ACTION);
            event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(joueur_), contrats_.get(indiceContrat_).display()),ContainerGame.RETURN_LINE);
            event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//            container.ajouterTexteDansZone(event_);
//            container.ajouterTexteDansZone(StringList.simpleFormat(mess_, pseudos_.get(joueur_), contrats_.get(indiceContrat_))+ContainerBelote.RETURN_LINE_CHAR);
//            container.ajouterTexteDansZone(ContainerBelote.EMPTY+ContainerBelote.RETURN_LINE_CHAR);
            container.pause();
            if(container.isArretDemo()) {
                arretDemo();
                return;
            }
        }
        if(!partie_.getContrat().getEnchere().jouerDonne()) {
            event_ = StringList.concat(container.getMessages().getVal(MainWindow.NO_BID),ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//            container.ajouterTexteDansZone(event_);
//            container.ajouterTexteDansZone(container.getMessages().getVal(MainWindow.NO_BID)+ContainerBelote.RETURN_LINE_CHAR);
            container.revalidate();
            return;
            //container.pack();
//            while(true)
//            {
//                if(container.isArretDemo())
//                {
//                    arretDemo();
//                    return;
//                }
//            }
        }
        container.getMini().setStatus(Status.TAKER, partie_.getPreneur());
        String mess_ = container.getMessages().getVal(MainWindow.TEAM_TAKER);
        event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(preneur_), pseudos_.get((preneur_+2)%nombreJoueurs_)),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//        container.ajouterTexteDansZone(event_);
//        container.ajouterTexteDansZone(StringList.simpleFormat(mess_, pseudos_.get(preneur_), pseudos_.get((preneur_+2)%nombreJoueurs_))+ContainerBelote.RETURN_LINE_CHAR);
        if (!dealAll_) {
            event_ = container.getMessages().getVal(MainWindow.TAKE_TOP_CARD);
//            container.ajouterTexteDansZone(container.getMessages().getVal(MainWindow.TAKE_TOP_CARD));
            mess_ = container.getMessages().getVal(MainWindow.DEAL_REMAIN_CARDS);
//            container.ajouterTexteDansZone(StringList.simpleFormat(mess_, pseudos_.get(donneur_))+ContainerBelote.RETURN_LINE_CHAR);
            event_ = StringList.concat(event_,StringList.simpleStringsFormat(mess_, pseudos_.get(donneur_)),ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//            container.ajouterTexteDansZone(event_);
        }
        boolean first_ = true;
        int step_ = 1;
        for (int nb_: partie_.getRegles().getRepartition().getDistributionFin()) {
            for (int indiceJoueur_ = CustList.FIRST_INDEX;indiceJoueur_<nombreJoueurs_;indiceJoueur_++) {
                byte joueur_=(byte)((indiceJoueur_+entameur_)%nombreJoueurs_);
                int gotCards_ = nb_;
                mess_ = container.getMessages().getVal(MainWindow.DEAL_SET_CARDS);
                if(joueur_==preneur_) {
                    if (first_) {
                        gotCards_ --;
                    }
                }
                event_ = StringList.concat(ContainerBelote.TAB,StringList.simpleStringsFormat(mess_, Long.toString(step_), Long.toString(gotCards_), pseudos_.get(joueur_)));
                ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                container.ajouterTexteDansZone(event_);
//                container.ajouterTexteDansZone(ContainerBelote.TAB+StringList.simpleFormat(mess_, step_, gotCards_, pseudos_.get(joueur_)));
                step_++;
            }
            first_ = false;
        }
        event_ = ContainerGame.RETURN_LINE;
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//        container.ajouterTexteDansZone(event_);
//        container.ajouterTexteDansZone(ContainerBelote.EMPTY+ContainerBelote.RETURN_LINE_CHAR);
        Constants.sleep(3000);
        ThreadInvoker.invokeNow(new WithdrawCards(container));
        container.pause();
        if(container.isArretDemo()) {
            arretDemo();
            return;
        }
        afficherMainUtilisateurSimuBelote(mainsUtilisateurs_.get(1));
        container.revalidate();
        //container.pack();
        Constants.sleep(4000);
        container.pause();
        if(container.isArretDemo()) {
            arretDemo();
            return;
        }
        CustList<TrickBelote> plisFaits_=partie_.unionPlis();
        int nbTricks_ = plisFaits_.size();
        for(int indicePli_=CustList.FIRST_INDEX;indicePli_<nbTricks_;indicePli_++) {
            TrickBelote pli_=plisFaits_.get(indicePli_);
            entameur_=pli_.getEntameur();
            for(byte indiceCarte_=CustList.FIRST_INDEX;indiceCarte_<nombreJoueurs_;indiceCarte_++) {
                byte joueur_=(byte)((entameur_+indiceCarte_)%nombreJoueurs_);
                CardBelote carte_=pli_.carteDuJoueur(joueur_, nombreJoueurs_);
                if(joueur_==entameur_) {
                    mess_ = container.getMessages().getVal(MainWindow.PLAY_CARD_FIRST);
                    event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(joueur_)),ContainerGame.RETURN_LINE);
//                    container.ajouterTexteDansZone(StringList.simpleFormat(mess_, pseudos_.get(joueur_))+ContainerBelote.RETURN_LINE_CHAR);
                } else {
                    mess_ = container.getMessages().getVal(MainWindow.PLAY_CARD_THEN);
                    event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(joueur_)),ContainerGame.RETURN_LINE);
//                    container.ajouterTexteDansZone(StringList.simpleFormat(mess_, pseudos_.get(joueur_))+ContainerBelote.RETURN_LINE_CHAR);
                }
                ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                container.ajouterTexteDansZone(event_);
                Constants.sleep(1000);
                container.pause();
                if(indicePli_==CustList.FIRST_INDEX) {
                    DeclareHandBelote decl_ = partie_.getAnnonce(joueur_);
                    if (decl_.getAnnonce() != DeclaresBelote.UNDEFINED) {
                        mess_ = container.getMessages().getVal(MainWindow.DEMO_ACTION_TWO);
                        event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(joueur_), decl_.getAnnonce().display(), decl_.getMain().display()),ContainerGame.RETURN_LINE);
                        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                        container.ajouterTexteDansZone(event_);
//                        container.ajouterTexteDansZone(StringList.simpleFormat(mess_, pseudos_.get(joueur_), decl_.getAnnonce(), decl_.getMain())+ContainerBelote.RETURN_LINE_CHAR);
                    }
                }
                if(partie_.cartesBeloteRebelote().contient(carte_)) {
                    mess_ = container.getMessages().getVal(MainWindow.DEMO_ACTION);
                    event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(joueur_),DeclaresBeloteRebelote.BELOTE_REBELOTE.display()),ContainerGame.RETURN_LINE);
                    ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                    container.ajouterTexteDansZone(event_);
//                    container.ajouterTexteDansZone(StringList.simpleFormat(mess_, pseudos_.get(joueur_),DeclaresBeloteRebelote.BELOTE_REBELOTE)+ContainerBelote.RETURN_LINE_CHAR);
                }
                container.tapisBelote().setCarteBelote(joueur_,carte_);
                if(joueur_==0) {
                    if(indicePli_<plisFaits_.size()-1) {
                        afficherMainUtilisateurSimuBelote(mainsUtilisateurs_.get(indiceMainDepart_+indicePli_));
                    } else {
                        afficherMainUtilisateurSimuBelote(new HandBelote());
                    }
                    container.revalidate();
                    //container.pack();
                }
                if(container.isArretDemo()) {
                    arretDemo();
                    return;
                }
            }
            byte ramasseur_=partie_.ramasseur(plisFaits_,(byte)indicePli_);
            mess_ = container.getMessages().getVal(MainWindow.TRICK_WINNER);
            event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(ramasseur_)),ContainerGame.RETURN_LINE);
            event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//            container.ajouterTexteDansZone(event_);
//            container.ajouterTexteDansZone(StringList.simpleFormat(mess_, pseudos_.get(ramasseur_))+ContainerBelote.RETURN_LINE_CHAR);
//            container.ajouterTexteDansZone(ContainerBelote.EMPTY+ContainerBelote.RETURN_LINE_CHAR);
            if(indicePli_==plisFaits_.size()-1) {
                if(partie_.getDixDeDer(ramasseur_)) {
                    mess_ = container.getMessages().getVal(MainWindow.BONUS_WIN);
                    event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(ramasseur_), BonusBelote.LAST_TRICK.display()),ContainerGame.RETURN_LINE);
                    ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                    container.ajouterTexteDansZone(event_);
//                    container.ajouterTexteDansZone(StringList.simpleFormat(mess_, pseudos_.get(ramasseur_), BonusBelote.LAST_TRICK)+ContainerBelote.RETURN_LINE_CHAR);
                }
            }
            Constants.sleep(4000);
            container.pause();
            container.tapisBelote().setCartesBeloteJeu(nombreJoueurs_);
            if(container.isArretDemo()) {
                arretDemo();
                return;
            }
        }
        SwingUtilities.invokeLater(new EndSimulation(this));
//        JPanel panneau_=new JPanel();
//        panneau_.setLayout(new BoxLayout(panneau_, BoxLayout.PAGE_AXIS));
//        ResultsBelote res_ = new ResultsBelote();
//        res_.setGame(partie_);
//        res_.initialize(new CustList<>(pseudos_), container.getScores());
//        res_.setUser(DealBelote.NUMERO_UTILISATEUR);
//        res_.setMessages(Constants.getLanguage());
//        SessionEditorPane editor_ = new SessionEditorPane();
//        try {
//            editor_.setMainClass(SoftApplication.getMainClass());
//            editor_.setFiles(FileConst.RESOURCES_HTML_FOLDER);
//            editor_.setLanguage(Constants.getLanguage());
//            editor_.setDataBase(res_);
//            editor_.initialize(FileConst.RESOURCES_HTML_FILES_RESULTS_BELOTE);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        editor_.setEditable(false);
//        JScrollPane scroll_=new JScrollPane(editor_);
//        scroll_.setPreferredSize(new Dimension(300,300));
//        panneau_.add(scroll_);
//        LabelButton bouton_=new LabelButton(container.getMessages().getVal(MainWindow.STOP_DEMO));
//        bouton_.addMouseListener(new MouseAdapter() {
//
//            @Override
//            public void mouseReleased(MouseEvent _e) {
//                container.setArretDemo(true);
//                arretDemo();
//            }
//        });
//        panneau_.add(bouton_);
//        panneau_.add(container.getOwner().getClock());
//        panneau_.add(container.getOwner().getLastSavedGameDate());
//        container.setContentPane(panneau_);
//        PackingWindowAfter.pack(container);
    }
    private void arretDemo() {
        SwingUtilities.invokeLater(new StopDemo(container));
        //container.getOwner().menuSoloGames();
    }
    private void afficherMainUtilisateurSimuBelote(HandBelote _mainUtilisateur) {
        ThreadInvoker.invokeNow(new SimulationRefreshHandBelote(container, _mainUtilisateur));
//        boolean entered_ = false;
//        for(CardBelote c: _mainUtilisateur)
//        {
//            GraphicBeloteCard carte_=new GraphicBeloteCard(c,SwingConstants.RIGHT,!entered_);
//            carte_.setPreferredSize(entered_);
//            panneau1.add(carte_);
//            entered_ = true;
//        }

    }

    @Override
    public void endSimulation() {
        JPanel panneau_=new JPanel();
        panneau_.setLayout(new BoxLayout(panneau_, BoxLayout.PAGE_AXIS));
        ResultsBelote res_ = new ResultsBelote();
        GameBelote currentGame_=partieBeloteSimulee();
        res_.setGame(currentGame_);
        StringList nicknames_=pseudosSimuleeBelote();
        res_.initialize(new StringList(nicknames_), container.getScores());
        res_.setUser(DealBelote.NUMERO_UTILISATEUR);
        res_.setMessages(Constants.getLanguage());
        JScrollPane scroll_=new JScrollPane();
        RenderedPage editor_ = new RenderedPage(scroll_);
        try {
//            editor_.setMainClass(SoftApplication.getMainClass());
//            editor_.setTextFilesWithPrefix(FileConst.RESOURCES_HTML_FOLDER + StreamTextFile.SEPARATEUR);
            editor_.setLanguage(Constants.getLanguage());
            editor_.setDataBase(res_);
            editor_.initialize(FileConst.RESOURCES_HTML_FILES_RESULTS_BELOTE, new BeloteStandards());
        } catch (RuntimeException _0) {
            _0.printStackTrace();
        }
        scroll_.setPreferredSize(new Dimension(300,300));
        panneau_.add(scroll_);
//        LabelButton bouton_=new LabelButton(container.getMessages().getVal(MainWindow.STOP_DEMO));
//        bouton_.addMouseListener(new MouseAdapter() {
//
//            @Override
//            public void mouseReleased(MouseEvent _e) {
//                container.setArretDemo(true);
//                arretDemo();
//            }
//        });
        panneau_.add(stopButton);
        panneau_.add(container.getOwner().getClock());
        panneau_.add(container.getOwner().getLastSavedGameDate());
        container.setContentPane(panneau_);
        container.pack();
    }
}
