package code.expressionlanguage.adv;

import code.gui.AbsPanel;
import code.gui.events.AbsActionListener;

public final class RefreshLocationEvent implements AbsActionListener {

    private final AbsPanel panel;
    private final WindowWithTreeImpl window;
    private final ResultRowSrcLocationList result;
    public RefreshLocationEvent(AbsPanel _e, WindowWithTreeImpl _w, ResultRowSrcLocationList _r) {
        panel = _e;
        window = _w;
        result = _r;
    }

    @Override
    public void action() {
        RefreshLocationTabEvent.act(-1,window, panel, result);
    }
}
