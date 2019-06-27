package cards.gui.containers;
import cards.gui.MainWindow;
import cards.gui.animations.SimulationGameTarot;

public class ContainerSimuTarot extends ContainerTarot implements ContainerSimu {

    private SimulationGameTarot animationSimulation;
    public ContainerSimuTarot(MainWindow _window) {
        super(_window);
        animationSimulation=new SimulationGameTarot(this);
        animationSimulation.start();
    }

    @Override
    public void withdrawCards() {
        tapisTarot().retirerCartes();
    }

}

