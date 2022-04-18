package cards.gui.containers.events;

import cards.gui.containers.ContainerPlayablePresident;
import code.gui.events.AbsActionListener;

public class NoPlayPresidentEvent implements AbsActionListener {

    private ContainerPlayablePresident container;

    public NoPlayPresidentEvent(ContainerPlayablePresident _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.noPlay();
    }
}
