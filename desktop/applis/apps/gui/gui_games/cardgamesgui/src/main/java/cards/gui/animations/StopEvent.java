package cards.gui.animations;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StopEvent extends MouseAdapter {

    private SimulationGame simulation;

    public StopEvent(SimulationGame _simulation) {
        simulation = _simulation;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        simulation.stopSimulation();
    }
}
