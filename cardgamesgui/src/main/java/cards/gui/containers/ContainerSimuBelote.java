package cards.gui.containers;
import cards.gui.MainWindow;
import cards.gui.animations.LoadingVideo;
import cards.gui.animations.SimulationGameBelote;

public class ContainerSimuBelote extends ContainerBelote implements ContainerSimu {

    private SimulationGameBelote animationSimulation;
    public ContainerSimuBelote(MainWindow _window) {
        super(_window);
        setAnimChargement(new LoadingVideo(this));
        animationSimulation=new SimulationGameBelote(this);
        animationSimulation.start();
        getAnimChargement().start();
    }

    @Override
    public void withdrawCards() {
        tapisBelote().retirerCartes();
    }

}

