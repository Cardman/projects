package aiki.gui.components.listeners;
import aiki.gui.components.Paginator;

public class SearchEvent extends NavigationEvent {

    public SearchEvent(Paginator _paginator) {
        super(_paginator);
    }

    @Override
    protected void process() {
        getPaginator().search();
    }

}
