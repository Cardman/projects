package cards.gui.animations;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSingleTarot;
import code.gui.MenuItemUtils;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class AfterAnimationCardTarot implements Runnable {

    private final ContainerSingleTarot container;
    private final int kindExit;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public AfterAnimationCardTarot(ContainerSingleTarot _container, int _k) {
        container = _container;
        kindExit = _k;
    }

    @Override
    public void run() {
        //Desactiver le menu Partie/Pause
        MenuItemUtils.setEnabledMenu(container.getPause(),false);
        if(kindExit == ContainerGame.USER_INSTANT) {
            container.setThreadAnime(false);
            container.placerBoutonsAvantJeuUtilisateurTarot();
        } else if(kindExit == ContainerGame.END_GAME) {
            container.finPartieTarot();
        } else {
            container.setThreadAnime(false);
            container.placerBoutonsFinPliUtilisateurTarot();
        }
        container.pack();
    }
}
