package aiki.gui.components.listeners;
import aiki.gui.components.Paginator;

public class NextDeltaEvent extends NavigationEvent {

    public NextDeltaEvent(Paginator _paginator) {
        super(_paginator);
    }

    @Override
    protected void process() {
        getPaginator().nextDelta();
    }

}
