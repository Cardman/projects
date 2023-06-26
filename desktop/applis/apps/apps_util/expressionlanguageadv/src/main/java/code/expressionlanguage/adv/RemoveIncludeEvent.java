package code.expressionlanguage.adv;

import code.expressionlanguage.exec.dbg.AbsCallContraints;
import code.gui.events.AbsActionListener;

public final class RemoveIncludeEvent implements AbsActionListener {
    private final GuiStackForm form;
    private final AbsDebuggerGui window;
    private final AbsCallContraints elt;

    public RemoveIncludeEvent(GuiStackForm _f,AbsDebuggerGui _w, AbsCallContraints _l) {
        this.form = _f;
        this.window = _w;
        this.elt = _l;
    }

    @Override
    public void action() {
        GuiStackForm.remove(form.getMustBe(), elt);
        form.actualiseLists(window);
    }
}
