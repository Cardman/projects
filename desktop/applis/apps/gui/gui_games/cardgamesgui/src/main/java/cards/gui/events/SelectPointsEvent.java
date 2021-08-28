package cards.gui.events;

import cards.gui.containers.ContainerBelote;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

public class SelectPointsEvent implements AbsActionListener {

    private ContainerBelote container;

    private int points;

    public SelectPointsEvent(ContainerBelote _container, int _points) {
        container = _container;
        points = _points;
    }

    @Override
    public void action() {
        container.setPoints(points);
    }
}
