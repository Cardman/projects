package code.expressionlanguage.adv;

import code.gui.AbsTreeGui;
import code.gui.events.AbsActionListener;

public class RefreshRenderDynEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final AbsTreeGui tree;
    private final DbgRootStruct root;
    public RefreshRenderDynEvent(AbsDebuggerGui _w, AbsTreeGui _tr, DbgRootStruct _r) {
        window = _w;
        tree = _tr;
        root = _r;
    }

    @Override
    public void action() {
        window.refreshRenderDyn(tree,root);
    }
}
