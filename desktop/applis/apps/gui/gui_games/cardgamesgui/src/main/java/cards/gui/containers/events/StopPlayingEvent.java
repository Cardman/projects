package cards.gui.containers.events;

import cards.gui.containers.ContainerSingle;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

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
