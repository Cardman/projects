package cards.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import cards.gui.containers.ContainerBelote;

public class SelectPointsEvent extends MouseAdapter {

    private ContainerBelote container;

    private int points;

    public SelectPointsEvent(ContainerBelote _container, int _points) {
        container = _container;
        points = _points;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        container.setPoints(points);
    }
}
