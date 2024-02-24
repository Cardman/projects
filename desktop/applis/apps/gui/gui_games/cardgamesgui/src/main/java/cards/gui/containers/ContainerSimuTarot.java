package cards.gui.containers;
import cards.gui.WindowCards;
import cards.gui.animations.SimulationGameTarot;

public class ContainerSimuTarot extends ContainerTarot implements ContainerSimuWithdraw {

//    private SimulationGameTarot animationSimulation;
    private final WindowCards win;
    public ContainerSimuTarot(WindowCards _window) {
        super(_window);
        win = _window;
//        animationSimulation=new SimulationGameTarot(this,_window);
        getOwner().getThreadFactory().newStartedThread(new SimulationGameTarot(this,_window));
    }

    @Override
    public void withdrawCards() {
        tapisTarot().retirerCartes();
    }

    @Override
    public WindowCards window() {
        return win;
    }
}

