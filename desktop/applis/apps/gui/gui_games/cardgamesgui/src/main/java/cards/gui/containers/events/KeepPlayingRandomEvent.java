package cards.gui.containers.events;

import cards.gui.containers.ContainerSingle;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class KeepPlayingRandomEvent extends AbsMouseListenerRel {

    private ContainerSingle container;

    public KeepPlayingRandomEvent(ContainerSingle _container) {
        container = _container;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        container.keepPlayingRandom();
    }
}
