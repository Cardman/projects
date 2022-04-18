package cards.gui.containers.events;

import cards.gui.containers.ContainerSingle;
import code.gui.events.AbsActionListener;

public class StopPlayingEvent implements AbsActionListener {

    private ContainerSingle container;

    public StopPlayingEvent(ContainerSingle _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.stopPlaying();
    }
}
