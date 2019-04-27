package cards.gui.animations;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import cards.consts.Status;
import cards.facade.Games;
import cards.gameresults.sml.DocumentReaderCardsResultsUtil;
import cards.gui.MainWindow;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSimuTarot;
import cards.gui.containers.ContainerTarot;
import cards.gui.dialogs.FileConst;
import cards.gui.labels.GraphicTarotCard;
import cards.tarot.DealTarot;
import cards.tarot.GameTarot;
import cards.tarot.HandTarot;
import cards.tarot.ResultsTarot;
import cards.tarot.TrickTarot;
import cards.tarot.beans.TarotStandards;
import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.BonusTarot;
import cards.tarot.enumerations.CardTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import cards.tarot.enumerations.PlayingDog;
import code.gui.LabelButton;
import code.gui.Panel;
import code.gui.ThreadInvoker;
import code.gui.ThreadUtil;
import code.gui.document.RenderedPage;
import code.util.CustList;
import code.util.EnumList;
import code.util.EqList;
import code.util.Numbers;
import code.util.StringList;

/**This class thread is independant from EDT,
Thread safe class*/
public final class GoSimulateTarot extends Thread implements GoSimulate {

    private Games partieSimulee = new Games();

    private ContainerSimuTarot container;

    private LabelButton stopButton;

    /**This class thread is independant from EDT*/
    public GoSimulateTarot(Games _partieSimulee, ContainerSimuTarot _container, LabelButton _stopButton) {
        partieSimulee = _partieSimulee;
        container = _container;
        stopButton = _stopButton;
    }

    private GameTarot partieTarotSimulee() {
        return partieSimulee.partieTarot();
    }
    /**Pseudos utilis_&eacute_;s*/
    private StringList pseudosSimuleeTarot() {
//        CustList<String> pseudos_=new CustList<String>();
        GameTarot partie_=partieTarotSimulee();
//        partie_.getNombreDeJoueurs();
//        pseudos_.add(container.pseudo());
//        pseudos_.addAll(container.getPseudosJoueurs().getPseudosTarot());
//        return pseudos_;
        return container.pseudosTarot(partie_.getNombreDeJoueurs());
    }

    @Override
    public void run() {
        EqList<HandTarot> mainsUtilisateurs_=new EqList<HandTarot>();
        GameTarot partie_=partieTarotSimulee();
        byte nombreJoueurs_=partie_.getNombreDeJoueurs();
        byte indiceMainDepart_;
        partie_.setChargementSimulation(100);
        String lg_ = container.getOwner().getLanguageKey();
        if(partie_.getSimulationAvecContrats()) {
            CustList<TrickTarot> plisFaits_=partie_.unionPlis(true);
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
        } else {
            mainsUtilisateurs_.add(partie_.getDistribution().main());
            mainsUtilisateurs_.get(0).trier(container.getDisplayingTarot().getCouleurs(),container.getDisplayingTarot().getDecroissant());
        }
        ThreadUtil.sleep(500);
        String event_;
        event_ = StringList.concat(container.getMessages().getVal(MainWindow.BEGIN_DEMO),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//        container.ajouterTexteDansZone(event_);
//        container.ajouterTexteDansZone(StringList.concat(container.getMessages().getVal(MainWindow.BEGIN_DEMO),ContainerGame.RETURN_LINE));
        container.pause();
        EnumList<BidTarot> contrats_=partie_.tousContrats();
        int tailleContrat_=contrats_.size();
        StringList pseudos_=pseudosSimuleeTarot();
        byte preneur_=partie_.getPreneur();
        if(preneur_==0&&(partie_.getContrat().getJeuChien() == PlayingDog.WITH
                ||partie_.getContrat().getJeuChien() == PlayingDog.OUT)) {
            indiceMainDepart_=(byte)3;
        } else {
            indiceMainDepart_=(byte)1;
        }
        byte donneur_=partie_.getDistribution().getDonneur();
        byte entameur_=(byte)((donneur_+1)%nombreJoueurs_);
        for (int indiceContrat_ = CustList.FIRST_INDEX;indiceContrat_<tailleContrat_;indiceContrat_++) {
            byte joueur_=(byte)((entameur_+indiceContrat_)%nombreJoueurs_);
            event_ = StringList.concat(StringList.simpleStringsFormat(container.getMessages().getVal(MainWindow.DECLARE_BID), pseudos_.get(joueur_)),ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//            container.ajouterTexteDansZone(event_);
//            container.ajouterTexteDansZone(StringList.simpleFormat(container.getMessages().getVal(MainWindow.DECLARE_BID), pseudos_.get(joueur_))+ContainerTarot.RETURN_LINE_CHAR);
            ThreadUtil.sleep(1000);
            String mess_ = container.getMessages().getVal(MainWindow.DEMO_ACTION);
            event_ = StringList.concat(StringList.simpleStringsFormat(mess_,pseudos_.get(joueur_),contrats_.get(indiceContrat_).toString(lg_)),ContainerGame.RETURN_LINE);
            event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//            container.ajouterTexteDansZone(event_);
//            container.ajouterTexteDansZone(StringList.simpleFormat(mess_,pseudos_.get(joueur_),contrats_.get(indiceContrat_))+ContainerTarot.RETURN_LINE_CHAR);
//            container.ajouterTexteDansZone(ContainerTarot.EMPTY+ContainerTarot.RETURN_LINE_CHAR);
            container.pause();
            if(container.isArretDemo()) {
                arretDemo();
                return;
            }
        }
        if(!partie_.getContrat().isJouerDonne()) {
            event_ = StringList.concat(container.getMessages().getVal(MainWindow.NO_BID),ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//            container.ajouterTexteDansZone(event_);
//            container.ajouterTexteDansZone(StringList.concat(container.getMessages().getVal(MainWindow.NO_BID),ContainerGame.RETURN_LINE));
            //container.pack();
            container.revalidate();
            return;
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
        Numbers<Byte> appele_=partie_.getAppele();
        if (partie_.getRegles().getDiscardAfterCall()) {
            if(partie_.existeCarteAppelee()) {
                event_ = StringList.concat(container.getMessages().getVal(MainWindow.TAKER_CALL),ContainerGame.RETURN_LINE);
//                container.ajouterTexteDansZone(StringList.concat(container.getMessages().getVal(MainWindow.TAKER_CALL),ContainerGame.RETURN_LINE));
                event_ = StringList.concat(event_,container.getMessages().getVal(MainWindow.TAKER_CALL_WARNING),ContainerGame.RETURN_LINE);
//                container.ajouterTexteDansZone(StringList.concat(container.getMessages().getVal(MainWindow.TAKER_CALL_WARNING),ContainerGame.RETURN_LINE));
                event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
//                container.ajouterTexteDansZone(ContainerTarot.EMPTY+ContainerTarot.RETURN_LINE_CHAR);
                ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                container.ajouterTexteDansZone(event_);
                ThreadUtil.sleep(1000);
                container.pause();
                if(container.isArretDemo()) {
                    arretDemo();
                    return;
                }
                String mess_ = container.getMessages().getVal(MainWindow.DEMO_ACTION);
                event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(preneur_), partie_.getCarteAppelee().toString(lg_)),ContainerGame.RETURN_LINE);
//                container.ajouterTexteDansZone(StringList.simpleFormat(mess_, pseudos_.get(preneur_), partie_.getCarteAppelee())+ContainerTarot.RETURN_LINE_CHAR);
                mess_ = container.getMessages().getVal(MainWindow.CALLED_PLAYER);
                event_ = StringList.concat(event_, StringList.simpleStringsFormat(mess_, partie_.getCarteAppelee().toString(lg_)),ContainerGame.RETURN_LINE);
//                container.ajouterTexteDansZone(StringList.simpleFormat(mess_, partie_.getCarteAppelee())+ContainerTarot.RETURN_LINE_CHAR);
                event_ = StringList.concat(event_, container.getMessages().getVal(MainWindow.CALLED_PLAYER_WARNING),ContainerGame.RETURN_LINE);
//                container.ajouterTexteDansZone(StringList.concat(container.getMessages().getVal(MainWindow.CALLED_PLAYER_WARNING),ContainerGame.RETURN_LINE));
                event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
//                container.ajouterTexteDansZone(ContainerTarot.EMPTY+ContainerTarot.RETURN_LINE_CHAR);
                ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                container.ajouterTexteDansZone(event_);
            } else {
                for(byte a:appele_) {
                    String mess_ = container.getMessages().getVal(MainWindow.PARTNERS_TAKER);
                    event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(a)),ContainerGame.RETURN_LINE);
                    ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                    container.ajouterTexteDansZone(event_);
//                    container.ajouterTexteDansZone(StringList.simpleFormat(mess_, pseudos_.get(a))+ContainerTarot.RETURN_LINE_CHAR);
                }
            }
            appelEcart(preneur_, appele_, mainsUtilisateurs_);
        } else {
            appelEcart(preneur_, appele_, mainsUtilisateurs_);
            if(partie_.existeCarteAppelee()) {
                event_ = StringList.concat(container.getMessages().getVal(MainWindow.TAKER_CALL),ContainerGame.RETURN_LINE);
                event_ = StringList.concat(event_,container.getMessages().getVal(MainWindow.TAKER_CALL_WARNING),ContainerGame.RETURN_LINE);
                event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
                ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                container.ajouterTexteDansZone(event_);
//                container.ajouterTexteDansZone(StringList.concat(container.getMessages().getVal(MainWindow.TAKER_CALL),ContainerGame.RETURN_LINE));
//                container.ajouterTexteDansZone(StringList.concat(container.getMessages().getVal(MainWindow.TAKER_CALL_WARNING),ContainerGame.RETURN_LINE));
//                container.ajouterTexteDansZone(ContainerTarot.EMPTY+ContainerTarot.RETURN_LINE_CHAR);
                ThreadUtil.sleep(1000);
                container.pause();
                if(container.isArretDemo()) {
                    arretDemo();
                    return;
                }
                String mess_ = container.getMessages().getVal(MainWindow.DEMO_ACTION);
                event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(preneur_), partie_.getCarteAppelee().toString(lg_)),ContainerGame.RETURN_LINE);

//                container.ajouterTexteDansZone(StringList.simpleFormat(mess_, pseudos_.get(preneur_), partie_.getCarteAppelee())+ContainerTarot.RETURN_LINE_CHAR);
                mess_ = container.getMessages().getVal(MainWindow.CALLED_PLAYER);
                event_ = StringList.concat(event_, StringList.simpleStringsFormat(mess_, partie_.getCarteAppelee().toString(lg_)),ContainerGame.RETURN_LINE);
//                container.ajouterTexteDansZone(StringList.simpleFormat(mess_, partie_.getCarteAppelee())+ContainerTarot.RETURN_LINE_CHAR);
                event_ = StringList.concat(event_,container.getMessages().getVal(MainWindow.CALLED_PLAYER_WARNING),ContainerGame.RETURN_LINE);
//                container.ajouterTexteDansZone(StringList.concat(container.getMessages().getVal(MainWindow.CALLED_PLAYER_WARNING),ContainerGame.RETURN_LINE));
                event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
//                container.ajouterTexteDansZone(ContainerTarot.EMPTY+ContainerTarot.RETURN_LINE_CHAR);
                ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                container.ajouterTexteDansZone(event_);
            } else {
                for(byte a:appele_) {
                    String mess_ = container.getMessages().getVal(MainWindow.PARTNERS_TAKER);
                    event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(a)),ContainerGame.RETURN_LINE);
                    ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                    container.ajouterTexteDansZone(event_);
//                    container.ajouterTexteDansZone(StringList.simpleFormat(mess_, pseudos_.get(a))+ContainerTarot.RETURN_LINE_CHAR);
                }
            }
        }
        event_ = ContainerGame.RETURN_LINE;
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//        container.ajouterTexteDansZone(event_);
//        container.ajouterTexteDansZone(ContainerTarot.EMPTY+ContainerTarot.RETURN_LINE_CHAR);
        boolean passeBoucle_=false;
        CustList<TrickTarot> plisFaits_=partie_.unionPlis(false);
        int nbTricks_ = plisFaits_.size();
        for(int indicePli_=CustList.SECOND_INDEX;indicePli_<nbTricks_;indicePli_++) {
            TrickTarot pli_=plisFaits_.get(indicePli_);

            entameur_=pli_.getEntameur();
            for(byte indiceCarte_=CustList.FIRST_INDEX;indiceCarte_<nombreJoueurs_;indiceCarte_++) {
                byte joueur_=(byte)((entameur_+indiceCarte_)%nombreJoueurs_);
                CardTarot carte_=pli_.carteDuJoueur(joueur_);
                String mess_;
                if(joueur_==entameur_) {
                    mess_ = container.getMessages().getVal(MainWindow.PLAY_CARD_FIRST);
                    event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(joueur_)),ContainerGame.RETURN_LINE);
//                    container.ajouterTexteDansZone(StringList.simpleFormat(mess_, pseudos_.get(joueur_))+ContainerTarot.RETURN_LINE_CHAR);
                } else {
                    mess_ = container.getMessages().getVal(MainWindow.PLAY_CARD_THEN);
                    event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(joueur_)),ContainerGame.RETURN_LINE);
//                    container.ajouterTexteDansZone(StringList.simpleFormat(mess_, pseudos_.get(joueur_))+ContainerTarot.RETURN_LINE_CHAR);
                }
                ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                container.ajouterTexteDansZone(event_);
                ThreadUtil.sleep(1000);
                container.pause();
                if(indicePli_==CustList.SECOND_INDEX) {
                    if(partie_.pasJeuMisere()) {
                        for(Miseres annonce_:partie_.getAnnoncesMiseres(joueur_)) {
                            mess_ = container.getMessages().getVal(MainWindow.DEMO_ACTION);
                            event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(joueur_),annonce_.toString(lg_)),ContainerGame.RETURN_LINE);
                            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                            container.ajouterTexteDansZone(event_);
//                            container.ajouterTexteDansZone(StringList.simpleFormat(mess_, pseudos_.get(joueur_),annonce_)+ContainerTarot.RETURN_LINE_CHAR);
                        }
                        HandTarot poignee_=partie_.getPoignee(joueur_);
                        if(!poignee_.estVide()) {
                            EnumList<Handfuls> annoncesPoigneesJoueur_ = partie_.getAnnoncesPoignees(joueur_);
                            mess_ = container.getMessages().getVal(MainWindow.DEMO_ACTION);
                            event_ = StringList.concat(StringList.simpleStringsFormat(mess_,pseudos_.get(joueur_),annoncesPoigneesJoueur_.first().toString(lg_)),ContainerGame.RETURN_LINE);
                            event_ = StringList.concat(event_, StringList.simpleStringsFormat(mess_,pseudos_.get(joueur_),poignee_.toString(lg_)),ContainerGame.RETURN_LINE);
                            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                            container.ajouterTexteDansZone(event_);
//                            container.ajouterTexteDansZone(StringList.simpleFormat(mess_,pseudos_.get(joueur_),annoncesPoigneesJoueur_.first())+ContainerTarot.RETURN_LINE_CHAR);
//                            container.ajouterTexteDansZone(StringList.simpleFormat(mess_,pseudos_.get(joueur_),poignee_)+ContainerTarot.RETURN_LINE_CHAR);
                        }
                    }
                }
                if(partie_.existeCarteAppelee()&&partie_.getCarteAppelee().contient(carte_)) {
                    mess_ = container.getMessages().getVal(MainWindow.DEMO_ACTION);
                    container.getMini().setStatus(Status.CALLED_PLAYER, joueur_);
                    event_ = StringList.concat(StringList.simpleStringsFormat(mess_,pseudos_.get(joueur_),Status.CALLED_PLAYER.toString(lg_)),ContainerGame.RETURN_LINE);
                    ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                    container.ajouterTexteDansZone(event_);
//                    container.ajouterTexteDansZone(StringList.simpleFormat(mess_,pseudos_.get(joueur_),Status.CALLED_PLAYER.toString())+ContainerTarot.RETURN_LINE_CHAR);
                }
                container.tapisTarot().setCarteTarot(lg_,joueur_,carte_);
                if(joueur_==DealTarot.NUMERO_UTILISATEUR) {
                    if(indicePli_<plisFaits_.size()-1) {
                        if(!passeBoucle_) {
                            afficherMainUtilisateurSimuTarot(mainsUtilisateurs_.get(indiceMainDepart_+indicePli_-1));
                        } else {
                            afficherMainUtilisateurSimuTarot(mainsUtilisateurs_.get(indiceMainDepart_+indicePli_-2));
                        }
                    } else {
                        afficherMainUtilisateurSimuTarot(new HandTarot());
                    }
                    //container.pack();
                    container.revalidate();
                }
                if(container.isArretDemo()) {
                    arretDemo();
                    return;
                }
            }
            byte ramasseur_=GameTarot.ramasseur(plisFaits_,(byte)indicePli_);
            String mess_ = container.getMessages().getVal(MainWindow.TRICK_WINNER);
            event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(ramasseur_)),ContainerGame.RETURN_LINE);
            event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//            container.ajouterTexteDansZone(event_);
//            container.ajouterTexteDansZone(StringList.simpleFormat(mess_, pseudos_.get(ramasseur_))+ContainerTarot.RETURN_LINE_CHAR);
//            container.ajouterTexteDansZone(ContainerTarot.EMPTY+ContainerTarot.RETURN_LINE_CHAR);
            if(indicePli_==plisFaits_.size()-1) {
                if(partie_.petitMeneAuBout(ramasseur_)) {
                    mess_ = container.getMessages().getVal(MainWindow.BONUS_WIN);
                    event_ = StringList.concat(StringList.simpleStringsFormat(mess_, pseudos_.get(ramasseur_), BonusTarot.SMALL_BOUND.toString(lg_)),ContainerGame.RETURN_LINE);
                    ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                    container.ajouterTexteDansZone(event_);
//                    container.ajouterTexteDansZone(StringList.simpleFormat(mess_, pseudos_.get(ramasseur_), BonusTarot.SMALL_BOUND)+ContainerTarot.RETURN_LINE_CHAR);
                }
            }
            ThreadUtil.sleep(4000);
            container.pause();
            container.tapisTarot().setCartesTarotJeu(lg_,nombreJoueurs_);
            if(container.isArretDemo()) {
                arretDemo();
                return;
            }
        }
        SwingUtilities.invokeLater(new EndSimulation(this));
    }


    private void arretDemo() {
        SwingUtilities.invokeLater(new StopDemo(container));
    }
    private void appelEcart(byte _preneur,Numbers<Byte> _appele,EqList<HandTarot> _mainsUtilisateurs) {
        GameTarot partie_=partieTarotSimulee();
        String event_;
        if(partie_.getContrat().getJeuChien() == PlayingDog.WITH) {
            event_ = StringList.concat(container.getMessages().getVal(MainWindow.SHOWN_DOG),ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//            container.ajouterTexteDansZone(event_);
//            container.ajouterTexteDansZone(StringList.concat(container.getMessages().getVal(MainWindow.SHOWN_DOG),ContainerGame.RETURN_LINE));
            ThreadUtil.sleep(1000);
            setChien(partie_.getDistribution().derniereMain());
            event_ = StringList.concat(container.getMessages().getVal(MainWindow.PLAYERS_SHOW_DOG),ContainerGame.RETURN_LINE);
            event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
//            container.ajouterTexteDansZone(event_);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//            container.ajouterTexteDansZone(StringList.concat(container.getMessages().getVal(MainWindow.PLAYERS_SHOW_DOG),ContainerGame.RETURN_LINE));
//            container.ajouterTexteDansZone(ContainerTarot.EMPTY+ContainerTarot.RETURN_LINE_CHAR);
            //container.pack();
            container.revalidate();
            container.pause();
            if(container.isArretDemo()) {
                arretDemo();
                return;
            }
            ThreadUtil.sleep(1000);
            if (_appele.containsObj(_preneur)) {
                event_ = StringList.concat(container.getMessages().getVal(MainWindow.ALONE_TAKER),ContainerGame.RETURN_LINE);
                event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
                ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
            }
            container.revalidate();
            container.pause();
            if(container.isArretDemo()) {
                arretDemo();
                return;
            }
            event_ = StringList.concat(container.getMessages().getVal(MainWindow.TAKE_DOG),ContainerGame.RETURN_LINE);
            event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//            container.ajouterTexteDansZone(event_);
//            container.ajouterTexteDansZone(StringList.concat(container.getMessages().getVal(MainWindow.TAKE_DOG),ContainerGame.RETURN_LINE));
//            container.ajouterTexteDansZone(ContainerTarot.EMPTY+ContainerTarot.RETURN_LINE_CHAR);
            ThreadInvoker.invokeNow(new WithdrawCards(container));
            if(_preneur==0) {
                afficherMainUtilisateurSimuTarot(_mainsUtilisateurs.get(1));
            }
            ThreadUtil.sleep(1000);
            //container.pack();
            container.revalidate();
            container.pause();
            if(container.isArretDemo()) {
                arretDemo();
                return;
            }
            String mess_ = container.getMessages().getVal(MainWindow.DISCARD_CARDS);
            HandTarot lastHand_ = partie_.getDistribution().derniereMain();
            event_ = StringList.concat(StringList.simpleNumberFormat(mess_, lastHand_.total()),ContainerGame.RETURN_LINE);
            event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//            container.ajouterTexteDansZone(event_);
//            container.ajouterTexteDansZone(StringList.simpleFormat(mess_, lastHand_.total())+ContainerTarot.RETURN_LINE_CHAR);
//            container.ajouterTexteDansZone(ContainerTarot.EMPTY+ContainerTarot.RETURN_LINE_CHAR);
            ThreadInvoker.invokeNow(new SimulationDiscardTarot(container, lastHand_));
            if(_preneur==0) {
                afficherMainUtilisateurSimuTarot(_mainsUtilisateurs.get(2));
            }
            ThreadUtil.sleep(1000);
            //container.pack();
            container.revalidate();
            container.pause();
            if(container.isArretDemo()) {
                arretDemo();
                return;
            }
            if(partie_.chelemAnnonce(_preneur)) {
                event_ = StringList.concat(container.getMessages().getVal(MainWindow.DECLARING_SLAM_DEMO_DISCARD),ContainerGame.RETURN_LINE);
                event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
                ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                container.ajouterTexteDansZone(event_);
//                container.ajouterTexteDansZone(StringList.concat(container.getMessages().getVal(MainWindow.DECLARING_SLAM_DEMO_DISCARD),ContainerGame.RETURN_LINE));
//                container.ajouterTexteDansZone(ContainerTarot.EMPTY+ContainerTarot.RETURN_LINE_CHAR);
            }
        } else {
            if(partie_.chelemAnnonce(_preneur)) {
                event_ = StringList.concat(container.getMessages().getVal(MainWindow.DECLARING_SLAM_DEMO),ContainerGame.RETURN_LINE);
                event_ = StringList.concat(event_,ContainerGame.RETURN_LINE);
                ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//                container.ajouterTexteDansZone(event_);
//                container.ajouterTexteDansZone(StringList.concat(container.getMessages().getVal(MainWindow.DECLARING_SLAM_DEMO),ContainerGame.RETURN_LINE));
//                container.ajouterTexteDansZone(ContainerTarot.EMPTY+ContainerTarot.RETURN_LINE_CHAR);
            }
        }
        event_ = StringList.concat(container.getMessages().getVal(MainWindow.BEGIN_PLAY_CARDS),ContainerGame.RETURN_LINE);
        ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//        container.ajouterTexteDansZone(event_);
//        container.ajouterTexteDansZone(StringList.concat(container.getMessages().getVal(MainWindow.BEGIN_PLAY_CARDS),ContainerGame.RETURN_LINE));
        ThreadUtil.sleep(2000);
        //container.pack();
        container.revalidate();
        container.pause();
        if(container.isArretDemo()) {
            arretDemo();
            return;
        }
    }

    private void setChien(HandTarot _main) {
        container.setCanDiscard(false);
        ThreadInvoker.invokeNow(new SimulationRefreshHandTarotDog(container, _main));
    }
    static void updateCardsInPanelTarotDog(ContainerSimuTarot _s,Panel _panel, HandTarot _hand) {
        _panel.removeAll();
        String lg_ = _s.getOwner().getLanguageKey();
        for (GraphicTarotCard c: ContainerTarot.getGraphicCards(lg_,_hand)) {
            _panel.add(c);
        }
        _panel.repaint();
        _panel.revalidate();
//        boolean entered_ = false;
//        for(CardTarot c: _hand)
//        {
//            GraphicTarotCard carte_=new GraphicTarotCard(c,SwingConstants.RIGHT,!entered_);
//            carte_.setPreferredSize(entered_);
//            _panel.add(carte_);
//        }
    }
    private void afficherMainUtilisateurSimuTarot(HandTarot _mainUtilisateur) {
        ThreadInvoker.invokeNow(new SimulationRefreshHandTarot(container, _mainUtilisateur));
//        JPanel panneau1=container.getPanelHand();
//        panneau1.removeAll();
//        //On place les cartes de l'utilisateur
//        for (GraphicTarotCard c: ContainerTarot.getGraphicCards(_mainUtilisateur)) {
//            panneau1.add(c);
//        }
//        panneau1.repaint();
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
    public void endSimulation() {
        String lg_ = container.getOwner().getLanguageKey();
        ResultsTarot res_ = new ResultsTarot();
        GameTarot currentGame_=partieTarotSimulee();
        res_.setGame(currentGame_);
        StringList nicknames_=pseudosSimuleeTarot();
        res_.initialize(new StringList(nicknames_), container.getScores());
        res_.setUser(DealTarot.NUMERO_UTILISATEUR);
        DocumentReaderCardsResultsUtil.setMessages(res_,lg_);
        JScrollPane scroll_=new JScrollPane();
        RenderedPage editor_ = new RenderedPage(scroll_);
        editor_.setLanguage(lg_);
        editor_.setDataBase(res_);
        editor_.initialize(FileConst.RESOURCES_HTML_FILES_RESULTS_TAROT, new TarotStandards());
        scroll_.setPreferredSize(new Dimension(300,300));

        Panel panneau_=new Panel();
        panneau_.setLayout(new BoxLayout(panneau_.getComponent(), BoxLayout.PAGE_AXIS));
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
