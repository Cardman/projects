package cards.gui.animations;

import cards.belote.DealBelote;
import cards.belote.GameBelote;
import cards.gui.containers.ContainerSingleBelote;
import code.sml.util.TranslationsLg;
import code.threads.ThreadUtil;
import code.util.StringList;

/**This class thread is independant from EDT,
Thread safe class*/
public final class AnimationCardBelote implements Runnable {

    public static final int USER_INSTANT = 0;
    public static final int END_GAME = 1;
    public static final int CLICK_TRICK = 2;
    private final ContainerSingleBelote container;

    /**This class thread is independant from EDT*/
    public AnimationCardBelote(ContainerSingleBelote _container) {
        container = _container;
    }

    @Override
    public void run() {
        container.setThreadAnime(true);
        GameBelote partie_=container.partieBelote();
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        if (partie_.getPliEnCours().estVide() && !container.getParametres().getAttentePlisClic()) {
            long delaiPli_ = container.getParametres().getDelaiAttentePlis();
            ThreadUtil.sleep(container.getOwner().getThreadFactory(), delaiPli_);
            //Le joueur reflechit pendant 0.5 s
            container.tapisBelote().setCartesBeloteJeu(container.getWindow().getImageFactory(), partie_.getNombreDeJoueurs(), lg_);
        }
        //Activer le menu Partie/Pause
        container.getOwner().getFrames().getCompoFactory().invokeNow(new ChangingPause(container, true));
//        container.getPause().setEnabled(true);
        loopTrick(container);
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

    static void loopTrick(ContainerSingleBelote _container) {
        StringList pseudos_ = _container.pseudosBelote();
        long delaiCarte_;
        delaiCarte_= _container.getParametres().getDelaiAttenteCartes();
        GameBelote partie_= _container.partieBelote();
        TranslationsLg lg_ = _container.getOwner().getFrames().currentLg();
        while (true) {
            if (!partie_.keepPlayingCurrentTrick()) {
                partie_.ajouterDixDeDerPliEnCours();
                if (_container.getParametres().getAttentePlisClic()) {
                    _container.getOwner().getFrames().getCompoFactory().invokeNow(new AfterAnimationCardBelote(_container, CLICK_TRICK));
                    return;
                }
                long delaiPli_= _container.getParametres().getDelaiAttentePlis();
                ThreadUtil.sleep(_container.getOwner().getThreadFactory(),delaiPli_);
                //Le joueur reflechit pendant 0.5 s
                if (!partie_.keepPlayingCurrentGame()) {
                    _container.getOwner().getFrames().getCompoFactory().invokeNow(new AfterAnimationCardBelote(_container, END_GAME));
                    return;
                }
                _container.tapisBelote().setCartesBeloteJeu(_container.getWindow().getImageFactory(), partie_.getNombreDeJoueurs(), lg_);
                //validate container.pack();
            }
            byte player_ = partie_.playerHavingToPlay();
            if (player_ == DealBelote.NUMERO_UTILISATEUR) {
                _container.getOwner().getFrames().getCompoFactory().invokeNow(new AfterAnimationCardBelote(_container, USER_INSTANT));
                return;
            }
            ThreadUtil.sleep(_container.getOwner().getThreadFactory(), delaiCarte_);
            //Le joueur reflechit pendant 0.5 s
            _container.jouerBelote(player_, pseudos_.get(player_));
//            if (_container.isPasse()) {
//                _container.setState(CardAnimState.TRICK_BELOTE);
//                return;
//            }
        }
    }
}
