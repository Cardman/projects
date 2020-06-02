package cards.gui.animations;
import cards.gui.containers.ContainerGame;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class StopDemo implements Runnable {

    private ContainerGame container;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public StopDemo(ContainerGame _container) {
        container = _container;
    }

    @Override
    public void run() {
        container.getOwner().menuSoloGames();
    }
}
