package aiki.gui.components.listeners;
import aiki.gui.components.Paginator;

public class NextEvent extends NavigationEvent {

    public NextEvent(Paginator _paginator) {
        super(_paginator);
    }

    @Override
    protected void process() {
        getPaginator().next();
    }

}
