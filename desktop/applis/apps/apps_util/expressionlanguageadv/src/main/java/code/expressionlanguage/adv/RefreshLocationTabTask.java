package code.expressionlanguage.adv;

import code.gui.AbsPanel;

public final class RefreshLocationTabTask extends AbsRefreshLocationTabTask {

    private final String relPath;
    private final int car;
    public RefreshLocationTabTask(AbsPanel _e,WindowWithTreeImpl _w, String _r, int _c, ResultRowSrcLocationList _result) {
        super(_e, _w, _result);
        relPath = _r;
        car = _c;
    }

    @Override
    protected int caret() {
        return car;
    }

    @Override
    protected String path() {
        return relPath;
    }
}
