package cards.gui.animations;

import cards.gui.containers.ContainerSingleImpl;
import cards.gui.containers.ContainerSinglePresident;
import cards.president.DealPresident;
import cards.president.GamePresident;
import code.threads.ThreadUtil;
import code.util.StringList;

/**This class thread is independant from EDT,
Thread safe class*/
public final class AnimationCardPresident implements Runnable {

    private final ContainerSinglePresident container;

    /**This class thread is independant from EDT*/
    public AnimationCardPresident(ContainerSinglePresident _container) {
        container = _container;
    }

    @Override
    public void run() {
//        container.setThreadAnime(true);
        container.getConsulting().setEnabled(false);
//        if(partie_.getProgressingTrick().estVide()) {
//            long delaiPli_;
//            if(!container.getParametres().getAttentePlisClic()) {
//                delaiPli_=container.getParametres().getDelaiAttentePlis();
//                Constants.sleep(delaiPli_);//Le joueur reflechit pendant 0.5 s
//                container.tapisPresident().setTalonPresident();
//            }
//        }
        //Activer le menu Partie/Pause
        container.getOwner().getFrames().getCompoFactory().invokeNow(new ChangingPause(container));
//        container.getPause().setEnabled(true);
        loopTrick(container);
    }

    static void loopTrick(ContainerSinglePresident _container) {
        StringList pseudos_ = _container.pseudosPresident();
        long delaiCarte_ = _container.getParametres().getDelaiAttenteCartes();
        GamePresident partie_= _container.partiePresident();
        while (true) {
            if (_container.window().getPausingCardsAnims().state(_container) == ContainerSingleImpl.PAUSE_STOPPED) {
                _container.getOwner().getFrames().getCompoFactory().invokeNow(new ChangingPauseAfter(_container,CardAnimState.TRICK_PRESIDENT));
                return;
            }
            if (partie_.getProgressingTrick().estVide()) {
//                if (container.getParametres().getAttentePlisClic()) {
//                    break;
//                }
                long delaiPli_= _container.getParametres().getDelaiAttentePlis();
                ThreadUtil.sleep(_container.getOwner().getThreadFactory(),delaiPli_);
                //Le joueur reflechit pendant 0.5 s
                if (!partie_.keepPlayingCurrentGame()) {
                    _container.getOwner().getFrames().getCompoFactory().invokeNow(new AfterAnimationCardPresident(_container));
                    return;
                }
                _container.getOwner().getFrames().getCompoFactory().invokeNow(new SettingPresidentDeck(_container));
//                container.tapisPresident().setTalonPresident();
//                container.tapisPresident().repaintValidate();
            }
//            if (!partie_.keepPlayingCurrentTrick()) {
//                if (container.getParametres().getAttentePlisClic()) {
//                    break;
//                }
//                long delaiPli_=container.getParametres().getDelaiAttentePlis();
//                Constants.sleep(delaiPli_);//Le joueur reflechit pendant 0.5 s
//                if (!partie_.keepPlayingCurrentGame()) {
//                    break;
//                }
//                container.tapisPresident().setTalonPresident();
//                //validate container.pack();
//            }
            int player_ = partie_.nextPlayer();
            if (player_ == DealPresident.NUMERO_UTILISATEUR) {
                _container.getOwner().getFrames().getCompoFactory().invokeNow(new AfterAnimationCardPresident(_container));
                return;
            }
            ThreadUtil.sleep(_container.getOwner().getThreadFactory(), delaiCarte_);
            //Le joueur reflechit pendant 0.5 s
            _container.jouerPresident(pseudos_.get(player_));
//            if (_container.isPasse()) {
//                _container.setState(CardAnimState.TRICK_PRESIDENT);
//                return;
//            }
//            if (partie_.getProgressingTrick().estVide()) {
//                Constants.sleep(delaiCarte_);
//            }
        }
    }
}
