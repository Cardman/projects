package code.gui.adv.event;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import code.gui.adv.SessionEditorPane;

public class HyperlinkClickEvent implements HyperlinkListener {

    private SessionEditorPane session;

    public HyperlinkClickEvent(SessionEditorPane _session) {
        session = _session;
    }

    @Override
    public void hyperlinkUpdate(HyperlinkEvent _e) {
        session.hyperlinkUpdate(_e);
    }
}
