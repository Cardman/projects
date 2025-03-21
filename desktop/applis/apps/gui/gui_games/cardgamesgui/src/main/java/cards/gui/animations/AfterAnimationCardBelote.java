package cards.gui.animations;
import cards.gui.containers.ContainerGame;
import cards.gui.containers.ContainerSingleBelote;

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
        container.getPause().setEnabled(false);
        if(kindExit == ContainerGame.USER_INSTANT) {
//            container.setThreadAnime(false);
            container.placerBoutonsAvantJeuUtilisateurBelote();
            container.window().changeStreamsMenusEnabled(true);
        } else if(kindExit == ContainerGame.END_GAME) {
            container.finPartieBelote();
        } else {
//            container.setThreadAnime(false);
            container.placerBoutonsFinPliUtilisateurBelote();
            container.window().changeStreamsMenusEnabled(true);
        }
        container.pack();
    }
}
