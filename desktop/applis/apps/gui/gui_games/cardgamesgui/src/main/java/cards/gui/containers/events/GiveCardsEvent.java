package cards.gui.containers.events;

import cards.gui.containers.ContainerPresident;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class GiveCardsEvent extends AbsMouseListenerRel {

    private ContainerPresident container;

    public GiveCardsEvent(ContainerPresident _container) {
        container = _container;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        container.discard();
    }
}
