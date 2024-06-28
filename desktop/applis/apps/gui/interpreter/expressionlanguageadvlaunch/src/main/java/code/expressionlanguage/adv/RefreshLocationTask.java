package code.expressionlanguage.adv;

import code.gui.AbsPanel;

public final class RefreshLocationTask extends AbsRefreshLocationTabTask {

    public RefreshLocationTask(AbsPanel _e, WindowWithTreeImpl _w, ResultRowSrcLocationList _result) {
        super(_e, _w, _result);
    }

    @Override
    protected int caret() {
        return getResult().getCaret();
    }

    @Override
    protected String path() {
        return getResult().getRelPath();
    }
}
