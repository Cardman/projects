package cards.gui.containers;
import cards.gui.WindowCards;
import cards.gui.animations.SimulationGamePresident;

public class ContainerSimuPresident extends ContainerPresident implements
        ContainerSimu {

    public ContainerSimuPresident(WindowCards _window) {
        super(_window);
        getOwner().getThreadFactory().newStartedThread(new SimulationGamePresident(this));
    }

    @Override
    public void withdrawCards() {
        tapisPresident().setTalonPresident(getWindow().getImageFactory());
//        tapisPresident().repaintValidate();
    }

}
