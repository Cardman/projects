package code.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.gui.SessionEditorPane;

public class FindEvent extends MouseAdapter {

    private SessionEditorPane session;

    public FindEvent(SessionEditorPane _session) {
        session = _session;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        session.findNext();
    }
}
