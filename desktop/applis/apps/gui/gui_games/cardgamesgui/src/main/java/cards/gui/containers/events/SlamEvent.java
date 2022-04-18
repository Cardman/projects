package cards.gui.containers.events;

import cards.gui.containers.ContainerPlayableTarot;
import code.gui.events.AbsActionListener;

public class SlamEvent implements AbsActionListener {

    private ContainerPlayableTarot container;

    public SlamEvent(ContainerPlayableTarot _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.annonceTarotChelem();
    }
}
