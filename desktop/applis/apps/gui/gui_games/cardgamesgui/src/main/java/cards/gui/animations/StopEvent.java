package cards.gui.animations;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;


public class StopEvent extends AbsMouseListenerRel {

    private SimulationGame simulation;

    public StopEvent(SimulationGame _simulation) {
        simulation = _simulation;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        simulation.stopSimulation();
    }
}
