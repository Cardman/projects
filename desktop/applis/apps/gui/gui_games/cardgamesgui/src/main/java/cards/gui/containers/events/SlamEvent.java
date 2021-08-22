package cards.gui.containers.events;

import cards.gui.containers.ContainerTarot;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class SlamEvent extends AbsMouseListenerRel {

    private ContainerTarot container;

    public SlamEvent(ContainerTarot _container) {
        container = _container;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        container.annonceTarotChelem();
    }
}
