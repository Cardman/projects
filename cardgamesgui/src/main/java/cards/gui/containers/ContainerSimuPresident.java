package cards.gui.containers;
import cards.gui.MainWindow;
import cards.gui.animations.SimulationGamePresident;

public class ContainerSimuPresident extends ContainerPresident implements
        ContainerSimu {

    private SimulationGamePresident animationSimulation;
    public ContainerSimuPresident(MainWindow _window) {
        super(_window);
        animationSimulation=new SimulationGamePresident(this);
        animationSimulation.start();
    }

    @Override
    public void withdrawCards() {
        tapisPresident().setTalonPresident();
//        tapisPresident().repaintValidate();
    }

}
