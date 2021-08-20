package cards.gui.containers.events;

import cards.gui.containers.ContainerBelote;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class BidEvent extends AbsMouseListenerRel {

    private ContainerBelote container;

    public BidEvent(ContainerBelote _container) {
        container = _container;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        container.bid();
    }
}
