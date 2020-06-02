package aiki.gui.components.listeners;
import aiki.gui.components.Paginator;

public class EndEvent extends NavigationEvent {

    public EndEvent(Paginator _paginator) {
        super(_paginator);
    }

    @Override
    protected void process() {
        getPaginator().end();
    }

}
