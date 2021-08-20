package cards.gui.containers.events;

import cards.gui.containers.ContainerBelote;
import code.gui.events.AbsActionListener;

public class ChangeBeloteRebeloteEvent implements AbsActionListener {

    private ContainerBelote container;

    public ChangeBeloteRebeloteEvent(ContainerBelote _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.invertBeloteRebelote();
    }
}
