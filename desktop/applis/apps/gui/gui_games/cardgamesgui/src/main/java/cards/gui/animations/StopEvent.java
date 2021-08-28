package cards.gui.animations;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;


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
