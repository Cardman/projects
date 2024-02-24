package cards.gui.containers;
import cards.gui.WindowCards;
import cards.gui.animations.SimulationGameBelote;

public class ContainerSimuBelote extends ContainerBelote implements ContainerSimu {

//    private SimulationGameBelote animationSimulation;
    private final WindowCards win;
    public ContainerSimuBelote(WindowCards _window) {
        super(_window);
        win = _window;
//        animationSimulation=new SimulationGameBelote(this,_window);
        thread(new SimulationGameBelote(this,_window));
//        getOwner().getThreadFactory().newStartedThread(new SimulationGameBelote(this,_window));
    }

    @Override
    public void withdrawCards() {
        tapisBelote().retirerCartes();
    }

    @Override
    public WindowCards window() {
        return win;
    }
}

