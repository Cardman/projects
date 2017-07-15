package cards.gui.containers;
import cards.gui.MainWindow;
import cards.gui.animations.LoadingVideo;
import cards.gui.animations.SimulationGamePresident;

public class ContainerSimuPresident extends ContainerPresident implements
        ContainerSimu {

    private SimulationGamePresident animationSimulation;
    public ContainerSimuPresident(MainWindow _window) {
        super(_window);
        setAnimChargement(new LoadingVideo(this));
        animationSimulation=new SimulationGamePresident(this);
        animationSimulation.start();
        getAnimChargement().start();
    }

    @Override
    public void withdrawCards() {
        tapisPresident().setTalonPresident();
//        tapisPresident().repaintValidate();
    }

}
