package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class CallersHierarchyEvent implements AbsActionListener {
    private final ResultRowSrcLocationList result;
    private final WindowWithTreeImpl window;

    public CallersHierarchyEvent(ResultRowSrcLocationList _r, WindowWithTreeImpl _w) {
        this.result = _r;
        window = _w;
    }

    @Override
    public void action() {
        window.getFinderSymbol().submitLater(new LookForCallersTask(window,result));
    }

}
