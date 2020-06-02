package cards.gui.animations;
import cards.gui.containers.ContainerSinglePresident;
import cards.president.GamePresident;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class AfterAnimationCardPresident implements Runnable {

    private ContainerSinglePresident container;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public AfterAnimationCardPresident(ContainerSinglePresident _container) {
        container = _container;
    }

    @Override
    public void run() {
        GamePresident currentGame_=container.partiePresident();
        //Desactiver le menu Partie/Pause
        container.getPause().setEnabledMenu(false);
        if (currentGame_.keepPlayingCurrentGame()) {
            container.setThreadAnime(false);
            container.placerBoutonsAvantJeuUtilisateurPresident();
        } else {
            container.finPartiePresident();
        }
//        if(currentGame_.getProgressingTrick().estVide()) {
//            if(!container.getParametres().getAttentePlisClic()) {
//                container.tapisPresident().setTalonPresident();
//            }
//        }
//        if(currentGame_.keepPlayingCurrentTrick()) {
//            container.setThreadAnime(false);
//            container.placerBoutonsAvantJeuUtilisateurPresident();
//        } else {
//            if (currentGame_.keepPlayingCurrentGame() && container.getParametres().getAttentePlisClic()) {
//                container.setThreadAnime(false);
//                container.placerBoutonsFinPliUtilisateurPresident();
//            } else {
//                container.finPartiePresident();
//            }
//        }
        container.pack();
    }
}
