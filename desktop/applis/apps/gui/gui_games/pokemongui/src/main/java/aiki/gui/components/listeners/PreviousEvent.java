package aiki.gui.components.listeners;
import aiki.gui.components.Paginator;

public class PreviousEvent extends NavigationEvent {

    public PreviousEvent(Paginator _paginator) {
        super(_paginator);
    }

    @Override
    protected void process() {
        getPaginator().previous();
    }

}
