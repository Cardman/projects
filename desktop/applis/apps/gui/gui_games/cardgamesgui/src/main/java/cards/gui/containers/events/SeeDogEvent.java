package cards.gui.containers.events;

import cards.gui.containers.ContainerSingleTarot;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class SeeDogEvent extends AbsMouseListenerRel {

    private ContainerSingleTarot container;

    public SeeDogEvent(ContainerSingleTarot _container) {
        container = _container;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        container.voirChien();
    }
}
