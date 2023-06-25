package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.ExecFileBlockTraceIndex;
import code.gui.events.AbsActionListener;

public final class RemoveExcludeEvent implements AbsActionListener {
    private final GuiStackForm form;
    private final AbsDebuggerGui window;
    private final ExecFileBlockTraceIndex elt;

    public RemoveExcludeEvent(GuiStackForm _f,AbsDebuggerGui _w, ExecFileBlockTraceIndex _l) {
        this.form = _f;
        this.window = _w;
        this.elt = _l;
    }

    @Override
    public void action() {
        GuiStackForm.remove(form.getMustNotBe(), elt);
        form.actualiseLists(window);
    }
}
