package cards.gui.animations;
import javax.swing.SwingUtilities;

import cards.gui.containers.ContainerSinglePresident;
import cards.president.DealPresident;
import cards.president.GamePresident;
import code.gui.ThreadInvoker;
import code.util.StringList;
import code.util.consts.Constants;

/**This class thread is independant from EDT,
Thread safe class*/
public final class AnimationCardPresident extends Thread {

    private ContainerSinglePresident container;

    /**This class thread is independant from EDT*/
    public AnimationCardPresident(ContainerSinglePresident _container) {
        container = _container;
    }

    @Override
    public void run() {
        container.setThreadAnime(true);
        StringList pseudos_ = container.pseudosPresident();
        long delaiCarte_;
        delaiCarte_=container.getParametres().getDelaiAttenteCartes();
        GamePresident partie_=container.partiePresident();
//        if(partie_.getProgressingTrick().estVide()) {
//            long delaiPli_;
//            if(!container.getParametres().getAttentePlisClic()) {
//                delaiPli_=container.getParametres().getDelaiAttentePlis();
//                Constants.sleep(delaiPli_);//Le joueur reflechit pendant 0.5 s
//                container.tapisPresident().setTalonPresident();
//            }
//        }
        //Activer le menu Partie/Pause
        ThreadInvoker.invokeNow(new ChangingPause(container, true));
//        container.getPause().setEnabled(true);
        while (true) {
            if (partie_.getProgressingTrick().estVide()) {
//                if (container.getParametres().getAttentePlisClic()) {
//                    break;
//                }
                long delaiPli_=container.getParametres().getDelaiAttentePlis();
                Constants.sleep(delaiPli_);
                //Le joueur reflechit pendant 0.5 s
                if (!partie_.keepPlayingCurrentGame()) {
                    break;
                }
                ThreadInvoker.invokeNow(new SettingPresidentDeck(container));
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
            Constants.sleep(delaiCarte_);
            //Le joueur reflechit pendant 0.5 s
            container.jouerPresident(player_,pseudos_.get(player_));
            container.pause();
//            if (partie_.getProgressingTrick().estVide()) {
//                Constants.sleep(delaiCarte_);
//            }
        }
        SwingUtilities.invokeLater(new AfterAnimationCardPresident(container));
    }
}
