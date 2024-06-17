package cards.gui.containers.events;

import cards.gui.containers.ContainerMulti;
import code.gui.events.AbsActionListener;

public final class ReadyEvent implements AbsActionListener {

    private final ContainerMulti container;

    public ReadyEvent(ContainerMulti _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.getContainerMultiContent().changeReady();
    }
}
