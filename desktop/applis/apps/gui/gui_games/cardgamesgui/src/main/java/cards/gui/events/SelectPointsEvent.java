package cards.gui.events;

import cards.gui.containers.ContainerBelote;
import code.gui.events.AbsActionListener;

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
