package cards.gui.containers.events;

import cards.gui.containers.ContainerTarot;
import code.gui.events.AbsActionListener;

public class SlamEvent implements AbsActionListener {

    private ContainerTarot container;

    public SlamEvent(ContainerTarot _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.annonceTarotChelem();
    }
}
