package cards.gui.containers.events;

import cards.gui.containers.ContainerMulti;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class PlayNextDealEvent extends AbsMouseListenerRel {

    private ContainerMulti container;

    public PlayNextDealEvent(ContainerMulti _container) {
        container = _container;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        container.dealNext();
    }
}
