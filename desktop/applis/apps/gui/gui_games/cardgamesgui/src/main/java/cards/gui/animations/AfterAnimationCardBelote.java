package cards.gui.animations;
import cards.gui.containers.ContainerSingleBelote;
import code.gui.MenuItemUtils;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class AfterAnimationCardBelote implements Runnable {

    private final ContainerSingleBelote container;
    private final int kindExit;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public AfterAnimationCardBelote(ContainerSingleBelote _container, int _k) {
        container = _container;
        kindExit = _k;
    }

    @Override
    public void run() {
        //Desactiver le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(container.getPause(),false);
        if(kindExit == AnimationCardBelote.USER_INSTANT) {
            container.setThreadAnime(false);
            container.placerBoutonsAvantJeuUtilisateurBelote();
        } else if(kindExit == AnimationCardBelote.END_GAME) {
            container.finPartieBelote();
        } else {
            container.setThreadAnime(false);
            container.placerBoutonsFinPliUtilisateurBelote();
        }
        container.pack();
    }
}
