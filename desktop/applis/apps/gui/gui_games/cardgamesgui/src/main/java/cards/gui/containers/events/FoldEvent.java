package cards.gui.containers.events;

import cards.gui.containers.ContainerBelote;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

public class FoldEvent implements AbsActionListener {

    private ContainerBelote container;

    public FoldEvent(ContainerBelote _container) {
        container = _container;
    }

    @Override
    public void action() {
        container.fold();
    }
}
