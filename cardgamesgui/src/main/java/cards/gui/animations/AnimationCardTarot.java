package cards.gui.animations;
import javax.swing.SwingUtilities;

import code.util.StringList;
import code.util.consts.Constants;
import cards.gui.containers.ContainerSingleTarot;
import cards.tarot.DealTarot;
import cards.tarot.GameTarot;

/**This class thread is independant from EDT,
Thread safe class*/
public final class AnimationCardTarot extends Thread {

    private ContainerSingleTarot container;

    /**This class thread is independant from EDT*/
    public AnimationCardTarot(ContainerSingleTarot _container) {
        container = _container;
    }

    @Override
    public void run() {
        StringList pseudos_ = container.pseudosTarot();
        container.setThreadAnime(true);
        long delaiCarte_;
        delaiCarte_=container.getParametres().getDelaiAttenteCartes();
        GameTarot partie_=container.partieTarot();
        if(partie_.getPliEnCours().estVide()) {
            long delaiPli_;
            if(!container.getParametres().getAttentePlisClic()) {
                delaiPli_=container.getParametres().getDelaiAttentePlis();
                Constants.sleep(delaiPli_);
                //Le joueur reflechit pendant 0.5 s
                //container.tapisTarot().setEcart(partie_.getDistribution().derniereMain());
                container.tapisTarot().setCartesTarotJeu(partie_.getNombreDeJoueurs());
            }
        }
        //Activer le menu Partie/Pause
        container.getPause().setEnabled(true);
        while (true) {
            if (!partie_.keepPlayingCurrentTrick()) {
                partie_.ajouterPetitAuBoutPliEnCours();
                if (container.getParametres().getAttentePlisClic()) {
                    break;
                }
                long delaiPli_=container.getParametres().getDelaiAttentePlis();
                Constants.sleep(delaiPli_);
                //Le joueur reflechit pendant 0.5 s
                if (!partie_.keepPlayingCurrentGame()) {
                    break;
                }
                //container.tapisTarot().setEcart(partie_.getDistribution().derniereMain());
                container.tapisTarot().setCartesTarotJeu(partie_.getNombreDeJoueurs());
                //validate container.pack();
                partie_.setPliEnCours(true);
            }
            byte player_ = partie_.playerHavingToPlay();
            if (player_ == DealTarot.NUMERO_UTILISATEUR) {
                break;
            }
            Constants.sleep(delaiCarte_);
            //Le joueur reflechit pendant 0.5 s
            container.jouerTarot(player_,pseudos_.get(player_),partie_.premierTour());
            container.pause();
        }
        SwingUtilities.invokeLater(new AfterAnimationCardTarot(container));
//        if(partie_.keepPlayingCurrentTrick())
//        {
//            container.setThreadAnime(false);
//            container.placerBoutonsAvantJeuUtilisateurTarot(partie_.premierTour());
//        }
//        else
//        {
//            if (partie_.keepPlayingCurrentGame() && container.getParametres().getAttentePlisClic()) {
//                container.setThreadAnime(false);
//                container.placerBoutonsFinPliUtilisateurTarot();
//            } else {
//                container.finPartieTarot();
//            }
//        }
//        PackingWindowAfter.pack(container);
    }
}
