package cards.gui.containers;
import cards.gui.MainWindow;
import cards.gui.animations.SimulationGameTarot;
import code.gui.CustComponent;

public class ContainerSimuTarot extends ContainerTarot implements ContainerSimu {

    private SimulationGameTarot animationSimulation;
    public ContainerSimuTarot(MainWindow _window) {
        super(_window);
        animationSimulation=new SimulationGameTarot(this);
        CustComponent.newThread(animationSimulation).start();
    }

    @Override
    public void withdrawCards() {
        tapisTarot().retirerCartes();
    }

}

