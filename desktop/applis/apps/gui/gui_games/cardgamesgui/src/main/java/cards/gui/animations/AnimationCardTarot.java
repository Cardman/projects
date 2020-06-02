package cards.gui.animations;

import cards.gui.containers.ContainerSingleTarot;
import cards.tarot.DealTarot;
import cards.tarot.GameTarot;
import code.gui.CustComponent;
import code.threads.ThreadUtil;
import code.util.StringList;

/**This class thread is independant from EDT,
Thread safe class*/
public final class AnimationCardTarot implements Runnable {

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
        String lg_ = container.getOwner().getLanguageKey();
        if(partie_.getPliEnCours().estVide()) {
            long delaiPli_;
            if(!container.getParametres().getAttentePlisClic()) {
                delaiPli_=container.getParametres().getDelaiAttentePlis();
                ThreadUtil.sleep(delaiPli_);
                //Le joueur reflechit pendant 0.5 s
                //container.tapisTarot().setEcart(partie_.getDistribution().derniereMain());
                container.tapisTarot().setCartesTarotJeu(lg_,partie_.getNombreDeJoueurs());
            }
        }
        //Activer le menu Partie/Pause
        container.getPause().setEnabledMenu(true);
        while (true) {
            if (!partie_.keepPlayingCurrentTrick()) {
                partie_.ajouterPetitAuBoutPliEnCours();
                partie_.setPliEnCours(true);
                if (container.getParametres().getAttentePlisClic()) {
                    break;
                }
                long delaiPli_=container.getParametres().getDelaiAttentePlis();
                ThreadUtil.sleep(delaiPli_);
                //Le joueur reflechit pendant 0.5 s
                if (!partie_.keepPlayingCurrentGame()) {
                    break;
                }
                //container.tapisTarot().setEcart(partie_.getDistribution().derniereMain());
                container.tapisTarot().setCartesTarotJeu(lg_,partie_.getNombreDeJoueurs());
                //validate container.pack();
            }
            byte player_ = partie_.playerHavingToPlay();
            if (player_ == DealTarot.NUMERO_UTILISATEUR) {
                break;
            }
            ThreadUtil.sleep(delaiCarte_);
            //Le joueur reflechit pendant 0.5 s
            container.jouerTarot(player_,pseudos_.get(player_));
            container.pause();
        }
        CustComponent.invokeLater(new AfterAnimationCardTarot(container));
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
