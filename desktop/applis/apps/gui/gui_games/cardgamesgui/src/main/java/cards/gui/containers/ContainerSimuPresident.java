package cards.gui.containers;
import cards.gui.WindowCards;
import cards.gui.animations.SimulationGamePresident;

public class ContainerSimuPresident extends ContainerPresident implements
        ContainerSimu {
    private final WindowCards win;
    public ContainerSimuPresident(WindowCards _window) {
        super(_window);
        win = _window;
        getOwner().getThreadFactory().newStartedThread(new SimulationGamePresident(this,_window));
    }

//    @Override
//    public void withdrawCards() {
//        tapisPresident().setTalonPresident(getWindow().getImageFactory());
////        tapisPresident().repaintValidate();
//    }

    @Override
    public WindowCards window() {
        return win;
    }
}
