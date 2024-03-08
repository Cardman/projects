package cards.gui.animations;

import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSingleImpl;
import cards.gui.containers.ContainerSingleTarot;
import cards.tarot.DealTarot;
import cards.tarot.GameTarot;
import code.gui.MenuItemUtils;
import code.sml.util.TranslationsLg;
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
//        container.setThreadAnime(true);
        MenuItemUtils.setEnabledMenu(container.getConsulting(),false);
        GameTarot partie_=container.partieTarot();
        TranslationsLg lg_ = container.getOwner().getFrames().currentLg();
        if (partie_.getPliEnCours().estVide() && !container.getParametres().getAttentePlisClic()) {
            long delaiPli_ = container.getParametres().getDelaiAttentePlis();
            ThreadUtil.sleep(container.getOwner().getThreadFactory(), delaiPli_);
            //Le joueur reflechit pendant 0.5 s
            //container.tapisTarot().setEcart(partie_.getDistribution().derniereMain());
            container.tapisTarot().setCartesTarotJeu(container.getWindow().getImageFactory(), lg_, partie_.getNombreDeJoueurs());
        }
        container.getOwner().getFrames().getCompoFactory().invokeNow(new ChangingPause(container, true));
        //Activer le menu Partie/Pause
//        MenuItemUtils.setEnabledMenu(container.getPause(),true);
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
        TranslationsLg lg_ = _container.getOwner().getFrames().currentLg();
        while (true) {
            if (_container.window().getPausingCardsAnims().state(_container) == ContainerSingleImpl.PAUSE_STOPPED) {
                _container.setState(CardAnimState.TRICK_TAROT);
                ContainerSingleImpl.putInPause(_container);
                return;
            }
            if (!partie_.keepPlayingCurrentTrick()) {
                partie_.ajouterPetitAuBoutPliEnCours();
                if (_container.getParametres().getAttentePlisClic()) {
                    _container.getOwner().getFrames().getCompoFactory().invokeNow(new AfterAnimationCardTarot(_container, ContainerGame.CLICK_TRICK));
                    return;
                }
                long delaiPli_= _container.getParametres().getDelaiAttentePlis();
                ThreadUtil.sleep(_container.getOwner().getThreadFactory(),delaiPli_);
                //Le joueur reflechit pendant 0.5 s
                if (!partie_.keepPlayingCurrentGame()) {
                    _container.getOwner().getFrames().getCompoFactory().invokeNow(new AfterAnimationCardTarot(_container, ContainerGame.END_GAME));
                    return;
                }
                //container.tapisTarot().setEcart(partie_.getDistribution().derniereMain());
                _container.tapisTarot().setCartesTarotJeu(_container.getWindow().getImageFactory(), lg_, partie_.getNombreDeJoueurs());
                //validate container.pack();
            }
            byte player_ = partie_.playerHavingToPlay();
            if (player_ == DealTarot.NUMERO_UTILISATEUR) {
                _container.getOwner().getFrames().getCompoFactory().invokeNow(new AfterAnimationCardTarot(_container, ContainerGame.USER_INSTANT));
                return;
            }
            ThreadUtil.sleep(_container.getOwner().getThreadFactory(), delaiCarte_);
            //Le joueur reflechit pendant 0.5 s
            _container.jouerTarot(player_, pseudos_.get(player_));
//            if (_container.isPasse()) {
//                _container.setState(CardAnimState.TRICK_TAROT);
//                return;
//            }
        }
    }
}
