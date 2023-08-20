package code.expressionlanguage.adv;

import code.expressionlanguage.exec.variables.ViewPage;
import code.gui.AbsTreeGui;
import code.gui.events.AbsActionListener;

public final class SelectCallStackEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final ViewPage viewPage;
    private final AbsTreeGui treeDetailEvt;
    private final DbgRootStruct treeRoot;

    public SelectCallStackEvent(AbsDebuggerGui _d, ViewPage _v, AbsTreeGui _b, DbgRootStruct _r) {
        window = _d;
        viewPage = _v;
        treeDetailEvt = _b;
        treeRoot = _r;
    }

    @Override
    public void action() {
        window.updateGui(viewPage, treeDetailEvt, treeRoot);
    }
}
