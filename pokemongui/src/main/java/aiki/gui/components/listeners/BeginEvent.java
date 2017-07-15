package aiki.gui.components.listeners;
import aiki.gui.components.Paginator;


public class BeginEvent extends NavigationEvent {

    public BeginEvent(Paginator _paginator) {
        super(_paginator);
    }

    @Override
    protected void process() {
        getPaginator().begin();
    }
}
