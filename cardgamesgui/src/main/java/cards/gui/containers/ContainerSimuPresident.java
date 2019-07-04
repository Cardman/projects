package cards.gui.containers;
import cards.gui.MainWindow;
import cards.gui.animations.SimulationGamePresident;

public class ContainerSimuPresident extends ContainerPresident implements
        ContainerSimu {

    public ContainerSimuPresident(MainWindow _window) {
        super(_window);
        new Thread(new SimulationGamePresident(this)).start();
    }

    @Override
    public void withdrawCards() {
        tapisPresident().setTalonPresident();
//        tapisPresident().repaintValidate();
    }

}
