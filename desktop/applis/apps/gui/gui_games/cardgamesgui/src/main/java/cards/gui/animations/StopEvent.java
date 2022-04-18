package cards.gui.animations;
import code.gui.events.AbsActionListener;


public class StopEvent implements AbsActionListener {

    private SimulationGame simulation;

    public StopEvent(SimulationGame _simulation) {
        simulation = _simulation;
    }

    @Override
    public void action() {
        simulation.stopSimulation();
    }
}
