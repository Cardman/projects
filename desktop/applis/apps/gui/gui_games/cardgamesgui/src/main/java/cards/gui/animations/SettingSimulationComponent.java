package cards.gui.animations;

/**This class thread is used by EDT (invokeLater of SwingUtilities),
Thread safe class*/
public final class SettingSimulationComponent implements Runnable {

    private SimulationGame simulation;

    /**This class thread is used by EDT (invokeLater of SwingUtilities)*/
    public SettingSimulationComponent(SimulationGame _simulation) {
        simulation = _simulation;
    }

    @Override
    public void run() {
        simulation.setSimulationGui();
    }
}
