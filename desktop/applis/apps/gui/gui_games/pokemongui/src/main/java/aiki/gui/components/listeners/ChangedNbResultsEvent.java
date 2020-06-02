package aiki.gui.components.listeners;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import aiki.gui.components.Paginator;

public class ChangedNbResultsEvent implements ChangeListener {

    private Paginator paginator;

    public ChangedNbResultsEvent(Paginator _paginator) {
        paginator = _paginator;
    }

    @Override
    public void stateChanged(ChangeEvent _e) {
        paginator.changeNbResults();
    }

}
