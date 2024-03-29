package code.expressionlanguage.adv;

import code.gui.AbsPanel;
import code.gui.events.AbsActionListener;
import code.util.CustList;

public final class RefreshLocationTabEvent implements AbsActionListener {

    private final AbsPanel panel;
    private final WindowWithTreeImpl window;
    private final ResultRowSrcLocationList result;
    public RefreshLocationTabEvent(AbsPanel _e, WindowWithTreeImpl _w, ResultRowSrcLocationList _r) {
        this.panel = _e;
        this.window = _w;
        this.result = _r;
    }

    @Override
    public void action() {
        int index_ = window.getEditors().getSelectedIndex();
        act(index_, window, panel, result);
    }

    static void act(int _index, WindowWithTreeImpl _w, AbsPanel _p, ResultRowSrcLocationList _r) {
        CustList<TabEditor> tabs_ = _w.getTabs();
        if (tabs_.isValidIndex(_index)) {
            TabEditor t_ = tabs_.get(_index);
            String relPath_ = t_.getRelPath();
            int caretPosition_ = t_.getCenter().getCaretPosition();
            _w.getFinderSymbol().submitLater(new RefreshLocationTabTask(_p, _w,relPath_,caretPosition_, _r));
        } else {
            _w.getFinderSymbol().submitLater(new RefreshLocationTask(_p, _w, _r));
        }
    }
}
