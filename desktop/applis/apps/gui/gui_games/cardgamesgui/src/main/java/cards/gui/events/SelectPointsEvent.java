package cards.gui.events;

import cards.gui.containers.ContainerBelote;
import code.gui.AbsMouseButtons;
import code.gui.AbsMouseKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class SelectPointsEvent extends AbsMouseListenerRel {

    private ContainerBelote container;

    private int points;

    public SelectPointsEvent(ContainerBelote _container, int _points) {
        container = _container;
        points = _points;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsMouseKeyState _keyState, AbsMouseButtons _buttons) {
        container.setPoints(points);
    }
}
