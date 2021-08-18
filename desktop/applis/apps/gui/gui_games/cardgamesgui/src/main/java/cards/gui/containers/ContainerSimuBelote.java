package cards.gui.containers;
import cards.gui.WindowCards;
import cards.gui.animations.SimulationGameBelote;

public class ContainerSimuBelote extends ContainerBelote implements ContainerSimu {

    private SimulationGameBelote animationSimulation;
    public ContainerSimuBelote(WindowCards _window) {
        super(_window);
        animationSimulation=new SimulationGameBelote(this);
        getOwner().getThreadFactory().newStartedThread(animationSimulation);
    }

    @Override
    public void withdrawCards() {
        tapisBelote().retirerCartes();
    }

}

