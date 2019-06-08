package cards.gui.animations;
import cards.gui.containers.ContainerSingleTarot;
import cards.tarot.GameTarot;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class AfterAnimationCardTarot extends Thread {

    private ContainerSingleTarot container;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public AfterAnimationCardTarot(ContainerSingleTarot _container) {
        container = _container;
    }

    @Override
    public void run() {
        GameTarot currentGame_=container.partieTarot();
        //Desactiver le menu Partie/Pause
        container.getPause().setEnabledMenu(false);
        if(currentGame_.keepPlayingCurrentTrick()) {
            container.setThreadAnime(false);
            container.placerBoutonsAvantJeuUtilisateurTarot();
        } else {
            if (currentGame_.keepPlayingCurrentGame() && container.getParametres().getAttentePlisClic()) {
                container.setThreadAnime(false);
                container.placerBoutonsFinPliUtilisateurTarot();
            } else {
                container.finPartieTarot();
            }
        }
        container.pack();
    }
}
