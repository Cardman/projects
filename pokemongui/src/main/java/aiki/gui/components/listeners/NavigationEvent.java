package aiki.gui.components.listeners;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.components.Paginator;

public abstract class NavigationEvent extends MouseAdapter {

    private Paginator paginator;

    public NavigationEvent(Paginator _paginator) {
        paginator = _paginator;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        process();
    }

    protected Paginator getPaginator() {
        return paginator;
    }

    protected abstract void process();
}
