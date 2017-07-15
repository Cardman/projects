package cards.gui.animations;
import javax.swing.SwingUtilities;

import code.gui.ThreadInvoker;
import code.util.StringList;
import code.util.consts.Constants;
import cards.belote.DealBelote;
import cards.belote.GameBelote;
import cards.gui.containers.ContainerSingleBelote;

/**This class thread is independant from EDT,
Thread safe class*/
public final class AnimationCardBelote extends Thread {

    private ContainerSingleBelote container;

    /**This class thread is independant from EDT*/
    public AnimationCardBelote(ContainerSingleBelote _container) {
        container = _container;
    }

    @Override
    public void run() {
        container.setThreadAnime(true);
        StringList pseudos_ = container.pseudosBelote();
        long delaiCarte_;
        delaiCarte_=container.getParametres().getDelaiAttenteCartes();
        GameBelote partie_=container.partieBelote();
        if(partie_.getPliEnCours().estVide()) {
            long delaiPli_;
            if(!container.getParametres().getAttentePlisClic()) {
                delaiPli_=container.getParametres().getDelaiAttentePlis();
                Constants.sleep(delaiPli_);
                //Le joueur reflechit pendant 0.5 s
                container.tapisBelote().setCartesBeloteJeu(partie_.getNombreDeJoueurs());
            }
        }
        //Activer le menu Partie/Pause
        ThreadInvoker.invokeNow(new ChangingPause(container, true));
//        container.getPause().setEnabled(true);
        while (true) {
            if (!partie_.keepPlayingCurrentTrick()) {
                partie_.ajouterDixDeDerPliEnCours();
                if(partie_.premierTour()) {
                    partie_.annulerAnnonces();
                }
                if (container.getParametres().getAttentePlisClic()) {
                    break;
                }
                long delaiPli_=container.getParametres().getDelaiAttentePlis();
                Constants.sleep(delaiPli_);
                //Le joueur reflechit pendant 0.5 s
                if (!partie_.keepPlayingCurrentGame()) {
                    break;
                }
                container.tapisBelote().setCartesBeloteJeu(partie_.getNombreDeJoueurs());
                //validate container.pack();
                partie_.setPliEnCours();
            }
            byte player_ = partie_.playerHavingToPlay();
            if (player_ == DealBelote.NUMERO_UTILISATEUR) {
                break;
            }
            Constants.sleep(delaiCarte_);
            //Le joueur reflechit pendant 0.5 s
            container.jouerBelote(player_,pseudos_.get(player_),partie_.premierTour());
            container.pause();
        }
        SwingUtilities.invokeLater(new AfterAnimationCardBelote(container));
//        //Desactiver le menu Partie/Pause
//        if(partie_.keepPlayingCurrentTrick())
//        {
//            container.setThreadAnime(false);
//            container.placerBoutonsAvantJeuUtilisateurBelote(partie_.premierTour());
//        }
//        else
//        {
//            if (partie_.keepPlayingCurrentGame() && container.getParametres().getAttentePlisClic()) {
//                container.setThreadAnime(false);
//                container.placerBoutonsFinPliUtilisateurBelote();
//            } else {
//                container.finPartieBelote();
//            }
//        }
//        PackingWindowAfter.pack(container);
    }
}
