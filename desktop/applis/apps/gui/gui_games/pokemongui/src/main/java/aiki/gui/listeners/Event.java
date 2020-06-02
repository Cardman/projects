package aiki.gui.listeners;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.components.Paginator;


public class Event extends MouseAdapter {

    private Paginator paginator;

    private int index;

    public Event(Paginator _paginator, int _index) {
        paginator = _paginator;
        index = _index;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        paginator.check(index);
    }
}
