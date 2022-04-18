package cards.gui.containers.events;

import cards.gui.containers.ContainerPresident;
import code.gui.events.AbsActionListener;

public class NoPlayPresidentEvent implements AbsActionListener {

    private ContainerPresident container;

    public NoPlayPresidentEvent(ContainerPresident _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.noPlay();
    }
}
