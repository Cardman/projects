package cards.gui.animations;
import javax.swing.SwingUtilities;

import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSingleTarot;
import cards.tarot.DealTarot;
import cards.tarot.GameTarot;
import cards.tarot.enumerations.BidTarot;
import code.gui.ThreadInvoker;
import code.util.StringList;
import code.util.consts.Constants;

/**This class thread is independant from EDT,
Thread safe class*/
public final class AnimationBidTarot extends Thread {

    private ContainerSingleTarot container;

    /**This class thread is independant from EDT*/
    public AnimationBidTarot(ContainerSingleTarot _container) {
        container = _container;
    }

    @Override
    public void run() {
        StringList pseudos_ = container.pseudosTarot();
        container.setThreadAnime(true);
        long delaiContrat_;
        GameTarot partie_=container.partieTarot();
        if (partie_.playerHavingToBid() == DealTarot.NUMERO_UTILISATEUR) {
            BidTarot contrat_=container.getContratUtilisateur();
            partie_.ajouterContrat(contrat_,DealTarot.NUMERO_UTILISATEUR);
            String event_ = StringList.concat(pseudos_.get(DealTarot.NUMERO_UTILISATEUR),ContainerGame.INTRODUCTION_PTS,contrat_.display(),ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//            container.ajouterTexteDansZone(event_);
//            container.ajouterTexteDansZone(pseudos_.get(DealTarot.NUMERO_UTILISATEUR)+ContainerGame.INTRODUCTION_PTS+contrat_+ContainerTarot.RETURN_LINE_CHAR);
        }
        delaiContrat_=container.getParametres().getDelaiAttenteContrats();
        //Activer le menu Partie/Pause
//        container.getPause().setEnabled(true);
        ThreadInvoker.invokeNow(new ChangingPause(container, true));
        while (true) {
            if(!partie_.keepBidding()){
                break;
            }
            byte player_ = partie_.playerHavingToBid();
            if (player_ == DealTarot.NUMERO_UTILISATEUR) {
                break;
            }
            //Les Fenetre.ROBOTS precedant l'utilisateur annoncent leur contrat
            Constants.sleep(delaiContrat_);
            BidTarot contrat_=partie_.strategieContrat();
            partie_.ajouterContrat(contrat_,player_);
            String event_ = StringList.concat(pseudos_.get(player_),ContainerGame.INTRODUCTION_PTS,contrat_.display(),ContainerGame.RETURN_LINE);
            ThreadInvoker.invokeNow(new AddTextEvents(container, event_));
//            container.ajouterTexteDansZone(event_);
//            container.ajouterTexteDansZone(pseudos_.get(player_)+ContainerGame.INTRODUCTION_PTS+contrat_+ContainerTarot.RETURN_LINE_CHAR);
            container.pause();
        }
        SwingUtilities.invokeLater(new AfterAnimationBidTarot(container));
    }
}
