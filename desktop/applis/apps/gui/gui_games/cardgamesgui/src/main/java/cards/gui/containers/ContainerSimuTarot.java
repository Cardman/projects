package cards.gui.containers;
import cards.gui.WindowCards;
import cards.gui.animations.SimulationGameTarot;

public class ContainerSimuTarot extends ContainerTarot implements ContainerSimu {

//    private SimulationGameTarot animationSimulation;
    private final WindowCards win;
    public ContainerSimuTarot(WindowCards _window) {
        super(_window);
        update(_window);
        win = _window;
//        animationSimulation=new SimulationGameTarot(this,_window);
        thread(new SimulationGameTarot(this,_window));
    }

//    @Override
//    public void withdrawCards() {
//        tapisTarot().retirerCartes();
//    }

    @Override
    public WindowCards window() {
        return win;
    }
}

