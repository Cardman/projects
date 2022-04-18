package cards.gui.containers.events;

import cards.gui.containers.ContainerSingleTarot;
import code.gui.events.AbsActionListener;

public class SeeDogEvent implements AbsActionListener {

    private ContainerSingleTarot container;

    public SeeDogEvent(ContainerSingleTarot _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.voirChien();
    }
}
