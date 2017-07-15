package cards.gui.animations;
import cards.gui.containers.ContainerGame;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class GoSimulateGame extends Thread {

    private Thread simulation;

    private ContainerGame container;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public GoSimulateGame(Thread _simulation, ContainerGame _container) {
        simulation = _simulation;
        container = _container;
    }

    @Override
    public void run() {
        container.pack();
        simulation.start();
    }
}
