package aiki.gui.components.listeners;

import aiki.gui.components.Paginator;
import code.gui.events.AbsChangeListener;

public class ChangedNbResultsEvent implements AbsChangeListener {

    private Paginator paginator;

    public ChangedNbResultsEvent(Paginator _paginator) {
        paginator = _paginator;
    }

    @Override
    public void stateChanged() {
        paginator.changeNbResults();
    }

}
