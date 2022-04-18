package cards.gui.containers.events;

import cards.gui.containers.ContainerTarot;
import code.gui.events.AbsActionListener;

public class TakeDogEvent implements AbsActionListener {

    private ContainerTarot container;

    public TakeDogEvent(ContainerTarot _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.prendreCartesChien();
    }
}
