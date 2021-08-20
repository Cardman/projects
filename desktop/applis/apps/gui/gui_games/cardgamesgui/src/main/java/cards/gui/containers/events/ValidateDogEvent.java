package cards.gui.containers.events;

import cards.gui.containers.ContainerTarot;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class ValidateDogEvent extends AbsMouseListenerRel {

    private ContainerTarot container;

    public ValidateDogEvent(ContainerTarot _container) {
        container = _container;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        container.validateDog();
    }
}
