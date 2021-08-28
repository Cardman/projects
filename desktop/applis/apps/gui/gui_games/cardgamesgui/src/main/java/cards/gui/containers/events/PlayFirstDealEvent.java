package cards.gui.containers.events;

import cards.gui.containers.ContainerMulti;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

public class PlayFirstDealEvent implements AbsActionListener {

    private ContainerMulti container;

    public PlayFirstDealEvent(ContainerMulti _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.dealFirst();
    }
}
