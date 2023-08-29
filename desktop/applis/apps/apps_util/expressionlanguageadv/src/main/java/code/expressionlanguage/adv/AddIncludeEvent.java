package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class AddIncludeEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final GuiStackForm form;

    public AddIncludeEvent(GuiStackForm _f,AbsDebuggerGui _w) {
        this.form = _f;
        this.window = _w;
    }

    @Override
    public void action() {
        ReadOnlyFormTabEditor e_ = form.getReadOnlyFormTabEditor();
        form.add(window.getCurrentResult(),form.getMustBe(), e_);
        form.actualiseLists(window.getCommonFrame());
    }
}
