package aiki.gui.components.listeners;
import aiki.gui.components.Paginator;
import aiki.gui.listeners.DocumentAdaptater;

public final class ChangedDeltaPageEvent extends DocumentAdaptater {

    private Paginator paginator;

    public ChangedDeltaPageEvent(Paginator _paginator) {
        paginator = _paginator;
    }

    @Override
    public void updateText() {
        paginator.changeDeltaPage();
    }

}
