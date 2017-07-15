package cards.gui.animations;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class EndSimulation extends Thread {

    private GoSimulate simulation;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public EndSimulation(GoSimulate _simulation) {
        simulation = _simulation;
    }

    @Override
    public void run() {
        simulation.endSimulation();
    }
}
