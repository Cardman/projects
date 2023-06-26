package code.expressionlanguage.adv;

import code.gui.events.AbsActionListener;

public final class AddExcludeEvent implements AbsActionListener {
    private final AbsDebuggerGui window;
    private final GuiStackForm form;

    public AddExcludeEvent(GuiStackForm _f,AbsDebuggerGui _w) {
        this.form = _f;
        this.window = _w;
    }

    @Override
    public void action() {
        ReadOnlyFormTabEditor e_ = form.getReadOnlyFormTabEditor();
        form.add(window.getCurrentResult(),form.getMustNotBe(), e_);
        form.actualiseLists(window);
    }
}
