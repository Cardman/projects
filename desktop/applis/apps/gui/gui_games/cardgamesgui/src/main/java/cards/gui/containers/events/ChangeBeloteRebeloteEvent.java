package cards.gui.containers.events;

import cards.gui.containers.ContainerPlayableBelote;
import code.gui.events.AbsActionListener;

public class ChangeBeloteRebeloteEvent implements AbsActionListener {

    private ContainerPlayableBelote container;

    public ChangeBeloteRebeloteEvent(ContainerPlayableBelote _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.invertBeloteRebelote();
    }
}
