package cards.gui.animations;

import cards.belote.DealBelote;
import cards.belote.GameBelote;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSingleBelote;
import cards.gui.containers.ContainerSingleImpl;
import code.gui.MenuItemUtils;
import code.sml.util.TranslationsLg;
import code.threads.ThreadUtil;
import code.util.StringList;

/**This class thread is independant from EDT,
Thread safe class*/
public final class AnimationCardBelote implements Runnable {

    private final ContainerSingleBelote container;

    /**This class thread is independant from EDT*/
    public AnimationCardBelote(ContainerSingleBelote _container) {
        container = _container;
    }

    @Override
    public void run() {
//        container.setThreadAnime(true);
        MenuItemUtils.setEnabledMenu(container.getConsulting(),false);
        GameBelote partie_=container.partieBelote();
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        if (partie_.pliEnCoursEstVide() && !container.getParametres().getAttentePlisClic()) {
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
            if (_container.window().getPausingCardsAnims().state(_container) == ContainerSingleImpl.PAUSE_STOPPED) {
                _container.window().changeStreamsMenusEnabled(true);
                _container.window().getPause().setEnabled(true);
                _container.setState(CardAnimState.TRICK_BELOTE);
                return;
            }
            if (!partie_.keepPlayingCurrentTrick()) {
                partie_.ajouterDixDeDerPliEnCours();
                if (_container.getParametres().getAttentePlisClic()) {
                    _container.getOwner().getFrames().getCompoFactory().invokeNow(new AfterAnimationCardBelote(_container, ContainerGame.CLICK_TRICK));
                    return;
                }
                long delaiPli_= _container.getParametres().getDelaiAttentePlis();
                ThreadUtil.sleep(_container.getOwner().getThreadFactory(),delaiPli_);
                //Le joueur reflechit pendant 0.5 s
                if (!partie_.keepPlayingCurrentGame()) {
                    _container.getOwner().getFrames().getCompoFactory().invokeNow(new AfterAnimationCardBelote(_container, ContainerGame.END_GAME));
                    return;
                }
                _container.tapisBelote().setCartesBeloteJeu(_container.getWindow().getImageFactory(), partie_.getNombreDeJoueurs(), lg_);
                //validate container.pack();
            }
            byte player_ = partie_.playerHavingToPlay();
            if (player_ == DealBelote.NUMERO_UTILISATEUR) {
                _container.getOwner().getFrames().getCompoFactory().invokeNow(new AfterAnimationCardBelote(_container, ContainerGame.USER_INSTANT));
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
