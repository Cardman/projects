package cards.gui.containers.events;

import cards.gui.containers.ContainerMulti;
import code.gui.events.AbsActionListener;

public class ReadyEvent implements AbsActionListener {

    private ContainerMulti container;

    public ReadyEvent(ContainerMulti _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.changeReady();
    }
}
