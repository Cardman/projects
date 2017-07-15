package cards.gui.containers;
import cards.gui.MainWindow;
import cards.gui.animations.LoadingVideo;
import cards.gui.animations.SimulationGameTarot;

public class ContainerSimuTarot extends ContainerTarot implements ContainerSimu {

    private SimulationGameTarot animationSimulation;
    public ContainerSimuTarot(MainWindow _window) {
        super(_window);
        setAnimChargement(new LoadingVideo(this));
        animationSimulation=new SimulationGameTarot(this);
        animationSimulation.start();
        getAnimChargement().start();
    }

    @Override
    public void withdrawCards() {
        tapisTarot().retirerCartes();
    }

}

