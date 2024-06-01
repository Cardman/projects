package cards.gui.containers.events;

import cards.gui.containers.*;
import code.gui.events.AbsActionListener;

public class TakeDiscardEvent implements AbsActionListener {

    private final ContainerPlayableBelote container;

    public TakeDiscardEvent(ContainerPlayableBelote _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.prendreCartesChien();
    }
}
