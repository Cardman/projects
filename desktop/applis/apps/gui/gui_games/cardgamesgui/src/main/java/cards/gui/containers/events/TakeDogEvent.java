package cards.gui.containers.events;

import cards.gui.containers.ContainerPlayableTarot;
import code.gui.events.AbsActionListener;

public class TakeDogEvent implements AbsActionListener {

    private ContainerPlayableTarot container;

    public TakeDogEvent(ContainerPlayableTarot _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.prendreCartesChien();
    }
}
