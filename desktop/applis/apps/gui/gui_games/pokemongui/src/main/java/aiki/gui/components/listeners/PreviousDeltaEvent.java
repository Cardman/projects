package aiki.gui.components.listeners;
import aiki.gui.components.Paginator;

public class PreviousDeltaEvent extends NavigationEvent {

    public PreviousDeltaEvent(Paginator _paginator) {
        super(_paginator);
    }

    @Override
    protected void process() {
        getPaginator().changeDeltaPage();
        getPaginator().previousDelta();
    }

}
