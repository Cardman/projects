package cards.gui.containers.events;

import cards.gui.containers.ContainerPlayableSlam;
import code.gui.events.AbsActionListener;

public class SlamEvent implements AbsActionListener {

    private final ContainerPlayableSlam container;

    public SlamEvent(ContainerPlayableSlam _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.annonceChelem();
    }
}
