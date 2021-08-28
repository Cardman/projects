package cards.gui.containers.events;

import cards.gui.containers.ContainerTarot;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

public class ValidateDogEvent implements AbsActionListener {

    private ContainerTarot container;

    public ValidateDogEvent(ContainerTarot _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.validateDog();
    }
}
