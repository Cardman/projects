package cards.gui.containers.events;

import cards.gui.containers.ContainerSin;
import code.gui.events.AbsActionListener;

public class StopPlayingEvent implements AbsActionListener {

    private final ContainerSin container;

    public StopPlayingEvent(ContainerSin _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.stopPlaying();
    }
}
