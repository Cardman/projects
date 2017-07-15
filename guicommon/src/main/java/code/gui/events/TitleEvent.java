package code.gui.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.gui.SessionEditorPane;

public class TitleEvent extends MouseAdapter {

    private SessionEditorPane session;

    public TitleEvent(SessionEditorPane _session) {
        session = _session;
    }

    @Override
    public void mouseMoved(MouseEvent _e) {
        session.handleTitle(_e);
    }
}
