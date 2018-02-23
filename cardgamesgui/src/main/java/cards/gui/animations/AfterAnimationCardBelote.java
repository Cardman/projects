package cards.gui.animations;
import cards.belote.GameBelote;
import cards.gui.containers.ContainerSingleBelote;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class AfterAnimationCardBelote extends Thread {

    private ContainerSingleBelote container;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public AfterAnimationCardBelote(ContainerSingleBelote _container) {
        container = _container;
    }

    @Override
    public void run() {
        GameBelote currentGame_=container.partieBelote();
        //Desactiver le menu Partie/Pause
        container.getPause().setEnabledMenu(false);
        if(currentGame_.keepPlayingCurrentTrick()) {
            container.setThreadAnime(false);
            container.placerBoutonsAvantJeuUtilisateurBelote(currentGame_.premierTour());
        } else {
            if (currentGame_.keepPlayingCurrentGame() && container.getParametres().getAttentePlisClic()) {
                container.setThreadAnime(false);
                container.placerBoutonsFinPliUtilisateurBelote();
            } else {
                container.finPartieBelote();
            }
        }
        container.pack();
    }
}
