package cards.gui.animations;

import cards.gui.containers.ContainerSinglePresident;
import cards.president.DealPresident;
import cards.president.GamePresident;
import code.gui.FrameUtil;
import code.gui.ThreadInvoker;
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
        container.setThreadAnime(true);
//        if(partie_.getProgressingTrick().estVide()) {
//            long delaiPli_;
//            if(!container.getParametres().getAttentePlisClic()) {
//                delaiPli_=container.getParametres().getDelaiAttentePlis();
//                Constants.sleep(delaiPli_);//Le joueur reflechit pendant 0.5 s
//                container.tapisPresident().setTalonPresident();
//            }
//        }
        //Activer le menu Partie/Pause
        ThreadInvoker.invokeNow(container.getOwner().getThreadFactory(),new ChangingPause(container, true), container.getOwner().getFrames());
//        container.getPause().setEnabled(true);
        loopTrick(container);
    }

    static void loopTrick(ContainerSinglePresident _container) {
        StringList pseudos_ = _container.pseudosPresident();
        long delaiCarte_ = _container.getParametres().getDelaiAttenteCartes();
        GamePresident partie_= _container.partiePresident();
        while (true) {
            if (partie_.getProgressingTrick().estVide()) {
//                if (container.getParametres().getAttentePlisClic()) {
//                    break;
//                }
                long delaiPli_= _container.getParametres().getDelaiAttentePlis();
                ThreadUtil.sleep(_container.getOwner().getThreadFactory(),delaiPli_);
                //Le joueur reflechit pendant 0.5 s
                if (!partie_.keepPlayingCurrentGame()) {
                    break;
                }
                ThreadInvoker.invokeNow(_container.getOwner().getThreadFactory(),new SettingPresidentDeck(_container), _container.getOwner().getFrames());
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
            byte player_ = partie_.getNextPlayer();
            if (player_ == DealPresident.NUMERO_UTILISATEUR) {
                break;
            }
            ThreadUtil.sleep(_container.getOwner().getThreadFactory(), delaiCarte_);
            //Le joueur reflechit pendant 0.5 s
            _container.jouerPresident(player_, pseudos_.get(player_));
//            if (_container.isPasse()) {
//                _container.setState(CardAnimState.TRICK_PRESIDENT);
//                return;
//            }
//            if (partie_.getProgressingTrick().estVide()) {
//                Constants.sleep(delaiCarte_);
//            }
        }
        FrameUtil.invokeLater(new AfterAnimationCardPresident(_container), _container.getOwner().getFrames());
    }
}
