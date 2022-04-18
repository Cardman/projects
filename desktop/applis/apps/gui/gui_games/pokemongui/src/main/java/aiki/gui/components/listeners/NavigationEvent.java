package aiki.gui.components.listeners;

import aiki.gui.components.Paginator;
import code.gui.events.AbsActionListener;

public abstract class NavigationEvent implements AbsActionListener {

    private Paginator paginator;

    public NavigationEvent(Paginator _paginator) {
        paginator = _paginator;
    }

    @Override
    public void action() {
        process();
    }

    protected Paginator getPaginator() {
        return paginator;
    }

    protected abstract void process();
}
