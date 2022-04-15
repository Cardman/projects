package cards.gui.animations;

import cards.gui.containers.ContainerSingleTarot;
import cards.tarot.DealTarot;
import cards.tarot.GameTarot;
import code.gui.FrameUtil;
import code.threads.ThreadUtil;
import code.util.StringList;

/**This class thread is independant from EDT,
Thread safe class*/
public final class AnimationCardTarot implements Runnable {

    private final ContainerSingleTarot container;

    /**This class thread is independant from EDT*/
    public AnimationCardTarot(ContainerSingleTarot _container) {
        container = _container;
    }

    @Override
    public void run() {
        container.setThreadAnime(true);
        GameTarot partie_=container.partieTarot();
        String lg_ = container.getOwner().getLanguageKey();
        if (partie_.getPliEnCours().estVide() && !container.getParametres().getAttentePlisClic()) {
            long delaiPli_ = container.getParametres().getDelaiAttentePlis();
            ThreadUtil.sleep(container.getOwner().getThreadFactory(), delaiPli_);
            //Le joueur reflechit pendant 0.5 s
            //container.tapisTarot().setEcart(partie_.getDistribution().derniereMain());
            container.tapisTarot().setCartesTarotJeu(container.getWindow().getImageFactory(), lg_, partie_.getNombreDeJoueurs());
        }
        //Activer le menu Partie/Pause
        container.getPause().setEnabledMenu(true);
        loopTrick(container);
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

    static void loopTrick(ContainerSingleTarot _container) {
        StringList pseudos_ = _container.pseudosTarot();
        long delaiCarte_ = _container.getParametres().getDelaiAttenteCartes();
        GameTarot partie_= _container.partieTarot();
        String lg_ = _container.getOwner().getLanguageKey();
        while (true) {
            if (!partie_.keepPlayingCurrentTrick()) {
                partie_.ajouterPetitAuBoutPliEnCours();
                partie_.setPliEnCours(true);
                if (_container.getParametres().getAttentePlisClic()) {
                    break;
                }
                long delaiPli_= _container.getParametres().getDelaiAttentePlis();
                ThreadUtil.sleep(_container.getOwner().getThreadFactory(),delaiPli_);
                //Le joueur reflechit pendant 0.5 s
                if (!partie_.keepPlayingCurrentGame()) {
                    break;
                }
                //container.tapisTarot().setEcart(partie_.getDistribution().derniereMain());
                _container.tapisTarot().setCartesTarotJeu(_container.getWindow().getImageFactory(), lg_, partie_.getNombreDeJoueurs());
                //validate container.pack();
            }
            byte player_ = partie_.playerHavingToPlay();
            if (player_ == DealTarot.NUMERO_UTILISATEUR) {
                break;
            }
            ThreadUtil.sleep(_container.getOwner().getThreadFactory(), delaiCarte_);
            //Le joueur reflechit pendant 0.5 s
            _container.jouerTarot(player_, pseudos_.get(player_));
//            if (_container.isPasse()) {
//                _container.setState(CardAnimState.TRICK_TAROT);
//                return;
//            }
        }
        FrameUtil.invokeLater(new AfterAnimationCardTarot(_container), _container.getOwner().getFrames());
    }
}
