package aiki.gui.components.listeners;
import aiki.gui.components.Paginator;

public class NewSearchEvent extends NavigationEvent {

    public NewSearchEvent(Paginator _paginator) {
        super(_paginator);
    }

    @Override
    protected void process() {
        getPaginator().newSearch();
    }

}
