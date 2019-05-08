package cards.gui.animations;
import javax.swing.SwingUtilities;

import cards.belote.BidBeloteSuit;
import cards.belote.DealBelote;
import cards.belote.GameBelote;
import cards.facade.Games;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSingleBelote;
import code.gui.ThreadInvoker;
import code.gui.ThreadUtil;
import code.util.StringList;

/**This class thread is independant from EDT,
Thread safe class*/
public final class AnimationBidBelote extends Thread {

    private ContainerSingleBelote container;

    /**This class thread is independant from EDT*/
    public AnimationBidBelote(ContainerSingleBelote _container) {
        container = _container;
    }

    @Override
    public void run() {
        String lg_ = container.getOwner().getLanguageKey();
        StringList pseudos_ = container.pseudosBelote();
        container.setThreadAnime(true);
        long delaiContrat_=container.getParametres().getDelaiAttenteContrats();
        GameBelote partie_=container.partieBelote();
        if (partie_.playerHavingToBid() == DealBelote.NUMERO_UTILISATEUR) {
            BidBeloteSuit contrat_=container.getContratUtilisateurBelote();
            partie_.ajouterContrat(contrat_,DealBelote.NUMERO_UTILISATEUR);
            String event_ = StringList.concat(container.pseudo(),ContainerGame.INTRODUCTION_PTS,Games.toString(contrat_,lg_),ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//            container.ajouterTexteDansZone(event_);
//            container.ajouterTexteDansZone(container.pseudo()+ContainerGame.INTRODUCTION_PTS+contrat_.toString()+ContainerBelote.RETURN_LINE_CHAR);
        }
        //Activer le menu Partie/Pause
//        container.getPause().setEnabled(true);
        ThreadInvoker.invokeNow(new ChangingPause(container, true));
        while (true) {
            if(!partie_.keepBidding()){
                break;
            }
            byte player_ = partie_.playerHavingToBid();
            if (player_ == DealBelote.NUMERO_UTILISATEUR) {
                break;
            }
            //Les Fenetre.ROBOTS precedant l'utilisateur annoncent leur contrat
            ThreadUtil.sleep(delaiContrat_);
            BidBeloteSuit contrat_=partie_.strategieContrat();
            partie_.ajouterContrat(contrat_, player_);
//            container.ajouterTexteDansZone(pseudos_.get(player_)+ContainerGame.INTRODUCTION_PTS+contrat_+ContainerBelote.RETURN_LINE_CHAR);
            String event_ = StringList.concat(pseudos_.get(player_),ContainerGame.INTRODUCTION_PTS,Games.toString(contrat_,lg_),ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//            container.ajouterTexteDansZone(event_);
            if (partie_.tailleContrats() == partie_.getNombreDeJoueurs()) {
                partie_.finEncherePremierTour();
            }
            container.pause();
        }
        SwingUtilities.invokeLater(new AfterAnimationBidBelote(container));
//        container.getPanneauBoutonsJeu().removeAll();
//        if(partie_.keepBidding()) {
//            container.setCanBid(true);
//            if (!partie_.getRegles().dealAll()) {
//                for(BidBeloteSuit e:partie_.allowedBids()){
//                    container.ajouterBoutonContratBelote(e.toString(),e,e.estDemandable(partie_.getContrat()));
//                }
//            } else {
//                container.addButtonsForCoinche(partie_);
//            }
//        } else if(partie_.getContrat().jouerDonne()) {
//            container.getMini().setStatus(Status.TAKER, partie_.getPreneur());
//            container.getMini().setStatus(Status.CALLED_PLAYER, partie_.partenaires(partie_.getPreneur()).first());
//            container.addButtonNextTrickBelote(container.getMessages().getVal(MainWindow.GO_CARD_GAME), true);
//        } else {
//            container.addButtonEndDealBelote(container.getMessages().getVal(MainWindow.END_DEAL), true);
//        }
//        container.setThreadAnime(false);
//        PackingWindowAfter.pack(container);
    }
}
