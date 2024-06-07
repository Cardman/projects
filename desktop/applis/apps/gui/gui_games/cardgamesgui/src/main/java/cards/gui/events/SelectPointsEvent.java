package cards.gui.events;

import cards.gui.containers.ContainerPlayableBelote;
import code.gui.events.AbsActionListener;

public class SelectPointsEvent implements AbsActionListener {

    private final ContainerPlayableBelote container;

    private final int points;

    public SelectPointsEvent(ContainerPlayableBelote _container, int _points) {
        container = _container;
        points = _points;
    }

    @Override
    public void action() {
        container.setPoints(points);
    }
}
