package cards.gui.containers;
import cards.gui.WindowCards;
import cards.gui.animations.SimulationGameTarot;

public class ContainerSimuTarot extends ContainerTarot implements ContainerSimu {

    private SimulationGameTarot animationSimulation;
    public ContainerSimuTarot(WindowCards _window) {
        super(_window);
        animationSimulation=new SimulationGameTarot(this);
        getOwner().getThreadFactory().newStartedThread(animationSimulation);
    }

    @Override
    public void withdrawCards() {
        tapisTarot().retirerCartes();
    }

}

