package cards.gui.containers;
import cards.gui.MainWindow;
import cards.gui.animations.SimulationGamePresident;
import code.gui.CustComponent;

public class ContainerSimuPresident extends ContainerPresident implements
        ContainerSimu {

    public ContainerSimuPresident(MainWindow _window) {
        super(_window);
        CustComponent.newThread(new SimulationGamePresident(this)).start();
    }

    @Override
    public void withdrawCards() {
        tapisPresident().setTalonPresident();
//        tapisPresident().repaintValidate();
    }

}
