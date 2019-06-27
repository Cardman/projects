package cards.gui.containers;
import cards.gui.MainWindow;
import cards.gui.animations.SimulationGameBelote;

public class ContainerSimuBelote extends ContainerBelote implements ContainerSimu {

    private SimulationGameBelote animationSimulation;
    public ContainerSimuBelote(MainWindow _window) {
        super(_window);
        animationSimulation=new SimulationGameBelote(this);
        animationSimulation.start();
    }

    @Override
    public void withdrawCards() {
        tapisBelote().retirerCartes();
    }

}

